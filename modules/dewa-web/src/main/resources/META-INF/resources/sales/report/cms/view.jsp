<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/sales/report/cms/style-css.jsp" %>

<portlet:resourceURL id="/sales-report-list" var="salesReportListURL"/>
<portlet:resourceURL id="/sales-report/action" var="salesReportActionURL"/>
<portlet:resourceURL id="/sales-report/edit" var="salesReportEditURL"/>
<portlet:resourceURL id="/sales-report/upload-file" var="uploadFileSalesReportCommandURL"/>
<portlet:resourceURL id="/sales-report/delete-file" var="deleteFileSalesReportCommandURL"/>
<portlet:resourceURL id="/non-cms/dealers-with-details" var="dealerURL"/>

<div class="sales_report cms-menu">
   <div class="tabcontent">
      <ul class="nav nav-tabs" id="cmsTab" role="tablist">
         <li class="nav-item">
            <a class="nav-link" id="buat_sales_report" data-toggle="tab" href="#buat_sales_report_tab" role="tab"
               aria-controls="buat_sp3d" aria-selected="false"><span id="action-status"></span>Sales Report</a>
         </li>
         <li class="nav-item">
            <a class="nav-link" id="list_sales_report" data-toggle="tab" href="#list_sales_report_tab" role="tab"
               aria-controls="list_sp3d" aria-selected="false">Kelola Sales Report</a>
         </li>
      </ul>
      <div class="tab-content" id="myTabContent">
         <div class="tab-pane fade" id="list_sales_report_tab" role="tabpanel" aria-labelledby="profile-tab">
            <div class="row table-filters">
               <div class="col-xs-12 col-sm-12 col-md-3 dealer-filter dealerId">
                  <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;">
                     <option value="ALL">Select All</option>
                  </select>
               </div>
               <div class="col-xs-12 col-sm-12 col-md-3">
                  <div class="input-group">
                     <input type="text" onkeydown="return false" class="form-control dateIcon required date-input" name="periode-date" placeholder="Pilih Tanggal Faktur" readonly>
                     <span class="input-group-btn">
                        <button class="btn btn-info btn_table" onclick="refresh()">
                           <span><i class="icon-refresh"></i></span>
                        </button>
                     </span>
                  </div>
               </div>
            </div>

            <table id="sales_report_table" class="table table-hover nowrap cms-table" style="width:100%">
               <thead>
               <tr>
                  <th>No</th>
                  <th>Sales Report</th>
                  <th>Periode Faktur</th>
                  <th>Tanggal Upload</th>
                  <th>Aksi</th>
               </tr>
               </thead>
               <tbody></tbody>
            </table>
         </div>

         <div class="tab-pane fade" id="buat_sales_report_tab" role="tabpanel" aria-labelledby="profile-tab">
            <form id="formSalesReport" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
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
                     <input type="text" onkeydown="return false"
                            class="form-control dateIcon required date-input"
                            name="periode-date-buat" id="periode-date-buat"
                            placeholder="Pilih Tanggal Faktur"
                            readonly>
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
                                  placeholder="" name="salesReportFile" id="salesReportFile" data-filename=""
                                  data-location="" style="display: none;" aria-invalid="false"
                                  class="materail_input valid">
                        </label>
                        <input type="text" class="form-control required" data-type="file" data-name=""
                               name="salesReportFileName" readonly
                               style="border: 1px solid #DBEDFF; background-color: white">
                        <input type="text" class="dpn" name="salesReportFileId"/>
                        <input type="hidden" class="dpn" name="salesReportFilePath"/>
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
                     <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="/group/dealink/cms-sales-report">Cancel</a>
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
   var homeUrl = "/group/dealink/cms-sales-report";

   $(document).ready(function () {
      reloadForm();
      $('#action-status').html('Buat ');
      $('#list_sales_report')[0].click();
      $('[data-toggle="tooltip"]').tooltip();
      action = "create";
      $('.dataTables_length select').select2({
         minimumResultsForSearch: -1
      });
   });

   function formNew() {
      $('[name="entryId"]')[0].value = "0";
      $('[name="dealerIdBuat"]')[0].value = "";
      $('[name="salesReportFileId"]')[0].value = "";
      $('[name="salesReportFileName"]')[0].value = "";
      $('[name="salesReportFilePath"]')[0].value = "";
      $('[name="periode"]')[0].value = "";
      $('[name="periode-date"]')[0].value = "";
   }

   function formEdit() {
      $('[name="entryId"]')[0].value = data.id;
      $('[name="dealerIdBuat"]')[0].value = data.dealerId;
      $('[name="salesReportFileId"]')[0].value = data.fileId;
      $('[name="salesReportFileName"]')[0].value = data.fileName;
      $('[name="salesReportFilePath"]')[0].value = data.filePath;

      // Date
      let eDate = ""
      if (data.periode !== "") {
         eDate = moment(data.periode).format('DD.MM.YYYY');
      }
      $('[name="periode-date-buat"]').val(eDate);
      $('[name="periode"]')[0].value = moment(data.periode).format('DD.MM.YYYY');
      setInvoiceDatePicker();
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
      setInvoiceDatePicker();
      getDealersBuat();
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
      salesReportDataTable.ajax.reload();
   }

   var salesReportTableLanguage = {
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

   var salesReportDataTable = $('#sales_report_table').DataTable({
      searching: false,
      paging: true,
      info: false,
      // serverSide: true,
      language: salesReportTableLanguage,
      dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
      ajax: {
         url: '${salesReportListURL}',
         type: 'GET',
         data: function (d) {
            d.dealerId = dealerId;
            d.periode = periode;
         },
         dataSrc: function (json) {
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
            render: function (data, type, row, meta) {
               return renderActionButton(data);
            },
            orderable: false
         }
      ],
      responsive: true,
      order: [],
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
         '<div class="action-wrapper" data-id="' + dataId + '">' +
            '<a href="javascript:void(0)" onclick="updateSalesReport(this)" data-id="' + dataId + '">' +
               '<span><img data-toogle="tooltip" title="Edit" alt="" src="<%=request.getContextPath()%>/assets/img/edit.svg"/></span>' +
            '</a>' +
            '<a href="javascript:void(0)" onclick="deleteSalesReport(this)" data-id="' + dataId + '">' +
               '<span><img data-toogle="tooltip" title="Delete" alt="" src="<%=request.getContextPath()%>/assets/img/trash.svg"/></span>' +
            '</a>' +
         '</div>';
      return response;
   }

   function updateSalesReport(element) {
      action = "update";
      $('#action-status').html('Edit ');
      $('#buat_sales_report')[0].click();
      var entryId = $(element).data("id");
      var formData = new FormData();
      formData.append("entryId", entryId);
      $.ajax({
         url: "${salesReportEditURL}",
         type: "POST",
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            data = JSON.parse(response);
            console.log(response)
            console.log(data);
            formEdit();
            reloadForm();
         }
      })
   }

   function deleteSalesReport(element) {
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
               url: "${salesReportActionURL}",
               type: "POST",
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var data = JSON.parse(response);
                  if (data["acknowledge"] === 0) {
                     Swal.fire("Successfully delete data", "", "success")
                        .then((res) => {
                           salesReportDataTable.ajax.reload();
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
   $("#salesReportFile").change(function (e) {
      processUpload(e, "salesReport", $(this));
   });
   var submitProcess = false;
   $("#formSalesReport").submit(function (e) {
      e.preventDefault();
      // const dealerId = $('[name="dealerId"]').val();
      const dealerId = $('[name="dealerIdBuat"]').val();
      const periode = $('[name="periode"]').val();
      const salesReportFileId = $('[name="salesReportFileId"]').val();
      if (submitProcess === false) {
         if (dealerId === null || dealerId === undefined || dealerId.length < 1 || dealerId === 'List Dealer') {
            Swal.fire("Kode Dealer belum terisi.", "", "warning");
         } else if (periode === null || periode === undefined || periode.length < 1) {
            Swal.fire("Periode Report belum terisi.", "", "warning");
         } else if (salesReportFileId === null || salesReportFileId === undefined || salesReportFileId.length < 1) {
            Swal.fire("File Report belum terisi.", "", "warning");
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.set("crudType", action)
            formData.set("entryId", $('[name="entryId"]').val());
            formData.set("dealerId", dealerId);
            formData.set("dealerName", $('[name="dealerName"]').val());
            formData.set("periode", periode);
            formData.set("salesReportFileId", salesReportFileId);
            formData.set("salesReportFileName", $('[name="salesReportFileName"]').val());
            formData.set("salesReportFilePath", $('[name="salesReportFilePath"]').val());

            createLoading();
            $.ajax({
               url: '${salesReportActionURL}',
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


   function processUpload(e, name, element) {
      const dealerId = $('[name="dealerId"]').val();
      const periode = $('[name="periode"]').val();
      const fileName = e.target.files[0].name;
      const fileFormat = fileName.split('.').pop();
      var format = false;
      if (fileFormat.toLowerCase() === "xlsx" || fileFormat.toLowerCase() === "xls") {
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
      $('#salesReportFile').val(null);
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
            url: '${uploadFileSalesReportCommandURL}',
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
      let parentElement = element.parentElement.parentElement.parentElement
      // let inputElement = $(parentElement1.siblings().find('input.form-control.required.switch-readonly'))[0];
      let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url: '${deleteFileSalesReportCommandURL}',
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
         url: "${dealerURL}",
         type: "POST",
         data: {"dealerId": "test"},
         success: function (response) {
            var responseData = JSON.parse(response);
            var dealerConst = $('#dealerId').select2({
               data: responseData.Data,
               tags: "true",
               placeholder: 'List Dealer',
               allowClear: false,
               maximumInputLength: 100,

               templateSelection: function (data) {
                  if (data.id !== '') {
                     dealerId = data.id;
                     salesReportDataTable.ajax.reload();
                  }
                  return data.text;
               }
            });
            dealerConst.val(null);
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

   function getDealersBuat() {
      $.ajax({
         url: "${dealerURL}",
         type: "POST",
         data: {"windowsId": "test"},
         success: function (response) {
            var responseData = JSON.parse(response);
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

   function setInvoiceDatePicker() {
      $('[name="periode-date-buat"]').datetimepicker({
         format: 'DD.MM.YYYY',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: false
      }).on('dp.change', function (e) {
         $(this).data('DateTimePicker').hide();
         var selectedDate = e.date;
         $('[name="periode"]').val(selectedDate.format('DD.MM.YYYY'));
      });

      $('[name="periode-date"]').datetimepicker({
         format: 'DD.MM.YYYY',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: false
      }).on('dp.change', function (e) {
         $(this).data('DateTimePicker').hide();
         var selectedDate = e.date;
         periode = selectedDate.format('DD.MM.YYYY');
         salesReportDataTable.ajax.reload();
      });

      $('[name^=periode-date]').on('click', function() {
         $(this).data('DateTimePicker').show();
      });
   }
</script>