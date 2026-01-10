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
 * This class is a wrapper for {@link DiskonOther}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiskonOther
 * @generated
 */
public class DiskonOtherWrapper
	extends BaseModelWrapper<DiskonOther>
	implements DiskonOther, ModelWrapper<DiskonOther> {

	public DiskonOtherWrapper(DiskonOther diskonOther) {
		super(diskonOther);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("diskonOtherId", getDiskonOtherId());
		attributes.put("dealerId", getDealerId());
		attributes.put("tahun", getTahun());
		attributes.put("kuartalId", getKuartalId());
		attributes.put("keterangan", getKeterangan());
		attributes.put("fileId", getFileId());
		attributes.put("fileName", getFileName());
		attributes.put("filePath", getFilePath());
		attributes.put("rowStatus", isRowStatus());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("modifiedBy", getModifiedBy());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long diskonOtherId = (Long)attributes.get("diskonOtherId");

		if (diskonOtherId != null) {
			setDiskonOtherId(diskonOtherId);
		}

		Integer dealerId = (Integer)attributes.get("dealerId");

		if (dealerId != null) {
			setDealerId(dealerId);
		}

		Integer tahun = (Integer)attributes.get("tahun");

		if (tahun != null) {
			setTahun(tahun);
		}

		Integer kuartalId = (Integer)attributes.get("kuartalId");

		if (kuartalId != null) {
			setKuartalId(kuartalId);
		}

		String keterangan = (String)attributes.get("keterangan");

		if (keterangan != null) {
			setKeterangan(keterangan);
		}

		Long fileId = (Long)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		String filePath = (String)attributes.get("filePath");

		if (filePath != null) {
			setFilePath(filePath);
		}

		Boolean rowStatus = (Boolean)attributes.get("rowStatus");

		if (rowStatus != null) {
			setRowStatus(rowStatus);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}
	}

	@Override
	public DiskonOther cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this diskon other.
	 *
	 * @return the created by of this diskon other
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this diskon other.
	 *
	 * @return the created date of this diskon other
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the dealer ID of this diskon other.
	 *
	 * @return the dealer ID of this diskon other
	 */
	@Override
	public int getDealerId() {
		return model.getDealerId();
	}

	/**
	 * Returns the diskon other ID of this diskon other.
	 *
	 * @return the diskon other ID of this diskon other
	 */
	@Override
	public long getDiskonOtherId() {
		return model.getDiskonOtherId();
	}

	/**
	 * Returns the file ID of this diskon other.
	 *
	 * @return the file ID of this diskon other
	 */
	@Override
	public long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the file name of this diskon other.
	 *
	 * @return the file name of this diskon other
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the file path of this diskon other.
	 *
	 * @return the file path of this diskon other
	 */
	@Override
	public String getFilePath() {
		return model.getFilePath();
	}

	/**
	 * Returns the keterangan of this diskon other.
	 *
	 * @return the keterangan of this diskon other
	 */
	@Override
	public String getKeterangan() {
		return model.getKeterangan();
	}

	/**
	 * Returns the kuartal ID of this diskon other.
	 *
	 * @return the kuartal ID of this diskon other
	 */
	@Override
	public int getKuartalId() {
		return model.getKuartalId();
	}

	/**
	 * Returns the modified by of this diskon other.
	 *
	 * @return the modified by of this diskon other
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this diskon other.
	 *
	 * @return the modified date of this diskon other
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this diskon other.
	 *
	 * @return the primary key of this diskon other
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this diskon other.
	 *
	 * @return the row status of this diskon other
	 */
	@Override
	public boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the tahun of this diskon other.
	 *
	 * @return the tahun of this diskon other
	 */
	@Override
	public int getTahun() {
		return model.getTahun();
	}

	/**
	 * Returns <code>true</code> if this diskon other is row status.
	 *
	 * @return <code>true</code> if this diskon other is row status; <code>false</code> otherwise
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
	 * Sets the created by of this diskon other.
	 *
	 * @param createdBy the created by of this diskon other
	 */
	@Override
	public void setCreatedBy(String createdBy) {
		model.setCreatedBy(createdBy);
	}

	/**
	 * Sets the created date of this diskon other.
	 *
	 * @param createdDate the created date of this diskon other
	 */
	@Override
	public void setCreatedDate(Date createdDate) {
		model.setCreatedDate(createdDate);
	}

	/**
	 * Sets the dealer ID of this diskon other.
	 *
	 * @param dealerId the dealer ID of this diskon other
	 */
	@Override
	public void setDealerId(int dealerId) {
		model.setDealerId(dealerId);
	}

	/**
	 * Sets the diskon other ID of this diskon other.
	 *
	 * @param diskonOtherId the diskon other ID of this diskon other
	 */
	@Override
	public void setDiskonOtherId(long diskonOtherId) {
		model.setDiskonOtherId(diskonOtherId);
	}

	/**
	 * Sets the file ID of this diskon other.
	 *
	 * @param fileId the file ID of this diskon other
	 */
	@Override
	public void setFileId(long fileId) {
		model.setFileId(fileId);
	}

	/**
	 * Sets the file name of this diskon other.
	 *
	 * @param fileName the file name of this diskon other
	 */
	@Override
	public void setFileName(String fileName) {
		model.setFileName(fileName);
	}

	/**
	 * Sets the file path of this diskon other.
	 *
	 * @param filePath the file path of this diskon other
	 */
	@Override
	public void setFilePath(String filePath) {
		model.setFilePath(filePath);
	}

	/**
	 * Sets the keterangan of this diskon other.
	 *
	 * @param keterangan the keterangan of this diskon other
	 */
	@Override
	public void setKeterangan(String keterangan) {
		model.setKeterangan(keterangan);
	}

	/**
	 * Sets the kuartal ID of this diskon other.
	 *
	 * @param kuartalId the kuartal ID of this diskon other
	 */
	@Override
	public void setKuartalId(int kuartalId) {
		model.setKuartalId(kuartalId);
	}

	/**
	 * Sets the modified by of this diskon other.
	 *
	 * @param modifiedBy the modified by of this diskon other
	 */
	@Override
	public void setModifiedBy(String modifiedBy) {
		model.setModifiedBy(modifiedBy);
	}

	/**
	 * Sets the modified date of this diskon other.
	 *
	 * @param modifiedDate the modified date of this diskon other
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this diskon other.
	 *
	 * @param primaryKey the primary key of this diskon other
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this diskon other is row status.
	 *
	 * @param rowStatus the row status of this diskon other
	 */
	@Override
	public void setRowStatus(boolean rowStatus) {
		model.setRowStatus(rowStatus);
	}

	/**
	 * Sets the tahun of this diskon other.
	 *
	 * @param tahun the tahun of this diskon other
	 */
	@Override
	public void setTahun(int tahun) {
		model.setTahun(tahun);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected DiskonOtherWrapper wrap(DiskonOther diskonOther) {
		return new DiskonOtherWrapper(diskonOther);
	}

}