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

import com.astra.dewa.exception.NoSuchMasterApprovalException;
import com.astra.dewa.model.MasterApproval;
import com.astra.dewa.model.MasterApprovalTable;
import com.astra.dewa.model.impl.MasterApprovalImpl;
import com.astra.dewa.model.impl.MasterApprovalModelImpl;
import com.astra.dewa.service.persistence.MasterApprovalPersistence;
import com.astra.dewa.service.persistence.MasterApprovalUtil;

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
 * The persistence implementation for the master approval service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MasterApprovalPersistenceImpl
	extends BasePersistenceImpl<MasterApproval>
	implements MasterApprovalPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MasterApprovalUtil</code> to access the master approval persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MasterApprovalImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public MasterApprovalPersistenceImpl() {
		setModelClass(MasterApproval.class);

		setModelImplClass(MasterApprovalImpl.class);
		setModelPKClass(int.class);

		setTable(MasterApprovalTable.INSTANCE);
	}

	/**
	 * Caches the master approval in the entity cache if it is enabled.
	 *
	 * @param masterApproval the master approval
	 */
	@Override
	public void cacheResult(MasterApproval masterApproval) {
		entityCache.putResult(
			MasterApprovalImpl.class, masterApproval.getPrimaryKey(),
			masterApproval);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the master approvals in the entity cache if it is enabled.
	 *
	 * @param masterApprovals the master approvals
	 */
	@Override
	public void cacheResult(List<MasterApproval> masterApprovals) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (masterApprovals.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (MasterApproval masterApproval : masterApprovals) {
			if (entityCache.getResult(
					MasterApprovalImpl.class, masterApproval.getPrimaryKey()) ==
						null) {

				cacheResult(masterApproval);
			}
		}
	}

	/**
	 * Clears the cache for all master approvals.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MasterApprovalImpl.class);

		finderCache.clearCache(MasterApprovalImpl.class);
	}

	/**
	 * Clears the cache for the master approval.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MasterApproval masterApproval) {
		entityCache.removeResult(MasterApprovalImpl.class, masterApproval);
	}

	@Override
	public void clearCache(List<MasterApproval> masterApprovals) {
		for (MasterApproval masterApproval : masterApprovals) {
			entityCache.removeResult(MasterApprovalImpl.class, masterApproval);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MasterApprovalImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(MasterApprovalImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new master approval with the primary key. Does not add the master approval to the database.
	 *
	 * @param Id the primary key for the new master approval
	 * @return the new master approval
	 */
	@Override
	public MasterApproval create(int Id) {
		MasterApproval masterApproval = new MasterApprovalImpl();

		masterApproval.setNew(true);
		masterApproval.setPrimaryKey(Id);

		return masterApproval;
	}

	/**
	 * Removes the master approval with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the master approval
	 * @return the master approval that was removed
	 * @throws NoSuchMasterApprovalException if a master approval with the primary key could not be found
	 */
	@Override
	public MasterApproval remove(int Id) throws NoSuchMasterApprovalException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the master approval with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the master approval
	 * @return the master approval that was removed
	 * @throws NoSuchMasterApprovalException if a master approval with the primary key could not be found
	 */
	@Override
	public MasterApproval remove(Serializable primaryKey)
		throws NoSuchMasterApprovalException {

		Session session = null;

		try {
			session = openSession();

			MasterApproval masterApproval = (MasterApproval)session.get(
				MasterApprovalImpl.class, primaryKey);

			if (masterApproval == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMasterApprovalException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(masterApproval);
		}
		catch (NoSuchMasterApprovalException noSuchEntityException) {
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
	protected MasterApproval removeImpl(MasterApproval masterApproval) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(masterApproval)) {
				masterApproval = (MasterApproval)session.get(
					MasterApprovalImpl.class,
					masterApproval.getPrimaryKeyObj());
			}

			if (masterApproval != null) {
				session.delete(masterApproval);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (masterApproval != null) {
			clearCache(masterApproval);
		}

		return masterApproval;
	}

	@Override
	public MasterApproval updateImpl(MasterApproval masterApproval) {
		boolean isNew = masterApproval.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(masterApproval);
			}
			else {
				masterApproval = (MasterApproval)session.merge(masterApproval);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MasterApprovalImpl.class, masterApproval, false, true);

		if (isNew) {
			masterApproval.setNew(false);
		}

		masterApproval.resetOriginalValues();

		return masterApproval;
	}

	/**
	 * Returns the master approval with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the master approval
	 * @return the master approval
	 * @throws NoSuchMasterApprovalException if a master approval with the primary key could not be found
	 */
	@Override
	public MasterApproval findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMasterApprovalException {

		MasterApproval masterApproval = fetchByPrimaryKey(primaryKey);

		if (masterApproval == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMasterApprovalException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return masterApproval;
	}

	/**
	 * Returns the master approval with the primary key or throws a <code>NoSuchMasterApprovalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval
	 * @return the master approval
	 * @throws NoSuchMasterApprovalException if a master approval with the primary key could not be found
	 */
	@Override
	public MasterApproval findByPrimaryKey(int Id)
		throws NoSuchMasterApprovalException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the master approval with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval
	 * @return the master approval, or <code>null</code> if a master approval with the primary key could not be found
	 */
	@Override
	public MasterApproval fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the master approvals.
	 *
	 * @return the master approvals
	 */
	@Override
	public List<MasterApproval> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the master approvals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approvals
	 * @param end the upper bound of the range of master approvals (not inclusive)
	 * @return the range of master approvals
	 */
	@Override
	public List<MasterApproval> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the master approvals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approvals
	 * @param end the upper bound of the range of master approvals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of master approvals
	 */
	@Override
	public List<MasterApproval> findAll(
		int start, int end,
		OrderByComparator<MasterApproval> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the master approvals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approvals
	 * @param end the upper bound of the range of master approvals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of master approvals
	 */
	@Override
	public List<MasterApproval> findAll(
		int start, int end, OrderByComparator<MasterApproval> orderByComparator,
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

		List<MasterApproval> list = null;

		if (useFinderCache) {
			list = (List<MasterApproval>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MASTERAPPROVAL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MASTERAPPROVAL;

				sql = sql.concat(MasterApprovalModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MasterApproval>)QueryUtil.list(
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
	 * Removes all the master approvals from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MasterApproval masterApproval : findAll()) {
			remove(masterApproval);
		}
	}

	/**
	 * Returns the number of master approvals.
	 *
	 * @return the number of master approvals
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_MASTERAPPROVAL);

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
		return _SQL_SELECT_MASTERAPPROVAL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MasterApprovalModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the master approval persistence.
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

		_setMasterApprovalUtilPersistence(this);
	}

	public void destroy() {
		_setMasterApprovalUtilPersistence(null);

		entityCache.removeCache(MasterApprovalImpl.class.getName());
	}

	private void _setMasterApprovalUtilPersistence(
		MasterApprovalPersistence masterApprovalPersistence) {

		try {
			Field field = MasterApprovalUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, masterApprovalPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MASTERAPPROVAL =
		"SELECT masterApproval FROM MasterApproval masterApproval";

	private static final String _SQL_COUNT_MASTERAPPROVAL =
		"SELECT COUNT(masterApproval) FROM MasterApproval masterApproval";

	private static final String _ORDER_BY_ENTITY_ALIAS = "masterApproval.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MasterApproval exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		MasterApprovalPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}