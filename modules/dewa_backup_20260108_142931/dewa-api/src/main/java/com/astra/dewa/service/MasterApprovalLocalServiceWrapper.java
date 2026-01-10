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
 * Provides a wrapper for {@link MasterApprovalLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalLocalService
 * @generated
 */
public class MasterApprovalLocalServiceWrapper
	implements MasterApprovalLocalService,
			   ServiceWrapper<MasterApprovalLocalService> {

	public MasterApprovalLocalServiceWrapper() {
		this(null);
	}

	public MasterApprovalLocalServiceWrapper(
		MasterApprovalLocalService masterApprovalLocalService) {

		_masterApprovalLocalService = masterApprovalLocalService;
	}

	/**
	 * Adds the master approval to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param masterApproval the master approval
	 * @return the master approval that was added
	 */
	@Override
	public com.astra.dewa.model.MasterApproval addMasterApproval(
		com.astra.dewa.model.MasterApproval masterApproval) {

		return _masterApprovalLocalService.addMasterApproval(masterApproval);
	}

	/**
	 * Creates a new master approval with the primary key. Does not add the master approval to the database.
	 *
	 * @param Id the primary key for the new master approval
	 * @return the new master approval
	 */
	@Override
	public com.astra.dewa.model.MasterApproval createMasterApproval(int Id) {
		return _masterApprovalLocalService.createMasterApproval(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the master approval with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the master approval
	 * @return the master approval that was removed
	 * @throws PortalException if a master approval with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.MasterApproval deleteMasterApproval(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalLocalService.deleteMasterApproval(Id);
	}

	/**
	 * Deletes the master approval from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param masterApproval the master approval
	 * @return the master approval that was removed
	 */
	@Override
	public com.astra.dewa.model.MasterApproval deleteMasterApproval(
		com.astra.dewa.model.MasterApproval masterApproval) {

		return _masterApprovalLocalService.deleteMasterApproval(masterApproval);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _masterApprovalLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _masterApprovalLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _masterApprovalLocalService.dynamicQuery();
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

		return _masterApprovalLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MasterApprovalModelImpl</code>.
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

		return _masterApprovalLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MasterApprovalModelImpl</code>.
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

		return _masterApprovalLocalService.dynamicQuery(
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

		return _masterApprovalLocalService.dynamicQueryCount(dynamicQuery);
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

		return _masterApprovalLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.MasterApproval fetchMasterApproval(int Id) {
		return _masterApprovalLocalService.fetchMasterApproval(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _masterApprovalLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _masterApprovalLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the master approval with the primary key.
	 *
	 * @param Id the primary key of the master approval
	 * @return the master approval
	 * @throws PortalException if a master approval with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.MasterApproval getMasterApproval(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalLocalService.getMasterApproval(Id);
	}

	/**
	 * Returns a range of all the master approvals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.MasterApprovalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approvals
	 * @param end the upper bound of the range of master approvals (not inclusive)
	 * @return the range of master approvals
	 */
	@Override
	public java.util.List<com.astra.dewa.model.MasterApproval>
		getMasterApprovals(int start, int end) {

		return _masterApprovalLocalService.getMasterApprovals(start, end);
	}

	/**
	 * Returns the number of master approvals.
	 *
	 * @return the number of master approvals
	 */
	@Override
	public int getMasterApprovalsCount() {
		return _masterApprovalLocalService.getMasterApprovalsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _masterApprovalLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _masterApprovalLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the master approval in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MasterApprovalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param masterApproval the master approval
	 * @return the master approval that was updated
	 */
	@Override
	public com.astra.dewa.model.MasterApproval updateMasterApproval(
		com.astra.dewa.model.MasterApproval masterApproval) {

		return _masterApprovalLocalService.updateMasterApproval(masterApproval);
	}

	@Override
	public MasterApprovalLocalService getWrappedService() {
		return _masterApprovalLocalService;
	}

	@Override
	public void setWrappedService(
		MasterApprovalLocalService masterApprovalLocalService) {

		_masterApprovalLocalService = masterApprovalLocalService;
	}

	private MasterApprovalLocalService _masterApprovalLocalService;

}