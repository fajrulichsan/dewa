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

import com.astra.dewa.model.ReportPlafond;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ReportPlafond in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ReportPlafondCacheModel
	implements CacheModel<ReportPlafond>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ReportPlafondCacheModel)) {
			return false;
		}

		ReportPlafondCacheModel reportPlafondCacheModel =
			(ReportPlafondCacheModel)object;

		if (Id == reportPlafondCacheModel.Id) {
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
		sb.append(", Periode=");
		sb.append(Periode);
		sb.append(", Keterangan=");
		sb.append(Keterangan);
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
	public ReportPlafond toEntityModel() {
		ReportPlafondImpl reportPlafondImpl = new ReportPlafondImpl();

		reportPlafondImpl.setId(Id);
		reportPlafondImpl.setDealerId(DealerId);
		reportPlafondImpl.setFileId(FileId);

		if (FileName == null) {
			reportPlafondImpl.setFileName("");
		}
		else {
			reportPlafondImpl.setFileName(FileName);
		}

		if (FilePath == null) {
			reportPlafondImpl.setFilePath("");
		}
		else {
			reportPlafondImpl.setFilePath(FilePath);
		}

		if (Periode == Long.MIN_VALUE) {
			reportPlafondImpl.setPeriode(null);
		}
		else {
			reportPlafondImpl.setPeriode(new Date(Periode));
		}

		if (Keterangan == null) {
			reportPlafondImpl.setKeterangan("");
		}
		else {
			reportPlafondImpl.setKeterangan(Keterangan);
		}

		reportPlafondImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			reportPlafondImpl.setCreatedDate(null);
		}
		else {
			reportPlafondImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			reportPlafondImpl.setCreatedBy("");
		}
		else {
			reportPlafondImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			reportPlafondImpl.setModifiedDate(null);
		}
		else {
			reportPlafondImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			reportPlafondImpl.setModifiedBy("");
		}
		else {
			reportPlafondImpl.setModifiedBy(ModifiedBy);
		}

		reportPlafondImpl.resetOriginalValues();

		return reportPlafondImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		DealerId = objectInput.readInt();

		FileId = objectInput.readLong();
		FileName = objectInput.readUTF();
		FilePath = objectInput.readUTF();
		Periode = objectInput.readLong();
		Keterangan = objectInput.readUTF();

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

		objectOutput.writeLong(Periode);

		if (Keterangan == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Keterangan);
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
	public long FileId;
	public String FileName;
	public String FilePath;
	public long Periode;
	public String Keterangan;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}