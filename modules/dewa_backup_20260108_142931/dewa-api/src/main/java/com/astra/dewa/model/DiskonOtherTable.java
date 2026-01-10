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
 * The table class for the &quot;diskon_others&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see DiskonOther
 * @generated
 */
public class DiskonOtherTable extends BaseTable<DiskonOtherTable> {

	public static final DiskonOtherTable INSTANCE = new DiskonOtherTable();

	public final Column<DiskonOtherTable, Long> diskonOtherId = createColumn(
		"diskonOtherId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<DiskonOtherTable, Integer> dealerId = createColumn(
		"dealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<DiskonOtherTable, Integer> tahun = createColumn(
		"tahun", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<DiskonOtherTable, Integer> kuartalId = createColumn(
		"kuartalId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<DiskonOtherTable, String> keterangan = createColumn(
		"keterangan", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonOtherTable, Long> fileId = createColumn(
		"fileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DiskonOtherTable, String> fileName = createColumn(
		"fileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonOtherTable, String> filePath = createColumn(
		"filePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonOtherTable, Boolean> rowStatus = createColumn(
		"rowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<DiskonOtherTable, Date> createdDate = createColumn(
		"createdDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DiskonOtherTable, String> createdBy = createColumn(
		"createdBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DiskonOtherTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DiskonOtherTable, String> modifiedBy = createColumn(
		"modifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private DiskonOtherTable() {
		super("diskon_others", DiskonOtherTable::new);
	}

}