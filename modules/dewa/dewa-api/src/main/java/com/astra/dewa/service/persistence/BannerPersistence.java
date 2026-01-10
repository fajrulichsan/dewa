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

import com.astra.dewa.exception.NoSuchBannerException;
import com.astra.dewa.model.Banner;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the banner service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BannerUtil
 * @generated
 */
@ProviderType
public interface BannerPersistence extends BasePersistence<Banner> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BannerUtil} to access the banner persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the banner in the entity cache if it is enabled.
	 *
	 * @param banner the banner
	 */
	public void cacheResult(Banner banner);

	/**
	 * Caches the banners in the entity cache if it is enabled.
	 *
	 * @param banners the banners
	 */
	public void cacheResult(java.util.List<Banner> banners);

	/**
	 * Creates a new banner with the primary key. Does not add the banner to the database.
	 *
	 * @param Id the primary key for the new banner
	 * @return the new banner
	 */
	public Banner create(int Id);

	/**
	 * Removes the banner with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the banner
	 * @return the banner that was removed
	 * @throws NoSuchBannerException if a banner with the primary key could not be found
	 */
	public Banner remove(int Id) throws NoSuchBannerException;

	public Banner updateImpl(Banner banner);

	/**
	 * Returns the banner with the primary key or throws a <code>NoSuchBannerException</code> if it could not be found.
	 *
	 * @param Id the primary key of the banner
	 * @return the banner
	 * @throws NoSuchBannerException if a banner with the primary key could not be found
	 */
	public Banner findByPrimaryKey(int Id) throws NoSuchBannerException;

	/**
	 * Returns the banner with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the banner
	 * @return the banner, or <code>null</code> if a banner with the primary key could not be found
	 */
	public Banner fetchByPrimaryKey(int Id);

	/**
	 * Returns all the banners.
	 *
	 * @return the banners
	 */
	public java.util.List<Banner> findAll();

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
	public java.util.List<Banner> findAll(int start, int end);

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
	public java.util.List<Banner> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Banner>
			orderByComparator);

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
	public java.util.List<Banner> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Banner>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the banners from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of banners.
	 *
	 * @return the number of banners
	 */
	public int countAll();

}