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
 * This class is a wrapper for {@link TrainingAgenda}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingAgenda
 * @generated
 */
public class TrainingAgendaWrapper
	extends BaseModelWrapper<TrainingAgenda>
	implements ModelWrapper<TrainingAgenda>, TrainingAgenda {

	public TrainingAgendaWrapper(TrainingAgenda trainingAgenda) {
		super(trainingAgenda);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("JenisAgenda", getJenisAgenda());
		attributes.put("StatusAgenda", getStatusAgenda());
		attributes.put("Judul", getJudul());
		attributes.put("Location", getLocation());
		attributes.put("LinkMeeting", getLinkMeeting());
		attributes.put("Deskripsi", getDeskripsi());
		attributes.put("StartDate", getStartDate());
		attributes.put("StartDateHours", getStartDateHours());
		attributes.put("EndDate", getEndDate());
		attributes.put("EndDateHours", getEndDateHours());
		attributes.put("RegistrationLimitDate", getRegistrationLimitDate());
		attributes.put(
			"RegistrationLimitDateHours", getRegistrationLimitDateHours());
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

		Integer JenisAgenda = (Integer)attributes.get("JenisAgenda");

		if (JenisAgenda != null) {
			setJenisAgenda(JenisAgenda);
		}

		Integer StatusAgenda = (Integer)attributes.get("StatusAgenda");

		if (StatusAgenda != null) {
			setStatusAgenda(StatusAgenda);
		}

		String Judul = (String)attributes.get("Judul");

		if (Judul != null) {
			setJudul(Judul);
		}

		String Location = (String)attributes.get("Location");

		if (Location != null) {
			setLocation(Location);
		}

		String LinkMeeting = (String)attributes.get("LinkMeeting");

		if (LinkMeeting != null) {
			setLinkMeeting(LinkMeeting);
		}

		String Deskripsi = (String)attributes.get("Deskripsi");

		if (Deskripsi != null) {
			setDeskripsi(Deskripsi);
		}

		Date StartDate = (Date)attributes.get("StartDate");

		if (StartDate != null) {
			setStartDate(StartDate);
		}

		String StartDateHours = (String)attributes.get("StartDateHours");

		if (StartDateHours != null) {
			setStartDateHours(StartDateHours);
		}

		Date EndDate = (Date)attributes.get("EndDate");

		if (EndDate != null) {
			setEndDate(EndDate);
		}

		String EndDateHours = (String)attributes.get("EndDateHours");

		if (EndDateHours != null) {
			setEndDateHours(EndDateHours);
		}

		Date RegistrationLimitDate = (Date)attributes.get(
			"RegistrationLimitDate");

		if (RegistrationLimitDate != null) {
			setRegistrationLimitDate(RegistrationLimitDate);
		}

		String RegistrationLimitDateHours = (String)attributes.get(
			"RegistrationLimitDateHours");

		if (RegistrationLimitDateHours != null) {
			setRegistrationLimitDateHours(RegistrationLimitDateHours);
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
	public TrainingAgenda cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this training agenda.
	 *
	 * @return the created by of this training agenda
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this training agenda.
	 *
	 * @return the created date of this training agenda
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the deskripsi of this training agenda.
	 *
	 * @return the deskripsi of this training agenda
	 */
	@Override
	public String getDeskripsi() {
		return model.getDeskripsi();
	}

	/**
	 * Returns the end date of this training agenda.
	 *
	 * @return the end date of this training agenda
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the end date hours of this training agenda.
	 *
	 * @return the end date hours of this training agenda
	 */
	@Override
	public String getEndDateHours() {
		return model.getEndDateHours();
	}

	/**
	 * Returns the ID of this training agenda.
	 *
	 * @return the ID of this training agenda
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the image ID of this training agenda.
	 *
	 * @return the image ID of this training agenda
	 */
	@Override
	public Long getImageId() {
		return model.getImageId();
	}

	/**
	 * Returns the image name of this training agenda.
	 *
	 * @return the image name of this training agenda
	 */
	@Override
	public String getImageName() {
		return model.getImageName();
	}

	/**
	 * Returns the image path of this training agenda.
	 *
	 * @return the image path of this training agenda
	 */
	@Override
	public String getImagePath() {
		return model.getImagePath();
	}

	/**
	 * Returns the jenis agenda of this training agenda.
	 *
	 * @return the jenis agenda of this training agenda
	 */
	@Override
	public int getJenisAgenda() {
		return model.getJenisAgenda();
	}

	/**
	 * Returns the judul of this training agenda.
	 *
	 * @return the judul of this training agenda
	 */
	@Override
	public String getJudul() {
		return model.getJudul();
	}

	/**
	 * Returns the link meeting of this training agenda.
	 *
	 * @return the link meeting of this training agenda
	 */
	@Override
	public String getLinkMeeting() {
		return model.getLinkMeeting();
	}

	/**
	 * Returns the location of this training agenda.
	 *
	 * @return the location of this training agenda
	 */
	@Override
	public String getLocation() {
		return model.getLocation();
	}

	/**
	 * Returns the modified by of this training agenda.
	 *
	 * @return the modified by of this training agenda
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this training agenda.
	 *
	 * @return the modified date of this training agenda
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this training agenda.
	 *
	 * @return the primary key of this training agenda
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the registration limit date of this training agenda.
	 *
	 * @return the registration limit date of this training agenda
	 */
	@Override
	public Date getRegistrationLimitDate() {
		return model.getRegistrationLimitDate();
	}

	/**
	 * Returns the registration limit date hours of this training agenda.
	 *
	 * @return the registration limit date hours of this training agenda
	 */
	@Override
	public String getRegistrationLimitDateHours() {
		return model.getRegistrationLimitDateHours();
	}

	/**
	 * Returns the row status of this training agenda.
	 *
	 * @return the row status of this training agenda
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the start date of this training agenda.
	 *
	 * @return the start date of this training agenda
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the start date hours of this training agenda.
	 *
	 * @return the start date hours of this training agenda
	 */
	@Override
	public String getStartDateHours() {
		return model.getStartDateHours();
	}

	/**
	 * Returns the status agenda of this training agenda.
	 *
	 * @return the status agenda of this training agenda
	 */
	@Override
	public int getStatusAgenda() {
		return model.getStatusAgenda();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the created by of this training agenda.
	 *
	 * @param CreatedBy the created by of this training agenda
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this training agenda.
	 *
	 * @param CreatedDate the created date of this training agenda
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the deskripsi of this training agenda.
	 *
	 * @param Deskripsi the deskripsi of this training agenda
	 */
	@Override
	public void setDeskripsi(String Deskripsi) {
		model.setDeskripsi(Deskripsi);
	}

	/**
	 * Sets the end date of this training agenda.
	 *
	 * @param EndDate the end date of this training agenda
	 */
	@Override
	public void setEndDate(Date EndDate) {
		model.setEndDate(EndDate);
	}

	/**
	 * Sets the end date hours of this training agenda.
	 *
	 * @param EndDateHours the end date hours of this training agenda
	 */
	@Override
	public void setEndDateHours(String EndDateHours) {
		model.setEndDateHours(EndDateHours);
	}

	/**
	 * Sets the ID of this training agenda.
	 *
	 * @param Id the ID of this training agenda
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the image ID of this training agenda.
	 *
	 * @param ImageId the image ID of this training agenda
	 */
	@Override
	public void setImageId(Long ImageId) {
		model.setImageId(ImageId);
	}

	/**
	 * Sets the image name of this training agenda.
	 *
	 * @param ImageName the image name of this training agenda
	 */
	@Override
	public void setImageName(String ImageName) {
		model.setImageName(ImageName);
	}

	/**
	 * Sets the image path of this training agenda.
	 *
	 * @param ImagePath the image path of this training agenda
	 */
	@Override
	public void setImagePath(String ImagePath) {
		model.setImagePath(ImagePath);
	}

	/**
	 * Sets the jenis agenda of this training agenda.
	 *
	 * @param JenisAgenda the jenis agenda of this training agenda
	 */
	@Override
	public void setJenisAgenda(int JenisAgenda) {
		model.setJenisAgenda(JenisAgenda);
	}

	/**
	 * Sets the judul of this training agenda.
	 *
	 * @param Judul the judul of this training agenda
	 */
	@Override
	public void setJudul(String Judul) {
		model.setJudul(Judul);
	}

	/**
	 * Sets the link meeting of this training agenda.
	 *
	 * @param LinkMeeting the link meeting of this training agenda
	 */
	@Override
	public void setLinkMeeting(String LinkMeeting) {
		model.setLinkMeeting(LinkMeeting);
	}

	/**
	 * Sets the location of this training agenda.
	 *
	 * @param Location the location of this training agenda
	 */
	@Override
	public void setLocation(String Location) {
		model.setLocation(Location);
	}

	/**
	 * Sets the modified by of this training agenda.
	 *
	 * @param ModifiedBy the modified by of this training agenda
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this training agenda.
	 *
	 * @param ModifiedDate the modified date of this training agenda
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this training agenda.
	 *
	 * @param primaryKey the primary key of this training agenda
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the registration limit date of this training agenda.
	 *
	 * @param RegistrationLimitDate the registration limit date of this training agenda
	 */
	@Override
	public void setRegistrationLimitDate(Date RegistrationLimitDate) {
		model.setRegistrationLimitDate(RegistrationLimitDate);
	}

	/**
	 * Sets the registration limit date hours of this training agenda.
	 *
	 * @param RegistrationLimitDateHours the registration limit date hours of this training agenda
	 */
	@Override
	public void setRegistrationLimitDateHours(
		String RegistrationLimitDateHours) {

		model.setRegistrationLimitDateHours(RegistrationLimitDateHours);
	}

	/**
	 * Sets the row status of this training agenda.
	 *
	 * @param RowStatus the row status of this training agenda
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the start date of this training agenda.
	 *
	 * @param StartDate the start date of this training agenda
	 */
	@Override
	public void setStartDate(Date StartDate) {
		model.setStartDate(StartDate);
	}

	/**
	 * Sets the start date hours of this training agenda.
	 *
	 * @param StartDateHours the start date hours of this training agenda
	 */
	@Override
	public void setStartDateHours(String StartDateHours) {
		model.setStartDateHours(StartDateHours);
	}

	/**
	 * Sets the status agenda of this training agenda.
	 *
	 * @param StatusAgenda the status agenda of this training agenda
	 */
	@Override
	public void setStatusAgenda(int StatusAgenda) {
		model.setStatusAgenda(StatusAgenda);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected TrainingAgendaWrapper wrap(TrainingAgenda trainingAgenda) {
		return new TrainingAgendaWrapper(trainingAgenda);
	}

}