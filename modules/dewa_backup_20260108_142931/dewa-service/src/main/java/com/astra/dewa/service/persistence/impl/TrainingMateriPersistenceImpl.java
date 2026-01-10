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

import com.astra.dewa.exception.NoSuchTrainingMateriException;
import com.astra.dewa.model.TrainingMateri;
import com.astra.dewa.model.TrainingMateriTable;
import com.astra.dewa.model.impl.TrainingMateriImpl;
import com.astra.dewa.model.impl.TrainingMateriModelImpl;
import com.astra.dewa.service.persistence.TrainingMateriPersistence;
import com.astra.dewa.service.persistence.TrainingMateriUtil;

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
 * The persistence implementation for the training materi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TrainingMateriPersistenceImpl
	extends BasePersistenceImpl<TrainingMateri>
	implements TrainingMateriPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TrainingMateriUtil</code> to access the training materi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TrainingMateriImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public TrainingMateriPersistenceImpl() {
		setModelClass(TrainingMateri.class);

		setModelImplClass(TrainingMateriImpl.class);
		setModelPKClass(int.class);

		setTable(TrainingMateriTable.INSTANCE);
	}

	/**
	 * Caches the training materi in the entity cache if it is enabled.
	 *
	 * @param trainingMateri the training materi
	 */
	@Override
	public void cacheResult(TrainingMateri trainingMateri) {
		dummyEntityCache.putResult(
			TrainingMateriImpl.class, trainingMateri.getPrimaryKey(),
			trainingMateri);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the training materis in the entity cache if it is enabled.
	 *
	 * @param trainingMateris the training materis
	 */
	@Override
	public void cacheResult(List<TrainingMateri> trainingMateris) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (trainingMateris.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TrainingMateri trainingMateri : trainingMateris) {
			if (dummyEntityCache.getResult(
					TrainingMateriImpl.class, trainingMateri.getPrimaryKey()) ==
						null) {

				cacheResult(trainingMateri);
			}
		}
	}

	/**
	 * Clears the cache for all training materis.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(TrainingMateriImpl.class);

		dummyFinderCache.clearCache(TrainingMateriImpl.class);
	}

	/**
	 * Clears the cache for the training materi.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingMateri trainingMateri) {
		dummyEntityCache.removeResult(TrainingMateriImpl.class, trainingMateri);
	}

	@Override
	public void clearCache(List<TrainingMateri> trainingMateris) {
		for (TrainingMateri trainingMateri : trainingMateris) {
			dummyEntityCache.removeResult(
				TrainingMateriImpl.class, trainingMateri);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(TrainingMateriImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(TrainingMateriImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new training materi with the primary key. Does not add the training materi to the database.
	 *
	 * @param Id the primary key for the new training materi
	 * @return the new training materi
	 */
	@Override
	public TrainingMateri create(int Id) {
		TrainingMateri trainingMateri = new TrainingMateriImpl();

		trainingMateri.setNew(true);
		trainingMateri.setPrimaryKey(Id);

		return trainingMateri;
	}

	/**
	 * Removes the training materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the training materi
	 * @return the training materi that was removed
	 * @throws NoSuchTrainingMateriException if a training materi with the primary key could not be found
	 */
	@Override
	public TrainingMateri remove(int Id) throws NoSuchTrainingMateriException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the training materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training materi
	 * @return the training materi that was removed
	 * @throws NoSuchTrainingMateriException if a training materi with the primary key could not be found
	 */
	@Override
	public TrainingMateri remove(Serializable primaryKey)
		throws NoSuchTrainingMateriException {

		Session session = null;

		try {
			session = openSession();

			TrainingMateri trainingMateri = (TrainingMateri)session.get(
				TrainingMateriImpl.class, primaryKey);

			if (trainingMateri == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingMateriException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(trainingMateri);
		}
		catch (NoSuchTrainingMateriException noSuchEntityException) {
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
	protected TrainingMateri removeImpl(TrainingMateri trainingMateri) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(trainingMateri)) {
				trainingMateri = (TrainingMateri)session.get(
					TrainingMateriImpl.class,
					trainingMateri.getPrimaryKeyObj());
			}

			if (trainingMateri != null) {
				session.delete(trainingMateri);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (trainingMateri != null) {
			clearCache(trainingMateri);
		}

		return trainingMateri;
	}

	@Override
	public TrainingMateri updateImpl(TrainingMateri trainingMateri) {
		boolean isNew = trainingMateri.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(trainingMateri);
			}
			else {
				trainingMateri = (TrainingMateri)session.merge(trainingMateri);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			TrainingMateriImpl.class, trainingMateri, false, true);

		if (isNew) {
			trainingMateri.setNew(false);
		}

		trainingMateri.resetOriginalValues();

		return trainingMateri;
	}

	/**
	 * Returns the training materi with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training materi
	 * @return the training materi
	 * @throws NoSuchTrainingMateriException if a training materi with the primary key could not be found
	 */
	@Override
	public TrainingMateri findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTrainingMateriException {

		TrainingMateri trainingMateri = fetchByPrimaryKey(primaryKey);

		if (trainingMateri == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTrainingMateriException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return trainingMateri;
	}

	/**
	 * Returns the training materi with the primary key or throws a <code>NoSuchTrainingMateriException</code> if it could not be found.
	 *
	 * @param Id the primary key of the training materi
	 * @return the training materi
	 * @throws NoSuchTrainingMateriException if a training materi with the primary key could not be found
	 */
	@Override
	public TrainingMateri findByPrimaryKey(int Id)
		throws NoSuchTrainingMateriException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the training materi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the training materi
	 * @return the training materi, or <code>null</code> if a training materi with the primary key could not be found
	 */
	@Override
	public TrainingMateri fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the training materis.
	 *
	 * @return the training materis
	 */
	@Override
	public List<TrainingMateri> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training materis
	 * @param end the upper bound of the range of training materis (not inclusive)
	 * @return the range of training materis
	 */
	@Override
	public List<TrainingMateri> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training materis
	 * @param end the upper bound of the range of training materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training materis
	 */
	@Override
	public List<TrainingMateri> findAll(
		int start, int end,
		OrderByComparator<TrainingMateri> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the training materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training materis
	 * @param end the upper bound of the range of training materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of training materis
	 */
	@Override
	public List<TrainingMateri> findAll(
		int start, int end, OrderByComparator<TrainingMateri> orderByComparator,
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

		List<TrainingMateri> list = null;

		if (useFinderCache) {
			list = (List<TrainingMateri>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TRAININGMATERI);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGMATERI;

				sql = sql.concat(TrainingMateriModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TrainingMateri>)QueryUtil.list(
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
	 * Removes all the training materis from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TrainingMateri trainingMateri : findAll()) {
			remove(trainingMateri);
		}
	}

	/**
	 * Returns the number of training materis.
	 *
	 * @return the number of training materis
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_TRAININGMATERI);

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
		return _SQL_SELECT_TRAININGMATERI;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TrainingMateriModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the training materi persistence.
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

		_setTrainingMateriUtilPersistence(this);
	}

	public void destroy() {
		_setTrainingMateriUtilPersistence(null);

		dummyEntityCache.removeCache(TrainingMateriImpl.class.getName());
	}

	private void _setTrainingMateriUtilPersistence(
		TrainingMateriPersistence trainingMateriPersistence) {

		try {
			Field field = TrainingMateriUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, trainingMateriPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private static final String _SQL_SELECT_TRAININGMATERI =
		"SELECT trainingMateri FROM TrainingMateri trainingMateri";

	private static final String _SQL_COUNT_TRAININGMATERI =
		"SELECT COUNT(trainingMateri) FROM TrainingMateri trainingMateri";

	private static final String _ORDER_BY_ENTITY_ALIAS = "trainingMateri.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TrainingMateri exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		TrainingMateriPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}