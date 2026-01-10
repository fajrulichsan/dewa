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

import com.astra.dewa.model.KategoriDealer;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KategoriDealer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KategoriDealerCacheModel
	implements CacheModel<KategoriDealer>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof KategoriDealerCacheModel)) {
			return false;
		}

		KategoriDealerCacheModel kategoriDealerCacheModel =
			(KategoriDealerCacheModel)object;

		if (Id == kategoriDealerCacheModel.Id) {
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
		sb.append(", Judul=");
		sb.append(Judul);
		sb.append(", PeriodeReview=");
		sb.append(PeriodeReview);
		sb.append(", Tahun=");
		sb.append(Tahun);
		sb.append(", FileId=");
		sb.append(FileId);
		sb.append(", FileName=");
		sb.append(FileName);
		sb.append(", rowStatus=");
		sb.append(rowStatus);
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
	public KategoriDealer toEntityModel() {
		KategoriDealerImpl kategoriDealerImpl = new KategoriDealerImpl();

		kategoriDealerImpl.setId(Id);
		kategoriDealerImpl.setDealerId(DealerId);

		if (Judul == null) {
			kategoriDealerImpl.setJudul("");
		}
		else {
			kategoriDealerImpl.setJudul(Judul);
		}

		if (PeriodeReview == null) {
			kategoriDealerImpl.setPeriodeReview("");
		}
		else {
			kategoriDealerImpl.setPeriodeReview(PeriodeReview);
		}

		kategoriDealerImpl.setTahun(Tahun);
		kategoriDealerImpl.setFileId(FileId);

		if (FileName == null) {
			kategoriDealerImpl.setFileName("");
		}
		else {
			kategoriDealerImpl.setFileName(FileName);
		}

		kategoriDealerImpl.setRowStatus(rowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			kategoriDealerImpl.setCreatedDate(null);
		}
		else {
			kategoriDealerImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			kategoriDealerImpl.setCreatedBy("");
		}
		else {
			kategoriDealerImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			kategoriDealerImpl.setModifiedDate(null);
		}
		else {
			kategoriDealerImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			kategoriDealerImpl.setModifiedBy("");
		}
		else {
			kategoriDealerImpl.setModifiedBy(ModifiedBy);
		}

		kategoriDealerImpl.resetOriginalValues();

		return kategoriDealerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readLong();

		DealerId = objectInput.readInt();
		Judul = objectInput.readUTF();
		PeriodeReview = objectInput.readUTF();

		Tahun = objectInput.readInt();

		FileId = objectInput.readLong();
		FileName = objectInput.readUTF();

		rowStatus = objectInput.readBoolean();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(Id);

		objectOutput.writeInt(DealerId);

		if (Judul == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Judul);
		}

		if (PeriodeReview == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(PeriodeReview);
		}

		objectOutput.writeInt(Tahun);

		objectOutput.writeLong(FileId);

		if (FileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(FileName);
		}

		objectOutput.writeBoolean(rowStatus);
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

	public long Id;
	public int DealerId;
	public String Judul;
	public String PeriodeReview;
	public int Tahun;
	public long FileId;
	public String FileName;
	public boolean rowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}