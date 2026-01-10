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
 * Provides a wrapper for {@link DiskonFleetLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DiskonFleetLocalService
 * @generated
 */
public class DiskonFleetLocalServiceWrapper
	implements DiskonFleetLocalService,
			   ServiceWrapper<DiskonFleetLocalService> {

	public DiskonFleetLocalServiceWrapper() {
		this(null);
	}

	public DiskonFleetLocalServiceWrapper(
		DiskonFleetLocalService diskonFleetLocalService) {

		_diskonFleetLocalService = diskonFleetLocalService;
	}

	/**
	 * Adds the diskon fleet to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonFleetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonFleet the diskon fleet
	 * @return the diskon fleet that was added
	 */
	@Override
	public com.astra.dewa.model.DiskonFleet addDiskonFleet(
		com.astra.dewa.model.DiskonFleet diskonFleet) {

		return _diskonFleetLocalService.addDiskonFleet(diskonFleet);
	}

	/**
	 * Creates a new diskon fleet with the primary key. Does not add the diskon fleet to the database.
	 *
	 * @param Id the primary key for the new diskon fleet
	 * @return the new diskon fleet
	 */
	@Override
	public com.astra.dewa.model.DiskonFleet createDiskonFleet(int Id) {
		return _diskonFleetLocalService.createDiskonFleet(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonFleetLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the diskon fleet from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonFleetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonFleet the diskon fleet
	 * @return the diskon fleet that was removed
	 */
	@Override
	public com.astra.dewa.model.DiskonFleet deleteDiskonFleet(
		com.astra.dewa.model.DiskonFleet diskonFleet) {

		return _diskonFleetLocalService.deleteDiskonFleet(diskonFleet);
	}

	/**
	 * Deletes the diskon fleet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonFleetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the diskon fleet
	 * @return the diskon fleet that was removed
	 * @throws PortalException if a diskon fleet with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.DiskonFleet deleteDiskonFleet(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonFleetLocalService.deleteDiskonFleet(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonFleetLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _diskonFleetLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _diskonFleetLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _diskonFleetLocalService.dynamicQuery();
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

		return _diskonFleetLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonFleetModelImpl</code>.
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

		return _diskonFleetLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonFleetModelImpl</code>.
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

		return _diskonFleetLocalService.dynamicQuery(
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

		return _diskonFleetLocalService.dynamicQueryCount(dynamicQuery);
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

		return _diskonFleetLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.DiskonFleet fetchDiskonFleet(int Id) {
		return _diskonFleetLocalService.fetchDiskonFleet(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _diskonFleetLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the diskon fleet with the primary key.
	 *
	 * @param Id the primary key of the diskon fleet
	 * @return the diskon fleet
	 * @throws PortalException if a diskon fleet with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.DiskonFleet getDiskonFleet(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonFleetLocalService.getDiskonFleet(Id);
	}

	/**
	 * Returns a range of all the diskon fleets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonFleetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fleets
	 * @param end the upper bound of the range of diskon fleets (not inclusive)
	 * @return the range of diskon fleets
	 */
	@Override
	public java.util.List<com.astra.dewa.model.DiskonFleet> getDiskonFleets(
		int start, int end) {

		return _diskonFleetLocalService.getDiskonFleets(start, end);
	}

	/**
	 * Returns the number of diskon fleets.
	 *
	 * @return the number of diskon fleets
	 */
	@Override
	public int getDiskonFleetsCount() {
		return _diskonFleetLocalService.getDiskonFleetsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _diskonFleetLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _diskonFleetLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonFleetLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the diskon fleet in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonFleetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonFleet the diskon fleet
	 * @return the diskon fleet that was updated
	 */
	@Override
	public com.astra.dewa.model.DiskonFleet updateDiskonFleet(
		com.astra.dewa.model.DiskonFleet diskonFleet) {

		return _diskonFleetLocalService.updateDiskonFleet(diskonFleet);
	}

	@Override
	public DiskonFleetLocalService getWrappedService() {
		return _diskonFleetLocalService;
	}

	@Override
	public void setWrappedService(
		DiskonFleetLocalService diskonFleetLocalService) {

		_diskonFleetLocalService = diskonFleetLocalService;
	}

	private DiskonFleetLocalService _diskonFleetLocalService;

}