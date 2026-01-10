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

import com.astra.dewa.model.SalesReport;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SalesReport in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SalesReportCacheModel
	implements CacheModel<SalesReport>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SalesReportCacheModel)) {
			return false;
		}

		SalesReportCacheModel salesReportCacheModel =
			(SalesReportCacheModel)object;

		if (Id == salesReportCacheModel.Id) {
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
	public SalesReport toEntityModel() {
		SalesReportImpl salesReportImpl = new SalesReportImpl();

		salesReportImpl.setId(Id);
		salesReportImpl.setDealerId(DealerId);
		salesReportImpl.setFileId(FileId);

		if (FileName == null) {
			salesReportImpl.setFileName("");
		}
		else {
			salesReportImpl.setFileName(FileName);
		}

		if (FilePath == null) {
			salesReportImpl.setFilePath("");
		}
		else {
			salesReportImpl.setFilePath(FilePath);
		}

		if (Periode == Long.MIN_VALUE) {
			salesReportImpl.setPeriode(null);
		}
		else {
			salesReportImpl.setPeriode(new Date(Periode));
		}

		if (Keterangan == null) {
			salesReportImpl.setKeterangan("");
		}
		else {
			salesReportImpl.setKeterangan(Keterangan);
		}

		salesReportImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			salesReportImpl.setCreatedDate(null);
		}
		else {
			salesReportImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			salesReportImpl.setCreatedBy("");
		}
		else {
			salesReportImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			salesReportImpl.setModifiedDate(null);
		}
		else {
			salesReportImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			salesReportImpl.setModifiedBy("");
		}
		else {
			salesReportImpl.setModifiedBy(ModifiedBy);
		}

		salesReportImpl.resetOriginalValues();

		return salesReportImpl;
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