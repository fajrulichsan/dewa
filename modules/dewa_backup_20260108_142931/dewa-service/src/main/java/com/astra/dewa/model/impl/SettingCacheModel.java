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

import com.astra.dewa.model.Setting;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Setting in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SettingCacheModel implements CacheModel<Setting>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SettingCacheModel)) {
			return false;
		}

		SettingCacheModel settingCacheModel = (SettingCacheModel)object;

		if (Id == settingCacheModel.Id) {
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
		StringBundler sb = new StringBundler(19);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", KeySetting=");
		sb.append(KeySetting);
		sb.append(", Code=");
		sb.append(Code);
		sb.append(", Value=");
		sb.append(Value);
		sb.append(", RowStatus=");
		sb.append(RowStatus);
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
	public Setting toEntityModel() {
		SettingImpl settingImpl = new SettingImpl();

		settingImpl.setId(Id);

		if (KeySetting == null) {
			settingImpl.setKeySetting("");
		}
		else {
			settingImpl.setKeySetting(KeySetting);
		}

		if (Code == null) {
			settingImpl.setCode("");
		}
		else {
			settingImpl.setCode(Code);
		}

		if (Value == null) {
			settingImpl.setValue("");
		}
		else {
			settingImpl.setValue(Value);
		}

		settingImpl.setRowStatus(RowStatus);

		if (CreatedBy == null) {
			settingImpl.setCreatedBy("");
		}
		else {
			settingImpl.setCreatedBy(CreatedBy);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			settingImpl.setCreatedDate(null);
		}
		else {
			settingImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (ModifiedBy == null) {
			settingImpl.setModifiedBy("");
		}
		else {
			settingImpl.setModifiedBy(ModifiedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			settingImpl.setModifiedDate(null);
		}
		else {
			settingImpl.setModifiedDate(new Date(ModifiedDate));
		}

		settingImpl.resetOriginalValues();

		return settingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();
		KeySetting = objectInput.readUTF();
		Code = objectInput.readUTF();
		Value = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
		CreatedBy = objectInput.readUTF();
		CreatedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		if (KeySetting == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(KeySetting);
		}

		if (Code == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Code);
		}

		if (Value == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Value);
		}

		objectOutput.writeBoolean(RowStatus);

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

	public int Id;
	public String KeySetting;
	public String Code;
	public String Value;
	public boolean RowStatus;
	public String CreatedBy;
	public long CreatedDate;
	public String ModifiedBy;
	public long ModifiedDate;

}