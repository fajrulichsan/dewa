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

import com.astra.dewa.exception.NoSuchDiskonFleetException;
import com.astra.dewa.model.DiskonFleet;
import com.astra.dewa.model.DiskonFleetTable;
import com.astra.dewa.model.impl.DiskonFleetImpl;
import com.astra.dewa.model.impl.DiskonFleetModelImpl;
import com.astra.dewa.service.persistence.DiskonFleetPersistence;
import com.astra.dewa.service.persistence.DiskonFleetUtil;

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
 * The persistence implementation for the diskon fleet service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DiskonFleetPersistenceImpl
	extends BasePersistenceImpl<DiskonFleet> implements DiskonFleetPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DiskonFleetUtil</code> to access the diskon fleet persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DiskonFleetImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public DiskonFleetPersistenceImpl() {
		setModelClass(DiskonFleet.class);

		setModelImplClass(DiskonFleetImpl.class);
		setModelPKClass(int.class);

		setTable(DiskonFleetTable.INSTANCE);
	}

	/**
	 * Caches the diskon fleet in the entity cache if it is enabled.
	 *
	 * @param diskonFleet the diskon fleet
	 */
	@Override
	public void cacheResult(DiskonFleet diskonFleet) {
		entityCache.putResult(
			DiskonFleetImpl.class, diskonFleet.getPrimaryKey(), diskonFleet);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the diskon fleets in the entity cache if it is enabled.
	 *
	 * @param diskonFleets the diskon fleets
	 */
	@Override
	public void cacheResult(List<DiskonFleet> diskonFleets) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (diskonFleets.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DiskonFleet diskonFleet : diskonFleets) {
			if (entityCache.getResult(
					DiskonFleetImpl.class, diskonFleet.getPrimaryKey()) ==
						null) {

				cacheResult(diskonFleet);
			}
		}
	}

	/**
	 * Clears the cache for all diskon fleets.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DiskonFleetImpl.class);

		finderCache.clearCache(DiskonFleetImpl.class);
	}

	/**
	 * Clears the cache for the diskon fleet.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DiskonFleet diskonFleet) {
		entityCache.removeResult(DiskonFleetImpl.class, diskonFleet);
	}

	@Override
	public void clearCache(List<DiskonFleet> diskonFleets) {
		for (DiskonFleet diskonFleet : diskonFleets) {
			entityCache.removeResult(DiskonFleetImpl.class, diskonFleet);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DiskonFleetImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DiskonFleetImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new diskon fleet with the primary key. Does not add the diskon fleet to the database.
	 *
	 * @param Id the primary key for the new diskon fleet
	 * @return the new diskon fleet
	 */
	@Override
	public DiskonFleet create(int Id) {
		DiskonFleet diskonFleet = new DiskonFleetImpl();

		diskonFleet.setNew(true);
		diskonFleet.setPrimaryKey(Id);

		return diskonFleet;
	}

	/**
	 * Removes the diskon fleet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the diskon fleet
	 * @return the diskon fleet that was removed
	 * @throws NoSuchDiskonFleetException if a diskon fleet with the primary key could not be found
	 */
	@Override
	public DiskonFleet remove(int Id) throws NoSuchDiskonFleetException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the diskon fleet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the diskon fleet
	 * @return the diskon fleet that was removed
	 * @throws NoSuchDiskonFleetException if a diskon fleet with the primary key could not be found
	 */
	@Override
	public DiskonFleet remove(Serializable primaryKey)
		throws NoSuchDiskonFleetException {

		Session session = null;

		try {
			session = openSession();

			DiskonFleet diskonFleet = (DiskonFleet)session.get(
				DiskonFleetImpl.class, primaryKey);

			if (diskonFleet == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDiskonFleetException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(diskonFleet);
		}
		catch (NoSuchDiskonFleetException noSuchEntityException) {
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
	protected DiskonFleet removeImpl(DiskonFleet diskonFleet) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(diskonFleet)) {
				diskonFleet = (DiskonFleet)session.get(
					DiskonFleetImpl.class, diskonFleet.getPrimaryKeyObj());
			}

			if (diskonFleet != null) {
				session.delete(diskonFleet);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (diskonFleet != null) {
			clearCache(diskonFleet);
		}

		return diskonFleet;
	}

	@Override
	public DiskonFleet updateImpl(DiskonFleet diskonFleet) {
		boolean isNew = diskonFleet.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(diskonFleet);
			}
			else {
				diskonFleet = (DiskonFleet)session.merge(diskonFleet);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(DiskonFleetImpl.class, diskonFleet, false, true);

		if (isNew) {
			diskonFleet.setNew(false);
		}

		diskonFleet.resetOriginalValues();

		return diskonFleet;
	}

	/**
	 * Returns the diskon fleet with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the diskon fleet
	 * @return the diskon fleet
	 * @throws NoSuchDiskonFleetException if a diskon fleet with the primary key could not be found
	 */
	@Override
	public DiskonFleet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDiskonFleetException {

		DiskonFleet diskonFleet = fetchByPrimaryKey(primaryKey);

		if (diskonFleet == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDiskonFleetException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return diskonFleet;
	}

	/**
	 * Returns the diskon fleet with the primary key or throws a <code>NoSuchDiskonFleetException</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon fleet
	 * @return the diskon fleet
	 * @throws NoSuchDiskonFleetException if a diskon fleet with the primary key could not be found
	 */
	@Override
	public DiskonFleet findByPrimaryKey(int Id)
		throws NoSuchDiskonFleetException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the diskon fleet with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon fleet
	 * @return the diskon fleet, or <code>null</code> if a diskon fleet with the primary key could not be found
	 */
	@Override
	public DiskonFleet fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the diskon fleets.
	 *
	 * @return the diskon fleets
	 */
	@Override
	public List<DiskonFleet> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the diskon fleets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFleetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fleets
	 * @param end the upper bound of the range of diskon fleets (not inclusive)
	 * @return the range of diskon fleets
	 */
	@Override
	public List<DiskonFleet> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the diskon fleets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFleetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fleets
	 * @param end the upper bound of the range of diskon fleets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of diskon fleets
	 */
	@Override
	public List<DiskonFleet> findAll(
		int start, int end, OrderByComparator<DiskonFleet> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the diskon fleets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFleetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fleets
	 * @param end the upper bound of the range of diskon fleets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of diskon fleets
	 */
	@Override
	public List<DiskonFleet> findAll(
		int start, int end, OrderByComparator<DiskonFleet> orderByComparator,
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

		List<DiskonFleet> list = null;

		if (useFinderCache) {
			list = (List<DiskonFleet>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DISKONFLEET);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DISKONFLEET;

				sql = sql.concat(DiskonFleetModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DiskonFleet>)QueryUtil.list(
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
	 * Removes all the diskon fleets from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DiskonFleet diskonFleet : findAll()) {
			remove(diskonFleet);
		}
	}

	/**
	 * Returns the number of diskon fleets.
	 *
	 * @return the number of diskon fleets
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DISKONFLEET);

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
		return _SQL_SELECT_DISKONFLEET;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DiskonFleetModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the diskon fleet persistence.
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

		_setDiskonFleetUtilPersistence(this);
	}

	public void destroy() {
		_setDiskonFleetUtilPersistence(null);

		entityCache.removeCache(DiskonFleetImpl.class.getName());
	}

	private void _setDiskonFleetUtilPersistence(
		DiskonFleetPersistence diskonFleetPersistence) {

		try {
			Field field = DiskonFleetUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, diskonFleetPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_DISKONFLEET =
		"SELECT diskonFleet FROM DiskonFleet diskonFleet";

	private static final String _SQL_COUNT_DISKONFLEET =
		"SELECT COUNT(diskonFleet) FROM DiskonFleet diskonFleet";

	private static final String _ORDER_BY_ENTITY_ALIAS = "diskonFleet.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DiskonFleet exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		DiskonFleetPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}