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

import com.astra.dewa.exception.NoSuchSP3DException;
import com.astra.dewa.model.SP3D;
import com.astra.dewa.model.SP3DTable;
import com.astra.dewa.model.impl.SP3DImpl;
import com.astra.dewa.model.impl.SP3DModelImpl;
import com.astra.dewa.service.persistence.SP3DPersistence;
import com.astra.dewa.service.persistence.SP3DUtil;
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
 * The persistence implementation for the sp3d service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SP3DPersistence.class)
public class SP3DPersistenceImpl
	extends BasePersistenceImpl<SP3D> implements SP3DPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SP3DUtil</code> to access the sp3d persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SP3DImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public SP3DPersistenceImpl() {
		setModelClass(SP3D.class);

		setModelImplClass(SP3DImpl.class);
		setModelPKClass(int.class);

		setTable(SP3DTable.INSTANCE);
	}

	/**
	 * Caches the sp3d in the entity cache if it is enabled.
	 *
	 * @param sp3d the sp3d
	 */
	@Override
	public void cacheResult(SP3D sp3d) {
		entityCache.putResult(SP3DImpl.class, sp3d.getPrimaryKey(), sp3d);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the sp3ds in the entity cache if it is enabled.
	 *
	 * @param sp3ds the sp3ds
	 */
	@Override
	public void cacheResult(List<SP3D> sp3ds) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (sp3ds.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SP3D sp3d : sp3ds) {
			if (entityCache.getResult(SP3DImpl.class, sp3d.getPrimaryKey()) ==
					null) {

				cacheResult(sp3d);
			}
		}
	}

	/**
	 * Clears the cache for all sp3ds.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SP3DImpl.class);

		finderCache.clearCache(SP3DImpl.class);
	}

	/**
	 * Clears the cache for the sp3d.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SP3D sp3d) {
		entityCache.removeResult(SP3DImpl.class, sp3d);
	}

	@Override
	public void clearCache(List<SP3D> sp3ds) {
		for (SP3D sp3d : sp3ds) {
			entityCache.removeResult(SP3DImpl.class, sp3d);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SP3DImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SP3DImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new sp3d with the primary key. Does not add the sp3d to the database.
	 *
	 * @param Id the primary key for the new sp3d
	 * @return the new sp3d
	 */
	@Override
	public SP3D create(int Id) {
		SP3D sp3d = new SP3DImpl();

		sp3d.setNew(true);
		sp3d.setPrimaryKey(Id);

		return sp3d;
	}

	/**
	 * Removes the sp3d with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the sp3d
	 * @return the sp3d that was removed
	 * @throws NoSuchSP3DException if a sp3d with the primary key could not be found
	 */
	@Override
	public SP3D remove(int Id) throws NoSuchSP3DException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the sp3d with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sp3d
	 * @return the sp3d that was removed
	 * @throws NoSuchSP3DException if a sp3d with the primary key could not be found
	 */
	@Override
	public SP3D remove(Serializable primaryKey) throws NoSuchSP3DException {
		Session session = null;

		try {
			session = openSession();

			SP3D sp3d = (SP3D)session.get(SP3DImpl.class, primaryKey);

			if (sp3d == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSP3DException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(sp3d);
		}
		catch (NoSuchSP3DException noSuchEntityException) {
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
	protected SP3D removeImpl(SP3D sp3d) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sp3d)) {
				sp3d = (SP3D)session.get(
					SP3DImpl.class, sp3d.getPrimaryKeyObj());
			}

			if (sp3d != null) {
				session.delete(sp3d);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (sp3d != null) {
			clearCache(sp3d);
		}

		return sp3d;
	}

	@Override
	public SP3D updateImpl(SP3D sp3d) {
		boolean isNew = sp3d.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(sp3d);
			}
			else {
				sp3d = (SP3D)session.merge(sp3d);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(SP3DImpl.class, sp3d, false, true);

		if (isNew) {
			sp3d.setNew(false);
		}

		sp3d.resetOriginalValues();

		return sp3d;
	}

	/**
	 * Returns the sp3d with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sp3d
	 * @return the sp3d
	 * @throws NoSuchSP3DException if a sp3d with the primary key could not be found
	 */
	@Override
	public SP3D findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSP3DException {

		SP3D sp3d = fetchByPrimaryKey(primaryKey);

		if (sp3d == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSP3DException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return sp3d;
	}

	/**
	 * Returns the sp3d with the primary key or throws a <code>NoSuchSP3DException</code> if it could not be found.
	 *
	 * @param Id the primary key of the sp3d
	 * @return the sp3d
	 * @throws NoSuchSP3DException if a sp3d with the primary key could not be found
	 */
	@Override
	public SP3D findByPrimaryKey(int Id) throws NoSuchSP3DException {
		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the sp3d with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the sp3d
	 * @return the sp3d, or <code>null</code> if a sp3d with the primary key could not be found
	 */
	@Override
	public SP3D fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the sp3ds.
	 *
	 * @return the sp3ds
	 */
	@Override
	public List<SP3D> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sp3ds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SP3DModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sp3ds
	 * @param end the upper bound of the range of sp3ds (not inclusive)
	 * @return the range of sp3ds
	 */
	@Override
	public List<SP3D> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sp3ds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SP3DModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sp3ds
	 * @param end the upper bound of the range of sp3ds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sp3ds
	 */
	@Override
	public List<SP3D> findAll(
		int start, int end, OrderByComparator<SP3D> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sp3ds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SP3DModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sp3ds
	 * @param end the upper bound of the range of sp3ds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sp3ds
	 */
	@Override
	public List<SP3D> findAll(
		int start, int end, OrderByComparator<SP3D> orderByComparator,
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

		List<SP3D> list = null;

		if (useFinderCache) {
			list = (List<SP3D>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SP3D);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SP3D;

				sql = sql.concat(SP3DModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SP3D>)QueryUtil.list(
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
	 * Removes all the sp3ds from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SP3D sp3d : findAll()) {
			remove(sp3d);
		}
	}

	/**
	 * Returns the number of sp3ds.
	 *
	 * @return the number of sp3ds
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SP3D);

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
		return _SQL_SELECT_SP3D;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SP3DModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sp3d persistence.
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

		_setSP3DUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSP3DUtilPersistence(null);

		entityCache.removeCache(SP3DImpl.class.getName());
	}

	private void _setSP3DUtilPersistence(SP3DPersistence sp3dPersistence) {
		try {
			Field field = SP3DUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, sp3dPersistence);
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

	private static final String _SQL_SELECT_SP3D = "SELECT sp3d FROM SP3D sp3d";

	private static final String _SQL_COUNT_SP3D =
		"SELECT COUNT(sp3d) FROM SP3D sp3d";

	private static final String _ORDER_BY_ENTITY_ALIAS = "sp3d.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SP3D exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		SP3DPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}