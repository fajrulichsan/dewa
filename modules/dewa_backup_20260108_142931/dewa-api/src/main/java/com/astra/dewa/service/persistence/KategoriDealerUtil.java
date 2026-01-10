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

import com.astra.dewa.model.KategoriDealer;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the kategori dealer service. This utility wraps <code>com.astra.dewa.service.persistence.impl.KategoriDealerPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KategoriDealerPersistence
 * @generated
 */
public class KategoriDealerUtil {

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
	public static void clearCache(KategoriDealer kategoriDealer) {
		getPersistence().clearCache(kategoriDealer);
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
	public static Map<Serializable, KategoriDealer> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KategoriDealer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KategoriDealer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KategoriDealer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KategoriDealer> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static KategoriDealer update(KategoriDealer kategoriDealer) {
		return getPersistence().update(kategoriDealer);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static KategoriDealer update(
		KategoriDealer kategoriDealer, ServiceContext serviceContext) {

		return getPersistence().update(kategoriDealer, serviceContext);
	}

	/**
	 * Caches the kategori dealer in the entity cache if it is enabled.
	 *
	 * @param kategoriDealer the kategori dealer
	 */
	public static void cacheResult(KategoriDealer kategoriDealer) {
		getPersistence().cacheResult(kategoriDealer);
	}

	/**
	 * Caches the kategori dealers in the entity cache if it is enabled.
	 *
	 * @param kategoriDealers the kategori dealers
	 */
	public static void cacheResult(List<KategoriDealer> kategoriDealers) {
		getPersistence().cacheResult(kategoriDealers);
	}

	/**
	 * Creates a new kategori dealer with the primary key. Does not add the kategori dealer to the database.
	 *
	 * @param Id the primary key for the new kategori dealer
	 * @return the new kategori dealer
	 */
	public static KategoriDealer create(long Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the kategori dealer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the kategori dealer
	 * @return the kategori dealer that was removed
	 * @throws NoSuchKategoriDealerException if a kategori dealer with the primary key could not be found
	 */
	public static KategoriDealer remove(long Id)
		throws com.astra.dewa.exception.NoSuchKategoriDealerException {

		return getPersistence().remove(Id);
	}

	public static KategoriDealer updateImpl(KategoriDealer kategoriDealer) {
		return getPersistence().updateImpl(kategoriDealer);
	}

	/**
	 * Returns the kategori dealer with the primary key or throws a <code>NoSuchKategoriDealerException</code> if it could not be found.
	 *
	 * @param Id the primary key of the kategori dealer
	 * @return the kategori dealer
	 * @throws NoSuchKategoriDealerException if a kategori dealer with the primary key could not be found
	 */
	public static KategoriDealer findByPrimaryKey(long Id)
		throws com.astra.dewa.exception.NoSuchKategoriDealerException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the kategori dealer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the kategori dealer
	 * @return the kategori dealer, or <code>null</code> if a kategori dealer with the primary key could not be found
	 */
	public static KategoriDealer fetchByPrimaryKey(long Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the kategori dealers.
	 *
	 * @return the kategori dealers
	 */
	public static List<KategoriDealer> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the kategori dealers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KategoriDealerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kategori dealers
	 * @param end the upper bound of the range of kategori dealers (not inclusive)
	 * @return the range of kategori dealers
	 */
	public static List<KategoriDealer> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the kategori dealers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KategoriDealerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kategori dealers
	 * @param end the upper bound of the range of kategori dealers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kategori dealers
	 */
	public static List<KategoriDealer> findAll(
		int start, int end,
		OrderByComparator<KategoriDealer> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the kategori dealers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KategoriDealerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kategori dealers
	 * @param end the upper bound of the range of kategori dealers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of kategori dealers
	 */
	public static List<KategoriDealer> findAll(
		int start, int end, OrderByComparator<KategoriDealer> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the kategori dealers from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of kategori dealers.
	 *
	 * @return the number of kategori dealers
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static KategoriDealerPersistence getPersistence() {
		return _persistence;
	}

	private static volatile KategoriDealerPersistence _persistence;

}