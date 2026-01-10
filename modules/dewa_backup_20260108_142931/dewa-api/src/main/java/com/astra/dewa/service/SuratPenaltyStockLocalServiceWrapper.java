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
 * Provides a wrapper for {@link SuratPenaltyStockLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SuratPenaltyStockLocalService
 * @generated
 */
public class SuratPenaltyStockLocalServiceWrapper
	implements ServiceWrapper<SuratPenaltyStockLocalService>,
			   SuratPenaltyStockLocalService {

	public SuratPenaltyStockLocalServiceWrapper() {
		this(null);
	}

	public SuratPenaltyStockLocalServiceWrapper(
		SuratPenaltyStockLocalService suratPenaltyStockLocalService) {

		_suratPenaltyStockLocalService = suratPenaltyStockLocalService;
	}

	/**
	 * Adds the surat penalty stock to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SuratPenaltyStockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param suratPenaltyStock the surat penalty stock
	 * @return the surat penalty stock that was added
	 */
	@Override
	public com.astra.dewa.model.SuratPenaltyStock addSuratPenaltyStock(
		com.astra.dewa.model.SuratPenaltyStock suratPenaltyStock) {

		return _suratPenaltyStockLocalService.addSuratPenaltyStock(
			suratPenaltyStock);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _suratPenaltyStockLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new surat penalty stock with the primary key. Does not add the surat penalty stock to the database.
	 *
	 * @param Id the primary key for the new surat penalty stock
	 * @return the new surat penalty stock
	 */
	@Override
	public com.astra.dewa.model.SuratPenaltyStock createSuratPenaltyStock(
		long Id) {

		return _suratPenaltyStockLocalService.createSuratPenaltyStock(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _suratPenaltyStockLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the surat penalty stock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SuratPenaltyStockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the surat penalty stock
	 * @return the surat penalty stock that was removed
	 * @throws PortalException if a surat penalty stock with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.SuratPenaltyStock deleteSuratPenaltyStock(
			long Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _suratPenaltyStockLocalService.deleteSuratPenaltyStock(Id);
	}

	/**
	 * Deletes the surat penalty stock from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SuratPenaltyStockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param suratPenaltyStock the surat penalty stock
	 * @return the surat penalty stock that was removed
	 */
	@Override
	public com.astra.dewa.model.SuratPenaltyStock deleteSuratPenaltyStock(
		com.astra.dewa.model.SuratPenaltyStock suratPenaltyStock) {

		return _suratPenaltyStockLocalService.deleteSuratPenaltyStock(
			suratPenaltyStock);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _suratPenaltyStockLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _suratPenaltyStockLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _suratPenaltyStockLocalService.dynamicQuery();
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

		return _suratPenaltyStockLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.SuratPenaltyStockModelImpl</code>.
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

		return _suratPenaltyStockLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.SuratPenaltyStockModelImpl</code>.
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

		return _suratPenaltyStockLocalService.dynamicQuery(
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

		return _suratPenaltyStockLocalService.dynamicQueryCount(dynamicQuery);
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

		return _suratPenaltyStockLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.SuratPenaltyStock fetchSuratPenaltyStock(
		long Id) {

		return _suratPenaltyStockLocalService.fetchSuratPenaltyStock(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _suratPenaltyStockLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _suratPenaltyStockLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _suratPenaltyStockLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _suratPenaltyStockLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the surat penalty stock with the primary key.
	 *
	 * @param Id the primary key of the surat penalty stock
	 * @return the surat penalty stock
	 * @throws PortalException if a surat penalty stock with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.SuratPenaltyStock getSuratPenaltyStock(long Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _suratPenaltyStockLocalService.getSuratPenaltyStock(Id);
	}

	/**
	 * Returns a range of all the surat penalty stocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.SuratPenaltyStockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of surat penalty stocks
	 * @param end the upper bound of the range of surat penalty stocks (not inclusive)
	 * @return the range of surat penalty stocks
	 */
	@Override
	public java.util.List<com.astra.dewa.model.SuratPenaltyStock>
		getSuratPenaltyStocks(int start, int end) {

		return _suratPenaltyStockLocalService.getSuratPenaltyStocks(start, end);
	}

	/**
	 * Returns the number of surat penalty stocks.
	 *
	 * @return the number of surat penalty stocks
	 */
	@Override
	public int getSuratPenaltyStocksCount() {
		return _suratPenaltyStockLocalService.getSuratPenaltyStocksCount();
	}

	/**
	 * Updates the surat penalty stock in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SuratPenaltyStockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param suratPenaltyStock the surat penalty stock
	 * @return the surat penalty stock that was updated
	 */
	@Override
	public com.astra.dewa.model.SuratPenaltyStock updateSuratPenaltyStock(
		com.astra.dewa.model.SuratPenaltyStock suratPenaltyStock) {

		return _suratPenaltyStockLocalService.updateSuratPenaltyStock(
			suratPenaltyStock);
	}

	@Override
	public SuratPenaltyStockLocalService getWrappedService() {
		return _suratPenaltyStockLocalService;
	}

	@Override
	public void setWrappedService(
		SuratPenaltyStockLocalService suratPenaltyStockLocalService) {

		_suratPenaltyStockLocalService = suratPenaltyStockLocalService;
	}

	private SuratPenaltyStockLocalService _suratPenaltyStockLocalService;

}