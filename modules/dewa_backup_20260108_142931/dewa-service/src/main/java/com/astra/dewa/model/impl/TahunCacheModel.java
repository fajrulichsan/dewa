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

import com.astra.dewa.model.Tahun;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Tahun in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TahunCacheModel implements CacheModel<Tahun>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TahunCacheModel)) {
			return false;
		}

		TahunCacheModel tahunCacheModel = (TahunCacheModel)object;

		if (Id.equals(tahunCacheModel.Id)) {
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
		StringBundler sb = new StringBundler(5);

		sb.append("{Id=");
		sb.append(Id);
		sb.append(", Name=");
		sb.append(Name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Tahun toEntityModel() {
		TahunImpl tahunImpl = new TahunImpl();

		if (Id == null) {
			tahunImpl.setId("");
		}
		else {
			tahunImpl.setId(Id);
		}

		if (Name == null) {
			tahunImpl.setName("");
		}
		else {
			tahunImpl.setName(Name);
		}

		tahunImpl.resetOriginalValues();

		return tahunImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readUTF();
		Name = objectInput.readUTF();
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
	}

	public String Id;
	public String Name;

}