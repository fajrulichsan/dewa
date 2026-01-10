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

import com.astra.dewa.model.MasterApprovalDetailJournal;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MasterApprovalDetailJournal in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MasterApprovalDetailJournalCacheModel
	implements CacheModel<MasterApprovalDetailJournal>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MasterApprovalDetailJournalCacheModel)) {
			return false;
		}

		MasterApprovalDetailJournalCacheModel
			masterApprovalDetailJournalCacheModel =
				(MasterApprovalDetailJournalCacheModel)object;

		if (Id == masterApprovalDetailJournalCacheModel.Id) {
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
		sb.append(", MasterApprovalId=");
		sb.append(MasterApprovalId);
		sb.append(", MasterApprovalJournalId=");
		sb.append(MasterApprovalJournalId);
		sb.append(", MasterApprovalDetailId=");
		sb.append(MasterApprovalDetailId);
		sb.append(", UserId=");
		sb.append(UserId);
		sb.append(", ApprovalLevel=");
		sb.append(ApprovalLevel);
		sb.append(", IsDefault=");
		sb.append(IsDefault);
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
	public MasterApprovalDetailJournal toEntityModel() {
		MasterApprovalDetailJournalImpl masterApprovalDetailJournalImpl =
			new MasterApprovalDetailJournalImpl();

		masterApprovalDetailJournalImpl.setId(Id);
		masterApprovalDetailJournalImpl.setMasterApprovalId(MasterApprovalId);
		masterApprovalDetailJournalImpl.setMasterApprovalJournalId(
			MasterApprovalJournalId);
		masterApprovalDetailJournalImpl.setMasterApprovalDetailId(
			MasterApprovalDetailId);
		masterApprovalDetailJournalImpl.setUserId(UserId);
		masterApprovalDetailJournalImpl.setApprovalLevel(ApprovalLevel);
		masterApprovalDetailJournalImpl.setIsDefault(IsDefault);
		masterApprovalDetailJournalImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			masterApprovalDetailJournalImpl.setCreatedDate(null);
		}
		else {
			masterApprovalDetailJournalImpl.setCreatedDate(
				new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			masterApprovalDetailJournalImpl.setCreatedBy("");
		}
		else {
			masterApprovalDetailJournalImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			masterApprovalDetailJournalImpl.setModifiedDate(null);
		}
		else {
			masterApprovalDetailJournalImpl.setModifiedDate(
				new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			masterApprovalDetailJournalImpl.setModifiedBy("");
		}
		else {
			masterApprovalDetailJournalImpl.setModifiedBy(ModifiedBy);
		}

		masterApprovalDetailJournalImpl.resetOriginalValues();

		return masterApprovalDetailJournalImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		MasterApprovalId = objectInput.readInt();

		MasterApprovalJournalId = objectInput.readInt();

		MasterApprovalDetailId = objectInput.readInt();

		UserId = objectInput.readLong();

		ApprovalLevel = objectInput.readInt();

		IsDefault = objectInput.readBoolean();

		RowStatus = objectInput.readBoolean();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		objectOutput.writeInt(MasterApprovalId);

		objectOutput.writeInt(MasterApprovalJournalId);

		objectOutput.writeInt(MasterApprovalDetailId);

		objectOutput.writeLong(UserId);

		objectOutput.writeInt(ApprovalLevel);

		objectOutput.writeBoolean(IsDefault);

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
	public int MasterApprovalId;
	public int MasterApprovalJournalId;
	public int MasterApprovalDetailId;
	public long UserId;
	public int ApprovalLevel;
	public boolean IsDefault;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}