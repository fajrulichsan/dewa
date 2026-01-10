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

import com.astra.dewa.exception.NoSuchDiskonFakpolException;
import com.astra.dewa.model.DiskonFakpol;
import com.astra.dewa.model.DiskonFakpolTable;
import com.astra.dewa.model.impl.DiskonFakpolImpl;
import com.astra.dewa.model.impl.DiskonFakpolModelImpl;
import com.astra.dewa.service.persistence.DiskonFakpolPersistence;
import com.astra.dewa.service.persistence.DiskonFakpolUtil;
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
 * The persistence implementation for the diskon fakpol service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DiskonFakpolPersistence.class)
public class DiskonFakpolPersistenceImpl
	extends BasePersistenceImpl<DiskonFakpol>
	implements DiskonFakpolPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DiskonFakpolUtil</code> to access the diskon fakpol persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DiskonFakpolImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public DiskonFakpolPersistenceImpl() {
		setModelClass(DiskonFakpol.class);

		setModelImplClass(DiskonFakpolImpl.class);
		setModelPKClass(int.class);

		setTable(DiskonFakpolTable.INSTANCE);
	}

	/**
	 * Caches the diskon fakpol in the entity cache if it is enabled.
	 *
	 * @param diskonFakpol the diskon fakpol
	 */
	@Override
	public void cacheResult(DiskonFakpol diskonFakpol) {
		entityCache.putResult(
			DiskonFakpolImpl.class, diskonFakpol.getPrimaryKey(), diskonFakpol);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the diskon fakpols in the entity cache if it is enabled.
	 *
	 * @param diskonFakpols the diskon fakpols
	 */
	@Override
	public void cacheResult(List<DiskonFakpol> diskonFakpols) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (diskonFakpols.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DiskonFakpol diskonFakpol : diskonFakpols) {
			if (entityCache.getResult(
					DiskonFakpolImpl.class, diskonFakpol.getPrimaryKey()) ==
						null) {

				cacheResult(diskonFakpol);
			}
		}
	}

	/**
	 * Clears the cache for all diskon fakpols.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DiskonFakpolImpl.class);

		finderCache.clearCache(DiskonFakpolImpl.class);
	}

	/**
	 * Clears the cache for the diskon fakpol.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DiskonFakpol diskonFakpol) {
		entityCache.removeResult(DiskonFakpolImpl.class, diskonFakpol);
	}

	@Override
	public void clearCache(List<DiskonFakpol> diskonFakpols) {
		for (DiskonFakpol diskonFakpol : diskonFakpols) {
			entityCache.removeResult(DiskonFakpolImpl.class, diskonFakpol);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DiskonFakpolImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DiskonFakpolImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new diskon fakpol with the primary key. Does not add the diskon fakpol to the database.
	 *
	 * @param Id the primary key for the new diskon fakpol
	 * @return the new diskon fakpol
	 */
	@Override
	public DiskonFakpol create(int Id) {
		DiskonFakpol diskonFakpol = new DiskonFakpolImpl();

		diskonFakpol.setNew(true);
		diskonFakpol.setPrimaryKey(Id);

		return diskonFakpol;
	}

	/**
	 * Removes the diskon fakpol with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the diskon fakpol
	 * @return the diskon fakpol that was removed
	 * @throws NoSuchDiskonFakpolException if a diskon fakpol with the primary key could not be found
	 */
	@Override
	public DiskonFakpol remove(int Id) throws NoSuchDiskonFakpolException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the diskon fakpol with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the diskon fakpol
	 * @return the diskon fakpol that was removed
	 * @throws NoSuchDiskonFakpolException if a diskon fakpol with the primary key could not be found
	 */
	@Override
	public DiskonFakpol remove(Serializable primaryKey)
		throws NoSuchDiskonFakpolException {

		Session session = null;

		try {
			session = openSession();

			DiskonFakpol diskonFakpol = (DiskonFakpol)session.get(
				DiskonFakpolImpl.class, primaryKey);

			if (diskonFakpol == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDiskonFakpolException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(diskonFakpol);
		}
		catch (NoSuchDiskonFakpolException noSuchEntityException) {
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
	protected DiskonFakpol removeImpl(DiskonFakpol diskonFakpol) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(diskonFakpol)) {
				diskonFakpol = (DiskonFakpol)session.get(
					DiskonFakpolImpl.class, diskonFakpol.getPrimaryKeyObj());
			}

			if (diskonFakpol != null) {
				session.delete(diskonFakpol);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (diskonFakpol != null) {
			clearCache(diskonFakpol);
		}

		return diskonFakpol;
	}

	@Override
	public DiskonFakpol updateImpl(DiskonFakpol diskonFakpol) {
		boolean isNew = diskonFakpol.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(diskonFakpol);
			}
			else {
				diskonFakpol = (DiskonFakpol)session.merge(diskonFakpol);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DiskonFakpolImpl.class, diskonFakpol, false, true);

		if (isNew) {
			diskonFakpol.setNew(false);
		}

		diskonFakpol.resetOriginalValues();

		return diskonFakpol;
	}

	/**
	 * Returns the diskon fakpol with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the diskon fakpol
	 * @return the diskon fakpol
	 * @throws NoSuchDiskonFakpolException if a diskon fakpol with the primary key could not be found
	 */
	@Override
	public DiskonFakpol findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDiskonFakpolException {

		DiskonFakpol diskonFakpol = fetchByPrimaryKey(primaryKey);

		if (diskonFakpol == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDiskonFakpolException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return diskonFakpol;
	}

	/**
	 * Returns the diskon fakpol with the primary key or throws a <code>NoSuchDiskonFakpolException</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon fakpol
	 * @return the diskon fakpol
	 * @throws NoSuchDiskonFakpolException if a diskon fakpol with the primary key could not be found
	 */
	@Override
	public DiskonFakpol findByPrimaryKey(int Id)
		throws NoSuchDiskonFakpolException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the diskon fakpol with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the diskon fakpol
	 * @return the diskon fakpol, or <code>null</code> if a diskon fakpol with the primary key could not be found
	 */
	@Override
	public DiskonFakpol fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the diskon fakpols.
	 *
	 * @return the diskon fakpols
	 */
	@Override
	public List<DiskonFakpol> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the diskon fakpols.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFakpolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fakpols
	 * @param end the upper bound of the range of diskon fakpols (not inclusive)
	 * @return the range of diskon fakpols
	 */
	@Override
	public List<DiskonFakpol> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the diskon fakpols.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFakpolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fakpols
	 * @param end the upper bound of the range of diskon fakpols (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of diskon fakpols
	 */
	@Override
	public List<DiskonFakpol> findAll(
		int start, int end, OrderByComparator<DiskonFakpol> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the diskon fakpols.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonFakpolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon fakpols
	 * @param end the upper bound of the range of diskon fakpols (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of diskon fakpols
	 */
	@Override
	public List<DiskonFakpol> findAll(
		int start, int end, OrderByComparator<DiskonFakpol> orderByComparator,
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

		List<DiskonFakpol> list = null;

		if (useFinderCache) {
			list = (List<DiskonFakpol>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DISKONFAKPOL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DISKONFAKPOL;

				sql = sql.concat(DiskonFakpolModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DiskonFakpol>)QueryUtil.list(
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
	 * Removes all the diskon fakpols from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DiskonFakpol diskonFakpol : findAll()) {
			remove(diskonFakpol);
		}
	}

	/**
	 * Returns the number of diskon fakpols.
	 *
	 * @return the number of diskon fakpols
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DISKONFAKPOL);

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
		return _SQL_SELECT_DISKONFAKPOL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DiskonFakpolModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the diskon fakpol persistence.
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

		_setDiskonFakpolUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setDiskonFakpolUtilPersistence(null);

		entityCache.removeCache(DiskonFakpolImpl.class.getName());
	}

	private void _setDiskonFakpolUtilPersistence(
		DiskonFakpolPersistence diskonFakpolPersistence) {

		try {
			Field field = DiskonFakpolUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, diskonFakpolPersistence);
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

	private static final String _SQL_SELECT_DISKONFAKPOL =
		"SELECT diskonFakpol FROM DiskonFakpol diskonFakpol";

	private static final String _SQL_COUNT_DISKONFAKPOL =
		"SELECT COUNT(diskonFakpol) FROM DiskonFakpol diskonFakpol";

	private static final String _ORDER_BY_ENTITY_ALIAS = "diskonFakpol.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DiskonFakpol exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		DiskonFakpolPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}