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

import com.astra.dewa.exception.NoSuchMasterApprovalJournalException;
import com.astra.dewa.model.MasterApprovalJournal;
import com.astra.dewa.model.MasterApprovalJournalTable;
import com.astra.dewa.model.impl.MasterApprovalJournalImpl;
import com.astra.dewa.model.impl.MasterApprovalJournalModelImpl;
import com.astra.dewa.service.persistence.MasterApprovalJournalPersistence;
import com.astra.dewa.service.persistence.MasterApprovalJournalUtil;

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
 * The persistence implementation for the master approval journal service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MasterApprovalJournalPersistenceImpl
	extends BasePersistenceImpl<MasterApprovalJournal>
	implements MasterApprovalJournalPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MasterApprovalJournalUtil</code> to access the master approval journal persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MasterApprovalJournalImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public MasterApprovalJournalPersistenceImpl() {
		setModelClass(MasterApprovalJournal.class);

		setModelImplClass(MasterApprovalJournalImpl.class);
		setModelPKClass(int.class);

		setTable(MasterApprovalJournalTable.INSTANCE);
	}

	/**
	 * Caches the master approval journal in the entity cache if it is enabled.
	 *
	 * @param masterApprovalJournal the master approval journal
	 */
	@Override
	public void cacheResult(MasterApprovalJournal masterApprovalJournal) {
		entityCache.putResult(
			MasterApprovalJournalImpl.class,
			masterApprovalJournal.getPrimaryKey(), masterApprovalJournal);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the master approval journals in the entity cache if it is enabled.
	 *
	 * @param masterApprovalJournals the master approval journals
	 */
	@Override
	public void cacheResult(
		List<MasterApprovalJournal> masterApprovalJournals) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (masterApprovalJournals.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (MasterApprovalJournal masterApprovalJournal :
				masterApprovalJournals) {

			if (entityCache.getResult(
					MasterApprovalJournalImpl.class,
					masterApprovalJournal.getPrimaryKey()) == null) {

				cacheResult(masterApprovalJournal);
			}
		}
	}

	/**
	 * Clears the cache for all master approval journals.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MasterApprovalJournalImpl.class);

		finderCache.clearCache(MasterApprovalJournalImpl.class);
	}

	/**
	 * Clears the cache for the master approval journal.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MasterApprovalJournal masterApprovalJournal) {
		entityCache.removeResult(
			MasterApprovalJournalImpl.class, masterApprovalJournal);
	}

	@Override
	public void clearCache(List<MasterApprovalJournal> masterApprovalJournals) {
		for (MasterApprovalJournal masterApprovalJournal :
				masterApprovalJournals) {

			entityCache.removeResult(
				MasterApprovalJournalImpl.class, masterApprovalJournal);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MasterApprovalJournalImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				MasterApprovalJournalImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new master approval journal with the primary key. Does not add the master approval journal to the database.
	 *
	 * @param Id the primary key for the new master approval journal
	 * @return the new master approval journal
	 */
	@Override
	public MasterApprovalJournal create(int Id) {
		MasterApprovalJournal masterApprovalJournal =
			new MasterApprovalJournalImpl();

		masterApprovalJournal.setNew(true);
		masterApprovalJournal.setPrimaryKey(Id);

		return masterApprovalJournal;
	}

	/**
	 * Removes the master approval journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the master approval journal
	 * @return the master approval journal that was removed
	 * @throws NoSuchMasterApprovalJournalException if a master approval journal with the primary key could not be found
	 */
	@Override
	public MasterApprovalJournal remove(int Id)
		throws NoSuchMasterApprovalJournalException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the master approval journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the master approval journal
	 * @return the master approval journal that was removed
	 * @throws NoSuchMasterApprovalJournalException if a master approval journal with the primary key could not be found
	 */
	@Override
	public MasterApprovalJournal remove(Serializable primaryKey)
		throws NoSuchMasterApprovalJournalException {

		Session session = null;

		try {
			session = openSession();

			MasterApprovalJournal masterApprovalJournal =
				(MasterApprovalJournal)session.get(
					MasterApprovalJournalImpl.class, primaryKey);

			if (masterApprovalJournal == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMasterApprovalJournalException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(masterApprovalJournal);
		}
		catch (NoSuchMasterApprovalJournalException noSuchEntityException) {
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
	protected MasterApprovalJournal removeImpl(
		MasterApprovalJournal masterApprovalJournal) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(masterApprovalJournal)) {
				masterApprovalJournal = (MasterApprovalJournal)session.get(
					MasterApprovalJournalImpl.class,
					masterApprovalJournal.getPrimaryKeyObj());
			}

			if (masterApprovalJournal != null) {
				session.delete(masterApprovalJournal);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (masterApprovalJournal != null) {
			clearCache(masterApprovalJournal);
		}

		return masterApprovalJournal;
	}

	@Override
	public MasterApprovalJournal updateImpl(
		MasterApprovalJournal masterApprovalJournal) {

		boolean isNew = masterApprovalJournal.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(masterApprovalJournal);
			}
			else {
				masterApprovalJournal = (MasterApprovalJournal)session.merge(
					masterApprovalJournal);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MasterApprovalJournalImpl.class, masterApprovalJournal, false,
			true);

		if (isNew) {
			masterApprovalJournal.setNew(false);
		}

		masterApprovalJournal.resetOriginalValues();

		return masterApprovalJournal;
	}

	/**
	 * Returns the master approval journal with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the master approval journal
	 * @return the master approval journal
	 * @throws NoSuchMasterApprovalJournalException if a master approval journal with the primary key could not be found
	 */
	@Override
	public MasterApprovalJournal findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMasterApprovalJournalException {

		MasterApprovalJournal masterApprovalJournal = fetchByPrimaryKey(
			primaryKey);

		if (masterApprovalJournal == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMasterApprovalJournalException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return masterApprovalJournal;
	}

	/**
	 * Returns the master approval journal with the primary key or throws a <code>NoSuchMasterApprovalJournalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval journal
	 * @return the master approval journal
	 * @throws NoSuchMasterApprovalJournalException if a master approval journal with the primary key could not be found
	 */
	@Override
	public MasterApprovalJournal findByPrimaryKey(int Id)
		throws NoSuchMasterApprovalJournalException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the master approval journal with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval journal
	 * @return the master approval journal, or <code>null</code> if a master approval journal with the primary key could not be found
	 */
	@Override
	public MasterApprovalJournal fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the master approval journals.
	 *
	 * @return the master approval journals
	 */
	@Override
	public List<MasterApprovalJournal> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the master approval journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval journals
	 * @param end the upper bound of the range of master approval journals (not inclusive)
	 * @return the range of master approval journals
	 */
	@Override
	public List<MasterApprovalJournal> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the master approval journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval journals
	 * @param end the upper bound of the range of master approval journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of master approval journals
	 */
	@Override
	public List<MasterApprovalJournal> findAll(
		int start, int end,
		OrderByComparator<MasterApprovalJournal> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the master approval journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval journals
	 * @param end the upper bound of the range of master approval journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of master approval journals
	 */
	@Override
	public List<MasterApprovalJournal> findAll(
		int start, int end,
		OrderByComparator<MasterApprovalJournal> orderByComparator,
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

		List<MasterApprovalJournal> list = null;

		if (useFinderCache) {
			list = (List<MasterApprovalJournal>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MASTERAPPROVALJOURNAL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MASTERAPPROVALJOURNAL;

				sql = sql.concat(MasterApprovalJournalModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MasterApprovalJournal>)QueryUtil.list(
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
	 * Removes all the master approval journals from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MasterApprovalJournal masterApprovalJournal : findAll()) {
			remove(masterApprovalJournal);
		}
	}

	/**
	 * Returns the number of master approval journals.
	 *
	 * @return the number of master approval journals
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
					_SQL_COUNT_MASTERAPPROVALJOURNAL);

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
		return _SQL_SELECT_MASTERAPPROVALJOURNAL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MasterApprovalJournalModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the master approval journal persistence.
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

		_setMasterApprovalJournalUtilPersistence(this);
	}

	public void destroy() {
		_setMasterApprovalJournalUtilPersistence(null);

		entityCache.removeCache(MasterApprovalJournalImpl.class.getName());
	}

	private void _setMasterApprovalJournalUtilPersistence(
		MasterApprovalJournalPersistence masterApprovalJournalPersistence) {

		try {
			Field field = MasterApprovalJournalUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, masterApprovalJournalPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MASTERAPPROVALJOURNAL =
		"SELECT masterApprovalJournal FROM MasterApprovalJournal masterApprovalJournal";

	private static final String _SQL_COUNT_MASTERAPPROVALJOURNAL =
		"SELECT COUNT(masterApprovalJournal) FROM MasterApprovalJournal masterApprovalJournal";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"masterApprovalJournal.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MasterApprovalJournal exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		MasterApprovalJournalPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}