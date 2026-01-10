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

import com.astra.dewa.exception.NoSuchApplicationAssignJournalException;
import com.astra.dewa.model.ApplicationAssignJournal;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the application assign journal service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationAssignJournalUtil
 * @generated
 */
@ProviderType
public interface ApplicationAssignJournalPersistence
	extends BasePersistence<ApplicationAssignJournal> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicationAssignJournalUtil} to access the application assign journal persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the application assign journal in the entity cache if it is enabled.
	 *
	 * @param applicationAssignJournal the application assign journal
	 */
	public void cacheResult(ApplicationAssignJournal applicationAssignJournal);

	/**
	 * Caches the application assign journals in the entity cache if it is enabled.
	 *
	 * @param applicationAssignJournals the application assign journals
	 */
	public void cacheResult(
		java.util.List<ApplicationAssignJournal> applicationAssignJournals);

	/**
	 * Creates a new application assign journal with the primary key. Does not add the application assign journal to the database.
	 *
	 * @param Id the primary key for the new application assign journal
	 * @return the new application assign journal
	 */
	public ApplicationAssignJournal create(int Id);

	/**
	 * Removes the application assign journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application assign journal
	 * @return the application assign journal that was removed
	 * @throws NoSuchApplicationAssignJournalException if a application assign journal with the primary key could not be found
	 */
	public ApplicationAssignJournal remove(int Id)
		throws NoSuchApplicationAssignJournalException;

	public ApplicationAssignJournal updateImpl(
		ApplicationAssignJournal applicationAssignJournal);

	/**
	 * Returns the application assign journal with the primary key or throws a <code>NoSuchApplicationAssignJournalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign journal
	 * @return the application assign journal
	 * @throws NoSuchApplicationAssignJournalException if a application assign journal with the primary key could not be found
	 */
	public ApplicationAssignJournal findByPrimaryKey(int Id)
		throws NoSuchApplicationAssignJournalException;

	/**
	 * Returns the application assign journal with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign journal
	 * @return the application assign journal, or <code>null</code> if a application assign journal with the primary key could not be found
	 */
	public ApplicationAssignJournal fetchByPrimaryKey(int Id);

	/**
	 * Returns all the application assign journals.
	 *
	 * @return the application assign journals
	 */
	public java.util.List<ApplicationAssignJournal> findAll();

	/**
	 * Returns a range of all the application assign journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign journals
	 * @param end the upper bound of the range of application assign journals (not inclusive)
	 * @return the range of application assign journals
	 */
	public java.util.List<ApplicationAssignJournal> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the application assign journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign journals
	 * @param end the upper bound of the range of application assign journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application assign journals
	 */
	public java.util.List<ApplicationAssignJournal> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<ApplicationAssignJournal> orderByComparator);

	/**
	 * Returns an ordered range of all the application assign journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign journals
	 * @param end the upper bound of the range of application assign journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of application assign journals
	 */
	public java.util.List<ApplicationAssignJournal> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<ApplicationAssignJournal> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the application assign journals from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of application assign journals.
	 *
	 * @return the number of application assign journals
	 */
	public int countAll();

}