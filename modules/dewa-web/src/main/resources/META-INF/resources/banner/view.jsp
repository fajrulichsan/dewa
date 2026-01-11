<%@ include file="/META-INF/resources/init.jsp" %>

<style>
    /* ===========================================
       BANNER STYLES
       =========================================== */
    
    /* Button Styles */
    .btn.btn-delete {
        color: #ffffff;
        background-color: #f01f1e;
        border: none;
        transition: background-color 0.3s ease;
    }

    .btn.btn-delete:hover {
        background-color: #d01a19;
    }

    /* Thumbnail Styles */
    .thumbnail-box {
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        border-radius: 10px 10px 0 0;
        height: 200px;
    }

    .thumbnail-box-wrapper.bn-hd {
        border: 1px solid #dfe8f1;
        margin-bottom: 10px;
        border-radius: 10px;
        transition: box-shadow 0.3s ease;
    }

    .thumbnail-box-wrapper.bn-hd:hover {
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    /* Banner List Item */
    .bn-ls {
        float: left;
        height: 280px;
        width: 28%;
        margin: 0 1rem 1px 1rem;
    }

    /* List Container */
    div#listRisalah {
        margin-top: 5rem;
        padding: 0 4rem;
    }

    #myList {
        list-style: none;
        padding: 0;
        margin: 0;
    }

    #myList li::marker {
        content: none;
    }

    /* Thumbnail Action Pane */
    .thumb-pane {
        display: flex;
        gap: 15px;
        align-items: center;
        padding: 10px;
        justify-content: center;
    }

    /* Navigation Tabs */
    .nav-tabs > li.active > a,
    .nav-tabs > li.active > a:hover,
    .nav-tabs > li.active > a:focus {
        border-top: none;
        border-left: none;
        border-right: none;
        border-bottom: 3px solid #083f78;
    }

    /* ===========================================
       TUI PAGINATION STYLES
       =========================================== */
    
    .tui-pagination .tui-page-btn {
        border: none !important;
        margin: 0 3px;
        border-radius: 5px;
        transition: background-color 0.3s ease;
    }

    .tui-pagination .tui-is-selected,
    .tui-pagination .tui-is-selected:hover {
        background-color: #014689;
        color: #ffffff;
    }

    .tui-last,
    .tui-first {
        display: none !important;
    }

    .tui-pagination .tui-next,
    .tui-pagination .tui-prev {
        border: none;
        border-radius: 5px;
    }

    .tui-is-disabled.tui-prev .tui-ico-prev {
        width: 9px;
        height: 16px;
        background: url('<%=request.getContextPath()%>/assets/img/chevron-left-disabled.svg') no-repeat 0 0;
    }

    .tui-prev .tui-ico-prev {
        width: 9px;
        height: 16px;
        background: url('<%=request.getContextPath()%>/assets/img/chevron-left.svg') no-repeat 0 0;
    }

    .tui-is-disabled.tui-next .tui-ico-next {
        width: 9px;
        height: 16px;
        background: url('<%=request.getContextPath()%>/assets/img/chevron-right-disabled.svg') no-repeat 0 0;
    }

    .tui-next .tui-ico-next {
        width: 9px;
        height: 16px;
        background: url('<%=request.getContextPath()%>/assets/img/chevron-right.svg') no-repeat 0 0;
    }

    /* ===========================================
       RESPONSIVE MEDIA QUERIES
       =========================================== */
    
    /* Tablet View */
    @media only screen and (min-width: 651px) and (max-width: 850px) {
        .bn-ls {
            width: 43%;
        }

        div#listRisalah {
            padding: 0 2rem;
        }
    }

    /* Mobile View */
    @media only screen and (max-width: 650px) {
        .bn-ls {
            width: 100%;
            margin: 0 0 1rem 0;
        }

        div#listRisalah {
            margin-top: 3rem;
            padding: 0 1rem;
        }
    }
</style>

<form data-toggle="validator" role="form" id="formBanner" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
   <div class="container" style="margin-left: 3pc;width: 80%">
      <h3 id="form-title" style="margin-bottom: 50px;">Slide Show</h3>
      <div class="form-group">
         <div class="col-md-12" style="margin-top: 15px;">
            <label class="col-form-label ipr-color">
                Image
                <span style="color: red;">*</span>
            </label>
            <div class="input-group">
               <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv"
                      style="border-width: 0;background-color: #014689;">
                        <span class="icon-browse">
                            <img src="<%=request.getContextPath()%>/assets/img/file.svg">
                        </span>
                  <span class="text-browse">Pilih File</span>
                  <input type="file" accept="image/gif, image/jpeg, image/png" placeholder="" name="bannerImageFile"
                         id="bannerImageFile" data-filename="" data-location="" style="display: none;"
                         aria-invalid="false" class="materail_input valid">
               </label>
               <input type="text" class="form-control required" data-type="file" data-name="" name="bannerImageFileName"
                      readonly style="border: 1px solid #DBEDFF; background-color: white">
               <input type="hidden" class="dpn" name="bannerImageFileId">
               <input type="hidden" class="dpn" name="bannerImageFilePath">
            </div>
            <label class="col-form-label ipr-gray">File dengan format jpg, jpeg dan png *maks. 1MB</label>
         </div>
      </div>

      <div class="col-md-12">
         <button class="btn-ipr pull-right" type="submit">Simpan</button>
      </div>
   </div>
</form>

<div id="listRisalah">
   <div class="row side-box">
      <div class="col-md-12">
         <ul class="reset-ul row" id="myList" style="width:100%;">
         </ul>
      </div>
   </div>

   <div id="pagination" class="tui-pagination"></div>
</div>

<portlet:resourceURL var="resourceURL"/>

<script>
   var submitProcess = false;
   var entryId = "0";
   var homeUrl = "/group/dealink/cms-home";
   var Pagination = tui.Pagination;
   var paginationOptions = {
      usageStatistics: false,
      centerAlign: true,
      totalItems: 0,
      itemsPerPage: 15,
      visiblePages: 5
   };

   var container = document.getElementById('pagination');
   var tuiInstance = new Pagination(container, paginationOptions);

   $(document).ready(function () {
      datalist(true, 15, 1);

      tuiInstance.on('beforeMove', (event) => {
         var currentPage = event.page;
         var itemPerPage = tuiInstance._options.itemsPerPage;

         datalist(false, itemPerPage, currentPage);
      });
   });

   $("#bannerImageFile").change(function (e) {
      processUpload(e, "bannerImage", $(this));
   });

   $("#formBanner").submit(function (e) {
      e.preventDefault();
      const bannerImageFileId = $('[name="bannerImageFileId"]').val();
      const bannerImageFilePath = $('[name="bannerImageFilePath"]').val();
      const bannerImageFileName = $('[name="bannerImageFileName"]').val();

      if (submitProcess === false) {
         if (bannerImageFileId === null || bannerImageFileId === undefined || bannerImageFileId.length < 1) {
            Swal.fire("Images belum diisi.", "", "warning");
            return false;
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.set("<portlet:namespace/>state", "POST");
            formData.set('<portlet:namespace/>bannerImageFileId', bannerImageFileId);
            formData.set('<portlet:namespace/>bannerImageFilePath', bannerImageFilePath);
            formData.set('<portlet:namespace/>bannerImageFileName', bannerImageFileName);
            formData.set('<portlet:namespace/>p_auth', Liferay.authToken);

            createLoading();
            $.ajax({
               url: '${resourceURL.toString()}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  if (JSON.parse(response).status === 'success') {
                     swal.fire("Success", "Your request has been saved", "success")
                        .then(function () {
                           window.location = homeUrl;
                        });
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
      if (e.target.files[0].size > 1048576) {
         swal.fire("", "File Size cannot more than 1 MB", "warning");
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
         formData.set("<portlet:namespace/>file-upload", file);
         formData.set("<portlet:namespace/>file-name", fileName);
         formData.set("<portlet:namespace/>file-id", fileID);
         formData.set("<portlet:namespace/>state", "UPLOAD_FILE");
         formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

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
            url: '${resourceURL.toString()}',
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
                  swal.fire({icon: "error", title: "Failed to upload file"});
               }
            },
            error: function (e) {
               console.log(e);
               swal.fire({icon: "error", title: "Failed to upload file"});
            },
            complete: function () {
               console.log("DONE");
            }
         });
      }
   }

   function datalist(reset, itemPerPage, currentPage) {
      $.ajax({
         url: '${resourceURL.toString()}',
         type: 'POST',
         data: {
            <portlet:namespace />state: "LIST_DATA",
            <portlet:namespace/>itemPerPage: itemPerPage,
            <portlet:namespace/>currentPage: currentPage
         },
         success: function (data) {

            var banners = '';
            var data_ = JSON.parse(data);
            if (reset) {
               tuiInstance.reset(data_["count"]);
            }

            $.each(data_["data"], function (k, row) {
               var checked = "";
               if (row.isShow == true) {
                  checked = "checked";
               }
               banners += '<li class="bn-ls" id="<portlet:namespace/>item-' + row.id + '">' +
                  '<div class="thumbnail-box-wrapper bn-hd">' +
                  '<div class="thumbnail-box" style="background-image: url(' + row.url + ')">' +
                  '</div>' +
                  '<div class="thumb-pane">' +
                  '<div>' +
                  '<label class="switch">' +
                  '<input type="checkbox" data-id="' + row.id + '" data-status="' + row.isShow + '" onchange="status(this)" ' + checked + ' id="togBtn"><span class="slider round"></span>' +
                  '</label>' +
                  '</div>' +
                  ' <button type="button" onclick="hapus(this)" id="deleteButton" data-id="' + row.id + '" class="btn btn-delete" >Hapus</button>' +
                  '</div>' +
                  '</div>' +
                  '</li>';
            });

            $('#myList').empty().append(banners);
            destroyLoading();

         }, error: function () {
            swal({
               title: "Error!",
               text: "Error get data banner",
               icon: "warning",
               button: "Ok",
            });
         }
      });
   }

   function hapus(e) {
      var id = $(e).attr('data-id');
      Swal.fire({
         title: "Apakah anda yakin",
         text: "Hapus data ini!",
         type: 'question',
         showCancelButton: true,
         confirmButtonText: 'Ya, Hapus!'
      }).then(function (val) {
         if (val.value) {
            createLoading();
            $.ajax({
               url: '${resourceURL.toString()}',
               type: 'POST',
               data: {
                  <portlet:namespace />bannerId: id,
                  <portlet:namespace />state: "DELETE"
               },
               success: function (data) {
                  if (JSON.parse(data).status === "success") {
                     Swal.fire({
                        title: "Success!",
                        type: "success"
                     });
                     //refresh
                     window.location.reload();
                  } else {
                     Swal.fire({
                        title: "Error!",
                        type: "error",
                     });
                  }
               }, error: function () {
                  Swal.fire({
                     title: "Failed!",
                     type: 'error'
                  });
               },
               complete: function () {
                  destroyLoading();
               }
            });
         }
      });
   }

   function status(e) {
      var id = $(e).attr('data-id');
      var status = $(e).attr('data-status');

      createLoading();
      $.ajax({
         url: '${resourceURL.toString()}',
         type: 'POST',
         data: {
            <portlet:namespace />bannerId: id,
            <portlet:namespace />state: "TOGGLE_SHOW",
            <portlet:namespace />status: status,
         },
         success: function (data) {
            if (JSON.parse(data).status === "success") {
               Swal.fire({
                  title: "Success!",
                  type: "success"
               });
            } else {
               Swal.fire({
                  title: "Error!",
                  type: "error",
               });
            }
            //refresh
            window.location.reload();
         }, error: function () {
            Swal.fire({
               title: "Failed!",
               type: 'error'
            });
         }, complete: function () {
            destroyLoading();
         }
      });
   }
</script>