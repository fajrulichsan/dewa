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
 * Provides a wrapper for {@link MasterApprovalDetailLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalDetailLocalService
 * @generated
 */
public class MasterApprovalDetailLocalServiceWrapper
	implements MasterApprovalDetailLocalService,
			   ServiceWrapper<MasterApprovalDetailLocalService> {

	public MasterApprovalDetailLocalServiceWrapper() {
		this(null);
	}

	public MasterApprovalDetailLocalServiceWrapper(
		MasterApprovalDetailLocalService masterApprovalDetailLocalService) {

		_masterApprovalDetailLocalService = masterApprovalDetailLocalService;
	}

	/**
	 * Adds the master approval detail to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalDetailLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param masterApprovalDetail the master approval detail
	 * @return the master approval detail that was added
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalDetail addMasterApprovalDetail(
		com.astra.dewa.model.MasterApprovalDetail masterApprovalDetail) {

		return _masterApprovalDetailLocalService.addMasterApprovalDetail(
			masterApprovalDetail);
	}

	/**
	 * Creates a new master approval detail with the primary key. Does not add the master approval detail to the database.
	 *
	 * @param Id the primary key for the new master approval detail
	 * @return the new master approval detail
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalDetail createMasterApprovalDetail(
		int Id) {

		return _masterApprovalDetailLocalService.createMasterApprovalDetail(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalDetailLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the master approval detail with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalDetailLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the master approval detail
	 * @return the master approval detail that was removed
	 * @throws PortalException if a master approval detail with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalDetail deleteMasterApprovalDetail(
			int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalDetailLocalService.deleteMasterApprovalDetail(Id);
	}

	/**
	 * Deletes the master approval detail from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalDetailLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param masterApprovalDetail the master approval detail
	 * @return the master approval detail that was removed
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalDetail deleteMasterApprovalDetail(
		com.astra.dewa.model.MasterApprovalDetail masterApprovalDetail) {

		return _masterApprovalDetailLocalService.deleteMasterApprovalDetail(
			masterApprovalDetail);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalDetailLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _masterApprovalDetailLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _masterApprovalDetailLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _masterApprovalDetailLocalService.dynamicQuery();
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

		return _masterApprovalDetailLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MasterApprovalDetailModelImpl</code>.
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

		return _masterApprovalDetailLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MasterApprovalDetailModelImpl</code>.
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

		return _masterApprovalDetailLocalService.dynamicQuery(
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

		return _masterApprovalDetailLocalService.dynamicQueryCount(
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

		return _masterApprovalDetailLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.MasterApprovalDetail fetchMasterApprovalDetail(
		int Id) {

		return _masterApprovalDetailLocalService.fetchMasterApprovalDetail(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _masterApprovalDetailLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _masterApprovalDetailLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the master approval detail with the primary key.
	 *
	 * @param Id the primary key of the master approval detail
	 * @return the master approval detail
	 * @throws PortalException if a master approval detail with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalDetail getMasterApprovalDetail(
			int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalDetailLocalService.getMasterApprovalDetail(Id);
	}

	/**
	 * Returns a range of all the master approval details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MasterApprovalDetailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval details
	 * @param end the upper bound of the range of master approval details (not inclusive)
	 * @return the range of master approval details
	 */
	@Override
	public java.util.List<com.astra.dewa.model.MasterApprovalDetail>
		getMasterApprovalDetails(int start, int end) {

		return _masterApprovalDetailLocalService.getMasterApprovalDetails(
			start, end);
	}

	/**
	 * Returns the number of master approval details.
	 *
	 * @return the number of master approval details
	 */
	@Override
	public int getMasterApprovalDetailsCount() {
		return _masterApprovalDetailLocalService.
			getMasterApprovalDetailsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _masterApprovalDetailLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalDetailLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the master approval detail in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalDetailLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param masterApprovalDetail the master approval detail
	 * @return the master approval detail that was updated
	 */
	@Override
	public com.astra.dewa.model.MasterApprovalDetail updateMasterApprovalDetail(
		com.astra.dewa.model.MasterApprovalDetail masterApprovalDetail) {

		return _masterApprovalDetailLocalService.updateMasterApprovalDetail(
			masterApprovalDetail);
	}

	@Override
	public MasterApprovalDetailLocalService getWrappedService() {
		return _masterApprovalDetailLocalService;
	}

	@Override
	public void setWrappedService(
		MasterApprovalDetailLocalService masterApprovalDetailLocalService) {

		_masterApprovalDetailLocalService = masterApprovalDetailLocalService;
	}

	private MasterApprovalDetailLocalService _masterApprovalDetailLocalService;

}