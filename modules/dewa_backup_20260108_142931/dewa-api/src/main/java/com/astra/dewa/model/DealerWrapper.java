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
 * This class is a wrapper for {@link Dealer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Dealer
 * @generated
 */
public class DealerWrapper
	extends BaseModelWrapper<Dealer> implements Dealer, ModelWrapper<Dealer> {

	public DealerWrapper(Dealer dealer) {
		super(dealer);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("CabangId", getCabangId());
		attributes.put("WilayahId", getWilayahId());
		attributes.put("GroupDealer", getGroupDealer());
		attributes.put("Name", getName());
		attributes.put("Code", getCode());
		attributes.put("KodeHo", getKodeHo());
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

		Integer CabangId = (Integer)attributes.get("CabangId");

		if (CabangId != null) {
			setCabangId(CabangId);
		}

		Integer WilayahId = (Integer)attributes.get("WilayahId");

		if (WilayahId != null) {
			setWilayahId(WilayahId);
		}

		Integer GroupDealer = (Integer)attributes.get("GroupDealer");

		if (GroupDealer != null) {
			setGroupDealer(GroupDealer);
		}

		String Name = (String)attributes.get("Name");

		if (Name != null) {
			setName(Name);
		}

		String Code = (String)attributes.get("Code");

		if (Code != null) {
			setCode(Code);
		}

		String KodeHo = (String)attributes.get("KodeHo");

		if (KodeHo != null) {
			setKodeHo(KodeHo);
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
	public Dealer cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the cabang ID of this dealer.
	 *
	 * @return the cabang ID of this dealer
	 */
	@Override
	public int getCabangId() {
		return model.getCabangId();
	}

	/**
	 * Returns the code of this dealer.
	 *
	 * @return the code of this dealer
	 */
	@Override
	public String getCode() {
		return model.getCode();
	}

	/**
	 * Returns the created by of this dealer.
	 *
	 * @return the created by of this dealer
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this dealer.
	 *
	 * @return the created date of this dealer
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the group dealer of this dealer.
	 *
	 * @return the group dealer of this dealer
	 */
	@Override
	public int getGroupDealer() {
		return model.getGroupDealer();
	}

	/**
	 * Returns the ID of this dealer.
	 *
	 * @return the ID of this dealer
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the kode ho of this dealer.
	 *
	 * @return the kode ho of this dealer
	 */
	@Override
	public String getKodeHo() {
		return model.getKodeHo();
	}

	/**
	 * Returns the modified by of this dealer.
	 *
	 * @return the modified by of this dealer
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this dealer.
	 *
	 * @return the modified date of this dealer
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this dealer.
	 *
	 * @return the name of this dealer
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this dealer.
	 *
	 * @return the primary key of this dealer
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the row status of this dealer.
	 *
	 * @return the row status of this dealer
	 */
	@Override
	public Boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns the wilayah ID of this dealer.
	 *
	 * @return the wilayah ID of this dealer
	 */
	@Override
	public int getWilayahId() {
		return model.getWilayahId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the cabang ID of this dealer.
	 *
	 * @param CabangId the cabang ID of this dealer
	 */
	@Override
	public void setCabangId(int CabangId) {
		model.setCabangId(CabangId);
	}

	/**
	 * Sets the code of this dealer.
	 *
	 * @param Code the code of this dealer
	 */
	@Override
	public void setCode(String Code) {
		model.setCode(Code);
	}

	/**
	 * Sets the created by of this dealer.
	 *
	 * @param CreatedBy the created by of this dealer
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this dealer.
	 *
	 * @param CreatedDate the created date of this dealer
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the group dealer of this dealer.
	 *
	 * @param GroupDealer the group dealer of this dealer
	 */
	@Override
	public void setGroupDealer(int GroupDealer) {
		model.setGroupDealer(GroupDealer);
	}

	/**
	 * Sets the ID of this dealer.
	 *
	 * @param Id the ID of this dealer
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets the kode ho of this dealer.
	 *
	 * @param KodeHo the kode ho of this dealer
	 */
	@Override
	public void setKodeHo(String KodeHo) {
		model.setKodeHo(KodeHo);
	}

	/**
	 * Sets the modified by of this dealer.
	 *
	 * @param ModifiedBy the modified by of this dealer
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this dealer.
	 *
	 * @param ModifiedDate the modified date of this dealer
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the name of this dealer.
	 *
	 * @param Name the name of this dealer
	 */
	@Override
	public void setName(String Name) {
		model.setName(Name);
	}

	/**
	 * Sets the primary key of this dealer.
	 *
	 * @param primaryKey the primary key of this dealer
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the row status of this dealer.
	 *
	 * @param RowStatus the row status of this dealer
	 */
	@Override
	public void setRowStatus(Boolean RowStatus) {
		model.setRowStatus(RowStatus);
	}

	/**
	 * Sets the wilayah ID of this dealer.
	 *
	 * @param WilayahId the wilayah ID of this dealer
	 */
	@Override
	public void setWilayahId(int WilayahId) {
		model.setWilayahId(WilayahId);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected DealerWrapper wrap(Dealer dealer) {
		return new DealerWrapper(dealer);
	}

}