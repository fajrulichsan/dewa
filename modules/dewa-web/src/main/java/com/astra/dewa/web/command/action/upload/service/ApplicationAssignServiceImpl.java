package com.astra.dewa.web.command.action.upload.service;

import com.astra.dewa.model.ApplicationAssign;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.service.ApplicationAssignLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Date;

public class ApplicationAssignServiceImpl implements ApplicationAssignService {
   private final Log LOG = LogFactoryUtil.getLog(ApplicationAssignServiceImpl.class);

   @Override
   public ApplicationAssign createApplicationAssign(ApplicationHeader header, int applicationAssignStatusId, String notes, long profileId, String screenName) {
      try {
         Date now = new Date();
         ApplicationAssign assign = ApplicationAssignLocalServiceUtil.createApplicationAssign(0);
         assign.setApplicationHeaderId(header.getId());
         assign.setApplicationAssignStatusId(applicationAssignStatusId);
         assign.setProfileId(profileId);
         assign.setStartDateOn(now);
         assign.setCompletedDateOn(null);
         assign.setNotes(notes);
         assign.setRowStatus(true);
         assign.setCreatedBy(screenName);
         assign.setCreatedDate(now);
         assign.setModifiedBy(screenName);
         assign.setModifiedDate(now);
         ApplicationAssignLocalServiceUtil.addApplicationAssign(assign);
         return assign;
      } catch (Exception e) {
         LOG.error(e);
         return null;
      }
   }
}
