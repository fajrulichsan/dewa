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
 * Provides a wrapper for {@link BulanLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BulanLocalService
 * @generated
 */
public class BulanLocalServiceWrapper
	implements BulanLocalService, ServiceWrapper<BulanLocalService> {

	public BulanLocalServiceWrapper() {
		this(null);
	}

	public BulanLocalServiceWrapper(BulanLocalService bulanLocalService) {
		_bulanLocalService = bulanLocalService;
	}

	/**
	 * Adds the bulan to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BulanLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param bulan the bulan
	 * @return the bulan that was added
	 */
	@Override
	public com.astra.dewa.model.Bulan addBulan(
		com.astra.dewa.model.Bulan bulan) {

		return _bulanLocalService.addBulan(bulan);
	}

	/**
	 * Creates a new bulan with the primary key. Does not add the bulan to the database.
	 *
	 * @param Id the primary key for the new bulan
	 * @return the new bulan
	 */
	@Override
	public com.astra.dewa.model.Bulan createBulan(String Id) {
		return _bulanLocalService.createBulan(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bulanLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the bulan from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BulanLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param bulan the bulan
	 * @return the bulan that was removed
	 */
	@Override
	public com.astra.dewa.model.Bulan deleteBulan(
		com.astra.dewa.model.Bulan bulan) {

		return _bulanLocalService.deleteBulan(bulan);
	}

	/**
	 * Deletes the bulan with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BulanLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the bulan
	 * @return the bulan that was removed
	 * @throws PortalException if a bulan with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.Bulan deleteBulan(String Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bulanLocalService.deleteBulan(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bulanLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _bulanLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _bulanLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _bulanLocalService.dynamicQuery();
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

		return _bulanLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.BulanModelImpl</code>.
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

		return _bulanLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.BulanModelImpl</code>.
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

		return _bulanLocalService.dynamicQuery(
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

		return _bulanLocalService.dynamicQueryCount(dynamicQuery);
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

		return _bulanLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.Bulan fetchBulan(String Id) {
		return _bulanLocalService.fetchBulan(Id);
	}

	/**
	 * Returns the bulan with the primary key.
	 *
	 * @param Id the primary key of the bulan
	 * @return the bulan
	 * @throws PortalException if a bulan with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.Bulan getBulan(String Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bulanLocalService.getBulan(Id);
	}

	/**
	 * Returns a range of all the bulans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.BulanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bulans
	 * @param end the upper bound of the range of bulans (not inclusive)
	 * @return the range of bulans
	 */
	@Override
	public java.util.List<com.astra.dewa.model.Bulan> getBulans(
		int start, int end) {

		return _bulanLocalService.getBulans(start, end);
	}

	/**
	 * Returns the number of bulans.
	 *
	 * @return the number of bulans
	 */
	@Override
	public int getBulansCount() {
		return _bulanLocalService.getBulansCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _bulanLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bulanLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the bulan in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BulanLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param bulan the bulan
	 * @return the bulan that was updated
	 */
	@Override
	public com.astra.dewa.model.Bulan updateBulan(
		com.astra.dewa.model.Bulan bulan) {

		return _bulanLocalService.updateBulan(bulan);
	}

	@Override
	public BulanLocalService getWrappedService() {
		return _bulanLocalService;
	}

	@Override
	public void setWrappedService(BulanLocalService bulanLocalService) {
		_bulanLocalService = bulanLocalService;
	}

	private BulanLocalService _bulanLocalService;

}