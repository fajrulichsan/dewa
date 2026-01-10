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

import com.astra.dewa.model.SkIris;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the sk iris service. This utility wraps <code>com.astra.dewa.service.persistence.impl.SkIrisPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SkIrisPersistence
 * @generated
 */
public class SkIrisUtil {

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
	public static void clearCache(SkIris skIris) {
		getPersistence().clearCache(skIris);
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
	public static Map<Serializable, SkIris> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SkIris> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SkIris> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SkIris> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SkIris> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SkIris update(SkIris skIris) {
		return getPersistence().update(skIris);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SkIris update(SkIris skIris, ServiceContext serviceContext) {
		return getPersistence().update(skIris, serviceContext);
	}

	/**
	 * Caches the sk iris in the entity cache if it is enabled.
	 *
	 * @param skIris the sk iris
	 */
	public static void cacheResult(SkIris skIris) {
		getPersistence().cacheResult(skIris);
	}

	/**
	 * Caches the sk irises in the entity cache if it is enabled.
	 *
	 * @param skIrises the sk irises
	 */
	public static void cacheResult(List<SkIris> skIrises) {
		getPersistence().cacheResult(skIrises);
	}

	/**
	 * Creates a new sk iris with the primary key. Does not add the sk iris to the database.
	 *
	 * @param Id the primary key for the new sk iris
	 * @return the new sk iris
	 */
	public static SkIris create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the sk iris with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the sk iris
	 * @return the sk iris that was removed
	 * @throws NoSuchSkIrisException if a sk iris with the primary key could not be found
	 */
	public static SkIris remove(int Id)
		throws com.astra.dewa.exception.NoSuchSkIrisException {

		return getPersistence().remove(Id);
	}

	public static SkIris updateImpl(SkIris skIris) {
		return getPersistence().updateImpl(skIris);
	}

	/**
	 * Returns the sk iris with the primary key or throws a <code>NoSuchSkIrisException</code> if it could not be found.
	 *
	 * @param Id the primary key of the sk iris
	 * @return the sk iris
	 * @throws NoSuchSkIrisException if a sk iris with the primary key could not be found
	 */
	public static SkIris findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchSkIrisException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the sk iris with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the sk iris
	 * @return the sk iris, or <code>null</code> if a sk iris with the primary key could not be found
	 */
	public static SkIris fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the sk irises.
	 *
	 * @return the sk irises
	 */
	public static List<SkIris> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the sk irises.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkIrisModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sk irises
	 * @param end the upper bound of the range of sk irises (not inclusive)
	 * @return the range of sk irises
	 */
	public static List<SkIris> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the sk irises.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkIrisModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sk irises
	 * @param end the upper bound of the range of sk irises (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sk irises
	 */
	public static List<SkIris> findAll(
		int start, int end, OrderByComparator<SkIris> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the sk irises.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkIrisModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sk irises
	 * @param end the upper bound of the range of sk irises (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sk irises
	 */
	public static List<SkIris> findAll(
		int start, int end, OrderByComparator<SkIris> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the sk irises from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of sk irises.
	 *
	 * @return the number of sk irises
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SkIrisPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SkIrisPersistence _persistence;

}