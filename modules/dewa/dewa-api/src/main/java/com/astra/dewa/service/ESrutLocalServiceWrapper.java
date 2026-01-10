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
 * Provides a wrapper for {@link ESrutLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ESrutLocalService
 * @generated
 */
public class ESrutLocalServiceWrapper
	implements ESrutLocalService, ServiceWrapper<ESrutLocalService> {

	public ESrutLocalServiceWrapper() {
		this(null);
	}

	public ESrutLocalServiceWrapper(ESrutLocalService eSrutLocalService) {
		_eSrutLocalService = eSrutLocalService;
	}

	/**
	 * Adds the e srut to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ESrutLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eSrut the e srut
	 * @return the e srut that was added
	 */
	@Override
	public com.astra.dewa.model.ESrut addESrut(
		com.astra.dewa.model.ESrut eSrut) {

		return _eSrutLocalService.addESrut(eSrut);
	}

	/**
	 * Creates a new e srut with the primary key. Does not add the e srut to the database.
	 *
	 * @param Id the primary key for the new e srut
	 * @return the new e srut
	 */
	@Override
	public com.astra.dewa.model.ESrut createESrut(int Id) {
		return _eSrutLocalService.createESrut(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eSrutLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the e srut from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ESrutLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eSrut the e srut
	 * @return the e srut that was removed
	 */
	@Override
	public com.astra.dewa.model.ESrut deleteESrut(
		com.astra.dewa.model.ESrut eSrut) {

		return _eSrutLocalService.deleteESrut(eSrut);
	}

	/**
	 * Deletes the e srut with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ESrutLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the e srut
	 * @return the e srut that was removed
	 * @throws PortalException if a e srut with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ESrut deleteESrut(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eSrutLocalService.deleteESrut(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eSrutLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _eSrutLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _eSrutLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _eSrutLocalService.dynamicQuery();
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

		return _eSrutLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ESrutModelImpl</code>.
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

		return _eSrutLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ESrutModelImpl</code>.
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

		return _eSrutLocalService.dynamicQuery(
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

		return _eSrutLocalService.dynamicQueryCount(dynamicQuery);
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

		return _eSrutLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.ESrut fetchESrut(int Id) {
		return _eSrutLocalService.fetchESrut(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _eSrutLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the e srut with the primary key.
	 *
	 * @param Id the primary key of the e srut
	 * @return the e srut
	 * @throws PortalException if a e srut with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ESrut getESrut(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eSrutLocalService.getESrut(Id);
	}

	/**
	 * Returns a range of all the e sruts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ESrutModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e sruts
	 * @param end the upper bound of the range of e sruts (not inclusive)
	 * @return the range of e sruts
	 */
	@Override
	public java.util.List<com.astra.dewa.model.ESrut> getESruts(
		int start, int end) {

		return _eSrutLocalService.getESruts(start, end);
	}

	/**
	 * Returns the number of e sruts.
	 *
	 * @return the number of e sruts
	 */
	@Override
	public int getESrutsCount() {
		return _eSrutLocalService.getESrutsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _eSrutLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _eSrutLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eSrutLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the e srut in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ESrutLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eSrut the e srut
	 * @return the e srut that was updated
	 */
	@Override
	public com.astra.dewa.model.ESrut updateESrut(
		com.astra.dewa.model.ESrut eSrut) {

		return _eSrutLocalService.updateESrut(eSrut);
	}

	@Override
	public ESrutLocalService getWrappedService() {
		return _eSrutLocalService;
	}

	@Override
	public void setWrappedService(ESrutLocalService eSrutLocalService) {
		_eSrutLocalService = eSrutLocalService;
	}

	private ESrutLocalService _eSrutLocalService;

}