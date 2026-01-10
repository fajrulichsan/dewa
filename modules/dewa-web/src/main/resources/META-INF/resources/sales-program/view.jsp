<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/sales-program/style-css.jsp" %>

<portlet:resourceURL id="/sales-program-list" var="salesProgramListURL"/>
<portlet:resourceURL id="/sales-program-action" var="salesProgramActionURL"/>
<portlet:renderURL var="salesProgramEditURL">
   <portlet:param name="mvcRenderCommandName" value="/sales-program-edit"/>
</portlet:renderURL>

<portlet:resourceURL id="/upload-sales-program" var="uploadFileSalesProgramCommandURL"/>
<portlet:resourceURL id="/delete-sales-program" var="deleteFileSalesProgramCommandURL"/>
<portlet:resourceURL id="/tahun-sales-program" var="tahunSalesProgramURL"/>
<portlet:resourceURL id="/bulan-sales-program" var="bulanSalesProgramURL"/>

<div class="sales_program cms-menu">
   <div class="tabcontent" id="cms-tabcontent-sp">
      <ul class="nav nav-tabs" id="cmsTab" role="tablist">
         <li class="nav-item">
            <a class="nav-link" id="buat_sp_navtab" data-toggle="tab" href="#buat_sp_tab" role="tab" aria-controls="buat-sp" aria-selected="false">
               Buat Sales Program
            </a>
         </li>
         <li class="nav-item">
            <a class="nav-link" id="kelola_sp_navtab" data-toggle="tab" href="#kelola_sp_tab" role="tab" aria-controls="kelola_sp_tab" aria-selected="false">
               Kelola Sales Program
            </a>
         </li>
      </ul>
      <div class="tab-content" id="myTabContent">
         <div class="tab-pane fade" id="buat_sp_tab" role="tabpanel" aria-labelledby="profile-tab">
            <form id="formSalesProgramCreate" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
               <div class="container row form-field">
                  <input type="hidden" name="entryId">
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left label-sp" for="periodeId">
                        Periode
                        <span style="color: red;">*</span>
                     </label>
                     <input type="hidden" class="form-control" name="periode"/>
                     <select class="form-control bulanId" name="periodeId" id="periodeId" style="width: 100%;"></select>
                  </div>
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left label-sp" for="tahun">
                        Tahun
                        <span style="color: red;">*</span>
                     </label>
                     <input type="hidden" class="form-control" name="tahun"/>
                     <select class="form-control" name="tahun" id="tahun" style="width: 100%;"></select>
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
                           <input type="file" accept="application/pdf" placeholder="" name="salesProgramFile"
                                  id="salesProgramFile" data-filename="" data-location="" style="display: none;"
                                  aria-invalid="false" class="materail_input valid">
                        </label>
                        <input type="text" class="form-control required" data-type="file" data-name="" name="salesProgramFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
                        <input type="text" class="dpn" name="salesProgramFileId">
                        <input type="text" class="dpn" name="salesProgramFilePath">
                     </div>
                     <label class="col-form-label ipr-gray">
                        Maksimal upload file 5MB dengan format pdf
                     </label>
                  </div>
                  <div class="form-group col-md-9">
                     <div class="action-line"></div>
                  </div>
                  <div class="form-group col-md-9 action-button">
                     <button class="btn-ipr" type="submit">Simpan</button>
                     <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="/group/dealink/cms-sales-program">Batal</a>
                  </div>

                  <%--
                  <div class="col-md-9 line-aksi" style="border-top: 1px solid #D9D9D9 ; margin: 10px 15px"></div>
                  <div class="col-md-9 btn-aksi">
                     <button class="btn-ipr" type="submit">Simpan</button>
                     <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="/group/dealink/cms-sales-program">Batal</a>
                  </div>
                  --%>
               </div>
            </form>
         </div>
         <div class="tab-pane fade" id="kelola_sp_tab" role="tabpanel" aria-labelledby="profile-tab">
            <div class="row table-filters">
               <div class="col-xs-12 col-sm-12 col-md-3">
                  <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
                     <option value="ALL">Select All</option>
                  </select>
               </div>
               <div class="col-xs-12 col-sm-12 col-md-3">
                  <select class="form-control bulanId" name="bulanId" id="bulanId" style="width: 100%;">
                     <option value="ALL">Select All</option>
                  </select>
               </div>
            </div>
            <table id="sales_program_table" class="table table-hover nowrap cms-table" style="width:100%">
               <thead>
                  <tr>
                     <th>No</th>
                     <th>Sales Program</th>
                     <th>Periode</th>
                     <th>Tahun</th>
                     <th>Tanggal Upload</th>
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
   var tahun = "ALL";
   var bulan = "ALL";
   var homeUrl = "/group/dealink/cms-sales-program";
   var viewUrl = "/group/dealink/preview-document";

   var salesProgramTableLanguage = {
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

   var salesProgramDataTable = $('#sales_program_table').DataTable({
      searching: false,
      paging: true,
      responsive: true,
      info: false,
      language: salesProgramTableLanguage,
      ajax: {
         url: '${salesProgramListURL}',
         type: 'GET',
         data: function (d) {
            d.p_auth = Liferay.authToken;
            d.tahun = tahun;
            d.periode = bulan;
         },
         dataSrc: function (json) {
            return json.Data;
         }
      },
      columns: [
         {data: "no", "width": "5%", className: "text-center"},
         {data: "fileName", "width": "25%"},
         {
            data: "periodeNumber",
            "width": "15%",
            className: "text-center",
            "render": function (data, type, row) {
               if (type === "display") {
                  return row.periode;
               }
               return data;
            },
         },
         {data: "yearFile", "width": "15%", className: "text-center"},
         {
            data: "dateFile",
            "width": "15%",
            className: "text-center",
            "render": function (data, type, row, meta) {
               if (type === "sort") {
                  return row.dateSorting;
               }
               return data;
            },
         },
         {
            data: null,
            "width": "7%",
            className: "text-center",
            "render": function (data, type, row, meta) {
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
            '<a href="${salesProgramEditURL}&id=' + dataId + '&p_auth=' + Liferay.authToken + '" data-toggle="tooltip" title="Edit">' +
               '<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg"></img></span>' +
            '</a>' +
            '<a href="javascript:void(0)" onclick="deleteSalesProgram(this)" data-id="' + dataId + '" data-toggle="tooltip" title="Delete">' +
               '<span><img src="<%=request.getContextPath()%>/assets/img/trash.svg"></img></span>' +
            '</a>' +
            '<a href="' + viewUrl + '?id=' + fileId + '"  data-toggle="tooltip" title="View">' +
               '<span><img src="<%=request.getContextPath()%>/assets/img/eye.svg"></img></span>' +
            '</a>' +
         '</span>';
      return response;
   }

   function deleteSalesProgram(element) {
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
               url: "${salesProgramActionURL}",
               type: "POST",
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var data = JSON.parse(response);
                  if (data["acknowledge"] === 1) {
                     Swal.fire("Failed to Delete", data["message"], "");
                  } else {
                     Swal.fire("Successfully delete data", "", "success")
                        .then((res) => {
                           salesProgramDataTable.ajax.reload();
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

   function getTahuns() {
      $.ajax({
         url: "${tahunSalesProgramURL}",
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
                     salesProgramDataTable.ajax.reload();
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

   function getBulans() {
      $.ajax({
         url: "${bulanSalesProgramURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var bulanData = JSON.parse(response);
            var bulanConst = $('.bulanId').select2({
               data: bulanData.Data,
               placeholder: 'Pilih Periode',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  <%--
                  if (data.id !== '') {
                     bulan = data.id;
                     salesProgramDataTable.ajax.reload();
                  }
                  --%>
                  return data.text;
               }
            });
            bulanConst.val(null);
            bulanConst.trigger('change');
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            $('[name="bulanId"]').change(function () {
               bulan = $('[name="bulanId"]').val();
               salesProgramDataTable.ajax.reload();
            });
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
      console.log(year);

      var yearSelect = $('#tahun').select2({
         data: year.map(function (year) {
            return {id: year, text: year};
         }),
         placeholder: 'Pilih Tahun',
      });

      yearSelect.val(null);
      yearSelect.trigger('change');
   }

   $("#salesProgramFile").change(function (e) {
      processUpload(e, "salesProgram", $(this));
   });

   function processUpload(e, name, element) {
      const fileName = e.target.files[0].name;
      const fileFormat = fileName.split('.').pop();
      if (e.target.files[0].size > 5242880) {
         swal.fire("", "Mohon unggah file dengan ukuran maksimal 5MB", "warning");
      } else if (fileFormat !== "pdf") {
         Swal.fire("", "Mohon unggah file dengan format .pdf", "warning");
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
         fileName = fileName.replaceAll(",", "")
         formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
         formData.set("<portlet:namespace/>file-upload", file);
         formData.set("<portlet:namespace/>file-name", fileName);
         formData.set("<portlet:namespace/>file-id", fileID);

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

   function viewUploadedFile(element) {
      var fileId = $(element).data("id");
      var urlShare = themeDisplay.getPortalURL() + "/group/dealink/preview-document?id=" + fileId;
      var newTab = window.open(urlShare, '_blank');
      newTab.focus();
   }

   var submitProcess = false;
   var action = "create";
   $("#formSalesProgramCreate").submit(function (e) {
      e.preventDefault();
      const tahun = $('#tahun').val();
      const periode = $('[name="periodeId"]').val();
      const salesProgramFileId = $('[name="salesProgramFileId"]').val();
      if (submitProcess === false) {
         if (periode === null || periode === undefined) {
            Swal.fire("Periode belum dipilih", "", "warning");
            return false;
         } else if (tahun === null || tahun === undefined) {
            Swal.fire("Tahun belum dipilih", "", "warning");
            return false;
         } else if (salesProgramFileId === null || salesProgramFileId === undefined || salesProgramFileId.length < 1) {
            Swal.fire("File belum diisi", "", "warning");
            return false;
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.set("<portlet:namespace/>crudType", action)
            formData.set("<portlet:namespace/>periode", $('[name="periodeId"]').val());
            formData.set("<portlet:namespace/>tahun", $('#tahun').val());
            formData.set("<portlet:namespace/>salesProgramFileId", $('[name="salesProgramFileId"]').val());
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            createLoading();
            $.ajax({
               url: '${salesProgramActionURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  if (JSON.parse(response).status === 'success') {
                     swal.fire("Data berhasil disimpan", "", "success")
                        .then(function () {
                              window.location.href = homeUrl;
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
         url: '${deleteFileSalesProgramCommandURL}',
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
               title: "Gagal menghapus file"
            });
         },
         complete: function () {
            console.log("DONE");
         }
      });
   }

   $(document).ready(function () {
      getTahuns();
      getBulans();
      getYears();
      $('#kelola_sp_navtab')[0].click();

      $('.dataTables_length select').select2({
         minimumResultsForSearch: -1
      });
   });
</script>