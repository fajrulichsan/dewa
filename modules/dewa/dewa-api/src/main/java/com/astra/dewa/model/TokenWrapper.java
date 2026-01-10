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
 * This class is a wrapper for {@link Token}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Token
 * @generated
 */
public class TokenWrapper
	extends BaseModelWrapper<Token> implements ModelWrapper<Token>, Token {

	public TokenWrapper(Token token) {
		super(token);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("TokenNumber", getTokenNumber());
		attributes.put("CreatedDate", getCreatedDate());
		attributes.put("ExpiredDate", getExpiredDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer Id = (Integer)attributes.get("Id");

		if (Id != null) {
			setId(Id);
		}

		String TokenNumber = (String)attributes.get("TokenNumber");

		if (TokenNumber != null) {
			setTokenNumber(TokenNumber);
		}

		Date CreatedDate = (Date)attributes.get("CreatedDate");

		if (CreatedDate != null) {
			setCreatedDate(CreatedDate);
		}

		Date ExpiredDate = (Date)attributes.get("ExpiredDate");

		if (ExpiredDate != null) {
			setExpiredDate(ExpiredDate);
		}
	}

	@Override
	public Token cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created date of this token.
	 *
	 * @return the created date of this token
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the expired date of this token.
	 *
	 * @return the expired date of this token
	 */
	@Override
	public Date getExpiredDate() {
		return model.getExpiredDate();
	}

	/**
	 * Returns the ID of this token.
	 *
	 * @return the ID of this token
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the primary key of this token.
	 *
	 * @return the primary key of this token
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the token number of this token.
	 *
	 * @return the token number of this token
	 */
	@Override
	public String getTokenNumber() {
		return model.getTokenNumber();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the created date of this token.
	 *
	 * @param CreatedDate the created date of this token
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the expired date of this token.
	 *
	 * @param ExpiredDate the expired date of this token
	 */
	@Override
	public void setExpiredDate(Date ExpiredDate) {
		model.setExpiredDate(ExpiredDate);
	}

	/**
	 * Sets the ID of this token.
	 *
	 * @param Id the ID of this token
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the primary key of this token.
	 *
	 * @param primaryKey the primary key of this token
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the token number of this token.
	 *
	 * @param TokenNumber the token number of this token
	 */
	@Override
	public void setTokenNumber(String TokenNumber) {
		model.setTokenNumber(TokenNumber);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected TokenWrapper wrap(Token token) {
		return new TokenWrapper(token);
	}

}