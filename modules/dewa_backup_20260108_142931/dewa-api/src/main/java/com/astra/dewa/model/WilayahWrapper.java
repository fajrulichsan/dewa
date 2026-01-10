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
 * This class is a wrapper for {@link Wilayah}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Wilayah
 * @generated
 */
public class WilayahWrapper
	extends BaseModelWrapper<Wilayah>
	implements ModelWrapper<Wilayah>, Wilayah {

	public WilayahWrapper(Wilayah wilayah) {
		super(wilayah);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("Name", getName());
		attributes.put("SK", getSK());
		attributes.put("Description", getDescription());
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

		String Name = (String)attributes.get("Name");

		if (Name != null) {
			setName(Name);
		}

		String SK = (String)attributes.get("SK");

		if (SK != null) {
			setSK(SK);
		}

		String Description = (String)attributes.get("Description");

		if (Description != null) {
			setDescription(Description);
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
	public Wilayah cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this wilayah.
	 *
	 * @return the created by of this wilayah
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this wilayah.
	 *
	 * @return the created date of this wilayah
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the description of this wilayah.
	 *
	 * @return the description of this wilayah
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the ID of this wilayah.
	 *
	 * @return the ID of this wilayah
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this wilayah.
	 *
	 * @return the modified by of this wilayah
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this wilayah.
	 *
	 * @return the modified date of this wilayah
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this wilayah.
	 *
	 * @return the name of this wilayah
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this wilayah.
	 *
	 * @return the primary key of this wilayah
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this wilayah.
	 *
	 * @return the row status of this wilayah
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the sk of this wilayah.
	 *
	 * @return the sk of this wilayah
	 */
	@Override
	public String getSK() {
		return model.getSK();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the created by of this wilayah.
	 *
	 * @param CreatedBy the created by of this wilayah
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this wilayah.
	 *
	 * @param CreatedDate the created date of this wilayah
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the description of this wilayah.
	 *
	 * @param Description the description of this wilayah
	 */
	@Override
	public void setDescription(String Description) {
		model.setDescription(Description);
	}

	/**
	 * Sets the ID of this wilayah.
	 *
	 * @param Id the ID of this wilayah
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this wilayah.
	 *
	 * @param ModifiedBy the modified by of this wilayah
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this wilayah.
	 *
	 * @param ModifiedDate the modified date of this wilayah
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the name of this wilayah.
	 *
	 * @param Name the name of this wilayah
	 */
	@Override
	public void setName(String Name) {
		model.setName(Name);
	}

	/**
	 * Sets the primary key of this wilayah.
	 *
	 * @param primaryKey the primary key of this wilayah
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the row status of this wilayah.
	 *
	 * @param RowStatus the row status of this wilayah
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the sk of this wilayah.
	 *
	 * @param SK the sk of this wilayah
	 */
	@Override
	public void setSK(String SK) {
		model.setSK(SK);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected WilayahWrapper wrap(Wilayah wilayah) {
		return new WilayahWrapper(wilayah);
	}

}