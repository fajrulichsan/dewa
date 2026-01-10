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

import com.astra.dewa.model.Cabang;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Cabang in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CabangCacheModel implements CacheModel<Cabang>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CabangCacheModel)) {
			return false;
		}

		CabangCacheModel cabangCacheModel = (CabangCacheModel)object;

		if (Id == cabangCacheModel.Id) {
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
		StringBundler sb = new StringBundler(17);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", Name=");
		sb.append(Name);
		sb.append(", Description=");
		sb.append(Description);
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
	public Cabang toEntityModel() {
		CabangImpl cabangImpl = new CabangImpl();

		cabangImpl.setId(Id);

		if (Name == null) {
			cabangImpl.setName("");
		}
		else {
			cabangImpl.setName(Name);
		}

		if (Description == null) {
			cabangImpl.setDescription("");
		}
		else {
			cabangImpl.setDescription(Description);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			cabangImpl.setCreatedDate(null);
		}
		else {
			cabangImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			cabangImpl.setCreatedBy("");
		}
		else {
			cabangImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			cabangImpl.setModifiedDate(null);
		}
		else {
			cabangImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			cabangImpl.setModifiedBy("");
		}
		else {
			cabangImpl.setModifiedBy(ModifiedBy);
		}

		cabangImpl.setRowStatus(RowStatus);

		cabangImpl.resetOriginalValues();

		return cabangImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();
		Name = objectInput.readUTF();
		Description = objectInput.readUTF();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
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

		if (Description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Description);
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
	public String Name;
	public String Description;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;
	public boolean RowStatus;

}