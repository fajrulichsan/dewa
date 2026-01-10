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

import com.astra.dewa.model.SkIris;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SkIris in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SkIrisCacheModel implements CacheModel<SkIris>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SkIrisCacheModel)) {
			return false;
		}

		SkIrisCacheModel skIrisCacheModel = (SkIrisCacheModel)object;

		if (Id == skIrisCacheModel.Id) {
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
		StringBundler sb = new StringBundler(29);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", Periode=");
		sb.append(Periode);
		sb.append(", DealerId=");
		sb.append(DealerId);
		sb.append(", WilayahId=");
		sb.append(WilayahId);
		sb.append(", Tahun=");
		sb.append(Tahun);
		sb.append(", Kategori=");
		sb.append(Kategori);
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
	public SkIris toEntityModel() {
		SkIrisImpl skIrisImpl = new SkIrisImpl();

		skIrisImpl.setId(Id);

		if (Periode == null) {
			skIrisImpl.setPeriode("");
		}
		else {
			skIrisImpl.setPeriode(Periode);
		}

		skIrisImpl.setDealerId(DealerId);
		skIrisImpl.setWilayahId(WilayahId);
		skIrisImpl.setTahun(Tahun);

		if (Kategori == null) {
			skIrisImpl.setKategori("");
		}
		else {
			skIrisImpl.setKategori(Kategori);
		}

		skIrisImpl.setFileId(FileId);

		if (FileName == null) {
			skIrisImpl.setFileName("");
		}
		else {
			skIrisImpl.setFileName(FileName);
		}

		if (FilePath == null) {
			skIrisImpl.setFilePath("");
		}
		else {
			skIrisImpl.setFilePath(FilePath);
		}

		skIrisImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			skIrisImpl.setCreatedDate(null);
		}
		else {
			skIrisImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			skIrisImpl.setCreatedBy("");
		}
		else {
			skIrisImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			skIrisImpl.setModifiedDate(null);
		}
		else {
			skIrisImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			skIrisImpl.setModifiedBy("");
		}
		else {
			skIrisImpl.setModifiedBy(ModifiedBy);
		}

		skIrisImpl.resetOriginalValues();

		return skIrisImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();
		Periode = objectInput.readUTF();

		DealerId = objectInput.readInt();

		WilayahId = objectInput.readLong();

		Tahun = objectInput.readInt();
		Kategori = objectInput.readUTF();

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

		if (Periode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Periode);
		}

		objectOutput.writeInt(DealerId);

		objectOutput.writeLong(WilayahId);

		objectOutput.writeInt(Tahun);

		if (Kategori == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Kategori);
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
	public String Periode;
	public int DealerId;
	public long WilayahId;
	public int Tahun;
	public String Kategori;
	public long FileId;
	public String FileName;
	public String FilePath;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}