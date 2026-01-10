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

import com.astra.dewa.model.SalesProgram;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SalesProgram in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SalesProgramCacheModel
	implements CacheModel<SalesProgram>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SalesProgramCacheModel)) {
			return false;
		}

		SalesProgramCacheModel salesProgramCacheModel =
			(SalesProgramCacheModel)object;

		if (Id == salesProgramCacheModel.Id) {
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
		StringBundler sb = new StringBundler(21);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", Tahun=");
		sb.append(Tahun);
		sb.append(", Periode=");
		sb.append(Periode);
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
	public SalesProgram toEntityModel() {
		SalesProgramImpl salesProgramImpl = new SalesProgramImpl();

		salesProgramImpl.setId(Id);
		salesProgramImpl.setTahun(Tahun);

		if (Periode == null) {
			salesProgramImpl.setPeriode("");
		}
		else {
			salesProgramImpl.setPeriode(Periode);
		}

		salesProgramImpl.setFileId(FileId);

		if (FileName == null) {
			salesProgramImpl.setFileName("");
		}
		else {
			salesProgramImpl.setFileName(FileName);
		}

		salesProgramImpl.setRowStatus(rowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			salesProgramImpl.setCreatedDate(null);
		}
		else {
			salesProgramImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			salesProgramImpl.setCreatedBy("");
		}
		else {
			salesProgramImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			salesProgramImpl.setModifiedDate(null);
		}
		else {
			salesProgramImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			salesProgramImpl.setModifiedBy("");
		}
		else {
			salesProgramImpl.setModifiedBy(ModifiedBy);
		}

		salesProgramImpl.resetOriginalValues();

		return salesProgramImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readLong();

		Tahun = objectInput.readInt();
		Periode = objectInput.readUTF();

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

		objectOutput.writeInt(Tahun);

		if (Periode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Periode);
		}

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
	public int Tahun;
	public String Periode;
	public long FileId;
	public String FileName;
	public boolean rowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}