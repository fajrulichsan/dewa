<%@ include file="/META-INF/resources/init.jsp" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/jquery.validate.min.js"></script>
<link href="<%=request.getContextPath()%>/assets/css/custom.css?<%=System.currentTimeMillis()  %>" rel="stylesheet" type="text/css"/>

<style>
    .custom-modal {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        min-height: 100vh;
        z-index: 1050;
        background-color: rgba(0, 0, 0, 0.4);
    }

    .custom-modal.open {
        display: inherit;
    }

    .custom-modal-content {
        position: absolute;
        min-width: 50%;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        background-color: #fff;
        box-shadow: 0px 4px 10px rgb(0, 0, 0, 0.15);
        border-radius: 10px;

    }

    .custom-modal-title {
        /*text-align: center;*/
        font-size: 20px;
        font-weight: 600;
        color: #014689;
        display: flex;
        justify-content: center;
        position: relative;
    }

    .custom-modal-title .close {
        position: absolute;
        right: 20px;
    }

    .custom-modal-body {
        max-height: 500px;
        overflow-y: auto;
        padding: 2rem;
    }

    .custom-modal-footer {
        padding: 2rem;
        border-top: 1px solid #c7c0c0;
        text-align: right;
    }

    .custom-modal-header {
        border-bottom: 1px solid #c7c0c0;
        /*padding-bottom: 2rem;*/
        padding: 2rem 0;
    }

    .line-daftar {
        margin: 5px 0;
        border-bottom: 1px solid #938c8c;
    }

    .ql-clipboard {
        display: none !important;
    }

    .ql-tooltip.ql-hidden {
        display: none !important;
    }

    .panel {
        border: 0 solid transparent;
        box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.2);
        border-radius: 5px;
    }

    .detail-acara {
        margin-left: 2rem;
    }

    #kembali, .addMore {
        background: #777777;
        border-color: #777777;
        color: white;
    }

    .daftar {
        color: white;
        border-color: #084996;
        background: #084996;
    }

    .title-agenda {
        font-size: larger;
        color: black;
    }

    h2 {
        color: black !important;
    }

    .form-control:focus {
        color: #000000;
    }
</style>

<section class="page-section" id="forum-category-head" style="padding-top: 50px;">
   <div class="container-fluid" id="home">
      <div class="row">
         <div class="col-md-12" style="padding: 0 15px;">
            <p style="margin: 0;"><small id="headLokasi"></small></p>
            <h2 style="margin-top: unset;" id="headTitle"></h2>
            <div class="">
               <div class="text-right">
                  <button type="button" id="kembali" class="btn btn-default">Kembali</button>
                  <c:if test="${notDSO}">
                     <button id="btnDaftar" class="btn btn-primary daftar" data-target="#modalRegisterPeserta">
                        Daftar
                     </button>
                  </c:if>
               </div>
            </div>
            <hr>
         </div>
      </div>

      <div class="row">
         <div class="col-md-12">
            <div class="col-md-8">
               <div class="panel">
                  <div class="patenel-body" id="resultDetail"></div>
               </div>
            </div>
            <div class="col-md-4">
               <div class="">
                  <div class="panel-heading">
                     <h2 style="margin-top: 0px">Agenda Selanjutnya</h2>
                  </div>
                  <div id="resultList"></div>
               </div>
            </div>
         </div>
      </div>
   </div>
</section>

<div id="fieldGroupCopy" style="display: none; border-top: 1px solid #f5f5f5;">
   <div class="group-form">
      <div class="col-md-12 text-right" style="border-top: 1px solid #f5f5f5;">
         <a href="javascript:;" id="remove" class="text-danger" style="color:red;" title="Hapus">x</a>
      </div>

      <div class="col-md-6">
         <div class="col-md-12">
            <div class="form-group">
               <label>
                   Email
                   <span style="color: red;">*</span>
               </label>
               <input type="email" name="<portlet:namespace/>email"
                      class="form-control select-email form-required basic-character"
                      placeholder="" required/>
            </div>
         </div>

         <div class="col-md-12">
            <div class="form-group">
               <label>
                   No. Handphone
                   <span style="color: red;">*</span>
               </label>
               <input type="number" name="<portlet:namespace/>phone" class="form-control select-phone form-required"
                      placeholder=""
                      required>
            </div>
         </div>
      </div>
      <div class="col-md-6">
         <div class="col-md-12">
            <div class="form-group">
               <label>
                   Nama
                   <span style="color: red;">*</span>
               </label>
               <input type="text" name="<portlet:namespace/>name"
                      class="form-control select-name form-required basic-character" placeholder="" required>
            </div>
         </div>

         <div class="col-md-12">
            <div class="form-group ">
               <label>
                   Nama Dealer
                   <span style="color: red;">*</span>
               </label>
               <select name="<portlet:namespace/>company" class="form-control select-company form-required"></select>
            </div>
         </div>
      </div>

      <div class="col-md-12 lampiran-div line-daftar"></div>
   </div>
</div>

<div class="custom-modal" id="modalRegisterPeserta">
   <div class="custom-modal-content">
      <form class="form" id="frmRegister" action="">
         <div class="custom-modal-header">
            <div class="custom-modal-title" id="divisionModalTitle">
               Daftar Peserta
               <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
         </div>
         <div class="custom-modal-body">
            <div class="fieldGroup">
               <div class="group-form" id="group-1" data-id="1">
                  <input type="hidden" name="<portlet:namespace/>id" id="vId"/>
                  <input type="hidden" name="<portlet:namespace/>state" value="post"/>
                  <div class="col-md-6">
                     <div class="col-md-12">
                        <div class="form-group">
                           <label>Email<span style="color: red;"> *</span></label>
                           <input type="hidden" class="form-control" name="userId"/>
                           <input type="hidden" class="form-control" name="userEmail"/>
                           <input type="email" name="<portlet:namespace/>email"
                                  class="form-control select-email form-required basic-character"
                                  id="selectEmail1" data-id="1" placeholder=""
                                  required/>
                        </div>
                     </div>

                     <div class="col-md-12">
                        <div class="form-group">
                           <label>No. Handphone <span style="color: red;"> *</span></label>
                           <input type="number" name="<portlet:namespace/>phone"
                                  class="form-control select-phone form-required"
                                  id="selectPhone1" placeholder="" required>
                        </div>
                     </div>

                  </div>
                  <div class="col-md-6">
                     <div class="col-md-12">
                        <div class="form-group">
                           <label>Nama <span style="color: red;"> *</span></label>
                           <input type="text" name="<portlet:namespace/>name"
                                  class="form-control select-name form-required basic-character"
                                  id="selectName1" placeholder="" required>
                        </div>
                     </div>

                     <div class="col-md-12">
                        <div class="form-group ">
                           <label>Nama Dealer <span style="color: red;"> *</span></label>
                           <select name="<portlet:namespace/>company" class="form-control select-company form-required"
                                   id="selectCompany1" required></select>
                        </div>
                     </div>
                  </div>

                  <div class="col-sm-12">
                     <div class="col-md-12 lampiran-div line-daftar"></div>
                  </div>

               </div>
            </div>
         </div>
         <div class="custom-modal-footer">
            <button type="button" class="btn btn-default addMore">
                <i class="fa fa-plush"></i>
                Tambah Peserta
            </button>
            <button type="submit" class="btn btn-primary daftar">Daftar</button>
         </div>
      </form>
   </div>
</div>

<portlet:resourceURL var="resourceURL"/>

<script type="text/javascript">
   var acaraId = ${acaraId};
   var lampir;

   $(document).ready(function () {
      $.validator.setDefaults({
         highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
         },
         unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
            $(element).closest('.form-group').removeClass('error-display');
         },
         errorElement: 'span',
         errorClass: 'help-block',
         errorPlacement: function (error, element) {
            return true;
         }
      });

      //ini untuk onchange input
      $("input.form-required, select.form-required").on("input", function () {
         var $input = $(this);
         var $formField = $input.closest(".form-group");

         if ($input.val()) {
            $formField.find(".error-display").remove();

            if ($input.is(".basic-character")) {
               if (!regexBasicCharacter.test($input.val())) {
                  console.log("ada .,/()@&_-");
                  $input.addClass("error-field");
                  if ($(this).parents(".form-group").find(".error-display").length == 0) {
                     $formField.find(".error-display").text("Allowed special character .,/()@&_-");
                  } else {
                     $formField.append(`<div class='error-display' style='color: red'>Allowed special character .,/()@&_-</div>`);
                  }
               }
            }
         } else {
            var formField = $input.parents(".form-group");
            if ($(this).parents(".form-group").find(".error-display").length == 0) {
               formField.append("<div class='error-display' style='color: red'>Please fill this field</div>");
            }
         }
      })

      //validate
      $('#frmRegister').validate({
         rules: {
            <portlet:namespace/>email: {required: true},
            <portlet:namespace/>name: {required: true},
            <portlet:namespace/>phone: {required: true},
            <portlet:namespace/>company: {required: true},
         }
      });

      $('#vId').val(acaraId);

      $('#kembali').click(function () {
         window.location.href = "/group/dealink/calendar-event";
      });
      /* daftar */
      var maxGroup = 10;

      $(".addMore").click(function () {
         $(".error-display").remove();

         var seq = $('body').find('.fieldGroup').length;
         if (seq < maxGroup) {
            seq += 1;
            $('#fieldGroupCopy').find('.group-form').attr('id', 'group-' + seq);
            $('#fieldGroupCopy').find('.group-form').attr('data-id', seq);
            $('#fieldGroupCopy').find('.select-email').attr('id', 'selectEmail' + seq);
            $('#fieldGroupCopy').find('.select-email').attr('data-id', seq);
            $('#fieldGroupCopy').find('.select-name').attr('id', 'selectName' + seq);
            $('#fieldGroupCopy').find('.select-phone').attr('id', 'selectPhone' + seq);
            $('#fieldGroupCopy').find('.select-company').attr('id', 'selectCompany' + seq);
            $('#fieldGroupCopy').find('.select-jabatanspesifik').attr('id', 'jabatanSpesifik' + seq);
            $('#fieldGroupCopy').find('.select-jabatan').attr('id', 'selectJabatan' + seq);
            $('#fieldGroupCopy').find('.select-file').attr('id', 'file' + seq);
            $('#fieldGroupCopy').find('.select-file').attr('onchange', 'addFile(' + seq + ');');
            $('#fieldGroupCopy').find('.select-file-name').attr('id', 'fileName' + seq);

            var fieldHTML = '<div class="fieldGroup">' + $("#fieldGroupCopy").html() + '</div>';
            $('body').find('.fieldGroup:last').after(fieldHTML);
            if ($('body').find('.fieldGroup').length > 9) {
               $('.addMore').hide();
            }
         } else {
            alert('Maximum ' + maxGroup + ' groups are allowed.');
         }
      });

      //remove fields group
      $("body").on("click", "#remove", function () {
         $(this).parents(".fieldGroup").remove();
         $('.addMore').show();
      });

      //show form
      $("#btnDaftar").on("click", function () {
         var modalId = $(this).data("target");
         $(modalId).modal();
      });


      /* post */
      /*start*/
      $("form#frmRegister").submit(function (e) {
         console.log("masuk submit daftar tidak");
         e.preventDefault();
         var formData = new FormData(this);
         formData.set('<portlet:namespace/>p_auth', Liferay.authToken);
         var valid = false;

         // checkValidation();

         // if ($(this).valid() == true) {
         //     valid = true;
         // }else{
         //     return;
         // }

         if (checkValidation()) {
            $(this).parents(".custom-modal").fadeOut();
            $(this).parents(".custom-modal").removeClass("open");
            createLoading();
            $.ajax({
               url: '${resourceURL.toString()}',
               type: 'POST',
               data: formData,
               success: function (data) {
                  if (data == "success") {
                     Swal.fire({
                        icon: "success",
                        title: "Information",
                        text: "Data peserta berhasil tersimpan",
                        type: 'success',
                        showCancelButton: false,
                        confirmButtonText: 'Ok'
                     }).then(function (val) {
                        if (val.value) {
                           location.reload();
                        }
                     });
                  } else if (data == "waktuHabis") {
                     Swal.fire({
                        icon: "info",
                        title: "Information",
                        text: "Waktu Pendaftaran Telah Habis ",
                        type: "info",
                        confirmButtonText: 'Ok'
                     }).then(function (val) {
                        if (val.value) {
                           location.reload();
                        }
                     });
                  } else {
                     Swal.fire({
                        icon: "error",
                        text: "Error",
                        type: "info",
                        confirmButtonText: 'Ok'
                     });
                  }
                  destroyLoading();
               }, error: function () {
                  destroyLoading();
                  Swal.fire(
                     'Failed!',
                     'Koneksi error, Silakan kontak administrator',
                     'error'
                  )
               },
               cache: false,
               contentType: false,
               processData: false,
            });
         }
      });

      /* more agenda */
      $.ajax({
         url: '${resourceURL.toString()}',
         type: 'POST',
         data: {
            <portlet:namespace/>state: "datalist",
            <portlet:namespace/>id: acaraId,
            <portlet:namespace/>p_auth: Liferay.authToken
         },
         success: function (data) {
            var result = $.parseJSON(data);
            var html = "";
            var regex = /(<([^>]+)>)/gi;
            $.each(result.data, function (k, row) {
               console.log(row);
               var desc = row.desc;
               desc = desc.replace(regex, '');
               html += '<div class="panel">' +
                  '<div class="panel-body">' +
                  '<div class="ml-15">' +
                  '<a href="' + row.url + '" title="' + $("<span>").text(row.title).html() + '" class="title-agenda"><b>' + $("<span>").text(row.title).html() + '</b></a>' +
                  '<p>' +
                  '<small style="font-size: 13px;">' + row.day + ', ' + row.date + '</small>' +
                  '</p>' +
                  '<p style="flex-flow: wrap;font-size: 13px;">' + desc.substr(0, 70) + '...</p>' +
                  '</div>' +
                  '</div>' +
                  '</div>';
            });
            $('#resultList').empty().append(html);
         },
         error: function (e) {
            console.log(e);
         }
      });

      /* getbyid */
      $.ajax({
         url: '${resourceURL.toString()}',
         type: 'POST',
         data: {
            <portlet:namespace/>state: "getbyid",
            <portlet:namespace/>id: acaraId,
            <portlet:namespace/>p_auth: Liferay.authToken
         },
         success: function (data) {
            var result = $.parseJSON(data);
            var row = result.data;

            var $html = '<div><div>' +
               '<img src="' + row.image + '" class="img-responsive border-radius-5" title="' + row.title + '" style="width: 100%;">' +
               '</div><div class="detail-acara">' +
               '<h2>Details</h2>' +
               '<p>' + row.desc + '</p></div></div>';

            $('#resultDetail').empty().append($html);
            $(".ql-editor").attr("contenteditable", "false");

            var status;
            if (row.status === 'Tersedia') {
               status = row.title + ' <small><span class="post-tag" style="color: black;">' + row.status + '</span></small>';
            } else {
               status = row.title + ' <small><span class="post-tag-old" style="color: white;">' + row.status + '</span></small>';
            }

            var isOpen = row.isOpen;
            if (isOpen === 'false' || isOpen === false) {
               $('#btnDaftar').hide();
            }

            $('#headTitle').empty().append(status);

            if (row.jenisAcara === 0) {
               var lokasi = row.day + ', ' + row.date + ' | ' + row.lokasi;
               $('#headLokasi').text(lokasi);
            } else {
               var link = row.day + ', ' + row.date + ' | online: <a href="' + row.lokasi + '"> link meeting </a>';
               $('#headLokasi').empty().append(link);
            }
         },
         error: function (e) {
            console.log(e);
         }
      });

      /* more company */
      $.ajax({
         url: '${resourceURL.toString()}',
         type: 'POST',
         data: {
            <portlet:namespace/>state: "company",
            <portlet:namespace/>id: acaraId,
            <portlet:namespace/>p_auth: Liferay.authToken
         },
         success: function (data) {
            var result = $.parseJSON(data);
            var html = "<option value=''>-- Pilih --</option>";
            $.each(result.data, function (k, row) {
               html += '<option value="' + row.id + '">' + $("<span>").text(row.text).html() + '</option>';
            });

            $('.select-company').empty().append(html);
         },
         error: function (e) {
            console.log(e);
         }
      });
   });

   function checkValidation() {
      var boolValid = true;
      $(".error-display").remove();

      $("#modalRegisterPeserta input.form-required, #modalRegisterPeserta select.form-required").each(function () {
         var value = $(this).val();
         console.log("select bagian");
         console.log(value);
         console.log("!value : " + !value);
         console.log("select : " + $(this).is("select"));
         console.log("hasil  : " + ($(this).is("select") && value === ""));
         console.log("regexBasicCharacter : " + !regexBasicCharacter.test(value));
         console.log("============================");

         if (!value || ($(this).is("select") && value === "")) {
            boolValid = false;
            var formField = $(this).parents(".form-group");
            if (formField.find(".error-display").length === 0) {
               formField.append("<div class='error-display' style='color: red'>Please fill this field</div>");
            }
         } else if (!regexBasicCharacter.test(value)) {
            boolValid = false;
            var formField = $(this).parents(".form-group");
            if (formField.find(".error-display").length === 0) {
               formField.append("<div class='error-display' style='color: red'>Allowed special character .,/()@&_-</div>");
            }
         }

         console.log(boolValid);
      });

      console.log("akhir : " + boolValid);
      return boolValid;
   }

   function addFile(seq) {
      document.getElementById("fileName" + seq).value = document.getElementById('file' + seq).files.item(0).name;
   }
</script>