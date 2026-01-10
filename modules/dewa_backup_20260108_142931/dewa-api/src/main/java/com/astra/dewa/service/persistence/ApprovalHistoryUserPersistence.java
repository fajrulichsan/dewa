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

import com.astra.dewa.exception.NoSuchApprovalHistoryUserException;
import com.astra.dewa.model.ApprovalHistoryUser;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the approval history user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApprovalHistoryUserUtil
 * @generated
 */
@ProviderType
public interface ApprovalHistoryUserPersistence
	extends BasePersistence<ApprovalHistoryUser> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApprovalHistoryUserUtil} to access the approval history user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the approval history user in the entity cache if it is enabled.
	 *
	 * @param approvalHistoryUser the approval history user
	 */
	public void cacheResult(ApprovalHistoryUser approvalHistoryUser);

	/**
	 * Caches the approval history users in the entity cache if it is enabled.
	 *
	 * @param approvalHistoryUsers the approval history users
	 */
	public void cacheResult(
		java.util.List<ApprovalHistoryUser> approvalHistoryUsers);

	/**
	 * Creates a new approval history user with the primary key. Does not add the approval history user to the database.
	 *
	 * @param Id the primary key for the new approval history user
	 * @return the new approval history user
	 */
	public ApprovalHistoryUser create(int Id);

	/**
	 * Removes the approval history user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the approval history user
	 * @return the approval history user that was removed
	 * @throws NoSuchApprovalHistoryUserException if a approval history user with the primary key could not be found
	 */
	public ApprovalHistoryUser remove(int Id)
		throws NoSuchApprovalHistoryUserException;

	public ApprovalHistoryUser updateImpl(
		ApprovalHistoryUser approvalHistoryUser);

	/**
	 * Returns the approval history user with the primary key or throws a <code>NoSuchApprovalHistoryUserException</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval history user
	 * @return the approval history user
	 * @throws NoSuchApprovalHistoryUserException if a approval history user with the primary key could not be found
	 */
	public ApprovalHistoryUser findByPrimaryKey(int Id)
		throws NoSuchApprovalHistoryUserException;

	/**
	 * Returns the approval history user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval history user
	 * @return the approval history user, or <code>null</code> if a approval history user with the primary key could not be found
	 */
	public ApprovalHistoryUser fetchByPrimaryKey(int Id);

	/**
	 * Returns all the approval history users.
	 *
	 * @return the approval history users
	 */
	public java.util.List<ApprovalHistoryUser> findAll();

	/**
	 * Returns a range of all the approval history users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalHistoryUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval history users
	 * @param end the upper bound of the range of approval history users (not inclusive)
	 * @return the range of approval history users
	 */
	public java.util.List<ApprovalHistoryUser> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the approval history users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalHistoryUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval history users
	 * @param end the upper bound of the range of approval history users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of approval history users
	 */
	public java.util.List<ApprovalHistoryUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApprovalHistoryUser>
			orderByComparator);

	/**
	 * Returns an ordered range of all the approval history users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalHistoryUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval history users
	 * @param end the upper bound of the range of approval history users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of approval history users
	 */
	public java.util.List<ApprovalHistoryUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApprovalHistoryUser>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the approval history users from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of approval history users.
	 *
	 * @return the number of approval history users
	 */
	public int countAll();

}