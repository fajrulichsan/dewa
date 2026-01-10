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
 * The table class for the &quot;master_approval_journal&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see MasterApprovalJournal
 * @generated
 */
public class MasterApprovalJournalTable
	extends BaseTable<MasterApprovalJournalTable> {

	public static final MasterApprovalJournalTable INSTANCE =
		new MasterApprovalJournalTable();

	public final Column<MasterApprovalJournalTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<MasterApprovalJournalTable, Integer> MasterApprovalId =
		createColumn(
			"MasterApprovalId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<MasterApprovalJournalTable, Integer> RoleId =
		createColumn(
			"RoleId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalJournalTable, Integer> MenuId =
		createColumn(
			"MenuId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalJournalTable, String> ApprovalGroup =
		createColumn(
			"ApprovalGroup", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalJournalTable, Boolean> ApprovalWorkflow =
		createColumn(
			"ApprovalWorkflow", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<MasterApprovalJournalTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalJournalTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalJournalTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalJournalTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MasterApprovalJournalTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private MasterApprovalJournalTable() {
		super("master_approval_journal", MasterApprovalJournalTable::new);
	}

}