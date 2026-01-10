<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/non-cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/kategori-dealer/non-cms/style-css.jsp" %>

<portlet:resourceURL id="/kategori-dealer-noncms-list" var="kategoriDealerNonCMSListURL"/>
<portlet:resourceURL id="/kategori-dealer-noncms-action" var="kategoriDealerNonCMSActionURL"/>
<portlet:resourceURL id="/download-kategori-dealer-noncms" var="downloadKategoriDealerNonCMSActionURL">
   <portlet:param name="entryId" value="0"/>
</portlet:resourceURL>
<portlet:resourceURL id="/tahun-kategori-dealer-noncms" var="tahunKategoriDealerNonCMSURL"/>
<portlet:resourceURL id="/periode-review-kategori-dealer-noncms" var="periodeReviewKategoriDealerNonCMSURL"/>

<div class="kategori_dealer_nonCMS non-cms-menu">
   <ol class="breadcrumb">
      <li>Home</li>
      <li>Download</li>
      <li>Unit</li>
      <li>Kategori Dealer</li>
   </ol>

   <div class="main-title">
      <h3>Kategori Dealer</h3>
   </div>

   <div class="row table-filters">
      <div class="col-xs-12 col-md-6 row sub-filters">
         <div class="col-xs-6">
            <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
               <option value="ALL">Select All</option>
            </select>
         </div>
         <div class="col-xs-6">
            <select class="form-control bulanId periodeReviewId" name="periodeReviewId" style="width: 100%;">
               <option value="ALL">Select All</option>
            </select>
         </div>
      </div>
   </div>

   <div class="tabcontent">
      <table id="kategori_dealer_nonMCS_table" class="table table-hover nowrap row-border non-cms-table" style="width:100%">
         <thead>
         <tr>
            <th>No</th>
            <th>Kategori Dealer</th>
            <th>Periode Review</th>
            <th>Tahun</th>
            <th>Tanggal Upload</th>
            <th>Aksi</th>
         </tr>
         </thead>
         <tbody></tbody>
      </table>
   </div>
</div>

<script>
   var tahun = "ALL";
   var periodeReview = "ALL";

   $(document).ready(function () {
      getTahuns();
      getPeriodeReviews();
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

   var kategoriDealerNonCMSDataTable = $('#kategori_dealer_nonMCS_table').DataTable({
      searching: false,
      paging: true,
      responsive: false,
      language: tableLanguage,
      dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
      info: false,
      ajax: {
         url: '${kategoriDealerNonCMSListURL}',
         type: 'GET',
         data: function (d) {
            d.tahun = tahun;
            d.periodeReview = periodeReview;
         },
         dataSrc: function (json) {
            return json.Data;
         }
      },
      columns: [
         {data: "no", "width": "5%", className: "text-center"},
         {data: "fileName", "width": "25%"},
         {data: "periodeReviewName", "width": "20%", className: "text-center"},
         {data: "tahun", "width": "15%", className: "text-center"},
         {
            data: "dateFile",
            "width": "15%",
            className: "text-center",
            "render": function (data, type, row, meta) {
               if (type === "sort") {
                  return row.dateSorting;
               }
               return data;
            },
         },
         {
            data: "id",
            "width": "7%",
            className: "text-center",
            "render": function (data, type, row, meta) {
               return renderActionButton(data);
            },
            orderable: false
         }
      ],
      order: [],
      columnDefs: [
         {
            targets: [2],
            orderData: [2, 3]
         }
      ]
   });

   function renderActionButton(dataId) {
      var downloadURL = new URL("${downloadKategoriDealerNonCMSActionURL}");
      downloadURL.searchParams.set("<portlet:namespace/>entryId", dataId);
      var response = "";
      response =
         '<span class="action-wrapper" data-id="' + dataId + '">' +
             '<a target="_blank" href="' + downloadURL.toString() + '" style="margin-right: 5px;" class="text-center">' +
                'Unduh Data' +
             '</a>' +
         '</span>';
      return response;
   }

   function getTahuns() {
      $.ajax({
         url: "${tahunKategoriDealerNonCMSURL}",
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
                     kategoriDealerNonCMSDataTable.ajax.reload();
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

   function getPeriodeReviews() {
      $.ajax({
         url: "${periodeReviewKategoriDealerNonCMSURL}",
         type: "POST",
         data: {"periodeReviewId": "test"},
         success: function (response) {
            var periodeReviewData = JSON.parse(response);
            const periodeReviewConst = $('.periodeReviewId').select2({
               data: periodeReviewData.Data,
               placeholder: 'Pilih Periode',
               allowClear: false,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  if (data.id !== '') {
                     periodeReview = data.id;
                     kategoriDealerNonCMSDataTable.ajax.reload();
                  }
                  return data.text;
               }
            });
            periodeReviewConst.val(null);
            periodeReviewConst.trigger('change');
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