package com.astra.dewa.cms.command.render.upload.bonus.util;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.utils.ApplicationEnum;
import com.astra.dewa.utils.TahunUtils;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.UPLOAD_BONUS,
            "mvc.command.name=upload-bonus-tahun"
      },
      service = MVCResourceCommand.class
)
public class TahunBonusRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(TahunBonusRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(request);

      int acknowledge = 0;
      int count = 0;

      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;

      try {
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         jsonData = getTahun();
         acknowledge = 1;
         count = jsonData.length();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpServletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpServletRequest, "p_auth", "none"), e);
            jsonMessage = ERROR(401, "Unauthorized request!");
         } else {
            LOG.error(e.getMessage());
            jsonMessage = ERROR(500, e.getMessage());
         }
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

   private JSONArray getTahun() {
      JSONArray result = JSONFactoryUtil.createJSONArray();
      try {
         List<Integer> listYear = new ArrayList<>();
         DynamicQuery query = ApplicationHeaderLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("ApplicationId", ApplicationEnum.UPLOAD_BONUS.ordinal() + 1));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         List<ApplicationHeader> applicationHeaders = ApplicationHeaderLocalServiceUtil.dynamicQuery(query);

         for (ApplicationHeader applicationHeader : applicationHeaders) {
            Integer tahun = applicationHeader.getReqYear();
            if (!listYear.contains(tahun)) {
               listYear.add(tahun);
            }
         }
         Collections.sort(listYear);

         result = TahunUtils.listTahun(listYear);

      } catch (Exception e) {
         LOG.error(e);
      }
      return result;
   }
}