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

import com.astra.dewa.exception.NoSuchTrainingAgendaParticipantUfException;
import com.astra.dewa.model.TrainingAgendaParticipantUf;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the training agenda participant uf service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingAgendaParticipantUfUtil
 * @generated
 */
@ProviderType
public interface TrainingAgendaParticipantUfPersistence
	extends BasePersistence<TrainingAgendaParticipantUf> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingAgendaParticipantUfUtil} to access the training agenda participant uf persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the training agenda participant uf in the entity cache if it is enabled.
	 *
	 * @param trainingAgendaParticipantUf the training agenda participant uf
	 */
	public void cacheResult(
		TrainingAgendaParticipantUf trainingAgendaParticipantUf);

	/**
	 * Caches the training agenda participant ufs in the entity cache if it is enabled.
	 *
	 * @param trainingAgendaParticipantUfs the training agenda participant ufs
	 */
	public void cacheResult(
		java.util.List<TrainingAgendaParticipantUf>
			trainingAgendaParticipantUfs);

	/**
	 * Creates a new training agenda participant uf with the primary key. Does not add the training agenda participant uf to the database.
	 *
	 * @param Id the primary key for the new training agenda participant uf
	 * @return the new training agenda participant uf
	 */
	public TrainingAgendaParticipantUf create(int Id);

	/**
	 * Removes the training agenda participant uf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the training agenda participant uf
	 * @return the training agenda participant uf that was removed
	 * @throws NoSuchTrainingAgendaParticipantUfException if a training agenda participant uf with the primary key could not be found
	 */
	public TrainingAgendaParticipantUf remove(int Id)
		throws NoSuchTrainingAgendaParticipantUfException;

	public TrainingAgendaParticipantUf updateImpl(
		TrainingAgendaParticipantUf trainingAgendaParticipantUf);

	/**
	 * Returns the training agenda participant uf with the primary key or throws a <code>NoSuchTrainingAgendaParticipantUfException</code> if it could not be found.
	 *
	 * @param Id the primary key of the training agenda participant uf
	 * @return the training agenda participant uf
	 * @throws NoSuchTrainingAgendaParticipantUfException if a training agenda participant uf with the primary key could not be found
	 */
	public TrainingAgendaParticipantUf findByPrimaryKey(int Id)
		throws NoSuchTrainingAgendaParticipantUfException;

	/**
	 * Returns the training agenda participant uf with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the training agenda participant uf
	 * @return the training agenda participant uf, or <code>null</code> if a training agenda participant uf with the primary key could not be found
	 */
	public TrainingAgendaParticipantUf fetchByPrimaryKey(int Id);

	/**
	 * Returns all the training agenda participant ufs.
	 *
	 * @return the training agenda participant ufs
	 */
	public java.util.List<TrainingAgendaParticipantUf> findAll();

	/**
	 * Returns a range of all the training agenda participant ufs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaParticipantUfModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agenda participant ufs
	 * @param end the upper bound of the range of training agenda participant ufs (not inclusive)
	 * @return the range of training agenda participant ufs
	 */
	public java.util.List<TrainingAgendaParticipantUf> findAll(
		int start, int end);

	/**
	 * Returns an ordered range of all the training agenda participant ufs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaParticipantUfModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agenda participant ufs
	 * @param end the upper bound of the range of training agenda participant ufs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training agenda participant ufs
	 */
	public java.util.List<TrainingAgendaParticipantUf> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<TrainingAgendaParticipantUf> orderByComparator);

	/**
	 * Returns an ordered range of all the training agenda participant ufs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaParticipantUfModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agenda participant ufs
	 * @param end the upper bound of the range of training agenda participant ufs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of training agenda participant ufs
	 */
	public java.util.List<TrainingAgendaParticipantUf> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<TrainingAgendaParticipantUf> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the training agenda participant ufs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of training agenda participant ufs.
	 *
	 * @return the number of training agenda participant ufs
	 */
	public int countAll();

}