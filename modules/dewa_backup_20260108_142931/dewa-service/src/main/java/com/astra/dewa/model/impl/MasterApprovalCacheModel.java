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

import com.astra.dewa.model.MasterApproval;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MasterApproval in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MasterApprovalCacheModel
	implements CacheModel<MasterApproval>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MasterApprovalCacheModel)) {
			return false;
		}

		MasterApprovalCacheModel masterApprovalCacheModel =
			(MasterApprovalCacheModel)object;

		if (Id == masterApprovalCacheModel.Id) {
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
		sb.append(", RoleId=");
		sb.append(RoleId);
		sb.append(", MenuId=");
		sb.append(MenuId);
		sb.append(", ApprovalGroup=");
		sb.append(ApprovalGroup);
		sb.append(", ApprovalWorkflow=");
		sb.append(ApprovalWorkflow);
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
	public MasterApproval toEntityModel() {
		MasterApprovalImpl masterApprovalImpl = new MasterApprovalImpl();

		masterApprovalImpl.setId(Id);
		masterApprovalImpl.setRoleId(RoleId);
		masterApprovalImpl.setMenuId(MenuId);

		if (ApprovalGroup == null) {
			masterApprovalImpl.setApprovalGroup("");
		}
		else {
			masterApprovalImpl.setApprovalGroup(ApprovalGroup);
		}

		masterApprovalImpl.setApprovalWorkflow(ApprovalWorkflow);
		masterApprovalImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			masterApprovalImpl.setCreatedDate(null);
		}
		else {
			masterApprovalImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			masterApprovalImpl.setCreatedBy("");
		}
		else {
			masterApprovalImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			masterApprovalImpl.setModifiedDate(null);
		}
		else {
			masterApprovalImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			masterApprovalImpl.setModifiedBy("");
		}
		else {
			masterApprovalImpl.setModifiedBy(ModifiedBy);
		}

		masterApprovalImpl.resetOriginalValues();

		return masterApprovalImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		RoleId = objectInput.readInt();

		MenuId = objectInput.readInt();
		ApprovalGroup = objectInput.readUTF();

		ApprovalWorkflow = objectInput.readBoolean();

		RowStatus = objectInput.readBoolean();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		objectOutput.writeInt(RoleId);

		objectOutput.writeInt(MenuId);

		if (ApprovalGroup == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ApprovalGroup);
		}

		objectOutput.writeBoolean(ApprovalWorkflow);

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
	public int RoleId;
	public int MenuId;
	public String ApprovalGroup;
	public boolean ApprovalWorkflow;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}