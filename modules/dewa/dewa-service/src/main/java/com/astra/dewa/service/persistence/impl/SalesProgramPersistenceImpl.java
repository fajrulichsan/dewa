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

import com.astra.dewa.exception.NoSuchSalesProgramException;
import com.astra.dewa.model.SalesProgram;
import com.astra.dewa.model.SalesProgramTable;
import com.astra.dewa.model.impl.SalesProgramImpl;
import com.astra.dewa.model.impl.SalesProgramModelImpl;
import com.astra.dewa.service.persistence.SalesProgramPersistence;
import com.astra.dewa.service.persistence.SalesProgramUtil;
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
 * The persistence implementation for the sales program service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SalesProgramPersistence.class)
public class SalesProgramPersistenceImpl
	extends BasePersistenceImpl<SalesProgram>
	implements SalesProgramPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SalesProgramUtil</code> to access the sales program persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SalesProgramImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public SalesProgramPersistenceImpl() {
		setModelClass(SalesProgram.class);

		setModelImplClass(SalesProgramImpl.class);
		setModelPKClass(long.class);

		setTable(SalesProgramTable.INSTANCE);
	}

	/**
	 * Caches the sales program in the entity cache if it is enabled.
	 *
	 * @param salesProgram the sales program
	 */
	@Override
	public void cacheResult(SalesProgram salesProgram) {
		entityCache.putResult(
			SalesProgramImpl.class, salesProgram.getPrimaryKey(), salesProgram);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the sales programs in the entity cache if it is enabled.
	 *
	 * @param salesPrograms the sales programs
	 */
	@Override
	public void cacheResult(List<SalesProgram> salesPrograms) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (salesPrograms.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SalesProgram salesProgram : salesPrograms) {
			if (entityCache.getResult(
					SalesProgramImpl.class, salesProgram.getPrimaryKey()) ==
						null) {

				cacheResult(salesProgram);
			}
		}
	}

	/**
	 * Clears the cache for all sales programs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SalesProgramImpl.class);

		finderCache.clearCache(SalesProgramImpl.class);
	}

	/**
	 * Clears the cache for the sales program.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SalesProgram salesProgram) {
		entityCache.removeResult(SalesProgramImpl.class, salesProgram);
	}

	@Override
	public void clearCache(List<SalesProgram> salesPrograms) {
		for (SalesProgram salesProgram : salesPrograms) {
			entityCache.removeResult(SalesProgramImpl.class, salesProgram);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SalesProgramImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SalesProgramImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new sales program with the primary key. Does not add the sales program to the database.
	 *
	 * @param Id the primary key for the new sales program
	 * @return the new sales program
	 */
	@Override
	public SalesProgram create(long Id) {
		SalesProgram salesProgram = new SalesProgramImpl();

		salesProgram.setNew(true);
		salesProgram.setPrimaryKey(Id);

		return salesProgram;
	}

	/**
	 * Removes the sales program with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the sales program
	 * @return the sales program that was removed
	 * @throws NoSuchSalesProgramException if a sales program with the primary key could not be found
	 */
	@Override
	public SalesProgram remove(long Id) throws NoSuchSalesProgramException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the sales program with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sales program
	 * @return the sales program that was removed
	 * @throws NoSuchSalesProgramException if a sales program with the primary key could not be found
	 */
	@Override
	public SalesProgram remove(Serializable primaryKey)
		throws NoSuchSalesProgramException {

		Session session = null;

		try {
			session = openSession();

			SalesProgram salesProgram = (SalesProgram)session.get(
				SalesProgramImpl.class, primaryKey);

			if (salesProgram == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSalesProgramException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(salesProgram);
		}
		catch (NoSuchSalesProgramException noSuchEntityException) {
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
	protected SalesProgram removeImpl(SalesProgram salesProgram) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(salesProgram)) {
				salesProgram = (SalesProgram)session.get(
					SalesProgramImpl.class, salesProgram.getPrimaryKeyObj());
			}

			if (salesProgram != null) {
				session.delete(salesProgram);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (salesProgram != null) {
			clearCache(salesProgram);
		}

		return salesProgram;
	}

	@Override
	public SalesProgram updateImpl(SalesProgram salesProgram) {
		boolean isNew = salesProgram.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(salesProgram);
			}
			else {
				salesProgram = (SalesProgram)session.merge(salesProgram);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SalesProgramImpl.class, salesProgram, false, true);

		if (isNew) {
			salesProgram.setNew(false);
		}

		salesProgram.resetOriginalValues();

		return salesProgram;
	}

	/**
	 * Returns the sales program with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sales program
	 * @return the sales program
	 * @throws NoSuchSalesProgramException if a sales program with the primary key could not be found
	 */
	@Override
	public SalesProgram findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSalesProgramException {

		SalesProgram salesProgram = fetchByPrimaryKey(primaryKey);

		if (salesProgram == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSalesProgramException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return salesProgram;
	}

	/**
	 * Returns the sales program with the primary key or throws a <code>NoSuchSalesProgramException</code> if it could not be found.
	 *
	 * @param Id the primary key of the sales program
	 * @return the sales program
	 * @throws NoSuchSalesProgramException if a sales program with the primary key could not be found
	 */
	@Override
	public SalesProgram findByPrimaryKey(long Id)
		throws NoSuchSalesProgramException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the sales program with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the sales program
	 * @return the sales program, or <code>null</code> if a sales program with the primary key could not be found
	 */
	@Override
	public SalesProgram fetchByPrimaryKey(long Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the sales programs.
	 *
	 * @return the sales programs
	 */
	@Override
	public List<SalesProgram> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<SalesProgram> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<SalesProgram> findAll(
		int start, int end, OrderByComparator<SalesProgram> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<SalesProgram> findAll(
		int start, int end, OrderByComparator<SalesProgram> orderByComparator,
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

		List<SalesProgram> list = null;

		if (useFinderCache) {
			list = (List<SalesProgram>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SALESPROGRAM);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SALESPROGRAM;

				sql = sql.concat(SalesProgramModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SalesProgram>)QueryUtil.list(
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
	 * Removes all the sales programs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SalesProgram salesProgram : findAll()) {
			remove(salesProgram);
		}
	}

	/**
	 * Returns the number of sales programs.
	 *
	 * @return the number of sales programs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SALESPROGRAM);

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
		return _SQL_SELECT_SALESPROGRAM;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SalesProgramModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sales program persistence.
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

		_setSalesProgramUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSalesProgramUtilPersistence(null);

		entityCache.removeCache(SalesProgramImpl.class.getName());
	}

	private void _setSalesProgramUtilPersistence(
		SalesProgramPersistence salesProgramPersistence) {

		try {
			Field field = SalesProgramUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, salesProgramPersistence);
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

	private static final String _SQL_SELECT_SALESPROGRAM =
		"SELECT salesProgram FROM SalesProgram salesProgram";

	private static final String _SQL_COUNT_SALESPROGRAM =
		"SELECT COUNT(salesProgram) FROM SalesProgram salesProgram";

	private static final String _ORDER_BY_ENTITY_ALIAS = "salesProgram.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SalesProgram exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		SalesProgramPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}