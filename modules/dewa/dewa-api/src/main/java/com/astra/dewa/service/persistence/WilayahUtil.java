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

import com.astra.dewa.model.Wilayah;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the wilayah service. This utility wraps <code>com.astra.dewa.service.persistence.impl.WilayahPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WilayahPersistence
 * @generated
 */
public class WilayahUtil {

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
	public static void clearCache(Wilayah wilayah) {
		getPersistence().clearCache(wilayah);
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
	public static Map<Serializable, Wilayah> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Wilayah> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Wilayah> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Wilayah> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Wilayah> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Wilayah update(Wilayah wilayah) {
		return getPersistence().update(wilayah);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Wilayah update(
		Wilayah wilayah, ServiceContext serviceContext) {

		return getPersistence().update(wilayah, serviceContext);
	}

	/**
	 * Caches the wilayah in the entity cache if it is enabled.
	 *
	 * @param wilayah the wilayah
	 */
	public static void cacheResult(Wilayah wilayah) {
		getPersistence().cacheResult(wilayah);
	}

	/**
	 * Caches the wilayahs in the entity cache if it is enabled.
	 *
	 * @param wilayahs the wilayahs
	 */
	public static void cacheResult(List<Wilayah> wilayahs) {
		getPersistence().cacheResult(wilayahs);
	}

	/**
	 * Creates a new wilayah with the primary key. Does not add the wilayah to the database.
	 *
	 * @param Id the primary key for the new wilayah
	 * @return the new wilayah
	 */
	public static Wilayah create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the wilayah with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the wilayah
	 * @return the wilayah that was removed
	 * @throws NoSuchWilayahException if a wilayah with the primary key could not be found
	 */
	public static Wilayah remove(int Id)
		throws com.astra.dewa.exception.NoSuchWilayahException {

		return getPersistence().remove(Id);
	}

	public static Wilayah updateImpl(Wilayah wilayah) {
		return getPersistence().updateImpl(wilayah);
	}

	/**
	 * Returns the wilayah with the primary key or throws a <code>NoSuchWilayahException</code> if it could not be found.
	 *
	 * @param Id the primary key of the wilayah
	 * @return the wilayah
	 * @throws NoSuchWilayahException if a wilayah with the primary key could not be found
	 */
	public static Wilayah findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchWilayahException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the wilayah with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the wilayah
	 * @return the wilayah, or <code>null</code> if a wilayah with the primary key could not be found
	 */
	public static Wilayah fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the wilayahs.
	 *
	 * @return the wilayahs
	 */
	public static List<Wilayah> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the wilayahs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WilayahModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wilayahs
	 * @param end the upper bound of the range of wilayahs (not inclusive)
	 * @return the range of wilayahs
	 */
	public static List<Wilayah> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the wilayahs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WilayahModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wilayahs
	 * @param end the upper bound of the range of wilayahs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of wilayahs
	 */
	public static List<Wilayah> findAll(
		int start, int end, OrderByComparator<Wilayah> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the wilayahs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WilayahModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wilayahs
	 * @param end the upper bound of the range of wilayahs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of wilayahs
	 */
	public static List<Wilayah> findAll(
		int start, int end, OrderByComparator<Wilayah> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the wilayahs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of wilayahs.
	 *
	 * @return the number of wilayahs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static WilayahPersistence getPersistence() {
		return _persistence;
	}

	private static volatile WilayahPersistence _persistence;

}