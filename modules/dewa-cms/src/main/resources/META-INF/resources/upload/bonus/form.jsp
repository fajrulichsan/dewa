<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="upload-bonus-action" var="uploadBonusActionURL" />
<portlet:resourceURL id="upload-file-upload-bonus" var="uploadFileUploadBonusCommandURL" />
<portlet:resourceURL id="delete-file-upload-bonus" var="deleteFileUploadBonusCommandURL" />

<portlet:resourceURL id="upload-bonus-application-category" var="requestUploadBonusURL"/>
<portlet:resourceURL id="upload-bonus-application-header-status" var="statusUploadBonusURL"/>

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
</style>

<form data-toggle="validator" role="form" class="form-horizontal" id="formUploadBonus" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
   <div class="row">
      <div class="col-md-12">
         <h5 style="padding: 5px;margin-bottom: 20px;color: #FFFFFF;background-color: #456b9a;">General Information</h5>
      </div>
   </div>
   <div class="row">
      <div class="col-md-4 col-lg-4">
         <div class="form-group">
            <label for="ticketDate" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Ticket Date</label>
            <div class="col-xs-7 col-sm-7 col-md-5 col-lg-5">
               <input type="text" class="form-control" name="ticketDate" id="ticketDate" readonly>
            </div>
            <div class="col-xs-5 col-sm-5 col-md-3 col-lg-3">
               <input type="text" class="form-control" id="ticketHour" name="ticketHour" placeholder="Auto" readonly />
            </div>
         </div>
         <div class="form-group">
            <label for="ticketNumber" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Ticket Number</label>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
               <input type="text" class="form-control" id="ticketNumber" name="ticketNumber" placeholder="Auto Generate" readonly>
            </div>
         </div>
         <div class="form-group">
            <label for="userId" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">User ID</label>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
               <input type="text" class="form-control" id="userId" name="userId" placeholder="Auto" readonly>
            </div>
         </div>
      </div>
      <div class="col-md-4 col-lg-4">
         <div class="form-group">
            <label for="userName" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Request Name</label>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
               <input type="text" class="form-control" id="userName" name="userName" placeholder="Auto" readonly>
            </div>
         </div>
         <div class="form-group">
            <label for="dealerName" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Cabang Dealer</label>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
               <input type="hidden" class="form-control" id="dealerId" name="dealerId" readonly>
               <input type="text" class="form-control" id="dealerName" name="dealerName" placeholder="Auto" readonly>
            </div>
         </div>
         <div class="form-group">
            <label for="approverName" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Approver</label>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
               <input type="hidden" class="form-control" id="approverId" name="approverId" readonly>
               <input type="text" class="form-control" id="approverName" name="approverName" placeholder="Auto" readonly>
            </div>
         </div>
      </div>
   </div>

   <div class="row">
      <div class="col-md-12">
         <h5 style="padding: 5px;margin-bottom: 20px;color: #FFFFFF;background-color: #456b9a;">Contact Information</h5>
      </div>
   </div>
   <div class="row">
      <div class="col-md-4">
         <div class="form-group">
            <label for="email" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Email</label>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
               <input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" data-minlength="6" maxlength="35" class="form-control" id="email" name="email" placeholder="Email" data-error="Minimal 6-35 karakter & Email harus valid." required readonly />
               <div class="help-block with-errors"></div>
            </div>
         </div>
         <div class="form-group">
            <label for="emailCC" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Email CC</label>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
               <input type="email" maxlength="35" class="form-control" id="emailCC" name="emailCC" placeholder="" />
               <div class="help-block with-errors"></div>
            </div>
         </div>
      </div>
      <div class="col-md-4">
         <div class="form-group">
            <label for="phone" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Phone <span style="color: red;">*</span></label>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
               <input type="tel" class="form-control numberOnly" id="phone" name="phone"
                      required pattern="[0-9]+" data-minlength="9" maxlength="16"
                      data-error="Nomor 9-16 karakter."/>
               <div class="help-block with-errors"></div>
            </div>
         </div>
      </div>
   </div>

   <div class="row">
      <div class="col-md-12">
         <h5 style="padding: 5px;margin-bottom: 20px;color: #FFFFFF;background-color: #456b9a;">Request Information</h5>
      </div>
   </div>
   <div class="row">
      <div class="col-md-4">
         <div class="form-group">
            <label for="requestBonusId" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Request Category <span style="color: red;">*</span></label>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
               <input type="hidden" class="form-control" id="requestBonusName" name="requestBonusName" />
               <select class="form-control" name="requestBonusId" id="requestBonusId" style="width: 100%;">
                  <%--<option value="ALL">Select All</option>--%>
               </select>
            </div>
         </div>
      </div>
      <div class="col-md-4">
         <div class="form-group nominalPengajuan">
            <label for="nominalPengajuan" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Nominal Pengajuan <span style="color: red;">*</span></label>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
               <input type="tel" class="form-control numberOnly" id="nominalPengajuan" name="nominalPengajuan"
                      pattern="[0-9]+" data-minlength="0" maxlength="16"
                      data-error="Nomer 0-16 karakter."/>
               <div class="help-block with-errors"></div>
            </div>
         </div>
      </div>
   </div>

   <div class="row">
      <div class="col-md-4">
         <div class="form-group">
            <label for="reqYearId" class="col-xs-12 col-sm-12 col-md-4 col-lg-4 control-label">Tahun <span style="color: red;">*</span></label>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
               <select class="form-control" name="reqYearId" id="reqYearId" style="width: 100%;">
                  <%--<option value="ALL">Select All</option>--%>
               </select>
            </div>
         </div>
      </div>
   </div>

   <div class="row">
      <div class="col-md-8">
         <div class="form-group">
            <label for="requestDescription" class="col-xs-12 col-sm-12 col-md-2 col-lg-2 control-label">Request Description <span style="color: red;">*</span></label>
            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
               <textarea class="form-control" rows="3" id="requestDescription" name="requestDescription"
                         required pattern="[a-zA-Z0-9 .]+" data-minlength="9" maxlength="255"
                         data-error="Text 9-255 karakter."></textarea>
               <div class="help-block with-errors"></div>
            </div>
         </div>
         <div class="form-group">
            <label for="businessBenefit" class="col-xs-12 col-sm-12 col-md-2 col-lg-2 control-label">Business Benefit <span style="color: red;">*</span></label>
            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
               <textarea class="form-control" rows="3" id="businessBenefit" name="businessBenefit"
                         required pattern="[a-zA-Z0-9 .]+" data-minlength="9" maxlength="255"
                         data-error="Text 9-255 karakter."></textarea>
               <div class="help-block with-errors"></div>
            </div>
         </div>
         <div class="form-group">
            <label for="attachment" class="col-xs-12 col-sm-12 col-md-2 col-lg-2 control-label">Attachment <span style="color: red;">*</span></label>
            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
               <div class="input-group" >
                  <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                     <span class="icon-browse"><img src="/o/com.astra.dewa.cms/assets/img/file.svg" /></span>
                     <span class="text-browse">Pilih File</span>
                     <input type="file" accept="application/pdf, image/jpg, image/jpeg, image/png" placeholder="" name="uploadBonusImageFile" id="uploadBonusImageFile" data-filename="" data-location="" style="display: none;" aria-invalid="false" class="materail_input valid">
                  </label>
                  <input type="text" class="form-control required" data-type="file" data-name="" name="uploadBonusImageFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
                  <input type="hidden" class="dpn" id="attachment" name="uploadBonusImageFileId" />
                  <input type="hidden" class="dpn" name="uploadBonusImageFilePath" />
               </div>
               <label class="col-form-label ipr-gray">Note: Maksimum Document Size 5 MB (pdf, jpg, jpeg, png)</label>
               <div style="margin-top: 0px;">
                  <button type="button" class="btn btn-xs btn-success" id="btnPreview">Preview</button>
                  <button type="button" class="btn btn-xs btn-warning" id="btnDelete">Delete</button>
               </div>
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
      <div class="col-md-8">
         <div class="form-group">
            <label for="requestDescription" class="col-xs-12 col-sm-12 col-md-2 col-lg-2 control-label">Notes <span style="color: red;">*</span></label>
            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
               <textarea class="form-control" rows="3" id="notes" name="notes"
                         required pattern="[a-zA-Z0-9 .]+" data-minlength="9" maxlength="255"
                         data-error="Text 9-255 karakter."></textarea>
               <div class="help-block with-errors"></div>
            </div>
         </div>
         <div class="form-group">
            <label for="businessBenefit" class="col-xs-12 col-sm-12 col-md-2 col-lg-2 control-label">Notes History</label>
            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
               <textarea class="form-control" rows="9" id="notesHistory" name="notesHistory" placeholder="" readonly></textarea>
            </div>
         </div>
      </div>
   </div>

   <div class="row">
      <div class="col-md-8" style="border-top: 1px solid #D9D9D9 ; margin: 10px 15px"></div>
      <div class="col-md-8">
         <button class="btn-ipr" type="button" id="btnSubmit">SUBMIT</button>
         <button class="btn-ipr" type="button" id="btnDraft">SAVE AS DRAFT</button>
         <button class="btn btn-ipr-cancel" type="button" id="btnCancel">CANCEL</button>
         <button class="btn btn-ipr-cancel" type="button" id="btnReset">RESET</button>
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
            <h4 class="modal-title text-left" id="myModalLabel"></h4>
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
   var action = "${action}";
   var ticketDateNew = "";
   var ticketHourNew = "";
   var homeUrl = "/group/dewa/cms-upload-bonus";
   var submitProcess = false;
   var entryId = "0";
   var fileId = ""

   $('#btnDraft').click(function (e) {
      // draft
      formSend('0')
   })
   $('#btnSubmit').click(function (e) {
      // waiting
      formSend('1')
   })
   $('#btnCancel').click(function (e) {
      window.location = homeUrl;
   })
   $('#btnReset').click(function (e) {
      if(entryId === '0') {
         formNew()
      } else {
         formEdit()
      }
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
            openForm()
         }
      }
   })
   $('#btnDelete').click(function (e) {
      if(fileId === null || fileId === '') {
         swal.fire({icon: "info", title: "File not found."});
      } else {
         deleteFile()
      }
   })

   function openForm() {
      var img = '<img src="' + $('[name="uploadBonusImageFilePath"]').val() + '" class="img-responsive img-rounded center-block">';
      $('#myModal').modal({
         backdrop: 'static',
         keyboard: false,
         isBodyOverflowing: true
      }).show()
      $('#myModalLabel').html($('[name="uploadBonusImageFileName"]').val())
      $('.modal .modal-body').css('overflow-y', 'auto');
      var _height = $(window).height()*0.8;
      $('.modal .modal-body').css('max-height', _height);
      $("#filePreview").html('');
      $("#filePreview").html(img);
   }

   function hideForm() {
      $('#myModal').modal('hide')
   }

   $(document).ready(function() {
      getRequestUploads()
      getReqYears()
      if (action === "create") {
         $('#form-title').text("New Acara")
         formNew();
      } else if (action === "update") {
         $('#form-title').text("Edit Acara")
         formEdit();
      } else {
         $('#form-title').text("Acara")
         formEdit();
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
      $('[name="nominalPengajuan"]')[0].value = "";
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

   function formEdit() {
      // if(data.statusBonusId === "draft" || data.statusBonusId === "revise") {
      if(data.statusBonusId === 0 || data.statusBonusId === 4) {
         $('#btnSubmit').show();
         $('#btnDraft').show();
      } else {
         $('#btnSubmit').hide();
         $('#btnDraft').hide();
      }

      entryId = data.id;
      // General Information
      $('[name="ticketDate"]')[0].value = data.ticketDate;
      $('[name="ticketHour"]')[0].value = data.ticketHour;
      $('[name="ticketNumber"]')[0].value = data.ticketNumber;
      $('[name="userId"]')[0].value = data.userId;
      $('[name="userName"]')[0].value = data.userName;
      $('[name="dealerId"]')[0].value = data.dealerId;
      $('[name="dealerName"]')[0].value = data.dealerName;
      $('[name="approverId"]')[0].value = data.approverId;
      $('[name="approverName"]')[0].value = data.approverName;
      // Contact Information
      $('[name="email"]')[0].value = data.email;
      $('[name="emailCC"]')[0].value = data.emailCC;
      $('[name="phone"]')[0].value = data.phone;
      // Request Information
      $('[name="requestBonusId"]')[0].value = data.requestBonusId;
      $('[name="requestBonusName"]')[0].value = data.requestBonusName;
      $('[name="nominalPengajuan"]')[0].value = data.nominalPengajuan;
      $('[name="reqYearId"]')[0].value = data.reqYearId;
      $('[name="requestDescription"]')[0].value = data.requestDescription;
      $('[name="businessBenefit"]')[0].value = data.businessBenefit;
      // Attachment
      fileId = data.attachmentId;
      $('[name="uploadBonusImageFileId"]')[0].value = data.attachmentId;
      $('[name="uploadBonusImageFileName"]')[0].value = data.attachmentName;
      $('[name="uploadBonusImageFilePath"]')[0].value = data.attachmentPath;
      // Notes
      $('[name="notes"]')[0].value = data.notes;
      $('[name="notesHistory"]')[0].value = data.notesHistory;
   }

   function formSend(statusBonusId) {
      const phone = $('[name="phone"]').val()
      const requestBonusId = $('[name="requestBonusId"]').val();
      const requestBonusName = $('[name="requestBonusName"]').val();
      var nominalPengajuan = $('[name="nominalPengajuan"]').val();
      const reqYearId = $('[name="reqYearId"]').val();
      const requestDescription = $('[name="requestDescription"]').val();
      const businessBenefit = $('[name="businessBenefit"]').val();
      const notes = $('[name="notes"]').val();
      const uploadBonusImageFileId = $('[name="uploadBonusImageFileId"]').val()
      if (submitProcess === false) {
         if (phone === null || phone === undefined || phone.length < 9) {
            Swal.fire("Phone belum sesuai.", "", "warning");
            return false;
         } else if (requestBonusName === null || requestBonusName === undefined || requestBonusName.length < 1  || requestBonusName === 'Pilih Request') {
            Swal.fire("Request Category belum sesuai.", "", "warning");
            return false;
         // } else if (requestBonusId !== 'claim' && (nominalPengajuan === null || nominalPengajuan === undefined || nominalPengajuan.length < 1)) {
         } else if (requestBonusId !== '3' && (nominalPengajuan === null || nominalPengajuan === undefined || nominalPengajuan.length < 1)) {
            Swal.fire("Nominal pengajuan belum diisi.", "", "warning");
            return false;
         } else if (reqYearId === null || reqYearId === undefined || reqYearId.length < 1) {
            Swal.fire("Tahun belum diisi.", "", "warning");
            return false;
         } else if (requestDescription === null || requestDescription === undefined || requestDescription.length < 9) {
            Swal.fire("Description belum sesuai.", "", "warning");
            return false;
         } else if (businessBenefit === null || businessBenefit === undefined || businessBenefit.length < 9) {
            Swal.fire("Business Benefit belum sesuai.", "", "warning");
            return false;
         } else if (uploadBonusImageFileId === null || uploadBonusImageFileId === undefined || uploadBonusImageFileId.length < 1) {
            Swal.fire("Image belum diisi.", "", "warning");
            return false;
         } else if (notes === null || notes === undefined || notes.length < 9) {
            Swal.fire("Notes belum sesuai.", "", "warning");
            return false;
         } else {
            if (requestBonusId === '3') {
               nominalPengajuan = 0;
            }
            submitProcess = true
            // var formData = new FormData(this);
            var formData = new FormData();
            formData.append("crudType", action)
            formData.append("entryId", entryId);
            // Contact Information
            formData.append("email", $('[name="email"]').val());
            formData.append("emailCC", $('[name="emailCC"]').val());
            formData.append("phone", phone);
            // Request Information
            formData.append("requestBonusId", requestBonusId);
            formData.append("requestBonusName", requestBonusName);
            if (requestBonusId === '3') {
               formData.append("nominalPengajuan", 0);
            } else {
               formData.append("nominalPengajuan", nominalPengajuan);
            }
            formData.append("reqYearId", reqYearId);
            formData.append("requestDescription", requestDescription);
            formData.append("businessBenefit", businessBenefit);
            // Attachment
            formData.append("attachmentId", uploadBonusImageFileId);
            formData.append("attachmentName", $('[name="uploadBonusImageFileName"]').val());
            formData.append("attachmentPath", $('[name="uploadBonusImageFilePath"]').val());
            // Notes
            formData.append("notes", notes);
            formData.append("notesHistory", $('[name="notesHistory"]').val());
            formData.append("statusBonusId", statusBonusId);

            createLoading();
            $.ajax({
               url: '${uploadBonusActionURL}',
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
            url: '${uploadFileUploadBonusCommandURL}',
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
      formData.append("authToken", Liferay.authToken);
      formData.append("file-id", fileID);
      let parentElement  = element.parentElement.parentElement.parentElement
      // let inputElement = $(parentElement1.siblings().find('input.form-control.required.switch-readonly'))[0];
      let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url : '${deleteFileUploadBonusCommandURL}',
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
            formData.append("entryId", entryId);
            formData.append("crudType", 'deleteFile');
            $.ajax({
               url : '${uploadBonusActionURL}',
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
         url: "${requestUploadBonusURL}",
         type: "POST",
         data: {"windowsId": "test"},
         success: function (response) {
            var requestData = JSON.parse(response);
            var requestConst = $('#requestBonusId').select2({
               data: requestData.Data,
               tags: "true",
               placeholder: 'Pilih Request',
               allowClear: false,
               // ajax: {
               //    url: "http://example.org/api/test",
               //    cache: false
               // }
               // minimumInputLength: 3,
               minimumInputLength: -1,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="requestBonusId"]')[0].value = data.id;
                  $('[name="requestBonusName"]')[0].value = data.text;
                  if (data.id === 'Pilih Request') {
                     return 'Pilih Request';
                  }
                  // if(data.id === 'claim') {
                  if(data.id === '3') {
                     $('.nominalPengajuan').hide()
                  } else {
                     $('.nominalPengajuan').show()
                  }
                  return data.text;
               }
            });
            if(action === "update") {
               requestConst.val(data.requestBonusId);
               requestConst.trigger('change');
            } else {
               requestConst.val(null);
               requestConst.trigger('change');
            }
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
      var year = date.getFullYear()
      var years = [
         {'id': year - 1, 'text': year - 1},
         {'id': year, 'text': year},
         {'id': year + 1, 'text': year + 1},
         {'id': year + 2, 'text': year + 2}
      ]
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
</script>
