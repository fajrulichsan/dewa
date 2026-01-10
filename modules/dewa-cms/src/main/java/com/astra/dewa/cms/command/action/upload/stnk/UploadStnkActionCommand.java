package com.astra.dewa.cms.command.action.upload.stnk;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Application;
import com.astra.dewa.model.ApplicationAssign;
import com.astra.dewa.model.ApplicationAssignJournal;
import com.astra.dewa.model.ApplicationAssignStatus;
import com.astra.dewa.model.ApplicationCategory;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.model.ApplicationHeaderJournal;
import com.astra.dewa.model.ApplicationHeaderStatus;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.ApplicationAssignJournalLocalServiceUtil;
import com.astra.dewa.service.ApplicationAssignLocalServiceUtil;
import com.astra.dewa.service.ApplicationAssignStatusLocalServiceUtil;
import com.astra.dewa.service.ApplicationCategoryLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderJournalLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderStatusLocalServiceUtil;
import com.astra.dewa.service.ApplicationLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.utils.ApplicationCategoryEnum;
import com.astra.dewa.utils.ApplicationEnum;
import com.astra.dewa.utils.ApplicationHeaderStatusEnum;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.UploadUtils;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.time.LocalDate;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.UPLOAD_STNK,
            "mvc.command.name=upload-stnk-action"
      },
      service = MVCResourceCommand.class
)
public class UploadStnkActionCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(UploadStnkActionCommand.class);

   private User user;

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      UploadPortletRequest portletRequest = PortalUtil.getUploadPortletRequest(request);
      ServiceContext serviceContext = ServiceContextFactory.getInstance(request);
      user = UserLocalServiceUtil.getUser(serviceContext.getUserId());
      UsersDealers roleDealer = RoleDealerUtils.userId(user.getUserId());

      boolean isRequestContainsXSS = false;
      Enumeration<String> attributes = request.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = request.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            log.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = portletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = portletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            log.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      int entryId = ParamUtil.getInteger(portletRequest, "entryId");
      String crudType = ParamUtil.getString(portletRequest, "crudType");
      LocalDate date = LocalDate.now();

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
      if (isRequestContainsXSS) {
         jsonObject = ERROR("Your request contains XSS payload.");
      } else if (crudType.equalsIgnoreCase("deleteFile")) {
         jsonObject = deleteFile(entryId);
      } else if (crudType.equalsIgnoreCase("delete")) {
         jsonObject = delete(entryId);
      } else {
         // Contact Information
         String email = ParamUtil.getString(portletRequest, "email");
         String emailCC = ParamUtil.getString(portletRequest, "emailCC");
         String phone = ParamUtil.getString(portletRequest, "phone");
         // Request Information
         int categoryId = ParamUtil.getInteger(portletRequest, "requestBonusId");
         ApplicationCategory category = ApplicationCategoryLocalServiceUtil.getApplicationCategory(categoryId);
         String nominalPengajuan = ParamUtil.getString(portletRequest, "nominalPengajuan");
         Integer reqYearId = ParamUtil.getInteger(portletRequest, "reqYearId");
         String requestDescription = ParamUtil.getString(portletRequest, "requestDescription");
         String businessBenefit = ParamUtil.getString(portletRequest, "businessBenefit");
         // Attachment
         long attachmentId = ParamUtil.getLong(portletRequest, "attachmentId");
         String attachmentName = ParamUtil.getString(portletRequest, "attachmentName");
         String attachmentPath = ParamUtil.getString(portletRequest, "attachmentPath");
         // Notes
         String notes = ParamUtil.getString(portletRequest, "notes");
         // String notesHistory = ParamUtil.getString(uploadPortletRequest, "notesHistory")
         int statusId = ParamUtil.getInteger(portletRequest, "statusBonusId");
         ApplicationHeaderStatus status = ApplicationHeaderStatusLocalServiceUtil.getApplicationHeaderStatus(statusId);
         ApplicationHeader header;
         if (crudType.equalsIgnoreCase("create")) {
            header = ApplicationHeaderLocalServiceUtil.createApplicationHeader(0);
         } else {
            header = ApplicationHeaderLocalServiceUtil.getApplicationHeader(entryId);
         }
         // Contact Information
         header.setReqEmail(email);
         header.setReqCCEmail(emailCC);
         header.setReqPhone(phone);
         // Request Information
         header.setApplicationCategoryId(category.getId());
         if (category.getId() != ApplicationCategoryEnum.CLAIM_TEST_CAR.ordinal() + 1) {
            header.setNominalPengajuan(0);
         } else {
            header.setNominalPengajuan(Integer.parseInt(nominalPengajuan));
         }
         header.setReqYear(reqYearId);
         header.setReqDesc(requestDescription);
         header.setBusinessBenefit(businessBenefit);
         // Attachment
         header.setFileId(attachmentId);
         header.setFileName(attachmentName);
         header.setFileUrl(attachmentPath);
         // Notes
         header.setNotes(notes);
         // header.setNotesHistory(notesHistory)
         // Status Bonus
         header.setApplicationHeaderStatusId(status.getId());

         assert roleDealer != null;
         Dealer dealer = DealerLocalServiceUtil.getDealer(roleDealer.getDealerId());

         if (crudType.equalsIgnoreCase("create")) {
            // General Information
            header.setDealerId(dealer.getId());
            if (status.getId() == ApplicationHeaderStatusEnum.WAITING.ordinal() + 1) {
               header.setReqDate(new Date());
               // header.setReqYear(date.getYear());
               header.setReqMonth(date.getMonthValue());
               String autoCode = UploadUtils.autoCode();
               // Bonus-51XXXXXX-00001
               header.setTicketNo(category.getName().replace(" ", "_") + "-" + dealer.getCode() + "-" + autoCode);
               header.setTicketCode(autoCode);
//               EmailUploadBonusUtil.sendEmailSubmitToDso(APIConstant.KWITANSI_STNK, header.getTicketNo(), header.getReqDesc(), header.getBusinessBenefit(), header.getNotes());
//               EmailUploadBonusUtil.sendEmailSubmitToHoDealer(APIConstant.KWITANSI_STNK, user.getEmailAddress(), user.getFullName(), header.getTicketNo(), header.getReqDesc(), header.getBusinessBenefit(), header.getNotes());
               // [email requester] [tanggal submit] : SUBMITTED
               String notesHistory = user.getEmailAddress() + "\t " + DateUtil.convertDateToStringIndo(new Date(), true) + " : SUBMITTED";
               header.setNotesHistory(notesHistory);
            }
            header.setReqScreenName(user.getScreenName());
            header.setReqName(user.getFullName());
            jsonObject = create(header);
         } else if (crudType.equalsIgnoreCase("update")) {
            if (status.getId() == ApplicationHeaderStatusEnum.WAITING.ordinal() + 1) {
               if (header.getReqDate() == null) {
                  header.setReqDate(new Date());
                  // header.setReqYear(date.getYear());
                  header.setReqMonth(date.getMonthValue());
                  String autoCode = UploadUtils.autoCode();
                  // Bonus-51XXXXXX-00001
                  header.setTicketNo(category.getName().replace(" ", "_") + "-" + dealer.getCode() + "-" + autoCode);
                  header.setTicketCode(autoCode);
               }
//               EmailUploadBonusUtil.sendEmailSubmitToDso(APIConstant.KWITANSI_STNK, header.getTicketNo(), header.getReqDesc(), header.getBusinessBenefit(), header.getNotes());
//               EmailUploadBonusUtil.sendEmailSubmitToHoDealer(APIConstant.KWITANSI_STNK, user.getEmailAddress(), user.getFullName(), header.getTicketNo(), header.getReqDesc(), header.getBusinessBenefit(), header.getNotes());
               // [email approver] [tanggal submit] : SUBMITTED
               String notesHistory = user.getEmailAddress() + "\t " + DateUtil.convertDateToStringIndo(new Date(), true) + " : SUBMITTED\n" + header.getNotesHistory();
               header.setNotesHistory(notesHistory);
            }
            jsonObject = update(header);
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

   private JSONObject create(ApplicationHeader header) {
      try {
         Application application = ApplicationLocalServiceUtil.getApplication(ApplicationEnum.UPLOAD_STNK.ordinal() + 1);
         header.setApplicationId(application.getId());
         header.setRowStatus(true);
         header.setCreatedDate(new Date());
         header.setCreatedBy(user.getScreenName());
         header.setModifiedDate(new Date());
         header.setModifiedBy(user.getScreenName());

         ApplicationHeaderLocalServiceUtil.addApplicationHeader(header);
         createApplicationHeaderJournal(header, user.getScreenName());

         if (header.getApplicationHeaderStatusId() == ApplicationHeaderStatusEnum.WAITING.ordinal() + 1) {
            ApplicationAssignStatus status = ApplicationAssignStatusLocalServiceUtil.getApplicationAssignStatus(ApplicationHeaderStatusEnum.WAITING.ordinal() + 1);
            List<UsersDealers> roleDealers = UsersDealersLocalServiceUtil.getUsersDealerses(0, 1);
            ApplicationAssign assign = createApplicationAssign(header, status.getId(), header.getNotes(), roleDealers.get(0).getUserId(), user.getScreenName());
            createApplicationAssignJournal(assign, user.getScreenName());
            return SUCCESS("Successfully Submit Ticket No " + header.getTicketNo(), header.getId() + "");
         } else {
            return SUCCESS("Data tersimpan", header.getId() + "");
         }
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject update(ApplicationHeader header) {
      try {
         header.setModifiedDate(new Date());
         header.setModifiedBy(user.getScreenName());

         ApplicationHeaderLocalServiceUtil.updateApplicationHeader(header);
         createApplicationHeaderJournal(header, user.getScreenName());
         if (header.getApplicationHeaderStatusId() != ApplicationHeaderStatusEnum.DRAFT.ordinal()) {
            ApplicationAssignStatus status = ApplicationAssignStatusLocalServiceUtil.getApplicationAssignStatus(header.getApplicationHeaderStatusId());
            List<UsersDealers> roleDealers = UsersDealersLocalServiceUtil.getUsersDealerses(0, 1);
            ApplicationAssign assign = createApplicationAssign(header, status.getId(), header.getNotes(), roleDealers.get(0).getUserId(), user.getScreenName());
            createApplicationAssignJournal(assign, user.getScreenName());
            return SUCCESS("Successfully Submit Ticket No " + header.getTicketNo(), header.getId() + "");
         } else {
            return SUCCESS("Data terupdate.", header.getId() + "");
         }
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   private JSONObject delete(int entryId) {
      try {
         ApplicationHeader header = ApplicationHeaderLocalServiceUtil.getApplicationHeader(entryId);
         header.setRowStatus(false);
         ApplicationHeaderLocalServiceUtil.updateApplicationHeader(header);
         return SUCCESS("Data deleted.", entryId + "");
      } catch (Exception e) {
         log.error(e);
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
         return SUCCESS("Data deleted.", entryId + "");
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

   /*
   private boolean isExist(String name) {
      DynamicQuery query = ApplicationHeaderLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Name", name));
      List<ApplicationHeader> headers = ApplicationHeaderLocalServiceUtil.dynamicQuery(query);
      return headers.size() > 0;
   }
   */
   
   private void createApplicationHeaderJournal(ApplicationHeader header, String screenName) {
      try {
         ApplicationHeaderJournal journal = ApplicationHeaderJournalLocalServiceUtil.createApplicationHeaderJournal(-1);
         journal.setApplicationHeaderId(header.getId());
         journal.setApplicationId(header.getApplicationId());
         // Status Bonus
         journal.setApplicationHeaderStatusId(header.getApplicationHeaderStatusId());
         // Request Information
         journal.setApplicationCategoryId(header.getApplicationCategoryId());
         journal.setDealerId(header.getDealerId());
         journal.setTicketNo(header.getTicketNo());
         journal.setTicketCode(header.getTicketCode());
         journal.setReqDate(header.getReqDate());
         journal.setReqYear(header.getReqYear());
         journal.setReqMonth(header.getReqMonth());
         journal.setReqScreenName(header.getReqScreenName());
         journal.setReqName(header.getReqName());
         journal.setReqEmail(header.getReqEmail());
         journal.setReqCCEmail(header.getReqCCEmail());
         journal.setReqPhone(header.getReqPhone());
         journal.setNominalPengajuan(header.getNominalPengajuan());
         journal.setReqDesc(header.getReqDesc());
         journal.setBusinessBenefit(header.getBusinessBenefit());
         // Attachment
         journal.setFileId(header.getFileId());
         journal.setFileName(header.getFileName());
         journal.setFileUrl(header.getFileUrl());
         // Notes
         journal.setNotes(header.getNotes());
         journal.setNotesHistory(header.getNotesHistory());
         journal.setRowStatus(true);
         journal.setCreatedBy(screenName);
         journal.setCreatedDate(new Date());
         journal.setModifiedBy(screenName);
         journal.setModifiedDate(new Date());
         ApplicationHeaderJournalLocalServiceUtil.addApplicationHeaderJournal(journal);
      } catch (Exception e) {
         log.error(e);
      }
   }

   private ApplicationAssign createApplicationAssign(
         ApplicationHeader header,
         int applicationAssignStatusId,
         String notes,
         long profileId,
         String screenName
   ) {
      try {
         ApplicationAssign assign = ApplicationAssignLocalServiceUtil.createApplicationAssign(-1);
         assign.setApplicationHeaderId(header.getId());
         assign.setApplicationAssignStatusId(applicationAssignStatusId);
         assign.setProfileId(profileId);
         assign.setStartDateOn(new Date());
         assign.setCompletedDateOn(null);
         assign.setNotes(notes);
         assign.setRowStatus(true);
         assign.setCreatedBy(screenName);
         assign.setCreatedDate(new Date());
         assign.setModifiedBy(screenName);
         assign.setModifiedDate(new Date());
         ApplicationAssignLocalServiceUtil.addApplicationAssign(assign);
         return assign;
      } catch (Exception e) {
         log.error(e);
         return null;
      }
   }

   private void createApplicationAssignJournal(ApplicationAssign assign, String screenName) {
      try {
         ApplicationAssignJournal jurnal = ApplicationAssignJournalLocalServiceUtil.createApplicationAssignJournal(-1);
         jurnal.setApplicationAssignId(assign.getId());
         jurnal.setApplicationHeaderId(assign.getApplicationHeaderId());
         jurnal.setApplicationAssignStatusId(assign.getApplicationAssignStatusId());
         jurnal.setProfileId(assign.getProfileId());
         jurnal.setStartDateOn(new Date());
         jurnal.setCompletedDateOn(new Date());
         jurnal.setNotes(assign.getNotes());
         jurnal.setRowStatus(true);
         jurnal.setCreatedBy(screenName);
         jurnal.setCreatedDate(new Date());
         jurnal.setModifiedBy(screenName);
         jurnal.setModifiedDate(new Date());
         ApplicationAssignJournalLocalServiceUtil.addApplicationAssignJournal(jurnal);
      } catch (Exception e) {
         log.error(e);
      }
   }

}
