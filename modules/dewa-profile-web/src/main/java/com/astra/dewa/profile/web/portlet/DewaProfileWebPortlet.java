package com.astra.dewa.profile.web.portlet;

import com.astra.dewa.model.MenuAuthorization;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.profile.web.constants.DewaProfileWebPortletKeys;
import com.astra.dewa.service.MenuAuthorizationLocalServiceUtil;
import com.astra.dewa.service.RolesLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.utils.RoleUtils;
import com.astra.dewa.utils.RolesEnum;
import com.astra.dewa.utils.user.DealinkUserUtil;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import java.util.List;

/**
 * @author psmmutia0113
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Dewa UI",
                "com.liferay.portlet.header-portlet-css=/assets/css/main.css",
                "com.liferay.portlet.instanceable=false",
                "javax.portlet.display-name=Dewa Profile Web",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + DewaProfileWebPortletKeys.DEWAPROFILEWEB,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class DewaProfileWebPortlet extends MVCPortlet {
    private final Log _log = LogFactoryUtil.getLog(DewaProfileWebPortlet.class);

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            String siteDealerURL = themeDisplay.getPortalURL();

            // GET LIFERAY ROLES
            User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

            boolean isAdminLiferay = DealinkUserUtil.isLiferayAdmin(themeDisplay);
            boolean isAdminDealink = DealinkUserUtil.hasRoleGroup(user.getUserId(), RolesEnum.ADMIN_DSO);
            boolean isCMS = themeDisplay.getThemeId().contains("cms");

            String roleUser = "";
            String fullName = "";
            String photoUrl = "";
            if (!isAdminLiferay) {
                roleUser = isAdminDealink ? RolesEnum.ADMIN_DSO.getName() : RolesLocalServiceUtil.getRoles(RoleUtils.getUserRole(user.getUserId())).getName();

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
                renderRequest.setAttribute("isDSODepartment", !isAdminDealink && DealinkUserUtil.hasRoleGroup(user.getUserId(), RolesEnum.DSO));
                renderRequest.setAttribute("isAdminDivision", DealinkUserUtil.hasRoleGroup(user.getUserId(), RolesEnum.ADMIN_DIVISION));
                renderRequest.setAttribute("menus", getMenuByRoleId(RoleUtils.getUserRole(user.getUserId())));
            } else {
                fullName = user.getFullName();
                roleUser = RolesEnum.ADMIN_DSO.getName();
            }

            renderRequest.setAttribute("isCMS", isCMS);
            renderRequest.setAttribute("roleUser", roleUser);
            renderRequest.setAttribute("siteDealerURL", siteDealerURL);
            renderRequest.setAttribute("fullName", fullName);
            renderRequest.setAttribute("photoUrl", photoUrl);
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
        super.render(renderRequest, renderResponse);
    }

    private List<String> getMenuByRoleId(int roleId) {
        List<String> menuList = new ArrayList<>();
        try {
            DynamicQuery dynamicQuery = MenuAuthorizationLocalServiceUtil
                    .dynamicQuery()
                    .add(RestrictionsFactoryUtil.eq("RoleId", roleId))
                    .add(RestrictionsFactoryUtil.eq("RowStatus", true));
            List<MenuAuthorization> menuAuthList = MenuAuthorizationLocalServiceUtil.dynamicQuery(dynamicQuery);

            for (MenuAuthorization menuAuth : menuAuthList) {
                menuList.add(menuAuth.getMenus());
            }
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
        return menuList;
    }
}