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
 * This class is a wrapper for {@link TrainingMateri}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingMateri
 * @generated
 */
public class TrainingMateriWrapper
	extends BaseModelWrapper<TrainingMateri>
	implements ModelWrapper<TrainingMateri>, TrainingMateri {

	public TrainingMateriWrapper(TrainingMateri trainingMateri) {
		super(trainingMateri);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("JenisMateriId", getJenisMateriId());
		attributes.put("TopikMateriId", getTopikMateriId());
		attributes.put("JudulMateri", getJudulMateri());
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

		Integer JenisMateriId = (Integer)attributes.get("JenisMateriId");

		if (JenisMateriId != null) {
			setJenisMateriId(JenisMateriId);
		}

		Integer TopikMateriId = (Integer)attributes.get("TopikMateriId");

		if (TopikMateriId != null) {
			setTopikMateriId(TopikMateriId);
		}

		String JudulMateri = (String)attributes.get("JudulMateri");

		if (JudulMateri != null) {
			setJudulMateri(JudulMateri);
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
	public TrainingMateri cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this training materi.
	 *
	 * @return the created by of this training materi
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this training materi.
	 *
	 * @return the created date of this training materi
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the ID of this training materi.
	 *
	 * @return the ID of this training materi
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the image ID of this training materi.
	 *
	 * @return the image ID of this training materi
	 */
	@Override
	public Long getImageId() {
		return model.getImageId();
	}

	/**
	 * Returns the image name of this training materi.
	 *
	 * @return the image name of this training materi
	 */
	@Override
	public String getImageName() {
		return model.getImageName();
	}

	/**
	 * Returns the image path of this training materi.
	 *
	 * @return the image path of this training materi
	 */
	@Override
	public String getImagePath() {
		return model.getImagePath();
	}

	/**
	 * Returns the jenis materi ID of this training materi.
	 *
	 * @return the jenis materi ID of this training materi
	 */
	@Override
	public int getJenisMateriId() {
		return model.getJenisMateriId();
	}

	/**
	 * Returns the judul materi of this training materi.
	 *
	 * @return the judul materi of this training materi
	 */
	@Override
	public String getJudulMateri() {
		return model.getJudulMateri();
	}

	/**
	 * Returns the modified by of this training materi.
	 *
	 * @return the modified by of this training materi
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this training materi.
	 *
	 * @return the modified date of this training materi
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this training materi.
	 *
	 * @return the primary key of this training materi
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this training materi.
	 *
	 * @return the row status of this training materi
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the topik materi ID of this training materi.
	 *
	 * @return the topik materi ID of this training materi
	 */
	@Override
	public int getTopikMateriId() {
		return model.getTopikMateriId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the created by of this training materi.
	 *
	 * @param CreatedBy the created by of this training materi
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this training materi.
	 *
	 * @param CreatedDate the created date of this training materi
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the ID of this training materi.
	 *
	 * @param Id the ID of this training materi
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the image ID of this training materi.
	 *
	 * @param ImageId the image ID of this training materi
	 */
	@Override
	public void setImageId(Long ImageId) {
		model.setImageId(ImageId);
	}

	/**
	 * Sets the image name of this training materi.
	 *
	 * @param ImageName the image name of this training materi
	 */
	@Override
	public void setImageName(String ImageName) {
		model.setImageName(ImageName);
	}

	/**
	 * Sets the image path of this training materi.
	 *
	 * @param ImagePath the image path of this training materi
	 */
	@Override
	public void setImagePath(String ImagePath) {
		model.setImagePath(ImagePath);
	}

	/**
	 * Sets the jenis materi ID of this training materi.
	 *
	 * @param JenisMateriId the jenis materi ID of this training materi
	 */
	@Override
	public void setJenisMateriId(int JenisMateriId) {
		model.setJenisMateriId(JenisMateriId);
	}

	/**
	 * Sets the judul materi of this training materi.
	 *
	 * @param JudulMateri the judul materi of this training materi
	 */
	@Override
	public void setJudulMateri(String JudulMateri) {
		model.setJudulMateri(JudulMateri);
	}

	/**
	 * Sets the modified by of this training materi.
	 *
	 * @param ModifiedBy the modified by of this training materi
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this training materi.
	 *
	 * @param ModifiedDate the modified date of this training materi
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this training materi.
	 *
	 * @param primaryKey the primary key of this training materi
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the row status of this training materi.
	 *
	 * @param RowStatus the row status of this training materi
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the topik materi ID of this training materi.
	 *
	 * @param TopikMateriId the topik materi ID of this training materi
	 */
	@Override
	public void setTopikMateriId(int TopikMateriId) {
		model.setTopikMateriId(TopikMateriId);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected TrainingMateriWrapper wrap(TrainingMateri trainingMateri) {
		return new TrainingMateriWrapper(trainingMateri);
	}

}