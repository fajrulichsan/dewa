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
 * This class is a wrapper for {@link SalesProgram}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SalesProgram
 * @generated
 */
public class SalesProgramWrapper
	extends BaseModelWrapper<SalesProgram>
	implements ModelWrapper<SalesProgram>, SalesProgram {

	public SalesProgramWrapper(SalesProgram salesProgram) {
		super(salesProgram);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("Tahun", getTahun());
		attributes.put("Periode", getPeriode());
		attributes.put("FileId", getFileId());
		attributes.put("FileName", getFileName());
		attributes.put("rowStatus", isRowStatus());
		attributes.put("CreatedDate", getCreatedDate());
		attributes.put("CreatedBy", getCreatedBy());
		attributes.put("ModifiedDate", getModifiedDate());
		attributes.put("ModifiedBy", getModifiedBy());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long Id = (Long)attributes.get("Id");

		if (Id != null) {
			setId(Id);
		}

		Integer Tahun = (Integer)attributes.get("Tahun");

		if (Tahun != null) {
			setTahun(Tahun);
		}

		String Periode = (String)attributes.get("Periode");

		if (Periode != null) {
			setPeriode(Periode);
		}

		Long FileId = (Long)attributes.get("FileId");

		if (FileId != null) {
			setFileId(FileId);
		}

		String FileName = (String)attributes.get("FileName");

		if (FileName != null) {
			setFileName(FileName);
		}

		Boolean rowStatus = (Boolean)attributes.get("rowStatus");

		if (rowStatus != null) {
			setRowStatus(rowStatus);
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
	public SalesProgram cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this sales program.
	 *
	 * @return the created by of this sales program
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this sales program.
	 *
	 * @return the created date of this sales program
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the file ID of this sales program.
	 *
	 * @return the file ID of this sales program
	 */
	@Override
	public Long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the file name of this sales program.
	 *
	 * @return the file name of this sales program
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the ID of this sales program.
	 *
	 * @return the ID of this sales program
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the modified by of this sales program.
	 *
	 * @return the modified by of this sales program
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this sales program.
	 *
	 * @return the modified date of this sales program
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the periode of this sales program.
	 *
	 * @return the periode of this sales program
	 */
	@Override
	public String getPeriode() {
		return model.getPeriode();
	}

	/**
	 * Returns the primary key of this sales program.
	 *
	 * @return the primary key of this sales program
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this sales program.
	 *
	 * @return the row status of this sales program
	 */
	@Override
	public boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the tahun of this sales program.
	 *
	 * @return the tahun of this sales program
	 */
	@Override
	public int getTahun() {
		return model.getTahun();
	}

	/**
	 * Returns <code>true</code> if this sales program is row status.
	 *
	 * @return <code>true</code> if this sales program is row status; <code>false</code> otherwise
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
	 * Sets the created by of this sales program.
	 *
	 * @param CreatedBy the created by of this sales program
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this sales program.
	 *
	 * @param CreatedDate the created date of this sales program
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the file ID of this sales program.
	 *
	 * @param FileId the file ID of this sales program
	 */
	@Override
	public void setFileId(Long FileId) {
		model.setFileId(FileId);
	}

	/**
	 * Sets the file name of this sales program.
	 *
	 * @param FileName the file name of this sales program
	 */
	@Override
	public void setFileName(String FileName) {
		model.setFileName(FileName);
	}

	/**
	 * Sets the ID of this sales program.
	 *
	 * @param Id the ID of this sales program
	 */
	@Override
	public void setId(long Id) {
		model.setId(Id);
	}

	/**
	 * Sets the modified by of this sales program.
	 *
	 * @param ModifiedBy the modified by of this sales program
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this sales program.
	 *
	 * @param ModifiedDate the modified date of this sales program
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the periode of this sales program.
	 *
	 * @param Periode the periode of this sales program
	 */
	@Override
	public void setPeriode(String Periode) {
		model.setPeriode(Periode);
	}

	/**
	 * Sets the primary key of this sales program.
	 *
	 * @param primaryKey the primary key of this sales program
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this sales program is row status.
	 *
	 * @param rowStatus the row status of this sales program
	 */
	@Override
	public void setRowStatus(boolean rowStatus) {
		model.setRowStatus(rowStatus);
	}

	/**
	 * Sets the tahun of this sales program.
	 *
	 * @param Tahun the tahun of this sales program
	 */
	@Override
	public void setTahun(int Tahun) {
		model.setTahun(Tahun);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected SalesProgramWrapper wrap(SalesProgram salesProgram) {
		return new SalesProgramWrapper(salesProgram);
	}

}