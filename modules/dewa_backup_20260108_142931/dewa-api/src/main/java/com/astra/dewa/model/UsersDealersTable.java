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
 * The table class for the &quot;UsersDealers&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see UsersDealers
 * @generated
 */
public class UsersDealersTable extends BaseTable<UsersDealersTable> {

	public static final UsersDealersTable INSTANCE = new UsersDealersTable();

	public final Column<UsersDealersTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<UsersDealersTable, Long> UserId = createColumn(
		"UserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<UsersDealersTable, Integer> DealerId = createColumn(
		"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<UsersDealersTable, Integer> RoleId = createColumn(
		"RoleId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<UsersDealersTable, Long> PhotoFileId = createColumn(
		"PhotoFileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<UsersDealersTable, String> ADB2CId = createColumn(
		"ADB2CId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UsersDealersTable, String> userPrincipalName =
		createColumn(
			"userPrincipalName", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<UsersDealersTable, String> FullName = createColumn(
		"FullName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UsersDealersTable, String> UserEmail = createColumn(
		"UserEmail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UsersDealersTable, Date> ApprovedDate = createColumn(
		"ApprovedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<UsersDealersTable, Boolean> RowStatus = createColumn(
		"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<UsersDealersTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<UsersDealersTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UsersDealersTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<UsersDealersTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private UsersDealersTable() {
		super("UsersDealers", UsersDealersTable::new);
	}

}