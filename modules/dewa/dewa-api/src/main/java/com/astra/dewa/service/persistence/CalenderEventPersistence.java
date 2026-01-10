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

import com.astra.dewa.exception.NoSuchCalenderEventException;
import com.astra.dewa.model.CalenderEvent;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the calender event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CalenderEventUtil
 * @generated
 */
@ProviderType
public interface CalenderEventPersistence
	extends BasePersistence<CalenderEvent> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CalenderEventUtil} to access the calender event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the calender event in the entity cache if it is enabled.
	 *
	 * @param calenderEvent the calender event
	 */
	public void cacheResult(CalenderEvent calenderEvent);

	/**
	 * Caches the calender events in the entity cache if it is enabled.
	 *
	 * @param calenderEvents the calender events
	 */
	public void cacheResult(java.util.List<CalenderEvent> calenderEvents);

	/**
	 * Creates a new calender event with the primary key. Does not add the calender event to the database.
	 *
	 * @param Id the primary key for the new calender event
	 * @return the new calender event
	 */
	public CalenderEvent create(int Id);

	/**
	 * Removes the calender event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the calender event
	 * @return the calender event that was removed
	 * @throws NoSuchCalenderEventException if a calender event with the primary key could not be found
	 */
	public CalenderEvent remove(int Id) throws NoSuchCalenderEventException;

	public CalenderEvent updateImpl(CalenderEvent calenderEvent);

	/**
	 * Returns the calender event with the primary key or throws a <code>NoSuchCalenderEventException</code> if it could not be found.
	 *
	 * @param Id the primary key of the calender event
	 * @return the calender event
	 * @throws NoSuchCalenderEventException if a calender event with the primary key could not be found
	 */
	public CalenderEvent findByPrimaryKey(int Id)
		throws NoSuchCalenderEventException;

	/**
	 * Returns the calender event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the calender event
	 * @return the calender event, or <code>null</code> if a calender event with the primary key could not be found
	 */
	public CalenderEvent fetchByPrimaryKey(int Id);

	/**
	 * Returns all the calender events.
	 *
	 * @return the calender events
	 */
	public java.util.List<CalenderEvent> findAll();

	/**
	 * Returns a range of all the calender events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalenderEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender events
	 * @param end the upper bound of the range of calender events (not inclusive)
	 * @return the range of calender events
	 */
	public java.util.List<CalenderEvent> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the calender events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalenderEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender events
	 * @param end the upper bound of the range of calender events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calender events
	 */
	public java.util.List<CalenderEvent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalenderEvent>
			orderByComparator);

	/**
	 * Returns an ordered range of all the calender events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalenderEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender events
	 * @param end the upper bound of the range of calender events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of calender events
	 */
	public java.util.List<CalenderEvent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalenderEvent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the calender events from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of calender events.
	 *
	 * @return the number of calender events
	 */
	public int countAll();

}