<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/faktur-pajak/style-css.jsp" %>

<portlet:resourceURL id="/faktur-pajak-list" var="fakturPajakListURL"/>
<portlet:resourceURL id="/faktur-pajak-action" var="fakturPajakActionURL"/>
<portlet:resourceURL id="/faktur-pajak-edit" var="fakturPajakEditURL"/>
<portlet:resourceURL id="/upload-file-faktur-pajak" var="uploadFileFakturPajakCommandURL"/>
<portlet:resourceURL id="/delete-file-faktur-pajak" var="deleteFileFakturPajakCommandURL"/>
<portlet:resourceURL id="/dealer-faktur-pajak" var="dealerFakturPajakURL"/>

<div class="faktur_pajak cms-menu">
   <div class="tabcontent">
      <ul class="nav nav-tabs" id="cmsTab" role="tablist">
         <li class="nav-item">
            <a class="nav-link" id="create_faktur_pajak_navtab" data-toggle="tab" href="#create_faktur_pajak_tab" role="tab" aria-controls="create_faktur_pajak_tab" aria-selected="false">
               <span id="action-status"></span>
               Faktur Pajak
            </a>
         </li>
         <li class="nav-item">
            <a class="nav-link" id="kelola_faktur_pajak_navtab" data-toggle="tab" href="#kelola_faktur_pajak_tab" role="tab" aria-controls="kelola_faktur_pajak_tab" aria-selected="false">
               Kelola Faktur Pajak
            </a>
         </li>
      </ul>
      <div class="tab-content" id="myTabContent">
         <div class="tab-pane fade" id="kelola_faktur_pajak_tab" role="tabpanel" aria-labelledby="profile-tab">
            <div class="row table-filters">
               <div class="col-xs-12 col-md-3 dealerId">
                  <select class="form-control" name="viewDealerId" id="viewDealerId" style="width: 100%;">
                     <option value="ALL">Select All</option>
                  </select>
               </div>
               <div class="col-xs-12 col-md-3">
                  <div class="input-group">
                     <input type="text" onkeydown="return false" class="form-control dateIcon required" name="invoice-date-view" placeholder="Pilih Tanggal Faktur">
                     <span class="input-group-btn">
                        <button class="btn btn-info btn_table" onclick="refresh()">
                           <span><i class="icon-refresh"></i></span>
                        </button>
                     </span>
                  </div>
               </div>
            </div>
            <table id="faktur_pajak_table" class="table table-hover nowrap cms-table" style="width:100%">
               <thead>
                  <tr>
                     <th>No</th>
                     <th>Faktur Pajak</th>
                     <th>Periode Faktur</th>
                     <th>Tanggal Upload</th>
                     <th>Aksi</th>
                  </tr>
               </thead>
               <tbody></tbody>
            </table>
         </div>
         <div class="tab-pane fade" id="create_faktur_pajak_tab" role="tabpanel" aria-labelledby="profile-tab">
            <form id="formFakturPajak" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
               <div class="container row form-field">
                  <input type="hidden" name="entryId">
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="dealerId">
                        Kode Dealer
                        <span class="text-danger">*</span>
                     </label>
                     <input type="hidden" class="form-control" name="dealerId"/>
                     <select class="form-control" id="dealerId" style="width: 100%;">
                        <%-- <option value="NULL">List Dealer</option>--%>
                     </select>
                  </div>
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="invoice-date-picker">
                        Tanggal Faktur
                        <span class="text-danger">*</span>
                     </label>
                     <input type="hidden" name="invoice-date-input">
                     <input type="text" onkeydown="return false" class="form-control dateIcon required" name="invoice-date-picker" id="invoice-date-picker" placeholder="Pilih Tanggal Faktur">
                  </div>
                  <div class="form-group col-sm-9">
                     <label class="col-form-label ipr-color">
                        File Report
                        <span class="text-danger">*</span>
                     </label>
                     <div class="input-group upload-file-group">
                        <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                           <span class="icon-browse">
                              <img src="<%=request.getContextPath()%>/assets/img/file.svg">
                           </span>
                           <span class="text-browse">Pilih File</span>
                           <input type="file" accept="application/pdf" placeholder="" name="fakturPajakFile"
                                  id="fakturPajakFile" data-filename="" data-location="" style="display: none;"
                                  aria-invalid="false" class="materail_input valid">
                        </label>
                        <input type="text" class="form-control required" data-type="file" data-name="" name="fakturPajakFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
                        <input type="text" class="dpn" name="fakturPajakFileId">
                        <input type="text" class="dpn" name="fakturPajakFilePath">
                     </div>
                     <label class="col-form-label text-danger" id="file-restriction-text">
                        Format file .pdf dengan maksimal ukuran file 1MB
                     </label>
                  </div>
                  <div class="form-group col-md-9">
                     <div class=" action-line"></div>
                  </div>
                  <div class="form-group col-md-9 action-button">
                     <button class="btn-ipr" type="submit">Simpan</button>
                     <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="javascript:void(0)">Batal</a>
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
   var data = null;
   var action = null;
   var homeUrl = "/group/dealink/cms-faktur-pajak";
   var viewUrl = "/group/dealink/preview-document";
   var startDate = new Date(1900, 0, 0)

   function formNew() {
      $('[name="entryId"]')[0].value = "0";
      $('[name="dealerId"]')[0].value = "";
      $('[name="invoice-date-picker"]')[0].value = "";
      $('[name="fakturPajakFileId"]')[0].value = "";
      $('[name="fakturPajakFileName"]')[0].value = "";
      $('[name="fakturPajakFilePath"]')[0].value = "";
   }

   function formEdit() {
      $('[name="entryId"]')[0].value = data.id;
      $('[name="dealerId"]')[0].value = data.dealerId;
      $('[name="fakturPajakFileId"]')[0].value = data.fileId;
      $('[name="fakturPajakFileName"]')[0].value = data.fileName;
      $('[name="fakturPajakFilePath"]')[0].value = data.filePath;
      $('[name="invoice-date-picker"]')[0].value = data.invoiceDate;
   }

   function refresh() {
      dealerId = "ALL";
      periode = "ALL";
      fakturPajakDataTable.ajax.reload();
      $('[name=invoice-date-view]')[0].value = "";
      $('#viewDealerId')[0].value = "";
      getDealers();
   }

   $("#backButtonDocFlow").click(function (e) {
      refreshForm();
   })

   function refreshForm() {
      action = "create";
      $('#action-status').html('Buat');
      $('#delete-file-button').find('a').click();
      $('#kelola_faktur_pajak_navtab')[0].click();
      $('#upload-status').hide();
      $('#file-restriction-text').show();
      formNew();
      getDealers();
   }

   $("#fakturPajakFile").change(function (e) {
      processUpload(e, "fakturPajak", $(this));
   });

   function processUpload(e, name, element) {
      const dealerId = $('[name="dealerId"]').val();
      const invoiceDate = $('[name="invoice-date-picker"]').val();
      let fileName = e.target.files[0].name;
      let fileSize = e.target.files[0].size;
      if (fileSize > 1048576) {
         swal.fire("Ukuran file maksimal 1MB!", "", "warning");
      } else if (dealerId === null || dealerId === undefined || dealerId.length < 1 || dealerId === "Pilih Dealer") {
         Swal.fire("Kode Dealer belum terisi.", "", "warning");
      } else if (invoiceDate === null || invoiceDate === undefined || invoiceDate.length < 1) {
         Swal.fire("Tanggal faktur belum terisi.", "", "warning");
      } else if (!(fileName && fileName.endsWith('.pdf'))) {
         Swal.fire("File harus .pdf!", "", "warning");
      } else {
         uploadDocumentFile(e.target.files[0], fileName, $('[name="' + name + 'FileId"]')[0].value, element, name);
         return true;
      }
      $('#fakturPajakFile').val(null);
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
         formData.set("<portlet:namespace/>invoiceDate", $('[name="invoice-date-picker"]').val());

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
            url: '${uploadFileFakturPajakCommandURL}',
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
                     <div id="upload-status" style="margin-top: -8px;margin-bottom: 15px;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
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
               $('#file-restriction-text').hide();
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
         url: '${deleteFileFakturPajakCommandURL}',
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
            $('[name="fakturPajakFileId"]')[0].value = null;
            $('#file-restriction-text').show();
            console.log("DONE");
         }
      });
   }

   var submitProcess = false;
   $("#formFakturPajak").submit(function (e) {
      e.preventDefault();
      const dealerId = $('[name="dealerId"]').val();
      const invoiceDate = $('[name="invoice-date-picker"]').val();
      const fakturPajakFileId = $('[name="fakturPajakFileId"]').val();
      if (submitProcess === false) {
         if (dealerId === null || dealerId === undefined || dealerId.length < 1) {
            Swal.fire("Kode Dealer belum terisi.", "", "warning");
         } else if (invoiceDate === null || invoiceDate === undefined || invoiceDate.length < 1) {
            Swal.fire("Tanggal faktur belum terisi.", "", "warning");
         } else if (fakturPajakFileId === null || fakturPajakFileId === undefined || fakturPajakFileId.length < 1) {
            Swal.fire("File Report belum terisi.", "", "warning");
         } else {
            var formData = new FormData(this);
            formData.set("<portlet:namespace/>crudType", action);
            formData.set("<portlet:namespace/>entryId", $('[name="entryId"]').val());
            formData.set("<portlet:namespace/>dealerId", dealerId);
            formData.set("<portlet:namespace/>invoiceDate", invoiceDate);
            formData.set("<portlet:namespace/>fakturPajakFileId", fakturPajakFileId);
            formData.set("<portlet:namespace/>fakturPajakFileName", $('[name="fakturPajakFileName"]').val());
            formData.set("<portlet:namespace/>fakturPajakFilePath", $('[name="fakturPajakFilePath"]').val());
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            Swal.fire({
               title: 'Apakah data ini sudah benar?',
               icon: 'question',
               showCancelButton: true,
               confirmButtonText: 'Yes'
            }).then((result) => {
               if (result.isConfirmed) {
                  createLoading();
                  $.ajax({
                     url: '${fakturPajakActionURL}',
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
                  });
               }
            });
         }
      }
   });

   var languageDiskonFakpolTable = {
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

   var fakturPajakDataTable = $('#faktur_pajak_table').DataTable({
      searching: false,
      paging: true,
      info: false,
      ajax: {
         url: '${fakturPajakListURL}',
         type: 'GET',
         data: function (d) {
            d.p_auth = Liferay.authToken;
            d.dealerId = dealerId;
            d.invoiceDate = periode;
         },
         dataSrc: function (json) {
            return json.Data;
         }
      },
      language: languageDiskonFakpolTable,
      columns: [
         {data: "no", width: "5%", className: "text-center"},
         {data: "fileName", width: "30%"},
         {
            data: "invoiceDate",
            width: "30%",
            className: "text-center",
            render: function (data, type, row, meta) {
               if (type === "sort") {
                  return row.invoiceDateSort;
               }
               return data;
            }
         },
         {
            data: "uploadDate",
            width: "30%",
            className: "text-center",
            render: function (data, type, row, meta) {
               if (type === "sort") {
                  return row.uploadDateSort;
               }
               return data;
            }
         },
         {
            data: "id",
            width: "5%",
            className: "text-center",
            render: function (data, type, row, meta) {
               return renderActionButton(data, row);
            },
            orderable: false
         }
      ],
      responsive: true,
      order: [],
      columnDefs: [
         {
            targets: [2],
            orderData: [2, 1]
         }
      ],
      dom: '<"row"<"col-sm-12 cms-table-length"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>'
      <%-- dom: '<"row"<"col-md-6"l><"col-md-6"f>><"row"<"col-md-12"t>><"row"<"col-md-12"ip>>' --%>
   });

   function renderActionButton(dataId, row) {
      var response = "";
      response =
         '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a href="javascript:void(0)" onclick="updateFakturPajak(this)" data-id="' + dataId + '">' +
               '<span><img data-toggle="tooltip" title="Edit" src="<%=request.getContextPath()%>/assets/img/edit.svg"></img></span>' +
            '</a>' +
            '<a href="javascript:void(0)" onclick="deleteFakturPajak(this)" data-id="' + dataId + '">' +
               '<span><img data-toggle="tooltip" title="Hapus" src="<%=request.getContextPath()%>/assets/img/trash.svg"></img></span>' +
            '</a>' +
            '<a href="' + viewUrl + '?id=' + row.fileId + '">' +
               '<span><img data-toggle="tooltip" title="Preview" src="<%=request.getContextPath()%>/assets/img/eye.svg"></img></span>' +
            '</a>' +
         '</span>';
      return response;
   }

   function updateFakturPajak(element) {
      action = "update";
      $('#action-status').html('Edit');
      var entryId = $(element).data("id");
      var formData = new FormData();
      formData.set("<portlet:namespace/>entryId", entryId);
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      createLoading();
      $.ajax({
         url: "${fakturPajakEditURL}",
         type: "POST",
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            data = JSON.parse(response);
            const oneYearAgo = new Date().getFullYear() - 1;
            if (data.year < oneYearAgo) {
               Swal.fire({
                  icon: 'error',
                  title: 'Dokumen tidak bisa diedit!',
                  confirmButtonText: 'Back',
                  text: 'Tidak bisa edit dokumen satu tahun ke belakang.',
                  allowOutsideClick: false
               });
            } else {
               formEdit();
               getDealers();
               $('#create_faktur_pajak_navtab')[0].click();
            }
         },
         error: function (error) {
            Swal.fire({
               icon: 'error',
               title: 'Oops...',
               text: 'Something went wrong!',
            });
         },
         complete: function () {
            destroyLoading();
         }
      });
   }

   function deleteFakturPajak(element) {
      let entryId = $(element).data("id");
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
               url: "${fakturPajakActionURL}",
               type: "POST",
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var data = JSON.parse(response);
                  if (data["acknowledge"] === 0) {
                     Swal.fire("Data berhasil dihapus", "", "success")
                        .then((res) => {
                           fakturPajakDataTable.ajax.reload();
                        });
                  } else {
                     Swal.fire("Gagal menghapus data", data["message"], "");
                  }
               },
               error: function (err) {
                  console.log(err);
               }
            });
         }
      });
   }

   function getDealers() {
      $.ajax({
         url: "${dealerFakturPajakURL}",
         type: "POST",
         data: {"windowsId": "test"},
         success: function (response) {
            var responseData = JSON.parse(response);

            var viewDealerConst = $('#viewDealerId').select2({
               data: responseData.Data,
               tags: "true",
               placeholder: 'Pilih Dealer',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  if (data.id !== '') {
                     dealerId = data.id;
                     fakturPajakDataTable.ajax.reload();
                  }
                  return data.text;
               }
            }).tooltip('disable');
            viewDealerConst.val(null);
            viewDealerConst.trigger('change');

            var dealerConst = $('#dealerId').select2({
               data: responseData.Data,
               tags: "true",
               placeholder: 'Pilih Dealer',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="dealerId"]')[0].value = data.id;
                  if (data.id === 'Pilih Dealer') {
                     return 'Pilih Dealer';
                  }
                  return data.text;
               }
            }).tooltip("disable");
            if (action === "update") {
               dealerConst.val(data.dealerId);
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

   function setInvoiceDate() {
      $('#invoice-date-picker').datetimepicker({
         format: 'DD.MM.YYYY',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: false
      }).on('dp.hide', function (e) {
         // console.log('invoice date: ' + e.date);
      });
   }

   function setFakturDatepicker() {
      $('[name=invoice-date-view]').datepicker({
         forceParse: false,
         startDate: startDate,
         meridiem: '',
         format: 'dd.mm.yyyy',
         autoclose: true
      }).on('changeDate', function (ev) {
         let date = $(this).datepicker('getDate');
         periode = moment(date).format("DD.MM.YYYY");
         fakturPajakDataTable.ajax.reload();
         $(this).datepicker('hide');
      });
   }

   $(document).ready(function () {
      getDealers();
      formNew();
      setInvoiceDate();
      setFakturDatepicker();
      action = "create";
      $('#action-status').html('Buat');
      $('#kelola_faktur_pajak_navtab')[0].click();
      $('[data-toggle="tooltip"]').tooltip();

      $('.dataTables_length select').select2({
         minimumResultsForSearch: -1
      });
   });
</script>