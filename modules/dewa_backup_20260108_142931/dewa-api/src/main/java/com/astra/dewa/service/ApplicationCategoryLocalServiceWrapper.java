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
 * Provides a wrapper for {@link ApplicationCategoryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationCategoryLocalService
 * @generated
 */
public class ApplicationCategoryLocalServiceWrapper
	implements ApplicationCategoryLocalService,
			   ServiceWrapper<ApplicationCategoryLocalService> {

	public ApplicationCategoryLocalServiceWrapper() {
		this(null);
	}

	public ApplicationCategoryLocalServiceWrapper(
		ApplicationCategoryLocalService applicationCategoryLocalService) {

		_applicationCategoryLocalService = applicationCategoryLocalService;
	}

	/**
	 * Adds the application category to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationCategory the application category
	 * @return the application category that was added
	 */
	@Override
	public com.astra.dewa.model.ApplicationCategory addApplicationCategory(
		com.astra.dewa.model.ApplicationCategory applicationCategory) {

		return _applicationCategoryLocalService.addApplicationCategory(
			applicationCategory);
	}

	/**
	 * Creates a new application category with the primary key. Does not add the application category to the database.
	 *
	 * @param Id the primary key for the new application category
	 * @return the new application category
	 */
	@Override
	public com.astra.dewa.model.ApplicationCategory createApplicationCategory(
		int Id) {

		return _applicationCategoryLocalService.createApplicationCategory(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationCategoryLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the application category from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationCategory the application category
	 * @return the application category that was removed
	 */
	@Override
	public com.astra.dewa.model.ApplicationCategory deleteApplicationCategory(
		com.astra.dewa.model.ApplicationCategory applicationCategory) {

		return _applicationCategoryLocalService.deleteApplicationCategory(
			applicationCategory);
	}

	/**
	 * Deletes the application category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the application category
	 * @return the application category that was removed
	 * @throws PortalException if a application category with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationCategory deleteApplicationCategory(
			int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationCategoryLocalService.deleteApplicationCategory(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationCategoryLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _applicationCategoryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _applicationCategoryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _applicationCategoryLocalService.dynamicQuery();
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

		return _applicationCategoryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationCategoryModelImpl</code>.
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

		return _applicationCategoryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationCategoryModelImpl</code>.
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

		return _applicationCategoryLocalService.dynamicQuery(
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

		return _applicationCategoryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _applicationCategoryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.ApplicationCategory fetchApplicationCategory(
		int Id) {

		return _applicationCategoryLocalService.fetchApplicationCategory(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _applicationCategoryLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the application categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.ApplicationCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application categories
	 * @param end the upper bound of the range of application categories (not inclusive)
	 * @return the range of application categories
	 */
	@Override
	public java.util.List<com.astra.dewa.model.ApplicationCategory>
		getApplicationCategories(int start, int end) {

		return _applicationCategoryLocalService.getApplicationCategories(
			start, end);
	}

	/**
	 * Returns the number of application categories.
	 *
	 * @return the number of application categories
	 */
	@Override
	public int getApplicationCategoriesCount() {
		return _applicationCategoryLocalService.getApplicationCategoriesCount();
	}

	/**
	 * Returns the application category with the primary key.
	 *
	 * @param Id the primary key of the application category
	 * @return the application category
	 * @throws PortalException if a application category with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.ApplicationCategory getApplicationCategory(
			int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationCategoryLocalService.getApplicationCategory(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _applicationCategoryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicationCategoryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _applicationCategoryLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the application category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ApplicationCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param applicationCategory the application category
	 * @return the application category that was updated
	 */
	@Override
	public com.astra.dewa.model.ApplicationCategory updateApplicationCategory(
		com.astra.dewa.model.ApplicationCategory applicationCategory) {

		return _applicationCategoryLocalService.updateApplicationCategory(
			applicationCategory);
	}

	@Override
	public ApplicationCategoryLocalService getWrappedService() {
		return _applicationCategoryLocalService;
	}

	@Override
	public void setWrappedService(
		ApplicationCategoryLocalService applicationCategoryLocalService) {

		_applicationCategoryLocalService = applicationCategoryLocalService;
	}

	private ApplicationCategoryLocalService _applicationCategoryLocalService;

}