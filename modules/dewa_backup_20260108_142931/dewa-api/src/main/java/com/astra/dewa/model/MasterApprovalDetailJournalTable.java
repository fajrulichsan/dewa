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
 * The table class for the &quot;master_approval_detail_journal&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalDetailJournal
 * @generated
 */
public class MasterApprovalDetailJournalTable
	extends BaseTable<MasterApprovalDetailJournalTable> {

	public static final MasterApprovalDetailJournalTable INSTANCE =
		new MasterApprovalDetailJournalTable();

	public final Column<MasterApprovalDetailJournalTable, Integer> Id =
		createColumn("Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<MasterApprovalDetailJournalTable, Integer>
		MasterApprovalId = createColumn(
			"MasterApprovalId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailJournalTable, Integer>
		MasterApprovalJournalId = createColumn(
			"MasterApprovalJournalId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailJournalTable, Integer>
		MasterApprovalDetailId = createColumn(
			"MasterApprovalDetailId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailJournalTable, Long> UserId =
		createColumn("UserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailJournalTable, Integer>
		ApprovalLevel = createColumn(
			"ApprovalLevel", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailJournalTable, Boolean> IsDefault =
		createColumn(
			"IsDefault", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailJournalTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailJournalTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailJournalTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailJournalTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalDetailJournalTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private MasterApprovalDetailJournalTable() {
		super(
			"master_approval_detail_journal",
			MasterApprovalDetailJournalTable::new);
	}

}