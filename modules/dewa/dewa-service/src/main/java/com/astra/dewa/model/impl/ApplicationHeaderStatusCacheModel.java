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

import com.astra.dewa.model.ApplicationHeaderStatus;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ApplicationHeaderStatus in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApplicationHeaderStatusCacheModel
	implements CacheModel<ApplicationHeaderStatus>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ApplicationHeaderStatusCacheModel)) {
			return false;
		}

		ApplicationHeaderStatusCacheModel applicationHeaderStatusCacheModel =
			(ApplicationHeaderStatusCacheModel)object;

		if (Id == applicationHeaderStatusCacheModel.Id) {
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
		sb.append(", RowStatus=");
		sb.append(RowStatus);
		sb.append(", CreatedBy=");
		sb.append(CreatedBy);
		sb.append(", CreatedDate=");
		sb.append(CreatedDate);
		sb.append(", ModifiedBy=");
		sb.append(ModifiedBy);
		sb.append(", ModifiedDate=");
		sb.append(ModifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ApplicationHeaderStatus toEntityModel() {
		ApplicationHeaderStatusImpl applicationHeaderStatusImpl =
			new ApplicationHeaderStatusImpl();

		applicationHeaderStatusImpl.setId(Id);

		if (Name == null) {
			applicationHeaderStatusImpl.setName("");
		}
		else {
			applicationHeaderStatusImpl.setName(Name);
		}

		if (Description == null) {
			applicationHeaderStatusImpl.setDescription("");
		}
		else {
			applicationHeaderStatusImpl.setDescription(Description);
		}

		applicationHeaderStatusImpl.setRowStatus(RowStatus);

		if (CreatedBy == null) {
			applicationHeaderStatusImpl.setCreatedBy("");
		}
		else {
			applicationHeaderStatusImpl.setCreatedBy(CreatedBy);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			applicationHeaderStatusImpl.setCreatedDate(null);
		}
		else {
			applicationHeaderStatusImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (ModifiedBy == null) {
			applicationHeaderStatusImpl.setModifiedBy("");
		}
		else {
			applicationHeaderStatusImpl.setModifiedBy(ModifiedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			applicationHeaderStatusImpl.setModifiedDate(null);
		}
		else {
			applicationHeaderStatusImpl.setModifiedDate(new Date(ModifiedDate));
		}

		applicationHeaderStatusImpl.resetOriginalValues();

		return applicationHeaderStatusImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();
		Name = objectInput.readUTF();
		Description = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
		CreatedBy = objectInput.readUTF();
		CreatedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
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

		objectOutput.writeBoolean(RowStatus);

		if (CreatedBy == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CreatedBy);
		}

		objectOutput.writeLong(CreatedDate);

		if (ModifiedBy == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ModifiedBy);
		}

		objectOutput.writeLong(ModifiedDate);
	}

	public int Id;
	public String Name;
	public String Description;
	public boolean RowStatus;
	public String CreatedBy;
	public long CreatedDate;
	public String ModifiedBy;
	public long ModifiedDate;

}