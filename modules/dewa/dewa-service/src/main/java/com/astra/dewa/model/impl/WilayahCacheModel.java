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

import com.astra.dewa.model.Wilayah;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Wilayah in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class WilayahCacheModel implements CacheModel<Wilayah>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof WilayahCacheModel)) {
			return false;
		}

		WilayahCacheModel wilayahCacheModel = (WilayahCacheModel)object;

		if (Id == wilayahCacheModel.Id) {
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
		sb.append(", Name=");
		sb.append(Name);
		sb.append(", SK=");
		sb.append(SK);
		sb.append(", Description=");
		sb.append(Description);
		sb.append(", RowStatus=");
		sb.append(RowStatus);
		sb.append(", CreatedDate=");
		sb.append(CreatedDate);
		sb.append(", CreatedBy=");
		sb.append(CreatedBy);
		sb.append(", ModifiedDate=");
		sb.append(ModifiedDate);
		sb.append(", ModifiedBy=");
		sb.append(ModifiedBy);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Wilayah toEntityModel() {
		WilayahImpl wilayahImpl = new WilayahImpl();

		wilayahImpl.setId(Id);

		if (Name == null) {
			wilayahImpl.setName("");
		}
		else {
			wilayahImpl.setName(Name);
		}

		if (SK == null) {
			wilayahImpl.setSK("");
		}
		else {
			wilayahImpl.setSK(SK);
		}

		if (Description == null) {
			wilayahImpl.setDescription("");
		}
		else {
			wilayahImpl.setDescription(Description);
		}

		wilayahImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			wilayahImpl.setCreatedDate(null);
		}
		else {
			wilayahImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			wilayahImpl.setCreatedBy("");
		}
		else {
			wilayahImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			wilayahImpl.setModifiedDate(null);
		}
		else {
			wilayahImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			wilayahImpl.setModifiedBy("");
		}
		else {
			wilayahImpl.setModifiedBy(ModifiedBy);
		}

		wilayahImpl.resetOriginalValues();

		return wilayahImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();
		Name = objectInput.readUTF();
		SK = objectInput.readUTF();
		Description = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		if (Name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Name);
		}

		if (SK == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(SK);
		}

		if (Description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Description);
		}

		objectOutput.writeBoolean(RowStatus);
		objectOutput.writeLong(CreatedDate);

		if (CreatedBy == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CreatedBy);
		}

		objectOutput.writeLong(ModifiedDate);

		if (ModifiedBy == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ModifiedBy);
		}
	}

	public int Id;
	public String Name;

	public String SK;

	public String Description;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}