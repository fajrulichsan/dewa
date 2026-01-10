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

import com.astra.dewa.exception.NoSuchBannerException;
import com.astra.dewa.model.Banner;
import com.astra.dewa.model.BannerTable;
import com.astra.dewa.model.impl.BannerImpl;
import com.astra.dewa.model.impl.BannerModelImpl;
import com.astra.dewa.service.persistence.BannerPersistence;
import com.astra.dewa.service.persistence.BannerUtil;

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
 * The persistence implementation for the banner service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class BannerPersistenceImpl
	extends BasePersistenceImpl<Banner> implements BannerPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>BannerUtil</code> to access the banner persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		BannerImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public BannerPersistenceImpl() {
		setModelClass(Banner.class);

		setModelImplClass(BannerImpl.class);
		setModelPKClass(int.class);

		setTable(BannerTable.INSTANCE);
	}

	/**
	 * Caches the banner in the entity cache if it is enabled.
	 *
	 * @param banner the banner
	 */
	@Override
	public void cacheResult(Banner banner) {
		dummyEntityCache.putResult(
			BannerImpl.class, banner.getPrimaryKey(), banner);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the banners in the entity cache if it is enabled.
	 *
	 * @param banners the banners
	 */
	@Override
	public void cacheResult(List<Banner> banners) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (banners.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Banner banner : banners) {
			if (dummyEntityCache.getResult(
					BannerImpl.class, banner.getPrimaryKey()) == null) {

				cacheResult(banner);
			}
		}
	}

	/**
	 * Clears the cache for all banners.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(BannerImpl.class);

		dummyFinderCache.clearCache(BannerImpl.class);
	}

	/**
	 * Clears the cache for the banner.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Banner banner) {
		dummyEntityCache.removeResult(BannerImpl.class, banner);
	}

	@Override
	public void clearCache(List<Banner> banners) {
		for (Banner banner : banners) {
			dummyEntityCache.removeResult(BannerImpl.class, banner);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(BannerImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(BannerImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new banner with the primary key. Does not add the banner to the database.
	 *
	 * @param Id the primary key for the new banner
	 * @return the new banner
	 */
	@Override
	public Banner create(int Id) {
		Banner banner = new BannerImpl();

		banner.setNew(true);
		banner.setPrimaryKey(Id);

		return banner;
	}

	/**
	 * Removes the banner with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the banner
	 * @return the banner that was removed
	 * @throws NoSuchBannerException if a banner with the primary key could not be found
	 */
	@Override
	public Banner remove(int Id) throws NoSuchBannerException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the banner with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the banner
	 * @return the banner that was removed
	 * @throws NoSuchBannerException if a banner with the primary key could not be found
	 */
	@Override
	public Banner remove(Serializable primaryKey) throws NoSuchBannerException {
		Session session = null;

		try {
			session = openSession();

			Banner banner = (Banner)session.get(BannerImpl.class, primaryKey);

			if (banner == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBannerException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(banner);
		}
		catch (NoSuchBannerException noSuchEntityException) {
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
	protected Banner removeImpl(Banner banner) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(banner)) {
				banner = (Banner)session.get(
					BannerImpl.class, banner.getPrimaryKeyObj());
			}

			if (banner != null) {
				session.delete(banner);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (banner != null) {
			clearCache(banner);
		}

		return banner;
	}

	@Override
	public Banner updateImpl(Banner banner) {
		boolean isNew = banner.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(banner);
			}
			else {
				banner = (Banner)session.merge(banner);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(BannerImpl.class, banner, false, true);

		if (isNew) {
			banner.setNew(false);
		}

		banner.resetOriginalValues();

		return banner;
	}

	/**
	 * Returns the banner with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the banner
	 * @return the banner
	 * @throws NoSuchBannerException if a banner with the primary key could not be found
	 */
	@Override
	public Banner findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBannerException {

		Banner banner = fetchByPrimaryKey(primaryKey);

		if (banner == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBannerException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return banner;
	}

	/**
	 * Returns the banner with the primary key or throws a <code>NoSuchBannerException</code> if it could not be found.
	 *
	 * @param Id the primary key of the banner
	 * @return the banner
	 * @throws NoSuchBannerException if a banner with the primary key could not be found
	 */
	@Override
	public Banner findByPrimaryKey(int Id) throws NoSuchBannerException {
		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the banner with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the banner
	 * @return the banner, or <code>null</code> if a banner with the primary key could not be found
	 */
	@Override
	public Banner fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the banners.
	 *
	 * @return the banners
	 */
	@Override
	public List<Banner> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the banners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BannerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of banners
	 * @param end the upper bound of the range of banners (not inclusive)
	 * @return the range of banners
	 */
	@Override
	public List<Banner> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the banners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BannerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of banners
	 * @param end the upper bound of the range of banners (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of banners
	 */
	@Override
	public List<Banner> findAll(
		int start, int end, OrderByComparator<Banner> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the banners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BannerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of banners
	 * @param end the upper bound of the range of banners (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of banners
	 */
	@Override
	public List<Banner> findAll(
		int start, int end, OrderByComparator<Banner> orderByComparator,
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

		List<Banner> list = null;

		if (useFinderCache) {
			list = (List<Banner>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_BANNER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_BANNER;

				sql = sql.concat(BannerModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Banner>)QueryUtil.list(
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
	 * Removes all the banners from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Banner banner : findAll()) {
			remove(banner);
		}
	}

	/**
	 * Returns the number of banners.
	 *
	 * @return the number of banners
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_BANNER);

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
		return _SQL_SELECT_BANNER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return BannerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the banner persistence.
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

		_setBannerUtilPersistence(this);
	}

	public void destroy() {
		_setBannerUtilPersistence(null);

		dummyEntityCache.removeCache(BannerImpl.class.getName());
	}

	private void _setBannerUtilPersistence(
		BannerPersistence bannerPersistence) {

		try {
			Field field = BannerUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, bannerPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private static final String _SQL_SELECT_BANNER =
		"SELECT banner FROM Banner banner";

	private static final String _SQL_COUNT_BANNER =
		"SELECT COUNT(banner) FROM Banner banner";

	private static final String _ORDER_BY_ENTITY_ALIAS = "banner.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Banner exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		BannerPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}