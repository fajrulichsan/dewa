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

import com.astra.dewa.exception.NoSuchMasterApprovalDetailJournalException;
import com.astra.dewa.model.MasterApprovalDetailJournal;
import com.astra.dewa.model.MasterApprovalDetailJournalTable;
import com.astra.dewa.model.impl.MasterApprovalDetailJournalImpl;
import com.astra.dewa.model.impl.MasterApprovalDetailJournalModelImpl;
import com.astra.dewa.service.persistence.MasterApprovalDetailJournalPersistence;
import com.astra.dewa.service.persistence.MasterApprovalDetailJournalUtil;

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
 * The persistence implementation for the master approval detail journal service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MasterApprovalDetailJournalPersistenceImpl
	extends BasePersistenceImpl<MasterApprovalDetailJournal>
	implements MasterApprovalDetailJournalPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MasterApprovalDetailJournalUtil</code> to access the master approval detail journal persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MasterApprovalDetailJournalImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public MasterApprovalDetailJournalPersistenceImpl() {
		setModelClass(MasterApprovalDetailJournal.class);

		setModelImplClass(MasterApprovalDetailJournalImpl.class);
		setModelPKClass(int.class);

		setTable(MasterApprovalDetailJournalTable.INSTANCE);
	}

	/**
	 * Caches the master approval detail journal in the entity cache if it is enabled.
	 *
	 * @param masterApprovalDetailJournal the master approval detail journal
	 */
	@Override
	public void cacheResult(
		MasterApprovalDetailJournal masterApprovalDetailJournal) {

		entityCache.putResult(
			MasterApprovalDetailJournalImpl.class,
			masterApprovalDetailJournal.getPrimaryKey(),
			masterApprovalDetailJournal);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the master approval detail journals in the entity cache if it is enabled.
	 *
	 * @param masterApprovalDetailJournals the master approval detail journals
	 */
	@Override
	public void cacheResult(
		List<MasterApprovalDetailJournal> masterApprovalDetailJournals) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (masterApprovalDetailJournals.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (MasterApprovalDetailJournal masterApprovalDetailJournal :
				masterApprovalDetailJournals) {

			if (entityCache.getResult(
					MasterApprovalDetailJournalImpl.class,
					masterApprovalDetailJournal.getPrimaryKey()) == null) {

				cacheResult(masterApprovalDetailJournal);
			}
		}
	}

	/**
	 * Clears the cache for all master approval detail journals.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MasterApprovalDetailJournalImpl.class);

		finderCache.clearCache(MasterApprovalDetailJournalImpl.class);
	}

	/**
	 * Clears the cache for the master approval detail journal.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		MasterApprovalDetailJournal masterApprovalDetailJournal) {

		entityCache.removeResult(
			MasterApprovalDetailJournalImpl.class, masterApprovalDetailJournal);
	}

	@Override
	public void clearCache(
		List<MasterApprovalDetailJournal> masterApprovalDetailJournals) {

		for (MasterApprovalDetailJournal masterApprovalDetailJournal :
				masterApprovalDetailJournals) {

			entityCache.removeResult(
				MasterApprovalDetailJournalImpl.class,
				masterApprovalDetailJournal);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MasterApprovalDetailJournalImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				MasterApprovalDetailJournalImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new master approval detail journal with the primary key. Does not add the master approval detail journal to the database.
	 *
	 * @param Id the primary key for the new master approval detail journal
	 * @return the new master approval detail journal
	 */
	@Override
	public MasterApprovalDetailJournal create(int Id) {
		MasterApprovalDetailJournal masterApprovalDetailJournal =
			new MasterApprovalDetailJournalImpl();

		masterApprovalDetailJournal.setNew(true);
		masterApprovalDetailJournal.setPrimaryKey(Id);

		return masterApprovalDetailJournal;
	}

	/**
	 * Removes the master approval detail journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the master approval detail journal
	 * @return the master approval detail journal that was removed
	 * @throws NoSuchMasterApprovalDetailJournalException if a master approval detail journal with the primary key could not be found
	 */
	@Override
	public MasterApprovalDetailJournal remove(int Id)
		throws NoSuchMasterApprovalDetailJournalException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the master approval detail journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the master approval detail journal
	 * @return the master approval detail journal that was removed
	 * @throws NoSuchMasterApprovalDetailJournalException if a master approval detail journal with the primary key could not be found
	 */
	@Override
	public MasterApprovalDetailJournal remove(Serializable primaryKey)
		throws NoSuchMasterApprovalDetailJournalException {

		Session session = null;

		try {
			session = openSession();

			MasterApprovalDetailJournal masterApprovalDetailJournal =
				(MasterApprovalDetailJournal)session.get(
					MasterApprovalDetailJournalImpl.class, primaryKey);

			if (masterApprovalDetailJournal == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMasterApprovalDetailJournalException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(masterApprovalDetailJournal);
		}
		catch (NoSuchMasterApprovalDetailJournalException
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
	protected MasterApprovalDetailJournal removeImpl(
		MasterApprovalDetailJournal masterApprovalDetailJournal) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(masterApprovalDetailJournal)) {
				masterApprovalDetailJournal =
					(MasterApprovalDetailJournal)session.get(
						MasterApprovalDetailJournalImpl.class,
						masterApprovalDetailJournal.getPrimaryKeyObj());
			}

			if (masterApprovalDetailJournal != null) {
				session.delete(masterApprovalDetailJournal);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (masterApprovalDetailJournal != null) {
			clearCache(masterApprovalDetailJournal);
		}

		return masterApprovalDetailJournal;
	}

	@Override
	public MasterApprovalDetailJournal updateImpl(
		MasterApprovalDetailJournal masterApprovalDetailJournal) {

		boolean isNew = masterApprovalDetailJournal.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(masterApprovalDetailJournal);
			}
			else {
				masterApprovalDetailJournal =
					(MasterApprovalDetailJournal)session.merge(
						masterApprovalDetailJournal);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MasterApprovalDetailJournalImpl.class, masterApprovalDetailJournal,
			false, true);

		if (isNew) {
			masterApprovalDetailJournal.setNew(false);
		}

		masterApprovalDetailJournal.resetOriginalValues();

		return masterApprovalDetailJournal;
	}

	/**
	 * Returns the master approval detail journal with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the master approval detail journal
	 * @return the master approval detail journal
	 * @throws NoSuchMasterApprovalDetailJournalException if a master approval detail journal with the primary key could not be found
	 */
	@Override
	public MasterApprovalDetailJournal findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMasterApprovalDetailJournalException {

		MasterApprovalDetailJournal masterApprovalDetailJournal =
			fetchByPrimaryKey(primaryKey);

		if (masterApprovalDetailJournal == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMasterApprovalDetailJournalException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return masterApprovalDetailJournal;
	}

	/**
	 * Returns the master approval detail journal with the primary key or throws a <code>NoSuchMasterApprovalDetailJournalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval detail journal
	 * @return the master approval detail journal
	 * @throws NoSuchMasterApprovalDetailJournalException if a master approval detail journal with the primary key could not be found
	 */
	@Override
	public MasterApprovalDetailJournal findByPrimaryKey(int Id)
		throws NoSuchMasterApprovalDetailJournalException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the master approval detail journal with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval detail journal
	 * @return the master approval detail journal, or <code>null</code> if a master approval detail journal with the primary key could not be found
	 */
	@Override
	public MasterApprovalDetailJournal fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the master approval detail journals.
	 *
	 * @return the master approval detail journals
	 */
	@Override
	public List<MasterApprovalDetailJournal> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the master approval detail journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval detail journals
	 * @param end the upper bound of the range of master approval detail journals (not inclusive)
	 * @return the range of master approval detail journals
	 */
	@Override
	public List<MasterApprovalDetailJournal> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the master approval detail journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval detail journals
	 * @param end the upper bound of the range of master approval detail journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of master approval detail journals
	 */
	@Override
	public List<MasterApprovalDetailJournal> findAll(
		int start, int end,
		OrderByComparator<MasterApprovalDetailJournal> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the master approval detail journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval detail journals
	 * @param end the upper bound of the range of master approval detail journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of master approval detail journals
	 */
	@Override
	public List<MasterApprovalDetailJournal> findAll(
		int start, int end,
		OrderByComparator<MasterApprovalDetailJournal> orderByComparator,
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

		List<MasterApprovalDetailJournal> list = null;

		if (useFinderCache) {
			list = (List<MasterApprovalDetailJournal>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MASTERAPPROVALDETAILJOURNAL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MASTERAPPROVALDETAILJOURNAL;

				sql = sql.concat(
					MasterApprovalDetailJournalModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MasterApprovalDetailJournal>)QueryUtil.list(
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
	 * Removes all the master approval detail journals from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MasterApprovalDetailJournal masterApprovalDetailJournal :
				findAll()) {

			remove(masterApprovalDetailJournal);
		}
	}

	/**
	 * Returns the number of master approval detail journals.
	 *
	 * @return the number of master approval detail journals
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
					_SQL_COUNT_MASTERAPPROVALDETAILJOURNAL);

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
		return _SQL_SELECT_MASTERAPPROVALDETAILJOURNAL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MasterApprovalDetailJournalModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the master approval detail journal persistence.
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

		_setMasterApprovalDetailJournalUtilPersistence(this);
	}

	public void destroy() {
		_setMasterApprovalDetailJournalUtilPersistence(null);

		entityCache.removeCache(
			MasterApprovalDetailJournalImpl.class.getName());
	}

	private void _setMasterApprovalDetailJournalUtilPersistence(
		MasterApprovalDetailJournalPersistence
			masterApprovalDetailJournalPersistence) {

		try {
			Field field =
				MasterApprovalDetailJournalUtil.class.getDeclaredField(
					"_persistence");

			field.setAccessible(true);

			field.set(null, masterApprovalDetailJournalPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MASTERAPPROVALDETAILJOURNAL =
		"SELECT masterApprovalDetailJournal FROM MasterApprovalDetailJournal masterApprovalDetailJournal";

	private static final String _SQL_COUNT_MASTERAPPROVALDETAILJOURNAL =
		"SELECT COUNT(masterApprovalDetailJournal) FROM MasterApprovalDetailJournal masterApprovalDetailJournal";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"masterApprovalDetailJournal.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MasterApprovalDetailJournal exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		MasterApprovalDetailJournalPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}