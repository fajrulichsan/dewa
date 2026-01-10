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
 * The table class for the &quot;ApplicationHeader&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationHeader
 * @generated
 */
public class ApplicationHeaderTable extends BaseTable<ApplicationHeaderTable> {

	public static final ApplicationHeaderTable INSTANCE =
		new ApplicationHeaderTable();

	public final Column<ApplicationHeaderTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<ApplicationHeaderTable, Integer> ApplicationId =
		createColumn(
			"ApplicationId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, Integer>
		ApplicationHeaderStatusId = createColumn(
			"ApplicationHeaderStatusId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, Integer> ApplicationCategoryId =
		createColumn(
			"ApplicationCategoryId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, Integer> DealerId =
		createColumn(
			"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> TicketNo = createColumn(
		"TicketNo", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> TicketCode =
		createColumn(
			"TicketCode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, Date> ReqDate = createColumn(
		"ReqDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, Integer> ReqYear = createColumn(
		"ReqYear", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, Integer> ReqMonth =
		createColumn(
			"ReqMonth", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> ReqScreenName =
		createColumn(
			"ReqScreenName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> ReqName = createColumn(
		"ReqName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> ReqEmail = createColumn(
		"ReqEmail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> ReqCCEmail =
		createColumn(
			"ReqCCEmail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> ReqPhone = createColumn(
		"ReqPhone", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, Integer> NominalPengajuan =
		createColumn(
			"NominalPengajuan", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> ReqDesc = createColumn(
		"ReqDesc", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> BusinessBenefit =
		createColumn(
			"BusinessBenefit", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, Long> FileId = createColumn(
		"FileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> FileName = createColumn(
		"FileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> FileUrl = createColumn(
		"FileUrl", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> Notes = createColumn(
		"Notes", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> NotesHistory =
		createColumn(
			"NotesHistory", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationHeaderTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ApplicationHeaderTable() {
		super("ApplicationHeader", ApplicationHeaderTable::new);
	}

}