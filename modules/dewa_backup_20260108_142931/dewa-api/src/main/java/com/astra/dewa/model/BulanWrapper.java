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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Bulan}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Bulan
 * @generated
 */
public class BulanWrapper
	extends BaseModelWrapper<Bulan> implements Bulan, ModelWrapper<Bulan> {

	public BulanWrapper(Bulan bulan) {
		super(bulan);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("Name", getName());
		attributes.put("SortIndex", getSortIndex());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String Id = (String)attributes.get("Id");

		if (Id != null) {
			setId(Id);
		}

		String Name = (String)attributes.get("Name");

		if (Name != null) {
			setName(Name);
		}

		Integer SortIndex = (Integer)attributes.get("SortIndex");

		if (SortIndex != null) {
			setSortIndex(SortIndex);
		}
	}

	@Override
	public Bulan cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the ID of this bulan.
	 *
	 * @return the ID of this bulan
	 */
	@Override
	public String getId() {
		return model.getId();
	}

	/**
	 * Returns the name of this bulan.
	 *
	 * @return the name of this bulan
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this bulan.
	 *
	 * @return the primary key of this bulan
	 */
	@Override
	public String getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the sort index of this bulan.
	 *
	 * @return the sort index of this bulan
	 */
	@Override
	public Integer getSortIndex() {
		return model.getSortIndex();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the ID of this bulan.
	 *
	 * @param Id the ID of this bulan
	 */
	@Override
	public void setId(String Id) {
		model.setId(Id);
	}

	/**
	 * Sets the name of this bulan.
	 *
	 * @param Name the name of this bulan
	 */
	@Override
	public void setName(String Name) {
		model.setName(Name);
	}

	/**
	 * Sets the primary key of this bulan.
	 *
	 * @param primaryKey the primary key of this bulan
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the sort index of this bulan.
	 *
	 * @param SortIndex the sort index of this bulan
	 */
	@Override
	public void setSortIndex(Integer SortIndex) {
		model.setSortIndex(SortIndex);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected BulanWrapper wrap(Bulan bulan) {
		return new BulanWrapper(bulan);
	}

}