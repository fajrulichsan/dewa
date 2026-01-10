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

import com.astra.dewa.exception.NoSuchApprovalHeaderUserException;
import com.astra.dewa.model.ApprovalHeaderUser;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the approval header user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApprovalHeaderUserUtil
 * @generated
 */
@ProviderType
public interface ApprovalHeaderUserPersistence
	extends BasePersistence<ApprovalHeaderUser> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApprovalHeaderUserUtil} to access the approval header user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the approval header user in the entity cache if it is enabled.
	 *
	 * @param approvalHeaderUser the approval header user
	 */
	public void cacheResult(ApprovalHeaderUser approvalHeaderUser);

	/**
	 * Caches the approval header users in the entity cache if it is enabled.
	 *
	 * @param approvalHeaderUsers the approval header users
	 */
	public void cacheResult(
		java.util.List<ApprovalHeaderUser> approvalHeaderUsers);

	/**
	 * Creates a new approval header user with the primary key. Does not add the approval header user to the database.
	 *
	 * @param Id the primary key for the new approval header user
	 * @return the new approval header user
	 */
	public ApprovalHeaderUser create(int Id);

	/**
	 * Removes the approval header user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the approval header user
	 * @return the approval header user that was removed
	 * @throws NoSuchApprovalHeaderUserException if a approval header user with the primary key could not be found
	 */
	public ApprovalHeaderUser remove(int Id)
		throws NoSuchApprovalHeaderUserException;

	public ApprovalHeaderUser updateImpl(ApprovalHeaderUser approvalHeaderUser);

	/**
	 * Returns the approval header user with the primary key or throws a <code>NoSuchApprovalHeaderUserException</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval header user
	 * @return the approval header user
	 * @throws NoSuchApprovalHeaderUserException if a approval header user with the primary key could not be found
	 */
	public ApprovalHeaderUser findByPrimaryKey(int Id)
		throws NoSuchApprovalHeaderUserException;

	/**
	 * Returns the approval header user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval header user
	 * @return the approval header user, or <code>null</code> if a approval header user with the primary key could not be found
	 */
	public ApprovalHeaderUser fetchByPrimaryKey(int Id);

	/**
	 * Returns all the approval header users.
	 *
	 * @return the approval header users
	 */
	public java.util.List<ApprovalHeaderUser> findAll();

	/**
	 * Returns a range of all the approval header users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalHeaderUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval header users
	 * @param end the upper bound of the range of approval header users (not inclusive)
	 * @return the range of approval header users
	 */
	public java.util.List<ApprovalHeaderUser> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the approval header users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalHeaderUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval header users
	 * @param end the upper bound of the range of approval header users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of approval header users
	 */
	public java.util.List<ApprovalHeaderUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApprovalHeaderUser>
			orderByComparator);

	/**
	 * Returns an ordered range of all the approval header users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalHeaderUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval header users
	 * @param end the upper bound of the range of approval header users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of approval header users
	 */
	public java.util.List<ApprovalHeaderUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApprovalHeaderUser>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the approval header users from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of approval header users.
	 *
	 * @return the number of approval header users
	 */
	public int countAll();

}