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
 * The table class for the &quot;ApprovalHistoryUser&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ApprovalHistoryUser
 * @generated
 */
public class ApprovalHistoryUserTable
	extends BaseTable<ApprovalHistoryUserTable> {

	public static final ApprovalHistoryUserTable INSTANCE =
		new ApprovalHistoryUserTable();

	public final Column<ApprovalHistoryUserTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<ApprovalHistoryUserTable, Integer>
		ApprovalHeaderUserId = createColumn(
			"ApprovalHeaderUserId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApprovalHistoryUserTable, Integer>
		ApplicationAssignStatusId = createColumn(
			"ApplicationAssignStatusId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApprovalHistoryUserTable, Long> ApproverUserId =
		createColumn(
			"ApproverUserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ApprovalHistoryUserTable, Integer> DealerId =
		createColumn(
			"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApprovalHistoryUserTable, Integer> CabangId =
		createColumn(
			"CabangId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApprovalHistoryUserTable, String> RequesterName =
		createColumn(
			"RequesterName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApprovalHistoryUserTable, String> RequesterEmail =
		createColumn(
			"RequesterEmail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApprovalHistoryUserTable, String> RejectReason =
		createColumn(
			"RejectReason", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApprovalHistoryUserTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApprovalHistoryUserTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApprovalHistoryUserTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApprovalHistoryUserTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApprovalHistoryUserTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ApprovalHistoryUserTable() {
		super("ApprovalHistoryUser", ApprovalHistoryUserTable::new);
	}

}