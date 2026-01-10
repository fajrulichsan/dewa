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
 * Provides a wrapper for {@link ApplicationAssignJournalLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationAssignJournalLocalService
 * @generated
 */
public class ApplicationAssignJournalLocalServiceWrapper
	implements ApplicationAssignJournalLocalService,
			   ServiceWrapper<ApplicationAssignJournalLocalService> {

	public ApplicationAssignJournalLocalServiceWrapper() {
		this(null);
	}

	public ApplicationAssignJournalLocalServiceWrapper(
		ApplicationAssignJournalLocalService
			applicationAssignJournalLocalService) {

		_applicationAssignJournalLocalService =
			applicationAssignJournalLocalService;
	}

	/**
	 * Adds the application assign journal to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationAssignJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationAssignJournal the application assign journal
	 * @return the application assign journal that was added
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssignJournal
		addApplicationAssignJournal(
			com.astra.dewa.model.ApplicationAssignJournal
				applicationAssignJournal) {

		return _applicationAssignJournalLocalService.
			addApplicationAssignJournal(applicationAssignJournal);
	}

	/**
	 * Creates a new application assign journal with the primary key. Does not add the application assign journal to the database.
	 *
	 * @param Id the primary key for the new application assign journal
	 * @return the new application assign journal
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssignJournal
		createApplicationAssignJournal(int Id) {

		return _applicationAssignJournalLocalService.
			createApplicationAssignJournal(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignJournalLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the application assign journal from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationAssignJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationAssignJournal the application assign journal
	 * @return the application assign journal that was removed
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssignJournal
		deleteApplicationAssignJournal(
			com.astra.dewa.model.ApplicationAssignJournal
				applicationAssignJournal) {

		return _applicationAssignJournalLocalService.
			deleteApplicationAssignJournal(applicationAssignJournal);
	}

	/**
	 * Deletes the application assign journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationAssignJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the application assign journal
	 * @return the application assign journal that was removed
	 * @throws PortalException if a application assign journal with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssignJournal
			deleteApplicationAssignJournal(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignJournalLocalService.
			deleteApplicationAssignJournal(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignJournalLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _applicationAssignJournalLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _applicationAssignJournalLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _applicationAssignJournalLocalService.dynamicQuery();
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

		return _applicationAssignJournalLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationAssignJournalModelImpl</code>.
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

		return _applicationAssignJournalLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationAssignJournalModelImpl</code>.
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

		return _applicationAssignJournalLocalService.dynamicQuery(
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

		return _applicationAssignJournalLocalService.dynamicQueryCount(
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

		return _applicationAssignJournalLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.ApplicationAssignJournal
		fetchApplicationAssignJournal(int Id) {

		return _applicationAssignJournalLocalService.
			fetchApplicationAssignJournal(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _applicationAssignJournalLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns the application assign journal with the primary key.
	 *
	 * @param Id the primary key of the application assign journal
	 * @return the application assign journal
	 * @throws PortalException if a application assign journal with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssignJournal
			getApplicationAssignJournal(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignJournalLocalService.
			getApplicationAssignJournal(Id);
	}

	/**
	 * Returns a range of all the application assign journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationAssignJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign journals
	 * @param end the upper bound of the range of application assign journals (not inclusive)
	 * @return the range of application assign journals
	 */
	@Override
	public java.util.List<com.astra.dewa.model.ApplicationAssignJournal>
		getApplicationAssignJournals(int start, int end) {

		return _applicationAssignJournalLocalService.
			getApplicationAssignJournals(start, end);
	}

	/**
	 * Returns the number of application assign journals.
	 *
	 * @return the number of application assign journals
	 */
	@Override
	public int getApplicationAssignJournalsCount() {
		return _applicationAssignJournalLocalService.
			getApplicationAssignJournalsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _applicationAssignJournalLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicationAssignJournalLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationAssignJournalLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the application assign journal in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationAssignJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationAssignJournal the application assign journal
	 * @return the application assign journal that was updated
	 */
	@Override
	public com.astra.dewa.model.ApplicationAssignJournal
		updateApplicationAssignJournal(
			com.astra.dewa.model.ApplicationAssignJournal
				applicationAssignJournal) {

		return _applicationAssignJournalLocalService.
			updateApplicationAssignJournal(applicationAssignJournal);
	}

	@Override
	public ApplicationAssignJournalLocalService getWrappedService() {
		return _applicationAssignJournalLocalService;
	}

	@Override
	public void setWrappedService(
		ApplicationAssignJournalLocalService
			applicationAssignJournalLocalService) {

		_applicationAssignJournalLocalService =
			applicationAssignJournalLocalService;
	}

	private ApplicationAssignJournalLocalService
		_applicationAssignJournalLocalService;

}