package com.astra.dewa.cms.command.render.upload.bonus;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.ApplicationCategory;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.model.ApplicationHeaderStatus;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.service.ApplicationCategoryLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderStatusLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.MasterApprovalUtils;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.UPLOAD_BONUS,
            "mvc.command.name=upload-bonus-single"
      },
      service = MVCResourceCommand.class
)
public class UploadBonusSingleRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadBonusSingleRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
      ServiceContext serviceContext = ServiceContextFactory.getInstance(request);

      String entryId = httpReq.getParameter("entryId");
      boolean isDisplay = Boolean.parseBoolean(httpReq.getParameter("isDisplay"));

      JSONObject dto = JSONFactoryUtil.createJSONObject();

      try {
         AuthTokenUtil.checkCSRFToken(httpReq, this.getClass().getName());

         User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

         ApplicationHeader header = ApplicationHeaderLocalServiceUtil.getApplicationHeader(Integer.parseInt(entryId));
         dto.put("id", header.getId());
         boolean hasChecked = false;

         boolean isApprover = MasterApprovalUtils.isActiveApprover(header.getApplicationId(), user.getUserId());
         boolean isActiveAssignee = MasterApprovalUtils.isActiveAssignee(header.getId(), user.getUserId());

         // CHECK USER ASSIGNMENT STATUS
         if (!isApprover || !isActiveAssignee || isDisplay) {
            hasChecked = true;
         }

         dto.put("isApprovable", hasChecked);

         // GENERAL INFORMATION
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

         if (header.getDealerId() != 0) {
            Dealer dealer = DealerLocalServiceUtil.getDealer(header.getDealerId());
            dto.put("dealerId", dealer.getId());
            dto.put("dealerName", dealer.getName());
         } else {
            dto.put("dealerId", "-");
            dto.put("dealerName", "-");
         }

         dto.put("approverId", "-");
         dto.put("approverName", "-");

         // CONTACT INFORMATION
         dto.put("email", header.getReqEmail());
         dto.put("emailCC", header.getReqCCEmail());
         dto.put("phone", header.getReqPhone());

         // REQUEST INFORMATION
         if (header.getApplicationCategoryId() > 0) {
            ApplicationCategory category = ApplicationCategoryLocalServiceUtil.getApplicationCategory(header.getApplicationCategoryId());
            dto.put("requestBonusId", category.getId());
            dto.put("requestBonusName", category.getName());
         } else {
            dto.put("requestBonusId", "-");
            dto.put("requestBonusName", "-");
         }
         dto.put("nominalPengajuan", header.getNominalPengajuan());
         dto.put("reqYearId", header.getReqYear());
         dto.put("requestDescription", header.getReqDesc());
         dto.put("businessBenefit", header.getBusinessBenefit());

         // ATTACHMENT
         dto.put("attachmentId", header.getFileId());
         dto.put("attachmentName", header.getFileName());
         dto.put("attachmentPath", header.getFileUrl());

         // NOTES
         dto.put("notes", header.getNotes());
         dto.put("notesHistory", header.getNotesHistory());

         if (header.getApplicationHeaderStatusId() > 0) {
            ApplicationHeaderStatus status = ApplicationHeaderStatusLocalServiceUtil.getApplicationHeaderStatus(header.getApplicationHeaderStatusId());
            dto.put("statusBonusId", status.getId());
            dto.put("statusBonusName", status.getName());
         } else {
            dto.put("statusBonusId", "-");
            dto.put("statusBonusName", "-");
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpReq));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpReq, "p_auth", "none"), e);
         } else {
            LOG.error(e.getMessage(), e);
         }
      }
      JSONObject jsonObject = SUCCESS("Sukses", entryId, dto);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}