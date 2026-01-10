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

import com.astra.dewa.model.Common;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Common in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CommonCacheModel implements CacheModel<Common>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommonCacheModel)) {
			return false;
		}

		CommonCacheModel commonCacheModel = (CommonCacheModel)object;

		if (CommonKey.equals(commonCacheModel.CommonKey)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CommonKey);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{CommonKey=");
		sb.append(CommonKey);
		sb.append(", CommonCode=");
		sb.append(CommonCode);
		sb.append(", CommonDesc1=");
		sb.append(CommonDesc1);
		sb.append(", CommonDesc2=");
		sb.append(CommonDesc2);
		sb.append(", CommonDesc3=");
		sb.append(CommonDesc3);
		sb.append(", CommonDesc4=");
		sb.append(CommonDesc4);
		sb.append(", CommonDesc5=");
		sb.append(CommonDesc5);
		sb.append(", Sequence=");
		sb.append(Sequence);
		sb.append(", IsActive=");
		sb.append(IsActive);
		sb.append(", CreatedBy=");
		sb.append(CreatedBy);
		sb.append(", CreatedDate=");
		sb.append(CreatedDate);
		sb.append(", ModifiedBy=");
		sb.append(ModifiedBy);
		sb.append(", ModifiedDate=");
		sb.append(ModifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Common toEntityModel() {
		CommonImpl commonImpl = new CommonImpl();

		if (CommonKey == null) {
			commonImpl.setCommonKey("");
		}
		else {
			commonImpl.setCommonKey(CommonKey);
		}

		if (CommonCode == null) {
			commonImpl.setCommonCode("");
		}
		else {
			commonImpl.setCommonCode(CommonCode);
		}

		if (CommonDesc1 == null) {
			commonImpl.setCommonDesc1("");
		}
		else {
			commonImpl.setCommonDesc1(CommonDesc1);
		}

		if (CommonDesc2 == null) {
			commonImpl.setCommonDesc2("");
		}
		else {
			commonImpl.setCommonDesc2(CommonDesc2);
		}

		if (CommonDesc3 == null) {
			commonImpl.setCommonDesc3("");
		}
		else {
			commonImpl.setCommonDesc3(CommonDesc3);
		}

		if (CommonDesc4 == null) {
			commonImpl.setCommonDesc4("");
		}
		else {
			commonImpl.setCommonDesc4(CommonDesc4);
		}

		if (CommonDesc5 == null) {
			commonImpl.setCommonDesc5("");
		}
		else {
			commonImpl.setCommonDesc5(CommonDesc5);
		}

		commonImpl.setSequence(Sequence);
		commonImpl.setIsActive(IsActive);

		if (CreatedBy == null) {
			commonImpl.setCreatedBy("");
		}
		else {
			commonImpl.setCreatedBy(CreatedBy);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			commonImpl.setCreatedDate(null);
		}
		else {
			commonImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (ModifiedBy == null) {
			commonImpl.setModifiedBy("");
		}
		else {
			commonImpl.setModifiedBy(ModifiedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			commonImpl.setModifiedDate(null);
		}
		else {
			commonImpl.setModifiedDate(new Date(ModifiedDate));
		}

		commonImpl.resetOriginalValues();

		return commonImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		CommonKey = objectInput.readUTF();
		CommonCode = objectInput.readUTF();
		CommonDesc1 = objectInput.readUTF();
		CommonDesc2 = objectInput.readUTF();
		CommonDesc3 = objectInput.readUTF();
		CommonDesc4 = objectInput.readUTF();
		CommonDesc5 = objectInput.readUTF();

		Sequence = objectInput.readInt();

		IsActive = objectInput.readBoolean();
		CreatedBy = objectInput.readUTF();
		CreatedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (CommonKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CommonKey);
		}

		if (CommonCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CommonCode);
		}

		if (CommonDesc1 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CommonDesc1);
		}

		if (CommonDesc2 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CommonDesc2);
		}

		if (CommonDesc3 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CommonDesc3);
		}

		if (CommonDesc4 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CommonDesc4);
		}

		if (CommonDesc5 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CommonDesc5);
		}

		objectOutput.writeInt(Sequence);

		objectOutput.writeBoolean(IsActive);

		if (CreatedBy == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CreatedBy);
		}

		objectOutput.writeLong(CreatedDate);

		if (ModifiedBy == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ModifiedBy);
		}

		objectOutput.writeLong(ModifiedDate);
	}

	public String CommonKey;
	public String CommonCode;
	public String CommonDesc1;
	public String CommonDesc2;
	public String CommonDesc3;
	public String CommonDesc4;
	public String CommonDesc5;
	public int Sequence;
	public boolean IsActive;
	public String CreatedBy;
	public long CreatedDate;
	public String ModifiedBy;
	public long ModifiedDate;

}