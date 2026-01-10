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

/**
 * The table class for the &quot;tahun&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Tahun
 * @generated
 */
public class TahunTable extends BaseTable<TahunTable> {

	public static final TahunTable INSTANCE = new TahunTable();

	public final Column<TahunTable, String> Id = createColumn(
		"Id", String.class, Types.VARCHAR, Column.FLAG_PRIMARY);
	public final Column<TahunTable, String> Name = createColumn(
		"Name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private TahunTable() {
		super("tahun", TahunTable::new);
	}

}