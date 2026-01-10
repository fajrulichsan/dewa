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
 * Provides a wrapper for {@link EmailDomainLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EmailDomainLocalService
 * @generated
 */
public class EmailDomainLocalServiceWrapper
	implements EmailDomainLocalService,
			   ServiceWrapper<EmailDomainLocalService> {

	public EmailDomainLocalServiceWrapper() {
		this(null);
	}

	public EmailDomainLocalServiceWrapper(
		EmailDomainLocalService emailDomainLocalService) {

		_emailDomainLocalService = emailDomainLocalService;
	}

	/**
	 * Adds the email domain to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EmailDomainLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param emailDomain the email domain
	 * @return the email domain that was added
	 */
	@Override
	public com.astra.dewa.model.EmailDomain addEmailDomain(
		com.astra.dewa.model.EmailDomain emailDomain) {

		return _emailDomainLocalService.addEmailDomain(emailDomain);
	}

	/**
	 * Creates a new email domain with the primary key. Does not add the email domain to the database.
	 *
	 * @param Id the primary key for the new email domain
	 * @return the new email domain
	 */
	@Override
	public com.astra.dewa.model.EmailDomain createEmailDomain(int Id) {
		return _emailDomainLocalService.createEmailDomain(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _emailDomainLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the email domain from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EmailDomainLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param emailDomain the email domain
	 * @return the email domain that was removed
	 */
	@Override
	public com.astra.dewa.model.EmailDomain deleteEmailDomain(
		com.astra.dewa.model.EmailDomain emailDomain) {

		return _emailDomainLocalService.deleteEmailDomain(emailDomain);
	}

	/**
	 * Deletes the email domain with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EmailDomainLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the email domain
	 * @return the email domain that was removed
	 * @throws PortalException if a email domain with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.EmailDomain deleteEmailDomain(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _emailDomainLocalService.deleteEmailDomain(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _emailDomainLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _emailDomainLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _emailDomainLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _emailDomainLocalService.dynamicQuery();
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

		return _emailDomainLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.EmailDomainModelImpl</code>.
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

		return _emailDomainLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.EmailDomainModelImpl</code>.
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

		return _emailDomainLocalService.dynamicQuery(
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

		return _emailDomainLocalService.dynamicQueryCount(dynamicQuery);
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

		return _emailDomainLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.EmailDomain fetchEmailDomain(int Id) {
		return _emailDomainLocalService.fetchEmailDomain(Id);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _emailDomainLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the email domain with the primary key.
	 *
	 * @param Id the primary key of the email domain
	 * @return the email domain
	 * @throws PortalException if a email domain with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.EmailDomain getEmailDomain(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _emailDomainLocalService.getEmailDomain(Id);
	}

	/**
	 * Returns a range of all the email domains.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.EmailDomainModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email domains
	 * @param end the upper bound of the range of email domains (not inclusive)
	 * @return the range of email domains
	 */
	@Override
	public java.util.List<com.astra.dewa.model.EmailDomain> getEmailDomains(
		int start, int end) {

		return _emailDomainLocalService.getEmailDomains(start, end);
	}

	/**
	 * Returns the number of email domains.
	 *
	 * @return the number of email domains
	 */
	@Override
	public int getEmailDomainsCount() {
		return _emailDomainLocalService.getEmailDomainsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _emailDomainLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _emailDomainLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _emailDomainLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the email domain in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EmailDomainLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param emailDomain the email domain
	 * @return the email domain that was updated
	 */
	@Override
	public com.astra.dewa.model.EmailDomain updateEmailDomain(
		com.astra.dewa.model.EmailDomain emailDomain) {

		return _emailDomainLocalService.updateEmailDomain(emailDomain);
	}

	@Override
	public EmailDomainLocalService getWrappedService() {
		return _emailDomainLocalService;
	}

	@Override
	public void setWrappedService(
		EmailDomainLocalService emailDomainLocalService) {

		_emailDomainLocalService = emailDomainLocalService;
	}

	private EmailDomainLocalService _emailDomainLocalService;

}