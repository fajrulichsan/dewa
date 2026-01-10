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
 * The table class for the &quot;ticket&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Ticket
 * @generated
 */
public class TicketTable extends BaseTable<TicketTable> {

	public static final TicketTable INSTANCE = new TicketTable();

	public final Column<TicketTable, String> Id = createColumn(
		"Id", String.class, Types.VARCHAR, Column.FLAG_PRIMARY);
	public final Column<TicketTable, String> DealerCode = createColumn(
		"DealerCode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, Date> TicketDate = createColumn(
		"TicketDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> TicketNumber = createColumn(
		"TicketNumber", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> RequestName = createColumn(
		"RequestName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> FirstApprover = createColumn(
		"FirstApprover", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> Email = createColumn(
		"Email", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> EmailCc = createColumn(
		"EmailCc", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> Phone = createColumn(
		"Phone", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> RequestCategory = createColumn(
		"RequestCategory", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> RequestDescription = createColumn(
		"RequestDescription", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> BussinesBenefit = createColumn(
		"BussinesBenefit", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> NameFile = createColumn(
		"NameFile", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> PathFile = createColumn(
		"PathFile", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private TicketTable() {
		super("ticket", TicketTable::new);
	}

}