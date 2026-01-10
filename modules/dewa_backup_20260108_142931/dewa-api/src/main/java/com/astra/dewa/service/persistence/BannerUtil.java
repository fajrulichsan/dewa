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

import com.astra.dewa.model.Banner;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the banner service. This utility wraps <code>com.astra.dewa.service.persistence.impl.BannerPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BannerPersistence
 * @generated
 */
public class BannerUtil {

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
	public static void clearCache(Banner banner) {
		getPersistence().clearCache(banner);
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
	public static Map<Serializable, Banner> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Banner> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Banner> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Banner> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Banner> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Banner update(Banner banner) {
		return getPersistence().update(banner);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Banner update(Banner banner, ServiceContext serviceContext) {
		return getPersistence().update(banner, serviceContext);
	}

	/**
	 * Caches the banner in the entity cache if it is enabled.
	 *
	 * @param banner the banner
	 */
	public static void cacheResult(Banner banner) {
		getPersistence().cacheResult(banner);
	}

	/**
	 * Caches the banners in the entity cache if it is enabled.
	 *
	 * @param banners the banners
	 */
	public static void cacheResult(List<Banner> banners) {
		getPersistence().cacheResult(banners);
	}

	/**
	 * Creates a new banner with the primary key. Does not add the banner to the database.
	 *
	 * @param Id the primary key for the new banner
	 * @return the new banner
	 */
	public static Banner create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the banner with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the banner
	 * @return the banner that was removed
	 * @throws NoSuchBannerException if a banner with the primary key could not be found
	 */
	public static Banner remove(int Id)
		throws com.astra.dewa.exception.NoSuchBannerException {

		return getPersistence().remove(Id);
	}

	public static Banner updateImpl(Banner banner) {
		return getPersistence().updateImpl(banner);
	}

	/**
	 * Returns the banner with the primary key or throws a <code>NoSuchBannerException</code> if it could not be found.
	 *
	 * @param Id the primary key of the banner
	 * @return the banner
	 * @throws NoSuchBannerException if a banner with the primary key could not be found
	 */
	public static Banner findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchBannerException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the banner with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the banner
	 * @return the banner, or <code>null</code> if a banner with the primary key could not be found
	 */
	public static Banner fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the banners.
	 *
	 * @return the banners
	 */
	public static List<Banner> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the banners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BannerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of banners
	 * @param end the upper bound of the range of banners (not inclusive)
	 * @return the range of banners
	 */
	public static List<Banner> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the banners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BannerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of banners
	 * @param end the upper bound of the range of banners (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of banners
	 */
	public static List<Banner> findAll(
		int start, int end, OrderByComparator<Banner> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the banners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BannerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of banners
	 * @param end the upper bound of the range of banners (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of banners
	 */
	public static List<Banner> findAll(
		int start, int end, OrderByComparator<Banner> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the banners from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of banners.
	 *
	 * @return the number of banners
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static BannerPersistence getPersistence() {
		return _persistence;
	}

	private static volatile BannerPersistence _persistence;

}