package com.astra.dewa.web.command.render.e_srut;

import com.astra.dewa.model.ESrut;
import com.astra.dewa.service.ESrutLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
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
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.E_SRUT,
            "mvc.command.name=/e-srut-list"
      },
      service = MVCResourceCommand.class
)
public class ESrutListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(ESrutListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
      String dealerId = httpServletRequest.getParameter("dealerId");
      String periodParam = httpServletRequest.getParameter("periodDate");

      Date periodDate = null;

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();

      try {
         // CSRF AUTHENTICATION
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         DynamicQuery query = ESrutLocalServiceUtil.dynamicQuery();
         if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
         }
         if (!periodParam.equalsIgnoreCase("ALL") && !periodParam.isEmpty()) {
            try {
               SimpleDateFormat sdf = new SimpleDateFormat(DewaWebKeys.DATE_FORMAT_SLASH);
               periodDate = sdf.parse(periodParam);
               query.add(RestrictionsFactoryUtil.eq("PeriodDate", periodDate));
            } catch (ParseException e) {
               LOG.error("Error parsing date: " + e.getMessage());
            }
         }
         List<ESrut> eSrutList = new ArrayList<>();
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
         eSrutList = ESrutLocalServiceUtil.dynamicQuery(query);

         SimpleDateFormat sdf = new SimpleDateFormat(DewaWebKeys.DATE_FORMAT_DOT);

         for (ESrut eSrut : eSrutList) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();

            String fileDate = "";
            if (!eSrut.getModifiedBy().isEmpty()) {
               fileDate = DateUtil.dateToString(eSrut.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H);
            }

            dto.put("no", count);
            dto.put("id", eSrut.getId());
            dto.put("periodDate", HtmlUtil.escape(sdf.format(eSrut.getPeriodDate())));
            dto.put("fileDate", HtmlUtil.escape(fileDate));
            dto.put("fileDateSort", eSrut.getModifiedDate().getTime());
            dto.put("fileId", eSrut.getFileId());
            dto.put("fileName", HtmlUtil.escape(eSrut.getFileName()));
            dto.put("filePath", eSrut.getFilePath());
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = eSrutList.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(resourceRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(resourceRequest, "p_auth", "none"), e);
            jsonMessage = ERROR(401, "Unauthorized request!");
         } else {
            LOG.error(e);
            jsonMessage = ERROR(500, e.getMessage());
         }
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }
}