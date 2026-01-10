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
 * This class is a wrapper for {@link DiskonTestCar}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiskonTestCar
 * @generated
 */
public class DiskonTestCarWrapper
	extends BaseModelWrapper<DiskonTestCar>
	implements DiskonTestCar, ModelWrapper<DiskonTestCar> {

	public DiskonTestCarWrapper(DiskonTestCar diskonTestCar) {
		super(diskonTestCar);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("DealerId", getDealerId());
		attributes.put("KuartalId", getKuartalId());
		attributes.put("TipeKendaraanId", getTipeKendaraanId());
		attributes.put("Tahun", getTahun());
		attributes.put("Keterangan", getKeterangan());
		attributes.put("FileId", getFileId());
		attributes.put("FileName", getFileName());
		attributes.put("FilePath", getFilePath());
		attributes.put("RowStatus", isRowStatus());
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

		String KuartalId = (String)attributes.get("KuartalId");

		if (KuartalId != null) {
			setKuartalId(KuartalId);
		}

		Integer TipeKendaraanId = (Integer)attributes.get("TipeKendaraanId");

		if (TipeKendaraanId != null) {
			setTipeKendaraanId(TipeKendaraanId);
		}

		Integer Tahun = (Integer)attributes.get("Tahun");

		if (Tahun != null) {
			setTahun(Tahun);
		}

		String Keterangan = (String)attributes.get("Keterangan");

		if (Keterangan != null) {
			setKeterangan(Keterangan);
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
	public DiskonTestCar cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this diskon test car.
	 *
	 * @return the created by of this diskon test car
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this diskon test car.
	 *
	 * @return the created date of this diskon test car
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the dealer ID of this diskon test car.
	 *
	 * @return the dealer ID of this diskon test car
	 */
	@Override
	public int getDealerId() {
		return model.getDealerId();
	}

	/**
	 * Returns the file ID of this diskon test car.
	 *
	 * @return the file ID of this diskon test car
	 */
	@Override
	public Long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the file name of this diskon test car.
	 *
	 * @return the file name of this diskon test car
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the file path of this diskon test car.
	 *
	 * @return the file path of this diskon test car
	 */
	@Override
	public String getFilePath() {
		return model.getFilePath();
	}

	/**
	 * Returns the ID of this diskon test car.
	 *
	 * @return the ID of this diskon test car
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the keterangan of this diskon test car.
	 *
	 * @return the keterangan of this diskon test car
	 */
	@Override
	public String getKeterangan() {
		return model.getKeterangan();
	}

	/**
	 * Returns the kuartal ID of this diskon test car.
	 *
	 * @return the kuartal ID of this diskon test car
	 */
	@Override
	public String getKuartalId() {
		return model.getKuartalId();
	}

	/**
	 * Returns the modified by of this diskon test car.
	 *
	 * @return the modified by of this diskon test car
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this diskon test car.
	 *
	 * @return the modified date of this diskon test car
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this diskon test car.
	 *
	 * @return the primary key of this diskon test car
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this diskon test car.
	 *
	 * @return the row status of this diskon test car
	 */
	@Override
	public boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the tahun of this diskon test car.
	 *
	 * @return the tahun of this diskon test car
	 */
	@Override
	public Integer getTahun() {
		return model.getTahun();
	}

	/**
	 * Returns the tipe kendaraan ID of this diskon test car.
	 *
	 * @return the tipe kendaraan ID of this diskon test car
	 */
	@Override
	public int getTipeKendaraanId() {
		return model.getTipeKendaraanId();
	}

	/**
	 * Returns <code>true</code> if this diskon test car is row status.
	 *
	 * @return <code>true</code> if this diskon test car is row status; <code>false</code> otherwise
	 */
	@Override
	public boolean isRowStatus() {
		return model.isRowStatus();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the created by of this diskon test car.
	 *
	 * @param CreatedBy the created by of this diskon test car
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this diskon test car.
	 *
	 * @param CreatedDate the created date of this diskon test car
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the dealer ID of this diskon test car.
	 *
	 * @param DealerId the dealer ID of this diskon test car
	 */
	@Override
	public void setDealerId(int DealerId) {
		model.setDealerId(DealerId);
	}

	/**
	 * Sets the file ID of this diskon test car.
	 *
	 * @param FileId the file ID of this diskon test car
	 */
	@Override
	public void setFileId(Long FileId) {
		model.setFileId(FileId);
	}

	/**
	 * Sets the file name of this diskon test car.
	 *
	 * @param FileName the file name of this diskon test car
	 */
	@Override
	public void setFileName(String FileName) {
		model.setFileName(FileName);
	}

	/**
	 * Sets the file path of this diskon test car.
	 *
	 * @param FilePath the file path of this diskon test car
	 */
	@Override
	public void setFilePath(String FilePath) {
		model.setFilePath(FilePath);
	}

	/**
	 * Sets the ID of this diskon test car.
	 *
	 * @param Id the ID of this diskon test car
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the keterangan of this diskon test car.
	 *
	 * @param Keterangan the keterangan of this diskon test car
	 */
	@Override
	public void setKeterangan(String Keterangan) {
		model.setKeterangan(Keterangan);
	}

	/**
	 * Sets the kuartal ID of this diskon test car.
	 *
	 * @param KuartalId the kuartal ID of this diskon test car
	 */
	@Override
	public void setKuartalId(String KuartalId) {
		model.setKuartalId(KuartalId);
	}

	/**
	 * Sets the modified by of this diskon test car.
	 *
	 * @param ModifiedBy the modified by of this diskon test car
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this diskon test car.
	 *
	 * @param ModifiedDate the modified date of this diskon test car
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this diskon test car.
	 *
	 * @param primaryKey the primary key of this diskon test car
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this diskon test car is row status.
	 *
	 * @param RowStatus the row status of this diskon test car
	 */
	@Override
	public void setRowStatus(boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the tahun of this diskon test car.
	 *
	 * @param Tahun the tahun of this diskon test car
	 */
	@Override
	public void setTahun(Integer Tahun) {
		model.setTahun(Tahun);
	}

	/**
	 * Sets the tipe kendaraan ID of this diskon test car.
	 *
	 * @param TipeKendaraanId the tipe kendaraan ID of this diskon test car
	 */
	@Override
	public void setTipeKendaraanId(int TipeKendaraanId) {
		model.setTipeKendaraanId(TipeKendaraanId);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected DiskonTestCarWrapper wrap(DiskonTestCar diskonTestCar) {
		return new DiskonTestCarWrapper(diskonTestCar);
	}

}