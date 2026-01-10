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

import com.astra.dewa.exception.NoSuchTrainingMateriException;
import com.astra.dewa.model.TrainingMateri;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the training materi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingMateriUtil
 * @generated
 */
@ProviderType
public interface TrainingMateriPersistence
	extends BasePersistence<TrainingMateri> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingMateriUtil} to access the training materi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the training materi in the entity cache if it is enabled.
	 *
	 * @param trainingMateri the training materi
	 */
	public void cacheResult(TrainingMateri trainingMateri);

	/**
	 * Caches the training materis in the entity cache if it is enabled.
	 *
	 * @param trainingMateris the training materis
	 */
	public void cacheResult(java.util.List<TrainingMateri> trainingMateris);

	/**
	 * Creates a new training materi with the primary key. Does not add the training materi to the database.
	 *
	 * @param Id the primary key for the new training materi
	 * @return the new training materi
	 */
	public TrainingMateri create(int Id);

	/**
	 * Removes the training materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the training materi
	 * @return the training materi that was removed
	 * @throws NoSuchTrainingMateriException if a training materi with the primary key could not be found
	 */
	public TrainingMateri remove(int Id) throws NoSuchTrainingMateriException;

	public TrainingMateri updateImpl(TrainingMateri trainingMateri);

	/**
	 * Returns the training materi with the primary key or throws a <code>NoSuchTrainingMateriException</code> if it could not be found.
	 *
	 * @param Id the primary key of the training materi
	 * @return the training materi
	 * @throws NoSuchTrainingMateriException if a training materi with the primary key could not be found
	 */
	public TrainingMateri findByPrimaryKey(int Id)
		throws NoSuchTrainingMateriException;

	/**
	 * Returns the training materi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the training materi
	 * @return the training materi, or <code>null</code> if a training materi with the primary key could not be found
	 */
	public TrainingMateri fetchByPrimaryKey(int Id);

	/**
	 * Returns all the training materis.
	 *
	 * @return the training materis
	 */
	public java.util.List<TrainingMateri> findAll();

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
	public java.util.List<TrainingMateri> findAll(int start, int end);

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
	public java.util.List<TrainingMateri> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrainingMateri>
			orderByComparator);

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
	public java.util.List<TrainingMateri> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrainingMateri>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the training materis from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of training materis.
	 *
	 * @return the number of training materis
	 */
	public int countAll();

}