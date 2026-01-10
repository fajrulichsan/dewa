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

import com.astra.dewa.exception.NoSuchDiskonTestCarException;
import com.astra.dewa.model.DiskonTestCar;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the diskon test car service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiskonTestCarUtil
 * @generated
 */
@ProviderType
public interface DiskonTestCarPersistence
	extends BasePersistence<DiskonTestCar> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DiskonTestCarUtil} to access the diskon test car persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the diskon test car in the entity cache if it is enabled.
	 *
	 * @param diskonTestCar the diskon test car
	 */
	public void cacheResult(DiskonTestCar diskonTestCar);

	/**
	 * Caches the diskon test cars in the entity cache if it is enabled.
	 *
	 * @param diskonTestCars the diskon test cars
	 */
	public void cacheResult(java.util.List<DiskonTestCar> diskonTestCars);

	/**
	 * Creates a new diskon test car with the primary key. Does not add the diskon test car to the database.
	 *
	 * @param Id the primary key for the new diskon test car
	 * @return the new diskon test car
	 */
	public DiskonTestCar create(int Id);

	/**
	 * Removes the diskon test car with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the diskon test car
	 * @return the diskon test car that was removed
	 * @throws NoSuchDiskonTestCarException if a diskon test car with the primary key could not be found
	 */
	public DiskonTestCar remove(int Id) throws NoSuchDiskonTestCarException;

	public DiskonTestCar updateImpl(DiskonTestCar diskonTestCar);

	/**
	 * Returns the diskon test car with the primary key or throws a <code>NoSuchDiskonTestCarException</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon test car
	 * @return the diskon test car
	 * @throws NoSuchDiskonTestCarException if a diskon test car with the primary key could not be found
	 */
	public DiskonTestCar findByPrimaryKey(int Id)
		throws NoSuchDiskonTestCarException;

	/**
	 * Returns the diskon test car with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon test car
	 * @return the diskon test car, or <code>null</code> if a diskon test car with the primary key could not be found
	 */
	public DiskonTestCar fetchByPrimaryKey(int Id);

	/**
	 * Returns all the diskon test cars.
	 *
	 * @return the diskon test cars
	 */
	public java.util.List<DiskonTestCar> findAll();

	/**
	 * Returns a range of all the diskon test cars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonTestCarModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon test cars
	 * @param end the upper bound of the range of diskon test cars (not inclusive)
	 * @return the range of diskon test cars
	 */
	public java.util.List<DiskonTestCar> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the diskon test cars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonTestCarModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon test cars
	 * @param end the upper bound of the range of diskon test cars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of diskon test cars
	 */
	public java.util.List<DiskonTestCar> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DiskonTestCar>
			orderByComparator);

	/**
	 * Returns an ordered range of all the diskon test cars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonTestCarModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon test cars
	 * @param end the upper bound of the range of diskon test cars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of diskon test cars
	 */
	public java.util.List<DiskonTestCar> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DiskonTestCar>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the diskon test cars from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of diskon test cars.
	 *
	 * @return the number of diskon test cars
	 */
	public int countAll();

}