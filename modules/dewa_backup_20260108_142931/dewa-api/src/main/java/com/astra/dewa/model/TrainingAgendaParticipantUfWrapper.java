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
 * This class is a wrapper for {@link TrainingAgendaParticipantUf}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingAgendaParticipantUf
 * @generated
 */
public class TrainingAgendaParticipantUfWrapper
	extends BaseModelWrapper<TrainingAgendaParticipantUf>
	implements ModelWrapper<TrainingAgendaParticipantUf>,
			   TrainingAgendaParticipantUf {

	public TrainingAgendaParticipantUfWrapper(
		TrainingAgendaParticipantUf trainingAgendaParticipantUf) {

		super(trainingAgendaParticipantUf);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("TrainingAgendaId", getTrainingAgendaId());
		attributes.put("DealerId", getDealerId());
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

		Integer TrainingAgendaId = (Integer)attributes.get("TrainingAgendaId");

		if (TrainingAgendaId != null) {
			setTrainingAgendaId(TrainingAgendaId);
		}

		Integer DealerId = (Integer)attributes.get("DealerId");

		if (DealerId != null) {
			setDealerId(DealerId);
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
	public TrainingAgendaParticipantUf cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this training agenda participant uf.
	 *
	 * @return the created by of this training agenda participant uf
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this training agenda participant uf.
	 *
	 * @return the created date of this training agenda participant uf
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the dealer ID of this training agenda participant uf.
	 *
	 * @return the dealer ID of this training agenda participant uf
	 */
	@Override
	public int getDealerId() {
		return model.getDealerId();
	}

	/**
	 * Returns the file ID of this training agenda participant uf.
	 *
	 * @return the file ID of this training agenda participant uf
	 */
	@Override
	public Long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the file name of this training agenda participant uf.
	 *
	 * @return the file name of this training agenda participant uf
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the file path of this training agenda participant uf.
	 *
	 * @return the file path of this training agenda participant uf
	 */
	@Override
	public String getFilePath() {
		return model.getFilePath();
	}

	/**
	 * Returns the ID of this training agenda participant uf.
	 *
	 * @return the ID of this training agenda participant uf
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this training agenda participant uf.
	 *
	 * @return the modified by of this training agenda participant uf
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this training agenda participant uf.
	 *
	 * @return the modified date of this training agenda participant uf
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this training agenda participant uf.
	 *
	 * @return the primary key of this training agenda participant uf
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this training agenda participant uf.
	 *
	 * @return the row status of this training agenda participant uf
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the training agenda ID of this training agenda participant uf.
	 *
	 * @return the training agenda ID of this training agenda participant uf
	 */
	@Override
	public int getTrainingAgendaId() {
		return model.getTrainingAgendaId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the created by of this training agenda participant uf.
	 *
	 * @param CreatedBy the created by of this training agenda participant uf
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this training agenda participant uf.
	 *
	 * @param CreatedDate the created date of this training agenda participant uf
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the dealer ID of this training agenda participant uf.
	 *
	 * @param DealerId the dealer ID of this training agenda participant uf
	 */
	@Override
	public void setDealerId(int DealerId) {
		model.setDealerId(DealerId);
	}

	/**
	 * Sets the file ID of this training agenda participant uf.
	 *
	 * @param FileId the file ID of this training agenda participant uf
	 */
	@Override
	public void setFileId(Long FileId) {
		model.setFileId(FileId);
	}

	/**
	 * Sets the file name of this training agenda participant uf.
	 *
	 * @param FileName the file name of this training agenda participant uf
	 */
	@Override
	public void setFileName(String FileName) {
		model.setFileName(FileName);
	}

	/**
	 * Sets the file path of this training agenda participant uf.
	 *
	 * @param FilePath the file path of this training agenda participant uf
	 */
	@Override
	public void setFilePath(String FilePath) {
		model.setFilePath(FilePath);
	}

	/**
	 * Sets the ID of this training agenda participant uf.
	 *
	 * @param Id the ID of this training agenda participant uf
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this training agenda participant uf.
	 *
	 * @param ModifiedBy the modified by of this training agenda participant uf
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this training agenda participant uf.
	 *
	 * @param ModifiedDate the modified date of this training agenda participant uf
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this training agenda participant uf.
	 *
	 * @param primaryKey the primary key of this training agenda participant uf
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the row status of this training agenda participant uf.
	 *
	 * @param RowStatus the row status of this training agenda participant uf
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the training agenda ID of this training agenda participant uf.
	 *
	 * @param TrainingAgendaId the training agenda ID of this training agenda participant uf
	 */
	@Override
	public void setTrainingAgendaId(int TrainingAgendaId) {
		model.setTrainingAgendaId(TrainingAgendaId);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected TrainingAgendaParticipantUfWrapper wrap(
		TrainingAgendaParticipantUf trainingAgendaParticipantUf) {

		return new TrainingAgendaParticipantUfWrapper(
			trainingAgendaParticipantUf);
	}

}