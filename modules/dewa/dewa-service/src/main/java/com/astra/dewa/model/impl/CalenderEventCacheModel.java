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

import com.astra.dewa.model.CalenderEvent;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CalenderEvent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CalenderEventCacheModel
	implements CacheModel<CalenderEvent>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CalenderEventCacheModel)) {
			return false;
		}

		CalenderEventCacheModel calenderEventCacheModel =
			(CalenderEventCacheModel)object;

		if (Id == calenderEventCacheModel.Id) {
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
		sb.append(", JenisAcara=");
		sb.append(JenisAcara);
		sb.append(", StatusAcara=");
		sb.append(StatusAcara);
		sb.append(", Judul=");
		sb.append(Judul);
		sb.append(", Location=");
		sb.append(Location);
		sb.append(", LinkMeeting=");
		sb.append(LinkMeeting);
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
		sb.append(", Deskripsi=");
		sb.append(Deskripsi);
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
	public CalenderEvent toEntityModel() {
		CalenderEventImpl calenderEventImpl = new CalenderEventImpl();

		calenderEventImpl.setId(Id);
		calenderEventImpl.setJenisAcara(JenisAcara);
		calenderEventImpl.setStatusAcara(StatusAcara);

		if (Judul == null) {
			calenderEventImpl.setJudul("");
		}
		else {
			calenderEventImpl.setJudul(Judul);
		}

		if (Location == null) {
			calenderEventImpl.setLocation("");
		}
		else {
			calenderEventImpl.setLocation(Location);
		}

		if (LinkMeeting == null) {
			calenderEventImpl.setLinkMeeting("");
		}
		else {
			calenderEventImpl.setLinkMeeting(LinkMeeting);
		}

		if (StartDate == Long.MIN_VALUE) {
			calenderEventImpl.setStartDate(null);
		}
		else {
			calenderEventImpl.setStartDate(new Date(StartDate));
		}

		if (StartDateHours == null) {
			calenderEventImpl.setStartDateHours("");
		}
		else {
			calenderEventImpl.setStartDateHours(StartDateHours);
		}

		if (EndDate == Long.MIN_VALUE) {
			calenderEventImpl.setEndDate(null);
		}
		else {
			calenderEventImpl.setEndDate(new Date(EndDate));
		}

		if (EndDateHours == null) {
			calenderEventImpl.setEndDateHours("");
		}
		else {
			calenderEventImpl.setEndDateHours(EndDateHours);
		}

		if (RegistrationLimitDate == Long.MIN_VALUE) {
			calenderEventImpl.setRegistrationLimitDate(null);
		}
		else {
			calenderEventImpl.setRegistrationLimitDate(
				new Date(RegistrationLimitDate));
		}

		if (RegistrationLimitDateHours == null) {
			calenderEventImpl.setRegistrationLimitDateHours("");
		}
		else {
			calenderEventImpl.setRegistrationLimitDateHours(
				RegistrationLimitDateHours);
		}

		if (Deskripsi == null) {
			calenderEventImpl.setDeskripsi("");
		}
		else {
			calenderEventImpl.setDeskripsi(Deskripsi);
		}

		calenderEventImpl.setImageId(ImageId);

		if (ImageName == null) {
			calenderEventImpl.setImageName("");
		}
		else {
			calenderEventImpl.setImageName(ImageName);
		}

		if (ImagePath == null) {
			calenderEventImpl.setImagePath("");
		}
		else {
			calenderEventImpl.setImagePath(ImagePath);
		}

		calenderEventImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			calenderEventImpl.setCreatedDate(null);
		}
		else {
			calenderEventImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			calenderEventImpl.setCreatedBy("");
		}
		else {
			calenderEventImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			calenderEventImpl.setModifiedDate(null);
		}
		else {
			calenderEventImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			calenderEventImpl.setModifiedBy("");
		}
		else {
			calenderEventImpl.setModifiedBy(ModifiedBy);
		}

		calenderEventImpl.resetOriginalValues();

		return calenderEventImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		JenisAcara = objectInput.readInt();

		StatusAcara = objectInput.readInt();
		Judul = objectInput.readUTF();
		Location = objectInput.readUTF();
		LinkMeeting = objectInput.readUTF();
		StartDate = objectInput.readLong();
		StartDateHours = objectInput.readUTF();
		EndDate = objectInput.readLong();
		EndDateHours = objectInput.readUTF();
		RegistrationLimitDate = objectInput.readLong();
		RegistrationLimitDateHours = objectInput.readUTF();
		Deskripsi = objectInput.readUTF();

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

		objectOutput.writeInt(JenisAcara);

		objectOutput.writeInt(StatusAcara);

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

		if (Deskripsi == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Deskripsi);
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
	public int JenisAcara;
	public int StatusAcara;
	public String Judul;
	public String Location;
	public String LinkMeeting;
	public long StartDate;
	public String StartDateHours;
	public long EndDate;
	public String EndDateHours;
	public long RegistrationLimitDate;
	public String RegistrationLimitDateHours;
	public String Deskripsi;
	public long ImageId;
	public String ImageName;
	public String ImagePath;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}