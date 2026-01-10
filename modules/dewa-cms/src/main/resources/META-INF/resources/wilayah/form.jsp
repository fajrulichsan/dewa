<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/wilayah-action" var="wilayahActionURL"/>

<form id="formWilayah" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
   <div class="container" style="margin-left: 0;">
      <h3 id="form-title" style="margin-bottom: 50px;">Add New Data Wilayah</h3>
      <input type="hidden" name="entryId">
      <div class="row">
         <div class="form-group col-md-9">
            <label class="title-form pull-left">Nama Wilayah <span
                    style="color: red;">*</span></label>
            <input type="text" class="form-control" name="wilayahName" />
         </div>
         <div class="col-md-9" style="border-top: 1px solid #D9D9D9 ; margin: 10px 15px"></div>
         <div class="col-md-9">
            <button class="btn-ipr" type="submit">Save</button>
            <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="/group/dealink/cms-wilayah">Cancel</a>
         </div>
      </div>
   </div>
</form>

<script>
   var data;
   var action;
   var wilayahActionURL = '${wilayahActionURL}';
   var homeUrl = "/group/dealink/cms-wilayah";

   $(document).ready(function () {
      data = ${data};
      action = "${action}";
      refresh()
   });

   function refresh() {
      if (action === "create") {
         $('#form-title').text("Add New Data Wilayah")
         formNew();
      } else if (action === "update") {
         $('#form-title').text("Edit Data Wilayah")
         formEdit();
      } else {
         $('#form-title').text("Data Wilayah")
         formEdit();
      }
   }

   function formNew() {
      $('[name="entryId"]')[0].value = "0";
      $('[name="wilayahName"]')[0].value = "";
   }

   function formEdit() {
      $('[name="entryId"]')[0].value = data.id;
      $('[name="wilayahName"]')[0].value = data.name;
   }

   var submitProcess = false;
   $("#formWilayah").submit(function (e) {
      e.preventDefault();
      const wilayahName = $('[name="wilayahName"]').val()

      if (submitProcess === false) {

         if (wilayahName === null || wilayahName === undefined || wilayahName.length < 3) {
            Swal.fire("Nama wilayah belum diisi atau nama wilayah harus 3-50 karakter", "", "warning");
            return false;
         } else if (!regexBasicCharacter.test(wilayahName)) {
            Swal.fire("Nama wilayah hanya boleh diisi dengan karakter .,/()@&_-", "", "warning");
            return false;
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.set("<portlet:namespace/>crudType", action);
            formData.set("<portlet:namespace/>entryId", $('[name="entryId"]').val());
            formData.set("<portlet:namespace/>wilayahName", $('[name="wilayahName"]').val());
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            createLoading();
            $.ajax({
               url: wilayahActionURL,
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
      }
   })
</script>
