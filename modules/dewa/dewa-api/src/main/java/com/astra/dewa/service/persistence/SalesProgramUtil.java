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

import com.astra.dewa.model.SalesProgram;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the sales program service. This utility wraps <code>com.astra.dewa.service.persistence.impl.SalesProgramPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SalesProgramPersistence
 * @generated
 */
public class SalesProgramUtil {

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
	public static void clearCache(SalesProgram salesProgram) {
		getPersistence().clearCache(salesProgram);
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
	public static Map<Serializable, SalesProgram> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SalesProgram> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SalesProgram> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SalesProgram> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SalesProgram> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SalesProgram update(SalesProgram salesProgram) {
		return getPersistence().update(salesProgram);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SalesProgram update(
		SalesProgram salesProgram, ServiceContext serviceContext) {

		return getPersistence().update(salesProgram, serviceContext);
	}

	/**
	 * Caches the sales program in the entity cache if it is enabled.
	 *
	 * @param salesProgram the sales program
	 */
	public static void cacheResult(SalesProgram salesProgram) {
		getPersistence().cacheResult(salesProgram);
	}

	/**
	 * Caches the sales programs in the entity cache if it is enabled.
	 *
	 * @param salesPrograms the sales programs
	 */
	public static void cacheResult(List<SalesProgram> salesPrograms) {
		getPersistence().cacheResult(salesPrograms);
	}

	/**
	 * Creates a new sales program with the primary key. Does not add the sales program to the database.
	 *
	 * @param Id the primary key for the new sales program
	 * @return the new sales program
	 */
	public static SalesProgram create(long Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the sales program with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the sales program
	 * @return the sales program that was removed
	 * @throws NoSuchSalesProgramException if a sales program with the primary key could not be found
	 */
	public static SalesProgram remove(long Id)
		throws com.astra.dewa.exception.NoSuchSalesProgramException {

		return getPersistence().remove(Id);
	}

	public static SalesProgram updateImpl(SalesProgram salesProgram) {
		return getPersistence().updateImpl(salesProgram);
	}

	/**
	 * Returns the sales program with the primary key or throws a <code>NoSuchSalesProgramException</code> if it could not be found.
	 *
	 * @param Id the primary key of the sales program
	 * @return the sales program
	 * @throws NoSuchSalesProgramException if a sales program with the primary key could not be found
	 */
	public static SalesProgram findByPrimaryKey(long Id)
		throws com.astra.dewa.exception.NoSuchSalesProgramException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the sales program with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the sales program
	 * @return the sales program, or <code>null</code> if a sales program with the primary key could not be found
	 */
	public static SalesProgram fetchByPrimaryKey(long Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the sales programs.
	 *
	 * @return the sales programs
	 */
	public static List<SalesProgram> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the sales programs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SalesProgramModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sales programs
	 * @param end the upper bound of the range of sales programs (not inclusive)
	 * @return the range of sales programs
	 */
	public static List<SalesProgram> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the sales programs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SalesProgramModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sales programs
	 * @param end the upper bound of the range of sales programs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sales programs
	 */
	public static List<SalesProgram> findAll(
		int start, int end, OrderByComparator<SalesProgram> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the sales programs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SalesProgramModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sales programs
	 * @param end the upper bound of the range of sales programs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sales programs
	 */
	public static List<SalesProgram> findAll(
		int start, int end, OrderByComparator<SalesProgram> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the sales programs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of sales programs.
	 *
	 * @return the number of sales programs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SalesProgramPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SalesProgramPersistence _persistence;

}