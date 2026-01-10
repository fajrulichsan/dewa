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

import com.astra.dewa.model.MenuAuthorization;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MenuAuthorization in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MenuAuthorizationCacheModel
	implements CacheModel<MenuAuthorization>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MenuAuthorizationCacheModel)) {
			return false;
		}

		MenuAuthorizationCacheModel menuAuthorizationCacheModel =
			(MenuAuthorizationCacheModel)object;

		if (Id == menuAuthorizationCacheModel.Id) {
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
		sb.append(", Menus=");
		sb.append(Menus);
		sb.append(", IsRssp=");
		sb.append(IsRssp);
		sb.append(", IsCmsDso=");
		sb.append(IsCmsDso);
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
	public MenuAuthorization toEntityModel() {
		MenuAuthorizationImpl menuAuthorizationImpl =
			new MenuAuthorizationImpl();

		menuAuthorizationImpl.setId(Id);
		menuAuthorizationImpl.setRoleId(RoleId);

		if (Menus == null) {
			menuAuthorizationImpl.setMenus("");
		}
		else {
			menuAuthorizationImpl.setMenus(Menus);
		}

		menuAuthorizationImpl.setIsRssp(IsRssp);
		menuAuthorizationImpl.setIsCmsDso(IsCmsDso);
		menuAuthorizationImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			menuAuthorizationImpl.setCreatedDate(null);
		}
		else {
			menuAuthorizationImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			menuAuthorizationImpl.setCreatedBy("");
		}
		else {
			menuAuthorizationImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			menuAuthorizationImpl.setModifiedDate(null);
		}
		else {
			menuAuthorizationImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			menuAuthorizationImpl.setModifiedBy("");
		}
		else {
			menuAuthorizationImpl.setModifiedBy(ModifiedBy);
		}

		menuAuthorizationImpl.resetOriginalValues();

		return menuAuthorizationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		RoleId = objectInput.readInt();
		Menus = objectInput.readUTF();

		IsRssp = objectInput.readBoolean();

		IsCmsDso = objectInput.readBoolean();

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

		if (Menus == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Menus);
		}

		objectOutput.writeBoolean(IsRssp);

		objectOutput.writeBoolean(IsCmsDso);

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
	public String Menus;
	public boolean IsRssp;
	public boolean IsCmsDso;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}