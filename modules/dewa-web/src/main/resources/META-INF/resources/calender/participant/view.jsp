<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="calender-event-participant-list" var="calenderEventParticipantListURL"/>
<portlet:resourceURL id="calender-event-participant-action" var="calenderEventParticipantActionURL"/>
<portlet:renderURL var="calenderEventParticipantEditURL">
   <portlet:param name="mvcRenderCommandName" value="calender-event-participant-edit"/>
</portlet:renderURL>

<style>
    .calender_event {
        margin: 25px;
    }

    .calender_event > .tabcontent {
        box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
        border-radius: 10px;
        padding: 0.75em;
    }

    #calender_event_participant_table > thead {
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

    table#calender_event_participant_table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#calender_event_participant_table {
        border-radius: 10px 10px 10px 10px;
    }

    #calender_event_participant_table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #calender_event_participant_table > thead > tr > th {
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

    .modal-dialog {
        position: absolute;
        left: 0;
        right: 0;
        top: 10%;
        bottom: 0;
        margin: auto;
        /*!*width:500px;*!*/
        /*!*height:300px;*!*/
    }
</style>

<div class="cms-content">
   <%--<h3 id="eventTitle" style="font-size: 24px; font-weight: 700; margin-bottom: 1em;">Event</h3>--%>
   <div class="tabcontent" id="cms-tabcontent">
      <ul class="nav nav-tabs" id="cmsTab" role="tablist">
         <li class="nav-item">
            <a class="nav-link" id="kelola_acara_navtab" data-toggle="" href="/group/dealink/cms-calendar-event" role="tab" aria-controls="kelola-acara" aria-selected="true">Kelola Acara</a>
         </li>
         <li class="nav-item">
            <a class="nav-link" id="participant_acara_navtab" data-toggle="tab" href="#participant_acara_tab" role="tab" aria-controls="participant-acara" aria-selected="true">Peserta</a>
         </li>
      </ul>
      <div class="tab-content" id="myTabContent">
         <div class="tab-pane fade" id="participant_acara_tab" role="tabpanel" aria-labelledby="profile-tab">
            <h3 id="eventTitle" style="font-size: 24px; font-weight: 700; margin-bottom: 1em;">Event</h3>
            <div class="row" style="margin-bottom: 16px;">
               <div class="col-xs-12 col-sm-12 col-md-3">
                  <button class="btn btn-info btn_table" onclick="downloadExcel()">Unduh Data Peserta</button>
                  <%--<button class="btn btn-info btn_table" onclick="openParticipant();">Create</button>--%>
                  <%--<a id="createParticipant" href="" class="btn btn-info btn_table">Create</a>--%>
               </div>
               <div class="col-xs-12 col-sm-12 col-md-3"></div>
               <div class="col-xs-12 col-sm-12 col-md-6" style="text-align: right;"></div>
            </div>
            <table id="calender_event_participant_table" class="table table-hover nowrap" style="width:100%">
               <thead>
               <tr>
                  <%--<th>No</th>--%>
                  <th style="text-align: center;">
                     <%--<input type="checkbox" name="checkSelectAll" id="checkSelectAll" value="1" />--%>
                  </th>
                  <th>Nama</th>
                  <th>Email</th>
                  <th>No. Telepon</th>
                  <th>Nama Dealer</th>
                  <%--<th>Aksi</th>--%>
               </tr>
               </thead>
               <tbody></tbody>
            </table>
         </div>
      </div>
   </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
   <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
         </div>
         <div class="modal-body">
            ...
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" onclick="hideParticipant();">Save changes</button>
         </div>
      </div>
   </div>
</div>

<script>
   var calenderEventId = '${calenderEventId}'
   var eventName = '${calenderEventName}'
   var dataParticipants = []

   $(document).ready(function () {
      dataParticipants = ${dataSet}
      $('#eventTitle').text(eventName)
      $('#participant_acara_navtab')[0].click()
      $("#createParticipant").attr("href", "${calenderEventParticipantEditURL}&id=0")
   });

   function openParticipant() {
      $('#myModal').modal({
         backdrop: 'static',
         keyboard: false
      }).show()
   }

   function hideParticipant() {
      $('#myModal').modal('hide')
   }

   var languageOptions = {
      info: "",
      "lengthMenu": "_MENU_",
      "paginate": {
         "next":       '<span class="glyphicon glyphicon-menu-right"></span>',
         "previous":   '<span class="glyphicon glyphicon-menu-left"></span>'
      },
      "search":         "",
      searchPlaceholder: "Cari Dokumen"
   }

   function renderActionButton(dataId) {
      var response = "";
      response = '<span class="action-wrapper" data-id="' + dataId + '">' +
         '<a href="${calenderEventParticipantEditURL}&id=' + dataId + '" >' +
            '<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg" /></span>' +
         '</a> &nbsp;' +
         '<a href="javascript:void(0)" onclick="deleteParticipant(this)" data-id="' + dataId + '" >' +
          	'<span><img src="<%=request.getContextPath()%>/assets/img/trash.svg" /></span>' +
         '</a>' +
      '</span>';
      return response;
   }

   var calenderEventParticipantTable = $('#calender_event_participant_table').DataTable({
      destroy: true,
      processing: false,
      serverSide: false,
      paging: false,
      info: false,
      searching: false,
      ordering: true,
      // select: true,
      autoWidth: true,
      responsive: true,
      // order: [],
      // scrollY: '65vh',
      // fixedHeader: true,
      language: languageOptions,
      columnDefs: [
         // {
         //    targets: [2,3], visible: false
         // },
         // {
         //    targets: [4,5], orderable: false
         // },
         {
            targets: 0,
            checkboxes: {
               selectRow: false
            }
         }
      ],
      // select: {
      //    style: 'multi'
      // },
      // dom: 'Bfrtip',
      // buttons: [
      //    'copy',
      //    'csv',
      //    {
      //       extend: 'excel',
      //       text: 'Download Exel',
      //       title: 'Daftar Peserta',
      //       exportOptions: {
      //          modifier: {
      //             selected: null
      //          }
      //       }
      //    },
      //    {
      //       extend: 'pdf',
      //       text: 'Download Pdf',
      //       title: 'Daftar Peserta',
      //       exportOptions: {
      //          columns: [0,1,2],
      //          modifier: {
      //             selected: null
      //          }
      //       }
      //    },
      //    {
      //       extend: 'print',
      //       text: 'Print all (not just selected)',
      //       exportOptions: {
      //          modifier: {
      //             selected: null
      //          }
      //       }
      //    }
      // ],
      <%--data: ${dataSet},--%>
      ajax: {
         url: '${calenderEventParticipantListURL}',
         type: 'GET',
         data: function(d){
            d.calenderEventId =  calenderEventId;
         },
         // dataSrc: 'Data'
         dataSrc: function (json) {
            dataParticipants = json.Data;
            return json.Data;
         }
      },
      columns: [
         // {data: "no", "width": "5%", className: "text-center"},
         {
            data: "id",
            "width": "9%",
            'targets': 0,
            'searchable':false,
            'orderable':false,
            'className': 'text-center',
            // 'render': function (data, type, full, meta) {
            //    return '<input type="checkbox" name="id[]" value="' + data + '"/>';
            // }
         },
         {data: "fullName", "width": "20%"},
         {data: "email", "width": "20%"},
         {data: "phone", "width": "20%"},
         {
            data: "dealerName",
            "render": function (data, type, row, meta) {
               return viewDealer(data, row);
            },
         },
         // {
         // 	data: "description",
         // 	orderable: false,
         // 	render: $.fn.dataTable.render.ellipsis(25)
         // },
         // {
         //    data: "id",
         //    "width": "7%",
         //    className: "text-center",
         //    "render": function (data, type, row, meta) {
         //       return renderActionButton(data);
         //    },
         //    orderable: false
         // }
      ]
   });

   function viewDealer(dataId, row) {
      // return row.dealerCode + " - " + row.dealerName + " - " + row.cabangName;
      return row.dealerName + " - " + row.cabangName;
   }

   function getParticipant(array, id) {
      var obj = array.filter(function (val) {
         return val.id === id;
      });
      return obj[0];
   }

   function downloadExcel() {
      var participants = [];
      var rows_selected = calenderEventParticipantTable.column(0).checkboxes.selected();
      $.each(rows_selected, function(index, rowValue){
         var participant = getParticipant(dataParticipants, rowValue);
         participants.push(
            {
               id: participant.id,
               fullName: participant.fullName,
               email: participant.email,
               phone: participant.phone,
               dealerName: participant.dealerName + " - " + participant.cabangName
            }
         )
      });
      createExcels(participants)
   }

   function createExcels(participants) {
      if(participants.length < 1) {
         Swal.fire("Info", "Data belum terpilih.", "");
         return;
      }
      const workbook = new ExcelJS.Workbook();
      workbook.creator = 'M Abdul Honi';
      workbook.lastModifiedBy = 'Bot';
      workbook.created = new Date();
      workbook.modified = new Date();
      workbook.lastPrinted = new Date();

      const worksheet = workbook.addWorksheet('New Sheet');
      worksheet.columns = [
         { header: 'NAMA LENGKAP', key: 'fullName' },
         { header: 'EMAIL', key: 'email' },
         { header: 'TELEPHONE', key: 'phone' },
         { header: 'NAMA DEALER', key: 'dealerName' }
      ];
      // worksheet.addRow({id: 1, name: 'John Doe', age: 35});
      worksheet.addRows(participants);

      // Set footer (default centered), result: "Page 2 of 16"
      worksheet.headerFooter.oddFooter = "Page &P of &N";

      workbook.xlsx.writeBuffer().then((xData) => {
         const blob = new Blob([xData], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });
         saveAs(blob, eventName + ".xlsx");
      });
   }

   function deleteParticipant(element) {
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
            formData.append("crudType", "delete");
            formData.append("calenderEventId", entryId);

            $.ajax({
               url: "${calenderEventParticipantActionURL}",
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
                        calenderEventParticipantTable.ajax.reload();
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