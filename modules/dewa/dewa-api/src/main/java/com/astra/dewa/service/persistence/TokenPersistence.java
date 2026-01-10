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

import com.astra.dewa.exception.NoSuchTokenException;
import com.astra.dewa.model.Token;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TokenUtil
 * @generated
 */
@ProviderType
public interface TokenPersistence extends BasePersistence<Token> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TokenUtil} to access the token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the token in the entity cache if it is enabled.
	 *
	 * @param token the token
	 */
	public void cacheResult(Token token);

	/**
	 * Caches the tokens in the entity cache if it is enabled.
	 *
	 * @param tokens the tokens
	 */
	public void cacheResult(java.util.List<Token> tokens);

	/**
	 * Creates a new token with the primary key. Does not add the token to the database.
	 *
	 * @param Id the primary key for the new token
	 * @return the new token
	 */
	public Token create(int Id);

	/**
	 * Removes the token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the token
	 * @return the token that was removed
	 * @throws NoSuchTokenException if a token with the primary key could not be found
	 */
	public Token remove(int Id) throws NoSuchTokenException;

	public Token updateImpl(Token token);

	/**
	 * Returns the token with the primary key or throws a <code>NoSuchTokenException</code> if it could not be found.
	 *
	 * @param Id the primary key of the token
	 * @return the token
	 * @throws NoSuchTokenException if a token with the primary key could not be found
	 */
	public Token findByPrimaryKey(int Id) throws NoSuchTokenException;

	/**
	 * Returns the token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the token
	 * @return the token, or <code>null</code> if a token with the primary key could not be found
	 */
	public Token fetchByPrimaryKey(int Id);

	/**
	 * Returns all the tokens.
	 *
	 * @return the tokens
	 */
	public java.util.List<Token> findAll();

	/**
	 * Returns a range of all the tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tokens
	 * @param end the upper bound of the range of tokens (not inclusive)
	 * @return the range of tokens
	 */
	public java.util.List<Token> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tokens
	 * @param end the upper bound of the range of tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tokens
	 */
	public java.util.List<Token> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Token>
			orderByComparator);

	/**
	 * Returns an ordered range of all the tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tokens
	 * @param end the upper bound of the range of tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of tokens
	 */
	public java.util.List<Token> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Token>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the tokens from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of tokens.
	 *
	 * @return the number of tokens
	 */
	public int countAll();

}