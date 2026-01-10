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

import com.astra.dewa.exception.NoSuchMenuAuthorizationException;
import com.astra.dewa.model.MenuAuthorization;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the menu authorization service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MenuAuthorizationUtil
 * @generated
 */
@ProviderType
public interface MenuAuthorizationPersistence
	extends BasePersistence<MenuAuthorization> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MenuAuthorizationUtil} to access the menu authorization persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the menu authorization in the entity cache if it is enabled.
	 *
	 * @param menuAuthorization the menu authorization
	 */
	public void cacheResult(MenuAuthorization menuAuthorization);

	/**
	 * Caches the menu authorizations in the entity cache if it is enabled.
	 *
	 * @param menuAuthorizations the menu authorizations
	 */
	public void cacheResult(
		java.util.List<MenuAuthorization> menuAuthorizations);

	/**
	 * Creates a new menu authorization with the primary key. Does not add the menu authorization to the database.
	 *
	 * @param Id the primary key for the new menu authorization
	 * @return the new menu authorization
	 */
	public MenuAuthorization create(int Id);

	/**
	 * Removes the menu authorization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the menu authorization
	 * @return the menu authorization that was removed
	 * @throws NoSuchMenuAuthorizationException if a menu authorization with the primary key could not be found
	 */
	public MenuAuthorization remove(int Id)
		throws NoSuchMenuAuthorizationException;

	public MenuAuthorization updateImpl(MenuAuthorization menuAuthorization);

	/**
	 * Returns the menu authorization with the primary key or throws a <code>NoSuchMenuAuthorizationException</code> if it could not be found.
	 *
	 * @param Id the primary key of the menu authorization
	 * @return the menu authorization
	 * @throws NoSuchMenuAuthorizationException if a menu authorization with the primary key could not be found
	 */
	public MenuAuthorization findByPrimaryKey(int Id)
		throws NoSuchMenuAuthorizationException;

	/**
	 * Returns the menu authorization with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the menu authorization
	 * @return the menu authorization, or <code>null</code> if a menu authorization with the primary key could not be found
	 */
	public MenuAuthorization fetchByPrimaryKey(int Id);

	/**
	 * Returns all the menu authorizations.
	 *
	 * @return the menu authorizations
	 */
	public java.util.List<MenuAuthorization> findAll();

	/**
	 * Returns a range of all the menu authorizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenuAuthorizationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of menu authorizations
	 * @param end the upper bound of the range of menu authorizations (not inclusive)
	 * @return the range of menu authorizations
	 */
	public java.util.List<MenuAuthorization> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the menu authorizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenuAuthorizationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of menu authorizations
	 * @param end the upper bound of the range of menu authorizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of menu authorizations
	 */
	public java.util.List<MenuAuthorization> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuAuthorization>
			orderByComparator);

	/**
	 * Returns an ordered range of all the menu authorizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenuAuthorizationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of menu authorizations
	 * @param end the upper bound of the range of menu authorizations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of menu authorizations
	 */
	public java.util.List<MenuAuthorization> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MenuAuthorization>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the menu authorizations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of menu authorizations.
	 *
	 * @return the number of menu authorizations
	 */
	public int countAll();

}