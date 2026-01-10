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

import com.astra.dewa.exception.NoSuchSettingException;
import com.astra.dewa.model.Setting;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the setting service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SettingUtil
 * @generated
 */
@ProviderType
public interface SettingPersistence extends BasePersistence<Setting> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SettingUtil} to access the setting persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the settings where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @return the matching settings
	 */
	public java.util.List<Setting> findByMailKey(String KeySetting);

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
	public java.util.List<Setting> findByMailKey(
		String KeySetting, int start, int end);

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
	public java.util.List<Setting> findByMailKey(
		String KeySetting, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Setting>
			orderByComparator);

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
	public java.util.List<Setting> findByMailKey(
		String KeySetting, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Setting>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching setting
	 * @throws NoSuchSettingException if a matching setting could not be found
	 */
	public Setting findByMailKey_First(
			String KeySetting,
			com.liferay.portal.kernel.util.OrderByComparator<Setting>
				orderByComparator)
		throws NoSuchSettingException;

	/**
	 * Returns the first setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching setting, or <code>null</code> if a matching setting could not be found
	 */
	public Setting fetchByMailKey_First(
		String KeySetting,
		com.liferay.portal.kernel.util.OrderByComparator<Setting>
			orderByComparator);

	/**
	 * Returns the last setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching setting
	 * @throws NoSuchSettingException if a matching setting could not be found
	 */
	public Setting findByMailKey_Last(
			String KeySetting,
			com.liferay.portal.kernel.util.OrderByComparator<Setting>
				orderByComparator)
		throws NoSuchSettingException;

	/**
	 * Returns the last setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching setting, or <code>null</code> if a matching setting could not be found
	 */
	public Setting fetchByMailKey_Last(
		String KeySetting,
		com.liferay.portal.kernel.util.OrderByComparator<Setting>
			orderByComparator);

	/**
	 * Returns the settings before and after the current setting in the ordered set where KeySetting = &#63;.
	 *
	 * @param Id the primary key of the current setting
	 * @param KeySetting the key setting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next setting
	 * @throws NoSuchSettingException if a setting with the primary key could not be found
	 */
	public Setting[] findByMailKey_PrevAndNext(
			int Id, String KeySetting,
			com.liferay.portal.kernel.util.OrderByComparator<Setting>
				orderByComparator)
		throws NoSuchSettingException;

	/**
	 * Removes all the settings where KeySetting = &#63; from the database.
	 *
	 * @param KeySetting the key setting
	 */
	public void removeByMailKey(String KeySetting);

	/**
	 * Returns the number of settings where KeySetting = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @return the number of matching settings
	 */
	public int countByMailKey(String KeySetting);

	/**
	 * Returns the setting where KeySetting = &#63; and Code = &#63; or throws a <code>NoSuchSettingException</code> if it could not be found.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @return the matching setting
	 * @throws NoSuchSettingException if a matching setting could not be found
	 */
	public Setting findByFindCredential(String KeySetting, String Code)
		throws NoSuchSettingException;

	/**
	 * Returns the setting where KeySetting = &#63; and Code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @return the matching setting, or <code>null</code> if a matching setting could not be found
	 */
	public Setting fetchByFindCredential(String KeySetting, String Code);

	/**
	 * Returns the setting where KeySetting = &#63; and Code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching setting, or <code>null</code> if a matching setting could not be found
	 */
	public Setting fetchByFindCredential(
		String KeySetting, String Code, boolean useFinderCache);

	/**
	 * Removes the setting where KeySetting = &#63; and Code = &#63; from the database.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @return the setting that was removed
	 */
	public Setting removeByFindCredential(String KeySetting, String Code)
		throws NoSuchSettingException;

	/**
	 * Returns the number of settings where KeySetting = &#63; and Code = &#63;.
	 *
	 * @param KeySetting the key setting
	 * @param Code the code
	 * @return the number of matching settings
	 */
	public int countByFindCredential(String KeySetting, String Code);

	/**
	 * Caches the setting in the entity cache if it is enabled.
	 *
	 * @param setting the setting
	 */
	public void cacheResult(Setting setting);

	/**
	 * Caches the settings in the entity cache if it is enabled.
	 *
	 * @param settings the settings
	 */
	public void cacheResult(java.util.List<Setting> settings);

	/**
	 * Creates a new setting with the primary key. Does not add the setting to the database.
	 *
	 * @param Id the primary key for the new setting
	 * @return the new setting
	 */
	public Setting create(int Id);

	/**
	 * Removes the setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the setting
	 * @return the setting that was removed
	 * @throws NoSuchSettingException if a setting with the primary key could not be found
	 */
	public Setting remove(int Id) throws NoSuchSettingException;

	public Setting updateImpl(Setting setting);

	/**
	 * Returns the setting with the primary key or throws a <code>NoSuchSettingException</code> if it could not be found.
	 *
	 * @param Id the primary key of the setting
	 * @return the setting
	 * @throws NoSuchSettingException if a setting with the primary key could not be found
	 */
	public Setting findByPrimaryKey(int Id) throws NoSuchSettingException;

	/**
	 * Returns the setting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the setting
	 * @return the setting, or <code>null</code> if a setting with the primary key could not be found
	 */
	public Setting fetchByPrimaryKey(int Id);

	/**
	 * Returns all the settings.
	 *
	 * @return the settings
	 */
	public java.util.List<Setting> findAll();

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
	public java.util.List<Setting> findAll(int start, int end);

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
	public java.util.List<Setting> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Setting>
			orderByComparator);

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
	public java.util.List<Setting> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Setting>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the settings from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of settings.
	 *
	 * @return the number of settings
	 */
	public int countAll();

}