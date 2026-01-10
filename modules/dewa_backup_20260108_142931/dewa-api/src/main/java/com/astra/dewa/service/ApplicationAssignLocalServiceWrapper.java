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
 * Provides a wrapper for {@link ApplicationAssignLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationAssignLocalService
 * @generated
 */
public class ApplicationAssignLocalServiceWrapper
	implements ApplicationAssignLocalService,
			   ServiceWrapper<ApplicationAssignLocalService> {

	public ApplicationAssignLocalServiceWrapper() {
		this(null);
	}

	public ApplicationAssignLocalServiceWrapper(
		ApplicationAssignLocalService applicationAssignLocalService) {

		_applicationAssignLocalService = applicationAssignLocalService;
	}

	/**
	 * Adds the application assign to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationAssignLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationAssign the application assign
	 * @return the application assign that was added
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssign addApplicationAssign(
		com.astra.dewa.model.ApplicationAssign applicationAssign) {

		return _applicationAssignLocalService.addApplicationAssign(
			applicationAssign);
	}

	/**
	 * Creates a new application assign with the primary key. Does not add the application assign to the database.
	 *
	 * @param Id the primary key for the new application assign
	 * @return the new application assign
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssign createApplicationAssign(
		int Id) {

		return _applicationAssignLocalService.createApplicationAssign(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the application assign from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationAssignLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationAssign the application assign
	 * @return the application assign that was removed
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssign deleteApplicationAssign(
		com.astra.dewa.model.ApplicationAssign applicationAssign) {

		return _applicationAssignLocalService.deleteApplicationAssign(
			applicationAssign);
	}

	/**
	 * Deletes the application assign with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationAssignLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the application assign
	 * @return the application assign that was removed
	 * @throws PortalException if a application assign with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssign deleteApplicationAssign(
			int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignLocalService.deleteApplicationAssign(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _applicationAssignLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _applicationAssignLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _applicationAssignLocalService.dynamicQuery();
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

		return _applicationAssignLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationAssignModelImpl</code>.
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

		return _applicationAssignLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationAssignModelImpl</code>.
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

		return _applicationAssignLocalService.dynamicQuery(
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

		return _applicationAssignLocalService.dynamicQueryCount(dynamicQuery);
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

		return _applicationAssignLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.ApplicationAssign fetchApplicationAssign(
		int Id) {

		return _applicationAssignLocalService.fetchApplicationAssign(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _applicationAssignLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the application assign with the primary key.
	 *
	 * @param Id the primary key of the application assign
	 * @return the application assign
	 * @throws PortalException if a application assign with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssign getApplicationAssign(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignLocalService.getApplicationAssign(Id);
	}

	/**
	 * Returns a range of all the application assigns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationAssignModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assigns
	 * @param end the upper bound of the range of application assigns (not inclusive)
	 * @return the range of application assigns
	 */
	@Override
	public java.util.List<com.astra.dewa.model.ApplicationAssign>
		getApplicationAssigns(int start, int end) {

		return _applicationAssignLocalService.getApplicationAssigns(start, end);
	}

	/**
	 * Returns the number of application assigns.
	 *
	 * @return the number of application assigns
	 */
	@Override
	public int getApplicationAssignsCount() {
		return _applicationAssignLocalService.getApplicationAssignsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _applicationAssignLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicationAssignLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the application assign in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationAssignLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationAssign the application assign
	 * @return the application assign that was updated
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssign updateApplicationAssign(
		com.astra.dewa.model.ApplicationAssign applicationAssign) {

		return _applicationAssignLocalService.updateApplicationAssign(
			applicationAssign);
	}

	@Override
	public ApplicationAssignLocalService getWrappedService() {
		return _applicationAssignLocalService;
	}

	@Override
	public void setWrappedService(
		ApplicationAssignLocalService applicationAssignLocalService) {

		_applicationAssignLocalService = applicationAssignLocalService;
	}

	private ApplicationAssignLocalService _applicationAssignLocalService;

}