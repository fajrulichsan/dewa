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
 * Provides a wrapper for {@link SalesReportLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SalesReportLocalService
 * @generated
 */
public class SalesReportLocalServiceWrapper
	implements SalesReportLocalService,
			   ServiceWrapper<SalesReportLocalService> {

	public SalesReportLocalServiceWrapper() {
		this(null);
	}

	public SalesReportLocalServiceWrapper(
		SalesReportLocalService salesReportLocalService) {

		_salesReportLocalService = salesReportLocalService;
	}

	/**
	 * Adds the sales report to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SalesReportLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param salesReport the sales report
	 * @return the sales report that was added
	 */
	@Override
	public com.astra.dewa.model.SalesReport addSalesReport(
		com.astra.dewa.model.SalesReport salesReport) {

		return _salesReportLocalService.addSalesReport(salesReport);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _salesReportLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new sales report with the primary key. Does not add the sales report to the database.
	 *
	 * @param Id the primary key for the new sales report
	 * @return the new sales report
	 */
	@Override
	public com.astra.dewa.model.SalesReport createSalesReport(int Id) {
		return _salesReportLocalService.createSalesReport(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _salesReportLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the sales report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SalesReportLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the sales report
	 * @return the sales report that was removed
	 * @throws PortalException if a sales report with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.SalesReport deleteSalesReport(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _salesReportLocalService.deleteSalesReport(Id);
	}

	/**
	 * Deletes the sales report from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SalesReportLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param salesReport the sales report
	 * @return the sales report that was removed
	 */
	@Override
	public com.astra.dewa.model.SalesReport deleteSalesReport(
		com.astra.dewa.model.SalesReport salesReport) {

		return _salesReportLocalService.deleteSalesReport(salesReport);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _salesReportLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _salesReportLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _salesReportLocalService.dynamicQuery();
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

		return _salesReportLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.SalesReportModelImpl</code>.
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

		return _salesReportLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.SalesReportModelImpl</code>.
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

		return _salesReportLocalService.dynamicQuery(
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

		return _salesReportLocalService.dynamicQueryCount(dynamicQuery);
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

		return _salesReportLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.SalesReport fetchSalesReport(int Id) {
		return _salesReportLocalService.fetchSalesReport(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _salesReportLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _salesReportLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _salesReportLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _salesReportLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the sales report with the primary key.
	 *
	 * @param Id the primary key of the sales report
	 * @return the sales report
	 * @throws PortalException if a sales report with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.SalesReport getSalesReport(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _salesReportLocalService.getSalesReport(Id);
	}

	/**
	 * Returns a range of all the sales reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.SalesReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sales reports
	 * @param end the upper bound of the range of sales reports (not inclusive)
	 * @return the range of sales reports
	 */
	@Override
	public java.util.List<com.astra.dewa.model.SalesReport> getSalesReports(
		int start, int end) {

		return _salesReportLocalService.getSalesReports(start, end);
	}

	/**
	 * Returns the number of sales reports.
	 *
	 * @return the number of sales reports
	 */
	@Override
	public int getSalesReportsCount() {
		return _salesReportLocalService.getSalesReportsCount();
	}

	/**
	 * Updates the sales report in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SalesReportLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param salesReport the sales report
	 * @return the sales report that was updated
	 */
	@Override
	public com.astra.dewa.model.SalesReport updateSalesReport(
		com.astra.dewa.model.SalesReport salesReport) {

		return _salesReportLocalService.updateSalesReport(salesReport);
	}

	@Override
	public SalesReportLocalService getWrappedService() {
		return _salesReportLocalService;
	}

	@Override
	public void setWrappedService(
		SalesReportLocalService salesReportLocalService) {

		_salesReportLocalService = salesReportLocalService;
	}

	private SalesReportLocalService _salesReportLocalService;

}