<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/surat-penalty-stock/style-css.jsp" %>

<portlet:resourceURL id="/surat-penalty-stock-list" var="suratPenaltyStockListURL"/>
<portlet:resourceURL id="/surat-penalty-stock-action" var="suratPenaltyStockActionURL"/>
<portlet:renderURL var="suratPenaltyStockEditURL">
   <portlet:param name="mvcRenderCommandName" value="/surat-penalty-stock-edit"/>
</portlet:renderURL>

<portlet:resourceURL id="/dealer-surat-penalty-stock" var="dealerSuratPenaltyStockURL"/>
<portlet:resourceURL id="/tahun-surat-penalty-stock" var="tahunSuratPenaltyStockURL"/>
<portlet:resourceURL id="/periode-review-surat-penalty-stock" var="periodeReviewSuratPenaltyStockURL"/>
<portlet:resourceURL id="/upload-file-surat-penalty-stock" var="uploadSuratPenaltyStockURL"/>
<portlet:resourceURL id="/delete-file-surat-penalty-stock" var="deleteSuratPenaltyStockURL"/>

<div class="surat_penalty_stock cms-menu">
   <div class="tabcontent" id="cms-tabcontent-sp">
      <ul class="nav nav-tabs" id="cmsTab" role="tablist">
         <li class="nav-item">
            <a class="nav-link" id="buat_sps_navtab" data-toggle="tab" href="#buat_sps_tab" role="tab" aria-controls="buat-sps" aria-selected="false">
               Buat Surat Penalty Stock
            </a>
         </li>
         <li class="nav-item">
            <a class="nav-link" id="kelola_sps_navtab" data-toggle="tab" href="#kelola_sps_tab" role="tab" aria-controls="kelola_sps_tab" aria-selected="false">
               Kelola Surat Penalty Stock
            </a>
         </li>
      </ul>
      <div class="tab-content" id="myTabContent">
         <div class="tab-pane fade" id="buat_sps_tab" role="tabpanel" aria-labelledby="profile-tab">
            <form id="formSuratPenaltyStockCreate" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
               <div class="container row form-field">
                  <input type="hidden" name="entryId">
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="judul">
                        Judul
                        <span style="color: red;">*</span>
                     </label>
                     <input type="text" class="form-control" name="judul" id="judul"
                            pattern="[A-Za-z0-9. ]+" data-minlength="5" maxlength="100"
                            data-error="Text dengan 5-100 karakter & tidak boleh kosong." required/>
                     <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left label-kd" for="periodeReviewId">
                        Periode Review
                        <span style="color: red;">*</span>
                     </label>
                     <input type="hidden" class="form-control" name="periodeReviewName"/>
                     <select class="form-control periodeReviewId" name="periodeReviewId" id="periodeReviewId" style="width: 100%;"></select>
                  </div>
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left label-kd" for="yearId">
                        Tahun
                        <span style="color: red;">*</span>
                     </label>
                     <input type="hidden" class="form-control" name="tahun"/>
                     <select class="form-control" name="yearId" id="yearId" style="width: 100%;"></select>
                  </div>
                  <div class="form-group col-sm-9">
                     <div class="">
                        <label class="col-form-label ipr-color">
                           Lampiran
                           <span style="color: red;">*</span>
                        </label>
                        <div class="input-group upload-file-group">
                           <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                              <span class="icon-browse">
                                 <img alt="" src="<%=request.getContextPath()%>/assets/img/file.svg">
                              </span>
                              <span class="text-browse">Pilih File</span>
                              <input type="file" accept="application/pdf" placeholder=""
                                     name="suratPenaltyStockFile" id="suratPenaltyStockFile" data-filename=""
                                     data-location="" style="display: none;" aria-invalid="false"
                                     class="materail_input valid">
                           </label>
                           <input type="text" class="form-control required" data-type="file" data-name="" name="suratPenaltyStockFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
                           <input type="hidden" class="dpn" name="suratPenaltyStockFileId">
                           <input type="hidden" class="dpn" name="suratPenaltyStockFilePath">
                        </div>
                        <label class="col-form-label ipr-gray">
                           File maksimal berukuran 5MB dengan format .pdf
                        </label>
                     </div>
                  </div>
                  <div class="form-group col-md-9 tabbing-form">
                     <label class="title-form pull-left label-kd" for="dealerId">
                        Dealer
                        <span style="color: red;">*</span>
                     </label>
                     <input type="hidden" class="form-control" name="dealerCode"/>
                     <input type="hidden" class="form-control" name="dealerName"/>
                     <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;"></select>
                  </div>
                  <div class="form-group col-md-9">
                     <div class="action-line"></div>
                  </div>
                  <div class="form-group col-md-9 action-button">
                     <button class="btn-ipr" type="submit">Save</button>
                     <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="/group/dealink/cms-surat-penalty-stock">Cancel</a>
                  </div>
               </div>
            </form>
         </div>
         <div class="tab-pane fade" id="kelola_sps_tab" role="tabpanel" aria-labelledby="profile-tab">
            <div class="row table-filters">
               <div class="col-xs-12 col-sm-12 col-md-3">
                  <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
                     <option value="ALL">Select All</option>
                  </select>
               </div>
               <div class="col-xs-12 col-sm-12 col-md-3">
                  <select class="form-control bulanId periodeReviewId" name="periodeReviewId" style="width: 100%;">
                     <option value="ALL">Select All</option>
                  </select>
               </div>
            </div>
            <table id="surat_penalty_stock_table" class="table table-hover nowrap cms-table" style="width:100%">
               <thead>
               <tr>
                  <th>No</th>
                  <th>Judul</th>
                  <th>Periode Review</th>
                  <th>Tahun</th>
                  <th>Tanggal Upload</th>
                  <th>Dealer</th>
                  <th>Aksi</th>
               </tr>
               </thead>
               <tbody></tbody>
            </table>
         </div>
      </div>
   </div>
</div>

<script>
   var dealerCode = "ALL";
   var tahun = "ALL";
   var periodeReview = "ALL";
   var role = "ALL";
   var dealerName = "";
   var homeUrl = "/group/dealink/cms-surat-penalty-stock";
   var viewUrl = "/group/dealink/preview-document";

   var suratPenaltyStockLanguage = {
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

   var suratPenaltyStockDataTable = $('#surat_penalty_stock_table').DataTable({
      searching: false,
      paging: true,
      responsive: true,
      info: false,
      language: suratPenaltyStockLanguage,
      ajax: {
         url: '${suratPenaltyStockListURL}',
         type: 'GET',
         data: function (d) {
            d.p_auth = Liferay.authToken;
            d.tahun = tahun;
            d.periodeReview = periodeReview;
         },
         dataSrc: function (json) {
            console.log(json);
            return json.Data;
         }
      },
      columns: [
         {data: "no", width: "5%", className: "text-center"},
         {data: "judul", width: "25%"},
         {data: "periodeReviewName", width: "20%", className: "text-center"},
         {data: "tahun", width: "20%", className: "text-center"},
         {
            data: "dateFile",
            width: "20%",
            className: "text-center",
            render: function (data, type, row, meta) {
               if (type === "sort") {
                  return row.dateSorting;
               }
               return data;
            },
         },
         {data: "dealerName", "width": "20%"},
         {
            data: null,
            width: "7%",
            className: "text-center",
            render: function (data, type, row, meta) {
               return renderActionButton(row.id, row.fileId);
            },
            orderable: false
         }
      ],
      order: [],
      columnDefs: [
         {
            targets: [2],
            orderData: [2, 3]
         }
      ],
      dom: '<"row"<"col-sm-12 cms-table-length"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>'
      <%-- dom: '<"row"<"col-md-6"l><"col-md-6"f>><"row"<"col-md-12"t>><"row"<"col-md-12"ip>>' --%>
   });

   function renderActionButton(dataId, fileId) {
      var response = "";
      response =
         '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a href="${suratPenaltyStockEditURL}&id=' + dataId + '&p_auth=' + Liferay.authToken + '" data-toggle="tooltip" title="Edit">' +
               '<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg"></img> </span>' +
            '</a>' +
            '<a href="javascript:void(0)" onclick="deleteSuratPenaltyStock(this)" data-id="' + dataId + '" data-toggle="tooltip" title="Delete">' +
               '<span><img src="<%=request.getContextPath()%>/assets/img/trash.svg"></img></span>' +
            '</a>' +
            '<a href="' + viewUrl + '?id=' + fileId + '"  data-toggle="tooltip" title="View">' +
               '<span><img src="<%=request.getContextPath()%>/assets/img/eye.svg"></img></span>' +
            '</a>' +
         '</span>';
      return response;
   }

   function deleteSuratPenaltyStock(element) {
      var entryId = $(element).data("id");
      Swal.fire({
         title: 'Apakah anda yakin ingin menghapus data ini?',
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
               url: "${suratPenaltyStockActionURL}",
               type: "POST",
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var data = JSON.parse(response);
                  if (data["acknowledge"] === 1) {
                     Swal.fire("Gagal menghapus data", data["message"], "");
                  } else {
                     Swal.fire("Data berhasil dihapus", "", "success")
                        .then((res) => {
                           suratPenaltyStockDataTable.ajax.reload();
                        });
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
         url: "${dealerSuratPenaltyStockURL}",
         type: "POST",
         data: {
            "<portlet:namespace/>p_auth": Liferay.authToken
         },
         success: function (response) {
            var responseData = JSON.parse(response);
            var dealerConst = $('#dealerId').select2({
               data: responseData.Data,
               placeholder: 'List Dealer',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  console.log(data);
                  if (data.id !== '') {
                     dealerCode = data.id;
                     suratPenaltyStockDataTable.ajax.reload();
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

   function getTahuns() {
      $.ajax({
         url: "${tahunSuratPenaltyStockURL}",
         type: "POST",
         data: {
            "<portlet:namespace/>p_auth": Liferay.authToken
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
                     suratPenaltyStockDataTable.ajax.reload();
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

   function getYears() {
      var tahunSekarang = new Date().getFullYear();
      var tahunTerakhir = tahunSekarang + 3;
      var year = [];

      for (var i = tahunSekarang - 1; i <= tahunTerakhir; i++) {
         year.push(i);
      }

      var yearSelect = $('#yearId').select2({
         data: year.map(function (year) {
            return {id: year, text: year};
         }),
         placeholder: 'Pilih Tahun',
      });
      yearSelect.val(null);
      yearSelect.trigger('change');
   }

   function getPeriodeReviews() {
      $.ajax({
         url: "${periodeReviewSuratPenaltyStockURL}",
         type: "POST",
         data: {
            "<portlet:namespace/>p_auth": Liferay.authToken
         },
         success: function (response) {
            var periodeReviewData = JSON.parse(response);
            const periodeReviewConst = $('.periodeReviewId').select2({
               data: periodeReviewData.Data,
               placeholder: 'Pilih Periode',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  if (data.id !== '') {
                     periodeReview = data.id;
                     suratPenaltyStockDataTable.ajax.reload();
                  }
                  return data.text;
               }
            });
            periodeReviewConst.val(null);
            periodeReviewConst.trigger('change');
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            console.log("complete");
         }
      });
   }

   $("#suratPenaltyStockFile").change(function (e) {
      processUpload(e, "suratPenaltyStock", $(this));
   });

   function processUpload(e, name, element) {
      const fileName = e.target.files[0].name;
      const fileFormat = fileName.split('.').pop();

      if (e.target.files[0].size > 5242880) {
         Swal.fire("Mohon unggah file dengan ukuran maksimal 5 MB", "", "warning");
      } else if (fileFormat !== "pdf") {
         Swal.fire("Mohon unggah file dengan format .pdf", "", "warning");
      } else {
         let fileName = e.target.files[0].name;
         uploadDocumentFile(e.target.files[0], fileName, $('[name="' + name + 'FileId"]')[0].value, element, name);
      }
   }

   function uploadDocumentFile(file, fileName, fileID, element, name) {
      let formData = new FormData();
      let format = /[:"\\|,<>\/?^*]/;
      if (format.test(fileName)) {
         swal.fire('', 'Mohon tidak menggunakan karakter ,^*:"|<>\\ /?', 'warning')
      } else {
         fileName = fileName.replaceAll(",", "");
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
            url: '${uploadSuratPenaltyStockURL}',
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
                     title: "Gagal mengunggah file"
                  });
               }
            },
            error: function (e) {
               console.log(e);
               swal.fire({
                  icon: "error",
                  title: "Gagal mengunggah file"
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
      let parentElement = element.parentElement.parentElement.parentElement;
      let inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url: '${deleteSuratPenaltyStockURL}',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            let data = JSON.parse(response);
            if (data["acknowledge"] === 1) {
               parentElement.remove();

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
               title: "Gagal menghapus file"
            });
         },
         complete: function () {
            console.log("DONE");
         }
      });
   }

   var submitProcess = false;
   var action = "create";
   $("#formSuratPenaltyStockCreate").submit(function (e) {
      e.preventDefault();
      const judul = $('[name="judul"]').val();
      const periodeReviewName = $('[name="periodeReviewId"]').val();
      const tahun = $('#yearId').val();
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

            Swal.fire({
               title: 'Apakah data ini sudah benar?',
               icon: 'question',
               showCancelButton: true,
               confirmButtonText: 'Yes'
            }).then((result) => {
               if (result.isConfirmed) {
                  createLoading();
                  $.ajax({
                     url: '${suratPenaltyStockActionURL}',
                     type: 'POST',
                     data: formData,
                     processData: false,
                     contentType: false,
                     success: function (response) {
                        submitProcess = true;
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
                  });
               }
            });
         }
      }
   });

   $(document).ready(function () {
      getDealers();
      getTahuns();
      getPeriodeReviews();
      getYears();
      $('#kelola_sps_navtab')[0].click();

      $('.dataTables_length select').select2({
         minimumResultsForSearch: -1
      });
   });
</script>