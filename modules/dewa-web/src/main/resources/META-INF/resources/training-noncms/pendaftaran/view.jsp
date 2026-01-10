<%@ include file="/META-INF/resources/init.jsp" %>

<link href="<%=request.getContextPath()%>/assets/css/custom.css?<%=System.currentTimeMillis()  %>" rel="stylesheet" type="text/css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/jquery.validate.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.13.1/xlsx.full.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/jszip.js"></script>

<portlet:resourceURL id="/pendaftaran-materi-noncms-list" var="pendaftaranMateriNonCMSListURL"/>
<portlet:resourceURL id="/upload-file-pendaftaran-materi-noncms" var="uploadFilePendaftaranMateriNonCMSCommandURL"/>
<portlet:resourceURL id="/company-pendaftaran-materi-noncms" var="companyPendaftaranMateriNonCMSCommandURL"/>
<portlet:resourceURL id="/submit-upload-pendaftaran-materi-noncms" var="submitUploadPendaftaranMateriNonCMSCommandURL"/>
<portlet:resourceURL id="/pendaftaran-materi-noncms-post" var="pendaftaranMateriNonCMSPostURL"/>

<style>
    #pendaftaran_pelatihan_nonCMS_table_length > label {
        visibility: hidden;
    }

    #pendaftaran_pelatihan_nonCMS_table_length > label > select {
        visibility: visible !important;
    }

    #pendaftaran_pelatihan_nonCMS_table thead {
        display: none;
    }

    .table > thead > tr > th, .table > thead > tr > td, .table > tbody > tr > th, .table > tbody > tr > td, .table > tfoot > tr > th, .table > tfoot > tr > td {
        border-top: transparent !important;
    }

    .table-hover > tbody > tr:hover {
        background-color: transparent;
    }

    .card-pendaftaran-materi {
        /*cursor: pointer;*/
        border-radius: 8px;
        box-shadow: 0px 5px 15px rgba(31, 31, 31, 0.08);
        margin-bottom: 2rem;
        /*padding-bottom: 15px;*/
        /*display: flex;*/
        align-items: center;
        justify-content: space-between;
    }

    .judul-card {
        font-weight: bold;
        font-size: x-large;
        white-space: normal;
        word-break: break-all;
    }

    .searchIcon {
        background-image: url(<%=request.getContextPath()%>/assets/img/search.svg);
        background-repeat: no-repeat;
        background-size: 20px;
        background-position: calc(5px) center;
        padding-left: 27px !important;
    }

    .btn_table {
        background-color: #014689;
        color: white;
        border: 1px solid #014689;
        opacity: 10;
    }

    .dataTables_filter > label > input {
        background-image: url(<%=request.getContextPath()%>/assets/img/search.svg);
        background-position: 10px 8px;
        background-repeat: no-repeat;
        padding: 5px 20px 5px 40px;
    }

    #pendaftaran_pelatihan_nonCMS_table .daftarPelatihan {
        width: 12pc !important;
    }

    /*style detail-view.jsp*/
    .header-detail-card {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .custom-modal {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        min-height: 100vh;
        z-index: 1050;
        background-color: rgba(0, 0, 0, 0.4);
        height: 100%;
    }

    .custom-modal.open {
        display: inherit;
    }

    .custom-modal-content {
        position: absolute;
        min-width: 50%;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        background-color: #fff;
        box-shadow: 0px 4px 10px rgb(0, 0, 0, 0.15);
        border-radius: 10px;
    }

    .custom-modal-title {
        text-align: center;
        font-size: 20px;
        font-weight: 600;
        color: #014689;
    }

    .custom-modal-body {
        max-height: 500px;
        overflow-y: auto;
        padding: 2rem;
    }

    .custom-modal-footer {
        padding: 2rem;
        border-top: 1px solid #c7c0c0;
        text-align: right;
    }

    .custom-modal-header {
        border-bottom: 1px solid #c7c0c0;
        padding-bottom: 2rem;
    }

    .post-tag {
        color: white;
        height: 35px;
        padding: 7px 20px;
        border-radius: 10px;
    }

    .ql-clipboard {
        display: none !important;
    }

    .ql-tooltip.ql-hidden {
        display: none !important;
    }

    .card-detail {
        display: flex;
        align-items: center;
        border-radius: 8px;
        /*box-shadow: 0px 8px 17px 12px rgba(13, 12, 34, 0.08);*/
        padding-left: 0px;
    }

    .card-img {
        max-width: 100%;
        height: auto;
        display: block;
    }

    .location {
        padding-bottom: 3em;
        padding-top: 5px;
    }

    .description {
        padding-top: 15px;
        padding-bottom: 3em;
        width: 66.67%;
        padding-left: 15px;
    }

    .image-card-pendaftaran {
        border-radius: 6px 0px 0px 6px;
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        height: 300px;
    }

    .btnBlue {
        background-color: #00448F;
        border-color: #00448F;
        color: white;
        border-radius: 20px;
    }

    .btnWhite {
        background-color: white;
        border-color: #00448F;
        color: #00448F;
        border-radius: 20px;
    }

    .upload-file {
        width: 100%;
        height: 150px;
        border: 1px dashed #C7C7C7;
        border-radius: 5px;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
    }

    .detail-file-name {
        background-color: unset;
        border-color: transparent;
        color: #00448F;
        text-align: center;
    }

    .text-browse {
        color: #00448F;
        font-weight: bold;
        padding-top: 10px;
    }

    .notFoundContainer {
        width: 50%;
        margin-left: 25%;
        display: block;
    }

    .image-card {
        padding-left: 0px;
        width: 33.3%;
    }

    #pendaftaran_pelatihan_nonCMS_table .panel {
        padding: 20px;
        border-radius: 10px;
    }

    #pendaftaran_pelatihan_nonCMS_table .panel-title {
        font-size: 1.5em;
    }

    .btnDaftar {
        margin-right: 15px;
    }

    @media only screen and (max-width: 780px) {
        .card-detail {
            align-items: stretch;
            flex-direction: column;
        }

        .image-card {
            width: 100%;
        }

        .description {
            width: 100%;
        }

        .group-btnDaftar {
            display: flex;
            justify-content: center;
        }

        .custom-modal-content {
            min-width: 90%;
        }

    }
</style>

<div id="pendaftaran-pelatihan-noncms">
   <h3 style="font-size: 24px; font-weight: 600; margin-bottom: 1em;" class="text-center">Pendaftaran Pelatihan</h3>
   <table id="pendaftaran_pelatihan_nonCMS_table" class="table table-hover nowrap" style="width:100%">
      <thead>
      <tr>
         <th></th>
      </tr>
      </thead>
      <tbody></tbody>
   </table>
</div>

<div id="fieldGroupCopy" style="display: none; border-top: 1px solid #f5f5f5;">
   <div class="group-form">
      <div class="col-md-12 text-right" style="border-top: 1px solid #f5f5f5;">
         <a href="javascript:;" id="remove" class="text-danger" style="color:red;" title="Hapus">x</a>
      </div>

      <div class="col-md-6">
         <div class="col-md-12">
            <div class="form-group">
               <label for="">
                  Email
                  <span style="color: red;">*</span>
               </label>
               <input type="email" name="<portlet:namespace/>email"
                      class="form-control select-email form-required basic-character"
                      placeholder="" required/>
            </div>
         </div>

         <div class="col-md-12">
            <div class="form-group">
               <label for="">
                  No. Handphone
                  <span style="color: red;">*</span>
               </label>
               <input type="number" name="<portlet:namespace/>phone" class="form-control select-phone form-required"
                      placeholder="" required>
            </div>
         </div>
      </div>
      <div class="col-md-6">
         <div class="col-md-12">
            <div class="form-group">
               <label for="">
                  Nama
                  <span style="color: red;">*</span>
               </label>
               <input type="text" name="<portlet:namespace/>name"
                      class="form-control select-name form-required basic-character" placeholder="" equired>
            </div>
         </div>

         <div class="col-md-12">
            <div class="form-group ">
               <label for="">
                  Nama Dealer
                  <span style="color: red;">*</span>
               </label>
               <select name="<portlet:namespace/>company" class="form-control select-company form-required" style="width: 100%"></select>
            </div>
         </div>
      </div>
      <div class="col-md-12 lampiran-div line-daftar"></div>
   </div>
</div>


<div class="custom-modal" id="modalRegisterPeserta">
   <div class="custom-modal-content">.
      <form class="form" id="frmRegister" action="">
         <div class="custom-modal-header">
            <div class="custom-modal-title" id="divisionModalTitle">Daftar Peserta</div>
         </div>
         <div class="custom-modal-body">
            <div class="fieldGroup">
               <div class="group-form" id="group-1" data-id="1">
                  <input type="hidden" name="<portlet:namespace/>id" id="vId"/>
                  <input type="hidden" name="<portlet:namespace/>state" value="post"/>
                  <div class="col-md-6">
                     <div class="col-md-12">
                        <div class="form-group">
                           <label for="selectEmail1">
                              Email
                              <span style="color: red;">*</span>
                           </label>
                           <input type="hidden" class="form-control" name="userId"/>
                           <input type="hidden" class="form-control" name="userEmail"/>
                           <input type="email" name="<portlet:namespace/>email"
                                  class="form-control select-email form-required basic-character"
                                  id="selectEmail1" data-id="1" placeholder="" required/>
                           <span class="help-block"></span>
                        </div>
                     </div>

                     <div class="col-md-12">
                        <div class="form-group">
                           <label for="selectPhone1">
                              No. Handphone
                              <span style="color: red;">*</span>
                           </label>
                           <input type="number" name="<portlet:namespace/>phone"
                                  class="form-control select-phone form-required"
                                  id="selectPhone1" placeholder="" required>
                           <span class="help-block"></span>
                        </div>
                     </div>

                  </div>
                  <div class="col-md-6">
                     <div class="col-md-12">
                        <div class="form-group">
                           <label for="selectName1">
                              Nama
                              <span style="color: red;">*</span>
                           </label>
                           <input type="text" name="<portlet:namespace/>name"
                                  class="form-control select-name form-required basic-character"
                                  id="selectName1" placeholder="" required>
                           <span class="help-block"></span>
                        </div>
                     </div>

                     <div class="col-md-12">
                        <div class="form-group ">
                           <label for="selectCompany1">
                              Nama Dealer
                              <span style="color: red;">*</span>
                           </label>
                           <select name="<portlet:namespace/>company" class="form-control select-company form-required"
                                   id="selectCompany1" style="width: 100%" required>
                           </select>
                           <span class="help-block"></span>
                        </div>
                     </div>
                  </div>

                  <div class="col-sm-12">
                     <div class="col-md-12 lampiran-div line-daftar"></div>
                  </div>
               </div>
            </div>
         </div>
         <div class="custom-modal-footer">
            <button type="button" class="btn btnWhite addMore">
               <i class="fa fa-plush"></i>
               Tambah Peserta
            </button>
            <button type="submit" class="btn btnBlue daftar">Daftar</button>
         </div>
      </form>
   </div>
</div>

<div class="custom-modal" id="modalUploadPeserta">
   <div class="custom-modal-content">.
      <form class="form" id="frmUploadRegister" action="">
         <div class="custom-modal-header">
            <div class="custom-modal-title" id="divisionUploadModalTitle">Upload Daftar Peserta</div>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
         </div>
         <div class="custom-modal-body">
            <div class="fieldUpload">
               <div style="padding-bottom: 20px">
                  <div style="padding-bottom: 5px; font-weight: bold;">Download Template File Peserta Pelatihan</div>
                  <a id="linkTemplate">Template Pendaftaran Peserta Pelatihan.xlsx</a>
               </div>
               <div>
                  <label for="" style="font-weight: bold;">
                     Nama Dealer
                     <span style="color: red;">*</span>
                  </label>
                  <select name="<portlet:namespace/>company" class="form-control select-company form-required" style="width: 100%"></select>

                  <div style="font-weight: bold;padding-top: 15px;">
                     Upload and attach file
                     <span style="color: red;">*</span>
                  </div>
                  <div class="input-group upload-file">
                     <label class="btn btn-browse btn-fill lbl-atc inv">
                        <div class="icon-browse">
                           <img src="<%=request.getContextPath()%>/assets/img/upload-file.png">
                        </div>
                        <div class="text-browse">
                           Click to Upload
                           <div style="color:#4A4A4A;font-size: 11px;font-weight: 100;">
                              Maximum file size 1 MB file .xlsx
                           </div>
                        </div>
                        <input type="file"
                               accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                               placeholder="" name="trainingAgendaFile" id="trainingAgendaFile" data-filename=""
                               data-location="" style="display: none;" aria-invalid="false"
                               class="materail_input valid">
                     </label>
                     <input type="text" class="form-control detail-file-name required" data-type="file" data-name=""
                            name="trainingAgendaFileName" readonly="" style="background-color: transparent;">
                     <input type="text" class="dpn" name="trainingAgendaFileId">
                     <input type="text" class="dpn" name="trainingAgendaFilePath">
                  </div>
               </div>
            </div>
         </div>
         <div class="custom-modal-footer">
            <button type="submit" class="btn btnBlue"> Submit</button>
         </div>
      </form>
   </div>
</div>


<script>
   var acaraId = 0;
   var homeUrl = "/group/dealink/pendaftaran-pelatihan";

   var dataTableLanguageOptionsTable = {
      "emptyTable": "Tidak ada list pendaftaran materi",
      "info": "",
      "lengthMenu": "_MENU_",
      "search": "",
      searchPlaceholder: "Search"
   }

   var pendaftaranMateriDataTable = $('#pendaftaran_pelatihan_nonCMS_table').DataTable({
      searching: true,
      processing: false,
      serverSide: false,
      paging: true,
      responsive: true,
      info: true,
      ajax: {
         url: '${pendaftaranMateriNonCMSListURL}',
         type: 'GET',
         data: function (d) {
            d.p_auth = Liferay.authToken
         },
         dataSrc: function (json) {
            return json.Data;
         }
      },
      language: dataTableLanguageOptionsTable,
      columns: [
         {
            data: "file",
            width: "100%",
            orderable: false,
            render: function (data, type, row, meta) {
               $("#linkTemplate").attr("href", row.urlTemplate);

               return cardDetail(row);
            }
         }
      ],
      order: [],
      "sDom": '<"row view-filter"<"col-sm-12"<"pull-left"l><"pull-right"f><"clearfix">>>t<"row view-pager"<"col-sm-12"<"text-center"ip>>>',
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
         input.on("keyup", function (e) {
            if (e.key === 'Enter' || e.keyCode === 13 || !input.val()) {
               self.search(input.val()).draw();
            }
         });

         $('.dataTables_filter').append($searchButton);
      }
   });

   // pindahan dari detail_view.jsp

   function cardDetail(row) {
      var cardHTML = '<div class="panel-group card-pendaftaran-materi" id="accordion_' + row.id + ' " role="tablist" aria-multiselectable="false" data-id="' + row.id + '" onclick="cardDetailPendaftaranPelatihan(this)">';
      cardHTML += '<div class="panel">';
      cardHTML += '<div class="panel-heading" role="tab" id="heading_' + row.id + '">';
      cardHTML += '<h4 class="panel-title">';
      cardHTML += '<a role="button" class="collapsed" data-toggle="collapse" data-parent="#accordion_' + row.id + '" href="#collapse_' + row.id + '"aria-expanded="true" aria-controls="collapse_' + row.id + '">' + row.judul + '</a>';
      cardHTML += '</h4> </div>';
      cardHTML += '<div id="collapse_' + row.id + '" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading_' + row.id + '">';
      cardHTML += '<div class="panel-body">';
      cardHTML += '<div id="detail-pendaftaran">';
      cardHTML += '<div class="col-md-12 card-detail">';
      cardHTML += '<div class="image-card">';
      cardHTML += '<div class="image-card-pendaftaran" style="background-image: url(' + row.image + ')"></div>';
      cardHTML += '</div> <div class="description">';
      cardHTML += '<div class="header-detail-card">';
      cardHTML += '<b>' + row.startDate + '</b>';

      if (row.ketAcara === "Akan datang") {
         cardHTML += '<div class="post-tag" style="background-color: #00448F">' + row.ketAcara + '</div>';
      } else if (row.ketAcara === "Tersedia") {
         cardHTML += '<div class="post-tag" style="background-color: #3cb081">' + row.ketAcara + '</div>';
      } else if (row.ketAcara === "Tidak tersedia") {
         cardHTML += '<div class="post-tag" style="background-color: #c50f27">' + row.ketAcara + '</div>';
      }

      cardHTML += '</div> <h4 style="font-weight: bold; word-break: break-all;">' + row.judul + '</h4>';
      cardHTML += '<div style="white-space: pre-wrap; overflow: hidden;">' + row.deskripsi + '</div> <div class="location">';

      if (row.jenisAgenda === 0) {
         cardHTML += '<h6 style="text-transform: none;">Lokasi : ' + row.linkLocation + '</h6>';
      } else if (row.jenisAgenda === 1) {
         cardHTML += '<b>Link Meeting : </b> <a>' + row.linkLocation + '</a>';
      }

      cardHTML += '</div> <div class="group-btnDaftar">';

      if (row.statusAgenda === 0) {
         cardHTML += '<button id="btnTrainingDaftar_' + row.id + '" class="btn btnDaftar btnBlue" data-target="#modalRegisterPeserta" onclick="trainingDaftar(this)">Daftar Peserta</button>';
         cardHTML += '<button id="btnTrainingUpload_' + row.id + '" class="btn btnUpload btnWhite" data-target="#modalUploadPeserta" onclick="trainingUpload(this)">Upload Peserta</button>';
      }

      cardHTML += ' </div> </div> </div> </div> </div> </div> </div> </div>';

      return cardHTML;
   }

   function cardDetailPendaftaranPelatihan(element) {
      $(".ql-editor").attr("contenteditable", "false");
      acaraId = $(element).data("id");
      console.log("acaraId");
      console.log(acaraId);
      $('#vId').val(acaraId);

      //pindahan dari yang disini document ready function
   };

   //show form
   function trainingDaftar(element) {
      var modalId = $(element).data("target");
      $(modalId).modal();
   }

   function trainingUpload(element) {
      var modalId = $(element).data("target");
      $(modalId).modal();
   }

   $(document).ready(function () {
      $.validator.setDefaults({
         highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
         },
         unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
            $(element).closest('.form-group').removeClass('error-display');
         },
         errorElement: 'span',
         errorClass: 'help-block',
         errorPlacement: function (error, element) {
            return true;
         }
      });

      //ini untuk onchange input
      $("input.form-required, select.form-required").on("input", function () {
         var $input = $(this);
         var $formField = $input.closest(".form-group");

         if ($input.val()) {
            $formField.find(".error-display").remove();

            if ($input.is(".basic-character")) {
               if (!regexBasicCharacter.test($input.val())) {
                  console.log("ada .,/()@&_-");
                  $input.addClass("error-field");
                  if ($(this).parents(".form-group").find(".error-display").length == 0) {
                     $formField.find(".error-display").text("Allowed special character .,/()@&_-");
                  } else {
                     $formField.append(`<div class='error-display' style='color: red'>Allowed special character .,/()@&_-</div>`);
                  }
               }
            }
         } else {
            var formField = $input.parents(".form-group");
            if ($(this).parents(".form-group").find(".error-display").length == 0) {
               formField.append("<div class='error-display' style='color: red'>Please fill this field</div>");
            }
         }
      })

      // $('#vId').val(acaraId);

      /* daftar */
      var maxGroup = 10;

      $(".addMore").click(function () {
         console.log("===>>> addMore peserta");
         $(".error-display").remove();

         var seq = $('body').find('.fieldGroup').length;
         if (seq < maxGroup) {
            seq += 1;
            $('#fieldGroupCopy').find('.group-form').attr('id', 'group-' + seq);
            $('#fieldGroupCopy').find('.group-form').attr('data-id', seq);
            $('#fieldGroupCopy').find('.select-email').attr('id', 'selectEmail' + seq);
            $('#fieldGroupCopy').find('.select-email').attr('data-id', seq);
            $('#fieldGroupCopy').find('.select-name').attr('id', 'selectName' + seq);
            $('#fieldGroupCopy').find('.select-phone').attr('id', 'selectPhone' + seq);
            $('#fieldGroupCopy').find('.select-company').attr('id', 'selectCompany' + seq);
            $('#fieldGroupCopy').find('.select-jabatanspesifik').attr('id', 'jabatanSpesifik' + seq);
            $('#fieldGroupCopy').find('.select-jabatan').attr('id', 'selectJabatan' + seq);
            $('#fieldGroupCopy').find('.select-file').attr('id', 'file' + seq);
            $('#fieldGroupCopy').find('.select-file').attr('onchange', 'addFile(' + seq + ');');
            $('#fieldGroupCopy').find('.select-file-name').attr('id', 'fileName' + seq);

            var fieldHTML = '<div class="fieldGroup">' + $("#fieldGroupCopy").html() + '</div>';
            $('body').find('.fieldGroup:last').after(fieldHTML);
            if ($('body').find('.fieldGroup').length > 9) {
               $('.addMore').hide();
            }
         } else {
            alert('Maximum ' + maxGroup + ' groups are allowed.');
         }
      });

      //remove fields group
      $("body").on("click", "#remove", function () {
         $(this).parents(".fieldGroup").remove();
         $('.addMore').show();
      });

      /* post */
      // validate
      $('#frmRegister').validate({
         rules: {
            ['<portlet:namespace/>' + 'email']: {
               required: true,
               email: true,
               maxlength: 255
            },
            ['<portlet:namespace/>' + 'name']: {
               required: true,
               minlength: 5,
               maxlength: 100
            },
            ['<portlet:namespace/>' + 'phone']: {
               required: true,
               minlength: 9,
               maxlength: 14,
               number: true
            },
            ['<portlet:namespace/>' + 'company']: {
               required: true
            },
         },
         messages: {
            email: {
               required: "Field email belum diisi",
               email: "Mohon gunakan format email yang sesuai"
            },
            name: {
               required: "Field nama belum diisi",
               minlength: "Mohon input minimal 5 karakter",
               maxlength: "Mohon input maksimal 100 karakter"
            },
            phone: {
               required: "Nomor handphone belum diisi",
               number: "Mohon gunakan nomor handphone yang sesuai"
            }
         },
      });

      /*start*/
      $("form#frmRegister").submit(function (e) {
         // console.log("masuk submit daftar tidak");
         e.preventDefault();
         var formData = new FormData(this);
         formData.set("<portlet:namespace/>id", acaraId);
         formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
         var valid = false;

         if (checkValidation()) {
            $(this).parents(".custom-modal").fadeOut();
            $(this).parents(".custom-modal").removeClass("open");
            createLoading();
            $.ajax({
               url: '${pendaftaranMateriNonCMSPostURL}',
               type: 'POST',
               data: formData,
               success: function (data) {
                  let response = JSON.parse(data);
                  if (response['message'] === "success") {
                     Swal.fire({
                        icon: "success",
                        title: "Information",
                        text: "Data peserta berhasil tersimpan",
                        type: 'success',
                        showCancelButton: false,
                        confirmButtonText: 'Ok'
                     }).then(function (val) {
                        if (val.value) {
                           location.reload();
                        }
                     });
                  } else if (response['message'] === "waktuHabis") {
                     Swal.fire({
                        icon: "info",
                        title: "Information",
                        text: "Waktu Pendaftaran Telah Habis ",
                        type: "info",
                        confirmButtonText: 'Ok'
                     }).then(function (val) {
                        if (val.value) {
                           location.reload();
                        }
                     });
                  } else if (response['status'] === "warning") {
                     Swal.fire({
                        icon: "warning",
                        text: response['message'],
                        type: "info",
                        confirmButtonText: 'Ok'
                     }).then(function (val) {
                        if (val.value) {
                           location.reload();
                        }
                     });
                  } else {
                     Swal.fire({
                        icon: "error",
                        text: "Error",
                        type: "info",
                        confirmButtonText: 'Ok'
                     });
                  }
                  destroyLoading();
               }, error: function () {
                  destroyLoading();
                  Swal.fire(
                     'Failed!',
                     'Koneksi error, Silakan kontak administrator',
                     'error'
                  )
               },
               cache: false,
               contentType: false,
               processData: false,
            });
         }
      });

      /* more company */
      $.ajax({
         url: '${companyPendaftaranMateriNonCMSCommandURL}',
         type: 'POST',
         data: {},
         success: function (data) {
            var result = $.parseJSON(data);
            var html = "<option value=''>-- Pilih --</option>";
            $.each(result.data, function (k, row) {
               html += '<option value="' + row.id + '">' + $("<span>").text(row.text).html() + '</option>';
            });

            $('.select-company').empty().append(html);
         },
         error: function (e) {
            console.log(e);
         }
      });
   });

   function checkValidation() {
      var boolValid = true;
      $(".error-display").remove();

      $(" #modalRegisterPeserta input.form-required, #modalRegisterPeserta select.form-required").each(function () {
         var value = $(this).val();
         // console.log("select bagian");
         // console.log(value);
         // console.log("!value : " + !value);
         // console.log("select : " + $(this).is("select"));
         // console.log("hasil  : " + ($(this).is("select") && value === ""));
         // console.log("regexBasicCharacter : " + !regexBasicCharacter.test(value));
         // console.log("============================");

         if (!value || ($(this).is("select") && value === "")) {
            boolValid = false;
            var formField = $(this).parents(".form-group");
            if (formField.find(".error-display").length === 0) {
               formField.append("<div class='error-display' style='color: red'>Please fill this field</div>");
            }
         } else if (!regexBasicCharacter.test(value)) {
            boolValid = false;
            var formField = $(this).parents(".form-group");
            if (formField.find(".error-display").length === 0) {
               formField.append("<div class='error-display' style='color: red'>Allowed special character .,/()@&_-</div>");
            }
         }

         console.log(boolValid);
      });

      console.log("akhir : " + boolValid);
      return boolValid;
   }

   function addFile(seq) {
      document.getElementById("fileName" + seq).value = document.getElementById('file' + seq).files.item(0).name;
   }


   $("#trainingAgendaFile").change(function (e) {
      processUpload(e, "trainingAgenda", $(this));
   });

   var submitProcess = false;
   $("#frmUploadRegister").submit(function (e) {
      e.preventDefault();
      const dealerCode = $('#frmUploadRegister select.form-required').val();
      const trainingAgendaFileId = $('[name="trainingAgendaFileId"]').val();
      const trainingAgendaFilePath = $('[name="trainingAgendaFilePath"]').val();
      const trainingAgendaFileName = $('[name="trainingAgendaFileName"]').val();

      if (submitProcess === false) {
         if (dealerCode === null || dealerCode === undefined || dealerCode.length < 1) {
            Swal.fire("Dealer belum terisi.", "", "warning");
            return false;
         } else if (trainingAgendaFileId === null || trainingAgendaFileId === undefined || trainingAgendaFileId.length < 1) {
            Swal.fire("File belum diupload.", "", "warning");
            return false;
         } else if (!trainingAgendaFileName.toLowerCase().endsWith('xlsx')) {
            Swal.fire('Mohon upload file dengan format .xlsx', '', '');
            return false;
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.set('<portlet:namespace/>acaraId', acaraId);
            formData.set('<portlet:namespace/>trainingAgendaFileId', trainingAgendaFileId);
            formData.set('<portlet:namespace/>trainingAgendaFilePath', trainingAgendaFilePath);
            formData.set('<portlet:namespace/>trainingAgendaFileName', trainingAgendaFileName);
            formData.set('<portlet:namespace/>p_auth', Liferay.authToken);

            formData.forEach(function (value, key) {
               console.log(key, value);
            });

            createLoading();
            $.ajax({
               url: '${submitUploadPendaftaranMateriNonCMSCommandURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var status = JSON.parse(response).status;
                  var message = JSON.parse(response).message;

                  if (status === 'success') {
                     swal.fire("Success", "Your request has been saved", "success")
                        .then(function () {
                              window.location = homeUrl;
                           }
                        );
                  } else if (status === 'warning') {
                     swal.fire('Sorry', message, 'warning');
                  } else {
                     swal.fire('Sorry', 'There is an internal error', 'error');
                  }
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
               },
               complete: function (jqXHR, textStatus) {
                  submitProcess = false;
                  destroyLoading();
               }
            });
         }
      }
   });

   function formValidation($form) {
      var isValidate = true;
      return isValidate;
   }

   function processUpload(e, name, element) {
      const allowedTypes = ['application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'];
      var file = e.target.files[0];

      if (file.size > 1048576) {
         swal.fire("", "Mohon tidak mengunggah file melebihi 1MB", "warning");
      } else if (!allowedTypes.includes(file.type)) {
         swal.fire('', 'Mohon unggah berkas dengan format xlsx', 'warning');
      } else {
         let fileName = e.target.files[0].name;
         uploadDocumentFile(e.target.files[0], fileName, $('[name="' + name + 'FileId"]')[0].value, element, name);
         return true;
      }
      $("#trainingAgendaFile").val(null);
   }

   function uploadDocumentFile(file, fileName, fileID, element, name) {
      // console.log("masuk sampe uploadFile");
      let formData = new FormData();
      let format = /[:"\\|,<>\/?^*]/;
      if (format.test(fileName)) {
         swal.fire('', 'Mohon tidak menggunakan karakter ,^*:"|<>\\ /?', 'warning');
      } else {
         fileName = fileName.replaceAll(",", "")
         formData.set('<portlet:namespace/>file-upload', file);
         formData.set('<portlet:namespace/>file-name', fileName);
         formData.set('<portlet:namespace/>p_auth', Liferay.authToken);

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
            url: '${uploadFilePendaftaranMateriNonCMSCommandURL}',
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
                  $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileId"]'))[0].value = fileID;
                  $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileName"]'))[0].value = fileName;
                  $(elementEdit.parent().siblings('[name="' + nameEdit + 'FilePath"]'))[0].value = fileURL;

                  let successHTML = `
                     <div style="margin-top: -8px;margin-bottom: 15px;padding: 0 15px;display: flex; align-items: center; justify-content: space-between;">
                        <div>
                           <p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px">Upload file berhasil </p>
                        </div>
                        <div class="wrap-btn">
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
</script>