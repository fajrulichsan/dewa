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

import com.astra.dewa.model.TrainingMateriLampiran;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the training materi lampiran service. This utility wraps <code>com.astra.dewa.service.persistence.impl.TrainingMateriLampiranPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingMateriLampiranPersistence
 * @generated
 */
public class TrainingMateriLampiranUtil {

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
		TrainingMateriLampiran trainingMateriLampiran) {

		getPersistence().clearCache(trainingMateriLampiran);
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
	public static Map<Serializable, TrainingMateriLampiran> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TrainingMateriLampiran> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrainingMateriLampiran> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrainingMateriLampiran> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TrainingMateriLampiran> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TrainingMateriLampiran update(
		TrainingMateriLampiran trainingMateriLampiran) {

		return getPersistence().update(trainingMateriLampiran);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TrainingMateriLampiran update(
		TrainingMateriLampiran trainingMateriLampiran,
		ServiceContext serviceContext) {

		return getPersistence().update(trainingMateriLampiran, serviceContext);
	}

	/**
	 * Caches the training materi lampiran in the entity cache if it is enabled.
	 *
	 * @param trainingMateriLampiran the training materi lampiran
	 */
	public static void cacheResult(
		TrainingMateriLampiran trainingMateriLampiran) {

		getPersistence().cacheResult(trainingMateriLampiran);
	}

	/**
	 * Caches the training materi lampirans in the entity cache if it is enabled.
	 *
	 * @param trainingMateriLampirans the training materi lampirans
	 */
	public static void cacheResult(
		List<TrainingMateriLampiran> trainingMateriLampirans) {

		getPersistence().cacheResult(trainingMateriLampirans);
	}

	/**
	 * Creates a new training materi lampiran with the primary key. Does not add the training materi lampiran to the database.
	 *
	 * @param Id the primary key for the new training materi lampiran
	 * @return the new training materi lampiran
	 */
	public static TrainingMateriLampiran create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the training materi lampiran with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the training materi lampiran
	 * @return the training materi lampiran that was removed
	 * @throws NoSuchTrainingMateriLampiranException if a training materi lampiran with the primary key could not be found
	 */
	public static TrainingMateriLampiran remove(int Id)
		throws com.astra.dewa.exception.NoSuchTrainingMateriLampiranException {

		return getPersistence().remove(Id);
	}

	public static TrainingMateriLampiran updateImpl(
		TrainingMateriLampiran trainingMateriLampiran) {

		return getPersistence().updateImpl(trainingMateriLampiran);
	}

	/**
	 * Returns the training materi lampiran with the primary key or throws a <code>NoSuchTrainingMateriLampiranException</code> if it could not be found.
	 *
	 * @param Id the primary key of the training materi lampiran
	 * @return the training materi lampiran
	 * @throws NoSuchTrainingMateriLampiranException if a training materi lampiran with the primary key could not be found
	 */
	public static TrainingMateriLampiran findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchTrainingMateriLampiranException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the training materi lampiran with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the training materi lampiran
	 * @return the training materi lampiran, or <code>null</code> if a training materi lampiran with the primary key could not be found
	 */
	public static TrainingMateriLampiran fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the training materi lampirans.
	 *
	 * @return the training materi lampirans
	 */
	public static List<TrainingMateriLampiran> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the training materi lampirans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingMateriLampiranModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training materi lampirans
	 * @param end the upper bound of the range of training materi lampirans (not inclusive)
	 * @return the range of training materi lampirans
	 */
	public static List<TrainingMateriLampiran> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the training materi lampirans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingMateriLampiranModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training materi lampirans
	 * @param end the upper bound of the range of training materi lampirans (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training materi lampirans
	 */
	public static List<TrainingMateriLampiran> findAll(
		int start, int end,
		OrderByComparator<TrainingMateriLampiran> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the training materi lampirans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingMateriLampiranModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training materi lampirans
	 * @param end the upper bound of the range of training materi lampirans (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of training materi lampirans
	 */
	public static List<TrainingMateriLampiran> findAll(
		int start, int end,
		OrderByComparator<TrainingMateriLampiran> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the training materi lampirans from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of training materi lampirans.
	 *
	 * @return the number of training materi lampirans
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TrainingMateriLampiranPersistence getPersistence() {
		return _persistence;
	}

	private static volatile TrainingMateriLampiranPersistence _persistence;

}