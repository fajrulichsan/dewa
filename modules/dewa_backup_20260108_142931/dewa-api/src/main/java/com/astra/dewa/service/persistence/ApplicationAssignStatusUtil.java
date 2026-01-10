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

import com.astra.dewa.model.ApplicationAssignStatus;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the application assign status service. This utility wraps <code>com.astra.dewa.service.persistence.impl.ApplicationAssignStatusPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationAssignStatusPersistence
 * @generated
 */
public class ApplicationAssignStatusUtil {

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
	public static void clearCache(
		ApplicationAssignStatus applicationAssignStatus) {

		getPersistence().clearCache(applicationAssignStatus);
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
	public static Map<Serializable, ApplicationAssignStatus> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ApplicationAssignStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ApplicationAssignStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ApplicationAssignStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ApplicationAssignStatus> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ApplicationAssignStatus update(
		ApplicationAssignStatus applicationAssignStatus) {

		return getPersistence().update(applicationAssignStatus);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ApplicationAssignStatus update(
		ApplicationAssignStatus applicationAssignStatus,
		ServiceContext serviceContext) {

		return getPersistence().update(applicationAssignStatus, serviceContext);
	}

	/**
	 * Caches the application assign status in the entity cache if it is enabled.
	 *
	 * @param applicationAssignStatus the application assign status
	 */
	public static void cacheResult(
		ApplicationAssignStatus applicationAssignStatus) {

		getPersistence().cacheResult(applicationAssignStatus);
	}

	/**
	 * Caches the application assign statuses in the entity cache if it is enabled.
	 *
	 * @param applicationAssignStatuses the application assign statuses
	 */
	public static void cacheResult(
		List<ApplicationAssignStatus> applicationAssignStatuses) {

		getPersistence().cacheResult(applicationAssignStatuses);
	}

	/**
	 * Creates a new application assign status with the primary key. Does not add the application assign status to the database.
	 *
	 * @param Id the primary key for the new application assign status
	 * @return the new application assign status
	 */
	public static ApplicationAssignStatus create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the application assign status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application assign status
	 * @return the application assign status that was removed
	 * @throws NoSuchApplicationAssignStatusException if a application assign status with the primary key could not be found
	 */
	public static ApplicationAssignStatus remove(int Id)
		throws com.astra.dewa.exception.NoSuchApplicationAssignStatusException {

		return getPersistence().remove(Id);
	}

	public static ApplicationAssignStatus updateImpl(
		ApplicationAssignStatus applicationAssignStatus) {

		return getPersistence().updateImpl(applicationAssignStatus);
	}

	/**
	 * Returns the application assign status with the primary key or throws a <code>NoSuchApplicationAssignStatusException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign status
	 * @return the application assign status
	 * @throws NoSuchApplicationAssignStatusException if a application assign status with the primary key could not be found
	 */
	public static ApplicationAssignStatus findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchApplicationAssignStatusException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the application assign status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign status
	 * @return the application assign status, or <code>null</code> if a application assign status with the primary key could not be found
	 */
	public static ApplicationAssignStatus fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the application assign statuses.
	 *
	 * @return the application assign statuses
	 */
	public static List<ApplicationAssignStatus> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the application assign statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign statuses
	 * @param end the upper bound of the range of application assign statuses (not inclusive)
	 * @return the range of application assign statuses
	 */
	public static List<ApplicationAssignStatus> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the application assign statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign statuses
	 * @param end the upper bound of the range of application assign statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application assign statuses
	 */
	public static List<ApplicationAssignStatus> findAll(
		int start, int end,
		OrderByComparator<ApplicationAssignStatus> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the application assign statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign statuses
	 * @param end the upper bound of the range of application assign statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of application assign statuses
	 */
	public static List<ApplicationAssignStatus> findAll(
		int start, int end,
		OrderByComparator<ApplicationAssignStatus> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the application assign statuses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of application assign statuses.
	 *
	 * @return the number of application assign statuses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ApplicationAssignStatusPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ApplicationAssignStatusPersistence _persistence;

}