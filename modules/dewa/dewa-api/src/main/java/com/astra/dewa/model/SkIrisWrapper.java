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
 * This class is a wrapper for {@link SkIris}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SkIris
 * @generated
 */
public class SkIrisWrapper
	extends BaseModelWrapper<SkIris> implements ModelWrapper<SkIris>, SkIris {

	public SkIrisWrapper(SkIris skIris) {
		super(skIris);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("Periode", getPeriode());
		attributes.put("DealerId", getDealerId());
		attributes.put("WilayahId", getWilayahId());
		attributes.put("Tahun", getTahun());
		attributes.put("Kategori", getKategori());
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

		String Periode = (String)attributes.get("Periode");

		if (Periode != null) {
			setPeriode(Periode);
		}

		Integer DealerId = (Integer)attributes.get("DealerId");

		if (DealerId != null) {
			setDealerId(DealerId);
		}

		Long WilayahId = (Long)attributes.get("WilayahId");

		if (WilayahId != null) {
			setWilayahId(WilayahId);
		}

		Integer Tahun = (Integer)attributes.get("Tahun");

		if (Tahun != null) {
			setTahun(Tahun);
		}

		String Kategori = (String)attributes.get("Kategori");

		if (Kategori != null) {
			setKategori(Kategori);
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
	public SkIris cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this sk iris.
	 *
	 * @return the created by of this sk iris
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this sk iris.
	 *
	 * @return the created date of this sk iris
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the dealer ID of this sk iris.
	 *
	 * @return the dealer ID of this sk iris
	 */
	@Override
	public int getDealerId() {
		return model.getDealerId();
	}

	/**
	 * Returns the file ID of this sk iris.
	 *
	 * @return the file ID of this sk iris
	 */
	@Override
	public Long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the file name of this sk iris.
	 *
	 * @return the file name of this sk iris
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the file path of this sk iris.
	 *
	 * @return the file path of this sk iris
	 */
	@Override
	public String getFilePath() {
		return model.getFilePath();
	}

	/**
	 * Returns the ID of this sk iris.
	 *
	 * @return the ID of this sk iris
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the kategori of this sk iris.
	 *
	 * @return the kategori of this sk iris
	 */
	@Override
	public String getKategori() {
		return model.getKategori();
	}

	/**
	 * Returns the modified by of this sk iris.
	 *
	 * @return the modified by of this sk iris
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this sk iris.
	 *
	 * @return the modified date of this sk iris
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the periode of this sk iris.
	 *
	 * @return the periode of this sk iris
	 */
	@Override
	public String getPeriode() {
		return model.getPeriode();
	}

	/**
	 * Returns the primary key of this sk iris.
	 *
	 * @return the primary key of this sk iris
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this sk iris.
	 *
	 * @return the row status of this sk iris
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the tahun of this sk iris.
	 *
	 * @return the tahun of this sk iris
	 */
	@Override
	public Integer getTahun() {
		return model.getTahun();
	}

	/**
	 * Returns the wilayah ID of this sk iris.
	 *
	 * @return the wilayah ID of this sk iris
	 */
	@Override
	public long getWilayahId() {
		return model.getWilayahId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the created by of this sk iris.
	 *
	 * @param CreatedBy the created by of this sk iris
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this sk iris.
	 *
	 * @param CreatedDate the created date of this sk iris
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the dealer ID of this sk iris.
	 *
	 * @param DealerId the dealer ID of this sk iris
	 */
	@Override
	public void setDealerId(int DealerId) {
		model.setDealerId(DealerId);
	}

	/**
	 * Sets the file ID of this sk iris.
	 *
	 * @param FileId the file ID of this sk iris
	 */
	@Override
	public void setFileId(Long FileId) {
		model.setFileId(FileId);
	}

	/**
	 * Sets the file name of this sk iris.
	 *
	 * @param FileName the file name of this sk iris
	 */
	@Override
	public void setFileName(String FileName) {
		model.setFileName(FileName);
	}

	/**
	 * Sets the file path of this sk iris.
	 *
	 * @param FilePath the file path of this sk iris
	 */
	@Override
	public void setFilePath(String FilePath) {
		model.setFilePath(FilePath);
	}

	/**
	 * Sets the ID of this sk iris.
	 *
	 * @param Id the ID of this sk iris
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the kategori of this sk iris.
	 *
	 * @param Kategori the kategori of this sk iris
	 */
	@Override
	public void setKategori(String Kategori) {
		model.setKategori(Kategori);
	}

	/**
	 * Sets the modified by of this sk iris.
	 *
	 * @param ModifiedBy the modified by of this sk iris
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this sk iris.
	 *
	 * @param ModifiedDate the modified date of this sk iris
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the periode of this sk iris.
	 *
	 * @param Periode the periode of this sk iris
	 */
	@Override
	public void setPeriode(String Periode) {
		model.setPeriode(Periode);
	}

	/**
	 * Sets the primary key of this sk iris.
	 *
	 * @param primaryKey the primary key of this sk iris
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the row status of this sk iris.
	 *
	 * @param RowStatus the row status of this sk iris
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the tahun of this sk iris.
	 *
	 * @param Tahun the tahun of this sk iris
	 */
	@Override
	public void setTahun(Integer Tahun) {
		model.setTahun(Tahun);
	}

	/**
	 * Sets the wilayah ID of this sk iris.
	 *
	 * @param WilayahId the wilayah ID of this sk iris
	 */
	@Override
	public void setWilayahId(long WilayahId) {
		model.setWilayahId(WilayahId);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected SkIrisWrapper wrap(SkIris skIris) {
		return new SkIrisWrapper(skIris);
	}

}