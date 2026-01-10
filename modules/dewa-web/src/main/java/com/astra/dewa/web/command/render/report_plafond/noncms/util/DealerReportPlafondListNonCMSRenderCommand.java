package com.astra.dewa.web.command.render.report_plafond.noncms.util;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RoleUtils;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

/**
 * @author psmafifd1401
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.REPORT_PLAFOND_NONCMS,
            "mvc.command.name=/non-cms/report-plafond/dealers"
      },
      service = MVCResourceCommand.class
)
public class DealerReportPlafondListNonCMSRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(DealerReportPlafondListNonCMSRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      UsersDealers user = RoleDealerUtils.userId(themeDisplay.getUserId());

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;
      try {
         assert user != null;
         if (!RoleUtils.isAdminDSO(RoleUtils.getUserRole(user.getUserId())) ||
               !RoleUtils.isDSO(RoleUtils.getUserRole(user.getUserId()))) {
            Dealer dealer = DealerLocalServiceUtil.getDealer(user.getDealerId());
            jsonData = DealerUtils.selects(user.getRoleId(), dealer.getKodeHo(), user.getDealerId());
         } else {
            jsonData = DealerUtils.selects();
         }
         acknowledge = 1;
         count = jsonData.length();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         LOG.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }
}
