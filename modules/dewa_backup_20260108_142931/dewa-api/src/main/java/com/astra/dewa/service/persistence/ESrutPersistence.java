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

import com.astra.dewa.exception.NoSuchESrutException;
import com.astra.dewa.model.ESrut;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the e srut service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ESrutUtil
 * @generated
 */
@ProviderType
public interface ESrutPersistence extends BasePersistence<ESrut> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ESrutUtil} to access the e srut persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the e srut in the entity cache if it is enabled.
	 *
	 * @param eSrut the e srut
	 */
	public void cacheResult(ESrut eSrut);

	/**
	 * Caches the e sruts in the entity cache if it is enabled.
	 *
	 * @param eSruts the e sruts
	 */
	public void cacheResult(java.util.List<ESrut> eSruts);

	/**
	 * Creates a new e srut with the primary key. Does not add the e srut to the database.
	 *
	 * @param Id the primary key for the new e srut
	 * @return the new e srut
	 */
	public ESrut create(int Id);

	/**
	 * Removes the e srut with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the e srut
	 * @return the e srut that was removed
	 * @throws NoSuchESrutException if a e srut with the primary key could not be found
	 */
	public ESrut remove(int Id) throws NoSuchESrutException;

	public ESrut updateImpl(ESrut eSrut);

	/**
	 * Returns the e srut with the primary key or throws a <code>NoSuchESrutException</code> if it could not be found.
	 *
	 * @param Id the primary key of the e srut
	 * @return the e srut
	 * @throws NoSuchESrutException if a e srut with the primary key could not be found
	 */
	public ESrut findByPrimaryKey(int Id) throws NoSuchESrutException;

	/**
	 * Returns the e srut with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the e srut
	 * @return the e srut, or <code>null</code> if a e srut with the primary key could not be found
	 */
	public ESrut fetchByPrimaryKey(int Id);

	/**
	 * Returns all the e sruts.
	 *
	 * @return the e sruts
	 */
	public java.util.List<ESrut> findAll();

	/**
	 * Returns a range of all the e sruts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ESrutModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e sruts
	 * @param end the upper bound of the range of e sruts (not inclusive)
	 * @return the range of e sruts
	 */
	public java.util.List<ESrut> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the e sruts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ESrutModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e sruts
	 * @param end the upper bound of the range of e sruts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of e sruts
	 */
	public java.util.List<ESrut> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ESrut>
			orderByComparator);

	/**
	 * Returns an ordered range of all the e sruts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ESrutModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e sruts
	 * @param end the upper bound of the range of e sruts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of e sruts
	 */
	public java.util.List<ESrut> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ESrut>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the e sruts from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of e sruts.
	 *
	 * @return the number of e sruts
	 */
	public int countAll();

}