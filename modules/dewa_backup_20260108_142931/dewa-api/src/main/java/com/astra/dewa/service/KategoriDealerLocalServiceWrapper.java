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
 * Provides a wrapper for {@link KategoriDealerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KategoriDealerLocalService
 * @generated
 */
public class KategoriDealerLocalServiceWrapper
	implements KategoriDealerLocalService,
			   ServiceWrapper<KategoriDealerLocalService> {

	public KategoriDealerLocalServiceWrapper() {
		this(null);
	}

	public KategoriDealerLocalServiceWrapper(
		KategoriDealerLocalService kategoriDealerLocalService) {

		_kategoriDealerLocalService = kategoriDealerLocalService;
	}

	/**
	 * Adds the kategori dealer to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KategoriDealerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kategoriDealer the kategori dealer
	 * @return the kategori dealer that was added
	 */
	@Override
	public com.astra.dewa.model.KategoriDealer addKategoriDealer(
		com.astra.dewa.model.KategoriDealer kategoriDealer) {

		return _kategoriDealerLocalService.addKategoriDealer(kategoriDealer);
	}

	/**
	 * Creates a new kategori dealer with the primary key. Does not add the kategori dealer to the database.
	 *
	 * @param Id the primary key for the new kategori dealer
	 * @return the new kategori dealer
	 */
	@Override
	public com.astra.dewa.model.KategoriDealer createKategoriDealer(long Id) {
		return _kategoriDealerLocalService.createKategoriDealer(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kategoriDealerLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the kategori dealer from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KategoriDealerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kategoriDealer the kategori dealer
	 * @return the kategori dealer that was removed
	 */
	@Override
	public com.astra.dewa.model.KategoriDealer deleteKategoriDealer(
		com.astra.dewa.model.KategoriDealer kategoriDealer) {

		return _kategoriDealerLocalService.deleteKategoriDealer(kategoriDealer);
	}

	/**
	 * Deletes the kategori dealer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KategoriDealerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the kategori dealer
	 * @return the kategori dealer that was removed
	 * @throws PortalException if a kategori dealer with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.KategoriDealer deleteKategoriDealer(long Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kategoriDealerLocalService.deleteKategoriDealer(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kategoriDealerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _kategoriDealerLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _kategoriDealerLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kategoriDealerLocalService.dynamicQuery();
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

		return _kategoriDealerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.KategoriDealerModelImpl</code>.
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

		return _kategoriDealerLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.KategoriDealerModelImpl</code>.
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

		return _kategoriDealerLocalService.dynamicQuery(
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

		return _kategoriDealerLocalService.dynamicQueryCount(dynamicQuery);
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

		return _kategoriDealerLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.KategoriDealer fetchKategoriDealer(long Id) {
		return _kategoriDealerLocalService.fetchKategoriDealer(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _kategoriDealerLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _kategoriDealerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the kategori dealer with the primary key.
	 *
	 * @param Id the primary key of the kategori dealer
	 * @return the kategori dealer
	 * @throws PortalException if a kategori dealer with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.KategoriDealer getKategoriDealer(long Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kategoriDealerLocalService.getKategoriDealer(Id);
	}

	/**
	 * Returns a range of all the kategori dealers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.KategoriDealerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kategori dealers
	 * @param end the upper bound of the range of kategori dealers (not inclusive)
	 * @return the range of kategori dealers
	 */
	@Override
	public java.util.List<com.astra.dewa.model.KategoriDealer>
		getKategoriDealers(int start, int end) {

		return _kategoriDealerLocalService.getKategoriDealers(start, end);
	}

	/**
	 * Returns the number of kategori dealers.
	 *
	 * @return the number of kategori dealers
	 */
	@Override
	public int getKategoriDealersCount() {
		return _kategoriDealerLocalService.getKategoriDealersCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _kategoriDealerLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kategoriDealerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the kategori dealer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KategoriDealerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kategoriDealer the kategori dealer
	 * @return the kategori dealer that was updated
	 */
	@Override
	public com.astra.dewa.model.KategoriDealer updateKategoriDealer(
		com.astra.dewa.model.KategoriDealer kategoriDealer) {

		return _kategoriDealerLocalService.updateKategoriDealer(kategoriDealer);
	}

	@Override
	public KategoriDealerLocalService getWrappedService() {
		return _kategoriDealerLocalService;
	}

	@Override
	public void setWrappedService(
		KategoriDealerLocalService kategoriDealerLocalService) {

		_kategoriDealerLocalService = kategoriDealerLocalService;
	}

	private KategoriDealerLocalService _kategoriDealerLocalService;

}