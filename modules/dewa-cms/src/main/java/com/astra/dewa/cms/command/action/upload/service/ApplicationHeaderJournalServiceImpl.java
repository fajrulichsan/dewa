package com.astra.dewa.cms.command.action.upload.service;

import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.model.ApplicationHeaderJournal;
import com.astra.dewa.service.ApplicationHeaderJournalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class ApplicationHeaderJournalServiceImpl implements ApplicationHeaderJournalService {
   private static final Log LOG = LogFactoryUtil.getLog(ApplicationHeaderJournalServiceImpl.class);

   @Override
   public void createApplicationHeaderJournal(ApplicationHeader header) throws PortalException {
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
         journal.setRowStatus(header.getRowStatus());
         journal.setCreatedBy(header.getCreatedBy());
         journal.setCreatedDate(header.getCreatedDate());
         journal.setModifiedBy(header.getModifiedBy());
         journal.setModifiedDate(header.getModifiedDate());
         ApplicationHeaderJournalLocalServiceUtil.addApplicationHeaderJournal(journal);
      } catch (Exception e) {
         LOG.error(e);
         throw new PortalException();
      }
   }
}
