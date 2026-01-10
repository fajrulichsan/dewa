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
 * The table class for the &quot;common&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Common
 * @generated
 */
public class CommonTable extends BaseTable<CommonTable> {

	public static final CommonTable INSTANCE = new CommonTable();

	public final Column<CommonTable, String> CommonKey = createColumn(
		"CommonKey", String.class, Types.VARCHAR, Column.FLAG_PRIMARY);
	public final Column<CommonTable, String> CommonCode = createColumn(
		"CommonCode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommonTable, String> CommonDesc1 = createColumn(
		"CommonDesc1", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommonTable, String> CommonDesc2 = createColumn(
		"CommonDesc2", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommonTable, String> CommonDesc3 = createColumn(
		"CommonDesc3", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommonTable, String> CommonDesc4 = createColumn(
		"CommonDesc4", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommonTable, String> CommonDesc5 = createColumn(
		"CommonDesc5", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommonTable, Integer> Sequence = createColumn(
		"Sequence", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<CommonTable, Boolean> IsActive = createColumn(
		"IsActive", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<CommonTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommonTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CommonTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CommonTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private CommonTable() {
		super("common", CommonTable::new);
	}

}