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
 * Provides a wrapper for {@link ApprovalHeaderUserLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApprovalHeaderUserLocalService
 * @generated
 */
public class ApprovalHeaderUserLocalServiceWrapper
	implements ApprovalHeaderUserLocalService,
			   ServiceWrapper<ApprovalHeaderUserLocalService> {

	public ApprovalHeaderUserLocalServiceWrapper() {
		this(null);
	}

	public ApprovalHeaderUserLocalServiceWrapper(
		ApprovalHeaderUserLocalService approvalHeaderUserLocalService) {

		_approvalHeaderUserLocalService = approvalHeaderUserLocalService;
	}

	/**
	 * Adds the approval header user to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApprovalHeaderUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param approvalHeaderUser the approval header user
	 * @return the approval header user that was added
	 */
	@Override
	public com.astra.dewa.model.ApprovalHeaderUser addApprovalHeaderUser(
		com.astra.dewa.model.ApprovalHeaderUser approvalHeaderUser) {

		return _approvalHeaderUserLocalService.addApprovalHeaderUser(
			approvalHeaderUser);
	}

	/**
	 * Creates a new approval header user with the primary key. Does not add the approval header user to the database.
	 *
	 * @param Id the primary key for the new approval header user
	 * @return the new approval header user
	 */
	@Override
	public com.astra.dewa.model.ApprovalHeaderUser createApprovalHeaderUser(
		int Id) {

		return _approvalHeaderUserLocalService.createApprovalHeaderUser(Id);
	}

	@Override
	public com.astra.dewa.model.ApprovalHeaderUser
			createApprovalHeaderUserWithHistoryAndDetails(
				com.astra.dewa.model.ApprovalHeaderUser approvalHeaderUser,
				String roles)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _approvalHeaderUserLocalService.
			createApprovalHeaderUserWithHistoryAndDetails(
				approvalHeaderUser, roles);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalHeaderUserLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the approval header user from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApprovalHeaderUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param approvalHeaderUser the approval header user
	 * @return the approval header user that was removed
	 */
	@Override
	public com.astra.dewa.model.ApprovalHeaderUser deleteApprovalHeaderUser(
		com.astra.dewa.model.ApprovalHeaderUser approvalHeaderUser) {

		return _approvalHeaderUserLocalService.deleteApprovalHeaderUser(
			approvalHeaderUser);
	}

	/**
	 * Deletes the approval header user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApprovalHeaderUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the approval header user
	 * @return the approval header user that was removed
	 * @throws PortalException if a approval header user with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApprovalHeaderUser deleteApprovalHeaderUser(
			int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalHeaderUserLocalService.deleteApprovalHeaderUser(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalHeaderUserLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _approvalHeaderUserLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _approvalHeaderUserLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _approvalHeaderUserLocalService.dynamicQuery();
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

		return _approvalHeaderUserLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApprovalHeaderUserModelImpl</code>.
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

		return _approvalHeaderUserLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApprovalHeaderUserModelImpl</code>.
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

		return _approvalHeaderUserLocalService.dynamicQuery(
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

		return _approvalHeaderUserLocalService.dynamicQueryCount(dynamicQuery);
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

		return _approvalHeaderUserLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.ApprovalHeaderUser fetchApprovalHeaderUser(
		int Id) {

		return _approvalHeaderUserLocalService.fetchApprovalHeaderUser(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _approvalHeaderUserLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the approval header user with the primary key.
	 *
	 * @param Id the primary key of the approval header user
	 * @return the approval header user
	 * @throws PortalException if a approval header user with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApprovalHeaderUser getApprovalHeaderUser(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalHeaderUserLocalService.getApprovalHeaderUser(Id);
	}

	/**
	 * Returns a range of all the approval header users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApprovalHeaderUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval header users
	 * @param end the upper bound of the range of approval header users (not inclusive)
	 * @return the range of approval header users
	 */
	@Override
	public java.util.List<com.astra.dewa.model.ApprovalHeaderUser>
		getApprovalHeaderUsers(int start, int end) {

		return _approvalHeaderUserLocalService.getApprovalHeaderUsers(
			start, end);
	}

	/**
	 * Returns the number of approval header users.
	 *
	 * @return the number of approval header users
	 */
	@Override
	public int getApprovalHeaderUsersCount() {
		return _approvalHeaderUserLocalService.getApprovalHeaderUsersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _approvalHeaderUserLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _approvalHeaderUserLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalHeaderUserLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the approval header user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApprovalHeaderUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param approvalHeaderUser the approval header user
	 * @return the approval header user that was updated
	 */
	@Override
	public com.astra.dewa.model.ApprovalHeaderUser updateApprovalHeaderUser(
		com.astra.dewa.model.ApprovalHeaderUser approvalHeaderUser) {

		return _approvalHeaderUserLocalService.updateApprovalHeaderUser(
			approvalHeaderUser);
	}

	@Override
	public com.astra.dewa.model.ApprovalHeaderUser
			updateApprovalHeaderUserWithHistory(
				com.astra.dewa.model.ApprovalHeaderUser approvalHeaderUser)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _approvalHeaderUserLocalService.
			updateApprovalHeaderUserWithHistory(approvalHeaderUser);
	}

	@Override
	public ApprovalHeaderUserLocalService getWrappedService() {
		return _approvalHeaderUserLocalService;
	}

	@Override
	public void setWrappedService(
		ApprovalHeaderUserLocalService approvalHeaderUserLocalService) {

		_approvalHeaderUserLocalService = approvalHeaderUserLocalService;
	}

	private ApprovalHeaderUserLocalService _approvalHeaderUserLocalService;

}