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

import com.astra.dewa.model.MasterApproval;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the master approval service. This utility wraps <code>com.astra.dewa.service.persistence.impl.MasterApprovalPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalPersistence
 * @generated
 */
public class MasterApprovalUtil {

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
	public static void clearCache(MasterApproval masterApproval) {
		getPersistence().clearCache(masterApproval);
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
	public static Map<Serializable, MasterApproval> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MasterApproval> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MasterApproval> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MasterApproval> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MasterApproval> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MasterApproval update(MasterApproval masterApproval) {
		return getPersistence().update(masterApproval);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MasterApproval update(
		MasterApproval masterApproval, ServiceContext serviceContext) {

		return getPersistence().update(masterApproval, serviceContext);
	}

	/**
	 * Caches the master approval in the entity cache if it is enabled.
	 *
	 * @param masterApproval the master approval
	 */
	public static void cacheResult(MasterApproval masterApproval) {
		getPersistence().cacheResult(masterApproval);
	}

	/**
	 * Caches the master approvals in the entity cache if it is enabled.
	 *
	 * @param masterApprovals the master approvals
	 */
	public static void cacheResult(List<MasterApproval> masterApprovals) {
		getPersistence().cacheResult(masterApprovals);
	}

	/**
	 * Creates a new master approval with the primary key. Does not add the master approval to the database.
	 *
	 * @param Id the primary key for the new master approval
	 * @return the new master approval
	 */
	public static MasterApproval create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the master approval with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the master approval
	 * @return the master approval that was removed
	 * @throws NoSuchMasterApprovalException if a master approval with the primary key could not be found
	 */
	public static MasterApproval remove(int Id)
		throws com.astra.dewa.exception.NoSuchMasterApprovalException {

		return getPersistence().remove(Id);
	}

	public static MasterApproval updateImpl(MasterApproval masterApproval) {
		return getPersistence().updateImpl(masterApproval);
	}

	/**
	 * Returns the master approval with the primary key or throws a <code>NoSuchMasterApprovalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval
	 * @return the master approval
	 * @throws NoSuchMasterApprovalException if a master approval with the primary key could not be found
	 */
	public static MasterApproval findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchMasterApprovalException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the master approval with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval
	 * @return the master approval, or <code>null</code> if a master approval with the primary key could not be found
	 */
	public static MasterApproval fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the master approvals.
	 *
	 * @return the master approvals
	 */
	public static List<MasterApproval> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the master approvals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approvals
	 * @param end the upper bound of the range of master approvals (not inclusive)
	 * @return the range of master approvals
	 */
	public static List<MasterApproval> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the master approvals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approvals
	 * @param end the upper bound of the range of master approvals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of master approvals
	 */
	public static List<MasterApproval> findAll(
		int start, int end,
		OrderByComparator<MasterApproval> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the master approvals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approvals
	 * @param end the upper bound of the range of master approvals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of master approvals
	 */
	public static List<MasterApproval> findAll(
		int start, int end, OrderByComparator<MasterApproval> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the master approvals from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of master approvals.
	 *
	 * @return the number of master approvals
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MasterApprovalPersistence getPersistence() {
		return _persistence;
	}

	private static volatile MasterApprovalPersistence _persistence;

}