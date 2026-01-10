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

import com.astra.dewa.exception.NoSuchMasterApprovalDetailJournalException;
import com.astra.dewa.model.MasterApprovalDetailJournal;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the master approval detail journal service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalDetailJournalUtil
 * @generated
 */
@ProviderType
public interface MasterApprovalDetailJournalPersistence
	extends BasePersistence<MasterApprovalDetailJournal> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MasterApprovalDetailJournalUtil} to access the master approval detail journal persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the master approval detail journal in the entity cache if it is enabled.
	 *
	 * @param masterApprovalDetailJournal the master approval detail journal
	 */
	public void cacheResult(
		MasterApprovalDetailJournal masterApprovalDetailJournal);

	/**
	 * Caches the master approval detail journals in the entity cache if it is enabled.
	 *
	 * @param masterApprovalDetailJournals the master approval detail journals
	 */
	public void cacheResult(
		java.util.List<MasterApprovalDetailJournal>
			masterApprovalDetailJournals);

	/**
	 * Creates a new master approval detail journal with the primary key. Does not add the master approval detail journal to the database.
	 *
	 * @param Id the primary key for the new master approval detail journal
	 * @return the new master approval detail journal
	 */
	public MasterApprovalDetailJournal create(int Id);

	/**
	 * Removes the master approval detail journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the master approval detail journal
	 * @return the master approval detail journal that was removed
	 * @throws NoSuchMasterApprovalDetailJournalException if a master approval detail journal with the primary key could not be found
	 */
	public MasterApprovalDetailJournal remove(int Id)
		throws NoSuchMasterApprovalDetailJournalException;

	public MasterApprovalDetailJournal updateImpl(
		MasterApprovalDetailJournal masterApprovalDetailJournal);

	/**
	 * Returns the master approval detail journal with the primary key or throws a <code>NoSuchMasterApprovalDetailJournalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval detail journal
	 * @return the master approval detail journal
	 * @throws NoSuchMasterApprovalDetailJournalException if a master approval detail journal with the primary key could not be found
	 */
	public MasterApprovalDetailJournal findByPrimaryKey(int Id)
		throws NoSuchMasterApprovalDetailJournalException;

	/**
	 * Returns the master approval detail journal with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval detail journal
	 * @return the master approval detail journal, or <code>null</code> if a master approval detail journal with the primary key could not be found
	 */
	public MasterApprovalDetailJournal fetchByPrimaryKey(int Id);

	/**
	 * Returns all the master approval detail journals.
	 *
	 * @return the master approval detail journals
	 */
	public java.util.List<MasterApprovalDetailJournal> findAll();

	/**
	 * Returns a range of all the master approval detail journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval detail journals
	 * @param end the upper bound of the range of master approval detail journals (not inclusive)
	 * @return the range of master approval detail journals
	 */
	public java.util.List<MasterApprovalDetailJournal> findAll(
		int start, int end);

	/**
	 * Returns an ordered range of all the master approval detail journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval detail journals
	 * @param end the upper bound of the range of master approval detail journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of master approval detail journals
	 */
	public java.util.List<MasterApprovalDetailJournal> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<MasterApprovalDetailJournal> orderByComparator);

	/**
	 * Returns an ordered range of all the master approval detail journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval detail journals
	 * @param end the upper bound of the range of master approval detail journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of master approval detail journals
	 */
	public java.util.List<MasterApprovalDetailJournal> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<MasterApprovalDetailJournal> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the master approval detail journals from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of master approval detail journals.
	 *
	 * @return the number of master approval detail journals
	 */
	public int countAll();

}