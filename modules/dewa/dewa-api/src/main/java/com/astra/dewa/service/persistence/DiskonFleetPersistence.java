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

import com.astra.dewa.exception.NoSuchDiskonFleetException;
import com.astra.dewa.model.DiskonFleet;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the diskon fleet service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiskonFleetUtil
 * @generated
 */
@ProviderType
public interface DiskonFleetPersistence extends BasePersistence<DiskonFleet> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DiskonFleetUtil} to access the diskon fleet persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the diskon fleet in the entity cache if it is enabled.
	 *
	 * @param diskonFleet the diskon fleet
	 */
	public void cacheResult(DiskonFleet diskonFleet);

	/**
	 * Caches the diskon fleets in the entity cache if it is enabled.
	 *
	 * @param diskonFleets the diskon fleets
	 */
	public void cacheResult(java.util.List<DiskonFleet> diskonFleets);

	/**
	 * Creates a new diskon fleet with the primary key. Does not add the diskon fleet to the database.
	 *
	 * @param Id the primary key for the new diskon fleet
	 * @return the new diskon fleet
	 */
	public DiskonFleet create(int Id);

	/**
	 * Removes the diskon fleet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the diskon fleet
	 * @return the diskon fleet that was removed
	 * @throws NoSuchDiskonFleetException if a diskon fleet with the primary key could not be found
	 */
	public DiskonFleet remove(int Id) throws NoSuchDiskonFleetException;

	public DiskonFleet updateImpl(DiskonFleet diskonFleet);

	/**
	 * Returns the diskon fleet with the primary key or throws a <code>NoSuchDiskonFleetException</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon fleet
	 * @return the diskon fleet
	 * @throws NoSuchDiskonFleetException if a diskon fleet with the primary key could not be found
	 */
	public DiskonFleet findByPrimaryKey(int Id)
		throws NoSuchDiskonFleetException;

	/**
	 * Returns the diskon fleet with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon fleet
	 * @return the diskon fleet, or <code>null</code> if a diskon fleet with the primary key could not be found
	 */
	public DiskonFleet fetchByPrimaryKey(int Id);

	/**
	 * Returns all the diskon fleets.
	 *
	 * @return the diskon fleets
	 */
	public java.util.List<DiskonFleet> findAll();

	/**
	 * Returns a range of all the diskon fleets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFleetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fleets
	 * @param end the upper bound of the range of diskon fleets (not inclusive)
	 * @return the range of diskon fleets
	 */
	public java.util.List<DiskonFleet> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the diskon fleets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFleetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fleets
	 * @param end the upper bound of the range of diskon fleets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of diskon fleets
	 */
	public java.util.List<DiskonFleet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DiskonFleet>
			orderByComparator);

	/**
	 * Returns an ordered range of all the diskon fleets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFleetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fleets
	 * @param end the upper bound of the range of diskon fleets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of diskon fleets
	 */
	public java.util.List<DiskonFleet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DiskonFleet>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the diskon fleets from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of diskon fleets.
	 *
	 * @return the number of diskon fleets
	 */
	public int countAll();

}