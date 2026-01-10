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

import com.astra.dewa.exception.NoSuchApplicationHeaderJournalException;
import com.astra.dewa.model.ApplicationHeaderJournal;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the application header journal service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationHeaderJournalUtil
 * @generated
 */
@ProviderType
public interface ApplicationHeaderJournalPersistence
	extends BasePersistence<ApplicationHeaderJournal> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicationHeaderJournalUtil} to access the application header journal persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the application header journal in the entity cache if it is enabled.
	 *
	 * @param applicationHeaderJournal the application header journal
	 */
	public void cacheResult(ApplicationHeaderJournal applicationHeaderJournal);

	/**
	 * Caches the application header journals in the entity cache if it is enabled.
	 *
	 * @param applicationHeaderJournals the application header journals
	 */
	public void cacheResult(
		java.util.List<ApplicationHeaderJournal> applicationHeaderJournals);

	/**
	 * Creates a new application header journal with the primary key. Does not add the application header journal to the database.
	 *
	 * @param Id the primary key for the new application header journal
	 * @return the new application header journal
	 */
	public ApplicationHeaderJournal create(int Id);

	/**
	 * Removes the application header journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application header journal
	 * @return the application header journal that was removed
	 * @throws NoSuchApplicationHeaderJournalException if a application header journal with the primary key could not be found
	 */
	public ApplicationHeaderJournal remove(int Id)
		throws NoSuchApplicationHeaderJournalException;

	public ApplicationHeaderJournal updateImpl(
		ApplicationHeaderJournal applicationHeaderJournal);

	/**
	 * Returns the application header journal with the primary key or throws a <code>NoSuchApplicationHeaderJournalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application header journal
	 * @return the application header journal
	 * @throws NoSuchApplicationHeaderJournalException if a application header journal with the primary key could not be found
	 */
	public ApplicationHeaderJournal findByPrimaryKey(int Id)
		throws NoSuchApplicationHeaderJournalException;

	/**
	 * Returns the application header journal with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application header journal
	 * @return the application header journal, or <code>null</code> if a application header journal with the primary key could not be found
	 */
	public ApplicationHeaderJournal fetchByPrimaryKey(int Id);

	/**
	 * Returns all the application header journals.
	 *
	 * @return the application header journals
	 */
	public java.util.List<ApplicationHeaderJournal> findAll();

	/**
	 * Returns a range of all the application header journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application header journals
	 * @param end the upper bound of the range of application header journals (not inclusive)
	 * @return the range of application header journals
	 */
	public java.util.List<ApplicationHeaderJournal> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the application header journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application header journals
	 * @param end the upper bound of the range of application header journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application header journals
	 */
	public java.util.List<ApplicationHeaderJournal> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<ApplicationHeaderJournal> orderByComparator);

	/**
	 * Returns an ordered range of all the application header journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application header journals
	 * @param end the upper bound of the range of application header journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of application header journals
	 */
	public java.util.List<ApplicationHeaderJournal> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<ApplicationHeaderJournal> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the application header journals from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of application header journals.
	 *
	 * @return the number of application header journals
	 */
	public int countAll();

}