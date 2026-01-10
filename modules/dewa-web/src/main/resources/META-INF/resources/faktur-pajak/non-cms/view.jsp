<%@ include file="/META-INF/resources/init.jsp" %> <%@ include file="/META-INF/resources/general/non-cms/general-css.jsp" %>

<portlet:resourceURL id="/faktur-pajak-non-cms-list" var="fakturPajakListURL" />
<portlet:resourceURL id="/download-faktur-pajak" var="downloadFakturPajakURL" />
<portlet:resourceURL id="/non-cms/dealers-with-details" var="dealerFakturPajakURL" />

<link href="<%=request.getContextPath()%>/faktur-pajak/non-cms/css/faktur-pajak.css" rel="stylesheet" type="text/css" />

<div class="faktur_pajak non-cms-menu">
	<ol class="breadcrumb">
		<li>Home</li>
		<li>Download</li>
		<li>Unit</li>
		<li>Faktur Pajak</li>
	</ol>

	<div class="main-title">
		<h3>Faktur Pajak</h3>
	</div>

	<div class="row table-filters">
		<div class="col-xs-12 col-md-3 dealerId">
			<select class="form-control" name="dealerId" id="dealerId" style="width: 100%">
				<option value="ALL">Select All</option>
			</select>
		</div>
		<div class="col-xs-12 col-md-3 period-filter">
			<div class="input-group">
				<input type="text" onkeydown="return false" class="form-control dateIcon required" name="periode-date" placeholder="Pilih Tanggal Faktur" />
				<span class="input-group-btn">
					<button class="btn btn-info btn_table" onclick="refresh()">
						<span><i class="icon-refresh"></i></span>
					</button>
				</span>
			</div>
		</div>
	</div>

	<div class="tabcontent">
		<table id="faktur_pajak_non_cms_table" class="table table-hover nowrap non-cms-table" style="width: 100%">
			<thead>
				<tr>
					<th>No</th>
					<th>Faktur Pajak</th>
					<th>Periode Faktur</th>
					<th>Tanggal Upload</th>
					<th>Keterangan</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
</div>

<script>
	var FAKTUR_PAJAK_LIST_URL = "${fakturPajakListURL}";
	var DOWNLOAD_FAKTUR_PAJAK_URL = "${downloadFakturPajakURL}";
	var DEALER_FAKTUR_PAJAK_URL = "${dealerFakturPajakURL}";
	var FAKTUR_PAJAK_ENTRY_ID = "<portlet:namespace/>entryId";
</script>

<script src="<%=request.getContextPath()%>/diskon/js/diskon-menu.js"></script>
<script src="<%=request.getContextPath()%>/faktur-pajak/non-cms/js/faktur-pajak.js"></script>
