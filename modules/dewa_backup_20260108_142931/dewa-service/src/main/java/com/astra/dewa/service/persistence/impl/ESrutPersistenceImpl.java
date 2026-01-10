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

import com.astra.dewa.exception.NoSuchESrutException;
import com.astra.dewa.model.ESrut;
import com.astra.dewa.model.ESrutTable;
import com.astra.dewa.model.impl.ESrutImpl;
import com.astra.dewa.model.impl.ESrutModelImpl;
import com.astra.dewa.service.persistence.ESrutPersistence;
import com.astra.dewa.service.persistence.ESrutUtil;

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
 * The persistence implementation for the e srut service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ESrutPersistenceImpl
	extends BasePersistenceImpl<ESrut> implements ESrutPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ESrutUtil</code> to access the e srut persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ESrutImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ESrutPersistenceImpl() {
		setModelClass(ESrut.class);

		setModelImplClass(ESrutImpl.class);
		setModelPKClass(int.class);

		setTable(ESrutTable.INSTANCE);
	}

	/**
	 * Caches the e srut in the entity cache if it is enabled.
	 *
	 * @param eSrut the e srut
	 */
	@Override
	public void cacheResult(ESrut eSrut) {
		entityCache.putResult(ESrutImpl.class, eSrut.getPrimaryKey(), eSrut);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the e sruts in the entity cache if it is enabled.
	 *
	 * @param eSruts the e sruts
	 */
	@Override
	public void cacheResult(List<ESrut> eSruts) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (eSruts.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ESrut eSrut : eSruts) {
			if (entityCache.getResult(ESrutImpl.class, eSrut.getPrimaryKey()) ==
					null) {

				cacheResult(eSrut);
			}
		}
	}

	/**
	 * Clears the cache for all e sruts.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ESrutImpl.class);

		finderCache.clearCache(ESrutImpl.class);
	}

	/**
	 * Clears the cache for the e srut.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ESrut eSrut) {
		entityCache.removeResult(ESrutImpl.class, eSrut);
	}

	@Override
	public void clearCache(List<ESrut> eSruts) {
		for (ESrut eSrut : eSruts) {
			entityCache.removeResult(ESrutImpl.class, eSrut);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ESrutImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ESrutImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new e srut with the primary key. Does not add the e srut to the database.
	 *
	 * @param Id the primary key for the new e srut
	 * @return the new e srut
	 */
	@Override
	public ESrut create(int Id) {
		ESrut eSrut = new ESrutImpl();

		eSrut.setNew(true);
		eSrut.setPrimaryKey(Id);

		return eSrut;
	}

	/**
	 * Removes the e srut with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the e srut
	 * @return the e srut that was removed
	 * @throws NoSuchESrutException if a e srut with the primary key could not be found
	 */
	@Override
	public ESrut remove(int Id) throws NoSuchESrutException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the e srut with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the e srut
	 * @return the e srut that was removed
	 * @throws NoSuchESrutException if a e srut with the primary key could not be found
	 */
	@Override
	public ESrut remove(Serializable primaryKey) throws NoSuchESrutException {
		Session session = null;

		try {
			session = openSession();

			ESrut eSrut = (ESrut)session.get(ESrutImpl.class, primaryKey);

			if (eSrut == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchESrutException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(eSrut);
		}
		catch (NoSuchESrutException noSuchEntityException) {
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
	protected ESrut removeImpl(ESrut eSrut) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(eSrut)) {
				eSrut = (ESrut)session.get(
					ESrutImpl.class, eSrut.getPrimaryKeyObj());
			}

			if (eSrut != null) {
				session.delete(eSrut);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (eSrut != null) {
			clearCache(eSrut);
		}

		return eSrut;
	}

	@Override
	public ESrut updateImpl(ESrut eSrut) {
		boolean isNew = eSrut.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(eSrut);
			}
			else {
				eSrut = (ESrut)session.merge(eSrut);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ESrutImpl.class, eSrut, false, true);

		if (isNew) {
			eSrut.setNew(false);
		}

		eSrut.resetOriginalValues();

		return eSrut;
	}

	/**
	 * Returns the e srut with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the e srut
	 * @return the e srut
	 * @throws NoSuchESrutException if a e srut with the primary key could not be found
	 */
	@Override
	public ESrut findByPrimaryKey(Serializable primaryKey)
		throws NoSuchESrutException {

		ESrut eSrut = fetchByPrimaryKey(primaryKey);

		if (eSrut == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchESrutException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return eSrut;
	}

	/**
	 * Returns the e srut with the primary key or throws a <code>NoSuchESrutException</code> if it could not be found.
	 *
	 * @param Id the primary key of the e srut
	 * @return the e srut
	 * @throws NoSuchESrutException if a e srut with the primary key could not be found
	 */
	@Override
	public ESrut findByPrimaryKey(int Id) throws NoSuchESrutException {
		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the e srut with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the e srut
	 * @return the e srut, or <code>null</code> if a e srut with the primary key could not be found
	 */
	@Override
	public ESrut fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the e sruts.
	 *
	 * @return the e sruts
	 */
	@Override
	public List<ESrut> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the e sruts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ESrutModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e sruts
	 * @param end the upper bound of the range of e sruts (not inclusive)
	 * @return the range of e sruts
	 */
	@Override
	public List<ESrut> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the e sruts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ESrutModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e sruts
	 * @param end the upper bound of the range of e sruts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of e sruts
	 */
	@Override
	public List<ESrut> findAll(
		int start, int end, OrderByComparator<ESrut> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the e sruts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ESrutModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of e sruts
	 * @param end the upper bound of the range of e sruts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of e sruts
	 */
	@Override
	public List<ESrut> findAll(
		int start, int end, OrderByComparator<ESrut> orderByComparator,
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

		List<ESrut> list = null;

		if (useFinderCache) {
			list = (List<ESrut>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ESRUT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ESRUT;

				sql = sql.concat(ESrutModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ESrut>)QueryUtil.list(
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
	 * Removes all the e sruts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ESrut eSrut : findAll()) {
			remove(eSrut);
		}
	}

	/**
	 * Returns the number of e sruts.
	 *
	 * @return the number of e sruts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ESRUT);

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
		return _SQL_SELECT_ESRUT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ESrutModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the e srut persistence.
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

		_setESrutUtilPersistence(this);
	}

	public void destroy() {
		_setESrutUtilPersistence(null);

		entityCache.removeCache(ESrutImpl.class.getName());
	}

	private void _setESrutUtilPersistence(ESrutPersistence eSrutPersistence) {
		try {
			Field field = ESrutUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, eSrutPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ESRUT =
		"SELECT eSrut FROM ESrut eSrut";

	private static final String _SQL_COUNT_ESRUT =
		"SELECT COUNT(eSrut) FROM ESrut eSrut";

	private static final String _ORDER_BY_ENTITY_ALIAS = "eSrut.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ESrut exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ESrutPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}