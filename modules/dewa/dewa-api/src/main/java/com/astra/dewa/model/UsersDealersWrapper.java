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
 * This class is a wrapper for {@link UsersDealers}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UsersDealers
 * @generated
 */
public class UsersDealersWrapper
	extends BaseModelWrapper<UsersDealers>
	implements ModelWrapper<UsersDealers>, UsersDealers {

	public UsersDealersWrapper(UsersDealers usersDealers) {
		super(usersDealers);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("UserId", getUserId());
		attributes.put("DealerId", getDealerId());
		attributes.put("RoleId", getRoleId());
		attributes.put("PhotoFileId", getPhotoFileId());
		attributes.put("ADB2CId", getADB2CId());
		attributes.put("userPrincipalName", getUserPrincipalName());
		attributes.put("FullName", getFullName());
		attributes.put("UserEmail", getUserEmail());
		attributes.put("ApprovedDate", getApprovedDate());
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

		Integer DealerId = (Integer)attributes.get("DealerId");

		if (DealerId != null) {
			setDealerId(DealerId);
		}

		Integer RoleId = (Integer)attributes.get("RoleId");

		if (RoleId != null) {
			setRoleId(RoleId);
		}

		Long PhotoFileId = (Long)attributes.get("PhotoFileId");

		if (PhotoFileId != null) {
			setPhotoFileId(PhotoFileId);
		}

		String ADB2CId = (String)attributes.get("ADB2CId");

		if (ADB2CId != null) {
			setADB2CId(ADB2CId);
		}

		String userPrincipalName = (String)attributes.get("userPrincipalName");

		if (userPrincipalName != null) {
			setUserPrincipalName(userPrincipalName);
		}

		String FullName = (String)attributes.get("FullName");

		if (FullName != null) {
			setFullName(FullName);
		}

		String UserEmail = (String)attributes.get("UserEmail");

		if (UserEmail != null) {
			setUserEmail(UserEmail);
		}

		Date ApprovedDate = (Date)attributes.get("ApprovedDate");

		if (ApprovedDate != null) {
			setApprovedDate(ApprovedDate);
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
	public UsersDealers cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the adb2c ID of this users dealers.
	 *
	 * @return the adb2c ID of this users dealers
	 */
	@Override
	public String getADB2CId() {
		return model.getADB2CId();
	}

	/**
	 * Returns the approved date of this users dealers.
	 *
	 * @return the approved date of this users dealers
	 */
	@Override
	public Date getApprovedDate() {
		return model.getApprovedDate();
	}

	/**
	 * Returns the created by of this users dealers.
	 *
	 * @return the created by of this users dealers
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this users dealers.
	 *
	 * @return the created date of this users dealers
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the dealer ID of this users dealers.
	 *
	 * @return the dealer ID of this users dealers
	 */
	@Override
	public int getDealerId() {
		return model.getDealerId();
	}

	/**
	 * Returns the full name of this users dealers.
	 *
	 * @return the full name of this users dealers
	 */
	@Override
	public String getFullName() {
		return model.getFullName();
	}

	/**
	 * Returns the ID of this users dealers.
	 *
	 * @return the ID of this users dealers
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this users dealers.
	 *
	 * @return the modified by of this users dealers
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this users dealers.
	 *
	 * @return the modified date of this users dealers
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the photo file ID of this users dealers.
	 *
	 * @return the photo file ID of this users dealers
	 */
	@Override
	public long getPhotoFileId() {
		return model.getPhotoFileId();
	}

	/**
	 * Returns the primary key of this users dealers.
	 *
	 * @return the primary key of this users dealers
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the role ID of this users dealers.
	 *
	 * @return the role ID of this users dealers
	 */
	@Override
	public int getRoleId() {
		return model.getRoleId();
	}

	/**
	 * Returns the row status of this users dealers.
	 *
	 * @return the row status of this users dealers
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the user email of this users dealers.
	 *
	 * @return the user email of this users dealers
	 */
	@Override
	public String getUserEmail() {
		return model.getUserEmail();
	}

	/**
	 * Returns the user ID of this users dealers.
	 *
	 * @return the user ID of this users dealers
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user principal name of this users dealers.
	 *
	 * @return the user principal name of this users dealers
	 */
	@Override
	public String getUserPrincipalName() {
		return model.getUserPrincipalName();
	}

	/**
	 * Returns the user uuid of this users dealers.
	 *
	 * @return the user uuid of this users dealers
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
	 * Sets the adb2c ID of this users dealers.
	 *
	 * @param ADB2CId the adb2c ID of this users dealers
	 */
	@Override
	public void setADB2CId(String ADB2CId) {
		model.setADB2CId(ADB2CId);
	}

	/**
	 * Sets the approved date of this users dealers.
	 *
	 * @param ApprovedDate the approved date of this users dealers
	 */
	@Override
	public void setApprovedDate(Date ApprovedDate) {
		model.setApprovedDate(ApprovedDate);
	}

	/**
	 * Sets the created by of this users dealers.
	 *
	 * @param CreatedBy the created by of this users dealers
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this users dealers.
	 *
	 * @param CreatedDate the created date of this users dealers
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the dealer ID of this users dealers.
	 *
	 * @param DealerId the dealer ID of this users dealers
	 */
	@Override
	public void setDealerId(int DealerId) {
		model.setDealerId(DealerId);
	}

	/**
	 * Sets the full name of this users dealers.
	 *
	 * @param FullName the full name of this users dealers
	 */
	@Override
	public void setFullName(String FullName) {
		model.setFullName(FullName);
	}

	/**
	 * Sets the ID of this users dealers.
	 *
	 * @param Id the ID of this users dealers
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this users dealers.
	 *
	 * @param ModifiedBy the modified by of this users dealers
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this users dealers.
	 *
	 * @param ModifiedDate the modified date of this users dealers
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the photo file ID of this users dealers.
	 *
	 * @param PhotoFileId the photo file ID of this users dealers
	 */
	@Override
	public void setPhotoFileId(long PhotoFileId) {
		model.setPhotoFileId(PhotoFileId);
	}

	/**
	 * Sets the primary key of this users dealers.
	 *
	 * @param primaryKey the primary key of this users dealers
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role ID of this users dealers.
	 *
	 * @param RoleId the role ID of this users dealers
	 */
	@Override
	public void setRoleId(int RoleId) {
		model.setRoleId(RoleId);
	}

	/**
	 * Sets the row status of this users dealers.
	 *
	 * @param RowStatus the row status of this users dealers
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the user email of this users dealers.
	 *
	 * @param UserEmail the user email of this users dealers
	 */
	@Override
	public void setUserEmail(String UserEmail) {
		model.setUserEmail(UserEmail);
	}

	/**
	 * Sets the user ID of this users dealers.
	 *
	 * @param UserId the user ID of this users dealers
	 */
	@Override
	public void setUserId(long UserId) {
		model.setUserId(UserId);
	}

	/**
	 * Sets the user principal name of this users dealers.
	 *
	 * @param userPrincipalName the user principal name of this users dealers
	 */
	@Override
	public void setUserPrincipalName(String userPrincipalName) {
		model.setUserPrincipalName(userPrincipalName);
	}

	/**
	 * Sets the user uuid of this users dealers.
	 *
	 * @param UserUuid the user uuid of this users dealers
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
	protected UsersDealersWrapper wrap(UsersDealers usersDealers) {
		return new UsersDealersWrapper(usersDealers);
	}

}