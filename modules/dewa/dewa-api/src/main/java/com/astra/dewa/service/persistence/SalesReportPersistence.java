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

import com.astra.dewa.exception.NoSuchSalesReportException;
import com.astra.dewa.model.SalesReport;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the sales report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SalesReportUtil
 * @generated
 */
@ProviderType
public interface SalesReportPersistence extends BasePersistence<SalesReport> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SalesReportUtil} to access the sales report persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the sales report in the entity cache if it is enabled.
	 *
	 * @param salesReport the sales report
	 */
	public void cacheResult(SalesReport salesReport);

	/**
	 * Caches the sales reports in the entity cache if it is enabled.
	 *
	 * @param salesReports the sales reports
	 */
	public void cacheResult(java.util.List<SalesReport> salesReports);

	/**
	 * Creates a new sales report with the primary key. Does not add the sales report to the database.
	 *
	 * @param Id the primary key for the new sales report
	 * @return the new sales report
	 */
	public SalesReport create(int Id);

	/**
	 * Removes the sales report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the sales report
	 * @return the sales report that was removed
	 * @throws NoSuchSalesReportException if a sales report with the primary key could not be found
	 */
	public SalesReport remove(int Id) throws NoSuchSalesReportException;

	public SalesReport updateImpl(SalesReport salesReport);

	/**
	 * Returns the sales report with the primary key or throws a <code>NoSuchSalesReportException</code> if it could not be found.
	 *
	 * @param Id the primary key of the sales report
	 * @return the sales report
	 * @throws NoSuchSalesReportException if a sales report with the primary key could not be found
	 */
	public SalesReport findByPrimaryKey(int Id)
		throws NoSuchSalesReportException;

	/**
	 * Returns the sales report with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the sales report
	 * @return the sales report, or <code>null</code> if a sales report with the primary key could not be found
	 */
	public SalesReport fetchByPrimaryKey(int Id);

	/**
	 * Returns all the sales reports.
	 *
	 * @return the sales reports
	 */
	public java.util.List<SalesReport> findAll();

	/**
	 * Returns a range of all the sales reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SalesReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sales reports
	 * @param end the upper bound of the range of sales reports (not inclusive)
	 * @return the range of sales reports
	 */
	public java.util.List<SalesReport> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the sales reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SalesReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sales reports
	 * @param end the upper bound of the range of sales reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sales reports
	 */
	public java.util.List<SalesReport> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesReport>
			orderByComparator);

	/**
	 * Returns an ordered range of all the sales reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SalesReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sales reports
	 * @param end the upper bound of the range of sales reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sales reports
	 */
	public java.util.List<SalesReport> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesReport>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the sales reports from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of sales reports.
	 *
	 * @return the number of sales reports
	 */
	public int countAll();

}