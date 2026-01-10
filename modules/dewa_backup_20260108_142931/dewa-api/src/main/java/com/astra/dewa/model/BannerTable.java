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
 * The table class for the &quot;Banner&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Banner
 * @generated
 */
public class BannerTable extends BaseTable<BannerTable> {

	public static final BannerTable INSTANCE = new BannerTable();

	public final Column<BannerTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<BannerTable, Boolean> IsShow = createColumn(
		"IsShow", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<BannerTable, Long> FileId = createColumn(
		"FileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<BannerTable, String> FileName = createColumn(
		"FileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<BannerTable, String> FilePath = createColumn(
		"FilePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<BannerTable, Boolean> RowStatus = createColumn(
		"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<BannerTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<BannerTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<BannerTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<BannerTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private BannerTable() {
		super("Banner", BannerTable::new);
	}

}