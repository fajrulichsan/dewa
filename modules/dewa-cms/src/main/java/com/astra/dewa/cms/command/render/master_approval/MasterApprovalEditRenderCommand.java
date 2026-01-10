package com.astra.dewa.cms.command.render.master_approval;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.MasterApproval;
import com.astra.dewa.model.MasterApprovalDetail;
import com.astra.dewa.service.MasterApprovalDetailLocalServiceUtil;
import com.astra.dewa.service.MasterApprovalLocalServiceUtil;
import com.astra.dewa.utils.CRUDActionKeys;
import com.astra.dewa.utils.RoleDealerUtils;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.MASTER_APPROVAL,
            "mvc.command.name=/master-approval-edit"
      },
      service = {MVCRenderCommand.class}
)
public class MasterApprovalEditRenderCommand implements MVCRenderCommand {
   private final Log LOG = LogFactoryUtil.getLog(MasterApprovalEditRenderCommand.class);

   @Override
   public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));

      String id = (PortalUtil.getOriginalServletRequest(httpServletRequest).getParameter("id"));
      String action = "";
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      JSONArray approvers = JSONFactoryUtil.createJSONArray();

      try {
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         if (Integer.parseInt(id) > 0) {
            action = CRUDActionKeys.UPDATE;
               MasterApproval masterApproval = MasterApprovalLocalServiceUtil.getMasterApproval(Integer.parseInt(id));
               dto.put("id", masterApproval.getId());
               dto.put("roleId", masterApproval.getRoleId());
               dto.put("menuId", masterApproval.getMenuId());
               dto.put("approvalGroup", masterApproval.getApprovalGroup());
               dto.put("isSequential", masterApproval.isApprovalWorkflow());

               // master approval detail
               DynamicQuery query = MasterApprovalDetailLocalServiceUtil.dynamicQuery();
               query.add(RestrictionsFactoryUtil.eq("MasterApprovalId", masterApproval.getId()));
               query.add(RestrictionsFactoryUtil.eq("RowStatus", true));

               List<MasterApprovalDetail> masterApprovalDetails = MasterApprovalDetailLocalServiceUtil.dynamicQuery(query);
               masterApprovalDetails.forEach(data -> {
                  JSONObject approver = JSONFactoryUtil.createJSONObject();
                  approver.put("id", data.getId());
                  approver.put("userId", data.getUserId());
                  approver.put("userName", RoleDealerUtils.getUserName(data.getUserId()));
                  approver.put("approvalLevel", data.getApprovalLevel());
                  approver.put("isDefault", data.getIsDefault());
                  approvers.put(approver);
               });
         } else {
            action = CRUDActionKeys.CREATE;
         }
      } catch (Exception e) {
         action = CRUDActionKeys.INVALID;
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpServletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpServletRequest, "p_auth", "none"), e);
         } else {
            LOG.error(e.getMessage(), e);
         }
      }

      renderRequest.setAttribute("approvers", approvers);
      renderRequest.setAttribute("data", dto);
      renderRequest.setAttribute("action", action);

      return "/master-approval/form.jsp";
   }
}