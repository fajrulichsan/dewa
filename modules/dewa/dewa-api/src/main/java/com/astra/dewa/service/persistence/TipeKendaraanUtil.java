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

import com.astra.dewa.model.TipeKendaraan;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the tipe kendaraan service. This utility wraps <code>com.astra.dewa.service.persistence.impl.TipeKendaraanPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TipeKendaraanPersistence
 * @generated
 */
public class TipeKendaraanUtil {

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
	public static void clearCache(TipeKendaraan tipeKendaraan) {
		getPersistence().clearCache(tipeKendaraan);
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
	public static Map<Serializable, TipeKendaraan> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TipeKendaraan> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TipeKendaraan> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TipeKendaraan> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TipeKendaraan> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TipeKendaraan update(TipeKendaraan tipeKendaraan) {
		return getPersistence().update(tipeKendaraan);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TipeKendaraan update(
		TipeKendaraan tipeKendaraan, ServiceContext serviceContext) {

		return getPersistence().update(tipeKendaraan, serviceContext);
	}

	/**
	 * Caches the tipe kendaraan in the entity cache if it is enabled.
	 *
	 * @param tipeKendaraan the tipe kendaraan
	 */
	public static void cacheResult(TipeKendaraan tipeKendaraan) {
		getPersistence().cacheResult(tipeKendaraan);
	}

	/**
	 * Caches the tipe kendaraans in the entity cache if it is enabled.
	 *
	 * @param tipeKendaraans the tipe kendaraans
	 */
	public static void cacheResult(List<TipeKendaraan> tipeKendaraans) {
		getPersistence().cacheResult(tipeKendaraans);
	}

	/**
	 * Creates a new tipe kendaraan with the primary key. Does not add the tipe kendaraan to the database.
	 *
	 * @param Id the primary key for the new tipe kendaraan
	 * @return the new tipe kendaraan
	 */
	public static TipeKendaraan create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the tipe kendaraan with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the tipe kendaraan
	 * @return the tipe kendaraan that was removed
	 * @throws NoSuchTipeKendaraanException if a tipe kendaraan with the primary key could not be found
	 */
	public static TipeKendaraan remove(int Id)
		throws com.astra.dewa.exception.NoSuchTipeKendaraanException {

		return getPersistence().remove(Id);
	}

	public static TipeKendaraan updateImpl(TipeKendaraan tipeKendaraan) {
		return getPersistence().updateImpl(tipeKendaraan);
	}

	/**
	 * Returns the tipe kendaraan with the primary key or throws a <code>NoSuchTipeKendaraanException</code> if it could not be found.
	 *
	 * @param Id the primary key of the tipe kendaraan
	 * @return the tipe kendaraan
	 * @throws NoSuchTipeKendaraanException if a tipe kendaraan with the primary key could not be found
	 */
	public static TipeKendaraan findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchTipeKendaraanException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the tipe kendaraan with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the tipe kendaraan
	 * @return the tipe kendaraan, or <code>null</code> if a tipe kendaraan with the primary key could not be found
	 */
	public static TipeKendaraan fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the tipe kendaraans.
	 *
	 * @return the tipe kendaraans
	 */
	public static List<TipeKendaraan> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the tipe kendaraans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TipeKendaraanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tipe kendaraans
	 * @param end the upper bound of the range of tipe kendaraans (not inclusive)
	 * @return the range of tipe kendaraans
	 */
	public static List<TipeKendaraan> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the tipe kendaraans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TipeKendaraanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tipe kendaraans
	 * @param end the upper bound of the range of tipe kendaraans (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tipe kendaraans
	 */
	public static List<TipeKendaraan> findAll(
		int start, int end,
		OrderByComparator<TipeKendaraan> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tipe kendaraans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TipeKendaraanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tipe kendaraans
	 * @param end the upper bound of the range of tipe kendaraans (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of tipe kendaraans
	 */
	public static List<TipeKendaraan> findAll(
		int start, int end, OrderByComparator<TipeKendaraan> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the tipe kendaraans from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of tipe kendaraans.
	 *
	 * @return the number of tipe kendaraans
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TipeKendaraanPersistence getPersistence() {
		return _persistence;
	}

	private static volatile TipeKendaraanPersistence _persistence;

}