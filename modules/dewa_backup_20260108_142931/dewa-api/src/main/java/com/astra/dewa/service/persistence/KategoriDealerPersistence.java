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

import com.astra.dewa.exception.NoSuchKategoriDealerException;
import com.astra.dewa.model.KategoriDealer;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the kategori dealer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KategoriDealerUtil
 * @generated
 */
@ProviderType
public interface KategoriDealerPersistence
	extends BasePersistence<KategoriDealer> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KategoriDealerUtil} to access the kategori dealer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the kategori dealer in the entity cache if it is enabled.
	 *
	 * @param kategoriDealer the kategori dealer
	 */
	public void cacheResult(KategoriDealer kategoriDealer);

	/**
	 * Caches the kategori dealers in the entity cache if it is enabled.
	 *
	 * @param kategoriDealers the kategori dealers
	 */
	public void cacheResult(java.util.List<KategoriDealer> kategoriDealers);

	/**
	 * Creates a new kategori dealer with the primary key. Does not add the kategori dealer to the database.
	 *
	 * @param Id the primary key for the new kategori dealer
	 * @return the new kategori dealer
	 */
	public KategoriDealer create(long Id);

	/**
	 * Removes the kategori dealer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the kategori dealer
	 * @return the kategori dealer that was removed
	 * @throws NoSuchKategoriDealerException if a kategori dealer with the primary key could not be found
	 */
	public KategoriDealer remove(long Id) throws NoSuchKategoriDealerException;

	public KategoriDealer updateImpl(KategoriDealer kategoriDealer);

	/**
	 * Returns the kategori dealer with the primary key or throws a <code>NoSuchKategoriDealerException</code> if it could not be found.
	 *
	 * @param Id the primary key of the kategori dealer
	 * @return the kategori dealer
	 * @throws NoSuchKategoriDealerException if a kategori dealer with the primary key could not be found
	 */
	public KategoriDealer findByPrimaryKey(long Id)
		throws NoSuchKategoriDealerException;

	/**
	 * Returns the kategori dealer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the kategori dealer
	 * @return the kategori dealer, or <code>null</code> if a kategori dealer with the primary key could not be found
	 */
	public KategoriDealer fetchByPrimaryKey(long Id);

	/**
	 * Returns all the kategori dealers.
	 *
	 * @return the kategori dealers
	 */
	public java.util.List<KategoriDealer> findAll();

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
	public java.util.List<KategoriDealer> findAll(int start, int end);

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
	public java.util.List<KategoriDealer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KategoriDealer>
			orderByComparator);

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
	public java.util.List<KategoriDealer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KategoriDealer>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the kategori dealers from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of kategori dealers.
	 *
	 * @return the number of kategori dealers
	 */
	public int countAll();

}