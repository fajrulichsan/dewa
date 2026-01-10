<%@ include file="/META-INF/resources/init.jsp" %>

<style>
    .upload-photo {
        width: 25%;
        height: 150px;
        border: 1px dashed #C7C7C7;
        border-radius: 8px;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
    }

    .thumbnail-box {
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        border-radius: 8px;
        width: 25%;
        height: 150px;
        display: flex;
    }

    .btn-ipr, .btn-ipr-cancel {
        border-radius: 20px;
    }

    .buttom-form {
        margin-bottom: 0px;
    }

    .photo-profile {
        margin-bottom: 35px;
        margin-left: 20px;
    }

    .section-photo {
        margin-bottom: 20px;
        padding-left: 0px;
    }

    <%-- Role Container --%>
    .roles-container {
        min-height: 150px;
        width: 100%;
        padding: 10px;
        background-color: #eee;
        color: #eee;
        cursor: not-allowed;
        border: 1px solid #ccc;
        border-radius: 6px;
        display: inline-block;
        vertical-align: top;
        box-sizing: border-box;
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);

    }

    .selected-role {
        margin: 0 10px 10px 0;
        padding: 7px 12px;
        border: none;
        border-radius: 6px;
        background: #014996;
        display: inherit;
        color: white;
        cursor: not-allowed;
    }

    @media screen and (max-width: 768px) {
        .input-group .form-control {
            z-index: 0;
        }

        .section-photo {
            display: flex;
            justify-content: center;
        }
    }
</style>

<form data-toggle="validator" role="form" id="formPersonalInformation" method="post" enctype="multipart/form-data"
      novalidate autocomplete="off">
   <div class="container" style="margin-left: 0;">
      <h3 id="form-title" style="margin-bottom: 50px;">Personal Information</h3>
      <input type="hidden" name="entryId">
      <div class="row">
         <div class="form-group col-md-12 photo-profile">
            <div class="">
               <label class="col-form-label ipr-color">
                   <b>Profile Photo</b>
               </label>

                <div class="col-md-12 section-photo">
                  <c:choose>
                     <c:when test="${photoUrl != ''}">
                        <div class="thumbnail-box" style="background-image: url('${photoUrl}')"></div>
                     </c:when>
                     <c:otherwise>
                        <div class="thumbnail-box" style="background-image: url('<%=user.getPortraitURL(themeDisplay)%>')"></div>
                     </c:otherwise>
                  </c:choose>
               </div>

                <div class="input-group col-md-12" style="margin-top: 20px;">
                  <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                     <span class="icon-browse">
                         <img src="<%=request.getContextPath()%>/assets/img/file.svg">
                     </span>
                     <span style="color: white">Upload Photo</span>
                     <input type="file" accept="image/jpeg, image/png" placeholder=""
                            name="profileFile" id="profileFile" data-filename="" data-location=""
                            style="display: none;" aria-invalid="false" class="materail_input valid">
                  </label>
                  <input type="text" class="form-control required" data-type="file" data-name=""
                         name="profileFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
                  <input type="text" class="dpn" name="profileFileId">
                  <input type="text" class="dpn" name="profileFilePath">
               </div>
               <label class="col-form-label ipr-gray">File dengan format jpg, jpeg dan png *maks. 1MB</label>
            </div>
         </div>

         <div class="form-group col-md-12">
            <div class="form-group col-md-6 buttom-form">
               <label class="title-form pull-left" for="fullName">
                  <b>Nama Lengkap</b>
                   <span style="color: red;">*</span>
               </label>
               <input type="text" class="form-control" name="fullName" id="fullName" value="${userFullName}"
                      pattern="[A-Za-z0-9. ]+" data-minlength="5" maxlength="100"
                      data-error="Text dengan 5-100 karakter & tidak boleh kosong." required/>
               <div class="help-block with-errors"></div>
            </div>

            <div class="form-group col-md-6 buttom-form">
               <label class="title-form pull-left" for="email">
                   <b>Email Address</b>
                   <span style="color: red;">*</span>
               </label>
               <input value="<%=user.getEmailAddress()%>" type="text" class="form-control" name="email" id="email" required disabled/>
               <div class="help-block with-errors"></div>
            </div>
         </div>

         <div class="form-group col-md-12">
            <div class="form-group col-md-6 buttom-form">
               <label class="title-form pull-left">
                   <b>Role</b>
                   <span style="color: red;">*</span>
               </label>
               <div class="roles-container"></div>
               <div class="help-block with-errors"></div>
            </div>
         </div>
         <div class="col-md-12" style="border-top: 1px solid #D9D9D9 ; margin: 10px 15px"></div>
         <div class="col-md-9" style="margin: 1pc 0;">
            <button class="btn-ipr" type="submit">Save</button>
            <a class="btn btn-ipr-cancel" href="/group/dealink/home">Cancel</a>
         </div>
      </div>
   </div>
</form>

<portlet:resourceURL var="resourceURL"/>

<script>
   var roles = ${roles};
   var $container = $('.roles-container');

   if (roles) {
      roles.forEach(item => {
         $container.append('<div class="selected-role" data-value="' + item.id + '" class="selected-role">' + item.name + '</div>')
      });
   }

   $('.roles-container').css({'background-color': '#eee', 'color': 'eee', 'cursor': 'not-allowed'});
   $('.roles-container .selected-role').css({'opacity': '0.5'});

   $("#profileFile").change(function (e) {
      processUpload(e, "profile", $(this));
   });

   function processUpload(e, name, element) {
      const fileName = e.target.files[0].name;
      const fileFormat = fileName.split('.').pop();

      if (e.target.files[0].size > 1048576) {
         swal.fire("", "File Size cannot more than 1 MB", "warning");
      } else if (!(fileFormat.toLowerCase() === "jpeg" || fileFormat.toLowerCase() === "png" || fileFormat.toLowerCase() === "jpg")) {
         Swal.fire("", "The file must be in JPEG, JPG atau PNG format.", "warning");
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
         formData.set("<portlet:namespace/>state", "uploadFile");
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

   $("#formPersonalInformation").submit(function (e) {
      e.preventDefault();
      const profileFileId = $('[name="profileFileId"]').val();
      const profileFilePath = $('[name="profileFilePath"]').val();
      const profileFileName = $('[name="profileFileName"]').val();
      const fullName = $('[name="fullName"]').val();

      if (fullName === null || fullName === undefined || fullName.length < 5) {
         Swal.fire("Nama lengkap belum terisi atau nama lengkap harus 5-100 karakter.", "", "warning");
         return false;
      } else if (!regexBasicCharacter.test(fullName)) {
         Swal.fire('Nama lengkap hanya boleh diisi dengan karakter .,/()@&_-', '', 'warning');
         return false;
      } else {
         var formData = new FormData(this);
         formData.set("<portlet:namespace/>state", "post");
         formData.set('<portlet:namespace/>profileFileId', profileFileId);
         formData.set('<portlet:namespace/>profileFilePath', profileFilePath);
         formData.set('<portlet:namespace/>profileFileName', profileFileName);
         formData.set('<portlet:namespace/>userFullName', fullName);

         formData.forEach(function (value, key) {
            console.log(key, value);
         });

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
                        window.location.reload();
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
   })
</script>