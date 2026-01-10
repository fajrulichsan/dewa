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
 * The table class for the &quot;jenis_materi&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see JenisMateri
 * @generated
 */
public class JenisMateriTable extends BaseTable<JenisMateriTable> {

	public static final JenisMateriTable INSTANCE = new JenisMateriTable();

	public final Column<JenisMateriTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<JenisMateriTable, String> Name = createColumn(
		"Name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JenisMateriTable, Long> ImageId = createColumn(
		"ImageId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<JenisMateriTable, String> ImageName = createColumn(
		"ImageName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JenisMateriTable, String> ImagePath = createColumn(
		"ImagePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JenisMateriTable, Boolean> RowStatus = createColumn(
		"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<JenisMateriTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<JenisMateriTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<JenisMateriTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<JenisMateriTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private JenisMateriTable() {
		super("jenis_materi", JenisMateriTable::new);
	}

}