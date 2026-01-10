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

import com.astra.dewa.model.MasterApprovalDetail;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MasterApprovalDetail in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MasterApprovalDetailCacheModel
	implements CacheModel<MasterApprovalDetail>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MasterApprovalDetailCacheModel)) {
			return false;
		}

		MasterApprovalDetailCacheModel masterApprovalDetailCacheModel =
			(MasterApprovalDetailCacheModel)object;

		if (Id == masterApprovalDetailCacheModel.Id) {
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
		StringBundler sb = new StringBundler(21);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", MasterApprovalId=");
		sb.append(MasterApprovalId);
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
	public MasterApprovalDetail toEntityModel() {
		MasterApprovalDetailImpl masterApprovalDetailImpl =
			new MasterApprovalDetailImpl();

		masterApprovalDetailImpl.setId(Id);
		masterApprovalDetailImpl.setMasterApprovalId(MasterApprovalId);
		masterApprovalDetailImpl.setUserId(UserId);
		masterApprovalDetailImpl.setApprovalLevel(ApprovalLevel);
		masterApprovalDetailImpl.setIsDefault(IsDefault);
		masterApprovalDetailImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			masterApprovalDetailImpl.setCreatedDate(null);
		}
		else {
			masterApprovalDetailImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			masterApprovalDetailImpl.setCreatedBy("");
		}
		else {
			masterApprovalDetailImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			masterApprovalDetailImpl.setModifiedDate(null);
		}
		else {
			masterApprovalDetailImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			masterApprovalDetailImpl.setModifiedBy("");
		}
		else {
			masterApprovalDetailImpl.setModifiedBy(ModifiedBy);
		}

		masterApprovalDetailImpl.resetOriginalValues();

		return masterApprovalDetailImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		MasterApprovalId = objectInput.readInt();

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
	public long UserId;
	public int ApprovalLevel;
	public boolean IsDefault;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}