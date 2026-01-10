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

import com.astra.dewa.exception.NoSuchEmailDomainException;
import com.astra.dewa.model.EmailDomain;
import com.astra.dewa.model.EmailDomainTable;
import com.astra.dewa.model.impl.EmailDomainImpl;
import com.astra.dewa.model.impl.EmailDomainModelImpl;
import com.astra.dewa.service.persistence.EmailDomainPersistence;
import com.astra.dewa.service.persistence.EmailDomainUtil;
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
 * The persistence implementation for the email domain service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = EmailDomainPersistence.class)
public class EmailDomainPersistenceImpl
	extends BasePersistenceImpl<EmailDomain> implements EmailDomainPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EmailDomainUtil</code> to access the email domain persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EmailDomainImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public EmailDomainPersistenceImpl() {
		setModelClass(EmailDomain.class);

		setModelImplClass(EmailDomainImpl.class);
		setModelPKClass(int.class);

		setTable(EmailDomainTable.INSTANCE);
	}

	/**
	 * Caches the email domain in the entity cache if it is enabled.
	 *
	 * @param emailDomain the email domain
	 */
	@Override
	public void cacheResult(EmailDomain emailDomain) {
		dummyEntityCache.putResult(
			EmailDomainImpl.class, emailDomain.getPrimaryKey(), emailDomain);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the email domains in the entity cache if it is enabled.
	 *
	 * @param emailDomains the email domains
	 */
	@Override
	public void cacheResult(List<EmailDomain> emailDomains) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (emailDomains.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (EmailDomain emailDomain : emailDomains) {
			if (dummyEntityCache.getResult(
					EmailDomainImpl.class, emailDomain.getPrimaryKey()) ==
						null) {

				cacheResult(emailDomain);
			}
		}
	}

	/**
	 * Clears the cache for all email domains.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(EmailDomainImpl.class);

		dummyFinderCache.clearCache(EmailDomainImpl.class);
	}

	/**
	 * Clears the cache for the email domain.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EmailDomain emailDomain) {
		dummyEntityCache.removeResult(EmailDomainImpl.class, emailDomain);
	}

	@Override
	public void clearCache(List<EmailDomain> emailDomains) {
		for (EmailDomain emailDomain : emailDomains) {
			dummyEntityCache.removeResult(EmailDomainImpl.class, emailDomain);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(EmailDomainImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(EmailDomainImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new email domain with the primary key. Does not add the email domain to the database.
	 *
	 * @param Id the primary key for the new email domain
	 * @return the new email domain
	 */
	@Override
	public EmailDomain create(int Id) {
		EmailDomain emailDomain = new EmailDomainImpl();

		emailDomain.setNew(true);
		emailDomain.setPrimaryKey(Id);

		return emailDomain;
	}

	/**
	 * Removes the email domain with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the email domain
	 * @return the email domain that was removed
	 * @throws NoSuchEmailDomainException if a email domain with the primary key could not be found
	 */
	@Override
	public EmailDomain remove(int Id) throws NoSuchEmailDomainException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the email domain with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the email domain
	 * @return the email domain that was removed
	 * @throws NoSuchEmailDomainException if a email domain with the primary key could not be found
	 */
	@Override
	public EmailDomain remove(Serializable primaryKey)
		throws NoSuchEmailDomainException {

		Session session = null;

		try {
			session = openSession();

			EmailDomain emailDomain = (EmailDomain)session.get(
				EmailDomainImpl.class, primaryKey);

			if (emailDomain == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEmailDomainException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(emailDomain);
		}
		catch (NoSuchEmailDomainException noSuchEntityException) {
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
	protected EmailDomain removeImpl(EmailDomain emailDomain) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(emailDomain)) {
				emailDomain = (EmailDomain)session.get(
					EmailDomainImpl.class, emailDomain.getPrimaryKeyObj());
			}

			if (emailDomain != null) {
				session.delete(emailDomain);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (emailDomain != null) {
			clearCache(emailDomain);
		}

		return emailDomain;
	}

	@Override
	public EmailDomain updateImpl(EmailDomain emailDomain) {
		boolean isNew = emailDomain.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(emailDomain);
			}
			else {
				emailDomain = (EmailDomain)session.merge(emailDomain);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			EmailDomainImpl.class, emailDomain, false, true);

		if (isNew) {
			emailDomain.setNew(false);
		}

		emailDomain.resetOriginalValues();

		return emailDomain;
	}

	/**
	 * Returns the email domain with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the email domain
	 * @return the email domain
	 * @throws NoSuchEmailDomainException if a email domain with the primary key could not be found
	 */
	@Override
	public EmailDomain findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEmailDomainException {

		EmailDomain emailDomain = fetchByPrimaryKey(primaryKey);

		if (emailDomain == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEmailDomainException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return emailDomain;
	}

	/**
	 * Returns the email domain with the primary key or throws a <code>NoSuchEmailDomainException</code> if it could not be found.
	 *
	 * @param Id the primary key of the email domain
	 * @return the email domain
	 * @throws NoSuchEmailDomainException if a email domain with the primary key could not be found
	 */
	@Override
	public EmailDomain findByPrimaryKey(int Id)
		throws NoSuchEmailDomainException {

		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the email domain with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the email domain
	 * @return the email domain, or <code>null</code> if a email domain with the primary key could not be found
	 */
	@Override
	public EmailDomain fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the email domains.
	 *
	 * @return the email domains
	 */
	@Override
	public List<EmailDomain> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the email domains.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailDomainModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email domains
	 * @param end the upper bound of the range of email domains (not inclusive)
	 * @return the range of email domains
	 */
	@Override
	public List<EmailDomain> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the email domains.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailDomainModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email domains
	 * @param end the upper bound of the range of email domains (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of email domains
	 */
	@Override
	public List<EmailDomain> findAll(
		int start, int end, OrderByComparator<EmailDomain> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the email domains.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailDomainModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email domains
	 * @param end the upper bound of the range of email domains (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of email domains
	 */
	@Override
	public List<EmailDomain> findAll(
		int start, int end, OrderByComparator<EmailDomain> orderByComparator,
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

		List<EmailDomain> list = null;

		if (useFinderCache) {
			list = (List<EmailDomain>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_EMAILDOMAIN);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_EMAILDOMAIN;

				sql = sql.concat(EmailDomainModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<EmailDomain>)QueryUtil.list(
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
	 * Removes all the email domains from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EmailDomain emailDomain : findAll()) {
			remove(emailDomain);
		}
	}

	/**
	 * Returns the number of email domains.
	 *
	 * @return the number of email domains
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_EMAILDOMAIN);

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
		return _SQL_SELECT_EMAILDOMAIN;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EmailDomainModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the email domain persistence.
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

		_setEmailDomainUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setEmailDomainUtilPersistence(null);

		dummyEntityCache.removeCache(EmailDomainImpl.class.getName());
	}

	private void _setEmailDomainUtilPersistence(
		EmailDomainPersistence emailDomainPersistence) {

		try {
			Field field = EmailDomainUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, emailDomainPersistence);
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

	private static final String _SQL_SELECT_EMAILDOMAIN =
		"SELECT emailDomain FROM EmailDomain emailDomain";

	private static final String _SQL_COUNT_EMAILDOMAIN =
		"SELECT COUNT(emailDomain) FROM EmailDomain emailDomain";

	private static final String _ORDER_BY_ENTITY_ALIAS = "emailDomain.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No EmailDomain exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		EmailDomainPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}