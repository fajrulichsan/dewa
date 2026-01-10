<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="upload-bonus-document-list-cms" var="uploadBonusDocumentListURL"/>

<portlet:resourceURL id="upload-dealer" var="dealerUploadBonusURL"/>
<portlet:resourceURL id="upload-application-header-status" var="statusUploadBonusURL"/>
<portlet:resourceURL id="upload-bonus-application-category" var="requestUploadBonusURL"/>

<%--<portlet:resourceURL id="upload-bonus-dealer" var="dealerUploadBonusURL"/>--%>
<%--<portlet:resourceURL id="upload-bonus-application-header-status" var="statusUploadBonusURL"/>--%>

<portlet:resourceURL id="upload-bonus-tahun" var="tahunUploadBonusURL"/>
<portlet:resourceURL id="upload-document-download" var="downloadFileURL"/>

<style>
   .upload_bonus {
      margin: 25px;
   }

   .btn_table, .btn_table:hover {
      border: 0;
      /*background-color: #014689 !important;*/
      background-color: #ffffff !important;
      color: #014689;
      font-weight: 400;
      opacity: 0.8;
      padding: 8px 16px;
      border-radius: 5px;
   }

   #UploadBonusDocumentCMS img {
      padding: 5px 0;
   }

   #UploadBonusDocumentCMS h4 {
      font-weight: 300;
      padding: 0 9px;
      margin: 0 0;
   }

   #UploadBonusDocumentCMS p {
      font-weight: 300;
      padding: 0 9px;
      margin: 0 0;
   }

   #UploadBonusDocumentCMS a {
      font-weight: 700;
      /*padding: 0 16px;*/
   }

   /* Select2 */
   .select2-container--default .select2-selection--single {
      padding: 6px;
      /*margin-top: 16px;*/
      /*margin-bottom: 16px;*/
      height: 37px;
      /*width: 148px;*/
      font-size: 1.2em;
      position: relative;
   }

   /* CUSTOM TUI STYLE */
   .tui-pagination .tui-page-btn {
      border: none !important;
      margin: 0 3px;
      border-radius: 5px;
   }

   .tui-pagination .tui-is-selected, .tui-pagination .tui-is-selected:hover {
      background-color: #014689;
   }

   .tui-last, .tui-first {
      display: none !important;
   }

   .tui-pagination .tui-next, .tui-pagination .tui-prev {
      border: none;
      border-radius: 5px;
   }

   .tui-is-disabled.tui-prev .tui-ico-prev {
      width: 9px;
      height: 16px;
      background: url('<%=request.getContextPath()%>/assets/img/chevron-left-disabled.svg') no-repeat 0 0;
   }

   .tui-prev .tui-ico-prev {
      width: 9px;
      height: 16px;
      background: url('<%=request.getContextPath()%>/assets/img/chevron-left.svg') no-repeat 0 0;
   }

   .tui-is-disabled.tui-next .tui-ico-next {
      width: 9px;
      height: 16px;
      background: url('<%=request.getContextPath()%>/assets/img/chevron-right-disabled.svg') no-repeat 0 0;
   }

   .tui-next .tui-ico-next {
      width: 9px;
      height: 16px;
      background: url('<%=request.getContextPath()%>/assets/img/chevron-right.svg') no-repeat 0 0;
   }

   /* END OF TUI STYLE */

   .card {
      min-height: 27em;
      padding: 10px 10px 20px;
      border-radius: 10px;
   }

   .card-document {
      margin: 0px;
      padding: 10px;
      border-radius: 10px;
   }

   .card-title {
      text-align: left;
      width: 100%;
      font-weight: bold;
      overflow: hidden;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
      padding-top: 5px;
      word-break: break-all;
      max-height: 45px;
   }

   .card-date {
      color: #4A4A4A;
      font-size: 10px;
      font-weight: 400;
      line-height: 12px;
      letter-spacing: 0em;
      text-align: left;
      padding: 0px
   }

   .img-rounded {
      border-radius: 6px;
      background-position: center;
      background-repeat: no-repeat;
      background-size: cover;
   }

   #bonusList {
      margin-bottom: 26px;
      padding: 20px;
   }
</style>

<section id="UploadBonusDocumentCMS">
   <div class="row">
      <div class="col-md-12">
         <h5 style="padding: 5px;margin-bottom: 20px;color: #FFFFFF;background-color: #456b9a;">Hasil Upload Dokumen</h5>
      </div>
   </div>

   <div class="row" style="margin-bottom: 16px;">
      <div class="col-xs-12 col-sm-12 col-md-2">
         <select class="form-control" name="columnId" id="columnId" style="width: 100%;"></select>
      </div>
      <div class="col-xs-12 col-sm-12 col-md-2 requestId">
         <select class="form-control" name="requestId" id="requestId" style="width: 100%;">
            <option value="ALL">Select All</option>
         </select>
      </div>
      <div class="col-xs-12 col-sm-12 col-md-3 dealerList">
         <select class="form-control" name="dealerList" id="dealerList" style="width: 100%;">
            <option value="ALL">Select All</option>
         </select>
      </div>
      <div class="col-xs-12 col-sm-12 col-md-2 ticketList">
         <input type="text" class="form-control" id="ticketList" placeholder="">
      </div>
      <div class="col-xs-12 col-sm-12 col-md-2 ticketDateList">
         <select class="form-control" name="ticketDateList" id="ticketDateList" style="width: 100%;">
            <option value="ALL">Select All</option>
         </select>
      </div>
      <div class="col-xs-12 col-sm-12 col-md-2 statusId">
         <select class="form-control" name="statusId" id="statusId" style="width: 100%;">
            <option value="ALL">Select All</option>
         </select>
      </div>
      <div class="col-xs-12 col-sm-12 col-md-2 tahunId">
         <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
            <option value="ALL">Select All</option>
         </select>
      </div>
      <div class="col-xs-12 col-sm-12 col-md-3" style="text-align: right;"></div>
   </div>
   <div class="row" id="bonusList"></div>
   <div id="pagination" class="tui-pagination"></div>
</section>

<script>
   var dataSet;
   var totalRows = 0 + " rows data";
   var column = "ALL";
   var keyword = "ALL";
   var itemRow = 12;

   var Pagination = tui.Pagination;
   var paginationOptions = {
      usageStatistics: false,
      centerAlign: true,
      totalItems: 0,
      itemsPerPage: itemRow,
      visiblePages: 5
   }

   var container = document.getElementById('pagination');
   var tuiInstance = new Pagination(container, paginationOptions);

   $(document).ready(function () {
      tuiInstance.on('beforeMove', (event) => {
         var currentPage = event.page;
         var itemPerPage = tuiInstance._options.itemsPerPage;
         getUploadBonus(currentPage, itemPerPage, false);
      });
      refresh();
   });

   function refresh() {
      column = 'ALL';
      keyword = 'ALL';
      getDealerUploads();
      getRequestUploads();
      getStatusUploads();
      getTahunUploads();
      getUploadBonus(1, itemRow, true);
      hideSearch();
   }

   function getUploadBonus(currentPage, itemPerPage, isReset) {
      createLoading();
      $.ajax({
         url: "${uploadBonusDocumentListURL}",
         type: "GET",
         data: {
            column: column,
            keyword: keyword,
            currentPage: currentPage,
            itemPerPage: itemPerPage,
            p_auth: Liferay.authToken
         },
         success: function (response) {
            destroyLoading();
            var json = JSON.parse(response);
            dataSet = json.data;
            if (isReset) {
               tuiInstance.reset(json.recordsTotal);
            }

            $("#bonusList").html('');
            var list = "";
            for (var i = 0; i < dataSet.length; i++) {
               var downloadURL = new URL('${downloadFileURL}');
               downloadURL.searchParams.set('fileId', dataSet[i].fileId);
               downloadURL.searchParams.set('p_auth', Liferay.authToken);

               list += '<div class="col-md-3 card-document" >';
               list += '<div class="card">';
               list += `<div class="img-rounded center-block" style="width:100%;height: 15em; background-image: url(` + dataSet[i].fileUrl + `)"></div>`;
               if (dataSet[i].fileExtension === 'png') {
                  list += `<img src="<%=request.getContextPath()%>/assets/img/icon/png.png" style="width: 30px;"></img>`;
               } else if (dataSet[i].fileExtension === 'jpg') {
                  list += `<img src="<%=request.getContextPath()%>/assets/img/icon/jpg.png" style="width: 30px;"></img>`;
               } else if (dataSet[i].fileExtension === 'jpeg') {
                  list += `<img src="<%=request.getContextPath()%>/assets/img/icon/jpeg.png" style="width: 30px;"></img>`;
               } else {
                  list += `<img src="<%=request.getContextPath()%>/assets/img/icon/pdf.png" style="width: 30px;"></img>`;
               }
               list += '<div class="card-title">' + dataSet[i].fileName + '</div>';
               list += '<div class="row" style="margin-top: 15px;">';
               list += '<div class="col-xs-9 col-sm-9 col-md-9 card-date">';
               list += '<p style="margin: 5px 0 0 5px;">' + dataSet[i].reqDate + '</p>';
               list += '</div>';
               list += '<div class="col-xs-3 col-sm-3 col-md-3" style="vertical-align:middle;text-align:center;padding:0;">';
               // list += '<a href="' + dataSet[i].fileUrl + '?download=true" target="_blank">';
               list += '<a href="' + downloadURL + '" target="_blank">'
               list += '<img src="<%=request.getContextPath()%>/assets/img/file_download.svg" style="width:35px;height:35px;margin:0px;">';
               list += '</a>';
               list += '</div>';
               list += '</div>';
               list += '</div>';
               list += '</div>';
            }
            $("#bonusList").html(list);
         },
         error: function (error) {
            destroyLoading();
            console.log(error);
         },
         complete: function () {
            destroyLoading();
            console.log("complete");
         }
      });
   }

   function getDealerUploads() {
      $.ajax({
         url: "${dealerUploadBonusURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var dealerData = JSON.parse(response);
            $('#dealerList').select2({
               data: dealerData.Data,
               tags: false,
               placeholder: 'Pilih Dealer',
               allowClear: false,
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
      console.log(data);
      if (data.id !== '' && data.id !== 'ALL') {
         keyword = data.id;
      } else {
         keyword = 'ALL';
      }
      getUploadBonus(1, itemRow, true);
   });

   function getRequestUploads() {
      $.ajax({
         url: "${requestUploadBonusURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var requestData = JSON.parse(response);
            $('#requestId').select2({
               data: requestData.Data,
               tags: false,
               placeholder: 'Pilih Request',
               allowClear: false,
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
      getUploadBonus(1, itemRow, true)
   })

   // Status
   function getStatusUploads() {
      $.ajax({
         url: "${statusUploadBonusURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var dataStatus = JSON.parse(response);
            $('#statusId').select2({
               data: dataStatus.Data,
               tags: false,
               placeholder: 'Pilih Status',
               allowClear: false,
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
         keyword = data.id;
      } else {
         keyword = 'ALL';
      }
      getUploadBonus(1, itemRow, true);
   })

   // Tahun
   function getTahunUploads() {
      $.ajax({
         url: "${tahunUploadBonusURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var dataStatus = JSON.parse(response);
            $('#tahunId').select2({
               data: dataStatus.Data,
               tags: false,
               placeholder: 'Pilih Tahun',
               allowClear: false,
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
         keyword = data.id;
      } else {
         keyword = 'ALL';
      }
      getUploadBonus(1, itemRow, true);
   })

   // Column
   function hideSearch() {
      $('.requestId').hide();
      $('.dealerList').hide();
      $('.ticketList').hide();
      $('.ticketDateList').hide();
      $('.statusId').hide();
      $('.tahunId').hide();
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
      maximumInputLength: 100,
      minimumResultsForSearch: -1,
   });

   $('#columnId').select2().on('select2:open', function () {
      $('.select2-drop-mask').remove();
   });

   $('#columnId').on('select2:select', function (e) {
      var data = e.params.data;
      hideSearch();
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
         getUploadBonus(1, itemRow, true);
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
      getUploadBonus(1, itemRow, true);
   });

   $("input#ticketList").bind('keyup', function (e) {
      var ticketNumber = $("#ticketList").val();
      if(ticketNumber.length > 2) {
         keyword = ticketNumber;
      } else {
         keyword = 'ALL';
      }
      currentPage = 1;
      getUploadBonus(1, itemRow, true);
   });
</script>