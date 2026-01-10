package com.astra.dewa.cms.command.action;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.exception.NoSuchRolesException;
import com.astra.dewa.model.MenuAuthorization;
import com.astra.dewa.model.Roles;
import com.astra.dewa.service.MenuAuthorizationLocalServiceUtil;
import com.astra.dewa.service.RolesLocalServiceUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.RoleUtils;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
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

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;
import static com.astra.dewa.utils.JSONResponseFormatUtil.DELETED_NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.DELETED_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

/**
 * @author psmfajru1107
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.MASTER_ROLE,
            "mvc.command.name=/master-role-action"
      },
      service = MVCResourceCommand.class
)
public class MasterRoleActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(MasterRoleActionCommand.class);
   private User user;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      this.user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

      boolean isRequestContainsXSS = false;
      Enumeration<String> attributes = resourceRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = resourceRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = uploadPortletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = uploadPortletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

      try {
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else {
            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
            String roleName = ParamUtil.getString(uploadPortletRequest, "roleName").trim();
            String roles = ParamUtil.getString(uploadPortletRequest, "roles").trim();
            int id = ParamUtil.getInteger(uploadPortletRequest, "id");

            Date date = new Date();

            switch (crudType) {
               case CREATE:
                  jsonObject = create(roleName, roles, date);
                  break;
               case UPDATE:
                  jsonObject = update(id, roleName, roles, date);
                  break;
               case DELETE:
                  jsonObject = delete(id);
                  break;
               default:
                  jsonObject = WARNING("Bad request");
                  break;
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            jsonObject = WARNING("Unauthorized request!");
         } else if (e instanceof SystemException) {
            jsonObject = WARNING(e.getMessage());
         } else {
            jsonObject = WARNING("Bad request");
         }
         LOG.error(e.getMessage(), e);
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject create(String roleName, String roles, Date date) {
      try {
         if (isExist(roleName)) {
            return WARNING("Maaf Role ini sudah terdaftar");
         }

         LOG.info("====== Create New Role ======");
         Roles role = RolesLocalServiceUtil.createRoles(0);
         role.setName(roleName);

         // ROLE MEMBER LIST
         JSONArray jsonRoles = JSONFactoryUtil.createJSONArray(roles);
         if (jsonRoles.length() > 0) {
            int i = -1;
            while (++i < jsonRoles.length()) {
               if (RoleUtils.getRole(jsonRoles.getJSONObject(i).getInt("id"))  == null) {
                  throw new NoSuchRolesException("Maaf Role untuk approval belum terdaftar");
               }
            }
            role.setRoleIdMember(roles);
         } else {
            role.setRoleIdMember(null);
         }

         role.setRowStatus(true);
         role.setCreatedDate(date);
         role.setModifiedDate(date);
         role.setCreatedBy(user.getScreenName());
         role.setModifiedBy(user.getScreenName());
         RolesLocalServiceUtil.addRoles(role);

         return SUCCESS("Data tersimpan", String.valueOf(role.getId()));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(int id, String roleName, String roles, Date date) {
      try {
         Roles roleOld = RolesLocalServiceUtil.getRoles(id);
         if (isExist(roleName) && !roleOld.getName().equals(roleName)) {
            return WARNING("Maaf Role ini sudah terdaftar");
         }

         LOG.info("====== Update Menu Auth ======");
         Roles role = RolesLocalServiceUtil.getRoles(id);
         role.setName(roleName);

         // ROLE MEMBER LIST
         JSONArray jsonRoles = JSONFactoryUtil.createJSONArray(roles);
         if (jsonRoles.length() > 0) {
            int i = -1;
            while (++i < jsonRoles.length()) {
               if (RoleUtils.getRole(jsonRoles.getJSONObject(i).getInt("id"))  == null) {
                  throw new NoSuchRolesException("Maaf Role untuk approval belum terdaftar");
               }
            }
            role.setRoleIdMember(roles);
         } else {
            role.setRoleIdMember(null);
         }

         role.setRowStatus(true);
         role.setModifiedDate(date);
         role.setModifiedBy(user.getScreenName());
         RolesLocalServiceUtil.updateRoles(role);

         return SUCCESS("Data terupdate.", String.valueOf(id));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete(int id) {
      try {
         JSONObject result = JSONFactoryUtil.createJSONObject();
         Roles role = RolesLocalServiceUtil.getRoles(id);
         role.setRowStatus(false);
         role.setModifiedBy(user.getScreenName());
         role.setModifiedDate(new Date());
         RolesLocalServiceUtil.updateRoles(role);

         //delete menu otorisasi
         DynamicQuery dynamicQuery = MenuAuthorizationLocalServiceUtil.dynamicQuery();
         dynamicQuery.add(RestrictionsFactoryUtil.eq("RoleId", role.getId()));
         dynamicQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         List<MenuAuthorization> menuAuthorizations = MenuAuthorizationLocalServiceUtil.dynamicQuery(dynamicQuery);

         if (!menuAuthorizations.isEmpty()) {
            MenuAuthorization menu = menuAuthorizations.get(0);
            menu.setRowStatus(false);
            menu.setModifiedBy(user.getScreenName());
            menu.setModifiedDate(new Date());
            MenuAuthorizationLocalServiceUtil.updateMenuAuthorization(menu);
         }

         result = DELETED_SUCCESS("Data terhapus.", String.valueOf(id));
         return result;
      } catch (Exception e) {
         LOG.error(e);
         return DELETED_NOT_SUCCESS("Data tidak terhapus.", String.valueOf(id));
      }
   }

   private boolean isExist(String roleName) {
      DynamicQuery query = RolesLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Name", roleName));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      long count = RolesLocalServiceUtil.dynamicQueryCount(query);
      return count > 0;
   }
}