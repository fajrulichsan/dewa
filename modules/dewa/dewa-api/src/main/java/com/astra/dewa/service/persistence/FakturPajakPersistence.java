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

import com.astra.dewa.exception.NoSuchFakturPajakException;
import com.astra.dewa.model.FakturPajak;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the faktur pajak service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FakturPajakUtil
 * @generated
 */
@ProviderType
public interface FakturPajakPersistence extends BasePersistence<FakturPajak> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FakturPajakUtil} to access the faktur pajak persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the faktur pajak in the entity cache if it is enabled.
	 *
	 * @param fakturPajak the faktur pajak
	 */
	public void cacheResult(FakturPajak fakturPajak);

	/**
	 * Caches the faktur pajaks in the entity cache if it is enabled.
	 *
	 * @param fakturPajaks the faktur pajaks
	 */
	public void cacheResult(java.util.List<FakturPajak> fakturPajaks);

	/**
	 * Creates a new faktur pajak with the primary key. Does not add the faktur pajak to the database.
	 *
	 * @param Id the primary key for the new faktur pajak
	 * @return the new faktur pajak
	 */
	public FakturPajak create(int Id);

	/**
	 * Removes the faktur pajak with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the faktur pajak
	 * @return the faktur pajak that was removed
	 * @throws NoSuchFakturPajakException if a faktur pajak with the primary key could not be found
	 */
	public FakturPajak remove(int Id) throws NoSuchFakturPajakException;

	public FakturPajak updateImpl(FakturPajak fakturPajak);

	/**
	 * Returns the faktur pajak with the primary key or throws a <code>NoSuchFakturPajakException</code> if it could not be found.
	 *
	 * @param Id the primary key of the faktur pajak
	 * @return the faktur pajak
	 * @throws NoSuchFakturPajakException if a faktur pajak with the primary key could not be found
	 */
	public FakturPajak findByPrimaryKey(int Id)
		throws NoSuchFakturPajakException;

	/**
	 * Returns the faktur pajak with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the faktur pajak
	 * @return the faktur pajak, or <code>null</code> if a faktur pajak with the primary key could not be found
	 */
	public FakturPajak fetchByPrimaryKey(int Id);

	/**
	 * Returns all the faktur pajaks.
	 *
	 * @return the faktur pajaks
	 */
	public java.util.List<FakturPajak> findAll();

	/**
	 * Returns a range of all the faktur pajaks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FakturPajakModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur pajaks
	 * @param end the upper bound of the range of faktur pajaks (not inclusive)
	 * @return the range of faktur pajaks
	 */
	public java.util.List<FakturPajak> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the faktur pajaks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FakturPajakModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur pajaks
	 * @param end the upper bound of the range of faktur pajaks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of faktur pajaks
	 */
	public java.util.List<FakturPajak> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FakturPajak>
			orderByComparator);

	/**
	 * Returns an ordered range of all the faktur pajaks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FakturPajakModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faktur pajaks
	 * @param end the upper bound of the range of faktur pajaks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of faktur pajaks
	 */
	public java.util.List<FakturPajak> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FakturPajak>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the faktur pajaks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of faktur pajaks.
	 *
	 * @return the number of faktur pajaks
	 */
	public int countAll();

}