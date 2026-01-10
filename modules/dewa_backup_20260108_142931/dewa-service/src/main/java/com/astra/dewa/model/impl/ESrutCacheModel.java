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

import com.astra.dewa.model.ESrut;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ESrut in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ESrutCacheModel implements CacheModel<ESrut>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ESrutCacheModel)) {
			return false;
		}

		ESrutCacheModel eSrutCacheModel = (ESrutCacheModel)object;

		if (Id == eSrutCacheModel.Id) {
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
		sb.append(", FileId=");
		sb.append(FileId);
		sb.append(", FileName=");
		sb.append(FileName);
		sb.append(", FilePath=");
		sb.append(FilePath);
		sb.append(", PeriodDate=");
		sb.append(PeriodDate);
		sb.append(", Keterangan=");
		sb.append(Keterangan);
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
	public ESrut toEntityModel() {
		ESrutImpl eSrutImpl = new ESrutImpl();

		eSrutImpl.setId(Id);
		eSrutImpl.setDealerId(DealerId);
		eSrutImpl.setFileId(FileId);

		if (FileName == null) {
			eSrutImpl.setFileName("");
		}
		else {
			eSrutImpl.setFileName(FileName);
		}

		if (FilePath == null) {
			eSrutImpl.setFilePath("");
		}
		else {
			eSrutImpl.setFilePath(FilePath);
		}

		if (PeriodDate == Long.MIN_VALUE) {
			eSrutImpl.setPeriodDate(null);
		}
		else {
			eSrutImpl.setPeriodDate(new Date(PeriodDate));
		}

		if (Keterangan == null) {
			eSrutImpl.setKeterangan("");
		}
		else {
			eSrutImpl.setKeterangan(Keterangan);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			eSrutImpl.setCreatedDate(null);
		}
		else {
			eSrutImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			eSrutImpl.setCreatedBy("");
		}
		else {
			eSrutImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			eSrutImpl.setModifiedDate(null);
		}
		else {
			eSrutImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			eSrutImpl.setModifiedBy("");
		}
		else {
			eSrutImpl.setModifiedBy(ModifiedBy);
		}

		eSrutImpl.setRowStatus(RowStatus);

		eSrutImpl.resetOriginalValues();

		return eSrutImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		DealerId = objectInput.readInt();

		FileId = objectInput.readLong();
		FileName = objectInput.readUTF();
		FilePath = objectInput.readUTF();
		PeriodDate = objectInput.readLong();
		Keterangan = objectInput.readUTF();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		objectOutput.writeInt(DealerId);

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

		objectOutput.writeLong(PeriodDate);

		if (Keterangan == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Keterangan);
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
	public int DealerId;
	public long FileId;
	public String FileName;
	public String FilePath;
	public long PeriodDate;
	public String Keterangan;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;
	public boolean RowStatus;

}