package com.astra.dewa.web.command.action.upload.stnk;

import com.astra.dewa.model.ApplicationAssign;
import com.astra.dewa.model.ApplicationCategory;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.model.ApplicationHeaderStatus;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.MasterApprovalDetail;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.ApplicationAssignStatusLocalServiceUtil;
import com.astra.dewa.service.ApplicationCategoryLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderStatusLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.utils.ApplicationAssignStatusEnum;
import com.astra.dewa.utils.ApplicationEnum;
import com.astra.dewa.utils.ApplicationHeaderStatusEnum;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.InputValidationUtils;
import com.astra.dewa.utils.MasterApprovalUtils;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.UploadUtils;
import com.astra.dewa.utils.email.APIConstant;
import com.astra.dewa.utils.email.EmailUploadUtils;
import com.astra.dewa.web.command.action.upload.service.ApplicationAssignJournalServiceImpl;
import com.astra.dewa.web.command.action.upload.service.ApplicationAssignServiceImpl;
import com.astra.dewa.web.command.action.upload.service.ApplicationHeaderJournalServiceImpl;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.time.LocalDate;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static com.astra.dewa.utils.CRUDActionKeys.CREATE;
import static com.astra.dewa.utils.CRUDActionKeys.DELETE;
import static com.astra.dewa.utils.CRUDActionKeys.UPDATE;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_STNK_MONITORING,
            "mvc.command.name=upload-stnk-monitoring-request-action"
      },
      service = MVCResourceCommand.class
)
public class UploadStnkMonitoringRequestActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadStnkMonitoringRequestActionCommand.class);
   private User user;

   private final ApplicationAssignServiceImpl applicationAssignService = new ApplicationAssignServiceImpl();
   private final ApplicationAssignJournalServiceImpl applicationAssignJournalService = new ApplicationAssignJournalServiceImpl();
   private final ApplicationHeaderJournalServiceImpl applicationHeaderJournalService = new ApplicationHeaderJournalServiceImpl();

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      UploadPortletRequest portletRequest = PortalUtil.getUploadPortletRequest(request);
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      boolean isRequestContainsXSS = false;
      Enumeration<String> attributes = request.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = request.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = portletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = portletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            LOG.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      int entryId = ParamUtil.getInteger(portletRequest, "entryId");
      String crudType = ParamUtil.getString(portletRequest, "crudType");
      LocalDate date = LocalDate.now();

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

      try {
         // CSRF AUTHENTICATION
         AuthTokenUtil.checkCSRFToken(portletRequest, this.getClass().getName());

         this.user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
         UsersDealers roleDealer = RoleDealerUtils.userId(themeDisplay.getUserId());

         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else if (crudType.equalsIgnoreCase("deleteFile")) {
            jsonObject = deleteFile(entryId);
         } else if (crudType.equalsIgnoreCase(DELETE)) {
            jsonObject = delete(entryId);
         } else {
            // Contact Information
            String email = ParamUtil.getString(portletRequest, "email");
            String emailCC = ParamUtil.getString(portletRequest, "emailCC");
            String phone = ParamUtil.getString(portletRequest, "phone");

            // CONTACT INFORMATION VALIDATION
            if (Validator.isNull(email)) {
               throw new SystemException("Email belum diisi");
            }
            if (!InputValidationUtils.isEmailValid(email)) {
               throw new SystemException("Mohon gunakan format email yang valid");
            }
            if (!Validator.isNull(emailCC) && !InputValidationUtils.isEmailValid(emailCC)) {
               throw new SystemException("Mohon gunakan format email yang valid pada CC email");
            }
            if (Validator.isNull(phone)) {
               throw new SystemException("Nomor handphone belum diisi");
            }
            if (!InputValidationUtils.isNumberOnly(phone)) {
               throw new SystemException("Mohon gunakan nomor dengan format 08xxx");
            }
            if (phone.length() < 9 || phone.length() > 16) {
               throw new SystemException("Mohon masukkan nomor yang sesuai");
            }

            // Request Information
            int categoryId = ParamUtil.getInteger(portletRequest, "requestBonusId");
            ApplicationCategory category = ApplicationCategoryLocalServiceUtil.getApplicationCategory(categoryId);
            // String nominalPengajuan = ParamUtil.getString(portletRequest, "nominalPengajuan");
            Integer reqYearId = ParamUtil.getInteger(portletRequest, "reqYearId");
            String requestDescription = ParamUtil.getString(portletRequest, "requestDescription");
            String businessBenefit = ParamUtil.getString(portletRequest, "businessBenefit");

            // REQUEST INFORMATION VALIDATION
            int minimumYear = date.getYear() - 3;
            int maximumYear = date.getYear() + 3;
            if (Validator.isNull(reqYearId)) {
               throw new SystemException("Tahun belum diisi");
            }
            if (reqYearId < minimumYear || reqYearId > maximumYear) {
               throw new SystemException("Mohon input tahun diantara " + minimumYear + " dan " + maximumYear);
            }
            if (!InputValidationUtils.isBasicCharacterWithEnter(requestDescription)) {
               throw new SystemException("Mohon tidak menggunakan karakter selain .,/()@&_- pada Description");
            }
            if (Validator.isNull(requestDescription) || requestDescription.length() < 9 || requestDescription.length() > 500) {
               throw new SystemException("Mohon input Description antara 9 sampai 500 karakter");
            }
            if (!InputValidationUtils.isBasicCharacterWithEnter(businessBenefit)) {
               throw new SystemException("Mohon tidak menggunakan karakter selain .,/()@&_- pada Business Benefit");
            }
            if (Validator.isNull(businessBenefit) || businessBenefit.length() < 9 || businessBenefit.length() > 500) {
               throw new SystemException("Mohon input Benefit antara 9 sampai 500 karakter");
            }

            // Attachment
            long attachmentId = ParamUtil.getLong(portletRequest, "attachmentId");
            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(attachmentId);
            String attachmentName = fileEntry.getFileName().replaceAll("_[^_]+(?=\\.)", "");
            String attachmentPath = themeDisplay.getPortalURL() +
                  themeDisplay.getPathContext() +
                  "/documents/" +
                  themeDisplay.getScopeGroupId() +
                  "/" + fileEntry.getFolderId() +
                  "/" + fileEntry.getFileName();

//            String attachmentName = ParamUtil.getString(portletRequest, "attachmentName");
//            String attachmentPath = ParamUtil.getString(portletRequest, "attachmentPath");

            // Notes
            String notes = ParamUtil.getString(portletRequest, "notes");
            if (!InputValidationUtils.isBasicCharacterWithEnter(notes)) {
               throw new SystemException("Mohon tidak menggunakan karakter selain .,/()@&_- pada Notes");
            }
            if (Validator.isNull(notes) || notes.length() < 9 || notes.length() > 500) {
               throw new SystemException("Mohon input Notes antara 9 sampai 500 karakter");
            }

            // String notesHistory = ParamUtil.getString(uploadPortletRequest, "notesHistory")
            int statusId = ParamUtil.getInteger(portletRequest, "statusBonusId");
            ApplicationHeaderStatus status = ApplicationHeaderStatusLocalServiceUtil.getApplicationHeaderStatus(statusId);

            ApplicationHeader header;
            if (crudType.equalsIgnoreCase(CREATE)) {
               header = ApplicationHeaderLocalServiceUtil.createApplicationHeader(-1);
            } else {
               header = ApplicationHeaderLocalServiceUtil.getApplicationHeader(entryId);
            }
            header.setReqYear(reqYearId);

            // Contact Information
            header.setReqEmail(email);
            header.setReqCCEmail(emailCC);
            header.setReqPhone(phone);

            // REQUEST INFORMATION
            header.setApplicationCategoryId(category.getId());
//            if (category.getId() == ApplicationCategoryEnum.CLAIM_TEST_CAR.ordinal() + 1) {
            header.setNominalPengajuan(0);
//            } else {
//               header.setNominalPengajuan(Integer.parseInt(nominalPengajuan));
//            }
            header.setReqDesc(requestDescription);
            header.setBusinessBenefit(businessBenefit);

            // Attachment
            header.setFileId(attachmentId);
            header.setFileName(attachmentName);
            header.setFileUrl(attachmentPath);

            // Notes
            header.setNotes(notes);

            assert roleDealer != null;
            Dealer dealer = DealerLocalServiceUtil.getDealer(roleDealer.getDealerId());

            boolean isSequential = MasterApprovalUtils.isSequential(ApplicationEnum.UPLOAD_STNK.ordinal() + 1);

            if (crudType.equalsIgnoreCase(UPDATE)) {
               if (ApplicationHeaderStatusEnum.isWaiting(status.getId())) {
                  if (header.getReqDate() == null) {
                     header.setReqDate(new Date());
                     header.setReqMonth(date.getMonthValue());
                     String autoCode = UploadUtils.autoCode();

                     // Bonus-51XXXXXX-00001
                     header.setTicketNo(category.getName().replace(" ", "_") + "-" + dealer.getCode() + "-" + autoCode);
                     header.setTicketCode(autoCode);
                  }

                  List<MasterApprovalDetail> approvers = MasterApprovalUtils.getApprovers(MasterApprovalUtils.getMasterApprovalId(ApplicationEnum.UPLOAD_STNK.ordinal() + 1));

                  assert approvers != null;
                  // APPLICATION HEADER STATUS
                  header.setApplicationHeaderStatusId(ApplicationHeaderStatusEnum.SUBMIT.getId());

                  if (isSequential) {
                     EmailUploadUtils.sendEmailSubmitToDso(
                           APIConstant.KWITANSI_STNK,
                           header.getTicketNo(),
                           header.getReqDesc(),
                           header.getBusinessBenefit(),
                           header.getNotes(),
                           approvers.get(0).getUserId()
                     );
                  } else {
                     for (MasterApprovalDetail approver : approvers) {
                        EmailUploadUtils.sendEmailSubmitToDso(
                              APIConstant.KWITANSI_STNK,
                              header.getTicketNo(),
                              header.getReqDesc(),
                              header.getBusinessBenefit(),
                              header.getNotes(),
                              approver.getUserId()
                        );
                     }
                  }

                  // SEND SUBMIT EMAIL TO HO DEALER
                  EmailUploadUtils.sendEmailSubmitToHoDealer(APIConstant.KWITANSI_STNK, user.getEmailAddress(), user.getFullName(), header.getTicketNo(), header.getReqDesc(), header.getBusinessBenefit(), header.getNotes());

                  // [email approver] [tanggal submit] : SUBMITTED
                  status = ApplicationHeaderStatusLocalServiceUtil.getApplicationHeaderStatus(ApplicationHeaderStatusEnum.SUBMIT.getId());
                  String notesHistory = user.getEmailAddress() + "\t " + DateUtil.convertDateToStringIndo(new Date(), true) + " : " + status.getName().toUpperCase() + "\n" + header.getNotesHistory();
                  header.setNotesHistory(notesHistory);
               } else {
                  header.setApplicationHeaderStatusId(ApplicationHeaderStatusEnum.DRAFT.getId());
               }
               jsonObject = update(header, isSequential);
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(request));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(request, "p_auth", "none"), e);
            jsonObject = ERROR(401, "Unauthorized request!");
         } else if (e instanceof SystemException) {
            LOG.error(e.getMessage(), e);
            jsonObject = WARNING(e.getMessage());
         } else {
            LOG.error(e.getMessage(), e);
            jsonObject = ERROR(400, e.getMessage());
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

   private JSONObject update(ApplicationHeader header, boolean isSequential) {
      try {
         header.setModifiedDate(new Date());
         header.setModifiedBy(user.getScreenName());
         ApplicationHeaderLocalServiceUtil.updateApplicationHeader(header);

         if (header.getApplicationHeaderStatusId() == ApplicationHeaderStatusEnum.SUBMIT.getId()) {
            // APPLICATION HEADER JOURNAL : SUBMIT
            applicationHeaderJournalService.createApplicationHeaderJournal(header, user.getScreenName());

            // APPLICATION HEADER JOURNAL : WAITING
            if (isSequential) {
               header.setApplicationHeaderStatusId(ApplicationHeaderStatusEnum.WAITING_LEVEL_1.getId());
            } else {
               header.setApplicationHeaderStatusId(ApplicationHeaderStatusEnum.WAITING.getId());
            }
            ApplicationHeaderLocalServiceUtil.updateApplicationHeader(header);
            applicationHeaderJournalService.createApplicationHeaderJournal(header, user.getScreenName());

            // APPLICATION ASSIGN STATUS
            int submitStatusId = ApplicationAssignStatusLocalServiceUtil.getApplicationAssignStatus(ApplicationAssignStatusEnum.SUBMIT.getId()).getId();
            int inReviewStatusId = ApplicationAssignStatusLocalServiceUtil.getApplicationAssignStatus(ApplicationAssignStatusEnum.IN_REVIEW.getId()).getId();
            int waitingStatusId = ApplicationAssignStatusLocalServiceUtil.getApplicationAssignStatus(ApplicationAssignStatusEnum.WAITING.getId()).getId();

            // APPLICATION ASSIGN : SUBMITTED
            ApplicationAssign appAssign;
            appAssign = applicationAssignService.createApplicationAssign(header, submitStatusId, header.getNotes(), user.getUserId(), user.getScreenName());
            applicationAssignJournalService.createApplicationAssignJournal(appAssign);

            // GET ASSIGNEES
            List<MasterApprovalDetail> approvers = MasterApprovalUtils.getApprovers(MasterApprovalUtils.getMasterApprovalId(header.getApplicationId()));

            // CREATE APPLICATION ASSIGN
            boolean isFirstApprover = true;

            if (null != approvers) {
               for (MasterApprovalDetail approver : approvers) {
                  if (isSequential) {
                     if (isFirstApprover) {
                        // APPLICATION ASSIGN : IN_REVIEW
                        appAssign = applicationAssignService.createApplicationAssign(header, inReviewStatusId, header.getNotes(), approver.getUserId(), user.getScreenName());
                        applicationAssignJournalService.createApplicationAssignJournal(appAssign);
                        isFirstApprover = false;
                     } else {
                        // APPLICATION ASSIGN : WAITING
                        appAssign = applicationAssignService.createApplicationAssign(header, waitingStatusId, header.getNotes(), approver.getUserId(), user.getScreenName());
                        applicationAssignJournalService.createApplicationAssignJournal(appAssign);
                     }
                  } else {
                     // APPLICATION ASSIGN : IN_REVIEW
                     appAssign = applicationAssignService.createApplicationAssign(header, inReviewStatusId, header.getNotes(), approver.getUserId(), user.getScreenName());
                     applicationAssignJournalService.createApplicationAssignJournal(appAssign);
                  }
               }
            }
            return SUCCESS("Successfully Submit Ticket No " + header.getTicketNo(), header.getId() + "");
         } else {
            return SUCCESS("Successfully Save As Draft", header.getId() + "");
         }
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete(int entryId) {
      try {
         ApplicationHeader header = ApplicationHeaderLocalServiceUtil.getApplicationHeader(entryId);
         ApplicationHeaderLocalServiceUtil.updateApplicationHeader(header);
         return SUCCESS("Data deleted.", String.valueOf(entryId));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject deleteFile(int entryId) {
      try {
         ApplicationHeader header = ApplicationHeaderLocalServiceUtil.getApplicationHeader(entryId);
         FileUtil.deleteFile(header.getFileId());
         header.setFileId(null);
         header.setFileName(null);
         header.setFileUrl(null);
         ApplicationHeaderLocalServiceUtil.updateApplicationHeader(header);
         return SUCCESS("Data deleted.", String.valueOf(entryId));
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }
}