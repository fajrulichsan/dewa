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

import com.astra.dewa.model.TrainingAgendaParticipantUf;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TrainingAgendaParticipantUf in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TrainingAgendaParticipantUfCacheModel
	implements CacheModel<TrainingAgendaParticipantUf>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TrainingAgendaParticipantUfCacheModel)) {
			return false;
		}

		TrainingAgendaParticipantUfCacheModel
			trainingAgendaParticipantUfCacheModel =
				(TrainingAgendaParticipantUfCacheModel)object;

		if (Id == trainingAgendaParticipantUfCacheModel.Id) {
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
		StringBundler sb = new StringBundler(23);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", TrainingAgendaId=");
		sb.append(TrainingAgendaId);
		sb.append(", DealerId=");
		sb.append(DealerId);
		sb.append(", FileId=");
		sb.append(FileId);
		sb.append(", FileName=");
		sb.append(FileName);
		sb.append(", FilePath=");
		sb.append(FilePath);
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
	public TrainingAgendaParticipantUf toEntityModel() {
		TrainingAgendaParticipantUfImpl trainingAgendaParticipantUfImpl =
			new TrainingAgendaParticipantUfImpl();

		trainingAgendaParticipantUfImpl.setId(Id);
		trainingAgendaParticipantUfImpl.setTrainingAgendaId(TrainingAgendaId);
		trainingAgendaParticipantUfImpl.setDealerId(DealerId);
		trainingAgendaParticipantUfImpl.setFileId(FileId);

		if (FileName == null) {
			trainingAgendaParticipantUfImpl.setFileName("");
		}
		else {
			trainingAgendaParticipantUfImpl.setFileName(FileName);
		}

		if (FilePath == null) {
			trainingAgendaParticipantUfImpl.setFilePath("");
		}
		else {
			trainingAgendaParticipantUfImpl.setFilePath(FilePath);
		}

		trainingAgendaParticipantUfImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			trainingAgendaParticipantUfImpl.setCreatedDate(null);
		}
		else {
			trainingAgendaParticipantUfImpl.setCreatedDate(
				new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			trainingAgendaParticipantUfImpl.setCreatedBy("");
		}
		else {
			trainingAgendaParticipantUfImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			trainingAgendaParticipantUfImpl.setModifiedDate(null);
		}
		else {
			trainingAgendaParticipantUfImpl.setModifiedDate(
				new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			trainingAgendaParticipantUfImpl.setModifiedBy("");
		}
		else {
			trainingAgendaParticipantUfImpl.setModifiedBy(ModifiedBy);
		}

		trainingAgendaParticipantUfImpl.resetOriginalValues();

		return trainingAgendaParticipantUfImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		TrainingAgendaId = objectInput.readInt();

		DealerId = objectInput.readInt();

		FileId = objectInput.readLong();
		FileName = objectInput.readUTF();
		FilePath = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		objectOutput.writeInt(TrainingAgendaId);

		objectOutput.writeInt(DealerId);

		objectOutput.writeLong(FileId);

		if (FileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(FileName);
		}

		if (FilePath == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(FilePath);
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
	public int TrainingAgendaId;
	public int DealerId;
	public long FileId;
	public String FileName;
	public String FilePath;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}