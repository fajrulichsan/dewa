package com.astra.dewa.cms.command.action;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.MenuAuthorization;
import com.astra.dewa.service.MenuAuthorizationLocalServiceUtil;
import com.astra.dewa.utils.FilterXSS;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.DELETED_NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.DELETED_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + CmsPortletKeys.OTORISASIMENU,
                "mvc.command.name=/otorisasi-menu-action"
        },
        service = MVCResourceCommand.class
)
public class OtorisasiMenuActionCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(OtorisasiMenuActionCommand.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

        boolean isRequestContainsXSS = false;
        Enumeration<String> attributes = resourceRequest.getParameterNames();
        while (attributes.hasMoreElements()) {
            String param = attributes.nextElement();
            String value = resourceRequest.getParameter(param);
            if (FilterXSS.checkXSS(value)) {
                _log.warn(value + " contains XSS payload");
                isRequestContainsXSS = true;
                break;
            }
        }

        attributes = uploadPortletRequest.getParameterNames();
        while (attributes.hasMoreElements()) {
            String param = attributes.nextElement();
            String value = uploadPortletRequest.getParameter(param);
            if (FilterXSS.checkXSS(value)) {
                _log.warn(value + " contains XSS payload");
                isRequestContainsXSS = true;
                break;
            }
        }

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
        } else {
            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
            int id = ParamUtil.getInteger(uploadPortletRequest, "id");
            int roleId = ParamUtil.getInteger(uploadPortletRequest, "roleId");
            String menus = ParamUtil.getString(uploadPortletRequest, "menus", "");
            boolean isRssp = ParamUtil.getBoolean(uploadPortletRequest, "isRssp");
            boolean isCmsDso = ParamUtil.getBoolean(uploadPortletRequest, "isCmsDso");

            Date date = new Date();
            if (crudType.equalsIgnoreCase("create")) {
                jsonObject = create(themeDisplay, roleId, menus, isRssp, isCmsDso, date);
            } else if (crudType.equalsIgnoreCase("delete")) {
                jsonObject = delete(themeDisplay, id, date);
            } else if (crudType.equalsIgnoreCase("edit")) {
                jsonObject = update(themeDisplay, id, roleId, menus, isRssp, isCmsDso, date);
            }
        }
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
    }

    private JSONObject create(ThemeDisplay themeDisplay, int roleId, String menus, boolean isRssp, boolean isCmsDso, Date date) {
        try {
            if (isExist(roleId)) {
                return WARNING("Maaf Role ini sudah terdaftar");
            }

            _log.info("==== CREATING MENU AUTHORIZATION ====");
            MenuAuthorization menuAuth = MenuAuthorizationLocalServiceUtil.createMenuAuthorization(0);
            menuAuth.setRoleId(roleId);
            menuAuth.setMenus(menus);
            menuAuth.setIsRssp(isRssp);
            menuAuth.setIsCmsDso(isCmsDso);

            menuAuth.setRowStatus(true);
            menuAuth.setCreatedDate(date);
            menuAuth.setCreatedBy(themeDisplay.getUser().getScreenName());
            menuAuth.setModifiedDate(date);
            menuAuth.setModifiedBy(themeDisplay.getUser().getScreenName());
            MenuAuthorizationLocalServiceUtil.addMenuAuthorization(menuAuth);

            return SUCCESS("Data tersimpan", String.valueOf(menuAuth.getId()));
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            return NOT_SUCCESS(e.getMessage());
        }
    }

    private JSONObject update(ThemeDisplay themeDisplay, int id, int roleId, String menus, boolean isRssp, boolean isCmsDso, Date date) {
        try {
            _log.info("====== UPDATING MENU AUTHORIZATION ========");
            MenuAuthorization menu = MenuAuthorizationLocalServiceUtil.getMenuAuthorization(id);
            menu.setRoleId(roleId);
            menu.setMenus(menus);
            menu.setIsRssp(isRssp);
            menu.setIsCmsDso(isCmsDso);

            menu.setRowStatus(true);
            menu.setModifiedDate(date);
            menu.setModifiedBy(themeDisplay.getUser().getScreenName());
            MenuAuthorizationLocalServiceUtil.updateMenuAuthorization(menu);

            return SUCCESS("Data terupdate.", String.valueOf(id));
        } catch (Exception e) {
            _log.error(e);
            return NOT_SUCCESS(e.getMessage());
        }
    }

    private JSONObject delete(ThemeDisplay themeDisplay, int id, Date date) {
        try {
            MenuAuthorization menu = MenuAuthorizationLocalServiceUtil.getMenuAuthorization(id);
            menu.setRowStatus(false);
            menu.setModifiedDate(date);
            menu.setModifiedBy(themeDisplay.getUser().getScreenName());
            MenuAuthorizationLocalServiceUtil.updateMenuAuthorization(menu);

            return DELETED_SUCCESS("Data terhapus.", String.valueOf(id));
        } catch (Exception e) {
            _log.error(e);
            return DELETED_NOT_SUCCESS("Data tidak terhapus.", String.valueOf(id));
        }
    }

    private boolean isExist(int roleId) {
        _log.info("Checking role authorization availability.");
        DynamicQuery query = MenuAuthorizationLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("RoleId", roleId))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<MenuAuthorization> menuAuthorizationList = MenuAuthorizationLocalServiceUtil.dynamicQuery(query);
        return !menuAuthorizationList.isEmpty();
    }

}
