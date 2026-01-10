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

import com.astra.dewa.model.ApplicationHeaderJournal;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ApplicationHeaderJournal in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApplicationHeaderJournalCacheModel
	implements CacheModel<ApplicationHeaderJournal>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ApplicationHeaderJournalCacheModel)) {
			return false;
		}

		ApplicationHeaderJournalCacheModel applicationHeaderJournalCacheModel =
			(ApplicationHeaderJournalCacheModel)object;

		if (Id == applicationHeaderJournalCacheModel.Id) {
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
		StringBundler sb = new StringBundler(59);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", ApplicationHeaderId=");
		sb.append(ApplicationHeaderId);
		sb.append(", ApplicationId=");
		sb.append(ApplicationId);
		sb.append(", ApplicationHeaderStatusId=");
		sb.append(ApplicationHeaderStatusId);
		sb.append(", ApplicationCategoryId=");
		sb.append(ApplicationCategoryId);
		sb.append(", DealerId=");
		sb.append(DealerId);
		sb.append(", TicketNo=");
		sb.append(TicketNo);
		sb.append(", TicketCode=");
		sb.append(TicketCode);
		sb.append(", ReqDate=");
		sb.append(ReqDate);
		sb.append(", ReqYear=");
		sb.append(ReqYear);
		sb.append(", ReqMonth=");
		sb.append(ReqMonth);
		sb.append(", ReqScreenName=");
		sb.append(ReqScreenName);
		sb.append(", ReqName=");
		sb.append(ReqName);
		sb.append(", ReqEmail=");
		sb.append(ReqEmail);
		sb.append(", ReqCCEmail=");
		sb.append(ReqCCEmail);
		sb.append(", ReqPhone=");
		sb.append(ReqPhone);
		sb.append(", NominalPengajuan=");
		sb.append(NominalPengajuan);
		sb.append(", ReqDesc=");
		sb.append(ReqDesc);
		sb.append(", BusinessBenefit=");
		sb.append(BusinessBenefit);
		sb.append(", FileId=");
		sb.append(FileId);
		sb.append(", FileName=");
		sb.append(FileName);
		sb.append(", FileUrl=");
		sb.append(FileUrl);
		sb.append(", Notes=");
		sb.append(Notes);
		sb.append(", NotesHistory=");
		sb.append(NotesHistory);
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
	public ApplicationHeaderJournal toEntityModel() {
		ApplicationHeaderJournalImpl applicationHeaderJournalImpl =
			new ApplicationHeaderJournalImpl();

		applicationHeaderJournalImpl.setId(Id);
		applicationHeaderJournalImpl.setApplicationHeaderId(
			ApplicationHeaderId);
		applicationHeaderJournalImpl.setApplicationId(ApplicationId);
		applicationHeaderJournalImpl.setApplicationHeaderStatusId(
			ApplicationHeaderStatusId);
		applicationHeaderJournalImpl.setApplicationCategoryId(
			ApplicationCategoryId);
		applicationHeaderJournalImpl.setDealerId(DealerId);

		if (TicketNo == null) {
			applicationHeaderJournalImpl.setTicketNo("");
		}
		else {
			applicationHeaderJournalImpl.setTicketNo(TicketNo);
		}

		if (TicketCode == null) {
			applicationHeaderJournalImpl.setTicketCode("");
		}
		else {
			applicationHeaderJournalImpl.setTicketCode(TicketCode);
		}

		if (ReqDate == Long.MIN_VALUE) {
			applicationHeaderJournalImpl.setReqDate(null);
		}
		else {
			applicationHeaderJournalImpl.setReqDate(new Date(ReqDate));
		}

		applicationHeaderJournalImpl.setReqYear(ReqYear);
		applicationHeaderJournalImpl.setReqMonth(ReqMonth);

		if (ReqScreenName == null) {
			applicationHeaderJournalImpl.setReqScreenName("");
		}
		else {
			applicationHeaderJournalImpl.setReqScreenName(ReqScreenName);
		}

		if (ReqName == null) {
			applicationHeaderJournalImpl.setReqName("");
		}
		else {
			applicationHeaderJournalImpl.setReqName(ReqName);
		}

		if (ReqEmail == null) {
			applicationHeaderJournalImpl.setReqEmail("");
		}
		else {
			applicationHeaderJournalImpl.setReqEmail(ReqEmail);
		}

		if (ReqCCEmail == null) {
			applicationHeaderJournalImpl.setReqCCEmail("");
		}
		else {
			applicationHeaderJournalImpl.setReqCCEmail(ReqCCEmail);
		}

		if (ReqPhone == null) {
			applicationHeaderJournalImpl.setReqPhone("");
		}
		else {
			applicationHeaderJournalImpl.setReqPhone(ReqPhone);
		}

		applicationHeaderJournalImpl.setNominalPengajuan(NominalPengajuan);

		if (ReqDesc == null) {
			applicationHeaderJournalImpl.setReqDesc("");
		}
		else {
			applicationHeaderJournalImpl.setReqDesc(ReqDesc);
		}

		if (BusinessBenefit == null) {
			applicationHeaderJournalImpl.setBusinessBenefit("");
		}
		else {
			applicationHeaderJournalImpl.setBusinessBenefit(BusinessBenefit);
		}

		applicationHeaderJournalImpl.setFileId(FileId);

		if (FileName == null) {
			applicationHeaderJournalImpl.setFileName("");
		}
		else {
			applicationHeaderJournalImpl.setFileName(FileName);
		}

		if (FileUrl == null) {
			applicationHeaderJournalImpl.setFileUrl("");
		}
		else {
			applicationHeaderJournalImpl.setFileUrl(FileUrl);
		}

		if (Notes == null) {
			applicationHeaderJournalImpl.setNotes("");
		}
		else {
			applicationHeaderJournalImpl.setNotes(Notes);
		}

		if (NotesHistory == null) {
			applicationHeaderJournalImpl.setNotesHistory("");
		}
		else {
			applicationHeaderJournalImpl.setNotesHistory(NotesHistory);
		}

		applicationHeaderJournalImpl.setRowStatus(RowStatus);

		if (CreatedBy == null) {
			applicationHeaderJournalImpl.setCreatedBy("");
		}
		else {
			applicationHeaderJournalImpl.setCreatedBy(CreatedBy);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			applicationHeaderJournalImpl.setCreatedDate(null);
		}
		else {
			applicationHeaderJournalImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (ModifiedBy == null) {
			applicationHeaderJournalImpl.setModifiedBy("");
		}
		else {
			applicationHeaderJournalImpl.setModifiedBy(ModifiedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			applicationHeaderJournalImpl.setModifiedDate(null);
		}
		else {
			applicationHeaderJournalImpl.setModifiedDate(
				new Date(ModifiedDate));
		}

		applicationHeaderJournalImpl.resetOriginalValues();

		return applicationHeaderJournalImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		ApplicationHeaderId = objectInput.readInt();

		ApplicationId = objectInput.readInt();

		ApplicationHeaderStatusId = objectInput.readInt();

		ApplicationCategoryId = objectInput.readInt();

		DealerId = objectInput.readInt();
		TicketNo = objectInput.readUTF();
		TicketCode = objectInput.readUTF();
		ReqDate = objectInput.readLong();

		ReqYear = objectInput.readInt();

		ReqMonth = objectInput.readInt();
		ReqScreenName = objectInput.readUTF();
		ReqName = objectInput.readUTF();
		ReqEmail = objectInput.readUTF();
		ReqCCEmail = objectInput.readUTF();
		ReqPhone = objectInput.readUTF();

		NominalPengajuan = objectInput.readInt();
		ReqDesc = objectInput.readUTF();
		BusinessBenefit = objectInput.readUTF();

		FileId = objectInput.readLong();
		FileName = objectInput.readUTF();
		FileUrl = objectInput.readUTF();
		Notes = objectInput.readUTF();
		NotesHistory = objectInput.readUTF();

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

		objectOutput.writeInt(ApplicationId);

		objectOutput.writeInt(ApplicationHeaderStatusId);

		objectOutput.writeInt(ApplicationCategoryId);

		objectOutput.writeInt(DealerId);

		if (TicketNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(TicketNo);
		}

		if (TicketCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(TicketCode);
		}

		objectOutput.writeLong(ReqDate);

		objectOutput.writeInt(ReqYear);

		objectOutput.writeInt(ReqMonth);

		if (ReqScreenName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ReqScreenName);
		}

		if (ReqName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ReqName);
		}

		if (ReqEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ReqEmail);
		}

		if (ReqCCEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ReqCCEmail);
		}

		if (ReqPhone == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ReqPhone);
		}

		objectOutput.writeInt(NominalPengajuan);

		if (ReqDesc == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ReqDesc);
		}

		if (BusinessBenefit == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(BusinessBenefit);
		}

		objectOutput.writeLong(FileId);

		if (FileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(FileName);
		}

		if (FileUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(FileUrl);
		}

		if (Notes == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Notes);
		}

		if (NotesHistory == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(NotesHistory);
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
	public int ApplicationId;
	public int ApplicationHeaderStatusId;
	public int ApplicationCategoryId;
	public int DealerId;
	public String TicketNo;
	public String TicketCode;
	public long ReqDate;
	public int ReqYear;
	public int ReqMonth;
	public String ReqScreenName;
	public String ReqName;
	public String ReqEmail;
	public String ReqCCEmail;
	public String ReqPhone;
	public int NominalPengajuan;
	public String ReqDesc;
	public String BusinessBenefit;
	public long FileId;
	public String FileName;
	public String FileUrl;
	public String Notes;
	public String NotesHistory;
	public boolean RowStatus;
	public String CreatedBy;
	public long CreatedDate;
	public String ModifiedBy;
	public long ModifiedDate;

}