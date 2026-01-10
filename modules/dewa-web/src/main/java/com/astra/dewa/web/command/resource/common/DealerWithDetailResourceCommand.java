package com.astra.dewa.web.command.resource.common;

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

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

/**
 * @author psmahmad1402
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_INDIRECT,
            "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_INDIRECT_NONCMS,
            "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_PAJAK,
            "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_PAJAK_NONCMS,
            "javax.portlet.name=" + DewaWebPortletKeys.REPORT_PLAFOND,
            "javax.portlet.name=" + DewaWebPortletKeys.REPORT_PLAFOND_NONCMS,
            "javax.portlet.name=" + DewaWebPortletKeys.SALES_REPORT,
            "javax.portlet.name=" + DewaWebPortletKeys.SALES_REPORT_NONCMS,
            "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FAKPOL,
            "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FAKPOL_NONCMS,
            "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FLEET,
            "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FLEET_NONCMS,
            "javax.portlet.name=" + DewaWebPortletKeys.DISKON_TEST_CAR,
            "javax.portlet.name=" + DewaWebPortletKeys.DISKON_TEST_CAR_NONCMS,
            "javax.portlet.name=" + DewaWebPortletKeys.E_SRUT,
            "javax.portlet.name=" + DewaWebPortletKeys.E_SRUT_NONCMS,
            "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_BONUS_MONITORING,
            "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_STNK_MONITORING,
            "mvc.command.name=/non-cms/dealers-with-details"
      },
      service = MVCResourceCommand.class
)
public class DealerWithDetailResourceCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(this.getClass().getName());

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;

      try {
         UsersDealers user = RoleDealerUtils.userId(themeDisplay.getUserId());
         assert user != null;
         if (RoleUtils.isHODealer(RoleUtils.getUserRole(user.getUserId())) ||
               RoleUtils.isDealer(RoleUtils.getUserRole(user.getUserId()))) {
            Dealer dealer = DealerLocalServiceUtil.getDealer(user.getDealerId());
            jsonData = DealerUtils.selectCodeNameAndCabang(RoleDealerUtils.getUserRoleGroup(user.getUserId()), dealer.getKodeHo(), user.getDealerId());
         } else {
            jsonData = DealerUtils.selectCodeNameAndCabang();
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
