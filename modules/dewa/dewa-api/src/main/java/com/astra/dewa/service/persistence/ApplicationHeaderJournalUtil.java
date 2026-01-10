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

import com.astra.dewa.model.ApplicationHeaderJournal;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the application header journal service. This utility wraps <code>com.astra.dewa.service.persistence.impl.ApplicationHeaderJournalPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationHeaderJournalPersistence
 * @generated
 */
public class ApplicationHeaderJournalUtil {

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
		ApplicationHeaderJournal applicationHeaderJournal) {

		getPersistence().clearCache(applicationHeaderJournal);
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
	public static Map<Serializable, ApplicationHeaderJournal>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ApplicationHeaderJournal> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ApplicationHeaderJournal> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ApplicationHeaderJournal> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ApplicationHeaderJournal> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ApplicationHeaderJournal update(
		ApplicationHeaderJournal applicationHeaderJournal) {

		return getPersistence().update(applicationHeaderJournal);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ApplicationHeaderJournal update(
		ApplicationHeaderJournal applicationHeaderJournal,
		ServiceContext serviceContext) {

		return getPersistence().update(
			applicationHeaderJournal, serviceContext);
	}

	/**
	 * Caches the application header journal in the entity cache if it is enabled.
	 *
	 * @param applicationHeaderJournal the application header journal
	 */
	public static void cacheResult(
		ApplicationHeaderJournal applicationHeaderJournal) {

		getPersistence().cacheResult(applicationHeaderJournal);
	}

	/**
	 * Caches the application header journals in the entity cache if it is enabled.
	 *
	 * @param applicationHeaderJournals the application header journals
	 */
	public static void cacheResult(
		List<ApplicationHeaderJournal> applicationHeaderJournals) {

		getPersistence().cacheResult(applicationHeaderJournals);
	}

	/**
	 * Creates a new application header journal with the primary key. Does not add the application header journal to the database.
	 *
	 * @param Id the primary key for the new application header journal
	 * @return the new application header journal
	 */
	public static ApplicationHeaderJournal create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the application header journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application header journal
	 * @return the application header journal that was removed
	 * @throws NoSuchApplicationHeaderJournalException if a application header journal with the primary key could not be found
	 */
	public static ApplicationHeaderJournal remove(int Id)
		throws com.astra.dewa.exception.
			NoSuchApplicationHeaderJournalException {

		return getPersistence().remove(Id);
	}

	public static ApplicationHeaderJournal updateImpl(
		ApplicationHeaderJournal applicationHeaderJournal) {

		return getPersistence().updateImpl(applicationHeaderJournal);
	}

	/**
	 * Returns the application header journal with the primary key or throws a <code>NoSuchApplicationHeaderJournalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application header journal
	 * @return the application header journal
	 * @throws NoSuchApplicationHeaderJournalException if a application header journal with the primary key could not be found
	 */
	public static ApplicationHeaderJournal findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.
			NoSuchApplicationHeaderJournalException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the application header journal with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application header journal
	 * @return the application header journal, or <code>null</code> if a application header journal with the primary key could not be found
	 */
	public static ApplicationHeaderJournal fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the application header journals.
	 *
	 * @return the application header journals
	 */
	public static List<ApplicationHeaderJournal> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ApplicationHeaderJournal> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ApplicationHeaderJournal> findAll(
		int start, int end,
		OrderByComparator<ApplicationHeaderJournal> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ApplicationHeaderJournal> findAll(
		int start, int end,
		OrderByComparator<ApplicationHeaderJournal> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the application header journals from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of application header journals.
	 *
	 * @return the number of application header journals
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ApplicationHeaderJournalPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ApplicationHeaderJournalPersistence _persistence;

}