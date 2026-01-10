<portlet:resourceURL id="/non_cms/diskon/others/list" var="diskonOtherListURL" />
<portlet:resourceURL id="/non_cms/diskon/others/download_file" var="downloadFileURL" />
<portlet:resourceURL id="/non_cms/diskon/others/year" var="yearURL" />
<portlet:resourceURL id="/non_cms/resource/dealer" var="dealerURL" />
<portlet:resourceURL id="/common/resource/quarter" var="quarterURL" />

<script type="text/javascript">
	(function () {
		var diskonOtherTable;

		$(document).ready(function () {
			diskonOtherTable = $('#<portlet:namespace/>diskonOthersNonCMSTable').DataTable({
				searching: false,
				paging: true,
				info: false,
				responsive: true,
				processing: true,
				serverSide: true,
				language: dataTableLanguage,
				dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
				order: [[4, 'asc']],
				ajax: {
				  url: '${diskonOtherListURL}',
				  type: 'GET',
				  data: function (d) {
				    d.dealerId = $('#<portlet:namespace/>dealerId').val();
				    d.tahun = $('#<portlet:namespace/>tahun').val();
				    d.kuartalId = $('#<portlet:namespace/>kuartalId').val();
				    d.p_auth = Liferay.authToken;
				  },
				  dataSrc: function (json) {
				    return json.data;
				  }
				},
				columns: [
				  { data: "no", "width": "5%", className: "text-center" },
				  { data: "fileName", "width": "30%" },
				  { data: "tahun", "width": "10%", className: "text-center" },
				  { data: "kuartal", "width": "20%", className: "text-center" },
				  {
						data: "uploadDateString",
						width: "30%",
						className: "text-center",
						render: function (data, type, row, meta) {
						  if (type === "sort") {
						    return row.uploadDate;
						  }
						  return data;
				     }
				  },
				  {
						data: "id",
						width: "5%",
						className: "text-center",
						render: function (data, type, row, meta) {
						  return renderActionButton(data);
						},
						orderable: false
				  }
				],
				columnDefs: [
				  {
						targets: [2],
						orderData: [2, 1]
				  },
				  {
				    targets: [3],
				    orderData: [3, 4]
				  }
				],
				initComplete: function () {
					$('.dataTables_length select').select2({ minimumResultsForSearch: -1 });
				}
			});

			populateSingleSelect2({
				element: '#<portlet:namespace/>dealerId',
				url: '${dealerURL}',
				type: 'POST',
				placeholderText: 'Pilih Dealer',
				minimumResultsForSearch: 10
			});

			populateSingleSelect2({
				element: '#<portlet:namespace/>tahun',
				url: '${yearURL}',
				type: 'POST',
				placeholderText: 'Pilih Tahun'
			});

			populateSingleSelect2({
				element: '#<portlet:namespace/>kuartalId',
				url: '${quarterURL}',
				type: 'POST',
				placeholderText: 'Pilih Kuartal'
			});

			$('#<portlet:namespace/>searchDiskonOthers select').on('select2:select', function () {
				const value = $(this).val();
				if (value) {
					$(this).siblings('.select2-container--default').find('.select2-selection__rendered').addClass('select2-selection__selected');
				} else {
					$(this).siblings('.select2-container--default').find('.select2-selection__rendered').removeClass('select2-selection__selected');
				}
				diskonOtherTable.ajax.reload();
			});
		});

		function renderActionButton(data) {
			const downloadURL = new URL('${downloadFileURL}');
			downloadURL.searchParams.set('<portlet:namespace/>entryId', data);
			var response =
				'<span class="action-wrapper" data-id="' + data + '">' +
					'<a target="_blank" href="' + downloadURL + '" class="text-center">Unduh Data</a>' +
				'</span>';
			return response;
		}
	})();
</script>