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

import com.astra.dewa.exception.NoSuchWilayahException;
import com.astra.dewa.model.Wilayah;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the wilayah service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WilayahUtil
 * @generated
 */
@ProviderType
public interface WilayahPersistence extends BasePersistence<Wilayah> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WilayahUtil} to access the wilayah persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the wilayah in the entity cache if it is enabled.
	 *
	 * @param wilayah the wilayah
	 */
	public void cacheResult(Wilayah wilayah);

	/**
	 * Caches the wilayahs in the entity cache if it is enabled.
	 *
	 * @param wilayahs the wilayahs
	 */
	public void cacheResult(java.util.List<Wilayah> wilayahs);

	/**
	 * Creates a new wilayah with the primary key. Does not add the wilayah to the database.
	 *
	 * @param Id the primary key for the new wilayah
	 * @return the new wilayah
	 */
	public Wilayah create(int Id);

	/**
	 * Removes the wilayah with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the wilayah
	 * @return the wilayah that was removed
	 * @throws NoSuchWilayahException if a wilayah with the primary key could not be found
	 */
	public Wilayah remove(int Id) throws NoSuchWilayahException;

	public Wilayah updateImpl(Wilayah wilayah);

	/**
	 * Returns the wilayah with the primary key or throws a <code>NoSuchWilayahException</code> if it could not be found.
	 *
	 * @param Id the primary key of the wilayah
	 * @return the wilayah
	 * @throws NoSuchWilayahException if a wilayah with the primary key could not be found
	 */
	public Wilayah findByPrimaryKey(int Id) throws NoSuchWilayahException;

	/**
	 * Returns the wilayah with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the wilayah
	 * @return the wilayah, or <code>null</code> if a wilayah with the primary key could not be found
	 */
	public Wilayah fetchByPrimaryKey(int Id);

	/**
	 * Returns all the wilayahs.
	 *
	 * @return the wilayahs
	 */
	public java.util.List<Wilayah> findAll();

	/**
	 * Returns a range of all the wilayahs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WilayahModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wilayahs
	 * @param end the upper bound of the range of wilayahs (not inclusive)
	 * @return the range of wilayahs
	 */
	public java.util.List<Wilayah> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the wilayahs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WilayahModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wilayahs
	 * @param end the upper bound of the range of wilayahs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of wilayahs
	 */
	public java.util.List<Wilayah> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Wilayah>
			orderByComparator);

	/**
	 * Returns an ordered range of all the wilayahs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WilayahModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wilayahs
	 * @param end the upper bound of the range of wilayahs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of wilayahs
	 */
	public java.util.List<Wilayah> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Wilayah>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the wilayahs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of wilayahs.
	 *
	 * @return the number of wilayahs
	 */
	public int countAll();

}