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
 * The table class for the &quot;MenuAuthorization&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see MenuAuthorization
 * @generated
 */
public class MenuAuthorizationTable extends BaseTable<MenuAuthorizationTable> {

	public static final MenuAuthorizationTable INSTANCE =
		new MenuAuthorizationTable();

	public final Column<MenuAuthorizationTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<MenuAuthorizationTable, Integer> RoleId = createColumn(
		"RoleId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<MenuAuthorizationTable, String> Menus = createColumn(
		"Menus", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MenuAuthorizationTable, Boolean> IsRssp = createColumn(
		"IsRssp", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MenuAuthorizationTable, Boolean> IsCmsDso =
		createColumn(
			"IsCmsDso", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MenuAuthorizationTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<MenuAuthorizationTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MenuAuthorizationTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<MenuAuthorizationTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<MenuAuthorizationTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private MenuAuthorizationTable() {
		super("MenuAuthorization", MenuAuthorizationTable::new);
	}

}