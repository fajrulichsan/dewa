package com.astra.dewa.cms.command.action.master_approval.service;

import com.astra.dewa.model.MasterApprovalDetail;

public interface MasterApprovalJournalDetailService {
   void createMasterApprovalJournalDetail(MasterApprovalDetail masterApprovalDetail) throws Exception;
   void createDeletedMasterApprovalJournalDetail(MasterApprovalDetail masterApprovalDetail) throws Exception;
}
