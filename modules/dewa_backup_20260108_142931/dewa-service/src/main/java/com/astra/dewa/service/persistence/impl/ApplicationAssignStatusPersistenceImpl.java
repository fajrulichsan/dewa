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

import com.astra.dewa.exception.NoSuchApplicationAssignStatusException;
import com.astra.dewa.model.ApplicationAssignStatus;
import com.astra.dewa.model.ApplicationAssignStatusTable;
import com.astra.dewa.model.impl.ApplicationAssignStatusImpl;
import com.astra.dewa.model.impl.ApplicationAssignStatusModelImpl;
import com.astra.dewa.service.persistence.ApplicationAssignStatusPersistence;
import com.astra.dewa.service.persistence.ApplicationAssignStatusUtil;

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
 * The persistence implementation for the application assign status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApplicationAssignStatusPersistenceImpl
	extends BasePersistenceImpl<ApplicationAssignStatus>
	implements ApplicationAssignStatusPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ApplicationAssignStatusUtil</code> to access the application assign status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ApplicationAssignStatusImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ApplicationAssignStatusPersistenceImpl() {
		setModelClass(ApplicationAssignStatus.class);

		setModelImplClass(ApplicationAssignStatusImpl.class);
		setModelPKClass(int.class);

		setTable(ApplicationAssignStatusTable.INSTANCE);
	}

	/**
	 * Caches the application assign status in the entity cache if it is enabled.
	 *
	 * @param applicationAssignStatus the application assign status
	 */
	@Override
	public void cacheResult(ApplicationAssignStatus applicationAssignStatus) {
		entityCache.putResult(
			ApplicationAssignStatusImpl.class,
			applicationAssignStatus.getPrimaryKey(), applicationAssignStatus);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the application assign statuses in the entity cache if it is enabled.
	 *
	 * @param applicationAssignStatuses the application assign statuses
	 */
	@Override
	public void cacheResult(
		List<ApplicationAssignStatus> applicationAssignStatuses) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (applicationAssignStatuses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ApplicationAssignStatus applicationAssignStatus :
				applicationAssignStatuses) {

			if (entityCache.getResult(
					ApplicationAssignStatusImpl.class,
					applicationAssignStatus.getPrimaryKey()) == null) {

				cacheResult(applicationAssignStatus);
			}
		}
	}

	/**
	 * Clears the cache for all application assign statuses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ApplicationAssignStatusImpl.class);

		finderCache.clearCache(ApplicationAssignStatusImpl.class);
	}

	/**
	 * Clears the cache for the application assign status.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ApplicationAssignStatus applicationAssignStatus) {
		entityCache.removeResult(
			ApplicationAssignStatusImpl.class, applicationAssignStatus);
	}

	@Override
	public void clearCache(
		List<ApplicationAssignStatus> applicationAssignStatuses) {

		for (ApplicationAssignStatus applicationAssignStatus :
				applicationAssignStatuses) {

			entityCache.removeResult(
				ApplicationAssignStatusImpl.class, applicationAssignStatus);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ApplicationAssignStatusImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				ApplicationAssignStatusImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new application assign status with the primary key. Does not add the application assign status to the database.
	 *
	 * @param Id the primary key for the new application assign status
	 * @return the new application assign status
	 */
	@Override
	public ApplicationAssignStatus create(int Id) {
		ApplicationAssignStatus applicationAssignStatus =
			new ApplicationAssignStatusImpl();

		applicationAssignStatus.setNew(true);
		applicationAssignStatus.setPrimaryKey(Id);

		return applicationAssignStatus;
	}

	/**
	 * Removes the application assign status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application assign status
	 * @return the application assign status that was removed
	 * @throws NoSuchApplicationAssignStatusException if a application assign status with the primary key could not be found
	 */
	@Override
	public ApplicationAssignStatus remove(int Id)
		throws NoSuchApplicationAssignStatusException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the application assign status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the application assign status
	 * @return the application assign status that was removed
	 * @throws NoSuchApplicationAssignStatusException if a application assign status with the primary key could not be found
	 */
	@Override
	public ApplicationAssignStatus remove(Serializable primaryKey)
		throws NoSuchApplicationAssignStatusException {

		Session session = null;

		try {
			session = openSession();

			ApplicationAssignStatus applicationAssignStatus =
				(ApplicationAssignStatus)session.get(
					ApplicationAssignStatusImpl.class, primaryKey);

			if (applicationAssignStatus == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApplicationAssignStatusException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(applicationAssignStatus);
		}
		catch (NoSuchApplicationAssignStatusException noSuchEntityException) {
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
	protected ApplicationAssignStatus removeImpl(
		ApplicationAssignStatus applicationAssignStatus) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(applicationAssignStatus)) {
				applicationAssignStatus = (ApplicationAssignStatus)session.get(
					ApplicationAssignStatusImpl.class,
					applicationAssignStatus.getPrimaryKeyObj());
			}

			if (applicationAssignStatus != null) {
				session.delete(applicationAssignStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (applicationAssignStatus != null) {
			clearCache(applicationAssignStatus);
		}

		return applicationAssignStatus;
	}

	@Override
	public ApplicationAssignStatus updateImpl(
		ApplicationAssignStatus applicationAssignStatus) {

		boolean isNew = applicationAssignStatus.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(applicationAssignStatus);
			}
			else {
				applicationAssignStatus =
					(ApplicationAssignStatus)session.merge(
						applicationAssignStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ApplicationAssignStatusImpl.class, applicationAssignStatus, false,
			true);

		if (isNew) {
			applicationAssignStatus.setNew(false);
		}

		applicationAssignStatus.resetOriginalValues();

		return applicationAssignStatus;
	}

	/**
	 * Returns the application assign status with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the application assign status
	 * @return the application assign status
	 * @throws NoSuchApplicationAssignStatusException if a application assign status with the primary key could not be found
	 */
	@Override
	public ApplicationAssignStatus findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApplicationAssignStatusException {

		ApplicationAssignStatus applicationAssignStatus = fetchByPrimaryKey(
			primaryKey);

		if (applicationAssignStatus == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApplicationAssignStatusException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return applicationAssignStatus;
	}

	/**
	 * Returns the application assign status with the primary key or throws a <code>NoSuchApplicationAssignStatusException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign status
	 * @return the application assign status
	 * @throws NoSuchApplicationAssignStatusException if a application assign status with the primary key could not be found
	 */
	@Override
	public ApplicationAssignStatus findByPrimaryKey(int Id)
		throws NoSuchApplicationAssignStatusException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the application assign status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign status
	 * @return the application assign status, or <code>null</code> if a application assign status with the primary key could not be found
	 */
	@Override
	public ApplicationAssignStatus fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the application assign statuses.
	 *
	 * @return the application assign statuses
	 */
	@Override
	public List<ApplicationAssignStatus> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the application assign statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign statuses
	 * @param end the upper bound of the range of application assign statuses (not inclusive)
	 * @return the range of application assign statuses
	 */
	@Override
	public List<ApplicationAssignStatus> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the application assign statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign statuses
	 * @param end the upper bound of the range of application assign statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application assign statuses
	 */
	@Override
	public List<ApplicationAssignStatus> findAll(
		int start, int end,
		OrderByComparator<ApplicationAssignStatus> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the application assign statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign statuses
	 * @param end the upper bound of the range of application assign statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of application assign statuses
	 */
	@Override
	public List<ApplicationAssignStatus> findAll(
		int start, int end,
		OrderByComparator<ApplicationAssignStatus> orderByComparator,
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

		List<ApplicationAssignStatus> list = null;

		if (useFinderCache) {
			list = (List<ApplicationAssignStatus>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_APPLICATIONASSIGNSTATUS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_APPLICATIONASSIGNSTATUS;

				sql = sql.concat(
					ApplicationAssignStatusModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ApplicationAssignStatus>)QueryUtil.list(
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
	 * Removes all the application assign statuses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ApplicationAssignStatus applicationAssignStatus : findAll()) {
			remove(applicationAssignStatus);
		}
	}

	/**
	 * Returns the number of application assign statuses.
	 *
	 * @return the number of application assign statuses
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
					_SQL_COUNT_APPLICATIONASSIGNSTATUS);

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
		return _SQL_SELECT_APPLICATIONASSIGNSTATUS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ApplicationAssignStatusModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the application assign status persistence.
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

		_setApplicationAssignStatusUtilPersistence(this);
	}

	public void destroy() {
		_setApplicationAssignStatusUtilPersistence(null);

		entityCache.removeCache(ApplicationAssignStatusImpl.class.getName());
	}

	private void _setApplicationAssignStatusUtilPersistence(
		ApplicationAssignStatusPersistence applicationAssignStatusPersistence) {

		try {
			Field field = ApplicationAssignStatusUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, applicationAssignStatusPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_APPLICATIONASSIGNSTATUS =
		"SELECT applicationAssignStatus FROM ApplicationAssignStatus applicationAssignStatus";

	private static final String _SQL_COUNT_APPLICATIONASSIGNSTATUS =
		"SELECT COUNT(applicationAssignStatus) FROM ApplicationAssignStatus applicationAssignStatus";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"applicationAssignStatus.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ApplicationAssignStatus exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ApplicationAssignStatusPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}