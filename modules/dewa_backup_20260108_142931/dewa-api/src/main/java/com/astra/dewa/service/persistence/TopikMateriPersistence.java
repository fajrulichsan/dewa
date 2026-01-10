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

import com.astra.dewa.exception.NoSuchTopikMateriException;
import com.astra.dewa.model.TopikMateri;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the topik materi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TopikMateriUtil
 * @generated
 */
@ProviderType
public interface TopikMateriPersistence extends BasePersistence<TopikMateri> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TopikMateriUtil} to access the topik materi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the topik materi in the entity cache if it is enabled.
	 *
	 * @param topikMateri the topik materi
	 */
	public void cacheResult(TopikMateri topikMateri);

	/**
	 * Caches the topik materis in the entity cache if it is enabled.
	 *
	 * @param topikMateris the topik materis
	 */
	public void cacheResult(java.util.List<TopikMateri> topikMateris);

	/**
	 * Creates a new topik materi with the primary key. Does not add the topik materi to the database.
	 *
	 * @param Id the primary key for the new topik materi
	 * @return the new topik materi
	 */
	public TopikMateri create(int Id);

	/**
	 * Removes the topik materi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the topik materi
	 * @return the topik materi that was removed
	 * @throws NoSuchTopikMateriException if a topik materi with the primary key could not be found
	 */
	public TopikMateri remove(int Id) throws NoSuchTopikMateriException;

	public TopikMateri updateImpl(TopikMateri topikMateri);

	/**
	 * Returns the topik materi with the primary key or throws a <code>NoSuchTopikMateriException</code> if it could not be found.
	 *
	 * @param Id the primary key of the topik materi
	 * @return the topik materi
	 * @throws NoSuchTopikMateriException if a topik materi with the primary key could not be found
	 */
	public TopikMateri findByPrimaryKey(int Id)
		throws NoSuchTopikMateriException;

	/**
	 * Returns the topik materi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the topik materi
	 * @return the topik materi, or <code>null</code> if a topik materi with the primary key could not be found
	 */
	public TopikMateri fetchByPrimaryKey(int Id);

	/**
	 * Returns all the topik materis.
	 *
	 * @return the topik materis
	 */
	public java.util.List<TopikMateri> findAll();

	/**
	 * Returns a range of all the topik materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TopikMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of topik materis
	 * @param end the upper bound of the range of topik materis (not inclusive)
	 * @return the range of topik materis
	 */
	public java.util.List<TopikMateri> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the topik materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TopikMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of topik materis
	 * @param end the upper bound of the range of topik materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of topik materis
	 */
	public java.util.List<TopikMateri> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TopikMateri>
			orderByComparator);

	/**
	 * Returns an ordered range of all the topik materis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TopikMateriModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of topik materis
	 * @param end the upper bound of the range of topik materis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of topik materis
	 */
	public java.util.List<TopikMateri> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TopikMateri>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the topik materis from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of topik materis.
	 *
	 * @return the number of topik materis
	 */
	public int countAll();

}