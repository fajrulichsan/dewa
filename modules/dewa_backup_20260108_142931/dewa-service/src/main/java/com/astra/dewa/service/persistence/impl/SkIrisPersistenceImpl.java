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

import com.astra.dewa.exception.NoSuchSkIrisException;
import com.astra.dewa.model.SkIris;
import com.astra.dewa.model.SkIrisTable;
import com.astra.dewa.model.impl.SkIrisImpl;
import com.astra.dewa.model.impl.SkIrisModelImpl;
import com.astra.dewa.service.persistence.SkIrisPersistence;
import com.astra.dewa.service.persistence.SkIrisUtil;

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
 * The persistence implementation for the sk iris service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SkIrisPersistenceImpl
	extends BasePersistenceImpl<SkIris> implements SkIrisPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SkIrisUtil</code> to access the sk iris persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SkIrisImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public SkIrisPersistenceImpl() {
		setModelClass(SkIris.class);

		setModelImplClass(SkIrisImpl.class);
		setModelPKClass(int.class);

		setTable(SkIrisTable.INSTANCE);
	}

	/**
	 * Caches the sk iris in the entity cache if it is enabled.
	 *
	 * @param skIris the sk iris
	 */
	@Override
	public void cacheResult(SkIris skIris) {
		entityCache.putResult(SkIrisImpl.class, skIris.getPrimaryKey(), skIris);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the sk irises in the entity cache if it is enabled.
	 *
	 * @param skIrises the sk irises
	 */
	@Override
	public void cacheResult(List<SkIris> skIrises) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (skIrises.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SkIris skIris : skIrises) {
			if (entityCache.getResult(
					SkIrisImpl.class, skIris.getPrimaryKey()) == null) {

				cacheResult(skIris);
			}
		}
	}

	/**
	 * Clears the cache for all sk irises.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SkIrisImpl.class);

		finderCache.clearCache(SkIrisImpl.class);
	}

	/**
	 * Clears the cache for the sk iris.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SkIris skIris) {
		entityCache.removeResult(SkIrisImpl.class, skIris);
	}

	@Override
	public void clearCache(List<SkIris> skIrises) {
		for (SkIris skIris : skIrises) {
			entityCache.removeResult(SkIrisImpl.class, skIris);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SkIrisImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SkIrisImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new sk iris with the primary key. Does not add the sk iris to the database.
	 *
	 * @param Id the primary key for the new sk iris
	 * @return the new sk iris
	 */
	@Override
	public SkIris create(int Id) {
		SkIris skIris = new SkIrisImpl();

		skIris.setNew(true);
		skIris.setPrimaryKey(Id);

		return skIris;
	}

	/**
	 * Removes the sk iris with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the sk iris
	 * @return the sk iris that was removed
	 * @throws NoSuchSkIrisException if a sk iris with the primary key could not be found
	 */
	@Override
	public SkIris remove(int Id) throws NoSuchSkIrisException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the sk iris with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sk iris
	 * @return the sk iris that was removed
	 * @throws NoSuchSkIrisException if a sk iris with the primary key could not be found
	 */
	@Override
	public SkIris remove(Serializable primaryKey) throws NoSuchSkIrisException {
		Session session = null;

		try {
			session = openSession();

			SkIris skIris = (SkIris)session.get(SkIrisImpl.class, primaryKey);

			if (skIris == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSkIrisException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(skIris);
		}
		catch (NoSuchSkIrisException noSuchEntityException) {
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
	protected SkIris removeImpl(SkIris skIris) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(skIris)) {
				skIris = (SkIris)session.get(
					SkIrisImpl.class, skIris.getPrimaryKeyObj());
			}

			if (skIris != null) {
				session.delete(skIris);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (skIris != null) {
			clearCache(skIris);
		}

		return skIris;
	}

	@Override
	public SkIris updateImpl(SkIris skIris) {
		boolean isNew = skIris.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(skIris);
			}
			else {
				skIris = (SkIris)session.merge(skIris);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(SkIrisImpl.class, skIris, false, true);

		if (isNew) {
			skIris.setNew(false);
		}

		skIris.resetOriginalValues();

		return skIris;
	}

	/**
	 * Returns the sk iris with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sk iris
	 * @return the sk iris
	 * @throws NoSuchSkIrisException if a sk iris with the primary key could not be found
	 */
	@Override
	public SkIris findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSkIrisException {

		SkIris skIris = fetchByPrimaryKey(primaryKey);

		if (skIris == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSkIrisException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return skIris;
	}

	/**
	 * Returns the sk iris with the primary key or throws a <code>NoSuchSkIrisException</code> if it could not be found.
	 *
	 * @param Id the primary key of the sk iris
	 * @return the sk iris
	 * @throws NoSuchSkIrisException if a sk iris with the primary key could not be found
	 */
	@Override
	public SkIris findByPrimaryKey(int Id) throws NoSuchSkIrisException {
		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the sk iris with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the sk iris
	 * @return the sk iris, or <code>null</code> if a sk iris with the primary key could not be found
	 */
	@Override
	public SkIris fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the sk irises.
	 *
	 * @return the sk irises
	 */
	@Override
	public List<SkIris> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sk irises.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkIrisModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sk irises
	 * @param end the upper bound of the range of sk irises (not inclusive)
	 * @return the range of sk irises
	 */
	@Override
	public List<SkIris> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sk irises.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkIrisModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sk irises
	 * @param end the upper bound of the range of sk irises (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sk irises
	 */
	@Override
	public List<SkIris> findAll(
		int start, int end, OrderByComparator<SkIris> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sk irises.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkIrisModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sk irises
	 * @param end the upper bound of the range of sk irises (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sk irises
	 */
	@Override
	public List<SkIris> findAll(
		int start, int end, OrderByComparator<SkIris> orderByComparator,
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

		List<SkIris> list = null;

		if (useFinderCache) {
			list = (List<SkIris>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SKIRIS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SKIRIS;

				sql = sql.concat(SkIrisModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SkIris>)QueryUtil.list(
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
	 * Removes all the sk irises from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SkIris skIris : findAll()) {
			remove(skIris);
		}
	}

	/**
	 * Returns the number of sk irises.
	 *
	 * @return the number of sk irises
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SKIRIS);

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
		return _SQL_SELECT_SKIRIS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SkIrisModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sk iris persistence.
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

		_setSkIrisUtilPersistence(this);
	}

	public void destroy() {
		_setSkIrisUtilPersistence(null);

		entityCache.removeCache(SkIrisImpl.class.getName());
	}

	private void _setSkIrisUtilPersistence(
		SkIrisPersistence skIrisPersistence) {

		try {
			Field field = SkIrisUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, skIrisPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SKIRIS =
		"SELECT skIris FROM SkIris skIris";

	private static final String _SQL_COUNT_SKIRIS =
		"SELECT COUNT(skIris) FROM SkIris skIris";

	private static final String _ORDER_BY_ENTITY_ALIAS = "skIris.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SkIris exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		SkIrisPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}