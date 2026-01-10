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

import com.astra.dewa.exception.NoSuchCalenderEventParticipantException;
import com.astra.dewa.model.CalenderEventParticipant;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the calender event participant service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CalenderEventParticipantUtil
 * @generated
 */
@ProviderType
public interface CalenderEventParticipantPersistence
	extends BasePersistence<CalenderEventParticipant> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CalenderEventParticipantUtil} to access the calender event participant persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the calender event participant in the entity cache if it is enabled.
	 *
	 * @param calenderEventParticipant the calender event participant
	 */
	public void cacheResult(CalenderEventParticipant calenderEventParticipant);

	/**
	 * Caches the calender event participants in the entity cache if it is enabled.
	 *
	 * @param calenderEventParticipants the calender event participants
	 */
	public void cacheResult(
		java.util.List<CalenderEventParticipant> calenderEventParticipants);

	/**
	 * Creates a new calender event participant with the primary key. Does not add the calender event participant to the database.
	 *
	 * @param Id the primary key for the new calender event participant
	 * @return the new calender event participant
	 */
	public CalenderEventParticipant create(int Id);

	/**
	 * Removes the calender event participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the calender event participant
	 * @return the calender event participant that was removed
	 * @throws NoSuchCalenderEventParticipantException if a calender event participant with the primary key could not be found
	 */
	public CalenderEventParticipant remove(int Id)
		throws NoSuchCalenderEventParticipantException;

	public CalenderEventParticipant updateImpl(
		CalenderEventParticipant calenderEventParticipant);

	/**
	 * Returns the calender event participant with the primary key or throws a <code>NoSuchCalenderEventParticipantException</code> if it could not be found.
	 *
	 * @param Id the primary key of the calender event participant
	 * @return the calender event participant
	 * @throws NoSuchCalenderEventParticipantException if a calender event participant with the primary key could not be found
	 */
	public CalenderEventParticipant findByPrimaryKey(int Id)
		throws NoSuchCalenderEventParticipantException;

	/**
	 * Returns the calender event participant with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the calender event participant
	 * @return the calender event participant, or <code>null</code> if a calender event participant with the primary key could not be found
	 */
	public CalenderEventParticipant fetchByPrimaryKey(int Id);

	/**
	 * Returns all the calender event participants.
	 *
	 * @return the calender event participants
	 */
	public java.util.List<CalenderEventParticipant> findAll();

	/**
	 * Returns a range of all the calender event participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalenderEventParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender event participants
	 * @param end the upper bound of the range of calender event participants (not inclusive)
	 * @return the range of calender event participants
	 */
	public java.util.List<CalenderEventParticipant> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the calender event participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalenderEventParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender event participants
	 * @param end the upper bound of the range of calender event participants (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calender event participants
	 */
	public java.util.List<CalenderEventParticipant> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CalenderEventParticipant> orderByComparator);

	/**
	 * Returns an ordered range of all the calender event participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalenderEventParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender event participants
	 * @param end the upper bound of the range of calender event participants (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of calender event participants
	 */
	public java.util.List<CalenderEventParticipant> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CalenderEventParticipant> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the calender event participants from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of calender event participants.
	 *
	 * @return the number of calender event participants
	 */
	public int countAll();

}