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

import com.astra.dewa.model.ApplicationAssign;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ApplicationAssign in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApplicationAssignCacheModel
	implements CacheModel<ApplicationAssign>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ApplicationAssignCacheModel)) {
			return false;
		}

		ApplicationAssignCacheModel applicationAssignCacheModel =
			(ApplicationAssignCacheModel)object;

		if (Id == applicationAssignCacheModel.Id) {
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
		sb.append(", ApplicationHeaderId=");
		sb.append(ApplicationHeaderId);
		sb.append(", ApplicationAssignStatusId=");
		sb.append(ApplicationAssignStatusId);
		sb.append(", ProfileId=");
		sb.append(ProfileId);
		sb.append(", StartDateOn=");
		sb.append(StartDateOn);
		sb.append(", CompletedDateOn=");
		sb.append(CompletedDateOn);
		sb.append(", Notes=");
		sb.append(Notes);
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
	public ApplicationAssign toEntityModel() {
		ApplicationAssignImpl applicationAssignImpl =
			new ApplicationAssignImpl();

		applicationAssignImpl.setId(Id);
		applicationAssignImpl.setApplicationHeaderId(ApplicationHeaderId);
		applicationAssignImpl.setApplicationAssignStatusId(
			ApplicationAssignStatusId);
		applicationAssignImpl.setProfileId(ProfileId);

		if (StartDateOn == Long.MIN_VALUE) {
			applicationAssignImpl.setStartDateOn(null);
		}
		else {
			applicationAssignImpl.setStartDateOn(new Date(StartDateOn));
		}

		if (CompletedDateOn == Long.MIN_VALUE) {
			applicationAssignImpl.setCompletedDateOn(null);
		}
		else {
			applicationAssignImpl.setCompletedDateOn(new Date(CompletedDateOn));
		}

		if (Notes == null) {
			applicationAssignImpl.setNotes("");
		}
		else {
			applicationAssignImpl.setNotes(Notes);
		}

		applicationAssignImpl.setRowStatus(RowStatus);

		if (CreatedBy == null) {
			applicationAssignImpl.setCreatedBy("");
		}
		else {
			applicationAssignImpl.setCreatedBy(CreatedBy);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			applicationAssignImpl.setCreatedDate(null);
		}
		else {
			applicationAssignImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (ModifiedBy == null) {
			applicationAssignImpl.setModifiedBy("");
		}
		else {
			applicationAssignImpl.setModifiedBy(ModifiedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			applicationAssignImpl.setModifiedDate(null);
		}
		else {
			applicationAssignImpl.setModifiedDate(new Date(ModifiedDate));
		}

		applicationAssignImpl.resetOriginalValues();

		return applicationAssignImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		ApplicationHeaderId = objectInput.readInt();

		ApplicationAssignStatusId = objectInput.readInt();

		ProfileId = objectInput.readLong();
		StartDateOn = objectInput.readLong();
		CompletedDateOn = objectInput.readLong();
		Notes = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
		CreatedBy = objectInput.readUTF();
		CreatedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		objectOutput.writeInt(ApplicationHeaderId);

		objectOutput.writeInt(ApplicationAssignStatusId);

		objectOutput.writeLong(ProfileId);
		objectOutput.writeLong(StartDateOn);
		objectOutput.writeLong(CompletedDateOn);

		if (Notes == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Notes);
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
	public int ApplicationHeaderId;
	public int ApplicationAssignStatusId;
	public long ProfileId;
	public long StartDateOn;
	public long CompletedDateOn;
	public String Notes;
	public boolean RowStatus;
	public String CreatedBy;
	public long CreatedDate;
	public String ModifiedBy;
	public long ModifiedDate;

}