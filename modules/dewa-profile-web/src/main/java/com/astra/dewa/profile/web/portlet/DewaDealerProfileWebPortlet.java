package com.astra.dewa.profile.web.portlet;

import com.astra.dewa.model.MenuAuthorization;
import com.astra.dewa.model.UserRoleType;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.profile.web.constants.DewaProfileWebPortletKeys;
import com.astra.dewa.service.MenuAuthorizationLocalServiceUtil;
import com.astra.dewa.service.UserRoleTypeLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RolesEnum;
import com.astra.dewa.utils.user.DealinkUserUtil;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author psmfajru1107
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Dewa UI",
                "com.liferay.portlet.header-portlet-css=/assets/css/main.css",
                "com.liferay.portlet.instanceable=false",
                "javax.portlet.display-name=Dewa Dealer Profile Web",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/dealer-view.jsp",
                "javax.portlet.name=" + DewaProfileWebPortletKeys.DEWA_DEALER_PROFILE_WEB,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class DewaDealerProfileWebPortlet extends MVCPortlet {
    private final Log _log = LogFactoryUtil.getLog(DewaDealerProfileWebPortlet.class);

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            String siteDealerURL = themeDisplay.getPortalURL();

            // GET LIFERAY ROLES
            User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
            List<Role> userRoles = user.getRoles();
            List<String> userRolesString = new ArrayList<>();

            if (!userRoles.isEmpty()) {
                for (Role role : userRoles) {
                    userRolesString.add(role.getName());
                }
            }

            boolean isAdminLiferay = userRolesString.contains(RolesEnum.ADMIN_DSO.getName());
            boolean isDSO = false;
            boolean isAdmin = false;
            boolean isRssp = false;
            boolean isCmsDso = false;
            boolean isHoDealer = false;
            boolean isAdminDivision = false;

            String fullName = "";
            String photoUrl = "";
            if (!isAdminLiferay) {
                isDSO = RoleDealerUtils.getUserRoleGroup(user.getUserId()) == RolesEnum.ADMIN_DSO.getId() ||
                        RoleDealerUtils.getUserRoleGroup(user.getUserId()) == RolesEnum.ADMIN_DIVISION.getId() ||
                        RoleDealerUtils.getUserRoleGroup(user.getUserId()) == RolesEnum.DSO.getId();

                // Admin Dealink
                isAdmin = RoleDealerUtils.getUserRoleGroup(user.getUserId()) == RolesEnum.ADMIN_DSO.getId();

                // Admin Department
                isAdminDivision = RoleDealerUtils.getUserRoleGroup(user.getUserId()) == RolesEnum.ADMIN_DIVISION.getId();

                // HO Dealer
                isHoDealer = RoleDealerUtils.getUserRoleGroup(user.getUserId()) == RolesEnum.HO_DEALER.getId();

                // retrieves user rssp role authorization
                isRssp = DealinkUserUtil.hasRsspRole(user.getUserId());

                //  retrieves user cms dso role authorization
                isCmsDso = DealinkUserUtil.hasCmsDsoRole(user.getUserId());

                UsersDealers usersDealers = UsersDealersLocalServiceUtil.findUsersDealersIdFromUserId(themeDisplay.getUserId(), true);
                if (usersDealers.getFullName().isEmpty()) {
                    fullName = themeDisplay.getUser().getFullName();
                } else {
                    fullName = usersDealers.getFullName();
                }

                if (Validator.isNotNull(usersDealers.getPhotoFileId())) {
                    FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(usersDealers.getPhotoFileId());
                    photoUrl = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" +
                            themeDisplay.getScopeGroupId() + "/" + fileEntry.getFolderId() + "/" + fileEntry.getFileName();
                }
            } else {
                fullName = user.getFullName();
            }

            renderRequest.setAttribute("isDSO", isDSO);
            renderRequest.setAttribute("siteDealerURL", siteDealerURL);
            renderRequest.setAttribute("fullName", fullName);
            renderRequest.setAttribute("photoUrl", photoUrl);
            renderRequest.setAttribute("isAdmin", isAdmin || isAdminLiferay);
            renderRequest.setAttribute("menus", getUserMenus(user.getUserId()));
            renderRequest.setAttribute("isRssp", isRssp);
            renderRequest.setAttribute("isCmsDso", isCmsDso);
            renderRequest.setAttribute("isAdminDivision", isAdminDivision);
            renderRequest.setAttribute("isHoDealer", isHoDealer);
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
        super.render(renderRequest, renderResponse);
    }

    private Set<String> getUserMenus(Long userId) {
        Set<String> uniqueMenus = new HashSet<>();
        try {
            // retrieves user roles
            List<UserRoleType> urtl = DealinkUserUtil.getUserRoleTypes(userId);
            for (UserRoleType urt : urtl) {
                DynamicQuery menuQuery = MenuAuthorizationLocalServiceUtil.
                        dynamicQuery()
                        .add(RestrictionsFactoryUtil.eq("RoleId", urt.getRoleId()))
                        .add(RestrictionsFactoryUtil.eq("RowStatus", true));
                List<MenuAuthorization> menuAuthList = MenuAuthorizationLocalServiceUtil.dynamicQuery(menuQuery);

                // retrieves role menu authorization
                for (MenuAuthorization menuAuth : menuAuthList) {
                    String menu = menuAuth.getMenus();
                    if (Validator.isNotNull(menu.trim())) {
                        uniqueMenus.add(menu);
                    }
                }
            }
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
        return uniqueMenus;
    }
}
