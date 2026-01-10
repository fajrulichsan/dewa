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

import com.astra.dewa.exception.NoSuchApplicationHeaderStatusException;
import com.astra.dewa.model.ApplicationHeaderStatus;
import com.astra.dewa.model.ApplicationHeaderStatusTable;
import com.astra.dewa.model.impl.ApplicationHeaderStatusImpl;
import com.astra.dewa.model.impl.ApplicationHeaderStatusModelImpl;
import com.astra.dewa.service.persistence.ApplicationHeaderStatusPersistence;
import com.astra.dewa.service.persistence.ApplicationHeaderStatusUtil;
import com.astra.dewa.service.persistence.impl.constants.DewaPersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
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

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the application header status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ApplicationHeaderStatusPersistence.class)
public class ApplicationHeaderStatusPersistenceImpl
	extends BasePersistenceImpl<ApplicationHeaderStatus>
	implements ApplicationHeaderStatusPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ApplicationHeaderStatusUtil</code> to access the application header status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ApplicationHeaderStatusImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ApplicationHeaderStatusPersistenceImpl() {
		setModelClass(ApplicationHeaderStatus.class);

		setModelImplClass(ApplicationHeaderStatusImpl.class);
		setModelPKClass(int.class);

		setTable(ApplicationHeaderStatusTable.INSTANCE);
	}

	/**
	 * Caches the application header status in the entity cache if it is enabled.
	 *
	 * @param applicationHeaderStatus the application header status
	 */
	@Override
	public void cacheResult(ApplicationHeaderStatus applicationHeaderStatus) {
		entityCache.putResult(
			ApplicationHeaderStatusImpl.class,
			applicationHeaderStatus.getPrimaryKey(), applicationHeaderStatus);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the application header statuses in the entity cache if it is enabled.
	 *
	 * @param applicationHeaderStatuses the application header statuses
	 */
	@Override
	public void cacheResult(
		List<ApplicationHeaderStatus> applicationHeaderStatuses) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (applicationHeaderStatuses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ApplicationHeaderStatus applicationHeaderStatus :
				applicationHeaderStatuses) {

			if (entityCache.getResult(
					ApplicationHeaderStatusImpl.class,
					applicationHeaderStatus.getPrimaryKey()) == null) {

				cacheResult(applicationHeaderStatus);
			}
		}
	}

	/**
	 * Clears the cache for all application header statuses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ApplicationHeaderStatusImpl.class);

		finderCache.clearCache(ApplicationHeaderStatusImpl.class);
	}

	/**
	 * Clears the cache for the application header status.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ApplicationHeaderStatus applicationHeaderStatus) {
		entityCache.removeResult(
			ApplicationHeaderStatusImpl.class, applicationHeaderStatus);
	}

	@Override
	public void clearCache(
		List<ApplicationHeaderStatus> applicationHeaderStatuses) {

		for (ApplicationHeaderStatus applicationHeaderStatus :
				applicationHeaderStatuses) {

			entityCache.removeResult(
				ApplicationHeaderStatusImpl.class, applicationHeaderStatus);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ApplicationHeaderStatusImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				ApplicationHeaderStatusImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new application header status with the primary key. Does not add the application header status to the database.
	 *
	 * @param Id the primary key for the new application header status
	 * @return the new application header status
	 */
	@Override
	public ApplicationHeaderStatus create(int Id) {
		ApplicationHeaderStatus applicationHeaderStatus =
			new ApplicationHeaderStatusImpl();

		applicationHeaderStatus.setNew(true);
		applicationHeaderStatus.setPrimaryKey(Id);

		return applicationHeaderStatus;
	}

	/**
	 * Removes the application header status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application header status
	 * @return the application header status that was removed
	 * @throws NoSuchApplicationHeaderStatusException if a application header status with the primary key could not be found
	 */
	@Override
	public ApplicationHeaderStatus remove(int Id)
		throws NoSuchApplicationHeaderStatusException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the application header status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the application header status
	 * @return the application header status that was removed
	 * @throws NoSuchApplicationHeaderStatusException if a application header status with the primary key could not be found
	 */
	@Override
	public ApplicationHeaderStatus remove(Serializable primaryKey)
		throws NoSuchApplicationHeaderStatusException {

		Session session = null;

		try {
			session = openSession();

			ApplicationHeaderStatus applicationHeaderStatus =
				(ApplicationHeaderStatus)session.get(
					ApplicationHeaderStatusImpl.class, primaryKey);

			if (applicationHeaderStatus == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApplicationHeaderStatusException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(applicationHeaderStatus);
		}
		catch (NoSuchApplicationHeaderStatusException noSuchEntityException) {
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
	protected ApplicationHeaderStatus removeImpl(
		ApplicationHeaderStatus applicationHeaderStatus) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(applicationHeaderStatus)) {
				applicationHeaderStatus = (ApplicationHeaderStatus)session.get(
					ApplicationHeaderStatusImpl.class,
					applicationHeaderStatus.getPrimaryKeyObj());
			}

			if (applicationHeaderStatus != null) {
				session.delete(applicationHeaderStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (applicationHeaderStatus != null) {
			clearCache(applicationHeaderStatus);
		}

		return applicationHeaderStatus;
	}

	@Override
	public ApplicationHeaderStatus updateImpl(
		ApplicationHeaderStatus applicationHeaderStatus) {

		boolean isNew = applicationHeaderStatus.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(applicationHeaderStatus);
			}
			else {
				applicationHeaderStatus =
					(ApplicationHeaderStatus)session.merge(
						applicationHeaderStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ApplicationHeaderStatusImpl.class, applicationHeaderStatus, false,
			true);

		if (isNew) {
			applicationHeaderStatus.setNew(false);
		}

		applicationHeaderStatus.resetOriginalValues();

		return applicationHeaderStatus;
	}

	/**
	 * Returns the application header status with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the application header status
	 * @return the application header status
	 * @throws NoSuchApplicationHeaderStatusException if a application header status with the primary key could not be found
	 */
	@Override
	public ApplicationHeaderStatus findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApplicationHeaderStatusException {

		ApplicationHeaderStatus applicationHeaderStatus = fetchByPrimaryKey(
			primaryKey);

		if (applicationHeaderStatus == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApplicationHeaderStatusException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return applicationHeaderStatus;
	}

	/**
	 * Returns the application header status with the primary key or throws a <code>NoSuchApplicationHeaderStatusException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application header status
	 * @return the application header status
	 * @throws NoSuchApplicationHeaderStatusException if a application header status with the primary key could not be found
	 */
	@Override
	public ApplicationHeaderStatus findByPrimaryKey(int Id)
		throws NoSuchApplicationHeaderStatusException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the application header status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application header status
	 * @return the application header status, or <code>null</code> if a application header status with the primary key could not be found
	 */
	@Override
	public ApplicationHeaderStatus fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the application header statuses.
	 *
	 * @return the application header statuses
	 */
	@Override
	public List<ApplicationHeaderStatus> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the application header statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application header statuses
	 * @param end the upper bound of the range of application header statuses (not inclusive)
	 * @return the range of application header statuses
	 */
	@Override
	public List<ApplicationHeaderStatus> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the application header statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application header statuses
	 * @param end the upper bound of the range of application header statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application header statuses
	 */
	@Override
	public List<ApplicationHeaderStatus> findAll(
		int start, int end,
		OrderByComparator<ApplicationHeaderStatus> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the application header statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application header statuses
	 * @param end the upper bound of the range of application header statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of application header statuses
	 */
	@Override
	public List<ApplicationHeaderStatus> findAll(
		int start, int end,
		OrderByComparator<ApplicationHeaderStatus> orderByComparator,
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

		List<ApplicationHeaderStatus> list = null;

		if (useFinderCache) {
			list = (List<ApplicationHeaderStatus>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_APPLICATIONHEADERSTATUS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_APPLICATIONHEADERSTATUS;

				sql = sql.concat(
					ApplicationHeaderStatusModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ApplicationHeaderStatus>)QueryUtil.list(
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
	 * Removes all the application header statuses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ApplicationHeaderStatus applicationHeaderStatus : findAll()) {
			remove(applicationHeaderStatus);
		}
	}

	/**
	 * Returns the number of application header statuses.
	 *
	 * @return the number of application header statuses
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
					_SQL_COUNT_APPLICATIONHEADERSTATUS);

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
		return _SQL_SELECT_APPLICATIONHEADERSTATUS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ApplicationHeaderStatusModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the application header status persistence.
	 */
	@Activate
	public void activate() {
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

		_setApplicationHeaderStatusUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setApplicationHeaderStatusUtilPersistence(null);

		entityCache.removeCache(ApplicationHeaderStatusImpl.class.getName());
	}

	private void _setApplicationHeaderStatusUtilPersistence(
		ApplicationHeaderStatusPersistence applicationHeaderStatusPersistence) {

		try {
			Field field = ApplicationHeaderStatusUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, applicationHeaderStatusPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = DewaPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = DewaPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = DewaPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_APPLICATIONHEADERSTATUS =
		"SELECT applicationHeaderStatus FROM ApplicationHeaderStatus applicationHeaderStatus";

	private static final String _SQL_COUNT_APPLICATIONHEADERSTATUS =
		"SELECT COUNT(applicationHeaderStatus) FROM ApplicationHeaderStatus applicationHeaderStatus";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"applicationHeaderStatus.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ApplicationHeaderStatus exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ApplicationHeaderStatusPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}