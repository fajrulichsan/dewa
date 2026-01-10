<%@ include file="/META-INF/resources/init.jsp" %>

<style>
   .log_master_approval {
      margin: 25px;
   }

   .log_master_approval > .tabcontent {
      box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
      border-radius: 10px;
      padding: 0.75em;
   }

   #log_master_approval_table > thead {
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

   table#log_master_approval_table {
      border: 1px solid #DCDFE3;
      border-radius: 10px 10px 10px 10px;
   }

   table#log_master_approval_table {
      border-radius: 10px 10px 10px 10px;
   }

   #log_master_approval_table tbody tr {
      border: 1px solid #DCDFE3;
   }

   #log_master_approval_table > thead > tr > th {
      font-weight: normal;
      text-align: center;
   }

   .form-control {
      height: 40px !important;
      border: 2px solid #DBEDFF;
   }

   .form-control:focus {
      border: 2px solid #DBEDFF;
   }

   .btn_table, .btn_table:hover {
      border: 0;
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

<div class="log_master_approval">
   <h3 style="font-size: 24px; font-weight: 700; margin-bottom: 1em;">Log Master Approval</h3>
   <div class="tabcontent">
      <table id="log_master_approval_table" class="table table-hover nowrap" style="width:100%">
         <thead>
         <tr>
            <th style="text-align: center;"></th>
            <th>No</th>
            <th>Menu</th>
            <th>Role</th>
            <th>Approval Group</th>
            <th>Approvers</th>
            <th>Tanggal Create</th>
            <th>Tanggal Edit</th>
         </tr>
         </thead>
         <tbody></tbody>
      </table>
   </div>
</div>

<script>
   {
      var data = ${data};
      var logMasterApprovalTable;

      $(document).ready(function () {
         getMasterApprovalLogs();
      });

      var logApprovalTableLanguage = {
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

      function getMasterApprovalLogs() {
         createLoading();
         logMasterApprovalTable = $('#log_master_approval_table').DataTable({
            autoWidth: true,
            destroy: true,
            fixedHeader: true,
            info: true,
            language: logApprovalTableLanguage,
            ordering: true,
            order: [],
            paging: true,
            processing: false,
            responsive: true,
            searchDelay: 1000,
            searching: true,
            serverSide: false,
            columnDefs: [
               {
                  targets: 0,
                  checkboxes: {
                     selectRow: false
                  }
               }
            ],
            data: data,
            columns: [
               {
                  data: 'id',
                  width: '9',
                  targets: 0,
                  searchable: false,
                  orderable: false,
                  className: 'text-center'
               },
               { data: 'no', width: '5', className: 'text-center', orderable: false },
               { data: 'menuName' },
               { data: 'roleName' },
               { data: 'approvalGroup' },
               {
                  data: 'approvers',
                  render: function (data) {
                     if (data !== null) {
                        var table = "<table>";
                        $.each(data, function(key, value) {
                           table += "<tr><td>" + value.name + "</td></tr>";
                        });
                        return table + "</table>";
                     } else {
                        return "";
                     }
                  }
               },
               { data: 'createdDate' },
               { data: 'modifiedDate' }
            ],
            initComplete: function () {
               destroyLoading();
               var downloadElement =
                  '<a href="javascript:void(0)" class="btn btn-info" style="margin-left: 10px;" onclick="getChecks()">' +
                  'Download' +
                  '</a>'
               $('.dataTables_length').append(downloadElement);
            }
         });
      }

      function getChecks() {
         var logApprovals = [];
         var rows_selected = logMasterApprovalTable.column(0).checkboxes.selected();
         $.each(rows_selected, function (index, value) {
            var approval = getLogApproval(data, Number(value));
            var approvers = approval.approvers.map(item =>item.name).join(", ");
            logApprovals.push({
               menuName: approval.menuName,
               roleName: approval.roleName,
               approvalGroup: approval.approvalGroup,
               approvers: approvers,
               createdDate: approval.createdDate,
               modifiedDate: approval.modifiedDate,
            });
         });
         createExcels(logApprovals);
      }

      function createExcels(data) {
         if (data.length < 1) {
            Swal.fire("Info", "Data belum dipilih.", "");
            return;
         }

         const workbook = new ExcelJS.Workbook();
         workbook.creator = 'DEALINK';
         workbook.lastModifiedBy = 'DEALINK';
         workbook.created = new Date();
         workbook.modified = new Date();
         workbook.lasPrinted = new Date();

         const worksheet = workbook.addWorksheet('New Sheet');
         worksheet.columns = [
            { header: 'Menu', key: 'menuName' },
            { header: 'Role ', key: 'roleName' },
            { header: 'Approval Group', key: 'approvalGroup' },
            { header: 'Approvers', key: 'approvers' },
            { header: 'Tanggal Create', key: 'createdDate' },
            { header: 'Tanggal Edit', key: 'modifiedDate' }
         ];

         worksheet.addRows(data);
         worksheet.headerFooter.oddFooter = "Page &P of &N";

         workbook.xlsx.writeBuffer().then((xData) => {
            const blob = new Blob([xData], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });
            saveAs(blob, "Log Master Approval" + ".xlsx");
         });
      }

      function getLogApproval(array, id) {
         var obj = array.filter(function (val) {
            return val.id === id;
         });
         return obj[0];
      }
   }
</script>