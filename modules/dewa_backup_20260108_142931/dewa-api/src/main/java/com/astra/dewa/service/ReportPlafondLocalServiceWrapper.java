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
 * Provides a wrapper for {@link ReportPlafondLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReportPlafondLocalService
 * @generated
 */
public class ReportPlafondLocalServiceWrapper
	implements ReportPlafondLocalService,
			   ServiceWrapper<ReportPlafondLocalService> {

	public ReportPlafondLocalServiceWrapper() {
		this(null);
	}

	public ReportPlafondLocalServiceWrapper(
		ReportPlafondLocalService reportPlafondLocalService) {

		_reportPlafondLocalService = reportPlafondLocalService;
	}

	/**
	 * Adds the report plafond to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReportPlafondLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reportPlafond the report plafond
	 * @return the report plafond that was added
	 */
	@Override
	public com.astra.dewa.model.ReportPlafond addReportPlafond(
		com.astra.dewa.model.ReportPlafond reportPlafond) {

		return _reportPlafondLocalService.addReportPlafond(reportPlafond);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reportPlafondLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new report plafond with the primary key. Does not add the report plafond to the database.
	 *
	 * @param Id the primary key for the new report plafond
	 * @return the new report plafond
	 */
	@Override
	public com.astra.dewa.model.ReportPlafond createReportPlafond(int Id) {
		return _reportPlafondLocalService.createReportPlafond(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reportPlafondLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the report plafond with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReportPlafondLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the report plafond
	 * @return the report plafond that was removed
	 * @throws PortalException if a report plafond with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ReportPlafond deleteReportPlafond(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reportPlafondLocalService.deleteReportPlafond(Id);
	}

	/**
	 * Deletes the report plafond from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReportPlafondLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reportPlafond the report plafond
	 * @return the report plafond that was removed
	 */
	@Override
	public com.astra.dewa.model.ReportPlafond deleteReportPlafond(
		com.astra.dewa.model.ReportPlafond reportPlafond) {

		return _reportPlafondLocalService.deleteReportPlafond(reportPlafond);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _reportPlafondLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _reportPlafondLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _reportPlafondLocalService.dynamicQuery();
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

		return _reportPlafondLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ReportPlafondModelImpl</code>.
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

		return _reportPlafondLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ReportPlafondModelImpl</code>.
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

		return _reportPlafondLocalService.dynamicQuery(
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

		return _reportPlafondLocalService.dynamicQueryCount(dynamicQuery);
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

		return _reportPlafondLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.ReportPlafond fetchReportPlafond(int Id) {
		return _reportPlafondLocalService.fetchReportPlafond(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _reportPlafondLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _reportPlafondLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _reportPlafondLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reportPlafondLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the report plafond with the primary key.
	 *
	 * @param Id the primary key of the report plafond
	 * @return the report plafond
	 * @throws PortalException if a report plafond with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ReportPlafond getReportPlafond(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reportPlafondLocalService.getReportPlafond(Id);
	}

	/**
	 * Returns a range of all the report plafonds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ReportPlafondModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of report plafonds
	 * @param end the upper bound of the range of report plafonds (not inclusive)
	 * @return the range of report plafonds
	 */
	@Override
	public java.util.List<com.astra.dewa.model.ReportPlafond> getReportPlafonds(
		int start, int end) {

		return _reportPlafondLocalService.getReportPlafonds(start, end);
	}

	/**
	 * Returns the number of report plafonds.
	 *
	 * @return the number of report plafonds
	 */
	@Override
	public int getReportPlafondsCount() {
		return _reportPlafondLocalService.getReportPlafondsCount();
	}

	/**
	 * Updates the report plafond in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReportPlafondLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reportPlafond the report plafond
	 * @return the report plafond that was updated
	 */
	@Override
	public com.astra.dewa.model.ReportPlafond updateReportPlafond(
		com.astra.dewa.model.ReportPlafond reportPlafond) {

		return _reportPlafondLocalService.updateReportPlafond(reportPlafond);
	}

	@Override
	public ReportPlafondLocalService getWrappedService() {
		return _reportPlafondLocalService;
	}

	@Override
	public void setWrappedService(
		ReportPlafondLocalService reportPlafondLocalService) {

		_reportPlafondLocalService = reportPlafondLocalService;
	}

	private ReportPlafondLocalService _reportPlafondLocalService;

}