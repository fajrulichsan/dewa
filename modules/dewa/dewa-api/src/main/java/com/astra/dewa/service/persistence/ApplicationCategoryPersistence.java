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

import com.astra.dewa.exception.NoSuchApplicationCategoryException;
import com.astra.dewa.model.ApplicationCategory;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the application category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationCategoryUtil
 * @generated
 */
@ProviderType
public interface ApplicationCategoryPersistence
	extends BasePersistence<ApplicationCategory> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicationCategoryUtil} to access the application category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the application category in the entity cache if it is enabled.
	 *
	 * @param applicationCategory the application category
	 */
	public void cacheResult(ApplicationCategory applicationCategory);

	/**
	 * Caches the application categories in the entity cache if it is enabled.
	 *
	 * @param applicationCategories the application categories
	 */
	public void cacheResult(
		java.util.List<ApplicationCategory> applicationCategories);

	/**
	 * Creates a new application category with the primary key. Does not add the application category to the database.
	 *
	 * @param Id the primary key for the new application category
	 * @return the new application category
	 */
	public ApplicationCategory create(int Id);

	/**
	 * Removes the application category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application category
	 * @return the application category that was removed
	 * @throws NoSuchApplicationCategoryException if a application category with the primary key could not be found
	 */
	public ApplicationCategory remove(int Id)
		throws NoSuchApplicationCategoryException;

	public ApplicationCategory updateImpl(
		ApplicationCategory applicationCategory);

	/**
	 * Returns the application category with the primary key or throws a <code>NoSuchApplicationCategoryException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application category
	 * @return the application category
	 * @throws NoSuchApplicationCategoryException if a application category with the primary key could not be found
	 */
	public ApplicationCategory findByPrimaryKey(int Id)
		throws NoSuchApplicationCategoryException;

	/**
	 * Returns the application category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application category
	 * @return the application category, or <code>null</code> if a application category with the primary key could not be found
	 */
	public ApplicationCategory fetchByPrimaryKey(int Id);

	/**
	 * Returns all the application categories.
	 *
	 * @return the application categories
	 */
	public java.util.List<ApplicationCategory> findAll();

	/**
	 * Returns a range of all the application categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application categories
	 * @param end the upper bound of the range of application categories (not inclusive)
	 * @return the range of application categories
	 */
	public java.util.List<ApplicationCategory> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the application categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application categories
	 * @param end the upper bound of the range of application categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application categories
	 */
	public java.util.List<ApplicationCategory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicationCategory>
			orderByComparator);

	/**
	 * Returns an ordered range of all the application categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application categories
	 * @param end the upper bound of the range of application categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of application categories
	 */
	public java.util.List<ApplicationCategory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicationCategory>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the application categories from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of application categories.
	 *
	 * @return the number of application categories
	 */
	public int countAll();

}