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
 * This class is a wrapper for {@link SalesReport}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SalesReport
 * @generated
 */
public class SalesReportWrapper
	extends BaseModelWrapper<SalesReport>
	implements ModelWrapper<SalesReport>, SalesReport {

	public SalesReportWrapper(SalesReport salesReport) {
		super(salesReport);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("DealerId", getDealerId());
		attributes.put("FileId", getFileId());
		attributes.put("FileName", getFileName());
		attributes.put("FilePath", getFilePath());
		attributes.put("Periode", getPeriode());
		attributes.put("Keterangan", getKeterangan());
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

		Date Periode = (Date)attributes.get("Periode");

		if (Periode != null) {
			setPeriode(Periode);
		}

		String Keterangan = (String)attributes.get("Keterangan");

		if (Keterangan != null) {
			setKeterangan(Keterangan);
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
	public SalesReport cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this sales report.
	 *
	 * @return the created by of this sales report
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this sales report.
	 *
	 * @return the created date of this sales report
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the dealer ID of this sales report.
	 *
	 * @return the dealer ID of this sales report
	 */
	@Override
	public int getDealerId() {
		return model.getDealerId();
	}

	/**
	 * Returns the file ID of this sales report.
	 *
	 * @return the file ID of this sales report
	 */
	@Override
	public Long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the file name of this sales report.
	 *
	 * @return the file name of this sales report
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the file path of this sales report.
	 *
	 * @return the file path of this sales report
	 */
	@Override
	public String getFilePath() {
		return model.getFilePath();
	}

	/**
	 * Returns the ID of this sales report.
	 *
	 * @return the ID of this sales report
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the keterangan of this sales report.
	 *
	 * @return the keterangan of this sales report
	 */
	@Override
	public String getKeterangan() {
		return model.getKeterangan();
	}

	/**
	 * Returns the modified by of this sales report.
	 *
	 * @return the modified by of this sales report
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this sales report.
	 *
	 * @return the modified date of this sales report
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the periode of this sales report.
	 *
	 * @return the periode of this sales report
	 */
	@Override
	public Date getPeriode() {
		return model.getPeriode();
	}

	/**
	 * Returns the primary key of this sales report.
	 *
	 * @return the primary key of this sales report
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this sales report.
	 *
	 * @return the row status of this sales report
	 */
	@Override
	public boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns <code>true</code> if this sales report is row status.
	 *
	 * @return <code>true</code> if this sales report is row status; <code>false</code> otherwise
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
	 * Sets the created by of this sales report.
	 *
	 * @param CreatedBy the created by of this sales report
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this sales report.
	 *
	 * @param CreatedDate the created date of this sales report
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the dealer ID of this sales report.
	 *
	 * @param DealerId the dealer ID of this sales report
	 */
	@Override
	public void setDealerId(int DealerId) {
		model.setDealerId(DealerId);
	}

	/**
	 * Sets the file ID of this sales report.
	 *
	 * @param FileId the file ID of this sales report
	 */
	@Override
	public void setFileId(Long FileId) {
		model.setFileId(FileId);
	}

	/**
	 * Sets the file name of this sales report.
	 *
	 * @param FileName the file name of this sales report
	 */
	@Override
	public void setFileName(String FileName) {
		model.setFileName(FileName);
	}

	/**
	 * Sets the file path of this sales report.
	 *
	 * @param FilePath the file path of this sales report
	 */
	@Override
	public void setFilePath(String FilePath) {
		model.setFilePath(FilePath);
	}

	/**
	 * Sets the ID of this sales report.
	 *
	 * @param Id the ID of this sales report
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the keterangan of this sales report.
	 *
	 * @param Keterangan the keterangan of this sales report
	 */
	@Override
	public void setKeterangan(String Keterangan) {
		model.setKeterangan(Keterangan);
	}

	/**
	 * Sets the modified by of this sales report.
	 *
	 * @param ModifiedBy the modified by of this sales report
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this sales report.
	 *
	 * @param ModifiedDate the modified date of this sales report
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the periode of this sales report.
	 *
	 * @param Periode the periode of this sales report
	 */
	@Override
	public void setPeriode(Date Periode) {
		model.setPeriode(Periode);
	}

	/**
	 * Sets the primary key of this sales report.
	 *
	 * @param primaryKey the primary key of this sales report
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this sales report is row status.
	 *
	 * @param RowStatus the row status of this sales report
	 */
	@Override
	public void setRowStatus(boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected SalesReportWrapper wrap(SalesReport salesReport) {
		return new SalesReportWrapper(salesReport);
	}

}