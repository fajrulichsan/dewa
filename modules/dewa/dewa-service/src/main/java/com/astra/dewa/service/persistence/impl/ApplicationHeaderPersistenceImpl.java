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

import com.astra.dewa.exception.NoSuchApplicationHeaderException;
import com.astra.dewa.model.ApplicationHeader;
import com.astra.dewa.model.ApplicationHeaderTable;
import com.astra.dewa.model.impl.ApplicationHeaderImpl;
import com.astra.dewa.model.impl.ApplicationHeaderModelImpl;
import com.astra.dewa.service.persistence.ApplicationHeaderPersistence;
import com.astra.dewa.service.persistence.ApplicationHeaderUtil;
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
 * The persistence implementation for the application header service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ApplicationHeaderPersistence.class)
public class ApplicationHeaderPersistenceImpl
	extends BasePersistenceImpl<ApplicationHeader>
	implements ApplicationHeaderPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ApplicationHeaderUtil</code> to access the application header persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ApplicationHeaderImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ApplicationHeaderPersistenceImpl() {
		setModelClass(ApplicationHeader.class);

		setModelImplClass(ApplicationHeaderImpl.class);
		setModelPKClass(int.class);

		setTable(ApplicationHeaderTable.INSTANCE);
	}

	/**
	 * Caches the application header in the entity cache if it is enabled.
	 *
	 * @param applicationHeader the application header
	 */
	@Override
	public void cacheResult(ApplicationHeader applicationHeader) {
		dummyEntityCache.putResult(
			ApplicationHeaderImpl.class, applicationHeader.getPrimaryKey(),
			applicationHeader);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the application headers in the entity cache if it is enabled.
	 *
	 * @param applicationHeaders the application headers
	 */
	@Override
	public void cacheResult(List<ApplicationHeader> applicationHeaders) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (applicationHeaders.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ApplicationHeader applicationHeader : applicationHeaders) {
			if (dummyEntityCache.getResult(
					ApplicationHeaderImpl.class,
					applicationHeader.getPrimaryKey()) == null) {

				cacheResult(applicationHeader);
			}
		}
	}

	/**
	 * Clears the cache for all application headers.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(ApplicationHeaderImpl.class);

		dummyFinderCache.clearCache(ApplicationHeaderImpl.class);
	}

	/**
	 * Clears the cache for the application header.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ApplicationHeader applicationHeader) {
		dummyEntityCache.removeResult(
			ApplicationHeaderImpl.class, applicationHeader);
	}

	@Override
	public void clearCache(List<ApplicationHeader> applicationHeaders) {
		for (ApplicationHeader applicationHeader : applicationHeaders) {
			dummyEntityCache.removeResult(
				ApplicationHeaderImpl.class, applicationHeader);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(ApplicationHeaderImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(
				ApplicationHeaderImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new application header with the primary key. Does not add the application header to the database.
	 *
	 * @param Id the primary key for the new application header
	 * @return the new application header
	 */
	@Override
	public ApplicationHeader create(int Id) {
		ApplicationHeader applicationHeader = new ApplicationHeaderImpl();

		applicationHeader.setNew(true);
		applicationHeader.setPrimaryKey(Id);

		return applicationHeader;
	}

	/**
	 * Removes the application header with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application header
	 * @return the application header that was removed
	 * @throws NoSuchApplicationHeaderException if a application header with the primary key could not be found
	 */
	@Override
	public ApplicationHeader remove(int Id)
		throws NoSuchApplicationHeaderException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the application header with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the application header
	 * @return the application header that was removed
	 * @throws NoSuchApplicationHeaderException if a application header with the primary key could not be found
	 */
	@Override
	public ApplicationHeader remove(Serializable primaryKey)
		throws NoSuchApplicationHeaderException {

		Session session = null;

		try {
			session = openSession();

			ApplicationHeader applicationHeader =
				(ApplicationHeader)session.get(
					ApplicationHeaderImpl.class, primaryKey);

			if (applicationHeader == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApplicationHeaderException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(applicationHeader);
		}
		catch (NoSuchApplicationHeaderException noSuchEntityException) {
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
	protected ApplicationHeader removeImpl(
		ApplicationHeader applicationHeader) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(applicationHeader)) {
				applicationHeader = (ApplicationHeader)session.get(
					ApplicationHeaderImpl.class,
					applicationHeader.getPrimaryKeyObj());
			}

			if (applicationHeader != null) {
				session.delete(applicationHeader);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (applicationHeader != null) {
			clearCache(applicationHeader);
		}

		return applicationHeader;
	}

	@Override
	public ApplicationHeader updateImpl(ApplicationHeader applicationHeader) {
		boolean isNew = applicationHeader.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(applicationHeader);
			}
			else {
				applicationHeader = (ApplicationHeader)session.merge(
					applicationHeader);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			ApplicationHeaderImpl.class, applicationHeader, false, true);

		if (isNew) {
			applicationHeader.setNew(false);
		}

		applicationHeader.resetOriginalValues();

		return applicationHeader;
	}

	/**
	 * Returns the application header with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the application header
	 * @return the application header
	 * @throws NoSuchApplicationHeaderException if a application header with the primary key could not be found
	 */
	@Override
	public ApplicationHeader findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApplicationHeaderException {

		ApplicationHeader applicationHeader = fetchByPrimaryKey(primaryKey);

		if (applicationHeader == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApplicationHeaderException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return applicationHeader;
	}

	/**
	 * Returns the application header with the primary key or throws a <code>NoSuchApplicationHeaderException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application header
	 * @return the application header
	 * @throws NoSuchApplicationHeaderException if a application header with the primary key could not be found
	 */
	@Override
	public ApplicationHeader findByPrimaryKey(int Id)
		throws NoSuchApplicationHeaderException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the application header with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application header
	 * @return the application header, or <code>null</code> if a application header with the primary key could not be found
	 */
	@Override
	public ApplicationHeader fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the application headers.
	 *
	 * @return the application headers
	 */
	@Override
	public List<ApplicationHeader> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the application headers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application headers
	 * @param end the upper bound of the range of application headers (not inclusive)
	 * @return the range of application headers
	 */
	@Override
	public List<ApplicationHeader> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the application headers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application headers
	 * @param end the upper bound of the range of application headers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application headers
	 */
	@Override
	public List<ApplicationHeader> findAll(
		int start, int end,
		OrderByComparator<ApplicationHeader> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the application headers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationHeaderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application headers
	 * @param end the upper bound of the range of application headers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of application headers
	 */
	@Override
	public List<ApplicationHeader> findAll(
		int start, int end,
		OrderByComparator<ApplicationHeader> orderByComparator,
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

		List<ApplicationHeader> list = null;

		if (useFinderCache) {
			list = (List<ApplicationHeader>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_APPLICATIONHEADER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_APPLICATIONHEADER;

				sql = sql.concat(ApplicationHeaderModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ApplicationHeader>)QueryUtil.list(
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
	 * Removes all the application headers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ApplicationHeader applicationHeader : findAll()) {
			remove(applicationHeader);
		}
	}

	/**
	 * Returns the number of application headers.
	 *
	 * @return the number of application headers
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_APPLICATIONHEADER);

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
		return _SQL_SELECT_APPLICATIONHEADER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ApplicationHeaderModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the application header persistence.
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

		_setApplicationHeaderUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setApplicationHeaderUtilPersistence(null);

		dummyEntityCache.removeCache(ApplicationHeaderImpl.class.getName());
	}

	private void _setApplicationHeaderUtilPersistence(
		ApplicationHeaderPersistence applicationHeaderPersistence) {

		try {
			Field field = ApplicationHeaderUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, applicationHeaderPersistence);
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

	private static final String _SQL_SELECT_APPLICATIONHEADER =
		"SELECT applicationHeader FROM ApplicationHeader applicationHeader";

	private static final String _SQL_COUNT_APPLICATIONHEADER =
		"SELECT COUNT(applicationHeader) FROM ApplicationHeader applicationHeader";

	private static final String _ORDER_BY_ENTITY_ALIAS = "applicationHeader.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ApplicationHeader exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ApplicationHeaderPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}