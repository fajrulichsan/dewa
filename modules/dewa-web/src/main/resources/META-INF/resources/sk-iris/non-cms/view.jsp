<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/non-cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/sk-iris/non-cms/style-css.jsp" %>

<%--List--%>
<portlet:resourceURL id="/sk-iris-non-cms-list" var="skIrisNonCMSListURL"/>
<%--Action--%>
<portlet:resourceURL id="/sk-iris-action" var="skIrisActionURL"/>
<portlet:renderURL var="skIrisEditURL">
   <portlet:param name="mvcRenderCommandName" value="/sk-iris-edit"/>
</portlet:renderURL>
<%--Util--%>
<portlet:resourceURL id="/tahun-sk-iris-non-cms" var="tahunSkIrisNonCMSURL"/>
<portlet:resourceURL id="/bulan-sk-iris-non-cms" var="bulanSkIrisNonCMSURL"/>
<%--File--%>
<portlet:resourceURL id="/download-sk-iris-non-cms" var="downloadSkIrisNonCMSURL"/>

<div class="sk_iris non-cms-menu">
   <ol class="breadcrumb">
      <li>Home</li>
      <li>Download</li>
      <li>SK</li>
      <li>SK IRIS</li>
   </ol>

   <div class="main-title">
      <h3>SK IRIS</h3>
   </div>

   <div class="row table-filters">
      <div class="col-xs-12 col-md-6 row sub-filters">
         <div class="col-xs-6">
            <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
               <option value="ALL">Select All</option>
            </select>
         </div>
         <div class="col-xs-6">
            <select class="form-control" name="bulanId" id="bulanId" style="width: 100%;">
               <option value="ALL">Select All</option>
            </select>
         </div>
      </div>
   </div>

   <div class="tabcontent">
      <table id="sk_iris_table" class="table table-hover nowrap non-cms-table" style="width:100%">
         <thead>
            <tr>
               <th>No</th>
               <th>SK Iris</th>
               <th>Periode</th>
               <th>Tahun</th>
               <th>Tanggal Upload</th>
               <th>Keterangan</th>
            </tr>
         </thead>
         <tbody></tbody>
      </table>
   </div>
</div>

<script>
   var tahun = "ALL";
   var bulan = "ALL";
   var role = "ALL";

   $(document).ready(function () {
      getTahuns();
      getBulans();
      $('.dataTables_length select').select2({
         minimumResultsForSearch: -1
      });
   });

   var tableLanguage = {
      "lengthMenu": "_MENU_",
      "paginate": {
         "first": "",
         "last": "",
         "next": '<span class="glyphicon glyphicon-menu-right"></span>',
         "previous": '<span class="glyphicon glyphicon-menu-left"></span>'
      },
      "search": "",
      "searchPlaceholder": "Search..."
   }

   var skIrisDataTable = $('#sk_iris_table').DataTable({
      searching: false,
      paging: true,
      info: false,
      language: tableLanguage,
      dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
      ajax: {
         url: '${skIrisNonCMSListURL}',
         type: 'GET',
         data: function (d) {
            d.tahun = tahun;
            d.periode = bulan;
         },
         // dataSrc: 'Data'
         dataSrc: function (json) {
            role = json.role;
            if (role === 'Dealer') {
               dealerCode = json.codeDealer;
            } else {
               // $( ".dealerName" ).hide();
            }
            if (role !== 'DSO') {
               // $( ".roleDso" ).hide();
            }
            return json.Data;
         }
      },
      columns: [
         {data: "no", "width": "5%", className: "text-center"},
         {data: "fileName", "width": "15%"},
         {
            data: "periode", "width": "30%",
            className: "text-center",
            "render": function (data, type, row, meta) {
               if (type === "sort") {
                  return row.periodeSort;
               }
               return data;
            },
         },
         {data: "tahun", "width": "15%", className: "text-center"},
         {
            data: "uploadDate", "width": "25%",
            className: "text-center",
            "render": function (data, type, row, meta) {
               if (type === "sort") {
                  return row.uploadDateSort;
               }
               return data;
            },
         },
         {
            data: "id",
            "width": "65%",
            className: "text-center",
            "render": function (data, type, row, meta) {
               return renderActionButton(data, row);
            },
            orderable: false
         }
      ],
      responsive: true,
      order: []
   });

   function renderActionButton(dataId, row) {
      var downloadURL = new URL("${downloadSkIrisNonCMSURL}");
      downloadURL.searchParams.set("<portlet:namespace/>entryId", dataId);
      var response = "";
      response =
         '<span class="action-wrapper" data-id="' + dataId + '">' +
             '<a target="_blank" href="' + downloadURL.toString() + '" class="text-center">' +
                 'Unduh Data' +
             '</a>' +
         '</span>';
      return response;
   }

   function getTahuns() {
      $.ajax({
         url: "${tahunSkIrisNonCMSURL}",
         type: "POST",
         data: {"windowsId": "test"},
         success: function (response) {
            var tahunData = JSON.parse(response);
            const tahunConst = $('#tahunId').select2({
               data: tahunData.Data,
               placeholder: 'Pilih Tahun',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  if (data.id !== '') {
                     tahun = data.id;
                     skIrisDataTable.ajax.reload();
                  }
                  return data.text;
               }
            });
            tahunConst.val(null);
            tahunConst.trigger('change');
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            console.log("complete");
         }
      });
   }

   function getBulans() {
      $.ajax({
         url: "${bulanSkIrisNonCMSURL}",
         type: "POST",
         data: {"windowsId": "test"},
         success: function (response) {
            var bulanData = JSON.parse(response);
            var bulanConst = $('#bulanId').select2({
               data: bulanData.Data,
               tags: "true",
               placeholder: 'Pilih Periode',
               allowClear: false,
               // ajax: {
               //    url: "http://example.org/api/test",
               //    cache: false
               // }
               // minimumInputLength: 3,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  if (data.id !== '') {
                     bulan = data.id;
                     skIrisDataTable.ajax.reload();
                  }
                  return data.text;
               }
            });
            bulanConst.val(null);
            bulanConst.trigger('change');
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