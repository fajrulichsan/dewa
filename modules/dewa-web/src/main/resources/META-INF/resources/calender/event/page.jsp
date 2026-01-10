<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/calender/event/style-css.jsp" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/library/quill/katex.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/library/quill/monokai-sublime.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/library/quill/quill.snow.css" />

<%--View--%>
<portlet:resourceURL id="calender-event-list" var="calenderEventListURL"/>
<portlet:renderURL var="calenderEventEditURL">
   <portlet:param name="mvcRenderCommandName" value="calender-event-edit"/>
</portlet:renderURL>
<portlet:renderURL var="calenderEventParticipantViewURL">
   <portlet:param name="mvcRenderCommandName" value="calender-event-participant-view"/>
</portlet:renderURL>

<%--Create--%>
<portlet:resourceURL id="calender-event-action" var="calenderEventActionURL"/>
<portlet:resourceURL id="upload-file-calender-event" var="uploadFileCalenderEventCommandURL"/>
<portlet:resourceURL id="delete-file-calender-event" var="deleteFileCalenderEventCommandURL"/>

<div class="cms-menu">
   <%--<h3 style="font-size: 24px; font-weight: 700; margin-bottom: 1em;">Buat Acara</h3>--%>
   <div class="tabcontent" id="cms-tabcontent">
      <ul class="nav nav-tabs" id="cmsTab" role="tablist">
         <li class="nav-item">
            <a class="nav-link" id="buat_acara_navtab" data-toggle="tab" href="#buat_acara_tab" role="tab" aria-controls="buat-acara" aria-selected="false">Buat Acara</a>
         </li>
         <li class="nav-item">
            <a class="nav-link" id="kelola_acara_navtab" data-toggle="tab" href="#kelola_acara_tab" role="tab" aria-controls="kelola-acara" aria-selected="true">Kelola Acara</a>
         </li>
      </ul>
      <div class="tab-content" id="myTabContent">
         <div class="tab-pane fade" id="buat_acara_tab" role="tabpanel" aria-labelledby="profile-tab">
            <%-- <%@ include file = "create.jsp" %> --%>
            <form <%-- data-toggle="validator" --%>role="form" id="formCalenderEvent" method="post" enctype="multipart/form-data" autocomplete="off">
               <div class="container row form-field">
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="judul">
                        Judul Acara
                        <span style="color: red;">*</span>
                     </label>
                     <input type="text" class="form-control" name="judul" id="judul"
                            pattern="[A-Za-z0-9. ]+" minlength="5" maxlength="255"
                            data-error="Text dengan 5-255 karakter & tidak boleh kosong." required/>
                     <%--<div class="help-block with-errors"></div>--%>
                  </div>
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="jenisAcaraId">
                        Jenis Acara
                        <span style="color: red;">*</span>
                     </label>
                     <select class="form-control" id="jenisAcaraId" name="jenisAcaraId" style="width: 100%;"></select>
                  </div>
                  <div class="form-group col-sm-9">
                     <label class="col-form-label ipr-color">
                        Image (760x450)
                        <span style="color: red;">*</span>
                     </label>
                     <div class="input-group upload-file-group">
                        <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                           <span class="icon-browse">
                              <img alt="" src="<%=request.getContextPath()%>/assets/img/file.svg">
                           </span>
                           <span class="text-browse">Pilih File</span>
                           <input type="file" accept="image/gif, image/jpeg, image/png" placeholder="" name="calenderEventImageFile" id="calenderEventImageFile" data-filename="" data-location="" style="display: none;" aria-invalid="false" class="materail_input valid">
                        </label>
                        <input type="text" class="form-control required" data-type="file" data-name="" name="calenderEventImageFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
                        <input type="hidden" class="dpn" name="calenderEventImageFileId" />
                        <input type="hidden" class="dpn" name="calenderEventImageFilePath" />
                     </div>
                     <label class="col-form-label ipr-gray">* Ukuran maksimal 5MB</label>
                  </div>
                  <div class="form-group col-md-9 lokasiMeeting">
                     <label class="title-form pull-left" for="lokasi">
                        Lokasi
                        <span style="color: red;">*</span>
                     </label>
                     <input type="text" class="form-control" name="lokasi" id="lokasi"
                            pattern="[A-Za-z0-9. ]+" minlength="5" maxlength="255"
                            data-error="Text dengan 5-255 karakter & tidak boleh kosong."/>
                     <%--<div class="help-block with-errors"></div>--%>
                  </div>
                  <div class="form-group col-md-9 linkMeeting">
                     <label class="title-form pull-left" for="linkMeeting">
                        Link Meeting
                        <span style="color: red;">*</span>
                     </label>
                     <input type="text" class="form-control" name="linkMeeting" id="linkMeeting"
                            pattern="[A-Za-z0-9. -:/]+" minlength="5" maxlength="255"
                            data-error="Text dengan 5-255 karakter & tidak boleh kosong."/>
                     <%--<div class="help-block with-errors"></div>--%>
                  </div>
                  <div class="form-group date-group col-md-9">
                     <div class="row">
                        <div class="col-lg-4">
                           <%--<div class="form-group">--%>
                              <label class="title-form pull-left" for="startDate">
                                 Tanggal Mulai
                                 <span style="color: red;">*</span>
                              </label>
                              <input type='text' class="form-control dateIcon required" name='startDate' id='startDate' readonly />
                           <%--</div>--%>
                        </div>
                        <div class="col-lg-4">
                           <%--<div class="form-group">--%>
                              <label class="title-form pull-left" for="endDate">
                                 Tanggal Berakhir
                                 <span style="color: red;">*</span>
                              </label>
                              <input type='text' class="form-control dateIcon required" name='endDate' id='endDate' readonly />
                           <%--</div>--%>
                        </div>
                        <div class="col-lg-4">
                           <%--<div class="form-group">--%>
                              <label class="title-form pull-left" for="registrationDate">
                                 Batas Pendaftaran
                                 <%--<span style="color: red;">*</span>--%>
                              </label>
                              <input type='text' class="form-control dateIcon required" name='registrationDate' id='registrationDate' readonly />
                           <%--</div>--%>
                        </div>
                     </div>
                  </div>
                  <div class="form-group col-md-9">
                     <p class="title-form">
                        Deskripsi
                        <span style="color: red;">*</span>
                     </p>
                     <div id="standalone-container">
                        <div id="toolbar-container">
                           <span class="ql-formats">
                              <%--<select class="ql-font"></select>--%>
                              <select class="ql-size"></select>
                            </span>
                           <span class="ql-formats">
                              <button class="ql-bold"></button>
                              <button class="ql-italic"></button>
                              <button class="ql-underline"></button>
                              <button class="ql-strike"></button>
                           </span>
                           <span class="ql-formats">
                              <select class="ql-color"></select>
                              <select class="ql-background"></select>
                           </span>
                           <span class="ql-formats">
                              <button class="ql-script" value="sub"></button>
                              <button class="ql-script" value="super"></button>
                           </span>
                           <span class="ql-formats">
                              <button class="ql-header" value="1"></button>
                              <button class="ql-header" value="2"></button>
                              <button class="ql-blockquote"></button>
                              <button class="ql-code-block"></button>
                           </span>
                           <span class="ql-formats">
                              <button class="ql-list" value="ordered"></button>
                              <button class="ql-list" value="bullet"></button>
                              <button class="ql-indent" value="-1"></button>
                              <button class="ql-indent" value="+1"></button>
                           </span>
                           <span class="ql-formats">
                              <button class="ql-direction" value="rtl"></button>
                              <select class="ql-align"></select>
                            </span>
                           <span class="ql-formats">
                              <button class="ql-link"></button>
                              <button class="ql-image"></button>
                              <button class="ql-video"></button>
                              <button class="ql-formula"></button>
                            </span>
                           <span class="ql-formats">
                              <button class="ql-clean"></button>
                           </span>
                        </div>
                        <div id="editor-container"></div>
                     </div>
                  </div>
                  <div class="form-group col-md-9">
                     <div class="action-line"></div>
                  </div>
                  <div class="form-group col-md-9 action-button">
                     <button type="button" class="btn btn-ipr-cancel" id="resetButton">Reset</button>
                     <button type="submit" class="btn-ipr">Simpan</button>
                  </div>
               </div>
            </form>
         </div>
         <div class="tab-pane fade" id="kelola_acara_tab" role="tabpanel" aria-labelledby="profile-tab">
            <%--<%@ include file = "view.jsp" %>--%>
               <div class="row table-filters">
                  <div class="col-xs-12 col-sm-12 col-md-3" style="text-align: left;">
                     <select class="form-control" name="viewStatusAcaraId" id="viewStatusAcaraId" style="width: 100%;">
                        <option value="ALL">Select All</option>
                     </select>
                  </div>
               </div>
               <table id="calender_event_table" class="table table-hover nowrap cms-table" style="width:100%">
                  <thead>
                     <tr>
                        <th>No</th>
                        <th>Acara</th>
                        <th>Tanggal Acara</th>
                        <th>Jenis Acara</th>
                        <th>Status</th>
                        <th>Aksi</th>
                     </tr>
                  </thead>
                  <tbody></tbody>
               </table>
         </div>
      </div>
   </div>
</div>

<script src="<%=request.getContextPath()%>/assets/library/quill/katex.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/quill/highlight.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/quill/quill.min.js"></script>

<script>
   // View
   var viewJenisAcaraId = "ALL";
   var viewStatusAcaraId = "ALL";
   var jenisAcaraData = [{id: 0, text: 'Acara Offline'}, {id: 1, text: 'Acara Online'}];
   var viewStatusAcaraData = [{id: 0, text: 'Belum Terlaksana'}, {id: 1, text: 'Sudah Terlaksana'}];

   var languageOptions = {
      // info: "rows",
      "lengthMenu": "_MENU_",
      "paginate": {
         "next": '<span class="glyphicon glyphicon-menu-right"></span>',
         "previous": '<span class="glyphicon glyphicon-menu-left"></span>'
      },
      "search": "",
      searchPlaceholder: "Cari"
   }

   $(function () {
      $("#calender_event_table").DataTable().destroy();
      call_calender_event_table()
   });

   function call_calender_event_table() {
      var calenderTable = $('#calender_event_table').DataTable({
         destroy: true,
         processing: true,
         serverSide: true,
         paging: true,
         info: true,
         autoWidth: true,
         responsive: true,
         order: [2,3,4,5],
         language: languageOptions,
         ajax: {
            url: '${calenderEventListURL}',
            type: 'GET',
            data: function(d) {
               d.statusAcaraId =  viewStatusAcaraId;
            },
         },
         columns: [
            {data: "no", "width": "5%", className: "text-center", orderable: false},
            {data: "judul", "width": "25%", render: $.fn.dataTable.render.ellipsis(25)},
            {data: "startDate", "width": "20"},
            {
               data: "jenisAcara",
               render: function (data, type, row, meta) {
                  var html = '';
                  if( data === 0 ) {
                     html = 'Acara Offline';
                  } else if( data === 1) {
                     html = 'Acara Online';
                  }
                  return html;
               },
               "width": "10%",
               className: "text-center"
            },
            {
               data: "statusAcara",
               render: function (data, type, row, meta) {
                  var html = '';
                  if( data === 0 ) {
                     html = '<a class="btn-status btn-on-danger" href="javascript:void(0)">Belum Terlaksana</a>';
                  } else if( data === 1) {
                     html = '<a class="btn-status btn-on-warning" href="javascript:void(0)">Sudah Terlaksana</a>';
                  }
                  return html;
               },
               "width": "10%",
               className: "text-center"
            },
            {
               data: "id",
               render: function (data, type, row, meta) {
                  return renderActionButton(data, row);
               },
               "width": "7%",
               className: "text-center",
               orderable: false
            }
         ]
      });
      $(".dataTables_filter input")
      .unbind()
      .bind("input", function(e) {
         if(this.value.length >= 3 || e.keyCode === 13) {
            calenderTable.search(this.value).draw();
         }
         if(this.value === "") {
            calenderTable.search("").draw();
         }
      });
   }

   function renderActionButton(dataId, row) {
      var response = "";
      var editMenu = '<a href="${calenderEventEditURL}&id=' + dataId + '&p_auth=' + Liferay.authToken + '">' +
            '<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg" /></span>' +
         '</a> &nbsp;';
      var deleteMenu = '<a href="javascript:void(0)" onclick="deleteCalenderEvent(this)" data-id="' + dataId + '" >' +
            '<span><img src="<%=request.getContextPath()%>/assets/img/trash.svg" /></span>' +
         '</a> &nbsp;';
      var participantMenu = '<a href="${calenderEventParticipantViewURL}&calenderEventId=' + dataId + '" class="btn btn-sm" style="border: 1px solid #D9D9D9;color: black;">' +
            '<i class="fa fa-users"></i>' +
         '</a>';
      if(row.statusAcara === 1) {
         response = '<span class="action-wrapper" data-id="' + dataId + '">' +
            deleteMenu +
            participantMenu +
         '</span>';
      } else {
         response = '<span class="action-wrapper" data-id="' + dataId + '">' +
            editMenu +
            deleteMenu +
            participantMenu +
         '</span>';
      }
      return response;
   }

   function deleteCalenderEvent(element) {
      var entryId = $(element).data("id");
      Swal.fire({
         title: 'Apakah anda yakin ingin menghapus data ini?',
         icon: 'question',
         showCancelButton: true,
         confirmButtonText: 'Yes',
         confirmButtonColor: '#EE1C25',
      }).then((result) => {
         if (result.isConfirmed) {
            var formData = new FormData();
            formData.set("<portlet:namespace/>crudType", "delete");
            formData.set("<portlet:namespace/>entryId", entryId);
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
            $.ajax({
               url: "${calenderEventActionURL}",
               type: "POST",
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var data = JSON.parse(response);
                  var acknowledge = data.acknowledge;
                  if (acknowledge === 1) {
                     Swal.fire("Data berhasil dihapus", "", "success")
                        .then((res) => {
                           call_calender_event_table();
                        });
                  } else {
                     Swal.fire("Gagal menghapus data", '', "warning");
                  }
               },
               error: function (err) {
                  console.log(err);
               }
            });
         }
      });
   }

   function viewStatusAcaras() {
      var viewStatusAcaraConst = $('#viewStatusAcaraId').select2({
         data: viewStatusAcaraData,
         tags: "true",
         placeholder: 'Status',
         allowClear: false,
         minimumInputLength: -1,
         maximumInputLength: 100,
         templateSelection: function (data) {
            if(data.id !== '') {
               viewStatusAcaraId = data.id;
               call_calender_event_table()
            }
            return data.text;
         }
      });
      viewStatusAcaraConst.val(null);
      viewStatusAcaraConst.trigger('change');
   }
</script>
<script>
   // Create Page
   var action = "create";
   var entryId = "0";
   var quill
   var submitProcess = false;

   $("#resetButton").click(function(){
      resetForm()
   });

   function resetForm() {
      createJenisAcaras();
      setTanggalMulai();
      setTanggalBerakhir();
      setRegisterEnd();
      formNew();
   }

   function formNew() {
      entryId = "0";
      $('[name="judul"]')[0].value = "";
      $('[name="jenisAcaraId"]')[0].value = "";
      $('[name="lokasi"]')[0].value = "";
      $('[name="linkMeeting"]')[0].value = "";
      $('[name="startDate"]')[0].value = "";
      $('[name="endDate"]')[0].value = "";
      $('[name="registrationDate"]')[0].value = "";
      // $('[name="deskripsi"]')[0].value = "";
      $('[name="calenderEventImageFileId"]')[0].value = "";
      $('[name="calenderEventImageFileName"]')[0].value = "";
      $('[name="calenderEventImageFilePath"]')[0].value = "";

      $(".linkMeeting").hide();
      $(".lokasiMeeting").hide();
      $(".upload-content").remove();
      // Clear Quill
      var element = document.getElementsByClassName("ql-editor");
      element[0].innerHTML = "";
   }

   $("#calenderEventImageFile").change(function(e) {
      const allowedTypes = ['image/gif', 'image/jpeg', 'image/png'];
      var imageFile = e.target.files[0];
      if (!allowedTypes.includes(imageFile.type)) {
         Swal.fire('Mohon unggah file dengan format jpg, jpeg, png, atau gif', '', 'warning');
         $(this).val(null);
      } else {
         processUpload(e, "calenderEventImage", $(this));
      }
   });

   $("#formCalenderEvent").submit(function (e) {
      e.preventDefault();
      const judul = $('[name="judul"]').val();
      const jenisAcaraId = $('[name="jenisAcaraId"]').val();
      const lokasi = $('[name="lokasi"]').val();
      const linkMeeting = $('[name="linkMeeting"]').val();
      const calenderEventImageFileId = $('[name="calenderEventImageFileId"]').val();
      const startDate = $('[name="startDate"]').val();
      const endDate = $('[name="endDate"]').val();
      const registrationDate = $('[name="registrationDate"]').val();
      const deskripsiHtml = quill.container.innerHTML;
      // console.log(quill.id)
      // console.log(quill.data)
      // console.log(quill.body)
      if (submitProcess === false) {
         if (judul === null || judul === undefined || judul.length < 5) {
            Swal.fire("Judul belum diisi", "", "warning");
            return false;
         } else if(!regexBasicCharacter.test(judul)) {
            Swal.fire("Mohon tidak menggunakan karakter selain .,/()@&_-", "", "warning");
            return false;
         } else if (jenisAcaraId === null || jenisAcaraId === undefined || jenisAcaraId.length < 1) {
            Swal.fire("Jenis Acara belum dipilih", "", "warning");
            return false;
         } else if (calenderEventImageFileId === null || calenderEventImageFileId === undefined || calenderEventImageFileId.length < 3) {
            Swal.fire("Image belum diunggah", "", "warning");
            return false;
         } else if (jenisAcaraId === '0' && (lokasi === null || lokasi === undefined || lokasi.length < 5)) {
            Swal.fire("Lokasi belum diisi", "", "warning");
            return false;
         } else if (jenisAcaraId === '0' && !regexBasicCharacter.test(lokasi)) {
            Swal.fire('Mohon tidak menggunakan karakter selain selain .,/()@&_-', '', 'warning');
            return false;
         } else if (jenisAcaraId === '1' && (linkMeeting === null || linkMeeting === undefined || linkMeeting.length < 5)) {
            Swal.fire("Link Meeting belum diisi", "", "warning");
            return false;
         } else if (jenisAcaraId === '1' && !regexLink.test(linkMeeting)) {
            Swal.fire('Format link belum sesuai', '', 'warning');
            return false;
         } else if (startDate === null || startDate === undefined || startDate.length < 3) {
            Swal.fire("Tanggal Mulai belum diisi", "", "warning");
            return false;
         } else if (endDate === null || endDate === undefined || endDate.length < 3) {
            Swal.fire("Tanggal Berakhir belum diisi", "", "warning");
            return false;
         <%--
         } else if (registrationDate === null || registrationDate === undefined || registrationDate.length < 3) {
            Swal.fire("Batas Pendaftaran belum diisi", "", "warning");
            return false;
         --%>
         } else if (quill.getText().trim().length < 5) {
            Swal.fire("Harap masukkan minimal 5 karakter", "", "warning");
            return false;
         } else {
            submitProcess = true;
            var formData = new FormData(this);
            formData.set("<portlet:namespace/>crudType", action);
            formData.set("<portlet:namespace/>entryId", entryId);
            formData.set("<portlet:namespace/>deskripsi", deskripsiHtml);
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            createLoading();
            $.ajax({
               url: '${calenderEventActionURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var status = JSON.parse(response).status;
                  var code = JSON.parse(response).code;
                  var message = JSON.parse(response).message;

                  if (status === 'success') {
                     swal.fire("Data berhasil disimpan", "", "success")
                     .then(function () {
                        $("#calender_event_table").DataTable().destroy();
                        call_calender_event_table(viewJenisAcaraId, viewStatusAcaraId);
                        $('#kelola_acara_navtab')[0].click();
                        resetForm();
                     });
                  } else if (code) {
                     swal.fire(message, '', 'warning');
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
            })
         }
      }
   })

   // Upload File
   function processUpload(e, name, element){
      if(e.target.files[0].size > 5242880){
         swal.fire("", "File Size cannot more than 5 MB", "warning");
      }else{
         let fileName = e.target.files[0].name;
         uploadDocumentFile(e.target.files[0], fileName, $('[name="'+name+'FileId"]')[0].value, element, name);
      }
   }

   var elementNya;
   function uploadDocumentFile(file, fileName, fileID, element, name) {
      elementNya = element;
      let formData = new FormData();
      let format = /[:"\\|,<>\/?^*]/;
      if (format.test(fileName)) {
         swal.fire('', 'Mohon tidak menggunakan karakter ,^*:"|<>\\ /?', 'warning')
      } else {
         fileName = fileName.replaceAll(",", "")
         formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
         formData.set("<portlet:namespace/>file-upload", file);
         formData.set("<portlet:namespace/>file-name", fileName);
         formData.set("<portlet:namespace/>file-id", fileID);
         formData.set("<portlet:namespace/>document-id", $('[name="document-id"]').val());
         formData.set("<portlet:namespace/>document-id-new",$('[name="document-id-new"]').val());

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
            url: '${uploadFileCalenderEventCommandURL}',
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
               var message = data.message;

               if (acknowledge === 1) {
                  $(elementEdit.parent().siblings('[name="'+nameEdit+'FileId"]'))[0].value=fileID;
                  $(elementEdit.parent().siblings('[name="'+nameEdit+'FileName"]'))[0].value=fileName;
                  $(elementEdit.parent().siblings('[name="'+nameEdit+'FilePath"]'))[0].value=fileURL;

                  let successHTML = `
                     <div style="margin-top: -8px;margin-bottom: 15px;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
                        <div>
                           <p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px">Upload file berhasil </p>
                        </div>
                        <div>
                           <div id="delete-file-button">
                              <a href="javascript:void(0);" onclick="deleteUploadedFile(this, ` + fileID + `)" title="Delete File"><i class="fas fa-trash"></i></a>
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
                  swal.fire({icon: "error", title: message});
               }
            },
            error: function (e) {
               swal.fire({icon: "error", title: "Failed to upload file"});
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
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      formData.set("<portlet:namespace/>file-id", fileID);
      let parentElement  = element.parentElement.parentElement.parentElement
      // let inputElement = $(parentElement1.siblings().find('input.form-control.required.switch-readonly'))[0];
      let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url : '${deleteFileCalenderEventCommandURL}',
         type : 'POST',
         data : formData,
         processData: false,
         contentType: false,
         success: function(response) {
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
            swal({icon: "error", title: "Failed to delete file"});
         },
         complete: function() {
            console.log("DONE");
         }
      });
   }

   function createJenisAcaras() {
      const jenisAcaraConst = $('#jenisAcaraId').select2({
         data: jenisAcaraData,
         tags: false,
         placeholder: 'Pilih Jenis Acara',
         allowClear: false,
         minimumResultsForSearch: -1,
         maximumInputLength: 20,
         templateSelection: function (data) {
            if(data.id === '0') {
               $( ".linkMeeting" ).hide();
               $( ".lokasiMeeting" ).show();
            } else if(data.id === '1') {
               $( ".linkMeeting" ).show();
               $( ".lokasiMeeting" ).hide();
            }
            if (data.id === 'Pilih Jenis Acara') {
               return 'Pilih Jenis Acara';
            } else {
               $('[name="jenisAcaraId"]')[0].value = data.id;
            }
            return data.text;
         }
      });
      jenisAcaraConst.val(null);
      jenisAcaraConst.trigger('change');
   }

   function setTanggalMulai() {
      $('#startDate').datetimepicker({
         maxDate: moment(new Date()).add(256, 'days').format('YYYY/MM/DD'),
         minDate: moment(new Date()).format('YYYY/MM/DD'),
         format: 'DD/MM/YYYY HH:mm',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: true
      }).on("dp.change", function (e) {
         console.log('e.date :' + e.date)
         $('[name="endDate"]')[0].value = "";
         $('[name="registrationDate"]')[0].value = "";
         const maxEnd = moment(e.date).add(30, 'days');
         $('#endDate').data("DateTimePicker").maxDate(maxEnd);
         const minEnd = moment(e.date).add(0, 'days');
         $('#endDate').data("DateTimePicker").minDate(minEnd);
         try {
            // issue : maxReg harus lebih besar dari minReg, kalau sama tanggalnya tidak bisa
            const maxReg = moment(e.date).subtract(1, 'days');
            $('#registrationDate').data("DateTimePicker").maxDate(maxReg);
            const minReg = moment(e.date).subtract(1, 'days');
            $('#registrationDate').data("DateTimePicker").minDate(minReg);
         } catch (e) {
            const minReg = moment(e.date).subtract(1, 'days');
            $('#registrationDate').data("DateTimePicker").minDate(minReg);
            const maxReg = moment(e.date).subtract(1, 'days');
            $('#registrationDate').data("DateTimePicker").maxDate(maxReg);
         }
      });
   }

   function setTanggalBerakhir() {
      $('#endDate').datetimepicker({
         // minDate: moment(new Date()).format('YYYY/MM/DD'),
         format: 'DD/MM/YYYY HH:mm',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: false
      }).on("dp.change", function (e) {
         console.log('dateEnd : ' + e.date)
      });
   }

   function setRegisterEnd() {
      $('#registrationDate').datetimepicker({
         format: 'DD/MM/YYYY HH:mm',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: false
      }).on("dp.change", function (e) {
         console.log('dateReg : ' + e.date);
      });
   }

   $(document).ready(function () {
      // Create Page
      const options = {
         debug: 'info',
         modules: {
            syntax: true,
            toolbar: '#toolbar-container'
         },
         theme: 'snow'  // 'snow' or 'bubble'
      };
      quill = new Quill('#editor-container', options);
      setTanggalMulai();
      setTanggalBerakhir();
      setRegisterEnd();
      viewStatusAcaras();
      createJenisAcaras();
      $('#kelola_acara_navtab')[0].click();

      $('.dataTables_length select').select2({
         minimumResultsForSearch: -1
      });
   });
</script>