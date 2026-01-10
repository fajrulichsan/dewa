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

import com.astra.dewa.exception.NoSuchTrainingAgendaException;
import com.astra.dewa.model.TrainingAgenda;
import com.astra.dewa.model.TrainingAgendaTable;
import com.astra.dewa.model.impl.TrainingAgendaImpl;
import com.astra.dewa.model.impl.TrainingAgendaModelImpl;
import com.astra.dewa.service.persistence.TrainingAgendaPersistence;
import com.astra.dewa.service.persistence.TrainingAgendaUtil;

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
 * The persistence implementation for the training agenda service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TrainingAgendaPersistenceImpl
	extends BasePersistenceImpl<TrainingAgenda>
	implements TrainingAgendaPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TrainingAgendaUtil</code> to access the training agenda persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TrainingAgendaImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public TrainingAgendaPersistenceImpl() {
		setModelClass(TrainingAgenda.class);

		setModelImplClass(TrainingAgendaImpl.class);
		setModelPKClass(int.class);

		setTable(TrainingAgendaTable.INSTANCE);
	}

	/**
	 * Caches the training agenda in the entity cache if it is enabled.
	 *
	 * @param trainingAgenda the training agenda
	 */
	@Override
	public void cacheResult(TrainingAgenda trainingAgenda) {
		dummyEntityCache.putResult(
			TrainingAgendaImpl.class, trainingAgenda.getPrimaryKey(),
			trainingAgenda);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the training agendas in the entity cache if it is enabled.
	 *
	 * @param trainingAgendas the training agendas
	 */
	@Override
	public void cacheResult(List<TrainingAgenda> trainingAgendas) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (trainingAgendas.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TrainingAgenda trainingAgenda : trainingAgendas) {
			if (dummyEntityCache.getResult(
					TrainingAgendaImpl.class, trainingAgenda.getPrimaryKey()) ==
						null) {

				cacheResult(trainingAgenda);
			}
		}
	}

	/**
	 * Clears the cache for all training agendas.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(TrainingAgendaImpl.class);

		dummyFinderCache.clearCache(TrainingAgendaImpl.class);
	}

	/**
	 * Clears the cache for the training agenda.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingAgenda trainingAgenda) {
		dummyEntityCache.removeResult(TrainingAgendaImpl.class, trainingAgenda);
	}

	@Override
	public void clearCache(List<TrainingAgenda> trainingAgendas) {
		for (TrainingAgenda trainingAgenda : trainingAgendas) {
			dummyEntityCache.removeResult(
				TrainingAgendaImpl.class, trainingAgenda);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(TrainingAgendaImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(TrainingAgendaImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new training agenda with the primary key. Does not add the training agenda to the database.
	 *
	 * @param Id the primary key for the new training agenda
	 * @return the new training agenda
	 */
	@Override
	public TrainingAgenda create(int Id) {
		TrainingAgenda trainingAgenda = new TrainingAgendaImpl();

		trainingAgenda.setNew(true);
		trainingAgenda.setPrimaryKey(Id);

		return trainingAgenda;
	}

	/**
	 * Removes the training agenda with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the training agenda
	 * @return the training agenda that was removed
	 * @throws NoSuchTrainingAgendaException if a training agenda with the primary key could not be found
	 */
	@Override
	public TrainingAgenda remove(int Id) throws NoSuchTrainingAgendaException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the training agenda with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training agenda
	 * @return the training agenda that was removed
	 * @throws NoSuchTrainingAgendaException if a training agenda with the primary key could not be found
	 */
	@Override
	public TrainingAgenda remove(Serializable primaryKey)
		throws NoSuchTrainingAgendaException {

		Session session = null;

		try {
			session = openSession();

			TrainingAgenda trainingAgenda = (TrainingAgenda)session.get(
				TrainingAgendaImpl.class, primaryKey);

			if (trainingAgenda == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingAgendaException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(trainingAgenda);
		}
		catch (NoSuchTrainingAgendaException noSuchEntityException) {
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
	protected TrainingAgenda removeImpl(TrainingAgenda trainingAgenda) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(trainingAgenda)) {
				trainingAgenda = (TrainingAgenda)session.get(
					TrainingAgendaImpl.class,
					trainingAgenda.getPrimaryKeyObj());
			}

			if (trainingAgenda != null) {
				session.delete(trainingAgenda);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (trainingAgenda != null) {
			clearCache(trainingAgenda);
		}

		return trainingAgenda;
	}

	@Override
	public TrainingAgenda updateImpl(TrainingAgenda trainingAgenda) {
		boolean isNew = trainingAgenda.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(trainingAgenda);
			}
			else {
				trainingAgenda = (TrainingAgenda)session.merge(trainingAgenda);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			TrainingAgendaImpl.class, trainingAgenda, false, true);

		if (isNew) {
			trainingAgenda.setNew(false);
		}

		trainingAgenda.resetOriginalValues();

		return trainingAgenda;
	}

	/**
	 * Returns the training agenda with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training agenda
	 * @return the training agenda
	 * @throws NoSuchTrainingAgendaException if a training agenda with the primary key could not be found
	 */
	@Override
	public TrainingAgenda findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTrainingAgendaException {

		TrainingAgenda trainingAgenda = fetchByPrimaryKey(primaryKey);

		if (trainingAgenda == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTrainingAgendaException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return trainingAgenda;
	}

	/**
	 * Returns the training agenda with the primary key or throws a <code>NoSuchTrainingAgendaException</code> if it could not be found.
	 *
	 * @param Id the primary key of the training agenda
	 * @return the training agenda
	 * @throws NoSuchTrainingAgendaException if a training agenda with the primary key could not be found
	 */
	@Override
	public TrainingAgenda findByPrimaryKey(int Id)
		throws NoSuchTrainingAgendaException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the training agenda with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the training agenda
	 * @return the training agenda, or <code>null</code> if a training agenda with the primary key could not be found
	 */
	@Override
	public TrainingAgenda fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the training agendas.
	 *
	 * @return the training agendas
	 */
	@Override
	public List<TrainingAgenda> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training agendas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agendas
	 * @param end the upper bound of the range of training agendas (not inclusive)
	 * @return the range of training agendas
	 */
	@Override
	public List<TrainingAgenda> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training agendas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agendas
	 * @param end the upper bound of the range of training agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training agendas
	 */
	@Override
	public List<TrainingAgenda> findAll(
		int start, int end,
		OrderByComparator<TrainingAgenda> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the training agendas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agendas
	 * @param end the upper bound of the range of training agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of training agendas
	 */
	@Override
	public List<TrainingAgenda> findAll(
		int start, int end, OrderByComparator<TrainingAgenda> orderByComparator,
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

		List<TrainingAgenda> list = null;

		if (useFinderCache) {
			list = (List<TrainingAgenda>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TRAININGAGENDA);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGAGENDA;

				sql = sql.concat(TrainingAgendaModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TrainingAgenda>)QueryUtil.list(
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
	 * Removes all the training agendas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TrainingAgenda trainingAgenda : findAll()) {
			remove(trainingAgenda);
		}
	}

	/**
	 * Returns the number of training agendas.
	 *
	 * @return the number of training agendas
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_TRAININGAGENDA);

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
		return _SQL_SELECT_TRAININGAGENDA;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TrainingAgendaModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the training agenda persistence.
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

		_setTrainingAgendaUtilPersistence(this);
	}

	public void destroy() {
		_setTrainingAgendaUtilPersistence(null);

		dummyEntityCache.removeCache(TrainingAgendaImpl.class.getName());
	}

	private void _setTrainingAgendaUtilPersistence(
		TrainingAgendaPersistence trainingAgendaPersistence) {

		try {
			Field field = TrainingAgendaUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, trainingAgendaPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private static final String _SQL_SELECT_TRAININGAGENDA =
		"SELECT trainingAgenda FROM TrainingAgenda trainingAgenda";

	private static final String _SQL_COUNT_TRAININGAGENDA =
		"SELECT COUNT(trainingAgenda) FROM TrainingAgenda trainingAgenda";

	private static final String _ORDER_BY_ENTITY_ALIAS = "trainingAgenda.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TrainingAgenda exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		TrainingAgendaPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}