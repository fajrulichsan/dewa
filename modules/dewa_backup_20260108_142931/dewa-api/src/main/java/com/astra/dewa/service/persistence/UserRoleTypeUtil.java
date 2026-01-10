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

package com.astra.dewa.service.persistence;

import com.astra.dewa.model.UserRoleType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the user role type service. This utility wraps <code>com.astra.dewa.service.persistence.impl.UserRoleTypePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserRoleTypePersistence
 * @generated
 */
public class UserRoleTypeUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(UserRoleType userRoleType) {
		getPersistence().clearCache(userRoleType);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, UserRoleType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserRoleType> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserRoleType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserRoleType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserRoleType> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserRoleType update(UserRoleType userRoleType) {
		return getPersistence().update(userRoleType);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserRoleType update(
		UserRoleType userRoleType, ServiceContext serviceContext) {

		return getPersistence().update(userRoleType, serviceContext);
	}

	/**
	 * Caches the user role type in the entity cache if it is enabled.
	 *
	 * @param userRoleType the user role type
	 */
	public static void cacheResult(UserRoleType userRoleType) {
		getPersistence().cacheResult(userRoleType);
	}

	/**
	 * Caches the user role types in the entity cache if it is enabled.
	 *
	 * @param userRoleTypes the user role types
	 */
	public static void cacheResult(List<UserRoleType> userRoleTypes) {
		getPersistence().cacheResult(userRoleTypes);
	}

	/**
	 * Creates a new user role type with the primary key. Does not add the user role type to the database.
	 *
	 * @param Id the primary key for the new user role type
	 * @return the new user role type
	 */
	public static UserRoleType create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the user role type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the user role type
	 * @return the user role type that was removed
	 * @throws NoSuchUserRoleTypeException if a user role type with the primary key could not be found
	 */
	public static UserRoleType remove(int Id)
		throws com.astra.dewa.exception.NoSuchUserRoleTypeException {

		return getPersistence().remove(Id);
	}

	public static UserRoleType updateImpl(UserRoleType userRoleType) {
		return getPersistence().updateImpl(userRoleType);
	}

	/**
	 * Returns the user role type with the primary key or throws a <code>NoSuchUserRoleTypeException</code> if it could not be found.
	 *
	 * @param Id the primary key of the user role type
	 * @return the user role type
	 * @throws NoSuchUserRoleTypeException if a user role type with the primary key could not be found
	 */
	public static UserRoleType findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchUserRoleTypeException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the user role type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the user role type
	 * @return the user role type, or <code>null</code> if a user role type with the primary key could not be found
	 */
	public static UserRoleType fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the user role types.
	 *
	 * @return the user role types
	 */
	public static List<UserRoleType> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the user role types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRoleTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user role types
	 * @param end the upper bound of the range of user role types (not inclusive)
	 * @return the range of user role types
	 */
	public static List<UserRoleType> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the user role types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRoleTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user role types
	 * @param end the upper bound of the range of user role types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user role types
	 */
	public static List<UserRoleType> findAll(
		int start, int end, OrderByComparator<UserRoleType> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user role types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRoleTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user role types
	 * @param end the upper bound of the range of user role types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user role types
	 */
	public static List<UserRoleType> findAll(
		int start, int end, OrderByComparator<UserRoleType> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the user role types from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of user role types.
	 *
	 * @return the number of user role types
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UserRoleTypePersistence getPersistence() {
		return _persistence;
	}

	private static volatile UserRoleTypePersistence _persistence;

}