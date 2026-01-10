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

package com.astra.dewa.model.impl;

import com.astra.dewa.model.OTP;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OTP in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class OTPCacheModel implements CacheModel<OTP>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof OTPCacheModel)) {
			return false;
		}

		OTPCacheModel otpCacheModel = (OTPCacheModel)object;

		if (Id == otpCacheModel.Id) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, Id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", OTPNumber=");
		sb.append(OTPNumber);
		sb.append(", EmailNewUser=");
		sb.append(EmailNewUser);
		sb.append(", CreatedDate=");
		sb.append(CreatedDate);
		sb.append(", ExpiredDate=");
		sb.append(ExpiredDate);
		sb.append(", IsVerified=");
		sb.append(IsVerified);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OTP toEntityModel() {
		OTPImpl otpImpl = new OTPImpl();

		otpImpl.setId(Id);
		otpImpl.setOTPNumber(OTPNumber);

		if (EmailNewUser == null) {
			otpImpl.setEmailNewUser("");
		}
		else {
			otpImpl.setEmailNewUser(EmailNewUser);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			otpImpl.setCreatedDate(null);
		}
		else {
			otpImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (ExpiredDate == Long.MIN_VALUE) {
			otpImpl.setExpiredDate(null);
		}
		else {
			otpImpl.setExpiredDate(new Date(ExpiredDate));
		}

		otpImpl.setIsVerified(IsVerified);

		otpImpl.resetOriginalValues();

		return otpImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		OTPNumber = objectInput.readInt();
		EmailNewUser = objectInput.readUTF();
		CreatedDate = objectInput.readLong();
		ExpiredDate = objectInput.readLong();

		IsVerified = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		objectOutput.writeInt(OTPNumber);

		if (EmailNewUser == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(EmailNewUser);
		}

		objectOutput.writeLong(CreatedDate);
		objectOutput.writeLong(ExpiredDate);

		objectOutput.writeBoolean(IsVerified);
	}

	public int Id;
	public int OTPNumber;
	public String EmailNewUser;
	public long CreatedDate;
	public long ExpiredDate;
	public boolean IsVerified;

}