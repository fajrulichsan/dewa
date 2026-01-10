<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/sales-program-action" var="salesProgramActionURL" />
<portlet:resourceURL id="/upload-sales-program" var="uploadFileSalesProgramCommandURL" />
<portlet:resourceURL id="/delete-sales-program" var="deleteFileSalesProgramCommandURL" />

<portlet:resourceURL id="/tahun-sales-program" var="tahunSalesProgramURL"/>
<portlet:resourceURL id="/bulan-sales-program" var="bulanSalesProgramURL"/>

<style>
    /*Select2*/
    .select2-container--default .select2-selection--single {
        padding:6px;
        height: 37px;
        font-size: 1.2em;
        position: relative;
    }

    .wrap-btn{
       display: flex;
       align-items: center;
       gap: 1rem;
    }
</style>

<form id="formSalesProgram" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
   <div class="container" style="margin-left: 0;">
      <h3 id="form-title" style="margin-bottom: 50px;">Edit Sales Program</h3>
      <input type="hidden" name="entryId">
      <div class="form-group col-md-9">
         <label class="title-form pull-left" for="tahunId">Tahun</label>
         <input type="hidden" class="form-control" name="tahun" />
         <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
         </select>
      </div>
      <div class="form-group col-md-9">
         <label class="title-form pull-left" for="periodeId">Periode</label>
         <input type="hidden" class="form-control" name="periode" />
         <select class="form-control" name="periodeId" id="periodeId" style="width: 100%;">
         </select>
      </div>
      <div class="form-group">
         <div class="col-sm-9">
            <label class="col-form-label ipr-color">File Report</label>
            <div class="input-group" >
               <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                  <span class="icon-browse"><img src="<%=request.getContextPath()%>/assets/img/file.svg"></span>
                  <span class="text-browse">Pilih File</span>
                  <input type="file" accept="application/pdf" placeholder="" name="salesProgramFile" id="salesProgramFile" data-filename="" data-location="" style="display: none;" aria-invalid="false" class="materail_input valid">
               </label>
               <input type="text" class="form-control required" data-type="file" data-name="" name="salesProgramFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
               <input type="hidden" class="dpn" name="salesProgramFileId" >
            </div>
            <label class="col-form-label ipr-gray">Maksimal upload file 5MB dengan format pdf </label>
         </div>
      </div>
      <div class="col-md-9" style="border-top: 1px solid #D9D9D9 ; margin: 10px 15px"></div>
      <div class="col-md-9" style="margin: 1pc 0;">
         <button class="btn-ipr" type="submit">Save</button>
         <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="/group/dealink/cms-sales-program">Cancel</a>
      </div>
   </div>
</form>

<script>
   var data = ${data};
   console.log(data);
   var action = "${action}";
   var homeUrl = "/group/dealink/cms-sales-program";

   $(document).ready(function() {
      console.log( "ready!" );
      getYears();
      getBulans();
      if (action === "create") {
         $('#form-title').text("New Sales Program")
         formNew();
      } else if (action === "update") {
         $('#form-title').text("Edit Sales Program")
         var oneYearAgo = new Date().getFullYear() - 1;
         if (data.tahun < oneYearAgo) {
            Swal.fire({
               icon: 'error',
               title: 'Dokument tidak bisa di edit',
               text: 'Edit document Sales Program sudah melewati batas satu tahun kebelakang',
               confirmButtonText: 'Back',
               allowOutsideClick: false
            }).then((result) => {
               if (result.isConfirmed) {
                  window.location.href = homeUrl;
               }
            });
         }else{
            formEdit();
         }
      } else {
         $('#form-title').text("Sales Program")
         formEdit();
      }
   });

   function formNew() {
      $('[name="entryId"]')[0].value = "0";
      $('[name="tahun"]')[0].value = "";
      $('[name="periode"]')[0].value = "";
      $('[name="salesProgramFileId"]')[0].value = "";
      $('[name="salesProgramFileName"]')[0].value = "";
   }

   function formEdit() {
      $('[name="entryId"]')[0].value = data.id;
      $('[name="tahun"]')[0].value = data.tahun;
      $('[name="periode"]')[0].value = data.periode;
      $('[name="salesProgramFileId"]')[0].value = data.fileId;
      $('[name="salesProgramFileName"]')[0].value = data.fileName;
   }

   $("#salesProgramFile").change(function(e){
      console.log("salesProgram upload.");
      processUpload(e, "salesProgram", $(this));
   });

   var submitProcess = false;
   $("#formSalesProgram").submit(function (e) {
      e.preventDefault();
      const periode = $('[name="periode"]').val();
      const tahun = $('#tahunId').val();
      const salesProgramFileId = $('[name="salesProgramFileId"]').val();

      if (submitProcess === false) {
         var $form = $(this);
         if (!formValidation($form)) {
            Swal.fire(
               "Please complete all fields before save data!",
               "",
               "warning"
            );
            return false;
         } else {
            submitProcess = true;
            var formData = new FormData(this);
            formData.set("<portlet:namespace/>crudType", action)
            formData.set("<portlet:namespace/>entryId", $('[name="entryId"]').val());
            formData.set("<portlet:namespace/>tahun", tahun);
            formData.set("<portlet:namespace/>periode", periode);
            formData.set("<portlet:namespace/>salesProgramFileId", salesProgramFileId);
            formData.set("<portlet:namespace/>salesProgramFileName", $('[name="salesProgramFileName"]').val());
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            createLoading();
            $.ajax({
               url: '${salesProgramActionURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  console.log("response  : " + JSON.stringify(response));
                  if (JSON.parse(response).status === 'success') {
                     swal.fire("Success", "Your request has been saved", "success")
                     .then(function () {
                           window.location = homeUrl;
                        }
                     );
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

   function formValidation($form) {
      var isValidate = true;
      return isValidate;
   }

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
            url: '${uploadFileSalesProgramCommandURL}',
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
               console.log(data);

               if (acknowledge === 1) {
                  $(elementEdit.parent().siblings('[name="'+nameEdit+'FileId"]'))[0].value=fileID
                  $(elementEdit.parent().siblings('[name="'+nameEdit+'FileName"]'))[0].value=fileName

                  let successHTML = `
                     <div style="margin-top: -8px;margin-bottom: 15px;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
                        <div>
                           <p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px">Upload file berhasil </p>
                        </div>
                        <div class="wrap-btn">
                           <div id="view-file-button">
                              <a href="javascript:void(0);" onclick="viewUploadedFile(this)" data-id="`+fileID+`" title="View File"><i class="fas fa-eye"></i></a>
                           </div>
                           <div id="delete-file-button">
                              <a href="javascript:void(0);" onclick="deleteUploadedFile(this, `+fileID+`)" title="Delete File"><i class="fas fa-trash"></i></a>
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

   function viewUploadedFile(element){
      var fileId = $(element).data("id");
      var urlShare = themeDisplay.getPortalURL() + "/group/dealink/preview-document?id=" +fileId;
      var newTab = window.open(urlShare, '_blank');
      newTab.focus();
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
         url : '${deleteFileSalesProgramCommandURL}',
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

   function getYears() {
      var tahunSekarang = new Date().getFullYear();
      var tahunTerakhir = tahunSekarang + 3;
      var year = [];

      for (var i = tahunSekarang-1; i <= tahunTerakhir; i++) {
         year.push(i);
      }
      console.log(year);

      const tahunConst = $('#tahunId').select2({
         data: year.map(function(year) {
            return { id: year, text: year };
         }),
         placeholder: 'Pilih Tahun',
      });

      if(action === "update") {
         tahunConst.val(data.tahun); // Select the option with a value of '1'
         tahunConst.trigger('change'); // Notify any JS components that the value changed
      } else {
         tahunConst.val(null);
         tahunConst.trigger('change');
      }
   }

   function getBulans() {
      $.ajax({
         url: "${bulanSalesProgramURL}",
         type: "POST",
         data: {
            "<portlet:namespace/>p_auth": Liferay.authToken
         },
         success: function (response) {
            var bulanData = JSON.parse(response);
            const periodeConst = $('#periodeId').select2({
               data: bulanData.Data,
               placeholder: 'Pilih Kuartal',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  console.log(data.id + " - " + data.text);
                  $('[name="periode"]')[0].value = data.text;
                  if (data.id === 'List Dealer') {
                     return 'Pilih Kuartal';
                  }
                  return data.text;
               }
            });
            if(action === "update") {
               periodeConst.val(data.periode); // Select the option with a value of '1'
               periodeConst.trigger('change'); // Notify any JS components that the value changed
            } else {
               periodeConst.val(null);
               periodeConst.trigger('change');
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