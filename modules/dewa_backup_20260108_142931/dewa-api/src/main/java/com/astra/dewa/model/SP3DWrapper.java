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
 * This class is a wrapper for {@link SP3D}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SP3D
 * @generated
 */
public class SP3DWrapper
	extends BaseModelWrapper<SP3D> implements ModelWrapper<SP3D>, SP3D {

	public SP3DWrapper(SP3D sp3d) {
		super(sp3d);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("DealerId", getDealerId());
		attributes.put("Tahun", getTahun());
		attributes.put("Bulan", getBulan());
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

		Integer DealerId = (Integer)attributes.get("DealerId");

		if (DealerId != null) {
			setDealerId(DealerId);
		}

		Integer Tahun = (Integer)attributes.get("Tahun");

		if (Tahun != null) {
			setTahun(Tahun);
		}

		String Bulan = (String)attributes.get("Bulan");

		if (Bulan != null) {
			setBulan(Bulan);
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
	public SP3D cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the bulan of this sp3d.
	 *
	 * @return the bulan of this sp3d
	 */
	@Override
	public String getBulan() {
		return model.getBulan();
	}

	/**
	 * Returns the created by of this sp3d.
	 *
	 * @return the created by of this sp3d
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this sp3d.
	 *
	 * @return the created date of this sp3d
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the dealer ID of this sp3d.
	 *
	 * @return the dealer ID of this sp3d
	 */
	@Override
	public int getDealerId() {
		return model.getDealerId();
	}

	/**
	 * Returns the file ID of this sp3d.
	 *
	 * @return the file ID of this sp3d
	 */
	@Override
	public Long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the file name of this sp3d.
	 *
	 * @return the file name of this sp3d
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the file path of this sp3d.
	 *
	 * @return the file path of this sp3d
	 */
	@Override
	public String getFilePath() {
		return model.getFilePath();
	}

	/**
	 * Returns the ID of this sp3d.
	 *
	 * @return the ID of this sp3d
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this sp3d.
	 *
	 * @return the modified by of this sp3d
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this sp3d.
	 *
	 * @return the modified date of this sp3d
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this sp3d.
	 *
	 * @return the primary key of this sp3d
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this sp3d.
	 *
	 * @return the row status of this sp3d
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the tahun of this sp3d.
	 *
	 * @return the tahun of this sp3d
	 */
	@Override
	public Integer getTahun() {
		return model.getTahun();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the bulan of this sp3d.
	 *
	 * @param Bulan the bulan of this sp3d
	 */
	@Override
	public void setBulan(String Bulan) {
		model.setBulan(Bulan);
	}

	/**
	 * Sets the created by of this sp3d.
	 *
	 * @param CreatedBy the created by of this sp3d
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this sp3d.
	 *
	 * @param CreatedDate the created date of this sp3d
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the dealer ID of this sp3d.
	 *
	 * @param DealerId the dealer ID of this sp3d
	 */
	@Override
	public void setDealerId(int DealerId) {
		model.setDealerId(DealerId);
	}

	/**
	 * Sets the file ID of this sp3d.
	 *
	 * @param FileId the file ID of this sp3d
	 */
	@Override
	public void setFileId(Long FileId) {
		model.setFileId(FileId);
	}

	/**
	 * Sets the file name of this sp3d.
	 *
	 * @param FileName the file name of this sp3d
	 */
	@Override
	public void setFileName(String FileName) {
		model.setFileName(FileName);
	}

	/**
	 * Sets the file path of this sp3d.
	 *
	 * @param FilePath the file path of this sp3d
	 */
	@Override
	public void setFilePath(String FilePath) {
		model.setFilePath(FilePath);
	}

	/**
	 * Sets the ID of this sp3d.
	 *
	 * @param Id the ID of this sp3d
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this sp3d.
	 *
	 * @param ModifiedBy the modified by of this sp3d
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this sp3d.
	 *
	 * @param ModifiedDate the modified date of this sp3d
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this sp3d.
	 *
	 * @param primaryKey the primary key of this sp3d
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the row status of this sp3d.
	 *
	 * @param RowStatus the row status of this sp3d
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the tahun of this sp3d.
	 *
	 * @param Tahun the tahun of this sp3d
	 */
	@Override
	public void setTahun(Integer Tahun) {
		model.setTahun(Tahun);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected SP3DWrapper wrap(SP3D sp3d) {
		return new SP3DWrapper(sp3d);
	}

}