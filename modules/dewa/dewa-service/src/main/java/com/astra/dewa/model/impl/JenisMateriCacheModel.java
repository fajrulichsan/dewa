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

import com.astra.dewa.model.JenisMateri;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing JenisMateri in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JenisMateriCacheModel
	implements CacheModel<JenisMateri>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof JenisMateriCacheModel)) {
			return false;
		}

		JenisMateriCacheModel jenisMateriCacheModel =
			(JenisMateriCacheModel)object;

		if (Id == jenisMateriCacheModel.Id) {
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
		sb.append(", Name=");
		sb.append(Name);
		sb.append(", ImageId=");
		sb.append(ImageId);
		sb.append(", ImageName=");
		sb.append(ImageName);
		sb.append(", ImagePath=");
		sb.append(ImagePath);
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
	public JenisMateri toEntityModel() {
		JenisMateriImpl jenisMateriImpl = new JenisMateriImpl();

		jenisMateriImpl.setId(Id);

		if (Name == null) {
			jenisMateriImpl.setName("");
		}
		else {
			jenisMateriImpl.setName(Name);
		}

		jenisMateriImpl.setImageId(ImageId);

		if (ImageName == null) {
			jenisMateriImpl.setImageName("");
		}
		else {
			jenisMateriImpl.setImageName(ImageName);
		}

		if (ImagePath == null) {
			jenisMateriImpl.setImagePath("");
		}
		else {
			jenisMateriImpl.setImagePath(ImagePath);
		}

		jenisMateriImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			jenisMateriImpl.setCreatedDate(null);
		}
		else {
			jenisMateriImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			jenisMateriImpl.setCreatedBy("");
		}
		else {
			jenisMateriImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			jenisMateriImpl.setModifiedDate(null);
		}
		else {
			jenisMateriImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			jenisMateriImpl.setModifiedBy("");
		}
		else {
			jenisMateriImpl.setModifiedBy(ModifiedBy);
		}

		jenisMateriImpl.resetOriginalValues();

		return jenisMateriImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();
		Name = objectInput.readUTF();

		ImageId = objectInput.readLong();
		ImageName = objectInput.readUTF();
		ImagePath = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		if (Name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Name);
		}

		objectOutput.writeLong(ImageId);

		if (ImageName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ImageName);
		}

		if (ImagePath == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ImagePath);
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
	public String Name;
	public long ImageId;
	public String ImageName;
	public String ImagePath;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}