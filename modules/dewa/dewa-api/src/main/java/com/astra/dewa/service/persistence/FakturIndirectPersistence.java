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

import com.astra.dewa.exception.NoSuchFakturIndirectException;
import com.astra.dewa.model.FakturIndirect;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the faktur indirect service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FakturIndirectUtil
 * @generated
 */
@ProviderType
public interface FakturIndirectPersistence
	extends BasePersistence<FakturIndirect> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FakturIndirectUtil} to access the faktur indirect persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the faktur indirect in the entity cache if it is enabled.
	 *
	 * @param fakturIndirect the faktur indirect
	 */
	public void cacheResult(FakturIndirect fakturIndirect);

	/**
	 * Caches the faktur indirects in the entity cache if it is enabled.
	 *
	 * @param fakturIndirects the faktur indirects
	 */
	public void cacheResult(java.util.List<FakturIndirect> fakturIndirects);

	/**
	 * Creates a new faktur indirect with the primary key. Does not add the faktur indirect to the database.
	 *
	 * @param Id the primary key for the new faktur indirect
	 * @return the new faktur indirect
	 */
	public FakturIndirect create(int Id);

	/**
	 * Removes the faktur indirect with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the faktur indirect
	 * @return the faktur indirect that was removed
	 * @throws NoSuchFakturIndirectException if a faktur indirect with the primary key could not be found
	 */
	public FakturIndirect remove(int Id) throws NoSuchFakturIndirectException;

	public FakturIndirect updateImpl(FakturIndirect fakturIndirect);

	/**
	 * Returns the faktur indirect with the primary key or throws a <code>NoSuchFakturIndirectException</code> if it could not be found.
	 *
	 * @param Id the primary key of the faktur indirect
	 * @return the faktur indirect
	 * @throws NoSuchFakturIndirectException if a faktur indirect with the primary key could not be found
	 */
	public FakturIndirect findByPrimaryKey(int Id)
		throws NoSuchFakturIndirectException;

	/**
	 * Returns the faktur indirect with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the faktur indirect
	 * @return the faktur indirect, or <code>null</code> if a faktur indirect with the primary key could not be found
	 */
	public FakturIndirect fetchByPrimaryKey(int Id);

	/**
	 * Returns all the faktur indirects.
	 *
	 * @return the faktur indirects
	 */
	public java.util.List<FakturIndirect> findAll();

	/**
	 * Returns a range of all the faktur indirects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FakturIndirectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur indirects
	 * @param end the upper bound of the range of faktur indirects (not inclusive)
	 * @return the range of faktur indirects
	 */
	public java.util.List<FakturIndirect> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the faktur indirects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FakturIndirectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur indirects
	 * @param end the upper bound of the range of faktur indirects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of faktur indirects
	 */
	public java.util.List<FakturIndirect> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FakturIndirect>
			orderByComparator);

	/**
	 * Returns an ordered range of all the faktur indirects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FakturIndirectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur indirects
	 * @param end the upper bound of the range of faktur indirects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of faktur indirects
	 */
	public java.util.List<FakturIndirect> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FakturIndirect>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the faktur indirects from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of faktur indirects.
	 *
	 * @return the number of faktur indirects
	 */
	public int countAll();

}