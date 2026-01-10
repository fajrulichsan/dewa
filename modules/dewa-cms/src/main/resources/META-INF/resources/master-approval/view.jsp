<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/general-css.jsp" %>

<portlet:resourceURL id="/master-approval-list" var="masterApprovalListURL"/>
<portlet:resourceURL id="/master-approval-action-command" var="actionCommandURL"/>
<portlet:renderURL var="masterApprovalEditURL">
   <portlet:param name="mvcRenderCommandName" value="/master-approval-edit"/>
</portlet:renderURL>
<portlet:renderURL var="logMasterApprovalURL">
   <portlet:param name="mvcRenderCommandName" value="/log-master-approval"/>
</portlet:renderURL>

<%--
<style>
   .master_approval {
      margin: 25px;
   }

   .master_approval > .tabcontent {
		box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
		border-radius: 10px;
		padding: 0.75em;
   }

   #master_approval_table > thead {
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

   table#master_approval_table {
      border: 1px solid #DCDFE3;
      border-radius: 10px 10px 10px 10px;
   }

   table#master_approval_table {
      border-radius: 10px 10px 10px 10px;
   }

   #master_approval_table tbody tr {
      border: 1px solid #DCDFE3;
   }

   #master_approval_table > thead > tr > th {
      font-weight: normal;
      text-align: center;
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

   .form-control {
       height: 40px !important;
       border: 2px solid #DBEDFF;
   }

   .form-control:focus {
       border: 2px solid #DBEDFF;
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

<div class="master_approval cms-menu">
   <h3 class="menu-title">Master Approval</h3>
   <div class="tabcontent">
      <table id="master_approval_table" class="table table-hover nowrap cms-table" style="width:100%">
         <thead>
            <tr>
               <th>No</th>
               <th>Menu</th>
               <th>Action</th>
            </tr>
         </thead>
         <tbody></tbody>
      </table>
   </div>
</div>

<script>
   var disableAddButton = false;

   $(document).ready(function () {
      $('[data-toggle="tooltip"]').tooltip();

      $('.dataTables_length select').select2({
         minimumResultsForSearch: -1
      });
   });

   var masterApprovalTableLanguage = {
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

   var masterApprovalDataTable = $('#master_approval_table').DataTable({
      autoWidth: true,
      columnDefs: [],
      destroy: true,
      info: true,
      language: masterApprovalTableLanguage,
      order: [],
      ordering: true,
      paging: true,
      processing: false,
      responsive: true,
      searchDelay: 1000,
      searching: true,
      serverSide: false,
      ajax: {
         url: '${masterApprovalListURL}',
         type: 'GET',
         data: function (param) {
            param.p_auth = Liferay.authToken;
         },
         dataSrc: function (json) {
            if (json.Data.length >= 2) {
               disableAddButton = true;
               $('.dataTables_filter .add-ma-btn').addClass('disabled').attr('disabled', true);
            } else {
               disableAddButton = false;
               $('.dataTables_filter .add-ma-btn').removeClass('disabled').attr('disabled', false);
            }
            return json.Data;
         }
      },
      columns: [
         { data: "no", "width": "5%", className: "text-center", orderable: false },
         { data: "name", "width": "75%", className: "text-center" },
         {
            data: "id",
            "width": "20%",
            className: "text-center",
            orderable: false,
            "render": function (data, type, row, meta) {
               return renderActionButton(data, row['isEditable']);
            }
         }
      ],
      initComplete: function () {
         var disabled = '';
         if (disableAddButton) {
            disabled = 'disabled';
         }

         var element =
            '<a href="${masterApprovalEditURL}&id=0&p_auth=' + Liferay.authToken + '" class="btn btn-info btn_table add-ma-btn ' + disabled + '" ' + disabled + '>' +
               '<span>' +
                  '<i class="fas fa-plus" style="margin-right: 10px"></i>' +
                  'Tambah ' +
               '</span>' +
            '</a>';
         $('.dataTables_filter').prepend(element);

         <%--
         if (disableAddButton = true) {
            $('.dataTables_filter .add-ma-btn').addClass('disabled').prop('disabled', true);
         } else {
            $('.dataTables_filter .add-ma-btn').removeClass('disabled').prop('disabled', false);
         }
         --%>
      }
   });

   function renderActionButton(dataId, row) {
      var response = '';
      response =
         '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a href="${logMasterApprovalURL}&id=' + dataId + '&p_auth=' + Liferay.authToken + '">' +
               '<span><img data-toggle="tooltip" title="View" src="<%=request.getContextPath()%>/assets/img/eye.svg"></img></span>' +
            '</a>' +
      // if (row === true) {
      //    response +=
            '<a href="${masterApprovalEditURL}&id=' + dataId + '&p_auth=' + Liferay.authToken + '">' +
               '<span><img data-toggle="tooltip" title="Edit" src="<%=request.getContextPath()%>/assets/img/edit.svg"></img></span>' +
            '</a>' +
      // }
      // response +=
            '<a href="javascript:void(0)" onclick="deleteData(this)" data-id="' + dataId + '">' +
               '<span><img data-toggle="tooltip" title="Hapus" src="<%=request.getContextPath()%>/assets/img/trash.svg"></img></span>' +
            '</a>' +
         '</span>';
      return response;
   }

   function deleteData(element) {
      var entryId = $(element).data("id");
      Swal.fire({
         title: 'Do you want to delete the data?',
         icon: 'question',
         showCancelButton: true,
         confirmButtonText: 'Yes',
         confirmButtonColor: '#EE1C25',
      }).then((result) => {
         if (result.isConfirmed) {
            createLoading();
            var formData = new FormData();
            formData.set("<portlet:namespace/>crudType", "delete");
            formData.set("<portlet:namespace/>entryId", entryId);
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

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
                        masterApprovalDataTable.ajax.reload();
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
</script>