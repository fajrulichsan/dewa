package com.astra.dewa.utils;

import com.astra.dewa.model.*;
import com.astra.dewa.service.ApplicationAssignLocalServiceUtil;
import com.astra.dewa.service.MasterApprovalDetailLocalServiceUtil;
import com.astra.dewa.service.MasterApprovalLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.List;

public class MasterApprovalUtils {
   private static final Log LOG = LogFactoryUtil.getLog(MasterApprovalUtils.class);

   /**
    * @param applicationId foreign key from Application
    * @return the Master Approval ID
    */
   public static int getMasterApprovalId(int applicationId) {
      try {
         DynamicQuery query = MasterApprovalLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("MenuId", applicationId));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         List<MasterApproval> masterApprovals = MasterApprovalLocalServiceUtil.dynamicQuery(query);
         return masterApprovals.isEmpty() ? 0 : masterApprovals.get(0).getId();
      } catch (Exception e) {
         LOG.error(e);
         return 0;
      }
   }

   /**
    * @param applicationId foreign key from Application
    * @return the Role ID of Master Approval
    * @throws NullPointerException
    */
   public static int getMasterApprovalRoleId(int applicationId) throws NullPointerException {
      try {
         DynamicQuery query = MasterApprovalLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("MenuId", applicationId))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true));
         List<MasterApproval> result = MasterApprovalLocalServiceUtil.dynamicQuery(query);
         return result.isEmpty() ? 0 : result.get(0).getRoleId();
      } catch (Exception e) {
         LOG.error(e);
         throw new NullPointerException();
      }
   }

   /**
    * @param applicationId foreign key from Application
    * @return the workflow status of the Master Approval
    * TRUE : workflow status is Sequential
    * FALSE : workflow status is Parallel
    * @throws PortalException
    */
   public static boolean isSequential(int applicationId) throws PortalException {
      try {
         DynamicQuery query = MasterApprovalLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("MenuId", applicationId));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         List<MasterApproval> masterApprovals = MasterApprovalLocalServiceUtil.dynamicQuery(query);
         return masterApprovals.get(0).getApprovalWorkflow();
      } catch (Exception e) {
         LOG.error(e);
         throw new PortalException();
      }
   }

   /**
    * @param masterApprovalId foreign key from Master Approval
    * @param userId foreign key from User
    * @return the Master Approval Detail
    */
   public static MasterApprovalDetail getMasterApprovalDetail(int masterApprovalId, long userId) {
      try {
         DynamicQuery query = MasterApprovalDetailLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.add(RestrictionsFactoryUtil.eq("UserId", userId));
         query.add(RestrictionsFactoryUtil.eq("MasterApprovalId", masterApprovalId));
         List<MasterApprovalDetail> data = MasterApprovalDetailLocalServiceUtil.dynamicQuery(query);
         return data.isEmpty() ? null : data.get(0);
      } catch (Exception e) {
         LOG.error(e);
         return null;
      }
   }

   /**
    * @param masterApprovalId foreign key from Master Approval
    * @return the list of active approvers in Master Approval
    * @throws PortalException
    */
   public static List<MasterApprovalDetail> getApprovers(int masterApprovalId) throws PortalException {
      try {
         DynamicQuery query = MasterApprovalDetailLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("MasterApprovalId", masterApprovalId));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.addOrder(OrderFactoryUtil.asc("ApprovalLevel"));
         List<MasterApprovalDetail> approvers = MasterApprovalDetailLocalServiceUtil.dynamicQuery(query);
         return approvers.isEmpty() ? null : approvers;
      } catch (Exception e) {
         LOG.error(e);
         throw new PortalException();
      }
   }

   /**
    * @param approvers list of Approver as JSON Array format
    * @param userId foreign key from User
    * @return the approver in JSON Object format
    */
   public static JSONObject getApprover(JSONArray approvers, long userId) {
      for (int i = 0; i < approvers.length(); i++) {
         JSONObject approver = approvers.getJSONObject(i);
         if (approver.getLong("userId") == userId) {
            return approver;
         }
      }
      return null;
   }

   /**
    * @param masterApprovalId foreign key from Master Approval
    * @return the default approver of the Master Approval
    */
   public static User getDefaultApprover(int masterApprovalId) {
      try {
         DynamicQuery query = MasterApprovalDetailLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("MasterApprovalId", masterApprovalId));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.add(RestrictionsFactoryUtil.eq("IsDefault", true));
         query.addOrder(OrderFactoryUtil.asc("ApprovalLevel"));
         List<MasterApprovalDetail> approvers = MasterApprovalDetailLocalServiceUtil.dynamicQuery(query);
         return approvers.isEmpty() ? null : UserLocalServiceUtil.getUserById(approvers.get(0).getUserId());
      } catch (Exception e) {
         LOG.error(e);
         return null;
      }
   }

   /**
    * @param userId foreign key from User
    * @return the active status of the Approver
    */
   public static boolean isActiveApprover(long userId) {
      try {
         DynamicQuery query = MasterApprovalDetailLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("RowStatus", true))
               .add(RestrictionsFactoryUtil.eq("UserId", userId));
         List<MasterApprovalDetail> result = MasterApprovalDetailLocalServiceUtil.dynamicQuery(query);
         return !result.isEmpty();
      } catch (Exception e) {
         LOG.error(e);
         throw new NullPointerException();
      }
   }

   /**
    * @param applicationId foreign key from Application
    * @param userId foreign key from User
    * @return the active status of the Approver
    */
   public static boolean isActiveApprover(int applicationId, long userId) {
      try {
         DynamicQuery query = MasterApprovalDetailLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("MasterApprovalId", getMasterApprovalId(applicationId)))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true))
               .add(RestrictionsFactoryUtil.eq("UserId", userId));
         List<MasterApprovalDetail> result = MasterApprovalDetailLocalServiceUtil.dynamicQuery(query);
         return !result.isEmpty();
      } catch (Exception e) {
         LOG.error(e);
         throw new NullPointerException();
      }
   }

   /**
    * @param applicationHeaderId foreign key of Application Header
    * @return the last Application Assign ID with status SUBMIT
    */
   public static Integer getLastSubmitAssignId(int applicationHeaderId) {
      try {
         DynamicQuery query = ApplicationAssignLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("ApplicationHeaderId", applicationHeaderId))
               .add(RestrictionsFactoryUtil.eq("ApplicationAssignStatusId", ApplicationAssignStatusEnum.SUBMIT.getId()))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true))
               .addOrder(OrderFactoryUtil.desc("Id"));
         List<ApplicationAssign> result = ApplicationAssignLocalServiceUtil.dynamicQuery(query, 0, 1);
         if (!result.isEmpty()) return result.get(0).getId();
         else return null;
      } catch (Exception e) {
         LOG.error(e);
         return null;
      }
   }

   /**
    * @param applicationHeaderId foreign key from Application Header
    * @param lastSubmitAssignId the last Application Assign ID with status SUBMIT
    * @return the list of active assignees
    */
   public static List<ApplicationAssign> getActiveAssignees(int applicationHeaderId, int lastSubmitAssignId) {
      try {
         DynamicQuery query = ApplicationAssignLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("ApplicationHeaderId", applicationHeaderId))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true))
               .add(RestrictionsFactoryUtil.gt("Id", lastSubmitAssignId));
         List<ApplicationAssign> result = ApplicationAssignLocalServiceUtil.dynamicQuery(query);
         if (!result.isEmpty()) return result;
         else return null;
      } catch (Exception e) {
         LOG.error(e);
         return null;
      }
   }

   /**
    * @param applicationHeaderId foreign key from Application Header
    * @param userId foreign key from User
    * @return the active status of the Assignee
    * @throws NullPointerException
    */
   public static boolean isActiveAssignee(int applicationHeaderId, long userId) throws NullPointerException {
      try {
         int lastSubmitAssignId = getLastSubmitAssignId(applicationHeaderId);
         DynamicQuery query = ApplicationAssignLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("ApplicationHeaderId", applicationHeaderId))
               .add(RestrictionsFactoryUtil.eq("ApplicationAssignStatusId", ApplicationAssignStatusEnum.IN_REVIEW.getId()))
               .add(RestrictionsFactoryUtil.eq("ProfileId", userId))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true))
               .add(RestrictionsFactoryUtil.gt("Id", lastSubmitAssignId));
         List<ApplicationAssign> result = ApplicationAssignLocalServiceUtil.dynamicQuery(query);
         return !result.isEmpty();
      } catch (Exception e) {
         LOG.error(e);
         throw new NullPointerException();
      }
   }

   /**
    * @param applicationId foreign key from Application
    * @param applicationHeaderId foreign key from Application Header
    * @param lastSubmitAssignId the last Application Assign ID with status SUBMIT
    * @return
    * @throws NullPointerException
    */
   public static boolean hasAllAsigneesApproved(int applicationId, int applicationHeaderId, int lastSubmitAssignId) throws NullPointerException {
      try {
         DynamicQuery query = ApplicationAssignLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("ApplicationHeaderId", applicationHeaderId))
               .add(RestrictionsFactoryUtil.eq("ApplicationAssignStatusId", ApplicationAssignStatusEnum.APPROVE.getId()))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true))
               .add(RestrictionsFactoryUtil.gt("Id", lastSubmitAssignId));
         List<ApplicationAssign> assignees = ApplicationAssignLocalServiceUtil.dynamicQuery(query);
         List<MasterApprovalDetail> approvers = getApprovers(getMasterApprovalId(applicationId));
         return assignees.size() == approvers.size();
      } catch (Exception e) {
         LOG.error(e);
         throw new NullPointerException();
      }
   }

   /**
    * @param applicationHeaderId foreign key from Application Header
    * @param lastSubmitAssignId the last Application Assign ID with status SUBMIT
    * @return the total approve of the Application Header
    * @throws NullPointerException
    */
   public static int approveCount(int applicationHeaderId, int lastSubmitAssignId) throws NullPointerException {
      try {
         DynamicQuery query = ApplicationAssignLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("ApplicationHeaderId", applicationHeaderId))
               .add(RestrictionsFactoryUtil.eq("ApplicationAssignStatusId", ApplicationAssignStatusEnum.APPROVE.getId()))
               .add(RestrictionsFactoryUtil.eq("RowStatus", true))
               .add(RestrictionsFactoryUtil.gt("Id", lastSubmitAssignId));
         List<ApplicationAssign> assignees = ApplicationAssignLocalServiceUtil.dynamicQuery(query);
         return assignees.isEmpty() ? 0 : assignees.size();
      } catch (Exception e) {
         LOG.error(e);
         throw new NullPointerException();
      }
   }
}
