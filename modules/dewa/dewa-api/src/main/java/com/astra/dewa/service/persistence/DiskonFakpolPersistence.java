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

import com.astra.dewa.exception.NoSuchDiskonFakpolException;
import com.astra.dewa.model.DiskonFakpol;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the diskon fakpol service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiskonFakpolUtil
 * @generated
 */
@ProviderType
public interface DiskonFakpolPersistence extends BasePersistence<DiskonFakpol> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DiskonFakpolUtil} to access the diskon fakpol persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the diskon fakpol in the entity cache if it is enabled.
	 *
	 * @param diskonFakpol the diskon fakpol
	 */
	public void cacheResult(DiskonFakpol diskonFakpol);

	/**
	 * Caches the diskon fakpols in the entity cache if it is enabled.
	 *
	 * @param diskonFakpols the diskon fakpols
	 */
	public void cacheResult(java.util.List<DiskonFakpol> diskonFakpols);

	/**
	 * Creates a new diskon fakpol with the primary key. Does not add the diskon fakpol to the database.
	 *
	 * @param Id the primary key for the new diskon fakpol
	 * @return the new diskon fakpol
	 */
	public DiskonFakpol create(int Id);

	/**
	 * Removes the diskon fakpol with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the diskon fakpol
	 * @return the diskon fakpol that was removed
	 * @throws NoSuchDiskonFakpolException if a diskon fakpol with the primary key could not be found
	 */
	public DiskonFakpol remove(int Id) throws NoSuchDiskonFakpolException;

	public DiskonFakpol updateImpl(DiskonFakpol diskonFakpol);

	/**
	 * Returns the diskon fakpol with the primary key or throws a <code>NoSuchDiskonFakpolException</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon fakpol
	 * @return the diskon fakpol
	 * @throws NoSuchDiskonFakpolException if a diskon fakpol with the primary key could not be found
	 */
	public DiskonFakpol findByPrimaryKey(int Id)
		throws NoSuchDiskonFakpolException;

	/**
	 * Returns the diskon fakpol with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon fakpol
	 * @return the diskon fakpol, or <code>null</code> if a diskon fakpol with the primary key could not be found
	 */
	public DiskonFakpol fetchByPrimaryKey(int Id);

	/**
	 * Returns all the diskon fakpols.
	 *
	 * @return the diskon fakpols
	 */
	public java.util.List<DiskonFakpol> findAll();

	/**
	 * Returns a range of all the diskon fakpols.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFakpolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fakpols
	 * @param end the upper bound of the range of diskon fakpols (not inclusive)
	 * @return the range of diskon fakpols
	 */
	public java.util.List<DiskonFakpol> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the diskon fakpols.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFakpolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fakpols
	 * @param end the upper bound of the range of diskon fakpols (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of diskon fakpols
	 */
	public java.util.List<DiskonFakpol> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DiskonFakpol>
			orderByComparator);

	/**
	 * Returns an ordered range of all the diskon fakpols.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFakpolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fakpols
	 * @param end the upper bound of the range of diskon fakpols (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of diskon fakpols
	 */
	public java.util.List<DiskonFakpol> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DiskonFakpol>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the diskon fakpols from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of diskon fakpols.
	 *
	 * @return the number of diskon fakpols
	 */
	public int countAll();

}