<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/library/quill/katex.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/library/quill/monokai-sublime.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/library/quill/quill.snow.css"/>

<portlet:resourceURL id="calender-event-action" var="calenderEventActionURL"/>
<portlet:resourceURL id="upload-file-calender-event" var="uploadFileCalenderEventCommandURL"/>
<portlet:resourceURL id="delete-file-calender-event" var="deleteFileCalenderEventCommandURL"/>

<style>
    /*Select2*/
    .select2-container--default .select2-selection--single {
        padding: 6px;
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

<form data-toggle="validator" role="form" id="formCalenderEvent" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
   <div class="container" style="margin-left: 0;">
      <div class="form-group col-md-9">
         <label class="title-form pull-left" for="judul">Judul Acara <span style="color: red;">*</span></label>
         <input type="text" class="form-control" name="judul" id="judul"
                pattern="[A-Za-z0-9. ]+" data-minlength="5" maxlength="255"
                data-error="Text dengan 5-255 karakter & tidak boleh kosong." required/>
         <div class="help-block with-errors"></div>
      </div>
      <div class="form-group col-md-9">
         <label class="title-form pull-left" for="jenisAcaraId">Jenis Acara <span style="color: red;">*</span></label>
         <input type="hidden" class="form-control" name="jenisAcaraName"/>
         <select class="form-control" name="jenisAcaraId" id="jenisAcaraId" style="width: 100%;"></select>
      </div>
      <div class="form-group">
         <div class="col-sm-9" style="margin-bottom: 10px;">
            <label class="col-form-label ipr-color">Image (760x450) <span style="color: red;">*</span></label>
            <div class="input-group">
               <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                  <span class="icon-browse"><img src="<%=request.getContextPath()%>/assets/img/file.svg"></span>
                  <span class="text-browse">Pilih File</span>
                  <input type="file" accept="image/gif, image/jpeg, image/png" placeholder=""
                         name="calenderEventImageFile" id="calenderEventImageFile" data-filename="" data-location=""
                         style="display: none;" aria-invalid="false" class="materail_input valid">
               </label>
               <input type="text" class="form-control required" data-type="file" data-name=""
                      name="calenderEventImageFileName" readonly
                      style="border: 1px solid #DBEDFF; background-color: white">
               <input type="hidden" class="dpn" name="calenderEventImageFileId"/>
               <input type="hidden" class="dpn" name="calenderEventImageFilePath"/>
            </div>
            <label class="col-form-label ipr-gray">*maks. 5MB</label>
         </div>
      </div>
      <div class="form-group col-md-9 lokasiMeeting">
         <label class="title-form pull-left" for="lokasi">Lokasi <span style="color: red;">*</span></label>
         <input type="text" class="form-control" name="lokasi" id="lokasi"
                pattern="[A-Za-z0-9. ]+" minlength="5" maxlength="255"
                data-error="Text dengan 5-255 karakter & tidak boleh kosong."/>
         <div class="help-block with-errors"></div>
      </div>
      <div class="form-group col-md-9 linkMeeting">
         <label class="title-form pull-left" for="linkMeeting">Link Meeting <span style="color: red;">*</span></label>
         <input type="text" class="form-control" name="linkMeeting" id="linkMeeting"
                pattern="[A-Za-z0-9. -\:\/]+" minlength="5" maxlength="255"
                data-error="Text dengan 5-255 karakter & tidak boleh kosong."/>
         <div class="help-block with-errors"></div>
      </div>
      <div class="form-group col-md-9">
         <div class="row">
            <div class="col-lg-4">
               <div class="form-group">
                  <label class="title-form pull-left" for="startDate">
                     Tanggal Mulai
                     <span style="color: red;">*</span>
                  </label>
                  <input type='text' class="form-control dateIcon required" name='startDate' id='startDate' style="margin-top: 15px;font-size: 15px;border: 1px solid gray;background: white;color: black;" readonly/>
               </div>
            </div>
            <div class="col-lg-4">
               <div class="form-group">
                  <label class="title-form pull-left" for="endDate">
                     Tanggal Berakhir
                     <span style="color: red;">*</span>
                  </label>
                  <input type='text' class="form-control dateIcon required" name='endDate' id='endDate' style="margin-top: 15px;font-size: 15px;border: 1px solid gray;background: white;color: black;" readonly/>
               </div>
            </div>
            <div class="col-lg-4">
               <div class="form-group">
                  <label class="title-form pull-left" for="registrationDate">
                     Batas Pendaftaran
                     <%--<span style="color: red;">*</span>--%>
                  </label>
                  <input type='text' class="form-control dateIcon required" name='registrationDate' id='registrationDate' style="margin-top: 15px;font-size: 15px;border: 1px solid gray;background: white;color: black;" readonly/>
               </div>
            </div>
         </div>
      </div>
      <div class="col-md-9">
         <p>
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
      <div class="col-md-9" style="border-top: 1px solid #D9D9D9; margin: 10px 15px"></div>
      <div class="col-md-9" style="margin-bottom: 15px">
         <button class="btn-ipr" type="submit">Simpan</button>
         <a class="btn btn-ipr-cancel" id="backButton" href="/group/dealink/cms-calendar-event">Batal</a>
      </div>
   </div>
</form>

<script src="<%=request.getContextPath()%>/assets/library/quill/katex.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/quill/highlight.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/quill/quill.min.js"></script>
<script>
   var data = ${data};
   var action = "update";
   var entryId = "0";
   var homeUrl = "/group/dealink/cms-calendar-event";
   var quill
   var submitProcess = false;
   var jenisAcaraData = [{id: 0, text: 'Acara Offline'}, {id: 1, text: 'Acara Online'}]
   var viewStatusAcaraData = [{id: 0, text: 'Belum Terlaksana'}, {id: 1, text: 'Sudah Terlaksana'}]

   $(document).ready(function () {
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

      getJenisAcaras();
      setTanggalMulai();
      setTanggalBerakhir();
      setRegisterEnd();
      formEdit();
   });

   function formEdit() {
      entryId = data.id;
      // $('[name="entryId"]')[0].value = data.id;
      $('[name="judul"]')[0].value = data.judul;
      $('[name="jenisAcaraId"]')[0].value = data.jenisAcara;
      var jenisAcaraName = '';
      if (data.jenisAcara === 0) {
         jenisAcaraName = 'Acara Offline';
      } else {
         jenisAcaraName = 'Acara Online';
      }
      $('[name="jenisAcaraName"]')[0].value = jenisAcaraName;
      $('[name="lokasi"]')[0].value = data.lokasi;
      $('[name="linkMeeting"]')[0].value = data.linkMeeting;

      quill.clipboard.dangerouslyPasteHTML(data.deskripsi);
      $('[name="calenderEventImageFileId"]')[0].value = data.imageId;
      $('[name="calenderEventImageFileName"]')[0].value = data.imageName;
      $('[name="calenderEventImageFilePath"]')[0].value = data.imagePath;
   }

   $("#calenderEventFile").change(function (e) {
      processUpload(e, "calenderEvent", $(this));
   });

   $("#calenderEventImageFile").change(function (e) {
      processUpload(e, "calenderEventImage", $(this));
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
      let deskripsiHtml = quill.container.innerHTML;
      if (submitProcess === false) {
         if (judul === null || judul === undefined || judul.length < 5) {
            Swal.fire("Judul belum diisi", "", "warning");
            return false;
         } else if (!regexBasicCharacter.test(judul)) {
            Swal.fire("Mohon tidak menggunakan karakter selain .,/()@&_- pada Judul", "", "warning");
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
            Swal.fire("Mohon tidak menggunakan karakter selain .,/()@&_- pada Lokasi", "", "warning");
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
         } else if (deskripsiHtml === null || deskripsiHtml === undefined || deskripsiHtml.length < 3) {
            Swal.fire("Harap masukkan minimal 5 karakter", "", "warning");
            return false;
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.set("<portlet:namespace/>crudType", action)
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
                  var code = JSON.parse(response).code;
                  var status = JSON.parse(response).status;
                  var message = JSON.parse(response).message;

                  if (status === 'success') {
                     Swal.fire('', "Your request has been saved", "success")
                        .then(function () {
                           window.location = homeUrl;
                        });
                  } else if (code) {
                     Swal.fire('', message, 'warning');
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
   function processUpload(e, name, element) {
      if (e.target.files[0].size > 5242880) {
         swal.fire("", "File Size cannot more than 5 MB", "warning");
      } else {
         let fileName = e.target.files[0].name;
         uploadDocumentFile(e.target.files[0], fileName, $('[name="' + name + 'FileId"]')[0].value, element, name);
      }
   }

   function uploadDocumentFile(file, fileName, fileID, element, name) {
      let formData = new FormData();
      let format = /[:"\\|,<>\/?^*]/;
      if (format.test(fileName)) {
         swal.fire('', 'Mohon tidak menggunakan karakter ,^*:"|<>\\ /?', 'warning')
      } else {
         fileName = fileName.replaceAll(",", "")
         formData.set("p_auth", Liferay.authToken);
         formData.set("file-upload", file);
         formData.set("file-name", fileName);
         formData.set("file-id", fileID);
         formData.set("document-id", $('[name="document-id"]').val());
         formData.set("document-id-new", $('[name="document-id-new"]').val());

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
                  $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileId"]'))[0].value = fileID;
                  $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileName"]'))[0].value = fileName;
                  $(elementEdit.parent().siblings('[name="' + nameEdit + 'FilePath"]'))[0].value = fileURL;

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
               console.log(e);
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
      let parentElement = element.parentElement.parentElement.parentElement
      // let inputElement = $(parentElement1.siblings().find('input.form-control.required.switch-readonly'))[0];
      let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url: '${deleteFileCalenderEventCommandURL}',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            let data = JSON.parse(response);
            if (data["acknowledge"] === 1) {
               parentElement.remove()

               parentElement1.remove();
               inputElement.value = '';
            } else {
               parentElement1.find("#delete-file-button").css("display", "inherit");
               parentElement1.find("#delete-loader").css("display", "none");

               parentElement.find("[name=file-status]").text("delete failed");
            }
         },
         error: function (e) {
            console.log(e);
            swal({icon: "error", title: "Failed to delete file"});
         },
         complete: function () {
            console.log("DONE");
         }
      });
   }

   function getJenisAcaras() {
      const jenisAcaraConst = $('#jenisAcaraId').select2({
         data: jenisAcaraData,
         tags: false,
         placeholder: 'Pilih Jenis Acara',
         allowClear: false,
         minimumResultsForSearch: -1,
         maximumInputLength: 100,
         templateSelection: function (data) {
            $('[name="jenisAcaraId"]')[0].value = data.id;
            $('[name="jenisAcaraName"]')[0].value = data.text;
            if (data.id === '0') {
               $(".linkMeeting").hide();
               $(".lokasiMeeting").show();
            }
            if (data.id === '1') {
               $(".linkMeeting").show();
               $(".lokasiMeeting").hide();
            }
            if (data.id === 'Pilih Jenis Acara') {
               return 'Pilih Jenis Acara';
            }
            return data.text;
         }
      });
      if (action === "update") {
         jenisAcaraConst.val(data.jenisAcara);
         jenisAcaraConst.trigger('change');
      } else {
         jenisAcaraConst.val(null);
         jenisAcaraConst.trigger('change');
      }
   }

   function setTanggalMulai() {
      $('#startDate').datetimepicker({
         date: data.startDateFull,
         minDate: moment(new Date()).format('YYYY/MM/DD'),
         format: 'DD/MM/YYYY HH:mm',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: true
      })
         .on('dp.change', function (e) {
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

   function setTanggalBerakhir() {
      $('#endDate').datetimepicker({
         date: data.endDateFull,
         format: 'DD/MM/YYYY HH:mm',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: false
      })
         .on('dp.change', function (e) {
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
         .on('dp.change', function (e) {
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