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

import com.astra.dewa.model.FakturIndirect;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FakturIndirect in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FakturIndirectCacheModel
	implements CacheModel<FakturIndirect>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FakturIndirectCacheModel)) {
			return false;
		}

		FakturIndirectCacheModel fakturIndirectCacheModel =
			(FakturIndirectCacheModel)object;

		if (Id == fakturIndirectCacheModel.Id) {
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
		sb.append(", Keterangan=");
		sb.append(Keterangan);
		sb.append(", InvoiceDate=");
		sb.append(InvoiceDate);
		sb.append(", UploadDate=");
		sb.append(UploadDate);
		sb.append(", FileId=");
		sb.append(FileId);
		sb.append(", FileName=");
		sb.append(FileName);
		sb.append(", FilePath=");
		sb.append(FilePath);
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
	public FakturIndirect toEntityModel() {
		FakturIndirectImpl fakturIndirectImpl = new FakturIndirectImpl();

		fakturIndirectImpl.setId(Id);
		fakturIndirectImpl.setDealerId(DealerId);

		if (Keterangan == null) {
			fakturIndirectImpl.setKeterangan("");
		}
		else {
			fakturIndirectImpl.setKeterangan(Keterangan);
		}

		if (InvoiceDate == Long.MIN_VALUE) {
			fakturIndirectImpl.setInvoiceDate(null);
		}
		else {
			fakturIndirectImpl.setInvoiceDate(new Date(InvoiceDate));
		}

		if (UploadDate == Long.MIN_VALUE) {
			fakturIndirectImpl.setUploadDate(null);
		}
		else {
			fakturIndirectImpl.setUploadDate(new Date(UploadDate));
		}

		fakturIndirectImpl.setFileId(FileId);

		if (FileName == null) {
			fakturIndirectImpl.setFileName("");
		}
		else {
			fakturIndirectImpl.setFileName(FileName);
		}

		if (FilePath == null) {
			fakturIndirectImpl.setFilePath("");
		}
		else {
			fakturIndirectImpl.setFilePath(FilePath);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			fakturIndirectImpl.setCreatedDate(null);
		}
		else {
			fakturIndirectImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			fakturIndirectImpl.setCreatedBy("");
		}
		else {
			fakturIndirectImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			fakturIndirectImpl.setModifiedDate(null);
		}
		else {
			fakturIndirectImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			fakturIndirectImpl.setModifiedBy("");
		}
		else {
			fakturIndirectImpl.setModifiedBy(ModifiedBy);
		}

		fakturIndirectImpl.setRowStatus(RowStatus);

		fakturIndirectImpl.resetOriginalValues();

		return fakturIndirectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		DealerId = objectInput.readInt();
		Keterangan = objectInput.readUTF();
		InvoiceDate = objectInput.readLong();
		UploadDate = objectInput.readLong();

		FileId = objectInput.readLong();
		FileName = objectInput.readUTF();
		FilePath = objectInput.readUTF();
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

		if (Keterangan == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Keterangan);
		}

		objectOutput.writeLong(InvoiceDate);
		objectOutput.writeLong(UploadDate);

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
	public String Keterangan;
	public long InvoiceDate;
	public long UploadDate;
	public long FileId;
	public String FileName;
	public String FilePath;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;
	public boolean RowStatus;

}