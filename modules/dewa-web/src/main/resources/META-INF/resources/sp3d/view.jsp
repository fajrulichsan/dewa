<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/cms/general-css.jsp" %>

<portlet:resourceURL id="/sp3d-list" var="sp3dListURL"/>
<portlet:resourceURL id="/sp3d-action" var="sp3dActionURL"/>
<portlet:resourceURL id="/sp3d-edit" var="sp3dEditURL"/>

<portlet:resourceURL id="/dealer-sp3d" var="dealersp3dURL"/>
<portlet:resourceURL id="/tahun-create-sp3d" var="tahunCreateSp3dURL"/>
<portlet:resourceURL id="/bulan-sp3d" var="bulanSp3dURL"/>
<portlet:resourceURL id="/tahun-filter-sp3d" var="tahunFilterSp3dURL"/>
<portlet:resourceURL id="/upload-file-sp3d" var="uploadFilesp3dCommandURL"/>
<portlet:resourceURL id="/delete-file-sp3d" var="deleteFilesp3dCommandURL"/>

<div class="sp3d cms-menu">
   <div class="tabcontent">
      <ul class="nav nav-tabs" id="cmsTab" role="tablist">
         <li class="nav-item">
            <a class="nav-link" id="buat_sp3d" data-toggle="tab" href="#buat_sp3d_tab" role="tab" aria-controls="buat_sp3d" aria-selected="false">
               <span id="action-status"></span>
               SP3D
            </a>
         </li>
         <li class="nav-item">
            <a class="nav-link" id="list_sp3d" data-toggle="tab" href="#list_sp3d_tab" role="tab" aria-controls="list_sp3d" aria-selected="false">
               Kelola SP3D
            </a>
         </li>
      </ul>
      <div class="tab-content" id="myTabContent">
         <%-- List SP3D Tab--%>
         <div class="tab-pane fade" id="list_sp3d_tab" role="tabpanel" aria-labelledby="profile-tab">
            <div class="row table-filters">
               <div class="col-xs-12 col-sm-12 col-md-3">
                  <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
                     <option value="ALL">Select All</option>
                  </select>
               </div>
            </div>
            <table id="sp3d_table" class="table table-hover nowrap cms-table" style="width: 100%;">
               <thead>
                  <tr>
                     <th>No</th>
                     <th>SP3D</th>
                     <th>Periode</th>
                     <th>Nama Dealer</th>
                     <th>Tanggal Upload</th>
                     <th>Aksi</th>
                  </tr>
               </thead>
               <tbody></tbody>
            </table>
         </div>

         <%-- Creat SP3D Tab--%>
         <div class="tab-pane fade" id="buat_sp3d_tab" role="tabpanel" aria-labelledby="profile-tab">
            <form id="formsp3d" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
               <input type="hidden" name="entryId">
               <div class="container row form-field">
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="tahunAction">
                        Tahun<span style="color: red;">*</span>
                     </label>
                     <input type="hidden" class=w"form-control" name="tahunActionId"/>
                     <select class="form-control" name="tahunAction" id="tahunAction" style="width: 100%;">
                        <option></option>
                     </select>
                  </div>
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="periodeAction">
                        Periode
                        <span style="color: red;">*</span>
                     </label>
                     <input type="hidden" class="form-control" name="periodeActionId"/>
                     <select class="form-control" name="periodeAction" id="periodeAction" style="width: 100%;">
                        <option></option>
                     </select>
                  </div>
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="dealerId">
                        List Dealer
                        <span style="color: red;">*</span>
                     </label>
                     <input type="hidden" class="form-control" name="dealerName"/>
                     <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;">
                        <option></option>
                     </select>
                  </div>
                  <div class="form-group col-sm-9">
                     <label class="col-form-label ipr-color">
                        File Report<span style="color: red;">*</span>
                     </label>
                     <div class="input-group upload-file-group">
                        <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                           <span class="icon-browse">
                              <img src="<%=request.getContextPath()%>/assets/img/file.svg">
                           </span>
                           <span class="text-browse">Pilih File</span>
                           <input type="file" accept="application/pdf" placeholder="" name="sp3dFile" id="sp3dFile"
                                  data-filename="" data-location="" style="display: none;" aria-invalid="false"
                                  class="materail_input valid">
                        </label>
                        <input type="text" class="form-control required" data-type="file" data-name="" name="sp3dFileName" readonly style="border: 1px solid #DBEDFF; background-color: white; width: 100%;">
                        <input type="text" class="dpn" name="sp3dFileId">
                        <input type="text" class="dpn" name="sp3dFilePath">
                     </div>
                     <label class="col-form-label ipr-gray" style="color: red;">
                        Maksimal upload file 5MB dengan format pdf
                     </label>
                  </div>
                  <div class="form-group col-md-9">
                     <div class="action-line"></div>
                  </div>
                  <div class="form-group col-md-9 action-button" id="btn-ipr">
                     <button class="btn-ipr" type="submit">Save</button>
                     <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="/group/dealink/cms-sp3d">Cancel</a>
                  </div>
               </div>
            </form>
         </div>
      </div>
   </div>
</div>

<script>
   var tahun = "ALL";
   var bulan = "ALL";
   var role = "ALL";
   var data;
   var action = "";
   var homeUrl = "/group/dealink/cms-sp3d";
   var viewUrl = "/group/dealink/preview-document";

   function refresh() {
      tahun = "ALL";
      sp3dDataTable.ajax.reload();
      $('[name=tahunId]')[0].value = "";
      getTahuns();
   }

   function refreshForm() {
      action = "create";
      $('#action-status').html('Buat ');
      $('#list_sp3d')[0].click();
      formNew();
      reloadForm();
      getTahuns();
      getBulans();
   }

   function reloadForm() {
      getDealers();
      gettahunAction();
      getTahuns();
      getBulans();
   }

   function formNew() {
      $('[name="entryId"]')[0].value = "";
      $('[name="tahunAction"]')[0].value = "";
      $('[name="periodeAction"]')[0].value = "";
      $('[name="dealerId"]').value = "";
      $('[name="dealerName"]').value = "";
      $('[name="sp3dFileId"]')[0].value = "";
      $('[name="sp3dFileName"]')[0].value = "";
      $('[name="sp3dFilePath"]')[0].value = "";
   }

   function formEdit() {
      $('[name="entryId"]')[0].value = data.id;
      $('[name="tahunAction"]')[0].value = data.tahun;
      $('[name="periodeAction"]')[0].value = data.periode;
      $('[name="dealerId"]').value = data.dealerId;
      $('[name="dealerName"]').value = data.dealerName;
      $('[name="sp3dFileId"]')[0].value = data.fileId;
      $('[name="sp3dFileName"]')[0].value = data.fileName;
      $('[name="sp3dFilePath"]')[0].value = data.filePath;
   }

   var sp3dTablelanguage = {
      "lengthMenu": "_MENU_",
      "paginate": {
         "first": "",
         "last": "",
         "next": '<span class="glyphicon glyphicon-menu-right"></span>',
         "previous": '<span class="glyphicon glyphicon-menu-left"></span>'
      },
      "search": "",
      searchPlaceholder: "Search..."
   }

   var sp3dDataTable = $('#sp3d_table').DataTable({
      searching: false,
      paging: true,
      info: false,
      language: sp3dTablelanguage,
      ajax: {
         url: '${sp3dListURL}',
         type: 'GET',
         data: function (d) {
            d.p_auth = Liferay.authToken;
            d.tahun = tahun;
         },
         dataSrc: function (json) {
            return json.Data;
         }
      },
      columns: [
         {data: "no", "width": "5%", className: "text-center"},
         {data: "fileName", "width": "25%"},
         {data: "tahun", "width": "30%", className: "text-center"},
         {data: "dealerName", "width": "30%", className: "text-center"},
         {
            data: "uploadDate", "width": "25%",
            className: "text-center",
            "render": function (data, type, row, meta) {
               if (type === "sort") {
                  return row.uploadDateSort;
               }
               return data;
            },
         },
         {
            data: "id",
            "width": "7%",
            className: "text-center",
            "render": function (data, type, row, meta) {
               return renderActionButton(row.id, row.fileId);
            },
            orderable: false
         }
      ],
      responsive: true,
      order: [],
      dom: '<"row"<"col-sm-12 cms-table-length"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>'
      <%-- dom: '<"row"<"col-md-6"l><"col-md-6"f>><"row"<"col-md-12"t>><"row"<"col-md-12"ip>>' --%>
   });

   function renderActionButton(dataId, fileId) {
      var response = "";
      response =
         '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a href="javascript:void(0)" onclick="updateSp3d(this)" data-id="' + dataId + '">' +
               '<span><img data-toogle="tooltip" title="Edit" src="<%=request.getContextPath()%>/assets/img/edit.svg"></img> </span>' +
            '</a>' +
            '<a href="javascript:void(0)" onclick="deleteSp3d(this)" data-id="' + dataId + '">' +
               '<span><img data-toogle="tooltip" title="Delete" src="<%=request.getContextPath()%>/assets/img/trash.svg"></img></span>' +
            '</a>' +
            '<a href="' + viewUrl + '?id=' + fileId + '"  data-toggle="tooltip" title="View" >' +
               '<span><img src="<%=request.getContextPath()%>/assets/img/eye.svg"></img></span>' +
            '</a>' +
         '</span>';
      return response;
   }

   function updateSp3d(element) {
      action = "update";
      var entryId = $(element).data("id");
      var formData = new FormData();
      formData.set("<portlet:namespace/>entryId", entryId);
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      $.ajax({
         url: "${sp3dEditURL}",
         type: "POST",
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            data = JSON.parse(response);
            var oneYearAgo = new Date().getFullYear() - 1;
            console.log(oneYearAgo);
            if (data.tahun < oneYearAgo) {
               Swal.fire({
                  icon: 'error',
                  title: 'Dokument tidak bisa di edit',
                  text: 'Edit document SP3D sudah melewati batas satu tahun kebelakang',
                  confirmButtonText: 'Back',
                  allowOutsideClick: false
               });
            } else {
               $('#action-status').html('Edit ');
               $('#buat_sp3d')[0].click();
               formEdit();
               reloadForm();
            }
         }
      })

   }

   function deleteSp3d(element) {
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
            formData.set("<portlet:namespace/>crudType", "delete");
            formData.set("<portlet:namespace/>entryId", entryId);
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            $.ajax({
               url: "${sp3dActionURL}",
               type: "POST",
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var data = JSON.parse(response);
                  if (data["acknowledge"] === 0) {
                     Swal.fire("Successfully delete data", "", "success")
                        .then((res) => {
                           sp3dDataTable.ajax.reload();
                        });
                  } else {
                     Swal.fire("Failed to Delete", data["message"], "");

                  }
               },
               error: function (err) {
                  console.log(err);
               }
            });
         }
      });
   }

   $("#sp3dFile").change(function (e) {
      processUpload(e, "sp3d", $(this));
   });

   var submitProcess = false;
   $("#formsp3d").submit(function (e) {
      e.preventDefault();
      const tahun = $('[name="tahunAction"]').val();
      const bulan = $('[name="periodeAction"]').val();
      const sp3dFileId = $('[name="sp3dFileId"]').val();
      var dealerCode = $('[name="dealerName"]').val();
      if (submitProcess === false) {
         if (tahun === null || tahun === undefined || tahun.length < 1 || tahun === 'Pilih Tahun') {
            Swal.fire("Tahun belum dipilih", "", "warning");
            return false;
         } else if (bulan === null || bulan === undefined || bulan.length < 1 || bulan === 'Pilih Bulan') {
            Swal.fire("Bulan belum dipilih", "", "warning");
         } else if (dealerCode === null || dealerCode === undefined || dealerCode.length < 1 || dealerCode === 'List Dealer') {
            Swal.fire("Kode Dealer belum dipilih", "", "warning");
         } else if (sp3dFileId === null || sp3dFileId === undefined || sp3dFileId.length < 1) {
            Swal.fire("File belum diisi", "", "warning");
            return false;
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.set("<portlet:namespace/>crudType", action)
            formData.set("<portlet:namespace/>entryId", $('[name="entryId"]').val());
            formData.set("<portlet:namespace/>tahun", tahun);
            formData.set("<portlet:namespace/>periode", bulan);
            formData.set("<portlet:namespace/>dealerId", $('#dealerId').val());
            formData.set("<portlet:namespace/>sp3dFileId", sp3dFileId);
            formData.set("<portlet:namespace/>sp3dFileName", $('[name="sp3dFileName"]').val());
            formData.set("<portlet:namespace/>sp3dFilePath", $('[name="sp3dFilePath"]').val());
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            createLoading();
            $.ajax({
               url: '${sp3dActionURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  if (JSON.parse(response).status === 'success') {
                     swal.fire("Data berhasil disimpan", "", "success")
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

   function processUpload(e, name, element) {
      const tahun = $('[name="tahunAction"]').val();
      const bulan = $('[name="periodeAction"]').val()
      const fileName = e.target.files[0].name;
      const fileFormat = fileName.split('.').pop();
      if (e.target.files[0].size > 5242880) {
         swal.fire("", "File Size cannot more than 5 MB", "warning");
      } else if (tahun === null || tahun === undefined || tahun.length < 1 || tahun === 'Pilih Tahun') {
         Swal.fire("Tahun Report belum terisi.", "", "warning");
      } else if (bulan === null || bulan === undefined || bulan.length < 1 || bulan === 'Pilih Bulan') {
         Swal.fire("Bulan Report belum terisi.", "", "warning");
      } else if (fileFormat !== "pdf") {
         Swal.fire("", "The file must be in PDF format.", "warning");
      } else {
         let fileName = e.target.files[0].name;
         uploadDocumentFile(e.target.files[0], fileName, $('[name="' + name + 'FileId"]')[0].value, element, name);
         return true;
      }
      $('#sp3dFile').val(null);

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
         formData.set("<portlet:namespace/>dealerName", $('[name="dealerName"]').val());
         formData.set("<portlet:namespace/>tahun", $('[name="tahunAction"]').val());
         formData.set("<portlet:namespace/>periode", $('[name="periodeAction"]').val());
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
            url: '${uploadFilesp3dCommandURL}',
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
                  $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileId"]'))[0].value = fileID
                  $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileName"]'))[0].value = fileName
                  $(elementEdit.parent().siblings('[name="' + nameEdit + 'FilePath"]'))[0].value = fileURL

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
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      formData.set("<portlet:namespace/>file-id", fileID);
      let parentElement = element.parentElement.parentElement.parentElement
      let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url: '${deleteFilesp3dCommandURL}',
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
         url: "${dealersp3dURL}",
         type: "POST",
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
                  $('[name="dealerId"]')[0].value = data.id;
                  $('[name="dealerName"]')[0].value = data.text;
                  if (data.id === 'List Dealer') {
                     return 'List Dealer';
                  }
                  return data.text;
               }
            });
            if (action === "update") {
               dealerConst.val(data.dealerId); // Select the option with a value of '1'
            } else {
               dealerConst.val(null);
            }
            dealerConst.trigger('change');
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
      $.ajax({
         url: "${tahunFilterSp3dURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var tahunData = JSON.parse(response);
            const tahunConst = $('#tahunId').select2({
               data: tahunData.Data,
               placeholder: 'Pilih Tahun',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  if (data.id !== '') {
                     tahun = data.id;
                     sp3dDataTable.ajax.reload();
                  }
                  return data.text;
               }
            });
            tahunConst.val(null);
            tahunConst.trigger('change');
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            console.log("complete");
         }
      });
   }

   function gettahunAction() {
      $.ajax({
         url: "${tahunCreateSp3dURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var tahunData = JSON.parse(response);
            const tahunConst = $('#tahunAction').select2({
               data: tahunData.Data,
               tags: "true",
               placeholder: 'Pilih Tahun',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="tahunAction"]')[0].value = data.text;
                  if (data.id === 'Pilih Tahun') {
                     return 'Pilih Tahun';
                  }
                  return data.text;
               }
            });
            if (action === "update") {
               tahunConst.val(data.tahun); // Select the option with a value of '1'
            } else {
               tahunConst.val(null);
            }
            tahunConst.trigger('change');
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            console.log("complete");
         }
      });
   }

   function getBulans() {
      $.ajax({
         url: "${bulanSp3dURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var bulanData = JSON.parse(response);
            const periodeConst = $('#periodeAction').select2({
               data: bulanData.Data,
               tags: "true",
               placeholder: 'Pilih Bulan',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="periodeAction"]')[0].value = data.text;
                  if (data.id === 'Pilih Bulan') {
                     return 'Pilih Bulan';
                  }
                  return data.text;
               }
            });
            if (action === "update") {
               periodeConst.val(data.periode); // Select the option with a value of '1'
            } else {
               periodeConst.val(null);
            }
            periodeConst.trigger('change');
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            console.log("complete");
         }
      });
   }

   $(document).ready(function () {
      reloadForm();
      $('#action-status').html('Buat ');
      $('#list_sp3d')[0].click();
      $('[data-toggle="tooltip"]').tooltip();
      action = "create";

      $('.dataTables_length select').select2({
         minimumResultsForSearch: -1
      });
   });
</script>