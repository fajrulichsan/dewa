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

import com.astra.dewa.exception.NoSuchApplicationAssignException;
import com.astra.dewa.model.ApplicationAssign;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the application assign service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationAssignUtil
 * @generated
 */
@ProviderType
public interface ApplicationAssignPersistence
	extends BasePersistence<ApplicationAssign> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicationAssignUtil} to access the application assign persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the application assign in the entity cache if it is enabled.
	 *
	 * @param applicationAssign the application assign
	 */
	public void cacheResult(ApplicationAssign applicationAssign);

	/**
	 * Caches the application assigns in the entity cache if it is enabled.
	 *
	 * @param applicationAssigns the application assigns
	 */
	public void cacheResult(
		java.util.List<ApplicationAssign> applicationAssigns);

	/**
	 * Creates a new application assign with the primary key. Does not add the application assign to the database.
	 *
	 * @param Id the primary key for the new application assign
	 * @return the new application assign
	 */
	public ApplicationAssign create(int Id);

	/**
	 * Removes the application assign with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application assign
	 * @return the application assign that was removed
	 * @throws NoSuchApplicationAssignException if a application assign with the primary key could not be found
	 */
	public ApplicationAssign remove(int Id)
		throws NoSuchApplicationAssignException;

	public ApplicationAssign updateImpl(ApplicationAssign applicationAssign);

	/**
	 * Returns the application assign with the primary key or throws a <code>NoSuchApplicationAssignException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign
	 * @return the application assign
	 * @throws NoSuchApplicationAssignException if a application assign with the primary key could not be found
	 */
	public ApplicationAssign findByPrimaryKey(int Id)
		throws NoSuchApplicationAssignException;

	/**
	 * Returns the application assign with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign
	 * @return the application assign, or <code>null</code> if a application assign with the primary key could not be found
	 */
	public ApplicationAssign fetchByPrimaryKey(int Id);

	/**
	 * Returns all the application assigns.
	 *
	 * @return the application assigns
	 */
	public java.util.List<ApplicationAssign> findAll();

	/**
	 * Returns a range of all the application assigns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assigns
	 * @param end the upper bound of the range of application assigns (not inclusive)
	 * @return the range of application assigns
	 */
	public java.util.List<ApplicationAssign> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the application assigns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assigns
	 * @param end the upper bound of the range of application assigns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application assigns
	 */
	public java.util.List<ApplicationAssign> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicationAssign>
			orderByComparator);

	/**
	 * Returns an ordered range of all the application assigns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assigns
	 * @param end the upper bound of the range of application assigns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of application assigns
	 */
	public java.util.List<ApplicationAssign> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicationAssign>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the application assigns from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of application assigns.
	 *
	 * @return the number of application assigns
	 */
	public int countAll();

}