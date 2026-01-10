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
 * The table class for the &quot;ApplicationAssignJournal&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationAssignJournal
 * @generated
 */
public class ApplicationAssignJournalTable
	extends BaseTable<ApplicationAssignJournalTable> {

	public static final ApplicationAssignJournalTable INSTANCE =
		new ApplicationAssignJournalTable();

	public final Column<ApplicationAssignJournalTable, Integer> Id =
		createColumn("Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<ApplicationAssignJournalTable, Integer>
		ApplicationAssignId = createColumn(
			"ApplicationAssignId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignJournalTable, Integer>
		ApplicationHeaderId = createColumn(
			"ApplicationHeaderId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignJournalTable, Integer>
		ApplicationAssignStatusId = createColumn(
			"ApplicationAssignStatusId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignJournalTable, Long> ProfileId =
		createColumn(
			"ProfileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignJournalTable, Date> StartDateOn =
		createColumn(
			"StartDateOn", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignJournalTable, Date> CompletedDateOn =
		createColumn(
			"CompletedDateOn", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignJournalTable, String> Notes =
		createColumn("Notes", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignJournalTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignJournalTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignJournalTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignJournalTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignJournalTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ApplicationAssignJournalTable() {
		super("ApplicationAssignJournal", ApplicationAssignJournalTable::new);
	}

}