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

import com.astra.dewa.exception.NoSuchSuratPenaltyStockException;
import com.astra.dewa.model.SuratPenaltyStock;
import com.astra.dewa.model.SuratPenaltyStockTable;
import com.astra.dewa.model.impl.SuratPenaltyStockImpl;
import com.astra.dewa.model.impl.SuratPenaltyStockModelImpl;
import com.astra.dewa.service.persistence.SuratPenaltyStockPersistence;
import com.astra.dewa.service.persistence.SuratPenaltyStockUtil;
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
 * The persistence implementation for the surat penalty stock service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SuratPenaltyStockPersistence.class)
public class SuratPenaltyStockPersistenceImpl
	extends BasePersistenceImpl<SuratPenaltyStock>
	implements SuratPenaltyStockPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SuratPenaltyStockUtil</code> to access the surat penalty stock persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SuratPenaltyStockImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public SuratPenaltyStockPersistenceImpl() {
		setModelClass(SuratPenaltyStock.class);

		setModelImplClass(SuratPenaltyStockImpl.class);
		setModelPKClass(long.class);

		setTable(SuratPenaltyStockTable.INSTANCE);
	}

	/**
	 * Caches the surat penalty stock in the entity cache if it is enabled.
	 *
	 * @param suratPenaltyStock the surat penalty stock
	 */
	@Override
	public void cacheResult(SuratPenaltyStock suratPenaltyStock) {
		entityCache.putResult(
			SuratPenaltyStockImpl.class, suratPenaltyStock.getPrimaryKey(),
			suratPenaltyStock);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the surat penalty stocks in the entity cache if it is enabled.
	 *
	 * @param suratPenaltyStocks the surat penalty stocks
	 */
	@Override
	public void cacheResult(List<SuratPenaltyStock> suratPenaltyStocks) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (suratPenaltyStocks.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SuratPenaltyStock suratPenaltyStock : suratPenaltyStocks) {
			if (entityCache.getResult(
					SuratPenaltyStockImpl.class,
					suratPenaltyStock.getPrimaryKey()) == null) {

				cacheResult(suratPenaltyStock);
			}
		}
	}

	/**
	 * Clears the cache for all surat penalty stocks.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SuratPenaltyStockImpl.class);

		finderCache.clearCache(SuratPenaltyStockImpl.class);
	}

	/**
	 * Clears the cache for the surat penalty stock.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SuratPenaltyStock suratPenaltyStock) {
		entityCache.removeResult(
			SuratPenaltyStockImpl.class, suratPenaltyStock);
	}

	@Override
	public void clearCache(List<SuratPenaltyStock> suratPenaltyStocks) {
		for (SuratPenaltyStock suratPenaltyStock : suratPenaltyStocks) {
			entityCache.removeResult(
				SuratPenaltyStockImpl.class, suratPenaltyStock);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SuratPenaltyStockImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SuratPenaltyStockImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new surat penalty stock with the primary key. Does not add the surat penalty stock to the database.
	 *
	 * @param Id the primary key for the new surat penalty stock
	 * @return the new surat penalty stock
	 */
	@Override
	public SuratPenaltyStock create(long Id) {
		SuratPenaltyStock suratPenaltyStock = new SuratPenaltyStockImpl();

		suratPenaltyStock.setNew(true);
		suratPenaltyStock.setPrimaryKey(Id);

		return suratPenaltyStock;
	}

	/**
	 * Removes the surat penalty stock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the surat penalty stock
	 * @return the surat penalty stock that was removed
	 * @throws NoSuchSuratPenaltyStockException if a surat penalty stock with the primary key could not be found
	 */
	@Override
	public SuratPenaltyStock remove(long Id)
		throws NoSuchSuratPenaltyStockException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the surat penalty stock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the surat penalty stock
	 * @return the surat penalty stock that was removed
	 * @throws NoSuchSuratPenaltyStockException if a surat penalty stock with the primary key could not be found
	 */
	@Override
	public SuratPenaltyStock remove(Serializable primaryKey)
		throws NoSuchSuratPenaltyStockException {

		Session session = null;

		try {
			session = openSession();

			SuratPenaltyStock suratPenaltyStock =
				(SuratPenaltyStock)session.get(
					SuratPenaltyStockImpl.class, primaryKey);

			if (suratPenaltyStock == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSuratPenaltyStockException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(suratPenaltyStock);
		}
		catch (NoSuchSuratPenaltyStockException noSuchEntityException) {
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
	protected SuratPenaltyStock removeImpl(
		SuratPenaltyStock suratPenaltyStock) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(suratPenaltyStock)) {
				suratPenaltyStock = (SuratPenaltyStock)session.get(
					SuratPenaltyStockImpl.class,
					suratPenaltyStock.getPrimaryKeyObj());
			}

			if (suratPenaltyStock != null) {
				session.delete(suratPenaltyStock);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (suratPenaltyStock != null) {
			clearCache(suratPenaltyStock);
		}

		return suratPenaltyStock;
	}

	@Override
	public SuratPenaltyStock updateImpl(SuratPenaltyStock suratPenaltyStock) {
		boolean isNew = suratPenaltyStock.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(suratPenaltyStock);
			}
			else {
				suratPenaltyStock = (SuratPenaltyStock)session.merge(
					suratPenaltyStock);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SuratPenaltyStockImpl.class, suratPenaltyStock, false, true);

		if (isNew) {
			suratPenaltyStock.setNew(false);
		}

		suratPenaltyStock.resetOriginalValues();

		return suratPenaltyStock;
	}

	/**
	 * Returns the surat penalty stock with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the surat penalty stock
	 * @return the surat penalty stock
	 * @throws NoSuchSuratPenaltyStockException if a surat penalty stock with the primary key could not be found
	 */
	@Override
	public SuratPenaltyStock findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSuratPenaltyStockException {

		SuratPenaltyStock suratPenaltyStock = fetchByPrimaryKey(primaryKey);

		if (suratPenaltyStock == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSuratPenaltyStockException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return suratPenaltyStock;
	}

	/**
	 * Returns the surat penalty stock with the primary key or throws a <code>NoSuchSuratPenaltyStockException</code> if it could not be found.
	 *
	 * @param Id the primary key of the surat penalty stock
	 * @return the surat penalty stock
	 * @throws NoSuchSuratPenaltyStockException if a surat penalty stock with the primary key could not be found
	 */
	@Override
	public SuratPenaltyStock findByPrimaryKey(long Id)
		throws NoSuchSuratPenaltyStockException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the surat penalty stock with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the surat penalty stock
	 * @return the surat penalty stock, or <code>null</code> if a surat penalty stock with the primary key could not be found
	 */
	@Override
	public SuratPenaltyStock fetchByPrimaryKey(long Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the surat penalty stocks.
	 *
	 * @return the surat penalty stocks
	 */
	@Override
	public List<SuratPenaltyStock> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<SuratPenaltyStock> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<SuratPenaltyStock> findAll(
		int start, int end,
		OrderByComparator<SuratPenaltyStock> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<SuratPenaltyStock> findAll(
		int start, int end,
		OrderByComparator<SuratPenaltyStock> orderByComparator,
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

		List<SuratPenaltyStock> list = null;

		if (useFinderCache) {
			list = (List<SuratPenaltyStock>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SURATPENALTYSTOCK);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SURATPENALTYSTOCK;

				sql = sql.concat(SuratPenaltyStockModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SuratPenaltyStock>)QueryUtil.list(
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
	 * Removes all the surat penalty stocks from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SuratPenaltyStock suratPenaltyStock : findAll()) {
			remove(suratPenaltyStock);
		}
	}

	/**
	 * Returns the number of surat penalty stocks.
	 *
	 * @return the number of surat penalty stocks
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SURATPENALTYSTOCK);

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
		return _SQL_SELECT_SURATPENALTYSTOCK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SuratPenaltyStockModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the surat penalty stock persistence.
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

		_setSuratPenaltyStockUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSuratPenaltyStockUtilPersistence(null);

		entityCache.removeCache(SuratPenaltyStockImpl.class.getName());
	}

	private void _setSuratPenaltyStockUtilPersistence(
		SuratPenaltyStockPersistence suratPenaltyStockPersistence) {

		try {
			Field field = SuratPenaltyStockUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, suratPenaltyStockPersistence);
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

	private static final String _SQL_SELECT_SURATPENALTYSTOCK =
		"SELECT suratPenaltyStock FROM SuratPenaltyStock suratPenaltyStock";

	private static final String _SQL_COUNT_SURATPENALTYSTOCK =
		"SELECT COUNT(suratPenaltyStock) FROM SuratPenaltyStock suratPenaltyStock";

	private static final String _ORDER_BY_ENTITY_ALIAS = "suratPenaltyStock.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SuratPenaltyStock exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		SuratPenaltyStockPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}