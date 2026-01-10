package com.astra.dewa.web.command.render.upload;

import com.astra.dewa.model.ApplicationCategory;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.model.ApplicationHeaderStatus;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.ApplicationCategoryLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderStatusLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.utils.ApplicationEnum;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.MasterApprovalUtils;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.INVALID;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_STNK_MONITORING,
            "mvc.command.name=upload-stnk-monitoring-edit"
      },
      service = {MVCRenderCommand.class}
)
public class UploadStnkMonitoringEditRenderCommand implements MVCRenderCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadStnkMonitoringEditRenderCommand.class);

   @Override
   public String render(RenderRequest request, RenderResponse response) throws PortletException {
      HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      String action;

      JSONObject dto = JSONFactoryUtil.createJSONObject();

      try {
//         AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

         User activeUser = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
         String id = ParamUtil.getString(httpServletRequest, "id");
         String p_auth = ParamUtil.getString(httpServletRequest, "p_auth");
         LOG.debug("request id: " + id);
         LOG.debug("p_auth" + p_auth);

         UsersDealers roleDealer = RoleDealerUtils.userId(activeUser.getUserId());

         int headerId = Integer.parseInt(id);
         if (headerId > 0) {
            action = UPDATE;

            ApplicationHeader header = ApplicationHeaderLocalServiceUtil.getApplicationHeader(headerId);
            dto.put("id", header.getId());

            // General Information
            if (header.getReqDate() != null) {
               dto.put("ticketDate", DateUtil.dateToString(header.getReqDate()));
               dto.put("ticketHour", DateUtil.localDateTimeToStringHours(header.getReqDate()));
            } else {
               dto.put("ticketDate", "-");
               dto.put("ticketHour", "-");
            }
            dto.put("ticketNumber", header.getTicketNo());
            dto.put("userId", header.getReqScreenName());
            dto.put("userName", header.getReqName());

            if (header.getDealerId() > 0) {
               Dealer dealer = DealerLocalServiceUtil.getDealer(header.getDealerId());
               dto.put("dealerId", dealer.getId());
               dto.put("dealerName", dealer.getName());
            } else {
               dto.put("dealerId", "-");
               dto.put("dealerName", "-");
            }

            // Contact Information
            dto.put("email", header.getReqEmail());
            dto.put("emailCC", header.getReqCCEmail());
            dto.put("phone", header.getReqPhone());

            // Request Information
            ApplicationCategory category = ApplicationCategoryLocalServiceUtil.getApplicationCategory(header.getApplicationCategoryId());
            dto.put("requestBonusId", category.getId());
            dto.put("requestBonusName", category.getName());
            dto.put("nominalPengajuan", header.getNominalPengajuan());
            dto.put("reqYearId", header.getReqYear());
            dto.put("requestDescription", header.getReqDesc());
            dto.put("businessBenefit", header.getBusinessBenefit());

            // Attachment
            dto.put("attachmentId", header.getFileId());
            dto.put("attachmentName", header.getFileName());
            dto.put("attachmentPath", header.getFileUrl());

            // Notes
            dto.put("notes", header.getNotes());
            dto.put("notesHistory", header.getNotesHistory());

            ApplicationHeaderStatus status = ApplicationHeaderStatusLocalServiceUtil.getApplicationHeaderStatus(header.getApplicationHeaderStatusId());
            dto.put("statusBonusId", status.getId());
            dto.put("statusBonusName", status.getName());
         } else {
            action = CREATE;
            dto.put("userId", activeUser.getScreenName());
            dto.put("userName", activeUser.getFullName());
            dto.put("email", activeUser.getEmailAddress());

            assert roleDealer != null;

            if (RoleDealerUtils.isSuperUser(roleDealer.getUserId())) {
               dto.put("dealerId", "-");
               dto.put("dealerName", "-");
               dto.put("approverName", "-");
            } else {
               Dealer dealer = DealerLocalServiceUtil.getDealer(roleDealer.getDealerId());
               dto.put("dealerId", dealer.getCode());
               dto.put("dealerName", dealer.getName());
            }
         }
         // get DSO
         int masterApprovalId = MasterApprovalUtils.getMasterApprovalId(ApplicationEnum.UPLOAD_STNK.ordinal() + 1);

         User user = MasterApprovalUtils.getDefaultApprover(masterApprovalId);
         if (user != null) {
            dto.put("approverName", user.getFullName());
         } else {
            dto.put("approverName", "-");
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(request));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(request, "p_auth", "none"), e);
         } else {
            LOG.error(e.getMessage(), e);
         }
         action = INVALID;
      }

      request.setAttribute("data", dto);
      request.setAttribute("action", action);
      return "/upload/stnk/monitoring/monitoring-edit.jsp";
   }
}