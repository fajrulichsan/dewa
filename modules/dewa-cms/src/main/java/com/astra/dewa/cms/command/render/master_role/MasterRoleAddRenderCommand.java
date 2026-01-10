package com.astra.dewa.cms.command.render.master_role;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Roles;
import com.astra.dewa.service.RolesLocalServiceUtil;
import com.astra.dewa.utils.RoleUtils;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
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
@Component(immediate = true, property = {
      "javax.portlet.name=" + CmsPortletKeys.MASTER_ROLE,
      "mvc.command.name=/master-role-add"
}, service = { MVCRenderCommand.class })
public class MasterRoleAddRenderCommand implements MVCRenderCommand {
   private final Log LOG = LogFactoryUtil.getLog(MasterRoleAddRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
      String id = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("id");
      String action = "create";

      try {
         int idMenu = Integer.parseInt(id);
         if (idMenu != 0) {
            action = "update";
            request.setAttribute("editMenu", renderEdit(idMenu));
         }
      } catch (Exception e) {
         LOG.error(e);
      }

      request.setAttribute("action", action);
      return "/master-role/form.jsp";
   }

   private JSONObject renderEdit(int id) {
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      try {
         Roles roles = RolesLocalServiceUtil.getRoles(id);
         dto.put("id", id);
         dto.put("name", roles.getName());
         dto.put("roleMember", getRoleMember(id));
      } catch (Exception e) {
         LOG.error(e);
      }
      return dto;
   }

   private JSONArray getRoleMember(int roleId) throws PortalException {
      List<Integer> roleIds = RoleUtils.getRoleIdMember(roleId);

      LOG.info(roleIds);

      DynamicQuery dynamicQuery = RolesLocalServiceUtil.dynamicQuery();
      dynamicQuery.add(RestrictionsFactoryUtil.in("Id", roleIds));
      List<Roles> roles = RolesLocalServiceUtil.dynamicQuery(dynamicQuery);

      LOG.info(roles);

      JSONArray result = JSONFactoryUtil.createJSONArray();

      for (Roles role : roles) {
         JSONObject roleJson = JSONFactoryUtil.createJSONObject();
         roleJson.put("id", role.getId());
         roleJson.put("name", role.getName());
         result.put(roleJson);
      }

      return result;
   }

}