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
 * This class is a wrapper for {@link MasterApprovalJournal}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalJournal
 * @generated
 */
public class MasterApprovalJournalWrapper
	extends BaseModelWrapper<MasterApprovalJournal>
	implements MasterApprovalJournal, ModelWrapper<MasterApprovalJournal> {

	public MasterApprovalJournalWrapper(
		MasterApprovalJournal masterApprovalJournal) {

		super(masterApprovalJournal);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("MasterApprovalId", getMasterApprovalId());
		attributes.put("RoleId", getRoleId());
		attributes.put("MenuId", getMenuId());
		attributes.put("ApprovalGroup", getApprovalGroup());
		attributes.put("ApprovalWorkflow", isApprovalWorkflow());
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

		Integer RoleId = (Integer)attributes.get("RoleId");

		if (RoleId != null) {
			setRoleId(RoleId);
		}

		Integer MenuId = (Integer)attributes.get("MenuId");

		if (MenuId != null) {
			setMenuId(MenuId);
		}

		String ApprovalGroup = (String)attributes.get("ApprovalGroup");

		if (ApprovalGroup != null) {
			setApprovalGroup(ApprovalGroup);
		}

		Boolean ApprovalWorkflow = (Boolean)attributes.get("ApprovalWorkflow");

		if (ApprovalWorkflow != null) {
			setApprovalWorkflow(ApprovalWorkflow);
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
	public MasterApprovalJournal cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the approval group of this master approval journal.
	 *
	 * @return the approval group of this master approval journal
	 */
	@Override
	public String getApprovalGroup() {
		return model.getApprovalGroup();
	}

	/**
	 * Returns the approval workflow of this master approval journal.
	 *
	 * @return the approval workflow of this master approval journal
	 */
	@Override
	public boolean getApprovalWorkflow() {
		return model.getApprovalWorkflow();
	}

	/**
	 * Returns the created by of this master approval journal.
	 *
	 * @return the created by of this master approval journal
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this master approval journal.
	 *
	 * @return the created date of this master approval journal
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the ID of this master approval journal.
	 *
	 * @return the ID of this master approval journal
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the master approval ID of this master approval journal.
	 *
	 * @return the master approval ID of this master approval journal
	 */
	@Override
	public int getMasterApprovalId() {
		return model.getMasterApprovalId();
	}

	/**
	 * Returns the menu ID of this master approval journal.
	 *
	 * @return the menu ID of this master approval journal
	 */
	@Override
	public int getMenuId() {
		return model.getMenuId();
	}

	/**
	 * Returns the modified by of this master approval journal.
	 *
	 * @return the modified by of this master approval journal
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this master approval journal.
	 *
	 * @return the modified date of this master approval journal
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this master approval journal.
	 *
	 * @return the primary key of this master approval journal
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the role ID of this master approval journal.
	 *
	 * @return the role ID of this master approval journal
	 */
	@Override
	public int getRoleId() {
		return model.getRoleId();
	}

	/**
	 * Returns the row status of this master approval journal.
	 *
	 * @return the row status of this master approval journal
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns <code>true</code> if this master approval journal is approval workflow.
	 *
	 * @return <code>true</code> if this master approval journal is approval workflow; <code>false</code> otherwise
	 */
	@Override
	public boolean isApprovalWorkflow() {
		return model.isApprovalWorkflow();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the approval group of this master approval journal.
	 *
	 * @param ApprovalGroup the approval group of this master approval journal
	 */
	@Override
	public void setApprovalGroup(String ApprovalGroup) {
		model.setApprovalGroup(ApprovalGroup);
	}

	/**
	 * Sets whether this master approval journal is approval workflow.
	 *
	 * @param ApprovalWorkflow the approval workflow of this master approval journal
	 */
	@Override
	public void setApprovalWorkflow(boolean ApprovalWorkflow) {
		model.setApprovalWorkflow(ApprovalWorkflow);
	}

	/**
	 * Sets the created by of this master approval journal.
	 *
	 * @param CreatedBy the created by of this master approval journal
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this master approval journal.
	 *
	 * @param CreatedDate the created date of this master approval journal
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the ID of this master approval journal.
	 *
	 * @param Id the ID of this master approval journal
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the master approval ID of this master approval journal.
	 *
	 * @param MasterApprovalId the master approval ID of this master approval journal
	 */
	@Override
	public void setMasterApprovalId(int MasterApprovalId) {
		model.setMasterApprovalId(MasterApprovalId);
	}

	/**
	 * Sets the menu ID of this master approval journal.
	 *
	 * @param MenuId the menu ID of this master approval journal
	 */
	@Override
	public void setMenuId(int MenuId) {
		model.setMenuId(MenuId);
	}

	/**
	 * Sets the modified by of this master approval journal.
	 *
	 * @param ModifiedBy the modified by of this master approval journal
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this master approval journal.
	 *
	 * @param ModifiedDate the modified date of this master approval journal
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this master approval journal.
	 *
	 * @param primaryKey the primary key of this master approval journal
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role ID of this master approval journal.
	 *
	 * @param RoleId the role ID of this master approval journal
	 */
	@Override
	public void setRoleId(int RoleId) {
		model.setRoleId(RoleId);
	}

	/**
	 * Sets the row status of this master approval journal.
	 *
	 * @param RowStatus the row status of this master approval journal
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
	protected MasterApprovalJournalWrapper wrap(
		MasterApprovalJournal masterApprovalJournal) {

		return new MasterApprovalJournalWrapper(masterApprovalJournal);
	}

}