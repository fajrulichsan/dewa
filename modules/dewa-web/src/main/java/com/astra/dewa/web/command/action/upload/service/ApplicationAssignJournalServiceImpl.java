package com.astra.dewa.web.command.action.upload.service;

import com.astra.dewa.model.ApplicationAssign;
import com.astra.dewa.model.ApplicationAssignJournal;
import com.astra.dewa.service.ApplicationAssignJournalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class ApplicationAssignJournalServiceImpl implements ApplicationAssignJournalService {
   private static final Log LOG = LogFactoryUtil.getLog(ApplicationAssignJournalServiceImpl.class);

   @Override
   public void createApplicationAssignJournal(ApplicationAssign assign) throws PortalException {
      try {
         ApplicationAssignJournal jurnal = ApplicationAssignJournalLocalServiceUtil.createApplicationAssignJournal(0);
         jurnal.setApplicationAssignId(assign.getId());
         jurnal.setApplicationHeaderId(assign.getApplicationHeaderId());
         jurnal.setApplicationAssignStatusId(assign.getApplicationAssignStatusId());
         jurnal.setProfileId(assign.getProfileId());
         jurnal.setStartDateOn(assign.getStartDateOn());
         jurnal.setCompletedDateOn(assign.getCompletedDateOn());
         jurnal.setNotes(assign.getNotes());
         jurnal.setRowStatus(assign.getRowStatus());
         jurnal.setCreatedBy(assign.getCreatedBy());
         jurnal.setCreatedDate(assign.getCreatedDate());
         jurnal.setModifiedBy(assign.getModifiedBy());
         jurnal.setModifiedDate(assign.getModifiedDate());
         ApplicationAssignJournalLocalServiceUtil.addApplicationAssignJournal(jurnal);
      } catch (Exception e) {
         LOG.error(e);
         throw new PortalException();
      }
   }
}
