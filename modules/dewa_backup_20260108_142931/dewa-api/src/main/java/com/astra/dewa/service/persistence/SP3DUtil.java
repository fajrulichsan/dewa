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

import com.astra.dewa.model.SP3D;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the sp3d service. This utility wraps <code>com.astra.dewa.service.persistence.impl.SP3DPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SP3DPersistence
 * @generated
 */
public class SP3DUtil {

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
	public static void clearCache(SP3D sp3d) {
		getPersistence().clearCache(sp3d);
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
	public static Map<Serializable, SP3D> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SP3D> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SP3D> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SP3D> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SP3D> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SP3D update(SP3D sp3d) {
		return getPersistence().update(sp3d);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SP3D update(SP3D sp3d, ServiceContext serviceContext) {
		return getPersistence().update(sp3d, serviceContext);
	}

	/**
	 * Caches the sp3d in the entity cache if it is enabled.
	 *
	 * @param sp3d the sp3d
	 */
	public static void cacheResult(SP3D sp3d) {
		getPersistence().cacheResult(sp3d);
	}

	/**
	 * Caches the sp3ds in the entity cache if it is enabled.
	 *
	 * @param sp3ds the sp3ds
	 */
	public static void cacheResult(List<SP3D> sp3ds) {
		getPersistence().cacheResult(sp3ds);
	}

	/**
	 * Creates a new sp3d with the primary key. Does not add the sp3d to the database.
	 *
	 * @param Id the primary key for the new sp3d
	 * @return the new sp3d
	 */
	public static SP3D create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the sp3d with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the sp3d
	 * @return the sp3d that was removed
	 * @throws NoSuchSP3DException if a sp3d with the primary key could not be found
	 */
	public static SP3D remove(int Id)
		throws com.astra.dewa.exception.NoSuchSP3DException {

		return getPersistence().remove(Id);
	}

	public static SP3D updateImpl(SP3D sp3d) {
		return getPersistence().updateImpl(sp3d);
	}

	/**
	 * Returns the sp3d with the primary key or throws a <code>NoSuchSP3DException</code> if it could not be found.
	 *
	 * @param Id the primary key of the sp3d
	 * @return the sp3d
	 * @throws NoSuchSP3DException if a sp3d with the primary key could not be found
	 */
	public static SP3D findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchSP3DException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the sp3d with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the sp3d
	 * @return the sp3d, or <code>null</code> if a sp3d with the primary key could not be found
	 */
	public static SP3D fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the sp3ds.
	 *
	 * @return the sp3ds
	 */
	public static List<SP3D> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the sp3ds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SP3DModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sp3ds
	 * @param end the upper bound of the range of sp3ds (not inclusive)
	 * @return the range of sp3ds
	 */
	public static List<SP3D> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the sp3ds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SP3DModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sp3ds
	 * @param end the upper bound of the range of sp3ds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sp3ds
	 */
	public static List<SP3D> findAll(
		int start, int end, OrderByComparator<SP3D> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the sp3ds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SP3DModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sp3ds
	 * @param end the upper bound of the range of sp3ds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sp3ds
	 */
	public static List<SP3D> findAll(
		int start, int end, OrderByComparator<SP3D> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the sp3ds from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of sp3ds.
	 *
	 * @return the number of sp3ds
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SP3DPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SP3DPersistence _persistence;

}