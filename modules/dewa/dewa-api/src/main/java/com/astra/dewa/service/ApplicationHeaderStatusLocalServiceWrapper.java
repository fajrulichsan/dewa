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
 * Provides a wrapper for {@link ApplicationHeaderStatusLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationHeaderStatusLocalService
 * @generated
 */
public class ApplicationHeaderStatusLocalServiceWrapper
	implements ApplicationHeaderStatusLocalService,
			   ServiceWrapper<ApplicationHeaderStatusLocalService> {

	public ApplicationHeaderStatusLocalServiceWrapper() {
		this(null);
	}

	public ApplicationHeaderStatusLocalServiceWrapper(
		ApplicationHeaderStatusLocalService
			applicationHeaderStatusLocalService) {

		_applicationHeaderStatusLocalService =
			applicationHeaderStatusLocalService;
	}

	/**
	 * Adds the application header status to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationHeaderStatusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationHeaderStatus the application header status
	 * @return the application header status that was added
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeaderStatus
		addApplicationHeaderStatus(
			com.astra.dewa.model.ApplicationHeaderStatus
				applicationHeaderStatus) {

		return _applicationHeaderStatusLocalService.addApplicationHeaderStatus(
			applicationHeaderStatus);
	}

	/**
	 * Creates a new application header status with the primary key. Does not add the application header status to the database.
	 *
	 * @param Id the primary key for the new application header status
	 * @return the new application header status
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeaderStatus
		createApplicationHeaderStatus(int Id) {

		return _applicationHeaderStatusLocalService.
			createApplicationHeaderStatus(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderStatusLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the application header status from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationHeaderStatusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationHeaderStatus the application header status
	 * @return the application header status that was removed
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeaderStatus
		deleteApplicationHeaderStatus(
			com.astra.dewa.model.ApplicationHeaderStatus
				applicationHeaderStatus) {

		return _applicationHeaderStatusLocalService.
			deleteApplicationHeaderStatus(applicationHeaderStatus);
	}

	/**
	 * Deletes the application header status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationHeaderStatusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the application header status
	 * @return the application header status that was removed
	 * @throws PortalException if a application header status with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeaderStatus
			deleteApplicationHeaderStatus(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderStatusLocalService.
			deleteApplicationHeaderStatus(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderStatusLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _applicationHeaderStatusLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _applicationHeaderStatusLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _applicationHeaderStatusLocalService.dynamicQuery();
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

		return _applicationHeaderStatusLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationHeaderStatusModelImpl</code>.
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

		return _applicationHeaderStatusLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationHeaderStatusModelImpl</code>.
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

		return _applicationHeaderStatusLocalService.dynamicQuery(
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

		return _applicationHeaderStatusLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _applicationHeaderStatusLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.ApplicationHeaderStatus
		fetchApplicationHeaderStatus(int Id) {

		return _applicationHeaderStatusLocalService.
			fetchApplicationHeaderStatus(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _applicationHeaderStatusLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the application header status with the primary key.
	 *
	 * @param Id the primary key of the application header status
	 * @return the application header status
	 * @throws PortalException if a application header status with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeaderStatus
			getApplicationHeaderStatus(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderStatusLocalService.getApplicationHeaderStatus(
			Id);
	}

	/**
	 * Returns a range of all the application header statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationHeaderStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application header statuses
	 * @param end the upper bound of the range of application header statuses (not inclusive)
	 * @return the range of application header statuses
	 */
	@Override
	public java.util.List<com.astra.dewa.model.ApplicationHeaderStatus>
		getApplicationHeaderStatuses(int start, int end) {

		return _applicationHeaderStatusLocalService.
			getApplicationHeaderStatuses(start, end);
	}

	/**
	 * Returns the number of application header statuses.
	 *
	 * @return the number of application header statuses
	 */
	@Override
	public int getApplicationHeaderStatusesCount() {
		return _applicationHeaderStatusLocalService.
			getApplicationHeaderStatusesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _applicationHeaderStatusLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicationHeaderStatusLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderStatusLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the application header status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationHeaderStatusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationHeaderStatus the application header status
	 * @return the application header status that was updated
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeaderStatus
		updateApplicationHeaderStatus(
			com.astra.dewa.model.ApplicationHeaderStatus
				applicationHeaderStatus) {

		return _applicationHeaderStatusLocalService.
			updateApplicationHeaderStatus(applicationHeaderStatus);
	}

	@Override
	public ApplicationHeaderStatusLocalService getWrappedService() {
		return _applicationHeaderStatusLocalService;
	}

	@Override
	public void setWrappedService(
		ApplicationHeaderStatusLocalService
			applicationHeaderStatusLocalService) {

		_applicationHeaderStatusLocalService =
			applicationHeaderStatusLocalService;
	}

	private ApplicationHeaderStatusLocalService
		_applicationHeaderStatusLocalService;

}