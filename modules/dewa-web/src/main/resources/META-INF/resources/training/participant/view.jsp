<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<%--View Manual--%>
<portlet:resourceURL id="/training-agenda-participant-list" var="trainingAgendaParticipantListURL"/>
<portlet:resourceURL id="/training-agenda-participant-single" var="trainingAgendaParticipantSingleURL"/>
<portlet:resourceURL id="/training-peserta-action" var="trainingPesertaActionURL"/>
<portlet:renderURL var="trainingPesertaManualEditURL">
   <portlet:param name="mvcRenderCommandName" value="/training-peserta-manual-edit"/>
</portlet:renderURL>
<%--Form Manual--%>
<portlet:resourceURL id="/training-peserta-manual-action" var="trainingPesertaManualActionURL" />
<portlet:resourceURL id="/dealer-training" var="dealerTrainingURL"/>
<%--View Upload--%>
<portlet:resourceURL id="/training-agenda-participant-uf-list" var="trainingAgendaParticipantUfListURL"/>
<portlet:resourceURL id="training-agenda-participant-uf-list-download" var="trainingAgendaParticipantUfListDownloadURL"/>
<portlet:resourceURL id="/training-agenda-participant-uf-single" var="trainingAgendaParticipantUfSingleURL"/>
<portlet:resourceURL id="/training-agenda-participant-uf-action" var="trainingAgendaParticipantUfActionURL"/>

<portlet:resourceURL id="/upload-file-training" var="uploadFileTrainingCommandURL" />
<portlet:resourceURL id="/delete-file-training" var="deleteFileTrainingCommandURL" />

<portlet:resourceURL id="download-training-participant-file" var="downloadFileURL"/>

<style>
    .training_status > .tabcontent {
        box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
        border-radius: 10px;
        padding: 0.75em;
    }

    #participant_manual_table > thead {
        background-color: #014689;
        border: none !important;
        color: white;
        border-radius: 10px 10px 0 0;
    }

    #participant_upload_table > thead {
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

    /*Manual*/
    table#participant_manual_table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#participant_manual_table {
        border-radius: 10px 10px 10px 10px;
    }

    #participant_manual_table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #participant_manual_table > thead > tr > th {
        font-weight: normal;
        text-align: center;
    }

    /*Upload*/
    table#participant_upload_table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#participant_upload_table {
        border-radius: 10px 10px 10px 10px;
    }

    #participant_upload_table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #participant_upload_table > thead > tr > th {
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
    }

    /*Select2*/
    .select2-container--default .select2-selection--single {
        padding: 6px;
        margin-top: 0px;
        /*margin-bottom: 16px;*/
        height: 37px;
        /*width: 148px;*/
        font-size: 1.2em;
        position: relative;
    }

    .modal-dialog{
        left: 0;
        right: 0;
    }
</style>

<div class="cms-content">
   <h3 id="agendaTitle" style="font-size: 24px; font-weight: 700; margin-bottom: 1em;">Pendaftar Manual</h3>
   <div class="tabcontent" id="cms-tabcontent">
      <ul class="nav nav-tabs" id="cmsTab" role="tablist">
         <li class="nav-item" style="width: 230px;">
            <a class="nav-link" data-senna-off="true" id="agenda_navtab" data-toggle="" href="/group/dealink/cms-training" role="tab" aria-controls="manual" aria-selected="true">Kelola Agenda Pelatihan</a>
         </li>
         <li class="nav-item" style="width: 230px;">
            <a class="nav-link" id="peserta_manual_navtab" data-toggle="tab" href="#peserta_manual_tab" role="tab" aria-controls="manual" aria-selected="true">Daftar Peserta Pelatihan</a>
         </li>
         <li class="nav-item" style="width: 230px;">
            <a class="nav-link" id="peserta_upload_navtab" data-toggle="tab" href="#peserta_upload_tab" role="tab" aria-controls="upload" aria-selected="true">Upload File</a>
         </li>
      </ul>
      <div class="tab-content" id="myTabContent">
         <div class="tab-pane fade" id="peserta_manual_tab" role="tabpanel" aria-labelledby="profile-tab">
            <div class="row" style="margin-bottom: 16px;">
               <div class="col-xs-12 col-sm-12 col-md-3">
                  <button class="btn btn-info btn_table" onclick="downloadPesertaManual()">
                     Unduh Data Peserta
                  </button>
               </div>
               <div class="col-xs-12 col-sm-12 col-md-3"></div>
               <div class="col-xs-12 col-sm-12 col-md-6" style="text-align: right;"></div>
            </div>
            <table id="participant_manual_table" class="table table-hover nowrap" style="width:100%">
               <thead>
               <tr>
                  <%--<th>No</th>--%>
                  <th style="text-align: center;">
                     <%--<input type="checkbox" name="checkSelectAll" id="checkSelectAll" value="1" />--%>
                  </th>
                  <th>Nama</th>
                  <th>Email</th>
                  <th>No. Telepon</th>
                  <th>Nama Dealer</th>
                  <%--<th>Aksi</th>--%>
               </tr>
               </thead>
               <tbody></tbody>
            </table>
         </div>
         <div class="tab-pane fade" id="peserta_upload_tab" role="tabpanel" aria-labelledby="profile-tab">
            <div class="row" style="margin-bottom: 16px;">
               <div class="col-xs-12 col-sm-12 col-md-3">
                  <button class="btn btn-info btn_table" onclick="downloadParticipantFromExel()">
                     Unduh Data Peserta
                  </button>
               </div>
               <div class="col-xs-12 col-sm-12 col-md-3"></div>
               <div class="col-xs-12 col-sm-12 col-md-6" style="text-align: right;"></div>
            </div>
            <table id="participant_upload_table" class="table table-hover nowrap" style="width:100%">
               <thead>
               <tr>
                  <%--<th>No</th>--%>
                  <th style="text-align: center;">
                     <%--<input type="checkbox" name="checkSelectAll" id="checkSelectAll" value="1" />--%>
                  </th>
                  <th>Nama Dealer</th>
                  <th>File</th>
               </tr>
               </thead>
               <tbody></tbody>
            </table>
         </div>
      </div>
   </div>
</div>

<!-- Modal Manual -->
<div class="modal fade" id="formManualModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
   <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" style="text-align: left;">Daftar Peserta</h4>
         </div>
         <div class="modal-body">
            <form id="formParticipantManual" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
               <%--<h3 id="form-title" style="margin-bottom: 50px;">Daftar Peserta</h3>--%>
               <div class="row col-12">
                  <div class="form-group col-md-6">
                     <label class="title-form pull-left" for="email">Email</label>
                     <input type="text" class="form-control" name="email" id="email" />
                  </div>
                  <div class="form-group col-md-6">
                     <label class="title-form pull-left" for="fullName">Nama Peserta</label>
                     <input type="text" class="form-control" name="fullName" id="fullName" />
                  </div>
               </div>
               <div class="row col-12">
                  <div class="form-group col-md-6">
                     <label class="title-form pull-left" for="phone">No. Handphone</label>
                     <input type="text" class="form-control" name="phone" id="phone" />
                  </div>
                  <div class="form-group col-md-6">
                     <label class="title-form pull-left" for="dealerId">Nama Dealer</label>
                     <input type="hidden" class="form-control" name="dealerName" />
                     <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;">
                        <%--<option value="NULL">List Dealer</option>--%>
                     </select>
                  </div>
               </div>
               <div class="row col-12" style="border-top: 1px solid #D9D9D9 ; margin: 10px 0px"></div>
               <div class="col-12" style="text-align: right;">
                  <button class="btn btn-ipr-cancel" type="button" id="btnAdd" onclick="hideFormManual();">Tutup</button>
                  <button class="btn-ipr" type="submit">Simpan</button>
               </div>
            </form>
         </div>
      </div>
   </div>
</div>

<!-- Modal Upload -->
<div class="modal fade" id="formUploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
   <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" style="text-align: left;">Daftar Peserta</h4>
         </div>
         <div class="modal-body">
            <form id="formParticipantUpload" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
               <%--<h3 id="form-title" style="margin-bottom: 50px;">Daftar Peserta</h3>--%>
               <div class="row col-12">
                  <div class="form-group col-md-12" style="margin-bottom: 10px;">
                     <label class="col-form-label ipr-color">Upload File <span style="color: red;">*</span></label>
                     <div class="input-group" >
                        <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                           <span class="icon-browse"><img src="<%=request.getContextPath()%>/assets/img/file.svg" alt=""></span>
                           <span class="text-browse">Pilih File</span>
                           <input type="file" accept="image/gif, image/jpeg, image/png" placeholder="" name="participantUploadFile" id="participantUploadFile" data-filename="" data-location="" style="display: none;" aria-invalid="false" class="materail_input valid">
                        </label>
                        <input type="text" class="form-control required" data-type="file" data-name="" name="participantUploadFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
                        <input type="text" class="dpn" name="participantUploadFileId" />
                        <input type="text" class="dpn" name="participantUploadFilePath" />
                     </div>
                     <label class="col-form-label ipr-gray">*maks. 5MB</label>
                  </div>
               </div>
               <div class="row col-12">
                  <div class="form-group col-md-12">
                     <label class="title-form pull-left" for="dealerIdUpload">Nama Dealer</label>
                     <input type="hidden" class="form-control" name="dealerNameUpload" />
                     <select class="form-control" name="dealerIdUpload" id="dealerIdUpload" style="width: 100%;">
                        <%--<option value="NULL">List Dealer</option>--%>
                     </select>
                  </div>
               </div>
               <div class="row col-12" style="border-top: 1px solid #D9D9D9 ; margin: 10px 0px"></div>
               <div class="col-12" style="text-align: right;">
                  <button class="btn btn-ipr-cancel" type="button" id="btnAddUpload" onclick="hideFormUpload();">Tutup</button>
                  <button class="btn-ipr" type="submit">Simpan</button>
               </div>
            </form>
         </div>
      </div>
   </div>
</div>

<script>
   var trainingAgendaId = '${trainingAgendaId}'
   var trainingAgendaName = '${trainingAgendaName}'
   var dataParticipants = []
   var dataParticipantUfs = []
   var dealers = ""
   var action = "create";
   var participantId = "0";
   var submitProcess = false;
   var languageOptions = {
      // info: "",
      "lengthMenu": "_MENU_",
      "paginate": {
         "next":       '<span class="glyphicon glyphicon-menu-right"></span>',
         "previous":   '<span class="glyphicon glyphicon-menu-left"></span>'
      },
      "search":         "",
      searchPlaceholder: "Cari Dokumen"
   }

   $("#btnNewManual").click(function(){
      newFormManual()
   });

   function newFormManual() {
      participantId = "0";
      formNewManual()
      $('#formManualModal').modal({
         backdrop: 'static',
         keyboard: false,
         isBodyOverflowing: true
      }).show()
      $('.modal .modal-body').css('overflow-y', 'auto');
      var _height = $(window).height()*0.8;
      $('.modal .modal-body').css('max-height', _height);
   }

   function editFormManual(element) {
      $('#formManualModal').modal({
         backdrop: 'static',
         keyboard: false,
         isBodyOverflowing: true
      }).show()
      $('.modal .modal-body').css('overflow-y', 'auto');
      var _height = $(window).height()*0.8;
      $('.modal .modal-body').css('max-height', _height);
      // get data
      participantId = $(element).data("id");
      $.ajax({
         url: "${trainingAgendaParticipantSingleURL}",
         type: "POST",
         data: {"entryId": participantId},
         success: function (response) {
            var data = JSON.parse(response);
            var participant = data["data"]
            formEditManual(participant)
         },
         error: function (err) {
            console.log(err);
         }
      });
   }

   function hideFormManual() {
      $('#formManualModal').modal('hide')
   }

   // Upload
   $("#btnNewUpload").click(function(){
      // newFormUpload()
      refreshUpload()
   });

   function newFormUpload() {
      participantId = "0";
      formNewUpload()
      $('#formUploadModal').modal({
         backdrop: 'static',
         keyboard: false,
         isBodyOverflowing: true
      }).show()
      $('.modal .modal-body').css('overflow-y', 'auto');
      var _height = $(window).height()*0.8;
      $('.modal .modal-body').css('max-height', _height);
   }

   function editFormUpload(element) {
      $('#formUploadModal').modal({
         backdrop: 'static',
         keyboard: false,
         isBodyOverflowing: true
      }).show()
      $('.modal .modal-body').css('overflow-y', 'auto');
      var _height = $(window).height()*0.8;
      $('.modal .modal-body').css('max-height', _height);
      // get data
      participantId = $(element).data("id");
      $.ajax({
         url: "${trainingAgendaParticipantSingleURL}",
         type: "POST",
         data: {"entryId": participantId},
         success: function (response) {
            var data = JSON.parse(response);
            var participant = data["data"]
            formEditUpload(participant)
         },
         error: function (err) {
            console.log(err);
         }
      });
   }

   function hideFormUpload() {
      $('#formUploadModal').modal('hide')
   }

   var participantManualTable = $('#participant_manual_table').DataTable({
         destroy: true,
         processing: false,
         serverSide: false,
         paging: false,
         info: true,
         searching: false,
         ordering: true,
         // select: true,
         autoWidth: true,
         responsive: true,
         order: [1,2,3,4],
         // scrollY: '65vh',
         // fixedHeader: true,
         language: languageOptions,
         columnDefs: [
            // {
            //    targets: [2,3],
            //    visible: false
            // },
            // {
            //    targets: [4,5],
            //    orderable: false
            // },
            {
               targets: [1,2,3,4],
               orderable: true
            },
            {
               targets: 0,
               checkboxes: {
                  selectRow: false
               }
            }
         ],
         // select: {
         //    style: 'multi'
         // },
         // dom: 'Bfrtip',
         // buttons: [
         //    'copy',
         //    'csv',
         //    {
         //       extend: 'excel',
         //       text: 'Download Exel',
         //       title: 'Daftar Peserta',
         //       exportOptions: {
         //          modifier: {
         //             selected: null
         //          }
         //       }
         //    },
         //    {
         //       extend: 'pdf',
         //       text: 'Download Pdf',
         //       title: 'Daftar Peserta',
         //       exportOptions: {
         //          columns: [0,1,2],
         //          modifier: {
         //             selected: null
         //          }
         //       }
         //    },
         //    {
         //       extend: 'print',
         //       text: 'Print all (not just selected)',
         //       exportOptions: {
         //          modifier: {
         //             selected: null
         //          }
         //       }
         //    }
         // ],
         <%--data: ${dataSet},--%>
         ajax: {
            url: '${trainingAgendaParticipantListURL}',
            type: 'GET',
            data: function(d){
               d.trainingAgendaId =  trainingAgendaId;
            },
            // dataSrc: 'Data'
            dataSrc: function (json) {
               dataParticipants = json.Data
               return json.Data;
            }
         },
         columns: [
            // {data: "no", "width": "5%", className: "text-center"},
            {
               data: "id",
               "width": "9",
               "targets": 0,
               "className": 'text-center',
               // 'render': function (data, type, full, meta) {
               //    return '<input type="checkbox" name="id[]" value="' + data + '"/>';
               // }
            },
            {data: "fullName", "width": "20"},
            {data: "email", "width": "20"},
            {data: "phone", "width": "20"},
            {
               data: "dealerName",
               "render": function (data, type, row, meta) {
                  return viewDealer(data, row);
               },
            },
            // {
            // 	data: "description",
            // 	orderable: false,
            // 	render: $.fn.dataTable.render.ellipsis(25)
            // },
            // {
            //    data: "id",
            //    "width": "7%",
            //    className: "text-center",
            //    "render": function (data, type, row, meta) {
            //       return btnPesertaManual(data);
            //    },
            //    orderable: false
            // }
         ]
      });

   function viewDealer(dataId, row) {
      // return row.dealerCode + " - " + row.dealerName + " - " + row.cabangName;
      return row.dealerName + " - " + row.cabangName;
   }

   function btnPesertaManual(data, type, row, meta) {
      var response = "";
      response = '<span class="action-wrapper" data-id="' + data + '">' +
         <%--'<a href="${trainingPesertaManualEditURL}&id=' + dataId + '&trainingAgendaId=' + trainingAgendaId + '&trainingAgendaName=' + trainingAgendaName + '" >' +--%>
         <%--   '<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg"></img></span>' +--%>
         <%--'</a> ' +--%>
         '<a href="javascript:void(0)" onclick="editFormManual(this)" data-id="' + data + '">' +
            '<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg" /></span>' +
         '</a>' +
         ' <a href="javascript:void(0)" onclick="deletePesertaManual(this)" data-id="' + data + '" >' +
         	'<span><img src="<%=request.getContextPath()%>/assets/img/trash.svg"></img></span>' +
         '</a>' +
         '</span>';
      return response;
   }

   function deletePesertaManual(element) {
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
               url: "${trainingPesertaActionURL}",
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
                        participantManualTable.ajax.reload();
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

   function getParticipant(array, id) {
      var obj = array.filter(function (val) {
         return val.id === id;
      });
      return obj[0];
   }

   function downloadPesertaManual() {
      var participants = [];
      var rows_selected = participantManualTable.column(0).checkboxes.selected();
      $.each(rows_selected, function(index, rowValue){
         var participant = getParticipant(dataParticipants, rowValue);
         participants.push(
            {
               id: participant.id,
               fullName: participant.fullName,
               email: participant.email,
               phone: participant.phone,
               dealerName: participant.dealerName + " - " + participant.cabangName
            }
         )
      });
      createPesertaManual(participants)
   }

   function createPesertaManual(participants) {
      if(participants.length < 1) {
         Swal.fire("Info", "Data belum terpilih.", "");
         return;
      }
      const workbook = new ExcelJS.Workbook();
      workbook.creator = 'M Abdul Honi';
      workbook.lastModifiedBy = 'Bot';
      workbook.created = new Date();
      workbook.modified = new Date();
      workbook.lastPrinted = new Date();

      const worksheet = workbook.addWorksheet('New Sheet');
      worksheet.columns = [
         { header: 'NAMA LENGKAP', key: 'fullName' },
         { header: 'EMAIL', key: 'email' },
         { header: 'TELEPHONE', key: 'phone' },
         { header: 'NAMA DEALER', key: 'dealerName' }
      ];
      // worksheet.addRow({id: 1, name: 'John Doe', age: 35});
      worksheet.addRows(participants);

      //Set footer (default centered), result: "Page 2 of 16"
      worksheet.headerFooter.oddFooter = "Page &P of &N";

      workbook.xlsx.writeBuffer().then((xData) => {
         const blob = new Blob([xData], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });
         saveAs(blob, trainingAgendaName + ".xlsx");
      });

   }
</script>
<script>
   function resetManual() {
      if (action === "create") {
         $('#form-title').text("Daftar Peserta")
         formNewManual();
      } else if (action === "update") {
         $('#form-title').text(trainingAgendaName)
         formEditManual();
      } else {
         $('#form-title').text(trainingAgendaName)
         formEditManual();
      }
   }

   function formNewManual() {
      participantId = "0";
      $('[name="fullName"]')[0].value = "M Abdul Honi";
      $('[name="email"]')[0].value = "abdul@gmail.com";
      $('[name="phone"]')[0].value = "082137306199";
      $('[name="dealerId"]')[0].value = "";
      $('[name="dealerName"]')[0].value = "";
   }

   function formEditManual(participant) {
      $('[name="email"]')[0].value = participant.email;
      $('[name="fullName"]')[0].value = participant.fullName;
      $('[name="phone"]')[0].value = participant.phone;
      $('[name="dealerId"]')[0].value = participant.dealerId;
      $('[name="dealerName"]')[0].value = participant.dealerName;
      setDealers(dealers, "update", participant.dealerId)
   }

   $("#formParticipantManual").submit(function (e) {
      e.preventDefault();
      if (submitProcess === false) {

         if (false) {

         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.append("crudType", action)
            formData.append("entryId", participantId);
            formData.append("trainingAgendaId", trainingAgendaId);

            createLoading();
            $.ajax({
               url: '${trainingPesertaManualActionURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  console.log("response  : " + JSON.stringify(response));
                  if (JSON.parse(response).status === 'success') {
                     swal.fire("Success", "Your request has been saved", "success")
                     .then(function () {
                        participantManualTable.ajax.reload();
                     });
                  } else if (JSON.parse(response).status === 'warning') {
                     swal.fire('Sorry', JSON.parse(response).message, 'warning');
                  } else {
                     swal.fire('Sorry', 'There is an internal error', 'error');
                  }
                  destroyLoading();
               },
               error: function (data, textStatus, XMLHttpRequest) {
                  if (data.status === 500) {
                     var msg = data.responseJSON.Message
                     swal('Sorry', msg, 'error');
                  } else if (data.status === 408) {
                     swal('Sorry', 'Request Time Out, Please Try Again', 'error');
                  } else {
                     swal('Sorry', textStatus + 'error submit', 'error');
                  }
                  destroyLoading();
               },
               complete: function (jqXHR, textStatus) {
                  submitProcess = false;
                  destroyLoading();
               }
            })
         }
      }
   })

   function getDealers() {
      $.ajax({
         url: "${dealerTrainingURL}",
         type: "POST",
         data: {"windowsId": "test"},
         success: function (response) {
            var dealerData = JSON.parse(response);
            dealers = dealerData.Data;
            setDealers(dealerData.Data, "create", "")
            setDealersUpload(dealerData.Data, "create", "")
         },
         error: function (error) {
            console.log(error);
         },
         complete: function (){
            console.log("complete");
         }
      });
   }

   function setDealers(dealerList, operation, dealerId) {
      console.log("operation : " + operation + " dealerId : " + dealerId);
      const dealerConst = $('#dealerId').select2({
         data: dealerList,
         tags: "false",
         placeholder: 'Pilih Nama Dealer',
         allowClear: false,
         // ajax: {
         //    url: "http://example.org/api/test",
         //    cache: false
         // }
         // minimumInputLength: 3,
         maximumInputLength: 100,
         templateSelection: function (data) {
            console.log(data.id + " - " + data.text);
            $('[name="dealerId"]')[0].value = data.id;
            $('[name="dealerName"]')[0].value = data.text;
            if (data.id === 'Pilih Nama Dealer') {
               return 'Pilih Nama Dealer';
            }
            return data.text;
         }
      });
      if(operation === "update") {
         dealerConst.val(dealerId); // Select the option with a value of '1'
         dealerConst.trigger('change'); // Notify any JS components that the value changed
      } else {
         dealerConst.val(null);
         dealerConst.trigger('change');
      }
   }
</script>
<script>
   var participantUploadTable = $('#participant_upload_table').DataTable({
         destroy: true,
         processing: false,
         serverSide: false,
         paging: false,
         info: true,
         searching: false,
         // ordering: true,
         // select: true,
         autoWidth: true,
         responsive: true,
         order: [1,2],
         // scrollY: '65vh',
         // fixedHeader: true,
         language: languageOptions,
         columnDefs: [
            // {
            //    targets: [2,3],
            //    visible: false
            // },
            {
               targets: [1,2],
               orderable: true
            },
            {
               targets: 0,
               checkboxes: {
                  selectRow: false
               }
            }
         ],
         // select: {
         //    style: 'multi'
         // },
         // dom: 'Bfrtip',
         // buttons: [
         //    'copy',
         //    'csv',
         //    {
         //       extend: 'excel',
         //       text: 'Download Exel',
         //       title: 'Daftar Peserta',
         //       exportOptions: {
         //          modifier: {
         //             selected: null
         //          }
         //       }
         //    },
         //    {
         //       extend: 'pdf',
         //       text: 'Download Pdf',
         //       title: 'Daftar Peserta',
         //       exportOptions: {
         //          columns: [0,1,2],
         //          modifier: {
         //             selected: null
         //          }
         //       }
         //    },
         //    {
         //       extend: 'print',
         //       text: 'Print all (not just selected)',
         //       exportOptions: {
         //          modifier: {
         //             selected: null
         //          }
         //       }
         //    }
         // ],
         <%--data: ${dataSet},--%>
         ajax: {
            url: '${trainingAgendaParticipantUfListURL}',
            type: 'GET',
            data: function(d) {
               d.trainingAgendaId = trainingAgendaId;
               d.p_auth = Liferay.authToken;
            },
            // dataSrc: 'Data'
            dataSrc: function (json) {
               dataParticipantUfs = json.Data
               return json.Data;
            }
         },
         columns: [
            // {data: "no", "width": "5%", className: "text-center"},
            {
               data: "id",
               width: "9%",
               targets: 0,
               searchable: false,
               orderable: false,
               // 'render': function (data, type, full, meta) {
               //    return '<input type="checkbox" name="id[]" value="' + data + '"/>';
               // }
            },
            {
               data: "dealerName",
               "render": function (data, type, row, meta) {
                  return viewDealer(data, row);
               },
            },
            // {
            // 	data: "description",
            // 	orderable: false,
            // 	render: $.fn.dataTable.render.ellipsis(25)
            // },
            {
               data: "id",
               width: "7%",
               className: "text-center",
               orderable: true,
               render: function (data, type, row, meta) {
                  return btnPesertaUpload(data, row);
               }
            }
         ]
      });

   function btnPesertaUpload(dataId, row) {
      var downloadURL = new URL('${downloadFileURL}');
      downloadURL.searchParams.set('id', dataId);
      downloadURL.searchParams.set('p_auth', Liferay.authToken);

      var response = "";
      response = `<span class="action-wrapper"><a href="` + downloadURL + `" target="_blank">` + row.fileName + `</a></span>`;

      <%--response = '<span class="action-wrapper" data-id="' + dataId + '">' +--%>
      <%--   '<a href="${trainingPesertaManualEditURL}&id=' + dataId + '" >' +--%>
      <%--      '<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg"></img></span>' +--%>
      <%--   '</a> ' +--%>
      <%--   '<a href="javascript:void(0)" onclick="deleteParticipantUpload(this)" data-id="' + dataId + '" >' +--%>
      <%--   	'<span><img src="<%=request.getContextPath()%>/assets/img/trash.svg"></img></span>' +--%>
      <%--   '</a>' +--%>
      <%--   '</span>';--%>

      // response = '<span class="action-wrapper" data-id="' + dataId + '">' +
      //    '<a href="' + row.filePath + '">' + row.fileName + '</a>' +
      //    '</span>';

      return response;
   }

   function deleteParticipantUpload(element) {
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
               url: "${trainingAgendaParticipantUfActionURL}",
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
                        participantUploadTable.ajax.reload();
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

   function downloadParticipantFromExel() {
      var participants = [];
      var rows_selected = participantUploadTable.column(0).checkboxes.selected();
      $.each(rows_selected, function(index, rowValue){
         var participant = getParticipant(dataParticipantUfs, rowValue);
         participants.push(participant.id)
      });
      if(participants.length < 1) {
         Swal.fire("Info", "Data belum terpilih.", "");
         return;
      }

      var formData = new FormData();
      formData.set("trainingAgendaId", trainingAgendaId);
      participants.forEach(item => {
         formData.append("participants", item);
      })

      $.ajax({
         url : '${trainingAgendaParticipantUfListDownloadURL}',
         type : 'POST',
         // data: {
         //    "trainingAgendaId":  trainingAgendaId,
         //    'participants': participants
         // },
         data: formData,
         processData: false,
         contentType: false,
         success: function(response){
            let data = JSON.parse(response);
            createExcelPesertaUpload(data.Data)
            if(data["acknowledge"] === 1) {
               console.log(data["acknowledge"]);
            } else {
               console.log(data["acknowledge"]);
            }
         },
         error: function(e){
            console.log(e);
            swal({
               icon: "error",
               title: "Failed to download file"
            });
         },
         complete: function() {
            console.log("DONE");
         }
      });
   }

   function createExcelPesertaUpload(dealers) {
      if(dealers.length < 1) {
         Swal.fire("Info", "Data belum terpilih.", "");
         return;
      }
      const workbook = new ExcelJS.Workbook();
      workbook.creator = 'M Abdul Honi';
      workbook.lastModifiedBy = 'Bot';
      workbook.created = new Date();
      workbook.modified = new Date();
      workbook.lastPrinted = new Date();

      $.each(dealers, function(index, rowValue) {
         const worksheet = workbook.addWorksheet(index + 1 + ". " + rowValue.dealerName);
         worksheet.columns = [
            { header: 'NAMA LENGKAP', key: 'fullName' },
            { header: 'EMAIL', key: 'email' },
            { header: 'TELEPHONE', key: 'phone' },
            { header: 'NAMA DEALER', key: 'dealerName' },
            { header: 'JABATAN', key: 'jabatan' }
         ];
         // worksheet.addRow({id: 1, name: 'John Doe', age: 35});
         worksheet.addRows(rowValue.participants);
         // Set footer (default centered), result: "Page 2 of 16"
         worksheet.headerFooter.oddFooter = "Page &P of &N";
      });

      workbook.xlsx.writeBuffer().then((xData) => {
         const blob = new Blob([xData], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });
         saveAs(blob, trainingAgendaName + ".xlsx");
      });
   }
</script>
<script>
   function refreshUpload() {
      participantUploadTable.ajax.reload();
   }

   function resetUpload() {
      if (action === "create") {
         $('#form-title').text("Daftar Peserta")
         formNewUpload();
      } else if (action === "update") {
         $('#form-title').text(trainingAgendaName)
         formEditUpload();
      } else {
         $('#form-title').text(trainingAgendaName)
         formEditUpload();
      }
   }

   function formNewUpload() {
      participantId = "0";
      $('[name="participantUploadFileId"]')[0].value = "";
      $('[name="participantUploadFileName"]')[0].value = "";
      $('[name="participantUploadFilePath"]')[0].value = "";
      $('[name="dealerIdUpload"]')[0].value = "";
      $('[name="dealerNameUpload"]')[0].value = "";
   }

   function formEditUpload(participant) {
      $('[name="participantUploadFileId"]')[0].value = participant.fileId;
      $('[name="participantUploadFileName"]')[0].value = participant.fileName;
      $('[name="participantUploadFilePath"]')[0].value = participant.filePath;
      $('[name="dealerIdUpload"]')[0].value = participant.dealerId;
      $('[name="dealerNameUpload"]')[0].value = participant.dealerName;
      setDealers(dealers, "update", participant.dealerId)
   }

   $("#formParticipantUpload").submit(function (e) {
      e.preventDefault();
      if (submitProcess === false) {

         if (false) {

         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.append("crudType", action)
            formData.append("entryId", participantId);
            formData.append("trainingAgendaId", trainingAgendaId);

            createLoading();
            $.ajax({
               url: '${trainingAgendaParticipantUfActionURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  console.log("response  : " + JSON.stringify(response));
                  if (JSON.parse(response).status === 'success') {
                     swal.fire("Success", "Your request has been saved", "success")
                     .then(function () {
                        participantUploadTable.ajax.reload();
                     });
                  } else if (JSON.parse(response).status === 'warning') {
                     swal.fire('Sorry', JSON.parse(response).message, 'warning');
                  } else {
                     swal.fire('Sorry', 'There is an internal error', 'error');
                  }
                  destroyLoading();
               },
               error: function (data, textStatus, XMLHttpRequest) {
                  if (data.status === 500) {
                     var msg = data.responseJSON.Message
                     swal('Sorry', msg, 'error');
                  } else if (data.status === 408) {
                     swal('Sorry', 'Request Time Out, Please Try Again', 'error');
                  } else {
                     swal('Sorry', textStatus + 'error submit', 'error');
                  }
                  destroyLoading();
               },
               complete: function (jqXHR, textStatus) {
                  submitProcess = false;
                  destroyLoading();
               }
            })
         }
      }
   })

   function setDealersUpload(dealerList, operation, dealerId) {
      console.log("operation : " + operation + " dealerId : " + dealerId);
      const dealerConst = $('#dealerIdUpload').select2({
         data: dealerList,
         tags: "false",
         placeholder: 'Pilih Nama Dealer',
         allowClear: false,
         // ajax: {
         //    url: "http://example.org/api/test",
         //    cache: false
         // }
         // minimumInputLength: 3,
         maximumInputLength: 100,
         templateSelection: function (data) {
            console.log(data.id + " - " + data.text);
            $('[name="dealerIdUpload"]')[0].value = data.id;
            $('[name="dealerNameUpload"]')[0].value = data.text;
            if (data.id === 'Pilih Nama Dealer') {
               return 'Pilih Nama Dealer';
            }
            return data.text;
         }
      });
      if(operation === "update") {
         dealerConst.val(dealerId); // Select the option with a value of '1'
         dealerConst.trigger('change'); // Notify any JS components that the value changed
      } else {
         dealerConst.val(null);
         dealerConst.trigger('change');
      }
   }

   $("#participantUploadFile").change(function(e){
      processUpload(e, "participantUpload", $(this));
   });

   // Upload File
   function processUpload(e, name, element){
      if(e.target.files[0].size > 5242880){
         swal.fire("", "File Size cannot more than 5 MB", "warning");
      }else{
         let fileName = e.target.files[0].name;
         uploadDocumentFile(e.target.files[0], fileName, $('[name="'+name+'FileId"]')[0].value, element, name);
      }
   }

   function uploadDocumentFile(file, fileName, fileID, element, name) {
      let formData = new FormData();
      let format = /[:"\\|,<>\/?^*]/;
      if (format.test(fileName)) {
         swal.fire('', 'File name cannot contains special character ,^*:"|<>\\ /?', 'warning')
      } else {
         fileName = fileName.replaceAll(",", "")
         formData.append("authToken", Liferay.authToken);
         formData.append("file-upload", file);
         formData.append("file-name", fileName);
         formData.append("file-id", fileID);
         formData.append("document-id", $('[name="document-id"]').val());
         formData.append("document-id-new",$('[name="document-id-new"]').val());

         element.parents(".input-group").siblings(".upload-content").remove();
         element.val("");

         let htmlLoading = $(`
                <div class="upload-content">
                    <div class="progress" style="margin-top: 10px; margin-bottom: 0px; transform: scaleY(0.7);">
                        <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" style="width: 0%;"></div>
                    </div>
                    <div id="progress-percentage">0% uploaded</div>
                </div>
            `);

         element.parents(".input-group").after(htmlLoading);

         let elementEdit = element;
         let nameEdit = name;

         $.ajax({
            url: '${uploadFileTrainingCommandURL}',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            xhr: function () {
               var xhr = new XMLHttpRequest();

               xhr.upload.addEventListener('progress', function (e) {
                  if (e.lengthComputable) {
                     var uploadPercent = e.loaded / e.total;
                     uploadPercent = (uploadPercent * 100);

                     if (parseInt(uploadPercent) === 100) {
                        htmlLoading.find("#progress-percentage").text('Silahkan tunggu...');
                     } else {
                        htmlLoading.find("#progress-percentage").text(parseInt(uploadPercent) + '% uploaded');
                     }
                     htmlLoading.find(".progress-bar").width(uploadPercent + '%');
                  }
               }, false);

               return xhr;
            },
            success: function (response) {
               let data = JSON.parse(response);
               let acknowledge = data["acknowledge"];
               let fileID = data["fileID"];
               fileName = data["fileName"];
               let fileURL = data["fileURL"];

               if (acknowledge === 1) {
                  $(elementEdit.parent().siblings('[name="'+nameEdit+'FileId"]'))[0].value=fileID
                  $(elementEdit.parent().siblings('[name="'+nameEdit+'FileName"]'))[0].value=fileName
                  $(elementEdit.parent().siblings('[name="'+nameEdit+'FilePath"]'))[0].value=fileURL

                  let successHTML = `
                     <div style="margin-top: -8px;margin-bottom: 15px;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
                        <div>
                           <p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px">Upload file berhasil </p>
                        </div>
                        <div>
                           <div id="delete-file-button">
                              <a href="javascript:void(0);" onclick="deleteUploadedFile(this, '${fileID}')" title="Delete File"><i class="fas fa-trash"></i></a>
                           </div>
                           <div id="delete-loader" style="display: none;">
                              <i class="fas fa-spinner anim-rotate"></i>
                           </div>
                        </div>
                     </div>
                  `;

                  setTimeout(function () {
                     htmlLoading.empty();
                     htmlLoading.append(successHTML);
                  }, 2000);
               } else {
                  swal.fire({
                     icon: "error",
                     title: "Failed to upload file"
                  });
               }
            },
            error: function (e) {
               console.log(e);
               swal.fire({
                  icon: "error",
                  title: "Failed to upload file"
               });
            },
            complete: function () {
               console.log("DONE");
            }
         });
      }
   }

   function deleteUploadedFile(element, fileID) {
      let parentElement1 = $(element).parents(".upload-content");
      parentElement1.find("#delete-file-button").css("display", "none");
      parentElement1.find("#delete-loader").css("display", "inherit");

      let formData = new FormData();
      formData.append("authToken", Liferay.authToken);
      formData.append("file-id", fileID);
      let parentElement  = element.parentElement.parentElement.parentElement
      // let inputElement = $(parentElement1.siblings().find('input.form-control.required.switch-readonly'))[0];
      let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url : '${deleteFileTrainingCommandURL}',
         type : 'POST',
         data : formData,
         processData: false,
         contentType: false,
         success: function(response){
            let data = JSON.parse(response);
            if(data["acknowledge"] === 1) {
               parentElement.remove()

               parentElement1.remove();
               inputElement.value='';
            }else {
               parentElement1.find("#delete-file-button").css("display", "inherit");
               parentElement1.find("#delete-loader").css("display", "none");

               parentElement.find("[name=file-status]").text("delete failed");
            }
         },
         error: function(e){
            console.log(e);
            swal({
               icon: "error",
               title: "Failed to delete file"
            });
         },
         complete: function() {
            console.log("DONE");
         }
      });
   }

   $(document).ready(function () {
      getDealers()
      $('#agendaTitle').text('${trainingAgendaName}')
      $('#peserta_manual_navtab')[0].click()
   });
</script>
