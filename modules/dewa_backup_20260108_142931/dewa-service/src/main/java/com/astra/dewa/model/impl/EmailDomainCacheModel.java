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

import com.astra.dewa.model.EmailDomain;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing EmailDomain in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmailDomainCacheModel
	implements CacheModel<EmailDomain>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EmailDomainCacheModel)) {
			return false;
		}

		EmailDomainCacheModel emailDomainCacheModel =
			(EmailDomainCacheModel)object;

		if (Id == emailDomainCacheModel.Id) {
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
		sb.append(", DomainName=");
		sb.append(DomainName);
		sb.append(", Category=");
		sb.append(Category);
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
	public EmailDomain toEntityModel() {
		EmailDomainImpl emailDomainImpl = new EmailDomainImpl();

		emailDomainImpl.setId(Id);

		if (DomainName == null) {
			emailDomainImpl.setDomainName("");
		}
		else {
			emailDomainImpl.setDomainName(DomainName);
		}

		if (Category == null) {
			emailDomainImpl.setCategory("");
		}
		else {
			emailDomainImpl.setCategory(Category);
		}

		emailDomainImpl.setRowStatus(RowStatus);

		if (CreatedBy == null) {
			emailDomainImpl.setCreatedBy("");
		}
		else {
			emailDomainImpl.setCreatedBy(CreatedBy);
		}

		if (CreatedDate == Long.MIN_VALUE) {
			emailDomainImpl.setCreatedDate(null);
		}
		else {
			emailDomainImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (ModifiedBy == null) {
			emailDomainImpl.setModifiedBy("");
		}
		else {
			emailDomainImpl.setModifiedBy(ModifiedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			emailDomainImpl.setModifiedDate(null);
		}
		else {
			emailDomainImpl.setModifiedDate(new Date(ModifiedDate));
		}

		emailDomainImpl.resetOriginalValues();

		return emailDomainImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();
		DomainName = objectInput.readUTF();
		Category = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
		CreatedBy = objectInput.readUTF();
		CreatedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		if (DomainName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(DomainName);
		}

		if (Category == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Category);
		}

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
	public String DomainName;
	public String Category;
	public boolean RowStatus;
	public String CreatedBy;
	public long CreatedDate;
	public String ModifiedBy;
	public long ModifiedDate;

}