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
 * This class is a wrapper for {@link ApplicationAssignJournal}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationAssignJournal
 * @generated
 */
public class ApplicationAssignJournalWrapper
	extends BaseModelWrapper<ApplicationAssignJournal>
	implements ApplicationAssignJournal,
			   ModelWrapper<ApplicationAssignJournal> {

	public ApplicationAssignJournalWrapper(
		ApplicationAssignJournal applicationAssignJournal) {

		super(applicationAssignJournal);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("ApplicationAssignId", getApplicationAssignId());
		attributes.put("ApplicationHeaderId", getApplicationHeaderId());
		attributes.put(
			"ApplicationAssignStatusId", getApplicationAssignStatusId());
		attributes.put("ProfileId", getProfileId());
		attributes.put("StartDateOn", getStartDateOn());
		attributes.put("CompletedDateOn", getCompletedDateOn());
		attributes.put("Notes", getNotes());
		attributes.put("RowStatus", getRowStatus());
		attributes.put("CreatedBy", getCreatedBy());
		attributes.put("CreatedDate", getCreatedDate());
		attributes.put("ModifiedBy", getModifiedBy());
		attributes.put("ModifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer Id = (Integer)attributes.get("Id");

		if (Id != null) {
			setId(Id);
		}

		Integer ApplicationAssignId = (Integer)attributes.get(
			"ApplicationAssignId");

		if (ApplicationAssignId != null) {
			setApplicationAssignId(ApplicationAssignId);
		}

		Integer ApplicationHeaderId = (Integer)attributes.get(
			"ApplicationHeaderId");

		if (ApplicationHeaderId != null) {
			setApplicationHeaderId(ApplicationHeaderId);
		}

		Integer ApplicationAssignStatusId = (Integer)attributes.get(
			"ApplicationAssignStatusId");

		if (ApplicationAssignStatusId != null) {
			setApplicationAssignStatusId(ApplicationAssignStatusId);
		}

		Long ProfileId = (Long)attributes.get("ProfileId");

		if (ProfileId != null) {
			setProfileId(ProfileId);
		}

		Date StartDateOn = (Date)attributes.get("StartDateOn");

		if (StartDateOn != null) {
			setStartDateOn(StartDateOn);
		}

		Date CompletedDateOn = (Date)attributes.get("CompletedDateOn");

		if (CompletedDateOn != null) {
			setCompletedDateOn(CompletedDateOn);
		}

		String Notes = (String)attributes.get("Notes");

		if (Notes != null) {
			setNotes(Notes);
		}

		Boolean RowStatus = (Boolean)attributes.get("RowStatus");

		if (RowStatus != null) {
			setRowStatus(RowStatus);
		}

		String CreatedBy = (String)attributes.get("CreatedBy");

		if (CreatedBy != null) {
			setCreatedBy(CreatedBy);
		}

		Date CreatedDate = (Date)attributes.get("CreatedDate");

		if (CreatedDate != null) {
			setCreatedDate(CreatedDate);
		}

		String ModifiedBy = (String)attributes.get("ModifiedBy");

		if (ModifiedBy != null) {
			setModifiedBy(ModifiedBy);
		}

		Date ModifiedDate = (Date)attributes.get("ModifiedDate");

		if (ModifiedDate != null) {
			setModifiedDate(ModifiedDate);
		}
	}

	@Override
	public ApplicationAssignJournal cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the application assign ID of this application assign journal.
	 *
	 * @return the application assign ID of this application assign journal
	 */
	@Override
	public int getApplicationAssignId() {
		return model.getApplicationAssignId();
	}

	/**
	 * Returns the application assign status ID of this application assign journal.
	 *
	 * @return the application assign status ID of this application assign journal
	 */
	@Override
	public int getApplicationAssignStatusId() {
		return model.getApplicationAssignStatusId();
	}

	/**
	 * Returns the application header ID of this application assign journal.
	 *
	 * @return the application header ID of this application assign journal
	 */
	@Override
	public int getApplicationHeaderId() {
		return model.getApplicationHeaderId();
	}

	/**
	 * Returns the completed date on of this application assign journal.
	 *
	 * @return the completed date on of this application assign journal
	 */
	@Override
	public Date getCompletedDateOn() {
		return model.getCompletedDateOn();
	}

	/**
	 * Returns the created by of this application assign journal.
	 *
	 * @return the created by of this application assign journal
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this application assign journal.
	 *
	 * @return the created date of this application assign journal
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the ID of this application assign journal.
	 *
	 * @return the ID of this application assign journal
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this application assign journal.
	 *
	 * @return the modified by of this application assign journal
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this application assign journal.
	 *
	 * @return the modified date of this application assign journal
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the notes of this application assign journal.
	 *
	 * @return the notes of this application assign journal
	 */
	@Override
	public String getNotes() {
		return model.getNotes();
	}

	/**
	 * Returns the primary key of this application assign journal.
	 *
	 * @return the primary key of this application assign journal
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the profile ID of this application assign journal.
	 *
	 * @return the profile ID of this application assign journal
	 */
	@Override
	public long getProfileId() {
		return model.getProfileId();
	}

	/**
	 * Returns the row status of this application assign journal.
	 *
	 * @return the row status of this application assign journal
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the start date on of this application assign journal.
	 *
	 * @return the start date on of this application assign journal
	 */
	@Override
	public Date getStartDateOn() {
		return model.getStartDateOn();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the application assign ID of this application assign journal.
	 *
	 * @param ApplicationAssignId the application assign ID of this application assign journal
	 */
	@Override
	public void setApplicationAssignId(int ApplicationAssignId) {
		model.setApplicationAssignId(ApplicationAssignId);
	}

	/**
	 * Sets the application assign status ID of this application assign journal.
	 *
	 * @param ApplicationAssignStatusId the application assign status ID of this application assign journal
	 */
	@Override
	public void setApplicationAssignStatusId(int ApplicationAssignStatusId) {
		model.setApplicationAssignStatusId(ApplicationAssignStatusId);
	}

	/**
	 * Sets the application header ID of this application assign journal.
	 *
	 * @param ApplicationHeaderId the application header ID of this application assign journal
	 */
	@Override
	public void setApplicationHeaderId(int ApplicationHeaderId) {
		model.setApplicationHeaderId(ApplicationHeaderId);
	}

	/**
	 * Sets the completed date on of this application assign journal.
	 *
	 * @param CompletedDateOn the completed date on of this application assign journal
	 */
	@Override
	public void setCompletedDateOn(Date CompletedDateOn) {
		model.setCompletedDateOn(CompletedDateOn);
	}

	/**
	 * Sets the created by of this application assign journal.
	 *
	 * @param CreatedBy the created by of this application assign journal
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this application assign journal.
	 *
	 * @param CreatedDate the created date of this application assign journal
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the ID of this application assign journal.
	 *
	 * @param Id the ID of this application assign journal
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this application assign journal.
	 *
	 * @param ModifiedBy the modified by of this application assign journal
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this application assign journal.
	 *
	 * @param ModifiedDate the modified date of this application assign journal
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the notes of this application assign journal.
	 *
	 * @param Notes the notes of this application assign journal
	 */
	@Override
	public void setNotes(String Notes) {
		model.setNotes(Notes);
	}

	/**
	 * Sets the primary key of this application assign journal.
	 *
	 * @param primaryKey the primary key of this application assign journal
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the profile ID of this application assign journal.
	 *
	 * @param ProfileId the profile ID of this application assign journal
	 */
	@Override
	public void setProfileId(long ProfileId) {
		model.setProfileId(ProfileId);
	}

	/**
	 * Sets the row status of this application assign journal.
	 *
	 * @param RowStatus the row status of this application assign journal
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the start date on of this application assign journal.
	 *
	 * @param StartDateOn the start date on of this application assign journal
	 */
	@Override
	public void setStartDateOn(Date StartDateOn) {
		model.setStartDateOn(StartDateOn);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected ApplicationAssignJournalWrapper wrap(
		ApplicationAssignJournal applicationAssignJournal) {

		return new ApplicationAssignJournalWrapper(applicationAssignJournal);
	}

}