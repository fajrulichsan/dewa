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

import com.astra.dewa.model.Ticket;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Ticket in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TicketCacheModel implements CacheModel<Ticket>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TicketCacheModel)) {
			return false;
		}

		TicketCacheModel ticketCacheModel = (TicketCacheModel)object;

		if (Id.equals(ticketCacheModel.Id)) {
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
		StringBundler sb = new StringBundler(37);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", DealerCode=");
		sb.append(DealerCode);
		sb.append(", TicketDate=");
		sb.append(TicketDate);
		sb.append(", TicketNumber=");
		sb.append(TicketNumber);
		sb.append(", RequestName=");
		sb.append(RequestName);
		sb.append(", FirstApprover=");
		sb.append(FirstApprover);
		sb.append(", Email=");
		sb.append(Email);
		sb.append(", EmailCc=");
		sb.append(EmailCc);
		sb.append(", Phone=");
		sb.append(Phone);
		sb.append(", RequestCategory=");
		sb.append(RequestCategory);
		sb.append(", RequestDescription=");
		sb.append(RequestDescription);
		sb.append(", BussinesBenefit=");
		sb.append(BussinesBenefit);
		sb.append(", NameFile=");
		sb.append(NameFile);
		sb.append(", PathFile=");
		sb.append(PathFile);
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
	public Ticket toEntityModel() {
		TicketImpl ticketImpl = new TicketImpl();

		if (Id == null) {
			ticketImpl.setId("");
		}
		else {
			ticketImpl.setId(Id);
		}

		if (DealerCode == null) {
			ticketImpl.setDealerCode("");
		}
		else {
			ticketImpl.setDealerCode(DealerCode);
		}

		if (TicketDate == Long.MIN_VALUE) {
			ticketImpl.setTicketDate(null);
		}
		else {
			ticketImpl.setTicketDate(new Date(TicketDate));
		}

		if (TicketNumber == null) {
			ticketImpl.setTicketNumber("");
		}
		else {
			ticketImpl.setTicketNumber(TicketNumber);
		}

		if (RequestName == null) {
			ticketImpl.setRequestName("");
		}
		else {
			ticketImpl.setRequestName(RequestName);
		}

		if (FirstApprover == null) {
			ticketImpl.setFirstApprover("");
		}
		else {
			ticketImpl.setFirstApprover(FirstApprover);
		}

		if (Email == null) {
			ticketImpl.setEmail("");
		}
		else {
			ticketImpl.setEmail(Email);
		}

		if (EmailCc == null) {
			ticketImpl.setEmailCc("");
		}
		else {
			ticketImpl.setEmailCc(EmailCc);
		}

		if (Phone == null) {
			ticketImpl.setPhone("");
		}
		else {
			ticketImpl.setPhone(Phone);
		}

		if (RequestCategory == null) {
			ticketImpl.setRequestCategory("");
		}
		else {
			ticketImpl.setRequestCategory(RequestCategory);
		}

		if (RequestDescription == null) {
			ticketImpl.setRequestDescription("");
		}
		else {
			ticketImpl.setRequestDescription(RequestDescription);
		}

		if (BussinesBenefit == null) {
			ticketImpl.setBussinesBenefit("");
		}
		else {
			ticketImpl.setBussinesBenefit(BussinesBenefit);
		}

		if (NameFile == null) {
			ticketImpl.setNameFile("");
		}
		else {
			ticketImpl.setNameFile(NameFile);
		}

		if (PathFile == null) {
			ticketImpl.setPathFile("");
		}
		else {
			ticketImpl.setPathFile(PathFile);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			ticketImpl.setCreatedDate(null);
		}
		else {
			ticketImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			ticketImpl.setCreatedBy("");
		}
		else {
			ticketImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			ticketImpl.setModifiedDate(null);
		}
		else {
			ticketImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			ticketImpl.setModifiedBy("");
		}
		else {
			ticketImpl.setModifiedBy(ModifiedBy);
		}

		ticketImpl.resetOriginalValues();

		return ticketImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readUTF();
		DealerCode = objectInput.readUTF();
		TicketDate = objectInput.readLong();
		TicketNumber = objectInput.readUTF();
		RequestName = objectInput.readUTF();
		FirstApprover = objectInput.readUTF();
		Email = objectInput.readUTF();
		EmailCc = objectInput.readUTF();
		Phone = objectInput.readUTF();
		RequestCategory = objectInput.readUTF();
		RequestDescription = objectInput.readUTF();
		BussinesBenefit = objectInput.readUTF();
		NameFile = objectInput.readUTF();
		PathFile = objectInput.readUTF();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (Id == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Id);
		}

		if (DealerCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(DealerCode);
		}

		objectOutput.writeLong(TicketDate);

		if (TicketNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(TicketNumber);
		}

		if (RequestName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(RequestName);
		}

		if (FirstApprover == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(FirstApprover);
		}

		if (Email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Email);
		}

		if (EmailCc == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(EmailCc);
		}

		if (Phone == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Phone);
		}

		if (RequestCategory == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(RequestCategory);
		}

		if (RequestDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(RequestDescription);
		}

		if (BussinesBenefit == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(BussinesBenefit);
		}

		if (NameFile == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(NameFile);
		}

		if (PathFile == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(PathFile);
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
	}

	public String Id;
	public String DealerCode;
	public long TicketDate;
	public String TicketNumber;
	public String RequestName;
	public String FirstApprover;
	public String Email;
	public String EmailCc;
	public String Phone;
	public String RequestCategory;
	public String RequestDescription;
	public String BussinesBenefit;
	public String NameFile;
	public String PathFile;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}