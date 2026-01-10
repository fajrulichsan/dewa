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

import com.astra.dewa.model.TrainingMateri;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the training materi service. This utility wraps <code>com.astra.dewa.service.persistence.impl.TrainingMateriPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingMateriPersistence
 * @generated
 */
public class TrainingMateriUtil {

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
	public static void clearCache(TrainingMateri trainingMateri) {
		getPersistence().clearCache(trainingMateri);
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
	public static Map<Serializable, TrainingMateri> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TrainingMateri> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrainingMateri> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrainingMateri> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TrainingMateri> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TrainingMateri update(TrainingMateri trainingMateri) {
		return getPersistence().update(trainingMateri);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TrainingMateri update(
		TrainingMateri trainingMateri, ServiceContext serviceContext) {

		return getPersistence().update(trainingMateri, serviceContext);
	}

	/**
	 * Caches the training materi in the entity cache if it is enabled.
	 *
	 * @param trainingMateri the training materi
	 */
	public static void cacheResult(TrainingMateri trainingMateri) {
		getPersistence().cacheResult(trainingMateri);
	}

	/**
	 * Caches the training materis in the entity cache if it is enabled.
	 *
	 * @param trainingMateris the training materis
	 */
	public static void cacheResult(List<TrainingMateri> trainingMateris) {
		getPersistence().cacheResult(trainingMateris);
	}

	/**
	 * Creates a new training materi with the primary key. Does not add the training materi to the database.
	 *
	 * @param Id the primary key for the new training materi
	 * @return the new training materi
	 */
	public static TrainingMateri create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the training materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the training materi
	 * @return the training materi that was removed
	 * @throws NoSuchTrainingMateriException if a training materi with the primary key could not be found
	 */
	public static TrainingMateri remove(int Id)
		throws com.astra.dewa.exception.NoSuchTrainingMateriException {

		return getPersistence().remove(Id);
	}

	public static TrainingMateri updateImpl(TrainingMateri trainingMateri) {
		return getPersistence().updateImpl(trainingMateri);
	}

	/**
	 * Returns the training materi with the primary key or throws a <code>NoSuchTrainingMateriException</code> if it could not be found.
	 *
	 * @param Id the primary key of the training materi
	 * @return the training materi
	 * @throws NoSuchTrainingMateriException if a training materi with the primary key could not be found
	 */
	public static TrainingMateri findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchTrainingMateriException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the training materi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the training materi
	 * @return the training materi, or <code>null</code> if a training materi with the primary key could not be found
	 */
	public static TrainingMateri fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the training materis.
	 *
	 * @return the training materis
	 */
	public static List<TrainingMateri> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the training materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training materis
	 * @param end the upper bound of the range of training materis (not inclusive)
	 * @return the range of training materis
	 */
	public static List<TrainingMateri> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the training materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training materis
	 * @param end the upper bound of the range of training materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training materis
	 */
	public static List<TrainingMateri> findAll(
		int start, int end,
		OrderByComparator<TrainingMateri> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the training materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training materis
	 * @param end the upper bound of the range of training materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of training materis
	 */
	public static List<TrainingMateri> findAll(
		int start, int end, OrderByComparator<TrainingMateri> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the training materis from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of training materis.
	 *
	 * @return the number of training materis
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TrainingMateriPersistence getPersistence() {
		return _persistence;
	}

	private static volatile TrainingMateriPersistence _persistence;

}