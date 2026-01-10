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
 * This class is a wrapper for {@link Common}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Common
 * @generated
 */
public class CommonWrapper
	extends BaseModelWrapper<Common> implements Common, ModelWrapper<Common> {

	public CommonWrapper(Common common) {
		super(common);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("CommonKey", getCommonKey());
		attributes.put("CommonCode", getCommonCode());
		attributes.put("CommonDesc1", getCommonDesc1());
		attributes.put("CommonDesc2", getCommonDesc2());
		attributes.put("CommonDesc3", getCommonDesc3());
		attributes.put("CommonDesc4", getCommonDesc4());
		attributes.put("CommonDesc5", getCommonDesc5());
		attributes.put("Sequence", getSequence());
		attributes.put("IsActive", isIsActive());
		attributes.put("CreatedBy", getCreatedBy());
		attributes.put("CreatedDate", getCreatedDate());
		attributes.put("ModifiedBy", getModifiedBy());
		attributes.put("ModifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String CommonKey = (String)attributes.get("CommonKey");

		if (CommonKey != null) {
			setCommonKey(CommonKey);
		}

		String CommonCode = (String)attributes.get("CommonCode");

		if (CommonCode != null) {
			setCommonCode(CommonCode);
		}

		String CommonDesc1 = (String)attributes.get("CommonDesc1");

		if (CommonDesc1 != null) {
			setCommonDesc1(CommonDesc1);
		}

		String CommonDesc2 = (String)attributes.get("CommonDesc2");

		if (CommonDesc2 != null) {
			setCommonDesc2(CommonDesc2);
		}

		String CommonDesc3 = (String)attributes.get("CommonDesc3");

		if (CommonDesc3 != null) {
			setCommonDesc3(CommonDesc3);
		}

		String CommonDesc4 = (String)attributes.get("CommonDesc4");

		if (CommonDesc4 != null) {
			setCommonDesc4(CommonDesc4);
		}

		String CommonDesc5 = (String)attributes.get("CommonDesc5");

		if (CommonDesc5 != null) {
			setCommonDesc5(CommonDesc5);
		}

		Integer Sequence = (Integer)attributes.get("Sequence");

		if (Sequence != null) {
			setSequence(Sequence);
		}

		Boolean IsActive = (Boolean)attributes.get("IsActive");

		if (IsActive != null) {
			setIsActive(IsActive);
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
	public Common cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the common code of this common.
	 *
	 * @return the common code of this common
	 */
	@Override
	public String getCommonCode() {
		return model.getCommonCode();
	}

	/**
	 * Returns the common desc1 of this common.
	 *
	 * @return the common desc1 of this common
	 */
	@Override
	public String getCommonDesc1() {
		return model.getCommonDesc1();
	}

	/**
	 * Returns the common desc2 of this common.
	 *
	 * @return the common desc2 of this common
	 */
	@Override
	public String getCommonDesc2() {
		return model.getCommonDesc2();
	}

	/**
	 * Returns the common desc3 of this common.
	 *
	 * @return the common desc3 of this common
	 */
	@Override
	public String getCommonDesc3() {
		return model.getCommonDesc3();
	}

	/**
	 * Returns the common desc4 of this common.
	 *
	 * @return the common desc4 of this common
	 */
	@Override
	public String getCommonDesc4() {
		return model.getCommonDesc4();
	}

	/**
	 * Returns the common desc5 of this common.
	 *
	 * @return the common desc5 of this common
	 */
	@Override
	public String getCommonDesc5() {
		return model.getCommonDesc5();
	}

	/**
	 * Returns the common key of this common.
	 *
	 * @return the common key of this common
	 */
	@Override
	public String getCommonKey() {
		return model.getCommonKey();
	}

	/**
	 * Returns the created by of this common.
	 *
	 * @return the created by of this common
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this common.
	 *
	 * @return the created date of this common
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the is active of this common.
	 *
	 * @return the is active of this common
	 */
	@Override
	public boolean getIsActive() {
		return model.getIsActive();
	}

	/**
	 * Returns the modified by of this common.
	 *
	 * @return the modified by of this common
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this common.
	 *
	 * @return the modified date of this common
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this common.
	 *
	 * @return the primary key of this common
	 */
	@Override
	public String getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the sequence of this common.
	 *
	 * @return the sequence of this common
	 */
	@Override
	public int getSequence() {
		return model.getSequence();
	}

	/**
	 * Returns <code>true</code> if this common is is active.
	 *
	 * @return <code>true</code> if this common is is active; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsActive() {
		return model.isIsActive();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the common code of this common.
	 *
	 * @param CommonCode the common code of this common
	 */
	@Override
	public void setCommonCode(String CommonCode) {
		model.setCommonCode(CommonCode);
	}

	/**
	 * Sets the common desc1 of this common.
	 *
	 * @param CommonDesc1 the common desc1 of this common
	 */
	@Override
	public void setCommonDesc1(String CommonDesc1) {
		model.setCommonDesc1(CommonDesc1);
	}

	/**
	 * Sets the common desc2 of this common.
	 *
	 * @param CommonDesc2 the common desc2 of this common
	 */
	@Override
	public void setCommonDesc2(String CommonDesc2) {
		model.setCommonDesc2(CommonDesc2);
	}

	/**
	 * Sets the common desc3 of this common.
	 *
	 * @param CommonDesc3 the common desc3 of this common
	 */
	@Override
	public void setCommonDesc3(String CommonDesc3) {
		model.setCommonDesc3(CommonDesc3);
	}

	/**
	 * Sets the common desc4 of this common.
	 *
	 * @param CommonDesc4 the common desc4 of this common
	 */
	@Override
	public void setCommonDesc4(String CommonDesc4) {
		model.setCommonDesc4(CommonDesc4);
	}

	/**
	 * Sets the common desc5 of this common.
	 *
	 * @param CommonDesc5 the common desc5 of this common
	 */
	@Override
	public void setCommonDesc5(String CommonDesc5) {
		model.setCommonDesc5(CommonDesc5);
	}

	/**
	 * Sets the common key of this common.
	 *
	 * @param CommonKey the common key of this common
	 */
	@Override
	public void setCommonKey(String CommonKey) {
		model.setCommonKey(CommonKey);
	}

	/**
	 * Sets the created by of this common.
	 *
	 * @param CreatedBy the created by of this common
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this common.
	 *
	 * @param CreatedDate the created date of this common
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets whether this common is is active.
	 *
	 * @param IsActive the is active of this common
	 */
	@Override
	public void setIsActive(boolean IsActive) {
		model.setIsActive(IsActive);
	}

	/**
	 * Sets the modified by of this common.
	 *
	 * @param ModifiedBy the modified by of this common
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this common.
	 *
	 * @param ModifiedDate the modified date of this common
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this common.
	 *
	 * @param primaryKey the primary key of this common
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the sequence of this common.
	 *
	 * @param Sequence the sequence of this common
	 */
	@Override
	public void setSequence(int Sequence) {
		model.setSequence(Sequence);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected CommonWrapper wrap(Common common) {
		return new CommonWrapper(common);
	}

}