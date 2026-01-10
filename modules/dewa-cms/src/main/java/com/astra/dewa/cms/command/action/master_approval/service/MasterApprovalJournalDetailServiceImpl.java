package com.astra.dewa.cms.command.action.master_approval.service;

import com.astra.dewa.model.MasterApprovalDetail;
import com.astra.dewa.model.MasterApprovalDetailJournal;
import com.astra.dewa.service.MasterApprovalDetailJournalLocalServiceUtil;
import com.astra.dewa.service.MasterApprovalDetailLocalServiceUtil;
import com.astra.dewa.service.MasterApprovalJournalLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;

import java.util.Date;

public class MasterApprovalJournalDetailServiceImpl implements MasterApprovalJournalDetailService {
   private final Log LOG = LogFactoryUtil.getLog(MasterApprovalJournalDetailServiceImpl.class);

   @Override
   public void createMasterApprovalJournalDetail(MasterApprovalDetail masterApprovalDetail) throws Exception {
      try {
         int count = MasterApprovalJournalLocalServiceUtil.getMasterApprovalJournalsCount();
         MasterApprovalDetailJournal masterApprovalDetailJournal = MasterApprovalDetailJournalLocalServiceUtil.createMasterApprovalDetailJournal(0);
         masterApprovalDetailJournal.setMasterApprovalId(masterApprovalDetail.getMasterApprovalId());
         masterApprovalDetailJournal.setMasterApprovalJournalId(count);
         masterApprovalDetailJournal.setMasterApprovalDetailId(masterApprovalDetail.getId());
         masterApprovalDetailJournal.setUserId(masterApprovalDetail.getUserId());
         masterApprovalDetailJournal.setApprovalLevel(masterApprovalDetail.getApprovalLevel());
         masterApprovalDetailJournal.setIsDefault(masterApprovalDetail.getIsDefault());
         masterApprovalDetailJournal.setRowStatus(masterApprovalDetail.getRowStatus());
         masterApprovalDetailJournal.setCreatedDate(masterApprovalDetail.getCreatedDate());
         masterApprovalDetailJournal.setCreatedBy(masterApprovalDetail.getCreatedBy());
         masterApprovalDetailJournal.setModifiedDate(masterApprovalDetail.getModifiedDate());
         masterApprovalDetailJournal.setModifiedBy(masterApprovalDetail.getModifiedBy());
         MasterApprovalDetailJournalLocalServiceUtil.updateMasterApprovalDetailJournal(masterApprovalDetailJournal);
      } catch (Exception e) {
         LOG.error(e);
         throw new Exception(e);
      }
   }

   public void createUpdatedMasterApprovalJournalDetail(int masterApprovalId, JSONObject approver, User user) throws Exception {
      try {
         Date now = new Date();
         int detailCounts = MasterApprovalDetailLocalServiceUtil.getMasterApprovalDetailsCount();
         int journalsCount = MasterApprovalJournalLocalServiceUtil.getMasterApprovalJournalsCount();
         MasterApprovalDetail masterApprovalDetail = MasterApprovalDetailLocalServiceUtil.getMasterApprovalDetail(detailCounts);
         MasterApprovalDetailJournal masterApprovalDetailJournal = MasterApprovalDetailJournalLocalServiceUtil.createMasterApprovalDetailJournal(0);
         masterApprovalDetailJournal.setMasterApprovalId(masterApprovalId);
         masterApprovalDetailJournal.setMasterApprovalJournalId(journalsCount);
         masterApprovalDetailJournal.setMasterApprovalDetailId(masterApprovalDetail.getId());
         masterApprovalDetailJournal.setUserId(approver.getLong("userId"));
         masterApprovalDetailJournal.setApprovalLevel(approver.getInt("level"));
         masterApprovalDetailJournal.setIsDefault(approver.getBoolean("isDefault"));
         masterApprovalDetailJournal.setRowStatus(true);
         masterApprovalDetailJournal.setCreatedDate(now);
         masterApprovalDetailJournal.setCreatedBy(user.getScreenName());
         masterApprovalDetailJournal.setModifiedDate(now);
         masterApprovalDetailJournal.setModifiedBy(user.getScreenName());
         MasterApprovalDetailJournalLocalServiceUtil.updateMasterApprovalDetailJournal(masterApprovalDetailJournal);
      } catch (Exception e) {
         LOG.error(e);
         throw new Exception(e);
      }
   }

   @Override
   public void createDeletedMasterApprovalJournalDetail(MasterApprovalDetail masterApprovalDetail) throws Exception {
      try {
         int count = MasterApprovalJournalLocalServiceUtil.getMasterApprovalJournalsCount();
         MasterApprovalDetailJournal masterApprovalDetailJournal = MasterApprovalDetailJournalLocalServiceUtil.createMasterApprovalDetailJournal(0);
         masterApprovalDetailJournal.setMasterApprovalId(masterApprovalDetail.getMasterApprovalId());
         masterApprovalDetailJournal.setMasterApprovalJournalId(count);
         masterApprovalDetailJournal.setMasterApprovalDetailId(masterApprovalDetail.getId());
         masterApprovalDetailJournal.setUserId(masterApprovalDetail.getUserId());
         masterApprovalDetailJournal.setApprovalLevel(masterApprovalDetail.getApprovalLevel());
         masterApprovalDetailJournal.setIsDefault(masterApprovalDetail.getIsDefault());
         masterApprovalDetailJournal.setRowStatus(false);
         masterApprovalDetailJournal.setCreatedDate(masterApprovalDetail.getCreatedDate());
         masterApprovalDetailJournal.setCreatedBy(masterApprovalDetail.getCreatedBy());
         masterApprovalDetailJournal.setModifiedDate(masterApprovalDetail.getModifiedDate());
         masterApprovalDetailJournal.setModifiedBy(masterApprovalDetail.getModifiedBy());
         MasterApprovalDetailJournalLocalServiceUtil.updateMasterApprovalDetailJournal(masterApprovalDetailJournal);
      } catch (Exception e) {
         LOG.error(e);
         throw new Exception(e);
      }
   }
}
