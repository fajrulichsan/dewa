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
 * Provides a wrapper for {@link JenisMateriLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see JenisMateriLocalService
 * @generated
 */
public class JenisMateriLocalServiceWrapper
	implements JenisMateriLocalService,
			   ServiceWrapper<JenisMateriLocalService> {

	public JenisMateriLocalServiceWrapper() {
		this(null);
	}

	public JenisMateriLocalServiceWrapper(
		JenisMateriLocalService jenisMateriLocalService) {

		_jenisMateriLocalService = jenisMateriLocalService;
	}

	/**
	 * Adds the jenis materi to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect JenisMateriLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param jenisMateri the jenis materi
	 * @return the jenis materi that was added
	 */
	@Override
	public com.astra.dewa.model.JenisMateri addJenisMateri(
		com.astra.dewa.model.JenisMateri jenisMateri) {

		return _jenisMateriLocalService.addJenisMateri(jenisMateri);
	}

	/**
	 * Creates a new jenis materi with the primary key. Does not add the jenis materi to the database.
	 *
	 * @param Id the primary key for the new jenis materi
	 * @return the new jenis materi
	 */
	@Override
	public com.astra.dewa.model.JenisMateri createJenisMateri(int Id) {
		return _jenisMateriLocalService.createJenisMateri(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jenisMateriLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the jenis materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect JenisMateriLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the jenis materi
	 * @return the jenis materi that was removed
	 * @throws PortalException if a jenis materi with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.JenisMateri deleteJenisMateri(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jenisMateriLocalService.deleteJenisMateri(Id);
	}

	/**
	 * Deletes the jenis materi from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect JenisMateriLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param jenisMateri the jenis materi
	 * @return the jenis materi that was removed
	 */
	@Override
	public com.astra.dewa.model.JenisMateri deleteJenisMateri(
		com.astra.dewa.model.JenisMateri jenisMateri) {

		return _jenisMateriLocalService.deleteJenisMateri(jenisMateri);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jenisMateriLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _jenisMateriLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _jenisMateriLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _jenisMateriLocalService.dynamicQuery();
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

		return _jenisMateriLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.JenisMateriModelImpl</code>.
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

		return _jenisMateriLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.JenisMateriModelImpl</code>.
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

		return _jenisMateriLocalService.dynamicQuery(
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

		return _jenisMateriLocalService.dynamicQueryCount(dynamicQuery);
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

		return _jenisMateriLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.JenisMateri fetchJenisMateri(int Id) {
		return _jenisMateriLocalService.fetchJenisMateri(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _jenisMateriLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _jenisMateriLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the jenis materi with the primary key.
	 *
	 * @param Id the primary key of the jenis materi
	 * @return the jenis materi
	 * @throws PortalException if a jenis materi with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.JenisMateri getJenisMateri(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jenisMateriLocalService.getJenisMateri(Id);
	}

	/**
	 * Returns a range of all the jenis materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.JenisMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jenis materis
	 * @param end the upper bound of the range of jenis materis (not inclusive)
	 * @return the range of jenis materis
	 */
	@Override
	public java.util.List<com.astra.dewa.model.JenisMateri> getJenisMateris(
		int start, int end) {

		return _jenisMateriLocalService.getJenisMateris(start, end);
	}

	/**
	 * Returns the number of jenis materis.
	 *
	 * @return the number of jenis materis
	 */
	@Override
	public int getJenisMaterisCount() {
		return _jenisMateriLocalService.getJenisMaterisCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _jenisMateriLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jenisMateriLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the jenis materi in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect JenisMateriLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param jenisMateri the jenis materi
	 * @return the jenis materi that was updated
	 */
	@Override
	public com.astra.dewa.model.JenisMateri updateJenisMateri(
		com.astra.dewa.model.JenisMateri jenisMateri) {

		return _jenisMateriLocalService.updateJenisMateri(jenisMateri);
	}

	@Override
	public JenisMateriLocalService getWrappedService() {
		return _jenisMateriLocalService;
	}

	@Override
	public void setWrappedService(
		JenisMateriLocalService jenisMateriLocalService) {

		_jenisMateriLocalService = jenisMateriLocalService;
	}

	private JenisMateriLocalService _jenisMateriLocalService;

}