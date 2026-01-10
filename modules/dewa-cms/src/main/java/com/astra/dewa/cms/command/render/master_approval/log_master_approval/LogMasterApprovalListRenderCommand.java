package com.astra.dewa.cms.command.render.master_approval.log_master_approval;

import com.astra.dewa.cms.constants.CmsKeys;
import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.MasterApprovalDetailJournal;
import com.astra.dewa.model.MasterApprovalJournal;
import com.astra.dewa.service.MasterApprovalDetailJournalLocalServiceUtil;
import com.astra.dewa.service.MasterApprovalJournalLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RoleUtils;
import com.astra.dewa.utils.revisi.ApplicationUtils;
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
            "mvc.command.name=/log-master-approval"
      },
      service = {MVCRenderCommand.class}
)
public class LogMasterApprovalListRenderCommand implements MVCRenderCommand {
   private final Log LOG = LogFactoryUtil.getLog(LogMasterApprovalListRenderCommand.class);

   @Override
   public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));

      String id = (PortalUtil.getOriginalServletRequest(httpServletRequest).getParameter("id"));
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();

      try {
         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         DynamicQuery query = MasterApprovalJournalLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("MasterApprovalId", Integer.parseInt(id)))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true));
         List<MasterApprovalJournal> masterApprovalJournals = MasterApprovalJournalLocalServiceUtil.dynamicQuery(query);

         for (int i = 0; i < masterApprovalJournals.size(); i++) {
            MasterApprovalJournal data = masterApprovalJournals.get(i);
            JSONObject history = JSONFactoryUtil.createJSONObject();

            if (i == masterApprovalJournals.size() - 1) {
               history.put("createdDate", DateUtil.dateToString(data.getModifiedDate(), CmsKeys.DATE_FORMAT_DOT));
               history.put("modifiedDate", "-");
            } else {
               history.put("createdDate", DateUtil.dateToString(masterApprovalJournals.get(i).getModifiedDate(), CmsKeys.DATE_FORMAT_DOT));
               history.put("modifiedDate", DateUtil.dateToString(masterApprovalJournals.get(i + 1).getModifiedDate(), CmsKeys.DATE_FORMAT_DOT));
            }

            history.put("id", data.getId());
            history.put("no", i + 1);

            String menuName = "";
            if (data.getMenuId() != 0) {
               menuName = ApplicationUtils.getApplicationName(data.getMenuId());
            }
            history.put("menuName", menuName);

            String roleName = "";
            if (data.getRoleId() != 0) {
               roleName = RoleUtils.getRoleName(data.getRoleId());
            }
            history.put("roleName", roleName);
            history.put("approvalGroup", data.getApprovalGroup());

            DynamicQuery approvalDetailQuery = MasterApprovalDetailJournalLocalServiceUtil.dynamicQuery()
                  .add(RestrictionsFactoryUtil.eq("MasterApprovalId", Integer.parseInt(id)))
                  .add(RestrictionsFactoryUtil.eq("MasterApprovalJournalId", data.getId()))
                  .add(RestrictionsFactoryUtil.eq("RowStatus", true));
            List<MasterApprovalDetailJournal> masterApprovalDetails = MasterApprovalDetailJournalLocalServiceUtil.dynamicQuery(approvalDetailQuery);

            JSONArray approvers = JSONFactoryUtil.createJSONArray();
            for (MasterApprovalDetailJournal approver : masterApprovalDetails) {
               JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

               String approverName = "";
               if (approver.getUserId() != 0) {
                  approverName = RoleDealerUtils.getUserName(approver.getUserId());
               }
               jsonObject.put("name", approverName);
               approvers.put(jsonObject);
            }

            history.put("approvers", approvers);
            jsonData.put(history);
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpServletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpServletRequest, "p_auth", "none"), e);
         } else {
            LOG.error(e.getMessage(), e);
         }
      }

      renderRequest.setAttribute("data", jsonData);

      return "/master-approval/view-detail.jsp";
   }
}
