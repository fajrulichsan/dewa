package com.astra.dewa.cms.command.action.upload.service;

import com.astra.dewa.model.ApplicationAssign;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.service.ApplicationAssignLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;

import java.util.Date;

public class ApplicationAssignServiceImpl implements ApplicationAssignService {
   private final Log LOG = LogFactoryUtil.getLog(ApplicationAssignServiceImpl.class);

   @Override
   public ApplicationAssign createApplicationAssign(ApplicationHeader header, int applicationAssignStatusId, User user) {
      try {
         ApplicationAssign assign = ApplicationAssignLocalServiceUtil.createApplicationAssign(0);
         assign.setApplicationHeaderId(header.getId());
         assign.setApplicationAssignStatusId(applicationAssignStatusId);
         assign.setProfileId(user.getUserId());
         assign.setStartDateOn(new Date());
         assign.setCompletedDateOn(null);
         assign.setNotes(header.getNotes());
         assign.setRowStatus(true);
         assign.setCompletedDateOn(null);
         assign.setCreatedBy(user.getScreenName());
         assign.setCreatedDate(new Date());
         assign.setModifiedBy(user.getScreenName());
         assign.setModifiedDate(new Date());
         ApplicationAssignLocalServiceUtil.addApplicationAssign(assign);
         return assign;
      } catch (Exception e) {
         LOG.error(e);
         return null;
      }
   }

   @Override
   public ApplicationAssign updateApplicationAssign(ApplicationAssign applicationAssign, int applicationAssignStatusId, String notes, long profileId, String screenName) {
      try {
         applicationAssign.setApplicationAssignStatusId(applicationAssignStatusId);
         applicationAssign.setModifiedDate(new Date());
         applicationAssign.setModifiedBy(screenName);
         ApplicationAssignLocalServiceUtil.updateApplicationAssign(applicationAssign);
         return applicationAssign;
      } catch (Exception e) {
         LOG.error(e);
         return null;
      }
   }
}
