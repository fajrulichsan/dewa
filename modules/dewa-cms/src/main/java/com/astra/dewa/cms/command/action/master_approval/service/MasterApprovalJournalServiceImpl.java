package com.astra.dewa.cms.command.action.master_approval.service;

import com.astra.dewa.model.MasterApproval;
import com.astra.dewa.model.MasterApprovalJournal;
import com.astra.dewa.service.MasterApprovalJournalLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class MasterApprovalJournalServiceImpl implements MasterApprovalJournalService {
   private final Log LOG = LogFactoryUtil.getLog(MasterApprovalJournalServiceImpl.class);

   @Override
   public void createMasterApprovalJournal(MasterApproval masterApproval) throws Exception {
      try {
         MasterApprovalJournal masterApprovalJournal = MasterApprovalJournalLocalServiceUtil.createMasterApprovalJournal(0);
         masterApprovalJournal.setMasterApprovalId(masterApproval.getId());
         masterApprovalJournal.setRoleId(masterApproval.getRoleId());
         masterApprovalJournal.setMenuId(masterApproval.getMenuId());
         masterApprovalJournal.setApprovalGroup(masterApproval.getApprovalGroup());
         masterApprovalJournal.setApprovalWorkflow(masterApproval.getApprovalWorkflow());
         masterApprovalJournal.setRowStatus(masterApproval.getRowStatus());
         masterApprovalJournal.setCreatedBy(masterApproval.getCreatedBy());
         masterApprovalJournal.setModifiedBy(masterApproval.getModifiedBy());
         masterApprovalJournal.setCreatedDate(masterApproval.getCreatedDate());
         masterApprovalJournal.setModifiedDate(masterApproval.getModifiedDate());
         MasterApprovalJournalLocalServiceUtil.updateMasterApprovalJournal(masterApprovalJournal);
      } catch (Exception e) {
         LOG.error(e);
         throw new Exception();
      }
   }
}
