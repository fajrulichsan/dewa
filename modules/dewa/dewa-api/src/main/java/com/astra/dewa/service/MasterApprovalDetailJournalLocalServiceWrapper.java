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
 * Provides a wrapper for {@link MasterApprovalDetailJournalLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalDetailJournalLocalService
 * @generated
 */
public class MasterApprovalDetailJournalLocalServiceWrapper
	implements MasterApprovalDetailJournalLocalService,
			   ServiceWrapper<MasterApprovalDetailJournalLocalService> {

	public MasterApprovalDetailJournalLocalServiceWrapper() {
		this(null);
	}

	public MasterApprovalDetailJournalLocalServiceWrapper(
		MasterApprovalDetailJournalLocalService
			masterApprovalDetailJournalLocalService) {

		_masterApprovalDetailJournalLocalService =
			masterApprovalDetailJournalLocalService;
	}

	/**
	 * Adds the master approval detail journal to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalDetailJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param masterApprovalDetailJournal the master approval detail journal
	 * @return the master approval detail journal that was added
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalDetailJournal
		addMasterApprovalDetailJournal(
			com.astra.dewa.model.MasterApprovalDetailJournal
				masterApprovalDetailJournal) {

		return _masterApprovalDetailJournalLocalService.
			addMasterApprovalDetailJournal(masterApprovalDetailJournal);
	}

	/**
	 * Creates a new master approval detail journal with the primary key. Does not add the master approval detail journal to the database.
	 *
	 * @param Id the primary key for the new master approval detail journal
	 * @return the new master approval detail journal
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalDetailJournal
		createMasterApprovalDetailJournal(int Id) {

		return _masterApprovalDetailJournalLocalService.
			createMasterApprovalDetailJournal(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalDetailJournalLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the master approval detail journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalDetailJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the master approval detail journal
	 * @return the master approval detail journal that was removed
	 * @throws PortalException if a master approval detail journal with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalDetailJournal
			deleteMasterApprovalDetailJournal(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalDetailJournalLocalService.
			deleteMasterApprovalDetailJournal(Id);
	}

	/**
	 * Deletes the master approval detail journal from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalDetailJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param masterApprovalDetailJournal the master approval detail journal
	 * @return the master approval detail journal that was removed
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalDetailJournal
		deleteMasterApprovalDetailJournal(
			com.astra.dewa.model.MasterApprovalDetailJournal
				masterApprovalDetailJournal) {

		return _masterApprovalDetailJournalLocalService.
			deleteMasterApprovalDetailJournal(masterApprovalDetailJournal);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalDetailJournalLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _masterApprovalDetailJournalLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _masterApprovalDetailJournalLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _masterApprovalDetailJournalLocalService.dynamicQuery();
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

		return _masterApprovalDetailJournalLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MasterApprovalDetailJournalModelImpl</code>.
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

		return _masterApprovalDetailJournalLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MasterApprovalDetailJournalModelImpl</code>.
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

		return _masterApprovalDetailJournalLocalService.dynamicQuery(
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

		return _masterApprovalDetailJournalLocalService.dynamicQueryCount(
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

		return _masterApprovalDetailJournalLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.MasterApprovalDetailJournal
		fetchMasterApprovalDetailJournal(int Id) {

		return _masterApprovalDetailJournalLocalService.
			fetchMasterApprovalDetailJournal(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _masterApprovalDetailJournalLocalService.
			getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _masterApprovalDetailJournalLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the master approval detail journal with the primary key.
	 *
	 * @param Id the primary key of the master approval detail journal
	 * @return the master approval detail journal
	 * @throws PortalException if a master approval detail journal with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalDetailJournal
			getMasterApprovalDetailJournal(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalDetailJournalLocalService.
			getMasterApprovalDetailJournal(Id);
	}

	/**
	 * Returns a range of all the master approval detail journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MasterApprovalDetailJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval detail journals
	 * @param end the upper bound of the range of master approval detail journals (not inclusive)
	 * @return the range of master approval detail journals
	 */
	@Override
	public java.util.List<com.astra.dewa.model.MasterApprovalDetailJournal>
		getMasterApprovalDetailJournals(int start, int end) {

		return _masterApprovalDetailJournalLocalService.
			getMasterApprovalDetailJournals(start, end);
	}

	/**
	 * Returns the number of master approval detail journals.
	 *
	 * @return the number of master approval detail journals
	 */
	@Override
	public int getMasterApprovalDetailJournalsCount() {
		return _masterApprovalDetailJournalLocalService.
			getMasterApprovalDetailJournalsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _masterApprovalDetailJournalLocalService.
			getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalDetailJournalLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the master approval detail journal in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalDetailJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param masterApprovalDetailJournal the master approval detail journal
	 * @return the master approval detail journal that was updated
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalDetailJournal
		updateMasterApprovalDetailJournal(
			com.astra.dewa.model.MasterApprovalDetailJournal
				masterApprovalDetailJournal) {

		return _masterApprovalDetailJournalLocalService.
			updateMasterApprovalDetailJournal(masterApprovalDetailJournal);
	}

	@Override
	public MasterApprovalDetailJournalLocalService getWrappedService() {
		return _masterApprovalDetailJournalLocalService;
	}

	@Override
	public void setWrappedService(
		MasterApprovalDetailJournalLocalService
			masterApprovalDetailJournalLocalService) {

		_masterApprovalDetailJournalLocalService =
			masterApprovalDetailJournalLocalService;
	}

	private MasterApprovalDetailJournalLocalService
		_masterApprovalDetailJournalLocalService;

}