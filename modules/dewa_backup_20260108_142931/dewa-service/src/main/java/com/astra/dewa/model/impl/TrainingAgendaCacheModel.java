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

import com.astra.dewa.model.TrainingAgenda;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TrainingAgenda in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TrainingAgendaCacheModel
	implements CacheModel<TrainingAgenda>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TrainingAgendaCacheModel)) {
			return false;
		}

		TrainingAgendaCacheModel trainingAgendaCacheModel =
			(TrainingAgendaCacheModel)object;

		if (Id == trainingAgendaCacheModel.Id) {
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
		StringBundler sb = new StringBundler(43);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", JenisAgenda=");
		sb.append(JenisAgenda);
		sb.append(", StatusAgenda=");
		sb.append(StatusAgenda);
		sb.append(", Judul=");
		sb.append(Judul);
		sb.append(", Location=");
		sb.append(Location);
		sb.append(", LinkMeeting=");
		sb.append(LinkMeeting);
		sb.append(", Deskripsi=");
		sb.append(Deskripsi);
		sb.append(", StartDate=");
		sb.append(StartDate);
		sb.append(", StartDateHours=");
		sb.append(StartDateHours);
		sb.append(", EndDate=");
		sb.append(EndDate);
		sb.append(", EndDateHours=");
		sb.append(EndDateHours);
		sb.append(", RegistrationLimitDate=");
		sb.append(RegistrationLimitDate);
		sb.append(", RegistrationLimitDateHours=");
		sb.append(RegistrationLimitDateHours);
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
	public TrainingAgenda toEntityModel() {
		TrainingAgendaImpl trainingAgendaImpl = new TrainingAgendaImpl();

		trainingAgendaImpl.setId(Id);
		trainingAgendaImpl.setJenisAgenda(JenisAgenda);
		trainingAgendaImpl.setStatusAgenda(StatusAgenda);

		if (Judul == null) {
			trainingAgendaImpl.setJudul("");
		}
		else {
			trainingAgendaImpl.setJudul(Judul);
		}

		if (Location == null) {
			trainingAgendaImpl.setLocation("");
		}
		else {
			trainingAgendaImpl.setLocation(Location);
		}

		if (LinkMeeting == null) {
			trainingAgendaImpl.setLinkMeeting("");
		}
		else {
			trainingAgendaImpl.setLinkMeeting(LinkMeeting);
		}

		if (Deskripsi == null) {
			trainingAgendaImpl.setDeskripsi("");
		}
		else {
			trainingAgendaImpl.setDeskripsi(Deskripsi);
		}

		if (StartDate == Long.MIN_VALUE) {
			trainingAgendaImpl.setStartDate(null);
		}
		else {
			trainingAgendaImpl.setStartDate(new Date(StartDate));
		}

		if (StartDateHours == null) {
			trainingAgendaImpl.setStartDateHours("");
		}
		else {
			trainingAgendaImpl.setStartDateHours(StartDateHours);
		}

		if (EndDate == Long.MIN_VALUE) {
			trainingAgendaImpl.setEndDate(null);
		}
		else {
			trainingAgendaImpl.setEndDate(new Date(EndDate));
		}

		if (EndDateHours == null) {
			trainingAgendaImpl.setEndDateHours("");
		}
		else {
			trainingAgendaImpl.setEndDateHours(EndDateHours);
		}

		if (RegistrationLimitDate == Long.MIN_VALUE) {
			trainingAgendaImpl.setRegistrationLimitDate(null);
		}
		else {
			trainingAgendaImpl.setRegistrationLimitDate(
				new Date(RegistrationLimitDate));
		}

		if (RegistrationLimitDateHours == null) {
			trainingAgendaImpl.setRegistrationLimitDateHours("");
		}
		else {
			trainingAgendaImpl.setRegistrationLimitDateHours(
				RegistrationLimitDateHours);
		}

		trainingAgendaImpl.setImageId(ImageId);

		if (ImageName == null) {
			trainingAgendaImpl.setImageName("");
		}
		else {
			trainingAgendaImpl.setImageName(ImageName);
		}

		if (ImagePath == null) {
			trainingAgendaImpl.setImagePath("");
		}
		else {
			trainingAgendaImpl.setImagePath(ImagePath);
		}

		trainingAgendaImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			trainingAgendaImpl.setCreatedDate(null);
		}
		else {
			trainingAgendaImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			trainingAgendaImpl.setCreatedBy("");
		}
		else {
			trainingAgendaImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			trainingAgendaImpl.setModifiedDate(null);
		}
		else {
			trainingAgendaImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			trainingAgendaImpl.setModifiedBy("");
		}
		else {
			trainingAgendaImpl.setModifiedBy(ModifiedBy);
		}

		trainingAgendaImpl.resetOriginalValues();

		return trainingAgendaImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		JenisAgenda = objectInput.readInt();

		StatusAgenda = objectInput.readInt();
		Judul = objectInput.readUTF();
		Location = objectInput.readUTF();
		LinkMeeting = objectInput.readUTF();
		Deskripsi = objectInput.readUTF();
		StartDate = objectInput.readLong();
		StartDateHours = objectInput.readUTF();
		EndDate = objectInput.readLong();
		EndDateHours = objectInput.readUTF();
		RegistrationLimitDate = objectInput.readLong();
		RegistrationLimitDateHours = objectInput.readUTF();

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

		objectOutput.writeInt(JenisAgenda);

		objectOutput.writeInt(StatusAgenda);

		if (Judul == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Judul);
		}

		if (Location == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Location);
		}

		if (LinkMeeting == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(LinkMeeting);
		}

		if (Deskripsi == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Deskripsi);
		}

		objectOutput.writeLong(StartDate);

		if (StartDateHours == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(StartDateHours);
		}

		objectOutput.writeLong(EndDate);

		if (EndDateHours == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(EndDateHours);
		}

		objectOutput.writeLong(RegistrationLimitDate);

		if (RegistrationLimitDateHours == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(RegistrationLimitDateHours);
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
	public int JenisAgenda;
	public int StatusAgenda;
	public String Judul;
	public String Location;
	public String LinkMeeting;
	public String Deskripsi;
	public long StartDate;
	public String StartDateHours;
	public long EndDate;
	public String EndDateHours;
	public long RegistrationLimitDate;
	public String RegistrationLimitDateHours;
	public long ImageId;
	public String ImageName;
	public String ImagePath;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}