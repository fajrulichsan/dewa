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

import com.astra.dewa.exception.NoSuchDiskonTestCarException;
import com.astra.dewa.model.DiskonTestCar;
import com.astra.dewa.model.DiskonTestCarTable;
import com.astra.dewa.model.impl.DiskonTestCarImpl;
import com.astra.dewa.model.impl.DiskonTestCarModelImpl;
import com.astra.dewa.service.persistence.DiskonTestCarPersistence;
import com.astra.dewa.service.persistence.DiskonTestCarUtil;
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
 * The persistence implementation for the diskon test car service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DiskonTestCarPersistence.class)
public class DiskonTestCarPersistenceImpl
	extends BasePersistenceImpl<DiskonTestCar>
	implements DiskonTestCarPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DiskonTestCarUtil</code> to access the diskon test car persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DiskonTestCarImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public DiskonTestCarPersistenceImpl() {
		setModelClass(DiskonTestCar.class);

		setModelImplClass(DiskonTestCarImpl.class);
		setModelPKClass(int.class);

		setTable(DiskonTestCarTable.INSTANCE);
	}

	/**
	 * Caches the diskon test car in the entity cache if it is enabled.
	 *
	 * @param diskonTestCar the diskon test car
	 */
	@Override
	public void cacheResult(DiskonTestCar diskonTestCar) {
		entityCache.putResult(
			DiskonTestCarImpl.class, diskonTestCar.getPrimaryKey(),
			diskonTestCar);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the diskon test cars in the entity cache if it is enabled.
	 *
	 * @param diskonTestCars the diskon test cars
	 */
	@Override
	public void cacheResult(List<DiskonTestCar> diskonTestCars) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (diskonTestCars.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DiskonTestCar diskonTestCar : diskonTestCars) {
			if (entityCache.getResult(
					DiskonTestCarImpl.class, diskonTestCar.getPrimaryKey()) ==
						null) {

				cacheResult(diskonTestCar);
			}
		}
	}

	/**
	 * Clears the cache for all diskon test cars.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DiskonTestCarImpl.class);

		finderCache.clearCache(DiskonTestCarImpl.class);
	}

	/**
	 * Clears the cache for the diskon test car.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DiskonTestCar diskonTestCar) {
		entityCache.removeResult(DiskonTestCarImpl.class, diskonTestCar);
	}

	@Override
	public void clearCache(List<DiskonTestCar> diskonTestCars) {
		for (DiskonTestCar diskonTestCar : diskonTestCars) {
			entityCache.removeResult(DiskonTestCarImpl.class, diskonTestCar);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DiskonTestCarImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DiskonTestCarImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new diskon test car with the primary key. Does not add the diskon test car to the database.
	 *
	 * @param Id the primary key for the new diskon test car
	 * @return the new diskon test car
	 */
	@Override
	public DiskonTestCar create(int Id) {
		DiskonTestCar diskonTestCar = new DiskonTestCarImpl();

		diskonTestCar.setNew(true);
		diskonTestCar.setPrimaryKey(Id);

		return diskonTestCar;
	}

	/**
	 * Removes the diskon test car with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the diskon test car
	 * @return the diskon test car that was removed
	 * @throws NoSuchDiskonTestCarException if a diskon test car with the primary key could not be found
	 */
	@Override
	public DiskonTestCar remove(int Id) throws NoSuchDiskonTestCarException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the diskon test car with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the diskon test car
	 * @return the diskon test car that was removed
	 * @throws NoSuchDiskonTestCarException if a diskon test car with the primary key could not be found
	 */
	@Override
	public DiskonTestCar remove(Serializable primaryKey)
		throws NoSuchDiskonTestCarException {

		Session session = null;

		try {
			session = openSession();

			DiskonTestCar diskonTestCar = (DiskonTestCar)session.get(
				DiskonTestCarImpl.class, primaryKey);

			if (diskonTestCar == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDiskonTestCarException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(diskonTestCar);
		}
		catch (NoSuchDiskonTestCarException noSuchEntityException) {
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
	protected DiskonTestCar removeImpl(DiskonTestCar diskonTestCar) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(diskonTestCar)) {
				diskonTestCar = (DiskonTestCar)session.get(
					DiskonTestCarImpl.class, diskonTestCar.getPrimaryKeyObj());
			}

			if (diskonTestCar != null) {
				session.delete(diskonTestCar);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (diskonTestCar != null) {
			clearCache(diskonTestCar);
		}

		return diskonTestCar;
	}

	@Override
	public DiskonTestCar updateImpl(DiskonTestCar diskonTestCar) {
		boolean isNew = diskonTestCar.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(diskonTestCar);
			}
			else {
				diskonTestCar = (DiskonTestCar)session.merge(diskonTestCar);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DiskonTestCarImpl.class, diskonTestCar, false, true);

		if (isNew) {
			diskonTestCar.setNew(false);
		}

		diskonTestCar.resetOriginalValues();

		return diskonTestCar;
	}

	/**
	 * Returns the diskon test car with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the diskon test car
	 * @return the diskon test car
	 * @throws NoSuchDiskonTestCarException if a diskon test car with the primary key could not be found
	 */
	@Override
	public DiskonTestCar findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDiskonTestCarException {

		DiskonTestCar diskonTestCar = fetchByPrimaryKey(primaryKey);

		if (diskonTestCar == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDiskonTestCarException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return diskonTestCar;
	}

	/**
	 * Returns the diskon test car with the primary key or throws a <code>NoSuchDiskonTestCarException</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon test car
	 * @return the diskon test car
	 * @throws NoSuchDiskonTestCarException if a diskon test car with the primary key could not be found
	 */
	@Override
	public DiskonTestCar findByPrimaryKey(int Id)
		throws NoSuchDiskonTestCarException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the diskon test car with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon test car
	 * @return the diskon test car, or <code>null</code> if a diskon test car with the primary key could not be found
	 */
	@Override
	public DiskonTestCar fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the diskon test cars.
	 *
	 * @return the diskon test cars
	 */
	@Override
	public List<DiskonTestCar> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the diskon test cars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonTestCarModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon test cars
	 * @param end the upper bound of the range of diskon test cars (not inclusive)
	 * @return the range of diskon test cars
	 */
	@Override
	public List<DiskonTestCar> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the diskon test cars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonTestCarModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon test cars
	 * @param end the upper bound of the range of diskon test cars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of diskon test cars
	 */
	@Override
	public List<DiskonTestCar> findAll(
		int start, int end,
		OrderByComparator<DiskonTestCar> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the diskon test cars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonTestCarModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon test cars
	 * @param end the upper bound of the range of diskon test cars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of diskon test cars
	 */
	@Override
	public List<DiskonTestCar> findAll(
		int start, int end, OrderByComparator<DiskonTestCar> orderByComparator,
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

		List<DiskonTestCar> list = null;

		if (useFinderCache) {
			list = (List<DiskonTestCar>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DISKONTESTCAR);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DISKONTESTCAR;

				sql = sql.concat(DiskonTestCarModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DiskonTestCar>)QueryUtil.list(
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
	 * Removes all the diskon test cars from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DiskonTestCar diskonTestCar : findAll()) {
			remove(diskonTestCar);
		}
	}

	/**
	 * Returns the number of diskon test cars.
	 *
	 * @return the number of diskon test cars
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DISKONTESTCAR);

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
		return _SQL_SELECT_DISKONTESTCAR;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DiskonTestCarModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the diskon test car persistence.
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

		_setDiskonTestCarUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setDiskonTestCarUtilPersistence(null);

		entityCache.removeCache(DiskonTestCarImpl.class.getName());
	}

	private void _setDiskonTestCarUtilPersistence(
		DiskonTestCarPersistence diskonTestCarPersistence) {

		try {
			Field field = DiskonTestCarUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, diskonTestCarPersistence);
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

	private static final String _SQL_SELECT_DISKONTESTCAR =
		"SELECT diskonTestCar FROM DiskonTestCar diskonTestCar";

	private static final String _SQL_COUNT_DISKONTESTCAR =
		"SELECT COUNT(diskonTestCar) FROM DiskonTestCar diskonTestCar";

	private static final String _ORDER_BY_ENTITY_ALIAS = "diskonTestCar.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DiskonTestCar exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		DiskonTestCarPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}