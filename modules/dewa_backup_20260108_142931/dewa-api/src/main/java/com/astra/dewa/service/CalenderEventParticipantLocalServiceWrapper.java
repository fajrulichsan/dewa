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
 * Provides a wrapper for {@link CalenderEventParticipantLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CalenderEventParticipantLocalService
 * @generated
 */
public class CalenderEventParticipantLocalServiceWrapper
	implements CalenderEventParticipantLocalService,
			   ServiceWrapper<CalenderEventParticipantLocalService> {

	public CalenderEventParticipantLocalServiceWrapper() {
		this(null);
	}

	public CalenderEventParticipantLocalServiceWrapper(
		CalenderEventParticipantLocalService
			calenderEventParticipantLocalService) {

		_calenderEventParticipantLocalService =
			calenderEventParticipantLocalService;
	}

	/**
	 * Adds the calender event participant to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalenderEventParticipantLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calenderEventParticipant the calender event participant
	 * @return the calender event participant that was added
	 */
	@Override
	public com.astra.dewa.model.CalenderEventParticipant
		addCalenderEventParticipant(
			com.astra.dewa.model.CalenderEventParticipant
				calenderEventParticipant) {

		return _calenderEventParticipantLocalService.
			addCalenderEventParticipant(calenderEventParticipant);
	}

	/**
	 * Creates a new calender event participant with the primary key. Does not add the calender event participant to the database.
	 *
	 * @param Id the primary key for the new calender event participant
	 * @return the new calender event participant
	 */
	@Override
	public com.astra.dewa.model.CalenderEventParticipant
		createCalenderEventParticipant(int Id) {

		return _calenderEventParticipantLocalService.
			createCalenderEventParticipant(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _calenderEventParticipantLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the calender event participant from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalenderEventParticipantLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calenderEventParticipant the calender event participant
	 * @return the calender event participant that was removed
	 */
	@Override
	public com.astra.dewa.model.CalenderEventParticipant
		deleteCalenderEventParticipant(
			com.astra.dewa.model.CalenderEventParticipant
				calenderEventParticipant) {

		return _calenderEventParticipantLocalService.
			deleteCalenderEventParticipant(calenderEventParticipant);
	}

	/**
	 * Deletes the calender event participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalenderEventParticipantLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the calender event participant
	 * @return the calender event participant that was removed
	 * @throws PortalException if a calender event participant with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.CalenderEventParticipant
			deleteCalenderEventParticipant(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _calenderEventParticipantLocalService.
			deleteCalenderEventParticipant(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _calenderEventParticipantLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _calenderEventParticipantLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _calenderEventParticipantLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _calenderEventParticipantLocalService.dynamicQuery();
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

		return _calenderEventParticipantLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.CalenderEventParticipantModelImpl</code>.
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

		return _calenderEventParticipantLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.CalenderEventParticipantModelImpl</code>.
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

		return _calenderEventParticipantLocalService.dynamicQuery(
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

		return _calenderEventParticipantLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _calenderEventParticipantLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.CalenderEventParticipant
		fetchCalenderEventParticipant(int Id) {

		return _calenderEventParticipantLocalService.
			fetchCalenderEventParticipant(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _calenderEventParticipantLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns the calender event participant with the primary key.
	 *
	 * @param Id the primary key of the calender event participant
	 * @return the calender event participant
	 * @throws PortalException if a calender event participant with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.CalenderEventParticipant
			getCalenderEventParticipant(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _calenderEventParticipantLocalService.
			getCalenderEventParticipant(Id);
	}

	/**
	 * Returns a range of all the calender event participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.CalenderEventParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender event participants
	 * @param end the upper bound of the range of calender event participants (not inclusive)
	 * @return the range of calender event participants
	 */
	@Override
	public java.util.List<com.astra.dewa.model.CalenderEventParticipant>
		getCalenderEventParticipants(int start, int end) {

		return _calenderEventParticipantLocalService.
			getCalenderEventParticipants(start, end);
	}

	/**
	 * Returns the number of calender event participants.
	 *
	 * @return the number of calender event participants
	 */
	@Override
	public int getCalenderEventParticipantsCount() {
		return _calenderEventParticipantLocalService.
			getCalenderEventParticipantsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _calenderEventParticipantLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _calenderEventParticipantLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _calenderEventParticipantLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the calender event participant in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalenderEventParticipantLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calenderEventParticipant the calender event participant
	 * @return the calender event participant that was updated
	 */
	@Override
	public com.astra.dewa.model.CalenderEventParticipant
		updateCalenderEventParticipant(
			com.astra.dewa.model.CalenderEventParticipant
				calenderEventParticipant) {

		return _calenderEventParticipantLocalService.
			updateCalenderEventParticipant(calenderEventParticipant);
	}

	@Override
	public CalenderEventParticipantLocalService getWrappedService() {
		return _calenderEventParticipantLocalService;
	}

	@Override
	public void setWrappedService(
		CalenderEventParticipantLocalService
			calenderEventParticipantLocalService) {

		_calenderEventParticipantLocalService =
			calenderEventParticipantLocalService;
	}

	private CalenderEventParticipantLocalService
		_calenderEventParticipantLocalService;

}