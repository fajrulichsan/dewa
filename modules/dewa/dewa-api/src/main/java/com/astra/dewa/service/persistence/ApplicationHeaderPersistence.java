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

import com.astra.dewa.exception.NoSuchApplicationHeaderException;
import com.astra.dewa.model.ApplicationHeader;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the application header service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationHeaderUtil
 * @generated
 */
@ProviderType
public interface ApplicationHeaderPersistence
	extends BasePersistence<ApplicationHeader> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicationHeaderUtil} to access the application header persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the application header in the entity cache if it is enabled.
	 *
	 * @param applicationHeader the application header
	 */
	public void cacheResult(ApplicationHeader applicationHeader);

	/**
	 * Caches the application headers in the entity cache if it is enabled.
	 *
	 * @param applicationHeaders the application headers
	 */
	public void cacheResult(
		java.util.List<ApplicationHeader> applicationHeaders);

	/**
	 * Creates a new application header with the primary key. Does not add the application header to the database.
	 *
	 * @param Id the primary key for the new application header
	 * @return the new application header
	 */
	public ApplicationHeader create(int Id);

	/**
	 * Removes the application header with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application header
	 * @return the application header that was removed
	 * @throws NoSuchApplicationHeaderException if a application header with the primary key could not be found
	 */
	public ApplicationHeader remove(int Id)
		throws NoSuchApplicationHeaderException;

	public ApplicationHeader updateImpl(ApplicationHeader applicationHeader);

	/**
	 * Returns the application header with the primary key or throws a <code>NoSuchApplicationHeaderException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application header
	 * @return the application header
	 * @throws NoSuchApplicationHeaderException if a application header with the primary key could not be found
	 */
	public ApplicationHeader findByPrimaryKey(int Id)
		throws NoSuchApplicationHeaderException;

	/**
	 * Returns the application header with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application header
	 * @return the application header, or <code>null</code> if a application header with the primary key could not be found
	 */
	public ApplicationHeader fetchByPrimaryKey(int Id);

	/**
	 * Returns all the application headers.
	 *
	 * @return the application headers
	 */
	public java.util.List<ApplicationHeader> findAll();

	/**
	 * Returns a range of all the application headers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application headers
	 * @param end the upper bound of the range of application headers (not inclusive)
	 * @return the range of application headers
	 */
	public java.util.List<ApplicationHeader> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the application headers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application headers
	 * @param end the upper bound of the range of application headers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application headers
	 */
	public java.util.List<ApplicationHeader> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicationHeader>
			orderByComparator);

	/**
	 * Returns an ordered range of all the application headers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application headers
	 * @param end the upper bound of the range of application headers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of application headers
	 */
	public java.util.List<ApplicationHeader> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicationHeader>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the application headers from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of application headers.
	 *
	 * @return the number of application headers
	 */
	public int countAll();

}