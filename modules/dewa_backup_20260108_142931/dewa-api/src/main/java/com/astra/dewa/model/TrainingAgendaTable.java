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
 * The table class for the &quot;training_agenda&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingAgenda
 * @generated
 */
public class TrainingAgendaTable extends BaseTable<TrainingAgendaTable> {

	public static final TrainingAgendaTable INSTANCE =
		new TrainingAgendaTable();

	public final Column<TrainingAgendaTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<TrainingAgendaTable, Integer> JenisAgenda =
		createColumn(
			"JenisAgenda", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, Integer> StatusAgenda =
		createColumn(
			"StatusAgenda", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, String> Judul = createColumn(
		"Judul", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, String> Location = createColumn(
		"Location", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, String> LinkMeeting = createColumn(
		"LinkMeeting", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, String> Deskripsi = createColumn(
		"Deskripsi", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, Date> StartDate = createColumn(
		"StartDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, String> StartDateHours =
		createColumn(
			"StartDateHours", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, Date> EndDate = createColumn(
		"EndDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, String> EndDateHours =
		createColumn(
			"EndDateHours", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, Date> RegistrationLimitDate =
		createColumn(
			"RegistrationLimitDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, String>
		RegistrationLimitDateHours = createColumn(
			"RegistrationLimitDateHours", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, Long> ImageId = createColumn(
		"ImageId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, String> ImageName = createColumn(
		"ImageName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, String> ImagePath = createColumn(
		"ImagePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, Boolean> RowStatus = createColumn(
		"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private TrainingAgendaTable() {
		super("training_agenda", TrainingAgendaTable::new);
	}

}