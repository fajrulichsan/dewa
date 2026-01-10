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

import com.astra.dewa.exception.NoSuchTrainingMateriLampiranException;
import com.astra.dewa.model.TrainingMateriLampiran;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the training materi lampiran service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingMateriLampiranUtil
 * @generated
 */
@ProviderType
public interface TrainingMateriLampiranPersistence
	extends BasePersistence<TrainingMateriLampiran> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingMateriLampiranUtil} to access the training materi lampiran persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the training materi lampiran in the entity cache if it is enabled.
	 *
	 * @param trainingMateriLampiran the training materi lampiran
	 */
	public void cacheResult(TrainingMateriLampiran trainingMateriLampiran);

	/**
	 * Caches the training materi lampirans in the entity cache if it is enabled.
	 *
	 * @param trainingMateriLampirans the training materi lampirans
	 */
	public void cacheResult(
		java.util.List<TrainingMateriLampiran> trainingMateriLampirans);

	/**
	 * Creates a new training materi lampiran with the primary key. Does not add the training materi lampiran to the database.
	 *
	 * @param Id the primary key for the new training materi lampiran
	 * @return the new training materi lampiran
	 */
	public TrainingMateriLampiran create(int Id);

	/**
	 * Removes the training materi lampiran with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the training materi lampiran
	 * @return the training materi lampiran that was removed
	 * @throws NoSuchTrainingMateriLampiranException if a training materi lampiran with the primary key could not be found
	 */
	public TrainingMateriLampiran remove(int Id)
		throws NoSuchTrainingMateriLampiranException;

	public TrainingMateriLampiran updateImpl(
		TrainingMateriLampiran trainingMateriLampiran);

	/**
	 * Returns the training materi lampiran with the primary key or throws a <code>NoSuchTrainingMateriLampiranException</code> if it could not be found.
	 *
	 * @param Id the primary key of the training materi lampiran
	 * @return the training materi lampiran
	 * @throws NoSuchTrainingMateriLampiranException if a training materi lampiran with the primary key could not be found
	 */
	public TrainingMateriLampiran findByPrimaryKey(int Id)
		throws NoSuchTrainingMateriLampiranException;

	/**
	 * Returns the training materi lampiran with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the training materi lampiran
	 * @return the training materi lampiran, or <code>null</code> if a training materi lampiran with the primary key could not be found
	 */
	public TrainingMateriLampiran fetchByPrimaryKey(int Id);

	/**
	 * Returns all the training materi lampirans.
	 *
	 * @return the training materi lampirans
	 */
	public java.util.List<TrainingMateriLampiran> findAll();

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
	public java.util.List<TrainingMateriLampiran> findAll(int start, int end);

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
	public java.util.List<TrainingMateriLampiran> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrainingMateriLampiran>
			orderByComparator);

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
	public java.util.List<TrainingMateriLampiran> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrainingMateriLampiran>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the training materi lampirans from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of training materi lampirans.
	 *
	 * @return the number of training materi lampirans
	 */
	public int countAll();

}