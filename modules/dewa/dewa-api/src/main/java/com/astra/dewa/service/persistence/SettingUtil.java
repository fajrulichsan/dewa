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

package com.astra.dewa.service.persistence;

import com.astra.dewa.model.Setting;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the setting service. This utility wraps <code>com.astra.dewa.service.persistence.impl.SettingPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SettingPersistence
 * @generated
 */
public class SettingUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Setting setting) {
		getPersistence().clearCache(setting);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Setting> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Setting> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Setting> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Setting> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Setting> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Setting update(Setting setting) {
		return getPersistence().update(setting);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Setting update(
		Setting setting, ServiceContext serviceContext) {

		return getPersistence().update(setting, serviceContext);
	}

	/**
	 * Returns all the settings where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @return the matching settings
	 */
	public static List<Setting> findByMailKey(String KeySetting) {
		return getPersistence().findByMailKey(KeySetting);
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
	public static List<Setting> findByMailKey(
		String KeySetting, int start, int end) {

		return getPersistence().findByMailKey(KeySetting, start, end);
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
	public static List<Setting> findByMailKey(
		String KeySetting, int start, int end,
		OrderByComparator<Setting> orderByComparator) {

		return getPersistence().findByMailKey(
			KeySetting, start, end, orderByComparator);
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
	public static List<Setting> findByMailKey(
		String KeySetting, int start, int end,
		OrderByComparator<Setting> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByMailKey(
			KeySetting, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching setting
	 * @throws NoSuchSettingException if a matching setting could not be found
	 */
	public static Setting findByMailKey_First(
			String KeySetting, OrderByComparator<Setting> orderByComparator)
		throws com.astra.dewa.exception.NoSuchSettingException {

		return getPersistence().findByMailKey_First(
			KeySetting, orderByComparator);
	}

	/**
	 * Returns the first setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching setting, or <code>null</code> if a matching setting could not be found
	 */
	public static Setting fetchByMailKey_First(
		String KeySetting, OrderByComparator<Setting> orderByComparator) {

		return getPersistence().fetchByMailKey_First(
			KeySetting, orderByComparator);
	}

	/**
	 * Returns the last setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching setting
	 * @throws NoSuchSettingException if a matching setting could not be found
	 */
	public static Setting findByMailKey_Last(
			String KeySetting, OrderByComparator<Setting> orderByComparator)
		throws com.astra.dewa.exception.NoSuchSettingException {

		return getPersistence().findByMailKey_Last(
			KeySetting, orderByComparator);
	}

	/**
	 * Returns the last setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching setting, or <code>null</code> if a matching setting could not be found
	 */
	public static Setting fetchByMailKey_Last(
		String KeySetting, OrderByComparator<Setting> orderByComparator) {

		return getPersistence().fetchByMailKey_Last(
			KeySetting, orderByComparator);
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
	public static Setting[] findByMailKey_PrevAndNext(
			int Id, String KeySetting,
			OrderByComparator<Setting> orderByComparator)
		throws com.astra.dewa.exception.NoSuchSettingException {

		return getPersistence().findByMailKey_PrevAndNext(
			Id, KeySetting, orderByComparator);
	}

	/**
	 * Removes all the settings where KeySetting = &#63; from the database.
	 *
	 * @param KeySetting the key setting
	 */
	public static void removeByMailKey(String KeySetting) {
		getPersistence().removeByMailKey(KeySetting);
	}

	/**
	 * Returns the number of settings where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @return the number of matching settings
	 */
	public static int countByMailKey(String KeySetting) {
		return getPersistence().countByMailKey(KeySetting);
	}

	/**
	 * Returns the setting where KeySetting = &#63; and Code = &#63; or throws a <code>NoSuchSettingException</code> if it could not be found.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @return the matching setting
	 * @throws NoSuchSettingException if a matching setting could not be found
	 */
	public static Setting findByFindCredential(String KeySetting, String Code)
		throws com.astra.dewa.exception.NoSuchSettingException {

		return getPersistence().findByFindCredential(KeySetting, Code);
	}

	/**
	 * Returns the setting where KeySetting = &#63; and Code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @return the matching setting, or <code>null</code> if a matching setting could not be found
	 */
	public static Setting fetchByFindCredential(
		String KeySetting, String Code) {

		return getPersistence().fetchByFindCredential(KeySetting, Code);
	}

	/**
	 * Returns the setting where KeySetting = &#63; and Code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching setting, or <code>null</code> if a matching setting could not be found
	 */
	public static Setting fetchByFindCredential(
		String KeySetting, String Code, boolean useFinderCache) {

		return getPersistence().fetchByFindCredential(
			KeySetting, Code, useFinderCache);
	}

	/**
	 * Removes the setting where KeySetting = &#63; and Code = &#63; from the database.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @return the setting that was removed
	 */
	public static Setting removeByFindCredential(String KeySetting, String Code)
		throws com.astra.dewa.exception.NoSuchSettingException {

		return getPersistence().removeByFindCredential(KeySetting, Code);
	}

	/**
	 * Returns the number of settings where KeySetting = &#63; and Code = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @return the number of matching settings
	 */
	public static int countByFindCredential(String KeySetting, String Code) {
		return getPersistence().countByFindCredential(KeySetting, Code);
	}

	/**
	 * Caches the setting in the entity cache if it is enabled.
	 *
	 * @param setting the setting
	 */
	public static void cacheResult(Setting setting) {
		getPersistence().cacheResult(setting);
	}

	/**
	 * Caches the settings in the entity cache if it is enabled.
	 *
	 * @param settings the settings
	 */
	public static void cacheResult(List<Setting> settings) {
		getPersistence().cacheResult(settings);
	}

	/**
	 * Creates a new setting with the primary key. Does not add the setting to the database.
	 *
	 * @param Id the primary key for the new setting
	 * @return the new setting
	 */
	public static Setting create(int Id) {
		return getPersistence().create(Id);
	}

	/**
	 * Removes the setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the setting
	 * @return the setting that was removed
	 * @throws NoSuchSettingException if a setting with the primary key could not be found
	 */
	public static Setting remove(int Id)
		throws com.astra.dewa.exception.NoSuchSettingException {

		return getPersistence().remove(Id);
	}

	public static Setting updateImpl(Setting setting) {
		return getPersistence().updateImpl(setting);
	}

	/**
	 * Returns the setting with the primary key or throws a <code>NoSuchSettingException</code> if it could not be found.
	 *
	 * @param Id the primary key of the setting
	 * @return the setting
	 * @throws NoSuchSettingException if a setting with the primary key could not be found
	 */
	public static Setting findByPrimaryKey(int Id)
		throws com.astra.dewa.exception.NoSuchSettingException {

		return getPersistence().findByPrimaryKey(Id);
	}

	/**
	 * Returns the setting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the setting
	 * @return the setting, or <code>null</code> if a setting with the primary key could not be found
	 */
	public static Setting fetchByPrimaryKey(int Id) {
		return getPersistence().fetchByPrimaryKey(Id);
	}

	/**
	 * Returns all the settings.
	 *
	 * @return the settings
	 */
	public static List<Setting> findAll() {
		return getPersistence().findAll();
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
	public static List<Setting> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<Setting> findAll(
		int start, int end, OrderByComparator<Setting> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<Setting> findAll(
		int start, int end, OrderByComparator<Setting> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the settings from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of settings.
	 *
	 * @return the number of settings
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SettingPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SettingPersistence _persistence;

}