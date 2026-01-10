<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/role-dealer-action" var="roleDealerActionURL"/>

<portlet:resourceURL id="/dealer-role-dealer" var="dealerRoleDealerURL"/>
<portlet:resourceURL id="/cabang-role-dealer" var="cabangRoleDealerURL"/>
<portlet:resourceURL id="/user-role-dealer" var="userRoleDealerURL"/>
<portlet:resourceURL id="/role-role-dealer" var="roleRoleDealerURL"/>
<portlet:resourceURL id="/user-single-role-dealer" var="userSingleRoleDealerEditURL"/>

<style>
   /* Select2 */
   .select2-container--default .select2-selection--single {
      padding: 6px;
      height: 37px;
      font-size: 1.2em;
      position: relative;
   }

   .select2-container--default.select2-container--disabled .select2-selection--single .select2-selection__rendered {
      background-color: #eee;
      color: #999;
      cursor: not-allowed;
   }
   /* == */

   .roles-container {
      min-height: 150px;
      width: 100%;
      padding: 10px;
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
      cursor: default;
   }

   .remove-role {
      cursor: pointer;
   }
</style>

<form id="formRoleDealer" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
   <div class="container" style="margin-left: 0;">
      <h3 id="form-title" style="margin-bottom: 50px;">Tambah User Role</h3>

      <div class="row">
         <div class="form-group col-md-9 userId">
            <label class="title-form pull-left" for="userId">
               Nama Lengkap
               <span style="color: red;">*</span>
            </label>
            <select class="form-control" name="userId" id="userId" style="width: 100%;"></select>
         </div>

         <div class="form-group col-md-9 fullName">
            <label class="title-form pull-left" for="fullName">
               Nama Lengkap
               <span style="color: red;">*</span>
            </label>
            <input class="form-control" type="text" name="fullName" id="fullName" readonly>
         </div>

         <div class="form-group col-md-9">
            <label for="userEmail" class="title-form pull-left">Email</label>
            <input type="text" class="form-control" name="userEmail" id="userEmail" readonly/>
         </div>

         <div class="form-group col-md-9">
            <label for="screenName" class="title-form pull-left">Username</label>
            <input type="text" class="form-control" name="screenName" id="screenName" readonly/>
         </div>

         <div class="form-group col-md-9 role-input">
            <label class="title-form pull-left" for="roleId">
               Role
               <span style="color: red;">*</span>
            </label>
            <select class="form-control" name="roleId" id="roleId" style="width: 100%;"></select>
         </div>

         <div class="form-group col-md-9">
            <div class="roles-container"></div>
         </div>

         <div class="form-group col-md-9 dealerId">
            <label class="title-form pull-left" for="dealerId">
               Kode Dealer
               <span style="color: red;">*</span>
            </label>
            <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;"
                    pattern="[0-9]+" data-minlength="5" maxlength="15"
                    data-error="Nomer 5-15 karakter & tidak boleh kosong." required>
            </select>
         </div>

         <div class="form-group col-md-9 cabangId">
            <label class="title-form pull-left" for="cabangId">Cabang <span style="color: red;">*</span></label>
            <input type="text" class="form-control" name="cabangId" id="cabangId" readonly />
         </div>

         <div class="col-md-9">
            <c:choose>
               <c:when test="${action ne 'view'}">
                  <button type="submit" class="btn-ipr">Save</button>
               </c:when>
            </c:choose>
            <button type="button" class="btn btn-ipr-cancel" id="backButtonDocFlow" onclick="backToUM()">Cancel</button>
         </div>
      </div>
   </div>
</form>

<script>
   var data = ${data};
   var roles = ${roles};
   var roleSelected = false;
   var dealerSelected = false;
   var roleGroupId;
   var entryId = 0;
   var action = "${action}";
   var homeUrl = "/group/dealink/cms-user-management";
   var loginUrl = "<%=themeDisplay.getPortalURL()%>/group/dealink/home";
   var submitProcess = false;

   $(document).ready(function () {
      getUsers();
      getRoles();
      if (action === "create") {
         $('#form-title').text("Tambah User Role");
         formNew();
      } else if (action === "update") {
         $('#form-title').text("Ubah User Role");
         formEdit();
      } else {
         $('#form-title').text('Detail User Role');
         formEdit();
      }
   });

   $('#roleId').on('select2:close', function (e) {
      $('#roleId').val(null).trigger('change');
      if ($('.roles-container .selected-role').length === 0) {
         validateRole('', '');
      }
   });

   function disableInputs() {
      $('#userId').prop('disabled', true);
      $('#roleId').prop('disabled', true);
      $('.roles-container').css({'background-color': '#eee', 'color': 'eee', 'cursor': 'not-allowed'});
      $('.roles-container .selected-role').css({'opacity': '0.5'});
      $('.remove-role').remove();
      $('#dealerId').prop('disabled', true);
   }

   function showDealer(flag) {
      if (flag === true) {
         $('.dealerId').show();
         $('.cabangId').show();
      } else {
         $('.dealerId').hide();
         $('.cabangId').hide();
      }
   }

   function backToUM() {
      window.location.href = '/group/dealink/cms-user-management';
   }

   function formNew() {
      $('.userId').show();
      $('.fullName').hide();
      $('.dealerId').hide();
      $('.cabangId').hide();
      $('[name="screenName"]')[0].value = "";
      $('[name="fullName"]')[0].value = "";
      $('[name="userEmail"]')[0].value = "";
      $('[name="roleId"]')[0].value = "";
      $('[name="dealerId"]')[0].value = "";
      $('[name="cabangId"]')[0].value = "";
   }

   function formEdit() {
      entryId = data.id;
      $('.fullName').show();
      $('.userId').hide();
      $('[name="screenName"]')[0].value = data.screenName;
      $('[name="fullName"]')[0].value = data.fullName;
      $('[name="userEmail"]')[0].value = data.userEmail;
      $('[name="roleId"]')[0].value = data.roleId;
      $('[name="dealerId"]')[0].value = data.dealerId;
      $('[name="cabangId"]')[0].value = data.cabangId;
      getUserSingle(data.userId);
      showDealer(data.roleGroup === 2 || data.roleGroup === 3);
   }

   $("#formRoleDealer").submit(function (e) {
      e.preventDefault();
      const screen = $('[name="screenName"]').val();
      const dealer = $('[name="dealerId"]').val();
      const cabang = $('[name="cabangId"]').val();
      const totalRoles = $('.roles-container .selected-role').length;

      if (!submitProcess) {
         if (screen === null || screen === undefined || screen === '') {
            Swal.fire("User belum dipilih", "", "warning");
            return false;
         } else if (totalRoles === 0) {
            Swal.fire("Role belum dipilih", "", "warning");
            return false;
         }
         processSubmit(this);
      }
   })

   function processSubmit(e) {
      roles = [];

      $('.roles-container .selected-role').each(function () {
         var roleId = { id: $(this).data('value') };
         roles.push(roleId);
      });

      var formData = new FormData(e);
      formData.set('<portlet:namespace/>p_auth', Liferay.authToken);
      formData.set('<portlet:namespace/>crudType', action);
      formData.set('<portlet:namespace/>entryId', entryId);
      formData.set('<portlet:namespace/>roles', JSON.stringify(roles));
      createLoading();
      $.ajax({
         url: "${roleDealerActionURL}",
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            submitProcess = true;
            if (JSON.parse(response).code === 302) {
               swal.fire("Success", "Your request has been saved", "success")
                  .then(function () {
                        $.ajax({
                           url: "/c/portal/logout",
                           method: 'POST',
                           success: function () {
                              window.location.href = loginUrl;
                           },
                           error: function (xhr, status, error) {
                              console.log(error);
                           }
                        });
                     }
                  );
            } else if (JSON.parse(response).status === 'success') {
               swal.fire("Success", "Your request has been saved", "success")
                  .then(function () {
                        window.location = homeUrl;
                     }
                  );
            } else if (JSON.parse(response).status === 'warning') {
               swal.fire(JSON.parse(response).message, '', 'warning');
            } else {
               swal.fire('Sorry', 'There is an internal error', 'error');
            }
         },
         error: function (data, textStatus, XMLHttpRequest) {
            if (data.status === 500) {
               var msg = data.responseJSON.Message;
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

   function getRoles() {
      $.ajax({
         url: "${roleRoleDealerURL}",
         type: "POST",
         data: {
            "windowsId": "test",
            "action": action
         },
         success: function (response) {
            var roleData = JSON.parse(response);
            var roleConst = $('#roleId').select2({
               data: roleData.Data,
               tags: false,
               placeholder: 'Cari Role',
               allowClear: false,
               minimumResultsForSearch: -1,
               maximumInputLength: 15,
               templateSelection: function (data) {
                  $('[name="roleId"]')[0].value = data.id;
                  if (data.id === 'Cari Role') {
                     return 'Cari Role';
                  }
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
            if (action === 'update' || action === 'view') {
               var $container = $('.roles-container');
               roles.forEach(item => {
                  $container.append(generateRoleItems(item.id, item.name, item.roleGroup));
                  $('#roleId option[value="' + item.id + '"]').prop('disabled', true);
               });
               roleGroupId = data.roleGroup;
               roleSelected = true;
               getDealers();
            }
            if (action === 'view') disableInputs();
            console.log("complete");
         }
      });
   }

   function generateRoleItems(id, text, roleGroup) {
      var element = $('<div class="selected-role" data-value="' + id + '" data-roleGroup="' + roleGroup + '">' + text + ' <span class="remove-role">&times;</span></div>');
      return element;
   }

   function handleRoleChange() {
      $('#roleId').on('change', function() {
         var selectedValues = $(this).val();
         var $container = $('.roles-container');

         if (!Array.isArray(selectedValues)) {
            selectedValues = selectedValues ? [selectedValues] : [];
         }

         if (selectedValues) {
            $.each(selectedValues, function(index, value) {
               var text = $('#roleId option[value="' + value + '"]').text();
               var existingItem = $container.find('.selected-role[data-value="' + value + '"]');
               $('#roleId option[value="' + value + '"]').prop('disabled', true);
               if (existingItem.length) {
                  existingItem.remove();
               } else {
                  var item = $('<div class="selected-role" data-value="' + value + '">' + text + ' <span class="remove-role">&times;</span></div>');
                  $container.append(item);
                  var selectedItem = '#roleId option[value="' + value + '"]';
                  validateRole(item, selectedItem);
               }
            });
         }
      });

      $('#roleId').on('select2:close', function (e) {
         $('#roleId').val(null).trigger('change');
      });

      // Handle click event on remove item
      $('.roles-container').on('click', '.remove-role', function() {
         var value = $(this).parent().data('value');
         $('.roles-container').find('.selected-role[data-value="' + value + '"]').remove();
         $('#roleId option[value="' + value + '"]').prop('disabled', false);
         $('#roleId').val(null).trigger('change');

         if ($('.roles-container .selected-role').length === 0) {
            validateRole('', '');
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
         formData.set('<portlet:namespace/>p_auth', Liferay.authToken);
         formData.set('<portlet:namespace/>crudType', 'VALIDATE_ROLE');
         formData.set('<portlet:namespace/>roles', JSON.stringify(roleIds));

         $.ajax({
            url: '${roleDealerActionURL}',
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
                  if (roleGroupId == 2 || roleGroupId == 3) {
                     showDealer(true);
                     getDealers();
                  } else {
                     showDealer(false);
                  }
               }
            }
         });
      } else {
         roleSelected = false;
         getDealers();
         dealerSelected = false;
         getCabangs();
      }
   }

   function getDealers() {
      if (roleSelected) {
         $('#dealerId').prop('disabled', false);
         var dealerConst;

         $.ajax({
            url: "${dealerRoleDealerURL}",
            type: "POST",
            data: {
               "roleGroupId": roleGroupId
            },
            success: function (response) {
               var responseData = JSON.parse(response);
               $('#dealerId').empty().trigger('change');
               dealerConst = $('#dealerId').select2({
                  data: responseData.Data,
                  tags: false,
                  placeholder: 'List Dealer',
                  allowClear: false,
                  maximumInputLength: 100,
                  templateSelection: function (data) {
                     if (data.id === 'List Dealer') {
                        return 'List Dealer';
                     }
                     return data.text;
                  }
               });
            },
            error: function (error) {
               console.log(error);
            },
            complete: function () {
               dealerSelected = false;
               dealerConst.on('change', function () {
                  dealerSelected = true;
                  getCabangs();
               });
               if (action === 'update' || action === 'view') {
                  dealerConst.val(data.dealerId).trigger('change');
               } else {
                  dealerConst.val(null).trigger('change');
               }
               if (action === 'view') disableInputs();
               console.log("complete");
            }
         });
      } else {
         $('#dealerId').select2({
            placeholder: 'List Dealer'
         });
         $('#dealerId').val(null).trigger('change');
         $('#dealerId').prop('disabled', true);
         $('#cabangId').val('Cabang Dealer');
      }
   }

   function getCabangs() {
      $.ajax({
         url: "${cabangRoleDealerURL}",
         type: "POST",
         data: {
            "dealerId": $('#dealerId').val()
         },
         success: function (response) {
            var cabangData = JSON.parse(response);
            if (cabangData.Data.length !== 0 && $('#dealerId').val() !== null) {
               $('#cabangId').val(cabangData.Data[0].text);
            }
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            console.log("complete");
         }
      });
   }

   function getUsers() {
      const userConst = $('#userId').select2({
         tags: false,
         minimumInputLength: 3,
         maximumInputLength: 55,
         placeholder: 'Cari User',
         allowClear: true,
         ajax: {
            url: "${userRoleDealerURL}",
            cache: false,
            dataType: 'json',
            delay: 250,
            processResults: function (response) {
               return {
                  results: response.results
               };
            }
         },
         templateSelection: function (data) {
            if (data.id === 'Cari User' || data.id === '') {
               return 'Cari User';
            } else {
               $('[name="fullName"]')[0].value = data.text
               getUserSingle(data.id)
            }
            return data.text;
         }
      });
      if (action === 'update' || action === 'view') {
         userConst.prop("disabled", true);
         userConst.val(data.userId);
         userConst.trigger('change');
      } else {
         userConst.val(null);
         userConst.trigger('change');
      }
      if (action === 'view') disableInputs();
   }

   function getUserSingle(userId) {
      $.ajax({
         url: "${userSingleRoleDealerEditURL}",
         type: "POST",
         data: {"userId": userId},
         success: function (response) {
            var userData = JSON.parse(response);
            $('[name="userId"]')[0].value = userData.Data.userId;
            $('[name="screenName"]')[0].value = userData.Data.screenName
            $('[name="userEmail"]')[0].value = userData.Data.email;
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            console.log("complete");
         }
      });
   }
</script>
