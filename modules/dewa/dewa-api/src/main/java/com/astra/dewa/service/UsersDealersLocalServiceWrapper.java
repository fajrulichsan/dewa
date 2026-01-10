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
 * Provides a wrapper for {@link UsersDealersLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UsersDealersLocalService
 * @generated
 */
public class UsersDealersLocalServiceWrapper
	implements ServiceWrapper<UsersDealersLocalService>,
			   UsersDealersLocalService {

	public UsersDealersLocalServiceWrapper() {
		this(null);
	}

	public UsersDealersLocalServiceWrapper(
		UsersDealersLocalService usersDealersLocalService) {

		_usersDealersLocalService = usersDealersLocalService;
	}

	/**
	 * Adds the users dealers to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UsersDealersLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param usersDealers the users dealers
	 * @return the users dealers that was added
	 */
	@Override
	public com.astra.dewa.model.UsersDealers addUsersDealers(
		com.astra.dewa.model.UsersDealers usersDealers) {

		return _usersDealersLocalService.addUsersDealers(usersDealers);
	}

	@Override
	public com.astra.dewa.model.UsersDealers createDealerUserWithRoles(
			com.astra.dewa.model.UsersDealers dealerUser,
			java.util.List<Integer> roles)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _usersDealersLocalService.createDealerUserWithRoles(
			dealerUser, roles);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _usersDealersLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new users dealers with the primary key. Does not add the users dealers to the database.
	 *
	 * @param Id the primary key for the new users dealers
	 * @return the new users dealers
	 */
	@Override
	public com.astra.dewa.model.UsersDealers createUsersDealers(int Id) {
		return _usersDealersLocalService.createUsersDealers(Id);
	}

	@Override
	public com.astra.dewa.model.UsersDealers deleteDealerUserWithRoles(
			com.astra.dewa.model.UsersDealers dealerUser)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _usersDealersLocalService.deleteDealerUserWithRoles(dealerUser);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _usersDealersLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the users dealers with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UsersDealersLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the users dealers
	 * @return the users dealers that was removed
	 * @throws PortalException if a users dealers with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.UsersDealers deleteUsersDealers(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _usersDealersLocalService.deleteUsersDealers(Id);
	}

	/**
	 * Deletes the users dealers from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UsersDealersLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param usersDealers the users dealers
	 * @return the users dealers that was removed
	 */
	@Override
	public com.astra.dewa.model.UsersDealers deleteUsersDealers(
		com.astra.dewa.model.UsersDealers usersDealers) {

		return _usersDealersLocalService.deleteUsersDealers(usersDealers);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _usersDealersLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _usersDealersLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _usersDealersLocalService.dynamicQuery();
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

		return _usersDealersLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.UsersDealersModelImpl</code>.
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

		return _usersDealersLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.UsersDealersModelImpl</code>.
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

		return _usersDealersLocalService.dynamicQuery(
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

		return _usersDealersLocalService.dynamicQueryCount(dynamicQuery);
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

		return _usersDealersLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.UsersDealers fetchUsersDealers(int Id) {
		return _usersDealersLocalService.fetchUsersDealers(Id);
	}

	@Override
	public com.astra.dewa.model.UsersDealers findUsersDealersIdFromUserId(
			long userId, boolean rowStatus)
		throws com.astra.dewa.exception.NoSuchUsersDealersException {

		return _usersDealersLocalService.findUsersDealersIdFromUserId(
			userId, rowStatus);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _usersDealersLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.astra.dewa.model.UsersDealers> getDealerUsers(
			String order, int start, int length, String searchValue,
			String roleQuery)
		throws Exception {

		return _usersDealersLocalService.getDealerUsers(
			order, start, length, searchValue, roleQuery);
	}

	@Override
	public long getDealerUsersCount(String searchValue, String roleQuery)
		throws Exception {

		return _usersDealersLocalService.getDealerUsersCount(
			searchValue, roleQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _usersDealersLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _usersDealersLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _usersDealersLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the users dealers with the primary key.
	 *
	 * @param Id the primary key of the users dealers
	 * @return the users dealers
	 * @throws PortalException if a users dealers with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.UsersDealers getUsersDealers(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _usersDealersLocalService.getUsersDealers(Id);
	}

	/**
	 * Returns a range of all the users dealerses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.UsersDealersModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of users dealerses
	 * @param end the upper bound of the range of users dealerses (not inclusive)
	 * @return the range of users dealerses
	 */
	@Override
	public java.util.List<com.astra.dewa.model.UsersDealers> getUsersDealerses(
		int start, int end) {

		return _usersDealersLocalService.getUsersDealerses(start, end);
	}

	/**
	 * Returns the number of users dealerses.
	 *
	 * @return the number of users dealerses
	 */
	@Override
	public int getUsersDealersesCount() {
		return _usersDealersLocalService.getUsersDealersesCount();
	}

	/**
	 * Updates the users dealers in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UsersDealersLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param usersDealers the users dealers
	 * @return the users dealers that was updated
	 */
	@Override
	public com.astra.dewa.model.UsersDealers updateUsersDealers(
		com.astra.dewa.model.UsersDealers usersDealers) {

		return _usersDealersLocalService.updateUsersDealers(usersDealers);
	}

	@Override
	public UsersDealersLocalService getWrappedService() {
		return _usersDealersLocalService;
	}

	@Override
	public void setWrappedService(
		UsersDealersLocalService usersDealersLocalService) {

		_usersDealersLocalService = usersDealersLocalService;
	}

	private UsersDealersLocalService _usersDealersLocalService;

}