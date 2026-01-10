package com.astra.dewa.cms.command.action.master_approval.service;

import com.astra.dewa.model.MasterApprovalDetail;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;

public interface MasterApprovalDetailService {
   void createMasterApprovalDetail(int masterApprovalId, User user, JSONObject approver) throws Exception;
   void updateMasterApprovalDetail(int masterApprovalDetailId, User user, JSONObject approver) throws Exception;
   void deleteMasterApprovalDetail(int masterApprovalDetailId, User user) throws Exception;
   boolean isDataChanged(MasterApprovalDetail masterApprovalDetail, JSONObject apprrover) throws Exception;
}
