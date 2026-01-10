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
 * Provides a wrapper for {@link DiskonOtherLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DiskonOtherLocalService
 * @generated
 */
public class DiskonOtherLocalServiceWrapper
	implements DiskonOtherLocalService,
			   ServiceWrapper<DiskonOtherLocalService> {

	public DiskonOtherLocalServiceWrapper() {
		this(null);
	}

	public DiskonOtherLocalServiceWrapper(
		DiskonOtherLocalService diskonOtherLocalService) {

		_diskonOtherLocalService = diskonOtherLocalService;
	}

	/**
	 * Adds the diskon other to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonOtherLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonOther the diskon other
	 * @return the diskon other that was added
	 */
	@Override
	public com.astra.dewa.model.DiskonOther addDiskonOther(
		com.astra.dewa.model.DiskonOther diskonOther) {

		return _diskonOtherLocalService.addDiskonOther(diskonOther);
	}

	/**
	 * Creates a new diskon other with the primary key. Does not add the diskon other to the database.
	 *
	 * @param diskonOtherId the primary key for the new diskon other
	 * @return the new diskon other
	 */
	@Override
	public com.astra.dewa.model.DiskonOther createDiskonOther(
		long diskonOtherId) {

		return _diskonOtherLocalService.createDiskonOther(diskonOtherId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonOtherLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the diskon other from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonOtherLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonOther the diskon other
	 * @return the diskon other that was removed
	 */
	@Override
	public com.astra.dewa.model.DiskonOther deleteDiskonOther(
		com.astra.dewa.model.DiskonOther diskonOther) {

		return _diskonOtherLocalService.deleteDiskonOther(diskonOther);
	}

	/**
	 * Deletes the diskon other with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonOtherLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonOtherId the primary key of the diskon other
	 * @return the diskon other that was removed
	 * @throws PortalException if a diskon other with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.DiskonOther deleteDiskonOther(
			long diskonOtherId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonOtherLocalService.deleteDiskonOther(diskonOtherId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonOtherLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _diskonOtherLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _diskonOtherLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _diskonOtherLocalService.dynamicQuery();
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

		return _diskonOtherLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonOtherModelImpl</code>.
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

		return _diskonOtherLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonOtherModelImpl</code>.
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

		return _diskonOtherLocalService.dynamicQuery(
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

		return _diskonOtherLocalService.dynamicQueryCount(dynamicQuery);
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

		return _diskonOtherLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.DiskonOther fetchDiskonOther(
		long diskonOtherId) {

		return _diskonOtherLocalService.fetchDiskonOther(diskonOtherId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _diskonOtherLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the diskon other with the primary key.
	 *
	 * @param diskonOtherId the primary key of the diskon other
	 * @return the diskon other
	 * @throws PortalException if a diskon other with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.DiskonOther getDiskonOther(long diskonOtherId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonOtherLocalService.getDiskonOther(diskonOtherId);
	}

	/**
	 * Returns a range of all the diskon others.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.DiskonOtherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon others
	 * @param end the upper bound of the range of diskon others (not inclusive)
	 * @return the range of diskon others
	 */
	@Override
	public java.util.List<com.astra.dewa.model.DiskonOther> getDiskonOthers(
		int start, int end) {

		return _diskonOtherLocalService.getDiskonOthers(start, end);
	}

	/**
	 * Returns the number of diskon others.
	 *
	 * @return the number of diskon others
	 */
	@Override
	public int getDiskonOthersCount() {
		return _diskonOtherLocalService.getDiskonOthersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _diskonOtherLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _diskonOtherLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _diskonOtherLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the diskon other in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DiskonOtherLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param diskonOther the diskon other
	 * @return the diskon other that was updated
	 */
	@Override
	public com.astra.dewa.model.DiskonOther updateDiskonOther(
		com.astra.dewa.model.DiskonOther diskonOther) {

		return _diskonOtherLocalService.updateDiskonOther(diskonOther);
	}

	@Override
	public DiskonOtherLocalService getWrappedService() {
		return _diskonOtherLocalService;
	}

	@Override
	public void setWrappedService(
		DiskonOtherLocalService diskonOtherLocalService) {

		_diskonOtherLocalService = diskonOtherLocalService;
	}

	private DiskonOtherLocalService _diskonOtherLocalService;

}