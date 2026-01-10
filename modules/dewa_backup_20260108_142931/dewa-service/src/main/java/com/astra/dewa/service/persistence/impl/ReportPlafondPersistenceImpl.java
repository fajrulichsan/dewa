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

import com.astra.dewa.exception.NoSuchReportPlafondException;
import com.astra.dewa.model.ReportPlafond;
import com.astra.dewa.model.ReportPlafondTable;
import com.astra.dewa.model.impl.ReportPlafondImpl;
import com.astra.dewa.model.impl.ReportPlafondModelImpl;
import com.astra.dewa.service.persistence.ReportPlafondPersistence;
import com.astra.dewa.service.persistence.ReportPlafondUtil;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the report plafond service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ReportPlafondPersistenceImpl
	extends BasePersistenceImpl<ReportPlafond>
	implements ReportPlafondPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ReportPlafondUtil</code> to access the report plafond persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ReportPlafondImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ReportPlafondPersistenceImpl() {
		setModelClass(ReportPlafond.class);

		setModelImplClass(ReportPlafondImpl.class);
		setModelPKClass(int.class);

		setTable(ReportPlafondTable.INSTANCE);
	}

	/**
	 * Caches the report plafond in the entity cache if it is enabled.
	 *
	 * @param reportPlafond the report plafond
	 */
	@Override
	public void cacheResult(ReportPlafond reportPlafond) {
		entityCache.putResult(
			ReportPlafondImpl.class, reportPlafond.getPrimaryKey(),
			reportPlafond);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the report plafonds in the entity cache if it is enabled.
	 *
	 * @param reportPlafonds the report plafonds
	 */
	@Override
	public void cacheResult(List<ReportPlafond> reportPlafonds) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (reportPlafonds.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ReportPlafond reportPlafond : reportPlafonds) {
			if (entityCache.getResult(
					ReportPlafondImpl.class, reportPlafond.getPrimaryKey()) ==
						null) {

				cacheResult(reportPlafond);
			}
		}
	}

	/**
	 * Clears the cache for all report plafonds.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReportPlafondImpl.class);

		finderCache.clearCache(ReportPlafondImpl.class);
	}

	/**
	 * Clears the cache for the report plafond.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReportPlafond reportPlafond) {
		entityCache.removeResult(ReportPlafondImpl.class, reportPlafond);
	}

	@Override
	public void clearCache(List<ReportPlafond> reportPlafonds) {
		for (ReportPlafond reportPlafond : reportPlafonds) {
			entityCache.removeResult(ReportPlafondImpl.class, reportPlafond);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ReportPlafondImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ReportPlafondImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new report plafond with the primary key. Does not add the report plafond to the database.
	 *
	 * @param Id the primary key for the new report plafond
	 * @return the new report plafond
	 */
	@Override
	public ReportPlafond create(int Id) {
		ReportPlafond reportPlafond = new ReportPlafondImpl();

		reportPlafond.setNew(true);
		reportPlafond.setPrimaryKey(Id);

		return reportPlafond;
	}

	/**
	 * Removes the report plafond with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the report plafond
	 * @return the report plafond that was removed
	 * @throws NoSuchReportPlafondException if a report plafond with the primary key could not be found
	 */
	@Override
	public ReportPlafond remove(int Id) throws NoSuchReportPlafondException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the report plafond with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the report plafond
	 * @return the report plafond that was removed
	 * @throws NoSuchReportPlafondException if a report plafond with the primary key could not be found
	 */
	@Override
	public ReportPlafond remove(Serializable primaryKey)
		throws NoSuchReportPlafondException {

		Session session = null;

		try {
			session = openSession();

			ReportPlafond reportPlafond = (ReportPlafond)session.get(
				ReportPlafondImpl.class, primaryKey);

			if (reportPlafond == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReportPlafondException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(reportPlafond);
		}
		catch (NoSuchReportPlafondException noSuchEntityException) {
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
	protected ReportPlafond removeImpl(ReportPlafond reportPlafond) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(reportPlafond)) {
				reportPlafond = (ReportPlafond)session.get(
					ReportPlafondImpl.class, reportPlafond.getPrimaryKeyObj());
			}

			if (reportPlafond != null) {
				session.delete(reportPlafond);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (reportPlafond != null) {
			clearCache(reportPlafond);
		}

		return reportPlafond;
	}

	@Override
	public ReportPlafond updateImpl(ReportPlafond reportPlafond) {
		boolean isNew = reportPlafond.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(reportPlafond);
			}
			else {
				reportPlafond = (ReportPlafond)session.merge(reportPlafond);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ReportPlafondImpl.class, reportPlafond, false, true);

		if (isNew) {
			reportPlafond.setNew(false);
		}

		reportPlafond.resetOriginalValues();

		return reportPlafond;
	}

	/**
	 * Returns the report plafond with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the report plafond
	 * @return the report plafond
	 * @throws NoSuchReportPlafondException if a report plafond with the primary key could not be found
	 */
	@Override
	public ReportPlafond findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReportPlafondException {

		ReportPlafond reportPlafond = fetchByPrimaryKey(primaryKey);

		if (reportPlafond == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReportPlafondException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return reportPlafond;
	}

	/**
	 * Returns the report plafond with the primary key or throws a <code>NoSuchReportPlafondException</code> if it could not be found.
	 *
	 * @param Id the primary key of the report plafond
	 * @return the report plafond
	 * @throws NoSuchReportPlafondException if a report plafond with the primary key could not be found
	 */
	@Override
	public ReportPlafond findByPrimaryKey(int Id)
		throws NoSuchReportPlafondException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the report plafond with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the report plafond
	 * @return the report plafond, or <code>null</code> if a report plafond with the primary key could not be found
	 */
	@Override
	public ReportPlafond fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the report plafonds.
	 *
	 * @return the report plafonds
	 */
	@Override
	public List<ReportPlafond> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<ReportPlafond> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<ReportPlafond> findAll(
		int start, int end,
		OrderByComparator<ReportPlafond> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<ReportPlafond> findAll(
		int start, int end, OrderByComparator<ReportPlafond> orderByComparator,
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

		List<ReportPlafond> list = null;

		if (useFinderCache) {
			list = (List<ReportPlafond>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_REPORTPLAFOND);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_REPORTPLAFOND;

				sql = sql.concat(ReportPlafondModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ReportPlafond>)QueryUtil.list(
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
	 * Removes all the report plafonds from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReportPlafond reportPlafond : findAll()) {
			remove(reportPlafond);
		}
	}

	/**
	 * Returns the number of report plafonds.
	 *
	 * @return the number of report plafonds
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_REPORTPLAFOND);

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
		return _SQL_SELECT_REPORTPLAFOND;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ReportPlafondModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the report plafond persistence.
	 */
	public void afterPropertiesSet() {
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

		_setReportPlafondUtilPersistence(this);
	}

	public void destroy() {
		_setReportPlafondUtilPersistence(null);

		entityCache.removeCache(ReportPlafondImpl.class.getName());
	}

	private void _setReportPlafondUtilPersistence(
		ReportPlafondPersistence reportPlafondPersistence) {

		try {
			Field field = ReportPlafondUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, reportPlafondPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_REPORTPLAFOND =
		"SELECT reportPlafond FROM ReportPlafond reportPlafond";

	private static final String _SQL_COUNT_REPORTPLAFOND =
		"SELECT COUNT(reportPlafond) FROM ReportPlafond reportPlafond";

	private static final String _ORDER_BY_ENTITY_ALIAS = "reportPlafond.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ReportPlafond exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ReportPlafondPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}