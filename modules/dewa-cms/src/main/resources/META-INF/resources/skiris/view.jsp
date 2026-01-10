<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/skiris-list" var="skirisListURL"/>
<portlet:resourceURL id="/skiris-action" var="skirisActionURL"/>
<portlet:renderURL var="skirisEditURL">
	<portlet:param name="mvcRenderCommandName" value="/skiris-edit"/>
</portlet:renderURL>

<style>
	.skiris {
		margin: 25px;
	}

	.skiris > .tabcontent {
		box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
		border-radius: 10px;
		padding: 0.75em;
	}

	#skiris_table > thead {
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

	table#skiris_table {
		border: 1px solid #DCDFE3;
		border-radius: 10px 10px 10px 10px;
	}

	table#skiris_table {
		border-radius: 10px 10px 10px 10px;
	}

	#skiris_table tbody tr {
		border: 1px solid #DCDFE3;
	}

	#skiris_table > thead > tr > th {
		font-weight: normal;
		text-align: center;
	}

	#approval_table_filter > label > input {
		background-image: url(/o/com.astra.dewa.web/assets/img/search.svg);
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
	}
</style>

<div class="cms-content">
	<h3>Master Data SK IRIS</h3>
	<div class="tabcontent" id="cms-tabcontent">
		<ul class="nav nav-tabs" id="cmsTab" role="tablist">
			<li class="nav-item">
				<a class="nav-link" id="dealer-navtab" data-toggle="" href="/group/dewa/cms-master-dealer" role="tab" aria-controls="dealer" aria-selected="false">Master Data Dealer</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" id="skiris_navtab" data-toggle="tab" href="#skiris_tab" role="tab" aria-controls="skiris" aria-selected="true">Master Data SK IRIS</a>
			</li>
		</ul>
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade" id="skiris_tab" role="tabpanel" aria-labelledby="profile-tab">
				<table id="skiris_table" class="table table-hover nowrap" style="width:100%;">
					<thead>
						<tr>
							<th>Kode Dealer</th>
							<th>Kode HO Dealer</th>
							<th>Nama Dealer</th>
							<th>Cabang Dealer</th>
							<th>Wilayah SK</th>
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
	var skirisDataTable = $('#skiris_table').DataTable({
		info: true,
		ajax: {
			url: '${skirisListURL}',
			type: 'POST',
			dataSrc: 'Data'
		},
		language: languageDealerTable,
		columns: [
			{data: "code", "width": "25"},
			{data: "roleDealer", "width": "40"},
			{data: "name"},
			{data: "cabangName", "width": "30"},
			{data: "wilayahName", "width": "30"},
			// {
			// 	data: "description", orderable: false,
			// 	render: $.fn.dataTable.render.ellipsis(25)
			// },
			{
				data: "id",
				"render": function (data, type, row, meta) {
					return renderActionButton(data);
				},
				orderable: false,
				className: "text-center"
			}
		],
		responsive: true,
		order: [],
		initComplete: function () {
			var input = $('.dataTables_filter input').unbind(),
				self = this.api(),
				$searchButton = $('<button>')
					.text('Search')
					.addClass("btn")
					.addClass("btn-info")
					.addClass("btn_table")
					.css("margin-left", "10px")
					.click(function () {
						self.search(input.val()).draw();
					})
			$('.dataTables_filter').append($searchButton);
			// $('.dataTables_filter').prepend('<a href="${skirisEditURL}&id=0" class="btn btn-info btn_table"><span><i class="fas fa-plus" style="margin-right: 10px"></i> Tambah </span></a>');
		}
	});

	function renderActionButton(dataId) {
		var response = "";
		response = '<span class="action-wrapper" data-id="' + dataId + '">' +
				'<a href="${skirisEditURL}&id=' + dataId + '" >' +
					'<span><img src="/o/com.astra.dewa.cms/assets/img/edit.svg"></img> </span>' +
				'</a>' +
				// '<a href="javascript:void(0)" onclick="deleteDealer(this)" data-id="' + dataId + '" >' +
				// 	'<span><img src="/o/com.astra.dewa.cms/assets/img/trash.svg"></img> </span>' +
				// '</a>' +
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
				formData.append("crudType", "delete");
				formData.append("entryId", entryId);

				$.ajax({
					url: "${skirisActionURL}",
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
									skirisDataTable.ajax.reload();
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
		$('#skiris_navtab')[0].click()
	});
</script>