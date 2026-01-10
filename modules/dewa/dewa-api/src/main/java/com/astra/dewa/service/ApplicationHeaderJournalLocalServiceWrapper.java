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
 * Provides a wrapper for {@link ApplicationHeaderJournalLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationHeaderJournalLocalService
 * @generated
 */
public class ApplicationHeaderJournalLocalServiceWrapper
	implements ApplicationHeaderJournalLocalService,
			   ServiceWrapper<ApplicationHeaderJournalLocalService> {

	public ApplicationHeaderJournalLocalServiceWrapper() {
		this(null);
	}

	public ApplicationHeaderJournalLocalServiceWrapper(
		ApplicationHeaderJournalLocalService
			applicationHeaderJournalLocalService) {

		_applicationHeaderJournalLocalService =
			applicationHeaderJournalLocalService;
	}

	/**
	 * Adds the application header journal to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationHeaderJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationHeaderJournal the application header journal
	 * @return the application header journal that was added
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeaderJournal
		addApplicationHeaderJournal(
			com.astra.dewa.model.ApplicationHeaderJournal
				applicationHeaderJournal) {

		return _applicationHeaderJournalLocalService.
			addApplicationHeaderJournal(applicationHeaderJournal);
	}

	/**
	 * Creates a new application header journal with the primary key. Does not add the application header journal to the database.
	 *
	 * @param Id the primary key for the new application header journal
	 * @return the new application header journal
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeaderJournal
		createApplicationHeaderJournal(int Id) {

		return _applicationHeaderJournalLocalService.
			createApplicationHeaderJournal(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderJournalLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the application header journal from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationHeaderJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationHeaderJournal the application header journal
	 * @return the application header journal that was removed
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeaderJournal
		deleteApplicationHeaderJournal(
			com.astra.dewa.model.ApplicationHeaderJournal
				applicationHeaderJournal) {

		return _applicationHeaderJournalLocalService.
			deleteApplicationHeaderJournal(applicationHeaderJournal);
	}

	/**
	 * Deletes the application header journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationHeaderJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the application header journal
	 * @return the application header journal that was removed
	 * @throws PortalException if a application header journal with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeaderJournal
			deleteApplicationHeaderJournal(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderJournalLocalService.
			deleteApplicationHeaderJournal(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderJournalLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _applicationHeaderJournalLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _applicationHeaderJournalLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _applicationHeaderJournalLocalService.dynamicQuery();
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

		return _applicationHeaderJournalLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationHeaderJournalModelImpl</code>.
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

		return _applicationHeaderJournalLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationHeaderJournalModelImpl</code>.
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

		return _applicationHeaderJournalLocalService.dynamicQuery(
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

		return _applicationHeaderJournalLocalService.dynamicQueryCount(
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

		return _applicationHeaderJournalLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.ApplicationHeaderJournal
		fetchApplicationHeaderJournal(int Id) {

		return _applicationHeaderJournalLocalService.
			fetchApplicationHeaderJournal(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _applicationHeaderJournalLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns the application header journal with the primary key.
	 *
	 * @param Id the primary key of the application header journal
	 * @return the application header journal
	 * @throws PortalException if a application header journal with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeaderJournal
			getApplicationHeaderJournal(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderJournalLocalService.
			getApplicationHeaderJournal(Id);
	}

	/**
	 * Returns a range of all the application header journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationHeaderJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application header journals
	 * @param end the upper bound of the range of application header journals (not inclusive)
	 * @return the range of application header journals
	 */
	@Override
	public java.util.List<com.astra.dewa.model.ApplicationHeaderJournal>
		getApplicationHeaderJournals(int start, int end) {

		return _applicationHeaderJournalLocalService.
			getApplicationHeaderJournals(start, end);
	}

	/**
	 * Returns the number of application header journals.
	 *
	 * @return the number of application header journals
	 */
	@Override
	public int getApplicationHeaderJournalsCount() {
		return _applicationHeaderJournalLocalService.
			getApplicationHeaderJournalsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _applicationHeaderJournalLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicationHeaderJournalLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationHeaderJournalLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the application header journal in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationHeaderJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationHeaderJournal the application header journal
	 * @return the application header journal that was updated
	 */
	@Override
	public com.astra.dewa.model.ApplicationHeaderJournal
		updateApplicationHeaderJournal(
			com.astra.dewa.model.ApplicationHeaderJournal
				applicationHeaderJournal) {

		return _applicationHeaderJournalLocalService.
			updateApplicationHeaderJournal(applicationHeaderJournal);
	}

	@Override
	public ApplicationHeaderJournalLocalService getWrappedService() {
		return _applicationHeaderJournalLocalService;
	}

	@Override
	public void setWrappedService(
		ApplicationHeaderJournalLocalService
			applicationHeaderJournalLocalService) {

		_applicationHeaderJournalLocalService =
			applicationHeaderJournalLocalService;
	}

	private ApplicationHeaderJournalLocalService
		_applicationHeaderJournalLocalService;

}