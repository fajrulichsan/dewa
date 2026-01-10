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
 * Provides a wrapper for {@link UserRoleTypeLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserRoleTypeLocalService
 * @generated
 */
public class UserRoleTypeLocalServiceWrapper
	implements ServiceWrapper<UserRoleTypeLocalService>,
			   UserRoleTypeLocalService {

	public UserRoleTypeLocalServiceWrapper() {
		this(null);
	}

	public UserRoleTypeLocalServiceWrapper(
		UserRoleTypeLocalService userRoleTypeLocalService) {

		_userRoleTypeLocalService = userRoleTypeLocalService;
	}

	/**
	 * Adds the user role type to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRoleTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRoleType the user role type
	 * @return the user role type that was added
	 */
	@Override
	public com.astra.dewa.model.UserRoleType addUserRoleType(
		com.astra.dewa.model.UserRoleType userRoleType) {

		return _userRoleTypeLocalService.addUserRoleType(userRoleType);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRoleTypeLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new user role type with the primary key. Does not add the user role type to the database.
	 *
	 * @param Id the primary key for the new user role type
	 * @return the new user role type
	 */
	@Override
	public com.astra.dewa.model.UserRoleType createUserRoleType(int Id) {
		return _userRoleTypeLocalService.createUserRoleType(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRoleTypeLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the user role type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRoleTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the user role type
	 * @return the user role type that was removed
	 * @throws PortalException if a user role type with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.UserRoleType deleteUserRoleType(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRoleTypeLocalService.deleteUserRoleType(Id);
	}

	/**
	 * Deletes the user role type from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRoleTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRoleType the user role type
	 * @return the user role type that was removed
	 */
	@Override
	public com.astra.dewa.model.UserRoleType deleteUserRoleType(
		com.astra.dewa.model.UserRoleType userRoleType) {

		return _userRoleTypeLocalService.deleteUserRoleType(userRoleType);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _userRoleTypeLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _userRoleTypeLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userRoleTypeLocalService.dynamicQuery();
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

		return _userRoleTypeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.UserRoleTypeModelImpl</code>.
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

		return _userRoleTypeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.UserRoleTypeModelImpl</code>.
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

		return _userRoleTypeLocalService.dynamicQuery(
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

		return _userRoleTypeLocalService.dynamicQueryCount(dynamicQuery);
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

		return _userRoleTypeLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.UserRoleType fetchUserRoleType(int Id) {
		return _userRoleTypeLocalService.fetchUserRoleType(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userRoleTypeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userRoleTypeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userRoleTypeLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRoleTypeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user role type with the primary key.
	 *
	 * @param Id the primary key of the user role type
	 * @return the user role type
	 * @throws PortalException if a user role type with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.UserRoleType getUserRoleType(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userRoleTypeLocalService.getUserRoleType(Id);
	}

	/**
	 * Returns a range of all the user role types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.UserRoleTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user role types
	 * @param end the upper bound of the range of user role types (not inclusive)
	 * @return the range of user role types
	 */
	@Override
	public java.util.List<com.astra.dewa.model.UserRoleType> getUserRoleTypes(
		int start, int end) {

		return _userRoleTypeLocalService.getUserRoleTypes(start, end);
	}

	/**
	 * Returns the number of user role types.
	 *
	 * @return the number of user role types
	 */
	@Override
	public int getUserRoleTypesCount() {
		return _userRoleTypeLocalService.getUserRoleTypesCount();
	}

	/**
	 * Updates the user role type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserRoleTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userRoleType the user role type
	 * @return the user role type that was updated
	 */
	@Override
	public com.astra.dewa.model.UserRoleType updateUserRoleType(
		com.astra.dewa.model.UserRoleType userRoleType) {

		return _userRoleTypeLocalService.updateUserRoleType(userRoleType);
	}

	@Override
	public UserRoleTypeLocalService getWrappedService() {
		return _userRoleTypeLocalService;
	}

	@Override
	public void setWrappedService(
		UserRoleTypeLocalService userRoleTypeLocalService) {

		_userRoleTypeLocalService = userRoleTypeLocalService;
	}

	private UserRoleTypeLocalService _userRoleTypeLocalService;

}