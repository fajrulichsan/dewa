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
 * Provides a wrapper for {@link ApplicationHeaderLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationHeaderLocalService
 * @generated
 */
public class ApplicationHeaderLocalServiceWrapper
	implements ApplicationHeaderLocalService,
			   ServiceWrapper<ApplicationHeaderLocalService> {

	public ApplicationHeaderLocalServiceWrapper() {
		this(null);
	}

	public ApplicationHeaderLocalServiceWrapper(
		ApplicationHeaderLocalService applicationHeaderLocalService) {

		_applicationHeaderLocalService = applicationHeaderLocalService;
	}

	/**
	 * Adds the application header to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationHeaderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationHeader the application header
	 * @return the application header that was added
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeader addApplicationHeader(
		com.astra.dewa.model.ApplicationHeader applicationHeader) {

		return _applicationHeaderLocalService.addApplicationHeader(
			applicationHeader);
	}

	/**
	 * Creates a new application header with the primary key. Does not add the application header to the database.
	 *
	 * @param Id the primary key for the new application header
	 * @return the new application header
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeader createApplicationHeader(
		int Id) {

		return _applicationHeaderLocalService.createApplicationHeader(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the application header from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationHeaderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationHeader the application header
	 * @return the application header that was removed
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeader deleteApplicationHeader(
		com.astra.dewa.model.ApplicationHeader applicationHeader) {

		return _applicationHeaderLocalService.deleteApplicationHeader(
			applicationHeader);
	}

	/**
	 * Deletes the application header with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationHeaderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the application header
	 * @return the application header that was removed
	 * @throws PortalException if a application header with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeader deleteApplicationHeader(
			int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderLocalService.deleteApplicationHeader(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _applicationHeaderLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _applicationHeaderLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _applicationHeaderLocalService.dynamicQuery();
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

		return _applicationHeaderLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationHeaderModelImpl</code>.
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

		return _applicationHeaderLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationHeaderModelImpl</code>.
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

		return _applicationHeaderLocalService.dynamicQuery(
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

		return _applicationHeaderLocalService.dynamicQueryCount(dynamicQuery);
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

		return _applicationHeaderLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.ApplicationHeader fetchApplicationHeader(
		int Id) {

		return _applicationHeaderLocalService.fetchApplicationHeader(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _applicationHeaderLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the application header with the primary key.
	 *
	 * @param Id the primary key of the application header
	 * @return the application header
	 * @throws PortalException if a application header with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeader getApplicationHeader(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderLocalService.getApplicationHeader(Id);
	}

	/**
	 * Returns a range of all the application headers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationHeaderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application headers
	 * @param end the upper bound of the range of application headers (not inclusive)
	 * @return the range of application headers
	 */
	@Override
	public java.util.List<com.astra.dewa.model.ApplicationHeader>
		getApplicationHeaders(int start, int end) {

		return _applicationHeaderLocalService.getApplicationHeaders(start, end);
	}

	/**
	 * Returns the number of application headers.
	 *
	 * @return the number of application headers
	 */
	@Override
	public int getApplicationHeadersCount() {
		return _applicationHeaderLocalService.getApplicationHeadersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _applicationHeaderLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicationHeaderLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the application header in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationHeaderLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationHeader the application header
	 * @return the application header that was updated
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeader updateApplicationHeader(
		com.astra.dewa.model.ApplicationHeader applicationHeader) {

		return _applicationHeaderLocalService.updateApplicationHeader(
			applicationHeader);
	}

	@Override
	public ApplicationHeaderLocalService getWrappedService() {
		return _applicationHeaderLocalService;
	}

	@Override
	public void setWrappedService(
		ApplicationHeaderLocalService applicationHeaderLocalService) {

		_applicationHeaderLocalService = applicationHeaderLocalService;
	}

	private ApplicationHeaderLocalService _applicationHeaderLocalService;

}