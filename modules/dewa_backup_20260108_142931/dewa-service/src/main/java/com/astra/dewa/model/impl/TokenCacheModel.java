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

import com.astra.dewa.model.Token;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Token in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TokenCacheModel implements CacheModel<Token>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TokenCacheModel)) {
			return false;
		}

		TokenCacheModel tokenCacheModel = (TokenCacheModel)object;

		if (Id == tokenCacheModel.Id) {
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
		StringBundler sb = new StringBundler(9);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", TokenNumber=");
		sb.append(TokenNumber);
		sb.append(", CreatedDate=");
		sb.append(CreatedDate);
		sb.append(", ExpiredDate=");
		sb.append(ExpiredDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Token toEntityModel() {
		TokenImpl tokenImpl = new TokenImpl();

		tokenImpl.setId(Id);

		if (TokenNumber == null) {
			tokenImpl.setTokenNumber("");
		}
		else {
			tokenImpl.setTokenNumber(TokenNumber);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			tokenImpl.setCreatedDate(null);
		}
		else {
			tokenImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (ExpiredDate == Long.MIN_VALUE) {
			tokenImpl.setExpiredDate(null);
		}
		else {
			tokenImpl.setExpiredDate(new Date(ExpiredDate));
		}

		tokenImpl.resetOriginalValues();

		return tokenImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();
		TokenNumber = objectInput.readUTF();
		CreatedDate = objectInput.readLong();
		ExpiredDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		if (TokenNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(TokenNumber);
		}

		objectOutput.writeLong(CreatedDate);
		objectOutput.writeLong(ExpiredDate);
	}

	public int Id;
	public String TokenNumber;
	public long CreatedDate;
	public long ExpiredDate;

}