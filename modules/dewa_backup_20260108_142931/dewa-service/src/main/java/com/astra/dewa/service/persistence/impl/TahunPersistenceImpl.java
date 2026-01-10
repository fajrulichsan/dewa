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

import com.astra.dewa.exception.NoSuchTahunException;
import com.astra.dewa.model.Tahun;
import com.astra.dewa.model.TahunTable;
import com.astra.dewa.model.impl.TahunImpl;
import com.astra.dewa.model.impl.TahunModelImpl;
import com.astra.dewa.service.persistence.TahunPersistence;
import com.astra.dewa.service.persistence.TahunUtil;

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
 * The persistence implementation for the tahun service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TahunPersistenceImpl
	extends BasePersistenceImpl<Tahun> implements TahunPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TahunUtil</code> to access the tahun persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TahunImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public TahunPersistenceImpl() {
		setModelClass(Tahun.class);

		setModelImplClass(TahunImpl.class);
		setModelPKClass(String.class);

		setTable(TahunTable.INSTANCE);
	}

	/**
	 * Caches the tahun in the entity cache if it is enabled.
	 *
	 * @param tahun the tahun
	 */
	@Override
	public void cacheResult(Tahun tahun) {
		entityCache.putResult(TahunImpl.class, tahun.getPrimaryKey(), tahun);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the tahuns in the entity cache if it is enabled.
	 *
	 * @param tahuns the tahuns
	 */
	@Override
	public void cacheResult(List<Tahun> tahuns) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (tahuns.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Tahun tahun : tahuns) {
			if (entityCache.getResult(TahunImpl.class, tahun.getPrimaryKey()) ==
					null) {

				cacheResult(tahun);
			}
		}
	}

	/**
	 * Clears the cache for all tahuns.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TahunImpl.class);

		finderCache.clearCache(TahunImpl.class);
	}

	/**
	 * Clears the cache for the tahun.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Tahun tahun) {
		entityCache.removeResult(TahunImpl.class, tahun);
	}

	@Override
	public void clearCache(List<Tahun> tahuns) {
		for (Tahun tahun : tahuns) {
			entityCache.removeResult(TahunImpl.class, tahun);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(TahunImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(TahunImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new tahun with the primary key. Does not add the tahun to the database.
	 *
	 * @param Id the primary key for the new tahun
	 * @return the new tahun
	 */
	@Override
	public Tahun create(String Id) {
		Tahun tahun = new TahunImpl();

		tahun.setNew(true);
		tahun.setPrimaryKey(Id);

		return tahun;
	}

	/**
	 * Removes the tahun with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the tahun
	 * @return the tahun that was removed
	 * @throws NoSuchTahunException if a tahun with the primary key could not be found
	 */
	@Override
	public Tahun remove(String Id) throws NoSuchTahunException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the tahun with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the tahun
	 * @return the tahun that was removed
	 * @throws NoSuchTahunException if a tahun with the primary key could not be found
	 */
	@Override
	public Tahun remove(Serializable primaryKey) throws NoSuchTahunException {
		Session session = null;

		try {
			session = openSession();

			Tahun tahun = (Tahun)session.get(TahunImpl.class, primaryKey);

			if (tahun == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTahunException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(tahun);
		}
		catch (NoSuchTahunException noSuchEntityException) {
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
	protected Tahun removeImpl(Tahun tahun) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(tahun)) {
				tahun = (Tahun)session.get(
					TahunImpl.class, tahun.getPrimaryKeyObj());
			}

			if (tahun != null) {
				session.delete(tahun);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (tahun != null) {
			clearCache(tahun);
		}

		return tahun;
	}

	@Override
	public Tahun updateImpl(Tahun tahun) {
		boolean isNew = tahun.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(tahun);
			}
			else {
				tahun = (Tahun)session.merge(tahun);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(TahunImpl.class, tahun, false, true);

		if (isNew) {
			tahun.setNew(false);
		}

		tahun.resetOriginalValues();

		return tahun;
	}

	/**
	 * Returns the tahun with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the tahun
	 * @return the tahun
	 * @throws NoSuchTahunException if a tahun with the primary key could not be found
	 */
	@Override
	public Tahun findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTahunException {

		Tahun tahun = fetchByPrimaryKey(primaryKey);

		if (tahun == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTahunException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return tahun;
	}

	/**
	 * Returns the tahun with the primary key or throws a <code>NoSuchTahunException</code> if it could not be found.
	 *
	 * @param Id the primary key of the tahun
	 * @return the tahun
	 * @throws NoSuchTahunException if a tahun with the primary key could not be found
	 */
	@Override
	public Tahun findByPrimaryKey(String Id) throws NoSuchTahunException {
		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the tahun with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the tahun
	 * @return the tahun, or <code>null</code> if a tahun with the primary key could not be found
	 */
	@Override
	public Tahun fetchByPrimaryKey(String Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the tahuns.
	 *
	 * @return the tahuns
	 */
	@Override
	public List<Tahun> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tahuns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TahunModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tahuns
	 * @param end the upper bound of the range of tahuns (not inclusive)
	 * @return the range of tahuns
	 */
	@Override
	public List<Tahun> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the tahuns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TahunModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tahuns
	 * @param end the upper bound of the range of tahuns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tahuns
	 */
	@Override
	public List<Tahun> findAll(
		int start, int end, OrderByComparator<Tahun> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the tahuns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TahunModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tahuns
	 * @param end the upper bound of the range of tahuns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of tahuns
	 */
	@Override
	public List<Tahun> findAll(
		int start, int end, OrderByComparator<Tahun> orderByComparator,
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

		List<Tahun> list = null;

		if (useFinderCache) {
			list = (List<Tahun>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TAHUN);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TAHUN;

				sql = sql.concat(TahunModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Tahun>)QueryUtil.list(
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
	 * Removes all the tahuns from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Tahun tahun : findAll()) {
			remove(tahun);
		}
	}

	/**
	 * Returns the number of tahuns.
	 *
	 * @return the number of tahuns
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_TAHUN);

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
		return _SQL_SELECT_TAHUN;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TahunModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the tahun persistence.
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

		_setTahunUtilPersistence(this);
	}

	public void destroy() {
		_setTahunUtilPersistence(null);

		entityCache.removeCache(TahunImpl.class.getName());
	}

	private void _setTahunUtilPersistence(TahunPersistence tahunPersistence) {
		try {
			Field field = TahunUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, tahunPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_TAHUN =
		"SELECT tahun FROM Tahun tahun";

	private static final String _SQL_COUNT_TAHUN =
		"SELECT COUNT(tahun) FROM Tahun tahun";

	private static final String _ORDER_BY_ENTITY_ALIAS = "tahun.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Tahun exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		TahunPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}