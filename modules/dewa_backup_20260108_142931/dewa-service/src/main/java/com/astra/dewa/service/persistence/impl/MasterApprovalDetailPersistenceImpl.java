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

import com.astra.dewa.exception.NoSuchMasterApprovalDetailException;
import com.astra.dewa.model.MasterApprovalDetail;
import com.astra.dewa.model.MasterApprovalDetailTable;
import com.astra.dewa.model.impl.MasterApprovalDetailImpl;
import com.astra.dewa.model.impl.MasterApprovalDetailModelImpl;
import com.astra.dewa.service.persistence.MasterApprovalDetailPersistence;
import com.astra.dewa.service.persistence.MasterApprovalDetailUtil;

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
 * The persistence implementation for the master approval detail service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MasterApprovalDetailPersistenceImpl
	extends BasePersistenceImpl<MasterApprovalDetail>
	implements MasterApprovalDetailPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MasterApprovalDetailUtil</code> to access the master approval detail persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MasterApprovalDetailImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public MasterApprovalDetailPersistenceImpl() {
		setModelClass(MasterApprovalDetail.class);

		setModelImplClass(MasterApprovalDetailImpl.class);
		setModelPKClass(int.class);

		setTable(MasterApprovalDetailTable.INSTANCE);
	}

	/**
	 * Caches the master approval detail in the entity cache if it is enabled.
	 *
	 * @param masterApprovalDetail the master approval detail
	 */
	@Override
	public void cacheResult(MasterApprovalDetail masterApprovalDetail) {
		entityCache.putResult(
			MasterApprovalDetailImpl.class,
			masterApprovalDetail.getPrimaryKey(), masterApprovalDetail);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the master approval details in the entity cache if it is enabled.
	 *
	 * @param masterApprovalDetails the master approval details
	 */
	@Override
	public void cacheResult(List<MasterApprovalDetail> masterApprovalDetails) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (masterApprovalDetails.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (MasterApprovalDetail masterApprovalDetail :
				masterApprovalDetails) {

			if (entityCache.getResult(
					MasterApprovalDetailImpl.class,
					masterApprovalDetail.getPrimaryKey()) == null) {

				cacheResult(masterApprovalDetail);
			}
		}
	}

	/**
	 * Clears the cache for all master approval details.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MasterApprovalDetailImpl.class);

		finderCache.clearCache(MasterApprovalDetailImpl.class);
	}

	/**
	 * Clears the cache for the master approval detail.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MasterApprovalDetail masterApprovalDetail) {
		entityCache.removeResult(
			MasterApprovalDetailImpl.class, masterApprovalDetail);
	}

	@Override
	public void clearCache(List<MasterApprovalDetail> masterApprovalDetails) {
		for (MasterApprovalDetail masterApprovalDetail :
				masterApprovalDetails) {

			entityCache.removeResult(
				MasterApprovalDetailImpl.class, masterApprovalDetail);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MasterApprovalDetailImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				MasterApprovalDetailImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new master approval detail with the primary key. Does not add the master approval detail to the database.
	 *
	 * @param Id the primary key for the new master approval detail
	 * @return the new master approval detail
	 */
	@Override
	public MasterApprovalDetail create(int Id) {
		MasterApprovalDetail masterApprovalDetail =
			new MasterApprovalDetailImpl();

		masterApprovalDetail.setNew(true);
		masterApprovalDetail.setPrimaryKey(Id);

		return masterApprovalDetail;
	}

	/**
	 * Removes the master approval detail with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the master approval detail
	 * @return the master approval detail that was removed
	 * @throws NoSuchMasterApprovalDetailException if a master approval detail with the primary key could not be found
	 */
	@Override
	public MasterApprovalDetail remove(int Id)
		throws NoSuchMasterApprovalDetailException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the master approval detail with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the master approval detail
	 * @return the master approval detail that was removed
	 * @throws NoSuchMasterApprovalDetailException if a master approval detail with the primary key could not be found
	 */
	@Override
	public MasterApprovalDetail remove(Serializable primaryKey)
		throws NoSuchMasterApprovalDetailException {

		Session session = null;

		try {
			session = openSession();

			MasterApprovalDetail masterApprovalDetail =
				(MasterApprovalDetail)session.get(
					MasterApprovalDetailImpl.class, primaryKey);

			if (masterApprovalDetail == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMasterApprovalDetailException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(masterApprovalDetail);
		}
		catch (NoSuchMasterApprovalDetailException noSuchEntityException) {
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
	protected MasterApprovalDetail removeImpl(
		MasterApprovalDetail masterApprovalDetail) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(masterApprovalDetail)) {
				masterApprovalDetail = (MasterApprovalDetail)session.get(
					MasterApprovalDetailImpl.class,
					masterApprovalDetail.getPrimaryKeyObj());
			}

			if (masterApprovalDetail != null) {
				session.delete(masterApprovalDetail);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (masterApprovalDetail != null) {
			clearCache(masterApprovalDetail);
		}

		return masterApprovalDetail;
	}

	@Override
	public MasterApprovalDetail updateImpl(
		MasterApprovalDetail masterApprovalDetail) {

		boolean isNew = masterApprovalDetail.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(masterApprovalDetail);
			}
			else {
				masterApprovalDetail = (MasterApprovalDetail)session.merge(
					masterApprovalDetail);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MasterApprovalDetailImpl.class, masterApprovalDetail, false, true);

		if (isNew) {
			masterApprovalDetail.setNew(false);
		}

		masterApprovalDetail.resetOriginalValues();

		return masterApprovalDetail;
	}

	/**
	 * Returns the master approval detail with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the master approval detail
	 * @return the master approval detail
	 * @throws NoSuchMasterApprovalDetailException if a master approval detail with the primary key could not be found
	 */
	@Override
	public MasterApprovalDetail findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMasterApprovalDetailException {

		MasterApprovalDetail masterApprovalDetail = fetchByPrimaryKey(
			primaryKey);

		if (masterApprovalDetail == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMasterApprovalDetailException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return masterApprovalDetail;
	}

	/**
	 * Returns the master approval detail with the primary key or throws a <code>NoSuchMasterApprovalDetailException</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval detail
	 * @return the master approval detail
	 * @throws NoSuchMasterApprovalDetailException if a master approval detail with the primary key could not be found
	 */
	@Override
	public MasterApprovalDetail findByPrimaryKey(int Id)
		throws NoSuchMasterApprovalDetailException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the master approval detail with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the master approval detail
	 * @return the master approval detail, or <code>null</code> if a master approval detail with the primary key could not be found
	 */
	@Override
	public MasterApprovalDetail fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the master approval details.
	 *
	 * @return the master approval details
	 */
	@Override
	public List<MasterApprovalDetail> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the master approval details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval details
	 * @param end the upper bound of the range of master approval details (not inclusive)
	 * @return the range of master approval details
	 */
	@Override
	public List<MasterApprovalDetail> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the master approval details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval details
	 * @param end the upper bound of the range of master approval details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of master approval details
	 */
	@Override
	public List<MasterApprovalDetail> findAll(
		int start, int end,
		OrderByComparator<MasterApprovalDetail> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the master approval details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MasterApprovalDetailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of master approval details
	 * @param end the upper bound of the range of master approval details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of master approval details
	 */
	@Override
	public List<MasterApprovalDetail> findAll(
		int start, int end,
		OrderByComparator<MasterApprovalDetail> orderByComparator,
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

		List<MasterApprovalDetail> list = null;

		if (useFinderCache) {
			list = (List<MasterApprovalDetail>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MASTERAPPROVALDETAIL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MASTERAPPROVALDETAIL;

				sql = sql.concat(MasterApprovalDetailModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MasterApprovalDetail>)QueryUtil.list(
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
	 * Removes all the master approval details from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MasterApprovalDetail masterApprovalDetail : findAll()) {
			remove(masterApprovalDetail);
		}
	}

	/**
	 * Returns the number of master approval details.
	 *
	 * @return the number of master approval details
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
					_SQL_COUNT_MASTERAPPROVALDETAIL);

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
		return _SQL_SELECT_MASTERAPPROVALDETAIL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MasterApprovalDetailModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the master approval detail persistence.
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

		_setMasterApprovalDetailUtilPersistence(this);
	}

	public void destroy() {
		_setMasterApprovalDetailUtilPersistence(null);

		entityCache.removeCache(MasterApprovalDetailImpl.class.getName());
	}

	private void _setMasterApprovalDetailUtilPersistence(
		MasterApprovalDetailPersistence masterApprovalDetailPersistence) {

		try {
			Field field = MasterApprovalDetailUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, masterApprovalDetailPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MASTERAPPROVALDETAIL =
		"SELECT masterApprovalDetail FROM MasterApprovalDetail masterApprovalDetail";

	private static final String _SQL_COUNT_MASTERAPPROVALDETAIL =
		"SELECT COUNT(masterApprovalDetail) FROM MasterApprovalDetail masterApprovalDetail";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"masterApprovalDetail.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MasterApprovalDetail exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		MasterApprovalDetailPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}