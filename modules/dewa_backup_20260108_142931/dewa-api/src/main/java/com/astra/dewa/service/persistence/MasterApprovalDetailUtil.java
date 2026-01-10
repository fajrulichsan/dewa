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

import com.astra.dewa.model.MasterApprovalDetail;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the master approval detail service. This utility wraps <code>com.astra.dewa.service.persistence.impl.MasterApprovalDetailPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalDetailPersistence
 * @generated
 */
public class MasterApprovalDetailUtil {

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
	public static void clearCache(MasterApprovalDetail masterApprovalDetail) {
		getPersistence().clearCache(masterApprovalDetail);
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
	public static Map<Serializable, MasterApprovalDetail> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MasterApprovalDetail> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MasterApprovalDetail> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MasterApprovalDetail> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MasterApprovalDetail> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MasterApprovalDetail update(
		MasterApprovalDetail masterApprovalDetail) {

		return getPersistence().update(masterApprovalDetail);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MasterApprovalDetail update(
		MasterApprovalDetail masterApprovalDetail,
		ServiceContext serviceContext) {

		return getPersistence().update(masterApprovalDetail, serviceContext);
	}

	/**
	 * Caches the master approval detail in the entity cache if it is enabled.
	 *
	 * @param masterApprovalDetail the master approval detail
	 */
	public static void cacheResult(MasterApprovalDetail masterApprovalDetail) {
		getPersistence().cacheResult(masterApprovalDetail);
	}

	/**
	 * Caches the master approval details in the entity cache if it is enabled.
	 *
	 * @param masterApprovalDetails the master approval details
	 */
	public static void cacheResult(
		List<MasterApprovalDetail> masterApprovalDetails) {

		getPersistence().cacheResult(masterApprovalDetails);
	}

	/**
	 * Creates a new master approval detail with the primary key. Does not add the master approval detail to the database.
	 *
	 * @param Id the primary key for the new master approval detail
	 * @return the new master approval detail
	 */
	public static MasterApprovalDetail create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the master approval detail with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the master approval detail
	 * @return the master approval detail that was removed
	 * @throws NoSuchMasterApprovalDetailException if a master approval detail with the primary key could not be found
	 */
	public static MasterApprovalDetail remove(int Id)
		throws com.astra.dewa.exception.NoSuchMasterApprovalDetailException {

		return getPersistence().remove(Id);
	}

	public static MasterApprovalDetail updateImpl(
		MasterApprovalDetail masterApprovalDetail) {

		return getPersistence().updateImpl(masterApprovalDetail);
	}

	/**
	 * Returns the master approval detail with the primary key or throws a <code>NoSuchMasterApprovalDetailException</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval detail
	 * @return the master approval detail
	 * @throws NoSuchMasterApprovalDetailException if a master approval detail with the primary key could not be found
	 */
	public static MasterApprovalDetail findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchMasterApprovalDetailException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the master approval detail with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval detail
	 * @return the master approval detail, or <code>null</code> if a master approval detail with the primary key could not be found
	 */
	public static MasterApprovalDetail fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the master approval details.
	 *
	 * @return the master approval details
	 */
	public static List<MasterApprovalDetail> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the master approval details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval details
	 * @param end the upper bound of the range of master approval details (not inclusive)
	 * @return the range of master approval details
	 */
	public static List<MasterApprovalDetail> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the master approval details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval details
	 * @param end the upper bound of the range of master approval details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of master approval details
	 */
	public static List<MasterApprovalDetail> findAll(
		int start, int end,
		OrderByComparator<MasterApprovalDetail> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the master approval details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval details
	 * @param end the upper bound of the range of master approval details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of master approval details
	 */
	public static List<MasterApprovalDetail> findAll(
		int start, int end,
		OrderByComparator<MasterApprovalDetail> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the master approval details from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of master approval details.
	 *
	 * @return the number of master approval details
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MasterApprovalDetailPersistence getPersistence() {
		return _persistence;
	}

	private static volatile MasterApprovalDetailPersistence _persistence;

}