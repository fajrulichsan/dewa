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

package com.astra.dewa.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link JenisMateri}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JenisMateri
 * @generated
 */
public class JenisMateriWrapper
	extends BaseModelWrapper<JenisMateri>
	implements JenisMateri, ModelWrapper<JenisMateri> {

	public JenisMateriWrapper(JenisMateri jenisMateri) {
		super(jenisMateri);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("Name", getName());
		attributes.put("ImageId", getImageId());
		attributes.put("ImageName", getImageName());
		attributes.put("ImagePath", getImagePath());
		attributes.put("RowStatus", getRowStatus());
		attributes.put("CreatedDate", getCreatedDate());
		attributes.put("CreatedBy", getCreatedBy());
		attributes.put("ModifiedDate", getModifiedDate());
		attributes.put("ModifiedBy", getModifiedBy());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer Id = (Integer)attributes.get("Id");

		if (Id != null) {
			setId(Id);
		}

		String Name = (String)attributes.get("Name");

		if (Name != null) {
			setName(Name);
		}

		Long ImageId = (Long)attributes.get("ImageId");

		if (ImageId != null) {
			setImageId(ImageId);
		}

		String ImageName = (String)attributes.get("ImageName");

		if (ImageName != null) {
			setImageName(ImageName);
		}

		String ImagePath = (String)attributes.get("ImagePath");

		if (ImagePath != null) {
			setImagePath(ImagePath);
		}

		Boolean RowStatus = (Boolean)attributes.get("RowStatus");

		if (RowStatus != null) {
			setRowStatus(RowStatus);
		}

		Date CreatedDate = (Date)attributes.get("CreatedDate");

		if (CreatedDate != null) {
			setCreatedDate(CreatedDate);
		}

		String CreatedBy = (String)attributes.get("CreatedBy");

		if (CreatedBy != null) {
			setCreatedBy(CreatedBy);
		}

		Date ModifiedDate = (Date)attributes.get("ModifiedDate");

		if (ModifiedDate != null) {
			setModifiedDate(ModifiedDate);
		}

		String ModifiedBy = (String)attributes.get("ModifiedBy");

		if (ModifiedBy != null) {
			setModifiedBy(ModifiedBy);
		}
	}

	@Override
	public JenisMateri cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this jenis materi.
	 *
	 * @return the created by of this jenis materi
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this jenis materi.
	 *
	 * @return the created date of this jenis materi
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the ID of this jenis materi.
	 *
	 * @return the ID of this jenis materi
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the image ID of this jenis materi.
	 *
	 * @return the image ID of this jenis materi
	 */
	@Override
	public Long getImageId() {
		return model.getImageId();
	}

	/**
	 * Returns the image name of this jenis materi.
	 *
	 * @return the image name of this jenis materi
	 */
	@Override
	public String getImageName() {
		return model.getImageName();
	}

	/**
	 * Returns the image path of this jenis materi.
	 *
	 * @return the image path of this jenis materi
	 */
	@Override
	public String getImagePath() {
		return model.getImagePath();
	}

	/**
	 * Returns the modified by of this jenis materi.
	 *
	 * @return the modified by of this jenis materi
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this jenis materi.
	 *
	 * @return the modified date of this jenis materi
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this jenis materi.
	 *
	 * @return the name of this jenis materi
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this jenis materi.
	 *
	 * @return the primary key of this jenis materi
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this jenis materi.
	 *
	 * @return the row status of this jenis materi
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the created by of this jenis materi.
	 *
	 * @param CreatedBy the created by of this jenis materi
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this jenis materi.
	 *
	 * @param CreatedDate the created date of this jenis materi
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the ID of this jenis materi.
	 *
	 * @param Id the ID of this jenis materi
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the image ID of this jenis materi.
	 *
	 * @param ImageId the image ID of this jenis materi
	 */
	@Override
	public void setImageId(Long ImageId) {
		model.setImageId(ImageId);
	}

	/**
	 * Sets the image name of this jenis materi.
	 *
	 * @param ImageName the image name of this jenis materi
	 */
	@Override
	public void setImageName(String ImageName) {
		model.setImageName(ImageName);
	}

	/**
	 * Sets the image path of this jenis materi.
	 *
	 * @param ImagePath the image path of this jenis materi
	 */
	@Override
	public void setImagePath(String ImagePath) {
		model.setImagePath(ImagePath);
	}

	/**
	 * Sets the modified by of this jenis materi.
	 *
	 * @param ModifiedBy the modified by of this jenis materi
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this jenis materi.
	 *
	 * @param ModifiedDate the modified date of this jenis materi
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the name of this jenis materi.
	 *
	 * @param Name the name of this jenis materi
	 */
	@Override
	public void setName(String Name) {
		model.setName(Name);
	}

	/**
	 * Sets the primary key of this jenis materi.
	 *
	 * @param primaryKey the primary key of this jenis materi
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the row status of this jenis materi.
	 *
	 * @param RowStatus the row status of this jenis materi
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected JenisMateriWrapper wrap(JenisMateri jenisMateri) {
		return new JenisMateriWrapper(jenisMateri);
	}

}