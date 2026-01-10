<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/cms/general-css.jsp" %>

<portlet:resourceURL id="/sk-iris-list" var="skIrisListURL"/>
<portlet:resourceURL id="/sk-iris-action" var="skIrisActionURL"/>
<portlet:resourceURL id="/sk-iris-edit" var="skIrisEditURL"/>

<portlet:resourceURL id="/upload-file-sk-iris" var="uploadFileSkIrisCommandURL" />
<portlet:resourceURL id="/delete-file-sk-iris" var="deleteFileSkIrisCommandURL" />


<portlet:resourceURL id="/tahun-sk-iris" var="tahunSkIrisURL"/>
<portlet:resourceURL id="/tahun-filter-sk-iris" var="tahunFilterSkIrisURL"/>
<portlet:resourceURL id="/bulan-sk-iris" var="bulanSkIrisURL"/>
<portlet:resourceURL id="/dealer-skiris" var="dealerSkIrisURL"/>
<portlet:resourceURL id="/wilayah-sk-iris-cms" var="wilayahSkIrisURL"/>

<div class="sk_iris cms-menu">
   <div class="tabcontent">
       <ul class="nav nav-tabs" id="cmsTab" role="tablist">
           <li class="nav-item">
               <a class="nav-link" id="buat_skiris" data-toggle="tab" href="#buat_skris_tab" role="tab" aria-controls="buat_sp3d" aria-selected="false">
                   <span id="action-status"></span>
                   SK Iris
               </a>
           </li>
           <li class="nav-item" >
               <a class="nav-link" id="list_skiris" data-toggle="tab" href="#list_skris_tab" role="tab" aria-controls="list_sp3d" aria-selected="false">
                   Kelola SK Iris
               </a>
           </li>
       </ul>

       <div class="tab-content" id="myTabContent">
           <%-- List SK Iris --%>
           <div class="tab-pane fade" id="list_skris_tab" role="tabpanel" aria-labelledby="profile-tab">
               <div class="row table-filters">
                   <div class="col-xs-12 col-sm-12 col-md-3">
                       <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
                           <option value="ALL">Select All</option>
                       </select>
                   </div>
                   <div class="col-xs-12 col-sm-12 col-md-3">
                       <select class="form-control" name="bulanId" id="bulanId" style="width: 100%;">
                           <option value="ALL">Select All</option>
                       </select>
                   </div>
               </div>
               <table id="sk_iris_table" class="table table-hover nowrap cms-table" style="width:100%">
                   <thead>
                       <tr>
                           <th>No</th>
                           <th>SK Iris</th>
                           <th>Periode</th>
                           <th>tahun</th>
                           <th>Tanggal Upload</th>
                           <th>Keterangan</th>
                       </tr>
                   </thead>
                   <tbody></tbody>
               </table>

           </div>
           <%--  Buat SK Iris --%>
           <div class="tab-pane fade" id="buat_skris_tab" role="tabpanel" aria-labelledby="profile-tab">
               <form id="formSkIris" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
                   <input type="hidden" name="entryId">
                   <div class="container row form-field">
                       <div class="form-group col-md-9">
                           <label class="title-form pull-left" for="wilayahId">
                               Wilayah
                               <span style="color: red;">*</span>
                           </label>
                           <input type="hidden" class="form-control" name="wilayahName" />
                           <select class="form-control" name="wilayahId" id="wilayahId" style="width: 100%;">
                               <option></option>
                           </select>
                       </div>
                       <div class="form-group col-md-9">
                           <label class="title-form pull-left" for="tahunIdBuat">
                               Tahun
                               <span style="color: red;">*</span>
                           </label>
                           <input type="hidden" class="form-control" name="tahun" />
                           <select class="form-control" name="tahunIdBuat" id="tahunIdBuat" style="width: 100%;">
                               <option></option>
                           </select>
                       </div>
                      <div class="form-group col-md-9">
                          <label class="title-form pull-left" for="periodeId">
                              Periode
                              <span style="color: red;">*</span>
                          </label>
                          <input type="hidden" class="form-control" name="periode" />
                          <select class="form-control" name="periodeId" id="periodeId" style="width: 100%;">
                              <option></option>
                          </select>
                      </div>
                       <div class="form-group col-md-9">
                           <label class="title-form pull-left" for="dealerId">
                               List Dealer
                               <span style="color: red;">*</span>
                           </label>
                           <input type="hidden" class="form-control" name="dealerName" />
                           <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;">
                               <option></option>
                           </select>
                       </div>
                      <div class="form-group col-md-9">
                          <label class="title-form pull-left" for="fileKategori">
                              File Kategori
                              <span style="color: red;">*</span>
                          </label>
                          <input type="hidden" class="form-control" name="kategori" />
                          <select class="form-control" name="fileKategori" id="fileKategori" style="width: 100%;">
                              <option></option>
                          </select>
                      </div>
                      <div class="form-group col-sm-9">
                          <label class="col-form-label ipr-color">
                              File Report
                              <span style="color: red;">*</span>
                          </label>
                          <div class="input-group upload-file-group" >
                              <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                                  <span class="icon-browse">
                                      <img src="<%=request.getContextPath()%>/assets/img/file.svg">
                                  </span>
                                  <span class="text-browse">Pilih File</span>
                                  <input type="file" accept="application/pdf" placeholder="" name="skIrisFile" id="skIrisFile" data-filename="" data-location="" style="display: none;" aria-invalid="false" class="materail_input valid">
                              </label>
                              <input type="text" class="form-control required" data-type="file" data-name="" name="skIrisFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
                              <input type="text" class="dpn" name="skIrisFileId" >
                              <input type="text" class="dpn" name="skIrisFilePath" >
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
                          <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="/group/dealink/cms-sk-iris">Cancel</a>
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
   var homeUrl = "/group/dealink/cms-sk-iris";

   $(document).ready(function () {
       reloadForm();
       $('#action-status').html('Buat ');
       $('#list_skiris')[0].click();
       $('[data-toggle="tooltip"]').tooltip();
       action = "create";
       $('.dataTables_length select').select2({
           minimumResultsForSearch: -1
       });
   });

   function formNew() {
       $('[name="entryId"]')[0].value = "0";
       $('[name="tahun"]')[0].value = "";
       $('[name="periode"]')[0].value = "";
       $('[name="dealerId"]').value = "";
       $('[name="wilayahId"]')[0].value = "";
       $('[name="wilayahName"]')[0].value = "";
       $('[name="skIrisFileId"]')[0].value = "";
       $('[name="skIrisFileName"]')[0].value = "";
       $('[name="skIrisFilePath"]')[0].value = "";
   }

   function formEdit() {
       $('[name="entryId"]')[0].value = data.id;
       $('[name="tahun"]')[0].value = data.tahun;
       $('[name="periode"]')[0].value = data.periode;
       $('[name="dealerName"]').value = data.dealerId;
       $('[name="wilayahId"]')[0].value = data.wilayahId;
       $('[name="wilayahName"]')[0].value = data.wilayahName;
       $('[name="kategori"]')[0].value = data.kategori;
       $('[name="skIrisFileId"]')[0].value = data.fileId;
       $('[name="skIrisFileName"]')[0].value = data.fileName;
       $('[name="skIrisFilePath"]')[0].value = data.filePath;
   }

   function refresh() {
       tahun = "ALL";
       bulan = "ALL";
       sp3dDataTable.ajax.reload();
       $('[name=tahunId]')[0].value = "";
       $('[name=bulanId]')[0].value = "";
       getTahuns();
       getBulans();
       getTahunFilter()
   }

   function refreshForm() {
       reloadForm();
       action = "create";
       $('#action-status').html('Buat ');
       $('#list_skiris')[0].click();
       formNew();
       reloadForm();
   }

   function reloadForm() {
       getFileKategori();
       getDealers();
       getWilayahs();
       getBulans();
       getTahuns();
       getTahunFilter()
   }

   var tableLanguage = {
       lengthMenu: "_MENU_",
       paginate: {
           "first": "",
           "last": "",
           "next": '<span class="glyphicon glyphicon-menu-right"></span>',
           "previous": '<span class="glyphicon glyphicon-menu-left"></span>'
       },
       search: "",
       searchPlaceholder: "Search..."
   }

   var skIrisDataTable = $('#sk_iris_table').DataTable({
      searching: false,
      paging: true,
      info: true,
      language: tableLanguage,
      dom: '<"row"<"col-sm-12 cms-table-length"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
      ajax: {
         url: '${skIrisListURL}',
         type: 'GET',
         data: function(d){
            d.tahun =  tahun;
            d.periode =  bulan;
         },
         dataSrc: function (json) {
            return json.Data;
         }
      },
      columns: [
         {data: "no", "width": "5%", className: "text-center"},
         {data: "fileName", "width": "25%",},
         {
             data: "periode", "width": "30%",
             className: "text-center",
             "render": function (data, type, row, meta) {
                 if (type === "sort") {
                     return row.periodeSort;
                 }
                 return data;
             },
         },
         {data: "tahun", "width": "5%", className: "text-center"},
          {
              data: "uploadDate", "width":"25%",
              className: "text-center",
              "render": function (data, type, row, meta) {
                  if(type === "sort"){
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
       columnDefs : [
           { targets : [2],
               orderData : [2,3]
           }
       ]
   });
   var viewUrl ="/group/dealink/preview-document";
   function renderActionButton(dataId, fileId) {
      var response = "";
         response = '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a href="javascript:void(0)" onclick="updateSkiris(this)" data-id="' + dataId + '">' +
               '<span><img data-toogle="tooltip" title="Edit" src="<%=request.getContextPath()%>/assets/img/edit.svg"></img> </span>' +
            '</a>' +
            '<a href="javascript:void(0)" onclick="deleteSkIris(this)" data-id="' + dataId + '">' +
               '<span><img data-toogle="tooltip" title="Delete" src="<%=request.getContextPath()%>/assets/img/trash.svg"></img></span>' +
            '</a>' +
             '<a href="'+ viewUrl +'?id='+fileId+'"  data-toggle="tooltip" title="View">' +
             '<span><img src="<%=request.getContextPath()%>/assets/img/eye.svg"></img></span>' +
             '</a>' +
         '</span>';
      return response;
   }

   function updateSkiris(element) {
       action = "update";
       var entryId = $(element).data("id");
       var formData = new FormData();
       formData.append("entryId", entryId);
       $.ajax({
           url: "${skIrisEditURL}",
           type: "POST",
           data: formData,
           processData: false,
           contentType: false,
           success: function (response) {
               data = JSON.parse(response);
               console.log(response)
               console.log(data);
               var oneYearAgo = new Date().getFullYear() - 1;
               console.log(oneYearAgo);
               if (data.tahun < oneYearAgo) {
                   Swal.fire({
                       icon: 'error',
                       title: 'Dokument tidak bisa di edit',
                       text: 'Edit document SK-IRIS sudah melewati batas satu tahun kebelakang',
                       confirmButtonText: 'Back',
                       allowOutsideClick: false
                   });
               }else{
                   $('#action-status').html('Edit ');
                   $('#buat_skiris')[0].click();
                   formEdit();
                   reloadForm();
               }
           }
       })
   }

   function deleteSkIris(element) {
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
            formData.append("crudType", "delete");
            formData.append("entryId", entryId);
            $.ajax({
               url: "${skIrisActionURL}",
               type: "POST",
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var data = JSON.parse(response);
                  if (data["acknowledge"] === 0) {
                      Swal.fire("Successfully delete data", "", "success")
                          .then((res) => {
                              skIrisDataTable.ajax.reload();
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


   $("#skIrisFile").change(function(e){
       processUpload(e, "skIris", $(this));
   });

   var submitProcess = false;
   $("#formSkIris").submit(function (e) {
       e.preventDefault();
       const tahun = $('[name="tahun"]').val()
       const periode = $('[name="periode"]').val()
       const dealer = $('[name="dealerName"]').val()
       const skIrisFileId = $('[name="skIrisFileId"]').val()
       const wilayah = $('[name="wilayahId"]').val()
       const kategori = $('[name="kategori"]').val()
       console.log(kategori);
       if (submitProcess === false) {
           if (wilayah === null || wilayah === undefined || wilayah.length < 1 || wilayah === 'Pilih Wilayah') {
               Swal.fire("Wilayah belum dipilih.", "", "warning");
               return false;
           } else if (tahun === null || tahun === undefined || tahun.length < 1 || tahun === 'Pilih Tahun') {
               Swal.fire("Tahun belum terisi.", "", "warning");
               return false;
           } else if (periode === null || periode === undefined || periode.length < 1 || periode === 'Pilih Bulan') {
               Swal.fire("Periode belum terisi.", "", "warning");
               return false;
           } else if (dealer === null || dealer === undefined || dealer.length < 1 || dealer === 'Pilih Dealer') {
               Swal.fire("Dealer belum terisi.", "", "warning");
               return false;
           } else if (kategori === null || kategori === undefined || kategori.length < 1 || kategori === 'Pilih Kategori') {
               Swal.fire("Kategori belum terisi.", "", "warning");
               return false;
           } else if (skIrisFileId === null || skIrisFileId === undefined || skIrisFileId.length < 1) {
               Swal.fire("File belum terisi.", "", "warning");
               return false;
           } else {
               submitProcess = true
               var formData = new FormData(this);
               formData.append("crudType", action)
               formData.append("entryId", $('[name="entryId"]').val());
               formData.append("tahun", tahun);
               formData.append("periode", periode);
               formData.append("dealerName", $('[name="dealerName"]').val());
               formData.append("wilayahId", wilayah);
               formData.append("wilayahName", $('[name="wilayahName"]').val());
               formData.append("fileKategori", kategori);
               formData.append("skIrisFileId", skIrisFileId);
               formData.append("skIrisFileName", $('[name="skIrisFileName"]').val());
               formData.append("skIrisFilePath", $('[name="skIrisFilePath"]').val());

               createLoading();
               $.ajax({
                   url: '${skIrisActionURL}',
                   type: 'POST',
                   data: formData,
                   processData: false,
                   contentType: false,
                   success: function (response) {
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

   function processUpload(e, name, element){
       const wilayah = $('[name="wilayahId"]').val();
       const periode = $('[name="periode"]').val();
       const tahun = $('[name="tahun"]').val();
       const fileName = e.target.files[0].name;
       const fileFormat = fileName.split('.').pop();
       const kategori = $('[name="kategori"]').val()
       if(e.target.files[0].size > 5242880) {
           swal.fire("", "File Size cannot more than 5 MB", "warning");
       } else if (wilayah === null || wilayah === undefined || wilayah.length < 1 || wilayah === "Pilih Wilayah") {
           Swal.fire("Wilayah belum terisi.", "", "warning");
       } else if (tahun === null || tahun === undefined || tahun.length < 1 || tahun === "Pilih Tahun") {
           Swal.fire("Tahun Report belum terisi.", "", "warning");
       } else if (periode === null || periode === undefined || periode.length < 1 || periode === "Pilih Bulan") {
           Swal.fire("Periode belum terisi.", "", "warning");
       } else if (fileFormat !== "pdf") {
           Swal.fire("", "The file must be in PDF format.", "warning");
       } else if (kategori === null || kategori === undefined || kategori.length < 1 || kategori === "Pilih Kategori") {
           Swal.fire("Kategori belum terisi.", "", "warning");
       } else {
           let fileName = e.target.files[0].name;
           uploadDocumentFile(e.target.files[0], fileName, $('[name="'+name+'FileId"]')[0].value, element, name);
           return true;
       }
       $('#skIrisFile').val(null);
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
           formData.append("wilayahName", $('[name="wilayahName"]').val());
           formData.append("periode", $('[name="periode"]').val());
           formData.append("tahun", $('[name="tahun"]').val());
           formData.append("kategori", $('[name="kategori"]').val());



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
               url: '${uploadFileSkIrisCommandURL}',
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
                                  <a href="javascript:void(0);" onclick="deleteUploadedFile(this, '${fileID}')" title="Delete File"><i class="fas fa-trash"></i></a>
                               </div>
                               <div id="delete-loader" style="display: none;">
                                  <i class="fas fa-spinner anim-rotate"></i>
                               </div>
                            </div>
                         </div>
                  ` ;

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
       formData.append("authToken", Liferay.authToken);
       formData.append("file-id", fileID);
       let parentElement  = element.parentElement.parentElement.parentElement
       let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

       $.ajax({
           url : '${deleteFileSkIrisCommandURL}',
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

   function getFileKategori(){
       const kategori = [{id:"PSP",text:"PSP"},{id:"Non PSP",text:"Non PSP"}];
       const kategoriConst = $('#fileKategori').select2({
           data: kategori,
           tags: "true",
           placeholder: 'Pilih Kategori',
           allowClear: false,
           maximumInputLength: 100,
           templateSelection: function (data) {
               $('[name="kategori"]')[0].value = data.text;
               if (data.id === 'Pilih Kategori') {
                   return 'Pilih Kategori';
               }
               console.log(data);
               return data.text;
           }
       });
       console.log(kategori);
       if(action === "update") {
           kategoriConst.val(data.kategori); // Select the option with a value of '1'
       } else {
           kategoriConst.val(null);
       }
       kategoriConst.trigger('change');
   }
   function getBulanBaru() {
       $.ajax({
           url: "${bulanSkIrisURL}",
           type: "POST",
           data: {"windowsId": "test"},
           success: function (response) {
               var bulanData = JSON.parse(response);
               const periodeConst = $('#periodeId').select2({
                   data: bulanData.Data,
                   tags: "true",
                   placeholder: 'Pilih Bulan',
                   allowClear: false,
                   maximumInputLength: 100,
                   templateSelection: function (data) {
                       console.log(data);
                       $('[name="periode"]')[0].value = data.text;
                       if (data.id === 'Pilih Bulan') {
                           return 'Pilih Bulan';
                       }
                       return data.text;
                   }
               });
               if(action === "update") {
                   periodeConst.val(data.periode); // Select the option with a value of '1'
               } else {
                   periodeConst.val(null);
               }
               periodeConst.trigger('change');
           },
           error: function (error) {
               console.log(error);
           },
           complete: function (){
               console.log("complete");
           }
       });
   }

   function getWilayahs() {
       $.ajax({
           url: "${wilayahSkIrisURL}",
           type: "POST",
           data: {"wilayahId": "test"},
           success: function (response) {
               var wilayahData = JSON.parse(response);
               const wilayahConst = $('#wilayahId').select2({
                   data: wilayahData.Data,
                   tags: "true",
                   allowClear: false,
                   placeholder: 'Pilih Wilayah',
                   maximumInputLength: 100,
                   templateSelection: function (data) {
                       $('[name="wilayahId"]')[0].value = data.id;
                       $('[name="wilayahName"]')[0].value = data.text;
                       if (data.id === 'Pilih Wilayah') {
                           return 'Pilih Wilayah';
                       }
                       return data.text;
                   }
               });
               if(action === "update") {
                   wilayahConst.val(data.wilayahId); // Select the option with a value of '1'
               } else {
                   wilayahConst.val(null);
               }
               wilayahConst.trigger('change');
           },
           error: function (error) {
               console.log(error);
           },
           complete: function (){
               console.log("complete");
           }
       });
   }
   function getTahuns() {
       $.ajax({
           url: "${tahunSkIrisURL}",
           type: "POST",
           data: {"windowsId": "test"},
           success: function (response) {
               var tahunData = JSON.parse(response);
               const tahunActionConst = $('#tahunIdBuat').select2({
                   data: tahunData.Data,
                   tags: "true",
                   placeholder: 'Pilih Tahun',
                   allowClear: false,
                   maximumInputLength: 100,
                   templateSelection: function (data) {
                       $('[name="tahun"]')[0].value = data.text;
                       if (data.id === 'Pilih Tahun') {
                           return 'Pilih Tahun';
                       }
                       return data.text;
                   }
               });
               if(action === "update") {
                   tahunActionConst.val(data.tahun); // Select the option with a value of '1'
               } else {
                   tahunActionConst.val(null);
               }
               tahunActionConst.trigger('change');
           },
           error: function (error) {
               console.log(error);
           },
           complete: function (){
               console.log("complete");
           }
       });
   }
   function getTahunFilter() {
       $.ajax({
           url: "${tahunFilterSkIrisURL}",
           type: "POST",
           data: {"windowsId": "test"},
           success: function (response) {
               var tahunData = JSON.parse(response);
               const tahunConst = $('#tahunId').select2({
                   data: tahunData.Data,
                   placeholder: 'Pilih Tahun',
                   allowClear: false,
                   maximumInputLength: 100,
                   templateSelection: function (data) {
                       if(data.id !== '') {
                           tahun = data.id;
                           skIrisDataTable.ajax.reload();
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
           complete: function (){
               console.log("complete");
           }
       });
   }

   function getBulans() {
       $.ajax({
           url: "${bulanSkIrisURL}",
           type: "POST",
           data: {"windowsId": "test"},
           success: function (response) {
               var bulanData = JSON.parse(response);

               var bulanConst = $('#bulanId').select2({
                   data: bulanData.Data,
                   tags: "true",
                   placeholder: 'Pilih Periode',
                   allowClear: false,
                   maximumInputLength: 100,
                   templateSelection: function (data) {
                       if(data.id !== '') {
                           bulan = data.id;
                           skIrisDataTable.ajax.reload();
                       }
                       return data.text;
                   }
               });
               bulanConst.val(null);
               bulanConst.trigger('change');

               const periodeConst = $('#periodeId').select2({
                   data: bulanData.Data,
                   tags: "true",
                   placeholder: 'Pilih Bulan',
                   allowClear: false,
                   maximumInputLength: 100,
                   templateSelection: function (data) {
                       console.log(data);
                       $('[name="periode"]')[0].value = data.text;
                       if (data.id === 'Pilih Bulan') {
                           return 'Pilih Bulan';
                       }
                       return data.text;
                   }
               });
               if(action === "update") {
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

   function getDealers() {
       $.ajax({
           url: "${dealerSkIrisURL}",
           type: "POST",
           // params: {"dealerCode": data.dealerCode},
           data: {"windowsId": "test"},
           success: function (response) {
               var responseData = JSON.parse(response);
               console.log(responseData);
               console.log(response);
               const dealerConst = $('#dealerId').select2({
                   data: responseData.Data,
                   tags: "true",
                   placeholder: 'Pilih Dealer',
                   allowClear: false,
                   maximumInputLength: 100,
                   templateSelection: function (data) {
                       $('[name="dealerId"]')[0].value = data.id;
                       $('[name="dealerName"]')[0].value = data.text;
                       if (data.id === 'Pilih Dealer') {
                           return 'pilih Dealer';
                       }
                       return data.text;
                   }
               });
               if(action === "update") {
                   dealerConst.val(data.dealerId); // Select the option with a value of '1'
               } else {
                   dealerConst.val(null);
               }
               dealerConst.trigger('change');
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