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
 * Provides a wrapper for {@link ApprovalDetailUserLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApprovalDetailUserLocalService
 * @generated
 */
public class ApprovalDetailUserLocalServiceWrapper
	implements ApprovalDetailUserLocalService,
			   ServiceWrapper<ApprovalDetailUserLocalService> {

	public ApprovalDetailUserLocalServiceWrapper() {
		this(null);
	}

	public ApprovalDetailUserLocalServiceWrapper(
		ApprovalDetailUserLocalService approvalDetailUserLocalService) {

		_approvalDetailUserLocalService = approvalDetailUserLocalService;
	}

	/**
	 * Adds the approval detail user to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApprovalDetailUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param approvalDetailUser the approval detail user
	 * @return the approval detail user that was added
	 */
	@Override
	public com.astra.dewa.model.ApprovalDetailUser addApprovalDetailUser(
		com.astra.dewa.model.ApprovalDetailUser approvalDetailUser) {

		return _approvalDetailUserLocalService.addApprovalDetailUser(
			approvalDetailUser);
	}

	/**
	 * Creates a new approval detail user with the primary key. Does not add the approval detail user to the database.
	 *
	 * @param Id the primary key for the new approval detail user
	 * @return the new approval detail user
	 */
	@Override
	public com.astra.dewa.model.ApprovalDetailUser createApprovalDetailUser(
		int Id) {

		return _approvalDetailUserLocalService.createApprovalDetailUser(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalDetailUserLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the approval detail user from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApprovalDetailUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param approvalDetailUser the approval detail user
	 * @return the approval detail user that was removed
	 */
	@Override
	public com.astra.dewa.model.ApprovalDetailUser deleteApprovalDetailUser(
		com.astra.dewa.model.ApprovalDetailUser approvalDetailUser) {

		return _approvalDetailUserLocalService.deleteApprovalDetailUser(
			approvalDetailUser);
	}

	/**
	 * Deletes the approval detail user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApprovalDetailUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the approval detail user
	 * @return the approval detail user that was removed
	 * @throws PortalException if a approval detail user with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApprovalDetailUser deleteApprovalDetailUser(
			int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalDetailUserLocalService.deleteApprovalDetailUser(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalDetailUserLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _approvalDetailUserLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _approvalDetailUserLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _approvalDetailUserLocalService.dynamicQuery();
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

		return _approvalDetailUserLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApprovalDetailUserModelImpl</code>.
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

		return _approvalDetailUserLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApprovalDetailUserModelImpl</code>.
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

		return _approvalDetailUserLocalService.dynamicQuery(
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

		return _approvalDetailUserLocalService.dynamicQueryCount(dynamicQuery);
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

		return _approvalDetailUserLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.ApprovalDetailUser fetchApprovalDetailUser(
		int Id) {

		return _approvalDetailUserLocalService.fetchApprovalDetailUser(Id);
	}

	@Override
	public com.astra.dewa.model.ApprovalDetailUser findDetailUser(
			Integer approvalHeaderUserId)
		throws com.astra.dewa.exception.NoSuchApprovalDetailUserException {

		return _approvalDetailUserLocalService.findDetailUser(
			approvalHeaderUserId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _approvalDetailUserLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the approval detail user with the primary key.
	 *
	 * @param Id the primary key of the approval detail user
	 * @return the approval detail user
	 * @throws PortalException if a approval detail user with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApprovalDetailUser getApprovalDetailUser(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalDetailUserLocalService.getApprovalDetailUser(Id);
	}

	/**
	 * Returns a range of all the approval detail users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApprovalDetailUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval detail users
	 * @param end the upper bound of the range of approval detail users (not inclusive)
	 * @return the range of approval detail users
	 */
	@Override
	public java.util.List<com.astra.dewa.model.ApprovalDetailUser>
		getApprovalDetailUsers(int start, int end) {

		return _approvalDetailUserLocalService.getApprovalDetailUsers(
			start, end);
	}

	/**
	 * Returns the number of approval detail users.
	 *
	 * @return the number of approval detail users
	 */
	@Override
	public int getApprovalDetailUsersCount() {
		return _approvalDetailUserLocalService.getApprovalDetailUsersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _approvalDetailUserLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _approvalDetailUserLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _approvalDetailUserLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the approval detail user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApprovalDetailUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param approvalDetailUser the approval detail user
	 * @return the approval detail user that was updated
	 */
	@Override
	public com.astra.dewa.model.ApprovalDetailUser updateApprovalDetailUser(
		com.astra.dewa.model.ApprovalDetailUser approvalDetailUser) {

		return _approvalDetailUserLocalService.updateApprovalDetailUser(
			approvalDetailUser);
	}

	@Override
	public com.astra.dewa.model.ApprovalDetailUser updateDetailUserAndUserRole(
			com.astra.dewa.model.ApprovalDetailUser approvalDetailUser,
			long userId, int roleId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _approvalDetailUserLocalService.updateDetailUserAndUserRole(
			approvalDetailUser, userId, roleId);
	}

	@Override
	public ApprovalDetailUserLocalService getWrappedService() {
		return _approvalDetailUserLocalService;
	}

	@Override
	public void setWrappedService(
		ApprovalDetailUserLocalService approvalDetailUserLocalService) {

		_approvalDetailUserLocalService = approvalDetailUserLocalService;
	}

	private ApprovalDetailUserLocalService _approvalDetailUserLocalService;

}