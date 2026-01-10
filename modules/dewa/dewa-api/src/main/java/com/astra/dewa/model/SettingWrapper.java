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
 * This class is a wrapper for {@link Setting}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Setting
 * @generated
 */
public class SettingWrapper
	extends BaseModelWrapper<Setting>
	implements ModelWrapper<Setting>, Setting {

	public SettingWrapper(Setting setting) {
		super(setting);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("KeySetting", getKeySetting());
		attributes.put("Code", getCode());
		attributes.put("Value", getValue());
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

		String KeySetting = (String)attributes.get("KeySetting");

		if (KeySetting != null) {
			setKeySetting(KeySetting);
		}

		String Code = (String)attributes.get("Code");

		if (Code != null) {
			setCode(Code);
		}

		String Value = (String)attributes.get("Value");

		if (Value != null) {
			setValue(Value);
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
	public Setting cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the code of this setting.
	 *
	 * @return the code of this setting
	 */
	@Override
	public String getCode() {
		return model.getCode();
	}

	/**
	 * Returns the created by of this setting.
	 *
	 * @return the created by of this setting
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this setting.
	 *
	 * @return the created date of this setting
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the ID of this setting.
	 *
	 * @return the ID of this setting
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the key setting of this setting.
	 *
	 * @return the key setting of this setting
	 */
	@Override
	public String getKeySetting() {
		return model.getKeySetting();
	}

	/**
	 * Returns the modified by of this setting.
	 *
	 * @return the modified by of this setting
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this setting.
	 *
	 * @return the modified date of this setting
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this setting.
	 *
	 * @return the primary key of this setting
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this setting.
	 *
	 * @return the row status of this setting
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the value of this setting.
	 *
	 * @return the value of this setting
	 */
	@Override
	public String getValue() {
		return model.getValue();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the code of this setting.
	 *
	 * @param Code the code of this setting
	 */
	@Override
	public void setCode(String Code) {
		model.setCode(Code);
	}

	/**
	 * Sets the created by of this setting.
	 *
	 * @param CreatedBy the created by of this setting
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this setting.
	 *
	 * @param CreatedDate the created date of this setting
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the ID of this setting.
	 *
	 * @param Id the ID of this setting
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the key setting of this setting.
	 *
	 * @param KeySetting the key setting of this setting
	 */
	@Override
	public void setKeySetting(String KeySetting) {
		model.setKeySetting(KeySetting);
	}

	/**
	 * Sets the modified by of this setting.
	 *
	 * @param ModifiedBy the modified by of this setting
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this setting.
	 *
	 * @param ModifiedDate the modified date of this setting
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this setting.
	 *
	 * @param primaryKey the primary key of this setting
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the row status of this setting.
	 *
	 * @param RowStatus the row status of this setting
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the value of this setting.
	 *
	 * @param Value the value of this setting
	 */
	@Override
	public void setValue(String Value) {
		model.setValue(Value);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected SettingWrapper wrap(Setting setting) {
		return new SettingWrapper(setting);
	}

}