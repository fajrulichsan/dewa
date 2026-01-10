package com.astra.dewa.cms.command.action.master_approval.service;

import com.astra.dewa.model.MasterApproval;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;

public interface MasterApprovalService {
   boolean isDataExist(int entryId, int menuId) throws Exception;
   JSONObject createMasterApproval(MasterApproval masterApproval, User user, String approverList) throws Exception;
   JSONObject updateMasterApproval(MasterApproval masterApproval, User user, String approverList) throws Exception;
   JSONObject deleteMasterApproval(int entryId, User user) throws Exception;
}
