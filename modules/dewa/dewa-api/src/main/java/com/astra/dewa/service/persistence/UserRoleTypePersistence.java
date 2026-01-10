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

import com.astra.dewa.exception.NoSuchUserRoleTypeException;
import com.astra.dewa.model.UserRoleType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the user role type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserRoleTypeUtil
 * @generated
 */
@ProviderType
public interface UserRoleTypePersistence extends BasePersistence<UserRoleType> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserRoleTypeUtil} to access the user role type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the user role type in the entity cache if it is enabled.
	 *
	 * @param userRoleType the user role type
	 */
	public void cacheResult(UserRoleType userRoleType);

	/**
	 * Caches the user role types in the entity cache if it is enabled.
	 *
	 * @param userRoleTypes the user role types
	 */
	public void cacheResult(java.util.List<UserRoleType> userRoleTypes);

	/**
	 * Creates a new user role type with the primary key. Does not add the user role type to the database.
	 *
	 * @param Id the primary key for the new user role type
	 * @return the new user role type
	 */
	public UserRoleType create(int Id);

	/**
	 * Removes the user role type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the user role type
	 * @return the user role type that was removed
	 * @throws NoSuchUserRoleTypeException if a user role type with the primary key could not be found
	 */
	public UserRoleType remove(int Id) throws NoSuchUserRoleTypeException;

	public UserRoleType updateImpl(UserRoleType userRoleType);

	/**
	 * Returns the user role type with the primary key or throws a <code>NoSuchUserRoleTypeException</code> if it could not be found.
	 *
	 * @param Id the primary key of the user role type
	 * @return the user role type
	 * @throws NoSuchUserRoleTypeException if a user role type with the primary key could not be found
	 */
	public UserRoleType findByPrimaryKey(int Id)
		throws NoSuchUserRoleTypeException;

	/**
	 * Returns the user role type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the user role type
	 * @return the user role type, or <code>null</code> if a user role type with the primary key could not be found
	 */
	public UserRoleType fetchByPrimaryKey(int Id);

	/**
	 * Returns all the user role types.
	 *
	 * @return the user role types
	 */
	public java.util.List<UserRoleType> findAll();

	/**
	 * Returns a range of all the user role types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRoleTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user role types
	 * @param end the upper bound of the range of user role types (not inclusive)
	 * @return the range of user role types
	 */
	public java.util.List<UserRoleType> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the user role types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRoleTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user role types
	 * @param end the upper bound of the range of user role types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user role types
	 */
	public java.util.List<UserRoleType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserRoleType>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user role types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRoleTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user role types
	 * @param end the upper bound of the range of user role types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user role types
	 */
	public java.util.List<UserRoleType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserRoleType>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the user role types from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of user role types.
	 *
	 * @return the number of user role types
	 */
	public int countAll();

}