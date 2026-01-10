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

import com.astra.dewa.exception.NoSuchReportPlafondException;
import com.astra.dewa.model.ReportPlafond;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the report plafond service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReportPlafondUtil
 * @generated
 */
@ProviderType
public interface ReportPlafondPersistence
	extends BasePersistence<ReportPlafond> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReportPlafondUtil} to access the report plafond persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the report plafond in the entity cache if it is enabled.
	 *
	 * @param reportPlafond the report plafond
	 */
	public void cacheResult(ReportPlafond reportPlafond);

	/**
	 * Caches the report plafonds in the entity cache if it is enabled.
	 *
	 * @param reportPlafonds the report plafonds
	 */
	public void cacheResult(java.util.List<ReportPlafond> reportPlafonds);

	/**
	 * Creates a new report plafond with the primary key. Does not add the report plafond to the database.
	 *
	 * @param Id the primary key for the new report plafond
	 * @return the new report plafond
	 */
	public ReportPlafond create(int Id);

	/**
	 * Removes the report plafond with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the report plafond
	 * @return the report plafond that was removed
	 * @throws NoSuchReportPlafondException if a report plafond with the primary key could not be found
	 */
	public ReportPlafond remove(int Id) throws NoSuchReportPlafondException;

	public ReportPlafond updateImpl(ReportPlafond reportPlafond);

	/**
	 * Returns the report plafond with the primary key or throws a <code>NoSuchReportPlafondException</code> if it could not be found.
	 *
	 * @param Id the primary key of the report plafond
	 * @return the report plafond
	 * @throws NoSuchReportPlafondException if a report plafond with the primary key could not be found
	 */
	public ReportPlafond findByPrimaryKey(int Id)
		throws NoSuchReportPlafondException;

	/**
	 * Returns the report plafond with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the report plafond
	 * @return the report plafond, or <code>null</code> if a report plafond with the primary key could not be found
	 */
	public ReportPlafond fetchByPrimaryKey(int Id);

	/**
	 * Returns all the report plafonds.
	 *
	 * @return the report plafonds
	 */
	public java.util.List<ReportPlafond> findAll();

	/**
	 * Returns a range of all the report plafonds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReportPlafondModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of report plafonds
	 * @param end the upper bound of the range of report plafonds (not inclusive)
	 * @return the range of report plafonds
	 */
	public java.util.List<ReportPlafond> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the report plafonds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReportPlafondModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of report plafonds
	 * @param end the upper bound of the range of report plafonds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of report plafonds
	 */
	public java.util.List<ReportPlafond> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportPlafond>
			orderByComparator);

	/**
	 * Returns an ordered range of all the report plafonds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReportPlafondModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of report plafonds
	 * @param end the upper bound of the range of report plafonds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of report plafonds
	 */
	public java.util.List<ReportPlafond> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportPlafond>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the report plafonds from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of report plafonds.
	 *
	 * @return the number of report plafonds
	 */
	public int countAll();

}