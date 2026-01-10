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

package com.astra.dewa.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MenuAuthorizationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MenuAuthorizationLocalService
 * @generated
 */
public class MenuAuthorizationLocalServiceWrapper
	implements MenuAuthorizationLocalService,
			   ServiceWrapper<MenuAuthorizationLocalService> {

	public MenuAuthorizationLocalServiceWrapper() {
		this(null);
	}

	public MenuAuthorizationLocalServiceWrapper(
		MenuAuthorizationLocalService menuAuthorizationLocalService) {

		_menuAuthorizationLocalService = menuAuthorizationLocalService;
	}

	/**
	 * Adds the menu authorization to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MenuAuthorizationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param menuAuthorization the menu authorization
	 * @return the menu authorization that was added
	 */
	@Override
	public com.astra.dewa.model.MenuAuthorization addMenuAuthorization(
		com.astra.dewa.model.MenuAuthorization menuAuthorization) {

		return _menuAuthorizationLocalService.addMenuAuthorization(
			menuAuthorization);
	}

	/**
	 * Creates a new menu authorization with the primary key. Does not add the menu authorization to the database.
	 *
	 * @param Id the primary key for the new menu authorization
	 * @return the new menu authorization
	 */
	@Override
	public com.astra.dewa.model.MenuAuthorization createMenuAuthorization(
		int Id) {

		return _menuAuthorizationLocalService.createMenuAuthorization(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _menuAuthorizationLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the menu authorization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MenuAuthorizationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the menu authorization
	 * @return the menu authorization that was removed
	 * @throws PortalException if a menu authorization with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.MenuAuthorization deleteMenuAuthorization(
			int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _menuAuthorizationLocalService.deleteMenuAuthorization(Id);
	}

	/**
	 * Deletes the menu authorization from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MenuAuthorizationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param menuAuthorization the menu authorization
	 * @return the menu authorization that was removed
	 */
	@Override
	public com.astra.dewa.model.MenuAuthorization deleteMenuAuthorization(
		com.astra.dewa.model.MenuAuthorization menuAuthorization) {

		return _menuAuthorizationLocalService.deleteMenuAuthorization(
			menuAuthorization);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _menuAuthorizationLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _menuAuthorizationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _menuAuthorizationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _menuAuthorizationLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _menuAuthorizationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MenuAuthorizationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _menuAuthorizationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MenuAuthorizationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _menuAuthorizationLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _menuAuthorizationLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _menuAuthorizationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.MenuAuthorization fetchMenuAuthorization(
		int Id) {

		return _menuAuthorizationLocalService.fetchMenuAuthorization(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _menuAuthorizationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _menuAuthorizationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the menu authorization with the primary key.
	 *
	 * @param Id the primary key of the menu authorization
	 * @return the menu authorization
	 * @throws PortalException if a menu authorization with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.MenuAuthorization getMenuAuthorization(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _menuAuthorizationLocalService.getMenuAuthorization(Id);
	}

	/**
	 * Returns a range of all the menu authorizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MenuAuthorizationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of menu authorizations
	 * @param end the upper bound of the range of menu authorizations (not inclusive)
	 * @return the range of menu authorizations
	 */
	@Override
	public java.util.List<com.astra.dewa.model.MenuAuthorization>
		getMenuAuthorizations(int start, int end) {

		return _menuAuthorizationLocalService.getMenuAuthorizations(start, end);
	}

	/**
	 * Returns the number of menu authorizations.
	 *
	 * @return the number of menu authorizations
	 */
	@Override
	public int getMenuAuthorizationsCount() {
		return _menuAuthorizationLocalService.getMenuAuthorizationsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _menuAuthorizationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _menuAuthorizationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the menu authorization in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MenuAuthorizationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param menuAuthorization the menu authorization
	 * @return the menu authorization that was updated
	 */
	@Override
	public com.astra.dewa.model.MenuAuthorization updateMenuAuthorization(
		com.astra.dewa.model.MenuAuthorization menuAuthorization) {

		return _menuAuthorizationLocalService.updateMenuAuthorization(
			menuAuthorization);
	}

	@Override
	public MenuAuthorizationLocalService getWrappedService() {
		return _menuAuthorizationLocalService;
	}

	@Override
	public void setWrappedService(
		MenuAuthorizationLocalService menuAuthorizationLocalService) {

		_menuAuthorizationLocalService = menuAuthorizationLocalService;
	}

	private MenuAuthorizationLocalService _menuAuthorizationLocalService;

}