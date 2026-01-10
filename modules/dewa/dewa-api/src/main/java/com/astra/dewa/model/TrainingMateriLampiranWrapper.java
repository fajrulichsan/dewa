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
 * This class is a wrapper for {@link TrainingMateriLampiran}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingMateriLampiran
 * @generated
 */
public class TrainingMateriLampiranWrapper
	extends BaseModelWrapper<TrainingMateriLampiran>
	implements ModelWrapper<TrainingMateriLampiran>, TrainingMateriLampiran {

	public TrainingMateriLampiranWrapper(
		TrainingMateriLampiran trainingMateriLampiran) {

		super(trainingMateriLampiran);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("TrainingMateriId", getTrainingMateriId());
		attributes.put("FileId", getFileId());
		attributes.put("FileName", getFileName());
		attributes.put("FilePath", getFilePath());
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

		Integer TrainingMateriId = (Integer)attributes.get("TrainingMateriId");

		if (TrainingMateriId != null) {
			setTrainingMateriId(TrainingMateriId);
		}

		Long FileId = (Long)attributes.get("FileId");

		if (FileId != null) {
			setFileId(FileId);
		}

		String FileName = (String)attributes.get("FileName");

		if (FileName != null) {
			setFileName(FileName);
		}

		String FilePath = (String)attributes.get("FilePath");

		if (FilePath != null) {
			setFilePath(FilePath);
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
	public TrainingMateriLampiran cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this training materi lampiran.
	 *
	 * @return the created by of this training materi lampiran
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this training materi lampiran.
	 *
	 * @return the created date of this training materi lampiran
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the file ID of this training materi lampiran.
	 *
	 * @return the file ID of this training materi lampiran
	 */
	@Override
	public Long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the file name of this training materi lampiran.
	 *
	 * @return the file name of this training materi lampiran
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the file path of this training materi lampiran.
	 *
	 * @return the file path of this training materi lampiran
	 */
	@Override
	public String getFilePath() {
		return model.getFilePath();
	}

	/**
	 * Returns the ID of this training materi lampiran.
	 *
	 * @return the ID of this training materi lampiran
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this training materi lampiran.
	 *
	 * @return the modified by of this training materi lampiran
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this training materi lampiran.
	 *
	 * @return the modified date of this training materi lampiran
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this training materi lampiran.
	 *
	 * @return the primary key of this training materi lampiran
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this training materi lampiran.
	 *
	 * @return the row status of this training materi lampiran
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the training materi ID of this training materi lampiran.
	 *
	 * @return the training materi ID of this training materi lampiran
	 */
	@Override
	public int getTrainingMateriId() {
		return model.getTrainingMateriId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the created by of this training materi lampiran.
	 *
	 * @param CreatedBy the created by of this training materi lampiran
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this training materi lampiran.
	 *
	 * @param CreatedDate the created date of this training materi lampiran
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the file ID of this training materi lampiran.
	 *
	 * @param FileId the file ID of this training materi lampiran
	 */
	@Override
	public void setFileId(Long FileId) {
		model.setFileId(FileId);
	}

	/**
	 * Sets the file name of this training materi lampiran.
	 *
	 * @param FileName the file name of this training materi lampiran
	 */
	@Override
	public void setFileName(String FileName) {
		model.setFileName(FileName);
	}

	/**
	 * Sets the file path of this training materi lampiran.
	 *
	 * @param FilePath the file path of this training materi lampiran
	 */
	@Override
	public void setFilePath(String FilePath) {
		model.setFilePath(FilePath);
	}

	/**
	 * Sets the ID of this training materi lampiran.
	 *
	 * @param Id the ID of this training materi lampiran
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this training materi lampiran.
	 *
	 * @param ModifiedBy the modified by of this training materi lampiran
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this training materi lampiran.
	 *
	 * @param ModifiedDate the modified date of this training materi lampiran
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this training materi lampiran.
	 *
	 * @param primaryKey the primary key of this training materi lampiran
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the row status of this training materi lampiran.
	 *
	 * @param RowStatus the row status of this training materi lampiran
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the training materi ID of this training materi lampiran.
	 *
	 * @param TrainingMateriId the training materi ID of this training materi lampiran
	 */
	@Override
	public void setTrainingMateriId(int TrainingMateriId) {
		model.setTrainingMateriId(TrainingMateriId);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected TrainingMateriLampiranWrapper wrap(
		TrainingMateriLampiran trainingMateriLampiran) {

		return new TrainingMateriLampiranWrapper(trainingMateriLampiran);
	}

}