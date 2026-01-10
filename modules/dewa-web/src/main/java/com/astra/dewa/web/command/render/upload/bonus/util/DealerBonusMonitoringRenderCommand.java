package com.astra.dewa.web.command.render.upload.bonus.util;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_BONUS_MONITORING,
            "mvc.command.name=upload-bonus-monitoring-dealer"
      },
      service = MVCResourceCommand.class
)
public class DealerBonusMonitoringRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(DealerBonusMonitoringRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      ServiceContext serviceContext = ServiceContextFactory.getInstance(request);
      UsersDealers roleDealer = RoleDealerUtils.userId(serviceContext.getUserId());

      int acknowledge = 0;
      int count = 0;

      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;

      try {
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         assert roleDealer != null;
         if (RoleDealerUtils.isRegularUser(roleDealer.getUserId())) {
            Dealer dealer = DealerLocalServiceUtil.getDealer(roleDealer.getDealerId());
            jsonData = DealerUtils.selects(roleDealer.getRoleId(), dealer.getKodeHo(), roleDealer.getDealerId());
            LOG.debug(jsonData);
            LOG.debug(DealerUtils.selects(roleDealer.getRoleId(), dealer.getKodeHo(), roleDealer.getDealerId()));
         } else {
            jsonData = DealerUtils.selects(roleDealer.getRoleId(), "", roleDealer.getDealerId());
            LOG.debug(jsonData);
            LOG.debug(DealerUtils.selects(roleDealer.getRoleId(), "", roleDealer.getDealerId()));
         }
         acknowledge = 1;
         count = jsonData.length();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpServletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpServletRequest, "p_auth", "none"), e);
            jsonMessage = ERROR(401, "Unauthorized request!");
         } else {
            LOG.error(e);
            jsonMessage = ERROR(500, e.getMessage());
         }
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}