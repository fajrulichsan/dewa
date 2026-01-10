var dealerId = "ALL";
var periode = "ALL";
var role = "ALL";
var length;
var startDate = new Date(1900, 0, 0);

$(document).ready(function () {
   $('.empty-table').hide();
   getDealers();
   setFakturDatepicker();
   $('.dataTables_length select').select2({
      minimumResultsForSearch: -1
   });
});

function refresh() {
	periode = "ALL";
	fakturPajakDataTable.ajax.reload();
	$("[name=periode-date]")[0].value = "";
}

var tableLanguage = {
	lengthMenu: "_MENU_",
	paginate: {
		first: "",
		last: "",
		next: '<span class="glyphicon glyphicon-menu-right"></span>',
		previous: '<span class="glyphicon glyphicon-menu-left"></span>',
	},
	search: "",
	searchPlaceholder: "Search..."
};

var FAKTUR_PAJAK_NON_CMS_TABLE_NAME = "#faktur_pajak_non_cms_table";
var fakturPajakDataTable = $(FAKTUR_PAJAK_NON_CMS_TABLE_NAME).DataTable({
	searching: false,
	paging: true,
	info: false,
	language: tableLanguage,
	dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
	ajax: {
		url: FAKTUR_PAJAK_LIST_URL,
		type: "GET",
		data: function (d) {
			d.dealerId = dealerId;
			d.invoiceDate = periode;
		},
		dataSrc: function (json) {
			role = json.role;
			if (role.toLowerCase().startsWith("dealer")) {
				$(".dealerId").hide();
				$(".white-space").removeClass("col-md-6").addClass("col-md-9");
				dealerId = json.codeDealer;
				dealerName = json.nameDealer;
			}
			return json.Data;
		},
	},
	columns: [
		{ data: "no", width: "5%", className: "text-center" },
		{ data: "fileName", width: "30%" },
		{
			data: "invoiceDate",
			width: "30%",
			className: "text-center",
			render: function (data, type, row, meta) {
				if (type === "sort") {
					return row.invoiceDateSort;
				}
				return data;
			},
		},
		{
			data: "uploadDate",
			width: "30%",
			className: "text-center",
			render: function (data, type, row, meta) {
				if (type === "sort") {
					return row.uploadDateSort;
				}
				return data;
			},
		},
		{
			data: "id",
			width: "5%",
			className: "text-center",
			render: function (data, type, row, meta) {
				console.log(data);
            return renderActionButton(data, row);
         },
         orderable: false,
		},
	],
	responsive: true,
	order: [],
	columnDefs: [
		{
			targets: [2],
			orderData: [2, 1],
		},
	],
});

function renderActionButton(dataId, row) {
	var downloadURL = new URL(DOWNLOAD_FAKTUR_PAJAK_URL);
	downloadURL.searchParams.set(FAKTUR_PAJAK_ENTRY_ID, dataId);
	var response = "";
	response =
        '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a target="_blank" href="' + downloadURL.toString() + '" class="text-center">Unduh Data</a>' +
        '</span>';
	return response;
}

function getDealers() {
   $.ajax({
      url: DEALER_FAKTUR_PAJAK_URL,
      type: "POST",
      data: {},
      success: function (response) {
         var responseData = JSON.parse(response);
         var dealerConst = $('#dealerId').select2({
            data: responseData.Data,
            tags: "true",
            placeholder: 'List Dealer',
            allowClear: false,
            maximumInputLength: 100,
            templateSelection: function (data) {
               if (data.id !== '') {
                  dealerId = data.id;
                  fakturPajakDataTable.ajax.reload();
               }
               return data.text;
            }
         });
         dealerConst.val(null);
         dealerConst.trigger('change');
      },
      error: function (error) {
         console.log(error);
      },
      complete: function () {
         console.log("complete");
      }
   });
}

function setFakturDatepicker(){
   $('[name=periode-date]').datepicker({
      forceParse: false,
      startDate: startDate,
      meridiem: '',
      format: 'dd.mm.yyyy',
      autoclose: true
   }).on('changeDate', function (ev) {
      let date = $(this).datepicker( 'getDate' );
      periode = moment(date).format("DD.MM.YYYY");
      fakturPajakDataTable.ajax.reload();
      $(this).datepicker('hide');
   });
}

$(window).on("resize", function () {
	var width = $(window).width();
	if (width < 768) {
		fakturPajakDataTable.page.len(10).draw();
	}
});