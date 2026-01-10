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
 * This class is a wrapper for {@link TrainingAgendaParticipant}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingAgendaParticipant
 * @generated
 */
public class TrainingAgendaParticipantWrapper
	extends BaseModelWrapper<TrainingAgendaParticipant>
	implements ModelWrapper<TrainingAgendaParticipant>,
			   TrainingAgendaParticipant {

	public TrainingAgendaParticipantWrapper(
		TrainingAgendaParticipant trainingAgendaParticipant) {

		super(trainingAgendaParticipant);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("TrainingAgendaId", getTrainingAgendaId());
		attributes.put("DealerId", getDealerId());
		attributes.put("FullName", getFullName());
		attributes.put("Email", getEmail());
		attributes.put("Phone", getPhone());
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

		String FullName = (String)attributes.get("FullName");

		if (FullName != null) {
			setFullName(FullName);
		}

		String Email = (String)attributes.get("Email");

		if (Email != null) {
			setEmail(Email);
		}

		String Phone = (String)attributes.get("Phone");

		if (Phone != null) {
			setPhone(Phone);
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
	public TrainingAgendaParticipant cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this training agenda participant.
	 *
	 * @return the created by of this training agenda participant
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this training agenda participant.
	 *
	 * @return the created date of this training agenda participant
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the dealer ID of this training agenda participant.
	 *
	 * @return the dealer ID of this training agenda participant
	 */
	@Override
	public int getDealerId() {
		return model.getDealerId();
	}

	/**
	 * Returns the email of this training agenda participant.
	 *
	 * @return the email of this training agenda participant
	 */
	@Override
	public String getEmail() {
		return model.getEmail();
	}

	/**
	 * Returns the full name of this training agenda participant.
	 *
	 * @return the full name of this training agenda participant
	 */
	@Override
	public String getFullName() {
		return model.getFullName();
	}

	/**
	 * Returns the ID of this training agenda participant.
	 *
	 * @return the ID of this training agenda participant
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this training agenda participant.
	 *
	 * @return the modified by of this training agenda participant
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this training agenda participant.
	 *
	 * @return the modified date of this training agenda participant
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the phone of this training agenda participant.
	 *
	 * @return the phone of this training agenda participant
	 */
	@Override
	public String getPhone() {
		return model.getPhone();
	}

	/**
	 * Returns the primary key of this training agenda participant.
	 *
	 * @return the primary key of this training agenda participant
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this training agenda participant.
	 *
	 * @return the row status of this training agenda participant
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the training agenda ID of this training agenda participant.
	 *
	 * @return the training agenda ID of this training agenda participant
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
	 * Sets the created by of this training agenda participant.
	 *
	 * @param CreatedBy the created by of this training agenda participant
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this training agenda participant.
	 *
	 * @param CreatedDate the created date of this training agenda participant
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the dealer ID of this training agenda participant.
	 *
	 * @param DealerId the dealer ID of this training agenda participant
	 */
	@Override
	public void setDealerId(int DealerId) {
		model.setDealerId(DealerId);
	}

	/**
	 * Sets the email of this training agenda participant.
	 *
	 * @param Email the email of this training agenda participant
	 */
	@Override
	public void setEmail(String Email) {
		model.setEmail(Email);
	}

	/**
	 * Sets the full name of this training agenda participant.
	 *
	 * @param FullName the full name of this training agenda participant
	 */
	@Override
	public void setFullName(String FullName) {
		model.setFullName(FullName);
	}

	/**
	 * Sets the ID of this training agenda participant.
	 *
	 * @param Id the ID of this training agenda participant
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this training agenda participant.
	 *
	 * @param ModifiedBy the modified by of this training agenda participant
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this training agenda participant.
	 *
	 * @param ModifiedDate the modified date of this training agenda participant
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the phone of this training agenda participant.
	 *
	 * @param Phone the phone of this training agenda participant
	 */
	@Override
	public void setPhone(String Phone) {
		model.setPhone(Phone);
	}

	/**
	 * Sets the primary key of this training agenda participant.
	 *
	 * @param primaryKey the primary key of this training agenda participant
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the row status of this training agenda participant.
	 *
	 * @param RowStatus the row status of this training agenda participant
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the training agenda ID of this training agenda participant.
	 *
	 * @param TrainingAgendaId the training agenda ID of this training agenda participant
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
	protected TrainingAgendaParticipantWrapper wrap(
		TrainingAgendaParticipant trainingAgendaParticipant) {

		return new TrainingAgendaParticipantWrapper(trainingAgendaParticipant);
	}

}