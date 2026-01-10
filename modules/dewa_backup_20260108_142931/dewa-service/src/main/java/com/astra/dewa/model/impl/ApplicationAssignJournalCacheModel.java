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

import com.astra.dewa.model.ApplicationAssignJournal;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ApplicationAssignJournal in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApplicationAssignJournalCacheModel
	implements CacheModel<ApplicationAssignJournal>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ApplicationAssignJournalCacheModel)) {
			return false;
		}

		ApplicationAssignJournalCacheModel applicationAssignJournalCacheModel =
			(ApplicationAssignJournalCacheModel)object;

		if (Id == applicationAssignJournalCacheModel.Id) {
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
		sb.append(", ApplicationAssignId=");
		sb.append(ApplicationAssignId);
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
	public ApplicationAssignJournal toEntityModel() {
		ApplicationAssignJournalImpl applicationAssignJournalImpl =
			new ApplicationAssignJournalImpl();

		applicationAssignJournalImpl.setId(Id);
		applicationAssignJournalImpl.setApplicationAssignId(
			ApplicationAssignId);
		applicationAssignJournalImpl.setApplicationHeaderId(
			ApplicationHeaderId);
		applicationAssignJournalImpl.setApplicationAssignStatusId(
			ApplicationAssignStatusId);
		applicationAssignJournalImpl.setProfileId(ProfileId);

		if (StartDateOn == Long.MIN_VALUE) {
			applicationAssignJournalImpl.setStartDateOn(null);
		}
		else {
			applicationAssignJournalImpl.setStartDateOn(new Date(StartDateOn));
		}

		if (CompletedDateOn == Long.MIN_VALUE) {
			applicationAssignJournalImpl.setCompletedDateOn(null);
		}
		else {
			applicationAssignJournalImpl.setCompletedDateOn(
				new Date(CompletedDateOn));
		}

		if (Notes == null) {
			applicationAssignJournalImpl.setNotes("");
		}
		else {
			applicationAssignJournalImpl.setNotes(Notes);
		}

		applicationAssignJournalImpl.setRowStatus(RowStatus);

		if (CreatedBy == null) {
			applicationAssignJournalImpl.setCreatedBy("");
		}
		else {
			applicationAssignJournalImpl.setCreatedBy(CreatedBy);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			applicationAssignJournalImpl.setCreatedDate(null);
		}
		else {
			applicationAssignJournalImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (ModifiedBy == null) {
			applicationAssignJournalImpl.setModifiedBy("");
		}
		else {
			applicationAssignJournalImpl.setModifiedBy(ModifiedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			applicationAssignJournalImpl.setModifiedDate(null);
		}
		else {
			applicationAssignJournalImpl.setModifiedDate(
				new Date(ModifiedDate));
		}

		applicationAssignJournalImpl.resetOriginalValues();

		return applicationAssignJournalImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		ApplicationAssignId = objectInput.readInt();

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

		objectOutput.writeInt(ApplicationAssignId);

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
	public int ApplicationAssignId;
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