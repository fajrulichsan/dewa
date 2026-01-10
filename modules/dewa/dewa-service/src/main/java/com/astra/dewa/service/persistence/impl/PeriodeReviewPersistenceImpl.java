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

import com.astra.dewa.exception.NoSuchPeriodeReviewException;
import com.astra.dewa.model.PeriodeReview;
import com.astra.dewa.model.PeriodeReviewTable;
import com.astra.dewa.model.impl.PeriodeReviewImpl;
import com.astra.dewa.model.impl.PeriodeReviewModelImpl;
import com.astra.dewa.service.persistence.PeriodeReviewPersistence;
import com.astra.dewa.service.persistence.PeriodeReviewUtil;
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
 * The persistence implementation for the periode review service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = PeriodeReviewPersistence.class)
public class PeriodeReviewPersistenceImpl
	extends BasePersistenceImpl<PeriodeReview>
	implements PeriodeReviewPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>PeriodeReviewUtil</code> to access the periode review persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		PeriodeReviewImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public PeriodeReviewPersistenceImpl() {
		setModelClass(PeriodeReview.class);

		setModelImplClass(PeriodeReviewImpl.class);
		setModelPKClass(String.class);

		setTable(PeriodeReviewTable.INSTANCE);
	}

	/**
	 * Caches the periode review in the entity cache if it is enabled.
	 *
	 * @param periodeReview the periode review
	 */
	@Override
	public void cacheResult(PeriodeReview periodeReview) {
		entityCache.putResult(
			PeriodeReviewImpl.class, periodeReview.getPrimaryKey(),
			periodeReview);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the periode reviews in the entity cache if it is enabled.
	 *
	 * @param periodeReviews the periode reviews
	 */
	@Override
	public void cacheResult(List<PeriodeReview> periodeReviews) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (periodeReviews.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (PeriodeReview periodeReview : periodeReviews) {
			if (entityCache.getResult(
					PeriodeReviewImpl.class, periodeReview.getPrimaryKey()) ==
						null) {

				cacheResult(periodeReview);
			}
		}
	}

	/**
	 * Clears the cache for all periode reviews.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PeriodeReviewImpl.class);

		finderCache.clearCache(PeriodeReviewImpl.class);
	}

	/**
	 * Clears the cache for the periode review.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PeriodeReview periodeReview) {
		entityCache.removeResult(PeriodeReviewImpl.class, periodeReview);
	}

	@Override
	public void clearCache(List<PeriodeReview> periodeReviews) {
		for (PeriodeReview periodeReview : periodeReviews) {
			entityCache.removeResult(PeriodeReviewImpl.class, periodeReview);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(PeriodeReviewImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(PeriodeReviewImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new periode review with the primary key. Does not add the periode review to the database.
	 *
	 * @param Id the primary key for the new periode review
	 * @return the new periode review
	 */
	@Override
	public PeriodeReview create(String Id) {
		PeriodeReview periodeReview = new PeriodeReviewImpl();

		periodeReview.setNew(true);
		periodeReview.setPrimaryKey(Id);

		return periodeReview;
	}

	/**
	 * Removes the periode review with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the periode review
	 * @return the periode review that was removed
	 * @throws NoSuchPeriodeReviewException if a periode review with the primary key could not be found
	 */
	@Override
	public PeriodeReview remove(String Id) throws NoSuchPeriodeReviewException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the periode review with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the periode review
	 * @return the periode review that was removed
	 * @throws NoSuchPeriodeReviewException if a periode review with the primary key could not be found
	 */
	@Override
	public PeriodeReview remove(Serializable primaryKey)
		throws NoSuchPeriodeReviewException {

		Session session = null;

		try {
			session = openSession();

			PeriodeReview periodeReview = (PeriodeReview)session.get(
				PeriodeReviewImpl.class, primaryKey);

			if (periodeReview == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPeriodeReviewException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(periodeReview);
		}
		catch (NoSuchPeriodeReviewException noSuchEntityException) {
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
	protected PeriodeReview removeImpl(PeriodeReview periodeReview) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(periodeReview)) {
				periodeReview = (PeriodeReview)session.get(
					PeriodeReviewImpl.class, periodeReview.getPrimaryKeyObj());
			}

			if (periodeReview != null) {
				session.delete(periodeReview);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (periodeReview != null) {
			clearCache(periodeReview);
		}

		return periodeReview;
	}

	@Override
	public PeriodeReview updateImpl(PeriodeReview periodeReview) {
		boolean isNew = periodeReview.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(periodeReview);
			}
			else {
				periodeReview = (PeriodeReview)session.merge(periodeReview);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			PeriodeReviewImpl.class, periodeReview, false, true);

		if (isNew) {
			periodeReview.setNew(false);
		}

		periodeReview.resetOriginalValues();

		return periodeReview;
	}

	/**
	 * Returns the periode review with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the periode review
	 * @return the periode review
	 * @throws NoSuchPeriodeReviewException if a periode review with the primary key could not be found
	 */
	@Override
	public PeriodeReview findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPeriodeReviewException {

		PeriodeReview periodeReview = fetchByPrimaryKey(primaryKey);

		if (periodeReview == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPeriodeReviewException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return periodeReview;
	}

	/**
	 * Returns the periode review with the primary key or throws a <code>NoSuchPeriodeReviewException</code> if it could not be found.
	 *
	 * @param Id the primary key of the periode review
	 * @return the periode review
	 * @throws NoSuchPeriodeReviewException if a periode review with the primary key could not be found
	 */
	@Override
	public PeriodeReview findByPrimaryKey(String Id)
		throws NoSuchPeriodeReviewException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the periode review with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the periode review
	 * @return the periode review, or <code>null</code> if a periode review with the primary key could not be found
	 */
	@Override
	public PeriodeReview fetchByPrimaryKey(String Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the periode reviews.
	 *
	 * @return the periode reviews
	 */
	@Override
	public List<PeriodeReview> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the periode reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PeriodeReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of periode reviews
	 * @param end the upper bound of the range of periode reviews (not inclusive)
	 * @return the range of periode reviews
	 */
	@Override
	public List<PeriodeReview> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the periode reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PeriodeReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of periode reviews
	 * @param end the upper bound of the range of periode reviews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of periode reviews
	 */
	@Override
	public List<PeriodeReview> findAll(
		int start, int end,
		OrderByComparator<PeriodeReview> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the periode reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PeriodeReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of periode reviews
	 * @param end the upper bound of the range of periode reviews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of periode reviews
	 */
	@Override
	public List<PeriodeReview> findAll(
		int start, int end, OrderByComparator<PeriodeReview> orderByComparator,
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

		List<PeriodeReview> list = null;

		if (useFinderCache) {
			list = (List<PeriodeReview>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PERIODEREVIEW);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PERIODEREVIEW;

				sql = sql.concat(PeriodeReviewModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<PeriodeReview>)QueryUtil.list(
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
	 * Removes all the periode reviews from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PeriodeReview periodeReview : findAll()) {
			remove(periodeReview);
		}
	}

	/**
	 * Returns the number of periode reviews.
	 *
	 * @return the number of periode reviews
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PERIODEREVIEW);

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
		return _SQL_SELECT_PERIODEREVIEW;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return PeriodeReviewModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the periode review persistence.
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

		_setPeriodeReviewUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setPeriodeReviewUtilPersistence(null);

		entityCache.removeCache(PeriodeReviewImpl.class.getName());
	}

	private void _setPeriodeReviewUtilPersistence(
		PeriodeReviewPersistence periodeReviewPersistence) {

		try {
			Field field = PeriodeReviewUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, periodeReviewPersistence);
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

	private static final String _SQL_SELECT_PERIODEREVIEW =
		"SELECT periodeReview FROM PeriodeReview periodeReview";

	private static final String _SQL_COUNT_PERIODEREVIEW =
		"SELECT COUNT(periodeReview) FROM PeriodeReview periodeReview";

	private static final String _ORDER_BY_ENTITY_ALIAS = "periodeReview.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No PeriodeReview exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		PeriodeReviewPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}