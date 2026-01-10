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

import com.astra.dewa.model.UserRoleType;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserRoleType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserRoleTypeCacheModel
	implements CacheModel<UserRoleType>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserRoleTypeCacheModel)) {
			return false;
		}

		UserRoleTypeCacheModel userRoleTypeCacheModel =
			(UserRoleTypeCacheModel)object;

		if (Id == userRoleTypeCacheModel.Id) {
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
		sb.append(", UserId=");
		sb.append(UserId);
		sb.append(", RoleId=");
		sb.append(RoleId);
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
	public UserRoleType toEntityModel() {
		UserRoleTypeImpl userRoleTypeImpl = new UserRoleTypeImpl();

		userRoleTypeImpl.setId(Id);
		userRoleTypeImpl.setUserId(UserId);
		userRoleTypeImpl.setRoleId(RoleId);
		userRoleTypeImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			userRoleTypeImpl.setCreatedDate(null);
		}
		else {
			userRoleTypeImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			userRoleTypeImpl.setCreatedBy("");
		}
		else {
			userRoleTypeImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			userRoleTypeImpl.setModifiedDate(null);
		}
		else {
			userRoleTypeImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			userRoleTypeImpl.setModifiedBy("");
		}
		else {
			userRoleTypeImpl.setModifiedBy(ModifiedBy);
		}

		userRoleTypeImpl.resetOriginalValues();

		return userRoleTypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		UserId = objectInput.readLong();

		RoleId = objectInput.readInt();

		RowStatus = objectInput.readBoolean();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		objectOutput.writeLong(UserId);

		objectOutput.writeInt(RoleId);

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
	public long UserId;
	public int RoleId;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}