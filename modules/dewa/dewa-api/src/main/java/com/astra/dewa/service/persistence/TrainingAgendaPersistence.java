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

import com.astra.dewa.exception.NoSuchTrainingAgendaException;
import com.astra.dewa.model.TrainingAgenda;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the training agenda service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingAgendaUtil
 * @generated
 */
@ProviderType
public interface TrainingAgendaPersistence
	extends BasePersistence<TrainingAgenda> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingAgendaUtil} to access the training agenda persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the training agenda in the entity cache if it is enabled.
	 *
	 * @param trainingAgenda the training agenda
	 */
	public void cacheResult(TrainingAgenda trainingAgenda);

	/**
	 * Caches the training agendas in the entity cache if it is enabled.
	 *
	 * @param trainingAgendas the training agendas
	 */
	public void cacheResult(java.util.List<TrainingAgenda> trainingAgendas);

	/**
	 * Creates a new training agenda with the primary key. Does not add the training agenda to the database.
	 *
	 * @param Id the primary key for the new training agenda
	 * @return the new training agenda
	 */
	public TrainingAgenda create(int Id);

	/**
	 * Removes the training agenda with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the training agenda
	 * @return the training agenda that was removed
	 * @throws NoSuchTrainingAgendaException if a training agenda with the primary key could not be found
	 */
	public TrainingAgenda remove(int Id) throws NoSuchTrainingAgendaException;

	public TrainingAgenda updateImpl(TrainingAgenda trainingAgenda);

	/**
	 * Returns the training agenda with the primary key or throws a <code>NoSuchTrainingAgendaException</code> if it could not be found.
	 *
	 * @param Id the primary key of the training agenda
	 * @return the training agenda
	 * @throws NoSuchTrainingAgendaException if a training agenda with the primary key could not be found
	 */
	public TrainingAgenda findByPrimaryKey(int Id)
		throws NoSuchTrainingAgendaException;

	/**
	 * Returns the training agenda with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the training agenda
	 * @return the training agenda, or <code>null</code> if a training agenda with the primary key could not be found
	 */
	public TrainingAgenda fetchByPrimaryKey(int Id);

	/**
	 * Returns all the training agendas.
	 *
	 * @return the training agendas
	 */
	public java.util.List<TrainingAgenda> findAll();

	/**
	 * Returns a range of all the training agendas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agendas
	 * @param end the upper bound of the range of training agendas (not inclusive)
	 * @return the range of training agendas
	 */
	public java.util.List<TrainingAgenda> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the training agendas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agendas
	 * @param end the upper bound of the range of training agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training agendas
	 */
	public java.util.List<TrainingAgenda> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrainingAgenda>
			orderByComparator);

	/**
	 * Returns an ordered range of all the training agendas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agendas
	 * @param end the upper bound of the range of training agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of training agendas
	 */
	public java.util.List<TrainingAgenda> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrainingAgenda>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the training agendas from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of training agendas.
	 *
	 * @return the number of training agendas
	 */
	public int countAll();

}