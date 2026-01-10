<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/report-plafond/cms/style-css.jsp" %>

<portlet:resourceURL id="/report-plafond/list" var="reportPlafondListURL"/>
<portlet:resourceURL id="/report-plafond/action" var="reportPlafondActionURL"/>
<portlet:resourceURL id="/report-plafond/edit" var="reportPlafondEditURL"/>
<portlet:resourceURL id="/report-plafond/upload-file" var="uploadFileReportPlafondCommandURL"/>
<portlet:resourceURL id="/report-plafond/delete-file" var="deleteFileReportPlafondCommandURL"/>
<portlet:resourceURL id="/non-cms/dealers-with-details" var="dealerReportPlafondURL"/>

<div class="report_plafond cms-menu">
   <div class="tabcontent">
      <ul class="nav nav-tabs" id="cmsTab" role="tablist">
         <li class="nav-item">
            <a class="nav-link" id="buat_report_plafond" data-toggle="tab" href="#buat_report_plafond_tab" role="tab"
               aria-controls="buat_report_plafond" aria-selected="false">
               <span id="action-status"></span>
               Report Plafond
            </a>
         </li>
         <li class="nav-item">
            <a class="nav-link" id="list_report_plafond" data-toggle="tab" href="#list_report_plafond_tab" role="tab"
               aria-controls="list_report_plafond" aria-selected="false">
               Kelola Report Plafond
            </a>
         </li>
      </ul>
      <div class="tab-content" id="myTabContent">
         <div class="tab-pane fade" id="list_report_plafond_tab" role="tabpanel" aria-labelledby="profile-tab">
            <div class="row table-filters">
               <div class="col-xs-12 col-sm-12 col-md-3 dealerId">
                  <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;">
                     <option value="ALL">Select All</option>
                  </select>
               </div>
               <div class="col-xs-12 col-sm-12 col-md-3">
                  <div class="input-group">
                     <input type="text" onkeydown="return false" class="form-control dateIcon required date-input" name="periode-date" placeholder="Pilih Tanggal Faktur">
                     <span class="input-group-btn">
                        <button class="btn btn-info btn_table" onclick="refresh()">
                            <span><i class="icon-refresh"></i></span>
                        </button>
                    </span>
                  </div>
               </div>
            </div>
            <table id="report_plafond_table" class="table table-hover nowrap cms-table" style="width:100%">
               <thead>
                  <tr>
                     <th>No</th>
                     <th>Report Plafond</th>
                     <th>Periode Faktur</th>
                     <th>Tanggal Upload</th>
                     <th>Aksi</th>
                  </tr>
               </thead>
               <tbody></tbody>
            </table>
         </div>

         <div class="tab-pane fade" id="buat_report_plafond_tab" role="tabpanel" aria-labelledby="profile-tab">
            <form id="formreportPlafond" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
               <input type="hidden" name="entryId">
               <div class="container row form-field">
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="dealerIdBuat">
                        Kode Dealer
                        <span style="color: red;">*</span>
                     </label>
                     <select class="form-control" name="dealerIdBuat" id="dealerIdBuat" style="width: 100%;">
                        <option></option>
                     </select>
                  </div>
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="periode-date-buat">
                        Periode Faktur
                        <span style="color: red;">*</span>
                     </label>
                     <input type="hidden" name="periode">
                     <input type="text" onkeydown="return false" class="form-control dateIcon required date-input" name="periode-date-buat" id="periode-date-buat" placeholder="Pilih Tanggal Faktur">
                  </div>
                  <div class="form-group col-sm-9">
                     <label class="col-form-label ipr-color">
                        File Report
                        <span style="color: red;">*</span>
                     </label>
                     <div class="input-group upload-file-group">
                        <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                           <span class="icon-browse">
                              <img src="<%=request.getContextPath()%>/assets/img/file.svg">
                           </span>
                           <span class="text-browse">Pilih File</span>
                           <input type="file"
                                  accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                                  placeholder="" name="reportPlafondFile" id="reportPlafondFile" data-filename=""
                                  data-location="" style="display: none;" aria-invalid="false"
                                  class="materail_input valid">
                        </label>
                        <input type="text" class="form-control required" data-type="file" data-name="" name="reportPlafondFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
                        <input type="text" class="dpn" name="reportPlafondFileId"/>
                        <input type="hidden" class="dpn" name="reportPlafondFilePath"/>
                     </div>
                     <label class="col-form-label text-danger">
                        Format file .xlsx dengan maksimal ukuran file 1MB
                     </label>
                  </div>
                  <div class="form-group col-md-9">
                     <div class="action-line"></div>
                  </div>
                  <div class="form-group col-md-9 action-button">
                     <button class="btn-ipr" type="submit">Save</button>
                     <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="/group/dealink/cms-report-plafond">Cancel</a>
                  </div>
               </div>
            </form>
         </div>
      </div>
   </div>
</div>

<script>
   var dealerId = "ALL";
   var periode = "ALL";
   var role = "ALL";
   var data;
   var action = "";
   var startDate = new Date(1900, 0, 0);
   var homeUrl = "/group/dealink/cms-report-plafond";

   $(document).ready(function () {
      reloadForm();
      $('#action-status').html('Buat ');
      $('#list_report_plafond')[0].click();
      $('[data-toggle="tooltip"]').tooltip();
      action = "create";
      $('.dataTables_length select').select2({
         minimumResultsForSearch: -1
      });
   });

   function formNew() {
      $('[name="entryId"]')[0].value = "0";
      $('[name="dealerId"]')[0].value = "";
      $('[name="reportPlafondFileId"]')[0].value = "";
      $('[name="reportPlafondFileName"]')[0].value = "";
      $('[name="reportPlafondFilePath"]')[0].value = "";
      $('[name="periode"]')[0].value = "";
      $('[name="periode-date"]')[0].value = "";
   }

   function formEdit() {
      $('[name="entryId"]')[0].value = data.id;
      $('[name="dealerIdBuat"]')[0].value = data.dealerId;
      $('[name="reportPlafondFileId"]')[0].value = data.fileId;
      $('[name="reportPlafondFileName"]')[0].value = data.fileName;
      $('[name="reportPlafondFilePath"]')[0].value = data.filePath;

      // Date
      let eDate = "";
      if (data.periode !== "") {
         eDate = moment(data.periode).format('DD/MM/YYYY');
      }
      $('[name="periode-date-buat"]').val(eDate);
      $('[name="periode"]')[0].value = moment(data.periode).format('YYYY-MM-DD');
      setFakturDatepicker();
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
      getDealers();
      setFakturDatepicker();
      <%-- getDealersBuat(); --%>
   }

   function clearForm() {
      $('[name=periode-date]')[0].value = "";
      if (role === 'DSO') {
         dealerId = "ALL";
         periode = 'ALL';
      } else if (role === 'HO Dealer') {
         dealerId = "ALL";
         periode = "ALL";
      } else if (role === 'Dealer') {
         periode = "ALL";
      }
   }

   function refresh() {
      clearForm();
      periode = "ALL";
      reportPlafondDataTable.ajax.reload();
   }

   var reportPlafondTableLanguage = {
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

   var reportPlafondDataTable = $('#report_plafond_table').DataTable({
      searching: false,
      paging: true,
      info: false,
      language: reportPlafondTableLanguage,
      dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
      // serverSide: true,
      responsive: true,
      order: [],
      ajax: {
         url: '${reportPlafondListURL}',
         type: 'GET',
         data: function(filter) {
            filter.dealerId = dealerId;
            filter.periode = periode;
            filter.p_auth = Liferay.authToken;
         },
         dataSrc: function (json) {
            console.log(json.Data);
            return json.Data;
         }
      },
      columns: [
         {
            data: "no",
            width: "5%",
            className: "text-center"
         },
         {
            data: "fileName",
            width: "40%",
            className: "text-center"
         },
         {
            data: "periode",
            width: "25%",
            className: "text-center",
            render: function (data, type, row, meta) {
               if (type === "sort") {
                  return row.periodeSort;
               }
               return data;
            },
         },
         {
            data: "uploadDate",
            width: "25%",
            className: "text-center",
            render: function (data, type, row, meta) {
               if (type === "sort") {
                  return row.uploadDateSort;
               }
               return data;
            },
         },
         {
            data: "id",
            width: "5%",
            className: "text-center",
            render: function (data, type, row) {
               return renderActionButton(row.id, row.fileId);
            },
            orderable: false
         }
      ],
      columnDefs: [
         {
            targets: [2],
            orderData: [2, 3]
         }
      ]
   });

   function renderActionButton(dataId) {
      var response = "";
      response =
         '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a href="javascript:void(0)" onclick="updatereportPlafond(this)" data-id="' + dataId + '">' +
               '<span><img data-toogle="tooltip" title="Edit" src="<%=request.getContextPath()%>/assets/img/edit.svg"></img> </span>' +
            '</a>' +
            '<a href="javascript:void(0)" onclick="deletereportPlafond(this)" data-id="' + dataId + '">' +
               '<span><img data-toogle="tooltip" title="Delete" src="<%=request.getContextPath()%>/assets/img/trash.svg"></img></span>' +
            '</a>' +
         '</span>';
      return response;
   }

   function updatereportPlafond(element) {
      action = "update";
      $('#action-status').html('Edit ');
      $('#buat_report_plafond')[0].click();
      var entryId = $(element).data("id");
      var formData = new FormData();
      formData.append("entryId", entryId);
      $.ajax({
         url: "${reportPlafondEditURL}",
         type: "POST",
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            data = JSON.parse(response);
            console.log(response)
            console.log(data);
            formEdit();
            <%-- getDealersBuat(); --%>
            getDealers();
            reloadForm();
         }
      })
   }

   function deletereportPlafond(element) {
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
               url: "${reportPlafondActionURL}",
               type: "POST",
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var data = JSON.parse(response);
                  if (data["acknowledge"] === 0) {
                     Swal.fire("Successfully delete data", "", "success")
                        .then((res) => {
                           reportPlafondDataTable.ajax.reload();
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

   // Create Sales Program
   $("#reportPlafondFile").change(function (e) {
      processUpload(e, "reportPlafond", $(this));
   });
   var submitProcess = false;
   $("#formreportPlafond").submit(function (e) {
      e.preventDefault();
      const dealerId = $('[name="dealerId"]').val()
      const periode = $('[name="periode"]').val()
      const reportPlafondFileId = $('[name="reportPlafondFileId"]').val()
      if (submitProcess === false) {
         if (dealerId === null || dealerId === undefined || dealerId.length < 1 || dealerId === 'List Dealer') {
            Swal.fire("Kode Dealer belum terisi.", "", "warning");
         } else if (periode === null || periode === undefined || periode.length < 1) {
            Swal.fire("Periode Report belum terisi.", "", "warning");
         } else if (reportPlafondFileId === null || reportPlafondFileId === undefined || reportPlafondFileId.length < 1) {
            Swal.fire("File Report belum terisi.", "", "warning");
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.append("crudType", action)
            formData.append("entryId", $('[name="entryId"]').val());
            formData.append("dealerId", dealerId);
            formData.append("periode", periode);
            formData.append("reportPlafondFileId", reportPlafondFileId);
            formData.append("reportPlafondFileName", $('[name="reportPlafondFileName"]').val());
            formData.append("reportPlafondFilePath", $('[name="reportPlafondFilePath"]').val());

            Swal.fire({
               title: 'Apakah data ini sudah benar?',
               icon: 'question',
               showCancelButton: true,
               confirmButtonText: 'Yes'
            }).then((result) => {
               if (result.isConfirmed) {
                  createLoading();
                  $.ajax({
                     url: '${reportPlafondActionURL}',
                     type: 'POST',
                     data: formData,
                     processData: false,
                     contentType: false,
                     success: function (response) {
                        submitProcess = true;
                        var status = JSON.parse(response).status;
                        var code = JSON.parse(response).code;
                        var message = JSON.parse(response).message;
                        if (status === 'success') {
                           swal.fire("Success!", message, "success")
                              .then(function () {
                                    window.location = homeUrl;
                                 }
                              );
                        } else if (status === 'warning') {
                           swal.fire('Sorry', message, 'warning');
                        } else {
                           if (code === 409) {
                              swal.fire('Sorry', message, 'error');
                           } else {
                              swal.fire('Sorry', 'There is an internal error', 'error');
                           }
                        }

                        // console.log("response  : " + JSON.stringify(response));
                        // if (JSON.parse(response).status === 'success') {
                        //    swal.fire("Success", "Your request has been saved", "success")
                        //       .then(function () {
                        //             window.location = homeUrl;
                        //          }
                        //       );
                        // } else if (JSON.parse(response).status === 'warning') {
                        //    swal.fire('Sorry', JSON.parse(response).message, 'warning');
                        // } else {
                        //    swal.fire('Sorry', 'There is an internal error', 'error');
                        // }
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
            });
         }
      }
   });

   function processUpload(e, name, element) {
      const dealerId = $('[name="dealerId"]').val();
      const periode = $('[name="periode"]').val();
      const fileName = e.target.files[0].name;
      const fileFormat = fileName.split('.').pop();
      var format = false;
      if (fileFormat.toLowerCase() == 'xls' || fileFormat.toLowerCase() == 'xlsx') {
         format = true;
      }
      if (e.target.files[0].size > 1048576) {
         swal.fire("", "File Size cannot more than 1 MB", "warning");
      } else if (dealerId === null || dealerId === undefined || dealerId.length < 1 || dealerId === 'List Dealer') {
         Swal.fire("Kode Dealer belum terisi.", "", "warning");
      } else if (periode === null || periode === undefined || periode.length < 1) {
         Swal.fire("Periode Report belum terisi.", "", "warning");
      } else if (!format) {
         Swal.fire("", "The file must be in .xls or .xlsx format.", "warning");
      } else {
         let fileName = e.target.files[0].name;
         uploadDocumentFile(e.target.files[0], fileName, $('[name="' + name + 'FileId"]')[0].value, element, name);
         return true;
      }
      $('#reportPlafondFile').val(null);
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
         formData.set("<portlet:namespace/>dealerId", $('[name="dealerId"]').val());
         formData.set("<portlet:namespace/>periode", $('[name="periode"]').val());

         element.parents(".input-group").siblings(".upload-content").remove();
         element.val("");
         console.log(formData);

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
            url: '${uploadFileReportPlafondCommandURL}',
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
               console.log(data);
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
      let parentElement = element.parentElement.parentElement.parentElement;
      // let inputElement = $(parentElement1.siblings().find('input.form-control.required.switch-readonly'))[0];
      let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url: '${deleteFileReportPlafondCommandURL}',
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
         url: "${dealerReportPlafondURL}",
         type: "POST",
         data: {'windowsId': 'test',},
         success: function (response) {
            var responseData = JSON.parse(response);

            var viewDealerConst = $('#dealerId').select2({
               data: responseData.Data,
               tags: "true",
               placeholder: 'List Dealer',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  if (data.id !== '') {
                     dealerId = data.id;
                     reportPlafondDataTable.ajax.reload();
                  }
                  return data.text;
               }
            });
            viewDealerConst.val(null).trigger('change');

            const dealerConst = $('#dealerIdBuat').select2({
               data: responseData.Data,
               tags: "true",
               placeholder: 'List Dealer',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="dealerId"]')[0].value = data.id;
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

   function setFakturDatepicker() {
      $('[name=periode-date]').datepicker({
         forceParse: false,
         startDate: startDate,
         meridiem: '',
         format: 'dd/mm/yyyy',
         autoclose: true
      }).on('changeDate', function () {
         var date = $(this).datepicker('getDate');
         periode = moment(date).format("YYYY-MM-DD");
         reportPlafondDataTable.ajax.reload();
         $(this).datepicker('hide');
      });

      $('[name=periode-date-buat]').datepicker({
         forceParse: false,
         startDate: startDate,
         meridiem: '',
         format: 'dd/mm/yyyy',
         autoclose: true
      }).on('changeDate', function (ev) {
         let date = $(this).datepicker('getDate');
         var periode = moment(date).format("YYYY-MM-DD");
         $('[name="periode"]').val(periode);
         $(this).datepicker('hide');
      });
   }

</script>