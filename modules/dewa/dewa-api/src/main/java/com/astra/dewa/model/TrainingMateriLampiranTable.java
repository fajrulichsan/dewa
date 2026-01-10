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
 * The table class for the &quot;training_materi_lampiran&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingMateriLampiran
 * @generated
 */
public class TrainingMateriLampiranTable
	extends BaseTable<TrainingMateriLampiranTable> {

	public static final TrainingMateriLampiranTable INSTANCE =
		new TrainingMateriLampiranTable();

	public final Column<TrainingMateriLampiranTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<TrainingMateriLampiranTable, Integer> TrainingMateriId =
		createColumn(
			"TrainingMateriId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<TrainingMateriLampiranTable, Long> FileId =
		createColumn("FileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriLampiranTable, String> FileName =
		createColumn(
			"FileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriLampiranTable, String> FilePath =
		createColumn(
			"FilePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriLampiranTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriLampiranTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriLampiranTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriLampiranTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriLampiranTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private TrainingMateriLampiranTable() {
		super("training_materi_lampiran", TrainingMateriLampiranTable::new);
	}

}