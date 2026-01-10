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
 * The table class for the &quot;Dealer&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Dealer
 * @generated
 */
public class DealerTable extends BaseTable<DealerTable> {

	public static final DealerTable INSTANCE = new DealerTable();

	public final Column<DealerTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<DealerTable, Integer> CabangId = createColumn(
		"CabangId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<DealerTable, Integer> WilayahId = createColumn(
		"WilayahId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<DealerTable, Integer> GroupDealer = createColumn(
		"GroupDealer", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<DealerTable, String> Name = createColumn(
		"Name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DealerTable, String> Code = createColumn(
		"Code", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DealerTable, String> KodeHo = createColumn(
		"KodeHo", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DealerTable, Boolean> RowStatus = createColumn(
		"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<DealerTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DealerTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DealerTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DealerTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private DealerTable() {
		super("Dealer", DealerTable::new);
	}

}