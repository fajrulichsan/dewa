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

import com.astra.dewa.exception.NoSuchApplicationAssignException;
import com.astra.dewa.model.ApplicationAssign;
import com.astra.dewa.model.ApplicationAssignTable;
import com.astra.dewa.model.impl.ApplicationAssignImpl;
import com.astra.dewa.model.impl.ApplicationAssignModelImpl;
import com.astra.dewa.service.persistence.ApplicationAssignPersistence;
import com.astra.dewa.service.persistence.ApplicationAssignUtil;

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
 * The persistence implementation for the application assign service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApplicationAssignPersistenceImpl
	extends BasePersistenceImpl<ApplicationAssign>
	implements ApplicationAssignPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ApplicationAssignUtil</code> to access the application assign persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ApplicationAssignImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ApplicationAssignPersistenceImpl() {
		setModelClass(ApplicationAssign.class);

		setModelImplClass(ApplicationAssignImpl.class);
		setModelPKClass(int.class);

		setTable(ApplicationAssignTable.INSTANCE);
	}

	/**
	 * Caches the application assign in the entity cache if it is enabled.
	 *
	 * @param applicationAssign the application assign
	 */
	@Override
	public void cacheResult(ApplicationAssign applicationAssign) {
		dummyEntityCache.putResult(
			ApplicationAssignImpl.class, applicationAssign.getPrimaryKey(),
			applicationAssign);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the application assigns in the entity cache if it is enabled.
	 *
	 * @param applicationAssigns the application assigns
	 */
	@Override
	public void cacheResult(List<ApplicationAssign> applicationAssigns) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (applicationAssigns.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ApplicationAssign applicationAssign : applicationAssigns) {
			if (dummyEntityCache.getResult(
					ApplicationAssignImpl.class,
					applicationAssign.getPrimaryKey()) == null) {

				cacheResult(applicationAssign);
			}
		}
	}

	/**
	 * Clears the cache for all application assigns.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(ApplicationAssignImpl.class);

		dummyFinderCache.clearCache(ApplicationAssignImpl.class);
	}

	/**
	 * Clears the cache for the application assign.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ApplicationAssign applicationAssign) {
		dummyEntityCache.removeResult(
			ApplicationAssignImpl.class, applicationAssign);
	}

	@Override
	public void clearCache(List<ApplicationAssign> applicationAssigns) {
		for (ApplicationAssign applicationAssign : applicationAssigns) {
			dummyEntityCache.removeResult(
				ApplicationAssignImpl.class, applicationAssign);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(ApplicationAssignImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(
				ApplicationAssignImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new application assign with the primary key. Does not add the application assign to the database.
	 *
	 * @param Id the primary key for the new application assign
	 * @return the new application assign
	 */
	@Override
	public ApplicationAssign create(int Id) {
		ApplicationAssign applicationAssign = new ApplicationAssignImpl();

		applicationAssign.setNew(true);
		applicationAssign.setPrimaryKey(Id);

		return applicationAssign;
	}

	/**
	 * Removes the application assign with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application assign
	 * @return the application assign that was removed
	 * @throws NoSuchApplicationAssignException if a application assign with the primary key could not be found
	 */
	@Override
	public ApplicationAssign remove(int Id)
		throws NoSuchApplicationAssignException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the application assign with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the application assign
	 * @return the application assign that was removed
	 * @throws NoSuchApplicationAssignException if a application assign with the primary key could not be found
	 */
	@Override
	public ApplicationAssign remove(Serializable primaryKey)
		throws NoSuchApplicationAssignException {

		Session session = null;

		try {
			session = openSession();

			ApplicationAssign applicationAssign =
				(ApplicationAssign)session.get(
					ApplicationAssignImpl.class, primaryKey);

			if (applicationAssign == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApplicationAssignException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(applicationAssign);
		}
		catch (NoSuchApplicationAssignException noSuchEntityException) {
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
	protected ApplicationAssign removeImpl(
		ApplicationAssign applicationAssign) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(applicationAssign)) {
				applicationAssign = (ApplicationAssign)session.get(
					ApplicationAssignImpl.class,
					applicationAssign.getPrimaryKeyObj());
			}

			if (applicationAssign != null) {
				session.delete(applicationAssign);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (applicationAssign != null) {
			clearCache(applicationAssign);
		}

		return applicationAssign;
	}

	@Override
	public ApplicationAssign updateImpl(ApplicationAssign applicationAssign) {
		boolean isNew = applicationAssign.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(applicationAssign);
			}
			else {
				applicationAssign = (ApplicationAssign)session.merge(
					applicationAssign);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			ApplicationAssignImpl.class, applicationAssign, false, true);

		if (isNew) {
			applicationAssign.setNew(false);
		}

		applicationAssign.resetOriginalValues();

		return applicationAssign;
	}

	/**
	 * Returns the application assign with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the application assign
	 * @return the application assign
	 * @throws NoSuchApplicationAssignException if a application assign with the primary key could not be found
	 */
	@Override
	public ApplicationAssign findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApplicationAssignException {

		ApplicationAssign applicationAssign = fetchByPrimaryKey(primaryKey);

		if (applicationAssign == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApplicationAssignException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return applicationAssign;
	}

	/**
	 * Returns the application assign with the primary key or throws a <code>NoSuchApplicationAssignException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign
	 * @return the application assign
	 * @throws NoSuchApplicationAssignException if a application assign with the primary key could not be found
	 */
	@Override
	public ApplicationAssign findByPrimaryKey(int Id)
		throws NoSuchApplicationAssignException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the application assign with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign
	 * @return the application assign, or <code>null</code> if a application assign with the primary key could not be found
	 */
	@Override
	public ApplicationAssign fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the application assigns.
	 *
	 * @return the application assigns
	 */
	@Override
	public List<ApplicationAssign> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the application assigns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assigns
	 * @param end the upper bound of the range of application assigns (not inclusive)
	 * @return the range of application assigns
	 */
	@Override
	public List<ApplicationAssign> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the application assigns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assigns
	 * @param end the upper bound of the range of application assigns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application assigns
	 */
	@Override
	public List<ApplicationAssign> findAll(
		int start, int end,
		OrderByComparator<ApplicationAssign> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the application assigns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assigns
	 * @param end the upper bound of the range of application assigns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of application assigns
	 */
	@Override
	public List<ApplicationAssign> findAll(
		int start, int end,
		OrderByComparator<ApplicationAssign> orderByComparator,
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

		List<ApplicationAssign> list = null;

		if (useFinderCache) {
			list = (List<ApplicationAssign>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_APPLICATIONASSIGN);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_APPLICATIONASSIGN;

				sql = sql.concat(ApplicationAssignModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ApplicationAssign>)QueryUtil.list(
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
	 * Removes all the application assigns from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ApplicationAssign applicationAssign : findAll()) {
			remove(applicationAssign);
		}
	}

	/**
	 * Returns the number of application assigns.
	 *
	 * @return the number of application assigns
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_APPLICATIONASSIGN);

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
		return _SQL_SELECT_APPLICATIONASSIGN;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ApplicationAssignModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the application assign persistence.
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

		_setApplicationAssignUtilPersistence(this);
	}

	public void destroy() {
		_setApplicationAssignUtilPersistence(null);

		dummyEntityCache.removeCache(ApplicationAssignImpl.class.getName());
	}

	private void _setApplicationAssignUtilPersistence(
		ApplicationAssignPersistence applicationAssignPersistence) {

		try {
			Field field = ApplicationAssignUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, applicationAssignPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private static final String _SQL_SELECT_APPLICATIONASSIGN =
		"SELECT applicationAssign FROM ApplicationAssign applicationAssign";

	private static final String _SQL_COUNT_APPLICATIONASSIGN =
		"SELECT COUNT(applicationAssign) FROM ApplicationAssign applicationAssign";

	private static final String _ORDER_BY_ENTITY_ALIAS = "applicationAssign.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ApplicationAssign exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ApplicationAssignPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}