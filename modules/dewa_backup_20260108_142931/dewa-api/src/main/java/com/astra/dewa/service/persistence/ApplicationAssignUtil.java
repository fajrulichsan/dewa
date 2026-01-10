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

import com.astra.dewa.model.ApplicationAssign;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the application assign service. This utility wraps <code>com.astra.dewa.service.persistence.impl.ApplicationAssignPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationAssignPersistence
 * @generated
 */
public class ApplicationAssignUtil {

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
	public static void clearCache(ApplicationAssign applicationAssign) {
		getPersistence().clearCache(applicationAssign);
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
	public static Map<Serializable, ApplicationAssign> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ApplicationAssign> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ApplicationAssign> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ApplicationAssign> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ApplicationAssign> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ApplicationAssign update(
		ApplicationAssign applicationAssign) {

		return getPersistence().update(applicationAssign);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ApplicationAssign update(
		ApplicationAssign applicationAssign, ServiceContext serviceContext) {

		return getPersistence().update(applicationAssign, serviceContext);
	}

	/**
	 * Caches the application assign in the entity cache if it is enabled.
	 *
	 * @param applicationAssign the application assign
	 */
	public static void cacheResult(ApplicationAssign applicationAssign) {
		getPersistence().cacheResult(applicationAssign);
	}

	/**
	 * Caches the application assigns in the entity cache if it is enabled.
	 *
	 * @param applicationAssigns the application assigns
	 */
	public static void cacheResult(List<ApplicationAssign> applicationAssigns) {
		getPersistence().cacheResult(applicationAssigns);
	}

	/**
	 * Creates a new application assign with the primary key. Does not add the application assign to the database.
	 *
	 * @param Id the primary key for the new application assign
	 * @return the new application assign
	 */
	public static ApplicationAssign create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the application assign with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application assign
	 * @return the application assign that was removed
	 * @throws NoSuchApplicationAssignException if a application assign with the primary key could not be found
	 */
	public static ApplicationAssign remove(int Id)
		throws com.astra.dewa.exception.NoSuchApplicationAssignException {

		return getPersistence().remove(Id);
	}

	public static ApplicationAssign updateImpl(
		ApplicationAssign applicationAssign) {

		return getPersistence().updateImpl(applicationAssign);
	}

	/**
	 * Returns the application assign with the primary key or throws a <code>NoSuchApplicationAssignException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign
	 * @return the application assign
	 * @throws NoSuchApplicationAssignException if a application assign with the primary key could not be found
	 */
	public static ApplicationAssign findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchApplicationAssignException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the application assign with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign
	 * @return the application assign, or <code>null</code> if a application assign with the primary key could not be found
	 */
	public static ApplicationAssign fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the application assigns.
	 *
	 * @return the application assigns
	 */
	public static List<ApplicationAssign> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the application assigns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assigns
	 * @param end the upper bound of the range of application assigns (not inclusive)
	 * @return the range of application assigns
	 */
	public static List<ApplicationAssign> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the application assigns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assigns
	 * @param end the upper bound of the range of application assigns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application assigns
	 */
	public static List<ApplicationAssign> findAll(
		int start, int end,
		OrderByComparator<ApplicationAssign> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the application assigns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assigns
	 * @param end the upper bound of the range of application assigns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of application assigns
	 */
	public static List<ApplicationAssign> findAll(
		int start, int end,
		OrderByComparator<ApplicationAssign> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the application assigns from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of application assigns.
	 *
	 * @return the number of application assigns
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ApplicationAssignPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ApplicationAssignPersistence _persistence;

}