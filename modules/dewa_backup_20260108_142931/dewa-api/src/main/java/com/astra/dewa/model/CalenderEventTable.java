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
 * The table class for the &quot;calender_event&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see CalenderEvent
 * @generated
 */
public class CalenderEventTable extends BaseTable<CalenderEventTable> {

	public static final CalenderEventTable INSTANCE = new CalenderEventTable();

	public final Column<CalenderEventTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<CalenderEventTable, Integer> JenisAcara = createColumn(
		"JenisAcara", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, Integer> StatusAcara = createColumn(
		"StatusAcara", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, String> Judul = createColumn(
		"Judul", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, String> Location = createColumn(
		"Location", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, String> LinkMeeting = createColumn(
		"LinkMeeting", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, Date> StartDate = createColumn(
		"StartDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, String> StartDateHours =
		createColumn(
			"StartDateHours", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, Date> EndDate = createColumn(
		"EndDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, String> EndDateHours = createColumn(
		"EndDateHours", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, Date> RegistrationLimitDate =
		createColumn(
			"RegistrationLimitDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, String> RegistrationLimitDateHours =
		createColumn(
			"RegistrationLimitDateHours", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, String> Deskripsi = createColumn(
		"Deskripsi", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, Long> ImageId = createColumn(
		"ImageId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, String> ImageName = createColumn(
		"ImageName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, String> ImagePath = createColumn(
		"ImagePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, Boolean> RowStatus = createColumn(
		"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CalenderEventTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private CalenderEventTable() {
		super("calender_event", CalenderEventTable::new);
	}

}