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

import com.astra.dewa.exception.NoSuchApprovalDetailUserException;
import com.astra.dewa.model.ApprovalDetailUser;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the approval detail user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApprovalDetailUserUtil
 * @generated
 */
@ProviderType
public interface ApprovalDetailUserPersistence
	extends BasePersistence<ApprovalDetailUser> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApprovalDetailUserUtil} to access the approval detail user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the approval detail user where ApprovalHeaderUserId = &#63; and RowStatus = &#63; or throws a <code>NoSuchApprovalDetailUserException</code> if it could not be found.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @return the matching approval detail user
	 * @throws NoSuchApprovalDetailUserException if a matching approval detail user could not be found
	 */
	public ApprovalDetailUser findByFindDetailUser(
			int ApprovalHeaderUserId, Boolean RowStatus)
		throws NoSuchApprovalDetailUserException;

	/**
	 * Returns the approval detail user where ApprovalHeaderUserId = &#63; and RowStatus = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @return the matching approval detail user, or <code>null</code> if a matching approval detail user could not be found
	 */
	public ApprovalDetailUser fetchByFindDetailUser(
		int ApprovalHeaderUserId, Boolean RowStatus);

	/**
	 * Returns the approval detail user where ApprovalHeaderUserId = &#63; and RowStatus = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching approval detail user, or <code>null</code> if a matching approval detail user could not be found
	 */
	public ApprovalDetailUser fetchByFindDetailUser(
		int ApprovalHeaderUserId, Boolean RowStatus, boolean useFinderCache);

	/**
	 * Removes the approval detail user where ApprovalHeaderUserId = &#63; and RowStatus = &#63; from the database.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @return the approval detail user that was removed
	 */
	public ApprovalDetailUser removeByFindDetailUser(
			int ApprovalHeaderUserId, Boolean RowStatus)
		throws NoSuchApprovalDetailUserException;

	/**
	 * Returns the number of approval detail users where ApprovalHeaderUserId = &#63; and RowStatus = &#63;.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @return the number of matching approval detail users
	 */
	public int countByFindDetailUser(
		int ApprovalHeaderUserId, Boolean RowStatus);

	/**
	 * Caches the approval detail user in the entity cache if it is enabled.
	 *
	 * @param approvalDetailUser the approval detail user
	 */
	public void cacheResult(ApprovalDetailUser approvalDetailUser);

	/**
	 * Caches the approval detail users in the entity cache if it is enabled.
	 *
	 * @param approvalDetailUsers the approval detail users
	 */
	public void cacheResult(
		java.util.List<ApprovalDetailUser> approvalDetailUsers);

	/**
	 * Creates a new approval detail user with the primary key. Does not add the approval detail user to the database.
	 *
	 * @param Id the primary key for the new approval detail user
	 * @return the new approval detail user
	 */
	public ApprovalDetailUser create(int Id);

	/**
	 * Removes the approval detail user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the approval detail user
	 * @return the approval detail user that was removed
	 * @throws NoSuchApprovalDetailUserException if a approval detail user with the primary key could not be found
	 */
	public ApprovalDetailUser remove(int Id)
		throws NoSuchApprovalDetailUserException;

	public ApprovalDetailUser updateImpl(ApprovalDetailUser approvalDetailUser);

	/**
	 * Returns the approval detail user with the primary key or throws a <code>NoSuchApprovalDetailUserException</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval detail user
	 * @return the approval detail user
	 * @throws NoSuchApprovalDetailUserException if a approval detail user with the primary key could not be found
	 */
	public ApprovalDetailUser findByPrimaryKey(int Id)
		throws NoSuchApprovalDetailUserException;

	/**
	 * Returns the approval detail user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval detail user
	 * @return the approval detail user, or <code>null</code> if a approval detail user with the primary key could not be found
	 */
	public ApprovalDetailUser fetchByPrimaryKey(int Id);

	/**
	 * Returns all the approval detail users.
	 *
	 * @return the approval detail users
	 */
	public java.util.List<ApprovalDetailUser> findAll();

	/**
	 * Returns a range of all the approval detail users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalDetailUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval detail users
	 * @param end the upper bound of the range of approval detail users (not inclusive)
	 * @return the range of approval detail users
	 */
	public java.util.List<ApprovalDetailUser> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the approval detail users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalDetailUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval detail users
	 * @param end the upper bound of the range of approval detail users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of approval detail users
	 */
	public java.util.List<ApprovalDetailUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApprovalDetailUser>
			orderByComparator);

	/**
	 * Returns an ordered range of all the approval detail users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalDetailUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval detail users
	 * @param end the upper bound of the range of approval detail users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of approval detail users
	 */
	public java.util.List<ApprovalDetailUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApprovalDetailUser>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the approval detail users from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of approval detail users.
	 *
	 * @return the number of approval detail users
	 */
	public int countAll();

}