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
 * The table class for the &quot;faktur_indirect&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see FakturIndirect
 * @generated
 */
public class FakturIndirectTable extends BaseTable<FakturIndirectTable> {

	public static final FakturIndirectTable INSTANCE =
		new FakturIndirectTable();

	public final Column<FakturIndirectTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<FakturIndirectTable, Integer> DealerId = createColumn(
		"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<FakturIndirectTable, String> Keterangan = createColumn(
		"Keterangan", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FakturIndirectTable, Date> InvoiceDate = createColumn(
		"InvoiceDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FakturIndirectTable, Date> UploadDate = createColumn(
		"UploadDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FakturIndirectTable, Long> FileId = createColumn(
		"FileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FakturIndirectTable, String> FileName = createColumn(
		"FileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FakturIndirectTable, String> FilePath = createColumn(
		"FilePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FakturIndirectTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FakturIndirectTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FakturIndirectTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FakturIndirectTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FakturIndirectTable, Boolean> RowStatus = createColumn(
		"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private FakturIndirectTable() {
		super("faktur_indirect", FakturIndirectTable::new);
	}

}