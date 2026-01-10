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

import com.astra.dewa.exception.NoSuchTahunException;
import com.astra.dewa.model.Tahun;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the tahun service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TahunUtil
 * @generated
 */
@ProviderType
public interface TahunPersistence extends BasePersistence<Tahun> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TahunUtil} to access the tahun persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the tahun in the entity cache if it is enabled.
	 *
	 * @param tahun the tahun
	 */
	public void cacheResult(Tahun tahun);

	/**
	 * Caches the tahuns in the entity cache if it is enabled.
	 *
	 * @param tahuns the tahuns
	 */
	public void cacheResult(java.util.List<Tahun> tahuns);

	/**
	 * Creates a new tahun with the primary key. Does not add the tahun to the database.
	 *
	 * @param Id the primary key for the new tahun
	 * @return the new tahun
	 */
	public Tahun create(String Id);

	/**
	 * Removes the tahun with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the tahun
	 * @return the tahun that was removed
	 * @throws NoSuchTahunException if a tahun with the primary key could not be found
	 */
	public Tahun remove(String Id) throws NoSuchTahunException;

	public Tahun updateImpl(Tahun tahun);

	/**
	 * Returns the tahun with the primary key or throws a <code>NoSuchTahunException</code> if it could not be found.
	 *
	 * @param Id the primary key of the tahun
	 * @return the tahun
	 * @throws NoSuchTahunException if a tahun with the primary key could not be found
	 */
	public Tahun findByPrimaryKey(String Id) throws NoSuchTahunException;

	/**
	 * Returns the tahun with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the tahun
	 * @return the tahun, or <code>null</code> if a tahun with the primary key could not be found
	 */
	public Tahun fetchByPrimaryKey(String Id);

	/**
	 * Returns all the tahuns.
	 *
	 * @return the tahuns
	 */
	public java.util.List<Tahun> findAll();

	/**
	 * Returns a range of all the tahuns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TahunModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tahuns
	 * @param end the upper bound of the range of tahuns (not inclusive)
	 * @return the range of tahuns
	 */
	public java.util.List<Tahun> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the tahuns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TahunModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tahuns
	 * @param end the upper bound of the range of tahuns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tahuns
	 */
	public java.util.List<Tahun> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tahun>
			orderByComparator);

	/**
	 * Returns an ordered range of all the tahuns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TahunModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tahuns
	 * @param end the upper bound of the range of tahuns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of tahuns
	 */
	public java.util.List<Tahun> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tahun>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the tahuns from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of tahuns.
	 *
	 * @return the number of tahuns
	 */
	public int countAll();

}