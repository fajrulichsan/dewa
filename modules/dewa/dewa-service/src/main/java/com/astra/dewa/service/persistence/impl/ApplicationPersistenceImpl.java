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

import com.astra.dewa.exception.NoSuchApplicationException;
import com.astra.dewa.model.Application;
import com.astra.dewa.model.ApplicationTable;
import com.astra.dewa.model.impl.ApplicationImpl;
import com.astra.dewa.model.impl.ApplicationModelImpl;
import com.astra.dewa.service.persistence.ApplicationPersistence;
import com.astra.dewa.service.persistence.ApplicationUtil;
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
 * The persistence implementation for the application service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ApplicationPersistence.class)
public class ApplicationPersistenceImpl
	extends BasePersistenceImpl<Application> implements ApplicationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ApplicationUtil</code> to access the application persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ApplicationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ApplicationPersistenceImpl() {
		setModelClass(Application.class);

		setModelImplClass(ApplicationImpl.class);
		setModelPKClass(int.class);

		setTable(ApplicationTable.INSTANCE);
	}

	/**
	 * Caches the application in the entity cache if it is enabled.
	 *
	 * @param application the application
	 */
	@Override
	public void cacheResult(Application application) {
		entityCache.putResult(
			ApplicationImpl.class, application.getPrimaryKey(), application);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the applications in the entity cache if it is enabled.
	 *
	 * @param applications the applications
	 */
	@Override
	public void cacheResult(List<Application> applications) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (applications.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Application application : applications) {
			if (entityCache.getResult(
					ApplicationImpl.class, application.getPrimaryKey()) ==
						null) {

				cacheResult(application);
			}
		}
	}

	/**
	 * Clears the cache for all applications.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ApplicationImpl.class);

		finderCache.clearCache(ApplicationImpl.class);
	}

	/**
	 * Clears the cache for the application.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Application application) {
		entityCache.removeResult(ApplicationImpl.class, application);
	}

	@Override
	public void clearCache(List<Application> applications) {
		for (Application application : applications) {
			entityCache.removeResult(ApplicationImpl.class, application);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ApplicationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ApplicationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new application with the primary key. Does not add the application to the database.
	 *
	 * @param Id the primary key for the new application
	 * @return the new application
	 */
	@Override
	public Application create(int Id) {
		Application application = new ApplicationImpl();

		application.setNew(true);
		application.setPrimaryKey(Id);

		return application;
	}

	/**
	 * Removes the application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application
	 * @return the application that was removed
	 * @throws NoSuchApplicationException if a application with the primary key could not be found
	 */
	@Override
	public Application remove(int Id) throws NoSuchApplicationException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the application
	 * @return the application that was removed
	 * @throws NoSuchApplicationException if a application with the primary key could not be found
	 */
	@Override
	public Application remove(Serializable primaryKey)
		throws NoSuchApplicationException {

		Session session = null;

		try {
			session = openSession();

			Application application = (Application)session.get(
				ApplicationImpl.class, primaryKey);

			if (application == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApplicationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(application);
		}
		catch (NoSuchApplicationException noSuchEntityException) {
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
	protected Application removeImpl(Application application) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(application)) {
				application = (Application)session.get(
					ApplicationImpl.class, application.getPrimaryKeyObj());
			}

			if (application != null) {
				session.delete(application);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (application != null) {
			clearCache(application);
		}

		return application;
	}

	@Override
	public Application updateImpl(Application application) {
		boolean isNew = application.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(application);
			}
			else {
				application = (Application)session.merge(application);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ApplicationImpl.class, application, false, true);

		if (isNew) {
			application.setNew(false);
		}

		application.resetOriginalValues();

		return application;
	}

	/**
	 * Returns the application with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the application
	 * @return the application
	 * @throws NoSuchApplicationException if a application with the primary key could not be found
	 */
	@Override
	public Application findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApplicationException {

		Application application = fetchByPrimaryKey(primaryKey);

		if (application == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApplicationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return application;
	}

	/**
	 * Returns the application with the primary key or throws a <code>NoSuchApplicationException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application
	 * @return the application
	 * @throws NoSuchApplicationException if a application with the primary key could not be found
	 */
	@Override
	public Application findByPrimaryKey(int Id)
		throws NoSuchApplicationException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the application with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application
	 * @return the application, or <code>null</code> if a application with the primary key could not be found
	 */
	@Override
	public Application fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the applications.
	 *
	 * @return the applications
	 */
	@Override
	public List<Application> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @return the range of applications
	 */
	@Override
	public List<Application> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of applications
	 */
	@Override
	public List<Application> findAll(
		int start, int end, OrderByComparator<Application> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of applications
	 */
	@Override
	public List<Application> findAll(
		int start, int end, OrderByComparator<Application> orderByComparator,
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

		List<Application> list = null;

		if (useFinderCache) {
			list = (List<Application>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_APPLICATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_APPLICATION;

				sql = sql.concat(ApplicationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Application>)QueryUtil.list(
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
	 * Removes all the applications from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Application application : findAll()) {
			remove(application);
		}
	}

	/**
	 * Returns the number of applications.
	 *
	 * @return the number of applications
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_APPLICATION);

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
		return _SQL_SELECT_APPLICATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ApplicationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the application persistence.
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

		_setApplicationUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setApplicationUtilPersistence(null);

		entityCache.removeCache(ApplicationImpl.class.getName());
	}

	private void _setApplicationUtilPersistence(
		ApplicationPersistence applicationPersistence) {

		try {
			Field field = ApplicationUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, applicationPersistence);
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

	private static final String _SQL_SELECT_APPLICATION =
		"SELECT application FROM Application application";

	private static final String _SQL_COUNT_APPLICATION =
		"SELECT COUNT(application) FROM Application application";

	private static final String _ORDER_BY_ENTITY_ALIAS = "application.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Application exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ApplicationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}