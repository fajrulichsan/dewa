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

import com.astra.dewa.model.ApplicationAssignStatus;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ApplicationAssignStatus in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApplicationAssignStatusCacheModel
	implements CacheModel<ApplicationAssignStatus>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ApplicationAssignStatusCacheModel)) {
			return false;
		}

		ApplicationAssignStatusCacheModel applicationAssignStatusCacheModel =
			(ApplicationAssignStatusCacheModel)object;

		if (Id == applicationAssignStatusCacheModel.Id) {
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
	public ApplicationAssignStatus toEntityModel() {
		ApplicationAssignStatusImpl applicationAssignStatusImpl =
			new ApplicationAssignStatusImpl();

		applicationAssignStatusImpl.setId(Id);

		if (Name == null) {
			applicationAssignStatusImpl.setName("");
		}
		else {
			applicationAssignStatusImpl.setName(Name);
		}

		if (Description == null) {
			applicationAssignStatusImpl.setDescription("");
		}
		else {
			applicationAssignStatusImpl.setDescription(Description);
		}

		applicationAssignStatusImpl.setRowStatus(RowStatus);

		if (CreatedBy == null) {
			applicationAssignStatusImpl.setCreatedBy("");
		}
		else {
			applicationAssignStatusImpl.setCreatedBy(CreatedBy);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			applicationAssignStatusImpl.setCreatedDate(null);
		}
		else {
			applicationAssignStatusImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (ModifiedBy == null) {
			applicationAssignStatusImpl.setModifiedBy("");
		}
		else {
			applicationAssignStatusImpl.setModifiedBy(ModifiedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			applicationAssignStatusImpl.setModifiedDate(null);
		}
		else {
			applicationAssignStatusImpl.setModifiedDate(new Date(ModifiedDate));
		}

		applicationAssignStatusImpl.resetOriginalValues();

		return applicationAssignStatusImpl;
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