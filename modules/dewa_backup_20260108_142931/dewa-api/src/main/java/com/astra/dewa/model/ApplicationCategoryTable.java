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
 * The table class for the &quot;ApplicationCategory&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationCategory
 * @generated
 */
public class ApplicationCategoryTable
	extends BaseTable<ApplicationCategoryTable> {

	public static final ApplicationCategoryTable INSTANCE =
		new ApplicationCategoryTable();

	public final Column<ApplicationCategoryTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<ApplicationCategoryTable, String> Name = createColumn(
		"Name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationCategoryTable, String> Description =
		createColumn(
			"Description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationCategoryTable, Integer> CategoryBonus =
		createColumn(
			"CategoryBonus", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ApplicationCategoryTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApplicationCategoryTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationCategoryTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApplicationCategoryTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationCategoryTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ApplicationCategoryTable() {
		super("ApplicationCategory", ApplicationCategoryTable::new);
	}

}