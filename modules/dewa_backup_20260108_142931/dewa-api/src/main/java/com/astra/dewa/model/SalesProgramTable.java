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
 * The table class for the &quot;sales_program&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SalesProgram
 * @generated
 */
public class SalesProgramTable extends BaseTable<SalesProgramTable> {

	public static final SalesProgramTable INSTANCE = new SalesProgramTable();

	public final Column<SalesProgramTable, Long> Id = createColumn(
		"Id", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SalesProgramTable, Integer> Tahun = createColumn(
		"Tahun", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SalesProgramTable, String> Periode = createColumn(
		"Periode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SalesProgramTable, Long> FileId = createColumn(
		"FileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SalesProgramTable, String> FileName = createColumn(
		"FileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SalesProgramTable, Boolean> rowStatus = createColumn(
		"rowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<SalesProgramTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SalesProgramTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SalesProgramTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SalesProgramTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private SalesProgramTable() {
		super("sales_program", SalesProgramTable::new);
	}

}