<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/master-role-action" var="masterRoleActionURL"/>
<portlet:resourceURL id="/otorisasi/rssp-roles" var="rsspRoleListURL"/>

<style>
   /* Select2 */
   .select2-container--default .select2-selection--single {
      padding: 6px;
      height: 37px;
      font-size: 1.2em;
      position: relative;
   }

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

<form method="post" enctype="multipart/form-data" novalidate autocomplete="off">
   <div class="container" style="margin-left: 0;">
      <h3 id="form-title" style="margin-bottom: 50px;"></h3>
      <div class="row">
         <div class="form-group col-md-9">
            <label class="title-form pull-left">
               <b class="field-title">Role</b>
               <span style="color: red;">*</span>
            </label>
            <input type="text" class="form-control" id="roleName" name="roleName">
         </div>

         <div class="form-group col-md-9 regis-field">
            <label class="title-form pull-left" for="roleId">
               <b class="field-title">Otorisasi Approval</b>
            </label>
            <input type="hidden" class="form-control" name="roleId"/>
            <select class="form-control" name="roleId" id="roleId" style="width: 100%;"></select>
            <div class="help-block with-errors"></div>
         </div>

         <div class="form-group col-md-9 regis-field">
            <div class="roles-container"></div>
         </div>

         <div class="form-group col-md-9">
            <button id="submitRole" class="btn-ipr" type="submit">Save</button>
            <a class="btn btn-ipr-cancel" id="backButtonDocFlow" onclick="backToUM()">Cancel</a>
         </div>
      </div>
   </div>
</form>


<script>
    var editMenu;
    var action = "${action}";
    var homeUrl = "/group/dealink/cms-master-role";
    var submitProcess = false;
    var id = 0;

   $(document).ready(function () {
      if (action === 'update') {
         editMenu = ${editMenu}
         $("#roleName").val(editMenu.name);
         $("#form-title").text("Edit Role");
      } else {
         $("#form-title").text("Tambah Role");
      }
      getRsspRoles();
   });

   function backToUM() {
      window.location.href = homeUrl;
   }

   function generateRoleItems(id, text) {
      var element = $('<div class="selected-role" data-value="' + id + '">' + text + ' <span class="remove-role">&times;</span></div>');
      return element;
   }

   function getRsspRoles() {
      var roleConst;
      $.ajax({
         url: "${rsspRoleListURL}",
         type: "GET",
         success: function (response) {
            var responseData = JSON.parse(response);
            roleConst = $('#roleId').select2({
               data: responseData.Data,
               tags: false,
               placeholder: 'Pilih Role',
               allowClear: false,
               minimumResultsForSearch: -1,
               maximumInputLength: 15,
               templateSelection: function (data) {
                  if (data.id === 'Pilih Role') {
                     return 'Pilih Role';
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
            if (action === 'update') {
               var $container = $('.roles-container');
               editMenu.roleMember.forEach(item => {
                  $container.append(generateRoleItems(item.id, item.name));
                  $('#roleId option[value="' + item.id + '"]').prop('disabled', true);
               });
            }
            console.log("complete");
         }
      });
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
               if (existingItem.length) {
                  existingItem.remove();
               } else {
                  var item = $('<div class="selected-role" data-value="' + value + '">' + text + ' <span class="remove-role">&times;</span></div>');
                  $container.append(item);
               }
               $('#roleId option[value="' + value + '"]').prop('disabled', true);
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
      });
   }

   $("#submitRole").click(function (event) {
      event.preventDefault();
      var roleName = $("#roleName").val().trim();
      console.log(roleName)
      if (roleName == null || roleName == "" || roleName.length == 0) {
         Swal.fire("Role belum diisi.", "", "warning");
         return false;
      }
      console.log(roleName)

      submitRole(id, roleName)
   });

   function submitRole(id, roleName) {
      roles = [];

      $('.roles-container .selected-role').each(function () {
         var roleId = { id: $(this).data('value') };
         roles.push(roleId);
      });

      var formData = new FormData();
      formData.set('<portlet:namespace/>crudType', action);
      formData.set('<portlet:namespace/>id', id);
      formData.set('<portlet:namespace/>roleName', roleName);
      formData.set('<portlet:namespace/>roles', JSON.stringify(roles));
      formData.set('<portlet:namespace/>p_auth', Liferay.authToken);

      if (action === 'update') {
         formData.set('<portlet:namespace/>id', editMenu.id);
      }

      createLoading();
      $.ajax({
         url: "${masterRoleActionURL}",
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
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
</script>
