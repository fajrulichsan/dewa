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
 * This class is a wrapper for {@link PeriodeReview}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PeriodeReview
 * @generated
 */
public class PeriodeReviewWrapper
	extends BaseModelWrapper<PeriodeReview>
	implements ModelWrapper<PeriodeReview>, PeriodeReview {

	public PeriodeReviewWrapper(PeriodeReview periodeReview) {
		super(periodeReview);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("Name", getName());

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
	}

	@Override
	public PeriodeReview cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the ID of this periode review.
	 *
	 * @return the ID of this periode review
	 */
	@Override
	public String getId() {
		return model.getId();
	}

	/**
	 * Returns the name of this periode review.
	 *
	 * @return the name of this periode review
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this periode review.
	 *
	 * @return the primary key of this periode review
	 */
	@Override
	public String getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the ID of this periode review.
	 *
	 * @param Id the ID of this periode review
	 */
	@Override
	public void setId(String Id) {
		model.setId(Id);
	}

	/**
	 * Sets the name of this periode review.
	 *
	 * @param Name the name of this periode review
	 */
	@Override
	public void setName(String Name) {
		model.setName(Name);
	}

	/**
	 * Sets the primary key of this periode review.
	 *
	 * @param primaryKey the primary key of this periode review
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected PeriodeReviewWrapper wrap(PeriodeReview periodeReview) {
		return new PeriodeReviewWrapper(periodeReview);
	}

}