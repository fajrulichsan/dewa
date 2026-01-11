<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/general-css.jsp" %>

<portlet:resourceURL id="/wilayah-list" var="wilayahListURL"/>
<portlet:resourceURL id="/wilayah-action" var="wilayahActionURL"/>
<portlet:renderURL var="wilayahEditURL">
   <portlet:param name="mvcRenderCommandName" value="/wilayah-edit"/>
</portlet:renderURL>

<%--
<style>
    .wilayah {
        margin: 25px;
    }

    .wilayah > .tabcontent {
        box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
        border-radius: 10px;
        padding: 0.75em;
    }

    #wilayah_table > thead {
        background-color: #014689;
        border: none !important;
        color: white;
        border-radius: 10px 10px 0 0;
    }

    .table > thead:first-child > tr:first-child > th:first-child {
        border-radius: 10px 0 0 0;
    }

    .table > thead:first-child > tr:first-child > th:last-child {
        border-radius: 0 10px 0 0;
    }

    table#wilayah_table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#wilayah_table {
        border-radius: 10px 10px 10px 10px;
    }

    #wilayah_table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #wilayah_table > thead > tr > th {
        font-weight: normal;
        text-align: center;
    }

    #approval_table_filter > label > input {
        background-image: url(<%=request.getContextPath()%>/assets/img/search.svg);
        background-position: 10px 8px;
        background-repeat: no-repeat;
        padding: 5px 20px 5px 40px;
    }

    .btn_table, .btn_table:hover {
        border: 0px;
        background-color: #014689 !important;
        color: white;
        font-weight: 400;
        opacity: 0.8;
        padding: 8px 16px;
        border-radius: 5px;
        margin-right: 15px;
    }

    /*Select2*/
    .select2-container--default .select2-selection--single {
        padding: 6px;
        margin-top: 16px;
        /*margin-bottom: 16px;*/
        height: 37px;
        /*width: 148px;*/
        font-size: 1.2em;
        position: relative;
    }
</style>
--%>

<div class="wilayah cms-menu">
   <h3 class="menu-title">Wilayah</h3>
   <div class="tabcontent">
      <table id="wilayah_table" class="table table-hover nowrap cms-table" style="width:100%">
         <thead>
            <tr>
               <th>No</th>
               <th>Name</th>
               <th>Action</th>
            </tr>
         </thead>
         <tbody></tbody>
      </table>
   </div>
</div>

<script>
   var wilayahDataTable;
   var column = "ALL";
   var keyword = "ALL";

   $(document).ready(function () {
      refresh();
      $('.dataTables_length select').select2({
         minimumResultsForSearch: -1
      });
   });

   function refresh() {
      column = 'ALL';
      keyword = 'ALL';
      getWilayahs();
   }

   var languageWilayahTable = {
      // info: "",
      "lengthMenu": "_MENU_",
      "paginate": {
         "first": "",
         "last": "",
         "next": '<i class="fas fa-chevron-right"></i>',
         "previous": '<i class="fas fa-chevron-left"></i>'
      },
      "search": "",
      searchPlaceholder: "Search..."
   }

   function getWilayahs() {
      wilayahDataTable = $('#wilayah_table').DataTable({
         // dom: '<"top"i>rt<"bottom"flp><"clear">',
         destroy: true,
         processing: false,
         serverSide: false,
         paging: true,
         info: true,
         searchDelay: 1000,
         searching: true,
         ordering: true,
         // select: true,
         autoWidth: true,
         responsive: true,
         order: [],
         // order: [[0, 'asc']],
         // scrollY: '65vh',
         fixedHeader: true,
         ajax: {
            url: '${wilayahListURL}',
            type: 'GET',
            data: function (d) {
               d.column = column;
               d.keyword = keyword;
               d.p_auth = Liferay.authToken;
            },
            // dataSrc: 'Data'
            dataSrc: function (json) {
               destroyLoading()
               return json.Data;
            }
         },
         language: languageWilayahTable,
         columns: [
            {
               data: null,
               width: "15",
               targets: 0,
               searchable: false,
               orderable: false,
               className: 'text-center',
               render: function (data, type, row, meta) {
                  return meta.row + 1;
               }
            },
            {data: "name"},
            {
               data: "id",
               className: "text-center",
               "render": function (data, type, row, meta) {
                  return renderActionButton(data);
               },
               orderable: false
            }
         ],
         initComplete: function () {
            $('.dataTables_filter input', $('#wilayah_table_wrapper'))
               .unbind()
               .bind("input", function (e) {
                  if (this.value.length >= 3 || e.keyCode === 13) {
                     wilayahDataTable.search(this.value).draw();
                  }
                  if (this.value === "") {
                     wilayahDataTable.search("").draw();
                  }
               });

            // $('.dataTables_filter').append($searchButton);
            $('.dataTables_filter').prepend('<a href="${wilayahEditURL}&id=0" class="btn btn-info btn_table"><span><i class="fas fa-plus" style="margin-right: 10px"></i> Tambah </span></a>');
            
            // Ubah col-md-6 menjadi col-md-1 dan col-md-11
            $('#wilayah_table_wrapper .row:first .col-sm-6:first').removeClass('col-sm-6').addClass('col-sm-1 col-md-1');
            $('#wilayah_table_wrapper .row:first .col-sm-6:last').removeClass('col-sm-6').addClass('col-sm-11 col-md-11');
         }
      });
   }

   function renderActionButton(dataId) {
      var response = "";
      response =
         '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a href="${wilayahEditURL}&id=' + dataId + '">' +
               '<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg"></img></span>' +
            '</a>' +
            // '<a href="javascript:void(0)" onclick="deleteWilayah(this)" data-id="' + dataId + '" >' +
            //    '<span><img src="<%=request.getContextPath()%>/assets/img/trash.svg"></img> </span>' +
            // '</a>' +
         '</span>';
      return response;
   }

   function deleteWilayah(element) {
      var entryId = $(element).data("id");
      Swal.fire({
         title: 'Do you want to delete the data?',
         icon: 'question',
         showCancelButton: true,
         confirmButtonText: 'Yes',
         confirmButtonColor: '#EE1C25',
      }).then((result) => {
         if (result.isConfirmed) {
            var formData = new FormData();
            formData.set("<portlet:namespace/>crudType", "delete");
            formData.set("<portlet:namespace/>entryId", entryId);
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            $.ajax({
               url: "${wilayahActionURL}",
               type: "POST",
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var data = JSON.parse(response);
                  if (data["acknowledge"] === 0) {
                     Swal.fire("Failed to Delete", data["message"], "");
                  } else {
                     Swal.fire("Successfully delete data", "", "success")
                        .then((res) => {
                           wilayahDataTable.ajax.reload();
                        });
                  }
               },
               error: function (err) {
                  console.log(err);
               }
            });
         }
      });
   }
</script>