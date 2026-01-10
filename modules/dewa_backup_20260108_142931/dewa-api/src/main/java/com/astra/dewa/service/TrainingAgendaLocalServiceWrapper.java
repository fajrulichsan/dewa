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
 * Provides a wrapper for {@link TrainingAgendaLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingAgendaLocalService
 * @generated
 */
public class TrainingAgendaLocalServiceWrapper
	implements ServiceWrapper<TrainingAgendaLocalService>,
			   TrainingAgendaLocalService {

	public TrainingAgendaLocalServiceWrapper() {
		this(null);
	}

	public TrainingAgendaLocalServiceWrapper(
		TrainingAgendaLocalService trainingAgendaLocalService) {

		_trainingAgendaLocalService = trainingAgendaLocalService;
	}

	/**
	 * Adds the training agenda to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingAgendaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param trainingAgenda the training agenda
	 * @return the training agenda that was added
	 */
	@Override
	public com.astra.dewa.model.TrainingAgenda addTrainingAgenda(
		com.astra.dewa.model.TrainingAgenda trainingAgenda) {

		return _trainingAgendaLocalService.addTrainingAgenda(trainingAgenda);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new training agenda with the primary key. Does not add the training agenda to the database.
	 *
	 * @param Id the primary key for the new training agenda
	 * @return the new training agenda
	 */
	@Override
	public com.astra.dewa.model.TrainingAgenda createTrainingAgenda(int Id) {
		return _trainingAgendaLocalService.createTrainingAgenda(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the training agenda with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingAgendaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the training agenda
	 * @return the training agenda that was removed
	 * @throws PortalException if a training agenda with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.TrainingAgenda deleteTrainingAgenda(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaLocalService.deleteTrainingAgenda(Id);
	}

	/**
	 * Deletes the training agenda from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingAgendaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param trainingAgenda the training agenda
	 * @return the training agenda that was removed
	 */
	@Override
	public com.astra.dewa.model.TrainingAgenda deleteTrainingAgenda(
		com.astra.dewa.model.TrainingAgenda trainingAgenda) {

		return _trainingAgendaLocalService.deleteTrainingAgenda(trainingAgenda);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _trainingAgendaLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _trainingAgendaLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trainingAgendaLocalService.dynamicQuery();
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

		return _trainingAgendaLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TrainingAgendaModelImpl</code>.
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

		return _trainingAgendaLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TrainingAgendaModelImpl</code>.
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

		return _trainingAgendaLocalService.dynamicQuery(
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

		return _trainingAgendaLocalService.dynamicQueryCount(dynamicQuery);
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

		return _trainingAgendaLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.TrainingAgenda fetchTrainingAgenda(int Id) {
		return _trainingAgendaLocalService.fetchTrainingAgenda(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _trainingAgendaLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _trainingAgendaLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _trainingAgendaLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the training agenda with the primary key.
	 *
	 * @param Id the primary key of the training agenda
	 * @return the training agenda
	 * @throws PortalException if a training agenda with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.TrainingAgenda getTrainingAgenda(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingAgendaLocalService.getTrainingAgenda(Id);
	}

	/**
	 * Returns a range of all the training agendas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TrainingAgendaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agendas
	 * @param end the upper bound of the range of training agendas (not inclusive)
	 * @return the range of training agendas
	 */
	@Override
	public java.util.List<com.astra.dewa.model.TrainingAgenda>
		getTrainingAgendas(int start, int end) {

		return _trainingAgendaLocalService.getTrainingAgendas(start, end);
	}

	/**
	 * Returns the number of training agendas.
	 *
	 * @return the number of training agendas
	 */
	@Override
	public int getTrainingAgendasCount() {
		return _trainingAgendaLocalService.getTrainingAgendasCount();
	}

	/**
	 * Updates the training agenda in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingAgendaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param trainingAgenda the training agenda
	 * @return the training agenda that was updated
	 */
	@Override
	public com.astra.dewa.model.TrainingAgenda updateTrainingAgenda(
		com.astra.dewa.model.TrainingAgenda trainingAgenda) {

		return _trainingAgendaLocalService.updateTrainingAgenda(trainingAgenda);
	}

	@Override
	public TrainingAgendaLocalService getWrappedService() {
		return _trainingAgendaLocalService;
	}

	@Override
	public void setWrappedService(
		TrainingAgendaLocalService trainingAgendaLocalService) {

		_trainingAgendaLocalService = trainingAgendaLocalService;
	}

	private TrainingAgendaLocalService _trainingAgendaLocalService;

}