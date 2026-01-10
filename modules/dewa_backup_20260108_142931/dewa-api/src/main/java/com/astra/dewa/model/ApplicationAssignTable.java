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
 * The table class for the &quot;ApplicationAssign&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationAssign
 * @generated
 */
public class ApplicationAssignTable extends BaseTable<ApplicationAssignTable> {

	public static final ApplicationAssignTable INSTANCE =
		new ApplicationAssignTable();

	public final Column<ApplicationAssignTable, Integer> Id = createColumn(
		"Id", Integer.class, Types.INTEGER, Column.FLAG_PRIMARY);
	public final Column<ApplicationAssignTable, Integer> ApplicationHeaderId =
		createColumn(
			"ApplicationHeaderId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignTable, Integer>
		ApplicationAssignStatusId = createColumn(
			"ApplicationAssignStatusId", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignTable, Long> ProfileId = createColumn(
		"ProfileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignTable, Date> StartDateOn =
		createColumn(
			"StartDateOn", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignTable, Date> CompletedDateOn =
		createColumn(
			"CompletedDateOn", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignTable, String> Notes = createColumn(
		"Notes", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignTable, Boolean> RowStatus =
		createColumn(
			"RowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignTable, String> CreatedBy =
		createColumn(
			"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignTable, Date> CreatedDate =
		createColumn(
			"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignTable, String> ModifiedBy =
		createColumn(
			"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApplicationAssignTable, Date> ModifiedDate =
		createColumn(
			"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ApplicationAssignTable() {
		super("ApplicationAssign", ApplicationAssignTable::new);
	}

}