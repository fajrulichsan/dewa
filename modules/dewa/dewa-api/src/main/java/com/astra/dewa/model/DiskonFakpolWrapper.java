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
 * This class is a wrapper for {@link DiskonFakpol}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiskonFakpol
 * @generated
 */
public class DiskonFakpolWrapper
	extends BaseModelWrapper<DiskonFakpol>
	implements DiskonFakpol, ModelWrapper<DiskonFakpol> {

	public DiskonFakpolWrapper(DiskonFakpol diskonFakpol) {
		super(diskonFakpol);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("DealerId", getDealerId());
		attributes.put("Tahun", getTahun());
		attributes.put("Periode", getPeriode());
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

		Integer Tahun = (Integer)attributes.get("Tahun");

		if (Tahun != null) {
			setTahun(Tahun);
		}

		String Periode = (String)attributes.get("Periode");

		if (Periode != null) {
			setPeriode(Periode);
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
	public DiskonFakpol cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this diskon fakpol.
	 *
	 * @return the created by of this diskon fakpol
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this diskon fakpol.
	 *
	 * @return the created date of this diskon fakpol
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the dealer ID of this diskon fakpol.
	 *
	 * @return the dealer ID of this diskon fakpol
	 */
	@Override
	public int getDealerId() {
		return model.getDealerId();
	}

	/**
	 * Returns the file ID of this diskon fakpol.
	 *
	 * @return the file ID of this diskon fakpol
	 */
	@Override
	public Long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the file name of this diskon fakpol.
	 *
	 * @return the file name of this diskon fakpol
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the file path of this diskon fakpol.
	 *
	 * @return the file path of this diskon fakpol
	 */
	@Override
	public String getFilePath() {
		return model.getFilePath();
	}

	/**
	 * Returns the ID of this diskon fakpol.
	 *
	 * @return the ID of this diskon fakpol
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the keterangan of this diskon fakpol.
	 *
	 * @return the keterangan of this diskon fakpol
	 */
	@Override
	public String getKeterangan() {
		return model.getKeterangan();
	}

	/**
	 * Returns the modified by of this diskon fakpol.
	 *
	 * @return the modified by of this diskon fakpol
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this diskon fakpol.
	 *
	 * @return the modified date of this diskon fakpol
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the periode of this diskon fakpol.
	 *
	 * @return the periode of this diskon fakpol
	 */
	@Override
	public String getPeriode() {
		return model.getPeriode();
	}

	/**
	 * Returns the primary key of this diskon fakpol.
	 *
	 * @return the primary key of this diskon fakpol
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this diskon fakpol.
	 *
	 * @return the row status of this diskon fakpol
	 */
	@Override
	public boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the tahun of this diskon fakpol.
	 *
	 * @return the tahun of this diskon fakpol
	 */
	@Override
	public Integer getTahun() {
		return model.getTahun();
	}

	/**
	 * Returns <code>true</code> if this diskon fakpol is row status.
	 *
	 * @return <code>true</code> if this diskon fakpol is row status; <code>false</code> otherwise
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
	 * Sets the created by of this diskon fakpol.
	 *
	 * @param CreatedBy the created by of this diskon fakpol
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this diskon fakpol.
	 *
	 * @param CreatedDate the created date of this diskon fakpol
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the dealer ID of this diskon fakpol.
	 *
	 * @param DealerId the dealer ID of this diskon fakpol
	 */
	@Override
	public void setDealerId(int DealerId) {
		model.setDealerId(DealerId);
	}

	/**
	 * Sets the file ID of this diskon fakpol.
	 *
	 * @param FileId the file ID of this diskon fakpol
	 */
	@Override
	public void setFileId(Long FileId) {
		model.setFileId(FileId);
	}

	/**
	 * Sets the file name of this diskon fakpol.
	 *
	 * @param FileName the file name of this diskon fakpol
	 */
	@Override
	public void setFileName(String FileName) {
		model.setFileName(FileName);
	}

	/**
	 * Sets the file path of this diskon fakpol.
	 *
	 * @param FilePath the file path of this diskon fakpol
	 */
	@Override
	public void setFilePath(String FilePath) {
		model.setFilePath(FilePath);
	}

	/**
	 * Sets the ID of this diskon fakpol.
	 *
	 * @param Id the ID of this diskon fakpol
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the keterangan of this diskon fakpol.
	 *
	 * @param Keterangan the keterangan of this diskon fakpol
	 */
	@Override
	public void setKeterangan(String Keterangan) {
		model.setKeterangan(Keterangan);
	}

	/**
	 * Sets the modified by of this diskon fakpol.
	 *
	 * @param ModifiedBy the modified by of this diskon fakpol
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this diskon fakpol.
	 *
	 * @param ModifiedDate the modified date of this diskon fakpol
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the periode of this diskon fakpol.
	 *
	 * @param Periode the periode of this diskon fakpol
	 */
	@Override
	public void setPeriode(String Periode) {
		model.setPeriode(Periode);
	}

	/**
	 * Sets the primary key of this diskon fakpol.
	 *
	 * @param primaryKey the primary key of this diskon fakpol
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this diskon fakpol is row status.
	 *
	 * @param RowStatus the row status of this diskon fakpol
	 */
	@Override
	public void setRowStatus(boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the tahun of this diskon fakpol.
	 *
	 * @param Tahun the tahun of this diskon fakpol
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
	protected DiskonFakpolWrapper wrap(DiskonFakpol diskonFakpol) {
		return new DiskonFakpolWrapper(diskonFakpol);
	}

}