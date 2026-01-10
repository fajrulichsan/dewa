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
 * This class is a wrapper for {@link ApplicationCategory}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationCategory
 * @generated
 */
public class ApplicationCategoryWrapper
	extends BaseModelWrapper<ApplicationCategory>
	implements ApplicationCategory, ModelWrapper<ApplicationCategory> {

	public ApplicationCategoryWrapper(ApplicationCategory applicationCategory) {
		super(applicationCategory);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("Name", getName());
		attributes.put("Description", getDescription());
		attributes.put("CategoryBonus", getCategoryBonus());
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

		Integer CategoryBonus = (Integer)attributes.get("CategoryBonus");

		if (CategoryBonus != null) {
			setCategoryBonus(CategoryBonus);
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
	public ApplicationCategory cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the category bonus of this application category.
	 *
	 * @return the category bonus of this application category
	 */
	@Override
	public Integer getCategoryBonus() {
		return model.getCategoryBonus();
	}

	/**
	 * Returns the created by of this application category.
	 *
	 * @return the created by of this application category
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this application category.
	 *
	 * @return the created date of this application category
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the description of this application category.
	 *
	 * @return the description of this application category
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the ID of this application category.
	 *
	 * @return the ID of this application category
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this application category.
	 *
	 * @return the modified by of this application category
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this application category.
	 *
	 * @return the modified date of this application category
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this application category.
	 *
	 * @return the name of this application category
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this application category.
	 *
	 * @return the primary key of this application category
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this application category.
	 *
	 * @return the row status of this application category
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
	 * Sets the category bonus of this application category.
	 *
	 * @param CategoryBonus the category bonus of this application category
	 */
	@Override
	public void setCategoryBonus(Integer CategoryBonus) {
		model.setCategoryBonus(CategoryBonus);
	}

	/**
	 * Sets the created by of this application category.
	 *
	 * @param CreatedBy the created by of this application category
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this application category.
	 *
	 * @param CreatedDate the created date of this application category
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the description of this application category.
	 *
	 * @param Description the description of this application category
	 */
	@Override
	public void setDescription(String Description) {
		model.setDescription(Description);
	}

	/**
	 * Sets the ID of this application category.
	 *
	 * @param Id the ID of this application category
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this application category.
	 *
	 * @param ModifiedBy the modified by of this application category
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this application category.
	 *
	 * @param ModifiedDate the modified date of this application category
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the name of this application category.
	 *
	 * @param Name the name of this application category
	 */
	@Override
	public void setName(String Name) {
		model.setName(Name);
	}

	/**
	 * Sets the primary key of this application category.
	 *
	 * @param primaryKey the primary key of this application category
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the row status of this application category.
	 *
	 * @param RowStatus the row status of this application category
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
	protected ApplicationCategoryWrapper wrap(
		ApplicationCategory applicationCategory) {

		return new ApplicationCategoryWrapper(applicationCategory);
	}

}