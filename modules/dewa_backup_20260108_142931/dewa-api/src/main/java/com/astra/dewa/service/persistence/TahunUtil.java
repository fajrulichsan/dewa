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

import com.astra.dewa.model.Tahun;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the tahun service. This utility wraps <code>com.astra.dewa.service.persistence.impl.TahunPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TahunPersistence
 * @generated
 */
public class TahunUtil {

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
	public static void clearCache(Tahun tahun) {
		getPersistence().clearCache(tahun);
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
	public static Map<Serializable, Tahun> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Tahun> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Tahun> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Tahun> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Tahun> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Tahun update(Tahun tahun) {
		return getPersistence().update(tahun);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Tahun update(Tahun tahun, ServiceContext serviceContext) {
		return getPersistence().update(tahun, serviceContext);
	}

	/**
	 * Caches the tahun in the entity cache if it is enabled.
	 *
	 * @param tahun the tahun
	 */
	public static void cacheResult(Tahun tahun) {
		getPersistence().cacheResult(tahun);
	}

	/**
	 * Caches the tahuns in the entity cache if it is enabled.
	 *
	 * @param tahuns the tahuns
	 */
	public static void cacheResult(List<Tahun> tahuns) {
		getPersistence().cacheResult(tahuns);
	}

	/**
	 * Creates a new tahun with the primary key. Does not add the tahun to the database.
	 *
	 * @param Id the primary key for the new tahun
	 * @return the new tahun
	 */
	public static Tahun create(String Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the tahun with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the tahun
	 * @return the tahun that was removed
	 * @throws NoSuchTahunException if a tahun with the primary key could not be found
	 */
	public static Tahun remove(String Id)
		throws com.astra.dewa.exception.NoSuchTahunException {

		return getPersistence().remove(Id);
	}

	public static Tahun updateImpl(Tahun tahun) {
		return getPersistence().updateImpl(tahun);
	}

	/**
	 * Returns the tahun with the primary key or throws a <code>NoSuchTahunException</code> if it could not be found.
	 *
	 * @param Id the primary key of the tahun
	 * @return the tahun
	 * @throws NoSuchTahunException if a tahun with the primary key could not be found
	 */
	public static Tahun findByPrimaryKey(String Id)
		throws com.astra.dewa.exception.NoSuchTahunException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the tahun with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the tahun
	 * @return the tahun, or <code>null</code> if a tahun with the primary key could not be found
	 */
	public static Tahun fetchByPrimaryKey(String Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the tahuns.
	 *
	 * @return the tahuns
	 */
	public static List<Tahun> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the tahuns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TahunModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tahuns
	 * @param end the upper bound of the range of tahuns (not inclusive)
	 * @return the range of tahuns
	 */
	public static List<Tahun> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the tahuns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TahunModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tahuns
	 * @param end the upper bound of the range of tahuns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tahuns
	 */
	public static List<Tahun> findAll(
		int start, int end, OrderByComparator<Tahun> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tahuns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TahunModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tahuns
	 * @param end the upper bound of the range of tahuns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of tahuns
	 */
	public static List<Tahun> findAll(
		int start, int end, OrderByComparator<Tahun> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the tahuns from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of tahuns.
	 *
	 * @return the number of tahuns
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TahunPersistence getPersistence() {
		return _persistence;
	}

	private static volatile TahunPersistence _persistence;

}