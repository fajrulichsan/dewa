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
 * Provides a wrapper for {@link TrainingMateriLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingMateriLocalService
 * @generated
 */
public class TrainingMateriLocalServiceWrapper
	implements ServiceWrapper<TrainingMateriLocalService>,
			   TrainingMateriLocalService {

	public TrainingMateriLocalServiceWrapper() {
		this(null);
	}

	public TrainingMateriLocalServiceWrapper(
		TrainingMateriLocalService trainingMateriLocalService) {

		_trainingMateriLocalService = trainingMateriLocalService;
	}

	/**
	 * Adds the training materi to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingMateriLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param trainingMateri the training materi
	 * @return the training materi that was added
	 */
	@Override
	public com.astra.dewa.model.TrainingMateri addTrainingMateri(
		com.astra.dewa.model.TrainingMateri trainingMateri) {

		return _trainingMateriLocalService.addTrainingMateri(trainingMateri);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingMateriLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new training materi with the primary key. Does not add the training materi to the database.
	 *
	 * @param Id the primary key for the new training materi
	 * @return the new training materi
	 */
	@Override
	public com.astra.dewa.model.TrainingMateri createTrainingMateri(int Id) {
		return _trainingMateriLocalService.createTrainingMateri(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingMateriLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the training materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingMateriLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the training materi
	 * @return the training materi that was removed
	 * @throws PortalException if a training materi with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.TrainingMateri deleteTrainingMateri(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingMateriLocalService.deleteTrainingMateri(Id);
	}

	/**
	 * Deletes the training materi from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingMateriLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param trainingMateri the training materi
	 * @return the training materi that was removed
	 */
	@Override
	public com.astra.dewa.model.TrainingMateri deleteTrainingMateri(
		com.astra.dewa.model.TrainingMateri trainingMateri) {

		return _trainingMateriLocalService.deleteTrainingMateri(trainingMateri);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _trainingMateriLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _trainingMateriLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trainingMateriLocalService.dynamicQuery();
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

		return _trainingMateriLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TrainingMateriModelImpl</code>.
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

		return _trainingMateriLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TrainingMateriModelImpl</code>.
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

		return _trainingMateriLocalService.dynamicQuery(
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

		return _trainingMateriLocalService.dynamicQueryCount(dynamicQuery);
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

		return _trainingMateriLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.TrainingMateri fetchTrainingMateri(int Id) {
		return _trainingMateriLocalService.fetchTrainingMateri(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _trainingMateriLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _trainingMateriLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _trainingMateriLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingMateriLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the training materi with the primary key.
	 *
	 * @param Id the primary key of the training materi
	 * @return the training materi
	 * @throws PortalException if a training materi with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.TrainingMateri getTrainingMateri(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _trainingMateriLocalService.getTrainingMateri(Id);
	}

	/**
	 * Returns a range of all the training materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TrainingMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training materis
	 * @param end the upper bound of the range of training materis (not inclusive)
	 * @return the range of training materis
	 */
	@Override
	public java.util.List<com.astra.dewa.model.TrainingMateri>
		getTrainingMateris(int start, int end) {

		return _trainingMateriLocalService.getTrainingMateris(start, end);
	}

	/**
	 * Returns the number of training materis.
	 *
	 * @return the number of training materis
	 */
	@Override
	public int getTrainingMaterisCount() {
		return _trainingMateriLocalService.getTrainingMaterisCount();
	}

	/**
	 * Updates the training materi in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TrainingMateriLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param trainingMateri the training materi
	 * @return the training materi that was updated
	 */
	@Override
	public com.astra.dewa.model.TrainingMateri updateTrainingMateri(
		com.astra.dewa.model.TrainingMateri trainingMateri) {

		return _trainingMateriLocalService.updateTrainingMateri(trainingMateri);
	}

	@Override
	public TrainingMateriLocalService getWrappedService() {
		return _trainingMateriLocalService;
	}

	@Override
	public void setWrappedService(
		TrainingMateriLocalService trainingMateriLocalService) {

		_trainingMateriLocalService = trainingMateriLocalService;
	}

	private TrainingMateriLocalService _trainingMateriLocalService;

}