package com.astra.dewa.cms.command.render.upload.util;

import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.service.ApplicationAssignLocalServiceUtil;
import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.astra.dewa.utils.ApplicationAssignStatusEnum;
import com.astra.dewa.utils.ApplicationEnum;
import com.astra.dewa.utils.ApplicationHeaderStatusEnum;
import com.astra.dewa.utils.MasterApprovalUtils;
import com.astra.dewa.utils.email.APIConstant;
import com.astra.dewa.utils.email.EmailUploadUtils;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ApplicationHeaderServiceImpl implements ApplicationHeaderService {
   private final Log LOG = LogFactoryUtil.getLog(getClass().getName());

   @Override
   public void sendApprovalReminder() throws PortalException {
      Calendar calendar = CalendarFactoryUtil.getCalendar();
      calendar.add(Calendar.DATE, -2);
      Date modifiedDate = calendar.getTime();

      DynamicQuery appHeaderQuery = ApplicationHeaderLocalServiceUtil.dynamicQuery()
            .add(PropertyFactoryUtil.forName("ApplicationHeaderStatusId").in(ApplicationHeaderStatusEnum.waitingStatusIdList()))
            .add(RestrictionsFactoryUtil.lt("ModifiedDate", modifiedDate))
            .add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<ApplicationHeader> requests = ApplicationHeaderLocalServiceUtil.dynamicQuery(appHeaderQuery);

      assert requests != null;
      LOG.info("Total active requests: " + requests.size());

      for (ApplicationHeader request : requests) {
         LOG.info("Active incoming requests: " + request.getTicketNo());
         DynamicQuery appAssignQuery = ApplicationAssignLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("ApplicationHeaderId", request.getId()))
               .add(RestrictionsFactoryUtil.eq("ApplicationAssignStatusId", ApplicationAssignStatusEnum.IN_REVIEW.getId()))
               .add(RestrictionsFactoryUtil.gt("Id", MasterApprovalUtils.getLastSubmitAssignId(request.getId())))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true))
               .setProjection(ProjectionFactoryUtil.property("ProfileId"));
         List<Long> assignees = ApplicationAssignLocalServiceUtil.dynamicQuery(appAssignQuery);

         assert assignees != null;

         // SEND REMINDER EMAIL TO ASSIGNEE(S)
         for (Long profileId : assignees) {
            LOG.info("Assignee: " + UserLocalServiceUtil.getUserById(profileId).getScreenName());
            EmailUploadUtils.sendEmailSubmitToDso(
                  request.getApplicationId() == ApplicationEnum.UPLOAD_BONUS.ordinal() + 1 ?
                        APIConstant.KWITANSI_BONUS :
                        APIConstant.KWITANSI_STNK,
                  request.getTicketNo(),
                  request.getReqDesc(),
                  request.getBusinessBenefit(),
                  request.getNotes(),
                  profileId
            );
         }
      }
   }
}
