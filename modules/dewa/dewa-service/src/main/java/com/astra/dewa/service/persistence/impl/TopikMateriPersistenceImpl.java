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

import com.astra.dewa.exception.NoSuchTopikMateriException;
import com.astra.dewa.model.TopikMateri;
import com.astra.dewa.model.TopikMateriTable;
import com.astra.dewa.model.impl.TopikMateriImpl;
import com.astra.dewa.model.impl.TopikMateriModelImpl;
import com.astra.dewa.service.persistence.TopikMateriPersistence;
import com.astra.dewa.service.persistence.TopikMateriUtil;
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
 * The persistence implementation for the topik materi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = TopikMateriPersistence.class)
public class TopikMateriPersistenceImpl
	extends BasePersistenceImpl<TopikMateri> implements TopikMateriPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TopikMateriUtil</code> to access the topik materi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TopikMateriImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public TopikMateriPersistenceImpl() {
		setModelClass(TopikMateri.class);

		setModelImplClass(TopikMateriImpl.class);
		setModelPKClass(int.class);

		setTable(TopikMateriTable.INSTANCE);
	}

	/**
	 * Caches the topik materi in the entity cache if it is enabled.
	 *
	 * @param topikMateri the topik materi
	 */
	@Override
	public void cacheResult(TopikMateri topikMateri) {
		dummyEntityCache.putResult(
			TopikMateriImpl.class, topikMateri.getPrimaryKey(), topikMateri);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the topik materis in the entity cache if it is enabled.
	 *
	 * @param topikMateris the topik materis
	 */
	@Override
	public void cacheResult(List<TopikMateri> topikMateris) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (topikMateris.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TopikMateri topikMateri : topikMateris) {
			if (dummyEntityCache.getResult(
					TopikMateriImpl.class, topikMateri.getPrimaryKey()) ==
						null) {

				cacheResult(topikMateri);
			}
		}
	}

	/**
	 * Clears the cache for all topik materis.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(TopikMateriImpl.class);

		dummyFinderCache.clearCache(TopikMateriImpl.class);
	}

	/**
	 * Clears the cache for the topik materi.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TopikMateri topikMateri) {
		dummyEntityCache.removeResult(TopikMateriImpl.class, topikMateri);
	}

	@Override
	public void clearCache(List<TopikMateri> topikMateris) {
		for (TopikMateri topikMateri : topikMateris) {
			dummyEntityCache.removeResult(TopikMateriImpl.class, topikMateri);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(TopikMateriImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(TopikMateriImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new topik materi with the primary key. Does not add the topik materi to the database.
	 *
	 * @param Id the primary key for the new topik materi
	 * @return the new topik materi
	 */
	@Override
	public TopikMateri create(int Id) {
		TopikMateri topikMateri = new TopikMateriImpl();

		topikMateri.setNew(true);
		topikMateri.setPrimaryKey(Id);

		return topikMateri;
	}

	/**
	 * Removes the topik materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the topik materi
	 * @return the topik materi that was removed
	 * @throws NoSuchTopikMateriException if a topik materi with the primary key could not be found
	 */
	@Override
	public TopikMateri remove(int Id) throws NoSuchTopikMateriException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the topik materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the topik materi
	 * @return the topik materi that was removed
	 * @throws NoSuchTopikMateriException if a topik materi with the primary key could not be found
	 */
	@Override
	public TopikMateri remove(Serializable primaryKey)
		throws NoSuchTopikMateriException {

		Session session = null;

		try {
			session = openSession();

			TopikMateri topikMateri = (TopikMateri)session.get(
				TopikMateriImpl.class, primaryKey);

			if (topikMateri == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTopikMateriException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(topikMateri);
		}
		catch (NoSuchTopikMateriException noSuchEntityException) {
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
	protected TopikMateri removeImpl(TopikMateri topikMateri) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(topikMateri)) {
				topikMateri = (TopikMateri)session.get(
					TopikMateriImpl.class, topikMateri.getPrimaryKeyObj());
			}

			if (topikMateri != null) {
				session.delete(topikMateri);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (topikMateri != null) {
			clearCache(topikMateri);
		}

		return topikMateri;
	}

	@Override
	public TopikMateri updateImpl(TopikMateri topikMateri) {
		boolean isNew = topikMateri.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(topikMateri);
			}
			else {
				topikMateri = (TopikMateri)session.merge(topikMateri);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			TopikMateriImpl.class, topikMateri, false, true);

		if (isNew) {
			topikMateri.setNew(false);
		}

		topikMateri.resetOriginalValues();

		return topikMateri;
	}

	/**
	 * Returns the topik materi with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the topik materi
	 * @return the topik materi
	 * @throws NoSuchTopikMateriException if a topik materi with the primary key could not be found
	 */
	@Override
	public TopikMateri findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTopikMateriException {

		TopikMateri topikMateri = fetchByPrimaryKey(primaryKey);

		if (topikMateri == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTopikMateriException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return topikMateri;
	}

	/**
	 * Returns the topik materi with the primary key or throws a <code>NoSuchTopikMateriException</code> if it could not be found.
	 *
	 * @param Id the primary key of the topik materi
	 * @return the topik materi
	 * @throws NoSuchTopikMateriException if a topik materi with the primary key could not be found
	 */
	@Override
	public TopikMateri findByPrimaryKey(int Id)
		throws NoSuchTopikMateriException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the topik materi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the topik materi
	 * @return the topik materi, or <code>null</code> if a topik materi with the primary key could not be found
	 */
	@Override
	public TopikMateri fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the topik materis.
	 *
	 * @return the topik materis
	 */
	@Override
	public List<TopikMateri> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the topik materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TopikMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of topik materis
	 * @param end the upper bound of the range of topik materis (not inclusive)
	 * @return the range of topik materis
	 */
	@Override
	public List<TopikMateri> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the topik materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TopikMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of topik materis
	 * @param end the upper bound of the range of topik materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of topik materis
	 */
	@Override
	public List<TopikMateri> findAll(
		int start, int end, OrderByComparator<TopikMateri> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the topik materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TopikMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of topik materis
	 * @param end the upper bound of the range of topik materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of topik materis
	 */
	@Override
	public List<TopikMateri> findAll(
		int start, int end, OrderByComparator<TopikMateri> orderByComparator,
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

		List<TopikMateri> list = null;

		if (useFinderCache) {
			list = (List<TopikMateri>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TOPIKMATERI);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TOPIKMATERI;

				sql = sql.concat(TopikMateriModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TopikMateri>)QueryUtil.list(
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
	 * Removes all the topik materis from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TopikMateri topikMateri : findAll()) {
			remove(topikMateri);
		}
	}

	/**
	 * Returns the number of topik materis.
	 *
	 * @return the number of topik materis
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_TOPIKMATERI);

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
		return _SQL_SELECT_TOPIKMATERI;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TopikMateriModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the topik materi persistence.
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

		_setTopikMateriUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setTopikMateriUtilPersistence(null);

		dummyEntityCache.removeCache(TopikMateriImpl.class.getName());
	}

	private void _setTopikMateriUtilPersistence(
		TopikMateriPersistence topikMateriPersistence) {

		try {
			Field field = TopikMateriUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, topikMateriPersistence);
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

	private static final String _SQL_SELECT_TOPIKMATERI =
		"SELECT topikMateri FROM TopikMateri topikMateri";

	private static final String _SQL_COUNT_TOPIKMATERI =
		"SELECT COUNT(topikMateri) FROM TopikMateri topikMateri";

	private static final String _ORDER_BY_ENTITY_ALIAS = "topikMateri.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TopikMateri exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		TopikMateriPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}