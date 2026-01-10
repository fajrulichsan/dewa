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
 * This class is a wrapper for {@link UserRoleType}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserRoleType
 * @generated
 */
public class UserRoleTypeWrapper
	extends BaseModelWrapper<UserRoleType>
	implements ModelWrapper<UserRoleType>, UserRoleType {

	public UserRoleTypeWrapper(UserRoleType userRoleType) {
		super(userRoleType);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("UserId", getUserId());
		attributes.put("RoleId", getRoleId());
		attributes.put("RowStatus", getRowStatus());
		attributes.put("CreatedDate", getCreatedDate());
		attributes.put("CreatedBy", getCreatedBy());
		attributes.put("ModifiedDate", getModifiedDate());
		attributes.put("ModifiedBy", getModifiedBy());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer Id = (Integer)attributes.get("Id");

		if (Id != null) {
			setId(Id);
		}

		Long UserId = (Long)attributes.get("UserId");

		if (UserId != null) {
			setUserId(UserId);
		}

		Integer RoleId = (Integer)attributes.get("RoleId");

		if (RoleId != null) {
			setRoleId(RoleId);
		}

		Boolean RowStatus = (Boolean)attributes.get("RowStatus");

		if (RowStatus != null) {
			setRowStatus(RowStatus);
		}

		Date CreatedDate = (Date)attributes.get("CreatedDate");

		if (CreatedDate != null) {
			setCreatedDate(CreatedDate);
		}

		String CreatedBy = (String)attributes.get("CreatedBy");

		if (CreatedBy != null) {
			setCreatedBy(CreatedBy);
		}

		Date ModifiedDate = (Date)attributes.get("ModifiedDate");

		if (ModifiedDate != null) {
			setModifiedDate(ModifiedDate);
		}

		String ModifiedBy = (String)attributes.get("ModifiedBy");

		if (ModifiedBy != null) {
			setModifiedBy(ModifiedBy);
		}
	}

	@Override
	public UserRoleType cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this user role type.
	 *
	 * @return the created by of this user role type
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this user role type.
	 *
	 * @return the created date of this user role type
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the ID of this user role type.
	 *
	 * @return the ID of this user role type
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this user role type.
	 *
	 * @return the modified by of this user role type
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this user role type.
	 *
	 * @return the modified date of this user role type
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this user role type.
	 *
	 * @return the primary key of this user role type
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the role ID of this user role type.
	 *
	 * @return the role ID of this user role type
	 */
	@Override
	public int getRoleId() {
		return model.getRoleId();
	}

	/**
	 * Returns the row status of this user role type.
	 *
	 * @return the row status of this user role type
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the user ID of this user role type.
	 *
	 * @return the user ID of this user role type
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this user role type.
	 *
	 * @return the user uuid of this user role type
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the created by of this user role type.
	 *
	 * @param CreatedBy the created by of this user role type
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this user role type.
	 *
	 * @param CreatedDate the created date of this user role type
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the ID of this user role type.
	 *
	 * @param Id the ID of this user role type
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this user role type.
	 *
	 * @param ModifiedBy the modified by of this user role type
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this user role type.
	 *
	 * @param ModifiedDate the modified date of this user role type
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this user role type.
	 *
	 * @param primaryKey the primary key of this user role type
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role ID of this user role type.
	 *
	 * @param RoleId the role ID of this user role type
	 */
	@Override
	public void setRoleId(int RoleId) {
		model.setRoleId(RoleId);
	}

	/**
	 * Sets the row status of this user role type.
	 *
	 * @param RowStatus the row status of this user role type
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the user ID of this user role type.
	 *
	 * @param UserId the user ID of this user role type
	 */
	@Override
	public void setUserId(long UserId) {
		model.setUserId(UserId);
	}

	/**
	 * Sets the user uuid of this user role type.
	 *
	 * @param UserUuid the user uuid of this user role type
	 */
	@Override
	public void setUserUuid(String UserUuid) {
		model.setUserUuid(UserUuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected UserRoleTypeWrapper wrap(UserRoleType userRoleType) {
		return new UserRoleTypeWrapper(userRoleType);
	}

}