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
 * Provides a wrapper for {@link TrainingAgendaParticipantUfLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingAgendaParticipantUfLocalService
 * @generated
 */
public class TrainingAgendaParticipantUfLocalServiceWrapper
	implements ServiceWrapper<TrainingAgendaParticipantUfLocalService>,
			   TrainingAgendaParticipantUfLocalService {

	public TrainingAgendaParticipantUfLocalServiceWrapper() {
		this(null);
	}

	public TrainingAgendaParticipantUfLocalServiceWrapper(
		TrainingAgendaParticipantUfLocalService
			trainingAgendaParticipantUfLocalService) {

		_trainingAgendaParticipantUfLocalService =
			trainingAgendaParticipantUfLocalService;
	}

	/**
	 * Adds the training agenda participant uf to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingAgendaParticipantUfLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param trainingAgendaParticipantUf the training agenda participant uf
	 * @return the training agenda participant uf that was added
	 */
	@Override
	public com.astra.dewa.model.TrainingAgendaParticipantUf
		addTrainingAgendaParticipantUf(
			com.astra.dewa.model.TrainingAgendaParticipantUf
				trainingAgendaParticipantUf) {

		return _trainingAgendaParticipantUfLocalService.
			addTrainingAgendaParticipantUf(trainingAgendaParticipantUf);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaParticipantUfLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new training agenda participant uf with the primary key. Does not add the training agenda participant uf to the database.
	 *
	 * @param Id the primary key for the new training agenda participant uf
	 * @return the new training agenda participant uf
	 */
	@Override
	public com.astra.dewa.model.TrainingAgendaParticipantUf
		createTrainingAgendaParticipantUf(int Id) {

		return _trainingAgendaParticipantUfLocalService.
			createTrainingAgendaParticipantUf(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaParticipantUfLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the training agenda participant uf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingAgendaParticipantUfLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the training agenda participant uf
	 * @return the training agenda participant uf that was removed
	 * @throws PortalException if a training agenda participant uf with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.TrainingAgendaParticipantUf
			deleteTrainingAgendaParticipantUf(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaParticipantUfLocalService.
			deleteTrainingAgendaParticipantUf(Id);
	}

	/**
	 * Deletes the training agenda participant uf from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingAgendaParticipantUfLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param trainingAgendaParticipantUf the training agenda participant uf
	 * @return the training agenda participant uf that was removed
	 */
	@Override
	public com.astra.dewa.model.TrainingAgendaParticipantUf
		deleteTrainingAgendaParticipantUf(
			com.astra.dewa.model.TrainingAgendaParticipantUf
				trainingAgendaParticipantUf) {

		return _trainingAgendaParticipantUfLocalService.
			deleteTrainingAgendaParticipantUf(trainingAgendaParticipantUf);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _trainingAgendaParticipantUfLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _trainingAgendaParticipantUfLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trainingAgendaParticipantUfLocalService.dynamicQuery();
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

		return _trainingAgendaParticipantUfLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TrainingAgendaParticipantUfModelImpl</code>.
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

		return _trainingAgendaParticipantUfLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TrainingAgendaParticipantUfModelImpl</code>.
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

		return _trainingAgendaParticipantUfLocalService.dynamicQuery(
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

		return _trainingAgendaParticipantUfLocalService.dynamicQueryCount(
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

		return _trainingAgendaParticipantUfLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.TrainingAgendaParticipantUf
		fetchTrainingAgendaParticipantUf(int Id) {

		return _trainingAgendaParticipantUfLocalService.
			fetchTrainingAgendaParticipantUf(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _trainingAgendaParticipantUfLocalService.
			getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _trainingAgendaParticipantUfLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _trainingAgendaParticipantUfLocalService.
			getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaParticipantUfLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the training agenda participant uf with the primary key.
	 *
	 * @param Id the primary key of the training agenda participant uf
	 * @return the training agenda participant uf
	 * @throws PortalException if a training agenda participant uf with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.TrainingAgendaParticipantUf
			getTrainingAgendaParticipantUf(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaParticipantUfLocalService.
			getTrainingAgendaParticipantUf(Id);
	}

	/**
	 * Returns a range of all the training agenda participant ufs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TrainingAgendaParticipantUfModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agenda participant ufs
	 * @param end the upper bound of the range of training agenda participant ufs (not inclusive)
	 * @return the range of training agenda participant ufs
	 */
	@Override
	public java.util.List<com.astra.dewa.model.TrainingAgendaParticipantUf>
		getTrainingAgendaParticipantUfs(int start, int end) {

		return _trainingAgendaParticipantUfLocalService.
			getTrainingAgendaParticipantUfs(start, end);
	}

	/**
	 * Returns the number of training agenda participant ufs.
	 *
	 * @return the number of training agenda participant ufs
	 */
	@Override
	public int getTrainingAgendaParticipantUfsCount() {
		return _trainingAgendaParticipantUfLocalService.
			getTrainingAgendaParticipantUfsCount();
	}

	/**
	 * Updates the training agenda participant uf in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingAgendaParticipantUfLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param trainingAgendaParticipantUf the training agenda participant uf
	 * @return the training agenda participant uf that was updated
	 */
	@Override
	public com.astra.dewa.model.TrainingAgendaParticipantUf
		updateTrainingAgendaParticipantUf(
			com.astra.dewa.model.TrainingAgendaParticipantUf
				trainingAgendaParticipantUf) {

		return _trainingAgendaParticipantUfLocalService.
			updateTrainingAgendaParticipantUf(trainingAgendaParticipantUf);
	}

	@Override
	public TrainingAgendaParticipantUfLocalService getWrappedService() {
		return _trainingAgendaParticipantUfLocalService;
	}

	@Override
	public void setWrappedService(
		TrainingAgendaParticipantUfLocalService
			trainingAgendaParticipantUfLocalService) {

		_trainingAgendaParticipantUfLocalService =
			trainingAgendaParticipantUfLocalService;
	}

	private TrainingAgendaParticipantUfLocalService
		_trainingAgendaParticipantUfLocalService;

}