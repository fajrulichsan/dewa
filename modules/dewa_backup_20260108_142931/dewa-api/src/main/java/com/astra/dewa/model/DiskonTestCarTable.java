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
 * The table class for the &quot;diskon_test_car&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see DiskonTestCar
 * @generated
 */
public class DiskonTestCarTable extends BaseTable<DiskonTestCarTable> {

	public static final DiskonTestCarTable INSTANCE = new DiskonTestCarTable();

	public final Column<DiskonTestCarTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<DiskonTestCarTable, Integer> DealerId = createColumn(
		"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<DiskonTestCarTable, String> KuartalId = createColumn(
		"KuartalId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonTestCarTable, Integer> TipeKendaraanId =
		createColumn(
			"TipeKendaraanId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<DiskonTestCarTable, Integer> Tahun = createColumn(
		"Tahun", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<DiskonTestCarTable, String> Keterangan = createColumn(
		"Keterangan", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonTestCarTable, Long> FileId = createColumn(
		"FileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DiskonTestCarTable, String> FileName = createColumn(
		"FileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonTestCarTable, String> FilePath = createColumn(
		"FilePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonTestCarTable, Boolean> RowStatus = createColumn(
		"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<DiskonTestCarTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DiskonTestCarTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonTestCarTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DiskonTestCarTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private DiskonTestCarTable() {
		super("diskon_test_car", DiskonTestCarTable::new);
	}

}