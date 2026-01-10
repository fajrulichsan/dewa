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
 * Provides a wrapper for {@link TipeKendaraanLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TipeKendaraanLocalService
 * @generated
 */
public class TipeKendaraanLocalServiceWrapper
	implements ServiceWrapper<TipeKendaraanLocalService>,
			   TipeKendaraanLocalService {

	public TipeKendaraanLocalServiceWrapper() {
		this(null);
	}

	public TipeKendaraanLocalServiceWrapper(
		TipeKendaraanLocalService tipeKendaraanLocalService) {

		_tipeKendaraanLocalService = tipeKendaraanLocalService;
	}

	/**
	 * Adds the tipe kendaraan to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TipeKendaraanLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tipeKendaraan the tipe kendaraan
	 * @return the tipe kendaraan that was added
	 */
	@Override
	public com.astra.dewa.model.TipeKendaraan addTipeKendaraan(
		com.astra.dewa.model.TipeKendaraan tipeKendaraan) {

		return _tipeKendaraanLocalService.addTipeKendaraan(tipeKendaraan);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tipeKendaraanLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new tipe kendaraan with the primary key. Does not add the tipe kendaraan to the database.
	 *
	 * @param Id the primary key for the new tipe kendaraan
	 * @return the new tipe kendaraan
	 */
	@Override
	public com.astra.dewa.model.TipeKendaraan createTipeKendaraan(int Id) {
		return _tipeKendaraanLocalService.createTipeKendaraan(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tipeKendaraanLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the tipe kendaraan with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TipeKendaraanLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the tipe kendaraan
	 * @return the tipe kendaraan that was removed
	 * @throws PortalException if a tipe kendaraan with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.TipeKendaraan deleteTipeKendaraan(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tipeKendaraanLocalService.deleteTipeKendaraan(Id);
	}

	/**
	 * Deletes the tipe kendaraan from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TipeKendaraanLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tipeKendaraan the tipe kendaraan
	 * @return the tipe kendaraan that was removed
	 */
	@Override
	public com.astra.dewa.model.TipeKendaraan deleteTipeKendaraan(
		com.astra.dewa.model.TipeKendaraan tipeKendaraan) {

		return _tipeKendaraanLocalService.deleteTipeKendaraan(tipeKendaraan);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _tipeKendaraanLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _tipeKendaraanLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _tipeKendaraanLocalService.dynamicQuery();
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

		return _tipeKendaraanLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TipeKendaraanModelImpl</code>.
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

		return _tipeKendaraanLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TipeKendaraanModelImpl</code>.
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

		return _tipeKendaraanLocalService.dynamicQuery(
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

		return _tipeKendaraanLocalService.dynamicQueryCount(dynamicQuery);
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

		return _tipeKendaraanLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.TipeKendaraan fetchTipeKendaraan(int Id) {
		return _tipeKendaraanLocalService.fetchTipeKendaraan(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _tipeKendaraanLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _tipeKendaraanLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _tipeKendaraanLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tipeKendaraanLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the tipe kendaraan with the primary key.
	 *
	 * @param Id the primary key of the tipe kendaraan
	 * @return the tipe kendaraan
	 * @throws PortalException if a tipe kendaraan with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.TipeKendaraan getTipeKendaraan(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tipeKendaraanLocalService.getTipeKendaraan(Id);
	}

	/**
	 * Returns a range of all the tipe kendaraans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TipeKendaraanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tipe kendaraans
	 * @param end the upper bound of the range of tipe kendaraans (not inclusive)
	 * @return the range of tipe kendaraans
	 */
	@Override
	public java.util.List<com.astra.dewa.model.TipeKendaraan> getTipeKendaraans(
		int start, int end) {

		return _tipeKendaraanLocalService.getTipeKendaraans(start, end);
	}

	/**
	 * Returns the number of tipe kendaraans.
	 *
	 * @return the number of tipe kendaraans
	 */
	@Override
	public int getTipeKendaraansCount() {
		return _tipeKendaraanLocalService.getTipeKendaraansCount();
	}

	/**
	 * Updates the tipe kendaraan in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TipeKendaraanLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tipeKendaraan the tipe kendaraan
	 * @return the tipe kendaraan that was updated
	 */
	@Override
	public com.astra.dewa.model.TipeKendaraan updateTipeKendaraan(
		com.astra.dewa.model.TipeKendaraan tipeKendaraan) {

		return _tipeKendaraanLocalService.updateTipeKendaraan(tipeKendaraan);
	}

	@Override
	public TipeKendaraanLocalService getWrappedService() {
		return _tipeKendaraanLocalService;
	}

	@Override
	public void setWrappedService(
		TipeKendaraanLocalService tipeKendaraanLocalService) {

		_tipeKendaraanLocalService = tipeKendaraanLocalService;
	}

	private TipeKendaraanLocalService _tipeKendaraanLocalService;

}