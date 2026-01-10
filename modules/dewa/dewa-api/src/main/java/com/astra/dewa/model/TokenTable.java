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

package com.astra.dewa.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Token&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Token
 * @generated
 */
public class TokenTable extends BaseTable<TokenTable> {

	public static final TokenTable INSTANCE = new TokenTable();

	public final Column<TokenTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<TokenTable, String> TokenNumber = createColumn(
		"TokenNumber", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TokenTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TokenTable, Date> ExpiredDate = createColumn(
		"ExpiredDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private TokenTable() {
		super("Token", TokenTable::new);
	}

}