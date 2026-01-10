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
 * Provides a wrapper for {@link PeriodeReviewLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PeriodeReviewLocalService
 * @generated
 */
public class PeriodeReviewLocalServiceWrapper
	implements PeriodeReviewLocalService,
			   ServiceWrapper<PeriodeReviewLocalService> {

	public PeriodeReviewLocalServiceWrapper() {
		this(null);
	}

	public PeriodeReviewLocalServiceWrapper(
		PeriodeReviewLocalService periodeReviewLocalService) {

		_periodeReviewLocalService = periodeReviewLocalService;
	}

	/**
	 * Adds the periode review to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PeriodeReviewLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param periodeReview the periode review
	 * @return the periode review that was added
	 */
	@Override
	public com.astra.dewa.model.PeriodeReview addPeriodeReview(
		com.astra.dewa.model.PeriodeReview periodeReview) {

		return _periodeReviewLocalService.addPeriodeReview(periodeReview);
	}

	/**
	 * Creates a new periode review with the primary key. Does not add the periode review to the database.
	 *
	 * @param Id the primary key for the new periode review
	 * @return the new periode review
	 */
	@Override
	public com.astra.dewa.model.PeriodeReview createPeriodeReview(String Id) {
		return _periodeReviewLocalService.createPeriodeReview(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _periodeReviewLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the periode review from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PeriodeReviewLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param periodeReview the periode review
	 * @return the periode review that was removed
	 */
	@Override
	public com.astra.dewa.model.PeriodeReview deletePeriodeReview(
		com.astra.dewa.model.PeriodeReview periodeReview) {

		return _periodeReviewLocalService.deletePeriodeReview(periodeReview);
	}

	/**
	 * Deletes the periode review with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PeriodeReviewLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the periode review
	 * @return the periode review that was removed
	 * @throws PortalException if a periode review with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.PeriodeReview deletePeriodeReview(String Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _periodeReviewLocalService.deletePeriodeReview(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _periodeReviewLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _periodeReviewLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _periodeReviewLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _periodeReviewLocalService.dynamicQuery();
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

		return _periodeReviewLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.PeriodeReviewModelImpl</code>.
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

		return _periodeReviewLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.PeriodeReviewModelImpl</code>.
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

		return _periodeReviewLocalService.dynamicQuery(
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

		return _periodeReviewLocalService.dynamicQueryCount(dynamicQuery);
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

		return _periodeReviewLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.PeriodeReview fetchPeriodeReview(String Id) {
		return _periodeReviewLocalService.fetchPeriodeReview(Id);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _periodeReviewLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * Returns the periode review with the primary key.
	 *
	 * @param Id the primary key of the periode review
	 * @return the periode review
	 * @throws PortalException if a periode review with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.PeriodeReview getPeriodeReview(String Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _periodeReviewLocalService.getPeriodeReview(Id);
	}

	/**
	 * Returns a range of all the periode reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.PeriodeReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of periode reviews
	 * @param end the upper bound of the range of periode reviews (not inclusive)
	 * @return the range of periode reviews
	 */
	@Override
	public java.util.List<com.astra.dewa.model.PeriodeReview> getPeriodeReviews(
		int start, int end) {

		return _periodeReviewLocalService.getPeriodeReviews(start, end);
	}

	/**
	 * Returns the number of periode reviews.
	 *
	 * @return the number of periode reviews
	 */
	@Override
	public int getPeriodeReviewsCount() {
		return _periodeReviewLocalService.getPeriodeReviewsCount();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _periodeReviewLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the periode review in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PeriodeReviewLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param periodeReview the periode review
	 * @return the periode review that was updated
	 */
	@Override
	public com.astra.dewa.model.PeriodeReview updatePeriodeReview(
		com.astra.dewa.model.PeriodeReview periodeReview) {

		return _periodeReviewLocalService.updatePeriodeReview(periodeReview);
	}

	@Override
	public PeriodeReviewLocalService getWrappedService() {
		return _periodeReviewLocalService;
	}

	@Override
	public void setWrappedService(
		PeriodeReviewLocalService periodeReviewLocalService) {

		_periodeReviewLocalService = periodeReviewLocalService;
	}

	private PeriodeReviewLocalService _periodeReviewLocalService;

}