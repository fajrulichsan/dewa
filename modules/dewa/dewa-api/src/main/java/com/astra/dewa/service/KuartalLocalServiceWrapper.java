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
 * Provides a wrapper for {@link KuartalLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KuartalLocalService
 * @generated
 */
public class KuartalLocalServiceWrapper
	implements KuartalLocalService, ServiceWrapper<KuartalLocalService> {

	public KuartalLocalServiceWrapper() {
		this(null);
	}

	public KuartalLocalServiceWrapper(KuartalLocalService kuartalLocalService) {
		_kuartalLocalService = kuartalLocalService;
	}

	/**
	 * Adds the kuartal to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KuartalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kuartal the kuartal
	 * @return the kuartal that was added
	 */
	@Override
	public com.astra.dewa.model.Kuartal addKuartal(
		com.astra.dewa.model.Kuartal kuartal) {

		return _kuartalLocalService.addKuartal(kuartal);
	}

	/**
	 * Creates a new kuartal with the primary key. Does not add the kuartal to the database.
	 *
	 * @param Id the primary key for the new kuartal
	 * @return the new kuartal
	 */
	@Override
	public com.astra.dewa.model.Kuartal createKuartal(String Id) {
		return _kuartalLocalService.createKuartal(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kuartalLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the kuartal from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KuartalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kuartal the kuartal
	 * @return the kuartal that was removed
	 */
	@Override
	public com.astra.dewa.model.Kuartal deleteKuartal(
		com.astra.dewa.model.Kuartal kuartal) {

		return _kuartalLocalService.deleteKuartal(kuartal);
	}

	/**
	 * Deletes the kuartal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KuartalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the kuartal
	 * @return the kuartal that was removed
	 * @throws PortalException if a kuartal with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.Kuartal deleteKuartal(String Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kuartalLocalService.deleteKuartal(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kuartalLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _kuartalLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _kuartalLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kuartalLocalService.dynamicQuery();
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

		return _kuartalLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.KuartalModelImpl</code>.
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

		return _kuartalLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.KuartalModelImpl</code>.
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

		return _kuartalLocalService.dynamicQuery(
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

		return _kuartalLocalService.dynamicQueryCount(dynamicQuery);
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

		return _kuartalLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.Kuartal fetchKuartal(String Id) {
		return _kuartalLocalService.fetchKuartal(Id);
	}

	/**
	 * Returns the kuartal with the primary key.
	 *
	 * @param Id the primary key of the kuartal
	 * @return the kuartal
	 * @throws PortalException if a kuartal with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.Kuartal getKuartal(String Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kuartalLocalService.getKuartal(Id);
	}

	/**
	 * Returns a range of all the kuartals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.KuartalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kuartals
	 * @param end the upper bound of the range of kuartals (not inclusive)
	 * @return the range of kuartals
	 */
	@Override
	public java.util.List<com.astra.dewa.model.Kuartal> getKuartals(
		int start, int end) {

		return _kuartalLocalService.getKuartals(start, end);
	}

	/**
	 * Returns the number of kuartals.
	 *
	 * @return the number of kuartals
	 */
	@Override
	public int getKuartalsCount() {
		return _kuartalLocalService.getKuartalsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _kuartalLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kuartalLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the kuartal in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KuartalLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kuartal the kuartal
	 * @return the kuartal that was updated
	 */
	@Override
	public com.astra.dewa.model.Kuartal updateKuartal(
		com.astra.dewa.model.Kuartal kuartal) {

		return _kuartalLocalService.updateKuartal(kuartal);
	}

	@Override
	public KuartalLocalService getWrappedService() {
		return _kuartalLocalService;
	}

	@Override
	public void setWrappedService(KuartalLocalService kuartalLocalService) {
		_kuartalLocalService = kuartalLocalService;
	}

	private KuartalLocalService _kuartalLocalService;

}