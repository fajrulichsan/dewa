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

import com.astra.dewa.model.CalenderEventParticipant;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CalenderEventParticipant in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CalenderEventParticipantCacheModel
	implements CacheModel<CalenderEventParticipant>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CalenderEventParticipantCacheModel)) {
			return false;
		}

		CalenderEventParticipantCacheModel calenderEventParticipantCacheModel =
			(CalenderEventParticipantCacheModel)object;

		if (Id == calenderEventParticipantCacheModel.Id) {
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
		sb.append(", CalenderEventId=");
		sb.append(CalenderEventId);
		sb.append(", DealerId=");
		sb.append(DealerId);
		sb.append(", FullName=");
		sb.append(FullName);
		sb.append(", Email=");
		sb.append(Email);
		sb.append(", Phone=");
		sb.append(Phone);
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
	public CalenderEventParticipant toEntityModel() {
		CalenderEventParticipantImpl calenderEventParticipantImpl =
			new CalenderEventParticipantImpl();

		calenderEventParticipantImpl.setId(Id);
		calenderEventParticipantImpl.setCalenderEventId(CalenderEventId);
		calenderEventParticipantImpl.setDealerId(DealerId);

		if (FullName == null) {
			calenderEventParticipantImpl.setFullName("");
		}
		else {
			calenderEventParticipantImpl.setFullName(FullName);
		}

		if (Email == null) {
			calenderEventParticipantImpl.setEmail("");
		}
		else {
			calenderEventParticipantImpl.setEmail(Email);
		}

		if (Phone == null) {
			calenderEventParticipantImpl.setPhone("");
		}
		else {
			calenderEventParticipantImpl.setPhone(Phone);
		}

		calenderEventParticipantImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			calenderEventParticipantImpl.setCreatedDate(null);
		}
		else {
			calenderEventParticipantImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			calenderEventParticipantImpl.setCreatedBy("");
		}
		else {
			calenderEventParticipantImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			calenderEventParticipantImpl.setModifiedDate(null);
		}
		else {
			calenderEventParticipantImpl.setModifiedDate(
				new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			calenderEventParticipantImpl.setModifiedBy("");
		}
		else {
			calenderEventParticipantImpl.setModifiedBy(ModifiedBy);
		}

		calenderEventParticipantImpl.resetOriginalValues();

		return calenderEventParticipantImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		CalenderEventId = objectInput.readInt();

		DealerId = objectInput.readInt();
		FullName = objectInput.readUTF();
		Email = objectInput.readUTF();
		Phone = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		objectOutput.writeInt(CalenderEventId);

		objectOutput.writeInt(DealerId);

		if (FullName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(FullName);
		}

		if (Email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Email);
		}

		if (Phone == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Phone);
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
	public int CalenderEventId;
	public int DealerId;
	public String FullName;
	public String Email;
	public String Phone;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}