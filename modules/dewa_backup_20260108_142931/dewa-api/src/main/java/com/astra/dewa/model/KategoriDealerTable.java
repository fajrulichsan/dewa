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
 * The table class for the &quot;kategori_dealer&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see KategoriDealer
 * @generated
 */
public class KategoriDealerTable extends BaseTable<KategoriDealerTable> {

	public static final KategoriDealerTable INSTANCE =
		new KategoriDealerTable();

	public final Column<KategoriDealerTable, Long> Id = createColumn(
		"Id", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<KategoriDealerTable, Integer> DealerId = createColumn(
		"DealerId", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<KategoriDealerTable, String> Judul = createColumn(
		"Judul", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KategoriDealerTable, String> PeriodeReview =
		createColumn(
			"PeriodeReview", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KategoriDealerTable, Integer> Tahun = createColumn(
		"Tahun", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<KategoriDealerTable, Long> FileId = createColumn(
		"FileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<KategoriDealerTable, String> FileName = createColumn(
		"FileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KategoriDealerTable, Boolean> rowStatus = createColumn(
		"rowStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<KategoriDealerTable, Date> CreatedDate = createColumn(
		"CreatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<KategoriDealerTable, String> CreatedBy = createColumn(
		"CreatedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KategoriDealerTable, Date> ModifiedDate = createColumn(
		"ModifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<KategoriDealerTable, String> ModifiedBy = createColumn(
		"ModifiedBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private KategoriDealerTable() {
		super("kategori_dealer", KategoriDealerTable::new);
	}

}