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

import com.astra.dewa.model.Dealer;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Dealer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DealerCacheModel implements CacheModel<Dealer>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DealerCacheModel)) {
			return false;
		}

		DealerCacheModel dealerCacheModel = (DealerCacheModel)object;

		if (Id == dealerCacheModel.Id) {
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
		sb.append(", CabangId=");
		sb.append(CabangId);
		sb.append(", WilayahId=");
		sb.append(WilayahId);
		sb.append(", GroupDealer=");
		sb.append(GroupDealer);
		sb.append(", Name=");
		sb.append(Name);
		sb.append(", Code=");
		sb.append(Code);
		sb.append(", KodeHo=");
		sb.append(KodeHo);
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
	public Dealer toEntityModel() {
		DealerImpl dealerImpl = new DealerImpl();

		dealerImpl.setId(Id);
		dealerImpl.setCabangId(CabangId);
		dealerImpl.setWilayahId(WilayahId);
		dealerImpl.setGroupDealer(GroupDealer);

		if (Name == null) {
			dealerImpl.setName("");
		}
		else {
			dealerImpl.setName(Name);
		}

		if (Code == null) {
			dealerImpl.setCode("");
		}
		else {
			dealerImpl.setCode(Code);
		}

		if (KodeHo == null) {
			dealerImpl.setKodeHo("");
		}
		else {
			dealerImpl.setKodeHo(KodeHo);
		}

		dealerImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			dealerImpl.setCreatedDate(null);
		}
		else {
			dealerImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			dealerImpl.setCreatedBy("");
		}
		else {
			dealerImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			dealerImpl.setModifiedDate(null);
		}
		else {
			dealerImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			dealerImpl.setModifiedBy("");
		}
		else {
			dealerImpl.setModifiedBy(ModifiedBy);
		}

		dealerImpl.resetOriginalValues();

		return dealerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		CabangId = objectInput.readInt();

		WilayahId = objectInput.readInt();

		GroupDealer = objectInput.readInt();
		Name = objectInput.readUTF();
		Code = objectInput.readUTF();
		KodeHo = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		objectOutput.writeInt(CabangId);

		objectOutput.writeInt(WilayahId);

		objectOutput.writeInt(GroupDealer);

		if (Name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Name);
		}

		if (Code == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Code);
		}

		if (KodeHo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(KodeHo);
		}

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
	public int CabangId;
	public int WilayahId;
	public int GroupDealer;
	public String Name;
	public String Code;
	public String KodeHo;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}