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

import com.astra.dewa.model.CalenderEventParticipant;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the calender event participant service. This utility wraps <code>com.astra.dewa.service.persistence.impl.CalenderEventParticipantPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CalenderEventParticipantPersistence
 * @generated
 */
public class CalenderEventParticipantUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(
		CalenderEventParticipant calenderEventParticipant) {

		getPersistence().clearCache(calenderEventParticipant);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CalenderEventParticipant>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CalenderEventParticipant> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CalenderEventParticipant> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CalenderEventParticipant> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CalenderEventParticipant> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CalenderEventParticipant update(
		CalenderEventParticipant calenderEventParticipant) {

		return getPersistence().update(calenderEventParticipant);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CalenderEventParticipant update(
		CalenderEventParticipant calenderEventParticipant,
		ServiceContext serviceContext) {

		return getPersistence().update(
			calenderEventParticipant, serviceContext);
	}

	/**
	 * Caches the calender event participant in the entity cache if it is enabled.
	 *
	 * @param calenderEventParticipant the calender event participant
	 */
	public static void cacheResult(
		CalenderEventParticipant calenderEventParticipant) {

		getPersistence().cacheResult(calenderEventParticipant);
	}

	/**
	 * Caches the calender event participants in the entity cache if it is enabled.
	 *
	 * @param calenderEventParticipants the calender event participants
	 */
	public static void cacheResult(
		List<CalenderEventParticipant> calenderEventParticipants) {

		getPersistence().cacheResult(calenderEventParticipants);
	}

	/**
	 * Creates a new calender event participant with the primary key. Does not add the calender event participant to the database.
	 *
	 * @param Id the primary key for the new calender event participant
	 * @return the new calender event participant
	 */
	public static CalenderEventParticipant create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the calender event participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the calender event participant
	 * @return the calender event participant that was removed
	 * @throws NoSuchCalenderEventParticipantException if a calender event participant with the primary key could not be found
	 */
	public static CalenderEventParticipant remove(int Id)
		throws com.astra.dewa.exception.
			NoSuchCalenderEventParticipantException {

		return getPersistence().remove(Id);
	}

	public static CalenderEventParticipant updateImpl(
		CalenderEventParticipant calenderEventParticipant) {

		return getPersistence().updateImpl(calenderEventParticipant);
	}

	/**
	 * Returns the calender event participant with the primary key or throws a <code>NoSuchCalenderEventParticipantException</code> if it could not be found.
	 *
	 * @param Id the primary key of the calender event participant
	 * @return the calender event participant
	 * @throws NoSuchCalenderEventParticipantException if a calender event participant with the primary key could not be found
	 */
	public static CalenderEventParticipant findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.
			NoSuchCalenderEventParticipantException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the calender event participant with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the calender event participant
	 * @return the calender event participant, or <code>null</code> if a calender event participant with the primary key could not be found
	 */
	public static CalenderEventParticipant fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the calender event participants.
	 *
	 * @return the calender event participants
	 */
	public static List<CalenderEventParticipant> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CalenderEventParticipant> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CalenderEventParticipant> findAll(
		int start, int end,
		OrderByComparator<CalenderEventParticipant> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CalenderEventParticipant> findAll(
		int start, int end,
		OrderByComparator<CalenderEventParticipant> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the calender event participants from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of calender event participants.
	 *
	 * @return the number of calender event participants
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CalenderEventParticipantPersistence getPersistence() {
		return _persistence;
	}

	private static volatile CalenderEventParticipantPersistence _persistence;

}