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

import com.astra.dewa.exception.NoSuchWilayahException;
import com.astra.dewa.model.Wilayah;
import com.astra.dewa.model.WilayahTable;
import com.astra.dewa.model.impl.WilayahImpl;
import com.astra.dewa.model.impl.WilayahModelImpl;
import com.astra.dewa.service.persistence.WilayahPersistence;
import com.astra.dewa.service.persistence.WilayahUtil;
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
 * The persistence implementation for the wilayah service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = WilayahPersistence.class)
public class WilayahPersistenceImpl
	extends BasePersistenceImpl<Wilayah> implements WilayahPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WilayahUtil</code> to access the wilayah persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WilayahImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WilayahPersistenceImpl() {
		setModelClass(Wilayah.class);

		setModelImplClass(WilayahImpl.class);
		setModelPKClass(int.class);

		setTable(WilayahTable.INSTANCE);
	}

	/**
	 * Caches the wilayah in the entity cache if it is enabled.
	 *
	 * @param wilayah the wilayah
	 */
	@Override
	public void cacheResult(Wilayah wilayah) {
		entityCache.putResult(
			WilayahImpl.class, wilayah.getPrimaryKey(), wilayah);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the wilayahs in the entity cache if it is enabled.
	 *
	 * @param wilayahs the wilayahs
	 */
	@Override
	public void cacheResult(List<Wilayah> wilayahs) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (wilayahs.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Wilayah wilayah : wilayahs) {
			if (entityCache.getResult(
					WilayahImpl.class, wilayah.getPrimaryKey()) == null) {

				cacheResult(wilayah);
			}
		}
	}

	/**
	 * Clears the cache for all wilayahs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WilayahImpl.class);

		finderCache.clearCache(WilayahImpl.class);
	}

	/**
	 * Clears the cache for the wilayah.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Wilayah wilayah) {
		entityCache.removeResult(WilayahImpl.class, wilayah);
	}

	@Override
	public void clearCache(List<Wilayah> wilayahs) {
		for (Wilayah wilayah : wilayahs) {
			entityCache.removeResult(WilayahImpl.class, wilayah);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(WilayahImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(WilayahImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new wilayah with the primary key. Does not add the wilayah to the database.
	 *
	 * @param Id the primary key for the new wilayah
	 * @return the new wilayah
	 */
	@Override
	public Wilayah create(int Id) {
		Wilayah wilayah = new WilayahImpl();

		wilayah.setNew(true);
		wilayah.setPrimaryKey(Id);

		return wilayah;
	}

	/**
	 * Removes the wilayah with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the wilayah
	 * @return the wilayah that was removed
	 * @throws NoSuchWilayahException if a wilayah with the primary key could not be found
	 */
	@Override
	public Wilayah remove(int Id) throws NoSuchWilayahException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the wilayah with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the wilayah
	 * @return the wilayah that was removed
	 * @throws NoSuchWilayahException if a wilayah with the primary key could not be found
	 */
	@Override
	public Wilayah remove(Serializable primaryKey)
		throws NoSuchWilayahException {

		Session session = null;

		try {
			session = openSession();

			Wilayah wilayah = (Wilayah)session.get(
				WilayahImpl.class, primaryKey);

			if (wilayah == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWilayahException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(wilayah);
		}
		catch (NoSuchWilayahException noSuchEntityException) {
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
	protected Wilayah removeImpl(Wilayah wilayah) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(wilayah)) {
				wilayah = (Wilayah)session.get(
					WilayahImpl.class, wilayah.getPrimaryKeyObj());
			}

			if (wilayah != null) {
				session.delete(wilayah);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (wilayah != null) {
			clearCache(wilayah);
		}

		return wilayah;
	}

	@Override
	public Wilayah updateImpl(Wilayah wilayah) {
		boolean isNew = wilayah.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(wilayah);
			}
			else {
				wilayah = (Wilayah)session.merge(wilayah);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(WilayahImpl.class, wilayah, false, true);

		if (isNew) {
			wilayah.setNew(false);
		}

		wilayah.resetOriginalValues();

		return wilayah;
	}

	/**
	 * Returns the wilayah with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the wilayah
	 * @return the wilayah
	 * @throws NoSuchWilayahException if a wilayah with the primary key could not be found
	 */
	@Override
	public Wilayah findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWilayahException {

		Wilayah wilayah = fetchByPrimaryKey(primaryKey);

		if (wilayah == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWilayahException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return wilayah;
	}

	/**
	 * Returns the wilayah with the primary key or throws a <code>NoSuchWilayahException</code> if it could not be found.
	 *
	 * @param Id the primary key of the wilayah
	 * @return the wilayah
	 * @throws NoSuchWilayahException if a wilayah with the primary key could not be found
	 */
	@Override
	public Wilayah findByPrimaryKey(int Id) throws NoSuchWilayahException {
		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the wilayah with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the wilayah
	 * @return the wilayah, or <code>null</code> if a wilayah with the primary key could not be found
	 */
	@Override
	public Wilayah fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the wilayahs.
	 *
	 * @return the wilayahs
	 */
	@Override
	public List<Wilayah> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the wilayahs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WilayahModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wilayahs
	 * @param end the upper bound of the range of wilayahs (not inclusive)
	 * @return the range of wilayahs
	 */
	@Override
	public List<Wilayah> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the wilayahs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WilayahModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wilayahs
	 * @param end the upper bound of the range of wilayahs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of wilayahs
	 */
	@Override
	public List<Wilayah> findAll(
		int start, int end, OrderByComparator<Wilayah> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the wilayahs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WilayahModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wilayahs
	 * @param end the upper bound of the range of wilayahs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of wilayahs
	 */
	@Override
	public List<Wilayah> findAll(
		int start, int end, OrderByComparator<Wilayah> orderByComparator,
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

		List<Wilayah> list = null;

		if (useFinderCache) {
			list = (List<Wilayah>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WILAYAH);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WILAYAH;

				sql = sql.concat(WilayahModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Wilayah>)QueryUtil.list(
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
	 * Removes all the wilayahs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Wilayah wilayah : findAll()) {
			remove(wilayah);
		}
	}

	/**
	 * Returns the number of wilayahs.
	 *
	 * @return the number of wilayahs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WILAYAH);

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
		return _SQL_SELECT_WILAYAH;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return WilayahModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the wilayah persistence.
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

		_setWilayahUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setWilayahUtilPersistence(null);

		entityCache.removeCache(WilayahImpl.class.getName());
	}

	private void _setWilayahUtilPersistence(
		WilayahPersistence wilayahPersistence) {

		try {
			Field field = WilayahUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, wilayahPersistence);
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

	private static final String _SQL_SELECT_WILAYAH =
		"SELECT wilayah FROM Wilayah wilayah";

	private static final String _SQL_COUNT_WILAYAH =
		"SELECT COUNT(wilayah) FROM Wilayah wilayah";

	private static final String _ORDER_BY_ENTITY_ALIAS = "wilayah.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Wilayah exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WilayahPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}