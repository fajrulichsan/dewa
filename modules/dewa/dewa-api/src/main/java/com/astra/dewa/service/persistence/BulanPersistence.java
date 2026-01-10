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

import com.astra.dewa.exception.NoSuchBulanException;
import com.astra.dewa.model.Bulan;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the bulan service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BulanUtil
 * @generated
 */
@ProviderType
public interface BulanPersistence extends BasePersistence<Bulan> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BulanUtil} to access the bulan persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the bulan in the entity cache if it is enabled.
	 *
	 * @param bulan the bulan
	 */
	public void cacheResult(Bulan bulan);

	/**
	 * Caches the bulans in the entity cache if it is enabled.
	 *
	 * @param bulans the bulans
	 */
	public void cacheResult(java.util.List<Bulan> bulans);

	/**
	 * Creates a new bulan with the primary key. Does not add the bulan to the database.
	 *
	 * @param Id the primary key for the new bulan
	 * @return the new bulan
	 */
	public Bulan create(String Id);

	/**
	 * Removes the bulan with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the bulan
	 * @return the bulan that was removed
	 * @throws NoSuchBulanException if a bulan with the primary key could not be found
	 */
	public Bulan remove(String Id) throws NoSuchBulanException;

	public Bulan updateImpl(Bulan bulan);

	/**
	 * Returns the bulan with the primary key or throws a <code>NoSuchBulanException</code> if it could not be found.
	 *
	 * @param Id the primary key of the bulan
	 * @return the bulan
	 * @throws NoSuchBulanException if a bulan with the primary key could not be found
	 */
	public Bulan findByPrimaryKey(String Id) throws NoSuchBulanException;

	/**
	 * Returns the bulan with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the bulan
	 * @return the bulan, or <code>null</code> if a bulan with the primary key could not be found
	 */
	public Bulan fetchByPrimaryKey(String Id);

	/**
	 * Returns all the bulans.
	 *
	 * @return the bulans
	 */
	public java.util.List<Bulan> findAll();

	/**
	 * Returns a range of all the bulans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BulanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bulans
	 * @param end the upper bound of the range of bulans (not inclusive)
	 * @return the range of bulans
	 */
	public java.util.List<Bulan> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the bulans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BulanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bulans
	 * @param end the upper bound of the range of bulans (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of bulans
	 */
	public java.util.List<Bulan> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bulan>
			orderByComparator);

	/**
	 * Returns an ordered range of all the bulans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BulanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bulans
	 * @param end the upper bound of the range of bulans (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of bulans
	 */
	public java.util.List<Bulan> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bulan>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the bulans from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of bulans.
	 *
	 * @return the number of bulans
	 */
	public int countAll();

}