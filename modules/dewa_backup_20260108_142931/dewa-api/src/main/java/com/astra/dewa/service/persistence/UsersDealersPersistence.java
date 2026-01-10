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

import com.astra.dewa.exception.NoSuchUsersDealersException;
import com.astra.dewa.model.UsersDealers;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the users dealers service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UsersDealersUtil
 * @generated
 */
@ProviderType
public interface UsersDealersPersistence extends BasePersistence<UsersDealers> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UsersDealersUtil} to access the users dealers persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the users dealers where UserId = &#63; and RowStatus = &#63; or throws a <code>NoSuchUsersDealersException</code> if it could not be found.
	 *
	 * @param UserId the user ID
	 * @param RowStatus the row status
	 * @return the matching users dealers
	 * @throws NoSuchUsersDealersException if a matching users dealers could not be found
	 */
	public UsersDealers findByUsersDealersIdFromUserId(
			long UserId, Boolean RowStatus)
		throws NoSuchUsersDealersException;

	/**
	 * Returns the users dealers where UserId = &#63; and RowStatus = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param UserId the user ID
	 * @param RowStatus the row status
	 * @return the matching users dealers, or <code>null</code> if a matching users dealers could not be found
	 */
	public UsersDealers fetchByUsersDealersIdFromUserId(
		long UserId, Boolean RowStatus);

	/**
	 * Returns the users dealers where UserId = &#63; and RowStatus = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param UserId the user ID
	 * @param RowStatus the row status
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching users dealers, or <code>null</code> if a matching users dealers could not be found
	 */
	public UsersDealers fetchByUsersDealersIdFromUserId(
		long UserId, Boolean RowStatus, boolean useFinderCache);

	/**
	 * Removes the users dealers where UserId = &#63; and RowStatus = &#63; from the database.
	 *
	 * @param UserId the user ID
	 * @param RowStatus the row status
	 * @return the users dealers that was removed
	 */
	public UsersDealers removeByUsersDealersIdFromUserId(
			long UserId, Boolean RowStatus)
		throws NoSuchUsersDealersException;

	/**
	 * Returns the number of users dealerses where UserId = &#63; and RowStatus = &#63;.
	 *
	 * @param UserId the user ID
	 * @param RowStatus the row status
	 * @return the number of matching users dealerses
	 */
	public int countByUsersDealersIdFromUserId(long UserId, Boolean RowStatus);

	/**
	 * Caches the users dealers in the entity cache if it is enabled.
	 *
	 * @param usersDealers the users dealers
	 */
	public void cacheResult(UsersDealers usersDealers);

	/**
	 * Caches the users dealerses in the entity cache if it is enabled.
	 *
	 * @param usersDealerses the users dealerses
	 */
	public void cacheResult(java.util.List<UsersDealers> usersDealerses);

	/**
	 * Creates a new users dealers with the primary key. Does not add the users dealers to the database.
	 *
	 * @param Id the primary key for the new users dealers
	 * @return the new users dealers
	 */
	public UsersDealers create(int Id);

	/**
	 * Removes the users dealers with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the users dealers
	 * @return the users dealers that was removed
	 * @throws NoSuchUsersDealersException if a users dealers with the primary key could not be found
	 */
	public UsersDealers remove(int Id) throws NoSuchUsersDealersException;

	public UsersDealers updateImpl(UsersDealers usersDealers);

	/**
	 * Returns the users dealers with the primary key or throws a <code>NoSuchUsersDealersException</code> if it could not be found.
	 *
	 * @param Id the primary key of the users dealers
	 * @return the users dealers
	 * @throws NoSuchUsersDealersException if a users dealers with the primary key could not be found
	 */
	public UsersDealers findByPrimaryKey(int Id)
		throws NoSuchUsersDealersException;

	/**
	 * Returns the users dealers with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the users dealers
	 * @return the users dealers, or <code>null</code> if a users dealers with the primary key could not be found
	 */
	public UsersDealers fetchByPrimaryKey(int Id);

	/**
	 * Returns all the users dealerses.
	 *
	 * @return the users dealerses
	 */
	public java.util.List<UsersDealers> findAll();

	/**
	 * Returns a range of all the users dealerses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UsersDealersModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of users dealerses
	 * @param end the upper bound of the range of users dealerses (not inclusive)
	 * @return the range of users dealerses
	 */
	public java.util.List<UsersDealers> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the users dealerses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UsersDealersModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of users dealerses
	 * @param end the upper bound of the range of users dealerses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of users dealerses
	 */
	public java.util.List<UsersDealers> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UsersDealers>
			orderByComparator);

	/**
	 * Returns an ordered range of all the users dealerses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UsersDealersModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of users dealerses
	 * @param end the upper bound of the range of users dealerses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of users dealerses
	 */
	public java.util.List<UsersDealers> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UsersDealers>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the users dealerses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of users dealerses.
	 *
	 * @return the number of users dealerses
	 */
	public int countAll();

}