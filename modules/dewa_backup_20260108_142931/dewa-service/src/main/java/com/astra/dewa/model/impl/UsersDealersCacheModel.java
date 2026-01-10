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

import com.astra.dewa.model.UsersDealers;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UsersDealers in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UsersDealersCacheModel
	implements CacheModel<UsersDealers>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UsersDealersCacheModel)) {
			return false;
		}

		UsersDealersCacheModel usersDealersCacheModel =
			(UsersDealersCacheModel)object;

		if (Id == usersDealersCacheModel.Id) {
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
		StringBundler sb = new StringBundler(31);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", UserId=");
		sb.append(UserId);
		sb.append(", DealerId=");
		sb.append(DealerId);
		sb.append(", RoleId=");
		sb.append(RoleId);
		sb.append(", PhotoFileId=");
		sb.append(PhotoFileId);
		sb.append(", ADB2CId=");
		sb.append(ADB2CId);
		sb.append(", userPrincipalName=");
		sb.append(userPrincipalName);
		sb.append(", FullName=");
		sb.append(FullName);
		sb.append(", UserEmail=");
		sb.append(UserEmail);
		sb.append(", ApprovedDate=");
		sb.append(ApprovedDate);
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
	public UsersDealers toEntityModel() {
		UsersDealersImpl usersDealersImpl = new UsersDealersImpl();

		usersDealersImpl.setId(Id);
		usersDealersImpl.setUserId(UserId);
		usersDealersImpl.setDealerId(DealerId);
		usersDealersImpl.setRoleId(RoleId);
		usersDealersImpl.setPhotoFileId(PhotoFileId);

		if (ADB2CId == null) {
			usersDealersImpl.setADB2CId("");
		}
		else {
			usersDealersImpl.setADB2CId(ADB2CId);
		}

		if (userPrincipalName == null) {
			usersDealersImpl.setUserPrincipalName("");
		}
		else {
			usersDealersImpl.setUserPrincipalName(userPrincipalName);
		}

		if (FullName == null) {
			usersDealersImpl.setFullName("");
		}
		else {
			usersDealersImpl.setFullName(FullName);
		}

		if (UserEmail == null) {
			usersDealersImpl.setUserEmail("");
		}
		else {
			usersDealersImpl.setUserEmail(UserEmail);
		}

		if (ApprovedDate == Long.MIN_VALUE) {
			usersDealersImpl.setApprovedDate(null);
		}
		else {
			usersDealersImpl.setApprovedDate(new Date(ApprovedDate));
		}

		usersDealersImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			usersDealersImpl.setCreatedDate(null);
		}
		else {
			usersDealersImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			usersDealersImpl.setCreatedBy("");
		}
		else {
			usersDealersImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			usersDealersImpl.setModifiedDate(null);
		}
		else {
			usersDealersImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			usersDealersImpl.setModifiedBy("");
		}
		else {
			usersDealersImpl.setModifiedBy(ModifiedBy);
		}

		usersDealersImpl.resetOriginalValues();

		return usersDealersImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		UserId = objectInput.readLong();

		DealerId = objectInput.readInt();

		RoleId = objectInput.readInt();

		PhotoFileId = objectInput.readLong();
		ADB2CId = objectInput.readUTF();
		userPrincipalName = objectInput.readUTF();
		FullName = objectInput.readUTF();
		UserEmail = objectInput.readUTF();
		ApprovedDate = objectInput.readLong();

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

		objectOutput.writeInt(DealerId);

		objectOutput.writeInt(RoleId);

		objectOutput.writeLong(PhotoFileId);

		if (ADB2CId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ADB2CId);
		}

		if (userPrincipalName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userPrincipalName);
		}

		if (FullName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(FullName);
		}

		if (UserEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(UserEmail);
		}

		objectOutput.writeLong(ApprovedDate);

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
	public int DealerId;
	public int RoleId;
	public long PhotoFileId;
	public String ADB2CId;
	public String userPrincipalName;
	public String FullName;
	public String UserEmail;
	public long ApprovedDate;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}