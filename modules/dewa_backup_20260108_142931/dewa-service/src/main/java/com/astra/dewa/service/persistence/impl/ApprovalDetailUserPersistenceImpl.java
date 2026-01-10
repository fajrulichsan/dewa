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

import com.astra.dewa.exception.NoSuchApprovalDetailUserException;
import com.astra.dewa.model.ApprovalDetailUser;
import com.astra.dewa.model.ApprovalDetailUserTable;
import com.astra.dewa.model.impl.ApprovalDetailUserImpl;
import com.astra.dewa.model.impl.ApprovalDetailUserModelImpl;
import com.astra.dewa.service.persistence.ApprovalDetailUserPersistence;
import com.astra.dewa.service.persistence.ApprovalDetailUserUtil;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the approval detail user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApprovalDetailUserPersistenceImpl
	extends BasePersistenceImpl<ApprovalDetailUser>
	implements ApprovalDetailUserPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ApprovalDetailUserUtil</code> to access the approval detail user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ApprovalDetailUserImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByFindDetailUser;
	private FinderPath _finderPathCountByFindDetailUser;

	/**
	 * Returns the approval detail user where ApprovalHeaderUserId = &#63; and RowStatus = &#63; or throws a <code>NoSuchApprovalDetailUserException</code> if it could not be found.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @return the matching approval detail user
	 * @throws NoSuchApprovalDetailUserException if a matching approval detail user could not be found
	 */
	@Override
	public ApprovalDetailUser findByFindDetailUser(
			int ApprovalHeaderUserId, Boolean RowStatus)
		throws NoSuchApprovalDetailUserException {

		ApprovalDetailUser approvalDetailUser = fetchByFindDetailUser(
			ApprovalHeaderUserId, RowStatus);

		if (approvalDetailUser == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("ApprovalHeaderUserId=");
			sb.append(ApprovalHeaderUserId);

			sb.append(", RowStatus=");
			sb.append(RowStatus);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchApprovalDetailUserException(sb.toString());
		}

		return approvalDetailUser;
	}

	/**
	 * Returns the approval detail user where ApprovalHeaderUserId = &#63; and RowStatus = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @return the matching approval detail user, or <code>null</code> if a matching approval detail user could not be found
	 */
	@Override
	public ApprovalDetailUser fetchByFindDetailUser(
		int ApprovalHeaderUserId, Boolean RowStatus) {

		return fetchByFindDetailUser(ApprovalHeaderUserId, RowStatus, true);
	}

	/**
	 * Returns the approval detail user where ApprovalHeaderUserId = &#63; and RowStatus = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching approval detail user, or <code>null</code> if a matching approval detail user could not be found
	 */
	@Override
	public ApprovalDetailUser fetchByFindDetailUser(
		int ApprovalHeaderUserId, Boolean RowStatus, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {ApprovalHeaderUserId, RowStatus};
		}

		Object result = null;

		if (useFinderCache) {
			result = dummyFinderCache.getResult(
				_finderPathFetchByFindDetailUser, finderArgs, this);
		}

		if (result instanceof ApprovalDetailUser) {
			ApprovalDetailUser approvalDetailUser = (ApprovalDetailUser)result;

			if ((ApprovalHeaderUserId !=
					approvalDetailUser.getApprovalHeaderUserId()) ||
				!Objects.equals(RowStatus, approvalDetailUser.getRowStatus())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_APPROVALDETAILUSER_WHERE);

			sb.append(_FINDER_COLUMN_FINDDETAILUSER_APPROVALHEADERUSERID_2);

			sb.append(_FINDER_COLUMN_FINDDETAILUSER_ROWSTATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ApprovalHeaderUserId);

				queryPos.add(RowStatus.booleanValue());

				List<ApprovalDetailUser> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						dummyFinderCache.putResult(
							_finderPathFetchByFindDetailUser, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									ApprovalHeaderUserId, RowStatus
								};
							}

							_log.warn(
								"ApprovalDetailUserPersistenceImpl.fetchByFindDetailUser(int, Boolean, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ApprovalDetailUser approvalDetailUser = list.get(0);

					result = approvalDetailUser;

					cacheResult(approvalDetailUser);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (ApprovalDetailUser)result;
		}
	}

	/**
	 * Removes the approval detail user where ApprovalHeaderUserId = &#63; and RowStatus = &#63; from the database.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @return the approval detail user that was removed
	 */
	@Override
	public ApprovalDetailUser removeByFindDetailUser(
			int ApprovalHeaderUserId, Boolean RowStatus)
		throws NoSuchApprovalDetailUserException {

		ApprovalDetailUser approvalDetailUser = findByFindDetailUser(
			ApprovalHeaderUserId, RowStatus);

		return remove(approvalDetailUser);
	}

	/**
	 * Returns the number of approval detail users where ApprovalHeaderUserId = &#63; and RowStatus = &#63;.
	 *
	 * @param ApprovalHeaderUserId the approval header user ID
	 * @param RowStatus the row status
	 * @return the number of matching approval detail users
	 */
	@Override
	public int countByFindDetailUser(
		int ApprovalHeaderUserId, Boolean RowStatus) {

		FinderPath finderPath = _finderPathCountByFindDetailUser;

		Object[] finderArgs = new Object[] {ApprovalHeaderUserId, RowStatus};

		Long count = (Long)dummyFinderCache.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_APPROVALDETAILUSER_WHERE);

			sb.append(_FINDER_COLUMN_FINDDETAILUSER_APPROVALHEADERUSERID_2);

			sb.append(_FINDER_COLUMN_FINDDETAILUSER_ROWSTATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ApprovalHeaderUserId);

				queryPos.add(RowStatus.booleanValue());

				count = (Long)query.uniqueResult();

				dummyFinderCache.putResult(finderPath, finderArgs, count);
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

	private static final String
		_FINDER_COLUMN_FINDDETAILUSER_APPROVALHEADERUSERID_2 =
			"approvalDetailUser.ApprovalHeaderUserId = ? AND ";

	private static final String _FINDER_COLUMN_FINDDETAILUSER_ROWSTATUS_2 =
		"approvalDetailUser.RowStatus = ?";

	public ApprovalDetailUserPersistenceImpl() {
		setModelClass(ApprovalDetailUser.class);

		setModelImplClass(ApprovalDetailUserImpl.class);
		setModelPKClass(int.class);

		setTable(ApprovalDetailUserTable.INSTANCE);
	}

	/**
	 * Caches the approval detail user in the entity cache if it is enabled.
	 *
	 * @param approvalDetailUser the approval detail user
	 */
	@Override
	public void cacheResult(ApprovalDetailUser approvalDetailUser) {
		dummyEntityCache.putResult(
			ApprovalDetailUserImpl.class, approvalDetailUser.getPrimaryKey(),
			approvalDetailUser);

		dummyFinderCache.putResult(
			_finderPathFetchByFindDetailUser,
			new Object[] {
				approvalDetailUser.getApprovalHeaderUserId(),
				approvalDetailUser.getRowStatus()
			},
			approvalDetailUser);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the approval detail users in the entity cache if it is enabled.
	 *
	 * @param approvalDetailUsers the approval detail users
	 */
	@Override
	public void cacheResult(List<ApprovalDetailUser> approvalDetailUsers) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (approvalDetailUsers.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ApprovalDetailUser approvalDetailUser : approvalDetailUsers) {
			if (dummyEntityCache.getResult(
					ApprovalDetailUserImpl.class,
					approvalDetailUser.getPrimaryKey()) == null) {

				cacheResult(approvalDetailUser);
			}
		}
	}

	/**
	 * Clears the cache for all approval detail users.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(ApprovalDetailUserImpl.class);

		dummyFinderCache.clearCache(ApprovalDetailUserImpl.class);
	}

	/**
	 * Clears the cache for the approval detail user.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ApprovalDetailUser approvalDetailUser) {
		dummyEntityCache.removeResult(
			ApprovalDetailUserImpl.class, approvalDetailUser);
	}

	@Override
	public void clearCache(List<ApprovalDetailUser> approvalDetailUsers) {
		for (ApprovalDetailUser approvalDetailUser : approvalDetailUsers) {
			dummyEntityCache.removeResult(
				ApprovalDetailUserImpl.class, approvalDetailUser);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(ApprovalDetailUserImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(
				ApprovalDetailUserImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ApprovalDetailUserModelImpl approvalDetailUserModelImpl) {

		Object[] args = new Object[] {
			approvalDetailUserModelImpl.getApprovalHeaderUserId(),
			approvalDetailUserModelImpl.getRowStatus()
		};

		dummyFinderCache.putResult(
			_finderPathCountByFindDetailUser, args, Long.valueOf(1));
		dummyFinderCache.putResult(
			_finderPathFetchByFindDetailUser, args,
			approvalDetailUserModelImpl);
	}

	/**
	 * Creates a new approval detail user with the primary key. Does not add the approval detail user to the database.
	 *
	 * @param Id the primary key for the new approval detail user
	 * @return the new approval detail user
	 */
	@Override
	public ApprovalDetailUser create(int Id) {
		ApprovalDetailUser approvalDetailUser = new ApprovalDetailUserImpl();

		approvalDetailUser.setNew(true);
		approvalDetailUser.setPrimaryKey(Id);

		return approvalDetailUser;
	}

	/**
	 * Removes the approval detail user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the approval detail user
	 * @return the approval detail user that was removed
	 * @throws NoSuchApprovalDetailUserException if a approval detail user with the primary key could not be found
	 */
	@Override
	public ApprovalDetailUser remove(int Id)
		throws NoSuchApprovalDetailUserException {

		return remove((Serializable)Id);
	}

	/**
	 * Removes the approval detail user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the approval detail user
	 * @return the approval detail user that was removed
	 * @throws NoSuchApprovalDetailUserException if a approval detail user with the primary key could not be found
	 */
	@Override
	public ApprovalDetailUser remove(Serializable primaryKey)
		throws NoSuchApprovalDetailUserException {

		Session session = null;

		try {
			session = openSession();

			ApprovalDetailUser approvalDetailUser =
				(ApprovalDetailUser)session.get(
					ApprovalDetailUserImpl.class, primaryKey);

			if (approvalDetailUser == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApprovalDetailUserException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(approvalDetailUser);
		}
		catch (NoSuchApprovalDetailUserException noSuchEntityException) {
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
	protected ApprovalDetailUser removeImpl(
		ApprovalDetailUser approvalDetailUser) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(approvalDetailUser)) {
				approvalDetailUser = (ApprovalDetailUser)session.get(
					ApprovalDetailUserImpl.class,
					approvalDetailUser.getPrimaryKeyObj());
			}

			if (approvalDetailUser != null) {
				session.delete(approvalDetailUser);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (approvalDetailUser != null) {
			clearCache(approvalDetailUser);
		}

		return approvalDetailUser;
	}

	@Override
	public ApprovalDetailUser updateImpl(
		ApprovalDetailUser approvalDetailUser) {

		boolean isNew = approvalDetailUser.isNew();

		if (!(approvalDetailUser instanceof ApprovalDetailUserModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(approvalDetailUser.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					approvalDetailUser);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in approvalDetailUser proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ApprovalDetailUser implementation " +
					approvalDetailUser.getClass());
		}

		ApprovalDetailUserModelImpl approvalDetailUserModelImpl =
			(ApprovalDetailUserModelImpl)approvalDetailUser;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(approvalDetailUser);
			}
			else {
				approvalDetailUser = (ApprovalDetailUser)session.merge(
					approvalDetailUser);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			ApprovalDetailUserImpl.class, approvalDetailUserModelImpl, false,
			true);

		cacheUniqueFindersCache(approvalDetailUserModelImpl);

		if (isNew) {
			approvalDetailUser.setNew(false);
		}

		approvalDetailUser.resetOriginalValues();

		return approvalDetailUser;
	}

	/**
	 * Returns the approval detail user with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the approval detail user
	 * @return the approval detail user
	 * @throws NoSuchApprovalDetailUserException if a approval detail user with the primary key could not be found
	 */
	@Override
	public ApprovalDetailUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApprovalDetailUserException {

		ApprovalDetailUser approvalDetailUser = fetchByPrimaryKey(primaryKey);

		if (approvalDetailUser == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApprovalDetailUserException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return approvalDetailUser;
	}

	/**
	 * Returns the approval detail user with the primary key or throws a <code>NoSuchApprovalDetailUserException</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval detail user
	 * @return the approval detail user
	 * @throws NoSuchApprovalDetailUserException if a approval detail user with the primary key could not be found
	 */
	@Override
	public ApprovalDetailUser findByPrimaryKey(int Id)
		throws NoSuchApprovalDetailUserException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the approval detail user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the approval detail user
	 * @return the approval detail user, or <code>null</code> if a approval detail user with the primary key could not be found
	 */
	@Override
	public ApprovalDetailUser fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the approval detail users.
	 *
	 * @return the approval detail users
	 */
	@Override
	public List<ApprovalDetailUser> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the approval detail users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalDetailUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval detail users
	 * @param end the upper bound of the range of approval detail users (not inclusive)
	 * @return the range of approval detail users
	 */
	@Override
	public List<ApprovalDetailUser> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the approval detail users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalDetailUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval detail users
	 * @param end the upper bound of the range of approval detail users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of approval detail users
	 */
	@Override
	public List<ApprovalDetailUser> findAll(
		int start, int end,
		OrderByComparator<ApprovalDetailUser> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the approval detail users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApprovalDetailUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of approval detail users
	 * @param end the upper bound of the range of approval detail users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of approval detail users
	 */
	@Override
	public List<ApprovalDetailUser> findAll(
		int start, int end,
		OrderByComparator<ApprovalDetailUser> orderByComparator,
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

		List<ApprovalDetailUser> list = null;

		if (useFinderCache) {
			list = (List<ApprovalDetailUser>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_APPROVALDETAILUSER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_APPROVALDETAILUSER;

				sql = sql.concat(ApprovalDetailUserModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ApprovalDetailUser>)QueryUtil.list(
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
	 * Removes all the approval detail users from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ApprovalDetailUser approvalDetailUser : findAll()) {
			remove(approvalDetailUser);
		}
	}

	/**
	 * Returns the number of approval detail users.
	 *
	 * @return the number of approval detail users
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
					_SQL_COUNT_APPROVALDETAILUSER);

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
		return _SQL_SELECT_APPROVALDETAILUSER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ApprovalDetailUserModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the approval detail user persistence.
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

		_finderPathFetchByFindDetailUser = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByFindDetailUser",
			new String[] {Integer.class.getName(), Boolean.class.getName()},
			new String[] {"ApprovalHeaderUserId", "RowStatus"}, true);

		_finderPathCountByFindDetailUser = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFindDetailUser",
			new String[] {Integer.class.getName(), Boolean.class.getName()},
			new String[] {"ApprovalHeaderUserId", "RowStatus"}, false);

		_setApprovalDetailUserUtilPersistence(this);
	}

	public void destroy() {
		_setApprovalDetailUserUtilPersistence(null);

		dummyEntityCache.removeCache(ApprovalDetailUserImpl.class.getName());
	}

	private void _setApprovalDetailUserUtilPersistence(
		ApprovalDetailUserPersistence approvalDetailUserPersistence) {

		try {
			Field field = ApprovalDetailUserUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, approvalDetailUserPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private static final String _SQL_SELECT_APPROVALDETAILUSER =
		"SELECT approvalDetailUser FROM ApprovalDetailUser approvalDetailUser";

	private static final String _SQL_SELECT_APPROVALDETAILUSER_WHERE =
		"SELECT approvalDetailUser FROM ApprovalDetailUser approvalDetailUser WHERE ";

	private static final String _SQL_COUNT_APPROVALDETAILUSER =
		"SELECT COUNT(approvalDetailUser) FROM ApprovalDetailUser approvalDetailUser";

	private static final String _SQL_COUNT_APPROVALDETAILUSER_WHERE =
		"SELECT COUNT(approvalDetailUser) FROM ApprovalDetailUser approvalDetailUser WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "approvalDetailUser.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ApprovalDetailUser exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ApprovalDetailUser exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ApprovalDetailUserPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}