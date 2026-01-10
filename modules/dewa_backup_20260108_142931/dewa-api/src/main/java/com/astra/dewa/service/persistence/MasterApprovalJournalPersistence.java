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

import com.astra.dewa.exception.NoSuchMasterApprovalJournalException;
import com.astra.dewa.model.MasterApprovalJournal;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the master approval journal service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalJournalUtil
 * @generated
 */
@ProviderType
public interface MasterApprovalJournalPersistence
	extends BasePersistence<MasterApprovalJournal> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MasterApprovalJournalUtil} to access the master approval journal persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the master approval journal in the entity cache if it is enabled.
	 *
	 * @param masterApprovalJournal the master approval journal
	 */
	public void cacheResult(MasterApprovalJournal masterApprovalJournal);

	/**
	 * Caches the master approval journals in the entity cache if it is enabled.
	 *
	 * @param masterApprovalJournals the master approval journals
	 */
	public void cacheResult(
		java.util.List<MasterApprovalJournal> masterApprovalJournals);

	/**
	 * Creates a new master approval journal with the primary key. Does not add the master approval journal to the database.
	 *
	 * @param Id the primary key for the new master approval journal
	 * @return the new master approval journal
	 */
	public MasterApprovalJournal create(int Id);

	/**
	 * Removes the master approval journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the master approval journal
	 * @return the master approval journal that was removed
	 * @throws NoSuchMasterApprovalJournalException if a master approval journal with the primary key could not be found
	 */
	public MasterApprovalJournal remove(int Id)
		throws NoSuchMasterApprovalJournalException;

	public MasterApprovalJournal updateImpl(
		MasterApprovalJournal masterApprovalJournal);

	/**
	 * Returns the master approval journal with the primary key or throws a <code>NoSuchMasterApprovalJournalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval journal
	 * @return the master approval journal
	 * @throws NoSuchMasterApprovalJournalException if a master approval journal with the primary key could not be found
	 */
	public MasterApprovalJournal findByPrimaryKey(int Id)
		throws NoSuchMasterApprovalJournalException;

	/**
	 * Returns the master approval journal with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval journal
	 * @return the master approval journal, or <code>null</code> if a master approval journal with the primary key could not be found
	 */
	public MasterApprovalJournal fetchByPrimaryKey(int Id);

	/**
	 * Returns all the master approval journals.
	 *
	 * @return the master approval journals
	 */
	public java.util.List<MasterApprovalJournal> findAll();

	/**
	 * Returns a range of all the master approval journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval journals
	 * @param end the upper bound of the range of master approval journals (not inclusive)
	 * @return the range of master approval journals
	 */
	public java.util.List<MasterApprovalJournal> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the master approval journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval journals
	 * @param end the upper bound of the range of master approval journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of master approval journals
	 */
	public java.util.List<MasterApprovalJournal> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterApprovalJournal>
			orderByComparator);

	/**
	 * Returns an ordered range of all the master approval journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval journals
	 * @param end the upper bound of the range of master approval journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of master approval journals
	 */
	public java.util.List<MasterApprovalJournal> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterApprovalJournal>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the master approval journals from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of master approval journals.
	 *
	 * @return the number of master approval journals
	 */
	public int countAll();

}