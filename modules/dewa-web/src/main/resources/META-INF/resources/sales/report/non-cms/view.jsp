<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/non-cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/sales/report/non-cms/style-css.jsp"%>

<portlet:resourceURL id="/non-cms/sales-report/list" var="salesReportListURL"/>
<portlet:resourceURL id="/non-cms/sales-report/download" var="downloadfileSalesReportURL"/>
<portlet:resourceURL id="/non-cms/dealers-with-details" var="dealerSalesReportURL"/>

<div class="sales_report non-cms-menu">
   <ol class="breadcrumb">
      <li>Home</li>
      <li>Report</li>
      <li>Unit</li>
      <li>Sales Report</li>
   </ol>

   <div class="main-title">
      <h3 >Sales Report</h3>
   </div>

   <div class="row table-filters">
      <%--
      <div class="col-xs-12 col-sm-12 col-md-3 col1"></div>
      <div class="col-xs-12 col-sm-12 col-md-3 col2"></div>.
      --%>
      <div class="col-xs-12 col-sm-12 col-md-3" id="dealer-filter">
         <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;">
            <option value="ALL">Select All</option>
         </select>
      </div>
      <div class="col-xs-12 col-sm-12 col-md-3 period-filter">
         <div class="input-group">
            <input type="text" onkeydown="return false" class="form-control dateIcon required" name="periode-date" placeholder="Pilih Tanggal Faktur">
            <span class="input-group-btn">
               <button class="btn btn-info btn_table" onclick="refresh()">
                  <span><i class="icon-refresh"></i></span>
               </button>
            </span>
         </div>
      </div>
   </div>
   <div class="tabcontent">
      <table id="sales_report_table" class="table table-hover nowrap non-cms-table" style="width:100%">
         <thead>
            <tr>
               <th>No</th>
               <th>Sales Report</th>
               <th>Periode Faktur</th>
               <th>Tanggal Upload</th>
               <th>Aksi</th>
            </tr>
         </thead>
         <tbody></tbody>
      </table>
   </div>
</div>

<script>
   var dealerId = "ALL";
   var periode = "ALL";
   var startDate = new Date(1900, 0, 0);

   $(document).ready(function () {
      getDealers();
      setFakturDatepicker();
      $('.dataTables_length select').select2({
         minimumResultsForSearch: -1
      });
   });

   function refresh() {
      $('[name=periode-date]')[0].value = "";
      periode = "ALL";
      salesReportDataTable.ajax.reload();
   }

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

   var salesReportDataTable = $('#sales_report_table').DataTable({
      searching: false,
      paging: true,
      info: false,
      language: tableLanguage,
      dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
      ajax: {
         url: '${salesReportListURL}',
         type: 'GET',
         data: function (d) {
            d.dealerId = dealerId;
            d.periode = periode;
         },
         dataSrc: function (json) {
            role = json.role;
            if (role.toLowerCase().startsWith('dealer')) {
               $('#dealer-filter').remove();
               <%--
               $('.col2').removeClass('col-md-3').addClass('col-md-6');
               --%>
            }
            return json.Data;
         }
      },
      columns: [
         {
            data: "no",
            width: "5%",
            className: "text-center"
         },
         {
            data: "fileName",
            width: "30%"
         },
         {
            data: "periode",
            width: "30%",
            className: "text-center",
            render: function (data, type, row, meta) {
               if (type === "sort") {
                  return row.periodeSort;
               }
               return data;
            },
         },
         {
            data: "uploadDate",
            width: "30%",
            className: "text-center",
            render: function (data, type, row, meta) {
               if (type === "sort") {
                  return row.uploadDateSort;
               }
               return data;
            },
         },
         {
            data: "id",
            width: "5%",
            className: "text-center",
            render: function (data, type, row, meta) {
               return renderActionButton(row.id, row.fileId);
            },
            orderable: false
         }
      ],
      responsive: true,
      order: [],
      columnDefs: [
         {
            targets: [2],
            orderData: [2, 3]
         }
      ]
   });

   function renderActionButton(dataId, row) {
      var downloadURL = new URL("${downloadfileSalesReportURL}");
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

   function getDealers() {
      $.ajax({
         url: "${dealerSalesReportURL}",
         type: "POST",
         data: {"dealerId": "test"},
         success: function (response) {
            var responseData = JSON.parse(response);
            var dealerConst = $('#dealerId').select2({
               data: responseData.Data,
               tags: "true",
               placeholder: 'List Dealer',
               allowClear: false,
               maximumInputLength: 100,

               templateSelection: function (data) {
                  if (data.id !== '') {
                     dealerId = data.id;
                     salesReportDataTable.ajax.reload();
                  }
                  return data.text;
               }
            });
            dealerConst.val(null);
            dealerConst.trigger('change');
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            console.log("complete");
         }
      });
   }

   function setFakturDatepicker() {
      $('[name=periode-date]').datepicker({
         forceParse: false,
         startDate: startDate,
         meridiem: '',
         format: 'dd/mm/yyyy',
         autoclose: true
      }).on('changeDate', function () {
         var date = $(this).datepicker('getDate');
         periode = moment(date).format("YYYY-MM-DD");
         salesReportDataTable.ajax.reload();
         $(this).datepicker('hide');
      });
   }

</script>

