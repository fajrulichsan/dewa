<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/general-css.jsp" %>

<portlet:resourceURL id="/otorisasi-menu-list" var="otorisasiMenuListURL"/>
<portlet:resourceURL id="/otorisasi-menu-action" var="otorisasiMenuActionURL"/>

<portlet:renderURL var="otorisasiMenuAddURL">
   <portlet:param name="mvcRenderCommandName" value="/otorisasi-menu-add"/>
</portlet:renderURL>

<%--
<style>
    .otorisasi-menu {
        margin: 25px;
    }

    .otorisasi-menu > .tabcontent {
        box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
        border-radius: 10px;
        padding: 0.75em;
    }

    #otorisasi-menu-table > thead {
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

    table#otorisasi-menu-table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#otorisasi-menu-table {
        border-radius: 10px 10px 10px 10px;
    }

    #otorisasi-menu-table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #otorisasi-menu-table > thead > tr > th {
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

<div class="otorisasi-menu cms-menu">
   <h3 class="menu-title">Otorisasi Menu</h3>
   <div class="tabcontent">
      <table id="otorisasi-menu-table" class="table table-hover nowrap cms-table" style="width:100%">
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
   var otorisasiMenuDataTable;
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
      getOtorisasiMenu();
   }

   var languageOtorisasiMenuTable = {
      // info: "",
      "lengthMenu": "_MENU_",
      "paginate": {
         "first": "",
         "last": "",
         "next": '<span class="glyphicon glyphicon-menu-right"></span>',
         "previous": '<span class="glyphicon glyphicon-menu-left"></span>'
      },
      "search": "",
      searchPlaceholder: "Search..."
   }

   function getOtorisasiMenu() {
      // createLoading();
      otorisasiMenuDataTable = $('#otorisasi-menu-table').DataTable({
         destroy: true,
         processing: false,
         serverSide: false,
         paging: true,
         info: true,
         searchDelay: 1000,
         searching: true,
         ordering: true,
         autoWidth: true,
         responsive: true,
         order: [],
         fixedHeader: true,
         ajax: {
            url: '${otorisasiMenuListURL}',
            type: 'GET',
            data: function (d) {
               d.column = column;
               d.keyword = keyword;
            },
            // dataSrc: 'Data',
            dataSrc: function (json) {
               destroyLoading();
               return json.Data;
            }
         },
         language: languageOtorisasiMenuTable,
         columns: [
            {
               data: "no",
               width: "15",
               targets: 0,
               searchable: false,
               orderable: false,
               className: 'text-center',
            },
            {data: "name"},
            {
               data: "id",
               "render": function (data, type, row, meta) {
                  return renderActionButton(data);
               },
               orderable: false,
               className: 'text-center'
            }
         ],
         initComplete: function () {
            $('.dataTables_filter input', $('#otorisasi-menu-table_wrapper'))
               .unbind()
               .bind("input", function (e) {
                  if (this.value.length >= 3 || e.keyCode === 13) {
                     otorisasiMenuDataTable.search(this.value).draw();
                  }
                  if (this.value === "") {
                     otorisasiMenuDataTable.search("").draw();
                  }
               });

            // $('.dataTables_filter').append($searchButton);
            $('.dataTables_filter').prepend('<a href="${otorisasiMenuAddURL}&id=0" class="btn btn-info btn_table"><span><i class="fas fa-plus" style="margin-right: 10px"></i>Tambah</span></a>');
         }
      });
   }

   function renderActionButton(dataId) {
      var response = "";
      response =
         '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a href="${otorisasiMenuAddURL}&id=' + dataId + '" >' +
                '<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg"></img></span>' +
             '</a>' +
             '<a href="javascript:void(0)" onclick="deleteOtorisasiMenu(this)" data-id="' + dataId + '" >' +
                 '<span><img src="<%=request.getContextPath()%>/assets/img/trash.svg"></img></span>' +
             '</a>' +
         '</span>';
      return response;
   }

   function deleteOtorisasiMenu(element) {
      var id = $(element).data("id");
      Swal.fire({
         title: 'Do you want to delete the data?',
         icon: 'question',
         showCancelButton: true,
         confirmButtonText: 'Yes',
         confirmButtonColor: '#EE1C25',
      }).then((result) => {
         if (result.isConfirmed) {
            var formData = new FormData();
            formData.append("crudType", "delete");
            formData.append("id", id);

            $.ajax({
               url: "${otorisasiMenuActionURL}",
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
                           otorisasiMenuDataTable.ajax.reload();
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