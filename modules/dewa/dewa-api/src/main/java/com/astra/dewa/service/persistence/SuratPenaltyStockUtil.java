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

import com.astra.dewa.model.SuratPenaltyStock;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the surat penalty stock service. This utility wraps <code>com.astra.dewa.service.persistence.impl.SuratPenaltyStockPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SuratPenaltyStockPersistence
 * @generated
 */
public class SuratPenaltyStockUtil {

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
	public static void clearCache(SuratPenaltyStock suratPenaltyStock) {
		getPersistence().clearCache(suratPenaltyStock);
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
	public static Map<Serializable, SuratPenaltyStock> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SuratPenaltyStock> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SuratPenaltyStock> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SuratPenaltyStock> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SuratPenaltyStock> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SuratPenaltyStock update(
		SuratPenaltyStock suratPenaltyStock) {

		return getPersistence().update(suratPenaltyStock);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SuratPenaltyStock update(
		SuratPenaltyStock suratPenaltyStock, ServiceContext serviceContext) {

		return getPersistence().update(suratPenaltyStock, serviceContext);
	}

	/**
	 * Caches the surat penalty stock in the entity cache if it is enabled.
	 *
	 * @param suratPenaltyStock the surat penalty stock
	 */
	public static void cacheResult(SuratPenaltyStock suratPenaltyStock) {
		getPersistence().cacheResult(suratPenaltyStock);
	}

	/**
	 * Caches the surat penalty stocks in the entity cache if it is enabled.
	 *
	 * @param suratPenaltyStocks the surat penalty stocks
	 */
	public static void cacheResult(List<SuratPenaltyStock> suratPenaltyStocks) {
		getPersistence().cacheResult(suratPenaltyStocks);
	}

	/**
	 * Creates a new surat penalty stock with the primary key. Does not add the surat penalty stock to the database.
	 *
	 * @param Id the primary key for the new surat penalty stock
	 * @return the new surat penalty stock
	 */
	public static SuratPenaltyStock create(long Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the surat penalty stock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the surat penalty stock
	 * @return the surat penalty stock that was removed
	 * @throws NoSuchSuratPenaltyStockException if a surat penalty stock with the primary key could not be found
	 */
	public static SuratPenaltyStock remove(long Id)
		throws com.astra.dewa.exception.NoSuchSuratPenaltyStockException {

		return getPersistence().remove(Id);
	}

	public static SuratPenaltyStock updateImpl(
		SuratPenaltyStock suratPenaltyStock) {

		return getPersistence().updateImpl(suratPenaltyStock);
	}

	/**
	 * Returns the surat penalty stock with the primary key or throws a <code>NoSuchSuratPenaltyStockException</code> if it could not be found.
	 *
	 * @param Id the primary key of the surat penalty stock
	 * @return the surat penalty stock
	 * @throws NoSuchSuratPenaltyStockException if a surat penalty stock with the primary key could not be found
	 */
	public static SuratPenaltyStock findByPrimaryKey(long Id)
		throws com.astra.dewa.exception.NoSuchSuratPenaltyStockException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the surat penalty stock with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the surat penalty stock
	 * @return the surat penalty stock, or <code>null</code> if a surat penalty stock with the primary key could not be found
	 */
	public static SuratPenaltyStock fetchByPrimaryKey(long Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the surat penalty stocks.
	 *
	 * @return the surat penalty stocks
	 */
	public static List<SuratPenaltyStock> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the surat penalty stocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SuratPenaltyStockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of surat penalty stocks
	 * @param end the upper bound of the range of surat penalty stocks (not inclusive)
	 * @return the range of surat penalty stocks
	 */
	public static List<SuratPenaltyStock> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the surat penalty stocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SuratPenaltyStockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of surat penalty stocks
	 * @param end the upper bound of the range of surat penalty stocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of surat penalty stocks
	 */
	public static List<SuratPenaltyStock> findAll(
		int start, int end,
		OrderByComparator<SuratPenaltyStock> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the surat penalty stocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SuratPenaltyStockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of surat penalty stocks
	 * @param end the upper bound of the range of surat penalty stocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of surat penalty stocks
	 */
	public static List<SuratPenaltyStock> findAll(
		int start, int end,
		OrderByComparator<SuratPenaltyStock> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the surat penalty stocks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of surat penalty stocks.
	 *
	 * @return the number of surat penalty stocks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SuratPenaltyStockPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SuratPenaltyStockPersistence _persistence;

}