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
 * The table class for the &quot;training_materi&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingMateri
 * @generated
 */
public class TrainingMateriTable extends BaseTable<TrainingMateriTable> {

	public static final TrainingMateriTable INSTANCE =
		new TrainingMateriTable();

	public final Column<TrainingMateriTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<TrainingMateriTable, Integer> JenisMateriId =
		createColumn(
			"JenisMateriId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriTable, Integer> TopikMateriId =
		createColumn(
			"TopikMateriId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriTable, String> JudulMateri = createColumn(
		"JudulMateri", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriTable, Long> ImageId = createColumn(
		"ImageId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriTable, String> ImageName = createColumn(
		"ImageName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriTable, String> ImagePath = createColumn(
		"ImagePath", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriTable, Boolean> RowStatus = createColumn(
		"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TrainingMateriTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private TrainingMateriTable() {
		super("training_materi", TrainingMateriTable::new);
	}

}