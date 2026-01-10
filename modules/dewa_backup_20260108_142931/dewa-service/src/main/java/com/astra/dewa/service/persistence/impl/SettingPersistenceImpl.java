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

import com.astra.dewa.exception.NoSuchSettingException;
import com.astra.dewa.model.Setting;
import com.astra.dewa.model.SettingTable;
import com.astra.dewa.model.impl.SettingImpl;
import com.astra.dewa.model.impl.SettingModelImpl;
import com.astra.dewa.service.persistence.SettingPersistence;
import com.astra.dewa.service.persistence.SettingUtil;

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
 * The persistence implementation for the setting service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SettingPersistenceImpl
	extends BasePersistenceImpl<Setting> implements SettingPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SettingUtil</code> to access the setting persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SettingImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByMailKey;
	private FinderPath _finderPathWithoutPaginationFindByMailKey;
	private FinderPath _finderPathCountByMailKey;

	/**
	 * Returns all the settings where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @return the matching settings
	 */
	@Override
	public List<Setting> findByMailKey(String KeySetting) {
		return findByMailKey(
			KeySetting, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the settings where KeySetting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SettingModelImpl</code>.
	 * </p>
	 *
	 * @param KeySetting the key setting
	 * @param start the lower bound of the range of settings
	 * @param end the upper bound of the range of settings (not inclusive)
	 * @return the range of matching settings
	 */
	@Override
	public List<Setting> findByMailKey(String KeySetting, int start, int end) {
		return findByMailKey(KeySetting, start, end, null);
	}

	/**
	 * Returns an ordered range of all the settings where KeySetting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SettingModelImpl</code>.
	 * </p>
	 *
	 * @param KeySetting the key setting
	 * @param start the lower bound of the range of settings
	 * @param end the upper bound of the range of settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching settings
	 */
	@Override
	public List<Setting> findByMailKey(
		String KeySetting, int start, int end,
		OrderByComparator<Setting> orderByComparator) {

		return findByMailKey(KeySetting, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the settings where KeySetting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SettingModelImpl</code>.
	 * </p>
	 *
	 * @param KeySetting the key setting
	 * @param start the lower bound of the range of settings
	 * @param end the upper bound of the range of settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching settings
	 */
	@Override
	public List<Setting> findByMailKey(
		String KeySetting, int start, int end,
		OrderByComparator<Setting> orderByComparator, boolean useFinderCache) {

		KeySetting = Objects.toString(KeySetting, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByMailKey;
				finderArgs = new Object[] {KeySetting};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByMailKey;
			finderArgs = new Object[] {
				KeySetting, start, end, orderByComparator
			};
		}

		List<Setting> list = null;

		if (useFinderCache) {
			list = (List<Setting>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Setting setting : list) {
					if (!KeySetting.equals(setting.getKeySetting())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_SETTING_WHERE);

			boolean bindKeySetting = false;

			if (KeySetting.isEmpty()) {
				sb.append(_FINDER_COLUMN_MAILKEY_KEYSETTING_3);
			}
			else {
				bindKeySetting = true;

				sb.append(_FINDER_COLUMN_MAILKEY_KEYSETTING_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SettingModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindKeySetting) {
					queryPos.add(KeySetting);
				}

				list = (List<Setting>)QueryUtil.list(
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
	 * Returns the first setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching setting
	 * @throws NoSuchSettingException if a matching setting could not be found
	 */
	@Override
	public Setting findByMailKey_First(
			String KeySetting, OrderByComparator<Setting> orderByComparator)
		throws NoSuchSettingException {

		Setting setting = fetchByMailKey_First(KeySetting, orderByComparator);

		if (setting != null) {
			return setting;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("KeySetting=");
		sb.append(KeySetting);

		sb.append("}");

		throw new NoSuchSettingException(sb.toString());
	}

	/**
	 * Returns the first setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching setting, or <code>null</code> if a matching setting could not be found
	 */
	@Override
	public Setting fetchByMailKey_First(
		String KeySetting, OrderByComparator<Setting> orderByComparator) {

		List<Setting> list = findByMailKey(KeySetting, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching setting
	 * @throws NoSuchSettingException if a matching setting could not be found
	 */
	@Override
	public Setting findByMailKey_Last(
			String KeySetting, OrderByComparator<Setting> orderByComparator)
		throws NoSuchSettingException {

		Setting setting = fetchByMailKey_Last(KeySetting, orderByComparator);

		if (setting != null) {
			return setting;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("KeySetting=");
		sb.append(KeySetting);

		sb.append("}");

		throw new NoSuchSettingException(sb.toString());
	}

	/**
	 * Returns the last setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching setting, or <code>null</code> if a matching setting could not be found
	 */
	@Override
	public Setting fetchByMailKey_Last(
		String KeySetting, OrderByComparator<Setting> orderByComparator) {

		int count = countByMailKey(KeySetting);

		if (count == 0) {
			return null;
		}

		List<Setting> list = findByMailKey(
			KeySetting, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the settings before and after the current setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param Id the primary key of the current setting
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next setting
	 * @throws NoSuchSettingException if a setting with the primary key could not be found
	 */
	@Override
	public Setting[] findByMailKey_PrevAndNext(
			int Id, String KeySetting,
			OrderByComparator<Setting> orderByComparator)
		throws NoSuchSettingException {

		KeySetting = Objects.toString(KeySetting, "");

		Setting setting = findByPrimaryKey(Id);

		Session session = null;

		try {
			session = openSession();

			Setting[] array = new SettingImpl[3];

			array[0] = getByMailKey_PrevAndNext(
				session, setting, KeySetting, orderByComparator, true);

			array[1] = setting;

			array[2] = getByMailKey_PrevAndNext(
				session, setting, KeySetting, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Setting getByMailKey_PrevAndNext(
		Session session, Setting setting, String KeySetting,
		OrderByComparator<Setting> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SETTING_WHERE);

		boolean bindKeySetting = false;

		if (KeySetting.isEmpty()) {
			sb.append(_FINDER_COLUMN_MAILKEY_KEYSETTING_3);
		}
		else {
			bindKeySetting = true;

			sb.append(_FINDER_COLUMN_MAILKEY_KEYSETTING_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(SettingModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindKeySetting) {
			queryPos.add(KeySetting);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(setting)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Setting> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the settings where KeySetting = &#63; from the database.
	 *
	 * @param KeySetting the key setting
	 */
	@Override
	public void removeByMailKey(String KeySetting) {
		for (Setting setting :
				findByMailKey(
					KeySetting, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(setting);
		}
	}

	/**
	 * Returns the number of settings where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @return the number of matching settings
	 */
	@Override
	public int countByMailKey(String KeySetting) {
		KeySetting = Objects.toString(KeySetting, "");

		FinderPath finderPath = _finderPathCountByMailKey;

		Object[] finderArgs = new Object[] {KeySetting};

		Long count = (Long)dummyFinderCache.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SETTING_WHERE);

			boolean bindKeySetting = false;

			if (KeySetting.isEmpty()) {
				sb.append(_FINDER_COLUMN_MAILKEY_KEYSETTING_3);
			}
			else {
				bindKeySetting = true;

				sb.append(_FINDER_COLUMN_MAILKEY_KEYSETTING_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindKeySetting) {
					queryPos.add(KeySetting);
				}

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

	private static final String _FINDER_COLUMN_MAILKEY_KEYSETTING_2 =
		"setting.KeySetting = ?";

	private static final String _FINDER_COLUMN_MAILKEY_KEYSETTING_3 =
		"(setting.KeySetting IS NULL OR setting.KeySetting = '')";

	private FinderPath _finderPathFetchByFindCredential;
	private FinderPath _finderPathCountByFindCredential;

	/**
	 * Returns the setting where KeySetting = &#63; and Code = &#63; or throws a <code>NoSuchSettingException</code> if it could not be found.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @return the matching setting
	 * @throws NoSuchSettingException if a matching setting could not be found
	 */
	@Override
	public Setting findByFindCredential(String KeySetting, String Code)
		throws NoSuchSettingException {

		Setting setting = fetchByFindCredential(KeySetting, Code);

		if (setting == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("KeySetting=");
			sb.append(KeySetting);

			sb.append(", Code=");
			sb.append(Code);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchSettingException(sb.toString());
		}

		return setting;
	}

	/**
	 * Returns the setting where KeySetting = &#63; and Code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @return the matching setting, or <code>null</code> if a matching setting could not be found
	 */
	@Override
	public Setting fetchByFindCredential(String KeySetting, String Code) {
		return fetchByFindCredential(KeySetting, Code, true);
	}

	/**
	 * Returns the setting where KeySetting = &#63; and Code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching setting, or <code>null</code> if a matching setting could not be found
	 */
	@Override
	public Setting fetchByFindCredential(
		String KeySetting, String Code, boolean useFinderCache) {

		KeySetting = Objects.toString(KeySetting, "");
		Code = Objects.toString(Code, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {KeySetting, Code};
		}

		Object result = null;

		if (useFinderCache) {
			result = dummyFinderCache.getResult(
				_finderPathFetchByFindCredential, finderArgs, this);
		}

		if (result instanceof Setting) {
			Setting setting = (Setting)result;

			if (!Objects.equals(KeySetting, setting.getKeySetting()) ||
				!Objects.equals(Code, setting.getCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_SETTING_WHERE);

			boolean bindKeySetting = false;

			if (KeySetting.isEmpty()) {
				sb.append(_FINDER_COLUMN_FINDCREDENTIAL_KEYSETTING_3);
			}
			else {
				bindKeySetting = true;

				sb.append(_FINDER_COLUMN_FINDCREDENTIAL_KEYSETTING_2);
			}

			boolean bindCode = false;

			if (Code.isEmpty()) {
				sb.append(_FINDER_COLUMN_FINDCREDENTIAL_CODE_3);
			}
			else {
				bindCode = true;

				sb.append(_FINDER_COLUMN_FINDCREDENTIAL_CODE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindKeySetting) {
					queryPos.add(KeySetting);
				}

				if (bindCode) {
					queryPos.add(Code);
				}

				List<Setting> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						dummyFinderCache.putResult(
							_finderPathFetchByFindCredential, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {KeySetting, Code};
							}

							_log.warn(
								"SettingPersistenceImpl.fetchByFindCredential(String, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Setting setting = list.get(0);

					result = setting;

					cacheResult(setting);
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
			return (Setting)result;
		}
	}

	/**
	 * Removes the setting where KeySetting = &#63; and Code = &#63; from the database.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @return the setting that was removed
	 */
	@Override
	public Setting removeByFindCredential(String KeySetting, String Code)
		throws NoSuchSettingException {

		Setting setting = findByFindCredential(KeySetting, Code);

		return remove(setting);
	}

	/**
	 * Returns the number of settings where KeySetting = &#63; and Code = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @return the number of matching settings
	 */
	@Override
	public int countByFindCredential(String KeySetting, String Code) {
		KeySetting = Objects.toString(KeySetting, "");
		Code = Objects.toString(Code, "");

		FinderPath finderPath = _finderPathCountByFindCredential;

		Object[] finderArgs = new Object[] {KeySetting, Code};

		Long count = (Long)dummyFinderCache.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SETTING_WHERE);

			boolean bindKeySetting = false;

			if (KeySetting.isEmpty()) {
				sb.append(_FINDER_COLUMN_FINDCREDENTIAL_KEYSETTING_3);
			}
			else {
				bindKeySetting = true;

				sb.append(_FINDER_COLUMN_FINDCREDENTIAL_KEYSETTING_2);
			}

			boolean bindCode = false;

			if (Code.isEmpty()) {
				sb.append(_FINDER_COLUMN_FINDCREDENTIAL_CODE_3);
			}
			else {
				bindCode = true;

				sb.append(_FINDER_COLUMN_FINDCREDENTIAL_CODE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindKeySetting) {
					queryPos.add(KeySetting);
				}

				if (bindCode) {
					queryPos.add(Code);
				}

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

	private static final String _FINDER_COLUMN_FINDCREDENTIAL_KEYSETTING_2 =
		"setting.KeySetting = ? AND ";

	private static final String _FINDER_COLUMN_FINDCREDENTIAL_KEYSETTING_3 =
		"(setting.KeySetting IS NULL OR setting.KeySetting = '') AND ";

	private static final String _FINDER_COLUMN_FINDCREDENTIAL_CODE_2 =
		"setting.Code = ?";

	private static final String _FINDER_COLUMN_FINDCREDENTIAL_CODE_3 =
		"(setting.Code IS NULL OR setting.Code = '')";

	public SettingPersistenceImpl() {
		setModelClass(Setting.class);

		setModelImplClass(SettingImpl.class);
		setModelPKClass(int.class);

		setTable(SettingTable.INSTANCE);
	}

	/**
	 * Caches the setting in the entity cache if it is enabled.
	 *
	 * @param setting the setting
	 */
	@Override
	public void cacheResult(Setting setting) {
		dummyEntityCache.putResult(
			SettingImpl.class, setting.getPrimaryKey(), setting);

		dummyFinderCache.putResult(
			_finderPathFetchByFindCredential,
			new Object[] {setting.getKeySetting(), setting.getCode()}, setting);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the settings in the entity cache if it is enabled.
	 *
	 * @param settings the settings
	 */
	@Override
	public void cacheResult(List<Setting> settings) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (settings.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Setting setting : settings) {
			if (dummyEntityCache.getResult(
					SettingImpl.class, setting.getPrimaryKey()) == null) {

				cacheResult(setting);
			}
		}
	}

	/**
	 * Clears the cache for all settings.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(SettingImpl.class);

		dummyFinderCache.clearCache(SettingImpl.class);
	}

	/**
	 * Clears the cache for the setting.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Setting setting) {
		dummyEntityCache.removeResult(SettingImpl.class, setting);
	}

	@Override
	public void clearCache(List<Setting> settings) {
		for (Setting setting : settings) {
			dummyEntityCache.removeResult(SettingImpl.class, setting);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(SettingImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(SettingImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(SettingModelImpl settingModelImpl) {
		Object[] args = new Object[] {
			settingModelImpl.getKeySetting(), settingModelImpl.getCode()
		};

		dummyFinderCache.putResult(
			_finderPathCountByFindCredential, args, Long.valueOf(1));
		dummyFinderCache.putResult(
			_finderPathFetchByFindCredential, args, settingModelImpl);
	}

	/**
	 * Creates a new setting with the primary key. Does not add the setting to the database.
	 *
	 * @param Id the primary key for the new setting
	 * @return the new setting
	 */
	@Override
	public Setting create(int Id) {
		Setting setting = new SettingImpl();

		setting.setNew(true);
		setting.setPrimaryKey(Id);

		return setting;
	}

	/**
	 * Removes the setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the setting
	 * @return the setting that was removed
	 * @throws NoSuchSettingException if a setting with the primary key could not be found
	 */
	@Override
	public Setting remove(int Id) throws NoSuchSettingException {
		return remove((Serializable)Id);
	}

	/**
	 * Removes the setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the setting
	 * @return the setting that was removed
	 * @throws NoSuchSettingException if a setting with the primary key could not be found
	 */
	@Override
	public Setting remove(Serializable primaryKey)
		throws NoSuchSettingException {

		Session session = null;

		try {
			session = openSession();

			Setting setting = (Setting)session.get(
				SettingImpl.class, primaryKey);

			if (setting == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSettingException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(setting);
		}
		catch (NoSuchSettingException noSuchEntityException) {
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
	protected Setting removeImpl(Setting setting) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(setting)) {
				setting = (Setting)session.get(
					SettingImpl.class, setting.getPrimaryKeyObj());
			}

			if (setting != null) {
				session.delete(setting);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (setting != null) {
			clearCache(setting);
		}

		return setting;
	}

	@Override
	public Setting updateImpl(Setting setting) {
		boolean isNew = setting.isNew();

		if (!(setting instanceof SettingModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(setting.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(setting);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in setting proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Setting implementation " +
					setting.getClass());
		}

		SettingModelImpl settingModelImpl = (SettingModelImpl)setting;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(setting);
			}
			else {
				setting = (Setting)session.merge(setting);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			SettingImpl.class, settingModelImpl, false, true);

		cacheUniqueFindersCache(settingModelImpl);

		if (isNew) {
			setting.setNew(false);
		}

		setting.resetOriginalValues();

		return setting;
	}

	/**
	 * Returns the setting with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the setting
	 * @return the setting
	 * @throws NoSuchSettingException if a setting with the primary key could not be found
	 */
	@Override
	public Setting findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSettingException {

		Setting setting = fetchByPrimaryKey(primaryKey);

		if (setting == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSettingException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return setting;
	}

	/**
	 * Returns the setting with the primary key or throws a <code>NoSuchSettingException</code> if it could not be found.
	 *
	 * @param Id the primary key of the setting
	 * @return the setting
	 * @throws NoSuchSettingException if a setting with the primary key could not be found
	 */
	@Override
	public Setting findByPrimaryKey(int Id) throws NoSuchSettingException {
		return findByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns the setting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the setting
	 * @return the setting, or <code>null</code> if a setting with the primary key could not be found
	 */
	@Override
	public Setting fetchByPrimaryKey(int Id) {
		return fetchByPrimaryKey((Serializable)Id);
	}

	/**
	 * Returns all the settings.
	 *
	 * @return the settings
	 */
	@Override
	public List<Setting> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SettingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of settings
	 * @param end the upper bound of the range of settings (not inclusive)
	 * @return the range of settings
	 */
	@Override
	public List<Setting> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SettingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of settings
	 * @param end the upper bound of the range of settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of settings
	 */
	@Override
	public List<Setting> findAll(
		int start, int end, OrderByComparator<Setting> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SettingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of settings
	 * @param end the upper bound of the range of settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of settings
	 */
	@Override
	public List<Setting> findAll(
		int start, int end, OrderByComparator<Setting> orderByComparator,
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

		List<Setting> list = null;

		if (useFinderCache) {
			list = (List<Setting>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SETTING);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SETTING;

				sql = sql.concat(SettingModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Setting>)QueryUtil.list(
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
	 * Removes all the settings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Setting setting : findAll()) {
			remove(setting);
		}
	}

	/**
	 * Returns the number of settings.
	 *
	 * @return the number of settings
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SETTING);

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
		return _SQL_SELECT_SETTING;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SettingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the setting persistence.
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

		_finderPathWithPaginationFindByMailKey = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMailKey",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"KeySetting"}, true);

		_finderPathWithoutPaginationFindByMailKey = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMailKey",
			new String[] {String.class.getName()}, new String[] {"KeySetting"},
			true);

		_finderPathCountByMailKey = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMailKey",
			new String[] {String.class.getName()}, new String[] {"KeySetting"},
			false);

		_finderPathFetchByFindCredential = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByFindCredential",
			new String[] {String.class.getName(), String.class.getName()},
			new String[] {"KeySetting", "Code"}, true);

		_finderPathCountByFindCredential = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFindCredential",
			new String[] {String.class.getName(), String.class.getName()},
			new String[] {"KeySetting", "Code"}, false);

		_setSettingUtilPersistence(this);
	}

	public void destroy() {
		_setSettingUtilPersistence(null);

		dummyEntityCache.removeCache(SettingImpl.class.getName());
	}

	private void _setSettingUtilPersistence(
		SettingPersistence settingPersistence) {

		try {
			Field field = SettingUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, settingPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private static final String _SQL_SELECT_SETTING =
		"SELECT setting FROM Setting setting";

	private static final String _SQL_SELECT_SETTING_WHERE =
		"SELECT setting FROM Setting setting WHERE ";

	private static final String _SQL_COUNT_SETTING =
		"SELECT COUNT(setting) FROM Setting setting";

	private static final String _SQL_COUNT_SETTING_WHERE =
		"SELECT COUNT(setting) FROM Setting setting WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "setting.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Setting exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Setting exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SettingPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}