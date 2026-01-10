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
 * This class is a wrapper for {@link ApprovalDetailUser}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApprovalDetailUser
 * @generated
 */
public class ApprovalDetailUserWrapper
	extends BaseModelWrapper<ApprovalDetailUser>
	implements ApprovalDetailUser, ModelWrapper<ApprovalDetailUser> {

	public ApprovalDetailUserWrapper(ApprovalDetailUser approvalDetailUser) {
		super(approvalDetailUser);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("ApprovalHeaderUserId", getApprovalHeaderUserId());
		attributes.put("RoleId", getRoleId());
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

		Integer ApprovalHeaderUserId = (Integer)attributes.get(
			"ApprovalHeaderUserId");

		if (ApprovalHeaderUserId != null) {
			setApprovalHeaderUserId(ApprovalHeaderUserId);
		}

		Integer RoleId = (Integer)attributes.get("RoleId");

		if (RoleId != null) {
			setRoleId(RoleId);
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
	public ApprovalDetailUser cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the approval header user ID of this approval detail user.
	 *
	 * @return the approval header user ID of this approval detail user
	 */
	@Override
	public int getApprovalHeaderUserId() {
		return model.getApprovalHeaderUserId();
	}

	/**
	 * Returns the created by of this approval detail user.
	 *
	 * @return the created by of this approval detail user
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this approval detail user.
	 *
	 * @return the created date of this approval detail user
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the ID of this approval detail user.
	 *
	 * @return the ID of this approval detail user
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this approval detail user.
	 *
	 * @return the modified by of this approval detail user
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this approval detail user.
	 *
	 * @return the modified date of this approval detail user
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this approval detail user.
	 *
	 * @return the primary key of this approval detail user
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the role ID of this approval detail user.
	 *
	 * @return the role ID of this approval detail user
	 */
	@Override
	public int getRoleId() {
		return model.getRoleId();
	}

	/**
	 * Returns the row status of this approval detail user.
	 *
	 * @return the row status of this approval detail user
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
	 * Sets the approval header user ID of this approval detail user.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID of this approval detail user
	 */
	@Override
	public void setApprovalHeaderUserId(int ApprovalHeaderUserId) {
		model.setApprovalHeaderUserId(ApprovalHeaderUserId);
	}

	/**
	 * Sets the created by of this approval detail user.
	 *
	 * @param CreatedBy the created by of this approval detail user
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this approval detail user.
	 *
	 * @param CreatedDate the created date of this approval detail user
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the ID of this approval detail user.
	 *
	 * @param Id the ID of this approval detail user
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this approval detail user.
	 *
	 * @param ModifiedBy the modified by of this approval detail user
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this approval detail user.
	 *
	 * @param ModifiedDate the modified date of this approval detail user
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this approval detail user.
	 *
	 * @param primaryKey the primary key of this approval detail user
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role ID of this approval detail user.
	 *
	 * @param RoleId the role ID of this approval detail user
	 */
	@Override
	public void setRoleId(int RoleId) {
		model.setRoleId(RoleId);
	}

	/**
	 * Sets the row status of this approval detail user.
	 *
	 * @param RowStatus the row status of this approval detail user
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
	protected ApprovalDetailUserWrapper wrap(
		ApprovalDetailUser approvalDetailUser) {

		return new ApprovalDetailUserWrapper(approvalDetailUser);
	}

}