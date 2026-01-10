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

import com.astra.dewa.model.ApprovalDetailUser;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ApprovalDetailUser in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApprovalDetailUserCacheModel
	implements CacheModel<ApprovalDetailUser>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ApprovalDetailUserCacheModel)) {
			return false;
		}

		ApprovalDetailUserCacheModel approvalDetailUserCacheModel =
			(ApprovalDetailUserCacheModel)object;

		if (Id == approvalDetailUserCacheModel.Id) {
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
		StringBundler sb = new StringBundler(17);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", ApprovalHeaderUserId=");
		sb.append(ApprovalHeaderUserId);
		sb.append(", RoleId=");
		sb.append(RoleId);
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
	public ApprovalDetailUser toEntityModel() {
		ApprovalDetailUserImpl approvalDetailUserImpl =
			new ApprovalDetailUserImpl();

		approvalDetailUserImpl.setId(Id);
		approvalDetailUserImpl.setApprovalHeaderUserId(ApprovalHeaderUserId);
		approvalDetailUserImpl.setRoleId(RoleId);
		approvalDetailUserImpl.setRowStatus(RowStatus);

		if (CreatedBy == null) {
			approvalDetailUserImpl.setCreatedBy("");
		}
		else {
			approvalDetailUserImpl.setCreatedBy(CreatedBy);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			approvalDetailUserImpl.setCreatedDate(null);
		}
		else {
			approvalDetailUserImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (ModifiedBy == null) {
			approvalDetailUserImpl.setModifiedBy("");
		}
		else {
			approvalDetailUserImpl.setModifiedBy(ModifiedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			approvalDetailUserImpl.setModifiedDate(null);
		}
		else {
			approvalDetailUserImpl.setModifiedDate(new Date(ModifiedDate));
		}

		approvalDetailUserImpl.resetOriginalValues();

		return approvalDetailUserImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		ApprovalHeaderUserId = objectInput.readInt();

		RoleId = objectInput.readInt();

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

		objectOutput.writeInt(RoleId);

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
	public int RoleId;
	public boolean RowStatus;
	public String CreatedBy;
	public long CreatedDate;
	public String ModifiedBy;
	public long ModifiedDate;

}