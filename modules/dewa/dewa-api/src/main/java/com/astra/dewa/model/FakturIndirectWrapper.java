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
 * This class is a wrapper for {@link FakturIndirect}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FakturIndirect
 * @generated
 */
public class FakturIndirectWrapper
	extends BaseModelWrapper<FakturIndirect>
	implements FakturIndirect, ModelWrapper<FakturIndirect> {

	public FakturIndirectWrapper(FakturIndirect fakturIndirect) {
		super(fakturIndirect);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("DealerId", getDealerId());
		attributes.put("Keterangan", getKeterangan());
		attributes.put("InvoiceDate", getInvoiceDate());
		attributes.put("UploadDate", getUploadDate());
		attributes.put("FileId", getFileId());
		attributes.put("FileName", getFileName());
		attributes.put("FilePath", getFilePath());
		attributes.put("CreatedDate", getCreatedDate());
		attributes.put("CreatedBy", getCreatedBy());
		attributes.put("ModifiedDate", getModifiedDate());
		attributes.put("ModifiedBy", getModifiedBy());
		attributes.put("RowStatus", isRowStatus());

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

		String Keterangan = (String)attributes.get("Keterangan");

		if (Keterangan != null) {
			setKeterangan(Keterangan);
		}

		Date InvoiceDate = (Date)attributes.get("InvoiceDate");

		if (InvoiceDate != null) {
			setInvoiceDate(InvoiceDate);
		}

		Date UploadDate = (Date)attributes.get("UploadDate");

		if (UploadDate != null) {
			setUploadDate(UploadDate);
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

		Boolean RowStatus = (Boolean)attributes.get("RowStatus");

		if (RowStatus != null) {
			setRowStatus(RowStatus);
		}
	}

	@Override
	public FakturIndirect cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this faktur indirect.
	 *
	 * @return the created by of this faktur indirect
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this faktur indirect.
	 *
	 * @return the created date of this faktur indirect
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the dealer ID of this faktur indirect.
	 *
	 * @return the dealer ID of this faktur indirect
	 */
	@Override
	public int getDealerId() {
		return model.getDealerId();
	}

	/**
	 * Returns the file ID of this faktur indirect.
	 *
	 * @return the file ID of this faktur indirect
	 */
	@Override
	public Long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the file name of this faktur indirect.
	 *
	 * @return the file name of this faktur indirect
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the file path of this faktur indirect.
	 *
	 * @return the file path of this faktur indirect
	 */
	@Override
	public String getFilePath() {
		return model.getFilePath();
	}

	/**
	 * Returns the ID of this faktur indirect.
	 *
	 * @return the ID of this faktur indirect
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the invoice date of this faktur indirect.
	 *
	 * @return the invoice date of this faktur indirect
	 */
	@Override
	public Date getInvoiceDate() {
		return model.getInvoiceDate();
	}

	/**
	 * Returns the keterangan of this faktur indirect.
	 *
	 * @return the keterangan of this faktur indirect
	 */
	@Override
	public String getKeterangan() {
		return model.getKeterangan();
	}

	/**
	 * Returns the modified by of this faktur indirect.
	 *
	 * @return the modified by of this faktur indirect
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this faktur indirect.
	 *
	 * @return the modified date of this faktur indirect
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this faktur indirect.
	 *
	 * @return the primary key of this faktur indirect
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this faktur indirect.
	 *
	 * @return the row status of this faktur indirect
	 */
	@Override
	public boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the upload date of this faktur indirect.
	 *
	 * @return the upload date of this faktur indirect
	 */
	@Override
	public Date getUploadDate() {
		return model.getUploadDate();
	}

	/**
	 * Returns <code>true</code> if this faktur indirect is row status.
	 *
	 * @return <code>true</code> if this faktur indirect is row status; <code>false</code> otherwise
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
	 * Sets the created by of this faktur indirect.
	 *
	 * @param CreatedBy the created by of this faktur indirect
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this faktur indirect.
	 *
	 * @param CreatedDate the created date of this faktur indirect
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the dealer ID of this faktur indirect.
	 *
	 * @param DealerId the dealer ID of this faktur indirect
	 */
	@Override
	public void setDealerId(int DealerId) {
		model.setDealerId(DealerId);
	}

	/**
	 * Sets the file ID of this faktur indirect.
	 *
	 * @param FileId the file ID of this faktur indirect
	 */
	@Override
	public void setFileId(Long FileId) {
		model.setFileId(FileId);
	}

	/**
	 * Sets the file name of this faktur indirect.
	 *
	 * @param FileName the file name of this faktur indirect
	 */
	@Override
	public void setFileName(String FileName) {
		model.setFileName(FileName);
	}

	/**
	 * Sets the file path of this faktur indirect.
	 *
	 * @param FilePath the file path of this faktur indirect
	 */
	@Override
	public void setFilePath(String FilePath) {
		model.setFilePath(FilePath);
	}

	/**
	 * Sets the ID of this faktur indirect.
	 *
	 * @param Id the ID of this faktur indirect
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the invoice date of this faktur indirect.
	 *
	 * @param InvoiceDate the invoice date of this faktur indirect
	 */
	@Override
	public void setInvoiceDate(Date InvoiceDate) {
		model.setInvoiceDate(InvoiceDate);
	}

	/**
	 * Sets the keterangan of this faktur indirect.
	 *
	 * @param Keterangan the keterangan of this faktur indirect
	 */
	@Override
	public void setKeterangan(String Keterangan) {
		model.setKeterangan(Keterangan);
	}

	/**
	 * Sets the modified by of this faktur indirect.
	 *
	 * @param ModifiedBy the modified by of this faktur indirect
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this faktur indirect.
	 *
	 * @param ModifiedDate the modified date of this faktur indirect
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this faktur indirect.
	 *
	 * @param primaryKey the primary key of this faktur indirect
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this faktur indirect is row status.
	 *
	 * @param RowStatus the row status of this faktur indirect
	 */
	@Override
	public void setRowStatus(boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the upload date of this faktur indirect.
	 *
	 * @param UploadDate the upload date of this faktur indirect
	 */
	@Override
	public void setUploadDate(Date UploadDate) {
		model.setUploadDate(UploadDate);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected FakturIndirectWrapper wrap(FakturIndirect fakturIndirect) {
		return new FakturIndirectWrapper(fakturIndirect);
	}

}