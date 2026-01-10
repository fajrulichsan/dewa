<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/general-css.jsp" %>

<portlet:resourceURL id="/dealer-list" var="dealerListURL"/>
<portlet:resourceURL id="/dealer-action" var="dealerActionURL"/>
<portlet:renderURL var="dealerEditURL">
	<portlet:param name="mvcRenderCommandName" value="/dealer-edit"/>
</portlet:renderURL>

<%--
<style>
	.dealer {
		margin: 25px;
	}

	.dealer > .tabcontent {
		box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
		border-radius: 10px;
		padding: 0.75em;
	}

	#dealer_table > thead {
		background-color: #014689;
		border: none !important;
		color: white;
		border-radius: 10px 10px 0 0;
	}

	.table > thead:first-child > tr:first-child > th:first-child {
		border-radius: 10px 0 0 0;
	}

	.table > thead:first-child > tr:first-child > th:last-child {
		border-radius: 0 10px 0 0;
	}

	table#dealer_table {
		border: 1px solid #DCDFE3;
		border-radius: 10px 10px 10px 10px;
	}

	table#dealer_table {
		border-radius: 10px 10px 10px 10px;
	}

	#dealer_table tbody tr {
		border: 1px solid #DCDFE3;
	}

	#dealer_table > thead > tr > th {
		font-weight: normal;
		text-align: center;
	}

	#approval_table_filter > label > input {
		background-image: url(<%=request.getContextPath()%>/assets/img/search.svg);
		background-position: 10px 8px;
		background-repeat: no-repeat;
		padding: 5px 20px 5px 40px;
	}

	.btn_table, .btn_table:hover {
		border: 0px;
		background-color: #014689 !important;
		color: white;
		font-weight: 400;
		opacity: 0.8;
		padding: 8px 16px;
		border-radius: 5px;
		margin-right: 15px;
	}
</style>
--%>

<div class="cms-menu">
	<h3 class="menu-title">Master Data Dealer</h3>
	<div class="tabcontent" id="cms-tabcontent">
		<ul class="nav nav-tabs" id="cmsTab" role="tablist">
			<li class="nav-item">
				<a class="nav-link" id="dealer_navtab" data-toggle="tab" href="#dealer_tab" role="tab" aria-controls="dealer" aria-selected="true">Master Data Dealer</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" id="skiris-navtab" data-toggle="" href="/group/dealink/cms-sk-iris" role="tab" aria-controls="skiris" aria-selected="false">Master Data SK IRIS</a>
			</li>
		</ul>
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade" id="dealer_tab" role="tabpanel" aria-labelledby="profile-tab">
				<div class="table-filters"></div>
				<table id="dealer_table" class="table table-hover nowrap cms-table" style="width:100%;">
					<thead>
						<tr>
							<th>Kode Dealer</th>
							<th>Kode HO Dealer</th>
							<th>Nama Dealer</th>
							<th>Group Dealer</th>
							<th>Cabang Dealer</th>
							<th>Wilayah Dealer</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script>
	var dealerDataTable;
	var column = "ALL";
	var keyword = "ALL";

	function refresh() {
		column = 'ALL';
		keyword = 'ALL';
		getDealers();
	}

	var languageDealerTable = {
		// info: "",
		"lengthMenu": "_MENU_",
		"paginate": {
			"first": "",
			"last": "",
			"next": '<span class="glyphicon glyphicon-menu-right"></span>',
			"previous": '<span class="glyphicon glyphicon-menu-left"></span>'
		},
		"search": "",
		searchPlaceholder: "Search..."
	}

	function getDealers() {
		dealerDataTable = $('#dealer_table').DataTable({
			// dom: '<"top"i>rt<"bottom"flp><"clear">',
			destroy: true,
			processing: true,
			serverSide: true,
			paging: true,
			info: true,
			searchDelay: 1000,
			searching: true,
			// ordering: true,
			// select: true,
			autoWidth: true,
			responsive: true,
			order: [],
			// order: [[0, 'asc']],
			// scrollY: '65vh',
			fixedHeader: true,
			ajax: {
				url: '${dealerListURL}',
				type: 'GET',
				data: function(d) {
					d.column = column;
					d.keyword = keyword;
					d.p_auth = Liferay.authToken;
				},
				// dataSrc: 'Data',
				dataSrc: function (json) {
					destroyLoading();
					return json.data;
				}
			},
			language: languageDealerTable,
			columns: [
				{data: "code", "width": "25", className: "text-center"},
				{data: "hoCode", "width": "40", className: "text-center"},
				{data: "name"},
				{data: "groupDealer", className: "text-center"},
				{data: "cabangName", "width": "30", className: "text-center"},
				{data: "wilayahName", "width": "30", className: "text-center"},
				{
					data: "id",
					"render": function (data, type, row, meta) {
						return renderActionButton(data);
					},
					orderable: false,
					className: "text-center"
				}
			],
			initComplete: function () {
				$('.dataTables_filter input', $('#dealer_table_wrapper'))
				.unbind()
				.bind("input", function(e) {
					if(this.value.length >= 3 || e.keyCode === 13) {
						dealerDataTable.search(this.value).draw();
					}
					if(this.value === "") {
						dealerDataTable.search("").draw();
					}
				});

				// $('.dataTables_filter').append($searchButton);
				$('.dataTables_filter').prepend('<a href="${dealerEditURL}&id=0" class="btn btn-info btn_table"><span><i class="fas fa-plus" style="margin-right: 10px"></i> Tambah </span></a>');
			}
		});
	}

	function renderActionButton(dataId) {
		var response = "";
		response = '<span class="action-wrapper" data-id="' + dataId + '">' +
				'<a href="${dealerEditURL}&id=' + dataId + '" >' +
					'<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg"></img> </span>' +
				'</a>' +
				'<a href="javascript:void(0)" onclick="deleteDealer(this)" data-id="' + dataId + '" >' +
					'<span><img src="<%=request.getContextPath()%>/assets/img/trash.svg"></img> </span>' +
				'</a>' +
			'</span>';
		return response;
	}

	function deleteDealer(element) {
		var entryId = $(element).data("id");
		Swal.fire({
			title: 'Do you want to delete the data?',
			icon: 'question',
			showCancelButton: true,
			confirmButtonText: 'Yes',
			confirmButtonColor: '#EE1C25',
		}).then((result) => {
			if (result.isConfirmed) {
				var formData = new FormData();
				formData.set("<portlet:namespace/>crudType", "delete");
				formData.set("<portlet:namespace/>entryId", entryId);
				formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

				$.ajax({
					url: "${dealerActionURL}",
					type: "POST",
					data: formData,
					processData: false,
					contentType: false,
					success: function (response) {
						var data = JSON.parse(response);
						if (data["acknowledge"] === 0) {
							Swal.fire("Failed to Delete", data["message"], "");
						} else {
							Swal.fire("Successfully delete data", "", "success")
								.then((res) => {
									dealerDataTable.ajax.reload();
								});
						}
					},
					error: function (err) {
						console.log(err);
					}
				});
			}
		});
	}

	$(document).ready(function () {
		refresh();
		$('#dealer_navtab')[0].click();
		$('.dataTables_length select').select2({
			minimumResultsForSearch: -1
		});
	});
</script>