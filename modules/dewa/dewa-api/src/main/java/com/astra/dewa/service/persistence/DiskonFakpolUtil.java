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

import com.astra.dewa.model.DiskonFakpol;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the diskon fakpol service. This utility wraps <code>com.astra.dewa.service.persistence.impl.DiskonFakpolPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiskonFakpolPersistence
 * @generated
 */
public class DiskonFakpolUtil {

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
	public static void clearCache(DiskonFakpol diskonFakpol) {
		getPersistence().clearCache(diskonFakpol);
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
	public static Map<Serializable, DiskonFakpol> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DiskonFakpol> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DiskonFakpol> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DiskonFakpol> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DiskonFakpol> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DiskonFakpol update(DiskonFakpol diskonFakpol) {
		return getPersistence().update(diskonFakpol);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DiskonFakpol update(
		DiskonFakpol diskonFakpol, ServiceContext serviceContext) {

		return getPersistence().update(diskonFakpol, serviceContext);
	}

	/**
	 * Caches the diskon fakpol in the entity cache if it is enabled.
	 *
	 * @param diskonFakpol the diskon fakpol
	 */
	public static void cacheResult(DiskonFakpol diskonFakpol) {
		getPersistence().cacheResult(diskonFakpol);
	}

	/**
	 * Caches the diskon fakpols in the entity cache if it is enabled.
	 *
	 * @param diskonFakpols the diskon fakpols
	 */
	public static void cacheResult(List<DiskonFakpol> diskonFakpols) {
		getPersistence().cacheResult(diskonFakpols);
	}

	/**
	 * Creates a new diskon fakpol with the primary key. Does not add the diskon fakpol to the database.
	 *
	 * @param Id the primary key for the new diskon fakpol
	 * @return the new diskon fakpol
	 */
	public static DiskonFakpol create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the diskon fakpol with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the diskon fakpol
	 * @return the diskon fakpol that was removed
	 * @throws NoSuchDiskonFakpolException if a diskon fakpol with the primary key could not be found
	 */
	public static DiskonFakpol remove(int Id)
		throws com.astra.dewa.exception.NoSuchDiskonFakpolException {

		return getPersistence().remove(Id);
	}

	public static DiskonFakpol updateImpl(DiskonFakpol diskonFakpol) {
		return getPersistence().updateImpl(diskonFakpol);
	}

	/**
	 * Returns the diskon fakpol with the primary key or throws a <code>NoSuchDiskonFakpolException</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon fakpol
	 * @return the diskon fakpol
	 * @throws NoSuchDiskonFakpolException if a diskon fakpol with the primary key could not be found
	 */
	public static DiskonFakpol findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchDiskonFakpolException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the diskon fakpol with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon fakpol
	 * @return the diskon fakpol, or <code>null</code> if a diskon fakpol with the primary key could not be found
	 */
	public static DiskonFakpol fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the diskon fakpols.
	 *
	 * @return the diskon fakpols
	 */
	public static List<DiskonFakpol> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the diskon fakpols.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFakpolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fakpols
	 * @param end the upper bound of the range of diskon fakpols (not inclusive)
	 * @return the range of diskon fakpols
	 */
	public static List<DiskonFakpol> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the diskon fakpols.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFakpolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fakpols
	 * @param end the upper bound of the range of diskon fakpols (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of diskon fakpols
	 */
	public static List<DiskonFakpol> findAll(
		int start, int end, OrderByComparator<DiskonFakpol> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the diskon fakpols.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFakpolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fakpols
	 * @param end the upper bound of the range of diskon fakpols (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of diskon fakpols
	 */
	public static List<DiskonFakpol> findAll(
		int start, int end, OrderByComparator<DiskonFakpol> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the diskon fakpols from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of diskon fakpols.
	 *
	 * @return the number of diskon fakpols
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DiskonFakpolPersistence getPersistence() {
		return _persistence;
	}

	private static volatile DiskonFakpolPersistence _persistence;

}