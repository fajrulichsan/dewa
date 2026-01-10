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
 * The table class for the &quot;OTP&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see OTP
 * @generated
 */
public class OTPTable extends BaseTable<OTPTable> {

	public static final OTPTable INSTANCE = new OTPTable();

	public final Column<OTPTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<OTPTable, Integer> OTPNumber = createColumn(
		"OTPNumber", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<OTPTable, String> EmailNewUser = createColumn(
		"EmailNewUser", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<OTPTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<OTPTable, Date> ExpiredDate = createColumn(
		"ExpiredDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<OTPTable, Boolean> IsVerified = createColumn(
		"IsVerified", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private OTPTable() {
		super("OTP", OTPTable::new);
	}

}