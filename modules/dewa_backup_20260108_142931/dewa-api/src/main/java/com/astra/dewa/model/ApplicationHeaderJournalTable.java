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
 * The table class for the &quot;ApplicationHeaderJournal&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationHeaderJournal
 * @generated
 */
public class ApplicationHeaderJournalTable
	extends BaseTable<ApplicationHeaderJournalTable> {

	public static final ApplicationHeaderJournalTable INSTANCE =
		new ApplicationHeaderJournalTable();

	public final Column<ApplicationHeaderJournalTable, Integer> Id =
		createColumn("Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<ApplicationHeaderJournalTable, Integer>
		ApplicationHeaderId = createColumn(
			"ApplicationHeaderId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, Integer> ApplicationId =
		createColumn(
			"ApplicationId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, Integer>
		ApplicationHeaderStatusId = createColumn(
			"ApplicationHeaderStatusId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, Integer>
		ApplicationCategoryId = createColumn(
			"ApplicationCategoryId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, Integer> DealerId =
		createColumn(
			"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> TicketNo =
		createColumn(
			"TicketNo", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> TicketCode =
		createColumn(
			"TicketCode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, Date> ReqDate =
		createColumn(
			"ReqDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, Integer> ReqYear =
		createColumn(
			"ReqYear", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, Integer> ReqMonth =
		createColumn(
			"ReqMonth", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> ReqScreenName =
		createColumn(
			"ReqScreenName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> ReqName =
		createColumn(
			"ReqName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> ReqEmail =
		createColumn(
			"ReqEmail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> ReqCCEmail =
		createColumn(
			"ReqCCEmail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> ReqPhone =
		createColumn(
			"ReqPhone", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, Integer>
		NominalPengajuan = createColumn(
			"NominalPengajuan", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> ReqDesc =
		createColumn(
			"ReqDesc", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> BusinessBenefit =
		createColumn(
			"BusinessBenefit", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, Long> FileId =
		createColumn("FileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> FileName =
		createColumn(
			"FileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> FileUrl =
		createColumn(
			"FileUrl", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> Notes =
		createColumn("Notes", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> NotesHistory =
		createColumn(
			"NotesHistory", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderJournalTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ApplicationHeaderJournalTable() {
		super("ApplicationHeaderJournal", ApplicationHeaderJournalTable::new);
	}

}