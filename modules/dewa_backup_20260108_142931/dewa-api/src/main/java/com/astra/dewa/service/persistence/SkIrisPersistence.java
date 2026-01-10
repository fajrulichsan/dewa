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

import com.astra.dewa.exception.NoSuchSkIrisException;
import com.astra.dewa.model.SkIris;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the sk iris service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SkIrisUtil
 * @generated
 */
@ProviderType
public interface SkIrisPersistence extends BasePersistence<SkIris> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SkIrisUtil} to access the sk iris persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the sk iris in the entity cache if it is enabled.
	 *
	 * @param skIris the sk iris
	 */
	public void cacheResult(SkIris skIris);

	/**
	 * Caches the sk irises in the entity cache if it is enabled.
	 *
	 * @param skIrises the sk irises
	 */
	public void cacheResult(java.util.List<SkIris> skIrises);

	/**
	 * Creates a new sk iris with the primary key. Does not add the sk iris to the database.
	 *
	 * @param Id the primary key for the new sk iris
	 * @return the new sk iris
	 */
	public SkIris create(int Id);

	/**
	 * Removes the sk iris with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the sk iris
	 * @return the sk iris that was removed
	 * @throws NoSuchSkIrisException if a sk iris with the primary key could not be found
	 */
	public SkIris remove(int Id) throws NoSuchSkIrisException;

	public SkIris updateImpl(SkIris skIris);

	/**
	 * Returns the sk iris with the primary key or throws a <code>NoSuchSkIrisException</code> if it could not be found.
	 *
	 * @param Id the primary key of the sk iris
	 * @return the sk iris
	 * @throws NoSuchSkIrisException if a sk iris with the primary key could not be found
	 */
	public SkIris findByPrimaryKey(int Id) throws NoSuchSkIrisException;

	/**
	 * Returns the sk iris with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the sk iris
	 * @return the sk iris, or <code>null</code> if a sk iris with the primary key could not be found
	 */
	public SkIris fetchByPrimaryKey(int Id);

	/**
	 * Returns all the sk irises.
	 *
	 * @return the sk irises
	 */
	public java.util.List<SkIris> findAll();

	/**
	 * Returns a range of all the sk irises.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkIrisModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sk irises
	 * @param end the upper bound of the range of sk irises (not inclusive)
	 * @return the range of sk irises
	 */
	public java.util.List<SkIris> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the sk irises.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkIrisModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sk irises
	 * @param end the upper bound of the range of sk irises (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sk irises
	 */
	public java.util.List<SkIris> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SkIris>
			orderByComparator);

	/**
	 * Returns an ordered range of all the sk irises.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkIrisModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sk irises
	 * @param end the upper bound of the range of sk irises (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sk irises
	 */
	public java.util.List<SkIris> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SkIris>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the sk irises from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of sk irises.
	 *
	 * @return the number of sk irises
	 */
	public int countAll();

}