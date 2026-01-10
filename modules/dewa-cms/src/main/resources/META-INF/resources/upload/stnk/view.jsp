<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="upload-stnk-list" var="uploadStnkListURL"/>
<portlet:resourceURL id="upload-stnk-single" var="uploadStnkSingleURL"/>
<portlet:resourceURL id="upload-stnk-action" var="uploadStnkActionURL"/>
<portlet:resourceURL id="upload-stnk-update-status-action" var="uploadStnkUpdateStatusActionURL"/>
<portlet:renderURL var="uploadStnkEditURL">
   <portlet:param name="mvcRenderCommandName" value="upload-stnk-edit"/>
</portlet:renderURL>
<portlet:renderURL var="uploadStnkDocumentURL">
   <portlet:param name="mvcRenderCommandName" value="upload-stnk-document"/>
</portlet:renderURL>

<portlet:resourceURL id="upload-stnk-dealer" var="dealerUploadStnkURL"/>
<portlet:resourceURL id="upload-stnk-application-category" var="requestUploadStnkURL"/>
<portlet:resourceURL id="upload-stnk-application-header-status" var="statusUploadStnkURL"/>
<portlet:resourceURL id="upload-stnk-tahun" var="tahunUploadStnkURL"/>

<portlet:resourceURL id="upload-document-download" var="downloadFileURL"/>

<style>
   #upload-stnk-table > thead {
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

   table#upload-stnk-table {
      border: 1px solid #DCDFE3;
      border-radius: 10px 10px 10px 10px;
   }

   table#upload-stnk-table {
      border-radius: 10px 10px 10px 10px;
   }

   #upload-stnk-table tbody tr {
      border: 1px solid #DCDFE3;
   }

   #upload-stnk-table > thead > tr > th {
      font-weight: normal;
      text-align: center;
      vertical-align: middle;
   }

   #upload-stnk-table table td {
      vertical-align: middle;
   }

   .btn_table, .btn_table:link, .btn_table:hover {
      border: 0;
      background-color: #014689 !important;
      color: white;
      font-weight: 400;
      padding: 8px 16px;
   }

   /*Select2*/
   .select2-container--default .select2-selection--single {
      padding: 6px;
      height: 37px;
      font-size: 1.2em;
      position: relative;
   }

   .modal-dialog {
      left: 0;
      right: 0;
   }

   .modal-body {
      max-height: calc(100vh - 212px);
      overflow-y: auto;
   }
</style>

<div class="upload-stnk">
   <div class="tabcontent">
      <ul class="nav nav-tabs" role="tablist">
         <li class="nav-item inbox-tab">
            <a class="nav-link" id="inbox-nav-tab">Inbox Request</a>
         </li>
         <li class="nav-item active">
            <a class="nav-link" id="display-nav-tab">Display Request</a>
         </li>
      </ul>

      <div>
         <div class="row">
            <div class="col-md-12">
               <h5 style="padding: 5px;margin-bottom: 20px;color: #FFFFFF;background-color: #456b9a;">Check Inbox List
                  of
                  Ticket</h5>
            </div>
         </div>
         <div class="row" style="margin-bottom: 15px;">
            <div class="col-xs-12 col-sm-12 col-md-2">
               <label for="columnId"></label>
               <select class="form-control" name="columnId" id="columnId" style="width: 100%;"></select>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-2 requestId">
               <label for="requestId"></label>
               <select class="form-control" name="requestId" id="requestId" style="width: 100%;">
                  <option value="ALL">Select All</option>
               </select>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-3 dealerList">
               <label for="dealerList"></label>
               <select class="form-control" name="dealerList" id="dealerList" style="width: 100%;">
                  <option value="ALL">Select All</option>
               </select>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-4 ticketList">
               <label for="ticketList"></label>
               <input type="text" class="form-control" id="ticketList" placeholder="">
            </div>
            <div class="col-xs-12 col-sm-12 col-md-2 ticketDateList">
               <label for="ticketDateList"></label>
               <select class="form-control" name="ticketDateList" id="ticketDateList" style="width: 100%;">
                  <option value="ALL">Select All</option>
               </select>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-2 statusId">
               <label for="statusId"></label>
               <select class="form-control" name="statusId" id="statusId" style="width: 100%;">
                  <option value="ALL">Select All</option>
               </select>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-2 tahunId">
               <label for="tahunId"></label>
               <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
                  <option value="ALL">Select All</option>
               </select>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-3" style="text-align: right;"></div>
         </div>
         <div class="row document-button-field" style="margin-bottom: 15px;">
            <div class="col-xs-12 col-md-6">
               <button class="btn btn-info btn_table btnDownload" onclick="getChecks()">
                  Download Report
               </button>
            </div>
            <div class="col-xs-12 col-md-6" style="text-align: right;">
               <form class="form-inline">
                  <a href="${uploadStnkDocumentURL}" class="btn btn-info btn_table btnDocument">
                     <span>Lihat Dokumen</span>
                  </a>
               </form>
            </div>
         </div>

         <table id="upload-stnk-table" class="table table-hover nowrap" style="width:100%; margin: 15px 0 36px 0;">
            <thead>
            <tr>
               <th>No</th>
               <th>Action</th>
               <th>Request Category</th>
               <th>Requester</th>
               <th>Dealer</th>
               <th>Ticket Number</th>
               <th>Ticket Date</th>
               <th>Status</th>
            </tr>
            </thead>
            <tbody></tbody>
         </table>
         <div class="row" style="margin-bottom: 36px;"></div>
      </div>
   </div>
</div>

<%-- Modal --%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
   <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
         <div class="modal-body">
            <form data-toggle="validator" role="form" class="form-horizontal">
               <div class="row">
                  <div class="col-md-12">
                     <h5 style="padding: 5px;margin-bottom: 20px;color: #FFFFFF;background-color: #456b9a;">General
                        Information</h5>
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-6 col-lg-6">
                     <input type="hidden" class="form-control" id="entryId" name="entryId"/>
                     <div class="form-group">
                        <label for="ticketDate" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Ticket
                           Date</label>
                        <div class="col-xs-7 col-sm-7 col-md-5 col-lg-5">
                           <input type="text" class="form-control" name="ticketDate" id="ticketDate" readonly>
                        </div>
                        <div class="col-xs-5 col-sm-5 col-md-3 col-lg-3">
                           <label for="ticketHour"></label>
                           <input type="text" class="form-control" id="ticketHour" name="ticketHour" placeholder="Auto"
                                  readonly/>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="ticketNumber" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Ticket
                           Number</label>
                        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                           <input type="text" class="form-control" id="ticketNumber" name="ticketNumber"
                                  placeholder="Auto Generate" readonly>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="userId" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">User ID</label>
                        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                           <input type="text" class="form-control" id="userId" name="userId" placeholder="Auto"
                                  readonly>
                        </div>
                     </div>
                  </div>
                  <div class="col-md-6 col-lg-6">
                     <div class="form-group">
                        <label for="userName" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Request
                           Name</label>
                        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                           <input type="text" class="form-control" id="userName" name="userName" placeholder="Auto"
                                  readonly>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="dealerName" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Cabang
                           Dealer</label>
                        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                           <input type="hidden" class="form-control" id="dealerId" name="dealerId" readonly>
                           <input type="text" class="form-control" id="dealerName" name="dealerName" placeholder="Auto"
                                  readonly>
                        </div>
                     </div>
                  </div>
               </div>

               <div class="row">
                  <div class="col-md-12">
                     <h5 style="padding: 5px;margin-bottom: 20px;color: #FFFFFF;background-color: #456b9a;">Contact
                        Information</h5>
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="email" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Email</label>
                        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                           <input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" data-minlength="6"
                                  maxlength="35" class="form-control" id="email" name="email" placeholder="Email"
                                  data-error="Minimal 6-35 karakter & Email harus valid." required readonly/>
                           <div class="help-block with-errors"></div>
                        </div>
                     </div>
                  </div>
               </div>

               <div class="row">
                  <div class="col-md-12">
                     <h5 style="padding: 5px;margin-bottom: 20px;color: #FFFFFF;background-color: #456b9a;">Request
                        Information</h5>
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="requestBonusName" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Request
                           Category <span style="color: red;">*</span></label>
                        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                           <input type="text" class="form-control" id="requestBonusName" name="requestBonusName"
                                  readonly/>
                        </div>
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="nominalPengajuan" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Nominal
                           Pengajuan <span style="color: red;">*</span></label>
                        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                           <input type="text" class="form-control" id="nominalPengajuan" name="nominalPengajuan"
                                  placeholder="" readonly/>
                        </div>
                     </div>
                  </div>
               </div>

               <div class="row">
                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="reqYearId" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Tahun <span
                              style="color: red;">*</span></label>
                        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                           <input type="text" class="form-control" id="reqYearId" name="reqYearId" placeholder=""
                                  readonly/>
                        </div>
                     </div>
                  </div>
               </div>

               <div class="row">
                  <div class="col-md-12">
                     <div class="form-group">
                        <label for="requestDescription" class="col-xs-12 col-sm-12 col-md-2 col-lg-2 control-label">Request
                           Description <span style="color: red;">*</span></label>
                        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
                           <textarea class="form-control" rows="3" id="requestDescription" name="requestDescription"
                                     placeholder="" readonly></textarea>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="businessBenefit" class="col-xs-12 col-sm-12 col-md-2 col-lg-2 control-label">Business
                           Benefit <span style="color: red;">*</span></label>
                        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
                           <textarea class="form-control" rows="3" id="businessBenefit" name="businessBenefit"
                                     placeholder="" readonly></textarea>
                        </div>
                     </div>
                     <div class="form-group">
                        <label class="col-xs-12 col-sm-12 col-md-2 col-lg-2 control-label">Attachment <span
                              style="color: red;">*</span></label>
                        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
                           <div class="input-group">
                              <a href="bonusFile" id="bonusFile" class="btn btn-ipr btn-info btn_table" target="_blank">
                                 <span>Download File</span>
                              </a>
                              <span id="bonusFileName" style="margin-left: 15px;font-weight: bold;"></span>
                           </div>
                           <label class="col-form-label ipr-gray">Note: Maksimum Document Size 5 MB (pdf, jpg, jpeg,
                              png)</label>
                        </div>
                     </div>
                  </div>
               </div>

               <div class="row">
                  <div class="col-md-12">
                     <h5 style="padding: 5px;margin-bottom: 20px;color: #FFFFFF;background-color: #456b9a;">Notes</h5>
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-12">
                     <div class="form-group">
                        <label for="notes" class="col-xs-12 col-sm-12 col-md-2 col-lg-2 control-label">Notes
                           <span style="color: red;">*</span></label>
                        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
                           <textarea class="form-control" rows="3" id="notes" name="notes" placeholder=""></textarea>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="businessBenefit" class="col-xs-12 col-sm-12 col-md-2 col-lg-2 control-label">Notes
                           History</label>
                        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
                           <textarea class="form-control" rows="9" id="notesHistory" name="notesHistory" placeholder=""
                                     readonly></textarea>
                        </div>
                     </div>
                  </div>
               </div>
            </form>
         </div>
         <div class="modal-footer btnDso">
            <button class="btn btn-ipr btn-success" type="button" onclick="updateStatus('2', 'Approve')">APPROVE
            </button>
            <button class="btn btn-ipr btn-warning" type="button" onclick="updateStatus('4', 'Revise')">REVISE</button>
            <button class="btn btn-ipr btn-warning" type="button" onclick="updateStatus('3', 'Reject')">REJECT</button>
            <button class="btn btn-ipr-cancel" onclick="hideForm();">Cancel</button>
         </div>
         <div class="modal-footer btnHo">
            <button class="btn btn-ipr-cancel" onclick="hideForm();">Cancel</button>
         </div>
      </div>
   </div>
</div>

<script>
   var bonusId = "";
   var uploadStnkDataTable;
   var totalRows = 0 + " rows data";
   var column = "ALL";
   var keyword = "ALL";
   var dataUploadBonuses = [];
   var isDisplayActive = true;

   $(document).ready(function () {
      refresh();
   });

   $('.nav-tabs li.nav-item').on('click', function (e) {
      e.preventDefault();
      $(this).parent().find('li.active').removeClass('active');
      $(this).addClass('active');
   });

   function refresh() {
      column = 'ALL';
      keyword = 'ALL';
      getDealerUploads();
      getRequestUploads();
      getStatusUploads();
      getTahunUploads();
      getUploadBonus();
      hideSearch();
      $('#columnId').val("ALL").trigger('change');
   }

   $('.btnDocument').click(function (e) {
      window.location = "${uploadStnkDocumentURL}";
   })

   $('.nav-tabs').on('click', '#inbox-nav-tab', function () {
      $('.document-button-field').hide();
      $('.bonus-title').html('Check Inbox List of Ticket');
      isDisplayActive = false;
      uploadStnkDataTable.clear().draw();
      refresh();
   });

   $('.nav-tabs').on('click', '#display-nav-tab', function () {
      $('.document-button-field').show();
      $('.bonus-title').html('Display Tickets');
      isDisplayActive = true;
      uploadStnkDataTable.clear().draw();
      refresh();
   });

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
      uploadStnkDataTable = $('#upload-stnk-table').DataTable({
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
         // order: [],
         language: languageOptions,
         columnDefs: [
            {
               targets: [2, 3, 4, 5, 6, 7],
               orderable: true
            },
            {
               targets: 0,
               checkboxes: {
                  selectRow: false
               }
            }
         ],
         ajax: {
            url: '${uploadStnkListURL}',
            type: 'GET',
            data: function (d) {
               d.isDisplay = isDisplayActive;
               d.column = column;
               d.keyword = keyword;
            },
            dataSrc: function (json) {
               destroyLoading();
               dataUploadBonuses = json.data;
               if (json.isApprover === true) {
                  $('.inbox-tab').show();
               } else {
                  $('.inbox-tab').hide();
               }
               return json.data;
            }
         },
         columns: [
            {
               data: "id",
               width: "5%",
               targets: 0,
               searchable: false,
               orderable: false,
               className: 'text-center',
            },
            {
               data: "id",
               width: "5%",
               orderable: false,
               className: "text-center",
               render: function (data, type, row, meta) {
                  return renderActionButton(data, row);
               }
            },
            {
               data: "applicationCategoryName",
               width: "10%"
            },
            {
               data: "screenName",
               width: "20",
               className: 'text-center'
            },
            {
               data: "dealerName",
               width: "20%",
               className: "text-left",
               render: function (data, type, row, meta) {
                  return row.dealerCode + "<br>" + row.dealerName;
               }
            },
            {
               data: "ticketNo",
               width: "20%"
            },
            {
               data: "reqDate",
               width: "10%",
               className: 'text-center',
               render: function (data, type, row, meta) {
                  if (data === '-') {
                     return '-';
                  } else {
                     return moment(data).format("DD.MM.YYYY");
                  }
               }
            },
            {
               data: "applicationHeaderStatusName",
               width: "10%",
               className: 'text-center'
            }
         ]
      });
   }

   function renderActionButton(dataId, row) {
      var response = "";
      response =
         '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a href="javascript:void(0)" style="margin:0px;" onclick="openForm(this)" data-id="' + dataId + '">' +
               '<img src="<%=request.getContextPath()%>/assets/img/approver.svg" style="height:29px;margin:0px;" />' +
            '</a>' +
         '</span>';
      return response;
   }

   function deleteUploadBonus(element) {
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
            formData.append("uploadBonusId", entryId);

            $.ajax({
               url: "${uploadStnkActionURL}",
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
                           getUploadBonus();
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

   function clearForm() {
      $('[name="entryId"]')[0].value = "";
      $('[name="ticketDate"]')[0].value = "";
      $('[name="ticketHour"]')[0].value = "";
      $('[name="ticketNumber"]')[0].value = "";
      $('[name="userId"]')[0].value = "";
      $('[name="userName"]')[0].value = "";
      $('[name="dealerId"]')[0].value = "";
      $('[name="dealerName"]')[0].value = "";
      // Contact Information
      $('[name="email"]')[0].value = "";
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
      }).show();
      $('#myModal').data('bs.modal').handleUpdate();
      clearForm();
      bonusId = $(element).data("id");
      getBonus();
   }

   function hideForm() {
      clearForm();
      $('#myModal').modal('hide');
   }

   function getBonus() {
      $.ajax({
         url: "${uploadStnkSingleURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>entryId': bonusId,
            '<portlet:namespace/>isDisplay': isDisplayActive,
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var data = JSON.parse(response);
            var bonus = data["data"];

            var downloadURL = new URL('${downloadFileURL}');
            downloadURL.searchParams.set('fileId', bonus.attachmentId);
            downloadURL.searchParams.set('p_auth', Liferay.authToken);

            $('[name="entryId"]')[0].value = bonus.id;
            $('[name="ticketDate"]')[0].value = bonus.ticketDate;
            $('[name="ticketHour"]')[0].value = bonus.ticketHour;
            $('[name="ticketNumber"]')[0].value = bonus.ticketNumber;
            $('[name="userId"]')[0].value = bonus.userId;
            $('[name="userName"]')[0].value = bonus.userName;
            $('[name="dealerId"]')[0].value = bonus.dealerId;
            $('[name="dealerName"]')[0].value = bonus.dealerName;
            // Contact Information
            $('[name="email"]')[0].value = bonus.email;
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
            // $('#bonusFile').attr('href', bonus.attachmentName === "" ? "bonusFile" : bonus.attachmentPath + "?download=true");
            $('#bonusFile').attr('href', bonus.attachmentName === "" ? "bonusFile" : downloadURL.toString());
            $('#bonusFileName').text(bonus.attachmentName)
            if (bonus.ticketDate === '-' || bonus.statusBonusId === 2 || bonus.statusBonusId === 3 || bonus.isApprovable === true) {
               $('#notes').prop('readonly', true);
               $('.btnDso').hide();
               $('.btnHo').show();
            } else {
               $('#notes').prop('readonly', false);
               $('.btnDso').show();
               $('.btnHo').hide();
            }
         },
         error: function (err) {
            console.log(err);
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
         x1 = x1.replace(rgx, '$1' + '.' + '$2');
      }
      return x1 + x2;
   }

   function updateStatus(statusId, statusName) {
      const notes = $('[name="notes"]').val();
      if (notes === null || notes === undefined || notes.length < 9) {
         Swal.fire("Notes belum diisi.", "", "warning");
         return false;
      } else {
         Swal.fire({
            title: 'Confirmation',
            text: statusName + ' data?',
            showCancelButton: true,
            confirmButtonText: 'Yes',
            confirmButtonColor: '#EE1C25',
         }).then((result) => {
            if (result.isConfirmed) {
               var formData = new FormData();
               formData.append("crudType", "updateStatus");
               formData.append("bonusId", bonusId);
               formData.append("statusId", statusId);
               formData.append("notes", notes);

               $.ajax({
                  url: "${uploadStnkUpdateStatusActionURL}",
                  type: "POST",
                  data: formData,
                  processData: false,
                  contentType: false,
                  success: function (response) {
                     var data = JSON.parse(response);
                     if (data["acknowledge"] === 1) {
                        Swal.fire({
                           title: 'Information',
                           text: "Failed to " + data["message"]
                        });
                     } else {
                        Swal.fire({
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
         url: "${dealerUploadStnkURL}",
         type: "POST",
         data: {"windowsId": "test"},
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
      if (data.id !== '' && data.id !== 'ALL') {
         keyword = data.id;
      } else {
         keyword = 'ALL';
      }
      getUploadBonus();
   });

   function getRequestUploads() {
      $.ajax({
         url: "${requestUploadStnkURL}",
         type: "POST",
         data: {"windowsId": "test"},
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
         keyword = 'ALL';
      }
      getUploadBonus();
   })

   $("input#ticketList").bind('keyup', function (e) {
      var ticketNumber = $("#ticketList").val();
      if (ticketNumber.length > 2) {
         keyword = ticketNumber;
      } else {
         keyword = 'ALL';
      }
      getUploadBonus();
   });

   // Status
   function getStatusUploads() {
      $('#statusId').empty();
      var option = new Option("Select All", "ALL", true, true);
      $('#statusId').append(option).trigger('change');
      $.ajax({
         url: "${statusUploadStnkURL}",
         type: "POST",
         data: {
            "windowsId": "test",
            "isDisplay": isDisplayActive
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
      getUploadBonus();
   })

   // Tahun
   function getTahunUploads() {
      $.ajax({
         url: "${tahunUploadStnkURL}",
         type: "POST",
         data: {"windowsId": "test"},
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
      getUploadBonus();
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
      hideSearch()
      if (data.id !== '' && data.id !== 'Select a category') {
         column = data.id;
         keyword = 'ALL';
         if (data.id === 'REQUEST_CATEGORY') {
            $('.requestId').show();
         } else if (data.id === 'DEALER_NAME') {
            $('.dealerList').show();
            getDealerUploads();
         } else if (data.id === 'TICKET_NUMBER') {
            $('.ticketList').show();
         } else if (data.id === 'TICKET_DATE') {
            $('.ticketDateList').show();
         } else if (data.id === 'STATUS') {
            $('.statusId').show();
         } else if (data.id === 'TAHUN') {
            $('.tahunId').show();
         }
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
      getUploadBonus();
   })

   function getKwitansi(array, id) {
      var obj = array.filter(function (val) {
         return val.id === id;
      });
      return obj[0];
   }

   function getChecks() {
      var kwitansiBonuses = [];
      var rows_selected = uploadStnkDataTable.column(0).checkboxes.selected();
      $.each(rows_selected, function (index, rowValue) {
         var kwitansi = getKwitansi(dataUploadBonuses, Number(rowValue));
         kwitansiBonuses.push(
            {
               applicationCategoryName: kwitansi.applicationCategoryName,
               reqName: kwitansi.reqName,
               dealerName: kwitansi.dealerName,
               ticketNo: kwitansi.ticketNo,
               reqDate: kwitansi.reqDate,
               nominalPengajuan: kwitansi.nominalPengajuan,
               reqYearId: kwitansi.reqYearId,
               applicationHeaderStatusName: kwitansi.applicationHeaderStatusName
            }
         )
      });
      createExels(kwitansiBonuses);
   }

   function createExels(kwitansiBonuses) {
      if (kwitansiBonuses.length < 1) {
         Swal.fire("Info", "Data belum terpilih.", "");
         return;
      }
      const workbook = new ExcelJS.Workbook();
      workbook.creator = 'DEALINK';
      workbook.lastModifiedBy = 'DEALINK';
      workbook.created = new Date();
      workbook.modified = new Date();
      workbook.lastPrinted = new Date();

      const worksheet = workbook.addWorksheet('New Sheet');
      worksheet.columns = [
         {header: 'Request Category', key: 'applicationCategoryName'},
         {header: 'Requester', key: 'reqName'},
         {header: 'Dealer', key: 'dealerName'},
         {header: 'Ticket Number', key: 'ticketNo'},
         {header: 'Ticket Date', key: 'reqDate'},
         {header: 'Nominal Pengajuan', key: 'nominalPengajuan'},
         {header: 'Tahun', key: 'reqYearId'},
         {header: 'Status', key: 'applicationHeaderStatusName'}
      ];
      worksheet.addRows(kwitansiBonuses);

      // Set footer (default centered), result: "Page 2 of 16"
      worksheet.headerFooter.oddFooter = "Page &P of &N";

      workbook.xlsx.writeBuffer().then((xData) => {
         const blob = new Blob([xData], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
         saveAs(blob, "Upload Copy STNK" + ".xlsx");
      });
   }
</script>