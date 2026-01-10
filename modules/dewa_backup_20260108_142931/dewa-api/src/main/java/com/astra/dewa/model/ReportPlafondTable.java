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
 * The table class for the &quot;report_plafond&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ReportPlafond
 * @generated
 */
public class ReportPlafondTable extends BaseTable<ReportPlafondTable> {

	public static final ReportPlafondTable INSTANCE = new ReportPlafondTable();

	public final Column<ReportPlafondTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<ReportPlafondTable, Integer> DealerId = createColumn(
		"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ReportPlafondTable, Long> FileId = createColumn(
		"FileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ReportPlafondTable, String> FileName = createColumn(
		"FileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReportPlafondTable, String> FilePath = createColumn(
		"FilePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReportPlafondTable, Date> Periode = createColumn(
		"Periode", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReportPlafondTable, String> Keterangan = createColumn(
		"Keterangan", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReportPlafondTable, Boolean> RowStatus = createColumn(
		"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ReportPlafondTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReportPlafondTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReportPlafondTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReportPlafondTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private ReportPlafondTable() {
		super("report_plafond", ReportPlafondTable::new);
	}

}