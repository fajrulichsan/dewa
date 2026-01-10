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
 * Provides a wrapper for {@link WilayahLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see WilayahLocalService
 * @generated
 */
public class WilayahLocalServiceWrapper
	implements ServiceWrapper<WilayahLocalService>, WilayahLocalService {

	public WilayahLocalServiceWrapper() {
		this(null);
	}

	public WilayahLocalServiceWrapper(WilayahLocalService wilayahLocalService) {
		_wilayahLocalService = wilayahLocalService;
	}

	/**
	 * Adds the wilayah to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WilayahLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wilayah the wilayah
	 * @return the wilayah that was added
	 */
	@Override
	public com.astra.dewa.model.Wilayah addWilayah(
		com.astra.dewa.model.Wilayah wilayah) {

		return _wilayahLocalService.addWilayah(wilayah);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _wilayahLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new wilayah with the primary key. Does not add the wilayah to the database.
	 *
	 * @param Id the primary key for the new wilayah
	 * @return the new wilayah
	 */
	@Override
	public com.astra.dewa.model.Wilayah createWilayah(int Id) {
		return _wilayahLocalService.createWilayah(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _wilayahLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the wilayah with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WilayahLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the wilayah
	 * @return the wilayah that was removed
	 * @throws PortalException if a wilayah with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.Wilayah deleteWilayah(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _wilayahLocalService.deleteWilayah(Id);
	}

	/**
	 * Deletes the wilayah from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WilayahLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wilayah the wilayah
	 * @return the wilayah that was removed
	 */
	@Override
	public com.astra.dewa.model.Wilayah deleteWilayah(
		com.astra.dewa.model.Wilayah wilayah) {

		return _wilayahLocalService.deleteWilayah(wilayah);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _wilayahLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _wilayahLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _wilayahLocalService.dynamicQuery();
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

		return _wilayahLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.WilayahModelImpl</code>.
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

		return _wilayahLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.WilayahModelImpl</code>.
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

		return _wilayahLocalService.dynamicQuery(
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

		return _wilayahLocalService.dynamicQueryCount(dynamicQuery);
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

		return _wilayahLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.Wilayah fetchWilayah(int Id) {
		return _wilayahLocalService.fetchWilayah(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _wilayahLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _wilayahLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _wilayahLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _wilayahLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the wilayah with the primary key.
	 *
	 * @param Id the primary key of the wilayah
	 * @return the wilayah
	 * @throws PortalException if a wilayah with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.Wilayah getWilayah(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _wilayahLocalService.getWilayah(Id);
	}

	/**
	 * Returns a range of all the wilayahs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.WilayahModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wilayahs
	 * @param end the upper bound of the range of wilayahs (not inclusive)
	 * @return the range of wilayahs
	 */
	@Override
	public java.util.List<com.astra.dewa.model.Wilayah> getWilayahs(
		int start, int end) {

		return _wilayahLocalService.getWilayahs(start, end);
	}

	/**
	 * Returns the number of wilayahs.
	 *
	 * @return the number of wilayahs
	 */
	@Override
	public int getWilayahsCount() {
		return _wilayahLocalService.getWilayahsCount();
	}

	/**
	 * Updates the wilayah in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WilayahLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wilayah the wilayah
	 * @return the wilayah that was updated
	 */
	@Override
	public com.astra.dewa.model.Wilayah updateWilayah(
		com.astra.dewa.model.Wilayah wilayah) {

		return _wilayahLocalService.updateWilayah(wilayah);
	}

	@Override
	public WilayahLocalService getWrappedService() {
		return _wilayahLocalService;
	}

	@Override
	public void setWrappedService(WilayahLocalService wilayahLocalService) {
		_wilayahLocalService = wilayahLocalService;
	}

	private WilayahLocalService _wilayahLocalService;

}