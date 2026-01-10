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
 * This class is a wrapper for {@link MenuAuthorization}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MenuAuthorization
 * @generated
 */
public class MenuAuthorizationWrapper
	extends BaseModelWrapper<MenuAuthorization>
	implements MenuAuthorization, ModelWrapper<MenuAuthorization> {

	public MenuAuthorizationWrapper(MenuAuthorization menuAuthorization) {
		super(menuAuthorization);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("Id", getId());
		attributes.put("RoleId", getRoleId());
		attributes.put("Menus", getMenus());
		attributes.put("IsRssp", isIsRssp());
		attributes.put("IsCmsDso", isIsCmsDso());
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

		Integer RoleId = (Integer)attributes.get("RoleId");

		if (RoleId != null) {
			setRoleId(RoleId);
		}

		String Menus = (String)attributes.get("Menus");

		if (Menus != null) {
			setMenus(Menus);
		}

		Boolean IsRssp = (Boolean)attributes.get("IsRssp");

		if (IsRssp != null) {
			setIsRssp(IsRssp);
		}

		Boolean IsCmsDso = (Boolean)attributes.get("IsCmsDso");

		if (IsCmsDso != null) {
			setIsCmsDso(IsCmsDso);
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
	public MenuAuthorization cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the created by of this menu authorization.
	 *
	 * @return the created by of this menu authorization
	 */
	@Override
	public String getCreatedBy() {
		return model.getCreatedBy();
	}

	/**
	 * Returns the created date of this menu authorization.
	 *
	 * @return the created date of this menu authorization
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the ID of this menu authorization.
	 *
	 * @return the ID of this menu authorization
	 */
	@Override
	public int getId() {
		return model.getId();
	}

	/**
	 * Returns the is cms dso of this menu authorization.
	 *
	 * @return the is cms dso of this menu authorization
	 */
	@Override
	public boolean getIsCmsDso() {
		return model.getIsCmsDso();
	}

	/**
	 * Returns the is rssp of this menu authorization.
	 *
	 * @return the is rssp of this menu authorization
	 */
	@Override
	public boolean getIsRssp() {
		return model.getIsRssp();
	}

	/**
	 * Returns the menus of this menu authorization.
	 *
	 * @return the menus of this menu authorization
	 */
	@Override
	public String getMenus() {
		return model.getMenus();
	}

	/**
	 * Returns the modified by of this menu authorization.
	 *
	 * @return the modified by of this menu authorization
	 */
	@Override
	public String getModifiedBy() {
		return model.getModifiedBy();
	}

	/**
	 * Returns the modified date of this menu authorization.
	 *
	 * @return the modified date of this menu authorization
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this menu authorization.
	 *
	 * @return the primary key of this menu authorization
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the role ID of this menu authorization.
	 *
	 * @return the role ID of this menu authorization
	 */
	@Override
	public int getRoleId() {
		return model.getRoleId();
	}

	/**
	 * Returns the row status of this menu authorization.
	 *
	 * @return the row status of this menu authorization
	 */
	@Override
	public boolean getRowStatus() {
		return model.getRowStatus();
	}

	/**
	 * Returns <code>true</code> if this menu authorization is is cms dso.
	 *
	 * @return <code>true</code> if this menu authorization is is cms dso; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsCmsDso() {
		return model.isIsCmsDso();
	}

	/**
	 * Returns <code>true</code> if this menu authorization is is rssp.
	 *
	 * @return <code>true</code> if this menu authorization is is rssp; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsRssp() {
		return model.isIsRssp();
	}

	/**
	 * Returns <code>true</code> if this menu authorization is row status.
	 *
	 * @return <code>true</code> if this menu authorization is row status; <code>false</code> otherwise
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
	 * Sets the created by of this menu authorization.
	 *
	 * @param CreatedBy the created by of this menu authorization
	 */
	@Override
	public void setCreatedBy(String CreatedBy) {
		model.setCreatedBy(CreatedBy);
	}

	/**
	 * Sets the created date of this menu authorization.
	 *
	 * @param CreatedDate the created date of this menu authorization
	 */
	@Override
	public void setCreatedDate(Date CreatedDate) {
		model.setCreatedDate(CreatedDate);
	}

	/**
	 * Sets the ID of this menu authorization.
	 *
	 * @param Id the ID of this menu authorization
	 */
	@Override
	public void setId(int Id) {
		model.setId(Id);
	}

	/**
	 * Sets whether this menu authorization is is cms dso.
	 *
	 * @param IsCmsDso the is cms dso of this menu authorization
	 */
	@Override
	public void setIsCmsDso(boolean IsCmsDso) {
		model.setIsCmsDso(IsCmsDso);
	}

	/**
	 * Sets whether this menu authorization is is rssp.
	 *
	 * @param IsRssp the is rssp of this menu authorization
	 */
	@Override
	public void setIsRssp(boolean IsRssp) {
		model.setIsRssp(IsRssp);
	}

	/**
	 * Sets the menus of this menu authorization.
	 *
	 * @param Menus the menus of this menu authorization
	 */
	@Override
	public void setMenus(String Menus) {
		model.setMenus(Menus);
	}

	/**
	 * Sets the modified by of this menu authorization.
	 *
	 * @param ModifiedBy the modified by of this menu authorization
	 */
	@Override
	public void setModifiedBy(String ModifiedBy) {
		model.setModifiedBy(ModifiedBy);
	}

	/**
	 * Sets the modified date of this menu authorization.
	 *
	 * @param ModifiedDate the modified date of this menu authorization
	 */
	@Override
	public void setModifiedDate(Date ModifiedDate) {
		model.setModifiedDate(ModifiedDate);
	}

	/**
	 * Sets the primary key of this menu authorization.
	 *
	 * @param primaryKey the primary key of this menu authorization
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role ID of this menu authorization.
	 *
	 * @param RoleId the role ID of this menu authorization
	 */
	@Override
	public void setRoleId(int RoleId) {
		model.setRoleId(RoleId);
	}

	/**
	 * Sets whether this menu authorization is row status.
	 *
	 * @param RowStatus the row status of this menu authorization
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
	protected MenuAuthorizationWrapper wrap(
		MenuAuthorization menuAuthorization) {

		return new MenuAuthorizationWrapper(menuAuthorization);
	}

}