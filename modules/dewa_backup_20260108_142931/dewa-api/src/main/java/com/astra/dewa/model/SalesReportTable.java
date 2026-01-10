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
 * The table class for the &quot;sales_report&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SalesReport
 * @generated
 */
public class SalesReportTable extends BaseTable<SalesReportTable> {

	public static final SalesReportTable INSTANCE = new SalesReportTable();

	public final Column<SalesReportTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<SalesReportTable, Integer> DealerId = createColumn(
		"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SalesReportTable, Long> FileId = createColumn(
		"FileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SalesReportTable, String> FileName = createColumn(
		"FileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SalesReportTable, String> FilePath = createColumn(
		"FilePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SalesReportTable, Date> Periode = createColumn(
		"Periode", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SalesReportTable, String> Keterangan = createColumn(
		"Keterangan", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SalesReportTable, Boolean> RowStatus = createColumn(
		"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<SalesReportTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SalesReportTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SalesReportTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SalesReportTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private SalesReportTable() {
		super("sales_report", SalesReportTable::new);
	}

}