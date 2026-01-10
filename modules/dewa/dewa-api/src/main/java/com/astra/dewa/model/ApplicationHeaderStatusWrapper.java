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
 * This class is a wrapper for {@link ApplicationHeaderStatus}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationHeaderStatus
 * @generated
 */
public class ApplicationHeaderStatusWrapper
	extends BaseModelWrapper<ApplicationHeaderStatus>
	implements ApplicationHeaderStatus, ModelWrapper<ApplicationHeaderStatus> {

	public ApplicationHeaderStatusWrapper(
		ApplicationHeaderStatus applicationHeaderStatus) {

		super(applicationHeaderStatus);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("Name", getName());
		attributes.put("Description", getDescription());
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

		String Name = (String)attributes.get("Name");

		if (Name != null) {
			setName(Name);
		}

		String Description = (String)attributes.get("Description");

		if (Description != null) {
			setDescription(Description);
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
	public ApplicationHeaderStatus cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this application header status.
	 *
	 * @return the created by of this application header status
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this application header status.
	 *
	 * @return the created date of this application header status
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the description of this application header status.
	 *
	 * @return the description of this application header status
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the ID of this application header status.
	 *
	 * @return the ID of this application header status
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this application header status.
	 *
	 * @return the modified by of this application header status
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this application header status.
	 *
	 * @return the modified date of this application header status
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this application header status.
	 *
	 * @return the name of this application header status
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this application header status.
	 *
	 * @return the primary key of this application header status
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this application header status.
	 *
	 * @return the row status of this application header status
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
	 * Sets the created by of this application header status.
	 *
	 * @param CreatedBy the created by of this application header status
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this application header status.
	 *
	 * @param CreatedDate the created date of this application header status
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the description of this application header status.
	 *
	 * @param Description the description of this application header status
	 */
	@Override
	public void setDescription(String Description) {
		model.setDescription(Description);
	}

	/**
	 * Sets the ID of this application header status.
	 *
	 * @param Id the ID of this application header status
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this application header status.
	 *
	 * @param ModifiedBy the modified by of this application header status
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this application header status.
	 *
	 * @param ModifiedDate the modified date of this application header status
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the name of this application header status.
	 *
	 * @param Name the name of this application header status
	 */
	@Override
	public void setName(String Name) {
		model.setName(Name);
	}

	/**
	 * Sets the primary key of this application header status.
	 *
	 * @param primaryKey the primary key of this application header status
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the row status of this application header status.
	 *
	 * @param RowStatus the row status of this application header status
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
	protected ApplicationHeaderStatusWrapper wrap(
		ApplicationHeaderStatus applicationHeaderStatus) {

		return new ApplicationHeaderStatusWrapper(applicationHeaderStatus);
	}

}