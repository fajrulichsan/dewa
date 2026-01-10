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

import com.astra.dewa.model.Kuartal;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the kuartal service. This utility wraps <code>com.astra.dewa.service.persistence.impl.KuartalPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KuartalPersistence
 * @generated
 */
public class KuartalUtil {

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
	public static void clearCache(Kuartal kuartal) {
		getPersistence().clearCache(kuartal);
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
	public static Map<Serializable, Kuartal> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Kuartal> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Kuartal> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Kuartal> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Kuartal> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Kuartal update(Kuartal kuartal) {
		return getPersistence().update(kuartal);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Kuartal update(
		Kuartal kuartal, ServiceContext serviceContext) {

		return getPersistence().update(kuartal, serviceContext);
	}

	/**
	 * Caches the kuartal in the entity cache if it is enabled.
	 *
	 * @param kuartal the kuartal
	 */
	public static void cacheResult(Kuartal kuartal) {
		getPersistence().cacheResult(kuartal);
	}

	/**
	 * Caches the kuartals in the entity cache if it is enabled.
	 *
	 * @param kuartals the kuartals
	 */
	public static void cacheResult(List<Kuartal> kuartals) {
		getPersistence().cacheResult(kuartals);
	}

	/**
	 * Creates a new kuartal with the primary key. Does not add the kuartal to the database.
	 *
	 * @param Id the primary key for the new kuartal
	 * @return the new kuartal
	 */
	public static Kuartal create(String Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the kuartal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the kuartal
	 * @return the kuartal that was removed
	 * @throws NoSuchKuartalException if a kuartal with the primary key could not be found
	 */
	public static Kuartal remove(String Id)
		throws com.astra.dewa.exception.NoSuchKuartalException {

		return getPersistence().remove(Id);
	}

	public static Kuartal updateImpl(Kuartal kuartal) {
		return getPersistence().updateImpl(kuartal);
	}

	/**
	 * Returns the kuartal with the primary key or throws a <code>NoSuchKuartalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the kuartal
	 * @return the kuartal
	 * @throws NoSuchKuartalException if a kuartal with the primary key could not be found
	 */
	public static Kuartal findByPrimaryKey(String Id)
		throws com.astra.dewa.exception.NoSuchKuartalException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the kuartal with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the kuartal
	 * @return the kuartal, or <code>null</code> if a kuartal with the primary key could not be found
	 */
	public static Kuartal fetchByPrimaryKey(String Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the kuartals.
	 *
	 * @return the kuartals
	 */
	public static List<Kuartal> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the kuartals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KuartalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kuartals
	 * @param end the upper bound of the range of kuartals (not inclusive)
	 * @return the range of kuartals
	 */
	public static List<Kuartal> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the kuartals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KuartalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kuartals
	 * @param end the upper bound of the range of kuartals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kuartals
	 */
	public static List<Kuartal> findAll(
		int start, int end, OrderByComparator<Kuartal> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the kuartals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KuartalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kuartals
	 * @param end the upper bound of the range of kuartals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of kuartals
	 */
	public static List<Kuartal> findAll(
		int start, int end, OrderByComparator<Kuartal> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the kuartals from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of kuartals.
	 *
	 * @return the number of kuartals
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static KuartalPersistence getPersistence() {
		return _persistence;
	}

	private static volatile KuartalPersistence _persistence;

}