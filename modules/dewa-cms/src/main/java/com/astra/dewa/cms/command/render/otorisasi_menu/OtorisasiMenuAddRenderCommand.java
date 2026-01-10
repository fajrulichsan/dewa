package com.astra.dewa.cms.command.render.otorisasi_menu;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.MenuAuthorization;
import com.astra.dewa.model.Roles;
import com.astra.dewa.service.MenuAuthorizationLocalServiceUtil;
import com.astra.dewa.service.RolesLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author psmfajru1107
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + CmsPortletKeys.OTORISASIMENU,
                "mvc.command.name=/otorisasi-menu-add"
        },
        service = MVCRenderCommand.class
)
public class OtorisasiMenuAddRenderCommand implements MVCRenderCommand {
    private final Log _log = LogFactoryUtil.getLog(OtorisasiMenuAddRenderCommand.class);

    @Override
    public String render(RenderRequest request, RenderResponse response) throws PortletException {
        HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

        String id = httpServletRequest.getParameter("id");

        String action = "create";

        int idMenu = Integer.parseInt(id);
        if (idMenu != 0) {
            action = "edit";
            request.setAttribute("editMenu", renderEdit(idMenu));
        }
        _log.info("Add");

        request.setAttribute("roles", getRoleList());
        request.setAttribute("action", action);
        return "/otorisasi-menu/form.jsp";
    }

    private JSONObject renderEdit(int id) {
        JSONObject dto = JSONFactoryUtil.createJSONObject();
        try {
            MenuAuthorization menu = MenuAuthorizationLocalServiceUtil.getMenuAuthorization(id);
            dto.put("id", id)
                    .put("roleId", menu.getRoleId())
                    .put("menus", menu.getMenus())
                    .put("isRssp", menu.getIsRssp())
                    .put("isCmsDso", menu.getIsCmsDso());
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
        return dto;
    }

    public JSONArray getRoleList() {
        DynamicQuery dynamicQuery = RolesLocalServiceUtil
                .dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                .add(RestrictionsFactoryUtil.ne("Name", "Administrator"));
        List<Roles> rolesList = RolesLocalServiceUtil.dynamicQuery(dynamicQuery);

        JSONArray rolesArray = JSONFactoryUtil.createJSONArray();

        for (Roles role : rolesList) {
            JSONObject roleObject = JSONFactoryUtil
                    .createJSONObject()
                    .put("roleId", role.getId())
                    .put("roleName", role.getName());
            rolesArray.put(roleObject);
        }
        return rolesArray;
    }
}