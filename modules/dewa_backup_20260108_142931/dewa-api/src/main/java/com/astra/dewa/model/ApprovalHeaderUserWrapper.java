/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.astra.dewa.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ApprovalHeaderUser}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApprovalHeaderUser
 * @generated
 */
public class ApprovalHeaderUserWrapper
	extends BaseModelWrapper<ApprovalHeaderUser>
	implements ApprovalHeaderUser, ModelWrapper<ApprovalHeaderUser> {

	public ApprovalHeaderUserWrapper(ApprovalHeaderUser approvalHeaderUser) {
		super(approvalHeaderUser);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put(
			"ApplicationAssignStatusId", getApplicationAssignStatusId());
		attributes.put("ApproverUserId", getApproverUserId());
		attributes.put("DealerId", getDealerId());
		attributes.put("CabangId", getCabangId());
		attributes.put("RequesterName", getRequesterName());
		attributes.put("RequesterEmail", getRequesterEmail());
		attributes.put("RejectReason", getRejectReason());
		attributes.put("RowStatus", getRowStatus());
		attributes.put("CreatedBy", getCreatedBy());
		attributes.put("CreatedDate", getCreatedDate());
		attributes.put("ModifiedBy", getModifiedBy());
		attributes.put("ModifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer Id = (Integer)attributes.get("Id");

		if (Id != null) {
			setId(Id);
		}

		Integer ApplicationAssignStatusId = (Integer)attributes.get(
			"ApplicationAssignStatusId");

		if (ApplicationAssignStatusId != null) {
			setApplicationAssignStatusId(ApplicationAssignStatusId);
		}

		Long ApproverUserId = (Long)attributes.get("ApproverUserId");

		if (ApproverUserId != null) {
			setApproverUserId(ApproverUserId);
		}

		Integer DealerId = (Integer)attributes.get("DealerId");

		if (DealerId != null) {
			setDealerId(DealerId);
		}

		Integer CabangId = (Integer)attributes.get("CabangId");

		if (CabangId != null) {
			setCabangId(CabangId);
		}

		String RequesterName = (String)attributes.get("RequesterName");

		if (RequesterName != null) {
			setRequesterName(RequesterName);
		}

		String RequesterEmail = (String)attributes.get("RequesterEmail");

		if (RequesterEmail != null) {
			setRequesterEmail(RequesterEmail);
		}

		String RejectReason = (String)attributes.get("RejectReason");

		if (RejectReason != null) {
			setRejectReason(RejectReason);
		}

		Boolean RowStatus = (Boolean)attributes.get("RowStatus");

		if (RowStatus != null) {
			setRowStatus(RowStatus);
		}

		String CreatedBy = (String)attributes.get("CreatedBy");

		if (CreatedBy != null) {
			setCreatedBy(CreatedBy);
		}

		Date CreatedDate = (Date)attributes.get("CreatedDate");

		if (CreatedDate != null) {
			setCreatedDate(CreatedDate);
		}

		String ModifiedBy = (String)attributes.get("ModifiedBy");

		if (ModifiedBy != null) {
			setModifiedBy(ModifiedBy);
		}

		Date ModifiedDate = (Date)attributes.get("ModifiedDate");

		if (ModifiedDate != null) {
			setModifiedDate(ModifiedDate);
		}
	}

	@Override
	public ApprovalHeaderUser cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the application assign status ID of this approval header user.
	 *
	 * @return the application assign status ID of this approval header user
	 */
	@Override
	public int getApplicationAssignStatusId() {
		return model.getApplicationAssignStatusId();
	}

	/**
	 * Returns the approver user ID of this approval header user.
	 *
	 * @return the approver user ID of this approval header user
	 */
	@Override
	public long getApproverUserId() {
		return model.getApproverUserId();
	}

	/**
	 * Returns the approver user uuid of this approval header user.
	 *
	 * @return the approver user uuid of this approval header user
	 */
	@Override
	public String getApproverUserUuid() {
		return model.getApproverUserUuid();
	}

	/**
	 * Returns the cabang ID of this approval header user.
	 *
	 * @return the cabang ID of this approval header user
	 */
	@Override
	public int getCabangId() {
		return model.getCabangId();
	}

	/**
	 * Returns the created by of this approval header user.
	 *
	 * @return the created by of this approval header user
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this approval header user.
	 *
	 * @return the created date of this approval header user
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the dealer ID of this approval header user.
	 *
	 * @return the dealer ID of this approval header user
	 */
	@Override
	public int getDealerId() {
		return model.getDealerId();
	}

	/**
	 * Returns the ID of this approval header user.
	 *
	 * @return the ID of this approval header user
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this approval header user.
	 *
	 * @return the modified by of this approval header user
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this approval header user.
	 *
	 * @return the modified date of this approval header user
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this approval header user.
	 *
	 * @return the primary key of this approval header user
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the reject reason of this approval header user.
	 *
	 * @return the reject reason of this approval header user
	 */
	@Override
	public String getRejectReason() {
		return model.getRejectReason();
	}

	/**
	 * Returns the requester email of this approval header user.
	 *
	 * @return the requester email of this approval header user
	 */
	@Override
	public String getRequesterEmail() {
		return model.getRequesterEmail();
	}

	/**
	 * Returns the requester name of this approval header user.
	 *
	 * @return the requester name of this approval header user
	 */
	@Override
	public String getRequesterName() {
		return model.getRequesterName();
	}

	/**
	 * Returns the row status of this approval header user.
	 *
	 * @return the row status of this approval header user
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the application assign status ID of this approval header user.
	 *
	 * @param ApplicationAssignStatusId the application assign status ID of this approval header user
	 */
	@Override
	public void setApplicationAssignStatusId(int ApplicationAssignStatusId) {
		model.setApplicationAssignStatusId(ApplicationAssignStatusId);
	}

	/**
	 * Sets the approver user ID of this approval header user.
	 *
	 * @param ApproverUserId the approver user ID of this approval header user
	 */
	@Override
	public void setApproverUserId(long ApproverUserId) {
		model.setApproverUserId(ApproverUserId);
	}

	/**
	 * Sets the approver user uuid of this approval header user.
	 *
	 * @param ApproverUserUuid the approver user uuid of this approval header user
	 */
	@Override
	public void setApproverUserUuid(String ApproverUserUuid) {
		model.setApproverUserUuid(ApproverUserUuid);
	}

	/**
	 * Sets the cabang ID of this approval header user.
	 *
	 * @param CabangId the cabang ID of this approval header user
	 */
	@Override
	public void setCabangId(int CabangId) {
		model.setCabangId(CabangId);
	}

	/**
	 * Sets the created by of this approval header user.
	 *
	 * @param CreatedBy the created by of this approval header user
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this approval header user.
	 *
	 * @param CreatedDate the created date of this approval header user
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the dealer ID of this approval header user.
	 *
	 * @param DealerId the dealer ID of this approval header user
	 */
	@Override
	public void setDealerId(int DealerId) {
		model.setDealerId(DealerId);
	}

	/**
	 * Sets the ID of this approval header user.
	 *
	 * @param Id the ID of this approval header user
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this approval header user.
	 *
	 * @param ModifiedBy the modified by of this approval header user
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this approval header user.
	 *
	 * @param ModifiedDate the modified date of this approval header user
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this approval header user.
	 *
	 * @param primaryKey the primary key of this approval header user
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the reject reason of this approval header user.
	 *
	 * @param RejectReason the reject reason of this approval header user
	 */
	@Override
	public void setRejectReason(String RejectReason) {
		model.setRejectReason(RejectReason);
	}

	/**
	 * Sets the requester email of this approval header user.
	 *
	 * @param RequesterEmail the requester email of this approval header user
	 */
	@Override
	public void setRequesterEmail(String RequesterEmail) {
		model.setRequesterEmail(RequesterEmail);
	}

	/**
	 * Sets the requester name of this approval header user.
	 *
	 * @param RequesterName the requester name of this approval header user
	 */
	@Override
	public void setRequesterName(String RequesterName) {
		model.setRequesterName(RequesterName);
	}

	/**
	 * Sets the row status of this approval header user.
	 *
	 * @param RowStatus the row status of this approval header user
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected ApprovalHeaderUserWrapper wrap(
		ApprovalHeaderUser approvalHeaderUser) {

		return new ApprovalHeaderUserWrapper(approvalHeaderUser);
	}

}