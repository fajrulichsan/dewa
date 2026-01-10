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

import com.astra.dewa.exception.NoSuchBulanException;
import com.astra.dewa.model.Bulan;
import com.astra.dewa.model.BulanTable;
import com.astra.dewa.model.impl.BulanImpl;
import com.astra.dewa.model.impl.BulanModelImpl;
import com.astra.dewa.service.persistence.BulanPersistence;
import com.astra.dewa.service.persistence.BulanUtil;

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
 * The persistence implementation for the bulan service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class BulanPersistenceImpl
	extends BasePersistenceImpl<Bulan> implements BulanPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>BulanUtil</code> to access the bulan persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		BulanImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public BulanPersistenceImpl() {
		setModelClass(Bulan.class);

		setModelImplClass(BulanImpl.class);
		setModelPKClass(String.class);

		setTable(BulanTable.INSTANCE);
	}

	/**
	 * Caches the bulan in the entity cache if it is enabled.
	 *
	 * @param bulan the bulan
	 */
	@Override
	public void cacheResult(Bulan bulan) {
		entityCache.putResult(BulanImpl.class, bulan.getPrimaryKey(), bulan);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the bulans in the entity cache if it is enabled.
	 *
	 * @param bulans the bulans
	 */
	@Override
	public void cacheResult(List<Bulan> bulans) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (bulans.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Bulan bulan : bulans) {
			if (entityCache.getResult(BulanImpl.class, bulan.getPrimaryKey()) ==
					null) {

				cacheResult(bulan);
			}
		}
	}

	/**
	 * Clears the cache for all bulans.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BulanImpl.class);

		finderCache.clearCache(BulanImpl.class);
	}

	/**
	 * Clears the cache for the bulan.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Bulan bulan) {
		entityCache.removeResult(BulanImpl.class, bulan);
	}

	@Override
	public void clearCache(List<Bulan> bulans) {
		for (Bulan bulan : bulans) {
			entityCache.removeResult(BulanImpl.class, bulan);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(BulanImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(BulanImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new bulan with the primary key. Does not add the bulan to the database.
	 *
	 * @param Id the primary key for the new bulan
	 * @return the new bulan
	 */
	@Override
	public Bulan create(String Id) {
		Bulan bulan = new BulanImpl();

		bulan.setNew(true);
		bulan.setPrimaryKey(Id);

		return bulan;
	}

	/**
	 * Removes the bulan with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the bulan
	 * @return the bulan that was removed
	 * @throws NoSuchBulanException if a bulan with the primary key could not be found
	 */
	@Override
	public Bulan remove(String Id) throws NoSuchBulanException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the bulan with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the bulan
	 * @return the bulan that was removed
	 * @throws NoSuchBulanException if a bulan with the primary key could not be found
	 */
	@Override
	public Bulan remove(Serializable primaryKey) throws NoSuchBulanException {
		Session session = null;

		try {
			session = openSession();

			Bulan bulan = (Bulan)session.get(BulanImpl.class, primaryKey);

			if (bulan == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBulanException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(bulan);
		}
		catch (NoSuchBulanException noSuchEntityException) {
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
	protected Bulan removeImpl(Bulan bulan) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(bulan)) {
				bulan = (Bulan)session.get(
					BulanImpl.class, bulan.getPrimaryKeyObj());
			}

			if (bulan != null) {
				session.delete(bulan);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (bulan != null) {
			clearCache(bulan);
		}

		return bulan;
	}

	@Override
	public Bulan updateImpl(Bulan bulan) {
		boolean isNew = bulan.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(bulan);
			}
			else {
				bulan = (Bulan)session.merge(bulan);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(BulanImpl.class, bulan, false, true);

		if (isNew) {
			bulan.setNew(false);
		}

		bulan.resetOriginalValues();

		return bulan;
	}

	/**
	 * Returns the bulan with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the bulan
	 * @return the bulan
	 * @throws NoSuchBulanException if a bulan with the primary key could not be found
	 */
	@Override
	public Bulan findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBulanException {

		Bulan bulan = fetchByPrimaryKey(primaryKey);

		if (bulan == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBulanException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return bulan;
	}

	/**
	 * Returns the bulan with the primary key or throws a <code>NoSuchBulanException</code> if it could not be found.
	 *
	 * @param Id the primary key of the bulan
	 * @return the bulan
	 * @throws NoSuchBulanException if a bulan with the primary key could not be found
	 */
	@Override
	public Bulan findByPrimaryKey(String Id) throws NoSuchBulanException {
		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the bulan with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the bulan
	 * @return the bulan, or <code>null</code> if a bulan with the primary key could not be found
	 */
	@Override
	public Bulan fetchByPrimaryKey(String Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the bulans.
	 *
	 * @return the bulans
	 */
	@Override
	public List<Bulan> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bulans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BulanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bulans
	 * @param end the upper bound of the range of bulans (not inclusive)
	 * @return the range of bulans
	 */
	@Override
	public List<Bulan> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the bulans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BulanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bulans
	 * @param end the upper bound of the range of bulans (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of bulans
	 */
	@Override
	public List<Bulan> findAll(
		int start, int end, OrderByComparator<Bulan> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bulans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BulanModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bulans
	 * @param end the upper bound of the range of bulans (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of bulans
	 */
	@Override
	public List<Bulan> findAll(
		int start, int end, OrderByComparator<Bulan> orderByComparator,
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

		List<Bulan> list = null;

		if (useFinderCache) {
			list = (List<Bulan>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_BULAN);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_BULAN;

				sql = sql.concat(BulanModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Bulan>)QueryUtil.list(
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
	 * Removes all the bulans from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Bulan bulan : findAll()) {
			remove(bulan);
		}
	}

	/**
	 * Returns the number of bulans.
	 *
	 * @return the number of bulans
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_BULAN);

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
		return _SQL_SELECT_BULAN;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return BulanModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the bulan persistence.
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

		_setBulanUtilPersistence(this);
	}

	public void destroy() {
		_setBulanUtilPersistence(null);

		entityCache.removeCache(BulanImpl.class.getName());
	}

	private void _setBulanUtilPersistence(BulanPersistence bulanPersistence) {
		try {
			Field field = BulanUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, bulanPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_BULAN =
		"SELECT bulan FROM Bulan bulan";

	private static final String _SQL_COUNT_BULAN =
		"SELECT COUNT(bulan) FROM Bulan bulan";

	private static final String _ORDER_BY_ENTITY_ALIAS = "bulan.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Bulan exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		BulanPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}