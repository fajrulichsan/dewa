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
 * This class is a wrapper for {@link CalenderEvent}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CalenderEvent
 * @generated
 */
public class CalenderEventWrapper
	extends BaseModelWrapper<CalenderEvent>
	implements CalenderEvent, ModelWrapper<CalenderEvent> {

	public CalenderEventWrapper(CalenderEvent calenderEvent) {
		super(calenderEvent);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("JenisAcara", getJenisAcara());
		attributes.put("StatusAcara", getStatusAcara());
		attributes.put("Judul", getJudul());
		attributes.put("Location", getLocation());
		attributes.put("LinkMeeting", getLinkMeeting());
		attributes.put("StartDate", getStartDate());
		attributes.put("StartDateHours", getStartDateHours());
		attributes.put("EndDate", getEndDate());
		attributes.put("EndDateHours", getEndDateHours());
		attributes.put("RegistrationLimitDate", getRegistrationLimitDate());
		attributes.put(
			"RegistrationLimitDateHours", getRegistrationLimitDateHours());
		attributes.put("Deskripsi", getDeskripsi());
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

		Integer JenisAcara = (Integer)attributes.get("JenisAcara");

		if (JenisAcara != null) {
			setJenisAcara(JenisAcara);
		}

		Integer StatusAcara = (Integer)attributes.get("StatusAcara");

		if (StatusAcara != null) {
			setStatusAcara(StatusAcara);
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

		String Deskripsi = (String)attributes.get("Deskripsi");

		if (Deskripsi != null) {
			setDeskripsi(Deskripsi);
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
	public CalenderEvent cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this calender event.
	 *
	 * @return the created by of this calender event
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this calender event.
	 *
	 * @return the created date of this calender event
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the deskripsi of this calender event.
	 *
	 * @return the deskripsi of this calender event
	 */
	@Override
	public String getDeskripsi() {
		return model.getDeskripsi();
	}

	/**
	 * Returns the end date of this calender event.
	 *
	 * @return the end date of this calender event
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the end date hours of this calender event.
	 *
	 * @return the end date hours of this calender event
	 */
	@Override
	public String getEndDateHours() {
		return model.getEndDateHours();
	}

	/**
	 * Returns the ID of this calender event.
	 *
	 * @return the ID of this calender event
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the image ID of this calender event.
	 *
	 * @return the image ID of this calender event
	 */
	@Override
	public Long getImageId() {
		return model.getImageId();
	}

	/**
	 * Returns the image name of this calender event.
	 *
	 * @return the image name of this calender event
	 */
	@Override
	public String getImageName() {
		return model.getImageName();
	}

	/**
	 * Returns the image path of this calender event.
	 *
	 * @return the image path of this calender event
	 */
	@Override
	public String getImagePath() {
		return model.getImagePath();
	}

	/**
	 * Returns the jenis acara of this calender event.
	 *
	 * @return the jenis acara of this calender event
	 */
	@Override
	public int getJenisAcara() {
		return model.getJenisAcara();
	}

	/**
	 * Returns the judul of this calender event.
	 *
	 * @return the judul of this calender event
	 */
	@Override
	public String getJudul() {
		return model.getJudul();
	}

	/**
	 * Returns the link meeting of this calender event.
	 *
	 * @return the link meeting of this calender event
	 */
	@Override
	public String getLinkMeeting() {
		return model.getLinkMeeting();
	}

	/**
	 * Returns the location of this calender event.
	 *
	 * @return the location of this calender event
	 */
	@Override
	public String getLocation() {
		return model.getLocation();
	}

	/**
	 * Returns the modified by of this calender event.
	 *
	 * @return the modified by of this calender event
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this calender event.
	 *
	 * @return the modified date of this calender event
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this calender event.
	 *
	 * @return the primary key of this calender event
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the registration limit date of this calender event.
	 *
	 * @return the registration limit date of this calender event
	 */
	@Override
	public Date getRegistrationLimitDate() {
		return model.getRegistrationLimitDate();
	}

	/**
	 * Returns the registration limit date hours of this calender event.
	 *
	 * @return the registration limit date hours of this calender event
	 */
	@Override
	public String getRegistrationLimitDateHours() {
		return model.getRegistrationLimitDateHours();
	}

	/**
	 * Returns the row status of this calender event.
	 *
	 * @return the row status of this calender event
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the start date of this calender event.
	 *
	 * @return the start date of this calender event
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the start date hours of this calender event.
	 *
	 * @return the start date hours of this calender event
	 */
	@Override
	public String getStartDateHours() {
		return model.getStartDateHours();
	}

	/**
	 * Returns the status acara of this calender event.
	 *
	 * @return the status acara of this calender event
	 */
	@Override
	public int getStatusAcara() {
		return model.getStatusAcara();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the created by of this calender event.
	 *
	 * @param CreatedBy the created by of this calender event
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this calender event.
	 *
	 * @param CreatedDate the created date of this calender event
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the deskripsi of this calender event.
	 *
	 * @param Deskripsi the deskripsi of this calender event
	 */
	@Override
	public void setDeskripsi(String Deskripsi) {
		model.setDeskripsi(Deskripsi);
	}

	/**
	 * Sets the end date of this calender event.
	 *
	 * @param EndDate the end date of this calender event
	 */
	@Override
	public void setEndDate(Date EndDate) {
		model.setEndDate(EndDate);
	}

	/**
	 * Sets the end date hours of this calender event.
	 *
	 * @param EndDateHours the end date hours of this calender event
	 */
	@Override
	public void setEndDateHours(String EndDateHours) {
		model.setEndDateHours(EndDateHours);
	}

	/**
	 * Sets the ID of this calender event.
	 *
	 * @param Id the ID of this calender event
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the image ID of this calender event.
	 *
	 * @param ImageId the image ID of this calender event
	 */
	@Override
	public void setImageId(Long ImageId) {
		model.setImageId(ImageId);
	}

	/**
	 * Sets the image name of this calender event.
	 *
	 * @param ImageName the image name of this calender event
	 */
	@Override
	public void setImageName(String ImageName) {
		model.setImageName(ImageName);
	}

	/**
	 * Sets the image path of this calender event.
	 *
	 * @param ImagePath the image path of this calender event
	 */
	@Override
	public void setImagePath(String ImagePath) {
		model.setImagePath(ImagePath);
	}

	/**
	 * Sets the jenis acara of this calender event.
	 *
	 * @param JenisAcara the jenis acara of this calender event
	 */
	@Override
	public void setJenisAcara(int JenisAcara) {
		model.setJenisAcara(JenisAcara);
	}

	/**
	 * Sets the judul of this calender event.
	 *
	 * @param Judul the judul of this calender event
	 */
	@Override
	public void setJudul(String Judul) {
		model.setJudul(Judul);
	}

	/**
	 * Sets the link meeting of this calender event.
	 *
	 * @param LinkMeeting the link meeting of this calender event
	 */
	@Override
	public void setLinkMeeting(String LinkMeeting) {
		model.setLinkMeeting(LinkMeeting);
	}

	/**
	 * Sets the location of this calender event.
	 *
	 * @param Location the location of this calender event
	 */
	@Override
	public void setLocation(String Location) {
		model.setLocation(Location);
	}

	/**
	 * Sets the modified by of this calender event.
	 *
	 * @param ModifiedBy the modified by of this calender event
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this calender event.
	 *
	 * @param ModifiedDate the modified date of this calender event
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this calender event.
	 *
	 * @param primaryKey the primary key of this calender event
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the registration limit date of this calender event.
	 *
	 * @param RegistrationLimitDate the registration limit date of this calender event
	 */
	@Override
	public void setRegistrationLimitDate(Date RegistrationLimitDate) {
		model.setRegistrationLimitDate(RegistrationLimitDate);
	}

	/**
	 * Sets the registration limit date hours of this calender event.
	 *
	 * @param RegistrationLimitDateHours the registration limit date hours of this calender event
	 */
	@Override
	public void setRegistrationLimitDateHours(
		String RegistrationLimitDateHours) {

		model.setRegistrationLimitDateHours(RegistrationLimitDateHours);
	}

	/**
	 * Sets the row status of this calender event.
	 *
	 * @param RowStatus the row status of this calender event
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the start date of this calender event.
	 *
	 * @param StartDate the start date of this calender event
	 */
	@Override
	public void setStartDate(Date StartDate) {
		model.setStartDate(StartDate);
	}

	/**
	 * Sets the start date hours of this calender event.
	 *
	 * @param StartDateHours the start date hours of this calender event
	 */
	@Override
	public void setStartDateHours(String StartDateHours) {
		model.setStartDateHours(StartDateHours);
	}

	/**
	 * Sets the status acara of this calender event.
	 *
	 * @param StatusAcara the status acara of this calender event
	 */
	@Override
	public void setStatusAcara(int StatusAcara) {
		model.setStatusAcara(StatusAcara);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected CalenderEventWrapper wrap(CalenderEvent calenderEvent) {
		return new CalenderEventWrapper(calenderEvent);
	}

}