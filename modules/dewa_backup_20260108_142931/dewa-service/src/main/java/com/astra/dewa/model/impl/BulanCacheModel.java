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

import com.astra.dewa.model.Bulan;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Bulan in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class BulanCacheModel implements CacheModel<Bulan>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof BulanCacheModel)) {
			return false;
		}

		BulanCacheModel bulanCacheModel = (BulanCacheModel)object;

		if (Id.equals(bulanCacheModel.Id)) {
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
		StringBundler sb = new StringBundler(7);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", Name=");
		sb.append(Name);
		sb.append(", SortIndex=");
		sb.append(SortIndex);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Bulan toEntityModel() {
		BulanImpl bulanImpl = new BulanImpl();

		if (Id == null) {
			bulanImpl.setId("");
		}
		else {
			bulanImpl.setId(Id);
		}

		if (Name == null) {
			bulanImpl.setName("");
		}
		else {
			bulanImpl.setName(Name);
		}

		bulanImpl.setSortIndex(SortIndex);

		bulanImpl.resetOriginalValues();

		return bulanImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readUTF();
		Name = objectInput.readUTF();

		SortIndex = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (Id == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Id);
		}

		if (Name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Name);
		}

		objectOutput.writeInt(SortIndex);
	}

	public String Id;
	public String Name;
	public int SortIndex;

}