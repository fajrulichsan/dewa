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
 * Provides a wrapper for {@link FakturIndirectLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FakturIndirectLocalService
 * @generated
 */
public class FakturIndirectLocalServiceWrapper
	implements FakturIndirectLocalService,
			   ServiceWrapper<FakturIndirectLocalService> {

	public FakturIndirectLocalServiceWrapper() {
		this(null);
	}

	public FakturIndirectLocalServiceWrapper(
		FakturIndirectLocalService fakturIndirectLocalService) {

		_fakturIndirectLocalService = fakturIndirectLocalService;
	}

	/**
	 * Adds the faktur indirect to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FakturIndirectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fakturIndirect the faktur indirect
	 * @return the faktur indirect that was added
	 */
	@Override
	public com.astra.dewa.model.FakturIndirect addFakturIndirect(
		com.astra.dewa.model.FakturIndirect fakturIndirect) {

		return _fakturIndirectLocalService.addFakturIndirect(fakturIndirect);
	}

	/**
	 * Creates a new faktur indirect with the primary key. Does not add the faktur indirect to the database.
	 *
	 * @param Id the primary key for the new faktur indirect
	 * @return the new faktur indirect
	 */
	@Override
	public com.astra.dewa.model.FakturIndirect createFakturIndirect(int Id) {
		return _fakturIndirectLocalService.createFakturIndirect(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fakturIndirectLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the faktur indirect from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FakturIndirectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fakturIndirect the faktur indirect
	 * @return the faktur indirect that was removed
	 */
	@Override
	public com.astra.dewa.model.FakturIndirect deleteFakturIndirect(
		com.astra.dewa.model.FakturIndirect fakturIndirect) {

		return _fakturIndirectLocalService.deleteFakturIndirect(fakturIndirect);
	}

	/**
	 * Deletes the faktur indirect with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FakturIndirectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the faktur indirect
	 * @return the faktur indirect that was removed
	 * @throws PortalException if a faktur indirect with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.FakturIndirect deleteFakturIndirect(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fakturIndirectLocalService.deleteFakturIndirect(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fakturIndirectLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _fakturIndirectLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _fakturIndirectLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fakturIndirectLocalService.dynamicQuery();
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

		return _fakturIndirectLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.FakturIndirectModelImpl</code>.
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

		return _fakturIndirectLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.FakturIndirectModelImpl</code>.
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

		return _fakturIndirectLocalService.dynamicQuery(
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

		return _fakturIndirectLocalService.dynamicQueryCount(dynamicQuery);
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

		return _fakturIndirectLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.FakturIndirect fetchFakturIndirect(int Id) {
		return _fakturIndirectLocalService.fetchFakturIndirect(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _fakturIndirectLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the faktur indirect with the primary key.
	 *
	 * @param Id the primary key of the faktur indirect
	 * @return the faktur indirect
	 * @throws PortalException if a faktur indirect with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.FakturIndirect getFakturIndirect(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fakturIndirectLocalService.getFakturIndirect(Id);
	}

	/**
	 * Returns a range of all the faktur indirects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.FakturIndirectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur indirects
	 * @param end the upper bound of the range of faktur indirects (not inclusive)
	 * @return the range of faktur indirects
	 */
	@Override
	public java.util.List<com.astra.dewa.model.FakturIndirect>
		getFakturIndirects(int start, int end) {

		return _fakturIndirectLocalService.getFakturIndirects(start, end);
	}

	/**
	 * Returns the number of faktur indirects.
	 *
	 * @return the number of faktur indirects
	 */
	@Override
	public int getFakturIndirectsCount() {
		return _fakturIndirectLocalService.getFakturIndirectsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _fakturIndirectLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _fakturIndirectLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fakturIndirectLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the faktur indirect in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FakturIndirectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fakturIndirect the faktur indirect
	 * @return the faktur indirect that was updated
	 */
	@Override
	public com.astra.dewa.model.FakturIndirect updateFakturIndirect(
		com.astra.dewa.model.FakturIndirect fakturIndirect) {

		return _fakturIndirectLocalService.updateFakturIndirect(fakturIndirect);
	}

	@Override
	public FakturIndirectLocalService getWrappedService() {
		return _fakturIndirectLocalService;
	}

	@Override
	public void setWrappedService(
		FakturIndirectLocalService fakturIndirectLocalService) {

		_fakturIndirectLocalService = fakturIndirectLocalService;
	}

	private FakturIndirectLocalService _fakturIndirectLocalService;

}