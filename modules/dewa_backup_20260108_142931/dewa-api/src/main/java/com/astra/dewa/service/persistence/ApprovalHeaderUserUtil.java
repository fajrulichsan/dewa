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

import com.astra.dewa.model.ApprovalHeaderUser;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the approval header user service. This utility wraps <code>com.astra.dewa.service.persistence.impl.ApprovalHeaderUserPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApprovalHeaderUserPersistence
 * @generated
 */
public class ApprovalHeaderUserUtil {

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
	public static void clearCache(ApprovalHeaderUser approvalHeaderUser) {
		getPersistence().clearCache(approvalHeaderUser);
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
	public static Map<Serializable, ApprovalHeaderUser> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ApprovalHeaderUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ApprovalHeaderUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ApprovalHeaderUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ApprovalHeaderUser> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ApprovalHeaderUser update(
		ApprovalHeaderUser approvalHeaderUser) {

		return getPersistence().update(approvalHeaderUser);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ApprovalHeaderUser update(
		ApprovalHeaderUser approvalHeaderUser, ServiceContext serviceContext) {

		return getPersistence().update(approvalHeaderUser, serviceContext);
	}

	/**
	 * Caches the approval header user in the entity cache if it is enabled.
	 *
	 * @param approvalHeaderUser the approval header user
	 */
	public static void cacheResult(ApprovalHeaderUser approvalHeaderUser) {
		getPersistence().cacheResult(approvalHeaderUser);
	}

	/**
	 * Caches the approval header users in the entity cache if it is enabled.
	 *
	 * @param approvalHeaderUsers the approval header users
	 */
	public static void cacheResult(
		List<ApprovalHeaderUser> approvalHeaderUsers) {

		getPersistence().cacheResult(approvalHeaderUsers);
	}

	/**
	 * Creates a new approval header user with the primary key. Does not add the approval header user to the database.
	 *
	 * @param Id the primary key for the new approval header user
	 * @return the new approval header user
	 */
	public static ApprovalHeaderUser create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the approval header user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the approval header user
	 * @return the approval header user that was removed
	 * @throws NoSuchApprovalHeaderUserException if a approval header user with the primary key could not be found
	 */
	public static ApprovalHeaderUser remove(int Id)
		throws com.astra.dewa.exception.NoSuchApprovalHeaderUserException {

		return getPersistence().remove(Id);
	}

	public static ApprovalHeaderUser updateImpl(
		ApprovalHeaderUser approvalHeaderUser) {

		return getPersistence().updateImpl(approvalHeaderUser);
	}

	/**
	 * Returns the approval header user with the primary key or throws a <code>NoSuchApprovalHeaderUserException</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval header user
	 * @return the approval header user
	 * @throws NoSuchApprovalHeaderUserException if a approval header user with the primary key could not be found
	 */
	public static ApprovalHeaderUser findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchApprovalHeaderUserException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the approval header user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval header user
	 * @return the approval header user, or <code>null</code> if a approval header user with the primary key could not be found
	 */
	public static ApprovalHeaderUser fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the approval header users.
	 *
	 * @return the approval header users
	 */
	public static List<ApprovalHeaderUser> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the approval header users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalHeaderUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval header users
	 * @param end the upper bound of the range of approval header users (not inclusive)
	 * @return the range of approval header users
	 */
	public static List<ApprovalHeaderUser> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the approval header users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalHeaderUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval header users
	 * @param end the upper bound of the range of approval header users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of approval header users
	 */
	public static List<ApprovalHeaderUser> findAll(
		int start, int end,
		OrderByComparator<ApprovalHeaderUser> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the approval header users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalHeaderUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval header users
	 * @param end the upper bound of the range of approval header users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of approval header users
	 */
	public static List<ApprovalHeaderUser> findAll(
		int start, int end,
		OrderByComparator<ApprovalHeaderUser> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the approval header users from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of approval header users.
	 *
	 * @return the number of approval header users
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ApprovalHeaderUserPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ApprovalHeaderUserPersistence _persistence;

}