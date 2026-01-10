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

import com.astra.dewa.exception.NoSuchTrainingAgendaParticipantException;
import com.astra.dewa.model.TrainingAgendaParticipant;
import com.astra.dewa.model.TrainingAgendaParticipantTable;
import com.astra.dewa.model.impl.TrainingAgendaParticipantImpl;
import com.astra.dewa.model.impl.TrainingAgendaParticipantModelImpl;
import com.astra.dewa.service.persistence.TrainingAgendaParticipantPersistence;
import com.astra.dewa.service.persistence.TrainingAgendaParticipantUtil;

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
 * The persistence implementation for the training agenda participant service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TrainingAgendaParticipantPersistenceImpl
	extends BasePersistenceImpl<TrainingAgendaParticipant>
	implements TrainingAgendaParticipantPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TrainingAgendaParticipantUtil</code> to access the training agenda participant persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TrainingAgendaParticipantImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public TrainingAgendaParticipantPersistenceImpl() {
		setModelClass(TrainingAgendaParticipant.class);

		setModelImplClass(TrainingAgendaParticipantImpl.class);
		setModelPKClass(int.class);

		setTable(TrainingAgendaParticipantTable.INSTANCE);
	}

	/**
	 * Caches the training agenda participant in the entity cache if it is enabled.
	 *
	 * @param trainingAgendaParticipant the training agenda participant
	 */
	@Override
	public void cacheResult(
		TrainingAgendaParticipant trainingAgendaParticipant) {

		dummyEntityCache.putResult(
			TrainingAgendaParticipantImpl.class,
			trainingAgendaParticipant.getPrimaryKey(),
			trainingAgendaParticipant);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the training agenda participants in the entity cache if it is enabled.
	 *
	 * @param trainingAgendaParticipants the training agenda participants
	 */
	@Override
	public void cacheResult(
		List<TrainingAgendaParticipant> trainingAgendaParticipants) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (trainingAgendaParticipants.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TrainingAgendaParticipant trainingAgendaParticipant :
				trainingAgendaParticipants) {

			if (dummyEntityCache.getResult(
					TrainingAgendaParticipantImpl.class,
					trainingAgendaParticipant.getPrimaryKey()) == null) {

				cacheResult(trainingAgendaParticipant);
			}
		}
	}

	/**
	 * Clears the cache for all training agenda participants.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(TrainingAgendaParticipantImpl.class);

		dummyFinderCache.clearCache(TrainingAgendaParticipantImpl.class);
	}

	/**
	 * Clears the cache for the training agenda participant.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		TrainingAgendaParticipant trainingAgendaParticipant) {

		dummyEntityCache.removeResult(
			TrainingAgendaParticipantImpl.class, trainingAgendaParticipant);
	}

	@Override
	public void clearCache(
		List<TrainingAgendaParticipant> trainingAgendaParticipants) {

		for (TrainingAgendaParticipant trainingAgendaParticipant :
				trainingAgendaParticipants) {

			dummyEntityCache.removeResult(
				TrainingAgendaParticipantImpl.class, trainingAgendaParticipant);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(TrainingAgendaParticipantImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(
				TrainingAgendaParticipantImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new training agenda participant with the primary key. Does not add the training agenda participant to the database.
	 *
	 * @param Id the primary key for the new training agenda participant
	 * @return the new training agenda participant
	 */
	@Override
	public TrainingAgendaParticipant create(int Id) {
		TrainingAgendaParticipant trainingAgendaParticipant =
			new TrainingAgendaParticipantImpl();

		trainingAgendaParticipant.setNew(true);
		trainingAgendaParticipant.setPrimaryKey(Id);

		return trainingAgendaParticipant;
	}

	/**
	 * Removes the training agenda participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the training agenda participant
	 * @return the training agenda participant that was removed
	 * @throws NoSuchTrainingAgendaParticipantException if a training agenda participant with the primary key could not be found
	 */
	@Override
	public TrainingAgendaParticipant remove(int Id)
		throws NoSuchTrainingAgendaParticipantException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the training agenda participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training agenda participant
	 * @return the training agenda participant that was removed
	 * @throws NoSuchTrainingAgendaParticipantException if a training agenda participant with the primary key could not be found
	 */
	@Override
	public TrainingAgendaParticipant remove(Serializable primaryKey)
		throws NoSuchTrainingAgendaParticipantException {

		Session session = null;

		try {
			session = openSession();

			TrainingAgendaParticipant trainingAgendaParticipant =
				(TrainingAgendaParticipant)session.get(
					TrainingAgendaParticipantImpl.class, primaryKey);

			if (trainingAgendaParticipant == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingAgendaParticipantException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(trainingAgendaParticipant);
		}
		catch (NoSuchTrainingAgendaParticipantException noSuchEntityException) {
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
	protected TrainingAgendaParticipant removeImpl(
		TrainingAgendaParticipant trainingAgendaParticipant) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(trainingAgendaParticipant)) {
				trainingAgendaParticipant =
					(TrainingAgendaParticipant)session.get(
						TrainingAgendaParticipantImpl.class,
						trainingAgendaParticipant.getPrimaryKeyObj());
			}

			if (trainingAgendaParticipant != null) {
				session.delete(trainingAgendaParticipant);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (trainingAgendaParticipant != null) {
			clearCache(trainingAgendaParticipant);
		}

		return trainingAgendaParticipant;
	}

	@Override
	public TrainingAgendaParticipant updateImpl(
		TrainingAgendaParticipant trainingAgendaParticipant) {

		boolean isNew = trainingAgendaParticipant.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(trainingAgendaParticipant);
			}
			else {
				trainingAgendaParticipant =
					(TrainingAgendaParticipant)session.merge(
						trainingAgendaParticipant);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			TrainingAgendaParticipantImpl.class, trainingAgendaParticipant,
			false, true);

		if (isNew) {
			trainingAgendaParticipant.setNew(false);
		}

		trainingAgendaParticipant.resetOriginalValues();

		return trainingAgendaParticipant;
	}

	/**
	 * Returns the training agenda participant with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training agenda participant
	 * @return the training agenda participant
	 * @throws NoSuchTrainingAgendaParticipantException if a training agenda participant with the primary key could not be found
	 */
	@Override
	public TrainingAgendaParticipant findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTrainingAgendaParticipantException {

		TrainingAgendaParticipant trainingAgendaParticipant = fetchByPrimaryKey(
			primaryKey);

		if (trainingAgendaParticipant == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTrainingAgendaParticipantException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return trainingAgendaParticipant;
	}

	/**
	 * Returns the training agenda participant with the primary key or throws a <code>NoSuchTrainingAgendaParticipantException</code> if it could not be found.
	 *
	 * @param Id the primary key of the training agenda participant
	 * @return the training agenda participant
	 * @throws NoSuchTrainingAgendaParticipantException if a training agenda participant with the primary key could not be found
	 */
	@Override
	public TrainingAgendaParticipant findByPrimaryKey(int Id)
		throws NoSuchTrainingAgendaParticipantException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the training agenda participant with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the training agenda participant
	 * @return the training agenda participant, or <code>null</code> if a training agenda participant with the primary key could not be found
	 */
	@Override
	public TrainingAgendaParticipant fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the training agenda participants.
	 *
	 * @return the training agenda participants
	 */
	@Override
	public List<TrainingAgendaParticipant> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training agenda participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agenda participants
	 * @param end the upper bound of the range of training agenda participants (not inclusive)
	 * @return the range of training agenda participants
	 */
	@Override
	public List<TrainingAgendaParticipant> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training agenda participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agenda participants
	 * @param end the upper bound of the range of training agenda participants (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training agenda participants
	 */
	@Override
	public List<TrainingAgendaParticipant> findAll(
		int start, int end,
		OrderByComparator<TrainingAgendaParticipant> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the training agenda participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingAgendaParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training agenda participants
	 * @param end the upper bound of the range of training agenda participants (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of training agenda participants
	 */
	@Override
	public List<TrainingAgendaParticipant> findAll(
		int start, int end,
		OrderByComparator<TrainingAgendaParticipant> orderByComparator,
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

		List<TrainingAgendaParticipant> list = null;

		if (useFinderCache) {
			list = (List<TrainingAgendaParticipant>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TRAININGAGENDAPARTICIPANT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGAGENDAPARTICIPANT;

				sql = sql.concat(
					TrainingAgendaParticipantModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TrainingAgendaParticipant>)QueryUtil.list(
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
	 * Removes all the training agenda participants from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TrainingAgendaParticipant trainingAgendaParticipant : findAll()) {
			remove(trainingAgendaParticipant);
		}
	}

	/**
	 * Returns the number of training agenda participants.
	 *
	 * @return the number of training agenda participants
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
					_SQL_COUNT_TRAININGAGENDAPARTICIPANT);

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
		return _SQL_SELECT_TRAININGAGENDAPARTICIPANT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TrainingAgendaParticipantModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the training agenda participant persistence.
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

		_setTrainingAgendaParticipantUtilPersistence(this);
	}

	public void destroy() {
		_setTrainingAgendaParticipantUtilPersistence(null);

		dummyEntityCache.removeCache(
			TrainingAgendaParticipantImpl.class.getName());
	}

	private void _setTrainingAgendaParticipantUtilPersistence(
		TrainingAgendaParticipantPersistence
			trainingAgendaParticipantPersistence) {

		try {
			Field field = TrainingAgendaParticipantUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, trainingAgendaParticipantPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private static final String _SQL_SELECT_TRAININGAGENDAPARTICIPANT =
		"SELECT trainingAgendaParticipant FROM TrainingAgendaParticipant trainingAgendaParticipant";

	private static final String _SQL_COUNT_TRAININGAGENDAPARTICIPANT =
		"SELECT COUNT(trainingAgendaParticipant) FROM TrainingAgendaParticipant trainingAgendaParticipant";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"trainingAgendaParticipant.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TrainingAgendaParticipant exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		TrainingAgendaParticipantPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}