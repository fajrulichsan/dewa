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

import com.astra.dewa.model.FakturPajak;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the faktur pajak service. This utility wraps <code>com.astra.dewa.service.persistence.impl.FakturPajakPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FakturPajakPersistence
 * @generated
 */
public class FakturPajakUtil {

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
	public static void clearCache(FakturPajak fakturPajak) {
		getPersistence().clearCache(fakturPajak);
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
	public static Map<Serializable, FakturPajak> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<FakturPajak> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FakturPajak> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FakturPajak> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FakturPajak> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FakturPajak update(FakturPajak fakturPajak) {
		return getPersistence().update(fakturPajak);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FakturPajak update(
		FakturPajak fakturPajak, ServiceContext serviceContext) {

		return getPersistence().update(fakturPajak, serviceContext);
	}

	/**
	 * Caches the faktur pajak in the entity cache if it is enabled.
	 *
	 * @param fakturPajak the faktur pajak
	 */
	public static void cacheResult(FakturPajak fakturPajak) {
		getPersistence().cacheResult(fakturPajak);
	}

	/**
	 * Caches the faktur pajaks in the entity cache if it is enabled.
	 *
	 * @param fakturPajaks the faktur pajaks
	 */
	public static void cacheResult(List<FakturPajak> fakturPajaks) {
		getPersistence().cacheResult(fakturPajaks);
	}

	/**
	 * Creates a new faktur pajak with the primary key. Does not add the faktur pajak to the database.
	 *
	 * @param Id the primary key for the new faktur pajak
	 * @return the new faktur pajak
	 */
	public static FakturPajak create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the faktur pajak with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the faktur pajak
	 * @return the faktur pajak that was removed
	 * @throws NoSuchFakturPajakException if a faktur pajak with the primary key could not be found
	 */
	public static FakturPajak remove(int Id)
		throws com.astra.dewa.exception.NoSuchFakturPajakException {

		return getPersistence().remove(Id);
	}

	public static FakturPajak updateImpl(FakturPajak fakturPajak) {
		return getPersistence().updateImpl(fakturPajak);
	}

	/**
	 * Returns the faktur pajak with the primary key or throws a <code>NoSuchFakturPajakException</code> if it could not be found.
	 *
	 * @param Id the primary key of the faktur pajak
	 * @return the faktur pajak
	 * @throws NoSuchFakturPajakException if a faktur pajak with the primary key could not be found
	 */
	public static FakturPajak findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchFakturPajakException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the faktur pajak with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the faktur pajak
	 * @return the faktur pajak, or <code>null</code> if a faktur pajak with the primary key could not be found
	 */
	public static FakturPajak fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the faktur pajaks.
	 *
	 * @return the faktur pajaks
	 */
	public static List<FakturPajak> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the faktur pajaks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FakturPajakModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur pajaks
	 * @param end the upper bound of the range of faktur pajaks (not inclusive)
	 * @return the range of faktur pajaks
	 */
	public static List<FakturPajak> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the faktur pajaks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FakturPajakModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur pajaks
	 * @param end the upper bound of the range of faktur pajaks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of faktur pajaks
	 */
	public static List<FakturPajak> findAll(
		int start, int end, OrderByComparator<FakturPajak> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the faktur pajaks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FakturPajakModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur pajaks
	 * @param end the upper bound of the range of faktur pajaks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of faktur pajaks
	 */
	public static List<FakturPajak> findAll(
		int start, int end, OrderByComparator<FakturPajak> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the faktur pajaks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of faktur pajaks.
	 *
	 * @return the number of faktur pajaks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FakturPajakPersistence getPersistence() {
		return _persistence;
	}

	private static volatile FakturPajakPersistence _persistence;

}