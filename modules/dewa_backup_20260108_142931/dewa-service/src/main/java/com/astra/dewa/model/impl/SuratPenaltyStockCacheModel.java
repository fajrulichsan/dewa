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

import com.astra.dewa.model.SuratPenaltyStock;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SuratPenaltyStock in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SuratPenaltyStockCacheModel
	implements CacheModel<SuratPenaltyStock>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SuratPenaltyStockCacheModel)) {
			return false;
		}

		SuratPenaltyStockCacheModel suratPenaltyStockCacheModel =
			(SuratPenaltyStockCacheModel)object;

		if (Id == suratPenaltyStockCacheModel.Id) {
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
		sb.append(", Periode=");
		sb.append(Periode);
		sb.append(", Tahun=");
		sb.append(Tahun);
		sb.append(", FileId=");
		sb.append(FileId);
		sb.append(", FileName=");
		sb.append(FileName);
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
	public SuratPenaltyStock toEntityModel() {
		SuratPenaltyStockImpl suratPenaltyStockImpl =
			new SuratPenaltyStockImpl();

		suratPenaltyStockImpl.setId(Id);
		suratPenaltyStockImpl.setDealerId(DealerId);

		if (Judul == null) {
			suratPenaltyStockImpl.setJudul("");
		}
		else {
			suratPenaltyStockImpl.setJudul(Judul);
		}

		if (Periode == null) {
			suratPenaltyStockImpl.setPeriode("");
		}
		else {
			suratPenaltyStockImpl.setPeriode(Periode);
		}

		suratPenaltyStockImpl.setTahun(Tahun);
		suratPenaltyStockImpl.setFileId(FileId);

		if (FileName == null) {
			suratPenaltyStockImpl.setFileName("");
		}
		else {
			suratPenaltyStockImpl.setFileName(FileName);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			suratPenaltyStockImpl.setCreatedDate(null);
		}
		else {
			suratPenaltyStockImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			suratPenaltyStockImpl.setCreatedBy("");
		}
		else {
			suratPenaltyStockImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			suratPenaltyStockImpl.setModifiedDate(null);
		}
		else {
			suratPenaltyStockImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			suratPenaltyStockImpl.setModifiedBy("");
		}
		else {
			suratPenaltyStockImpl.setModifiedBy(ModifiedBy);
		}

		suratPenaltyStockImpl.setRowStatus(RowStatus);

		suratPenaltyStockImpl.resetOriginalValues();

		return suratPenaltyStockImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readLong();

		DealerId = objectInput.readInt();
		Judul = objectInput.readUTF();
		Periode = objectInput.readUTF();

		Tahun = objectInput.readInt();

		FileId = objectInput.readLong();
		FileName = objectInput.readUTF();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
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

		if (Periode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Periode);
		}

		objectOutput.writeInt(Tahun);

		objectOutput.writeLong(FileId);

		if (FileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(FileName);
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

	public long Id;
	public int DealerId;
	public String Judul;
	public String Periode;
	public int Tahun;
	public long FileId;
	public String FileName;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;
	public boolean RowStatus;

}