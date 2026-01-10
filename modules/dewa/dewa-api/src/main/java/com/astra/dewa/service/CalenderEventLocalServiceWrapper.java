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
 * Provides a wrapper for {@link CalenderEventLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CalenderEventLocalService
 * @generated
 */
public class CalenderEventLocalServiceWrapper
	implements CalenderEventLocalService,
			   ServiceWrapper<CalenderEventLocalService> {

	public CalenderEventLocalServiceWrapper() {
		this(null);
	}

	public CalenderEventLocalServiceWrapper(
		CalenderEventLocalService calenderEventLocalService) {

		_calenderEventLocalService = calenderEventLocalService;
	}

	/**
	 * Adds the calender event to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalenderEventLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calenderEvent the calender event
	 * @return the calender event that was added
	 */
	@Override
	public com.astra.dewa.model.CalenderEvent addCalenderEvent(
		com.astra.dewa.model.CalenderEvent calenderEvent) {

		return _calenderEventLocalService.addCalenderEvent(calenderEvent);
	}

	/**
	 * Creates a new calender event with the primary key. Does not add the calender event to the database.
	 *
	 * @param Id the primary key for the new calender event
	 * @return the new calender event
	 */
	@Override
	public com.astra.dewa.model.CalenderEvent createCalenderEvent(int Id) {
		return _calenderEventLocalService.createCalenderEvent(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _calenderEventLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the calender event from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalenderEventLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calenderEvent the calender event
	 * @return the calender event that was removed
	 */
	@Override
	public com.astra.dewa.model.CalenderEvent deleteCalenderEvent(
		com.astra.dewa.model.CalenderEvent calenderEvent) {

		return _calenderEventLocalService.deleteCalenderEvent(calenderEvent);
	}

	/**
	 * Deletes the calender event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalenderEventLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the calender event
	 * @return the calender event that was removed
	 * @throws PortalException if a calender event with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.CalenderEvent deleteCalenderEvent(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _calenderEventLocalService.deleteCalenderEvent(Id);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject
			deleteCalenderEventWithDetails(int id, String screenName)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _calenderEventLocalService.deleteCalenderEventWithDetails(
			id, screenName);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _calenderEventLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _calenderEventLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _calenderEventLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _calenderEventLocalService.dynamicQuery();
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

		return _calenderEventLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.CalenderEventModelImpl</code>.
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

		return _calenderEventLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.CalenderEventModelImpl</code>.
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

		return _calenderEventLocalService.dynamicQuery(
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

		return _calenderEventLocalService.dynamicQueryCount(dynamicQuery);
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

		return _calenderEventLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.CalenderEvent fetchCalenderEvent(int Id) {
		return _calenderEventLocalService.fetchCalenderEvent(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _calenderEventLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the calender event with the primary key.
	 *
	 * @param Id the primary key of the calender event
	 * @return the calender event
	 * @throws PortalException if a calender event with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.CalenderEvent getCalenderEvent(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _calenderEventLocalService.getCalenderEvent(Id);
	}

	/**
	 * Returns a range of all the calender events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.CalenderEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender events
	 * @param end the upper bound of the range of calender events (not inclusive)
	 * @return the range of calender events
	 */
	@Override
	public java.util.List<com.astra.dewa.model.CalenderEvent> getCalenderEvents(
		int start, int end) {

		return _calenderEventLocalService.getCalenderEvents(start, end);
	}

	/**
	 * Returns the number of calender events.
	 *
	 * @return the number of calender events
	 */
	@Override
	public int getCalenderEventsCount() {
		return _calenderEventLocalService.getCalenderEventsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _calenderEventLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _calenderEventLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _calenderEventLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the calender event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalenderEventLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calenderEvent the calender event
	 * @return the calender event that was updated
	 */
	@Override
	public com.astra.dewa.model.CalenderEvent updateCalenderEvent(
		com.astra.dewa.model.CalenderEvent calenderEvent) {

		return _calenderEventLocalService.updateCalenderEvent(calenderEvent);
	}

	@Override
	public CalenderEventLocalService getWrappedService() {
		return _calenderEventLocalService;
	}

	@Override
	public void setWrappedService(
		CalenderEventLocalService calenderEventLocalService) {

		_calenderEventLocalService = calenderEventLocalService;
	}

	private CalenderEventLocalService _calenderEventLocalService;

}