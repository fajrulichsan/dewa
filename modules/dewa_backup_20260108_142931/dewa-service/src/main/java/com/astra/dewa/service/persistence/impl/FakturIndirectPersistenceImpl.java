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

import com.astra.dewa.exception.NoSuchFakturIndirectException;
import com.astra.dewa.model.FakturIndirect;
import com.astra.dewa.model.FakturIndirectTable;
import com.astra.dewa.model.impl.FakturIndirectImpl;
import com.astra.dewa.model.impl.FakturIndirectModelImpl;
import com.astra.dewa.service.persistence.FakturIndirectPersistence;
import com.astra.dewa.service.persistence.FakturIndirectUtil;

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
 * The persistence implementation for the faktur indirect service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FakturIndirectPersistenceImpl
	extends BasePersistenceImpl<FakturIndirect>
	implements FakturIndirectPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FakturIndirectUtil</code> to access the faktur indirect persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FakturIndirectImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public FakturIndirectPersistenceImpl() {
		setModelClass(FakturIndirect.class);

		setModelImplClass(FakturIndirectImpl.class);
		setModelPKClass(int.class);

		setTable(FakturIndirectTable.INSTANCE);
	}

	/**
	 * Caches the faktur indirect in the entity cache if it is enabled.
	 *
	 * @param fakturIndirect the faktur indirect
	 */
	@Override
	public void cacheResult(FakturIndirect fakturIndirect) {
		entityCache.putResult(
			FakturIndirectImpl.class, fakturIndirect.getPrimaryKey(),
			fakturIndirect);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the faktur indirects in the entity cache if it is enabled.
	 *
	 * @param fakturIndirects the faktur indirects
	 */
	@Override
	public void cacheResult(List<FakturIndirect> fakturIndirects) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (fakturIndirects.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FakturIndirect fakturIndirect : fakturIndirects) {
			if (entityCache.getResult(
					FakturIndirectImpl.class, fakturIndirect.getPrimaryKey()) ==
						null) {

				cacheResult(fakturIndirect);
			}
		}
	}

	/**
	 * Clears the cache for all faktur indirects.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FakturIndirectImpl.class);

		finderCache.clearCache(FakturIndirectImpl.class);
	}

	/**
	 * Clears the cache for the faktur indirect.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FakturIndirect fakturIndirect) {
		entityCache.removeResult(FakturIndirectImpl.class, fakturIndirect);
	}

	@Override
	public void clearCache(List<FakturIndirect> fakturIndirects) {
		for (FakturIndirect fakturIndirect : fakturIndirects) {
			entityCache.removeResult(FakturIndirectImpl.class, fakturIndirect);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FakturIndirectImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FakturIndirectImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new faktur indirect with the primary key. Does not add the faktur indirect to the database.
	 *
	 * @param Id the primary key for the new faktur indirect
	 * @return the new faktur indirect
	 */
	@Override
	public FakturIndirect create(int Id) {
		FakturIndirect fakturIndirect = new FakturIndirectImpl();

		fakturIndirect.setNew(true);
		fakturIndirect.setPrimaryKey(Id);

		return fakturIndirect;
	}

	/**
	 * Removes the faktur indirect with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the faktur indirect
	 * @return the faktur indirect that was removed
	 * @throws NoSuchFakturIndirectException if a faktur indirect with the primary key could not be found
	 */
	@Override
	public FakturIndirect remove(int Id) throws NoSuchFakturIndirectException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the faktur indirect with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the faktur indirect
	 * @return the faktur indirect that was removed
	 * @throws NoSuchFakturIndirectException if a faktur indirect with the primary key could not be found
	 */
	@Override
	public FakturIndirect remove(Serializable primaryKey)
		throws NoSuchFakturIndirectException {

		Session session = null;

		try {
			session = openSession();

			FakturIndirect fakturIndirect = (FakturIndirect)session.get(
				FakturIndirectImpl.class, primaryKey);

			if (fakturIndirect == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFakturIndirectException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(fakturIndirect);
		}
		catch (NoSuchFakturIndirectException noSuchEntityException) {
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
	protected FakturIndirect removeImpl(FakturIndirect fakturIndirect) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(fakturIndirect)) {
				fakturIndirect = (FakturIndirect)session.get(
					FakturIndirectImpl.class,
					fakturIndirect.getPrimaryKeyObj());
			}

			if (fakturIndirect != null) {
				session.delete(fakturIndirect);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (fakturIndirect != null) {
			clearCache(fakturIndirect);
		}

		return fakturIndirect;
	}

	@Override
	public FakturIndirect updateImpl(FakturIndirect fakturIndirect) {
		boolean isNew = fakturIndirect.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(fakturIndirect);
			}
			else {
				fakturIndirect = (FakturIndirect)session.merge(fakturIndirect);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FakturIndirectImpl.class, fakturIndirect, false, true);

		if (isNew) {
			fakturIndirect.setNew(false);
		}

		fakturIndirect.resetOriginalValues();

		return fakturIndirect;
	}

	/**
	 * Returns the faktur indirect with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the faktur indirect
	 * @return the faktur indirect
	 * @throws NoSuchFakturIndirectException if a faktur indirect with the primary key could not be found
	 */
	@Override
	public FakturIndirect findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFakturIndirectException {

		FakturIndirect fakturIndirect = fetchByPrimaryKey(primaryKey);

		if (fakturIndirect == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFakturIndirectException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return fakturIndirect;
	}

	/**
	 * Returns the faktur indirect with the primary key or throws a <code>NoSuchFakturIndirectException</code> if it could not be found.
	 *
	 * @param Id the primary key of the faktur indirect
	 * @return the faktur indirect
	 * @throws NoSuchFakturIndirectException if a faktur indirect with the primary key could not be found
	 */
	@Override
	public FakturIndirect findByPrimaryKey(int Id)
		throws NoSuchFakturIndirectException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the faktur indirect with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the faktur indirect
	 * @return the faktur indirect, or <code>null</code> if a faktur indirect with the primary key could not be found
	 */
	@Override
	public FakturIndirect fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the faktur indirects.
	 *
	 * @return the faktur indirects
	 */
	@Override
	public List<FakturIndirect> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the faktur indirects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FakturIndirectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur indirects
	 * @param end the upper bound of the range of faktur indirects (not inclusive)
	 * @return the range of faktur indirects
	 */
	@Override
	public List<FakturIndirect> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the faktur indirects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FakturIndirectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur indirects
	 * @param end the upper bound of the range of faktur indirects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of faktur indirects
	 */
	@Override
	public List<FakturIndirect> findAll(
		int start, int end,
		OrderByComparator<FakturIndirect> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the faktur indirects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FakturIndirectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur indirects
	 * @param end the upper bound of the range of faktur indirects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of faktur indirects
	 */
	@Override
	public List<FakturIndirect> findAll(
		int start, int end, OrderByComparator<FakturIndirect> orderByComparator,
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

		List<FakturIndirect> list = null;

		if (useFinderCache) {
			list = (List<FakturIndirect>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FAKTURINDIRECT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FAKTURINDIRECT;

				sql = sql.concat(FakturIndirectModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FakturIndirect>)QueryUtil.list(
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
	 * Removes all the faktur indirects from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FakturIndirect fakturIndirect : findAll()) {
			remove(fakturIndirect);
		}
	}

	/**
	 * Returns the number of faktur indirects.
	 *
	 * @return the number of faktur indirects
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FAKTURINDIRECT);

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
		return _SQL_SELECT_FAKTURINDIRECT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FakturIndirectModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the faktur indirect persistence.
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

		_setFakturIndirectUtilPersistence(this);
	}

	public void destroy() {
		_setFakturIndirectUtilPersistence(null);

		entityCache.removeCache(FakturIndirectImpl.class.getName());
	}

	private void _setFakturIndirectUtilPersistence(
		FakturIndirectPersistence fakturIndirectPersistence) {

		try {
			Field field = FakturIndirectUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, fakturIndirectPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_FAKTURINDIRECT =
		"SELECT fakturIndirect FROM FakturIndirect fakturIndirect";

	private static final String _SQL_COUNT_FAKTURINDIRECT =
		"SELECT COUNT(fakturIndirect) FROM FakturIndirect fakturIndirect";

	private static final String _ORDER_BY_ENTITY_ALIAS = "fakturIndirect.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FakturIndirect exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		FakturIndirectPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}