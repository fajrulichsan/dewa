package com.astra.dewa.cms.command.action.upload.bonus;

import com.astra.dewa.cms.command.action.upload.service.ApplicationAssignServiceImpl;
import com.astra.dewa.cms.command.action.upload.service.ApplicationHeaderJournalServiceImpl;
import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.cms.command.action.upload.service.ApplicationAssignJournalServiceImpl;
import com.astra.dewa.model.ApplicationAssign;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.model.ApplicationHeaderStatus;
import com.astra.dewa.model.MasterApprovalDetail;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderStatusLocalServiceUtil;
import com.astra.dewa.utils.ApplicationAssignStatusEnum;
import com.astra.dewa.utils.ApplicationHeaderStatusEnum;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.MasterApprovalUtils;
import com.astra.dewa.utils.RoleUtils;
import com.astra.dewa.utils.email.APIConstant;
import com.astra.dewa.utils.email.EmailUploadUtils;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
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
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + CmsPortletKeys.UPLOAD_BONUS,
            "mvc.command.name=upload-bonus-update-status-action"
      },
      service = MVCResourceCommand.class
)
public class UploadBonusUpdateStatusActionCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadBonusUpdateStatusActionCommand.class);
   private User user;

   private final ApplicationAssignServiceImpl applicationAssignService = new ApplicationAssignServiceImpl();
   private final ApplicationAssignJournalServiceImpl applicationAssignJournalService = new ApplicationAssignJournalServiceImpl();
   private final ApplicationHeaderJournalServiceImpl applicationHeaderJournalService = new ApplicationHeaderJournalServiceImpl();

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ServiceContext serviceContext = ServiceContextFactory.getInstance(resourceRequest);
      user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

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
         if (isRequestContainsXSS) {
            jsonObject = ERROR("Your request contains XSS payload.");
         } else {
            String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
            int bonusId = ParamUtil.getInteger(uploadPortletRequest, "bonusId");

            // General Information
            int statusId = ParamUtil.getInteger(uploadPortletRequest, "statusId");
            String notes = ParamUtil.getString(uploadPortletRequest, "notes");

            ApplicationHeader uploadBonus = ApplicationHeaderLocalServiceUtil.getApplicationHeader(bonusId);
            uploadBonus.setNotes(notes);
            uploadBonus.setModifiedDate(new Date());
            uploadBonus.setModifiedBy(user.getScreenName());
            ApplicationHeaderStatus status = ApplicationHeaderStatusLocalServiceUtil.getApplicationHeaderStatus(statusId);

            if (crudType.equalsIgnoreCase("updateStatus")) {
               jsonObject = updateStatus(uploadBonus, status);
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(resourceRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(resourceRequest, "p_auth", "none"), e);
            jsonObject = ERROR(401, "Unauthorized request!");
         } else {
            LOG.error(e.getMessage());
            jsonObject = ERROR(400, "Bad request!");
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject updateStatus(ApplicationHeader header, ApplicationHeaderStatus status) {
      try {
         boolean isSequential = MasterApprovalUtils.isSequential(header.getApplicationId());
         List<MasterApprovalDetail> approvers = MasterApprovalUtils.getApprovers(MasterApprovalUtils.getMasterApprovalId(header.getApplicationId()));
         String notesHistory = "";

         int lastSubmitAssignId = MasterApprovalUtils.getLastSubmitAssignId(header.getId());
         List<ApplicationAssign> appAssigns = MasterApprovalUtils.getActiveAssignees(header.getId(), lastSubmitAssignId);

         if (approvers.stream().map(MasterApprovalDetail::getUserId).collect(Collectors.toList()).contains(user.getUserId()) ||
               RoleUtils.isAdminDSO(RoleUtils.getUserRole(user.getUserId()))) {

            if (isSequential) {
               ApplicationAssign appAssign;
               if (status.getId() == ApplicationAssignStatusEnum.APPROVE.getId()) {
                  for (int approverLevel = 0; approverLevel < appAssigns.size(); approverLevel++) {
                     if (appAssigns.get(approverLevel).getProfileId() == user.getUserId()) {
                        // CHECK NEXT ASSIGNEE
                        if (approverLevel < approvers.size() - 1) {
                           // THE STATUS IS STILL SET TO WAITING
                           header.setApplicationHeaderStatusId(header.getApplicationHeaderStatusId() + 1);
                           notesHistory = user.getEmailAddress() + "\t " + DateUtil.convertDateToStringIndo(new Date(), true) + " : " + status.getName().toUpperCase() + "\n" + header.getNotesHistory();

                           // UPDATE APPLICATION HEADER
                           header.setNotesHistory(notesHistory);
                           header.setModifiedDate(new Date());
                           header.setModifiedBy(user.getScreenName());
                           ApplicationHeaderLocalServiceUtil.updateApplicationHeader(header);

                           // CREATE APPLICATION HEADER JOURNAL
                           applicationHeaderJournalService.createApplicationHeaderJournal(header);

                           // SET THE NEXT LEVEL ASSIGNEE STATUS TO IN REVIEW
                           appAssign = appAssigns.get(approverLevel + 1);
                           appAssign = applicationAssignService.updateApplicationAssign(appAssign, ApplicationAssignStatusEnum.IN_REVIEW.getId(), header.getNotes(), user.getUserId(), user.getScreenName());
                           applicationAssignJournalService.createApplicationAssignJournal(appAssign);

                           // SEND IN_REVIEW EMAIL TO NEXT LEVEL ASSIGNEE
                           EmailUploadUtils.sendEmailSubmitToDso(APIConstant.KWITANSI_BONUS, header.getTicketNo(), header.getReqDesc(), header.getBusinessBenefit(), header.getNotes(), approvers.get(approverLevel + 1).getUserId());
                        }

                        // UPDATE CURRENT ASSIGNEE
                        appAssign = appAssigns.get(approverLevel);
                        appAssign.setCompletedDateOn(new Date());
                        appAssign = applicationAssignService.updateApplicationAssign(appAssign, status.getId(), header.getNotes(), user.getUserId(), user.getScreenName());
                        applicationAssignJournalService.createApplicationAssignJournal(appAssign);

                        // WHEN ALL ASSIGNEES APPROVED THE REQUEST
                        if (MasterApprovalUtils.hasAllAsigneesApproved(header.getApplicationId(), header.getId(), lastSubmitAssignId)) {
                           // SET STATUS TO APPROVED
                           header.setApplicationHeaderStatusId(status.getId());
                           notesHistory = user.getEmailAddress() + "\t " + DateUtil.convertDateToStringIndo(new Date(), true) + " : " + status.getName().toUpperCase() + "\n" + header.getNotesHistory();

                           // UPDATE APPLICATION HEADER
                           header.setNotesHistory(notesHistory);
                           header.setModifiedDate(new Date());
                           header.setModifiedBy(user.getScreenName());
                           ApplicationHeaderLocalServiceUtil.updateApplicationHeader(header);

                           // CREATE APPLICATION HEADER JOURNAL
                           applicationHeaderJournalService.createApplicationHeaderJournal(header);

                           // SEND APPROVED EMAIL TO REQUESTER
                           EmailUploadUtils.sendEmailApprovedToHoDealer(APIConstant.KWITANSI_BONUS, header.getReqEmail(), header.getReqName(), header.getTicketNo(), header.getReqDesc());
                        }
                     }
                  }
               } else if (status.getId() == ApplicationAssignStatusEnum.REVISE.getId() || status.getId() == ApplicationAssignStatusEnum.REJECT.getId()) {
                  for (ApplicationAssign assign : appAssigns) {
                     if (assign.getProfileId() == user.getUserId()) {
                        // CHECK PREVIOUS ASSIGNEES
                        int currentApproverLevel = -1;
                        int approveCount = MasterApprovalUtils.approveCount(header.getId(), lastSubmitAssignId);

                        // SEND EMAIL TO THE LOWER LEVEL ASSIGNEE
                        while (++currentApproverLevel < approveCount) {
                           if (status.getId() == ApplicationHeaderStatusEnum.REVISE.getId()) {
                              EmailUploadUtils.sendEmailReviseToDso(
                                    APIConstant.KWITANSI_BONUS,
                                    header.getTicketNo(),
                                    header.getReqDesc(),
                                    header.getBusinessBenefit(),
                                    header.getNotes(),
                                    approvers.get(currentApproverLevel)
                              );
                           } else if (status.getId() == ApplicationHeaderStatusEnum.REJECT.getId()) {
                              EmailUploadUtils.sendEmailRejectToDso(
                                    APIConstant.KWITANSI_BONUS,
                                    header.getTicketNo(),
                                    header.getReqDesc(),
                                    header.getBusinessBenefit(),
                                    header.getNotes(),
                                    approvers.get(currentApproverLevel)
                              );
                           }
                        }

                        // SET STATUS TO REVISED OR REJECTED
                        header.setApplicationHeaderStatusId(status.getId());
                        notesHistory = user.getEmailAddress() + "\t " + DateUtil.convertDateToStringIndo(new Date(), true) + " : " + status.getName().toUpperCase() + "\n" + header.getNotesHistory();

                        // UPDATE APPLICATION HEADER
                        header.setNotesHistory(notesHistory);
                        header.setModifiedDate(new Date());
                        header.setModifiedBy(user.getScreenName());
                        ApplicationHeaderLocalServiceUtil.updateApplicationHeader(header);

                        // CREATE APPLICATION HEADER JOURNAL
                        applicationHeaderJournalService.createApplicationHeaderJournal(header);

                        // UPDATE CURRENT APPROVER
                        appAssign = assign;
                        appAssign.setCompletedDateOn(new Date());
                        appAssign = applicationAssignService.updateApplicationAssign(appAssign, status.getId(), header.getNotes(), user.getUserId(), user.getScreenName());
                        applicationAssignJournalService.createApplicationAssignJournal(appAssign);

                        // SEND EMAIL TO HO DEALER
                        if (status.getId() == ApplicationHeaderStatusEnum.REVISE.getId()) {
                           EmailUploadUtils.sendEmailReviseToHoDealer(
                                 APIConstant.KWITANSI_BONUS,
                                 header.getReqEmail(),
                                 header.getReqName(),
                                 header.getTicketNo(),
                                 header.getReqDesc(),
                                 header.getBusinessBenefit(),
                                 header.getNotes()
                           );
                        } else if (status.getId() == ApplicationHeaderStatusEnum.REJECT.getId()) {
                           EmailUploadUtils.sendEmailRejectToHoDealer(
                                 APIConstant.KWITANSI_BONUS,
                                 header.getReqEmail(),
                                 header.getReqName(),
                                 header.getTicketNo(),
                                 header.getReqDesc(),
                                 header.getBusinessBenefit(),
                                 header.getNotes()
                           );
                        }
                     }
                  }
               }
            } else {
               // [email approver] [tanggal approve] : APPROVED
               header.setApplicationHeaderStatusId(status.getId());
               notesHistory = user.getEmailAddress() + "\t " + DateUtil.convertDateToStringIndo(new Date(), true) + " : " + status.getName().toUpperCase() + "\n" + header.getNotesHistory();
               header.setNotesHistory(notesHistory);

               // UPDATE APPLICATION HEADER
               header.setNotesHistory(notesHistory);
               header.setModifiedDate(new Date());
               header.setModifiedBy(user.getScreenName());
               ApplicationHeaderLocalServiceUtil.updateApplicationHeader(header);

               // CREATE APPLICATION HEADER JOURNAL
               applicationHeaderJournalService.createApplicationHeaderJournal(header);

               if (status.getId() == ApplicationHeaderStatusEnum.APPROVE.getId()) {
                  // SEND APPROVED EMAIL
                  EmailUploadUtils.sendEmailApprovedToHoDealer(APIConstant.KWITANSI_BONUS, header.getReqEmail(), header.getReqName(), header.getTicketNo(), header.getReqDesc());
               } else if (status.getId() == ApplicationHeaderStatusEnum.REVISE.getId()) {
                  // SEND REVISED EMAIL
                  EmailUploadUtils.sendEmailReviseToHoDealer(APIConstant.KWITANSI_BONUS, header.getReqEmail(), header.getReqName(), header.getTicketNo(), header.getReqDesc(), header.getBusinessBenefit(), header.getNotes());
               } else if (status.getId() == ApplicationHeaderStatusEnum.REJECT.getId()) {
                  // SEND REJECTED EMAIL
                  EmailUploadUtils.sendEmailRejectToHoDealer(APIConstant.KWITANSI_BONUS, header.getReqEmail(), header.getReqName(), header.getTicketNo(), header.getReqDesc(), header.getBusinessBenefit(), header.getNotes());
               }

               for (ApplicationAssign applicationAssign : appAssigns) {
                  if (applicationAssign.getProfileId() == user.getUserId()) {
                     applicationAssign.setApplicationAssignStatusId(status.getId());
                     applicationAssign.setCompletedDateOn(new Date());
                     applicationAssignService.updateApplicationAssign(applicationAssign, status.getId(), header.getNotes(), user.getUserId(), user.getScreenName());
                     applicationAssignJournalService.createApplicationAssignJournal(applicationAssign);
                  }
               }
            }
         } else {
            return ERROR(401, "You have no authority to do this action.");
         }
         return SUCCESS(status.getName(), header.getId() + "");
      } catch (Exception e) {
         LOG.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }
}