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
 * The table class for the &quot;calender_event_participant&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see CalenderEventParticipant
 * @generated
 */
public class CalenderEventParticipantTable
	extends BaseTable<CalenderEventParticipantTable> {

	public static final CalenderEventParticipantTable INSTANCE =
		new CalenderEventParticipantTable();

	public final Column<CalenderEventParticipantTable, Integer> Id =
		createColumn("Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<CalenderEventParticipantTable, Integer>
		CalenderEventId = createColumn(
			"CalenderEventId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<CalenderEventParticipantTable, Integer> DealerId =
		createColumn(
			"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<CalenderEventParticipantTable, String> FullName =
		createColumn(
			"FullName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CalenderEventParticipantTable, String> Email =
		createColumn("Email", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CalenderEventParticipantTable, String> Phone =
		createColumn("Phone", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CalenderEventParticipantTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<CalenderEventParticipantTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CalenderEventParticipantTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CalenderEventParticipantTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CalenderEventParticipantTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private CalenderEventParticipantTable() {
		super("calender_event_participant", CalenderEventParticipantTable::new);
	}

}