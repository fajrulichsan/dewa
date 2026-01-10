var dealerId = "ALL";
var periodDate = "ALL";
var role = "ALL";
var length = 0;
var dealerName = "";
var startDate = new Date(1900, 0, 0);

$(document).ready(function () {
   // $('.empty-table').hide();
   // $('.period-field').hide();
   // $('.dealer-space').hide();
   getDealers();
   setPeriodDate();
   periodDate = "ALL";

   $('.dataTables_length select').select2({
      minimumResultsForSearch: -1
   });
});

function refresh() {
   // dealerId = "ALL";
   periodDate = "ALL";
   eSrutDataTable.ajax.reload();
   // $('[name=dealerId]')[0].value = "";
   $('#periodDate').val(null);
   // getDealers();
}

var tableLanguage = {
   "lengthMenu": "_MENU_",
   "paginate": {
      "first": "",
      "last": "",
      "next": '<span class="glyphicon glyphicon-menu-right"></span>',
      "previous": '<span class="glyphicon glyphicon-menu-left"></span>'
   },
   "search": "",
   "searchPlaceholder": "Search..."
}

var E_SRUT_NON_CMS_TABLE_NAME = "#e_srut_non_cms_table";
var eSrutDataTable = $(E_SRUT_NON_CMS_TABLE_NAME).DataTable({
   searching: false,
   paging: true,
   info: false,
   responsive: true,
   language: tableLanguage,
   dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
   ajax: {
      url: E_SRUT_LIST_URL,
      type: 'GET',
      data: function (d) {
         d.dealerId = dealerId;
         d.periodDate = periodDate;
      },
      dataSrc: function (json) {
         role = json.role;
         if (role.toLowerCase().startsWith('dealer')) {
            $('.dealerId').hide();
            $('.dealer-space').show();
            dealerId = json.codeDealer;
            dealerName = json.nameDealer;
         }
         /*
         length = json.Data.length;
         tableChecker($(window).width(), E_SRUT_NON_CMS_TABLE_NAME, length);
         */
         return json.Data;
      }
   },
   columns: [
      {data: "no", "width": "5%", className: "text-center"},
      {data: "fileName", "width": "30%"},
      {data: "periodDate", "width": "30%", className: "text-center"},
      {
         data: "fileDate",
         "width": "30%",
         className: "text-center",
         "render": function(data, type, row, meta) {
            if (type === "sort") {
               return row.fileDateSort;
            }
            return data;
         }
      },
      {
         data: "id",
         "width": "5%",
         className: "text-center",
         "render": function (data, type, row, meta) {
            return renderActionButton(data, row);
         },
         orderable: false
      }
   ],
   order: [],
   columnDefs: [
      {
         targets: [2],
         orderData: [2, 3]
      }
   ]
});

function renderActionButton(dataId, row) {
   var downloadURL = new URL(DOWNLOAD_E_SRUT_URL);
   downloadURL.searchParams.set(ENTRY_ID, dataId);
   var response = "";
   response =
         '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a target="_blank" href="' + downloadURL.toString() + '" class="text-center">Unduh Data</a>' +
         '</span>';
   return response;
}

function getDealers() {
   $.ajax({
      url: DEALER_E_SRUT_URL,
      type: "POST",
      data: {"windowsId": "test"},
      success: function (response) {
         var responseData = JSON.parse(response);
         var dealerConst = $('#dealerId').select2({
            data: responseData.Data,
            tags: "true",
            placeholder: 'Pilih Dealer',
            allowClear: false,
            maximumInputLength: 100,
            templateSelection: function (data) {
               if (data.id !== '') {
                  dealerId = data.id;
                  eSrutDataTable.ajax.reload();
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

/*
$('.period-trigger').click(function () {
   $('.dealerId').hide();
   $('.period-field').show();
   $('#periodDate').focus();
});

$('.refresh-trigger').click(function () {
   refresh();
});
*/

function setPeriodDate() {
   $('#periodDate').datepicker({
      forceParse: false,
      startDate: startDate,
      meridiem: '',
      format: 'dd.mm.yyyy',
      autoclose: true
   }).on('changeDate', function (ev) {
      let date = $(this).datepicker( 'getDate' );
      periodDate = moment(date).format("DD.MM.YYYY");
      eSrutDataTable.ajax.reload();
      $(this).datepicker('hide');
   });
}

/*
function setPeriodDate() {
   $('#periodDate').datetimepicker({
      format: 'DD/MM/YYYY',
      locale: 'id',
      showClose: true,
      ignoreReadonly: true,
      useCurrent: false
   }).on('dp.hide', function (e) {
      periodDate = $('#periodDate').val();
      // $('.period-field').hide();
      // $('.dealerId').show();
      // console.log('period date: ' + e.date)
      eSrutDataTable.ajax.reload();
      // $('#periodDate').trigger('blur');
   });
}
*/

$(window).on("resize", function () {
   var width = $(window).width();
   if (width <= 768) {
      eSrutDataTable.page.len(10).draw();
   }
   // tableChecker(width, E_SRUT_NON_CMS_TABLE_NAME, length);
});