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

import com.astra.dewa.model.Bulan;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the bulan service. This utility wraps <code>com.astra.dewa.service.persistence.impl.BulanPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BulanPersistence
 * @generated
 */
public class BulanUtil {

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
	public static void clearCache(Bulan bulan) {
		getPersistence().clearCache(bulan);
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
	public static Map<Serializable, Bulan> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Bulan> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Bulan> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Bulan> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Bulan> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Bulan update(Bulan bulan) {
		return getPersistence().update(bulan);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Bulan update(Bulan bulan, ServiceContext serviceContext) {
		return getPersistence().update(bulan, serviceContext);
	}

	/**
	 * Caches the bulan in the entity cache if it is enabled.
	 *
	 * @param bulan the bulan
	 */
	public static void cacheResult(Bulan bulan) {
		getPersistence().cacheResult(bulan);
	}

	/**
	 * Caches the bulans in the entity cache if it is enabled.
	 *
	 * @param bulans the bulans
	 */
	public static void cacheResult(List<Bulan> bulans) {
		getPersistence().cacheResult(bulans);
	}

	/**
	 * Creates a new bulan with the primary key. Does not add the bulan to the database.
	 *
	 * @param Id the primary key for the new bulan
	 * @return the new bulan
	 */
	public static Bulan create(String Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the bulan with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the bulan
	 * @return the bulan that was removed
	 * @throws NoSuchBulanException if a bulan with the primary key could not be found
	 */
	public static Bulan remove(String Id)
		throws com.astra.dewa.exception.NoSuchBulanException {

		return getPersistence().remove(Id);
	}

	public static Bulan updateImpl(Bulan bulan) {
		return getPersistence().updateImpl(bulan);
	}

	/**
	 * Returns the bulan with the primary key or throws a <code>NoSuchBulanException</code> if it could not be found.
	 *
	 * @param Id the primary key of the bulan
	 * @return the bulan
	 * @throws NoSuchBulanException if a bulan with the primary key could not be found
	 */
	public static Bulan findByPrimaryKey(String Id)
		throws com.astra.dewa.exception.NoSuchBulanException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the bulan with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the bulan
	 * @return the bulan, or <code>null</code> if a bulan with the primary key could not be found
	 */
	public static Bulan fetchByPrimaryKey(String Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the bulans.
	 *
	 * @return the bulans
	 */
	public static List<Bulan> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the bulans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BulanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bulans
	 * @param end the upper bound of the range of bulans (not inclusive)
	 * @return the range of bulans
	 */
	public static List<Bulan> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the bulans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BulanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bulans
	 * @param end the upper bound of the range of bulans (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of bulans
	 */
	public static List<Bulan> findAll(
		int start, int end, OrderByComparator<Bulan> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the bulans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BulanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bulans
	 * @param end the upper bound of the range of bulans (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of bulans
	 */
	public static List<Bulan> findAll(
		int start, int end, OrderByComparator<Bulan> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the bulans from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of bulans.
	 *
	 * @return the number of bulans
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static BulanPersistence getPersistence() {
		return _persistence;
	}

	private static volatile BulanPersistence _persistence;

}