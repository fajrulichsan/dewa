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
 * The table class for the &quot;ApprovalDetailUser&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ApprovalDetailUser
 * @generated
 */
public class ApprovalDetailUserTable
	extends BaseTable<ApprovalDetailUserTable> {

	public static final ApprovalDetailUserTable INSTANCE =
		new ApprovalDetailUserTable();

	public final Column<ApprovalDetailUserTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<ApprovalDetailUserTable, Integer> ApprovalHeaderUserId =
		createColumn(
			"ApprovalHeaderUserId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApprovalDetailUserTable, Integer> RoleId = createColumn(
		"RoleId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApprovalDetailUserTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApprovalDetailUserTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApprovalDetailUserTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApprovalDetailUserTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApprovalDetailUserTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ApprovalDetailUserTable() {
		super("ApprovalDetailUser", ApprovalDetailUserTable::new);
	}

}