var bonusId = "";
var uploadBonusDataTable
var totalRows = 0 + " rows data"
var column = "ALL"
var keyword = "ALL"
var currentPage = 1;
var itemRow = 10


var Pagination = tui.Pagination;
var paginationOptions = {
   totalItems: 0,
   itemsPerPage: itemRow,
   visiblePages: 3,
   centerAlign: true,
};

var container = document.getElementById('pagination');
var tuiInstance = new Pagination(container, paginationOptions);

$(document).ready(function () {
   tuiInstance.on('beforeMove', (event) => {
      currentPage = event.page;
      getUploadBonusCards(currentPage, itemRow)
   });
   refresh()
});

function refresh() {
   column = 'ALL';
   keyword = 'ALL';
   getDealerUploads()
   getRequestUploads()
   getStatusUploads()
   getTahunUploads()
   getUploadBonus()
   getUploadBonusCards(1, itemRow)
   hideSearch()
}

var languageOptions = {
   "lengthMenu": "_MENU_",
   "paginate": {
      "next": '<span class="glyphicon glyphicon-menu-right"></span>',
      "previous": '<span class="glyphicon glyphicon-menu-left"></span>'
   },
   "search": "",
   searchPlaceholder: "Cari"
}

function getUploadBonus() {
   createLoading();
   uploadBonusDataTable = $('#upload_bonus_table').DataTable({
      destroy: true,
      processing: true,
      serverSide: true,
      paging: true,
      info: true,
      searchDelay: 1000,
      searching: false,
      ordering: true,
      autoWidth: true,
      responsive: true,
      // order: [[6, 'asc']],
      fixedHeader: true,
      language: languageOptions,
      columnDefs: [
         {
            targets: [1, 2, 3, 4, 5, 6],
            orderable: true
         }
      ],
      ajax: {
         url: UPLOAD_BONUS_LIST_URL,
         type: 'GET',
         data: function (d) {
            d.column = column;
            d.keyword = keyword;
            d.currentUrl = window.location.href;
            d.p_auth = Liferay.authToken;
         },
         dataSrc: function (json) {
            destroyLoading();
            return json.data;
         }
      },
      columns: [
         {
            data: "id",
            width: "8%",
            orderable: false,
            className: "text-left",
            render: function (data, type, row, meta) {
               return actionButton(data, row.applicationHeaderStatusId);
            },
         },
         {data: "applicationCategoryName", "width": "10%"},
         {data: "screenName", "width": "20", className: "text-center"},
         {
            data: "dealerName",
            "width": "10%",
            className: "text-left",
            render: function (data, type, row, meta) {
               return row.dealerCode + "<br>" + row.dealerName;
            },
         },
         {
            data: "ticketNo",
            "width": "10%",
            render: $.fn.dataTable.render.ellipsis(25)
         },
         {
            data: "reqDate",
            "width": "10%",
            className: "text-center",
            render: function (data, type, row, meta) {
               return reqDateFormat(data)
            }
         },
         // {
         //    data: "nominalPengajuan",
         //    "width": "10%",
         //    className: "text-right",
         //    render: function (data, type, row, meta) {
         //       return formatNumber(data);
         //    }
         // },
         // {
         //    data: "reqYearId", className: "text-center"
         // },
         {data: "applicationHeaderStatusName", "width": "10%", className: "text-center"}
      ]
   });
}

function reqDateFormat(reqDate) {
   if (reqDate === '-' || reqDate === null) {
      return '-'
   } else {
      return moment(reqDate).format("DD.MM.YYYY")
   }
}

function actionButton(dataId, applicationHeaderStatusId) {
   var editURL = new URL(UPLOAD_BONUS_EDIT_URL);
   var isDisplayRequest = window.location.href.includes("monitoring-request");
   var showEdit = isDisplayRequest && applicationHeaderStatusId === 4 ? "none" : "inline";
   editURL.searchParams.set('id', dataId);
   // editURL.searchParams.set('p_auth', Liferay.authToken);
   var response = "";
   if (applicationHeaderStatusId === 4 || applicationHeaderStatusId === 5) {
      response =
         '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a href="javascript:void(0)" style="margin:0;" onclick="openForm(this)" data-id="' + dataId + '">' +
               '<img src="/o/com.astra.dewa.web/assets/img/fi-rs-search-alt.svg" style="width:20px;height:20px;margin:0 8px 0 0;" />' +
            '</a>' +
            '<a href="' + editURL.toString() + '" style="display: ' + showEdit + '">' +
               '<img src="/o/com.astra.dewa.web/assets/img/fi-rs-edit.svg" style="width:20px;height:20px;margin:0;" />' +
            '</a>' +
         '</span>';
   } else {
      response =
         '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a href="javascript:void(0)" style="margin:0;" onclick="openForm(this)" data-id="' + dataId + '">' +
               '<img src="/o/com.astra.dewa.web/assets/img/fi-rs-search-alt.svg" style="width:20px;height:20px;margin:0;" />' +
            '</a>' +
         '</span>';
   }
   return response;
}

function getUploadBonusCards(currentPage, itemRow) {
   createLoading();
   $.ajax({
      url: UPLOAD_BONUS_LIST_CARD_URL,
      type: "GET",
      data: {
         column: column,
         keyword: keyword,
         currentPage: currentPage,
         itemPerPage: itemRow
      },
      success: function (response) {
         var json = JSON.parse(response);
         if (json.recordsTotal > 0) {
            tuiInstance.resetAndPage(json.recordsTotal, currentPage);
         }
         uploadBonusCards(json.data)
      },
      error: function (error) {
         destroyLoading()
         console.log(error);
      },
      complete: function () {
         destroyLoading()
         console.log("complete");
      }
   });
}

function uploadBonusCards(data) {
   $('#upload_bonus_cards').html('');
   var upload_bonus_cards = [];
   for (var i = 0; i < data.length; i++) {
      var dfrv =
         '<div class="upload_bonus_card">' +
         '<div class="row">' +
         '<div class="col-xs-9">' +
         '<p style="line-height: 17px;margin-top: 7px;">' + data[i].applicationCategoryName + '<br><span>' + data[i].screenName + '</span></p>' +
         '</div>' +
         '<div class="col-xs-3 text-right">' + actionButton(data[i].id, data[i].applicationHeaderStatusId) + '</div>' +
         '</div>' +
         '<div class="row" style="margin-top: 15px;">' +
         '<div class="col-xs-12">' +
         '<table>' +
         '<tr>' +
         '<td style="font-weight: bold;padding-right: 15px;">Dealer</td>' + '<td>' + data[i].dealerName + '</td>' +
         '</tr>' +
         '<tr>' +
         '<td style="font-weight: bold;padding-right: 15px;">Ticket Number</td>' + '<td>' + data[i].ticketNo + '</td>' +
         '</tr>' +
         '<tr>' +
         '<td style="font-weight: bold;padding-right: 15px;">Ticket Date</td>' + '<td>' + reqDateFormat(data[i].reqDate) + '</td>' +
         '</tr>' +
         '<tr>' +
         '<td style="font-weight: bold;padding-right: 15px;">Status</td>' + '<td>' + data[i].applicationHeaderStatusName + '</td>' +
         '</tr>' +
         '</table>' +
         '</div>' +
         '</div>' +
         '</div>';
      upload_bonus_cards.push(dfrv);
   }
   $('#upload_bonus_cards').append($(upload_bonus_cards.join('')));
}

function clearForm() {
   $('[name="entryId"]')[0].value = "";
   $('[name="ticketDate"]')[0].value = "";
   $('[name="ticketHour"]')[0].value = "";
   $('[name="ticketNumber"]')[0].value = "";
   $('[name="userId"]')[0].value = "";
   $('[name="userName"]')[0].value = "";
   $('[name="dealerId"]')[0].value = "";
   $('[name="dealerName"]')[0].value = "";
   // $('[name="approverId"]')[0].value = "";
   $('[name="approverName"]')[0].value = "";
   // Contact Information
   $('[name="email"]')[0].value = "";
   $('[name="emailCC"]')[0].value = "";
   $('[name="phone"]')[0].value = "";
   // Request Information
   $('[name="requestBonusName"]')[0].value = "";
   $('[name="nominalPengajuan"]')[0].value = "";
   $('[name="reqYearId"]')[0].value = "";
   $('[name="requestDescription"]')[0].value = "";
   $('[name="businessBenefit"]')[0].value = "";
   // Notes
   $('[name="notes"]')[0].value = "";
   $('[name="notesHistory"]')[0].value = "";
   // File
   $('#bonusFile').attr('href', "bonusFile");
   $('#bonusFileName').text("")
}

function openForm(element) {
   $('#myModal').modal({
      backdrop: 'static',
      keyboard: false,
      isBodyOverflowing: true
   }).show()
   clearForm()
   bonusId = $(element).data("id");
   getBonus()
}

function hideForm() {
   clearForm()
   $('#myModal').modal('hide')
}

function getBonus() {
   createLoading();
   $.ajax({
      url: UPLOAD_BONUS_SINGLE_URL,
      type: "POST",
      data: {
         "entryId": bonusId,
         'p_auth': Liferay.authToken
      },
      success: function (response) {
         var data = JSON.parse(response);
         var bonus = data["data"];

         // Download URL
         var downloadURL = new URL(DOWNLOAD_URL);
         downloadURL.searchParams.set('id', bonus.id);
         downloadURL.searchParams.set('p_auth', Liferay.authToken);

         $('[name="entryId"]')[0].value = bonus.id;
         $('[name="ticketDate"]')[0].value = bonus.ticketDate;
         $('[name="ticketHour"]')[0].value = bonus.ticketHour;
         $('[name="ticketNumber"]')[0].value = bonus.ticketNumber;
         $('[name="userId"]')[0].value = bonus.userId;
         $('[name="userName"]')[0].value = bonus.userName;
         $('[name="dealerId"]')[0].value = bonus.dealerId;
         $('[name="dealerName"]')[0].value = bonus.dealerName;
         $('[name="approverName"]')[0].value = bonus.approverName;

         // Contact Information
         $('[name="email"]')[0].value = bonus.email;
         $('[name="emailCC"]')[0].value = bonus.emailCC;
         $('[name="phone"]')[0].value = bonus.phone;

         // Request Information
         $('[name="requestBonusName"]')[0].value = bonus.requestBonusName;
         $('[name="nominalPengajuan"]')[0].value = formatNumber(bonus.nominalPengajuan);
         $('[name="reqYearId"]')[0].value = bonus.reqYearId;
         $('[name="requestDescription"]')[0].value = bonus.requestDescription;
         $('[name="businessBenefit"]')[0].value = bonus.businessBenefit;

         // Notes
         $('[name="notes"]')[0].value = bonus.notes;
         $('[name="notesHistory"]')[0].value = bonus.notesHistory;

         // File
         $('#bonusFile').attr('href', downloadURL.toString());
         $('#bonusFileName').text(bonus.attachmentName);

         // // File
         // $('#bonusFile').attr('href', bonus.attachmentName === "" ? "bonusFile" : bonus.attachmentPath + "?download=true");
         // $('#bonusFileName').text(bonus.attachmentName)

         var phoneInput = document.getElementById('phone');
         var emailCC = document.getElementById('emailCC');
         var requestBonusName = document.getElementById('requestBonusName');
         var nominalPengajuan = document.getElementById('nominalPengajuan');
         var reqYearId = document.getElementById('reqYearId');
         var requestDescription = document.getElementById('requestDescription');
         var businessBenefit = document.getElementById('businessBenefit');
         var notes = document.getElementById('notes');
         phoneInput.readOnly = true;
         emailCC.readOnly = true;
         requestBonusName.readOnly = true;
         nominalPengajuan.readOnly = true;
         reqYearId.readOnly = true;
         requestDescription.readOnly = true;
         businessBenefit.readOnly = true;
         notes.readOnly = true;
      },
      error: function (err) {
         console.log(err);
      },
      complete: function () {
         destroyLoading();
      }
   });
}

function formatNumber(nStr) {
   nStr += '';
   x = nStr.split('.');
   x1 = x[0];
   x2 = x.length > 1 ? '.' + x[1] : '';
   var rgx = /(\d+)(\d{3})/;
   while (rgx.test(x1)) {
      // x1 = x1.replace(rgx, '$1' + ',' + '$2');
      x1 = x1.replace(rgx, '$1' + '.' + '$2');
   }
   return x1 + x2;
}

function updateStatus(statusId, statusName) {
   const notes = $('[name="notes"]').val();
   if (notes === null || notes === undefined || notes.length < 9) {
      Swal.fire("Mohon isi notes minimal 9 karakter", "", "warning");
      return false;
   } else {
      Swal.fire({
         // icon: 'question',
         title: 'Confirmation',
         text: statusName + ' data?',
         showCancelButton: true,
         confirmButtonText: 'Yes',
         confirmButtonColor: '#EE1C25',
      }).then((result) => {
         if (result.isConfirmed) {
            var formData = new FormData();
            formData.set("crudType", "updateStatus");
            formData.set("bonusId", bonusId);
            formData.set("statusId", statusId);
            formData.set("notes", notes);
            formData.set('p_auth', Liferay.authToken);

            $.ajax({
               url: UPLOAD_BONUS_UPDATE_STATUS_ACTION_URL,
               type: "POST",
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var data = JSON.parse(response);
                  if (data["acknowledge"] === 1) {
                     Swal.fire({
                        // icon: 'question',
                        title: 'Information',
                        text: "Failed to " + data["message"]
                     });
                  } else {
                     Swal.fire({
                        // icon: 'question',
                        title: 'Information',
                        text: "Successfully " + data["message"]
                     }).then((res) => {
                        getUploadBonus()
                        hideForm();
                        getBonus()
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
}

function getDealerUploads() {
   $.ajax({
      url: DEALER_UPLOAD_BONUS_URL,
      type: "POST",
      data: {
         'p_auth': Liferay.authToken
      },
      success: function (response) {
         var dealerData = JSON.parse(response);
         $('#dealerList').select2({
            data: dealerData.Data,
            tags: false,
            placeholder: 'Pilih Dealer',
            allowClear: false,
            // minimumInputLength: 3,
            maximumInputLength: 100
         });
      },
      error: function (error) {
         console.log(error);
      },
      complete: function () {
         console.log("complete");
      }
   });
}

$('#dealerList').select2().on('select2:open', function () {
   $('.select2-drop-mask').remove();
});

$('#dealerList').on('select2:select', function (e) {
   var data = e.params.data;
   if (data.id !== '' && data.id !== 'ALL') {
      keyword = data.id;
   } else {
      keyword = 'ALL'
   }
   currentPage = 1
   getUploadBonusCards(currentPage, itemRow)
   getUploadBonus()
})

function getRequestUploads() {
   $.ajax({
      url: REQUEST_UPLOAD_BONUS_URL,
      type: "POST",
      data: {"windowsId": "test"},
      success: function (response) {
         var requestData = JSON.parse(response);
         $('#requestId').select2({
            data: requestData.Data,
            tags: false,
            placeholder: 'Pilih Request',
            allowClear: false,
            // minimumInputLength: 3,
            maximumInputLength: 100,
         });
      },
      error: function (error) {
         console.log(error);
      },
      complete: function () {
         console.log("complete");
      }
   });
}

$('#requestId').select2().on('select2:open', function () {
   $('.select2-drop-mask').remove();
});

$('#requestId').on('select2:select', function (e) {
   var data = e.params.data;
   if (data.id !== '' && data.id !== 'ALL') {
      keyword = data.id;
   } else {
      keyword = 'ALL'
   }
   currentPage = 1
   getUploadBonusCards(currentPage, itemRow)
   getUploadBonus()
})

$("input#ticketList").bind('keyup', function (e) {
   var ticketNumber = $("#ticketList").val()
   if (ticketNumber.length > 2) {
      keyword = ticketNumber
   } else {
      keyword = 'ALL'
   }
   currentPage = 1
   getUploadBonusCards(currentPage, itemRow)
   getUploadBonus()
});

// Status
function getStatusUploads() {
   $.ajax({
      url: STATUS_UPLOAD_BONUS_URL,
      type: "POST",
      data: {
         'p_auth': Liferay.authToken
      },
      success: function (response) {
         var dataStatus = JSON.parse(response);
         $('#statusId').select2({
            data: dataStatus.Data,
            tags: false,
            placeholder: 'Pilih Status',
            allowClear: false,
            // minimumInputLength: 3,
            maximumInputLength: 100
         });
      },
      error: function (error) {
         console.log(error);
      },
      complete: function () {
         console.log("complete");
      }
   });
}

$('#statusId').select2().on('select2:open', function () {
   $('.select2-drop-mask').remove();
});

$('#statusId').on('select2:select', function (e) {
   var data = e.params.data;
   if (data.id !== '' && data.id !== 'ALL') {
      keyword = data.id
   } else {
      keyword = 'ALL'
   }
   currentPage = 1
   getUploadBonusCards(currentPage, itemRow)
   getUploadBonus()
})

// Tahun
function getTahunUploads() {
   $.ajax({
      url: TAHUN_UPLOAD_BONUS_URL,
      type: "POST",
      data: {"windowsId": "test"},
      success: function (response) {
         var dataStatus = JSON.parse(response);
         $('#tahunId').select2({
            data: dataStatus.Data,
            tags: false,
            placeholder: 'Pilih Tahun',
            allowClear: false,
            // minimumInputLength: 3,
            maximumInputLength: 100
         });
      },
      error: function (error) {
         console.log(error);
      },
      complete: function () {
         console.log("complete");
      }
   });
}

$('#tahunId').select2().on('select2:open', function () {
   $('.select2-drop-mask').remove();
});

$('#tahunId').on('select2:select', function (e) {
   var data = e.params.data;
   if (data.id !== '' && data.id !== 'ALL') {
      keyword = data.id
   } else {
      keyword = 'ALL'
   }
   currentPage = 1
   getUploadBonusCards(currentPage, itemRow)
   getUploadBonus()
})

// Column
function hideSearch() {
   $('.requestId').hide()
   $('.dealerList').hide()
   $('.ticketList').hide()
   $('.ticketDateList').hide()
   $('.statusId').hide()
   $('.tahunId').hide()
}

var columnData = [
   {id: "ALL", text: "Select All"},
   {id: "REQUEST_CATEGORY", text: "Request Category"},
   {id: "DEALER_NAME", text: "Dealer"},
   {id: "TICKET_NUMBER", text: "Ticket Number"},
   {id: "TICKET_DATE", text: "Ticket Date"},
   {id: "STATUS", text: "Status"},
   {id: "TAHUN", text: "Tahun"}
]

$('#columnId').select2({
   data: columnData,
   tags: false,
   placeholder: 'Select a category',
   allowClear: false,
   // minimumInputLength: 3,
   maximumInputLength: 100,
   minimumResultsForSearch: -1,
   // minimumResultsForSearch: Infinity
});

$('#columnId').select2().on('select2:open', function () {
   $('.select2-drop-mask').remove();
});

$('#columnId').on('select2:select', function (e) {
   var data = e.params.data;
   hideSearch()
   if (data.id !== '' && data.id !== 'Select a category') {
      column = data.id
      keyword = 'ALL'
      if (data.id === 'REQUEST_CATEGORY') {
         $('.requestId').show();
      } else if (data.id === 'DEALER_NAME') {
         $('.dealerList').show();
         getDealerUploads()
      } else if (data.id === 'TICKET_NUMBER') {
         $('.ticketList').show();
      } else if (data.id === 'TICKET_DATE') {
         $('.ticketDateList').show();
      } else if (data.id === 'STATUS') {
         $('.statusId').show();
      } else if (data.id === 'TAHUN') {
         $('.tahunId').show();
      }
      currentPage = 1
      getUploadBonus();
   }
})

// Bulan
var bulanData = [
   {id: "1", text: "Januari"},
   {id: "2", text: "Februari"},
   {id: "3", text: "Maret"},
   {id: "4", text: "April"},
   {id: "5", text: "Mei"},
   {id: "6", text: "Juni"},
   {id: "7", text: "Juli"},
   {id: "8", text: "Agustus"},
   {id: "9", text: "September"},
   {id: "10", text: "Oktober"},
   {id: "11", text: "November"},
   {id: "12", text: "Desember"}
]

$('#ticketDateList').select2({
   data: bulanData,
   tags: false,
   placeholder: 'Select Ticket Date',
   allowClear: false,
   // minimumInputLength: 3,
   maximumInputLength: 100,
   minimumResultsForSearch: -1
});

$('#ticketDateList').select2().on('select2:open', function () {
   $('.select2-drop-mask').remove();
});

$('#ticketDateList').on('select2:select', function (e) {
   var data = e.params.data;
   if (data.id !== '' && data.id !== 'Select Ticket Date') {
      keyword = data.id;
   } else {
      keyword = 'ALL';
   }
   currentPage = 1;
   getUploadBonusCards(currentPage, itemRow);
   getUploadBonus();
})
