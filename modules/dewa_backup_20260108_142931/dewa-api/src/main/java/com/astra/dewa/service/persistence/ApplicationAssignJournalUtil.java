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

import com.astra.dewa.model.ApplicationAssignJournal;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the application assign journal service. This utility wraps <code>com.astra.dewa.service.persistence.impl.ApplicationAssignJournalPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationAssignJournalPersistence
 * @generated
 */
public class ApplicationAssignJournalUtil {

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
		ApplicationAssignJournal applicationAssignJournal) {

		getPersistence().clearCache(applicationAssignJournal);
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
	public static Map<Serializable, ApplicationAssignJournal>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ApplicationAssignJournal> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ApplicationAssignJournal> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ApplicationAssignJournal> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ApplicationAssignJournal> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ApplicationAssignJournal update(
		ApplicationAssignJournal applicationAssignJournal) {

		return getPersistence().update(applicationAssignJournal);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ApplicationAssignJournal update(
		ApplicationAssignJournal applicationAssignJournal,
		ServiceContext serviceContext) {

		return getPersistence().update(
			applicationAssignJournal, serviceContext);
	}

	/**
	 * Caches the application assign journal in the entity cache if it is enabled.
	 *
	 * @param applicationAssignJournal the application assign journal
	 */
	public static void cacheResult(
		ApplicationAssignJournal applicationAssignJournal) {

		getPersistence().cacheResult(applicationAssignJournal);
	}

	/**
	 * Caches the application assign journals in the entity cache if it is enabled.
	 *
	 * @param applicationAssignJournals the application assign journals
	 */
	public static void cacheResult(
		List<ApplicationAssignJournal> applicationAssignJournals) {

		getPersistence().cacheResult(applicationAssignJournals);
	}

	/**
	 * Creates a new application assign journal with the primary key. Does not add the application assign journal to the database.
	 *
	 * @param Id the primary key for the new application assign journal
	 * @return the new application assign journal
	 */
	public static ApplicationAssignJournal create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the application assign journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application assign journal
	 * @return the application assign journal that was removed
	 * @throws NoSuchApplicationAssignJournalException if a application assign journal with the primary key could not be found
	 */
	public static ApplicationAssignJournal remove(int Id)
		throws com.astra.dewa.exception.
			NoSuchApplicationAssignJournalException {

		return getPersistence().remove(Id);
	}

	public static ApplicationAssignJournal updateImpl(
		ApplicationAssignJournal applicationAssignJournal) {

		return getPersistence().updateImpl(applicationAssignJournal);
	}

	/**
	 * Returns the application assign journal with the primary key or throws a <code>NoSuchApplicationAssignJournalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign journal
	 * @return the application assign journal
	 * @throws NoSuchApplicationAssignJournalException if a application assign journal with the primary key could not be found
	 */
	public static ApplicationAssignJournal findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.
			NoSuchApplicationAssignJournalException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the application assign journal with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign journal
	 * @return the application assign journal, or <code>null</code> if a application assign journal with the primary key could not be found
	 */
	public static ApplicationAssignJournal fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the application assign journals.
	 *
	 * @return the application assign journals
	 */
	public static List<ApplicationAssignJournal> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ApplicationAssignJournal> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ApplicationAssignJournal> findAll(
		int start, int end,
		OrderByComparator<ApplicationAssignJournal> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ApplicationAssignJournal> findAll(
		int start, int end,
		OrderByComparator<ApplicationAssignJournal> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the application assign journals from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of application assign journals.
	 *
	 * @return the number of application assign journals
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ApplicationAssignJournalPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ApplicationAssignJournalPersistence _persistence;

}