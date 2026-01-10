package com.astra.dewa.cms.command.action.master_approval.service;

import com.astra.dewa.model.MasterApprovalDetail;
import com.astra.dewa.service.MasterApprovalDetailLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;

import java.util.Date;

public class MasterApprovalDetailServiceImpl implements MasterApprovalDetailService {
   private final Log LOG = LogFactoryUtil.getLog(MasterApprovalDetailServiceImpl.class);
   private final Date NOW = new Date();
   private final MasterApprovalJournalDetailServiceImpl masterApprovalJournalDetailService = new MasterApprovalJournalDetailServiceImpl();

   @Override
   public void createMasterApprovalDetail(int masterApprovalId, User user, JSONObject approver) throws Exception {
      try {
         MasterApprovalDetail masterApprovalDetail = MasterApprovalDetailLocalServiceUtil.createMasterApprovalDetail(0);
         masterApprovalDetail.setMasterApprovalId(masterApprovalId);
         masterApprovalDetail.setUserId(approver.getInt("userId"));
         masterApprovalDetail.setApprovalLevel(approver.getInt("level"));
         masterApprovalDetail.setIsDefault(approver.getBoolean("isDefault"));
         masterApprovalDetail.setCreatedDate(NOW);
         masterApprovalDetail.setCreatedBy(user.getScreenName());
         masterApprovalDetail.setModifiedDate(NOW);
         masterApprovalDetail.setModifiedBy(user.getScreenName());
         masterApprovalDetail.setRowStatus(true);
         MasterApprovalDetailLocalServiceUtil.addMasterApprovalDetail(masterApprovalDetail);
         masterApprovalJournalDetailService.createMasterApprovalJournalDetail(masterApprovalDetail);
      } catch (Exception e) {
         LOG.error(e);
         throw new Exception(e);
      }
   }

   public void createAdditionalMasterApprovalDetail(int masterApprovalId, User user, JSONObject approver) throws Exception {
      try {
         MasterApprovalDetail masterApprovalDetail = MasterApprovalDetailLocalServiceUtil.createMasterApprovalDetail(0);
         masterApprovalDetail.setMasterApprovalId(masterApprovalId);
         masterApprovalDetail.setUserId(approver.getInt("userId"));
         masterApprovalDetail.setApprovalLevel(approver.getInt("level"));
         masterApprovalDetail.setIsDefault(approver.getBoolean("isDefault"));
         masterApprovalDetail.setCreatedDate(NOW);
         masterApprovalDetail.setCreatedBy(user.getScreenName());
         masterApprovalDetail.setModifiedDate(NOW);
         masterApprovalDetail.setModifiedBy(user.getScreenName());
         masterApprovalDetail.setRowStatus(true);
         MasterApprovalDetailLocalServiceUtil.addMasterApprovalDetail(masterApprovalDetail);
      } catch (Exception e) {
         LOG.error(e);
         throw new Exception(e);
      }
   }

   @Override
   public void updateMasterApprovalDetail(int masterApprovalDetailId, User user, JSONObject approver) throws Exception {
      try {
         MasterApprovalDetail masterApprovalDetail = MasterApprovalDetailLocalServiceUtil.getMasterApprovalDetail(masterApprovalDetailId);
         masterApprovalDetail.setUserId(approver.getInt("userId"));
         masterApprovalDetail.setApprovalLevel(approver.getInt("level"));
         masterApprovalDetail.setIsDefault(approver.getBoolean("isDefault"));
         masterApprovalDetail.setModifiedDate(NOW);
         masterApprovalDetail.setModifiedBy(user.getScreenName());
         MasterApprovalDetailLocalServiceUtil.updateMasterApprovalDetail(masterApprovalDetail);
      } catch (Exception e) {
         LOG.error(e);
         throw new Exception(e);
      }
   }

   @Override
   public void deleteMasterApprovalDetail(int masterApprovalDetailId, User user) throws Exception {
      try {
         MasterApprovalDetail masterApprovalDetail = MasterApprovalDetailLocalServiceUtil.getMasterApprovalDetail(masterApprovalDetailId);
         masterApprovalDetail.setModifiedDate(NOW);
         masterApprovalDetail.setModifiedBy(user.getScreenName());
         masterApprovalDetail.setRowStatus(false);
         MasterApprovalDetailLocalServiceUtil.updateMasterApprovalDetail(masterApprovalDetail);
      } catch (Exception e) {
         LOG.error(e);
         throw new Exception(e);
      }
   }

   @Override
   public boolean isDataChanged(MasterApprovalDetail masterApprovalDetail, JSONObject apprrover) throws Exception {
      try {
         return (
               masterApprovalDetail.getUserId() != apprrover.getInt("userId") ||
               masterApprovalDetail.getApprovalLevel() != apprrover.getInt("level") ||
               masterApprovalDetail.getIsDefault() != apprrover.getBoolean("isDefault")
         );
      } catch (Exception e) {
         LOG.error(e);
         throw new Exception(e);
      }
   }
}
