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
 * The table class for the &quot;surat_penalty_stock&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SuratPenaltyStock
 * @generated
 */
public class SuratPenaltyStockTable extends BaseTable<SuratPenaltyStockTable> {

	public static final SuratPenaltyStockTable INSTANCE =
		new SuratPenaltyStockTable();

	public final Column<SuratPenaltyStockTable, Long> Id = createColumn(
		"Id", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SuratPenaltyStockTable, Integer> DealerId =
		createColumn(
			"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SuratPenaltyStockTable, String> Judul = createColumn(
		"Judul", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SuratPenaltyStockTable, String> Periode = createColumn(
		"Periode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SuratPenaltyStockTable, Integer> Tahun = createColumn(
		"Tahun", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SuratPenaltyStockTable, Long> FileId = createColumn(
		"FileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SuratPenaltyStockTable, String> FileName = createColumn(
		"FileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SuratPenaltyStockTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SuratPenaltyStockTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SuratPenaltyStockTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SuratPenaltyStockTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SuratPenaltyStockTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private SuratPenaltyStockTable() {
		super("surat_penalty_stock", SuratPenaltyStockTable::new);
	}

}