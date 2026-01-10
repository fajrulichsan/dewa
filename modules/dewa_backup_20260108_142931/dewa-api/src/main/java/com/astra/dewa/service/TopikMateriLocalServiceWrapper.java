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
 * Provides a wrapper for {@link TopikMateriLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TopikMateriLocalService
 * @generated
 */
public class TopikMateriLocalServiceWrapper
	implements ServiceWrapper<TopikMateriLocalService>,
			   TopikMateriLocalService {

	public TopikMateriLocalServiceWrapper() {
		this(null);
	}

	public TopikMateriLocalServiceWrapper(
		TopikMateriLocalService topikMateriLocalService) {

		_topikMateriLocalService = topikMateriLocalService;
	}

	/**
	 * Adds the topik materi to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TopikMateriLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param topikMateri the topik materi
	 * @return the topik materi that was added
	 */
	@Override
	public com.astra.dewa.model.TopikMateri addTopikMateri(
		com.astra.dewa.model.TopikMateri topikMateri) {

		return _topikMateriLocalService.addTopikMateri(topikMateri);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _topikMateriLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new topik materi with the primary key. Does not add the topik materi to the database.
	 *
	 * @param Id the primary key for the new topik materi
	 * @return the new topik materi
	 */
	@Override
	public com.astra.dewa.model.TopikMateri createTopikMateri(int Id) {
		return _topikMateriLocalService.createTopikMateri(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _topikMateriLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the topik materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TopikMateriLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the topik materi
	 * @return the topik materi that was removed
	 * @throws PortalException if a topik materi with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.TopikMateri deleteTopikMateri(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _topikMateriLocalService.deleteTopikMateri(Id);
	}

	/**
	 * Deletes the topik materi from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TopikMateriLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param topikMateri the topik materi
	 * @return the topik materi that was removed
	 */
	@Override
	public com.astra.dewa.model.TopikMateri deleteTopikMateri(
		com.astra.dewa.model.TopikMateri topikMateri) {

		return _topikMateriLocalService.deleteTopikMateri(topikMateri);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _topikMateriLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _topikMateriLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _topikMateriLocalService.dynamicQuery();
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

		return _topikMateriLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TopikMateriModelImpl</code>.
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

		return _topikMateriLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TopikMateriModelImpl</code>.
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

		return _topikMateriLocalService.dynamicQuery(
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

		return _topikMateriLocalService.dynamicQueryCount(dynamicQuery);
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

		return _topikMateriLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.TopikMateri fetchTopikMateri(int Id) {
		return _topikMateriLocalService.fetchTopikMateri(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _topikMateriLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _topikMateriLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _topikMateriLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _topikMateriLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the topik materi with the primary key.
	 *
	 * @param Id the primary key of the topik materi
	 * @return the topik materi
	 * @throws PortalException if a topik materi with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.TopikMateri getTopikMateri(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _topikMateriLocalService.getTopikMateri(Id);
	}

	/**
	 * Returns a range of all the topik materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.TopikMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of topik materis
	 * @param end the upper bound of the range of topik materis (not inclusive)
	 * @return the range of topik materis
	 */
	@Override
	public java.util.List<com.astra.dewa.model.TopikMateri> getTopikMateris(
		int start, int end) {

		return _topikMateriLocalService.getTopikMateris(start, end);
	}

	/**
	 * Returns the number of topik materis.
	 *
	 * @return the number of topik materis
	 */
	@Override
	public int getTopikMaterisCount() {
		return _topikMateriLocalService.getTopikMaterisCount();
	}

	/**
	 * Updates the topik materi in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TopikMateriLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param topikMateri the topik materi
	 * @return the topik materi that was updated
	 */
	@Override
	public com.astra.dewa.model.TopikMateri updateTopikMateri(
		com.astra.dewa.model.TopikMateri topikMateri) {

		return _topikMateriLocalService.updateTopikMateri(topikMateri);
	}

	@Override
	public TopikMateriLocalService getWrappedService() {
		return _topikMateriLocalService;
	}

	@Override
	public void setWrappedService(
		TopikMateriLocalService topikMateriLocalService) {

		_topikMateriLocalService = topikMateriLocalService;
	}

	private TopikMateriLocalService _topikMateriLocalService;

}