var dealer = "ALL";
var tahun = "ALL";
var bulan = "ALL";
var role = "ALL";
var length = 0;
var dealerName = "";

$(document).ready(function () {
   getDealers();
   getTahuns();
   getBulans();
   $('.dataTables_length select').select2({
      minimumResultsForSearch: -1
   });
});

function refresh() {
   dealer = "ALL";
   tahun = "ALL";
   bulan = "ALL";
   diskonFakpolDataTable.ajax.reload();
   $('[name=dealerId]')[0].value = "";
   $('[name=tahunId]')[0].value = "";
   $('[name=bulanId]')[0].value = "";
   getDealers();
   getTahuns();
   getBulans();
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

var DISKON_FAKPOL_NON_CMS_TABLE_NAME = "#diskon_fakpol_non_cms_table";
var diskonFakpolDataTable = $(DISKON_FAKPOL_NON_CMS_TABLE_NAME).DataTable({
   searching: false,
   paging: true,
   info: false,
   responsive: true,
   language: tableLanguage,
   dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
   ajax: {
      url: DISKON_FAKPOL_LIST_URL,
      type: 'GET',
      data: function (d) {
         d.dealer = dealer;
         d.tahun = tahun;
         d.periode = bulan;
      },
      dataSrc: function (json) {
         role = json.role;
         if (role.toLowerCase().startsWith('dealer')) {
            $('.dealerId').hide();
            $('.dealer-space').removeClass('col-md-3').addClass('col-md-6');
            dealer = json.codeDealer;
            dealerName = json.nameDealer;
         }
         /*
         length = json.Data.length;
         tableChecker($(window).width(), DISKON_FAKPOL_NON_CMS_TABLE_NAME, length);
         */
         return json.Data;
      }
   },
   columns: [
      {data: "no", "width": "5%", className: "text-center"},
      {data: "fileName", "width": "30%"},
      {data: "tahun", "width": "10%", className: "text-center"},
      {
         data: "periodNumber",
         "width": "20%",
         className: "text-center",
         "render": function (data, type, row) {
            if (type === "display") {
               return row.periode;
            }
            return data;
         }
      },
      {
         data: "uploadDate",
         "width": "30%",
         className: "text-center",
         "render": function (data, type, row, meta) {
            if (type === "sort") {
               return row.uploadDateSort;
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
   // order: [],
   order: [[0, "asc"]],
   columnDefs: [
      {
         targets: [2],
         orderData: [2, 1]
      },
      {
         targets: [3],
         orderData: [3, 4]
      }
   ]
});

function renderActionButton(dataId, row) {
   var downloadURL = new URL(DOWNLOAD_DISKON_FAKPOL_URL);
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
      url: DEALER_DISKON_FAKPOL_URL,
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
                  dealer = data.id;
                  diskonFakpolDataTable.ajax.reload();
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

function getTahuns() {
   $.ajax({
      url: TAHUN_DISKON_FAKPOL_URL,
      type: "POST",
      data: {"windowsId": "test"},
      success: function (response) {
         var tahunData = JSON.parse(response);
         var tahunConst = $('#tahunId').select2({
            data: tahunData.Data,
            tags: "true",
            placeholder: 'Pilih Tahun',
            allowClear: false,
            maximumInputLength: 100,
            templateSelection: function (data) {
               if (data.id !== '') {
                  tahun = data.id;
                  diskonFakpolDataTable.ajax.reload();
               }
               return data.text;
            }
         });
         tahunConst.val(null);
         tahunConst.trigger('change');
      },
      error: function (error) {
         console.log(error);
      },
      complete: function () {
         console.log("complete");
      }
   });
}

function getBulans() {
   $.ajax({
      url: BULAN_DISKON_FAKPOL_URL,
      type: "POST",
      data: {"windowsId": "test"},
      success: function (response) {
         var bulanData = JSON.parse(response);
         var bulanConst = $('#bulanId').select2({
            data: bulanData.Data,
            tags: "true",
            placeholder: 'Pilih Bulan',
            allowClear: false,
            maximumInputLength: 100,
            templateSelection: function (data) {
               if (data.id !== '') {
                  bulan = data.id;
                  diskonFakpolDataTable.ajax.reload();
               }
               return data.text;
            }
         });
         bulanConst.val(null);
         bulanConst.trigger('change');
      },
      error: function (error) {
         console.log(error);
      },
      complete: function () {
         console.log("complete");
      }
   });
}

$(window).on("resize", function () {
   var width = $(window).width();
   if (width < 768) {
      diskonFakpolDataTable.page.len(10).draw();
   }
   // tableChecker(width, DISKON_FAKPOL_NON_CMS_TABLE_NAME, length);
});