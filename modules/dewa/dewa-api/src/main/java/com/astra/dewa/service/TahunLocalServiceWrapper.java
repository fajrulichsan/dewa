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
 * Provides a wrapper for {@link TahunLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TahunLocalService
 * @generated
 */
public class TahunLocalServiceWrapper
	implements ServiceWrapper<TahunLocalService>, TahunLocalService {

	public TahunLocalServiceWrapper() {
		this(null);
	}

	public TahunLocalServiceWrapper(TahunLocalService tahunLocalService) {
		_tahunLocalService = tahunLocalService;
	}

	/**
	 * Adds the tahun to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TahunLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tahun the tahun
	 * @return the tahun that was added
	 */
	@Override
	public com.astra.dewa.model.Tahun addTahun(
		com.astra.dewa.model.Tahun tahun) {

		return _tahunLocalService.addTahun(tahun);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tahunLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new tahun with the primary key. Does not add the tahun to the database.
	 *
	 * @param Id the primary key for the new tahun
	 * @return the new tahun
	 */
	@Override
	public com.astra.dewa.model.Tahun createTahun(String Id) {
		return _tahunLocalService.createTahun(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tahunLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the tahun with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TahunLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the tahun
	 * @return the tahun that was removed
	 * @throws PortalException if a tahun with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.Tahun deleteTahun(String Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tahunLocalService.deleteTahun(Id);
	}

	/**
	 * Deletes the tahun from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TahunLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tahun the tahun
	 * @return the tahun that was removed
	 */
	@Override
	public com.astra.dewa.model.Tahun deleteTahun(
		com.astra.dewa.model.Tahun tahun) {

		return _tahunLocalService.deleteTahun(tahun);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _tahunLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _tahunLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _tahunLocalService.dynamicQuery();
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

		return _tahunLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TahunModelImpl</code>.
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

		return _tahunLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TahunModelImpl</code>.
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

		return _tahunLocalService.dynamicQuery(
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

		return _tahunLocalService.dynamicQueryCount(dynamicQuery);
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

		return _tahunLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.Tahun fetchTahun(String Id) {
		return _tahunLocalService.fetchTahun(Id);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _tahunLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tahunLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the tahun with the primary key.
	 *
	 * @param Id the primary key of the tahun
	 * @return the tahun
	 * @throws PortalException if a tahun with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.Tahun getTahun(String Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tahunLocalService.getTahun(Id);
	}

	/**
	 * Returns a range of all the tahuns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TahunModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tahuns
	 * @param end the upper bound of the range of tahuns (not inclusive)
	 * @return the range of tahuns
	 */
	@Override
	public java.util.List<com.astra.dewa.model.Tahun> getTahuns(
		int start, int end) {

		return _tahunLocalService.getTahuns(start, end);
	}

	/**
	 * Returns the number of tahuns.
	 *
	 * @return the number of tahuns
	 */
	@Override
	public int getTahunsCount() {
		return _tahunLocalService.getTahunsCount();
	}

	/**
	 * Updates the tahun in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TahunLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tahun the tahun
	 * @return the tahun that was updated
	 */
	@Override
	public com.astra.dewa.model.Tahun updateTahun(
		com.astra.dewa.model.Tahun tahun) {

		return _tahunLocalService.updateTahun(tahun);
	}

	@Override
	public TahunLocalService getWrappedService() {
		return _tahunLocalService;
	}

	@Override
	public void setWrappedService(TahunLocalService tahunLocalService) {
		_tahunLocalService = tahunLocalService;
	}

	private TahunLocalService _tahunLocalService;

}