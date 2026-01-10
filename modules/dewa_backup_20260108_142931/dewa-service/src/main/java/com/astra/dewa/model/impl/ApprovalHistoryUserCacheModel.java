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

import com.astra.dewa.model.ApprovalHistoryUser;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ApprovalHistoryUser in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApprovalHistoryUserCacheModel
	implements CacheModel<ApprovalHistoryUser>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ApprovalHistoryUserCacheModel)) {
			return false;
		}

		ApprovalHistoryUserCacheModel approvalHistoryUserCacheModel =
			(ApprovalHistoryUserCacheModel)object;

		if (Id == approvalHistoryUserCacheModel.Id) {
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
		StringBundler sb = new StringBundler(29);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", ApprovalHeaderUserId=");
		sb.append(ApprovalHeaderUserId);
		sb.append(", ApplicationAssignStatusId=");
		sb.append(ApplicationAssignStatusId);
		sb.append(", ApproverUserId=");
		sb.append(ApproverUserId);
		sb.append(", DealerId=");
		sb.append(DealerId);
		sb.append(", CabangId=");
		sb.append(CabangId);
		sb.append(", RequesterName=");
		sb.append(RequesterName);
		sb.append(", RequesterEmail=");
		sb.append(RequesterEmail);
		sb.append(", RejectReason=");
		sb.append(RejectReason);
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
	public ApprovalHistoryUser toEntityModel() {
		ApprovalHistoryUserImpl approvalHistoryUserImpl =
			new ApprovalHistoryUserImpl();

		approvalHistoryUserImpl.setId(Id);
		approvalHistoryUserImpl.setApprovalHeaderUserId(ApprovalHeaderUserId);
		approvalHistoryUserImpl.setApplicationAssignStatusId(
			ApplicationAssignStatusId);
		approvalHistoryUserImpl.setApproverUserId(ApproverUserId);
		approvalHistoryUserImpl.setDealerId(DealerId);
		approvalHistoryUserImpl.setCabangId(CabangId);

		if (RequesterName == null) {
			approvalHistoryUserImpl.setRequesterName("");
		}
		else {
			approvalHistoryUserImpl.setRequesterName(RequesterName);
		}

		if (RequesterEmail == null) {
			approvalHistoryUserImpl.setRequesterEmail("");
		}
		else {
			approvalHistoryUserImpl.setRequesterEmail(RequesterEmail);
		}

		if (RejectReason == null) {
			approvalHistoryUserImpl.setRejectReason("");
		}
		else {
			approvalHistoryUserImpl.setRejectReason(RejectReason);
		}

		approvalHistoryUserImpl.setRowStatus(RowStatus);

		if (CreatedBy == null) {
			approvalHistoryUserImpl.setCreatedBy("");
		}
		else {
			approvalHistoryUserImpl.setCreatedBy(CreatedBy);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			approvalHistoryUserImpl.setCreatedDate(null);
		}
		else {
			approvalHistoryUserImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (ModifiedBy == null) {
			approvalHistoryUserImpl.setModifiedBy("");
		}
		else {
			approvalHistoryUserImpl.setModifiedBy(ModifiedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			approvalHistoryUserImpl.setModifiedDate(null);
		}
		else {
			approvalHistoryUserImpl.setModifiedDate(new Date(ModifiedDate));
		}

		approvalHistoryUserImpl.resetOriginalValues();

		return approvalHistoryUserImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		ApprovalHeaderUserId = objectInput.readInt();

		ApplicationAssignStatusId = objectInput.readInt();

		ApproverUserId = objectInput.readLong();

		DealerId = objectInput.readInt();

		CabangId = objectInput.readInt();
		RequesterName = objectInput.readUTF();
		RequesterEmail = objectInput.readUTF();
		RejectReason = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
		CreatedBy = objectInput.readUTF();
		CreatedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		objectOutput.writeInt(ApprovalHeaderUserId);

		objectOutput.writeInt(ApplicationAssignStatusId);

		objectOutput.writeLong(ApproverUserId);

		objectOutput.writeInt(DealerId);

		objectOutput.writeInt(CabangId);

		if (RequesterName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(RequesterName);
		}

		if (RequesterEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(RequesterEmail);
		}

		if (RejectReason == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(RejectReason);
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
	public int ApprovalHeaderUserId;
	public int ApplicationAssignStatusId;
	public long ApproverUserId;
	public int DealerId;
	public int CabangId;
	public String RequesterName;
	public String RequesterEmail;
	public String RejectReason;
	public boolean RowStatus;
	public String CreatedBy;
	public long CreatedDate;
	public String ModifiedBy;
	public long ModifiedDate;

}