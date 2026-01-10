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
 * The table class for the &quot;UserRoleType&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see UserRoleType
 * @generated
 */
public class UserRoleTypeTable extends BaseTable<UserRoleTypeTable> {

	public static final UserRoleTypeTable INSTANCE = new UserRoleTypeTable();

	public final Column<UserRoleTypeTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<UserRoleTypeTable, Long> UserId = createColumn(
		"UserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<UserRoleTypeTable, Integer> RoleId = createColumn(
		"RoleId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<UserRoleTypeTable, Boolean> RowStatus = createColumn(
		"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<UserRoleTypeTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<UserRoleTypeTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserRoleTypeTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<UserRoleTypeTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private UserRoleTypeTable() {
		super("UserRoleType", UserRoleTypeTable::new);
	}

}