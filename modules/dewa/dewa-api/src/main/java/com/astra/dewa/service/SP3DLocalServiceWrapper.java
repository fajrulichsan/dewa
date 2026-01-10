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
 * Provides a wrapper for {@link SP3DLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SP3DLocalService
 * @generated
 */
public class SP3DLocalServiceWrapper
	implements ServiceWrapper<SP3DLocalService>, SP3DLocalService {

	public SP3DLocalServiceWrapper() {
		this(null);
	}

	public SP3DLocalServiceWrapper(SP3DLocalService sp3dLocalService) {
		_sp3dLocalService = sp3dLocalService;
	}

	/**
	 * Adds the sp3d to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SP3DLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sp3d the sp3d
	 * @return the sp3d that was added
	 */
	@Override
	public com.astra.dewa.model.SP3D addSP3D(com.astra.dewa.model.SP3D sp3d) {
		return _sp3dLocalService.addSP3D(sp3d);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sp3dLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new sp3d with the primary key. Does not add the sp3d to the database.
	 *
	 * @param Id the primary key for the new sp3d
	 * @return the new sp3d
	 */
	@Override
	public com.astra.dewa.model.SP3D createSP3D(int Id) {
		return _sp3dLocalService.createSP3D(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sp3dLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the sp3d with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SP3DLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the sp3d
	 * @return the sp3d that was removed
	 * @throws PortalException if a sp3d with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.SP3D deleteSP3D(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sp3dLocalService.deleteSP3D(Id);
	}

	/**
	 * Deletes the sp3d from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SP3DLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sp3d the sp3d
	 * @return the sp3d that was removed
	 */
	@Override
	public com.astra.dewa.model.SP3D deleteSP3D(
		com.astra.dewa.model.SP3D sp3d) {

		return _sp3dLocalService.deleteSP3D(sp3d);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _sp3dLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _sp3dLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _sp3dLocalService.dynamicQuery();
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

		return _sp3dLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.SP3DModelImpl</code>.
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

		return _sp3dLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.SP3DModelImpl</code>.
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

		return _sp3dLocalService.dynamicQuery(
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

		return _sp3dLocalService.dynamicQueryCount(dynamicQuery);
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

		return _sp3dLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.SP3D fetchSP3D(int Id) {
		return _sp3dLocalService.fetchSP3D(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _sp3dLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _sp3dLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _sp3dLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sp3dLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the sp3d with the primary key.
	 *
	 * @param Id the primary key of the sp3d
	 * @return the sp3d
	 * @throws PortalException if a sp3d with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.SP3D getSP3D(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sp3dLocalService.getSP3D(Id);
	}

	/**
	 * Returns a range of all the sp3ds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.SP3DModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sp3ds
	 * @param end the upper bound of the range of sp3ds (not inclusive)
	 * @return the range of sp3ds
	 */
	@Override
	public java.util.List<com.astra.dewa.model.SP3D> getSP3Ds(
		int start, int end) {

		return _sp3dLocalService.getSP3Ds(start, end);
	}

	/**
	 * Returns the number of sp3ds.
	 *
	 * @return the number of sp3ds
	 */
	@Override
	public int getSP3DsCount() {
		return _sp3dLocalService.getSP3DsCount();
	}

	/**
	 * Updates the sp3d in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SP3DLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sp3d the sp3d
	 * @return the sp3d that was updated
	 */
	@Override
	public com.astra.dewa.model.SP3D updateSP3D(
		com.astra.dewa.model.SP3D sp3d) {

		return _sp3dLocalService.updateSP3D(sp3d);
	}

	@Override
	public SP3DLocalService getWrappedService() {
		return _sp3dLocalService;
	}

	@Override
	public void setWrappedService(SP3DLocalService sp3dLocalService) {
		_sp3dLocalService = sp3dLocalService;
	}

	private SP3DLocalService _sp3dLocalService;

}