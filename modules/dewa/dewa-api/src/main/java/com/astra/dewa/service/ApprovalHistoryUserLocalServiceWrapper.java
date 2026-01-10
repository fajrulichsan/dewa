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
 * Provides a wrapper for {@link ApprovalHistoryUserLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApprovalHistoryUserLocalService
 * @generated
 */
public class ApprovalHistoryUserLocalServiceWrapper
	implements ApprovalHistoryUserLocalService,
			   ServiceWrapper<ApprovalHistoryUserLocalService> {

	public ApprovalHistoryUserLocalServiceWrapper() {
		this(null);
	}

	public ApprovalHistoryUserLocalServiceWrapper(
		ApprovalHistoryUserLocalService approvalHistoryUserLocalService) {

		_approvalHistoryUserLocalService = approvalHistoryUserLocalService;
	}

	/**
	 * Adds the approval history user to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApprovalHistoryUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param approvalHistoryUser the approval history user
	 * @return the approval history user that was added
	 */
	@Override
	public com.astra.dewa.model.ApprovalHistoryUser addApprovalHistoryUser(
		com.astra.dewa.model.ApprovalHistoryUser approvalHistoryUser) {

		return _approvalHistoryUserLocalService.addApprovalHistoryUser(
			approvalHistoryUser);
	}

	/**
	 * Creates a new approval history user with the primary key. Does not add the approval history user to the database.
	 *
	 * @param Id the primary key for the new approval history user
	 * @return the new approval history user
	 */
	@Override
	public com.astra.dewa.model.ApprovalHistoryUser createApprovalHistoryUser(
		int Id) {

		return _approvalHistoryUserLocalService.createApprovalHistoryUser(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalHistoryUserLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the approval history user from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApprovalHistoryUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param approvalHistoryUser the approval history user
	 * @return the approval history user that was removed
	 */
	@Override
	public com.astra.dewa.model.ApprovalHistoryUser deleteApprovalHistoryUser(
		com.astra.dewa.model.ApprovalHistoryUser approvalHistoryUser) {

		return _approvalHistoryUserLocalService.deleteApprovalHistoryUser(
			approvalHistoryUser);
	}

	/**
	 * Deletes the approval history user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApprovalHistoryUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the approval history user
	 * @return the approval history user that was removed
	 * @throws PortalException if a approval history user with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApprovalHistoryUser deleteApprovalHistoryUser(
			int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalHistoryUserLocalService.deleteApprovalHistoryUser(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalHistoryUserLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _approvalHistoryUserLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _approvalHistoryUserLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _approvalHistoryUserLocalService.dynamicQuery();
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

		return _approvalHistoryUserLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApprovalHistoryUserModelImpl</code>.
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

		return _approvalHistoryUserLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApprovalHistoryUserModelImpl</code>.
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

		return _approvalHistoryUserLocalService.dynamicQuery(
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

		return _approvalHistoryUserLocalService.dynamicQueryCount(dynamicQuery);
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

		return _approvalHistoryUserLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.ApprovalHistoryUser fetchApprovalHistoryUser(
		int Id) {

		return _approvalHistoryUserLocalService.fetchApprovalHistoryUser(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _approvalHistoryUserLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the approval history user with the primary key.
	 *
	 * @param Id the primary key of the approval history user
	 * @return the approval history user
	 * @throws PortalException if a approval history user with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApprovalHistoryUser getApprovalHistoryUser(
			int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalHistoryUserLocalService.getApprovalHistoryUser(Id);
	}

	/**
	 * Returns a range of all the approval history users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApprovalHistoryUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval history users
	 * @param end the upper bound of the range of approval history users (not inclusive)
	 * @return the range of approval history users
	 */
	@Override
	public java.util.List<com.astra.dewa.model.ApprovalHistoryUser>
		getApprovalHistoryUsers(int start, int end) {

		return _approvalHistoryUserLocalService.getApprovalHistoryUsers(
			start, end);
	}

	/**
	 * Returns the number of approval history users.
	 *
	 * @return the number of approval history users
	 */
	@Override
	public int getApprovalHistoryUsersCount() {
		return _approvalHistoryUserLocalService.getApprovalHistoryUsersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _approvalHistoryUserLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _approvalHistoryUserLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalHistoryUserLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the approval history user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApprovalHistoryUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param approvalHistoryUser the approval history user
	 * @return the approval history user that was updated
	 */
	@Override
	public com.astra.dewa.model.ApprovalHistoryUser updateApprovalHistoryUser(
		com.astra.dewa.model.ApprovalHistoryUser approvalHistoryUser) {

		return _approvalHistoryUserLocalService.updateApprovalHistoryUser(
			approvalHistoryUser);
	}

	@Override
	public ApprovalHistoryUserLocalService getWrappedService() {
		return _approvalHistoryUserLocalService;
	}

	@Override
	public void setWrappedService(
		ApprovalHistoryUserLocalService approvalHistoryUserLocalService) {

		_approvalHistoryUserLocalService = approvalHistoryUserLocalService;
	}

	private ApprovalHistoryUserLocalService _approvalHistoryUserLocalService;

}