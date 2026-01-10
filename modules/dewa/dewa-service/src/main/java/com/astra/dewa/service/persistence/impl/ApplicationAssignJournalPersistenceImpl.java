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

import com.astra.dewa.exception.NoSuchApplicationAssignJournalException;
import com.astra.dewa.model.ApplicationAssignJournal;
import com.astra.dewa.model.ApplicationAssignJournalTable;
import com.astra.dewa.model.impl.ApplicationAssignJournalImpl;
import com.astra.dewa.model.impl.ApplicationAssignJournalModelImpl;
import com.astra.dewa.service.persistence.ApplicationAssignJournalPersistence;
import com.astra.dewa.service.persistence.ApplicationAssignJournalUtil;
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
 * The persistence implementation for the application assign journal service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ApplicationAssignJournalPersistence.class)
public class ApplicationAssignJournalPersistenceImpl
	extends BasePersistenceImpl<ApplicationAssignJournal>
	implements ApplicationAssignJournalPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ApplicationAssignJournalUtil</code> to access the application assign journal persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ApplicationAssignJournalImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ApplicationAssignJournalPersistenceImpl() {
		setModelClass(ApplicationAssignJournal.class);

		setModelImplClass(ApplicationAssignJournalImpl.class);
		setModelPKClass(int.class);

		setTable(ApplicationAssignJournalTable.INSTANCE);
	}

	/**
	 * Caches the application assign journal in the entity cache if it is enabled.
	 *
	 * @param applicationAssignJournal the application assign journal
	 */
	@Override
	public void cacheResult(ApplicationAssignJournal applicationAssignJournal) {
		dummyEntityCache.putResult(
			ApplicationAssignJournalImpl.class,
			applicationAssignJournal.getPrimaryKey(), applicationAssignJournal);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the application assign journals in the entity cache if it is enabled.
	 *
	 * @param applicationAssignJournals the application assign journals
	 */
	@Override
	public void cacheResult(
		List<ApplicationAssignJournal> applicationAssignJournals) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (applicationAssignJournals.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ApplicationAssignJournal applicationAssignJournal :
				applicationAssignJournals) {

			if (dummyEntityCache.getResult(
					ApplicationAssignJournalImpl.class,
					applicationAssignJournal.getPrimaryKey()) == null) {

				cacheResult(applicationAssignJournal);
			}
		}
	}

	/**
	 * Clears the cache for all application assign journals.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(ApplicationAssignJournalImpl.class);

		dummyFinderCache.clearCache(ApplicationAssignJournalImpl.class);
	}

	/**
	 * Clears the cache for the application assign journal.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ApplicationAssignJournal applicationAssignJournal) {
		dummyEntityCache.removeResult(
			ApplicationAssignJournalImpl.class, applicationAssignJournal);
	}

	@Override
	public void clearCache(
		List<ApplicationAssignJournal> applicationAssignJournals) {

		for (ApplicationAssignJournal applicationAssignJournal :
				applicationAssignJournals) {

			dummyEntityCache.removeResult(
				ApplicationAssignJournalImpl.class, applicationAssignJournal);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(ApplicationAssignJournalImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(
				ApplicationAssignJournalImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new application assign journal with the primary key. Does not add the application assign journal to the database.
	 *
	 * @param Id the primary key for the new application assign journal
	 * @return the new application assign journal
	 */
	@Override
	public ApplicationAssignJournal create(int Id) {
		ApplicationAssignJournal applicationAssignJournal =
			new ApplicationAssignJournalImpl();

		applicationAssignJournal.setNew(true);
		applicationAssignJournal.setPrimaryKey(Id);

		return applicationAssignJournal;
	}

	/**
	 * Removes the application assign journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the application assign journal
	 * @return the application assign journal that was removed
	 * @throws NoSuchApplicationAssignJournalException if a application assign journal with the primary key could not be found
	 */
	@Override
	public ApplicationAssignJournal remove(int Id)
		throws NoSuchApplicationAssignJournalException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the application assign journal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the application assign journal
	 * @return the application assign journal that was removed
	 * @throws NoSuchApplicationAssignJournalException if a application assign journal with the primary key could not be found
	 */
	@Override
	public ApplicationAssignJournal remove(Serializable primaryKey)
		throws NoSuchApplicationAssignJournalException {

		Session session = null;

		try {
			session = openSession();

			ApplicationAssignJournal applicationAssignJournal =
				(ApplicationAssignJournal)session.get(
					ApplicationAssignJournalImpl.class, primaryKey);

			if (applicationAssignJournal == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApplicationAssignJournalException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(applicationAssignJournal);
		}
		catch (NoSuchApplicationAssignJournalException noSuchEntityException) {
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
	protected ApplicationAssignJournal removeImpl(
		ApplicationAssignJournal applicationAssignJournal) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(applicationAssignJournal)) {
				applicationAssignJournal =
					(ApplicationAssignJournal)session.get(
						ApplicationAssignJournalImpl.class,
						applicationAssignJournal.getPrimaryKeyObj());
			}

			if (applicationAssignJournal != null) {
				session.delete(applicationAssignJournal);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (applicationAssignJournal != null) {
			clearCache(applicationAssignJournal);
		}

		return applicationAssignJournal;
	}

	@Override
	public ApplicationAssignJournal updateImpl(
		ApplicationAssignJournal applicationAssignJournal) {

		boolean isNew = applicationAssignJournal.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(applicationAssignJournal);
			}
			else {
				applicationAssignJournal =
					(ApplicationAssignJournal)session.merge(
						applicationAssignJournal);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			ApplicationAssignJournalImpl.class, applicationAssignJournal, false,
			true);

		if (isNew) {
			applicationAssignJournal.setNew(false);
		}

		applicationAssignJournal.resetOriginalValues();

		return applicationAssignJournal;
	}

	/**
	 * Returns the application assign journal with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the application assign journal
	 * @return the application assign journal
	 * @throws NoSuchApplicationAssignJournalException if a application assign journal with the primary key could not be found
	 */
	@Override
	public ApplicationAssignJournal findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApplicationAssignJournalException {

		ApplicationAssignJournal applicationAssignJournal = fetchByPrimaryKey(
			primaryKey);

		if (applicationAssignJournal == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApplicationAssignJournalException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return applicationAssignJournal;
	}

	/**
	 * Returns the application assign journal with the primary key or throws a <code>NoSuchApplicationAssignJournalException</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign journal
	 * @return the application assign journal
	 * @throws NoSuchApplicationAssignJournalException if a application assign journal with the primary key could not be found
	 */
	@Override
	public ApplicationAssignJournal findByPrimaryKey(int Id)
		throws NoSuchApplicationAssignJournalException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the application assign journal with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the application assign journal
	 * @return the application assign journal, or <code>null</code> if a application assign journal with the primary key could not be found
	 */
	@Override
	public ApplicationAssignJournal fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the application assign journals.
	 *
	 * @return the application assign journals
	 */
	@Override
	public List<ApplicationAssignJournal> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the application assign journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign journals
	 * @param end the upper bound of the range of application assign journals (not inclusive)
	 * @return the range of application assign journals
	 */
	@Override
	public List<ApplicationAssignJournal> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the application assign journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign journals
	 * @param end the upper bound of the range of application assign journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application assign journals
	 */
	@Override
	public List<ApplicationAssignJournal> findAll(
		int start, int end,
		OrderByComparator<ApplicationAssignJournal> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the application assign journals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApplicationAssignJournalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of application assign journals
	 * @param end the upper bound of the range of application assign journals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of application assign journals
	 */
	@Override
	public List<ApplicationAssignJournal> findAll(
		int start, int end,
		OrderByComparator<ApplicationAssignJournal> orderByComparator,
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

		List<ApplicationAssignJournal> list = null;

		if (useFinderCache) {
			list = (List<ApplicationAssignJournal>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_APPLICATIONASSIGNJOURNAL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_APPLICATIONASSIGNJOURNAL;

				sql = sql.concat(
					ApplicationAssignJournalModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ApplicationAssignJournal>)QueryUtil.list(
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
	 * Removes all the application assign journals from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ApplicationAssignJournal applicationAssignJournal : findAll()) {
			remove(applicationAssignJournal);
		}
	}

	/**
	 * Returns the number of application assign journals.
	 *
	 * @return the number of application assign journals
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_APPLICATIONASSIGNJOURNAL);

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
		return _SQL_SELECT_APPLICATIONASSIGNJOURNAL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ApplicationAssignJournalModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the application assign journal persistence.
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

		_setApplicationAssignJournalUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setApplicationAssignJournalUtilPersistence(null);

		dummyEntityCache.removeCache(
			ApplicationAssignJournalImpl.class.getName());
	}

	private void _setApplicationAssignJournalUtilPersistence(
		ApplicationAssignJournalPersistence
			applicationAssignJournalPersistence) {

		try {
			Field field = ApplicationAssignJournalUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, applicationAssignJournalPersistence);
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

	private static final String _SQL_SELECT_APPLICATIONASSIGNJOURNAL =
		"SELECT applicationAssignJournal FROM ApplicationAssignJournal applicationAssignJournal";

	private static final String _SQL_COUNT_APPLICATIONASSIGNJOURNAL =
		"SELECT COUNT(applicationAssignJournal) FROM ApplicationAssignJournal applicationAssignJournal";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"applicationAssignJournal.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ApplicationAssignJournal exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ApplicationAssignJournalPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}