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

import com.astra.dewa.exception.NoSuchMasterApprovalException;
import com.astra.dewa.model.MasterApproval;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the master approval service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalUtil
 * @generated
 */
@ProviderType
public interface MasterApprovalPersistence
	extends BasePersistence<MasterApproval> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MasterApprovalUtil} to access the master approval persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the master approval in the entity cache if it is enabled.
	 *
	 * @param masterApproval the master approval
	 */
	public void cacheResult(MasterApproval masterApproval);

	/**
	 * Caches the master approvals in the entity cache if it is enabled.
	 *
	 * @param masterApprovals the master approvals
	 */
	public void cacheResult(java.util.List<MasterApproval> masterApprovals);

	/**
	 * Creates a new master approval with the primary key. Does not add the master approval to the database.
	 *
	 * @param Id the primary key for the new master approval
	 * @return the new master approval
	 */
	public MasterApproval create(int Id);

	/**
	 * Removes the master approval with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the master approval
	 * @return the master approval that was removed
	 * @throws NoSuchMasterApprovalException if a master approval with the primary key could not be found
	 */
	public MasterApproval remove(int Id) throws NoSuchMasterApprovalException;

	public MasterApproval updateImpl(MasterApproval masterApproval);

	/**
	 * Returns the master approval with the primary key or throws a <code>NoSuchMasterApprovalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval
	 * @return the master approval
	 * @throws NoSuchMasterApprovalException if a master approval with the primary key could not be found
	 */
	public MasterApproval findByPrimaryKey(int Id)
		throws NoSuchMasterApprovalException;

	/**
	 * Returns the master approval with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval
	 * @return the master approval, or <code>null</code> if a master approval with the primary key could not be found
	 */
	public MasterApproval fetchByPrimaryKey(int Id);

	/**
	 * Returns all the master approvals.
	 *
	 * @return the master approvals
	 */
	public java.util.List<MasterApproval> findAll();

	/**
	 * Returns a range of all the master approvals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approvals
	 * @param end the upper bound of the range of master approvals (not inclusive)
	 * @return the range of master approvals
	 */
	public java.util.List<MasterApproval> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the master approvals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approvals
	 * @param end the upper bound of the range of master approvals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of master approvals
	 */
	public java.util.List<MasterApproval> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterApproval>
			orderByComparator);

	/**
	 * Returns an ordered range of all the master approvals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approvals
	 * @param end the upper bound of the range of master approvals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of master approvals
	 */
	public java.util.List<MasterApproval> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterApproval>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the master approvals from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of master approvals.
	 *
	 * @return the number of master approvals
	 */
	public int countAll();

}