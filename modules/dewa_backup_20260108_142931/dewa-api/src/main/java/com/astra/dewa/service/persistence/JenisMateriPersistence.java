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

import com.astra.dewa.exception.NoSuchJenisMateriException;
import com.astra.dewa.model.JenisMateri;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the jenis materi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JenisMateriUtil
 * @generated
 */
@ProviderType
public interface JenisMateriPersistence extends BasePersistence<JenisMateri> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JenisMateriUtil} to access the jenis materi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the jenis materi in the entity cache if it is enabled.
	 *
	 * @param jenisMateri the jenis materi
	 */
	public void cacheResult(JenisMateri jenisMateri);

	/**
	 * Caches the jenis materis in the entity cache if it is enabled.
	 *
	 * @param jenisMateris the jenis materis
	 */
	public void cacheResult(java.util.List<JenisMateri> jenisMateris);

	/**
	 * Creates a new jenis materi with the primary key. Does not add the jenis materi to the database.
	 *
	 * @param Id the primary key for the new jenis materi
	 * @return the new jenis materi
	 */
	public JenisMateri create(int Id);

	/**
	 * Removes the jenis materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the jenis materi
	 * @return the jenis materi that was removed
	 * @throws NoSuchJenisMateriException if a jenis materi with the primary key could not be found
	 */
	public JenisMateri remove(int Id) throws NoSuchJenisMateriException;

	public JenisMateri updateImpl(JenisMateri jenisMateri);

	/**
	 * Returns the jenis materi with the primary key or throws a <code>NoSuchJenisMateriException</code> if it could not be found.
	 *
	 * @param Id the primary key of the jenis materi
	 * @return the jenis materi
	 * @throws NoSuchJenisMateriException if a jenis materi with the primary key could not be found
	 */
	public JenisMateri findByPrimaryKey(int Id)
		throws NoSuchJenisMateriException;

	/**
	 * Returns the jenis materi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the jenis materi
	 * @return the jenis materi, or <code>null</code> if a jenis materi with the primary key could not be found
	 */
	public JenisMateri fetchByPrimaryKey(int Id);

	/**
	 * Returns all the jenis materis.
	 *
	 * @return the jenis materis
	 */
	public java.util.List<JenisMateri> findAll();

	/**
	 * Returns a range of all the jenis materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JenisMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jenis materis
	 * @param end the upper bound of the range of jenis materis (not inclusive)
	 * @return the range of jenis materis
	 */
	public java.util.List<JenisMateri> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the jenis materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JenisMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jenis materis
	 * @param end the upper bound of the range of jenis materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jenis materis
	 */
	public java.util.List<JenisMateri> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JenisMateri>
			orderByComparator);

	/**
	 * Returns an ordered range of all the jenis materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JenisMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jenis materis
	 * @param end the upper bound of the range of jenis materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of jenis materis
	 */
	public java.util.List<JenisMateri> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JenisMateri>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the jenis materis from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of jenis materis.
	 *
	 * @return the number of jenis materis
	 */
	public int countAll();

}