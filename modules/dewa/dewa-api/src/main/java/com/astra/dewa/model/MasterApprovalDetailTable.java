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
 * The table class for the &quot;master_approval_detail&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalDetail
 * @generated
 */
public class MasterApprovalDetailTable
	extends BaseTable<MasterApprovalDetailTable> {

	public static final MasterApprovalDetailTable INSTANCE =
		new MasterApprovalDetailTable();

	public final Column<MasterApprovalDetailTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<MasterApprovalDetailTable, Integer> MasterApprovalId =
		createColumn(
			"MasterApprovalId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailTable, Long> UserId = createColumn(
		"UserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailTable, Integer> ApprovalLevel =
		createColumn(
			"ApprovalLevel", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailTable, Boolean> IsDefault =
		createColumn(
			"IsDefault", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private MasterApprovalDetailTable() {
		super("master_approval_detail", MasterApprovalDetailTable::new);
	}

}