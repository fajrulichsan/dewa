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
 * The table class for the &quot;diskon_fakpol&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see DiskonFakpol
 * @generated
 */
public class DiskonFakpolTable extends BaseTable<DiskonFakpolTable> {

	public static final DiskonFakpolTable INSTANCE = new DiskonFakpolTable();

	public final Column<DiskonFakpolTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<DiskonFakpolTable, Integer> DealerId = createColumn(
		"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<DiskonFakpolTable, Integer> Tahun = createColumn(
		"Tahun", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<DiskonFakpolTable, String> Periode = createColumn(
		"Periode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonFakpolTable, String> Keterangan = createColumn(
		"Keterangan", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonFakpolTable, Long> FileId = createColumn(
		"FileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DiskonFakpolTable, String> FileName = createColumn(
		"FileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonFakpolTable, String> FilePath = createColumn(
		"FilePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonFakpolTable, Boolean> RowStatus = createColumn(
		"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<DiskonFakpolTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DiskonFakpolTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonFakpolTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DiskonFakpolTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private DiskonFakpolTable() {
		super("diskon_fakpol", DiskonFakpolTable::new);
	}

}