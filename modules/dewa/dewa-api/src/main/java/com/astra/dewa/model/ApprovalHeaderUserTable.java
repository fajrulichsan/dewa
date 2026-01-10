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
 * The table class for the &quot;ApprovalHeaderUser&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ApprovalHeaderUser
 * @generated
 */
public class ApprovalHeaderUserTable
	extends BaseTable<ApprovalHeaderUserTable> {

	public static final ApprovalHeaderUserTable INSTANCE =
		new ApprovalHeaderUserTable();

	public final Column<ApprovalHeaderUserTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<ApprovalHeaderUserTable, Integer>
		ApplicationAssignStatusId = createColumn(
			"ApplicationAssignStatusId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApprovalHeaderUserTable, Long> ApproverUserId =
		createColumn(
			"ApproverUserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ApprovalHeaderUserTable, Integer> DealerId =
		createColumn(
			"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApprovalHeaderUserTable, Integer> CabangId =
		createColumn(
			"CabangId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApprovalHeaderUserTable, String> RequesterName =
		createColumn(
			"RequesterName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApprovalHeaderUserTable, String> RequesterEmail =
		createColumn(
			"RequesterEmail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApprovalHeaderUserTable, String> RejectReason =
		createColumn(
			"RejectReason", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApprovalHeaderUserTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApprovalHeaderUserTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApprovalHeaderUserTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApprovalHeaderUserTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApprovalHeaderUserTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ApprovalHeaderUserTable() {
		super("ApprovalHeaderUser", ApprovalHeaderUserTable::new);
	}

}