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

import com.astra.dewa.exception.NoSuchCommonException;
import com.astra.dewa.model.Common;
import com.astra.dewa.model.CommonTable;
import com.astra.dewa.model.impl.CommonImpl;
import com.astra.dewa.model.impl.CommonModelImpl;
import com.astra.dewa.service.persistence.CommonPersistence;
import com.astra.dewa.service.persistence.CommonUtil;

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
 * The persistence implementation for the common service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CommonPersistenceImpl
	extends BasePersistenceImpl<Common> implements CommonPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommonUtil</code> to access the common persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommonImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public CommonPersistenceImpl() {
		setModelClass(Common.class);

		setModelImplClass(CommonImpl.class);
		setModelPKClass(String.class);

		setTable(CommonTable.INSTANCE);
	}

	/**
	 * Caches the common in the entity cache if it is enabled.
	 *
	 * @param common the common
	 */
	@Override
	public void cacheResult(Common common) {
		entityCache.putResult(CommonImpl.class, common.getPrimaryKey(), common);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the commons in the entity cache if it is enabled.
	 *
	 * @param commons the commons
	 */
	@Override
	public void cacheResult(List<Common> commons) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (commons.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Common common : commons) {
			if (entityCache.getResult(
					CommonImpl.class, common.getPrimaryKey()) == null) {

				cacheResult(common);
			}
		}
	}

	/**
	 * Clears the cache for all commons.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommonImpl.class);

		finderCache.clearCache(CommonImpl.class);
	}

	/**
	 * Clears the cache for the common.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Common common) {
		entityCache.removeResult(CommonImpl.class, common);
	}

	@Override
	public void clearCache(List<Common> commons) {
		for (Common common : commons) {
			entityCache.removeResult(CommonImpl.class, common);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CommonImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CommonImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new common with the primary key. Does not add the common to the database.
	 *
	 * @param CommonKey the primary key for the new common
	 * @return the new common
	 */
	@Override
	public Common create(String CommonKey) {
		Common common = new CommonImpl();

		common.setNew(true);
		common.setPrimaryKey(CommonKey);

		return common;
	}

	/**
	 * Removes the common with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CommonKey the primary key of the common
	 * @return the common that was removed
	 * @throws NoSuchCommonException if a common with the primary key could not be found
	 */
	@Override
	public Common remove(String CommonKey) throws NoSuchCommonException {
		return remove((Serializable)CommonKey);
	}

	/**
	 * Removes the common with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the common
	 * @return the common that was removed
	 * @throws NoSuchCommonException if a common with the primary key could not be found
	 */
	@Override
	public Common remove(Serializable primaryKey) throws NoSuchCommonException {
		Session session = null;

		try {
			session = openSession();

			Common common = (Common)session.get(CommonImpl.class, primaryKey);

			if (common == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCommonException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(common);
		}
		catch (NoSuchCommonException noSuchEntityException) {
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
	protected Common removeImpl(Common common) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(common)) {
				common = (Common)session.get(
					CommonImpl.class, common.getPrimaryKeyObj());
			}

			if (common != null) {
				session.delete(common);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (common != null) {
			clearCache(common);
		}

		return common;
	}

	@Override
	public Common updateImpl(Common common) {
		boolean isNew = common.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(common);
			}
			else {
				common = (Common)session.merge(common);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(CommonImpl.class, common, false, true);

		if (isNew) {
			common.setNew(false);
		}

		common.resetOriginalValues();

		return common;
	}

	/**
	 * Returns the common with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the common
	 * @return the common
	 * @throws NoSuchCommonException if a common with the primary key could not be found
	 */
	@Override
	public Common findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCommonException {

		Common common = fetchByPrimaryKey(primaryKey);

		if (common == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCommonException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return common;
	}

	/**
	 * Returns the common with the primary key or throws a <code>NoSuchCommonException</code> if it could not be found.
	 *
	 * @param CommonKey the primary key of the common
	 * @return the common
	 * @throws NoSuchCommonException if a common with the primary key could not be found
	 */
	@Override
	public Common findByPrimaryKey(String CommonKey)
		throws NoSuchCommonException {

		return findByPrimaryKey((Serializable)CommonKey);
	}

	/**
	 * Returns the common with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CommonKey the primary key of the common
	 * @return the common, or <code>null</code> if a common with the primary key could not be found
	 */
	@Override
	public Common fetchByPrimaryKey(String CommonKey) {
		return fetchByPrimaryKey((Serializable)CommonKey);
	}

	/**
	 * Returns all the commons.
	 *
	 * @return the commons
	 */
	@Override
	public List<Common> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commons
	 * @param end the upper bound of the range of commons (not inclusive)
	 * @return the range of commons
	 */
	@Override
	public List<Common> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commons
	 * @param end the upper bound of the range of commons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commons
	 */
	@Override
	public List<Common> findAll(
		int start, int end, OrderByComparator<Common> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commons
	 * @param end the upper bound of the range of commons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commons
	 */
	@Override
	public List<Common> findAll(
		int start, int end, OrderByComparator<Common> orderByComparator,
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

		List<Common> list = null;

		if (useFinderCache) {
			list = (List<Common>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMMON);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMMON;

				sql = sql.concat(CommonModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Common>)QueryUtil.list(
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
	 * Removes all the commons from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Common common : findAll()) {
			remove(common);
		}
	}

	/**
	 * Returns the number of commons.
	 *
	 * @return the number of commons
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_COMMON);

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
		return "CommonKey";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMMON;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CommonModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the common persistence.
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

		_setCommonUtilPersistence(this);
	}

	public void destroy() {
		_setCommonUtilPersistence(null);

		entityCache.removeCache(CommonImpl.class.getName());
	}

	private void _setCommonUtilPersistence(
		CommonPersistence commonPersistence) {

		try {
			Field field = CommonUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, commonPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMON =
		"SELECT common FROM Common common";

	private static final String _SQL_COUNT_COMMON =
		"SELECT COUNT(common) FROM Common common";

	private static final String _ORDER_BY_ENTITY_ALIAS = "common.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Common exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		CommonPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}