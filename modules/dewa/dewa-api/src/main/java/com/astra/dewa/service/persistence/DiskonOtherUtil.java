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

import com.astra.dewa.model.DiskonOther;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the diskon other service. This utility wraps <code>com.astra.dewa.service.persistence.impl.DiskonOtherPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiskonOtherPersistence
 * @generated
 */
public class DiskonOtherUtil {

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
	public static void clearCache(DiskonOther diskonOther) {
		getPersistence().clearCache(diskonOther);
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
	public static Map<Serializable, DiskonOther> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DiskonOther> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DiskonOther> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DiskonOther> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DiskonOther> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DiskonOther update(DiskonOther diskonOther) {
		return getPersistence().update(diskonOther);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DiskonOther update(
		DiskonOther diskonOther, ServiceContext serviceContext) {

		return getPersistence().update(diskonOther, serviceContext);
	}

	/**
	 * Caches the diskon other in the entity cache if it is enabled.
	 *
	 * @param diskonOther the diskon other
	 */
	public static void cacheResult(DiskonOther diskonOther) {
		getPersistence().cacheResult(diskonOther);
	}

	/**
	 * Caches the diskon others in the entity cache if it is enabled.
	 *
	 * @param diskonOthers the diskon others
	 */
	public static void cacheResult(List<DiskonOther> diskonOthers) {
		getPersistence().cacheResult(diskonOthers);
	}

	/**
	 * Creates a new diskon other with the primary key. Does not add the diskon other to the database.
	 *
	 * @param diskonOtherId the primary key for the new diskon other
	 * @return the new diskon other
	 */
	public static DiskonOther create(long diskonOtherId) {
		return getPersistence().create(diskonOtherId);
	}

	/**
	 * Removes the diskon other with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param diskonOtherId the primary key of the diskon other
	 * @return the diskon other that was removed
	 * @throws NoSuchDiskonOtherException if a diskon other with the primary key could not be found
	 */
	public static DiskonOther remove(long diskonOtherId)
		throws com.astra.dewa.exception.NoSuchDiskonOtherException {

		return getPersistence().remove(diskonOtherId);
	}

	public static DiskonOther updateImpl(DiskonOther diskonOther) {
		return getPersistence().updateImpl(diskonOther);
	}

	/**
	 * Returns the diskon other with the primary key or throws a <code>NoSuchDiskonOtherException</code> if it could not be found.
	 *
	 * @param diskonOtherId the primary key of the diskon other
	 * @return the diskon other
	 * @throws NoSuchDiskonOtherException if a diskon other with the primary key could not be found
	 */
	public static DiskonOther findByPrimaryKey(long diskonOtherId)
		throws com.astra.dewa.exception.NoSuchDiskonOtherException {

		return getPersistence().findByPrimaryKey(diskonOtherId);
	}

	/**
	 * Returns the diskon other with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param diskonOtherId the primary key of the diskon other
	 * @return the diskon other, or <code>null</code> if a diskon other with the primary key could not be found
	 */
	public static DiskonOther fetchByPrimaryKey(long diskonOtherId) {
		return getPersistence().fetchByPrimaryKey(diskonOtherId);
	}

	/**
	 * Returns all the diskon others.
	 *
	 * @return the diskon others
	 */
	public static List<DiskonOther> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the diskon others.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonOtherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon others
	 * @param end the upper bound of the range of diskon others (not inclusive)
	 * @return the range of diskon others
	 */
	public static List<DiskonOther> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the diskon others.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonOtherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon others
	 * @param end the upper bound of the range of diskon others (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of diskon others
	 */
	public static List<DiskonOther> findAll(
		int start, int end, OrderByComparator<DiskonOther> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the diskon others.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonOtherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon others
	 * @param end the upper bound of the range of diskon others (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of diskon others
	 */
	public static List<DiskonOther> findAll(
		int start, int end, OrderByComparator<DiskonOther> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the diskon others from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of diskon others.
	 *
	 * @return the number of diskon others
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DiskonOtherPersistence getPersistence() {
		return _persistence;
	}

	private static volatile DiskonOtherPersistence _persistence;

}