package com.astra.dewa.web.command.render.upload.bonus.request;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.utils.ApplicationEnum;
import com.astra.dewa.utils.MasterApprovalUtils;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_BONUS_REQUEST,
      "mvc.command.name=upload-bonus-request"
   },
   service = MVCResourceCommand.class
)
public class UploadBonusRequestRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadBonusRequestRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      long userId = themeDisplay.getUser().getUserId();
      String screenName = themeDisplay.getUser().getScreenName();
      String fullName = themeDisplay.getUser().getFullName();
      String email = themeDisplay.getUser().getEmailAddress();

      JSONObject dto = JSONFactoryUtil.createJSONObject();
      dto.put("userId", screenName);
      dto.put("userName", fullName);
      dto.put("email", email);

      try {
         // CSRF AUTHENTICATION
//         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         UsersDealers roleDealer = RoleDealerUtils.userId(userId);

         assert roleDealer != null;
         Dealer dealer = DealerLocalServiceUtil.getDealer(roleDealer.getDealerId());
         dto.put("dealerId", dealer.getCode());
         dto.put("dealerName", dealer.getName());

         // get default approver
         int masterApprovalId = MasterApprovalUtils.getMasterApprovalId(ApplicationEnum.UPLOAD_BONUS.ordinal() + 1);

         User user = MasterApprovalUtils.getDefaultApprover(masterApprovalId);
         if (user != null) {
            dto.put("approverName", user.getFullName());
         } else {
            dto.put("approverName", "-");
         }
      } catch (PortalException e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(request));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(request, "p_auth", "none"), e);
         } else {
            LOG.error(e.getMessage(), e);
         }
      }
      JSONObject jsonObject = SUCCESS("Sukses", "-", dto);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}