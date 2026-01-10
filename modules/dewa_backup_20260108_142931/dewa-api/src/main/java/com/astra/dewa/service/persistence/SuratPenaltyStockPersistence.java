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

import com.astra.dewa.exception.NoSuchSuratPenaltyStockException;
import com.astra.dewa.model.SuratPenaltyStock;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the surat penalty stock service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SuratPenaltyStockUtil
 * @generated
 */
@ProviderType
public interface SuratPenaltyStockPersistence
	extends BasePersistence<SuratPenaltyStock> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SuratPenaltyStockUtil} to access the surat penalty stock persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the surat penalty stock in the entity cache if it is enabled.
	 *
	 * @param suratPenaltyStock the surat penalty stock
	 */
	public void cacheResult(SuratPenaltyStock suratPenaltyStock);

	/**
	 * Caches the surat penalty stocks in the entity cache if it is enabled.
	 *
	 * @param suratPenaltyStocks the surat penalty stocks
	 */
	public void cacheResult(
		java.util.List<SuratPenaltyStock> suratPenaltyStocks);

	/**
	 * Creates a new surat penalty stock with the primary key. Does not add the surat penalty stock to the database.
	 *
	 * @param Id the primary key for the new surat penalty stock
	 * @return the new surat penalty stock
	 */
	public SuratPenaltyStock create(long Id);

	/**
	 * Removes the surat penalty stock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the surat penalty stock
	 * @return the surat penalty stock that was removed
	 * @throws NoSuchSuratPenaltyStockException if a surat penalty stock with the primary key could not be found
	 */
	public SuratPenaltyStock remove(long Id)
		throws NoSuchSuratPenaltyStockException;

	public SuratPenaltyStock updateImpl(SuratPenaltyStock suratPenaltyStock);

	/**
	 * Returns the surat penalty stock with the primary key or throws a <code>NoSuchSuratPenaltyStockException</code> if it could not be found.
	 *
	 * @param Id the primary key of the surat penalty stock
	 * @return the surat penalty stock
	 * @throws NoSuchSuratPenaltyStockException if a surat penalty stock with the primary key could not be found
	 */
	public SuratPenaltyStock findByPrimaryKey(long Id)
		throws NoSuchSuratPenaltyStockException;

	/**
	 * Returns the surat penalty stock with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the surat penalty stock
	 * @return the surat penalty stock, or <code>null</code> if a surat penalty stock with the primary key could not be found
	 */
	public SuratPenaltyStock fetchByPrimaryKey(long Id);

	/**
	 * Returns all the surat penalty stocks.
	 *
	 * @return the surat penalty stocks
	 */
	public java.util.List<SuratPenaltyStock> findAll();

	/**
	 * Returns a range of all the surat penalty stocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SuratPenaltyStockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of surat penalty stocks
	 * @param end the upper bound of the range of surat penalty stocks (not inclusive)
	 * @return the range of surat penalty stocks
	 */
	public java.util.List<SuratPenaltyStock> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the surat penalty stocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SuratPenaltyStockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of surat penalty stocks
	 * @param end the upper bound of the range of surat penalty stocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of surat penalty stocks
	 */
	public java.util.List<SuratPenaltyStock> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SuratPenaltyStock>
			orderByComparator);

	/**
	 * Returns an ordered range of all the surat penalty stocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SuratPenaltyStockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of surat penalty stocks
	 * @param end the upper bound of the range of surat penalty stocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of surat penalty stocks
	 */
	public java.util.List<SuratPenaltyStock> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SuratPenaltyStock>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the surat penalty stocks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of surat penalty stocks.
	 *
	 * @return the number of surat penalty stocks
	 */
	public int countAll();

}