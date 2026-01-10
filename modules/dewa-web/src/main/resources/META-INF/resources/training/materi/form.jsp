<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/training-materi-action" var="trainingMateriActionURL" />
<portlet:resourceURL id="/upload-file-training-materi" var="uploadFileTrainingMateriCommandURL" />
<portlet:resourceURL id="/delete-file-training-materi" var="deleteFileTrainingMateriCommandURL" />

<portlet:resourceURL id="/jenis-materi-training" var="jenisMateriTrainingURL"/>
<portlet:resourceURL id="/topik-materi-training" var="topikMateriTrainingURL"/>
<portlet:resourceURL id="/jenis-materi-action" var="jenisMateriActionURL"/>

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

    .modal-dialog {
        position: absolute;
        left: 0;
        right: 0;
        top: 10%;
        bottom: 0;
        margin: auto;
        /*!*width:500px;*!*/
        /*!*height:300px;*!*/
    }
</style>
<style>
    .datepicker {
        overflow-y: hidden;
    }

    .datepicker table tr td.today,
    .datepicker table tr td.today.focused,
    .datepicker table tr td.today:hover,
    .datepicker table tr td.today:focus,
    .datepicker table tr td.today:active,
    .datepicker table tr td.today:active:focus,
    .datepicker table tr td.today:active:hover {
        background-color: #DBEDFF;
    }

    .bootstrap-select .dropdown-menu.inner {
        max-height: 100%;
    }

    .form-control {
        border: 1px solid #DBEDFF;
        transition: none;
    }

    input.form-control, select.form-control{
        height: 40px !important;
    }

    .form-control:focus {
        border: 1px solid #8ec6ff;
        box-shadow: 0 0 5px rgba(142,198, 255, 0.2);
    }

    .form-control.date {
        background-image: url(<%=request.getContextPath()%>/assets/img/calendar.svg);
        background-repeat: no-repeat;
        background-size: 20px;
        background-position: calc(100% - 10px) center;
    }

    .file-container-button {
        display: flex;
        flex-wrap: wrap;
        gap: 8px;
    }

    .file-container-button > div {
        flex: 1;
    }

    .btn-add-file-container {
        background-color: #014689;
        color: #FFFFFF;
        height: 40px;
        width: 40px;
        line-height: 40px;
        border: none;
        outline: none;
        border-radius: 5px;
        text-align: center;
    }

    .file-input-container {
        position: relative;
        height: 40px;
        display: flex;
    }

    .file-input-label {
        background-image: url(<%=request.getContextPath()%>/assets/img/upload.svg);
        background-color: #014689;
        background-repeat: no-repeat;
        background-position: 12px 8px;
        background-size: 20px;
        color: #FFFFFF;
        display: inline-block;
        height: 40px;
        line-height: 40px;
        padding: 0 20px 0 40px;
        border-radius: 5px 0 0 5px;
        white-space: nowrap;
        cursor: pointer;
    }

    .file-input-container input[type=file] {
        position: absolute;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
        opacity: 0;
        cursor: pointer;
        z-index: 1;
    }

    .file-input-container .file-text {
        display: inline-block;
        width: 100%;
        height: 40px;
        outline: none;
        border: 1px solid #DBEDFF;
        border-radius: 0 5px 5px 0;
        padding-left: 10px;
        padding-right: 10px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }

    button.action-button {
        padding: 8px 16px;
        border: none;
        outline: none;
        border-radius: 5px;
        background-color: #014689;
        color: #fff;
    }

    button.action-button:hover,
    .btn-add-file-container:hover {
        background-color: #092f52;
    }

    span.action-title {
        color: #014689;
    }

    button.action-button.outline {
        background-color: transparent;
        color: #014689;
        border: 1px solid #014689;
    }

    button.action-button.outline:hover{
        background-color: #014689;
        color: #FFFFFF;
    }

    .myBreadcrumb > div > p{
        margin-left: 8rem;
        line-height: 42px;
    }

    .datepicker-days thead th{
        font-weight: 400;
    }

    .datepicker-days thead tr:last-child {
        font-size: 11px;
        font-weight: 300;
    }

    .datepicker table tr .day.highlighted,
    .datepicker table tr .day.highlighted:focus,
    .datepicker table tr .day.highlighted:active,
    .datepicker table tr .day.highlighted:active:focus {
        background-color: transparent;
        color: #EE1C25;
    }

    .datepicker table tr .day.highlighted.disabled {
        background-color: transparent !important;
    }

    .datepicker table tr .day.highlighted {
        color: #EE1C25;
        border-radius: 4px;
    }

    .datepicker table tr td.new,
    .datepicker table tr td.old {
        color: #dbdbdb;
    }

    .datepicker table tr td.disabled {
        color: #dbdbdb !important;
    }

    .datepicker table tr .day.highlighted.disabled,
    .datepicker table tr .day.old.highlighted,
    .datepicker table tr .day.new.highlighted{
        color: #ffd4d6 !important;
    }

    .datepicker table tr .day.highlighted:hover,
    .datepicker table tr .day.highlighted.focused,
    .datepicker table tr .day.highlighted:active:hover {
        background-color: #eee;
        color: #EE1C25;
    }

    @media only screen and (max-width: 1199px) {
        .myBreadcrumb {
            margin-right: -25px;
        }
    }

    @media only screen and (max-width: 991px){
        .col-md-3 input {
            margin-top: 10px;
        }
    }

    @media only screen and (max-width: 767px) {
        .myBreadcrumb {
            margin-right: -15px;
        }
    }
</style>

<form data-toggle="validator" role="form" id="formTrainingMateri" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
   <div class="container" style="margin-left: 0;">
      <h3 id="form-title" style="margin-bottom: 50px;">Materi Pelatihan</h3>
      <div class="form-group col-md-9">
         <label class="title-form pull-left" for="jenisMateriId">Jenis Materi Pelatihan <span style="color: red;">*</span></label>
         <select class="form-control" name="jenisMateriId" id="jenisMateriId" style="width: 100%;">
            <%-- <option value="NULL">List Dealer</option>--%>
         </select>
      </div>
<%--      <div class="form-group col-md-9" style="margin-top: 0px;">--%>
<%--         <button type="button" class="btn btn-sm btn-info" title="Add File" id="openJenisMateri">--%>
<%--            &lt;%&ndash;<i class="fas fa-plus"></i> &ndash;%&gt;--%>
<%--            &lt;%&ndash;<span>Tambahkan Jenis Materi Pelatihan</span>&ndash;%&gt;--%>
<%--               Tambahkan Jenis Materi Pelatihan--%>
<%--         </button>--%>
<%--      </div>--%>
      <div class="form-group col-md-9">
         <label class="title-form pull-left" for="topikMateriId">Topik Materi Pelatihan <span style="color: red;">*</span></label>
         <select class="form-control" name="topikMateriId" id="topikMateriId" style="width: 100%;">
            <%-- <option value="NULL">List Dealer</option>--%>
         </select>
      </div>
<%--      <div class="form-group col-md-9">--%>
<%--         <label class="title-form pull-left" for="topikMateri">Topik Materi <span style="color: red;">*</span></label>--%>
<%--         <input type="text" class="form-control" name="topikMateri" id="topikMateri"--%>
<%--                pattern="[A-Za-z0-9. ]+" data-minlength="5" maxlength="255"--%>
<%--                data-error="Text dengan 5-255 karakter & tidak boleh kosong." required/>--%>
<%--         <div class="help-block with-errors"></div>--%>
<%--      </div>--%>
      <div class="form-group col-md-9">
         <label class="title-form pull-left" for="judulMateri">
            Judul Materi
            <span style="color: red;">*</span>
         </label>
         <input type="text" class="form-control" name="judulMateri" id="judulMateri"
                pattern="[A-Za-z0-9. ]+" data-minlength="5" maxlength="255"
                data-error="Text dengan 5-255 karakter & tidak boleh kosong." required/>
         <div class="help-block with-errors"></div>
      </div>
      <div class="form-group">
         <div class="col-md-9">
            <label class="col-form-label ipr-color">
               Lampiran
               <span style="color: red;">*</span>
            </label>
            <div id="other-file-container">
               <div class="parent-file-container">
                  <div class="file-container-button" style="margin-bottom: 15px;">
                     <div>
                        <div class="file-input-container">
                           <label class="file-input-label" for="trainingMateri">Pilih File</label>
                           <input class="form-control" onchange="fileChange(this)" type="file" name="trainingMateri" id="trainingMateri" accept=".docx, .xlsx, .pdf">
                           <input class="form-control file-text" name="trainingMateriFileName" id="trainingMateriFileName">
                           <input class="form-control" type="hidden" name="trainingMateriFileId" id="trainingMateriFileId">
                           <input class="form-control" type="hidden" name="trainingMateriFilePath" id="trainingMateriFilePath">
                        </div>
                        <label class="col-form-label ipr-gray">File dengan format docx, xlsx, dan pdf *maks. 5MB</label>
                     </div>
                     <%--<button onclick="addOtherFile();" type="button" class="btn-add-file-container" title="Add File"><i class="fas fa-plus"></i></button>--%>
                  </div>
                  <%--<p class="upload-info">File dengan format docx, xlsx, dan pdf *maks. 5MB</p>--%>
               </div>
            </div>
            <button type="button" class="btn btn-sm btn-info" title="Add File" onclick="addOtherFile();">
               <%--<i class="fas fa-plus"></i> --%>
               <span>Tambahkan Lampiran</span>
            </button>
         </div>
      </div>

      <div class="form-group">
         <div class="col-sm-9" style="margin-top: 15px;">
            <label class="col-form-label ipr-color">Images <span style="color: red;">*</span></label>
            <div class="input-group" >
               <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                  <span class="icon-browse"><img src="<%=request.getContextPath()%>/assets/img/file.svg"></span>
                  <span class="text-browse">Pilih File</span>
                  <input type="file" accept="image/gif, image/jpeg, image/png" placeholder="" name="trainingMateriImageFile" id="trainingMateriImageFile" data-filename="" data-location="" style="display: none;" aria-invalid="false" class="materail_input valid">
               </label>
               <input type="text" class="form-control required" data-type="file" data-name="" name="trainingMateriImageFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
               <input type="hidden" class="dpn" name="trainingMateriImageFileId" >
               <input type="hidden" class="dpn" name="trainingMateriImageFilePath" >
            </div>
            <label class="col-form-label ipr-gray">File dengan format jpg dan jpeg *maks. 5MB</label>
         </div>
      </div>
      <div class="col-md-9" style="border-top: 1px solid #D9D9D9 ; margin: 10px 15px"></div>
      <div class="col-md-9">
         <button class="btn-ipr" type="submit">Simpan</button>
         <a class="btn btn-ipr-cancel" id="backButtonDocFlow" data-senna-off="true" href="/group/dealink/cms-training">Batal</a>
      </div>
   </div>
</form>

<!-- Modal Jenis Materi Pelatihan-->
<div class="modal fade" id="materiModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
   <div class="modal-dialog modal-sm" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">Jenis Materi Pelatihan</h4>
         </div>
         <div class="modal-body">
            <form id="formJenisMateri" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
               <div class="form-group">
                  <%--<label class="title-form pull-left" for="jenisMateriName">Jenis Materi Pelatihan <span style="color: red;">*</span></label>--%>
                  <input type="text" class="form-control" name="jmName" id="jmName" style="border: 1px solid black; margin-bottom: 0px;"/>
               </div>
               <button type="button" class="btn btn-default btn-block" data-dismiss="modal" onclick="createJenisMateri();">Simpan</button>
            </form>
         </div>
      </div>
   </div>
</div>

<script>
   var data = ${data};
   var action = "${action}";
   var entryId = "0";
   var homeUrl = "/group/dealink/cms-training";
   var maxFile = 4;
   var submitProcess = false;
   var listDeleted = []
   var jenisMateries
   var jenisAgendaData = [{id: 0, text: 'Acara Offline'},{id: 1, text: 'Acara Online'}]
   var statusAgendaData = [{id: 0, text: 'Belum Terlaksana'},{id: 1, text: 'Sudah Terlaksana'}]

   $(document).ready(function() {
      resetForm()
   });

   function resetForm() {
      getJenisMateris()
      getTopikMateris()
      if (action === "create") {
         $('#form-title').text("Buat Materi Pelatihan")
         formNew();
      } else if (action === "update") {
         $('#form-title').text("Edit Materi Pelatihan")
         formEdit();
      } else {
         $('#form-title').text("Materi Pelatihan")
         formEdit();
      }
   }

   function formNew() {
      entryId = "0";
      // $('[name="entryId"]')[0].value = "0";
      $('[name="jenisMateriId"]')[0].value = "";
      $('[name="topikMateriId"]')[0].value = "";
      $('[name="judulMateri"]')[0].value = "";
      $('[name="trainingMateriFileId"]')[0].value = "";
      $('[name="trainingMateriFileName"]')[0].value = "";
      $('[name="trainingMateriFilePath"]')[0].value = "";
      $('[name="trainingMateriImageFileId"]')[0].value = "";
      $('[name="trainingMateriImageFileName"]')[0].value = "";
      $('[name="trainingMateriImageFilePath"]')[0].value = "";
      $(".upload-content").remove();
   }

   function formEdit() {
      entryId = data.id;
      // $('[name="entryId"]')[0].value = data.id;
      $('[name="jenisMateriId"]')[0].value = data.jenisMateriId;
      $('[name="topikMateriId"]')[0].value = data.topikMateriId;
      $('[name="judulMateri"]')[0].value = data.judulMateri;
      $('[name="trainingMateriFileId"]')[0].value = data.fileId;
      $('[name="trainingMateriFileName"]')[0].value = data.fileName;
      $('[name="trainingMateriFilePath"]')[0].value = data.filePath;
      $('[name="trainingMateriImageFileId"]')[0].value = data.imageId;
      $('[name="trainingMateriImageFileName"]')[0].value = data.imageName;
      $('[name="trainingMateriImageFilePath"]')[0].value = data.imagePath;
      data.trainingMateriLampirans.forEach(item => {
         if(item.fileId !== data.fileId) {
            var $parentFileContainer = addOtherFile();
            $parentFileContainer.find("#trainingMateriFileId").val(item.fileId);
            $parentFileContainer.find("#trainingMateriFileName").val(item.fileName);
            $parentFileContainer.find("#trainingMateriFilePath").val(item.filePath);
         }
      })
   }

   $("#trainingMateriFile").change(function(e){
      processUpload(e, "trainingMateri", $(this));
   });

   $("#trainingMateriImageFile").change(function(e){
      processUpload(e, "trainingMateriImage", $(this));
   });

   $("#formTrainingMateri").submit(function (e) {
      e.preventDefault();
      const jenisMateriId = $('[name="jenisMateriId"]').val();
      const topikMateriId = $('[name="topikMateriId"]').val();
      const judulMateri = $('[name="judulMateri"]').val();
      const trainingMateriFileId = $('[name="trainingMateriFileId"]').val();
      const trainingMateriImageFileId = $('[name="trainingMateriImageFileId"]').val();
      if (submitProcess === false) {
         if (jenisMateriId === null || jenisMateriId === undefined || jenisMateriId.length < 1) {
            Swal.fire("Jenis Materi belum diisi.", "", "warning");
            return false;
         } else if (topikMateriId === null || topikMateriId === undefined || topikMateriId.length < 1) {
            Swal.fire("Topik Materi belum diisi.", "", "warning");
            return false;
         } else if (judulMateri === null || judulMateri === undefined || judulMateri.length < 5) {
            return false;
         } else if (trainingMateriFileId === null || trainingMateriFileId === undefined || trainingMateriFileId.length < 1) {
            Swal.fire("Lampiran belum diisi.", "", "warning");
            return false;
         } else if (trainingMateriImageFileId === null || trainingMateriImageFileId === undefined || trainingMateriImageFileId.length < 1) {
            Swal.fire("Images belum diisi.", "", "warning");
            return false;
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.set("<portlet:namespace/>crudType", action);
            formData.set("<portlet:namespace/>entryId", entryId);
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            listDeleted.forEach(item => {
               formData.append("<portlet:namespace/>listDeleted", item);
            });

            createLoading();
            $.ajax({
               url: '${trainingMateriActionURL}',
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
                     });
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
            url: '${uploadFileTrainingMateriCommandURL}',
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
                  swal.fire({icon: "error", title: "Failed to upload file"});
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
      let parentElement  = element.parentElement.parentElement.parentElement
      // let inputElement = $(parentElement1.siblings().find('input.form-control.required.switch-readonly'))[0];
      let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url : '${deleteFileTrainingMateriCommandURL}',
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

   function addOtherFile() {
      var html = $(`<div class="parent-file-container">
                        <div class="file-container-button" style="margin-bottom: 15px;">
                            <div>
                                <div class="file-input-container">
                                    <label class="file-input-label" for="trainingMateri">Pilih File</label>
                                    <input onchange="fileChange(this)" type="file" name="trainingMateri" id="trainingMateri" accept=".docx, .xlsx, .pdf">
                                    <input class="file-text" name="trainingMateriFileName" id="trainingMateriFileName">
                                    <input type="hidden" name="trainingMateriFileId" id="trainingMateriFileId">
                                    <input type="hidden" name="trainingMateriFilePath" id="trainingMateriFilePath">
                                </div>
                            </div>
                            <button onclick="removeFile(this);" type="button" class="btn-add-file-container" title="Remove File"><i class="fas fa-minus"></i></button>
                        </div>

                    </div>`);
      if ($("#other-file-container").find(".parent-file-container").length < maxFile) {
         $("#other-file-container").append(html);
      }
      return html;
   }

   function fileChange(element) {
      var format = /[:"\\|,<>\/?^*#%]/;
      var $element = $(element);
      var $parent = $element.parents(".file-input-container");
      var elementId = $element.attr("id");
      $parent.find("#" + elementId + "FileId").val("");
      $parent.find("#" + elementId + "FileName").val("");
      $parent.find("#" + elementId + "FilePath").val("");


      var file = element.files[0];
      if (file === undefined) {
         return false;
      }

      var fileId = $parent.find("#" + elementId + "FileId").val();
      var fileType = file["type"];
      var fileName = file["name"];
      var fileSize = file["size"];
      var fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

      // if (fileType !== "application/pdf") {
      //    $element.val("");
      //    Swal.fire("Upload File Gagal", "Hanya file PDF yang diperbolehkan", "warning");
      //    return false;
      // }
      if (fileSize > 5*1024*1024) {
         $element.val("");
         Swal.fire("Upload File Gagal", "Ukuran file terlalu besar", "warning");
         return false;
      }
      if (format.test(fileName)) {
         $element.val("");
         Swal.fire("Upload File Gagal", 'Nama file tidak boleh mengandung karakter spesial ,^*:"|<>\\ /?#%', "warning");
         return false;
      }
      if (fileExtension !== "pdf" && fileExtension !== "docx" && fileExtension !== "xlsx") {
         $element.val("");
         Swal.fire("Upload File Gagal", "Hanya file File dengan format docx, xlsx, dan pdf yang diperbolehkan", "warning");
         return false;
      }

      var htmlLoading = $(`
                <div class="upload-content">
                    <div class="progress" style="margin-top: 10px; margin-bottom: 0px; transform: scaleY(0.7);">
                        <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" style="width: 0%;"></div>
                    </div>
                    <div id="progress-percentage">0% uploaded</div>
                </div>
            `);

      $element.parent().siblings(".upload-content").remove();
      $element.parent().after(htmlLoading);

      let formData = new FormData();
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      formData.set("<portlet:namespace/>file-upload", file);
      formData.set("<portlet:namespace/>file-name", fileName);
      formData.set("<portlet:namespace/>file-id", fileId);
      formData.set("<portlet:namespace/>document-id", $("#<portlet:namespace/>documentId").val() == "0" ? "undefined" : $("#<portlet:namespace/>documentId").val());
      formData.set("<portlet:namespace/>document-id-new", $("#<portlet:namespace/>newDocumentId").val());
      $.ajax({
         url: '${uploadFileTrainingMateriCommandURL}',
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
                     htmlLoading.find("#progress-percentage").text(parseInt(uploadPercent) + '%');
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
               $parent.find("#" + elementId + "FileName").val(fileName);
               $parent.find("#" + elementId + "FileId").val(fileID);
               $parent.find("#" + elementId + "FilePath").val(fileURL);

               let successHTML = `
                        <div style="margin-top: -5px;margin-bottom: 0;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
                            <div>
                                <p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px">Upload file berhasil</p>
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

               htmlLoading.empty();
               htmlLoading.append(successHTML);
            } else {
               Swal.fire({icon: "error", title: "Gagal Mengupload File"});
            }
         },
         error: function (e) {
            console.log(e);
            Swal.fire({icon: "error", title: "Gagal Mengupload File"});
         },
         complete: function () {
            console.log("DONE");
         }
      });
   }

   function removeFile(element) {
      if(action === 'update') {
         var parent = $(element).parents(".parent-file-container");
         var id = parent.find("#trainingMateriFileId").val();
         var name = parent.find("#trainingMateriFileName").val();
         listDeleted.push(id)
      }
      $(element).parents(".parent-file-container").remove();
   }

   function getJenisMateris() {
      $.ajax({
         url: "${jenisMateriTrainingURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
      success: function (response) {
         var jenisMateriData = JSON.parse(response);
         jenisMateries = jenisMateriData.Data
         const jenisMateriConst = $("#jenisMateriId").select2({
            data: jenisMateriData.Data,
            tags: false,
            placeholder: '',
            allowClear: false,
            multiple: false,
            // minimumInputLength: 1,
            maximumInputLength: 100,
            templateSelection: function (data) {
               $('[name="jenisMateriId"]')[0].value = data.id;
               if (data.id === '') {
                  return '';
               }
               return data.text;
            }
         });
         if (action === 'update') {
            jenisMateriConst.val(data.jenisMateriId);
            jenisMateriConst.trigger('change');
         } else {
            jenisMateriConst.val(null);
            jenisMateriConst.trigger('change');
         }
      }
   ,
      error: function (error) {
         console.log(error);
      }
   ,
      complete: function () {
         console.log("complete");
      }
   })
      ;
   }

   function getTopikMateris() {
      $.ajax({
         url: "${topikMateriTrainingURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var topikMateriData = JSON.parse(response);
            topikMateries = topikMateriData.Data
            const topikMateriConst = $("#topikMateriId").select2({
               data: topikMateriData.Data,
               tags: false,
               placeholder: '',
               allowClear: false,
               multiple: false,
               // minimumInputLength: 1,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="topikMateriId"]')[0].value = data.id;
                  if (data.id === '') {
                     return '';
                  }
                  return data.text;
               }
            });
            if(action === 'update') {
               topikMateriConst.val(data.topikMateriId);
               topikMateriConst.trigger('change');
            } else {
               topikMateriConst.val(null);
               topikMateriConst.trigger('change');
            }
         },
         error: function (error) {
            console.log(error);
         },
         complete: function (){
            console.log("complete");
         }
      });
   }
</script>
<script>
   $("#openJenisMateri").click(function(){
      $('#materiModal').modal({
         backdrop: 'static',
         keyboard: false,
         isBodyOverflowing: true
      }).show()
   });

   function hideJenisMateri() {
      $("#openJenisMateri").modal('hide')
   }

   function createJenisMateri() {
      var formData = new FormData();
      formData.set("<portlet:namespace/>crudType", "create")
      formData.set("<portlet:namespace/>entryId", 0);
      formData.set("<portlet:namespace/>jenisMateriName", $("#jmName").val());
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      $.ajax({
         url: '${jenisMateriActionURL}',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            if (JSON.parse(response).status === 'success') {
               var res = JSON.parse(response)
               var newJm = new Option(res.data.text, res.data.id, true, true);
               $("#jenisMateriId").prepend(newJm).trigger('change');
               $('[name="jenisMateriId"]')[0].value = res.data.id;
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
</script>