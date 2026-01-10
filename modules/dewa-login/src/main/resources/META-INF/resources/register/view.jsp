<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/register/cabangs" var="registerCabangURL"/>
<portlet:resourceURL id="/register/dealers" var="registerDealerURL"/>
<portlet:resourceURL id="/register/roles" var="registerRoleURL"/>
<portlet:resourceURL id="/email-domains" var="emailDomainURL"/>
<portlet:resourceURL id="/register/action" var="registerActionURL"/>
<portlet:resourceURL id="/register-check-email" var="registerEmailCheckURL"/>
<portlet:resourceURL id="/otp-action" var="otpActionURL"/>
<portlet:resourceURL id="/register-captchaImage" var="registerCaptchaImageURL"/>

<link href="<%= request.getContextPath() %>/register/main.css?t=<%= System.currentTimeMillis() %>" rel="stylesheet" type="text/css" />

<form data-toggle="validator" role="form" id="formRegistrationUser" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
   <div class="container" style="margin-left: 0;">
      <div style="padding-left: 15px;">
         <div class="title-register">Register</div>
         <div id="form-title">Fill in the form below to create your account.</div>
      </div>
      <input type="hidden" name="entryId">
      <div class="row">
         <div class="form-group col-md-12 regis-field">
            <label class="title-form pull-left" for="fullName">
               <b class="field-title">Nama Lengkap</b>
               <span style="color: red;">*</span>
            </label>
            <input type="text" class="form-control" name="fullName" id="fullName"
                   pattern="[A-Za-z0-9. ]+" data-minlength="5" maxlength="100"
                   placeholder="Ketik nama lengkap anda"
                   data-error="Text dengan 5-100 karakter & tidak boleh kosong." required/>
            <div class="help-block with-errors"></div>
         </div>

         <div class="form-group col-md-12 regis-field email-field">
            <div class="col-md-6">
               <label class="title-form pull-left" for="email">
                  <b class="field-title">Alamat Email</b>
                  <span style="color: red;">*</span>
               </label>
               <input type="text" class="form-control" name="email" id="email"
                     pattern="[A-Za-z0-9.]+" data-minlength="5" maxlength="100"
                     placeholder="Ketikkan email anda"
                     data-error="Text dengan 5-100 karakter & tidak boleh kosong." required/>
               <div class="help-block with-errors"></div>
            </div>
            <div class="col-md-6">
               <label class="title-form" for="domain">
                  <b class="field-title">Domain Email</b>
                  <span style="color: red;">*</span>
               </label>
               <div class="input-group" id="_verify_otp">
                  <input type="hidden" class="form-control" name="generatedEmail"/>
                  <input type="hidden" class="form-control" name="verifyEmailButton"/>
                  <select class="form-control" name="domain" id="domain"></select>
                  <div class="input-group-addon btn btn-verify" data-target="#verifyEmailModal" onclick="verifyEmail(this)">Verify</div>
               </div>
            </div>
         </div>

         <div class="form-group col-md-12 regis-field">
            <label class="title-form pull-left" for="roleUserId">
               <b class="field-title">Tipe User</b>
               <span style="color: red;">*</span>
            </label>
            <input type="hidden" class="form-control" name="roleUserId"/>
            <select class="form-control" name="roleUserId" id="roleUserId" style="width: 100%;"></select>
            <div class="help-block with-errors"></div>
         </div>

         <div class="form-group col-md-12 regis-field">
            <div class="roles-container"></div>
         </div>

         <div class="form-group col-md-12 regis-field">
            <label class="title-form pull-left" for="dealerId">
               <b class="field-title">Nama Dealer</b>
               <span style="color: red;">*</span>
            </label>
            <input type="hidden" class="form-control" name="dealerId"/>
            <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;"></select>
            <div class="help-block with-errors"></div>
         </div>

         <div class="form-group col-md-12 regis-field">
            <label class="title-form pull-left" for="cabangDealerId">
               <b class="field-title">Cabang Dealer</b>
               <span style="color: red;">*</span></label>
            <input type="hidden" class="form-control" name="cabangDealerId"/>
            <select class="form-control" name="cabangDealerId" id="cabangDealerId" style="width: 100%;"></select>
            <div class="help-block with-errors"></div>
         </div>

         <div class="form-group col-md-12 regis-field" style="display: flex;flex-direction: column;">
            <label class="title-form pull-left" for="captcha">
               <b class="field-title">Verify Code</b>
               <span style="color: red;">*</span>
            </label>
            <div class="captcha-field input-group">
               <input class="form-control input-captcha" type="text" name="<porlet:namespace/>captchaText" required
                     placeholder="Enter Captcha" id="captcha"/>
               <div class="input-group-addon btn btn-reload-captcha" type="button" onclick="refreshCaptcha()">
                  <img src="<%=request.getContextPath()%>/assets/img/rotate-solid.svg" style="width: 20px" alt="">
               </div>
               <div class="input-group-addon captcha-field-image">
                  <img id="captchaImage" src="${registerCaptchaImageURL}" alt="Captcha Image" style="width: 100%; height: 100%;"/>
               </div>
            </div>
            <div class="help-block with-errors"></div>
         </div>

         <div class="col-md-9 section-btn-regist">
            <button class="btn btn-ipr" type="button" id="registerNewUser">Register</button>
         </div>

         <%--
         <div class="col-md-9 section-btn-regist">
            <button class="btn btn-ipr" type="button" data-target="#modalTnC" onclick="verifyTnC(this)">Register</button>
         </div>
         --%>
      </div>
   </div>
</form>

<div class="custom-modal" id="verifyEmailModal">
   <div class="custom-modal-content">
      <div class="form" id="frmVerify" action="">
         <div class="custom-modal-header header-verify-mail">
            <div class="custom-modal-title" id="divisionModalTitle">Masukkkan Kode Verifikasi Email</div>
         </div>
         <div class="custom-modal-body">
            <div class="fieldGroup">
               <input class="input-otp" type="text" inputmode="numeric" maxlength="6" required placeholder="Masukkkan kode verifikasi" name="otpNumber" id="otpNumber">
            </div>
            <div style="text-align: center; color: #474747; font-size: 12px;">Mohon tunggu dalam
               <span id="initial">0</span>
               detik untuk kirim ulang!
            </div>
            <div class="container-resend">
               <a id="resend-otp" class="resend-otp" onclick="resendOTP()">Kirim Ulang OTP</a>
            </div>
         </div>
         <div class="custom-modal-footer footer-button">
            <button class="btn btnBlue verify-otp" onclick="validateOTP()">Verify</button>
         </div>
      </div>
   </div>
</div>

<%--
<div class="custom-modal" id="modalTnC">
   <div class="custom-modal-content">
      <div class="form" id="formTnC" style="text-align: center">
         <div class="custom-modal-header tnc-modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
         </div>
         <div class="custom-modal-body">
            <h3>Term & Condition</h3>
            <div>
               1. xxxxxxxxxxxx <br>
               2. xxxxxxxxxxxx <br>
               3. xxxxxxxxxxxx <br>
               4. xxxxxxxxxxxx <br>
            </div>
         </div>
         <div class="custom-modal-footer footer-button">
            <button class="btn btnBlue" type="submit" id="registerNewUser">Agree</button>
         </div>
      </div>
   </div>
</div>
--%>

<script>
   const loginURL = "/web/dealink/login";
   var roleSelected = false;
   var dealerSelected = false;
   var roleGroupId = -1;
   var emailLocalPartRegex = new RegExp(/^[a-zA-Z0-9._%+-]+$/);

   $(document).ready(function () {
      $('.help-block').hide();
      getDomains();
      getRoles();
      getDealers();
      getCabang();
   });

   function getDomains() {
      $.ajax({
         url: "${emailDomainURL}",
         type: "POST",
         data: {"windowsId": "test"},
         success: function (response) {
            var responseData = JSON.parse(response);
            var domainConst = $('#domain').select2({
               data: responseData.Data,
               placeholder: 'Pilih Domain Email',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  return data.text;
               }
            });
            domainConst.val(null).trigger('change');
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
      if (roleSelected) {
         $('#dealerId').prop('disabled', false);
         var dealerConst;

         $.ajax({
            url: "${registerDealerURL}",
            type: "POST",
            data: {"windowsId": "test"},
            success: function (response) {
               var responseData = JSON.parse(response);
               dealerConst = $('#dealerId').select2({
                  data: responseData.Data,
                  placeholder: 'Pilih Nama Dealer',
                  allowClear: false,
                  maximumInputLength: 100,
                  templateSelection: function (data) {
                     $('[name="dealerId"]')[0].value = data.id;
                     return data.text;
                  }
               });
               dealerConst.val(null).trigger('change');
            },
            error: function (error) {
               console.log(error);
            },
            complete: function () {
               dealerSelected = false;
               dealerConst.on('select2:select', function () {
                  dealerSelected = true;
                  getCabang();
               });
               console.log("complete");
            }
         });
      } else {
         $('#dealerId').select2({
            placeholder: 'Pilih Nama Dealer'
         });
         $('#dealerId').prop('disabled', true);
      }
   }

   function getCabang() {
      if (dealerSelected) {
         $('#cabangDealerId').prop('disabled', false);
         var dealerId = $('#dealerId').val();
         var cabangConst;

         $.ajax({
            url: "${registerCabangURL}",
            type: "POST",
            data: {
               "windowsId": "test",
               "dealerId": dealerId,
               "groupId": roleGroupId
            },
            success: function (response) {
               var responseData = JSON.parse(response);
               $('#cabangDealerId').empty().trigger('change');
               cabangConst = $('#cabangDealerId').select2({
                  data: responseData.Data,
                  placeholder: 'Pilih Cabang Dealer',
                  allowClear: false,
                  maximumInputLength: 100,
                  templateSelection: function (data) {
                     $('[name="cabangDealerId"]')[0].value = data.id;
                     return data.text;
                  }
               });
               cabangConst.val(null).trigger('change');
            },
            error: function (error) {
               console.log(error);
            },
            complete: function () {
               console.log("complete");
            }
         });
      } else {
         $('#cabangDealerId').select2({
            placeholder: 'Pilih Cabang Dealer'
         });
         $('#cabangDealerId').prop('disabled', true);
      }
   }

   function getRoles() {
      $.ajax({
         url: "${registerRoleURL}",
         type: "POST",
         data: { "windowsId": "test" },
         success: function (response) {
            var responseData = JSON.parse(response);
            $('#roleUserId').empty().trigger('change');
            var roleConst = $('#roleUserId').select2({
               data: responseData.Data,
               placeholder: 'Pilih Role',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="roleUserId"]')[0].value = data.id;
                  return data.text;
               }
            });
            roleConst.val(null).trigger('change');
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            handleRoleChange();
            console.log("complete");
         }
      });
   }

   function validateRole(item, selectedItem) {
      var roleIds = [];

      $('.roles-container .selected-role').each(function () {
          var roleId = { id: $(this).data('value') };
          roleIds.push(roleId);
      });

      if ($('.roles-container .selected-role').length > 0) {
         roleSelected = true;
         var formData = new FormData();
         formData.set('<portlet:namespace/>action', 'VALIDATE');
         formData.set('<portlet:namespace/>roles', JSON.stringify(roleIds));

         $.ajax({
            url: '${registerActionURL}',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
               var status = JSON.parse(response).status;
               var message = JSON.parse(response).message;
               if (status === 'warning') {
                  Swal.fire('Sorry', message, status).then(function () {
                     item.remove();
                     $(selectedItem).prop('disabled', false);
                  });
               } else if (status === 'success') {
                  roleGroupId = JSON.parse(response).entryId;
                  getDealers();
               }
            }
         });
      } else {
         roleSelected = false;
         getDealers();
         dealerSelected = false;
         getCabang();
      }
   }

   function handleRoleChange() {
      $('#roleUserId').on('change', function() {
         var selectedValues = $(this).val();
         var $container = $('.roles-container');

         if (!Array.isArray(selectedValues)) {
            selectedValues = selectedValues ? [selectedValues] : [];
         }

         if (selectedValues) {
            $.each(selectedValues, function(index, value) {
               var text = $('#roleUserId option[value="' + value + '"]').text();
               var existingItem = $container.find('.selected-role[data-value="' + value + '"]');
               $('#roleUserId option[value="' + value + '"]').prop('disabled', true);
               if (existingItem.length) {
                  existingItem.remove();
               } else {
                  var item = $('<div class="selected-role" data-value="' + value + '">' + text + ' <span class="remove-role">&times;</span></div>');
                  $container.append(item);
                  var selectedItem = '#roleUserId option[value="' + value + '"]';
                  validateRole(item, selectedItem);
               }
            });
         }
      });

      $('#roleUserId').on('select2:close', function (e) {
         $('#roleUserId').val(null).trigger('change');
         if ($('.roles-container .selected-role').length === 0) {
            validateRole('', '');
         }
      });

      // Handle click event on remove item
      $('.roles-container').on('click', '.remove-role', function() {
         var value = $(this).parent().data('value');
         $('.roles-container').find('.selected-role[data-value="' + value + '"]').remove();
         $('#roleUserId option[value="' + value + '"]').prop('disabled', false);
         $('#roleUserId').val(null).trigger('change');

         if ($('.roles-container .selected-role').length === 0) {
            validateRole('', '');
         }
      });
   }

   function verifyEmail(element) {
      const email = $('#email').val();
      const domainId = $('#domain').val();

      if (!email) {
         Swal.fire("Email belum terisi", "", "warning");
      } else if (!emailLocalPartRegex.test(email)) {
         Swal.fire("Email hanya boleh diisi dengan karakter ., _, %, +, -", "", "warning");
      } else if (null === domainId) {
         Swal.fire("Domain belum dipilih", "", "warning");
      } else {
         var formData = new FormData();
         formData.set("<portlet:namespace/>email", email);
         formData.set("<portlet:namespace/>domainId", domainId);

         $.ajax({
            url: '${registerEmailCheckURL}',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
               var status = JSON.parse(response).status;
               var message = JSON.parse(response).message;
               var generatedEmail = JSON.parse(response).email;
               if (status === 'success') {
                  var kodeVerifikasiElemen = document.getElementById("kode-verifikasi-email");
                  if (kodeVerifikasiElemen) {
                     kodeVerifikasiElemen.parentNode.removeChild(kodeVerifikasiElemen);
                  }
                  var modalId = $(element).data("target");
                  $(modalId).modal();
                  var html = $('<div id="kode-verifikasi-email"> Kode verifikasi telah dikirimkan melalui email ' + generatedEmail + '</div>');
                  if ($('#kode-verifikasi-email').length) { $('#kode-verifikasi-email').remove(); }
                  $('.header-verify-mail').append(html);
                  $('[name="generatedEmail"]')[0].value = generatedEmail;
                  sendOTP();
                  setUp();
               } else if (status === 'error') {
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
            }
         })
      }
   }

   <%--
   function verifyTnC(element) {
      const fullName = $('[name="fullName"]').val();
      const email = $('#email').val();
      const domainId = $('#domain').val();
      const generatedEmail = $('[name="generatedEmail"]').val();
      const roles = $('.roles-container .selected-role').length;
      const dealerId = $('#dealerId').val();
      const cabangDealerId = $('#cabangDealerId').val();
      const captcha = $('#captcha').val();
      const verifyEmailButton = $('[name="verifyEmailButton"]').val();
      const roleCount = $('.roles-container .selected-role').length;

      if (submitProcess === false) {
         if (fullName === null || fullName === undefined || fullName.length < 5) {
            Swal.fire("Nama lengkap belum terisi atau nama lengkap harus 5-100 karakter.", "", "warning");
            return false;
         } else if (!regexBasicCharacter.test(fullName)) {
            Swal.fire("Nama lengkap hanya boleh diisi dengan karakter .,/()@&_-", "", "warning");
            return false;
         } else if (!email) {
            Swal.fire("Email belum terisi", "", "warning");
            return false;
         } else if (!emailLocalPartRegex.test(email)) {
            Swal.fire("Email hanya boleh diisi dengan karakter ., _, %, +, -", "", "warning");
            return false;
         } else if (null === domainId) {
            Swal.fire("Domain belum dipilih", "", "warning");
            return false;
         } else if (generatedEmail === null || generatedEmail === undefined || generatedEmail.length < 5) {
            Swal.fire("Email belum terverifikasi.", "", "warning");
            return false;
         } else if (!regexEmail.test(generatedEmail)) {
            Swal.fire("Email hanya boleh diisi dengan karakter ., _, -, +");
         } else if (roles < 1) {
            Swal.fire("Tidak ada role yang dipilih", "", "warning");
         } else if (verifyEmailButton !== '1') {
            Swal.fire("Email belum terverifikasi.", "", "warning");
            return false;
         } else if (roleCount === 0) {
            Swal.fire("Role belum dipilih.", "", "warning");
            return false;
         } else if (dealerId === null || dealerId === undefined || dealerId.length < 1) {
            Swal.fire("Dealer belum dipilih.", "", "warning");
            return false;
         } else if (cabangDealerId === null || cabangDealerId === undefined || cabangDealerId.length < 1) {
            Swal.fire("Cabang dealer belum dipilih.", "", "warning");
            return false;
         } else if (captcha === null || captcha === undefined || captcha.length < 1) {
            Swal.fire("Captcha belum terisi.", "", "warning");
            return false;
         } else {
            var modalId = $(element).data("target");
            $(modalId).modal({
               clickClose: false,
               escapeClose: false,
               showClose: false,
               backdrop: 'static',
               keyboard: false
            });
         }
      }
   }
   --%>

   var timer = null;

   function setUp() {
      $('#resend-otp').remove();
      var element = document.getElementById("initial");
      if (timer != null) {
         clearInterval(timer);
      }
      element.innerHTML = 30;

      timer = setInterval(function () {
         var value = element.innerHTML;
         value--;
         element.innerHTML = value;
         if (value === 0) {
            clearInterval(timer);
            var html = '<a id="resend-otp" class="resend-otp" onclick="resendOTP()">Kirim Ulang OTP</a>'
            $('.container-resend').append(html);
         }
      }, 1000);
   }

   function resendOTP() {
      sendOTP();
      setUp();
   }

   function refreshCaptcha() {
      var captchaImage = document.getElementById("captchaImage");
      var timestamp = new Date().getTime();
      captchaImage.src = "${registerCaptchaImageURL}&t=" + timestamp;
   }

   function sendOTP() {
      const email = $('#email').val();
      const domainId = $('#domain').val();

      var formData = new FormData();
      formData.set('<portlet:namespace/>email', email);
      formData.set('<portlet:namespace/>domainId', domainId);
      formData.set('<portlet:namespace/>state', 'SEND');

      $.ajax({
         url: '${otpActionURL}',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            if (JSON.parse(response).status === 'success') {
               console.log("send otp");
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
         }
      })
   }

   function validateOTP() {
      const otpNumber = $('[name="otpNumber"]').val();
      const email = $('#email').val();
      const domainId = $('#domain').val();

      var formData = new FormData();
      formData.set('<portlet:namespace/>email', email);
      formData.set('<portlet:namespace/>domainId', domainId);
      formData.set('<portlet:namespace/>otp', otpNumber);
      formData.set('<portlet:namespace/>state', 'VALIDATE');

      $.ajax({
         url: '${otpActionURL}',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            var status = JSON.parse(response).status;
            var message = JSON.parse(response).message;
            if (status === 'success') {
               swal.fire('Success', message, 'success')
                     .then(function () {
                        $("#verifyEmailModal").modal("hide");
                        $(".btn-verify").remove();
                        var html = '<div class="input-group-addon btn-verified" >Verified</div>';
                        $('#_verify_otp').append(html);
                        $('#email').prop('readonly', true);
                        $('[name="verifyEmailButton"]').val('1');
                     });
            } else if (status === 'warning') {
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
         }
      })
   }

   var submitProcess = false;
   $('#registerNewUser').click(function () {
      const fullName = $('[name="fullName"]').val();
      const email = $('#email').val();
      const domainId = $('#domain').val();
      const generatedEmail = $('[name="generatedEmail"]').val();
      const otpNumber = $('[name="otpNumber"]').val();
      const dealerId = $('#dealerId').val();
      const cabangDealerId = $('#cabangDealerId').val();
      const captcha = $('#captcha').val();
      const verifyEmailButton = $('[name="verifyEmailButton"]').val();
      const roleCount = $('.roles-container .selected-role').length;

      if (submitProcess === false) {
         if (fullName === null || fullName === undefined || fullName.length < 5) {
            Swal.fire("Nama lengkap belum terisi atau nama lengkap harus 5-100 karakter.", "", "warning");
            return false;
         } else if (!regexBasicCharacter.test(fullName)) {
            Swal.fire("Nama lengkap hanya boleh diisi dengan karakter .,/()@&_-", "", "warning");
            return false;
         } if (!email) {
            Swal.fire("Email belum terisi", "", "warning");
            return false;
         } else if (!emailLocalPartRegex.test(email)) {
            Swal.fire("Email hanya boleh diisi dengan karakter ., _, %, +, -", "", "warning");
            return false;
         } else if (null === domainId) {
            Swal.fire("Domain belum dipilih", "", "warning");
            return false;
         } else if (generatedEmail === null || generatedEmail === undefined || generatedEmail.length < 5) {
            Swal.fire("Email belum terverifikasi.", "", "warning");
            return false;
         } else if (verifyEmailButton !== '1') {
            Swal.fire("Email belum terverifikasi.", "", "warning");
            return false;
         } else if (roleCount === 0) {
            Swal.fire("Role belum dipilih.", "", "warning");
            return false;
         } else if (dealerId === null || dealerId === undefined || dealerId.length < 1) {
            Swal.fire("Dealer belum dipilih.", "", "warning");
            return false;
         } else if (cabangDealerId === null || cabangDealerId === undefined || cabangDealerId.length < 1) {
            Swal.fire("Cabang dealer belum dipilih.", "", "warning");
            return false;
         } else if (captcha === null || captcha === undefined || captcha.length < 1) {
            Swal.fire("Captcha belum terisi.", "", "warning");
            return false;
         } else {
            var roleIds = [];

            $('.roles-container .selected-role').each(function () {
                var roleId = { id: $(this).data('value') };
                roleIds.push(roleId);
            });

            var formData = new FormData();
            formData.set('<portlet:namespace/>action', "REGISTER");
            formData.set('<portlet:namespace/>fullName', fullName);
            formData.set('<portlet:namespace/>roles', JSON.stringify(roleIds));
            formData.set('<portlet:namespace/>email', generatedEmail);
            formData.set('<portlet:namespace/>dealerId', dealerId);
            formData.set('<portlet:namespace/>cabangDealerId', cabangDealerId);
            formData.set('<portlet:namespace/>captchaText', captcha);
            formData.set('<portlet:namespace/>otpNumber', otpNumber);

            createLoading();
            $.ajax({
               url: '${registerActionURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  submitProcess = true;
                  var status = JSON.parse(response).status;
                  var message = JSON.parse(response).message;

                  if (status === 'success') {
                     <%-- $("#modalTnC").modal("hide"); --%>
                     Swal.fire({
                        icon: 'success',
                        title: 'Success',
                        html: 'Successfully Registered </br> Please wait HO DSO approve your register'
                     }).then(function () {
                        window.location.href = loginURL;
                     });
                  } else if (message === 'CAPTCHA verification failed') {
                     Swal.fire('', message, 'warning');
                  } else if (status === 'warning') {
                     Swal.fire('', message, 'warning');
                  } else {
                     Swal.fire('', 'There is an internal error', 'error');
                  }
               },
               error: function (data, textStatus, XMLHttpRequest) {
                  if (data.status === 500) {
                     var msg = data.responseJSON.Message
                     Swal('Sorry', msg, 'error');
                  } else if (data.status === 408) {
                     Swal('Sorry', 'Request Time Out, Please Try Again', 'error');
                  } else {
                     Swal('Sorry', textStatus + 'error submit', 'error');
                  }
               },
               complete: function (jqXHR, textStatus) {
                  submitProcess = false;
                  destroyLoading();
               }
            });
         }
      }
   });

   <%--
   $('#registerNewUser').click(function () {
      const fullName = $('[name="fullName"]').val();
      const email = $('[name="generatedEmail"]').val();
      const dealerId = $('#dealerId').val();
      const cabangDealerId = $('#cabangDealerId').val();
      const captcha = $('#captcha').val();

      var roleIds = [];

      $('.roles-container .selected-role').each(function () {
          var roleId = { id: $(this).data('value') };
          roleIds.push(roleId);
      });

      var formData = new FormData();
      formData.set('<portlet:namespace/>action', "REGISTER");
      formData.set('<portlet:namespace/>fullName', fullName);
      formData.set('<portlet:namespace/>roles', JSON.stringify(roleIds));
      formData.set('<portlet:namespace/>email', email);
      formData.set('<portlet:namespace/>dealerId', dealerId);
      formData.set('<portlet:namespace/>cabangDealerId', cabangDealerId);
      formData.set('<portlet:namespace/>captchaText', captcha);

      createLoading();
      $.ajax({
         url: '${registerActionURL}',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            submitProcess = true;
            if (JSON.parse(response).status === 'success') {
               $("#modalTnC").modal("hide");
               Swal.fire({
                  icon: 'success',
                  title: 'Success',
                  html: 'Successfully Registered </br> Please wait HO DSO approve your register'
               }).then(function () {
                  window.location.href = loginURL;
               });
            } else if (JSON.parse(response).message === 'CAPTCHA verification failed') {
               swal.fire('Sorry', JSON.parse(response).message, 'warning');
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
      });
   });
   --%>
</script>