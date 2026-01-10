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

import com.astra.dewa.exception.NoSuchUserRoleTypeException;
import com.astra.dewa.model.UserRoleType;
import com.astra.dewa.model.UserRoleTypeTable;
import com.astra.dewa.model.impl.UserRoleTypeImpl;
import com.astra.dewa.model.impl.UserRoleTypeModelImpl;
import com.astra.dewa.service.persistence.UserRoleTypePersistence;
import com.astra.dewa.service.persistence.UserRoleTypeUtil;
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
 * The persistence implementation for the user role type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = UserRoleTypePersistence.class)
public class UserRoleTypePersistenceImpl
	extends BasePersistenceImpl<UserRoleType>
	implements UserRoleTypePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UserRoleTypeUtil</code> to access the user role type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UserRoleTypeImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public UserRoleTypePersistenceImpl() {
		setModelClass(UserRoleType.class);

		setModelImplClass(UserRoleTypeImpl.class);
		setModelPKClass(int.class);

		setTable(UserRoleTypeTable.INSTANCE);
	}

	/**
	 * Caches the user role type in the entity cache if it is enabled.
	 *
	 * @param userRoleType the user role type
	 */
	@Override
	public void cacheResult(UserRoleType userRoleType) {
		entityCache.putResult(
			UserRoleTypeImpl.class, userRoleType.getPrimaryKey(), userRoleType);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the user role types in the entity cache if it is enabled.
	 *
	 * @param userRoleTypes the user role types
	 */
	@Override
	public void cacheResult(List<UserRoleType> userRoleTypes) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (userRoleTypes.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (UserRoleType userRoleType : userRoleTypes) {
			if (entityCache.getResult(
					UserRoleTypeImpl.class, userRoleType.getPrimaryKey()) ==
						null) {

				cacheResult(userRoleType);
			}
		}
	}

	/**
	 * Clears the cache for all user role types.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserRoleTypeImpl.class);

		finderCache.clearCache(UserRoleTypeImpl.class);
	}

	/**
	 * Clears the cache for the user role type.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserRoleType userRoleType) {
		entityCache.removeResult(UserRoleTypeImpl.class, userRoleType);
	}

	@Override
	public void clearCache(List<UserRoleType> userRoleTypes) {
		for (UserRoleType userRoleType : userRoleTypes) {
			entityCache.removeResult(UserRoleTypeImpl.class, userRoleType);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(UserRoleTypeImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(UserRoleTypeImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new user role type with the primary key. Does not add the user role type to the database.
	 *
	 * @param Id the primary key for the new user role type
	 * @return the new user role type
	 */
	@Override
	public UserRoleType create(int Id) {
		UserRoleType userRoleType = new UserRoleTypeImpl();

		userRoleType.setNew(true);
		userRoleType.setPrimaryKey(Id);

		return userRoleType;
	}

	/**
	 * Removes the user role type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the user role type
	 * @return the user role type that was removed
	 * @throws NoSuchUserRoleTypeException if a user role type with the primary key could not be found
	 */
	@Override
	public UserRoleType remove(int Id) throws NoSuchUserRoleTypeException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the user role type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user role type
	 * @return the user role type that was removed
	 * @throws NoSuchUserRoleTypeException if a user role type with the primary key could not be found
	 */
	@Override
	public UserRoleType remove(Serializable primaryKey)
		throws NoSuchUserRoleTypeException {

		Session session = null;

		try {
			session = openSession();

			UserRoleType userRoleType = (UserRoleType)session.get(
				UserRoleTypeImpl.class, primaryKey);

			if (userRoleType == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserRoleTypeException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(userRoleType);
		}
		catch (NoSuchUserRoleTypeException noSuchEntityException) {
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
	protected UserRoleType removeImpl(UserRoleType userRoleType) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userRoleType)) {
				userRoleType = (UserRoleType)session.get(
					UserRoleTypeImpl.class, userRoleType.getPrimaryKeyObj());
			}

			if (userRoleType != null) {
				session.delete(userRoleType);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (userRoleType != null) {
			clearCache(userRoleType);
		}

		return userRoleType;
	}

	@Override
	public UserRoleType updateImpl(UserRoleType userRoleType) {
		boolean isNew = userRoleType.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(userRoleType);
			}
			else {
				userRoleType = (UserRoleType)session.merge(userRoleType);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			UserRoleTypeImpl.class, userRoleType, false, true);

		if (isNew) {
			userRoleType.setNew(false);
		}

		userRoleType.resetOriginalValues();

		return userRoleType;
	}

	/**
	 * Returns the user role type with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user role type
	 * @return the user role type
	 * @throws NoSuchUserRoleTypeException if a user role type with the primary key could not be found
	 */
	@Override
	public UserRoleType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserRoleTypeException {

		UserRoleType userRoleType = fetchByPrimaryKey(primaryKey);

		if (userRoleType == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserRoleTypeException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return userRoleType;
	}

	/**
	 * Returns the user role type with the primary key or throws a <code>NoSuchUserRoleTypeException</code> if it could not be found.
	 *
	 * @param Id the primary key of the user role type
	 * @return the user role type
	 * @throws NoSuchUserRoleTypeException if a user role type with the primary key could not be found
	 */
	@Override
	public UserRoleType findByPrimaryKey(int Id)
		throws NoSuchUserRoleTypeException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the user role type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the user role type
	 * @return the user role type, or <code>null</code> if a user role type with the primary key could not be found
	 */
	@Override
	public UserRoleType fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the user role types.
	 *
	 * @return the user role types
	 */
	@Override
	public List<UserRoleType> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user role types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRoleTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user role types
	 * @param end the upper bound of the range of user role types (not inclusive)
	 * @return the range of user role types
	 */
	@Override
	public List<UserRoleType> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user role types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRoleTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user role types
	 * @param end the upper bound of the range of user role types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user role types
	 */
	@Override
	public List<UserRoleType> findAll(
		int start, int end, OrderByComparator<UserRoleType> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user role types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRoleTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user role types
	 * @param end the upper bound of the range of user role types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user role types
	 */
	@Override
	public List<UserRoleType> findAll(
		int start, int end, OrderByComparator<UserRoleType> orderByComparator,
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

		List<UserRoleType> list = null;

		if (useFinderCache) {
			list = (List<UserRoleType>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_USERROLETYPE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_USERROLETYPE;

				sql = sql.concat(UserRoleTypeModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UserRoleType>)QueryUtil.list(
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
	 * Removes all the user role types from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserRoleType userRoleType : findAll()) {
			remove(userRoleType);
		}
	}

	/**
	 * Returns the number of user role types.
	 *
	 * @return the number of user role types
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_USERROLETYPE);

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
		return _SQL_SELECT_USERROLETYPE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UserRoleTypeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user role type persistence.
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

		_setUserRoleTypeUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setUserRoleTypeUtilPersistence(null);

		entityCache.removeCache(UserRoleTypeImpl.class.getName());
	}

	private void _setUserRoleTypeUtilPersistence(
		UserRoleTypePersistence userRoleTypePersistence) {

		try {
			Field field = UserRoleTypeUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, userRoleTypePersistence);
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

	private static final String _SQL_SELECT_USERROLETYPE =
		"SELECT userRoleType FROM UserRoleType userRoleType";

	private static final String _SQL_COUNT_USERROLETYPE =
		"SELECT COUNT(userRoleType) FROM UserRoleType userRoleType";

	private static final String _ORDER_BY_ENTITY_ALIAS = "userRoleType.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UserRoleType exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		UserRoleTypePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}