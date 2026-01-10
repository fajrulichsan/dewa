package com.astra.dewa.web.command.action.upload.bonus;

import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.model.ApplicationHeaderStatus;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderStatusLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.utils.ApplicationHeaderStatusEnum;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.email.APIConstant;
import com.astra.dewa.utils.email.EmailUploadUtils;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
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
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_BONUS_MONITORING,
            "mvc.command.name=upload-bonus-monitoring-update-status-action"
      },
      service = MVCResourceCommand.class
)
public class UploadBonusMonitoringRequestUpdateStatusActionCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(UploadBonusMonitoringRequestUpdateStatusActionCommand.class);
   private User user;

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
            log.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      attributes = uploadPortletRequest.getParameterNames();
      while (attributes.hasMoreElements()) {
         String param = attributes.nextElement();
         String value = uploadPortletRequest.getParameter(param);
         if (FilterXSS.checkXSS(value)) {
            log.warn(value + " contains XSS payload");
            isRequestContainsXSS = true;
            break;
         }
      }

      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
      if (isRequestContainsXSS) {
         jsonObject = ERROR("Your request contains XSS payload.");
      } else {
         String crudType = ParamUtil.getString(uploadPortletRequest, "crudType");
         int bonusId = ParamUtil.getInteger(uploadPortletRequest, "bonusId");

         // GENERAL INFORMATION
         int statusId = ParamUtil.getInteger(uploadPortletRequest, "statusId");
         String notes = ParamUtil.getString(uploadPortletRequest, "notes");

         ApplicationHeader uploadBonus = ApplicationHeaderLocalServiceUtil.getApplicationHeader(bonusId);
         uploadBonus.setNotes(notes);

         ApplicationHeaderStatus status = ApplicationHeaderStatusLocalServiceUtil.getApplicationHeaderStatus(statusId);
         uploadBonus.setApplicationHeaderStatusId(status.getId());

         if (crudType.equalsIgnoreCase("updateStatus")) {
            jsonObject = updateStatus(uploadBonus, status);
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONObject updateStatus(ApplicationHeader header, ApplicationHeaderStatus status) {
      try {
         // Dealer - DSO
         DynamicQuery query = UsersDealersLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("UserId", user.getUserId()));
         List<UsersDealers> roleDealers = UsersDealersLocalServiceUtil.dynamicQuery(query);
         if (!roleDealers.isEmpty()) {
            User user1 = UserLocalServiceUtil.getUser(roleDealers.get(0).getUserId());
            // [email approver] [tanggal approve] : APPROVED
            String notesHistory = user1.getEmailAddress() + "\t " + DateUtil.convertDateToStringIndo(new Date(), true) + " : " + status.getName().toUpperCase() + "\n" + header.getNotesHistory();
            header.setNotesHistory(notesHistory);
         }

         header.setModifiedDate(new Date());
         header.setModifiedBy(user.getScreenName());

         ApplicationHeaderLocalServiceUtil.updateApplicationHeader(header);
         if (status.getId() == ApplicationHeaderStatusEnum.APPROVE.ordinal() + 1) {
            EmailUploadUtils.sendEmailApprovedToHoDealer(APIConstant.KWITANSI_BONUS, header.getReqEmail(), header.getReqName(), header.getTicketNo(), header.getReqDesc());
         } else if (status.getId() == ApplicationHeaderStatusEnum.REVISE.ordinal() + 1) {
            EmailUploadUtils.sendEmailReviseToHoDealer(APIConstant.KWITANSI_BONUS, header.getReqEmail(), header.getReqName(), header.getTicketNo(), header.getReqDesc(), header.getBusinessBenefit(), header.getNotes());
         } else if (status.getId() == ApplicationHeaderStatusEnum.REJECT.ordinal() + 1) {
            EmailUploadUtils.sendEmailRejectToHoDealer(APIConstant.KWITANSI_BONUS, header.getReqEmail(), header.getReqName(), header.getTicketNo(), header.getReqDesc(), header.getBusinessBenefit(), header.getNotes());
         }
         return SUCCESS(status.getName(), header.getId() + "");
      } catch (Exception e) {
         log.error(e);
         return NOT_SUCCESS(e.getMessage());
      }
   }

}
