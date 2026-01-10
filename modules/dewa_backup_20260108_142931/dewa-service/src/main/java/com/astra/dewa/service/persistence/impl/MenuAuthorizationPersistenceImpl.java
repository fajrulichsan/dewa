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

import com.astra.dewa.exception.NoSuchMenuAuthorizationException;
import com.astra.dewa.model.MenuAuthorization;
import com.astra.dewa.model.MenuAuthorizationTable;
import com.astra.dewa.model.impl.MenuAuthorizationImpl;
import com.astra.dewa.model.impl.MenuAuthorizationModelImpl;
import com.astra.dewa.service.persistence.MenuAuthorizationPersistence;
import com.astra.dewa.service.persistence.MenuAuthorizationUtil;

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
 * The persistence implementation for the menu authorization service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MenuAuthorizationPersistenceImpl
	extends BasePersistenceImpl<MenuAuthorization>
	implements MenuAuthorizationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MenuAuthorizationUtil</code> to access the menu authorization persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MenuAuthorizationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public MenuAuthorizationPersistenceImpl() {
		setModelClass(MenuAuthorization.class);

		setModelImplClass(MenuAuthorizationImpl.class);
		setModelPKClass(int.class);

		setTable(MenuAuthorizationTable.INSTANCE);
	}

	/**
	 * Caches the menu authorization in the entity cache if it is enabled.
	 *
	 * @param menuAuthorization the menu authorization
	 */
	@Override
	public void cacheResult(MenuAuthorization menuAuthorization) {
		entityCache.putResult(
			MenuAuthorizationImpl.class, menuAuthorization.getPrimaryKey(),
			menuAuthorization);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the menu authorizations in the entity cache if it is enabled.
	 *
	 * @param menuAuthorizations the menu authorizations
	 */
	@Override
	public void cacheResult(List<MenuAuthorization> menuAuthorizations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (menuAuthorizations.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (MenuAuthorization menuAuthorization : menuAuthorizations) {
			if (entityCache.getResult(
					MenuAuthorizationImpl.class,
					menuAuthorization.getPrimaryKey()) == null) {

				cacheResult(menuAuthorization);
			}
		}
	}

	/**
	 * Clears the cache for all menu authorizations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MenuAuthorizationImpl.class);

		finderCache.clearCache(MenuAuthorizationImpl.class);
	}

	/**
	 * Clears the cache for the menu authorization.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MenuAuthorization menuAuthorization) {
		entityCache.removeResult(
			MenuAuthorizationImpl.class, menuAuthorization);
	}

	@Override
	public void clearCache(List<MenuAuthorization> menuAuthorizations) {
		for (MenuAuthorization menuAuthorization : menuAuthorizations) {
			entityCache.removeResult(
				MenuAuthorizationImpl.class, menuAuthorization);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MenuAuthorizationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(MenuAuthorizationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new menu authorization with the primary key. Does not add the menu authorization to the database.
	 *
	 * @param Id the primary key for the new menu authorization
	 * @return the new menu authorization
	 */
	@Override
	public MenuAuthorization create(int Id) {
		MenuAuthorization menuAuthorization = new MenuAuthorizationImpl();

		menuAuthorization.setNew(true);
		menuAuthorization.setPrimaryKey(Id);

		return menuAuthorization;
	}

	/**
	 * Removes the menu authorization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the menu authorization
	 * @return the menu authorization that was removed
	 * @throws NoSuchMenuAuthorizationException if a menu authorization with the primary key could not be found
	 */
	@Override
	public MenuAuthorization remove(int Id)
		throws NoSuchMenuAuthorizationException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the menu authorization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the menu authorization
	 * @return the menu authorization that was removed
	 * @throws NoSuchMenuAuthorizationException if a menu authorization with the primary key could not be found
	 */
	@Override
	public MenuAuthorization remove(Serializable primaryKey)
		throws NoSuchMenuAuthorizationException {

		Session session = null;

		try {
			session = openSession();

			MenuAuthorization menuAuthorization =
				(MenuAuthorization)session.get(
					MenuAuthorizationImpl.class, primaryKey);

			if (menuAuthorization == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMenuAuthorizationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(menuAuthorization);
		}
		catch (NoSuchMenuAuthorizationException noSuchEntityException) {
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
	protected MenuAuthorization removeImpl(
		MenuAuthorization menuAuthorization) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(menuAuthorization)) {
				menuAuthorization = (MenuAuthorization)session.get(
					MenuAuthorizationImpl.class,
					menuAuthorization.getPrimaryKeyObj());
			}

			if (menuAuthorization != null) {
				session.delete(menuAuthorization);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (menuAuthorization != null) {
			clearCache(menuAuthorization);
		}

		return menuAuthorization;
	}

	@Override
	public MenuAuthorization updateImpl(MenuAuthorization menuAuthorization) {
		boolean isNew = menuAuthorization.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(menuAuthorization);
			}
			else {
				menuAuthorization = (MenuAuthorization)session.merge(
					menuAuthorization);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MenuAuthorizationImpl.class, menuAuthorization, false, true);

		if (isNew) {
			menuAuthorization.setNew(false);
		}

		menuAuthorization.resetOriginalValues();

		return menuAuthorization;
	}

	/**
	 * Returns the menu authorization with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the menu authorization
	 * @return the menu authorization
	 * @throws NoSuchMenuAuthorizationException if a menu authorization with the primary key could not be found
	 */
	@Override
	public MenuAuthorization findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMenuAuthorizationException {

		MenuAuthorization menuAuthorization = fetchByPrimaryKey(primaryKey);

		if (menuAuthorization == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMenuAuthorizationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return menuAuthorization;
	}

	/**
	 * Returns the menu authorization with the primary key or throws a <code>NoSuchMenuAuthorizationException</code> if it could not be found.
	 *
	 * @param Id the primary key of the menu authorization
	 * @return the menu authorization
	 * @throws NoSuchMenuAuthorizationException if a menu authorization with the primary key could not be found
	 */
	@Override
	public MenuAuthorization findByPrimaryKey(int Id)
		throws NoSuchMenuAuthorizationException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the menu authorization with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the menu authorization
	 * @return the menu authorization, or <code>null</code> if a menu authorization with the primary key could not be found
	 */
	@Override
	public MenuAuthorization fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the menu authorizations.
	 *
	 * @return the menu authorizations
	 */
	@Override
	public List<MenuAuthorization> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the menu authorizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenuAuthorizationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of menu authorizations
	 * @param end the upper bound of the range of menu authorizations (not inclusive)
	 * @return the range of menu authorizations
	 */
	@Override
	public List<MenuAuthorization> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the menu authorizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenuAuthorizationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of menu authorizations
	 * @param end the upper bound of the range of menu authorizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of menu authorizations
	 */
	@Override
	public List<MenuAuthorization> findAll(
		int start, int end,
		OrderByComparator<MenuAuthorization> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the menu authorizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenuAuthorizationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of menu authorizations
	 * @param end the upper bound of the range of menu authorizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of menu authorizations
	 */
	@Override
	public List<MenuAuthorization> findAll(
		int start, int end,
		OrderByComparator<MenuAuthorization> orderByComparator,
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

		List<MenuAuthorization> list = null;

		if (useFinderCache) {
			list = (List<MenuAuthorization>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MENUAUTHORIZATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MENUAUTHORIZATION;

				sql = sql.concat(MenuAuthorizationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MenuAuthorization>)QueryUtil.list(
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
	 * Removes all the menu authorizations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MenuAuthorization menuAuthorization : findAll()) {
			remove(menuAuthorization);
		}
	}

	/**
	 * Returns the number of menu authorizations.
	 *
	 * @return the number of menu authorizations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_MENUAUTHORIZATION);

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
		return _SQL_SELECT_MENUAUTHORIZATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MenuAuthorizationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the menu authorization persistence.
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

		_setMenuAuthorizationUtilPersistence(this);
	}

	public void destroy() {
		_setMenuAuthorizationUtilPersistence(null);

		entityCache.removeCache(MenuAuthorizationImpl.class.getName());
	}

	private void _setMenuAuthorizationUtilPersistence(
		MenuAuthorizationPersistence menuAuthorizationPersistence) {

		try {
			Field field = MenuAuthorizationUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, menuAuthorizationPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MENUAUTHORIZATION =
		"SELECT menuAuthorization FROM MenuAuthorization menuAuthorization";

	private static final String _SQL_COUNT_MENUAUTHORIZATION =
		"SELECT COUNT(menuAuthorization) FROM MenuAuthorization menuAuthorization";

	private static final String _ORDER_BY_ENTITY_ALIAS = "menuAuthorization.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MenuAuthorization exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		MenuAuthorizationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}