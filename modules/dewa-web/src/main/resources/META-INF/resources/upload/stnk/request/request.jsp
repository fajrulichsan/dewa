<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="upload-stnk-request" var="uploadStnkRequestURL" />
<portlet:resourceURL id="upload-stnk-request-action" var="uploadStnkActionURL" />
<portlet:resourceURL id="upload-file-upload-stnk-request" var="uploadFileUploadStnkCommandURL" />
<portlet:resourceURL id="delete-file-upload-stnk-request" var="deleteFileUploadStnkCommandURL" />

<portlet:resourceURL id="/non-cms/stnk-application-category" var="requestUploadStnkURL"/>

<style>
    /* Select2 */
    .select2-container--default .select2-selection--single {
        padding:6px;
        /*margin-top: 16px;*/
        /*margin-bottom: 16px;*/
        height: 37px;
        /*width: 148px;*/
        font-size: 1.2em;
        position: relative;
    }
</style>

<form data-toggle="validator" role="form" class="form-horizontal" id="formUploadBonus" method="post" enctype="multipart/form-data" novalidate autocomplete="off">

   <div class="row">
      <h5 class="title-breadcrumb"></h5>
   </div>

   <div class="row">
      <h5 class="title-format">General Information</h5>
   </div>

   <div class="row">
      <div class="col-xs-6 col-md-3">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="ticketDate" class="title-form">Ticket Date</label>
            <input type="text" class="form-control" name="ticketDate" id="ticketDate" readonly>
         </div>
      </div>
      <div class="col-xs-6 col-md-3">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <input type="text" class="form-control" style="margin-top: 23px;" id="ticketHour" name="ticketHour" placeholder="Auto" readonly />
         </div>
      </div>
      <div class="col-xs-12 col-md-6">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="userName" class="title-form">Requester Name</label>
            <input type="text" class="form-control" id="userName" name="userName" placeholder="Auto" readonly>
         </div>
      </div>
      <div class="col-md-6">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="ticketNumber" class="title-form">Ticket Number</label>
            <input type="text" class="form-control" id="ticketNumber" name="ticketNumber" placeholder="Auto Generate" readonly>
         </div>
      </div>
      <div class="col-md-6">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="dealerName" class="title-form">Cabang Dealer</label>
            <input type="text" class="form-control" id="dealerName" name="dealerName" placeholder="Auto" readonly>
            <input type="hidden" id="dealerId" name="dealerId" readonly>
         </div>
      </div>
      <div class="col-md-6">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="userId" class="title-form">User ID</label>
            <input type="text" class="form-control" id="userId" name="userId" placeholder="Auto" readonly>
         </div>
      </div>
      <div class="col-md-6">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="approverName" class="title-form">Approver</label>
            <input type="text" class="form-control" id="approverName" name="approverName" placeholder="Auto" readonly>
            <input type="hidden" id="approverId" name="approverId" readonly>
         </div>
      </div>
   </div>

   <div class="row">
      <div class="col-md-12 line-bonus"></div>
   </div>

   <div class="row">
      <h5 class="title-format">Contact Information</h5>
   </div>

   <div class="row">
      <div class="col-md-6">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="email" class="control-label title-form">Email</label>
            <input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" data-minlength="6" maxlength="35" class="form-control" id="email" name="email" placeholder="Email" data-error="Minimal 6-35 karakter & Email harus valid." required readonly />
            <div class="help-block with-errors"></div>
         </div>
      </div>
      <div class="col-md-6">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="emailCC" class="control-label title-form">Email CC</label>
            <input type="email" maxlength="35" class="form-control" id="emailCC" name="emailCC" placeholder="" />
            <div class="help-block with-errors"></div>
         </div>
      </div>
      <div class="col-md-6">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="phone" class="control-label title-form">
               Phone
               <span style="color: red;">*</span>
            </label>
            <input type="tel" class="form-control numberOnly" id="phone" name="phone"
                   required pattern="[0-9]+" data-minlength="9" maxlength="16"
                   data-error="Nomor 9-16 karakter."/>
            <div class="help-block with-errors"></div>
         </div>
      </div>
   </div>

   <div class="row">
      <div class="col-md-12 line-bonus"></div>
   </div>

   <div class="row">
      <h5 class="title-format">Request Information</h5>
   </div>

   <div class="row">
      <div class="col-md-6">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="requestBonusId" class="control-label title-form">Request Category <span style="color: red;">*</span></label>
            <select class="form-control" name="requestBonusId" id="requestBonusId" style="width: 100%;">
            </select>
            <input type="hidden" id="requestBonusName" name="requestBonusName" />
         </div>
      </div>
   </div>

   <div class="row">
      <div class="col-md-6">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="reqYearId" class="control-label title-form">Tahun <span style="color: red;">*</span></label>
            <select class="form-control" name="reqYearId" id="reqYearId" style="width: 100%;">
               <%--<option value="ALL">Select All</option>--%>
            </select>
         </div>
      </div>
      <div class="col-md-6"></div>
      <div class="col-md-12">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="requestDescription" class="control-label title-form">Request Description <span style="color: red;">*</span></label>
            <textarea class="form-control" rows="3" id="requestDescription" name="requestDescription"
                      required pattern="[a-zA-Z0-9 .]+" data-minlength="9" maxlength="255"
                      data-error="Text 9-255 karakter."></textarea>
            <div class="help-block with-errors"></div>
         </div>
      </div>
      <div class="col-md-12">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="businessBenefit" class="control-label title-form">Business Benefit <span style="color: red;">*</span></label>
            <textarea class="form-control" rows="3" id="businessBenefit" name="businessBenefit"
                      required pattern="[a-zA-Z0-9 .]+" data-minlength="9" maxlength="255"
                      data-error="Text 9-255 karakter."></textarea>
            <div class="help-block with-errors"></div>
         </div>
      </div>
      <div class="col-md-12">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="attachment" class="control-label title-form">Attachment <span style="color: red;">*</span></label>
            <div class="input-group" >
               <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                  <span class="icon-browse"><img src="<%=request.getContextPath()%>/assets/img/file.svg" /></span>
                  <span class="text-browse">Pilih File</span>
                  <input type="file" accept="application/pdf, image/jpg, image/jpeg, image/png" placeholder="" name="uploadBonusImageFile" id="uploadBonusImageFile" data-filename="" data-location="" style="display: none;" aria-invalid="false" class="materail_input valid">
               </label>
               <input type="text" class="form-control required" data-type="file" data-name="" name="uploadBonusImageFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
               <input type="hidden" class="dpn" id="attachment" name="uploadBonusImageFileId" />
               <input type="hidden" class="dpn" name="uploadBonusImageFilePath" />
            </div>
            <label class="col-form-label ipr-gray">Note: Maksimum Document Size 5 MB (pdf, jpg, jpeg, png)</label>
            <div style="margin-top: 0px;">
               <button class="btn btn-sm btn-primary btn-round button-text" type="button" id="btnPreview" style="width: 142px;">Preview</button>
               <button class="btn btn-sm btn-danger btn-round button-text" type="button" id="btnDelete" style="width: 142px;">Delete</button>
            </div>
         </div>
      </div>
   </div>

   <div class="row">
      <div class="col-md-12 line-bonus"></div>
   </div>

   <div class="row">
      <h5 class="title-format">Notes</h5>
   </div>

   <div class="row">
      <div class="col-md-12">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="requestDescription" class="control-label title-form">Notes <span style="color: red;">*</span></label>
            <textarea class="form-control" rows="3" id="notes" name="notes"
                      required pattern="[a-zA-Z0-9 .]+" data-minlength="9" maxlength="255"
                      data-error="Text 9-255 karakter."></textarea>
            <div class="help-block with-errors"></div>
         </div>
      </div>
      <div class="col-md-12">
         <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
            <label for="businessBenefit" class="control-label title-form">Notes History</label>
            <textarea class="form-control" rows="9" id="notesHistory" name="notesHistory" placeholder="" readonly></textarea>
         </div>
      </div>
   </div>

   <div class="row">
      <div class="col-md-12" style="margin-left: 1px">
         <button class="btn-ipr btn-round button-text" type="button" id="btnSubmit">Submit</button>
         <button class="btn btn-ipr-cancel btn-round button-text" type="button" id="btnDraft">Save as Draft</button>
         <button class="btn btn-ipr-cancel btn-round button-text" type="button" id="btnReset">Reset</button>
         <button class="btn btn-ipr-cancel btn-round button-text" type="button" id="btnCancel">Cancel</button>
      </div>
   </div>
</form>
<div class="row" style="margin-bottom: 26px;"></div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
   <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel"></h4>
         </div>
         <div class="modal-body" id="filePreview"></div>
<%--         <div class="modal-footer">--%>
<%--            <button class="btn btn-block btn-ipr-cancel" onclick="hideForm();">Close</button>--%>
<%--         </div>--%>
      </div>
   </div>
</div>

<script>
   var data = '${data}';
   var action = "create";
   var ticketDateNew = "";
   var ticketHourNew = "";
   var homeUrl = "/group/dealink/copy-stnk-test-car/monitoring-request";
   var submitProcess = false;
   var entryId = "0";
   var fileId = "";

   $('#btnDraft').click(function (e) {
      // draft
      formSend('5');
   })
   $('#btnSubmit').click(function (e) {
      // waiting
      formSend('1');
   })
   $('#btnCancel').click(function (e) {
      window.location = homeUrl;
   })
   $('#btnReset').click(function (e) {
      formNew();
   })
   $('#btnPreview').click(function (e) {
      if(fileId === null || fileId === '') {
         swal.fire({icon: "info", title: "File not found."});
      } else {
         const fileName = $('[name="uploadBonusImageFileName"]').val();
         const fileFormat = fileName.split('.').pop();
         if(fileFormat === "pdf"){
            const filePath = $('[name="uploadBonusImageFilePath"]').val();
            $('<a href="' + filePath + '" target="blank"></a>')[0].click();
         } else if (fileFormat === "jpg" || fileFormat === "jpeg" || fileFormat === "png") {
            openForm();
         }
      }
   })
   $('#btnDelete').click(function (e) {
      if (fileId === null || fileId === '') {
         swal.fire({icon: "info", title: "File not found."});
      } else {
         deleteFile();
      }
   })

   function openForm() {
      var img = '<img src="' + $('[name="uploadBonusImageFilePath"]').val() + '" class="img-responsive img-rounded center-block">';
      $('#myModal').modal({
         backdrop: 'static',
         keyboard: false,
         isBodyOverflowing: true
      }).show()
      $('#myModalLabel').html($('[name="uploadBonusImageFileName"]').val());
      $('.modal .modal-body').css('overflow-y', 'auto');
      var _height = $(window).height()*0.8;
      $('.modal .modal-body').css('max-height', _height);
      $("#filePreview").html('');
      $("#filePreview").html(img);
   }

   function hideForm() {
      $('#myModal').modal('hide');
   }

   $(document).ready(function() {
      $('.title-breadcrumb').text('Home > Claim Dealer > Copy STNK Test Car > Buat Request');

      getUser();
      getRequestUploads();
      getReqYears();
      if (action === "create") {
         $('#form-title').text("New Acara");
         // formNew();
      }

      $('.numberOnly').keypress(function (e) {
         var charCode = (e.which) ? e.which : e.keyCode
         if (String.fromCharCode(charCode).match(/[^0-9]/g)) {
            return false;
         }
      });
   });

   function formNew() {
      ticketDateNew = moment(new Date()).format("DD.MM.YYYY")
      ticketHourNew = moment(new Date()).format("HH:mm")

      entryId = "0";
      // General Information
      $('[name="ticketDate"]')[0].value = ticketDateNew;
      $('[name="ticketHour"]')[0].value = ticketHourNew;
      $('[name="ticketNumber"]')[0].value = "";
      $('[name="userId"]')[0].value = data.userId;
      $('[name="userName"]')[0].value = data.userName;
      $('[name="dealerId"]')[0].value = data.dealerId;
      $('[name="dealerName"]')[0].value = data.dealerName;
      $('[name="approverId"]')[0].value = "";
      $('[name="approverName"]')[0].value = data.approverName;
      // Contact Information
      $('[name="email"]')[0].value = data.email;
      $('[name="emailCC"]')[0].value = "";
      $('[name="phone"]')[0].value = "";
      // Request Information
      $('[name="requestBonusId"]')[0].value = "";
      $('[name="requestBonusName"]')[0].value = "";
      // $('[name="nominalPengajuan"]')[0].value = "";
      $('[name="reqYearId"]')[0].value = "";
      $('[name="requestDescription"]')[0].value = "";
      $('[name="businessBenefit"]')[0].value = "";
      // Attachment
      $('[name="uploadBonusImageFileId"]')[0].value = "";
      $('[name="uploadBonusImageFileName"]')[0].value = "";
      $('[name="uploadBonusImageFilePath"]')[0].value = "";
      // Notes
      $('[name="notes"]')[0].value = "";
      $('[name="notesHistory"]')[0].value = "";
   }

   function formSend(statusBonusId) {
      const phone = $('[name="phone"]').val()
      const requestBonusId = $('[name="requestBonusId"]').val();
      const requestBonusName = $('[name="requestBonusName"]').val();
      // var nominalPengajuan = $('[name="nominalPengajuan"]').val();
      const reqYearId = $('[name="reqYearId"]').val();
      const requestDescription = $('[name="requestDescription"]').val();
      const businessBenefit = $('[name="businessBenefit"]').val();
      const notes = $('[name="notes"]').val();
      const uploadBonusImageFileId = $('[name="uploadBonusImageFileId"]').val();
      if (submitProcess === false) {
         if (phone === null || phone === undefined || phone.length < 9 || phone.length > 16) {
            Swal.fire("Mohon masukkan nomor yang sesuai", "", "warning");
            return false;
         } else if (requestBonusName === null || requestBonusName === undefined || requestBonusName.length < 1  || requestBonusName === 'Pilih Request') {
            Swal.fire("Request Category belum dipilih", "", "warning");
            return false;
         } else if (reqYearId === null || reqYearId === undefined || reqYearId.length < 1) {
            Swal.fire("Tahun belum dipilih", "", "warning");
            return false;
         } else if (requestDescription === null || requestDescription === undefined || requestDescription.length < 9) {
            Swal.fire("Mohon isi Description minimal 9 karakter", "", "warning");
            return false;
         } else if (!regexBasicCharacterWithEnter.test(requestDescription)) {
            Swal.fire("Mohon tidak menggunakan karakter selain .,/()@&_- pada Description", "", "warning");
            return false;
         } else if (businessBenefit === null || businessBenefit === undefined || businessBenefit.length < 9) {
            Swal.fire("Mohon isi Business Benefit minimal 9 karakter", "", "warning");
            return false;
         } else if (!regexBasicCharacterWithEnter.test(businessBenefit)) {
            Swal.fire("Mohon tidak menggunakan karakter selain .,/()@&_- pada Business Benefit", "", "warning");
            return false;
         } else if (uploadBonusImageFileId === null || uploadBonusImageFileId === undefined || uploadBonusImageFileId.length < 1) {
            Swal.fire("Image belum diisi", "", "warning");
            return false;
         } else if (notes === null || notes === undefined || notes.length < 9) {
            Swal.fire("Mohon isi Notes minimal 9 karakter", "", "warning");
            return false;
         } else if (!regexBasicCharacterWithEnter.test(notes)) {
            Swal.fire("Mohon tidak menggunakan karakter selain .,/()@&_- pada Notes", "", "warning");
            return false;
         } else {
            if (requestBonusId === '4') {
               // nominalPengajuan = 0;
            }
            submitProcess = true;
            // var formData = new FormData(this);
            var formData = new FormData();
            formData.set("<portlet:namespace/>crudType", action);
            formData.set("<portlet:namespace/>entryId", entryId);
            // Contact Information
            formData.set("<portlet:namespace/>email", $('[name="email"]').val());
            formData.set("<portlet:namespace/>emailCC", $('[name="emailCC"]').val());
            formData.set("<portlet:namespace/>phone", phone);
            // Request Information
            formData.set("<portlet:namespace/>requestBonusId", requestBonusId);
            formData.set("<portlet:namespace/>requestBonusName", requestBonusName);
            if (requestBonusId === '4') {
               formData.set("<portlet:namespace/>nominalPengajuan", 0);
            }
            formData.set("<portlet:namespace/>reqYearId", reqYearId);
            formData.set("<portlet:namespace/>requestDescription", requestDescription);
            formData.set("<portlet:namespace/>businessBenefit", businessBenefit);
            // Attachment
            formData.set("<portlet:namespace/>attachmentId", uploadBonusImageFileId);
            formData.set("<portlet:namespace/>attachmentName", $('[name="uploadBonusImageFileName"]').val());
            formData.set("<portlet:namespace/>attachmentPath", $('[name="uploadBonusImageFilePath"]').val());
            // Notes
            formData.set("<portlet:namespace/>notes", notes);
            formData.set("<portlet:namespace/>notesHistory", $('[name="notesHistory"]').val());
            formData.set("<portlet:namespace/>statusBonusId", statusBonusId);
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            createLoading();
            $.ajax({
               url: '${uploadStnkActionURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  destroyLoading();
                  if (JSON.parse(response).status === 'success') {
                     // swal.fire("Success", "Your request has been saved", "success")
                     swal.fire("Information", JSON.parse(response).message, "success")
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
                  destroyLoading();
                  if (data.status === 500) {
                     var msg = data.responseJSON.Message
                     swal.fire('Sorry', msg, 'error');
                  } else if (data.status === 408) {
                     swal.fire('Sorry', 'Request Time Out, Please Try Again', 'error');
                  } else {
                     swal.fire('Sorry', textStatus + 'error submit', 'error');
                  }
               },
               complete: function (jqXHR, textStatus) {
                  submitProcess = false;
                  destroyLoading();
               }
            })
         }
      }
   }

   $("#uploadBonusImageFile").change(function(e){
      processUpload(e, "uploadBonusImage", $(this));
   });

   // Upload File
   function processUpload(e, name, element){
      const fileName = e.target.files[0].name;
      const fileFormat = fileName.split('.').pop();
      if(e.target.files[0].size > 5242880){
         swal.fire("", "Maksimum Document Size 5 MB", "warning");
      } else if (fileFormat !== "pdf" && fileFormat !== "jpg" && fileFormat !== "jpeg" && fileFormat !== "png") {
         Swal.fire("", "Format file .pdf, .jpg, .jpeg, .png", "warning");
      } else {
         uploadDocumentFile(e.target.files[0], fileName, $('[name="'+name+'FileId"]')[0].value, element, name);
      }
   }

   function uploadDocumentFile(file, fileName, fileID, element, name) {
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
            url: '${uploadFileUploadStnkCommandURL}',
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
               fileId = data["fileID"];

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
         url : '${deleteFileUploadStnkCommandURL}',
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
               $('[name="uploadBonusImageFileId"]')[0].value = "";
               $('[name="uploadBonusImageFileName"]')[0].value = "";
               $('[name="uploadBonusImageFilePath"]')[0].value = "";
            } else {
               parentElement1.find("#delete-file-button").css("display", "inherit");
               parentElement1.find("#delete-loader").css("display", "none");

               parentElement.find("[name=file-status]").text("delete failed");
            }
         },
         error: function(e){
            console.log(e);
            swal.fire({icon: "error", title: "Failed to delete file"});
         },
         complete: function() {
            console.log("DONE");
         }
      });
   }

   function deleteFile() {
      Swal.fire({
         title: 'Delete data?',
         icon: 'question',
         showCancelButton: true,
         confirmButtonText: 'Yes',
         confirmButtonColor: '#EE1C25',
      }).then((result) => {
         if (result.isConfirmed) {
            let formData = new FormData();
            formData.set("<portlet:namespace/>entryId", entryId);
            formData.set("<portlet:namespace/>crudType", 'deleteFile');
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
            $.ajax({
               url : '${uploadStnkActionURL}',
               type : 'POST',
               data : formData,
               processData: false,
               contentType: false,
               success: function(response){
                  let data = JSON.parse(response);
                  if(data["acknowledge"] === 0) {
                     // Attachment
                     fileId = '';
                  } else {
                     console.log("else")
                  }
                  $('[name="uploadBonusImageFileId"]')[0].value = '';
                  $('[name="uploadBonusImageFileName"]')[0].value = '';
                  $('[name="uploadBonusImageFilePath"]')[0].value = '';
                  $('.upload-content').remove()
               },
               error: function(e){
                  console.log(e);
                  swal.fire({icon: "error", title: "Failed to delete file"});
               },
               complete: function() {
                  console.log("DONE");
               }
            });
         }
      });
   }

   function getRequestUploads() {
      $.ajax({
         url: "${requestUploadStnkURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var requestData = JSON.parse(response);
            var requestConst = $('#requestBonusId').select2({
               data: requestData.Data,
               tags: "true",
               placeholder: 'Pilih Request',
               allowClear: false,
               minimumInputLength: -1,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="requestBonusId"]')[0].value = data.id;
                  $('[name="requestBonusName"]')[0].value = data.text;
                  if (data.id === 'Pilih Request') {
                     return 'Pilih Request';
                  }
                  return data.text;
               }
            });
            requestConst.val(null);
            requestConst.trigger('change');
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            console.log("complete");
         }
      });
   }

   function getReqYears() {
      var date = new Date();
      var year = date.getFullYear();
      var years = []
      for(var i = -3; i <= 3; i++) {
         years.push({'id': year + i, 'text': year + i});
      }
      var reqYearConst = $('#reqYearId').select2({
         data: years,
         tags: "true",
         placeholder: 'Pilih Tahun',
         allowClear: false,
         minimumInputLength: -1,
         maximumInputLength: 100,
         templateSelection: function (data) {
            $('[name="reqYearId"]')[0].value = data.id;
            if (data.id === 'Pilih Tahun') {
               return 'Pilih Tahun';
            }
            return data.text;
         }
      });
      reqYearConst.val(null);
      reqYearConst.trigger('change');
   }

   function getUser() {
      $.ajax({
         url: "${uploadStnkRequestURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var userData = JSON.parse(response);
            data = userData.data;
            formNew()
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            console.log("complete");
         }
      });
   }
</script>