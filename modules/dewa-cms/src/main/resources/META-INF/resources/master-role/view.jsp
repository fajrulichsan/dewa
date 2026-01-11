<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/general-css.jsp" %>

<portlet:resourceURL id="/master-role-list" var="masterRoleListURL"/>
<portlet:resourceURL id="/master-role-action" var="masterRoleActionURL"/>

<portlet:renderURL var="otorisasiMenuAddURL">
   <portlet:param name="mvcRenderCommandName" value="/master-role-add"/>
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

    .form-control {
        height: 40px !important;
        border: 2px solid #DBEDFF;
    }

    /*modal*/
    .role-input {
        padding-right: 30px;
    }

    .modal-content {
        border-radius: 10px;
        padding: 20px;
    }

    .modal-header, .modal-footer {
        border: none;
    }

    .modal-header {
        padding-bottom: 0;
    }

    .modal-footer {
        padding-top: 0;
    }

    .form-group {
        margin-bottom: 1rem;
    }

    /* Center the modal */
    .modal-dialog-centered {
        display: flex;
        align-items: center;
        min-height: calc(100% - 1rem);
    }

    /* Blur background */
    .modal-backdrop.show {
        opacity: 0.5;
        filter: blur(5px);
    }
</style>
--%>

<style>
    /* Table Header Blue */
    #otorisasi-menu-table thead th {
        background-color: #014689 !important;
        color: white !important;
    }

    /* DataTables Sorting Icons with FontAwesome */
    table.dataTable thead .sorting:before,
    table.dataTable thead .sorting_asc:before,
    table.dataTable thead .sorting_desc:before {
        font-family: "Font Awesome 6 Free";
        font-weight: 900;
        position: absolute;
        right: 8px;
        top: 50%;
        transform: translateY(-50%);
    }

    table.dataTable thead .sorting:before {
        content: "\f0dc";
        opacity: 0.3;
    }

    table.dataTable thead .sorting_asc:before {
        content: "\f0de";
        opacity: 1;
    }

    table.dataTable thead .sorting_desc:before {
        content: "\f0dd";
        opacity: 1;
    }

    table.dataTable thead th {
        position: relative;
        padding-right: 30px;
    }

    /* Pagination Style like Banner */
    .dataTables_wrapper .dataTables_paginate .paginate_button {
        border: none !important;
        margin: 0 3px;
        border-radius: 5px;
        transition: background-color 0.3s ease;
        padding: 5px 10px;
    }

    .dataTables_wrapper .dataTables_paginate .paginate_button.current,
    .dataTables_wrapper .dataTables_paginate .paginate_button.current:hover {
        background-color: #014689 !important;
        color: #ffffff !important;
        border: none !important;
    }

    .dataTables_wrapper .dataTables_paginate .paginate_button:hover {
        background: #e9ecef !important;
        color: #000 !important;
        border: none !important;
    }

    .dataTables_wrapper .dataTables_paginate .paginate_button.disabled,
    .dataTables_wrapper .dataTables_paginate .paginate_button.disabled:hover {
        color: #ccc !important;
    }

    .dataTables_wrapper .dataTables_paginate .paginate_button.previous,
    .dataTables_wrapper .dataTables_paginate .paginate_button.next {
        border: none;
        border-radius: 5px;
    }
</style>

<div class="otorisasi-menu cms-menu">
   <h3 class="menu-title">Master Role</h3>
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

<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
   <div class="modal-dialog modal-lg">
      <div class="modal-content">
         <div class="modal-body">
            <form id="formOtorisasiMenu" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
               <div class="form-group role-input">
                  <label class="title-form">Role <span style="color: red;">*</span></label>
                  <input type="text" class="form-control" id="roleName" name="roleName">
               </div>
            </form>
         </div>
         <div class="modal-footer">
            <button id="submitOtorisasiMenu" class="btn btn-primary" type="submit">Save</button>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
         </div>
      </div>
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
         "next": '<i class="fas fa-chevron-right"></i>',
         "previous": '<i class="fas fa-chevron-left"></i>'
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
            url: '${masterRoleListURL}',
            type: 'GET',
            data: function (d) {
               d.column = column
               d.keyword = keyword
            },
            // dataSrc: 'Data',
            dataSrc: function (json) {
               destroyLoading()
               return json.Data;
            }
         },
         language: languageOtorisasiMenuTable,
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
            $('.dataTables_filter').prepend('<a href="${otorisasiMenuAddURL}&id=0" class="btn btn-info btn_table"><span><i class="fas fa-plus" style="margin-right: 10px"></i> Tambah </span></a>');
            
            // Ubah col-md-6 menjadi col-md-1 dan col-md-11
            $('#otorisasi-menu-table_wrapper .row:first .col-sm-6:first').removeClass('col-sm-6').addClass('col-sm-1 col-md-1');
            $('#otorisasi-menu-table_wrapper .row:first .col-sm-6:last').removeClass('col-sm-6').addClass('col-sm-11 col-md-11');
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
            formData.set("<portlet:namespace/>crudType", "delete");
            formData.set("<portlet:namespace/>id", id);
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            $.ajax({
               url: "${masterRoleActionURL}",
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

   function openForm(element) {
      $('#myModal').modal({
         backdrop: 'static',
         keyboard: false,
         isBodyOverflowing: true
      }).show();
      $('#myModal').data('bs.modal').handleUpdate();
      clearForm();
      bonusId = $(element).data("id");
      getBonus();
   }
</script>