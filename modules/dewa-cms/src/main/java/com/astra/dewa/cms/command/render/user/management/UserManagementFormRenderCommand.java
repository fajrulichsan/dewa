package com.astra.dewa.cms.command.render.user.management;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.UserRoleType;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.utils.CabangUtils;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.RoleUtils;
import com.astra.dewa.utils.RolesEnum;
import com.astra.dewa.utils.user.DealinkUserUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.INVALID;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.CRUDActionKeys.VIEW;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + CmsPortletKeys.ROLEDEALERWEB,
                "mvc.command.name=/role-dealer-edit"
        },
        service = {MVCRenderCommand.class}
)
public class UserManagementFormRenderCommand implements MVCRenderCommand {
    private final Log _log = LogFactoryUtil.getLog(UserManagementFormRenderCommand.class);

    @Override
    public String render(RenderRequest request, RenderResponse response) throws PortletException {
        HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        String id = httpServletRequest.getParameter("id");

        String action;
        JSONObject dto = JSONFactoryUtil.createJSONObject();
        JSONArray roles = JSONFactoryUtil.createJSONArray();

        try {
            // permission checker
            boolean hasAccess = DealinkUserUtil.isLiferayAdmin(themeDisplay) || DealinkUserUtil.hasRoleGroup(themeDisplay.getUserId(), new RolesEnum[]{RolesEnum.ADMIN_DSO, RolesEnum.DSO, RolesEnum.ADMIN_DIVISION});
            if (!hasAccess) {
                throw new PrincipalException("You have no authority to access this resource. UserId: " + themeDisplay.getUserId());
            }

            boolean isDivisionAdmin = DealinkUserUtil.hasRoleGroup(themeDisplay.getUserId(), RolesEnum.ADMIN_DIVISION);

            if (Integer.parseInt(id) > 0) {
                action = isDivisionAdmin ? VIEW : UPDATE;

                UsersDealers roleDealer = UsersDealersLocalServiceUtil.getUsersDealers(Integer.parseInt(id));
                User user = UserLocalServiceUtil.getUser(roleDealer.getUserId());

                RolesEnum rolesEnum = DealinkUserUtil.getUserRoleGroupEnum(roleDealer.getUserId());
                int roleGroup = Validator.isNotNull(rolesEnum) ? rolesEnum.getId() : -1;

                dto.put("id", roleDealer.getId());
                dto.put("userId", roleDealer.getUserId());
                dto.put("screenName", user.getScreenName());
                dto.put("fullName", roleDealer.getFullName());
                dto.put("userEmail", user.getEmailAddress());
                dto.put("roleGroup", roleGroup);

                List<UserRoleType> userRoles = DealinkUserUtil.getUserRoleTypes(roleDealer.getUserId());
                if (!userRoles.isEmpty()) {
                    List<Integer> userRoleIds = userRoles.stream().map(UserRoleType::getRoleId).collect(Collectors.toList());
                    for (Integer roleId : userRoleIds) {
                        JSONObject role = JSONFactoryUtil.createJSONObject()
                                .put("id", roleId)
                                .put("isSuperRole", RoleUtils.isAdminDSO(roleId) || RoleUtils.isDSO(roleId))
                                .put("name", RoleUtils.getRoleName(roleId));
                        roles.put(role);
                    }
                }

                if (DealinkUserUtil.hasRoleGroup(roleDealer.getUserId(), DealinkUserUtil.SUPER_ROLES)) {
                    dto.put("dealerId", "-");
                    dto.put("dealerName", "-");
                    dto.put("cabangId", "-");
                    dto.put("cabangName", "-");
                    dto.put("kodeHo", "-");
                } else {
                    Dealer dealer = DealerUtils.getDealer(roleDealer.getDealerId(), null);
                    dto.put("dealerId", null == dealer ? -1 : dealer.getId());
                    dto.put("dealerName", null == dealer ? "" : dealer.getName());
                    dto.put("kodeHo", null == dealer ? "" : dealer.getKodeHo());
                    if (null != dealer && dealer.getCabangId() > 0) {
                        Cabang cabang = CabangUtils.getCabang(dealer.getCabangId(), null);
                        dto.put("cabangId", null == cabang ? "-" : cabang.getId());
                        dto.put("cabangName", null == cabang ? "-" : cabang.getName());
                    } else {
                        dto.put("cabangId", "-");
                        dto.put("cabangName", "-");
                    }
                }
            } else {
                action = CREATE;
            }
        } catch (Exception e) {
            action = INVALID;
            _log.error("Failed to render: " + e.getMessage(), e);
        }

        request.setAttribute("data", dto);
        request.setAttribute("roles", roles);
        request.setAttribute("action", action);
        request.setAttribute("appsName", CmsPortletKeys.APPS_NAME + ":");
        return "/role-dealer/form.jsp";
    }
}
