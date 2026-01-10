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
 * This class is a wrapper for {@link MasterApprovalDetailJournal}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalDetailJournal
 * @generated
 */
public class MasterApprovalDetailJournalWrapper
	extends BaseModelWrapper<MasterApprovalDetailJournal>
	implements MasterApprovalDetailJournal,
			   ModelWrapper<MasterApprovalDetailJournal> {

	public MasterApprovalDetailJournalWrapper(
		MasterApprovalDetailJournal masterApprovalDetailJournal) {

		super(masterApprovalDetailJournal);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("MasterApprovalId", getMasterApprovalId());
		attributes.put("MasterApprovalJournalId", getMasterApprovalJournalId());
		attributes.put("MasterApprovalDetailId", getMasterApprovalDetailId());
		attributes.put("UserId", getUserId());
		attributes.put("ApprovalLevel", getApprovalLevel());
		attributes.put("IsDefault", isIsDefault());
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

		Integer MasterApprovalId = (Integer)attributes.get("MasterApprovalId");

		if (MasterApprovalId != null) {
			setMasterApprovalId(MasterApprovalId);
		}

		Integer MasterApprovalJournalId = (Integer)attributes.get(
			"MasterApprovalJournalId");

		if (MasterApprovalJournalId != null) {
			setMasterApprovalJournalId(MasterApprovalJournalId);
		}

		Integer MasterApprovalDetailId = (Integer)attributes.get(
			"MasterApprovalDetailId");

		if (MasterApprovalDetailId != null) {
			setMasterApprovalDetailId(MasterApprovalDetailId);
		}

		Long UserId = (Long)attributes.get("UserId");

		if (UserId != null) {
			setUserId(UserId);
		}

		Integer ApprovalLevel = (Integer)attributes.get("ApprovalLevel");

		if (ApprovalLevel != null) {
			setApprovalLevel(ApprovalLevel);
		}

		Boolean IsDefault = (Boolean)attributes.get("IsDefault");

		if (IsDefault != null) {
			setIsDefault(IsDefault);
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
	public MasterApprovalDetailJournal cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the approval level of this master approval detail journal.
	 *
	 * @return the approval level of this master approval detail journal
	 */
	@Override
	public int getApprovalLevel() {
		return model.getApprovalLevel();
	}

	/**
	 * Returns the created by of this master approval detail journal.
	 *
	 * @return the created by of this master approval detail journal
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this master approval detail journal.
	 *
	 * @return the created date of this master approval detail journal
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the ID of this master approval detail journal.
	 *
	 * @return the ID of this master approval detail journal
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the is default of this master approval detail journal.
	 *
	 * @return the is default of this master approval detail journal
	 */
	@Override
	public boolean getIsDefault() {
		return model.getIsDefault();
	}

	/**
	 * Returns the master approval detail ID of this master approval detail journal.
	 *
	 * @return the master approval detail ID of this master approval detail journal
	 */
	@Override
	public int getMasterApprovalDetailId() {
		return model.getMasterApprovalDetailId();
	}

	/**
	 * Returns the master approval ID of this master approval detail journal.
	 *
	 * @return the master approval ID of this master approval detail journal
	 */
	@Override
	public int getMasterApprovalId() {
		return model.getMasterApprovalId();
	}

	/**
	 * Returns the master approval journal ID of this master approval detail journal.
	 *
	 * @return the master approval journal ID of this master approval detail journal
	 */
	@Override
	public int getMasterApprovalJournalId() {
		return model.getMasterApprovalJournalId();
	}

	/**
	 * Returns the modified by of this master approval detail journal.
	 *
	 * @return the modified by of this master approval detail journal
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this master approval detail journal.
	 *
	 * @return the modified date of this master approval detail journal
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this master approval detail journal.
	 *
	 * @return the primary key of this master approval detail journal
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this master approval detail journal.
	 *
	 * @return the row status of this master approval detail journal
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the user ID of this master approval detail journal.
	 *
	 * @return the user ID of this master approval detail journal
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this master approval detail journal.
	 *
	 * @return the user uuid of this master approval detail journal
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this master approval detail journal is is default.
	 *
	 * @return <code>true</code> if this master approval detail journal is is default; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsDefault() {
		return model.isIsDefault();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the approval level of this master approval detail journal.
	 *
	 * @param ApprovalLevel the approval level of this master approval detail journal
	 */
	@Override
	public void setApprovalLevel(int ApprovalLevel) {
		model.setApprovalLevel(ApprovalLevel);
	}

	/**
	 * Sets the created by of this master approval detail journal.
	 *
	 * @param CreatedBy the created by of this master approval detail journal
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this master approval detail journal.
	 *
	 * @param CreatedDate the created date of this master approval detail journal
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the ID of this master approval detail journal.
	 *
	 * @param Id the ID of this master approval detail journal
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets whether this master approval detail journal is is default.
	 *
	 * @param IsDefault the is default of this master approval detail journal
	 */
	@Override
	public void setIsDefault(boolean IsDefault) {
		model.setIsDefault(IsDefault);
	}

	/**
	 * Sets the master approval detail ID of this master approval detail journal.
	 *
	 * @param MasterApprovalDetailId the master approval detail ID of this master approval detail journal
	 */
	@Override
	public void setMasterApprovalDetailId(int MasterApprovalDetailId) {
		model.setMasterApprovalDetailId(MasterApprovalDetailId);
	}

	/**
	 * Sets the master approval ID of this master approval detail journal.
	 *
	 * @param MasterApprovalId the master approval ID of this master approval detail journal
	 */
	@Override
	public void setMasterApprovalId(int MasterApprovalId) {
		model.setMasterApprovalId(MasterApprovalId);
	}

	/**
	 * Sets the master approval journal ID of this master approval detail journal.
	 *
	 * @param MasterApprovalJournalId the master approval journal ID of this master approval detail journal
	 */
	@Override
	public void setMasterApprovalJournalId(int MasterApprovalJournalId) {
		model.setMasterApprovalJournalId(MasterApprovalJournalId);
	}

	/**
	 * Sets the modified by of this master approval detail journal.
	 *
	 * @param ModifiedBy the modified by of this master approval detail journal
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this master approval detail journal.
	 *
	 * @param ModifiedDate the modified date of this master approval detail journal
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this master approval detail journal.
	 *
	 * @param primaryKey the primary key of this master approval detail journal
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the row status of this master approval detail journal.
	 *
	 * @param RowStatus the row status of this master approval detail journal
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the user ID of this master approval detail journal.
	 *
	 * @param UserId the user ID of this master approval detail journal
	 */
	@Override
	public void setUserId(long UserId) {
		model.setUserId(UserId);
	}

	/**
	 * Sets the user uuid of this master approval detail journal.
	 *
	 * @param UserUuid the user uuid of this master approval detail journal
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
	protected MasterApprovalDetailJournalWrapper wrap(
		MasterApprovalDetailJournal masterApprovalDetailJournal) {

		return new MasterApprovalDetailJournalWrapper(
			masterApprovalDetailJournal);
	}

}