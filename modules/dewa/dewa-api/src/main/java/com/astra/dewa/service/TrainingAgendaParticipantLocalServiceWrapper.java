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

package com.astra.dewa.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TrainingAgendaParticipantLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingAgendaParticipantLocalService
 * @generated
 */
public class TrainingAgendaParticipantLocalServiceWrapper
	implements ServiceWrapper<TrainingAgendaParticipantLocalService>,
			   TrainingAgendaParticipantLocalService {

	public TrainingAgendaParticipantLocalServiceWrapper() {
		this(null);
	}

	public TrainingAgendaParticipantLocalServiceWrapper(
		TrainingAgendaParticipantLocalService
			trainingAgendaParticipantLocalService) {

		_trainingAgendaParticipantLocalService =
			trainingAgendaParticipantLocalService;
	}

	/**
	 * Adds the training agenda participant to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingAgendaParticipantLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param trainingAgendaParticipant the training agenda participant
	 * @return the training agenda participant that was added
	 */
	@Override
	public com.astra.dewa.model.TrainingAgendaParticipant
		addTrainingAgendaParticipant(
			com.astra.dewa.model.TrainingAgendaParticipant
				trainingAgendaParticipant) {

		return _trainingAgendaParticipantLocalService.
			addTrainingAgendaParticipant(trainingAgendaParticipant);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaParticipantLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new training agenda participant with the primary key. Does not add the training agenda participant to the database.
	 *
	 * @param Id the primary key for the new training agenda participant
	 * @return the new training agenda participant
	 */
	@Override
	public com.astra.dewa.model.TrainingAgendaParticipant
		createTrainingAgendaParticipant(int Id) {

		return _trainingAgendaParticipantLocalService.
			createTrainingAgendaParticipant(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaParticipantLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the training agenda participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingAgendaParticipantLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the training agenda participant
	 * @return the training agenda participant that was removed
	 * @throws PortalException if a training agenda participant with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.TrainingAgendaParticipant
			deleteTrainingAgendaParticipant(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaParticipantLocalService.
			deleteTrainingAgendaParticipant(Id);
	}

	/**
	 * Deletes the training agenda participant from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingAgendaParticipantLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param trainingAgendaParticipant the training agenda participant
	 * @return the training agenda participant that was removed
	 */
	@Override
	public com.astra.dewa.model.TrainingAgendaParticipant
		deleteTrainingAgendaParticipant(
			com.astra.dewa.model.TrainingAgendaParticipant
				trainingAgendaParticipant) {

		return _trainingAgendaParticipantLocalService.
			deleteTrainingAgendaParticipant(trainingAgendaParticipant);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _trainingAgendaParticipantLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _trainingAgendaParticipantLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trainingAgendaParticipantLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _trainingAgendaParticipantLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TrainingAgendaParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _trainingAgendaParticipantLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TrainingAgendaParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _trainingAgendaParticipantLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _trainingAgendaParticipantLocalService.dynamicQueryCount(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _trainingAgendaParticipantLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.TrainingAgendaParticipant
		fetchTrainingAgendaParticipant(int Id) {

		return _trainingAgendaParticipantLocalService.
			fetchTrainingAgendaParticipant(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _trainingAgendaParticipantLocalService.
			getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _trainingAgendaParticipantLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _trainingAgendaParticipantLocalService.
			getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaParticipantLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the training agenda participant with the primary key.
	 *
	 * @param Id the primary key of the training agenda participant
	 * @return the training agenda participant
	 * @throws PortalException if a training agenda participant with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.TrainingAgendaParticipant
			getTrainingAgendaParticipant(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaParticipantLocalService.
			getTrainingAgendaParticipant(Id);
	}

	/**
	 * Returns a range of all the training agenda participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TrainingAgendaParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agenda participants
	 * @param end the upper bound of the range of training agenda participants (not inclusive)
	 * @return the range of training agenda participants
	 */
	@Override
	public java.util.List<com.astra.dewa.model.TrainingAgendaParticipant>
		getTrainingAgendaParticipants(int start, int end) {

		return _trainingAgendaParticipantLocalService.
			getTrainingAgendaParticipants(start, end);
	}

	/**
	 * Returns the number of training agenda participants.
	 *
	 * @return the number of training agenda participants
	 */
	@Override
	public int getTrainingAgendaParticipantsCount() {
		return _trainingAgendaParticipantLocalService.
			getTrainingAgendaParticipantsCount();
	}

	/**
	 * Updates the training agenda participant in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingAgendaParticipantLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param trainingAgendaParticipant the training agenda participant
	 * @return the training agenda participant that was updated
	 */
	@Override
	public com.astra.dewa.model.TrainingAgendaParticipant
		updateTrainingAgendaParticipant(
			com.astra.dewa.model.TrainingAgendaParticipant
				trainingAgendaParticipant) {

		return _trainingAgendaParticipantLocalService.
			updateTrainingAgendaParticipant(trainingAgendaParticipant);
	}

	@Override
	public TrainingAgendaParticipantLocalService getWrappedService() {
		return _trainingAgendaParticipantLocalService;
	}

	@Override
	public void setWrappedService(
		TrainingAgendaParticipantLocalService
			trainingAgendaParticipantLocalService) {

		_trainingAgendaParticipantLocalService =
			trainingAgendaParticipantLocalService;
	}

	private TrainingAgendaParticipantLocalService
		_trainingAgendaParticipantLocalService;

}