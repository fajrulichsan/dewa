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
 * Provides a wrapper for {@link DiskonFakpolLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DiskonFakpolLocalService
 * @generated
 */
public class DiskonFakpolLocalServiceWrapper
	implements DiskonFakpolLocalService,
			   ServiceWrapper<DiskonFakpolLocalService> {

	public DiskonFakpolLocalServiceWrapper() {
		this(null);
	}

	public DiskonFakpolLocalServiceWrapper(
		DiskonFakpolLocalService diskonFakpolLocalService) {

		_diskonFakpolLocalService = diskonFakpolLocalService;
	}

	/**
	 * Adds the diskon fakpol to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonFakpolLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonFakpol the diskon fakpol
	 * @return the diskon fakpol that was added
	 */
	@Override
	public com.astra.dewa.model.DiskonFakpol addDiskonFakpol(
		com.astra.dewa.model.DiskonFakpol diskonFakpol) {

		return _diskonFakpolLocalService.addDiskonFakpol(diskonFakpol);
	}

	/**
	 * Creates a new diskon fakpol with the primary key. Does not add the diskon fakpol to the database.
	 *
	 * @param Id the primary key for the new diskon fakpol
	 * @return the new diskon fakpol
	 */
	@Override
	public com.astra.dewa.model.DiskonFakpol createDiskonFakpol(int Id) {
		return _diskonFakpolLocalService.createDiskonFakpol(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonFakpolLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the diskon fakpol from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonFakpolLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonFakpol the diskon fakpol
	 * @return the diskon fakpol that was removed
	 */
	@Override
	public com.astra.dewa.model.DiskonFakpol deleteDiskonFakpol(
		com.astra.dewa.model.DiskonFakpol diskonFakpol) {

		return _diskonFakpolLocalService.deleteDiskonFakpol(diskonFakpol);
	}

	/**
	 * Deletes the diskon fakpol with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonFakpolLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the diskon fakpol
	 * @return the diskon fakpol that was removed
	 * @throws PortalException if a diskon fakpol with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.DiskonFakpol deleteDiskonFakpol(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonFakpolLocalService.deleteDiskonFakpol(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonFakpolLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _diskonFakpolLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _diskonFakpolLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _diskonFakpolLocalService.dynamicQuery();
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

		return _diskonFakpolLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonFakpolModelImpl</code>.
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

		return _diskonFakpolLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonFakpolModelImpl</code>.
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

		return _diskonFakpolLocalService.dynamicQuery(
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

		return _diskonFakpolLocalService.dynamicQueryCount(dynamicQuery);
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

		return _diskonFakpolLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.DiskonFakpol fetchDiskonFakpol(int Id) {
		return _diskonFakpolLocalService.fetchDiskonFakpol(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _diskonFakpolLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the diskon fakpol with the primary key.
	 *
	 * @param Id the primary key of the diskon fakpol
	 * @return the diskon fakpol
	 * @throws PortalException if a diskon fakpol with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.DiskonFakpol getDiskonFakpol(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonFakpolLocalService.getDiskonFakpol(Id);
	}

	/**
	 * Returns a range of all the diskon fakpols.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonFakpolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fakpols
	 * @param end the upper bound of the range of diskon fakpols (not inclusive)
	 * @return the range of diskon fakpols
	 */
	@Override
	public java.util.List<com.astra.dewa.model.DiskonFakpol> getDiskonFakpols(
		int start, int end) {

		return _diskonFakpolLocalService.getDiskonFakpols(start, end);
	}

	/**
	 * Returns the number of diskon fakpols.
	 *
	 * @return the number of diskon fakpols
	 */
	@Override
	public int getDiskonFakpolsCount() {
		return _diskonFakpolLocalService.getDiskonFakpolsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _diskonFakpolLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _diskonFakpolLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonFakpolLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the diskon fakpol in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonFakpolLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonFakpol the diskon fakpol
	 * @return the diskon fakpol that was updated
	 */
	@Override
	public com.astra.dewa.model.DiskonFakpol updateDiskonFakpol(
		com.astra.dewa.model.DiskonFakpol diskonFakpol) {

		return _diskonFakpolLocalService.updateDiskonFakpol(diskonFakpol);
	}

	@Override
	public DiskonFakpolLocalService getWrappedService() {
		return _diskonFakpolLocalService;
	}

	@Override
	public void setWrappedService(
		DiskonFakpolLocalService diskonFakpolLocalService) {

		_diskonFakpolLocalService = diskonFakpolLocalService;
	}

	private DiskonFakpolLocalService _diskonFakpolLocalService;

}