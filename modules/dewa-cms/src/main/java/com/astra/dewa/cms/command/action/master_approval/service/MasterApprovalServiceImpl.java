package com.astra.dewa.cms.command.action.master_approval.service;

import com.astra.dewa.model.MasterApproval;
import com.astra.dewa.model.MasterApprovalDetail;
import com.astra.dewa.service.MasterApprovalDetailLocalServiceUtil;
import com.astra.dewa.service.MasterApprovalLocalServiceUtil;
import com.astra.dewa.utils.MasterApprovalUtils;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;

public class MasterApprovalServiceImpl implements MasterApprovalService {
   private final Log LOG = LogFactoryUtil.getLog(MasterApprovalServiceImpl.class);
   private final MasterApprovalJournalServiceImpl masterApprovalJournalService = new MasterApprovalJournalServiceImpl();
   private final MasterApprovalDetailServiceImpl masterApprovalDetailService = new MasterApprovalDetailServiceImpl();
   private final MasterApprovalJournalDetailServiceImpl masterApprovalJournalDetailService = new MasterApprovalJournalDetailServiceImpl();

   @Override
   public boolean isDataExist(int entryId, int menuId) {
      try {
         DynamicQuery query = MasterApprovalLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("MenuId", menuId));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         List<MasterApproval> masterApprovals = MasterApprovalLocalServiceUtil.dynamicQuery(query);
         if (!masterApprovals.isEmpty()) {
            return !(masterApprovals.get(0).getId() == entryId);
         } else {
            return false;
         }
      } catch (Exception e) {
         return false;
      }
   }

   @Override
   public JSONObject createMasterApproval(MasterApproval masterApproval, User user, String approverList) {
      try {
         Date now = new Date();
         masterApproval.setCreatedDate(now);
         masterApproval.setCreatedBy(user.getScreenName());
         masterApproval.setModifiedDate(now);
         masterApproval.setModifiedBy(user.getScreenName());
         MasterApprovalLocalServiceUtil.updateMasterApproval(masterApproval);
         masterApprovalJournalService.createMasterApprovalJournal(masterApproval);

         JSONArray approvers = JSONFactoryUtil.createJSONArray(approverList);
         for (int i = 0; i < approvers.length(); i++) {
            masterApprovalDetailService.createMasterApprovalDetail(masterApproval.getId(), user, approvers.getJSONObject(i));
         }
         return SUCCESS(200, "Data berhasil disimpan.");
      } catch (Exception e) {
         LOG.error(e);
         return ERROR("Gagal menyimpan data.");
      }
   }

   @Override
   public JSONObject updateMasterApproval(MasterApproval masterApproval, User user, String approverList) {
      try {
         Date now = new Date();
         masterApproval.setModifiedDate(now);
         masterApproval.setModifiedBy(user.getScreenName());
         MasterApprovalLocalServiceUtil.updateMasterApproval(masterApproval);
         masterApprovalJournalService.createMasterApprovalJournal(masterApproval);

         int masterApprovalId = masterApproval.getId();

         DynamicQuery query = MasterApprovalDetailLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("MasterApprovalId", masterApprovalId))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true));
         List<MasterApprovalDetail> masterApprovalDetails = MasterApprovalDetailLocalServiceUtil.dynamicQuery(query);

         JSONArray approvers = JSONFactoryUtil.createJSONArray(approverList);

         createMasterApprovalDetailJournal(masterApprovalId, masterApprovalDetails, user, approvers);

         if (masterApprovalDetails.size() == approvers.length()) {
            int i = 0;
            for (MasterApprovalDetail data : masterApprovalDetails) {
               JSONObject approver = approvers.getJSONObject(i);
               if (masterApprovalDetailService.isDataChanged(data, approver)) {
                  masterApprovalDetailService.updateMasterApprovalDetail(data.getId(), user, approver);
               }
               i++;
            }
         } else if (approvers.length() > masterApprovalDetails.size()) {
            int i = 0;
            for (MasterApprovalDetail data : masterApprovalDetails) {
               JSONObject approver = approvers.getJSONObject(i);
               if (masterApprovalDetailService.isDataChanged(data, approver)) {
                  masterApprovalDetailService.updateMasterApprovalDetail(data.getId(), user, approver);
               }
               i++;
            }
            for (int j = i; j < approvers.length(); j++) {
               JSONObject approver = approvers.getJSONObject(j);
               masterApprovalDetailService.createAdditionalMasterApprovalDetail(masterApprovalId, user, approver);
            }
         } else if (approvers.length() < masterApprovalDetails.size()) {
            int i = 0;
            for (MasterApprovalDetail data : masterApprovalDetails) {
               if (i < approvers.length()) {
                  JSONObject approver = approvers.getJSONObject(i);
                  if (masterApprovalDetailService.isDataChanged(data, approver)) {
                     masterApprovalDetailService.updateMasterApprovalDetail(data.getId(), user, approver);
                  }
               } else {
                  masterApprovalDetailService.deleteMasterApprovalDetail(data.getId(), user);
               }
               i++;
            }
         }

         return SUCCESS(200, "Data berhasil diubah.");
      } catch (Exception e) {
         LOG.error(e);
         return ERROR("Gagal mengubah data.");
      }
   }

   @Override
   public JSONObject deleteMasterApproval(int entryId, User user) {
      try {
         Date now = new Date();
         MasterApproval masterApproval = MasterApprovalLocalServiceUtil.getMasterApproval(entryId);
         masterApproval.setModifiedDate(now);
         masterApproval.setModifiedBy(user.getScreenName());
         masterApproval.setRowStatus(false);
         MasterApprovalLocalServiceUtil.updateMasterApproval(masterApproval);
         masterApprovalJournalService.createMasterApprovalJournal(masterApproval);

         DynamicQuery query = MasterApprovalDetailLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("MasterApprovalId", masterApproval.getId()));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         List<MasterApprovalDetail> masterApprovalDetails = MasterApprovalDetailLocalServiceUtil.dynamicQuery(query);
         for (MasterApprovalDetail data : masterApprovalDetails) {
            masterApprovalDetailService.deleteMasterApprovalDetail(data.getId(), user);
            masterApprovalJournalDetailService.createDeletedMasterApprovalJournalDetail(data);
         }
         return SUCCESS(200, "Data berhasil dihapus.");
      } catch (Exception e) {
         LOG.error(e);
         return ERROR("Gagal menghapus data.");
      }
   }

   private void createMasterApprovalDetailJournal(int masterApprovalId, List<MasterApprovalDetail> masterApprovalDetails, User user, JSONArray approvers) throws Exception {
      List<Long> existingApprovers = new ArrayList<>();
      List<Long> requestedApprovers = new ArrayList<>();

      for (MasterApprovalDetail data : masterApprovalDetails) {
         existingApprovers.add(data.getUserId());
      }

      for (int i = 0; i < approvers.length(); i++) {
         JSONObject approver = approvers.getJSONObject(i);
         requestedApprovers.add(approver.getLong("userId"));
      }

      List<Long> changedApprovers = new ArrayList<>(existingApprovers);
      changedApprovers.retainAll(requestedApprovers);

      List<Long> deletedApprovers = new ArrayList<>(existingApprovers);
      deletedApprovers.removeAll(requestedApprovers);

      List<Long> additionalApprovers = new ArrayList<>(requestedApprovers);
      additionalApprovers.removeAll(existingApprovers);

      if (!changedApprovers.isEmpty()) {
         changedApprovers.forEach(approverId -> {
            try {
               masterApprovalJournalDetailService.
                     createUpdatedMasterApprovalJournalDetail(masterApprovalId, MasterApprovalUtils.getApprover(approvers, approverId), user);
            } catch (Exception e) {
               LOG.error(e);
            }
         });
      }

      if (!deletedApprovers.isEmpty()) {
         deletedApprovers.forEach(approverId -> {
            try {
               masterApprovalJournalDetailService.createDeletedMasterApprovalJournalDetail(MasterApprovalUtils.getMasterApprovalDetail(masterApprovalId, approverId));
            } catch (Exception e) {
               LOG.error(e);
            }
         });
      }

      if (!additionalApprovers.isEmpty()) {
         additionalApprovers.forEach(approverId -> {
            try {
               masterApprovalJournalDetailService
                     .createUpdatedMasterApprovalJournalDetail(masterApprovalId, MasterApprovalUtils.getApprover(approvers, approverId), user);
            } catch (Exception e) {
               LOG.error(e);
            }
         });
      }
   }
}
