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
 * Provides a wrapper for {@link ApplicationAssignStatusLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationAssignStatusLocalService
 * @generated
 */
public class ApplicationAssignStatusLocalServiceWrapper
	implements ApplicationAssignStatusLocalService,
			   ServiceWrapper<ApplicationAssignStatusLocalService> {

	public ApplicationAssignStatusLocalServiceWrapper() {
		this(null);
	}

	public ApplicationAssignStatusLocalServiceWrapper(
		ApplicationAssignStatusLocalService
			applicationAssignStatusLocalService) {

		_applicationAssignStatusLocalService =
			applicationAssignStatusLocalService;
	}

	/**
	 * Adds the application assign status to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationAssignStatusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationAssignStatus the application assign status
	 * @return the application assign status that was added
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssignStatus
		addApplicationAssignStatus(
			com.astra.dewa.model.ApplicationAssignStatus
				applicationAssignStatus) {

		return _applicationAssignStatusLocalService.addApplicationAssignStatus(
			applicationAssignStatus);
	}

	/**
	 * Creates a new application assign status with the primary key. Does not add the application assign status to the database.
	 *
	 * @param Id the primary key for the new application assign status
	 * @return the new application assign status
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssignStatus
		createApplicationAssignStatus(int Id) {

		return _applicationAssignStatusLocalService.
			createApplicationAssignStatus(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignStatusLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the application assign status from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationAssignStatusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationAssignStatus the application assign status
	 * @return the application assign status that was removed
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssignStatus
		deleteApplicationAssignStatus(
			com.astra.dewa.model.ApplicationAssignStatus
				applicationAssignStatus) {

		return _applicationAssignStatusLocalService.
			deleteApplicationAssignStatus(applicationAssignStatus);
	}

	/**
	 * Deletes the application assign status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationAssignStatusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the application assign status
	 * @return the application assign status that was removed
	 * @throws PortalException if a application assign status with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssignStatus
			deleteApplicationAssignStatus(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignStatusLocalService.
			deleteApplicationAssignStatus(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignStatusLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _applicationAssignStatusLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _applicationAssignStatusLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _applicationAssignStatusLocalService.dynamicQuery();
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

		return _applicationAssignStatusLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationAssignStatusModelImpl</code>.
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

		return _applicationAssignStatusLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationAssignStatusModelImpl</code>.
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

		return _applicationAssignStatusLocalService.dynamicQuery(
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

		return _applicationAssignStatusLocalService.dynamicQueryCount(
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

		return _applicationAssignStatusLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.ApplicationAssignStatus
		fetchApplicationAssignStatus(int Id) {

		return _applicationAssignStatusLocalService.
			fetchApplicationAssignStatus(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _applicationAssignStatusLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the application assign status with the primary key.
	 *
	 * @param Id the primary key of the application assign status
	 * @return the application assign status
	 * @throws PortalException if a application assign status with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssignStatus
			getApplicationAssignStatus(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignStatusLocalService.getApplicationAssignStatus(
			Id);
	}

	/**
	 * Returns a range of all the application assign statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationAssignStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign statuses
	 * @param end the upper bound of the range of application assign statuses (not inclusive)
	 * @return the range of application assign statuses
	 */
	@Override
	public java.util.List<com.astra.dewa.model.ApplicationAssignStatus>
		getApplicationAssignStatuses(int start, int end) {

		return _applicationAssignStatusLocalService.
			getApplicationAssignStatuses(start, end);
	}

	/**
	 * Returns the number of application assign statuses.
	 *
	 * @return the number of application assign statuses
	 */
	@Override
	public int getApplicationAssignStatusesCount() {
		return _applicationAssignStatusLocalService.
			getApplicationAssignStatusesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _applicationAssignStatusLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicationAssignStatusLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignStatusLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the application assign status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationAssignStatusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationAssignStatus the application assign status
	 * @return the application assign status that was updated
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssignStatus
		updateApplicationAssignStatus(
			com.astra.dewa.model.ApplicationAssignStatus
				applicationAssignStatus) {

		return _applicationAssignStatusLocalService.
			updateApplicationAssignStatus(applicationAssignStatus);
	}

	@Override
	public ApplicationAssignStatusLocalService getWrappedService() {
		return _applicationAssignStatusLocalService;
	}

	@Override
	public void setWrappedService(
		ApplicationAssignStatusLocalService
			applicationAssignStatusLocalService) {

		_applicationAssignStatusLocalService =
			applicationAssignStatusLocalService;
	}

	private ApplicationAssignStatusLocalService
		_applicationAssignStatusLocalService;

}