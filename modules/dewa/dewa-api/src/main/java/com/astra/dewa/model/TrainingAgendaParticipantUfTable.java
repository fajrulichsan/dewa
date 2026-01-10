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
 * The table class for the &quot;training_agenda_participant_uf&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingAgendaParticipantUf
 * @generated
 */
public class TrainingAgendaParticipantUfTable
	extends BaseTable<TrainingAgendaParticipantUfTable> {

	public static final TrainingAgendaParticipantUfTable INSTANCE =
		new TrainingAgendaParticipantUfTable();

	public final Column<TrainingAgendaParticipantUfTable, Integer> Id =
		createColumn("Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<TrainingAgendaParticipantUfTable, Integer>
		TrainingAgendaId = createColumn(
			"TrainingAgendaId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantUfTable, Integer> DealerId =
		createColumn(
			"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantUfTable, Long> FileId =
		createColumn("FileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantUfTable, String> FileName =
		createColumn(
			"FileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantUfTable, String> FilePath =
		createColumn(
			"FilePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantUfTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantUfTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantUfTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantUfTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TrainingAgendaParticipantUfTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private TrainingAgendaParticipantUfTable() {
		super(
			"training_agenda_participant_uf",
			TrainingAgendaParticipantUfTable::new);
	}

}