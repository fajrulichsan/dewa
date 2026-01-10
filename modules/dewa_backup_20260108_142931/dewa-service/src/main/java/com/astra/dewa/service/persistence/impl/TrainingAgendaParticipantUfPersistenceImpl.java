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

import com.astra.dewa.exception.NoSuchTrainingAgendaParticipantUfException;
import com.astra.dewa.model.TrainingAgendaParticipantUf;
import com.astra.dewa.model.TrainingAgendaParticipantUfTable;
import com.astra.dewa.model.impl.TrainingAgendaParticipantUfImpl;
import com.astra.dewa.model.impl.TrainingAgendaParticipantUfModelImpl;
import com.astra.dewa.service.persistence.TrainingAgendaParticipantUfPersistence;
import com.astra.dewa.service.persistence.TrainingAgendaParticipantUfUtil;

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
 * The persistence implementation for the training agenda participant uf service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TrainingAgendaParticipantUfPersistenceImpl
	extends BasePersistenceImpl<TrainingAgendaParticipantUf>
	implements TrainingAgendaParticipantUfPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TrainingAgendaParticipantUfUtil</code> to access the training agenda participant uf persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TrainingAgendaParticipantUfImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public TrainingAgendaParticipantUfPersistenceImpl() {
		setModelClass(TrainingAgendaParticipantUf.class);

		setModelImplClass(TrainingAgendaParticipantUfImpl.class);
		setModelPKClass(int.class);

		setTable(TrainingAgendaParticipantUfTable.INSTANCE);
	}

	/**
	 * Caches the training agenda participant uf in the entity cache if it is enabled.
	 *
	 * @param trainingAgendaParticipantUf the training agenda participant uf
	 */
	@Override
	public void cacheResult(
		TrainingAgendaParticipantUf trainingAgendaParticipantUf) {

		dummyEntityCache.putResult(
			TrainingAgendaParticipantUfImpl.class,
			trainingAgendaParticipantUf.getPrimaryKey(),
			trainingAgendaParticipantUf);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the training agenda participant ufs in the entity cache if it is enabled.
	 *
	 * @param trainingAgendaParticipantUfs the training agenda participant ufs
	 */
	@Override
	public void cacheResult(
		List<TrainingAgendaParticipantUf> trainingAgendaParticipantUfs) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (trainingAgendaParticipantUfs.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TrainingAgendaParticipantUf trainingAgendaParticipantUf :
				trainingAgendaParticipantUfs) {

			if (dummyEntityCache.getResult(
					TrainingAgendaParticipantUfImpl.class,
					trainingAgendaParticipantUf.getPrimaryKey()) == null) {

				cacheResult(trainingAgendaParticipantUf);
			}
		}
	}

	/**
	 * Clears the cache for all training agenda participant ufs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(TrainingAgendaParticipantUfImpl.class);

		dummyFinderCache.clearCache(TrainingAgendaParticipantUfImpl.class);
	}

	/**
	 * Clears the cache for the training agenda participant uf.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		TrainingAgendaParticipantUf trainingAgendaParticipantUf) {

		dummyEntityCache.removeResult(
			TrainingAgendaParticipantUfImpl.class, trainingAgendaParticipantUf);
	}

	@Override
	public void clearCache(
		List<TrainingAgendaParticipantUf> trainingAgendaParticipantUfs) {

		for (TrainingAgendaParticipantUf trainingAgendaParticipantUf :
				trainingAgendaParticipantUfs) {

			dummyEntityCache.removeResult(
				TrainingAgendaParticipantUfImpl.class,
				trainingAgendaParticipantUf);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(TrainingAgendaParticipantUfImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(
				TrainingAgendaParticipantUfImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new training agenda participant uf with the primary key. Does not add the training agenda participant uf to the database.
	 *
	 * @param Id the primary key for the new training agenda participant uf
	 * @return the new training agenda participant uf
	 */
	@Override
	public TrainingAgendaParticipantUf create(int Id) {
		TrainingAgendaParticipantUf trainingAgendaParticipantUf =
			new TrainingAgendaParticipantUfImpl();

		trainingAgendaParticipantUf.setNew(true);
		trainingAgendaParticipantUf.setPrimaryKey(Id);

		return trainingAgendaParticipantUf;
	}

	/**
	 * Removes the training agenda participant uf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the training agenda participant uf
	 * @return the training agenda participant uf that was removed
	 * @throws NoSuchTrainingAgendaParticipantUfException if a training agenda participant uf with the primary key could not be found
	 */
	@Override
	public TrainingAgendaParticipantUf remove(int Id)
		throws NoSuchTrainingAgendaParticipantUfException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the training agenda participant uf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training agenda participant uf
	 * @return the training agenda participant uf that was removed
	 * @throws NoSuchTrainingAgendaParticipantUfException if a training agenda participant uf with the primary key could not be found
	 */
	@Override
	public TrainingAgendaParticipantUf remove(Serializable primaryKey)
		throws NoSuchTrainingAgendaParticipantUfException {

		Session session = null;

		try {
			session = openSession();

			TrainingAgendaParticipantUf trainingAgendaParticipantUf =
				(TrainingAgendaParticipantUf)session.get(
					TrainingAgendaParticipantUfImpl.class, primaryKey);

			if (trainingAgendaParticipantUf == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingAgendaParticipantUfException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(trainingAgendaParticipantUf);
		}
		catch (NoSuchTrainingAgendaParticipantUfException
					noSuchEntityException) {

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
	protected TrainingAgendaParticipantUf removeImpl(
		TrainingAgendaParticipantUf trainingAgendaParticipantUf) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(trainingAgendaParticipantUf)) {
				trainingAgendaParticipantUf =
					(TrainingAgendaParticipantUf)session.get(
						TrainingAgendaParticipantUfImpl.class,
						trainingAgendaParticipantUf.getPrimaryKeyObj());
			}

			if (trainingAgendaParticipantUf != null) {
				session.delete(trainingAgendaParticipantUf);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (trainingAgendaParticipantUf != null) {
			clearCache(trainingAgendaParticipantUf);
		}

		return trainingAgendaParticipantUf;
	}

	@Override
	public TrainingAgendaParticipantUf updateImpl(
		TrainingAgendaParticipantUf trainingAgendaParticipantUf) {

		boolean isNew = trainingAgendaParticipantUf.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(trainingAgendaParticipantUf);
			}
			else {
				trainingAgendaParticipantUf =
					(TrainingAgendaParticipantUf)session.merge(
						trainingAgendaParticipantUf);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			TrainingAgendaParticipantUfImpl.class, trainingAgendaParticipantUf,
			false, true);

		if (isNew) {
			trainingAgendaParticipantUf.setNew(false);
		}

		trainingAgendaParticipantUf.resetOriginalValues();

		return trainingAgendaParticipantUf;
	}

	/**
	 * Returns the training agenda participant uf with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training agenda participant uf
	 * @return the training agenda participant uf
	 * @throws NoSuchTrainingAgendaParticipantUfException if a training agenda participant uf with the primary key could not be found
	 */
	@Override
	public TrainingAgendaParticipantUf findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTrainingAgendaParticipantUfException {

		TrainingAgendaParticipantUf trainingAgendaParticipantUf =
			fetchByPrimaryKey(primaryKey);

		if (trainingAgendaParticipantUf == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTrainingAgendaParticipantUfException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return trainingAgendaParticipantUf;
	}

	/**
	 * Returns the training agenda participant uf with the primary key or throws a <code>NoSuchTrainingAgendaParticipantUfException</code> if it could not be found.
	 *
	 * @param Id the primary key of the training agenda participant uf
	 * @return the training agenda participant uf
	 * @throws NoSuchTrainingAgendaParticipantUfException if a training agenda participant uf with the primary key could not be found
	 */
	@Override
	public TrainingAgendaParticipantUf findByPrimaryKey(int Id)
		throws NoSuchTrainingAgendaParticipantUfException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the training agenda participant uf with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the training agenda participant uf
	 * @return the training agenda participant uf, or <code>null</code> if a training agenda participant uf with the primary key could not be found
	 */
	@Override
	public TrainingAgendaParticipantUf fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the training agenda participant ufs.
	 *
	 * @return the training agenda participant ufs
	 */
	@Override
	public List<TrainingAgendaParticipantUf> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training agenda participant ufs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaParticipantUfModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agenda participant ufs
	 * @param end the upper bound of the range of training agenda participant ufs (not inclusive)
	 * @return the range of training agenda participant ufs
	 */
	@Override
	public List<TrainingAgendaParticipantUf> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training agenda participant ufs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaParticipantUfModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agenda participant ufs
	 * @param end the upper bound of the range of training agenda participant ufs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training agenda participant ufs
	 */
	@Override
	public List<TrainingAgendaParticipantUf> findAll(
		int start, int end,
		OrderByComparator<TrainingAgendaParticipantUf> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the training agenda participant ufs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaParticipantUfModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agenda participant ufs
	 * @param end the upper bound of the range of training agenda participant ufs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of training agenda participant ufs
	 */
	@Override
	public List<TrainingAgendaParticipantUf> findAll(
		int start, int end,
		OrderByComparator<TrainingAgendaParticipantUf> orderByComparator,
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

		List<TrainingAgendaParticipantUf> list = null;

		if (useFinderCache) {
			list =
				(List<TrainingAgendaParticipantUf>)dummyFinderCache.getResult(
					finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TRAININGAGENDAPARTICIPANTUF);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGAGENDAPARTICIPANTUF;

				sql = sql.concat(
					TrainingAgendaParticipantUfModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TrainingAgendaParticipantUf>)QueryUtil.list(
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
	 * Removes all the training agenda participant ufs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TrainingAgendaParticipantUf trainingAgendaParticipantUf :
				findAll()) {

			remove(trainingAgendaParticipantUf);
		}
	}

	/**
	 * Returns the number of training agenda participant ufs.
	 *
	 * @return the number of training agenda participant ufs
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
					_SQL_COUNT_TRAININGAGENDAPARTICIPANTUF);

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
		return _SQL_SELECT_TRAININGAGENDAPARTICIPANTUF;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TrainingAgendaParticipantUfModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the training agenda participant uf persistence.
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

		_setTrainingAgendaParticipantUfUtilPersistence(this);
	}

	public void destroy() {
		_setTrainingAgendaParticipantUfUtilPersistence(null);

		dummyEntityCache.removeCache(
			TrainingAgendaParticipantUfImpl.class.getName());
	}

	private void _setTrainingAgendaParticipantUfUtilPersistence(
		TrainingAgendaParticipantUfPersistence
			trainingAgendaParticipantUfPersistence) {

		try {
			Field field =
				TrainingAgendaParticipantUfUtil.class.getDeclaredField(
					"_persistence");

			field.setAccessible(true);

			field.set(null, trainingAgendaParticipantUfPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private static final String _SQL_SELECT_TRAININGAGENDAPARTICIPANTUF =
		"SELECT trainingAgendaParticipantUf FROM TrainingAgendaParticipantUf trainingAgendaParticipantUf";

	private static final String _SQL_COUNT_TRAININGAGENDAPARTICIPANTUF =
		"SELECT COUNT(trainingAgendaParticipantUf) FROM TrainingAgendaParticipantUf trainingAgendaParticipantUf";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"trainingAgendaParticipantUf.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TrainingAgendaParticipantUf exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		TrainingAgendaParticipantUfPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}