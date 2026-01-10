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

import com.astra.dewa.exception.NoSuchCabangException;
import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.CabangTable;
import com.astra.dewa.model.impl.CabangImpl;
import com.astra.dewa.model.impl.CabangModelImpl;
import com.astra.dewa.service.persistence.CabangPersistence;
import com.astra.dewa.service.persistence.CabangUtil;

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
 * The persistence implementation for the cabang service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CabangPersistenceImpl
	extends BasePersistenceImpl<Cabang> implements CabangPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CabangUtil</code> to access the cabang persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CabangImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public CabangPersistenceImpl() {
		setModelClass(Cabang.class);

		setModelImplClass(CabangImpl.class);
		setModelPKClass(int.class);

		setTable(CabangTable.INSTANCE);
	}

	/**
	 * Caches the cabang in the entity cache if it is enabled.
	 *
	 * @param cabang the cabang
	 */
	@Override
	public void cacheResult(Cabang cabang) {
		entityCache.putResult(CabangImpl.class, cabang.getPrimaryKey(), cabang);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the cabangs in the entity cache if it is enabled.
	 *
	 * @param cabangs the cabangs
	 */
	@Override
	public void cacheResult(List<Cabang> cabangs) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (cabangs.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Cabang cabang : cabangs) {
			if (entityCache.getResult(
					CabangImpl.class, cabang.getPrimaryKey()) == null) {

				cacheResult(cabang);
			}
		}
	}

	/**
	 * Clears the cache for all cabangs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CabangImpl.class);

		finderCache.clearCache(CabangImpl.class);
	}

	/**
	 * Clears the cache for the cabang.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Cabang cabang) {
		entityCache.removeResult(CabangImpl.class, cabang);
	}

	@Override
	public void clearCache(List<Cabang> cabangs) {
		for (Cabang cabang : cabangs) {
			entityCache.removeResult(CabangImpl.class, cabang);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CabangImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CabangImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new cabang with the primary key. Does not add the cabang to the database.
	 *
	 * @param Id the primary key for the new cabang
	 * @return the new cabang
	 */
	@Override
	public Cabang create(int Id) {
		Cabang cabang = new CabangImpl();

		cabang.setNew(true);
		cabang.setPrimaryKey(Id);

		return cabang;
	}

	/**
	 * Removes the cabang with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the cabang
	 * @return the cabang that was removed
	 * @throws NoSuchCabangException if a cabang with the primary key could not be found
	 */
	@Override
	public Cabang remove(int Id) throws NoSuchCabangException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the cabang with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cabang
	 * @return the cabang that was removed
	 * @throws NoSuchCabangException if a cabang with the primary key could not be found
	 */
	@Override
	public Cabang remove(Serializable primaryKey) throws NoSuchCabangException {
		Session session = null;

		try {
			session = openSession();

			Cabang cabang = (Cabang)session.get(CabangImpl.class, primaryKey);

			if (cabang == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCabangException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cabang);
		}
		catch (NoSuchCabangException noSuchEntityException) {
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
	protected Cabang removeImpl(Cabang cabang) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cabang)) {
				cabang = (Cabang)session.get(
					CabangImpl.class, cabang.getPrimaryKeyObj());
			}

			if (cabang != null) {
				session.delete(cabang);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (cabang != null) {
			clearCache(cabang);
		}

		return cabang;
	}

	@Override
	public Cabang updateImpl(Cabang cabang) {
		boolean isNew = cabang.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(cabang);
			}
			else {
				cabang = (Cabang)session.merge(cabang);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(CabangImpl.class, cabang, false, true);

		if (isNew) {
			cabang.setNew(false);
		}

		cabang.resetOriginalValues();

		return cabang;
	}

	/**
	 * Returns the cabang with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cabang
	 * @return the cabang
	 * @throws NoSuchCabangException if a cabang with the primary key could not be found
	 */
	@Override
	public Cabang findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCabangException {

		Cabang cabang = fetchByPrimaryKey(primaryKey);

		if (cabang == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCabangException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cabang;
	}

	/**
	 * Returns the cabang with the primary key or throws a <code>NoSuchCabangException</code> if it could not be found.
	 *
	 * @param Id the primary key of the cabang
	 * @return the cabang
	 * @throws NoSuchCabangException if a cabang with the primary key could not be found
	 */
	@Override
	public Cabang findByPrimaryKey(int Id) throws NoSuchCabangException {
		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the cabang with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the cabang
	 * @return the cabang, or <code>null</code> if a cabang with the primary key could not be found
	 */
	@Override
	public Cabang fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the cabangs.
	 *
	 * @return the cabangs
	 */
	@Override
	public List<Cabang> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cabangs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CabangModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cabangs
	 * @param end the upper bound of the range of cabangs (not inclusive)
	 * @return the range of cabangs
	 */
	@Override
	public List<Cabang> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cabangs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CabangModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cabangs
	 * @param end the upper bound of the range of cabangs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cabangs
	 */
	@Override
	public List<Cabang> findAll(
		int start, int end, OrderByComparator<Cabang> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cabangs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CabangModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cabangs
	 * @param end the upper bound of the range of cabangs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cabangs
	 */
	@Override
	public List<Cabang> findAll(
		int start, int end, OrderByComparator<Cabang> orderByComparator,
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

		List<Cabang> list = null;

		if (useFinderCache) {
			list = (List<Cabang>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CABANG);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CABANG;

				sql = sql.concat(CabangModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Cabang>)QueryUtil.list(
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
	 * Removes all the cabangs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Cabang cabang : findAll()) {
			remove(cabang);
		}
	}

	/**
	 * Returns the number of cabangs.
	 *
	 * @return the number of cabangs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_CABANG);

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
		return _SQL_SELECT_CABANG;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CabangModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cabang persistence.
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

		_setCabangUtilPersistence(this);
	}

	public void destroy() {
		_setCabangUtilPersistence(null);

		entityCache.removeCache(CabangImpl.class.getName());
	}

	private void _setCabangUtilPersistence(
		CabangPersistence cabangPersistence) {

		try {
			Field field = CabangUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, cabangPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CABANG =
		"SELECT cabang FROM Cabang cabang";

	private static final String _SQL_COUNT_CABANG =
		"SELECT COUNT(cabang) FROM Cabang cabang";

	private static final String _ORDER_BY_ENTITY_ALIAS = "cabang.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Cabang exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		CabangPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}