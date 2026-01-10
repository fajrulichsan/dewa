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

import com.astra.dewa.exception.NoSuchDiskonOtherException;
import com.astra.dewa.model.DiskonOther;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the diskon other service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiskonOtherUtil
 * @generated
 */
@ProviderType
public interface DiskonOtherPersistence extends BasePersistence<DiskonOther> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DiskonOtherUtil} to access the diskon other persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the diskon other in the entity cache if it is enabled.
	 *
	 * @param diskonOther the diskon other
	 */
	public void cacheResult(DiskonOther diskonOther);

	/**
	 * Caches the diskon others in the entity cache if it is enabled.
	 *
	 * @param diskonOthers the diskon others
	 */
	public void cacheResult(java.util.List<DiskonOther> diskonOthers);

	/**
	 * Creates a new diskon other with the primary key. Does not add the diskon other to the database.
	 *
	 * @param diskonOtherId the primary key for the new diskon other
	 * @return the new diskon other
	 */
	public DiskonOther create(long diskonOtherId);

	/**
	 * Removes the diskon other with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param diskonOtherId the primary key of the diskon other
	 * @return the diskon other that was removed
	 * @throws NoSuchDiskonOtherException if a diskon other with the primary key could not be found
	 */
	public DiskonOther remove(long diskonOtherId)
		throws NoSuchDiskonOtherException;

	public DiskonOther updateImpl(DiskonOther diskonOther);

	/**
	 * Returns the diskon other with the primary key or throws a <code>NoSuchDiskonOtherException</code> if it could not be found.
	 *
	 * @param diskonOtherId the primary key of the diskon other
	 * @return the diskon other
	 * @throws NoSuchDiskonOtherException if a diskon other with the primary key could not be found
	 */
	public DiskonOther findByPrimaryKey(long diskonOtherId)
		throws NoSuchDiskonOtherException;

	/**
	 * Returns the diskon other with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param diskonOtherId the primary key of the diskon other
	 * @return the diskon other, or <code>null</code> if a diskon other with the primary key could not be found
	 */
	public DiskonOther fetchByPrimaryKey(long diskonOtherId);

	/**
	 * Returns all the diskon others.
	 *
	 * @return the diskon others
	 */
	public java.util.List<DiskonOther> findAll();

	/**
	 * Returns a range of all the diskon others.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonOtherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon others
	 * @param end the upper bound of the range of diskon others (not inclusive)
	 * @return the range of diskon others
	 */
	public java.util.List<DiskonOther> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the diskon others.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonOtherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon others
	 * @param end the upper bound of the range of diskon others (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of diskon others
	 */
	public java.util.List<DiskonOther> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DiskonOther>
			orderByComparator);

	/**
	 * Returns an ordered range of all the diskon others.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DiskonOtherModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of diskon others
	 * @param end the upper bound of the range of diskon others (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of diskon others
	 */
	public java.util.List<DiskonOther> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DiskonOther>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the diskon others from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of diskon others.
	 *
	 * @return the number of diskon others
	 */
	public int countAll();

}