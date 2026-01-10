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

import com.astra.dewa.exception.NoSuchCalenderEventParticipantException;
import com.astra.dewa.model.CalenderEventParticipant;
import com.astra.dewa.model.CalenderEventParticipantTable;
import com.astra.dewa.model.impl.CalenderEventParticipantImpl;
import com.astra.dewa.model.impl.CalenderEventParticipantModelImpl;
import com.astra.dewa.service.persistence.CalenderEventParticipantPersistence;
import com.astra.dewa.service.persistence.CalenderEventParticipantUtil;

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
 * The persistence implementation for the calender event participant service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CalenderEventParticipantPersistenceImpl
	extends BasePersistenceImpl<CalenderEventParticipant>
	implements CalenderEventParticipantPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CalenderEventParticipantUtil</code> to access the calender event participant persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CalenderEventParticipantImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public CalenderEventParticipantPersistenceImpl() {
		setModelClass(CalenderEventParticipant.class);

		setModelImplClass(CalenderEventParticipantImpl.class);
		setModelPKClass(int.class);

		setTable(CalenderEventParticipantTable.INSTANCE);
	}

	/**
	 * Caches the calender event participant in the entity cache if it is enabled.
	 *
	 * @param calenderEventParticipant the calender event participant
	 */
	@Override
	public void cacheResult(CalenderEventParticipant calenderEventParticipant) {
		entityCache.putResult(
			CalenderEventParticipantImpl.class,
			calenderEventParticipant.getPrimaryKey(), calenderEventParticipant);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the calender event participants in the entity cache if it is enabled.
	 *
	 * @param calenderEventParticipants the calender event participants
	 */
	@Override
	public void cacheResult(
		List<CalenderEventParticipant> calenderEventParticipants) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (calenderEventParticipants.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CalenderEventParticipant calenderEventParticipant :
				calenderEventParticipants) {

			if (entityCache.getResult(
					CalenderEventParticipantImpl.class,
					calenderEventParticipant.getPrimaryKey()) == null) {

				cacheResult(calenderEventParticipant);
			}
		}
	}

	/**
	 * Clears the cache for all calender event participants.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CalenderEventParticipantImpl.class);

		finderCache.clearCache(CalenderEventParticipantImpl.class);
	}

	/**
	 * Clears the cache for the calender event participant.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CalenderEventParticipant calenderEventParticipant) {
		entityCache.removeResult(
			CalenderEventParticipantImpl.class, calenderEventParticipant);
	}

	@Override
	public void clearCache(
		List<CalenderEventParticipant> calenderEventParticipants) {

		for (CalenderEventParticipant calenderEventParticipant :
				calenderEventParticipants) {

			entityCache.removeResult(
				CalenderEventParticipantImpl.class, calenderEventParticipant);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CalenderEventParticipantImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CalenderEventParticipantImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new calender event participant with the primary key. Does not add the calender event participant to the database.
	 *
	 * @param Id the primary key for the new calender event participant
	 * @return the new calender event participant
	 */
	@Override
	public CalenderEventParticipant create(int Id) {
		CalenderEventParticipant calenderEventParticipant =
			new CalenderEventParticipantImpl();

		calenderEventParticipant.setNew(true);
		calenderEventParticipant.setPrimaryKey(Id);

		return calenderEventParticipant;
	}

	/**
	 * Removes the calender event participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the calender event participant
	 * @return the calender event participant that was removed
	 * @throws NoSuchCalenderEventParticipantException if a calender event participant with the primary key could not be found
	 */
	@Override
	public CalenderEventParticipant remove(int Id)
		throws NoSuchCalenderEventParticipantException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the calender event participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the calender event participant
	 * @return the calender event participant that was removed
	 * @throws NoSuchCalenderEventParticipantException if a calender event participant with the primary key could not be found
	 */
	@Override
	public CalenderEventParticipant remove(Serializable primaryKey)
		throws NoSuchCalenderEventParticipantException {

		Session session = null;

		try {
			session = openSession();

			CalenderEventParticipant calenderEventParticipant =
				(CalenderEventParticipant)session.get(
					CalenderEventParticipantImpl.class, primaryKey);

			if (calenderEventParticipant == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCalenderEventParticipantException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(calenderEventParticipant);
		}
		catch (NoSuchCalenderEventParticipantException noSuchEntityException) {
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
	protected CalenderEventParticipant removeImpl(
		CalenderEventParticipant calenderEventParticipant) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(calenderEventParticipant)) {
				calenderEventParticipant =
					(CalenderEventParticipant)session.get(
						CalenderEventParticipantImpl.class,
						calenderEventParticipant.getPrimaryKeyObj());
			}

			if (calenderEventParticipant != null) {
				session.delete(calenderEventParticipant);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (calenderEventParticipant != null) {
			clearCache(calenderEventParticipant);
		}

		return calenderEventParticipant;
	}

	@Override
	public CalenderEventParticipant updateImpl(
		CalenderEventParticipant calenderEventParticipant) {

		boolean isNew = calenderEventParticipant.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(calenderEventParticipant);
			}
			else {
				calenderEventParticipant =
					(CalenderEventParticipant)session.merge(
						calenderEventParticipant);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CalenderEventParticipantImpl.class, calenderEventParticipant, false,
			true);

		if (isNew) {
			calenderEventParticipant.setNew(false);
		}

		calenderEventParticipant.resetOriginalValues();

		return calenderEventParticipant;
	}

	/**
	 * Returns the calender event participant with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calender event participant
	 * @return the calender event participant
	 * @throws NoSuchCalenderEventParticipantException if a calender event participant with the primary key could not be found
	 */
	@Override
	public CalenderEventParticipant findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCalenderEventParticipantException {

		CalenderEventParticipant calenderEventParticipant = fetchByPrimaryKey(
			primaryKey);

		if (calenderEventParticipant == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCalenderEventParticipantException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return calenderEventParticipant;
	}

	/**
	 * Returns the calender event participant with the primary key or throws a <code>NoSuchCalenderEventParticipantException</code> if it could not be found.
	 *
	 * @param Id the primary key of the calender event participant
	 * @return the calender event participant
	 * @throws NoSuchCalenderEventParticipantException if a calender event participant with the primary key could not be found
	 */
	@Override
	public CalenderEventParticipant findByPrimaryKey(int Id)
		throws NoSuchCalenderEventParticipantException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the calender event participant with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the calender event participant
	 * @return the calender event participant, or <code>null</code> if a calender event participant with the primary key could not be found
	 */
	@Override
	public CalenderEventParticipant fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the calender event participants.
	 *
	 * @return the calender event participants
	 */
	@Override
	public List<CalenderEventParticipant> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calender event participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalenderEventParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender event participants
	 * @param end the upper bound of the range of calender event participants (not inclusive)
	 * @return the range of calender event participants
	 */
	@Override
	public List<CalenderEventParticipant> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the calender event participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalenderEventParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender event participants
	 * @param end the upper bound of the range of calender event participants (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calender event participants
	 */
	@Override
	public List<CalenderEventParticipant> findAll(
		int start, int end,
		OrderByComparator<CalenderEventParticipant> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the calender event participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CalenderEventParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calender event participants
	 * @param end the upper bound of the range of calender event participants (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of calender event participants
	 */
	@Override
	public List<CalenderEventParticipant> findAll(
		int start, int end,
		OrderByComparator<CalenderEventParticipant> orderByComparator,
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

		List<CalenderEventParticipant> list = null;

		if (useFinderCache) {
			list = (List<CalenderEventParticipant>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CALENDEREVENTPARTICIPANT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CALENDEREVENTPARTICIPANT;

				sql = sql.concat(
					CalenderEventParticipantModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CalenderEventParticipant>)QueryUtil.list(
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
	 * Removes all the calender event participants from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CalenderEventParticipant calenderEventParticipant : findAll()) {
			remove(calenderEventParticipant);
		}
	}

	/**
	 * Returns the number of calender event participants.
	 *
	 * @return the number of calender event participants
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_CALENDEREVENTPARTICIPANT);

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
		return _SQL_SELECT_CALENDEREVENTPARTICIPANT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CalenderEventParticipantModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the calender event participant persistence.
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

		_setCalenderEventParticipantUtilPersistence(this);
	}

	public void destroy() {
		_setCalenderEventParticipantUtilPersistence(null);

		entityCache.removeCache(CalenderEventParticipantImpl.class.getName());
	}

	private void _setCalenderEventParticipantUtilPersistence(
		CalenderEventParticipantPersistence
			calenderEventParticipantPersistence) {

		try {
			Field field = CalenderEventParticipantUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, calenderEventParticipantPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CALENDEREVENTPARTICIPANT =
		"SELECT calenderEventParticipant FROM CalenderEventParticipant calenderEventParticipant";

	private static final String _SQL_COUNT_CALENDEREVENTPARTICIPANT =
		"SELECT COUNT(calenderEventParticipant) FROM CalenderEventParticipant calenderEventParticipant";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"calenderEventParticipant.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CalenderEventParticipant exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		CalenderEventParticipantPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}