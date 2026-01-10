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

import com.astra.dewa.model.DiskonOther;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DiskonOther in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DiskonOtherCacheModel
	implements CacheModel<DiskonOther>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DiskonOtherCacheModel)) {
			return false;
		}

		DiskonOtherCacheModel diskonOtherCacheModel =
			(DiskonOtherCacheModel)object;

		if (diskonOtherId == diskonOtherCacheModel.diskonOtherId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, diskonOtherId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{diskonOtherId=");
		sb.append(diskonOtherId);
		sb.append(", dealerId=");
		sb.append(dealerId);
		sb.append(", tahun=");
		sb.append(tahun);
		sb.append(", kuartalId=");
		sb.append(kuartalId);
		sb.append(", keterangan=");
		sb.append(keterangan);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", filePath=");
		sb.append(filePath);
		sb.append(", rowStatus=");
		sb.append(rowStatus);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DiskonOther toEntityModel() {
		DiskonOtherImpl diskonOtherImpl = new DiskonOtherImpl();

		diskonOtherImpl.setDiskonOtherId(diskonOtherId);
		diskonOtherImpl.setDealerId(dealerId);
		diskonOtherImpl.setTahun(tahun);
		diskonOtherImpl.setKuartalId(kuartalId);

		if (keterangan == null) {
			diskonOtherImpl.setKeterangan("");
		}
		else {
			diskonOtherImpl.setKeterangan(keterangan);
		}

		diskonOtherImpl.setFileId(fileId);

		if (fileName == null) {
			diskonOtherImpl.setFileName("");
		}
		else {
			diskonOtherImpl.setFileName(fileName);
		}

		if (filePath == null) {
			diskonOtherImpl.setFilePath("");
		}
		else {
			diskonOtherImpl.setFilePath(filePath);
		}

		diskonOtherImpl.setRowStatus(rowStatus);

		if (createdDate == Long.MIN_VALUE) {
			diskonOtherImpl.setCreatedDate(null);
		}
		else {
			diskonOtherImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			diskonOtherImpl.setCreatedBy("");
		}
		else {
			diskonOtherImpl.setCreatedBy(createdBy);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			diskonOtherImpl.setModifiedDate(null);
		}
		else {
			diskonOtherImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (modifiedBy == null) {
			diskonOtherImpl.setModifiedBy("");
		}
		else {
			diskonOtherImpl.setModifiedBy(modifiedBy);
		}

		diskonOtherImpl.resetOriginalValues();

		return diskonOtherImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		diskonOtherId = objectInput.readLong();

		dealerId = objectInput.readInt();

		tahun = objectInput.readInt();

		kuartalId = objectInput.readInt();
		keterangan = objectInput.readUTF();

		fileId = objectInput.readLong();
		fileName = objectInput.readUTF();
		filePath = objectInput.readUTF();

		rowStatus = objectInput.readBoolean();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		modifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(diskonOtherId);

		objectOutput.writeInt(dealerId);

		objectOutput.writeInt(tahun);

		objectOutput.writeInt(kuartalId);

		if (keterangan == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(keterangan);
		}

		objectOutput.writeLong(fileId);

		if (fileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileName);
		}

		if (filePath == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(filePath);
		}

		objectOutput.writeBoolean(rowStatus);
		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		objectOutput.writeLong(modifiedDate);

		if (modifiedBy == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}
	}

	public long diskonOtherId;
	public int dealerId;
	public int tahun;
	public int kuartalId;
	public String keterangan;
	public long fileId;
	public String fileName;
	public String filePath;
	public boolean rowStatus;
	public long createdDate;
	public String createdBy;
	public long modifiedDate;
	public String modifiedBy;

}