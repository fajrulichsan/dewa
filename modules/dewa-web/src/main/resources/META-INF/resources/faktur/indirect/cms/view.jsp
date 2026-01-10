<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/faktur/indirect/cms/style-css.jsp" %>

<portlet:resourceURL id="/faktur-indirect/list" var="fakturIndirectListURL"/>
<portlet:resourceURL id="/faktur-indirect/action" var="fakturIndirectActionURL"/>
<portlet:resourceURL id="/faktur-indirect/edit" var="fakturIndirectEditURL"/>
<portlet:resourceURL id="/faktur-indirect/upload-file" var="uploadFileFakturIndirectCommandURL" />
<portlet:resourceURL id="/faktur-indirect/delete-file" var="deleteFileFakturIndirectCommandURL" />
<portlet:resourceURL id="/non-cms/dealers-with-details" var="dealerListURL"/>

<div class="faktur_indirect cms-menu">
   <div class="tabcontent">
      <ul class="nav nav-tabs" id="cmsTab" role="tablist">
         <li class="nav-item">
            <a class="nav-link" id="create_faktur_indirect_navtab" data-toggle="tab" href="#create_faktur_indirect_tab" role="tab" aria-controls="create_faktur_indirect_tab" aria-selected="false">
               <span id="action-status"></span>
               Faktur Indirect
            </a>
         </li>
         <li class="nav-item">
            <a class="nav-link" id="kelola_faktur_indirect_navtab" data-toggle="tab" href="#kelola_faktur_indirect_tab" role="tab" aria-controls="kelola_faktur_indirect_tab" aria-selected="false">
               Kelola Faktur Indirect
            </a>
         </li>
      </ul>

      <div class="tab-content" id="myTabContent">
         <div class="tab-pane fade" id="kelola_faktur_indirect_tab" role="tabpanel" aria-labelledby="profile-tab">
            <div class="row table-filters">
               <div class="col-xs-12 col-md-3 dealerId">
                  <select class="form-control" name="viewDealerId" id="viewDealerId" style="width: 100%;">
                     <option value="ALL">Select All</option>
                  </select>
               </div>
               <div class="col-xs-12 col-md-3">
                  <div class="input-group">
                     <input type="text" onkeydown="return false" class="form-control dateIcon required date-input" name="invoice-date-view" placeholder="Pilih Tanggal Faktur" readonly>
                     <span class="input-group-btn">
                        <button class="btn btn-info btn_table" onclick="refresh()">
                           <span><i class="icon-refresh"></i></span>
                        </button>
                     </span>
                  </div>
               </div>
            </div>

            <table id="faktur_indirect_table" class="table table-hover nowrap cms-table" style="width:100%">
               <thead>
                  <tr>
                     <th>No</th>
                     <th>Faktur Indirect</th>
                     <th>Periode Faktur</th>
                     <th>Tanggal Upload</th>
                     <th>Aksi</th>
                  </tr>
               </thead>
               <tbody></tbody>
            </table>
         </div>
         <div class="tab-pane fade" id="create_faktur_indirect_tab" role="tabpanel" aria-labelledby="profile-tab">
            <form id="formFakturIndirect" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
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
                     <input type="text" onkeydown="return false" class="form-control dateIcon required date-input" name="invoice-date-picker" id="invoice-date-picker" placeholder="Pilih Tanggal Faktur" readonly>
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
                           <input type="file" accept="application/pdf" placeholder="" name="fakturIndirectFile" id="fakturIndirectFile" data-filename="" data-location="" style="display: none;" aria-invalid="false" class="materail_input valid">
                        </label>
                        <input type="text" class="form-control required" data-type="file" data-name="" name="fakturIndirectFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
                        <input type="text" class="dpn" name="fakturIndirectFileId" >
                        <input type="hidden" class="dpn" name="fakturIndirectFilePath" >
                     </div>
                     <label class="col-form-label text-danger" id="file-restriction-text">
                        Format file .pdf dengan maksimal ukuran file 1MB
                     </label>
                  </div>
                  <div class="form-group col-md-9">
                     <div class="action-line"></div>
                  </div>
                  <div class="form-group col-md-9 action-button">
                     <button class="btn-ipr" type="submit">Save</button>
                     <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="javascript:void(0)">Cancel</a>
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
   var homeUrl = "/group/dealink/cms-faktur-indirect";
   var viewUrl = "/group/dealink/preview-document";
   var startDate = new Date(1900, 0, 0);

   $(document).ready(function () {
      getDealers();
      formNew();
      setInvoiceDate();
      setFakturDatepicker();
      action = "create";
      $('#action-status').html('Buat');
      $('#kelola_faktur_indirect_navtab')[0].click();
      $('[data-toggle="tooltip"]').tooltip();
      $('.dataTables_length select').select2({
         minimumResultsForSearch: -1
      });
   });

   function formNew() {
      $('[name="entryId"]')[0].value = "0";
      $('[name="dealerId"]')[0].value = "";
      $('[name="invoice-date-picker"]')[0].value = "";
      $('[name="fakturIndirectFileId"]')[0].value = "";
      $('[name="fakturIndirectFileName"]')[0].value = "";
      $('[name="fakturIndirectFilePath"]')[0].value = "";
   }

   function formEdit() {
      $('[name="entryId"]')[0].value = data.id;
      $('[name="dealerId"]')[0].value = data.dealerId;
      $('[name="invoice-date-picker"]')[0].value = data.invoiceDate;
      $('[name="fakturIndirectFileId"]')[0].value = data.fileId;
      $('[name="fakturIndirectFileName"]')[0].value = data.fileName;
      $('[name="fakturIndirectFilePath"]')[0].value = data.filePath;
   }

   function refresh() {
      dealerId = "ALL";
      periode = "ALL";
      fakturIndirectDataTable.ajax.reload();
      $('#viewDealerId')[0].value = "";
      $('[name="invoice-date-view"]')[0].value = "";
      getDealers();
   }

   $("#backButtonDocFlow").click(function (e) {
      refreshForm();
   })

   function refreshForm() {
      action = "create";
      $('#action-status').html('Buat');
      $('#delete-file-button').find('a').click();
      $('#kelola_faktur_indirect_navtab')[0].click();
      $('#upload-status').hide();
      $('#file-restriction-text').show();
      formNew();
      getDealers();
   }

   $("#fakturIndirectFile").change(function(e) {
      processUpload(e, "fakturIndirect", $(this));
   });

   function processUpload(e, name, element) {
      const dealerId = $('[name="dealerId"]').val();
      const invoiceDate = $('[name="invoice-date-picker"]').val();
      let fileName = e.target.files[0].name;
      let fileSize = e.target.files[0].size;
      if (fileSize > 1048576) {
         swal.fire("Ukuran file maksimal 1MB!", "", "warning");
      } else if (dealerId === null || dealerId === undefined || dealerId.length < 1) {
         Swal.fire("Kode Dealer belum terisi.", "", "warning");
      } else if (invoiceDate === null || invoiceDate === undefined || invoiceDate.length < 1) {
         Swal.fire("Tanggal faktur belum terisi.", "", "warning");
      } else if (!(fileName.endsWith('.pdf'))) {
         Swal.fire("File harus .pdf!", "", "warning");
      } else {
         uploadDocumentFile(e.target.files[0], fileName, $('[name="'+name+'FileId"]')[0].value, element, name);
         return true;
      }
      $('#fakturIndirectFile').val(null);
   }

   function uploadDocumentFile(file, fileName, fileID, element, name) {
      let formData = new FormData();
      let format = /[:"\\|,<>\/?^*]/;
      if (format.test(fileName)) {
         swal.fire('', 'File name cannot contains special character ,^*:"|<>\\ /?', 'warning')
      } else {
         fileName = fileName.replaceAll(",", "");
         formData.set('<portlet:namespace/>p_auth', Liferay.authToken);
         formData.set('<portlet:namespace/>file-upload', file);
         formData.set('<portlet:namespace/>file-name', fileName);
         formData.set('<portlet:namespace/>file-id', fileID);
         formData.set('<portlet:namespace/>document-id', $('[name="document-id"]').val());
         formData.set('<portlet:namespace/>document-id-new',$('[name="document-id-new"]').val());
         formData.set('<portlet:namespace/>dealerId', $('[name="dealerId"]').val());
         formData.set('<portlet:namespace/>invoiceDate', $('[name="invoice-date-picker"]').val());

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
            url: '${uploadFileFakturIndirectCommandURL}',
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
               console.log(data);

               if (acknowledge === 1) {
                  $(elementEdit.parent().siblings('[name="'+nameEdit+'FileId"]'))[0].value=fileID;
                  $(elementEdit.parent().siblings('[name="'+nameEdit+'FileName"]'))[0].value=fileName;
                  $(elementEdit.parent().siblings('[name="'+nameEdit+'FilePath"]'))[0].value=fileURL;

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
      formData.set('<portlet:namespace/>p_auth', Liferay.authToken);
      formData.set('<portlet:namespace/>file-id', fileID);
      let parentElement  = element.parentElement.parentElement.parentElement
      // let inputElement = $(parentElement1.siblings().find('input.form-control.required.switch-readonly'))[0];
      let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url : '${deleteFileFakturIndirectCommandURL}',
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
            $('[name="fakturIndirectFileId"]')[0].value = null;
            $('#file-restriction-text').show();
            console.log("DONE");
         }
      });
   }

   var submitProcess = false;
   $("#formFakturIndirect").submit(function (e) {
      e.preventDefault();
      const dealerId = $('[name="dealerId"]').val();
      const invoiceDate = $('[name="invoice-date-picker"]').val();
      const fakturIndirectFileId = $('[name="fakturIndirectFileId"]').val();
      if (submitProcess === false) {
         if (dealerId === null || dealerId === undefined || dealerId.length < 1) {
            Swal.fire("Kode Dealer belum terisi.", "", "warning");
         } else if (invoiceDate === null || invoiceDate === undefined || invoiceDate.length < 1) {
            Swal.fire("Tanggal faktur belum terisi.", "", "warning");
         } else if (fakturIndirectFileId === null || fakturIndirectFileId === undefined || fakturIndirectFileId.length < 1) {
            Swal.fire("File Report belum terisi.", "", "warning");
         } else {
            var formData = new FormData(this);
            formData.set('<portlet:namespace/>crudType', action);
            formData.set('<portlet:namespace/>p_auth', Liferay.authToken);
            formData.set('<portlet:namespace/>entryId', $('[name="entryId"]').val());
            formData.set('<portlet:namespace/>dealerId', dealerId);
            formData.set('<portlet:namespace/>invoiceDate', invoiceDate);
            formData.set('<portlet:namespace/>fakturIndirectFileId', fakturIndirectFileId);
            formData.set('<portlet:namespace/>fakturIndirectFileName', $('[name="fakturIndirectFileName"]').val());
            formData.set('<portlet:namespace/>fakturIndirectFilePath', $('[name="fakturIndirectFilePath"]').val());

            Swal.fire({
               title: 'Apakah data ini sudah benar?',
               icon: 'question',
               showCancelButton: true,
               confirmButtonText: 'Yes'
            }).then((result) => {
               if (result.isConfirmed) {
                  createLoading();
                  $.ajax({
                     url: '${fakturIndirectActionURL}',
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
                           swal.fire(message, "", "success")
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

   var fakturIndirectTableLanguage = {
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

   var fakturIndirectDataTable = $('#faktur_indirect_table').DataTable({
      searching: false,
      paging: true,
      info: false,
      serverSide: true,
      processing: true,
      dom: '<"row"<"col-sm-12 cms-table-length"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
      ajax: {
         url: '${fakturIndirectListURL}',
         type: 'GET',
         data: function(d) {
            d.dealerId = dealerId;
            d.invoiceDate = periode;
            d.p_auth = Liferay.authToken;
         },
         dataSrc: function (json) {
            return json.data;
            // return json.Data;
         }
      },
      language: fakturIndirectTableLanguage,
      columns: [
         {
            data: "no",
            width: "5%",
            className: "text-center",
            orderable: false
         },
         {
            data: "fileName",
            width: "40%"
         },
         {
            data: "invoiceDate",
            width: "25%",
            className: "text-center",
            render: function(data, type, row, meta) {
               if (type === "sort") {
                  return row.invoiceDateSort;
               }
               return data;
            }
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
            }
         },
         {
            data: "id",
            width: "5%",
            render: function (data, type, row, meta) {
               return renderActionButton(data, row.fileId);
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
      ],
   });

   function renderActionButton(dataId, fileId) {
      var response = "";
      response =
         '<div class="action-wrapper" data-id="' + dataId + '">' +
            '<a href="javascript:void(0)" onclick="updateFakturIndirect(this)" data-id="' + dataId + '"' +
               '<span><img data-toggle="tooltip" title="Edit" alt="" src="<%=request.getContextPath()%>/assets/img/edit.svg"/></span>' +
            '</a>' +
            '<a href="javascript:void(0)" onclick="deleteFakturIndirect(this)" data-id="' + dataId + '"' +
               '<span><img data-toggle="tooptip" title="Hapus" alt="" src="<%=request.getContextPath()%>/assets/img/trash.svg"/></span>' +
            '</a>' +
            '<a href="' + viewUrl +'?id=' + fileId + '">' +
               '<span><img data-toggle="tooltip" title="Preview" alt="" src="<%=request.getContextPath()%>/assets/img/eye.svg"/></span>' +
            '</a>' +
         '<div>';
      return response;
   }

   function updateFakturIndirect(element) {
      action = "update";
      $('#action-status').html('Edit');
      var entryId = $(element).data("id");
      var formData = new FormData();
      formData.set('<portlet:namespace/>entryId', entryId);
      formData.set('<portlet:namespace/>p_auth', Liferay.authToken);
      createLoading();
      $.ajax({
         url: "${fakturIndirectEditURL}",
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
               $('#create_faktur_indirect_navtab')[0].click();
            }
         },
         error: function (error) {
            Swal.fire({
               icon: 'error',
               title: 'Oops...',
               text: 'Something went wrong!',
            });
         },
         complete: function() {
            destroyLoading();
         }
      });
   }

   function deleteFakturIndirect(element) {
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
            formData.set('<portlet:namespace/>crudType', 'delete');
            formData.set('<portlet:namespace/>entryId', entryId);
            formData.set('<portlet:namespace/>p_auth', Liferay.authToken);

            $.ajax({
               url: "${fakturIndirectActionURL}",
               type: "POST",
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var data = JSON.parse(response);
                  if (data["acknowledge"] === 0) {
                     Swal.fire("Successfully delete data", "", "success")
                     .then((res) => {
                        fakturIndirectDataTable.ajax.reload();
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

   function getDealers() {
      $.ajax({
         url: "${dealerListURL}",
         type: "POST",
         data: {"windowsId": "test"},
         success: function (response) {
            var responseData = JSON.parse(response);

            var viewDealerConst = $('#viewDealerId').select2({
               data: responseData.Data,
               tags: "true",
               placeholder: 'List Dealer',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  if(data.id !== '') {
                     dealerId = data.id;
                     fakturIndirectDataTable.ajax.reload();
                  }
                  return data.text;
               }
            }).tooltip("disable");
            viewDealerConst.val(null).trigger('change');

            var dealerConst = $('#dealerId').select2({
               data: responseData.Data,
               tags: "true",
               placeholder: 'List Dealer',
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
      });
   }

   function setFakturDatepicker() {
      $('[name="invoice-date-view"]').datetimepicker({
         format: 'DD.MM.YYYY',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: false
      }).on('dp.change', function (e) {
         $(this).data('DateTimePicker').hide();
         var selectedDate = e.date;
         periode = selectedDate.format('DD.MM.YYYY');
         fakturIndirectDataTable.ajax.reload();
      });

      $('[name="invoice-date-view"]').on('click', function() {
         $(this).data('DateTimePicker').show();
      });
   }
</script>