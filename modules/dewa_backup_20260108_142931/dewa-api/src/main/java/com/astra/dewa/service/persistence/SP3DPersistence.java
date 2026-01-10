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

import com.astra.dewa.exception.NoSuchSP3DException;
import com.astra.dewa.model.SP3D;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the sp3d service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SP3DUtil
 * @generated
 */
@ProviderType
public interface SP3DPersistence extends BasePersistence<SP3D> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SP3DUtil} to access the sp3d persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the sp3d in the entity cache if it is enabled.
	 *
	 * @param sp3d the sp3d
	 */
	public void cacheResult(SP3D sp3d);

	/**
	 * Caches the sp3ds in the entity cache if it is enabled.
	 *
	 * @param sp3ds the sp3ds
	 */
	public void cacheResult(java.util.List<SP3D> sp3ds);

	/**
	 * Creates a new sp3d with the primary key. Does not add the sp3d to the database.
	 *
	 * @param Id the primary key for the new sp3d
	 * @return the new sp3d
	 */
	public SP3D create(int Id);

	/**
	 * Removes the sp3d with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the sp3d
	 * @return the sp3d that was removed
	 * @throws NoSuchSP3DException if a sp3d with the primary key could not be found
	 */
	public SP3D remove(int Id) throws NoSuchSP3DException;

	public SP3D updateImpl(SP3D sp3d);

	/**
	 * Returns the sp3d with the primary key or throws a <code>NoSuchSP3DException</code> if it could not be found.
	 *
	 * @param Id the primary key of the sp3d
	 * @return the sp3d
	 * @throws NoSuchSP3DException if a sp3d with the primary key could not be found
	 */
	public SP3D findByPrimaryKey(int Id) throws NoSuchSP3DException;

	/**
	 * Returns the sp3d with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the sp3d
	 * @return the sp3d, or <code>null</code> if a sp3d with the primary key could not be found
	 */
	public SP3D fetchByPrimaryKey(int Id);

	/**
	 * Returns all the sp3ds.
	 *
	 * @return the sp3ds
	 */
	public java.util.List<SP3D> findAll();

	/**
	 * Returns a range of all the sp3ds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SP3DModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sp3ds
	 * @param end the upper bound of the range of sp3ds (not inclusive)
	 * @return the range of sp3ds
	 */
	public java.util.List<SP3D> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the sp3ds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SP3DModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sp3ds
	 * @param end the upper bound of the range of sp3ds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sp3ds
	 */
	public java.util.List<SP3D> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SP3D>
			orderByComparator);

	/**
	 * Returns an ordered range of all the sp3ds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SP3DModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sp3ds
	 * @param end the upper bound of the range of sp3ds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sp3ds
	 */
	public java.util.List<SP3D> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SP3D>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the sp3ds from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of sp3ds.
	 *
	 * @return the number of sp3ds
	 */
	public int countAll();

}