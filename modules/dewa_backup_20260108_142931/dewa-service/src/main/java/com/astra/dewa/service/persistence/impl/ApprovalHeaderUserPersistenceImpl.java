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

import com.astra.dewa.exception.NoSuchApprovalHeaderUserException;
import com.astra.dewa.model.ApprovalHeaderUser;
import com.astra.dewa.model.ApprovalHeaderUserTable;
import com.astra.dewa.model.impl.ApprovalHeaderUserImpl;
import com.astra.dewa.model.impl.ApprovalHeaderUserModelImpl;
import com.astra.dewa.service.persistence.ApprovalHeaderUserPersistence;
import com.astra.dewa.service.persistence.ApprovalHeaderUserUtil;

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
 * The persistence implementation for the approval header user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApprovalHeaderUserPersistenceImpl
	extends BasePersistenceImpl<ApprovalHeaderUser>
	implements ApprovalHeaderUserPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ApprovalHeaderUserUtil</code> to access the approval header user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ApprovalHeaderUserImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ApprovalHeaderUserPersistenceImpl() {
		setModelClass(ApprovalHeaderUser.class);

		setModelImplClass(ApprovalHeaderUserImpl.class);
		setModelPKClass(int.class);

		setTable(ApprovalHeaderUserTable.INSTANCE);
	}

	/**
	 * Caches the approval header user in the entity cache if it is enabled.
	 *
	 * @param approvalHeaderUser the approval header user
	 */
	@Override
	public void cacheResult(ApprovalHeaderUser approvalHeaderUser) {
		dummyEntityCache.putResult(
			ApprovalHeaderUserImpl.class, approvalHeaderUser.getPrimaryKey(),
			approvalHeaderUser);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the approval header users in the entity cache if it is enabled.
	 *
	 * @param approvalHeaderUsers the approval header users
	 */
	@Override
	public void cacheResult(List<ApprovalHeaderUser> approvalHeaderUsers) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (approvalHeaderUsers.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ApprovalHeaderUser approvalHeaderUser : approvalHeaderUsers) {
			if (dummyEntityCache.getResult(
					ApprovalHeaderUserImpl.class,
					approvalHeaderUser.getPrimaryKey()) == null) {

				cacheResult(approvalHeaderUser);
			}
		}
	}

	/**
	 * Clears the cache for all approval header users.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(ApprovalHeaderUserImpl.class);

		dummyFinderCache.clearCache(ApprovalHeaderUserImpl.class);
	}

	/**
	 * Clears the cache for the approval header user.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ApprovalHeaderUser approvalHeaderUser) {
		dummyEntityCache.removeResult(
			ApprovalHeaderUserImpl.class, approvalHeaderUser);
	}

	@Override
	public void clearCache(List<ApprovalHeaderUser> approvalHeaderUsers) {
		for (ApprovalHeaderUser approvalHeaderUser : approvalHeaderUsers) {
			dummyEntityCache.removeResult(
				ApprovalHeaderUserImpl.class, approvalHeaderUser);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(ApprovalHeaderUserImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(
				ApprovalHeaderUserImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new approval header user with the primary key. Does not add the approval header user to the database.
	 *
	 * @param Id the primary key for the new approval header user
	 * @return the new approval header user
	 */
	@Override
	public ApprovalHeaderUser create(int Id) {
		ApprovalHeaderUser approvalHeaderUser = new ApprovalHeaderUserImpl();

		approvalHeaderUser.setNew(true);
		approvalHeaderUser.setPrimaryKey(Id);

		return approvalHeaderUser;
	}

	/**
	 * Removes the approval header user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the approval header user
	 * @return the approval header user that was removed
	 * @throws NoSuchApprovalHeaderUserException if a approval header user with the primary key could not be found
	 */
	@Override
	public ApprovalHeaderUser remove(int Id)
		throws NoSuchApprovalHeaderUserException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the approval header user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the approval header user
	 * @return the approval header user that was removed
	 * @throws NoSuchApprovalHeaderUserException if a approval header user with the primary key could not be found
	 */
	@Override
	public ApprovalHeaderUser remove(Serializable primaryKey)
		throws NoSuchApprovalHeaderUserException {

		Session session = null;

		try {
			session = openSession();

			ApprovalHeaderUser approvalHeaderUser =
				(ApprovalHeaderUser)session.get(
					ApprovalHeaderUserImpl.class, primaryKey);

			if (approvalHeaderUser == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApprovalHeaderUserException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(approvalHeaderUser);
		}
		catch (NoSuchApprovalHeaderUserException noSuchEntityException) {
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
	protected ApprovalHeaderUser removeImpl(
		ApprovalHeaderUser approvalHeaderUser) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(approvalHeaderUser)) {
				approvalHeaderUser = (ApprovalHeaderUser)session.get(
					ApprovalHeaderUserImpl.class,
					approvalHeaderUser.getPrimaryKeyObj());
			}

			if (approvalHeaderUser != null) {
				session.delete(approvalHeaderUser);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (approvalHeaderUser != null) {
			clearCache(approvalHeaderUser);
		}

		return approvalHeaderUser;
	}

	@Override
	public ApprovalHeaderUser updateImpl(
		ApprovalHeaderUser approvalHeaderUser) {

		boolean isNew = approvalHeaderUser.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(approvalHeaderUser);
			}
			else {
				approvalHeaderUser = (ApprovalHeaderUser)session.merge(
					approvalHeaderUser);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			ApprovalHeaderUserImpl.class, approvalHeaderUser, false, true);

		if (isNew) {
			approvalHeaderUser.setNew(false);
		}

		approvalHeaderUser.resetOriginalValues();

		return approvalHeaderUser;
	}

	/**
	 * Returns the approval header user with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the approval header user
	 * @return the approval header user
	 * @throws NoSuchApprovalHeaderUserException if a approval header user with the primary key could not be found
	 */
	@Override
	public ApprovalHeaderUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApprovalHeaderUserException {

		ApprovalHeaderUser approvalHeaderUser = fetchByPrimaryKey(primaryKey);

		if (approvalHeaderUser == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApprovalHeaderUserException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return approvalHeaderUser;
	}

	/**
	 * Returns the approval header user with the primary key or throws a <code>NoSuchApprovalHeaderUserException</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval header user
	 * @return the approval header user
	 * @throws NoSuchApprovalHeaderUserException if a approval header user with the primary key could not be found
	 */
	@Override
	public ApprovalHeaderUser findByPrimaryKey(int Id)
		throws NoSuchApprovalHeaderUserException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the approval header user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval header user
	 * @return the approval header user, or <code>null</code> if a approval header user with the primary key could not be found
	 */
	@Override
	public ApprovalHeaderUser fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the approval header users.
	 *
	 * @return the approval header users
	 */
	@Override
	public List<ApprovalHeaderUser> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the approval header users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalHeaderUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval header users
	 * @param end the upper bound of the range of approval header users (not inclusive)
	 * @return the range of approval header users
	 */
	@Override
	public List<ApprovalHeaderUser> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the approval header users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalHeaderUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval header users
	 * @param end the upper bound of the range of approval header users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of approval header users
	 */
	@Override
	public List<ApprovalHeaderUser> findAll(
		int start, int end,
		OrderByComparator<ApprovalHeaderUser> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the approval header users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalHeaderUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval header users
	 * @param end the upper bound of the range of approval header users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of approval header users
	 */
	@Override
	public List<ApprovalHeaderUser> findAll(
		int start, int end,
		OrderByComparator<ApprovalHeaderUser> orderByComparator,
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

		List<ApprovalHeaderUser> list = null;

		if (useFinderCache) {
			list = (List<ApprovalHeaderUser>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_APPROVALHEADERUSER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_APPROVALHEADERUSER;

				sql = sql.concat(ApprovalHeaderUserModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ApprovalHeaderUser>)QueryUtil.list(
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
	 * Removes all the approval header users from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ApprovalHeaderUser approvalHeaderUser : findAll()) {
			remove(approvalHeaderUser);
		}
	}

	/**
	 * Returns the number of approval header users.
	 *
	 * @return the number of approval header users
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
					_SQL_COUNT_APPROVALHEADERUSER);

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
		return _SQL_SELECT_APPROVALHEADERUSER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ApprovalHeaderUserModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the approval header user persistence.
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

		_setApprovalHeaderUserUtilPersistence(this);
	}

	public void destroy() {
		_setApprovalHeaderUserUtilPersistence(null);

		dummyEntityCache.removeCache(ApprovalHeaderUserImpl.class.getName());
	}

	private void _setApprovalHeaderUserUtilPersistence(
		ApprovalHeaderUserPersistence approvalHeaderUserPersistence) {

		try {
			Field field = ApprovalHeaderUserUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, approvalHeaderUserPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private static final String _SQL_SELECT_APPROVALHEADERUSER =
		"SELECT approvalHeaderUser FROM ApprovalHeaderUser approvalHeaderUser";

	private static final String _SQL_COUNT_APPROVALHEADERUSER =
		"SELECT COUNT(approvalHeaderUser) FROM ApprovalHeaderUser approvalHeaderUser";

	private static final String _ORDER_BY_ENTITY_ALIAS = "approvalHeaderUser.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ApprovalHeaderUser exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ApprovalHeaderUserPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}