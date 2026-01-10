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

import com.astra.dewa.model.SP3D;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SP3D in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SP3DCacheModel implements CacheModel<SP3D>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SP3DCacheModel)) {
			return false;
		}

		SP3DCacheModel sp3dCacheModel = (SP3DCacheModel)object;

		if (Id == sp3dCacheModel.Id) {
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
		StringBundler sb = new StringBundler(25);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", DealerId=");
		sb.append(DealerId);
		sb.append(", Tahun=");
		sb.append(Tahun);
		sb.append(", Bulan=");
		sb.append(Bulan);
		sb.append(", FileId=");
		sb.append(FileId);
		sb.append(", FileName=");
		sb.append(FileName);
		sb.append(", FilePath=");
		sb.append(FilePath);
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
	public SP3D toEntityModel() {
		SP3DImpl sp3dImpl = new SP3DImpl();

		sp3dImpl.setId(Id);
		sp3dImpl.setDealerId(DealerId);
		sp3dImpl.setTahun(Tahun);

		if (Bulan == null) {
			sp3dImpl.setBulan("");
		}
		else {
			sp3dImpl.setBulan(Bulan);
		}

		sp3dImpl.setFileId(FileId);

		if (FileName == null) {
			sp3dImpl.setFileName("");
		}
		else {
			sp3dImpl.setFileName(FileName);
		}

		if (FilePath == null) {
			sp3dImpl.setFilePath("");
		}
		else {
			sp3dImpl.setFilePath(FilePath);
		}

		sp3dImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			sp3dImpl.setCreatedDate(null);
		}
		else {
			sp3dImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			sp3dImpl.setCreatedBy("");
		}
		else {
			sp3dImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			sp3dImpl.setModifiedDate(null);
		}
		else {
			sp3dImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			sp3dImpl.setModifiedBy("");
		}
		else {
			sp3dImpl.setModifiedBy(ModifiedBy);
		}

		sp3dImpl.resetOriginalValues();

		return sp3dImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		DealerId = objectInput.readInt();

		Tahun = objectInput.readInt();
		Bulan = objectInput.readUTF();

		FileId = objectInput.readLong();
		FileName = objectInput.readUTF();
		FilePath = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		objectOutput.writeInt(DealerId);

		objectOutput.writeInt(Tahun);

		if (Bulan == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Bulan);
		}

		objectOutput.writeLong(FileId);

		if (FileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(FileName);
		}

		if (FilePath == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(FilePath);
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
	public int DealerId;
	public int Tahun;
	public String Bulan;
	public long FileId;
	public String FileName;
	public String FilePath;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}