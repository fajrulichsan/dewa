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

import com.astra.dewa.exception.NoSuchTrainingMateriLampiranException;
import com.astra.dewa.model.TrainingMateriLampiran;
import com.astra.dewa.model.TrainingMateriLampiranTable;
import com.astra.dewa.model.impl.TrainingMateriLampiranImpl;
import com.astra.dewa.model.impl.TrainingMateriLampiranModelImpl;
import com.astra.dewa.service.persistence.TrainingMateriLampiranPersistence;
import com.astra.dewa.service.persistence.TrainingMateriLampiranUtil;

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
 * The persistence implementation for the training materi lampiran service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TrainingMateriLampiranPersistenceImpl
	extends BasePersistenceImpl<TrainingMateriLampiran>
	implements TrainingMateriLampiranPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TrainingMateriLampiranUtil</code> to access the training materi lampiran persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TrainingMateriLampiranImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public TrainingMateriLampiranPersistenceImpl() {
		setModelClass(TrainingMateriLampiran.class);

		setModelImplClass(TrainingMateriLampiranImpl.class);
		setModelPKClass(int.class);

		setTable(TrainingMateriLampiranTable.INSTANCE);
	}

	/**
	 * Caches the training materi lampiran in the entity cache if it is enabled.
	 *
	 * @param trainingMateriLampiran the training materi lampiran
	 */
	@Override
	public void cacheResult(TrainingMateriLampiran trainingMateriLampiran) {
		dummyEntityCache.putResult(
			TrainingMateriLampiranImpl.class,
			trainingMateriLampiran.getPrimaryKey(), trainingMateriLampiran);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the training materi lampirans in the entity cache if it is enabled.
	 *
	 * @param trainingMateriLampirans the training materi lampirans
	 */
	@Override
	public void cacheResult(
		List<TrainingMateriLampiran> trainingMateriLampirans) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (trainingMateriLampirans.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TrainingMateriLampiran trainingMateriLampiran :
				trainingMateriLampirans) {

			if (dummyEntityCache.getResult(
					TrainingMateriLampiranImpl.class,
					trainingMateriLampiran.getPrimaryKey()) == null) {

				cacheResult(trainingMateriLampiran);
			}
		}
	}

	/**
	 * Clears the cache for all training materi lampirans.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(TrainingMateriLampiranImpl.class);

		dummyFinderCache.clearCache(TrainingMateriLampiranImpl.class);
	}

	/**
	 * Clears the cache for the training materi lampiran.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingMateriLampiran trainingMateriLampiran) {
		dummyEntityCache.removeResult(
			TrainingMateriLampiranImpl.class, trainingMateriLampiran);
	}

	@Override
	public void clearCache(
		List<TrainingMateriLampiran> trainingMateriLampirans) {

		for (TrainingMateriLampiran trainingMateriLampiran :
				trainingMateriLampirans) {

			dummyEntityCache.removeResult(
				TrainingMateriLampiranImpl.class, trainingMateriLampiran);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(TrainingMateriLampiranImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(
				TrainingMateriLampiranImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new training materi lampiran with the primary key. Does not add the training materi lampiran to the database.
	 *
	 * @param Id the primary key for the new training materi lampiran
	 * @return the new training materi lampiran
	 */
	@Override
	public TrainingMateriLampiran create(int Id) {
		TrainingMateriLampiran trainingMateriLampiran =
			new TrainingMateriLampiranImpl();

		trainingMateriLampiran.setNew(true);
		trainingMateriLampiran.setPrimaryKey(Id);

		return trainingMateriLampiran;
	}

	/**
	 * Removes the training materi lampiran with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the training materi lampiran
	 * @return the training materi lampiran that was removed
	 * @throws NoSuchTrainingMateriLampiranException if a training materi lampiran with the primary key could not be found
	 */
	@Override
	public TrainingMateriLampiran remove(int Id)
		throws NoSuchTrainingMateriLampiranException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the training materi lampiran with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training materi lampiran
	 * @return the training materi lampiran that was removed
	 * @throws NoSuchTrainingMateriLampiranException if a training materi lampiran with the primary key could not be found
	 */
	@Override
	public TrainingMateriLampiran remove(Serializable primaryKey)
		throws NoSuchTrainingMateriLampiranException {

		Session session = null;

		try {
			session = openSession();

			TrainingMateriLampiran trainingMateriLampiran =
				(TrainingMateriLampiran)session.get(
					TrainingMateriLampiranImpl.class, primaryKey);

			if (trainingMateriLampiran == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingMateriLampiranException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(trainingMateriLampiran);
		}
		catch (NoSuchTrainingMateriLampiranException noSuchEntityException) {
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
	protected TrainingMateriLampiran removeImpl(
		TrainingMateriLampiran trainingMateriLampiran) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(trainingMateriLampiran)) {
				trainingMateriLampiran = (TrainingMateriLampiran)session.get(
					TrainingMateriLampiranImpl.class,
					trainingMateriLampiran.getPrimaryKeyObj());
			}

			if (trainingMateriLampiran != null) {
				session.delete(trainingMateriLampiran);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (trainingMateriLampiran != null) {
			clearCache(trainingMateriLampiran);
		}

		return trainingMateriLampiran;
	}

	@Override
	public TrainingMateriLampiran updateImpl(
		TrainingMateriLampiran trainingMateriLampiran) {

		boolean isNew = trainingMateriLampiran.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(trainingMateriLampiran);
			}
			else {
				trainingMateriLampiran = (TrainingMateriLampiran)session.merge(
					trainingMateriLampiran);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			TrainingMateriLampiranImpl.class, trainingMateriLampiran, false,
			true);

		if (isNew) {
			trainingMateriLampiran.setNew(false);
		}

		trainingMateriLampiran.resetOriginalValues();

		return trainingMateriLampiran;
	}

	/**
	 * Returns the training materi lampiran with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training materi lampiran
	 * @return the training materi lampiran
	 * @throws NoSuchTrainingMateriLampiranException if a training materi lampiran with the primary key could not be found
	 */
	@Override
	public TrainingMateriLampiran findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTrainingMateriLampiranException {

		TrainingMateriLampiran trainingMateriLampiran = fetchByPrimaryKey(
			primaryKey);

		if (trainingMateriLampiran == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTrainingMateriLampiranException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return trainingMateriLampiran;
	}

	/**
	 * Returns the training materi lampiran with the primary key or throws a <code>NoSuchTrainingMateriLampiranException</code> if it could not be found.
	 *
	 * @param Id the primary key of the training materi lampiran
	 * @return the training materi lampiran
	 * @throws NoSuchTrainingMateriLampiranException if a training materi lampiran with the primary key could not be found
	 */
	@Override
	public TrainingMateriLampiran findByPrimaryKey(int Id)
		throws NoSuchTrainingMateriLampiranException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the training materi lampiran with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the training materi lampiran
	 * @return the training materi lampiran, or <code>null</code> if a training materi lampiran with the primary key could not be found
	 */
	@Override
	public TrainingMateriLampiran fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the training materi lampirans.
	 *
	 * @return the training materi lampirans
	 */
	@Override
	public List<TrainingMateriLampiran> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training materi lampirans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingMateriLampiranModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training materi lampirans
	 * @param end the upper bound of the range of training materi lampirans (not inclusive)
	 * @return the range of training materi lampirans
	 */
	@Override
	public List<TrainingMateriLampiran> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training materi lampirans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingMateriLampiranModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training materi lampirans
	 * @param end the upper bound of the range of training materi lampirans (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training materi lampirans
	 */
	@Override
	public List<TrainingMateriLampiran> findAll(
		int start, int end,
		OrderByComparator<TrainingMateriLampiran> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the training materi lampirans.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingMateriLampiranModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training materi lampirans
	 * @param end the upper bound of the range of training materi lampirans (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of training materi lampirans
	 */
	@Override
	public List<TrainingMateriLampiran> findAll(
		int start, int end,
		OrderByComparator<TrainingMateriLampiran> orderByComparator,
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

		List<TrainingMateriLampiran> list = null;

		if (useFinderCache) {
			list = (List<TrainingMateriLampiran>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TRAININGMATERILAMPIRAN);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGMATERILAMPIRAN;

				sql = sql.concat(TrainingMateriLampiranModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TrainingMateriLampiran>)QueryUtil.list(
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
	 * Removes all the training materi lampirans from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TrainingMateriLampiran trainingMateriLampiran : findAll()) {
			remove(trainingMateriLampiran);
		}
	}

	/**
	 * Returns the number of training materi lampirans.
	 *
	 * @return the number of training materi lampirans
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_TRAININGMATERILAMPIRAN);

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
		return _SQL_SELECT_TRAININGMATERILAMPIRAN;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TrainingMateriLampiranModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the training materi lampiran persistence.
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

		_setTrainingMateriLampiranUtilPersistence(this);
	}

	public void destroy() {
		_setTrainingMateriLampiranUtilPersistence(null);

		dummyEntityCache.removeCache(
			TrainingMateriLampiranImpl.class.getName());
	}

	private void _setTrainingMateriLampiranUtilPersistence(
		TrainingMateriLampiranPersistence trainingMateriLampiranPersistence) {

		try {
			Field field = TrainingMateriLampiranUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, trainingMateriLampiranPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private static final String _SQL_SELECT_TRAININGMATERILAMPIRAN =
		"SELECT trainingMateriLampiran FROM TrainingMateriLampiran trainingMateriLampiran";

	private static final String _SQL_COUNT_TRAININGMATERILAMPIRAN =
		"SELECT COUNT(trainingMateriLampiran) FROM TrainingMateriLampiran trainingMateriLampiran";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"trainingMateriLampiran.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TrainingMateriLampiran exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		TrainingMateriLampiranPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}