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

import com.astra.dewa.model.TrainingMateri;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TrainingMateri in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TrainingMateriCacheModel
	implements CacheModel<TrainingMateri>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TrainingMateriCacheModel)) {
			return false;
		}

		TrainingMateriCacheModel trainingMateriCacheModel =
			(TrainingMateriCacheModel)object;

		if (Id == trainingMateriCacheModel.Id) {
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
		sb.append(", JenisMateriId=");
		sb.append(JenisMateriId);
		sb.append(", TopikMateriId=");
		sb.append(TopikMateriId);
		sb.append(", JudulMateri=");
		sb.append(JudulMateri);
		sb.append(", ImageId=");
		sb.append(ImageId);
		sb.append(", ImageName=");
		sb.append(ImageName);
		sb.append(", ImagePath=");
		sb.append(ImagePath);
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
	public TrainingMateri toEntityModel() {
		TrainingMateriImpl trainingMateriImpl = new TrainingMateriImpl();

		trainingMateriImpl.setId(Id);
		trainingMateriImpl.setJenisMateriId(JenisMateriId);
		trainingMateriImpl.setTopikMateriId(TopikMateriId);

		if (JudulMateri == null) {
			trainingMateriImpl.setJudulMateri("");
		}
		else {
			trainingMateriImpl.setJudulMateri(JudulMateri);
		}

		trainingMateriImpl.setImageId(ImageId);

		if (ImageName == null) {
			trainingMateriImpl.setImageName("");
		}
		else {
			trainingMateriImpl.setImageName(ImageName);
		}

		if (ImagePath == null) {
			trainingMateriImpl.setImagePath("");
		}
		else {
			trainingMateriImpl.setImagePath(ImagePath);
		}

		trainingMateriImpl.setRowStatus(RowStatus);

		if (CreatedDate == Long.MIN_VALUE) {
			trainingMateriImpl.setCreatedDate(null);
		}
		else {
			trainingMateriImpl.setCreatedDate(new Date(CreatedDate));
		}

		if (CreatedBy == null) {
			trainingMateriImpl.setCreatedBy("");
		}
		else {
			trainingMateriImpl.setCreatedBy(CreatedBy);
		}

		if (ModifiedDate == Long.MIN_VALUE) {
			trainingMateriImpl.setModifiedDate(null);
		}
		else {
			trainingMateriImpl.setModifiedDate(new Date(ModifiedDate));
		}

		if (ModifiedBy == null) {
			trainingMateriImpl.setModifiedBy("");
		}
		else {
			trainingMateriImpl.setModifiedBy(ModifiedBy);
		}

		trainingMateriImpl.resetOriginalValues();

		return trainingMateriImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		Id = objectInput.readInt();

		JenisMateriId = objectInput.readInt();

		TopikMateriId = objectInput.readInt();
		JudulMateri = objectInput.readUTF();

		ImageId = objectInput.readLong();
		ImageName = objectInput.readUTF();
		ImagePath = objectInput.readUTF();

		RowStatus = objectInput.readBoolean();
		CreatedDate = objectInput.readLong();
		CreatedBy = objectInput.readUTF();
		ModifiedDate = objectInput.readLong();
		ModifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeInt(Id);

		objectOutput.writeInt(JenisMateriId);

		objectOutput.writeInt(TopikMateriId);

		if (JudulMateri == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(JudulMateri);
		}

		objectOutput.writeLong(ImageId);

		if (ImageName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ImageName);
		}

		if (ImagePath == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ImagePath);
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
	public int JenisMateriId;
	public int TopikMateriId;
	public String JudulMateri;
	public long ImageId;
	public String ImageName;
	public String ImagePath;
	public boolean RowStatus;
	public long CreatedDate;
	public String CreatedBy;
	public long ModifiedDate;
	public String ModifiedBy;

}