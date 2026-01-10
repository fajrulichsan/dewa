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

import com.astra.dewa.exception.NoSuchPeriodeReviewException;
import com.astra.dewa.model.PeriodeReview;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the periode review service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PeriodeReviewUtil
 * @generated
 */
@ProviderType
public interface PeriodeReviewPersistence
	extends BasePersistence<PeriodeReview> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PeriodeReviewUtil} to access the periode review persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the periode review in the entity cache if it is enabled.
	 *
	 * @param periodeReview the periode review
	 */
	public void cacheResult(PeriodeReview periodeReview);

	/**
	 * Caches the periode reviews in the entity cache if it is enabled.
	 *
	 * @param periodeReviews the periode reviews
	 */
	public void cacheResult(java.util.List<PeriodeReview> periodeReviews);

	/**
	 * Creates a new periode review with the primary key. Does not add the periode review to the database.
	 *
	 * @param Id the primary key for the new periode review
	 * @return the new periode review
	 */
	public PeriodeReview create(String Id);

	/**
	 * Removes the periode review with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the periode review
	 * @return the periode review that was removed
	 * @throws NoSuchPeriodeReviewException if a periode review with the primary key could not be found
	 */
	public PeriodeReview remove(String Id) throws NoSuchPeriodeReviewException;

	public PeriodeReview updateImpl(PeriodeReview periodeReview);

	/**
	 * Returns the periode review with the primary key or throws a <code>NoSuchPeriodeReviewException</code> if it could not be found.
	 *
	 * @param Id the primary key of the periode review
	 * @return the periode review
	 * @throws NoSuchPeriodeReviewException if a periode review with the primary key could not be found
	 */
	public PeriodeReview findByPrimaryKey(String Id)
		throws NoSuchPeriodeReviewException;

	/**
	 * Returns the periode review with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the periode review
	 * @return the periode review, or <code>null</code> if a periode review with the primary key could not be found
	 */
	public PeriodeReview fetchByPrimaryKey(String Id);

	/**
	 * Returns all the periode reviews.
	 *
	 * @return the periode reviews
	 */
	public java.util.List<PeriodeReview> findAll();

	/**
	 * Returns a range of all the periode reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PeriodeReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of periode reviews
	 * @param end the upper bound of the range of periode reviews (not inclusive)
	 * @return the range of periode reviews
	 */
	public java.util.List<PeriodeReview> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the periode reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PeriodeReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of periode reviews
	 * @param end the upper bound of the range of periode reviews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of periode reviews
	 */
	public java.util.List<PeriodeReview> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PeriodeReview>
			orderByComparator);

	/**
	 * Returns an ordered range of all the periode reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PeriodeReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of periode reviews
	 * @param end the upper bound of the range of periode reviews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of periode reviews
	 */
	public java.util.List<PeriodeReview> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PeriodeReview>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the periode reviews from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of periode reviews.
	 *
	 * @return the number of periode reviews
	 */
	public int countAll();

}