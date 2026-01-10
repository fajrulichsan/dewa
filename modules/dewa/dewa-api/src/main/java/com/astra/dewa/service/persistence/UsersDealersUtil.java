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

import com.astra.dewa.model.UsersDealers;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the users dealers service. This utility wraps <code>com.astra.dewa.service.persistence.impl.UsersDealersPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UsersDealersPersistence
 * @generated
 */
public class UsersDealersUtil {

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
	public static void clearCache(UsersDealers usersDealers) {
		getPersistence().clearCache(usersDealers);
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
	public static Map<Serializable, UsersDealers> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UsersDealers> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UsersDealers> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UsersDealers> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UsersDealers> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UsersDealers update(UsersDealers usersDealers) {
		return getPersistence().update(usersDealers);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UsersDealers update(
		UsersDealers usersDealers, ServiceContext serviceContext) {

		return getPersistence().update(usersDealers, serviceContext);
	}

	/**
	 * Returns the users dealers where UserId = &#63; and RowStatus = &#63; or throws a <code>NoSuchUsersDealersException</code> if it could not be found.
	 *
	 * @param UserId the user ID
	 * @param RowStatus the row status
	 * @return the matching users dealers
	 * @throws NoSuchUsersDealersException if a matching users dealers could not be found
	 */
	public static UsersDealers findByUsersDealersIdFromUserId(
			long UserId, Boolean RowStatus)
		throws com.astra.dewa.exception.NoSuchUsersDealersException {

		return getPersistence().findByUsersDealersIdFromUserId(
			UserId, RowStatus);
	}

	/**
	 * Returns the users dealers where UserId = &#63; and RowStatus = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param UserId the user ID
	 * @param RowStatus the row status
	 * @return the matching users dealers, or <code>null</code> if a matching users dealers could not be found
	 */
	public static UsersDealers fetchByUsersDealersIdFromUserId(
		long UserId, Boolean RowStatus) {

		return getPersistence().fetchByUsersDealersIdFromUserId(
			UserId, RowStatus);
	}

	/**
	 * Returns the users dealers where UserId = &#63; and RowStatus = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param UserId the user ID
	 * @param RowStatus the row status
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching users dealers, or <code>null</code> if a matching users dealers could not be found
	 */
	public static UsersDealers fetchByUsersDealersIdFromUserId(
		long UserId, Boolean RowStatus, boolean useFinderCache) {

		return getPersistence().fetchByUsersDealersIdFromUserId(
			UserId, RowStatus, useFinderCache);
	}

	/**
	 * Removes the users dealers where UserId = &#63; and RowStatus = &#63; from the database.
	 *
	 * @param UserId the user ID
	 * @param RowStatus the row status
	 * @return the users dealers that was removed
	 */
	public static UsersDealers removeByUsersDealersIdFromUserId(
			long UserId, Boolean RowStatus)
		throws com.astra.dewa.exception.NoSuchUsersDealersException {

		return getPersistence().removeByUsersDealersIdFromUserId(
			UserId, RowStatus);
	}

	/**
	 * Returns the number of users dealerses where UserId = &#63; and RowStatus = &#63;.
	 *
	 * @param UserId the user ID
	 * @param RowStatus the row status
	 * @return the number of matching users dealerses
	 */
	public static int countByUsersDealersIdFromUserId(
		long UserId, Boolean RowStatus) {

		return getPersistence().countByUsersDealersIdFromUserId(
			UserId, RowStatus);
	}

	/**
	 * Caches the users dealers in the entity cache if it is enabled.
	 *
	 * @param usersDealers the users dealers
	 */
	public static void cacheResult(UsersDealers usersDealers) {
		getPersistence().cacheResult(usersDealers);
	}

	/**
	 * Caches the users dealerses in the entity cache if it is enabled.
	 *
	 * @param usersDealerses the users dealerses
	 */
	public static void cacheResult(List<UsersDealers> usersDealerses) {
		getPersistence().cacheResult(usersDealerses);
	}

	/**
	 * Creates a new users dealers with the primary key. Does not add the users dealers to the database.
	 *
	 * @param Id the primary key for the new users dealers
	 * @return the new users dealers
	 */
	public static UsersDealers create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the users dealers with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the users dealers
	 * @return the users dealers that was removed
	 * @throws NoSuchUsersDealersException if a users dealers with the primary key could not be found
	 */
	public static UsersDealers remove(int Id)
		throws com.astra.dewa.exception.NoSuchUsersDealersException {

		return getPersistence().remove(Id);
	}

	public static UsersDealers updateImpl(UsersDealers usersDealers) {
		return getPersistence().updateImpl(usersDealers);
	}

	/**
	 * Returns the users dealers with the primary key or throws a <code>NoSuchUsersDealersException</code> if it could not be found.
	 *
	 * @param Id the primary key of the users dealers
	 * @return the users dealers
	 * @throws NoSuchUsersDealersException if a users dealers with the primary key could not be found
	 */
	public static UsersDealers findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchUsersDealersException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the users dealers with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the users dealers
	 * @return the users dealers, or <code>null</code> if a users dealers with the primary key could not be found
	 */
	public static UsersDealers fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the users dealerses.
	 *
	 * @return the users dealerses
	 */
	public static List<UsersDealers> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the users dealerses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UsersDealersModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of users dealerses
	 * @param end the upper bound of the range of users dealerses (not inclusive)
	 * @return the range of users dealerses
	 */
	public static List<UsersDealers> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the users dealerses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UsersDealersModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of users dealerses
	 * @param end the upper bound of the range of users dealerses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of users dealerses
	 */
	public static List<UsersDealers> findAll(
		int start, int end, OrderByComparator<UsersDealers> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the users dealerses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UsersDealersModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of users dealerses
	 * @param end the upper bound of the range of users dealerses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of users dealerses
	 */
	public static List<UsersDealers> findAll(
		int start, int end, OrderByComparator<UsersDealers> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the users dealerses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of users dealerses.
	 *
	 * @return the number of users dealerses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UsersDealersPersistence getPersistence() {
		return _persistence;
	}

	private static volatile UsersDealersPersistence _persistence;

}