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

/**
 * The table class for the &quot;periode_review&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see PeriodeReview
 * @generated
 */
public class PeriodeReviewTable extends BaseTable<PeriodeReviewTable> {

	public static final PeriodeReviewTable INSTANCE = new PeriodeReviewTable();

	public final Column<PeriodeReviewTable, String> Id = createColumn(
		"Id", String.class, Types.VARCHAR, Column.FLAG_PRIMARY);
	public final Column<PeriodeReviewTable, String> Name = createColumn(
		"Name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private PeriodeReviewTable() {
		super("periode_review", PeriodeReviewTable::new);
	}

}