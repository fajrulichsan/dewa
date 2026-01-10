<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/skiris-action" var="skIrisActionURL"/>

<portlet:resourceURL id="/dealer-sk-iris" var="dealerSkIrisURL"/>
<portlet:resourceURL id="/wilayah-sk-iris" var="wilayahSkIrisURL"/>

<style>
    /*Select2*/
    .select2-container--default .select2-selection--single {
        padding:6px;
        /*margin-top: 16px;*/
        /*margin-bottom: 16px;*/
        height: 37px;
        /*width: 148px;*/
        font-size: 1.2em;
        position: relative;
    }
</style>

<form id="formDealer" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
   <div class="container" style="margin-left: 0;">
      <h3 id="form-title" style="margin-bottom: 50px;">Form Data SK IRIS</h3>
      <input type="hidden" name="entryId">
      <div class="row">
         <div class="form-group col-md-9">
            <label class="title-form pull-left">Kode Dealer</label>
            <input type="text" class="form-control" name="dealerCode" id="dealerCode" readonly
                   pattern="[0-9]+" data-minlength="5" maxlength="15"
                   data-error="Nomor dengan 5-15 karakter & tidak boleh kosong." required />
            <div class="help-block with-errors"></div>
         </div>
         <div class="form-group col-md-9">
            <label class="title-form pull-left">Nama Dealer</label>
            <input type="text" class="form-control" name="dealerName" id="dealerName" readonly />
         </div>
         <div class="form-group col-md-9">
            <label class="title-form pull-left">Kode HO Dealer</label>
            <input type="text" class="form-control" name="roleDealerId" id="roleDealerId" readonly />
         </div>
         <div class="form-group col-md-9">
            <label class="title-form pull-left">Nama HO Dealer</label>
            <input type="text" class="form-control" name="roleDealerName" id="roleDealerName" readonly />
         </div>
         <div class="form-group col-md-9">
            <label class="title-form pull-left">Cabang</label>
            <input type="hidden" class="form-control" name="cabangId" id="cabangId" readonly />
            <input type="text" class="form-control" name="cabangName" id="cabangName" readonly />
         </div>
         <div class="form-group col-md-9">
            <label class="title-form pull-left">Wilayah Dealer</label>
            <input type="hidden" class="form-control" name="wilayahId" id="wilayahId" readonly />
            <input type="text" class="form-control" name="wilayahName" id="wilayahName" readonly />
         </div>
         <div class="form-group col-md-9">
            <label class="title-form pull-left" for="wilayahId">Wilayah SK</label>
            <input type="hidden" class="form-control" name="wilayahSkName" id="wilayahSkName" />
            <select class="form-control" name="wilayahSkId" id="wilayahSkId" style="width: 100%;">
               <%-- <option value="NULL">List Dealer</option>--%>
            </select>
         </div>
         <div class="col-md-9" style="border-top: 1px solid #D9D9D9 ; margin: 10px 15px"></div>
         <div class="col-md-9">
            <button class="btn-ipr" type="submit">Save</button>
            <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="/group/dealink/cms-sk-iris">Cancel</a>
         </div>
      </div>
   </div>
</form>

<script>
   var data = ${data};
   console.log(data);
   var action = "${action}";
   var homeUrl = "/group/dealink/cms-sk-iris";

   $(document).ready(function() {
      // getDealers()
      getWilayahs()
      if (action === "create") {
         $('#form-title').text("New Data SK IRIS")
         formNew();
      } else if (action === "update") {
         $('#form-title').text("Edit Data SK IRIS")
         formEdit();
      } else {
         $('#form-title').text("Data SK IRIS")
         formEdit();
      }
   });

   function formNew() {
      $('[name="entryId"]')[0].value = "0";
      $('[name="dealerCode"]')[0].value = "";
      $('[name="dealerName"]')[0].value = "";
      // $('[name="dealerHoCode"]')[0].value = "";
      $('[name="roleDealerId"]')[0].value = "";
      $('[name="roleDealerName"]')[0].value = "";
      $('[name="cabangId"]')[0].value = "";
      $('[name="cabangName"]')[0].value = "";
      $('[name="wilayahId"]')[0].value = "";
      $('[name="wilayahName"]')[0].value = "";
      $('[name="wilayahSkId"]')[0].value = "";
      $('[name="wilayahSkName"]')[0].value = "";
   }

   function formEdit() {
      $('[name="entryId"]')[0].value = data.id;
      $('[name="dealerCode"]')[0].value = data.code;
      $('[name="dealerName"]')[0].value = data.name;
      // $('[name="dealerHoCode"]')[0].value = data.hoCode;
      $('[name="roleDealerId"]')[0].value = data.hoCode;
      $('[name="roleDealerName"]')[0].value = data.hoName;
      $('[name="cabangId"]')[0].value = data.cabangId;
      $('[name="cabangName"]')[0].value = data.cabangName;
      $('[name="wilayahId"]')[0].value = data.wilayahId;
      $('[name="wilayahName"]')[0].value = data.wilayahName;
      $('[name="wilayahSkId"]')[0].value = data.wilayahSkId;
      $('[name="wilayahSkName"]')[0].value = data.wilayahSkName;
   }

   var submitProcess = false;
   $("#formDealer").submit(function (e) {
      e.preventDefault();
      const cabang = $('[name="cabangId"]').val()
      const wilayah = $('[name="wilayahId"]').val()
      const dealerCode = $('[name="dealerCode"]').val()
      const dealerName = $('[name="dealerName"]').val()
      // const dealerHoCode = $('[name="dealerHoCode"]').val()
      const roleDealerId = $('[name="roleDealerId"]').val()
      const wilayahSk = $('[name="wilayahSkId"]').val()
      if (submitProcess === false) {
         // var $form = $(this);
         // if (!formValidation($form)) {
         //    Swal.fire("Please complete all fields before save data!", "", "warning");
         //    return false;
         // }
         if (dealerCode === null || dealerCode === undefined || dealerCode.length < 1) {
            return false;
         } else if (dealerName === null || dealerName === undefined || dealerName.length < 1) {
            return false;
         } else if (roleDealerId === null || roleDealerId === undefined || roleDealerId.length < 1) {
            Swal.fire("Kode DSO/HO belum diisi..", "", "warning");
            return false;
         } else if (cabang === null || cabang === undefined || cabang.length < 1) {
            Swal.fire("Cabang belum dipilih.", "", "warning");
            return false;
         } else if (wilayah === null || wilayah === undefined || wilayah.length < 1) {
            Swal.fire("Wilayah belum dipilih.", "", "warning");
            return false;
         } else if (wilayahSk === null || wilayahSk === undefined || wilayahSk.length < 1) {
            Swal.fire("Wilayah SK belum dipilih.", "", "warning");
            return false;
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.append("crudType", action)
            formData.append("entryId", $('[name="entryId"]').val());
            formData.append("dealerCode", dealerCode);
            formData.append("dealerName", dealerName);
            // formData.append("dealerHoCode", dealerHoCode);
            formData.append("roleDealerId", roleDealerId);
            formData.append("roleDealerName", $('[name="roleDealerName"]').val());
            formData.append("cabangId", cabang);
            formData.append("cabangName", $('[name="cabangName"]').val());
            formData.append("wilayahId", wilayah);
            formData.append("wilayahName", $('[name="wilayahName"]').val());
            formData.append("wilayahSkId", wilayahSk);
            formData.append("wilayahSkName", $('[name="wilayahSkName"]').val());
            console.log("formData  : " + JSON.stringify(formData));

            createLoading();
            $.ajax({
               url: '${skIrisActionURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  console.log("response  : " + JSON.stringify(response));
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

   function formValidation($form) {
      var isValidate = true;
      // $form.find("input.required").each(function () {
      //    if ($(this).val() === "") {
      //       isValidate = false;
      //       return false;
      //    }
      // });
      // $form.find("select.required").each(function () {
      //    if ($(this).val() === "" || $(this).val() === null) {
      //       isValidate = false;
      //       return false;
      //    }
      // });
      // $form.find("textarea.required").each(function () {
      //    if ($(this).val() === "" || $(this).val() === null) {
      //       isValidate = false;
      //       return false;
      //    }
      // });
      return isValidate;
   }

   <%--function getDealers() {--%>
   <%--   $.ajax({--%>
   <%--      url: "${dealerSkIrisURL}",--%>
   <%--      type: "POST",--%>
   <%--      data: {"dealerId": "test"},--%>
   <%--      success: function (response) {--%>
   <%--         var dealerData = JSON.parse(response);--%>
   <%--         const dealerConst = $('#dealerId').select2({--%>
   <%--            data: dealerData.Data,--%>
   <%--            tags: "true",--%>
   <%--            placeholder: 'Cari Dealer',--%>
   <%--            allowClear: false,--%>
   <%--            // ajax: {--%>
   <%--            //    url: "http://example.org/api/test",--%>
   <%--            //    cache: false--%>
   <%--            // }--%>
   <%--            // minimumInputLength: 3,--%>
   <%--            maximumInputLength: 100,--%>
   <%--            templateSelection: function (data) {--%>
   <%--               console.log(data.id + " - " + data.text);--%>
   <%--               $('[name="dealerId"]')[0].value = data.id;--%>
   <%--               $('[name="dealerName"]')[0].value = data.text;--%>
   <%--               if (data.id === 'Cari Dealer') {--%>
   <%--                  return 'Cari Dealer';--%>
   <%--               }--%>
   <%--               return data.text;--%>
   <%--            }--%>
   <%--         });--%>
   <%--         if(action === "update") {--%>
   <%--            dealerConst.val(data.dealerId); // Select the option with a value of '1'--%>
   <%--            dealerConst.trigger('change'); // Notify any JS components that the value changed--%>
   <%--         } else {--%>
   <%--            dealerConst.val(null);--%>
   <%--            dealerConst.trigger('change');--%>
   <%--         }--%>
   <%--      },--%>
   <%--      error: function (error) {--%>
   <%--         console.log(error);--%>
   <%--      },--%>
   <%--      complete: function (){--%>
   <%--         console.log("complete");--%>
   <%--      }--%>
   <%--   });--%>
   <%--}--%>

   function getWilayahs() {
      $.ajax({
         url: "${wilayahSkIrisURL}",
         type: "POST",
         data: {"wilayahId": "test"},
         success: function (response) {
            var wilayahSkData = JSON.parse(response);
            const wilayahSkConst = $('#wilayahSkId').select2({
               data: wilayahSkData.Data,
               tags: "true",
               placeholder: 'Cari Wilayah SK',
               allowClear: false,
               // ajax: {
               //    url: "http://example.org/api/test",
               //    cache: false
               // }
               // minimumInputLength: 3,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  console.log(data.id + " - " + data.text);
                  $('[name="wilayahSkId"]')[0].value = data.id;
                  $('[name="wilayahSkName"]')[0].value = data.text;
                  if (data.id === 'Cari Wilayah SK') {
                     return 'Cari Wilayah SK';
                  }
                  return data.text;
               }
            });
            if(action === "update") {
               wilayahSkConst.val(data.wilayahSkId); // Select the option with a value of '1'
               wilayahSkConst.trigger('change'); // Notify any JS components that the value changed
            } else {
               wilayahSkConst.val(null);
               wilayahSkConst.trigger('change');
            }
         },
         error: function (error) {
            console.log(error);
         },
         complete: function (){
            console.log("complete");
         }
      });
   }
</script>
