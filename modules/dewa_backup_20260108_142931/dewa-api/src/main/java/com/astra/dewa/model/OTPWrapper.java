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
 * This class is a wrapper for {@link OTP}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OTP
 * @generated
 */
public class OTPWrapper
	extends BaseModelWrapper<OTP> implements ModelWrapper<OTP>, OTP {

	public OTPWrapper(OTP otp) {
		super(otp);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("OTPNumber", getOTPNumber());
		attributes.put("EmailNewUser", getEmailNewUser());
		attributes.put("CreatedDate", getCreatedDate());
		attributes.put("ExpiredDate", getExpiredDate());
		attributes.put("IsVerified", isIsVerified());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer Id = (Integer)attributes.get("Id");

		if (Id != null) {
			setId(Id);
		}

		Integer OTPNumber = (Integer)attributes.get("OTPNumber");

		if (OTPNumber != null) {
			setOTPNumber(OTPNumber);
		}

		String EmailNewUser = (String)attributes.get("EmailNewUser");

		if (EmailNewUser != null) {
			setEmailNewUser(EmailNewUser);
		}

		Date CreatedDate = (Date)attributes.get("CreatedDate");

		if (CreatedDate != null) {
			setCreatedDate(CreatedDate);
		}

		Date ExpiredDate = (Date)attributes.get("ExpiredDate");

		if (ExpiredDate != null) {
			setExpiredDate(ExpiredDate);
		}

		Boolean IsVerified = (Boolean)attributes.get("IsVerified");

		if (IsVerified != null) {
			setIsVerified(IsVerified);
		}
	}

	@Override
	public OTP cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created date of this otp.
	 *
	 * @return the created date of this otp
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the email new user of this otp.
	 *
	 * @return the email new user of this otp
	 */
	@Override
	public String getEmailNewUser() {
		return model.getEmailNewUser();
	}

	/**
	 * Returns the expired date of this otp.
	 *
	 * @return the expired date of this otp
	 */
	@Override
	public Date getExpiredDate() {
		return model.getExpiredDate();
	}

	/**
	 * Returns the ID of this otp.
	 *
	 * @return the ID of this otp
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the is verified of this otp.
	 *
	 * @return the is verified of this otp
	 */
	@Override
	public boolean getIsVerified() {
		return model.getIsVerified();
	}

	/**
	 * Returns the otp number of this otp.
	 *
	 * @return the otp number of this otp
	 */
	@Override
	public int getOTPNumber() {
		return model.getOTPNumber();
	}

	/**
	 * Returns the primary key of this otp.
	 *
	 * @return the primary key of this otp
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns <code>true</code> if this otp is is verified.
	 *
	 * @return <code>true</code> if this otp is is verified; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsVerified() {
		return model.isIsVerified();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the created date of this otp.
	 *
	 * @param CreatedDate the created date of this otp
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the email new user of this otp.
	 *
	 * @param EmailNewUser the email new user of this otp
	 */
	@Override
	public void setEmailNewUser(String EmailNewUser) {
		model.setEmailNewUser(EmailNewUser);
	}

	/**
	 * Sets the expired date of this otp.
	 *
	 * @param ExpiredDate the expired date of this otp
	 */
	@Override
	public void setExpiredDate(Date ExpiredDate) {
		model.setExpiredDate(ExpiredDate);
	}

	/**
	 * Sets the ID of this otp.
	 *
	 * @param Id the ID of this otp
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets whether this otp is is verified.
	 *
	 * @param IsVerified the is verified of this otp
	 */
	@Override
	public void setIsVerified(boolean IsVerified) {
		model.setIsVerified(IsVerified);
	}

	/**
	 * Sets the otp number of this otp.
	 *
	 * @param OTPNumber the otp number of this otp
	 */
	@Override
	public void setOTPNumber(int OTPNumber) {
		model.setOTPNumber(OTPNumber);
	}

	/**
	 * Sets the primary key of this otp.
	 *
	 * @param primaryKey the primary key of this otp
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected OTPWrapper wrap(OTP otp) {
		return new OTPWrapper(otp);
	}

}