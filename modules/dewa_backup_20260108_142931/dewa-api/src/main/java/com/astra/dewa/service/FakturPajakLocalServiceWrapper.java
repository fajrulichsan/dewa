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
 * Provides a wrapper for {@link FakturPajakLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FakturPajakLocalService
 * @generated
 */
public class FakturPajakLocalServiceWrapper
	implements FakturPajakLocalService,
			   ServiceWrapper<FakturPajakLocalService> {

	public FakturPajakLocalServiceWrapper() {
		this(null);
	}

	public FakturPajakLocalServiceWrapper(
		FakturPajakLocalService fakturPajakLocalService) {

		_fakturPajakLocalService = fakturPajakLocalService;
	}

	/**
	 * Adds the faktur pajak to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FakturPajakLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fakturPajak the faktur pajak
	 * @return the faktur pajak that was added
	 */
	@Override
	public com.astra.dewa.model.FakturPajak addFakturPajak(
		com.astra.dewa.model.FakturPajak fakturPajak) {

		return _fakturPajakLocalService.addFakturPajak(fakturPajak);
	}

	/**
	 * Creates a new faktur pajak with the primary key. Does not add the faktur pajak to the database.
	 *
	 * @param Id the primary key for the new faktur pajak
	 * @return the new faktur pajak
	 */
	@Override
	public com.astra.dewa.model.FakturPajak createFakturPajak(int Id) {
		return _fakturPajakLocalService.createFakturPajak(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fakturPajakLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the faktur pajak from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FakturPajakLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fakturPajak the faktur pajak
	 * @return the faktur pajak that was removed
	 */
	@Override
	public com.astra.dewa.model.FakturPajak deleteFakturPajak(
		com.astra.dewa.model.FakturPajak fakturPajak) {

		return _fakturPajakLocalService.deleteFakturPajak(fakturPajak);
	}

	/**
	 * Deletes the faktur pajak with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FakturPajakLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the faktur pajak
	 * @return the faktur pajak that was removed
	 * @throws PortalException if a faktur pajak with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.FakturPajak deleteFakturPajak(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fakturPajakLocalService.deleteFakturPajak(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fakturPajakLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _fakturPajakLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _fakturPajakLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fakturPajakLocalService.dynamicQuery();
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

		return _fakturPajakLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.FakturPajakModelImpl</code>.
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

		return _fakturPajakLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.FakturPajakModelImpl</code>.
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

		return _fakturPajakLocalService.dynamicQuery(
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

		return _fakturPajakLocalService.dynamicQueryCount(dynamicQuery);
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

		return _fakturPajakLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.FakturPajak fetchFakturPajak(int Id) {
		return _fakturPajakLocalService.fetchFakturPajak(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _fakturPajakLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the faktur pajak with the primary key.
	 *
	 * @param Id the primary key of the faktur pajak
	 * @return the faktur pajak
	 * @throws PortalException if a faktur pajak with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.FakturPajak getFakturPajak(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fakturPajakLocalService.getFakturPajak(Id);
	}

	/**
	 * Returns a range of all the faktur pajaks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.FakturPajakModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur pajaks
	 * @param end the upper bound of the range of faktur pajaks (not inclusive)
	 * @return the range of faktur pajaks
	 */
	@Override
	public java.util.List<com.astra.dewa.model.FakturPajak> getFakturPajaks(
		int start, int end) {

		return _fakturPajakLocalService.getFakturPajaks(start, end);
	}

	/**
	 * Returns the number of faktur pajaks.
	 *
	 * @return the number of faktur pajaks
	 */
	@Override
	public int getFakturPajaksCount() {
		return _fakturPajakLocalService.getFakturPajaksCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _fakturPajakLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _fakturPajakLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fakturPajakLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the faktur pajak in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FakturPajakLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fakturPajak the faktur pajak
	 * @return the faktur pajak that was updated
	 */
	@Override
	public com.astra.dewa.model.FakturPajak updateFakturPajak(
		com.astra.dewa.model.FakturPajak fakturPajak) {

		return _fakturPajakLocalService.updateFakturPajak(fakturPajak);
	}

	@Override
	public FakturPajakLocalService getWrappedService() {
		return _fakturPajakLocalService;
	}

	@Override
	public void setWrappedService(
		FakturPajakLocalService fakturPajakLocalService) {

		_fakturPajakLocalService = fakturPajakLocalService;
	}

	private FakturPajakLocalService _fakturPajakLocalService;

}