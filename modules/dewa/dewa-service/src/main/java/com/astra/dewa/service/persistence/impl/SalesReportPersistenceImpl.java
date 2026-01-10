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

package com.astra.dewa.service.persistence.impl;

import com.astra.dewa.exception.NoSuchSalesReportException;
import com.astra.dewa.model.SalesReport;
import com.astra.dewa.model.SalesReportTable;
import com.astra.dewa.model.impl.SalesReportImpl;
import com.astra.dewa.model.impl.SalesReportModelImpl;
import com.astra.dewa.service.persistence.SalesReportPersistence;
import com.astra.dewa.service.persistence.SalesReportUtil;
import com.astra.dewa.service.persistence.impl.constants.DewaPersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the sales report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SalesReportPersistence.class)
public class SalesReportPersistenceImpl
	extends BasePersistenceImpl<SalesReport> implements SalesReportPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SalesReportUtil</code> to access the sales report persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SalesReportImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public SalesReportPersistenceImpl() {
		setModelClass(SalesReport.class);

		setModelImplClass(SalesReportImpl.class);
		setModelPKClass(int.class);

		setTable(SalesReportTable.INSTANCE);
	}

	/**
	 * Caches the sales report in the entity cache if it is enabled.
	 *
	 * @param salesReport the sales report
	 */
	@Override
	public void cacheResult(SalesReport salesReport) {
		entityCache.putResult(
			SalesReportImpl.class, salesReport.getPrimaryKey(), salesReport);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the sales reports in the entity cache if it is enabled.
	 *
	 * @param salesReports the sales reports
	 */
	@Override
	public void cacheResult(List<SalesReport> salesReports) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (salesReports.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SalesReport salesReport : salesReports) {
			if (entityCache.getResult(
					SalesReportImpl.class, salesReport.getPrimaryKey()) ==
						null) {

				cacheResult(salesReport);
			}
		}
	}

	/**
	 * Clears the cache for all sales reports.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SalesReportImpl.class);

		finderCache.clearCache(SalesReportImpl.class);
	}

	/**
	 * Clears the cache for the sales report.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SalesReport salesReport) {
		entityCache.removeResult(SalesReportImpl.class, salesReport);
	}

	@Override
	public void clearCache(List<SalesReport> salesReports) {
		for (SalesReport salesReport : salesReports) {
			entityCache.removeResult(SalesReportImpl.class, salesReport);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SalesReportImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SalesReportImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new sales report with the primary key. Does not add the sales report to the database.
	 *
	 * @param Id the primary key for the new sales report
	 * @return the new sales report
	 */
	@Override
	public SalesReport create(int Id) {
		SalesReport salesReport = new SalesReportImpl();

		salesReport.setNew(true);
		salesReport.setPrimaryKey(Id);

		return salesReport;
	}

	/**
	 * Removes the sales report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the sales report
	 * @return the sales report that was removed
	 * @throws NoSuchSalesReportException if a sales report with the primary key could not be found
	 */
	@Override
	public SalesReport remove(int Id) throws NoSuchSalesReportException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the sales report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sales report
	 * @return the sales report that was removed
	 * @throws NoSuchSalesReportException if a sales report with the primary key could not be found
	 */
	@Override
	public SalesReport remove(Serializable primaryKey)
		throws NoSuchSalesReportException {

		Session session = null;

		try {
			session = openSession();

			SalesReport salesReport = (SalesReport)session.get(
				SalesReportImpl.class, primaryKey);

			if (salesReport == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSalesReportException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(salesReport);
		}
		catch (NoSuchSalesReportException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected SalesReport removeImpl(SalesReport salesReport) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(salesReport)) {
				salesReport = (SalesReport)session.get(
					SalesReportImpl.class, salesReport.getPrimaryKeyObj());
			}

			if (salesReport != null) {
				session.delete(salesReport);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (salesReport != null) {
			clearCache(salesReport);
		}

		return salesReport;
	}

	@Override
	public SalesReport updateImpl(SalesReport salesReport) {
		boolean isNew = salesReport.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(salesReport);
			}
			else {
				salesReport = (SalesReport)session.merge(salesReport);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(SalesReportImpl.class, salesReport, false, true);

		if (isNew) {
			salesReport.setNew(false);
		}

		salesReport.resetOriginalValues();

		return salesReport;
	}

	/**
	 * Returns the sales report with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sales report
	 * @return the sales report
	 * @throws NoSuchSalesReportException if a sales report with the primary key could not be found
	 */
	@Override
	public SalesReport findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSalesReportException {

		SalesReport salesReport = fetchByPrimaryKey(primaryKey);

		if (salesReport == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSalesReportException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return salesReport;
	}

	/**
	 * Returns the sales report with the primary key or throws a <code>NoSuchSalesReportException</code> if it could not be found.
	 *
	 * @param Id the primary key of the sales report
	 * @return the sales report
	 * @throws NoSuchSalesReportException if a sales report with the primary key could not be found
	 */
	@Override
	public SalesReport findByPrimaryKey(int Id)
		throws NoSuchSalesReportException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the sales report with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the sales report
	 * @return the sales report, or <code>null</code> if a sales report with the primary key could not be found
	 */
	@Override
	public SalesReport fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the sales reports.
	 *
	 * @return the sales reports
	 */
	@Override
	public List<SalesReport> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<SalesReport> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<SalesReport> findAll(
		int start, int end, OrderByComparator<SalesReport> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<SalesReport> findAll(
		int start, int end, OrderByComparator<SalesReport> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<SalesReport> list = null;

		if (useFinderCache) {
			list = (List<SalesReport>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SALESREPORT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SALESREPORT;

				sql = sql.concat(SalesReportModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SalesReport>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the sales reports from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SalesReport salesReport : findAll()) {
			remove(salesReport);
		}
	}

	/**
	 * Returns the number of sales reports.
	 *
	 * @return the number of sales reports
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SALESREPORT);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "Id";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SALESREPORT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SalesReportModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sales report persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_setSalesReportUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSalesReportUtilPersistence(null);

		entityCache.removeCache(SalesReportImpl.class.getName());
	}

	private void _setSalesReportUtilPersistence(
		SalesReportPersistence salesReportPersistence) {

		try {
			Field field = SalesReportUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, salesReportPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = DewaPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = DewaPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = DewaPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SALESREPORT =
		"SELECT salesReport FROM SalesReport salesReport";

	private static final String _SQL_COUNT_SALESREPORT =
		"SELECT COUNT(salesReport) FROM SalesReport salesReport";

	private static final String _ORDER_BY_ENTITY_ALIAS = "salesReport.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SalesReport exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		SalesReportPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}