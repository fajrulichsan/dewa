<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/dealer-action" var="dealerActionURL"/>

<portlet:resourceURL id="/role-dealer-dealer" var="roleDealerDealerURL"/>
<portlet:resourceURL id="/cabang-dealer" var="cabangDealerURL"/>
<portlet:resourceURL id="/wilayah-dealer" var="wilayahDealerURL"/>
<portlet:resourceURL id="/hoDealer-dealer" var="hoDealerDealerURL"/>

<style>
    /*Select2*/
    .select2-container--default .select2-selection--single {
        padding: 6px;
        height: 37px;
        font-size: 1.2em;
        position: relative;
    }
</style>

<form data-toggle="validator" role="form" id="formDealer" method="post" enctype="multipart/form-data" novalidate
      autocomplete="off">
   <div class="container" style="margin-left: 0;">
      <h3 id="form-title" style="margin-bottom: 50px;">Form Data Dealer</h3>
      <div class="row">
         <div class="form-group col-md-9">
            <label class="title-form pull-left" for="groupDealerId">
               Group Dealer
               <span style="color: red;">*</span>
            </label>
            <select class="form-control" name="groupDealerId" id="groupDealerId" style="width: 100%;"></select>
         </div>
         <div class="form-group col-md-9 hoCode">
            <label class="title-form pull-left" for="hoCode">
               Kode HO Dealer
               <span style="color: red;">*</span></label>
            <input type="text" class="form-control" name="hoCode" id="hoCode"
                   pattern="[0-9]+" data-minlength="5" maxlength="15"
                   data-error="Nomer 5-15 karakter & tidak boleh kosong." required/>
         </div>

         <div class="form-group col-md-9 hoCodeSelect">
            <label class="title-form pull-left" for="hoCodeSelect">Kode HO Dealer
               <span style="color: red;">*</span>
            </label>
            <select class="form-control" name="hoCode" id="hoCodeSelect" style="width: 100%;"></select>
         </div>

         <div class="form-group col-md-9 dealerCode">
            <label class="title-form pull-left">
               Kode Dealer
               <span style="color: red;">*</span>
            </label>
            <input type="text" class="form-control" name="dealerCode" id="dealerCode"
                   pattern="[0-9]+" data-minlength="5" maxlength="15"
                   data-error="Nomor dengan 5-15 karakter & tidak boleh kosong." required/>
            <div class="help-block with-errors"></div>
         </div>

         <div class="form-group col-md-9 dealerName">
            <label class="title-form pull-left">
               Nama Dealer
               <span style="color: red;">*</span>
            </label>
            <input type="text" class="form-control" name="dealerName" id="dealerName"
                   pattern="[A-Za-z0-9. ]+" data-minlength="5" maxlength="75"
                   data-error="Text dengan 5-75 karakter & tidak boleh kosong." required/>
            <div class="help-block with-errors"></div>
         </div>
         <div class="form-group col-md-9">
            <label class="title-form pull-left" for="cabangId">
               Cabang
               <span style="color: red;">*</span>
            </label>
            <select class="form-control" name="cabangId" id="cabangId" style="width: 100%;"></select>
         </div>
         <div class="form-group col-md-9">
            <label class="title-form pull-left" for="wilayahId">
               Wilayah Dealer
               <span style="color: red;">*</span>
            </label>
            <select class="form-control" name="wilayahId" id="wilayahId" style="width: 100%;"></select>
         </div>
         <div class="col-md-9" style="border-top: 1px solid #D9D9D9 ; margin: 10px 15px"></div>
         <div class="col-md-9">
            <button type="submit" class="btn-ipr">Save</button>
            <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="/group/dealink/cms-master-dealer">Cancel</a>
         </div>
      </div>
   </div>
</form>

<script>
   var data = ${data};
   var action = "${action}";
   var entryId = "0";
   var homeUrl = "/group/dealink/cms-master-dealer";
   var submitProcess = false;
   var groupDealers = [{id: 0, text: 'Ho Dealer'}, {id: 1, text: 'Dealer'}]

   $(document).ready(function () {
      getGroupDealers();
      getCabangs();
      getWilayahs();
      if (action === "create") {
         $('#form-title').text("New Data Dealer");
         formNew();
      } else if (action === "update") {
         $('#form-title').text("Edit Data Dealer");
         formEdit();
      } else {
         $('#form-title').text("Data Dealer");
         formEdit();
      }
   });

   $(function () {
      $("input[name='dealerCode']").on('input', function (e) {
         $(this).val($(this).val().replace(/[^0-9]/g, ''));
      });
      $("input[name='hoCode']").on('input', function (e) {
         $(this).val($(this).val().replace(/[^0-9]/g, ''));
      });
      $("input[name='dealerName']").on('input', function (e) {
         $(this).val($(this).val().replace(/[^A-Za-z0-9. ]/g, ''));
      });
   });

   function formNew() {
      entryId = "0";
      $('[name="dealerCode"]')[0].value = "";
      $('[name="dealerName"]')[0].value = "";
      $('[name="hoCode"]')[0].value = "";
      $('[name="groupDealerId"]')[0].value = "";
      $('[name="cabangId"]')[0].value = "";
      $('[name="wilayahId"]')[0].value = "";
   }

   function formEdit() {
      entryId = data.id
      $('[name="dealerCode"]')[0].value = data.code;
      $('[name="dealerName"]')[0].value = data.name;
      $('[name="hoCode"]')[0].value = data.hoCode;
      $('[name="groupDealerId"]')[0].value = data.groupDealerId;
      $('[name="cabangId"]')[0].value = data.cabangId;
      $('[name="wilayahId"]')[0].value = data.wilayahId;

      if ($('[name="groupDealerId"]')[0].value === '0') {
         $(".hoCodeSelect").hide();
         $(".hoCode").show();

      } else {
         $(".hoCodeSelect").show();
         $(".hoCode").hide();

         getHODealer();
      }

      //disable field Group Dealer, Kode HO Dealer, Kode Dealer, and Nama Dealer
      const elementGroupDealer = document.getElementById('groupDealerId');
      const elementHOCode = document.getElementById('hoCode');
      const elementHOCodeSelect = document.getElementById('hoCodeSelect');
      const elementDealerCode = document.getElementById('dealerCode');
      const elementDealerName = document.getElementById('dealerName');

      elementGroupDealer.setAttribute('disabled', 'disabled');
      elementHOCode.setAttribute('disabled', 'disabled');
      elementHOCodeSelect.setAttribute('disabled', 'disabled');
      elementDealerCode.setAttribute('disabled', 'disabled');
      elementDealerName.setAttribute('disabled', 'disabled');
   }

   $("#formDealer").submit(function (e) {
      e.preventDefault();
      const dealerCode = $('[name="dealerCode"]').val();
      const dealerName = $('[name="dealerName"]').val();
      const hoCode = $('[name="hoCode"]').val();
      const groupDealerId = $('[name="groupDealerId"]').val();
      const cabangId = $('[name="cabangId"]').val();
      const wilayahId = $('[name="wilayahId"]').val();
      if (submitProcess === false) {
         if (groupDealerId === null || groupDealerId === undefined || groupDealerId.length < 1) {
            Swal.fire("Group Dealer belum dipilih.", "", "warning");
            return false;
         } else if (hoCode === null || hoCode === undefined || hoCode.length < 1) {
            Swal.fire("Kode HO Dealer belum diisi.", "", "warning");
            return false;
         } else if (dealerCode === null || dealerCode === undefined || dealerCode.length < 1) {
            Swal.fire("Kode Dealer belum diisi.", "", "warning");
            return false;
         } else if (dealerName === null || dealerName === undefined || dealerName.length < 5) {
            Swal.fire("Nama dealer belum terisi atau nama dealer harus 5-75 karakter.", "", "warning");
            return false;
         } else if (!regexBasicCharacter.test(dealerName)) {
            Swal.fire("Nama dealer hanya boleh diisi dengan karakter .,/()@&_-", "", "warning");
            return false;
         } else if (cabangId === null || cabangId === undefined || cabangId.length < 1) {
            Swal.fire("Cabang belum dipilih.", "", "warning");
            return false;
         } else if (wilayahId === null || wilayahId === undefined || wilayahId.length < 1) {
            Swal.fire("Wilayah belum dipilih.", "", "warning");
            return false;
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.set('<portlet:namespace/>crudType', action)
            formData.set('<portlet:namespace/>entryId', entryId);
            formData.set('<portlet:namespace/>p_auth', Liferay.authToken);

            if (action === "update") {
               formData.set('<portlet:namespace/>groupDealerId', groupDealerId);
               formData.set('<portlet:namespace/>hoCode', hoCode);
               formData.set('<portlet:namespace/>dealerCode', dealerCode);
               formData.set('<portlet:namespace/>dealerName', dealerName);
            }

            createLoading();
            $.ajax({
               url: '${dealerActionURL}',
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

   function getGroupDealers() {
      const groupDealerConst = $('#groupDealerId').select2({
         data: groupDealers,
         tags: false,
         placeholder: 'Cari Group Dealer',
         allowClear: false,
         maximumInputLength: 100,
         templateSelection: function (data) {
            $('[name="groupDealerId"]')[0].value = data.id;

            if (data.id === 'Cari Group Dealer') {
               return 'Cari Group Dealer';
            }
            return data.text;
         }
      });
      if (action === "update") {
         groupDealerConst.val(data.groupDealerId);
         groupDealerConst.trigger('change');
      } else {
         groupDealerConst.val(null);
         groupDealerConst.trigger('change');
      }
   }

   $('[name="groupDealerId"]')[0].onchange = function (e) {
      const selectedValue = $('[name="groupDealerId"]')[0].value;
      console.log("=============");
      console.log(selectedValue);

      if (selectedValue === '0') {
         $(".dealerCode input").prop('readOnly', true);
         $(".dealerName input").prop('readOnly', false);

         $(".hoCodeSelect").hide();
         $(".hoCode").show();

         resetValue();

      } else if (selectedValue === '1') {
         $(".dealerCode input").prop('readOnly', false);
         $(".dealerName input").prop('readOnly', true);

         $(".hoCodeSelect").show();
         $(".hoCode").hide();

         getHODealer();
         resetValue();

      } else {
         $(".dealerCode input").prop('readOnly', false);
         $(".dealerName input").prop('readOnly', false);

         $(".hoCodeSelect").hide();
         $(".hoCode").show();
      }
   };

   function getCabangs() {
      $.ajax({
         url: "${cabangDealerURL}",
         type: "POST",
         data: {"cabangId": "test"},
         success: function (response) {
            var cabangData = JSON.parse(response);
            const cabangConst = $('#cabangId').select2({
               data: cabangData.Data,
               tags: false,
               placeholder: 'Cari Cabang',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="cabangId"]')[0].value = data.id;
                  if (data.id === 'Cari Cabang') {
                     return 'Cari Cabang';
                  }
                  return data.text;
               }
            });
            if (action === "update") {
               cabangConst.val(data.cabangId);
               cabangConst.trigger('change');
            } else {
               cabangConst.val(null);
               cabangConst.trigger('change');
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

   function getWilayahs() {
      $.ajax({
         url: "${wilayahDealerURL}",
         type: "POST",
         data: {"wilayahId": "test"},
         success: function (response) {
            var wilayahData = JSON.parse(response);
            const wilayahConst = $('#wilayahId').select2({
               data: wilayahData.Data,
               tags: false,
               placeholder: 'Cari Wilayah',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="wilayahId"]')[0].value = data.id;
                  if (data.id === 'Cari Wilayah') {
                     return 'Cari Wilayah';
                  }
                  return data.text;
               }
            });
            if (action === "update") {
               wilayahConst.val(data.wilayahId);
               wilayahConst.trigger('change');
            } else {
               wilayahConst.val(null);
               wilayahConst.trigger('change');
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

   function getHODealer() {
      $.ajax({
         url: "${hoDealerDealerURL}",
         type: "POST",
         data: {"hoCode": "test"},
         success: function (response) {
            var hoDealerData = JSON.parse(response);
            const hoDealerConst = $('#hoCodeSelect').select2({
               data: hoDealerData.Data,
               tags: false,
               placeholder: 'Cari Kode HO Dealer',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="hoCode"]')[0].value = data.id;
                  if (data.id === 'Cari Kode HO Dealer') {
                     return 'Cari Kode HO Dealer';
                  } else {
                     console.log("masuk ke sini");
                     setDealerName(data.text);
                     return data.text;
                  }
               }
            });
            if (action === "update") {
               hoDealerConst.val(data.hoCode);
               hoDealerConst.trigger('change');
            } else {
               hoDealerConst.val(null);
               hoDealerConst.trigger('change');
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

   function setDealerName(hoCode) {
      const dealerName = hoCode.split(' - ');
      const companyName = dealerName[1];

      $('[name="dealerName"]').val(companyName);
   }

   function resetValue() {
      $('[name="dealerCode"]').val('');
      $('[name="dealerName"]').val('');
      $('[name="hoCode"]').val('');
   }

   $("#hoCode").on("change", function () {
      var hoCodeValue = $(this).val();

      $('[name="dealerCode"]').val(hoCodeValue);
   });
</script>
