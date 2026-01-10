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

import com.astra.dewa.exception.NoSuchCabangException;
import com.astra.dewa.model.Cabang;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the cabang service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CabangUtil
 * @generated
 */
@ProviderType
public interface CabangPersistence extends BasePersistence<Cabang> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CabangUtil} to access the cabang persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the cabang in the entity cache if it is enabled.
	 *
	 * @param cabang the cabang
	 */
	public void cacheResult(Cabang cabang);

	/**
	 * Caches the cabangs in the entity cache if it is enabled.
	 *
	 * @param cabangs the cabangs
	 */
	public void cacheResult(java.util.List<Cabang> cabangs);

	/**
	 * Creates a new cabang with the primary key. Does not add the cabang to the database.
	 *
	 * @param Id the primary key for the new cabang
	 * @return the new cabang
	 */
	public Cabang create(int Id);

	/**
	 * Removes the cabang with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the cabang
	 * @return the cabang that was removed
	 * @throws NoSuchCabangException if a cabang with the primary key could not be found
	 */
	public Cabang remove(int Id) throws NoSuchCabangException;

	public Cabang updateImpl(Cabang cabang);

	/**
	 * Returns the cabang with the primary key or throws a <code>NoSuchCabangException</code> if it could not be found.
	 *
	 * @param Id the primary key of the cabang
	 * @return the cabang
	 * @throws NoSuchCabangException if a cabang with the primary key could not be found
	 */
	public Cabang findByPrimaryKey(int Id) throws NoSuchCabangException;

	/**
	 * Returns the cabang with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the cabang
	 * @return the cabang, or <code>null</code> if a cabang with the primary key could not be found
	 */
	public Cabang fetchByPrimaryKey(int Id);

	/**
	 * Returns all the cabangs.
	 *
	 * @return the cabangs
	 */
	public java.util.List<Cabang> findAll();

	/**
	 * Returns a range of all the cabangs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CabangModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cabangs
	 * @param end the upper bound of the range of cabangs (not inclusive)
	 * @return the range of cabangs
	 */
	public java.util.List<Cabang> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the cabangs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CabangModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cabangs
	 * @param end the upper bound of the range of cabangs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cabangs
	 */
	public java.util.List<Cabang> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Cabang>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cabangs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CabangModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cabangs
	 * @param end the upper bound of the range of cabangs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cabangs
	 */
	public java.util.List<Cabang> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Cabang>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the cabangs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of cabangs.
	 *
	 * @return the number of cabangs
	 */
	public int countAll();

}