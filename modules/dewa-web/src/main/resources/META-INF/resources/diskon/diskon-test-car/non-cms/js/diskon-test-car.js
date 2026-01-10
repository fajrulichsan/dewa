var dealer = "ALL";
var tahun = "ALL";
var kuartal = "ALL";
var tipeKendaraan = "ALL";
var role = "ALL";
var length = 0;
var dealerName = "";

$(document).ready(function () {
   getDealers();
   getTahuns();
   getTipeKendaraans();
   getKuartals();
   $('.dataTables_length select').select2({
      minimumResultsForSearch: -1
   });
});

function refresh() {
   dealer = "ALL";
   tahun = "ALL";
   kuartal = "ALL";
   tipeKendaraan = "ALL";
   diskonTestCarDataTable.ajax.reload();
   $('[name=dealerId]')[0].value = "";
   $('[name=tahunId]')[0].value = "";
   $('[name=kuartalId]')[0].value = "";
   $('[name=tipeKendaraanId]')[0].value = "";
   getDealers();
   getTahuns();
   getTipeKendaraans();
   getKuartals();
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

var DISKON_TEST_CAR_NON_CMS_TABLE_NAME = "#diskon_test_car_non_cms_table";
var diskonTestCarDataTable = $(DISKON_TEST_CAR_NON_CMS_TABLE_NAME).DataTable({
   searching: false,
   paging: true,
   info: false,
   responsive: true,
   language: tableLanguage,
   dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
   ajax: {
      url: DISKON_TEST_CAR_LIST_URL,
      type: 'GET',
      data: function (d) {
         d.dealer = dealer;
         d.tahun = tahun;
         d.kuartal = kuartal;
         d.tipeKendaraan = tipeKendaraan;
      },
      dataSrc: function (json) {
         role = json.role;
         if (role.toLowerCase().startsWith('dealer')) {
            $(".dealerId").remove();
            if (!($(window).width() > 768)) {
               $('.dealer-space').hide();
            }
            dealer = json.codeDealer;
            dealerName = json.nameDealer;
         } else {
            $('.dealer-space').hide();
         }
         /*
         length = json.Data.length;
         tableChecker($(window).width(), DISKON_TEST_CAR_NON_CMS_TABLE_NAME, length);
         */
         // diskonFakpolResponsiveTableChecker("#diskon_test_car_non_cms_table");
         return json.Data;
      }
   },
   columns: [
      {data: "no", "width": "5%", className: "text-center"},
      {data: "fileName", "width": "30%"},
      {data: "tahun", "width": "10%", className: "text-center"},
      {data: "kuartalName", "width": "10%", className: "text-center"},
      {data: "tipeKendaraanName", "width": "10%", className: "text-center"},
      {
         data: "uploadDate",
         "width": "30%",
         className: "text-center",
         "render": function(data, type, row, meta) {
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
   order: [],
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
   var downloadURL = new URL(DOWNLOAD_DISKON_TEST_CAR_URL);
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
      url: DEALER_DISKON_TEST_CAR_URL,
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
                  diskonTestCarDataTable.ajax.reload();
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
      url: TAHUN_DISKON_TEST_CAR_URL,
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
                  diskonTestCarDataTable.ajax.reload();
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

function getTipeKendaraans() {
   $.ajax({
      url: TIPE_KENDARAAN_DISKON_TEST_CAR_URL,
      type: "POST",
      data: {"windowsId": "test"},
      success: function (response) {
         var tipeKendaraanData = JSON.parse(response);
         var tipeKendaraanConst = $('#tipeKendaraanId').select2({
            data: tipeKendaraanData.Data,
            tags: "true",
            placeholder: 'Pilih Tipe Kendaraan',
            allowClear: false,
            maximumInputLength: 100,
            templateSelection: function (data) {
               if (data.id !== '') {
                  tipeKendaraan = data.id;
                  diskonTestCarDataTable.ajax.reload();
               }
               return data.text;
            }
         });
         tipeKendaraanConst.val(null);
         tipeKendaraanConst.trigger('change');
      },
      error: function (error) {
         console.log(error);
      },
      complete: function () {
         console.log("complete");
      }
   });
}

function getKuartals() {
   $.ajax({
      url: KUARTAL_DISKON_TEST_CAR_URL,
      type: "POST",
      data: {"windowsId": "test"},
      success: function (response) {
         var kuartalData = JSON.parse(response);
         var kuartalConst = $('#kuartalId').select2({
            data: kuartalData.Data,
            tags: "true",
            placeholder: 'Pilih Kuartal',
            allowClear: false,
            maximumInputLength: 100,
            templateSelection: function (data) {
               if (data.id !== '') {
                  kuartal = data.id;
                  diskonTestCarDataTable.ajax.reload();
               }
               return data.text;
            }
         });
         kuartalConst.val(null);
         kuartalConst.trigger('change');
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
      diskonTestCarDataTable.page.len(10).draw();
   }
   // tableChecker(width, DISKON_TEST_CAR_NON_CMS_TABLE_NAME, length);
});