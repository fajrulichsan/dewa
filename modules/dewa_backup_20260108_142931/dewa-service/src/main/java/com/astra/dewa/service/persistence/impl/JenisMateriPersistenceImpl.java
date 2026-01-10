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

import com.astra.dewa.exception.NoSuchJenisMateriException;
import com.astra.dewa.model.JenisMateri;
import com.astra.dewa.model.JenisMateriTable;
import com.astra.dewa.model.impl.JenisMateriImpl;
import com.astra.dewa.model.impl.JenisMateriModelImpl;
import com.astra.dewa.service.persistence.JenisMateriPersistence;
import com.astra.dewa.service.persistence.JenisMateriUtil;

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
 * The persistence implementation for the jenis materi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JenisMateriPersistenceImpl
	extends BasePersistenceImpl<JenisMateri> implements JenisMateriPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>JenisMateriUtil</code> to access the jenis materi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		JenisMateriImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public JenisMateriPersistenceImpl() {
		setModelClass(JenisMateri.class);

		setModelImplClass(JenisMateriImpl.class);
		setModelPKClass(int.class);

		setTable(JenisMateriTable.INSTANCE);
	}

	/**
	 * Caches the jenis materi in the entity cache if it is enabled.
	 *
	 * @param jenisMateri the jenis materi
	 */
	@Override
	public void cacheResult(JenisMateri jenisMateri) {
		dummyEntityCache.putResult(
			JenisMateriImpl.class, jenisMateri.getPrimaryKey(), jenisMateri);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the jenis materis in the entity cache if it is enabled.
	 *
	 * @param jenisMateris the jenis materis
	 */
	@Override
	public void cacheResult(List<JenisMateri> jenisMateris) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (jenisMateris.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (JenisMateri jenisMateri : jenisMateris) {
			if (dummyEntityCache.getResult(
					JenisMateriImpl.class, jenisMateri.getPrimaryKey()) ==
						null) {

				cacheResult(jenisMateri);
			}
		}
	}

	/**
	 * Clears the cache for all jenis materis.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(JenisMateriImpl.class);

		dummyFinderCache.clearCache(JenisMateriImpl.class);
	}

	/**
	 * Clears the cache for the jenis materi.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JenisMateri jenisMateri) {
		dummyEntityCache.removeResult(JenisMateriImpl.class, jenisMateri);
	}

	@Override
	public void clearCache(List<JenisMateri> jenisMateris) {
		for (JenisMateri jenisMateri : jenisMateris) {
			dummyEntityCache.removeResult(JenisMateriImpl.class, jenisMateri);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(JenisMateriImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(JenisMateriImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new jenis materi with the primary key. Does not add the jenis materi to the database.
	 *
	 * @param Id the primary key for the new jenis materi
	 * @return the new jenis materi
	 */
	@Override
	public JenisMateri create(int Id) {
		JenisMateri jenisMateri = new JenisMateriImpl();

		jenisMateri.setNew(true);
		jenisMateri.setPrimaryKey(Id);

		return jenisMateri;
	}

	/**
	 * Removes the jenis materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the jenis materi
	 * @return the jenis materi that was removed
	 * @throws NoSuchJenisMateriException if a jenis materi with the primary key could not be found
	 */
	@Override
	public JenisMateri remove(int Id) throws NoSuchJenisMateriException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the jenis materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the jenis materi
	 * @return the jenis materi that was removed
	 * @throws NoSuchJenisMateriException if a jenis materi with the primary key could not be found
	 */
	@Override
	public JenisMateri remove(Serializable primaryKey)
		throws NoSuchJenisMateriException {

		Session session = null;

		try {
			session = openSession();

			JenisMateri jenisMateri = (JenisMateri)session.get(
				JenisMateriImpl.class, primaryKey);

			if (jenisMateri == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJenisMateriException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(jenisMateri);
		}
		catch (NoSuchJenisMateriException noSuchEntityException) {
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
	protected JenisMateri removeImpl(JenisMateri jenisMateri) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jenisMateri)) {
				jenisMateri = (JenisMateri)session.get(
					JenisMateriImpl.class, jenisMateri.getPrimaryKeyObj());
			}

			if (jenisMateri != null) {
				session.delete(jenisMateri);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (jenisMateri != null) {
			clearCache(jenisMateri);
		}

		return jenisMateri;
	}

	@Override
	public JenisMateri updateImpl(JenisMateri jenisMateri) {
		boolean isNew = jenisMateri.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(jenisMateri);
			}
			else {
				jenisMateri = (JenisMateri)session.merge(jenisMateri);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			JenisMateriImpl.class, jenisMateri, false, true);

		if (isNew) {
			jenisMateri.setNew(false);
		}

		jenisMateri.resetOriginalValues();

		return jenisMateri;
	}

	/**
	 * Returns the jenis materi with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the jenis materi
	 * @return the jenis materi
	 * @throws NoSuchJenisMateriException if a jenis materi with the primary key could not be found
	 */
	@Override
	public JenisMateri findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJenisMateriException {

		JenisMateri jenisMateri = fetchByPrimaryKey(primaryKey);

		if (jenisMateri == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJenisMateriException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return jenisMateri;
	}

	/**
	 * Returns the jenis materi with the primary key or throws a <code>NoSuchJenisMateriException</code> if it could not be found.
	 *
	 * @param Id the primary key of the jenis materi
	 * @return the jenis materi
	 * @throws NoSuchJenisMateriException if a jenis materi with the primary key could not be found
	 */
	@Override
	public JenisMateri findByPrimaryKey(int Id)
		throws NoSuchJenisMateriException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the jenis materi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the jenis materi
	 * @return the jenis materi, or <code>null</code> if a jenis materi with the primary key could not be found
	 */
	@Override
	public JenisMateri fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the jenis materis.
	 *
	 * @return the jenis materis
	 */
	@Override
	public List<JenisMateri> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jenis materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JenisMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jenis materis
	 * @param end the upper bound of the range of jenis materis (not inclusive)
	 * @return the range of jenis materis
	 */
	@Override
	public List<JenisMateri> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the jenis materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JenisMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jenis materis
	 * @param end the upper bound of the range of jenis materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jenis materis
	 */
	@Override
	public List<JenisMateri> findAll(
		int start, int end, OrderByComparator<JenisMateri> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jenis materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JenisMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jenis materis
	 * @param end the upper bound of the range of jenis materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of jenis materis
	 */
	@Override
	public List<JenisMateri> findAll(
		int start, int end, OrderByComparator<JenisMateri> orderByComparator,
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

		List<JenisMateri> list = null;

		if (useFinderCache) {
			list = (List<JenisMateri>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_JENISMATERI);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_JENISMATERI;

				sql = sql.concat(JenisMateriModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<JenisMateri>)QueryUtil.list(
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
	 * Removes all the jenis materis from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (JenisMateri jenisMateri : findAll()) {
			remove(jenisMateri);
		}
	}

	/**
	 * Returns the number of jenis materis.
	 *
	 * @return the number of jenis materis
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_JENISMATERI);

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
		return _SQL_SELECT_JENISMATERI;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return JenisMateriModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the jenis materi persistence.
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

		_setJenisMateriUtilPersistence(this);
	}

	public void destroy() {
		_setJenisMateriUtilPersistence(null);

		dummyEntityCache.removeCache(JenisMateriImpl.class.getName());
	}

	private void _setJenisMateriUtilPersistence(
		JenisMateriPersistence jenisMateriPersistence) {

		try {
			Field field = JenisMateriUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, jenisMateriPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private static final String _SQL_SELECT_JENISMATERI =
		"SELECT jenisMateri FROM JenisMateri jenisMateri";

	private static final String _SQL_COUNT_JENISMATERI =
		"SELECT COUNT(jenisMateri) FROM JenisMateri jenisMateri";

	private static final String _ORDER_BY_ENTITY_ALIAS = "jenisMateri.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No JenisMateri exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		JenisMateriPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}