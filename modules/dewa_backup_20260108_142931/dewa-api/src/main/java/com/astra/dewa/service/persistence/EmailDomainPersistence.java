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

package com.astra.dewa.service.persistence;

import com.astra.dewa.exception.NoSuchEmailDomainException;
import com.astra.dewa.model.EmailDomain;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the email domain service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailDomainUtil
 * @generated
 */
@ProviderType
public interface EmailDomainPersistence extends BasePersistence<EmailDomain> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EmailDomainUtil} to access the email domain persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the email domain in the entity cache if it is enabled.
	 *
	 * @param emailDomain the email domain
	 */
	public void cacheResult(EmailDomain emailDomain);

	/**
	 * Caches the email domains in the entity cache if it is enabled.
	 *
	 * @param emailDomains the email domains
	 */
	public void cacheResult(java.util.List<EmailDomain> emailDomains);

	/**
	 * Creates a new email domain with the primary key. Does not add the email domain to the database.
	 *
	 * @param Id the primary key for the new email domain
	 * @return the new email domain
	 */
	public EmailDomain create(int Id);

	/**
	 * Removes the email domain with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the email domain
	 * @return the email domain that was removed
	 * @throws NoSuchEmailDomainException if a email domain with the primary key could not be found
	 */
	public EmailDomain remove(int Id) throws NoSuchEmailDomainException;

	public EmailDomain updateImpl(EmailDomain emailDomain);

	/**
	 * Returns the email domain with the primary key or throws a <code>NoSuchEmailDomainException</code> if it could not be found.
	 *
	 * @param Id the primary key of the email domain
	 * @return the email domain
	 * @throws NoSuchEmailDomainException if a email domain with the primary key could not be found
	 */
	public EmailDomain findByPrimaryKey(int Id)
		throws NoSuchEmailDomainException;

	/**
	 * Returns the email domain with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the email domain
	 * @return the email domain, or <code>null</code> if a email domain with the primary key could not be found
	 */
	public EmailDomain fetchByPrimaryKey(int Id);

	/**
	 * Returns all the email domains.
	 *
	 * @return the email domains
	 */
	public java.util.List<EmailDomain> findAll();

	/**
	 * Returns a range of all the email domains.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailDomainModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email domains
	 * @param end the upper bound of the range of email domains (not inclusive)
	 * @return the range of email domains
	 */
	public java.util.List<EmailDomain> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the email domains.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailDomainModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email domains
	 * @param end the upper bound of the range of email domains (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of email domains
	 */
	public java.util.List<EmailDomain> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailDomain>
			orderByComparator);

	/**
	 * Returns an ordered range of all the email domains.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailDomainModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email domains
	 * @param end the upper bound of the range of email domains (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of email domains
	 */
	public java.util.List<EmailDomain> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailDomain>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the email domains from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of email domains.
	 *
	 * @return the number of email domains
	 */
	public int countAll();

}