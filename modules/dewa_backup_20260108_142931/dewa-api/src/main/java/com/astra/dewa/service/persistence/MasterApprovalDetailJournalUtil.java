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

import com.astra.dewa.model.MasterApprovalDetailJournal;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the master approval detail journal service. This utility wraps <code>com.astra.dewa.service.persistence.impl.MasterApprovalDetailJournalPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalDetailJournalPersistence
 * @generated
 */
public class MasterApprovalDetailJournalUtil {

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
		MasterApprovalDetailJournal masterApprovalDetailJournal) {

		getPersistence().clearCache(masterApprovalDetailJournal);
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
	public static Map<Serializable, MasterApprovalDetailJournal>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MasterApprovalDetailJournal> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MasterApprovalDetailJournal> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MasterApprovalDetailJournal> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MasterApprovalDetailJournal> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MasterApprovalDetailJournal update(
		MasterApprovalDetailJournal masterApprovalDetailJournal) {

		return getPersistence().update(masterApprovalDetailJournal);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MasterApprovalDetailJournal update(
		MasterApprovalDetailJournal masterApprovalDetailJournal,
		ServiceContext serviceContext) {

		return getPersistence().update(
			masterApprovalDetailJournal, serviceContext);
	}

	/**
	 * Caches the master approval detail journal in the entity cache if it is enabled.
	 *
	 * @param masterApprovalDetailJournal the master approval detail journal
	 */
	public static void cacheResult(
		MasterApprovalDetailJournal masterApprovalDetailJournal) {

		getPersistence().cacheResult(masterApprovalDetailJournal);
	}

	/**
	 * Caches the master approval detail journals in the entity cache if it is enabled.
	 *
	 * @param masterApprovalDetailJournals the master approval detail journals
	 */
	public static void cacheResult(
		List<MasterApprovalDetailJournal> masterApprovalDetailJournals) {

		getPersistence().cacheResult(masterApprovalDetailJournals);
	}

	/**
	 * Creates a new master approval detail journal with the primary key. Does not add the master approval detail journal to the database.
	 *
	 * @param Id the primary key for the new master approval detail journal
	 * @return the new master approval detail journal
	 */
	public static MasterApprovalDetailJournal create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the master approval detail journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the master approval detail journal
	 * @return the master approval detail journal that was removed
	 * @throws NoSuchMasterApprovalDetailJournalException if a master approval detail journal with the primary key could not be found
	 */
	public static MasterApprovalDetailJournal remove(int Id)
		throws com.astra.dewa.exception.
			NoSuchMasterApprovalDetailJournalException {

		return getPersistence().remove(Id);
	}

	public static MasterApprovalDetailJournal updateImpl(
		MasterApprovalDetailJournal masterApprovalDetailJournal) {

		return getPersistence().updateImpl(masterApprovalDetailJournal);
	}

	/**
	 * Returns the master approval detail journal with the primary key or throws a <code>NoSuchMasterApprovalDetailJournalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval detail journal
	 * @return the master approval detail journal
	 * @throws NoSuchMasterApprovalDetailJournalException if a master approval detail journal with the primary key could not be found
	 */
	public static MasterApprovalDetailJournal findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.
			NoSuchMasterApprovalDetailJournalException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the master approval detail journal with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval detail journal
	 * @return the master approval detail journal, or <code>null</code> if a master approval detail journal with the primary key could not be found
	 */
	public static MasterApprovalDetailJournal fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the master approval detail journals.
	 *
	 * @return the master approval detail journals
	 */
	public static List<MasterApprovalDetailJournal> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<MasterApprovalDetailJournal> findAll(
		int start, int end) {

		return getPersistence().findAll(start, end);
	}

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
	public static List<MasterApprovalDetailJournal> findAll(
		int start, int end,
		OrderByComparator<MasterApprovalDetailJournal> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<MasterApprovalDetailJournal> findAll(
		int start, int end,
		OrderByComparator<MasterApprovalDetailJournal> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the master approval detail journals from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of master approval detail journals.
	 *
	 * @return the number of master approval detail journals
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MasterApprovalDetailJournalPersistence getPersistence() {
		return _persistence;
	}

	private static volatile MasterApprovalDetailJournalPersistence _persistence;

}