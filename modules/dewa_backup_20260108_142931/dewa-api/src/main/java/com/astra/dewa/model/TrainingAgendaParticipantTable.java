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
 * The table class for the &quot;training_agenda_participant&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingAgendaParticipant
 * @generated
 */
public class TrainingAgendaParticipantTable
	extends BaseTable<TrainingAgendaParticipantTable> {

	public static final TrainingAgendaParticipantTable INSTANCE =
		new TrainingAgendaParticipantTable();

	public final Column<TrainingAgendaParticipantTable, Integer> Id =
		createColumn("Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<TrainingAgendaParticipantTable, Integer>
		TrainingAgendaId = createColumn(
			"TrainingAgendaId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantTable, Integer> DealerId =
		createColumn(
			"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantTable, String> FullName =
		createColumn(
			"FullName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantTable, String> Email =
		createColumn("Email", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantTable, String> Phone =
		createColumn("Phone", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private TrainingAgendaParticipantTable() {
		super(
			"training_agenda_participant", TrainingAgendaParticipantTable::new);
	}

}