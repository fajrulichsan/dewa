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

import com.astra.dewa.exception.NoSuchSalesProgramException;
import com.astra.dewa.model.SalesProgram;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the sales program service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SalesProgramUtil
 * @generated
 */
@ProviderType
public interface SalesProgramPersistence extends BasePersistence<SalesProgram> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SalesProgramUtil} to access the sales program persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the sales program in the entity cache if it is enabled.
	 *
	 * @param salesProgram the sales program
	 */
	public void cacheResult(SalesProgram salesProgram);

	/**
	 * Caches the sales programs in the entity cache if it is enabled.
	 *
	 * @param salesPrograms the sales programs
	 */
	public void cacheResult(java.util.List<SalesProgram> salesPrograms);

	/**
	 * Creates a new sales program with the primary key. Does not add the sales program to the database.
	 *
	 * @param Id the primary key for the new sales program
	 * @return the new sales program
	 */
	public SalesProgram create(long Id);

	/**
	 * Removes the sales program with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the sales program
	 * @return the sales program that was removed
	 * @throws NoSuchSalesProgramException if a sales program with the primary key could not be found
	 */
	public SalesProgram remove(long Id) throws NoSuchSalesProgramException;

	public SalesProgram updateImpl(SalesProgram salesProgram);

	/**
	 * Returns the sales program with the primary key or throws a <code>NoSuchSalesProgramException</code> if it could not be found.
	 *
	 * @param Id the primary key of the sales program
	 * @return the sales program
	 * @throws NoSuchSalesProgramException if a sales program with the primary key could not be found
	 */
	public SalesProgram findByPrimaryKey(long Id)
		throws NoSuchSalesProgramException;

	/**
	 * Returns the sales program with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the sales program
	 * @return the sales program, or <code>null</code> if a sales program with the primary key could not be found
	 */
	public SalesProgram fetchByPrimaryKey(long Id);

	/**
	 * Returns all the sales programs.
	 *
	 * @return the sales programs
	 */
	public java.util.List<SalesProgram> findAll();

	/**
	 * Returns a range of all the sales programs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SalesProgramModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sales programs
	 * @param end the upper bound of the range of sales programs (not inclusive)
	 * @return the range of sales programs
	 */
	public java.util.List<SalesProgram> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the sales programs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SalesProgramModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sales programs
	 * @param end the upper bound of the range of sales programs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sales programs
	 */
	public java.util.List<SalesProgram> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesProgram>
			orderByComparator);

	/**
	 * Returns an ordered range of all the sales programs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SalesProgramModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sales programs
	 * @param end the upper bound of the range of sales programs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sales programs
	 */
	public java.util.List<SalesProgram> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesProgram>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the sales programs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of sales programs.
	 *
	 * @return the number of sales programs
	 */
	public int countAll();

}