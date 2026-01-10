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

import com.astra.dewa.model.Common;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the common service. This utility wraps <code>com.astra.dewa.service.persistence.impl.CommonPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommonPersistence
 * @generated
 */
public class CommonUtil {

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
	public static void clearCache(Common common) {
		getPersistence().clearCache(common);
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
	public static Map<Serializable, Common> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Common> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Common> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Common> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Common> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Common update(Common common) {
		return getPersistence().update(common);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Common update(Common common, ServiceContext serviceContext) {
		return getPersistence().update(common, serviceContext);
	}

	/**
	 * Caches the common in the entity cache if it is enabled.
	 *
	 * @param common the common
	 */
	public static void cacheResult(Common common) {
		getPersistence().cacheResult(common);
	}

	/**
	 * Caches the commons in the entity cache if it is enabled.
	 *
	 * @param commons the commons
	 */
	public static void cacheResult(List<Common> commons) {
		getPersistence().cacheResult(commons);
	}

	/**
	 * Creates a new common with the primary key. Does not add the common to the database.
	 *
	 * @param CommonKey the primary key for the new common
	 * @return the new common
	 */
	public static Common create(String CommonKey) {
		return getPersistence().create(CommonKey);
	}

	/**
	 * Removes the common with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CommonKey the primary key of the common
	 * @return the common that was removed
	 * @throws NoSuchCommonException if a common with the primary key could not be found
	 */
	public static Common remove(String CommonKey)
		throws com.astra.dewa.exception.NoSuchCommonException {

		return getPersistence().remove(CommonKey);
	}

	public static Common updateImpl(Common common) {
		return getPersistence().updateImpl(common);
	}

	/**
	 * Returns the common with the primary key or throws a <code>NoSuchCommonException</code> if it could not be found.
	 *
	 * @param CommonKey the primary key of the common
	 * @return the common
	 * @throws NoSuchCommonException if a common with the primary key could not be found
	 */
	public static Common findByPrimaryKey(String CommonKey)
		throws com.astra.dewa.exception.NoSuchCommonException {

		return getPersistence().findByPrimaryKey(CommonKey);
	}

	/**
	 * Returns the common with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CommonKey the primary key of the common
	 * @return the common, or <code>null</code> if a common with the primary key could not be found
	 */
	public static Common fetchByPrimaryKey(String CommonKey) {
		return getPersistence().fetchByPrimaryKey(CommonKey);
	}

	/**
	 * Returns all the commons.
	 *
	 * @return the commons
	 */
	public static List<Common> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the commons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commons
	 * @param end the upper bound of the range of commons (not inclusive)
	 * @return the range of commons
	 */
	public static List<Common> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the commons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commons
	 * @param end the upper bound of the range of commons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commons
	 */
	public static List<Common> findAll(
		int start, int end, OrderByComparator<Common> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commons
	 * @param end the upper bound of the range of commons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commons
	 */
	public static List<Common> findAll(
		int start, int end, OrderByComparator<Common> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commons from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commons.
	 *
	 * @return the number of commons
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommonPersistence getPersistence() {
		return _persistence;
	}

	private static volatile CommonPersistence _persistence;

}