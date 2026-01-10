<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/surat-penalty-stock-action" var="suratPenaltyStockActionURL"/>
<portlet:resourceURL id="/upload-file-surat-penalty-stock" var="uploadFileSuratPenaltyStockCommandURL"/>
<portlet:resourceURL id="/delete-file-surat-penalty-stock" var="deleteFileSuratPenaltyStockCommandURL"/>

<portlet:resourceURL id="/dealer-surat-penalty-stock" var="dealerSuratPenaltyStockURL"/>
<portlet:resourceURL id="/tahun-surat-penalty-stock" var="tahunSuratPenaltyStockURL"/>
<portlet:resourceURL id="/periode-review-surat-penalty-stock" var="periodeReviewSuratPenaltyStockURL"/>

<style>
    .dpn {
        display: none;
    }

    input#judul {
        border: 1px solid #aaa;
    }

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

    @media only screen and (min-width: 768px) and (max-width: 991px) {
        .tabbing-form {
            margin-top: 7.4pc;
        }
    }

    .wrap-btn {
        display: flex;
        align-items: center;
        gap: 1rem;
    }
</style>

<form data-toggle="validator" role="form" id="formSuratPenaltyStock" method="post" enctype="multipart/form-data"
      novalidate autocomplete="off">
   <div class="container" style="margin-left: 0;">
      <h3 id="form-title" style="margin-bottom: 50px;">Edit Surat Penalty Stock</h3>
      <input type="hidden" name="entryId">
      <div class="row">
         <div class="form-group col-md-9">
            <label class="title-form pull-left" for="judul">Judul</label>
            <input type="text" class="form-control" name="judul" id="judul"
                   pattern="[A-Za-z0-9. ]+" data-minlength="5" maxlength="100"
                   data-error="Text dengan 5-100 karakter & tidak boleh kosong." required/>
            <div class="help-block with-errors"></div>
         </div>
         <div class="form-group col-md-9">
            <label class="title-form pull-left" for="periodeReviewId">
               Periode Review
               <span style="color: red;">*</span>
            </label>
            <input type="hidden" class="form-control" name="periodeReviewName"/>
            <select class="form-control" name="periodeReviewId" id="periodeReviewId" style="width: 100%;"></select>
         </div>
         <div class="form-group col-md-9">
            <label class="title-form pull-left" for="tahunId">
               Tahun
               <span style="color: red;">*</span>
            </label>
            <input type="hidden" class="form-control" name="tahun"/>
            <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;"></select>
         </div>
         <div class="form-group col-sm-9">
            <div class="">
               <label class="col-form-label ipr-color">Lampiran *</label>
               <div class="input-group">
                  <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                     <span class="icon-browse">
                        <img src="<%=request.getContextPath()%>/assets/img/file.svg">
                     </span>
                     <span class="text-browse">Pilih File</span>
                     <input type="file" accept="application/pdf" placeholder="" name="suratPenaltyStockFile"
                            id="suratPenaltyStockFile" data-filename="" data-location="" style="display: none;"
                            aria-invalid="false" class="materail_input valid">
                  </label>
                  <input type="text" class="form-control required" data-type="file" data-name=""
                         name="suratPenaltyStockFileName" readonly
                         style="border: 1px solid #DBEDFF; background-color: white">
                  <input type="text" class="dpn" name="suratPenaltyStockFileId">
                  <input type="text" class="dpn" name="suratPenaltyStockFilePath">
               </div>
               <label class="col-form-label ipr-gray">file maksimal berukuran 5MB dengan format .pdf</label>
            </div>
         </div>
         <div class="form-group col-md-9 tabbing-form">
            <label class="title-form pull-left" for="dealerId">
               Dealer
               <span style="color: red;">*</span>
            </label>
            <input type="hidden" class="form-control" name="dealerCode"/>
            <input type="hidden" class="form-control" name="dealerName"/>
            <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;"></select>
         </div>
         <div class="col-md-9" style="border-top: 1px solid #D9D9D9 ; margin: 10px 15px"></div>
         <div class="col-md-9">
            <button class="btn-ipr" type="submit">Save</button>
            <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="/group/dealink/cms-surat-penalty-stock">Cancel</a>
         </div>
      </div>
   </div>
</form>

<script>
   var data = ${data};
   var action = "${action}";
   var homeUrl = "/group/dealink/cms-surat-penalty-stock";

   $(document).ready(function () {
      getDealers();
      getTahuns();
      getPeriodeReviews();
      if (action === "create") {
         $('#form-title').text("New Surat Penalty Stock");
         formNew();
      } else if (action === "update") {
         $('#form-title').text("Edit Surat Penalty Stock");
         var oneYearAgo = new Date().getFullYear() - 1;
         if (data.tahun < oneYearAgo) {
            Swal.fire({
               icon: 'error',
               title: 'Dokument tidak bisa di edit',
               text: 'Edit document Surat Penalty Stock sudah melewati batas satu tahun kebelakang',
               confirmButtonText: 'Back',
               allowOutsideClick: false
            }).then((result) => {
               if (result.isConfirmed) {
                  window.location.href = homeUrl;
               }
            });
         } else {
            formEdit();
         }
      } else {
         $('#form-title').text("Surat Penalty Stock")
         formEdit();
      }
   });

   function formNew() {
      $('[name="entryId"]')[0].value = "0";
      $('[name="dealerId"]')[0].value = "";
      $('[name="dealerCode"]')[0].value = "";
      $('[name="dealerName"]')[0].value = "";
      $('[name="tahun"]')[0].value = "";
      $('[name="periodeReviewId"]')[0].value = "";
      $('[name="periodeReviewName"]')[0].value = "";
      $('[name="suratPenaltyStockFileId"]')[0].value = "";
      $('[name="suratPenaltyStockFileName"]')[0].value = "";
      $('[name="suratPenaltyStockFilePath"]')[0].value = "";
   }

   function formEdit() {
      $('[name="entryId"]')[0].value = data.id;
      $('[name="dealerId"]')[0].value = data.dealerCode;
      $('[name="dealerCode"]')[0].value = data.dealerCode;
      $('[name="dealerName"]')[0].value = data.dealerName;
      $('[name="judul"]')[0].value = data.judul;
      $('[name="tahun"]')[0].value = data.tahun;
      $('[name="periodeReviewId"]')[0].value = data.periodeReviewId;
      $('[name="periodeReviewName"]')[0].value = data.periodeReviewName;
      $('[name="suratPenaltyStockFileId"]')[0].value = data.fileId;
      $('[name="suratPenaltyStockFileName"]')[0].value = data.fileName;
      $('[name="suratPenaltyStockFilePath"]')[0].value = data.filePath;
   }

   $("#suratPenaltyStockFile").change(function (e) {
      processUpload(e, "suratPenaltyStock", $(this));
   });

   var submitProcess = false;
   $("#formSuratPenaltyStock").submit(function (e) {
      e.preventDefault();
      const judul = $('[name="judul"]').val();
      const periodeReviewName = $('[name="periodeReviewName"]').val();
      const tahun = $('#tahunId').val();
      const suratPenaltyStockFileId = $('[name="suratPenaltyStockFileId"]').val();
      const dealerCode = $('#dealerId').val();

      if (submitProcess === false) {
         if (judul === null || judul === undefined || judul.length < 5) {
            Swal.fire("Judul belum terisi atau judul harus 5-100 karakter.", "", "warning");
            return false;
         } else if (!regexBasicCharacter.test(judul)) {
            Swal.fire("Judul hanya boleh diisi dengan karakter .,/()@&_-", "", "warning");
            return false;
         } else if (periodeReviewName === null || periodeReviewName === undefined || periodeReviewName.length < 1) {
            Swal.fire("Periode belum terisi.", "", "warning");
            return false;
         } else if (tahun === null || tahun === undefined || tahun.length < 1 || tahun === 'Pilih Tahun') {
            Swal.fire("Tahun belum terisi.", "", "warning");
            return false;
         } else if (suratPenaltyStockFileId === null || suratPenaltyStockFileId === undefined || suratPenaltyStockFileId.length < 1) {
            Swal.fire("Lampiran belum terisi.", "", "warning");
            return false;
         } else if (dealerCode === null || dealerCode === undefined || dealerCode.length < 1) {
            Swal.fire("Dealer belum terisi.", "", "warning");
            return false;
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.set("<portlet:namespace/>crudType", action)
            formData.set("<portlet:namespace/>entryId", $('[name="entryId"]').val());
            formData.set("<portlet:namespace/>judul", judul);
            formData.set("<portlet:namespace/>dealerCode", dealerCode);
            formData.set("<portlet:namespace/>tahun", tahun);
            formData.set("<portlet:namespace/>periodeReviewName", periodeReviewName);
            formData.set("<portlet:namespace/>suratPenaltyStockFileId", suratPenaltyStockFileId);
            formData.set("<portlet:namespace/>suratPenaltyStockFileName", $('[name="suratPenaltyStockFileName"]').val());
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            createLoading();
            $.ajax({
               url: '${suratPenaltyStockActionURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var code = JSON.parse(response).code;
                  var status = JSON.parse(response).status;
                  var message = JSON.parse(response).message;

                  if (status === 'success') {
                     swal.fire("Data berhasil disimpan", "", "success")
                        .then(function () {
                              window.location = homeUrl;
                           }
                        );
                  } else if (status === 'warning') {
                     swal.fire('Sorry', message, 'warning');
                  } else if (code) {
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

   function formValidation($form) {
      var isValidate = true;
      return isValidate;
   }

   function processUpload(e, name, element) {
      const dealerName = $('[name="dealerName"]').val()
      const tahun = $('[name="tahun"]').val()
      if (e.target.files[0].size > 5242880) {
         swal.fire("", "File Size cannot more than 5 MB", "warning");
      } else if (dealerName === null || dealerName === undefined || dealerName.length < 1) {
         Swal.fire("Nama Dealer belum terisi.", "", "warning");
      } else if (tahun === null || tahun === undefined || tahun.length < 1) {
         Swal.fire("Tahun Report belum terisi.", "", "warning");
      } else {
         let fileName = e.target.files[0].name;
         uploadDocumentFile(e.target.files[0], fileName, $('[name="' + name + 'FileId"]')[0].value, element, name);
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
         formData.set("<portlet:namespace/>document-id-new", $('[name="document-id-new"]').val());
         formData.set("<portlet:namespace/>dealerName", $('[name="dealerName"]').val());
         formData.set("<portlet:namespace/>tahun", $('[name="tahun"]').val());

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
            url: '${uploadFileSuratPenaltyStockCommandURL}',
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
                     <div style="margin-top: -8px;margin-bottom: 15px;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
                        <div>
                           <p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px">Upload file berhasil </p>
                        </div>
                        <div class="wrap-btn">
                           <div id="view-file-button">
                              <a href="javascript:void(0);" onclick="viewUploadedFile(this)" data-id="` + fileID + `" title="View File"><i class="fas fa-eye"></i></a>
                           </div>
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

   function viewUploadedFile(element) {
      var fileId = $(element).data("id");
      var urlShare = themeDisplay.getPortalURL() + "/group/dealink/preview-document?id=" + fileId;
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
      let parentElement = element.parentElement.parentElement.parentElement
      // let inputElement = $(parentElement1.siblings().find('input.form-control.required.switch-readonly'))[0];
      let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url: '${deleteFileSuratPenaltyStockCommandURL}',
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
            swal({
               icon: "error",
               title: "Failed to delete file"
            });
         },
         complete: function () {
            console.log("DONE");
         }
      });
   }

   function getDealers() {
      $.ajax({
         url: "${dealerSuratPenaltyStockURL}",
         type: "POST",
         params: {
            "<portlet:namespace/>dealerCode": data.dealerCode,
            "<portlet:namespace/>p_auth": Liferay.authToken
         },
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var responseData = JSON.parse(response);
            const dealerConst = $('#dealerId').select2({
               data: responseData.Data,
               tags: "true",
               placeholder: 'List Dealer',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="dealerCode"]')[0].value = data.id;
                  $('[name="dealerName"]')[0].value = data.text;
                  if (data.id === 'List Dealer') {
                     return 'List Dealer';
                  }
                  return data.text;
               }
            });
            if (action === "update") {
               dealerConst.val(data.dealerCode); // Select the option with a value of '1'
               dealerConst.trigger('change'); // Notify any JS components that the value changed
            } else {
               dealerConst.val(null);
               dealerConst.trigger('change');
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

   function getTahuns() {
      var tahunSekarang = new Date().getFullYear();
      var tahunTerakhir = tahunSekarang + 3;
      var year = [];

      for (var i = tahunSekarang - 1; i <= tahunTerakhir; i++) {
         year.push(i);
      }
      console.log(year);

      const tahunConst = $('#tahunId').select2({
         data: year.map(function (year) {
            return {id: year, text: year};
         }),
         placeholder: 'Pilih Tahun',
      });

      if (action === "update") {
         tahunConst.val(data.tahun); // Select the option with a value of '1'
         tahunConst.trigger('change'); // Notify any JS components that the value changed
      } else {
         tahunConst.val(null);
         tahunConst.trigger('change');
      }
   }

   function getPeriodeReviews() {
      $.ajax({
         url: "${periodeReviewSuratPenaltyStockURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var periodeReviewData = JSON.parse(response);
            const periodeReviewConst = $('#periodeReviewId').select2({
               data: periodeReviewData.Data,
               placeholder: 'Pilih Periode',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="periodeReviewId"]')[0].value = data.id;
                  $('[name="periodeReviewName"]')[0].value = data.text;
                  if (data.id === 'Pilih Periode') {
                     return 'Pilih Periode';
                  }
                  return data.text;
               }
            });
            if (action === "update") {
               periodeReviewConst.val(data.periodeReviewId); // Select the option with a value of '1'
               periodeReviewConst.trigger('change'); // Notify any JS components that the value changed
            } else {
               periodeReviewConst.val(null);
               periodeReviewConst.trigger('change');
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
</script>
