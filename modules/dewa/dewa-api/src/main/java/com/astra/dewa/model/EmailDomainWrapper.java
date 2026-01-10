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
 * This class is a wrapper for {@link EmailDomain}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailDomain
 * @generated
 */
public class EmailDomainWrapper
	extends BaseModelWrapper<EmailDomain>
	implements EmailDomain, ModelWrapper<EmailDomain> {

	public EmailDomainWrapper(EmailDomain emailDomain) {
		super(emailDomain);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("DomainName", getDomainName());
		attributes.put("Category", getCategory());
		attributes.put("RowStatus", isRowStatus());
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

		String DomainName = (String)attributes.get("DomainName");

		if (DomainName != null) {
			setDomainName(DomainName);
		}

		String Category = (String)attributes.get("Category");

		if (Category != null) {
			setCategory(Category);
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
	public EmailDomain cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the category of this email domain.
	 *
	 * @return the category of this email domain
	 */
	@Override
	public String getCategory() {
		return model.getCategory();
	}

	/**
	 * Returns the created by of this email domain.
	 *
	 * @return the created by of this email domain
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this email domain.
	 *
	 * @return the created date of this email domain
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the domain name of this email domain.
	 *
	 * @return the domain name of this email domain
	 */
	@Override
	public String getDomainName() {
		return model.getDomainName();
	}

	/**
	 * Returns the ID of this email domain.
	 *
	 * @return the ID of this email domain
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this email domain.
	 *
	 * @return the modified by of this email domain
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this email domain.
	 *
	 * @return the modified date of this email domain
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this email domain.
	 *
	 * @return the primary key of this email domain
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this email domain.
	 *
	 * @return the row status of this email domain
	 */
	@Override
	public boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns <code>true</code> if this email domain is row status.
	 *
	 * @return <code>true</code> if this email domain is row status; <code>false</code> otherwise
	 */
	@Override
	public boolean isRowStatus() {
		return model.isRowStatus();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the category of this email domain.
	 *
	 * @param Category the category of this email domain
	 */
	@Override
	public void setCategory(String Category) {
		model.setCategory(Category);
	}

	/**
	 * Sets the created by of this email domain.
	 *
	 * @param CreatedBy the created by of this email domain
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this email domain.
	 *
	 * @param CreatedDate the created date of this email domain
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the domain name of this email domain.
	 *
	 * @param DomainName the domain name of this email domain
	 */
	@Override
	public void setDomainName(String DomainName) {
		model.setDomainName(DomainName);
	}

	/**
	 * Sets the ID of this email domain.
	 *
	 * @param Id the ID of this email domain
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this email domain.
	 *
	 * @param ModifiedBy the modified by of this email domain
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this email domain.
	 *
	 * @param ModifiedDate the modified date of this email domain
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this email domain.
	 *
	 * @param primaryKey the primary key of this email domain
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this email domain is row status.
	 *
	 * @param RowStatus the row status of this email domain
	 */
	@Override
	public void setRowStatus(boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected EmailDomainWrapper wrap(EmailDomain emailDomain) {
		return new EmailDomainWrapper(emailDomain);
	}

}