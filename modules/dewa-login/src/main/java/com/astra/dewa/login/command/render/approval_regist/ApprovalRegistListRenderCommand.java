package com.astra.dewa.login.command.render.approval_regist;

import com.astra.dewa.login.constants.DewaLoginPortletKeys;
import com.astra.dewa.model.ApprovalHeaderUser;
import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.service.ApprovalDetailUserLocalServiceUtil;
import com.astra.dewa.service.ApprovalHeaderUserLocalServiceUtil;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.RolesLocalServiceUtil;
import com.astra.dewa.utils.ADB2C.ADB2CService;
import com.astra.dewa.utils.ApplicationAssignStatusEnum;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RolesEnum;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
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
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

/**
 * @author psmmutia0113
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaLoginPortletKeys.APPROVAL_REGISTER,
            "mvc.command.name=/approval-regist-list"
      },
      service = MVCResourceCommand.class
)
public class ApprovalRegistListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(this.getClass().getName());

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      String roleId = httpServletRequest.getParameter("roleId");

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;
      boolean isAdminDepartment = false;

      try {
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         List<String> userLiferayRoles = ADB2CService.getUserLiferayRoles(themeDisplay.getUserId());
         boolean isAdminLiferay = userLiferayRoles.contains(RolesEnum.ADMIN_DSO.getName());

         // ACTIVE REQUEST
         DynamicQuery appHeaderQuery = ApprovalHeaderUserLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("ApplicationAssignStatusId", ApplicationAssignStatusEnum.WAITING.getId()))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true))
               .setProjection(ProjectionFactoryUtil.property("Id"));

         // ACTIVE REQUEST DETAIL
         DynamicQuery appHeaderDetailQuery = ApprovalDetailUserLocalServiceUtil.dynamicQuery()
               .add(PropertyFactoryUtil.forName("ApprovalHeaderUserId").in(appHeaderQuery))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true));

         // USER IS ADMIN DEPARTMENT
         if (!isAdminLiferay && RoleDealerUtils.isAdminDepartment(themeDisplay.getUserId())) {
            appHeaderDetailQuery.add(RestrictionsFactoryUtil.in("RoleId", RoleDealerUtils.getAdminRoleIdMember(themeDisplay.getUserId())));
         } else if ((!roleId.equalsIgnoreCase("ALL") && !roleId.isEmpty())) {
            appHeaderDetailQuery.add(RestrictionsFactoryUtil.eq("RoleId", Integer.parseInt(roleId)));
         }
         appHeaderDetailQuery.setProjection(ProjectionFactoryUtil.distinct(
               ProjectionFactoryUtil.property("ApprovalHeaderUserId"))
         );

         List<Integer> requestDetails = ApprovalDetailUserLocalServiceUtil.dynamicQuery(appHeaderDetailQuery);

         if (!requestDetails.isEmpty()) {
            DynamicQuery query = ApprovalHeaderUserLocalServiceUtil.dynamicQuery()
                  .add(PropertyFactoryUtil.forName("Id").in(requestDetails));

            List<ApprovalHeaderUser> approvalHeaderUserList = ApprovalHeaderUserLocalServiceUtil.dynamicQuery(query);

            for (ApprovalHeaderUser approvalHeaderUser : approvalHeaderUserList) {
               Dealer dealer = DealerLocalServiceUtil.getDealer(approvalHeaderUser.getDealerId());
               Cabang cabang = CabangLocalServiceUtil.getCabang(approvalHeaderUser.getCabangId());

               DynamicQuery appDetailUserQuery = ApprovalDetailUserLocalServiceUtil.dynamicQuery()
                     .add(RestrictionsFactoryUtil.eq("ApprovalHeaderUserId", approvalHeaderUser.getId()))
                     .add(RestrictionsFactoryUtil.eq("RowStatus", true));

               if (!isAdminLiferay && RoleDealerUtils.isAdminDepartment(themeDisplay.getUserId())) {
                  appDetailUserQuery.add(RestrictionsFactoryUtil.in("RoleId", RoleDealerUtils.getAdminRoleIdMember(themeDisplay.getUserId())));
               } else if (!roleId.equalsIgnoreCase("ALL") && !roleId.isEmpty()) {
                  appDetailUserQuery.add(RestrictionsFactoryUtil.eq("RoleId", Integer.parseInt(roleId)));
               }

               appDetailUserQuery.setProjection(ProjectionFactoryUtil.property("RoleId"));

               List<Integer> appDetailRoles = ApprovalDetailUserLocalServiceUtil.dynamicQuery(appDetailUserQuery);

               for (Integer detailRoleId : appDetailRoles) {
                  String roleName = "";
                  if (detailRoleId != null) {
                     roleName = RolesLocalServiceUtil.getRoles(detailRoleId).getName();
                  }

                  count++;
                  JSONObject dto = JSONFactoryUtil.createJSONObject();
                  dto.put("no", count);
                  dto.put("id", approvalHeaderUser.getId());
                  dto.put("fullName", approvalHeaderUser.getRequesterName());
                  dto.put("userEmail", approvalHeaderUser.getRequesterEmail());
                  dto.put("roleId", detailRoleId);
                  dto.put("roleName", roleName);
                  dto.put("dealerId", dealer.getId());
                  dto.put("dealerName", dealer.getName());
                  dto.put("cabangId", cabang.getId());
                  dto.put("cabangName", cabang.getName());
                  jsonData.put(dto);
               }
            }
         }
         acknowledge = 1;
         isAdminDepartment = RoleDealerUtils.isAdminDepartment(themeDisplay.getUserId());
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(request));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(request, "p_auth", "none"), e);
            jsonMessage = ERROR(401, "Unauthorized request!");
         } else {
            LOG.error(e);
            jsonMessage = ERROR(500, e.getMessage());
         }
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      jsonObject.put("isDepartment", isAdminDepartment);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}
