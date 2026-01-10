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

import com.astra.dewa.exception.NoSuchMasterApprovalDetailException;
import com.astra.dewa.model.MasterApprovalDetail;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the master approval detail service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalDetailUtil
 * @generated
 */
@ProviderType
public interface MasterApprovalDetailPersistence
	extends BasePersistence<MasterApprovalDetail> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MasterApprovalDetailUtil} to access the master approval detail persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the master approval detail in the entity cache if it is enabled.
	 *
	 * @param masterApprovalDetail the master approval detail
	 */
	public void cacheResult(MasterApprovalDetail masterApprovalDetail);

	/**
	 * Caches the master approval details in the entity cache if it is enabled.
	 *
	 * @param masterApprovalDetails the master approval details
	 */
	public void cacheResult(
		java.util.List<MasterApprovalDetail> masterApprovalDetails);

	/**
	 * Creates a new master approval detail with the primary key. Does not add the master approval detail to the database.
	 *
	 * @param Id the primary key for the new master approval detail
	 * @return the new master approval detail
	 */
	public MasterApprovalDetail create(int Id);

	/**
	 * Removes the master approval detail with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the master approval detail
	 * @return the master approval detail that was removed
	 * @throws NoSuchMasterApprovalDetailException if a master approval detail with the primary key could not be found
	 */
	public MasterApprovalDetail remove(int Id)
		throws NoSuchMasterApprovalDetailException;

	public MasterApprovalDetail updateImpl(
		MasterApprovalDetail masterApprovalDetail);

	/**
	 * Returns the master approval detail with the primary key or throws a <code>NoSuchMasterApprovalDetailException</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval detail
	 * @return the master approval detail
	 * @throws NoSuchMasterApprovalDetailException if a master approval detail with the primary key could not be found
	 */
	public MasterApprovalDetail findByPrimaryKey(int Id)
		throws NoSuchMasterApprovalDetailException;

	/**
	 * Returns the master approval detail with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval detail
	 * @return the master approval detail, or <code>null</code> if a master approval detail with the primary key could not be found
	 */
	public MasterApprovalDetail fetchByPrimaryKey(int Id);

	/**
	 * Returns all the master approval details.
	 *
	 * @return the master approval details
	 */
	public java.util.List<MasterApprovalDetail> findAll();

	/**
	 * Returns a range of all the master approval details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval details
	 * @param end the upper bound of the range of master approval details (not inclusive)
	 * @return the range of master approval details
	 */
	public java.util.List<MasterApprovalDetail> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the master approval details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval details
	 * @param end the upper bound of the range of master approval details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of master approval details
	 */
	public java.util.List<MasterApprovalDetail> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterApprovalDetail>
			orderByComparator);

	/**
	 * Returns an ordered range of all the master approval details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval details
	 * @param end the upper bound of the range of master approval details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of master approval details
	 */
	public java.util.List<MasterApprovalDetail> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterApprovalDetail>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the master approval details from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of master approval details.
	 *
	 * @return the number of master approval details
	 */
	public int countAll();

}