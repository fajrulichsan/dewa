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

import com.astra.dewa.model.DiskonFakpol;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DiskonFakpol in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DiskonFakpolCacheModel
	implements CacheModel<DiskonFakpol>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DiskonFakpolCacheModel)) {
			return false;
		}

		DiskonFakpolCacheModel diskonFakpolCacheModel =
			(DiskonFakpolCacheModel)object;

		if (Id == diskonFakpolCacheModel.Id) {
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
		StringBundler sb = new StringBundler(27);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", DealerId=");
		sb.append(DealerId);
		sb.append(", Tahun=");
		sb.append(Tahun);
		sb.append(", Periode=");
		sb.append(Periode);
		sb.append(", Keterangan=");
		sb.append(Keterangan);
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
	public DiskonFakpol toEntityModel() {
		DiskonFakpolImpl diskonFakpolImpl = new DiskonFakpolImpl();

		diskonFakpolImpl.setId(Id);
		diskonFakpolImpl.setDealerId(DealerId);
		diskonFakpolImpl.setTahun(Tahun);

		if (Periode == null) {
			diskonFakpolImpl.setPeriode("");
		}
		else {
			diskonFakpolImpl.setPeriode(Periode);
		}

		if (Keterangan == null) {
			diskonFakpolImpl.setKeterangan("");
		}
		else {
			diskonFakpolImpl.setKeterangan(Keterangan);
		}

		diskonFakpolImpl.setFileId(FileId);

		if (FileName == null) {
			diskonFakpolImpl.setFileName("");
		}
		else {
			diskonFakpolImpl.setFileName(FileName);
		}

		if (FilePath == null) {
			diskonFakpolImpl.setFilePath("");
		}
		else {
			diskonFakpolImpl.setFilePath(FilePath);
		}

		diskonFakpolImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			diskonFakpolImpl.setCreatedDate(null);
		}
		else {
			diskonFakpolImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			diskonFakpolImpl.setCreatedBy("");
		}
		else {
			diskonFakpolImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			diskonFakpolImpl.setModifiedDate(null);
		}
		else {
			diskonFakpolImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			diskonFakpolImpl.setModifiedBy("");
		}
		else {
			diskonFakpolImpl.setModifiedBy(ModifiedBy);
		}

		diskonFakpolImpl.resetOriginalValues();

		return diskonFakpolImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		DealerId = objectInput.readInt();

		Tahun = objectInput.readInt();
		Periode = objectInput.readUTF();
		Keterangan = objectInput.readUTF();

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

		if (Periode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Periode);
		}

		if (Keterangan == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Keterangan);
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
	public String Periode;
	public String Keterangan;
	public long FileId;
	public String FileName;
	public String FilePath;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}