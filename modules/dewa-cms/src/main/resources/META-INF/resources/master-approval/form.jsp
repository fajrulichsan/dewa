<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/user-master-approval-list" var="userListURL"/>
<portlet:resourceURL id="/master-approval-action-command" var="actionCommandURL"/>
<portlet:resourceURL id="/super-roles" var="roleListURL"/>
<portlet:resourceURL id="/applications" var="menuListURL"/>

<style>
   /*Select2*/
   .select2-container--default .select2-selection--single {
      padding: 6px;
      height: 38px;
      font-size: 1.2em;
      position: relative;
   }

   .approverField .select2-container--default .select2-selection--single {
      border-radius: 4px 0 0 4px;
   }

   .action-line {
      border-top: 1px solid #D9D9D9;
      margin: 10px 15px;
   }

   .delete-user-btn {
      background-color: #fff;
      padding: 8px 16px;
      display: inline-block;
      margin-bottom: 0;
      font-weight: normal;
      text-align: center;
      cursor: pointer;
      border: 1px solid #aaa;
      border-left: none;
      border-radius: 0 4px 4px 0;
      white-space: nowrap;
      font-size: 14px;
      line-height: 1.428571429;
   }
</style>

<form id="masterApprovalForm" method="POST" enctype="text/plain" novalidate autocomplete="off">
   <div class="container" style="margin-left: 0;">
      <h3 id="form-title" style="margin-bottom: 50px;">Tambah Master Approval</h3>

      <div class="row">
         <input type="hidden" name="entryId"/>

         <div class="form-group col-md-9 role-input">
            <input type="hidden" name="roleId">
            <label class="title-form pull-left" for="roleId">
               Role
               <span style="color: red;">*</span>
            </label>
            <select class="form-control" id="roleId" style="width: 100%;"></select>
         </div>

         <div class="form-group col-md-9 menu-input">
            <input type="hidden" name="menuId"/>
            <label class="title-form pull-left" for="menuId">
               Menu
               <span style="color: red;">*</span>
            </label>
            <select class="form-control" id="menuId" style="width: 100%;"></select>
         </div>

         <div class="form-group col-md-9">
            <label for="approvalGroup" class="title-form pull-left">
               Approval Group
               <span style="color: red;">*</span>
            </label>
            <input type="text" class="form-control" name="approvalGroup" id="approvalGroup" readonly />
         </div>

         <div class="workflow-group col-md-9">
            <label class="title-form">
               Approval Workflow
               <span style="color: red;">*</span>
            </label>
            <div class="row">
               <div class="col-md-3" style="display: grid; justify-content: center;">
                  <div class="radio">
                     <label for="sequentialWorkflow">
                        <input type="radio" name="sequentialWorkflow" id="sequentialWorkflow" />
                        Sequential Approval
                     </label>
                  </div>
               </div>
               <div class="col-md-3" style="display: grid; justify-content: center;">
                  <div class="radio">
                     <label for="parallelWorkflow">
                        <input type="radio" name="parallelWorkflow" id="parallelWorkflow" />
                        Parallel Approval
                     </label>
                  </div>
               </div>
            </div>
         </div>

         <div class="approver-group col-md-9">
            <label class="title-form">
               Approvers
               <span style="color: red;">*</span>
            </label>

            <div class="form-group">
               <div class="approvers-group"></div>
            </div>

            <button id="addApprover" class="btn btn-info" type="button" style="margin-right: 6px;">
               <span>
                  <i class="fas fa-plus" style="margin-right: 10px"></i>
                  Add Approver
               </span>
            </button>
            <label class="col-form-label text-danger" id="maxApproverNote">Approvers maksimal 5.</label>
         </div>

         <div class="col-md-9 width-form action-line"></div>

         <div class="col-md-9">
            <button type="submit" class="btn-ipr">Save</button>
            <button type="button" class="btn btn-ipr-cancel" onclick="back()">Cancel</button>
         </div>
      </div>
   </div>
</form>

<script>
   var data = ${data};
   var approvers = ${approvers};
   var action = "${action}";
   var homeUrl = "/group/dealink/cms-master-approval";
   var approverFieldId = 0;
   var isChanged;

   $(document).ready(function () {
      getRoles();
      getMenus();
      isChanged = false;
   });

   function formNew() {
      $('[name="entryId"]')[0].value = "0";
      $('[name="roleId"]')[0].value = "";
      $('[name="menuId"]')[0].value = "";
      $('[name="approvalGroup"]')[0].value = "";
      $('[name="sequentialWorkflow"]').prop("checked", false);
      $('[name="parallelWorkflow"]').prop("checked", false);
   }

   function formEdit() {
      $('[name="entryId"]')[0].value = data.id;
      $('[name="roleId"]')[0].value = data.roleId;
      $('[name="menuId"]')[0].value = data.menuId;
      $('[name="approvalGroup"]')[0].value = data.approvalGroup;
      data.isSequential ?
            $('[name="sequentialWorkflow"]').prop("checked", true) :
            $('[name="parallelWorkflow"]').prop("checked", true);
   }

   $("#roleId, #menuId").change(function () {
      if (null !== $("#roleId").val() && null !== $("#menuId").val()) {
         var appGroup = $("#roleId").select2("data")[0].text.concat(" ", $("#menuId").select2("data")[0].text);
         $("#approvalGroup").val(appGroup);
      }
   });

   $("#sequentialWorkflow").on("click", function () {
      $("#parallelWorkflow").prop("checked", false);
   });

   $("#parallelWorkflow").on("click", function () {
      $("#sequentialWorkflow").prop("checked", false);
   });

   $("#addApprover").click(function () {
      var roleId = $('input[name="roleId"]').val();
      if (roleId === null || roleId === undefined || roleId.length < 1) {
         Swal.fire("Pilih role terlebih dahulu!", "", "warning");
         return;
      }

      let count = $(".approvers-group input.approverId").length;

      let approverElement = generateApproverField(approverFieldId, null);

      if (count < 5) {
         isChanged = true;
         $(".approvers-group").append(approverElement);
         getUsers(approverFieldId, null);
         approverFieldId++;
      } else {
         Swal.fire("Approver maksimal 5!", "", "warning");
      }
   });

   function generateApproverField(index, data) {
      let element = '';

      element =
         '<div class="row approverField" id="approverField' + index + '" style="margin-bottom: 20px;">' +
            '<div class="col-md-6">' +
               '<input type="hidden" name="approverEntryId" id="approverEntryId' + index +
                     '" class="approverEntryId' +
                     '" value=' + (data == null ? 0 : data.id) + ' />' +
               '<input type="hidden" name="approverId' + index +
                     '" class="approverId' +
                     '" value=' + (data == null ? "" : data.userId) + ' />' +
               '<div class="input-group">' +
                  '<select class="form-control" name="userId" id="userId' + index +
                        '" value=' + (data == null ? "" : data.userId) + ' style="width: 100%;"></select>' +
                  '<div class="input-group-btn">' +
                     '<button class="delete-user-btn" onclick="removeApprover(' + index + ')"><i class="fas fa-times"></i></button>' +
                  '</div>' +
               '</div>' +
            '</div>' +
            '<div class="col-md-6" style="padding: 0;">' +
               '<div class="radio" style="height: 38px; padding: 6px 0; margin: 0 0 0 10px;">' +
                  '<label for="isDefaultApprover' + index + '">' +
                     '<input type="radio" class="isDefaultApprover" id="isDefaultApprover' + index + '" required ' +
                        (data == null ? "" : data.isDefault ? "checked" : "") + ' style="margin-right:5px;"/> Set Default Name Approver <span style="color: red;">*</span>' +
                  '</label>' +
               '</div>' +
               // '<input type="radio" class="isDefaultApprover" id="isDefaultApprover' + index + '" required ' + (data == null ? "" : data.isDefault ? "checked" : "") + '/>' +
               // '<label for="isDefaultApprover' + index + '"> Set Default Name Approver <span style="color: red;">*</span></label>' +
            '</div>' +
         '</div>';

      return element;
   }

   function removeApprover(id) {
      isChanged = true;
      $('#approverField' + id + '').remove();
   }

   $('.container').on('change', 'input', function () {
      isChanged = true;
   });

   $('.approvers-group').on('change', ' .isDefaultApprover:radio', function () {
      if ($('.approvers-group .isDefaultApprover:radio:checked').length > 1) {
         $('.approvers-group .isDefaultApprover:radio').not(this).prop('checked', false);
      }
   });

   var submitProcess = false;
   $("#masterApprovalForm").submit(function (e) {
      e.preventDefault();
      const entryId = $('[name="entryId"]').val();
      const roleId = $('[name="roleId"]').val();
      const menuId = $('[name="menuId"]').val();
      const approvalGroup = $('[name="approvalGroup"]').val();
      const isSequential = $('#sequentialWorkflow').is(':checked');
      const isParallel = $('#parallelWorkflow').is(':checked');
      const totalApprovers = $(".approvers-group input.approverId").length;
      const approverElements = $(".approvers-group input.approverId");
      const defaultApproverCount = $(".approvers-group .isDefaultApprover:radio:checked").length;
      var approverList = [];

      var duplicatedApprover = false;
      approverElements.each(function (index, item) {
         if (duplicatedApprover) return;
         var val = $(item).val();
         if ($('.approvers-group input.approverId[value="' + val + '"]').length < 2) return;
         duplicatedApprover = true;
      });

      if (!submitProcess) {
         if (roleId === null || roleId === undefined || roleId.length < 1) {
            Swal.fire("Role belum dipilih.", "", "warning");
         } else if (menuId === null || menuId === undefined || menuId.length < 1) {
            Swal.fire("Menu belum dipilih.", "", "warning");
         } else if (approvalGroup === null || approvalGroup === undefined || approvalGroup.length < 1) {
            Swal.fire("Approval group belum diisi.", "", "warning");
         } else if (!isSequential && !isParallel) {
            Swal.fire("Approval workflow belum dipilih.", "", "warning");
         } else if (totalApprovers < 1) {
            Swal.fire("Silahkan tambah approver.", "", "warning");
         } else if (duplicatedApprover) {
            Swal.fire("Duplikat approver!", "", "warning");
         } else if (defaultApproverCount === 0) {
            Swal.fire("Tidak ada default approver yang dipilih!", "", "warning");
         } else if (defaultApproverCount > 1) {
            Swal.fire("Tidak dapat memilih lebih dari satu approver!", "", "warning");
         } else {
            if (!isChanged) {
               swal.fire('Success!', "Data berhasil disimpan.", 'success')
                     .then(function () {
                        window.location = homeUrl;
                     });
               return;
            }

            $(".approvers-group .approverField").each(function (index) {
               var approverDetailId = $(this).find('.approverEntryId').val();
               var approverId = $(this).find('.approverId').val();
               var isDefault = $(this).find('.isDefaultApprover').is(':checked');
               var approver = { id: approverDetailId, userId: approverId, isDefault: isDefault, level: index + 1 };
               approverList.push(approver);
            });

            var formData = new FormData(this);
            formData.set("<portlet:namespace/>crudType", action);
            formData.set("<portlet:namespace/>entryId", entryId);
            formData.set("<portlet:namespace/>roleId", roleId);
            formData.set("<portlet:namespace/>menuId", menuId);
            formData.set("<portlet:namespace/>approvalGroup", approvalGroup);
            formData.set("<portlet:namespace/>isSequential", isSequential);
            formData.set("<portlet:namespace/>approverList", JSON.stringify(approverList));
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
                     url: '${actionCommandURL}',
                     type: 'POST',
                     data: formData,
                     processData: false,
                     contentType: false,
                     success: function (response) {
                        submitProcess = true;
                        var code = JSON.parse(response).code;
                        var message = JSON.parse(response).message;

                        if (code === 200) {
                           swal.fire('Success!', message, 'success')
                           .then(function () {
                              window.location = homeUrl;
                           });
                        } else {
                           swal.fire('Sorry!', message, 'warning');
                        }
                     },
                     error: function () {
                        console.log("error");
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

   function getRoles() {
      var roleConst;
      $.ajax({
         url: "${roleListURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var roleData = JSON.parse(response);
            roleConst = $('#roleId').select2({
               data: roleData.Data,
               tags: false,
               placeholder: 'Cari Role',
               allowClear: false,
               minimumResultsForSearch: -1,
               maximumInputLength: 15,
               templateSelection: function (data) {
                  $('input[name="roleId"]').val(data.id).change();
                  if (data.id === 'Cari Role') {
                     return 'Cari Role';
                  }
                  return data.text;
               }
            });
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            isChanged = false;

            roleConst.on('change', function() {
               $(".approvers-group .approverField").each(function (index) {
                  $(this).remove();
               });
            });

            if (action === "update") {
               roleConst.val(data.roleId).change();
               formEdit();
               $('#form-title').text("Edit Master Approval");
               approvers.forEach(item => {
                  let approverElement = generateApproverField(approverFieldId, item);
                  $(".approvers-group").append(approverElement);
                  getUsers(approverFieldId, item);
                  approverFieldId++;
               });
            } else {
               roleConst.val(null).change();
               formNew();
               $('#form-title').text("Tambah Master Approval");
            }
            console.log("complete");
         }
      });
   }

   function getMenus() {
      $.ajax({
         url: "${menuListURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         success: function (response) {
            var menuData = JSON.parse(response);
            const menuConst = $('#menuId').select2({
               data: menuData.Data,
               tags: false,
               placeholder: 'Cari Menu',
               allowClear: false,
               minimumResultsForSearch: -1,
               maximumInputLength: 15,
               templateSelection: function (data) {
                  $('input[name="menuId"]').val(data.id).change();
                  if (data.id === 'Cari Menu') {
                     return 'Cari Menu';
                  }
                  return data.text;
               }
            });
            if (action === "update") {
               menuConst.val(data.menuId);
               menuConst.prop('disabled', true);
            } else {
               menuConst.val(null);
            }
            menuConst.trigger('change');
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            isChanged = false;
            console.log("complete");
         }
      });
   }

   function getUsers(id, user) {
      const userConst = $('#userId' + id + '').select2({
         tags: false,
         minimumInputLength: 3,
         maximumInputLength: 55,
         placeholder: 'Cari User',
         ajax: {
            url: "${userListURL}",
            cache: false,
            dataType: 'json',
            delay: 250,
            data: function (params) {
               var roleId = $('[name="roleId"]').val();
               var query = {
                  term: params.term,
                  roleId: roleId
               }
               return query;
            },
            processResults: function (response) {
               return {
                  results: response.results
               }
            },
            complete: function () {
               isChanged = false;
            }
         },
         templateSelection: function (data) {
            if (data.id === 'Cari User' || data.id === '' || data.id === null) {
               return 'Cari User';
            }
            isChanged = true;
            $('input[name="approverId' + id + '"]').val(data.id);
            return data.text;
         }
      });

      if (user == null) {
         userConst.val(null);
      } else {
         $('#userId' + id + '').select2("trigger", "select", { data: {id: user.userId, text: user.userName} } );
      }

      userConst.trigger('change');
   }

   function back() {
      window.location.href = homeUrl;
   }
</script>