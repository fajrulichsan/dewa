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

import com.astra.dewa.exception.NoSuchDealerException;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.DealerTable;
import com.astra.dewa.model.impl.DealerImpl;
import com.astra.dewa.model.impl.DealerModelImpl;
import com.astra.dewa.service.persistence.DealerPersistence;
import com.astra.dewa.service.persistence.DealerUtil;
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
 * The persistence implementation for the dealer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DealerPersistence.class)
public class DealerPersistenceImpl
	extends BasePersistenceImpl<Dealer> implements DealerPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DealerUtil</code> to access the dealer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DealerImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public DealerPersistenceImpl() {
		setModelClass(Dealer.class);

		setModelImplClass(DealerImpl.class);
		setModelPKClass(int.class);

		setTable(DealerTable.INSTANCE);
	}

	/**
	 * Caches the dealer in the entity cache if it is enabled.
	 *
	 * @param dealer the dealer
	 */
	@Override
	public void cacheResult(Dealer dealer) {
		dummyEntityCache.putResult(
			DealerImpl.class, dealer.getPrimaryKey(), dealer);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the dealers in the entity cache if it is enabled.
	 *
	 * @param dealers the dealers
	 */
	@Override
	public void cacheResult(List<Dealer> dealers) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (dealers.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Dealer dealer : dealers) {
			if (dummyEntityCache.getResult(
					DealerImpl.class, dealer.getPrimaryKey()) == null) {

				cacheResult(dealer);
			}
		}
	}

	/**
	 * Clears the cache for all dealers.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(DealerImpl.class);

		dummyFinderCache.clearCache(DealerImpl.class);
	}

	/**
	 * Clears the cache for the dealer.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Dealer dealer) {
		dummyEntityCache.removeResult(DealerImpl.class, dealer);
	}

	@Override
	public void clearCache(List<Dealer> dealers) {
		for (Dealer dealer : dealers) {
			dummyEntityCache.removeResult(DealerImpl.class, dealer);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(DealerImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(DealerImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new dealer with the primary key. Does not add the dealer to the database.
	 *
	 * @param Id the primary key for the new dealer
	 * @return the new dealer
	 */
	@Override
	public Dealer create(int Id) {
		Dealer dealer = new DealerImpl();

		dealer.setNew(true);
		dealer.setPrimaryKey(Id);

		return dealer;
	}

	/**
	 * Removes the dealer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the dealer
	 * @return the dealer that was removed
	 * @throws NoSuchDealerException if a dealer with the primary key could not be found
	 */
	@Override
	public Dealer remove(int Id) throws NoSuchDealerException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the dealer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dealer
	 * @return the dealer that was removed
	 * @throws NoSuchDealerException if a dealer with the primary key could not be found
	 */
	@Override
	public Dealer remove(Serializable primaryKey) throws NoSuchDealerException {
		Session session = null;

		try {
			session = openSession();

			Dealer dealer = (Dealer)session.get(DealerImpl.class, primaryKey);

			if (dealer == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDealerException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(dealer);
		}
		catch (NoSuchDealerException noSuchEntityException) {
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
	protected Dealer removeImpl(Dealer dealer) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dealer)) {
				dealer = (Dealer)session.get(
					DealerImpl.class, dealer.getPrimaryKeyObj());
			}

			if (dealer != null) {
				session.delete(dealer);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (dealer != null) {
			clearCache(dealer);
		}

		return dealer;
	}

	@Override
	public Dealer updateImpl(Dealer dealer) {
		boolean isNew = dealer.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(dealer);
			}
			else {
				dealer = (Dealer)session.merge(dealer);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(DealerImpl.class, dealer, false, true);

		if (isNew) {
			dealer.setNew(false);
		}

		dealer.resetOriginalValues();

		return dealer;
	}

	/**
	 * Returns the dealer with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dealer
	 * @return the dealer
	 * @throws NoSuchDealerException if a dealer with the primary key could not be found
	 */
	@Override
	public Dealer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDealerException {

		Dealer dealer = fetchByPrimaryKey(primaryKey);

		if (dealer == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDealerException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return dealer;
	}

	/**
	 * Returns the dealer with the primary key or throws a <code>NoSuchDealerException</code> if it could not be found.
	 *
	 * @param Id the primary key of the dealer
	 * @return the dealer
	 * @throws NoSuchDealerException if a dealer with the primary key could not be found
	 */
	@Override
	public Dealer findByPrimaryKey(int Id) throws NoSuchDealerException {
		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the dealer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the dealer
	 * @return the dealer, or <code>null</code> if a dealer with the primary key could not be found
	 */
	@Override
	public Dealer fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the dealers.
	 *
	 * @return the dealers
	 */
	@Override
	public List<Dealer> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dealers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DealerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of dealers
	 * @param end the upper bound of the range of dealers (not inclusive)
	 * @return the range of dealers
	 */
	@Override
	public List<Dealer> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dealers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DealerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of dealers
	 * @param end the upper bound of the range of dealers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dealers
	 */
	@Override
	public List<Dealer> findAll(
		int start, int end, OrderByComparator<Dealer> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dealers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DealerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of dealers
	 * @param end the upper bound of the range of dealers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of dealers
	 */
	@Override
	public List<Dealer> findAll(
		int start, int end, OrderByComparator<Dealer> orderByComparator,
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

		List<Dealer> list = null;

		if (useFinderCache) {
			list = (List<Dealer>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DEALER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DEALER;

				sql = sql.concat(DealerModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Dealer>)QueryUtil.list(
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
	 * Removes all the dealers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Dealer dealer : findAll()) {
			remove(dealer);
		}
	}

	/**
	 * Returns the number of dealers.
	 *
	 * @return the number of dealers
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DEALER);

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
		return _SQL_SELECT_DEALER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DealerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the dealer persistence.
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

		_setDealerUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setDealerUtilPersistence(null);

		dummyEntityCache.removeCache(DealerImpl.class.getName());
	}

	private void _setDealerUtilPersistence(
		DealerPersistence dealerPersistence) {

		try {
			Field field = DealerUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, dealerPersistence);
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

	private static final String _SQL_SELECT_DEALER =
		"SELECT dealer FROM Dealer dealer";

	private static final String _SQL_COUNT_DEALER =
		"SELECT COUNT(dealer) FROM Dealer dealer";

	private static final String _ORDER_BY_ENTITY_ALIAS = "dealer.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Dealer exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		DealerPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}