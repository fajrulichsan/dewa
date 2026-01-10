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

import com.astra.dewa.model.ApprovalDetailUser;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the approval detail user service. This utility wraps <code>com.astra.dewa.service.persistence.impl.ApprovalDetailUserPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApprovalDetailUserPersistence
 * @generated
 */
public class ApprovalDetailUserUtil {

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
	public static void clearCache(ApprovalDetailUser approvalDetailUser) {
		getPersistence().clearCache(approvalDetailUser);
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
	public static Map<Serializable, ApprovalDetailUser> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ApprovalDetailUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ApprovalDetailUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ApprovalDetailUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ApprovalDetailUser> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ApprovalDetailUser update(
		ApprovalDetailUser approvalDetailUser) {

		return getPersistence().update(approvalDetailUser);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ApprovalDetailUser update(
		ApprovalDetailUser approvalDetailUser, ServiceContext serviceContext) {

		return getPersistence().update(approvalDetailUser, serviceContext);
	}

	/**
	 * Returns the approval detail user where ApprovalHeaderUserId = &#63; and RowStatus = &#63; or throws a <code>NoSuchApprovalDetailUserException</code> if it could not be found.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @return the matching approval detail user
	 * @throws NoSuchApprovalDetailUserException if a matching approval detail user could not be found
	 */
	public static ApprovalDetailUser findByFindDetailUser(
			int ApprovalHeaderUserId, Boolean RowStatus)
		throws com.astra.dewa.exception.NoSuchApprovalDetailUserException {

		return getPersistence().findByFindDetailUser(
			ApprovalHeaderUserId, RowStatus);
	}

	/**
	 * Returns the approval detail user where ApprovalHeaderUserId = &#63; and RowStatus = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @return the matching approval detail user, or <code>null</code> if a matching approval detail user could not be found
	 */
	public static ApprovalDetailUser fetchByFindDetailUser(
		int ApprovalHeaderUserId, Boolean RowStatus) {

		return getPersistence().fetchByFindDetailUser(
			ApprovalHeaderUserId, RowStatus);
	}

	/**
	 * Returns the approval detail user where ApprovalHeaderUserId = &#63; and RowStatus = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching approval detail user, or <code>null</code> if a matching approval detail user could not be found
	 */
	public static ApprovalDetailUser fetchByFindDetailUser(
		int ApprovalHeaderUserId, Boolean RowStatus, boolean useFinderCache) {

		return getPersistence().fetchByFindDetailUser(
			ApprovalHeaderUserId, RowStatus, useFinderCache);
	}

	/**
	 * Removes the approval detail user where ApprovalHeaderUserId = &#63; and RowStatus = &#63; from the database.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @return the approval detail user that was removed
	 */
	public static ApprovalDetailUser removeByFindDetailUser(
			int ApprovalHeaderUserId, Boolean RowStatus)
		throws com.astra.dewa.exception.NoSuchApprovalDetailUserException {

		return getPersistence().removeByFindDetailUser(
			ApprovalHeaderUserId, RowStatus);
	}

	/**
	 * Returns the number of approval detail users where ApprovalHeaderUserId = &#63; and RowStatus = &#63;.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @return the number of matching approval detail users
	 */
	public static int countByFindDetailUser(
		int ApprovalHeaderUserId, Boolean RowStatus) {

		return getPersistence().countByFindDetailUser(
			ApprovalHeaderUserId, RowStatus);
	}

	/**
	 * Caches the approval detail user in the entity cache if it is enabled.
	 *
	 * @param approvalDetailUser the approval detail user
	 */
	public static void cacheResult(ApprovalDetailUser approvalDetailUser) {
		getPersistence().cacheResult(approvalDetailUser);
	}

	/**
	 * Caches the approval detail users in the entity cache if it is enabled.
	 *
	 * @param approvalDetailUsers the approval detail users
	 */
	public static void cacheResult(
		List<ApprovalDetailUser> approvalDetailUsers) {

		getPersistence().cacheResult(approvalDetailUsers);
	}

	/**
	 * Creates a new approval detail user with the primary key. Does not add the approval detail user to the database.
	 *
	 * @param Id the primary key for the new approval detail user
	 * @return the new approval detail user
	 */
	public static ApprovalDetailUser create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the approval detail user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the approval detail user
	 * @return the approval detail user that was removed
	 * @throws NoSuchApprovalDetailUserException if a approval detail user with the primary key could not be found
	 */
	public static ApprovalDetailUser remove(int Id)
		throws com.astra.dewa.exception.NoSuchApprovalDetailUserException {

		return getPersistence().remove(Id);
	}

	public static ApprovalDetailUser updateImpl(
		ApprovalDetailUser approvalDetailUser) {

		return getPersistence().updateImpl(approvalDetailUser);
	}

	/**
	 * Returns the approval detail user with the primary key or throws a <code>NoSuchApprovalDetailUserException</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval detail user
	 * @return the approval detail user
	 * @throws NoSuchApprovalDetailUserException if a approval detail user with the primary key could not be found
	 */
	public static ApprovalDetailUser findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchApprovalDetailUserException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the approval detail user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval detail user
	 * @return the approval detail user, or <code>null</code> if a approval detail user with the primary key could not be found
	 */
	public static ApprovalDetailUser fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the approval detail users.
	 *
	 * @return the approval detail users
	 */
	public static List<ApprovalDetailUser> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the approval detail users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalDetailUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval detail users
	 * @param end the upper bound of the range of approval detail users (not inclusive)
	 * @return the range of approval detail users
	 */
	public static List<ApprovalDetailUser> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the approval detail users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalDetailUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval detail users
	 * @param end the upper bound of the range of approval detail users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of approval detail users
	 */
	public static List<ApprovalDetailUser> findAll(
		int start, int end,
		OrderByComparator<ApprovalDetailUser> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the approval detail users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalDetailUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval detail users
	 * @param end the upper bound of the range of approval detail users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of approval detail users
	 */
	public static List<ApprovalDetailUser> findAll(
		int start, int end,
		OrderByComparator<ApprovalDetailUser> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the approval detail users from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of approval detail users.
	 *
	 * @return the number of approval detail users
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ApprovalDetailUserPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ApprovalDetailUserPersistence _persistence;

}