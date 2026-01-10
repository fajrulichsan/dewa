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

import com.astra.dewa.model.TopikMateri;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the topik materi service. This utility wraps <code>com.astra.dewa.service.persistence.impl.TopikMateriPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TopikMateriPersistence
 * @generated
 */
public class TopikMateriUtil {

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
	public static void clearCache(TopikMateri topikMateri) {
		getPersistence().clearCache(topikMateri);
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
	public static Map<Serializable, TopikMateri> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TopikMateri> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TopikMateri> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TopikMateri> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TopikMateri> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TopikMateri update(TopikMateri topikMateri) {
		return getPersistence().update(topikMateri);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TopikMateri update(
		TopikMateri topikMateri, ServiceContext serviceContext) {

		return getPersistence().update(topikMateri, serviceContext);
	}

	/**
	 * Caches the topik materi in the entity cache if it is enabled.
	 *
	 * @param topikMateri the topik materi
	 */
	public static void cacheResult(TopikMateri topikMateri) {
		getPersistence().cacheResult(topikMateri);
	}

	/**
	 * Caches the topik materis in the entity cache if it is enabled.
	 *
	 * @param topikMateris the topik materis
	 */
	public static void cacheResult(List<TopikMateri> topikMateris) {
		getPersistence().cacheResult(topikMateris);
	}

	/**
	 * Creates a new topik materi with the primary key. Does not add the topik materi to the database.
	 *
	 * @param Id the primary key for the new topik materi
	 * @return the new topik materi
	 */
	public static TopikMateri create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the topik materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the topik materi
	 * @return the topik materi that was removed
	 * @throws NoSuchTopikMateriException if a topik materi with the primary key could not be found
	 */
	public static TopikMateri remove(int Id)
		throws com.astra.dewa.exception.NoSuchTopikMateriException {

		return getPersistence().remove(Id);
	}

	public static TopikMateri updateImpl(TopikMateri topikMateri) {
		return getPersistence().updateImpl(topikMateri);
	}

	/**
	 * Returns the topik materi with the primary key or throws a <code>NoSuchTopikMateriException</code> if it could not be found.
	 *
	 * @param Id the primary key of the topik materi
	 * @return the topik materi
	 * @throws NoSuchTopikMateriException if a topik materi with the primary key could not be found
	 */
	public static TopikMateri findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchTopikMateriException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the topik materi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the topik materi
	 * @return the topik materi, or <code>null</code> if a topik materi with the primary key could not be found
	 */
	public static TopikMateri fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the topik materis.
	 *
	 * @return the topik materis
	 */
	public static List<TopikMateri> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the topik materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TopikMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of topik materis
	 * @param end the upper bound of the range of topik materis (not inclusive)
	 * @return the range of topik materis
	 */
	public static List<TopikMateri> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the topik materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TopikMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of topik materis
	 * @param end the upper bound of the range of topik materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of topik materis
	 */
	public static List<TopikMateri> findAll(
		int start, int end, OrderByComparator<TopikMateri> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the topik materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TopikMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of topik materis
	 * @param end the upper bound of the range of topik materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of topik materis
	 */
	public static List<TopikMateri> findAll(
		int start, int end, OrderByComparator<TopikMateri> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the topik materis from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of topik materis.
	 *
	 * @return the number of topik materis
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TopikMateriPersistence getPersistence() {
		return _persistence;
	}

	private static volatile TopikMateriPersistence _persistence;

}