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

import com.astra.dewa.model.TipeKendaraan;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TipeKendaraan in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TipeKendaraanCacheModel
	implements CacheModel<TipeKendaraan>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TipeKendaraanCacheModel)) {
			return false;
		}

		TipeKendaraanCacheModel tipeKendaraanCacheModel =
			(TipeKendaraanCacheModel)object;

		if (Id == tipeKendaraanCacheModel.Id) {
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
		StringBundler sb = new StringBundler(15);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", Name=");
		sb.append(Name);
		sb.append(", CreatedDate=");
		sb.append(CreatedDate);
		sb.append(", CreatedBy=");
		sb.append(CreatedBy);
		sb.append(", ModifiedDate=");
		sb.append(ModifiedDate);
		sb.append(", ModifiedBy=");
		sb.append(ModifiedBy);
		sb.append(", RowStatus=");
		sb.append(RowStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TipeKendaraan toEntityModel() {
		TipeKendaraanImpl tipeKendaraanImpl = new TipeKendaraanImpl();

		tipeKendaraanImpl.setId(Id);

		if (Name == null) {
			tipeKendaraanImpl.setName("");
		}
		else {
			tipeKendaraanImpl.setName(Name);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			tipeKendaraanImpl.setCreatedDate(null);
		}
		else {
			tipeKendaraanImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			tipeKendaraanImpl.setCreatedBy("");
		}
		else {
			tipeKendaraanImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			tipeKendaraanImpl.setModifiedDate(null);
		}
		else {
			tipeKendaraanImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			tipeKendaraanImpl.setModifiedBy("");
		}
		else {
			tipeKendaraanImpl.setModifiedBy(ModifiedBy);
		}

		tipeKendaraanImpl.setRowStatus(RowStatus);

		tipeKendaraanImpl.resetOriginalValues();

		return tipeKendaraanImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();
		Name = objectInput.readUTF();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
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

		objectOutput.writeBoolean(RowStatus);
	}

	public int Id;
	public String Name;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;
	public boolean RowStatus;

}