<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/library/quill/katex.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/library/quill/monokai-sublime.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/library/quill/quill.snow.css" />

<portlet:resourceURL id="/training-agenda-action" var="trainingAgendaActionURL" />
<portlet:resourceURL id="/upload-file-training-agenda" var="uploadFileTrainingAgendaCommandURL" />
<portlet:resourceURL id="/delete-file-training-agenda" var="deleteFileTrainingAgendaCommandURL" />

<portlet:resourceURL id="/jenis-agenda-training-agenda" var="jenisAgendaTrainingAgendaURL"/>
<portlet:resourceURL id="/status-agenda-training-agenda" var="statusAgendaTrainingAgendaURL"/>

<style>
    /*Select2*/
    .select2-container--default .select2-selection--single {
        padding:6px;
        /*margin-top: 16px;*/
        /*margin-bottom: 16px;*/
        height: 37px;
        /*width: 148px;*/
        font-size: 1.2em;
        position: relative;
    }

    #editor-container {
        height: 375px;
    }
</style>

<form data-toggle="validator" role="form" id="formTrainingAgenda" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
   <div class="container" style="margin-left: 0;">
      <h3 id="form-title" style="margin-bottom: 50px;">Kelola Agenda</h3>
      <div class="form-group col-md-9">
         <label class="title-form pull-left" for="judulAgenda">Judul Agenda Pelatihan <span style="color: red;">*</span></label>
         <input type="text" class="form-control" name="judulAgenda" id="judulAgenda"
                pattern="[A-Za-z0-9. ]+" data-minlength="5" maxlength="255"
                data-error="Text dengan 5-255 karakter & tidak boleh kosong." required/>
         <div class="help-block with-errors"></div>
      </div>
      <div class="form-group col-md-9">
         <label class="title-form pull-left" for="jenisAgendaId">Jenis Agenda Pelatihan <span style="color: red;">*</span></label>
         <input type="hidden" class="form-control" name="jenisAgendaName" />
         <select class="form-control" name="jenisAgendaId" id="jenisAgendaId" style="width: 100%;">
            <%-- <option value="NULL">List Dealer</option>--%>
         </select>
      </div>
      <div class="form-group">
         <div class="col-sm-9" style="margin-bottom: 10px;">
            <label class="col-form-label ipr-color">Image (760x450) <span style="color: red;">*</span></label>
            <div class="input-group" >
               <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                  <span class="icon-browse"><img src="<%=request.getContextPath()%>/assets/img/file.svg" alt=""></span>
                  <span class="text-browse">Pilih File</span>
                  <input type="file" accept="image/gif, image/jpeg, image/png" placeholder="" name="trainingAgendaImageFile" id="trainingAgendaImageFile" data-filename="" data-location="" style="display: none;" aria-invalid="false" class="materail_input valid">
               </label>
               <input type="text" class="form-control required" data-type="file" data-name="" name="trainingAgendaImageFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
               <input type="hidden" class="dpn" name="trainingAgendaImageFileId" />
               <input type="hidden" class="dpn" name="trainingAgendaImageFilePath" />
            </div>
            <label class="col-form-label ipr-gray">*maks. 5MB</label>
         </div>
      </div>
      <div class="form-group col-md-9 lokasiAgenda">
         <label class="title-form pull-left" for="lokasiAgenda">Lokasi <span style="color: red;">*</span></label>
         <input type="text" class="form-control" name="lokasiAgenda" id="lokasiAgenda"
                pattern="[A-Za-z0-9. ]+" data-minlength="0" maxlength="255"
                data-error="Text maksimal 255 karakter."/>
         <div class="help-block with-errors"></div>
      </div>
      <div class="form-group col-md-9 linkAgenda">
         <label class="title-form pull-left" for="linkAgenda">Link Meeting <span style="color: red;">*</span></label>
         <input type="text" class="form-control" name="linkAgenda" id="linkAgenda"
                pattern="[A-Za-z0-9. -:/]+" data-minlength="0" maxlength="255"
                data-error="Text maksimal 255 karakter."/>
         <div class="help-block with-errors"></div>
      </div>
      <div class="form-group col-md-9">
         <div class="row">
            <div class="col-lg-4">
               <div class="form-group">
                  <label class="title-form pull-left" for="startDate">Tanggal Mulai <span style="color: red;">*</span></label>
                  <input type='text' class="form-control dateIcon required" name='startDate' id='startDate' style="margin-top: 15px;font-size: 15px;border: 1px solid gray;" />
               </div>
            </div>
            <div class="col-lg-4">
               <div class="form-group">
                  <label class="title-form pull-left" for="endDate">Tanggal Berakhir <span style="color: red;">*</span></label>
                  <input type='text' class="form-control dateIcon required" name='endDate' id='endDate' style="margin-top: 15px;font-size: 15px;border: 1px solid gray;" />
               </div>
            </div>
            <div class="col-lg-4">
               <div class="form-group">
                  <label class="title-form pull-left" for="registrationDate">Batas Pendaftaran <span style="color: red;">*</span></label>
                  <input type='text' class="form-control dateIcon required" name='registrationDate' id='registrationDate' style="margin-top: 15px;font-size: 15px;border: 1px solid gray;" />
               </div>
            </div>
         </div>
      </div>
      <div class="col-md-9">
         <p>Deskripsi <span style="color: red;">*</span></p>
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
      <div class="col-md-9" style="border-top: 1px solid #D9D9D9; margin: 10px 15px"></div>
      <div class="col-md-9">
         <button type="submit" class="btn-ipr">Simpan</button>
         <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="/group/dealink/cms-training">Batal</a>
      </div>
   </div>
</form>

<script src="<%=request.getContextPath()%>/assets/library/quill/katex.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/quill/highlight.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/quill/quill.min.js"></script>
<script>
   var data = ${data};
   var action = "${action}";
   var entryId = "0";
   var homeUrl = "/group/dealink/cms-training";
   var quill;
   var submitProcess = false;
   var jenisAgendaData = [{id: 0, text: 'Acara Offline'},{id: 1, text: 'Acara Online'}]

   $(document).ready(function() {
      const options = {
         debug: 'info',
         modules: {
            syntax: true,
            toolbar: '#toolbar-container'
            // toolbar: [
            //    [{ header: [1, 2, 3, false] }],
            //    ['background', 'bold', 'color', 'font', 'code',  'italic', 'link', 'size', 'script', 'underline'],
            //    ['blockquote', 'header', 'indent', 'list', 'align', 'direction', 'code-block'],
            //    ['formula', 'image', 'video']
            // ]
         },
         // placeholder: 'Compose an epic...',
         // readOnly: true,
         theme: 'snow'  // 'snow' or 'bubble'
      };
      quill = new Quill('#editor-container', options);

      createJenisAgendas();
      setTanggalMulai();
      setTanggalBerakhir();
      setRegisterEnd();
      if (action === "create") {
         $('#form-title').text("New Agenda")
         formNew();
      } else if (action === "update") {
         $('#form-title').text("Edit Agenda")
         formEdit();
      } else {
         $('#form-title').text("Agenda")
         formEdit();
      }
   });

   function formNew() {
      entryId = "0";
      // $('[name="entryId"]')[0].value = "0";
      $('[name="judulAgenda"]')[0].value = "";
      $('[name="jenisAgendaId"]')[0].value = "";
      $('[name="jenisAgendaName"]')[0].value = "";
      $('[name="lokasiAgenda"]')[0].value = "";
      $('[name="linkAgenda"]')[0].value = "";
      $('[name="startDateAgenda"]')[0].value = "";
      $('[name="endDateAgenda"]')[0].value = "";
      $('[name="registrationDateAgenda"]')[0].value = "";
      // $('[name="deskripsi"]')[0].value = "";
      $('[name="trainingAgendaImageFileId"]')[0].value = "";
      $('[name="trainingAgendaImageFileName"]')[0].value = "";
      $('[name="trainingAgendaImageFilePath"]')[0].value = "";
   }

   function formEdit() {
      entryId = data.id;
      // $('[name="entryId"]')[0].value = data.id;
      $('[name="judulAgenda"]')[0].value = data.judul;
      $('[name="jenisAgendaId"]')[0].value = data.jenisAgenda;
      var jenisAgendaName = ''
      if(data.jenisAgenda === 0) {
         jenisAgendaName = 'Acara Offline'
      } else {
         jenisAgendaName = 'Acara Online'
      }
      $('[name="jenisAgendaName"]')[0].value = jenisAgendaName;
      $('[name="lokasiAgenda"]')[0].value = data.lokasi;
      $('[name="linkAgenda"]')[0].value = data.linkMeeting;

      quill.clipboard.dangerouslyPasteHTML(data.deskripsi);
      $('[name="trainingAgendaImageFileId"]')[0].value = data.imageId;
      $('[name="trainingAgendaImageFileName"]')[0].value = data.imageName;
      $('[name="trainingAgendaImageFilePath"]')[0].value = data.imagePath;
   }

   $("#trainingAgendaFile").change(function(e){
      processUpload(e, "trainingAgenda", $(this));
   });

   $("#trainingAgendaImageFile").change(function(e){
      processUpload(e, "trainingAgendaImage", $(this));
   });

   $("#formTrainingAgenda").submit(function (e) {
      e.preventDefault();
      const judul = $('[name="judulAgenda"]').val()
      const jenisAgendaId = $('[name="jenisAgendaId"]').val()
      const lokasi = $('[name="lokasiAgenda"]').val()
      const linkMeeting = $('[name="linkAgenda"]').val()
      const trainingAgendaImageFileId = $('[name="trainingAgendaImageFileId"]').val()
      const startDate = $('[name="startDate"]').val()
      const endDate = $('[name="endDate"]').val()
      const registrationDate = $('[name="registrationDate"]').val()
      const deskripsiHtml = quill.container.innerHTML;
      if (submitProcess === false) {
         if (judul === null || judul === undefined || judul.length < 5) {
            Swal.fire("Judul belum sesuai.", "", "warning");
            return false;
         } else if(/^[a-zA-Z0-9- ]*$/.test(judul) === false) {
            Swal.fire("Judul tidak boleh memakai karakter khusus.", "", "warning");
            return false;
         } else if (jenisAgendaId === null || jenisAgendaId === undefined) {
            Swal.fire("Jenis Agenda belum diisi.", "", "warning");
            return false;
         } else if (jenisAgendaId === '0' && (lokasi === null || lokasi === undefined || lokasi.length < 5)) {
            Swal.fire("Lokasi belum sesuai.", "", "warning");
            return false;
         } else if (jenisAgendaId === '1' && (linkMeeting === null || linkMeeting === undefined || linkMeeting.length < 5)) {
            Swal.fire("Link Meeting belum sesuai.", "", "warning");
            return false;
         } else if (trainingAgendaImageFileId === null || trainingAgendaImageFileId === undefined || trainingAgendaImageFileId.length < 1) {
            Swal.fire("Image belum diisi.", "", "warning");
            return false;
         } else if (startDate === null || startDate === undefined || startDate.length < 3) {
            Swal.fire("Tanggal Mulai belum sesuai.", "", "warning");
            return false;
         } else if (endDate === null || endDate === undefined || endDate.length < 3) {
            Swal.fire("Tanggal Berakhir belum sesuai.", "", "warning");
            return false;
         } else if (registrationDate === null || registrationDate === undefined || registrationDate.length < 3) {
            Swal.fire("Batas Pendaftaran belum sesuai.", "", "warning");
            return false;
         } else if (quill.getText().trim().length < 5) {
            Swal.fire("Deskripsi belum sesuai.", "", "warning");
            return false;
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.set("<portlet:namespace/>crudType", action)
            formData.set("<portlet:namespace/>entryId",entryId);
            formData.set("<portlet:namespace/>deskripsi", deskripsiHtml);
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            createLoading();
            $.ajax({
               url: '${trainingAgendaActionURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  if (JSON.parse(response).status === 'success') {
                     swal.fire("Success", "Your request has been saved", "success")
                     .then(function () {
                        window.location = homeUrl;
                     });
                  } else if (JSON.parse(response).status === 'warning') {
                     swal.fire('Sorry', JSON.parse(response).message, 'warning');
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

   function uploadDocumentFile(file, fileName, fileID, element, name) {
      let formData = new FormData();
      let format = /[:"\\|,<>\/?^*]/;
      if (format.test(fileName)) {
         swal.fire('', 'File name cannot contains special character ,^*:"|<>\\ /?', 'warning')
      } else {
         fileName = fileName.replaceAll(",", "")
         formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
         formData.set("<portlet:namespace/>file-upload", file);
         formData.set("<portlet:namespace/>file-name", fileName);
         formData.set("<portlet:namespace/>file-id", fileID);

         element.parents(".input-group").siblings(".upload-content").remove();
         element.val("");

         let htmlLoading = $(`
                <div class="upload-content">
                    <div class="progress" style="margin-top: 10px; margin-bottom: 0; transform: scaleY(0.7);">
                        <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" style="width: 0;"></div>
                    </div>
                    <div id="progress-percentage">0% uploaded</div>
                </div>
            `);

         element.parents(".input-group").after(htmlLoading);

         let elementEdit = element;
         let nameEdit = name;

         $.ajax({
            url: '${uploadFileTrainingAgendaCommandURL}',
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
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      formData.set("<portlet:namespace/>file-id", fileID);

      let parentElement  = element.parentElement.parentElement.parentElement
      // let inputElement = $(parentElement1.siblings().find('input.form-control.required.switch-readonly'))[0];
      let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url : '${deleteFileTrainingAgendaCommandURL}',
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

   function createJenisAgendas() {
      const jenisAgendaConst = $('#jenisAgendaId').select2({
         data: jenisAgendaData,
         tags: false,
         placeholder: 'Pilih Jenis Agenda',
         allowClear: false,
         minimumInputLength: -1,
         maximumInputLength: 100,
         templateSelection: function (data) {
            if(data.id === '0') {
               $( ".linkAgenda" ).hide();
               $( ".lokasiAgenda" ).show();
            }
            if(data.id === '1') {
               $( ".linkAgenda" ).show();
               $( ".lokasiAgenda" ).hide();
            }
            if (data.id === 'Pilih Jenis Agenda') {
               return 'Pilih Jenis Agenda';
            } else {
               $('[name="jenisAgendaId"]')[0].value = data.id;
            }
            return data.text;
         }
      });
      if(action === "update") {
         jenisAgendaConst.val(data.jenisAgenda);
         jenisAgendaConst.trigger('change');
      } else {
         jenisAgendaConst.val(null);
         jenisAgendaConst.trigger('change');
      }
   }

   function setTanggalMulai(){
      $('#startDate').datetimepicker({
         date: data.startDateFull,
         minDate: moment(new Date()).format('YYYY/MM/DD'),
         format: 'DD/MM/YYYY HH:mm',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: true
      })
      .on('dp.change', function(e) {
         $('[name="endDate"]')[0].value = "";
         $('[name="registrationDate"]')[0].value = "";
         const maxEnd = moment(e.date).add(30, 'days');
         $('#endDate').data("DateTimePicker").maxDate(maxEnd);
         const maxReg = moment(e.date).subtract(1, 'days');
         $('#registrationDate').data("DateTimePicker").maxDate(maxReg);
         const minEnd = moment(e.date).add(0, 'days');
         $('#endDate').data("DateTimePicker").minDate(minEnd);
         const minReg = moment(e.date).subtract(1, 'days');
         $('#registrationDate').data("DateTimePicker").minDate(minReg);
      })
   }

   function setTanggalBerakhir(){
      $('#endDate').datetimepicker({
         date: data.endDateFull,
         format: 'DD/MM/YYYY HH:mm',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: false
      })
      .on('dp.change', function(e) {
         console.log('dateEnd : ' + e.date)
      })
   }

   function setRegisterEnd() {
      $('#registrationDate').datetimepicker({
         date: data.registrationDateFull,
         format: 'DD/MM/YYYY HH:mm',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: false
      })
      .on('dp.change', function(e) {
         console.log('dateReg : ' + e.date)
      })
      const maxEnd = moment(data.startDateFull).add(30, 'days');
      $('#endDate').data("DateTimePicker").maxDate(maxEnd);
      const maxReg = moment(data.startDateFull).subtract(1, 'days');
      $('#registrationDate').data("DateTimePicker").maxDate(maxReg);
      const minEnd = moment(data.startDateFull).add(0, 'days');
      $('#endDate').data("DateTimePicker").minDate(minEnd);
      const minReg = moment(data.startDateFull).subtract(1, 'days');
      $('#registrationDate').data("DateTimePicker").minDate(minReg);
   }
</script>
