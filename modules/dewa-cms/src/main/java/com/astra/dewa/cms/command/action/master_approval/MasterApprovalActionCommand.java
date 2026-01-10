package com.astra.dewa.cms.command.action.master_approval;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.exception.NoSuchRolesException;
import com.astra.dewa.model.MasterApproval;
import com.astra.dewa.service.MasterApprovalLocalServiceUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.cms.command.action.master_approval.service.MasterApprovalServiceImpl;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RoleUtils;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
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
import java.util.Enumeration;

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

/**
 * @author psmahmad1402
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.MASTER_APPROVAL,
            "mvc.command.name=/master-approval-action-command"
      },
      service = MVCResourceCommand.class
)
public class MasterApprovalActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(MasterApprovalActionCommand.class);
   private final MasterApprovalServiceImpl masterApprovalService = new MasterApprovalServiceImpl();
   private UploadPortletRequest uploadPortletRequest;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      this.uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

      // XSS check start
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
      // XSS check end

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

      try {
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else {
            String crudType = ParamUtil.getString(resourceRequest, "crudType");
            int entryId = ParamUtil.getInteger(resourceRequest, "entryId");
            String approverList = ParamUtil.getString(uploadPortletRequest, "approverList");

            MasterApproval masterApproval = null;

            switch (crudType) {
               case CREATE:
                  masterApproval = MasterApprovalLocalServiceUtil.createMasterApproval(0);
                  setData(masterApproval, approverList);
                  jsonObject = masterApprovalService.createMasterApproval(masterApproval, user, approverList);
                  break;
               case UPDATE:
                  masterApproval = MasterApprovalLocalServiceUtil.getMasterApproval(entryId);
                  setData(masterApproval, approverList);
                  jsonObject = masterApprovalService.updateMasterApproval(masterApproval, user, approverList);
                  break;
               case DELETE:
                  jsonObject = masterApprovalService.deleteMasterApproval(entryId, user);
                  break;
               default:
                  jsonObject = WARNING("Bad request!");
            }

            /*
            int roleId = ParamUtil.getInteger(resourceRequest, "roleId");
            int menuId = ParamUtil.getInteger(resourceRequest, "menuId");
            String approvalGroup = ParamUtil.getString(resourceRequest, "approvalGroup");
            boolean isSequential = ParamUtil.getBoolean(resourceRequest, "isSequential");
            String approverList = ParamUtil.getString(resourceRequest, "approverList");

            // Parameter Validation
            if (!RoleUtils.isRoleValid(roleId)) {
               throw new NullPointerException("Role yang dipilih tidak tersedia");
            }

            JSONArray approvers = JSONFactoryUtil.createJSONArray(approverList);
            for (int i = 0; i < approvers.length(); i++) {
               long userId = approvers.getJSONObject(i).getLong("userId");
               if (RoleDealerUtils.userId(userId) == null) {
                  throw new NullPointerException("User tidak terdaftar");
               };
            }

            MasterApproval masterApproval;
            if (crudType.equalsIgnoreCase(CREATE)) {
               masterApproval = MasterApprovalLocalServiceUtil.createMasterApproval(entryId);
            } else {
               masterApproval = MasterApprovalLocalServiceUtil.getMasterApproval(entryId);
            }
            masterApproval.setRoleId(roleId);
            masterApproval.setMenuId(menuId);
            masterApproval.setApprovalGroup(approvalGroup);
            masterApproval.setApprovalWorkflow(isSequential);
            masterApproval.setRowStatus(true);

            boolean isExist = masterApprovalService.isDataExist(entryId, menuId);

            if (isExist) {
               jsonObject = ERROR("Menu approval sudah memiliki role.");
            } else {
               if (crudType.equalsIgnoreCase(CREATE)) {
                  jsonObject = masterApprovalService.createMasterApproval(masterApproval, user, approverList);
               } else if (crudType.equalsIgnoreCase(UPDATE)) {
                  jsonObject = masterApprovalService.updateMasterApproval(masterApproval, user, approverList);
               } else if (crudType.equalsIgnoreCase(DELETE)) {
                  jsonObject = masterApprovalService.deleteMasterApproval(entryId, user);
               }
            }
            */
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            jsonObject = ERROR(401, "Unauthorized request!");
         } else if (e instanceof NullPointerException || e instanceof SystemException) {
            jsonObject = ERROR(404, e.getMessage());
         } else {
            jsonObject = ERROR(500, "Internal Server Error");
         }
         LOG.error(e.getMessage(), e);
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private void setData(MasterApproval masterApproval, String approverList) throws SystemException, NoSuchRolesException, JSONException {
      int roleId = ParamUtil.getInteger(uploadPortletRequest, "roleId");
      int menuId = ParamUtil.getInteger(uploadPortletRequest, "menuId");
      String approvalGroup = ParamUtil.getString(uploadPortletRequest, "approvalGroup");
      boolean isSequential = ParamUtil.getBoolean(uploadPortletRequest, "isSequential");

      // Parameter Validation
      if (!RoleUtils.isRoleValid(roleId)) {
         throw new NullPointerException("Role yang dipilih tidak tersedia");
      }

      JSONArray approvers = JSONFactoryUtil.createJSONArray(approverList);

      for (int i = 0; i < approvers.length(); i++) {
         long userId = approvers.getJSONObject(i).getLong("userId");
         if (RoleDealerUtils.userId(userId) == null) {
            throw new NullPointerException("User tidak terdaftar");
         };
      }

      masterApproval.setRoleId(roleId);
      masterApproval.setMenuId(menuId);
      masterApproval.setApprovalGroup(approvalGroup);
      masterApproval.setApprovalWorkflow(isSequential);
      masterApproval.setRowStatus(true);

      boolean isExist = masterApprovalService.isDataExist(masterApproval.getId(), menuId);

      if (isExist) {
         throw new SystemException("Menu approval sudah memiliki role.");
      }
   }
}