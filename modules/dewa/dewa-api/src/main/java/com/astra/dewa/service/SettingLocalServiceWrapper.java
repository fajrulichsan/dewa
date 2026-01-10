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

package com.astra.dewa.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SettingLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SettingLocalService
 * @generated
 */
public class SettingLocalServiceWrapper
	implements ServiceWrapper<SettingLocalService>, SettingLocalService {

	public SettingLocalServiceWrapper() {
		this(null);
	}

	public SettingLocalServiceWrapper(SettingLocalService settingLocalService) {
		_settingLocalService = settingLocalService;
	}

	/**
	 * Adds the setting to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SettingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param setting the setting
	 * @return the setting that was added
	 */
	@Override
	public com.astra.dewa.model.Setting addSetting(
		com.astra.dewa.model.Setting setting) {

		return _settingLocalService.addSetting(setting);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _settingLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new setting with the primary key. Does not add the setting to the database.
	 *
	 * @param Id the primary key for the new setting
	 * @return the new setting
	 */
	@Override
	public com.astra.dewa.model.Setting createSetting(int Id) {
		return _settingLocalService.createSetting(Id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _settingLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SettingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param Id the primary key of the setting
	 * @return the setting that was removed
	 * @throws PortalException if a setting with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.Setting deleteSetting(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _settingLocalService.deleteSetting(Id);
	}

	/**
	 * Deletes the setting from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SettingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param setting the setting
	 * @return the setting that was removed
	 */
	@Override
	public com.astra.dewa.model.Setting deleteSetting(
		com.astra.dewa.model.Setting setting) {

		return _settingLocalService.deleteSetting(setting);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _settingLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _settingLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _settingLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _settingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.SettingModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _settingLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.SettingModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _settingLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _settingLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _settingLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.astra.dewa.model.Setting fetchSetting(int Id) {
		return _settingLocalService.fetchSetting(Id);
	}

	@Override
	public com.astra.dewa.model.Setting findCredential(
			String KeySetting, String code)
		throws com.astra.dewa.exception.NoSuchSettingException {

		return _settingLocalService.findCredential(KeySetting, code);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _settingLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _settingLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.astra.dewa.model.Setting> getMailKey(
			String KeySetting)
		throws com.astra.dewa.exception.NoSuchSettingException {

		return _settingLocalService.getMailKey(KeySetting);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _settingLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _settingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the setting with the primary key.
	 *
	 * @param Id the primary key of the setting
	 * @return the setting
	 * @throws PortalException if a setting with the primary key could not be found
	 */
	@Override
	public com.astra.dewa.model.Setting getSetting(int Id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _settingLocalService.getSetting(Id);
	}

	/**
	 * Returns a range of all the settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.astra.dewa.model.impl.SettingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of settings
	 * @param end the upper bound of the range of settings (not inclusive)
	 * @return the range of settings
	 */
	@Override
	public java.util.List<com.astra.dewa.model.Setting> getSettings(
		int start, int end) {

		return _settingLocalService.getSettings(start, end);
	}

	/**
	 * Returns the number of settings.
	 *
	 * @return the number of settings
	 */
	@Override
	public int getSettingsCount() {
		return _settingLocalService.getSettingsCount();
	}

	/**
	 * Updates the setting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SettingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param setting the setting
	 * @return the setting that was updated
	 */
	@Override
	public com.astra.dewa.model.Setting updateSetting(
		com.astra.dewa.model.Setting setting) {

		return _settingLocalService.updateSetting(setting);
	}

	@Override
	public SettingLocalService getWrappedService() {
		return _settingLocalService;
	}

	@Override
	public void setWrappedService(SettingLocalService settingLocalService) {
		_settingLocalService = settingLocalService;
	}

	private SettingLocalService _settingLocalService;

}