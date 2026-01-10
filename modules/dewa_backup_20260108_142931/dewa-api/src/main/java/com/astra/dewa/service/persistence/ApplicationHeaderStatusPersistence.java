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

import com.astra.dewa.exception.NoSuchApplicationHeaderStatusException;
import com.astra.dewa.model.ApplicationHeaderStatus;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the application header status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationHeaderStatusUtil
 * @generated
 */
@ProviderType
public interface ApplicationHeaderStatusPersistence
	extends BasePersistence<ApplicationHeaderStatus> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicationHeaderStatusUtil} to access the application header status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the application header status in the entity cache if it is enabled.
	 *
	 * @param applicationHeaderStatus the application header status
	 */
	public void cacheResult(ApplicationHeaderStatus applicationHeaderStatus);

	/**
	 * Caches the application header statuses in the entity cache if it is enabled.
	 *
	 * @param applicationHeaderStatuses the application header statuses
	 */
	public void cacheResult(
		java.util.List<ApplicationHeaderStatus> applicationHeaderStatuses);

	/**
	 * Creates a new application header status with the primary key. Does not add the application header status to the database.
	 *
	 * @param Id the primary key for the new application header status
	 * @return the new application header status
	 */
	public ApplicationHeaderStatus create(int Id);

	/**
	 * Removes the application header status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application header status
	 * @return the application header status that was removed
	 * @throws NoSuchApplicationHeaderStatusException if a application header status with the primary key could not be found
	 */
	public ApplicationHeaderStatus remove(int Id)
		throws NoSuchApplicationHeaderStatusException;

	public ApplicationHeaderStatus updateImpl(
		ApplicationHeaderStatus applicationHeaderStatus);

	/**
	 * Returns the application header status with the primary key or throws a <code>NoSuchApplicationHeaderStatusException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application header status
	 * @return the application header status
	 * @throws NoSuchApplicationHeaderStatusException if a application header status with the primary key could not be found
	 */
	public ApplicationHeaderStatus findByPrimaryKey(int Id)
		throws NoSuchApplicationHeaderStatusException;

	/**
	 * Returns the application header status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application header status
	 * @return the application header status, or <code>null</code> if a application header status with the primary key could not be found
	 */
	public ApplicationHeaderStatus fetchByPrimaryKey(int Id);

	/**
	 * Returns all the application header statuses.
	 *
	 * @return the application header statuses
	 */
	public java.util.List<ApplicationHeaderStatus> findAll();

	/**
	 * Returns a range of all the application header statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application header statuses
	 * @param end the upper bound of the range of application header statuses (not inclusive)
	 * @return the range of application header statuses
	 */
	public java.util.List<ApplicationHeaderStatus> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the application header statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application header statuses
	 * @param end the upper bound of the range of application header statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application header statuses
	 */
	public java.util.List<ApplicationHeaderStatus> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<ApplicationHeaderStatus> orderByComparator);

	/**
	 * Returns an ordered range of all the application header statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application header statuses
	 * @param end the upper bound of the range of application header statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of application header statuses
	 */
	public java.util.List<ApplicationHeaderStatus> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<ApplicationHeaderStatus> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the application header statuses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of application header statuses.
	 *
	 * @return the number of application header statuses
	 */
	public int countAll();

}