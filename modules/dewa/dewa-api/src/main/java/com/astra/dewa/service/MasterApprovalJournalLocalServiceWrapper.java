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
 * Provides a wrapper for {@link MasterApprovalJournalLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalJournalLocalService
 * @generated
 */
public class MasterApprovalJournalLocalServiceWrapper
	implements MasterApprovalJournalLocalService,
			   ServiceWrapper<MasterApprovalJournalLocalService> {

	public MasterApprovalJournalLocalServiceWrapper() {
		this(null);
	}

	public MasterApprovalJournalLocalServiceWrapper(
		MasterApprovalJournalLocalService masterApprovalJournalLocalService) {

		_masterApprovalJournalLocalService = masterApprovalJournalLocalService;
	}

	/**
	 * Adds the master approval journal to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param masterApprovalJournal the master approval journal
	 * @return the master approval journal that was added
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalJournal addMasterApprovalJournal(
		com.astra.dewa.model.MasterApprovalJournal masterApprovalJournal) {

		return _masterApprovalJournalLocalService.addMasterApprovalJournal(
			masterApprovalJournal);
	}

	/**
	 * Creates a new master approval journal with the primary key. Does not add the master approval journal to the database.
	 *
	 * @param Id the primary key for the new master approval journal
	 * @return the new master approval journal
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalJournal
		createMasterApprovalJournal(int Id) {

		return _masterApprovalJournalLocalService.createMasterApprovalJournal(
			Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalJournalLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the master approval journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the master approval journal
	 * @return the master approval journal that was removed
	 * @throws PortalException if a master approval journal with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalJournal
			deleteMasterApprovalJournal(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalJournalLocalService.deleteMasterApprovalJournal(
			Id);
	}

	/**
	 * Deletes the master approval journal from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param masterApprovalJournal the master approval journal
	 * @return the master approval journal that was removed
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalJournal
		deleteMasterApprovalJournal(
			com.astra.dewa.model.MasterApprovalJournal masterApprovalJournal) {

		return _masterApprovalJournalLocalService.deleteMasterApprovalJournal(
			masterApprovalJournal);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalJournalLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _masterApprovalJournalLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _masterApprovalJournalLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _masterApprovalJournalLocalService.dynamicQuery();
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

		return _masterApprovalJournalLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MasterApprovalJournalModelImpl</code>.
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

		return _masterApprovalJournalLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MasterApprovalJournalModelImpl</code>.
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

		return _masterApprovalJournalLocalService.dynamicQuery(
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

		return _masterApprovalJournalLocalService.dynamicQueryCount(
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

		return _masterApprovalJournalLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.MasterApprovalJournal
		fetchMasterApprovalJournal(int Id) {

		return _masterApprovalJournalLocalService.fetchMasterApprovalJournal(
			Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _masterApprovalJournalLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _masterApprovalJournalLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the master approval journal with the primary key.
	 *
	 * @param Id the primary key of the master approval journal
	 * @return the master approval journal
	 * @throws PortalException if a master approval journal with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalJournal getMasterApprovalJournal(
			int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalJournalLocalService.getMasterApprovalJournal(Id);
	}

	/**
	 * Returns a range of all the master approval journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MasterApprovalJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval journals
	 * @param end the upper bound of the range of master approval journals (not inclusive)
	 * @return the range of master approval journals
	 */
	@Override
	public java.util.List<com.astra.dewa.model.MasterApprovalJournal>
		getMasterApprovalJournals(int start, int end) {

		return _masterApprovalJournalLocalService.getMasterApprovalJournals(
			start, end);
	}

	/**
	 * Returns the number of master approval journals.
	 *
	 * @return the number of master approval journals
	 */
	@Override
	public int getMasterApprovalJournalsCount() {
		return _masterApprovalJournalLocalService.
			getMasterApprovalJournalsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _masterApprovalJournalLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalJournalLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the master approval journal in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalJournalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param masterApprovalJournal the master approval journal
	 * @return the master approval journal that was updated
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalJournal
		updateMasterApprovalJournal(
			com.astra.dewa.model.MasterApprovalJournal masterApprovalJournal) {

		return _masterApprovalJournalLocalService.updateMasterApprovalJournal(
			masterApprovalJournal);
	}

	@Override
	public MasterApprovalJournalLocalService getWrappedService() {
		return _masterApprovalJournalLocalService;
	}

	@Override
	public void setWrappedService(
		MasterApprovalJournalLocalService masterApprovalJournalLocalService) {

		_masterApprovalJournalLocalService = masterApprovalJournalLocalService;
	}

	private MasterApprovalJournalLocalService
		_masterApprovalJournalLocalService;

}