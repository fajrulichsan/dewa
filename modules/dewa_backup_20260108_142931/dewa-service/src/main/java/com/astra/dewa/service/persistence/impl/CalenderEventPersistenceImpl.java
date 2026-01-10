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

import com.astra.dewa.exception.NoSuchCalenderEventException;
import com.astra.dewa.model.CalenderEvent;
import com.astra.dewa.model.CalenderEventTable;
import com.astra.dewa.model.impl.CalenderEventImpl;
import com.astra.dewa.model.impl.CalenderEventModelImpl;
import com.astra.dewa.service.persistence.CalenderEventPersistence;
import com.astra.dewa.service.persistence.CalenderEventUtil;

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

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the calender event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CalenderEventPersistenceImpl
	extends BasePersistenceImpl<CalenderEvent>
	implements CalenderEventPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CalenderEventUtil</code> to access the calender event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CalenderEventImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public CalenderEventPersistenceImpl() {
		setModelClass(CalenderEvent.class);

		setModelImplClass(CalenderEventImpl.class);
		setModelPKClass(int.class);

		setTable(CalenderEventTable.INSTANCE);
	}

	/**
	 * Caches the calender event in the entity cache if it is enabled.
	 *
	 * @param calenderEvent the calender event
	 */
	@Override
	public void cacheResult(CalenderEvent calenderEvent) {
		dummyEntityCache.putResult(
			CalenderEventImpl.class, calenderEvent.getPrimaryKey(),
			calenderEvent);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the calender events in the entity cache if it is enabled.
	 *
	 * @param calenderEvents the calender events
	 */
	@Override
	public void cacheResult(List<CalenderEvent> calenderEvents) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (calenderEvents.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CalenderEvent calenderEvent : calenderEvents) {
			if (dummyEntityCache.getResult(
					CalenderEventImpl.class, calenderEvent.getPrimaryKey()) ==
						null) {

				cacheResult(calenderEvent);
			}
		}
	}

	/**
	 * Clears the cache for all calender events.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(CalenderEventImpl.class);

		dummyFinderCache.clearCache(CalenderEventImpl.class);
	}

	/**
	 * Clears the cache for the calender event.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CalenderEvent calenderEvent) {
		dummyEntityCache.removeResult(CalenderEventImpl.class, calenderEvent);
	}

	@Override
	public void clearCache(List<CalenderEvent> calenderEvents) {
		for (CalenderEvent calenderEvent : calenderEvents) {
			dummyEntityCache.removeResult(
				CalenderEventImpl.class, calenderEvent);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(CalenderEventImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(CalenderEventImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new calender event with the primary key. Does not add the calender event to the database.
	 *
	 * @param Id the primary key for the new calender event
	 * @return the new calender event
	 */
	@Override
	public CalenderEvent create(int Id) {
		CalenderEvent calenderEvent = new CalenderEventImpl();

		calenderEvent.setNew(true);
		calenderEvent.setPrimaryKey(Id);

		return calenderEvent;
	}

	/**
	 * Removes the calender event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the calender event
	 * @return the calender event that was removed
	 * @throws NoSuchCalenderEventException if a calender event with the primary key could not be found
	 */
	@Override
	public CalenderEvent remove(int Id) throws NoSuchCalenderEventException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the calender event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the calender event
	 * @return the calender event that was removed
	 * @throws NoSuchCalenderEventException if a calender event with the primary key could not be found
	 */
	@Override
	public CalenderEvent remove(Serializable primaryKey)
		throws NoSuchCalenderEventException {

		Session session = null;

		try {
			session = openSession();

			CalenderEvent calenderEvent = (CalenderEvent)session.get(
				CalenderEventImpl.class, primaryKey);

			if (calenderEvent == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCalenderEventException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(calenderEvent);
		}
		catch (NoSuchCalenderEventException noSuchEntityException) {
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
	protected CalenderEvent removeImpl(CalenderEvent calenderEvent) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(calenderEvent)) {
				calenderEvent = (CalenderEvent)session.get(
					CalenderEventImpl.class, calenderEvent.getPrimaryKeyObj());
			}

			if (calenderEvent != null) {
				session.delete(calenderEvent);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (calenderEvent != null) {
			clearCache(calenderEvent);
		}

		return calenderEvent;
	}

	@Override
	public CalenderEvent updateImpl(CalenderEvent calenderEvent) {
		boolean isNew = calenderEvent.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(calenderEvent);
			}
			else {
				calenderEvent = (CalenderEvent)session.merge(calenderEvent);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			CalenderEventImpl.class, calenderEvent, false, true);

		if (isNew) {
			calenderEvent.setNew(false);
		}

		calenderEvent.resetOriginalValues();

		return calenderEvent;
	}

	/**
	 * Returns the calender event with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calender event
	 * @return the calender event
	 * @throws NoSuchCalenderEventException if a calender event with the primary key could not be found
	 */
	@Override
	public CalenderEvent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCalenderEventException {

		CalenderEvent calenderEvent = fetchByPrimaryKey(primaryKey);

		if (calenderEvent == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCalenderEventException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return calenderEvent;
	}

	/**
	 * Returns the calender event with the primary key or throws a <code>NoSuchCalenderEventException</code> if it could not be found.
	 *
	 * @param Id the primary key of the calender event
	 * @return the calender event
	 * @throws NoSuchCalenderEventException if a calender event with the primary key could not be found
	 */
	@Override
	public CalenderEvent findByPrimaryKey(int Id)
		throws NoSuchCalenderEventException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the calender event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the calender event
	 * @return the calender event, or <code>null</code> if a calender event with the primary key could not be found
	 */
	@Override
	public CalenderEvent fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the calender events.
	 *
	 * @return the calender events
	 */
	@Override
	public List<CalenderEvent> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calender events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalenderEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender events
	 * @param end the upper bound of the range of calender events (not inclusive)
	 * @return the range of calender events
	 */
	@Override
	public List<CalenderEvent> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the calender events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalenderEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender events
	 * @param end the upper bound of the range of calender events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calender events
	 */
	@Override
	public List<CalenderEvent> findAll(
		int start, int end,
		OrderByComparator<CalenderEvent> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the calender events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalenderEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender events
	 * @param end the upper bound of the range of calender events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of calender events
	 */
	@Override
	public List<CalenderEvent> findAll(
		int start, int end, OrderByComparator<CalenderEvent> orderByComparator,
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

		List<CalenderEvent> list = null;

		if (useFinderCache) {
			list = (List<CalenderEvent>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CALENDEREVENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CALENDEREVENT;

				sql = sql.concat(CalenderEventModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CalenderEvent>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					dummyFinderCache.putResult(finderPath, finderArgs, list);
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
	 * Removes all the calender events from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CalenderEvent calenderEvent : findAll()) {
			remove(calenderEvent);
		}
	}

	/**
	 * Returns the number of calender events.
	 *
	 * @return the number of calender events
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_CALENDEREVENT);

				count = (Long)query.uniqueResult();

				dummyFinderCache.putResult(
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
		return dummyEntityCache;
	}

	@Override
	protected String getPKDBName() {
		return "Id";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CALENDEREVENT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CalenderEventModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the calender event persistence.
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

		_setCalenderEventUtilPersistence(this);
	}

	public void destroy() {
		_setCalenderEventUtilPersistence(null);

		dummyEntityCache.removeCache(CalenderEventImpl.class.getName());
	}

	private void _setCalenderEventUtilPersistence(
		CalenderEventPersistence calenderEventPersistence) {

		try {
			Field field = CalenderEventUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, calenderEventPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private static final String _SQL_SELECT_CALENDEREVENT =
		"SELECT calenderEvent FROM CalenderEvent calenderEvent";

	private static final String _SQL_COUNT_CALENDEREVENT =
		"SELECT COUNT(calenderEvent) FROM CalenderEvent calenderEvent";

	private static final String _ORDER_BY_ENTITY_ALIAS = "calenderEvent.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CalenderEvent exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		CalenderEventPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}