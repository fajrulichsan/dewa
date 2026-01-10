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
 * Provides a wrapper for {@link DiskonTestCarLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DiskonTestCarLocalService
 * @generated
 */
public class DiskonTestCarLocalServiceWrapper
	implements DiskonTestCarLocalService,
			   ServiceWrapper<DiskonTestCarLocalService> {

	public DiskonTestCarLocalServiceWrapper() {
		this(null);
	}

	public DiskonTestCarLocalServiceWrapper(
		DiskonTestCarLocalService diskonTestCarLocalService) {

		_diskonTestCarLocalService = diskonTestCarLocalService;
	}

	/**
	 * Adds the diskon test car to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonTestCarLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonTestCar the diskon test car
	 * @return the diskon test car that was added
	 */
	@Override
	public com.astra.dewa.model.DiskonTestCar addDiskonTestCar(
		com.astra.dewa.model.DiskonTestCar diskonTestCar) {

		return _diskonTestCarLocalService.addDiskonTestCar(diskonTestCar);
	}

	/**
	 * Creates a new diskon test car with the primary key. Does not add the diskon test car to the database.
	 *
	 * @param Id the primary key for the new diskon test car
	 * @return the new diskon test car
	 */
	@Override
	public com.astra.dewa.model.DiskonTestCar createDiskonTestCar(int Id) {
		return _diskonTestCarLocalService.createDiskonTestCar(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonTestCarLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the diskon test car from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonTestCarLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonTestCar the diskon test car
	 * @return the diskon test car that was removed
	 */
	@Override
	public com.astra.dewa.model.DiskonTestCar deleteDiskonTestCar(
		com.astra.dewa.model.DiskonTestCar diskonTestCar) {

		return _diskonTestCarLocalService.deleteDiskonTestCar(diskonTestCar);
	}

	/**
	 * Deletes the diskon test car with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonTestCarLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the diskon test car
	 * @return the diskon test car that was removed
	 * @throws PortalException if a diskon test car with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.DiskonTestCar deleteDiskonTestCar(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonTestCarLocalService.deleteDiskonTestCar(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonTestCarLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _diskonTestCarLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _diskonTestCarLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _diskonTestCarLocalService.dynamicQuery();
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

		return _diskonTestCarLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonTestCarModelImpl</code>.
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

		return _diskonTestCarLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonTestCarModelImpl</code>.
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

		return _diskonTestCarLocalService.dynamicQuery(
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

		return _diskonTestCarLocalService.dynamicQueryCount(dynamicQuery);
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

		return _diskonTestCarLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.DiskonTestCar fetchDiskonTestCar(int Id) {
		return _diskonTestCarLocalService.fetchDiskonTestCar(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _diskonTestCarLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the diskon test car with the primary key.
	 *
	 * @param Id the primary key of the diskon test car
	 * @return the diskon test car
	 * @throws PortalException if a diskon test car with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.DiskonTestCar getDiskonTestCar(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonTestCarLocalService.getDiskonTestCar(Id);
	}

	/**
	 * Returns a range of all the diskon test cars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonTestCarModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon test cars
	 * @param end the upper bound of the range of diskon test cars (not inclusive)
	 * @return the range of diskon test cars
	 */
	@Override
	public java.util.List<com.astra.dewa.model.DiskonTestCar> getDiskonTestCars(
		int start, int end) {

		return _diskonTestCarLocalService.getDiskonTestCars(start, end);
	}

	/**
	 * Returns the number of diskon test cars.
	 *
	 * @return the number of diskon test cars
	 */
	@Override
	public int getDiskonTestCarsCount() {
		return _diskonTestCarLocalService.getDiskonTestCarsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _diskonTestCarLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _diskonTestCarLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonTestCarLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the diskon test car in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonTestCarLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonTestCar the diskon test car
	 * @return the diskon test car that was updated
	 */
	@Override
	public com.astra.dewa.model.DiskonTestCar updateDiskonTestCar(
		com.astra.dewa.model.DiskonTestCar diskonTestCar) {

		return _diskonTestCarLocalService.updateDiskonTestCar(diskonTestCar);
	}

	@Override
	public DiskonTestCarLocalService getWrappedService() {
		return _diskonTestCarLocalService;
	}

	@Override
	public void setWrappedService(
		DiskonTestCarLocalService diskonTestCarLocalService) {

		_diskonTestCarLocalService = diskonTestCarLocalService;
	}

	private DiskonTestCarLocalService _diskonTestCarLocalService;

}