<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="upload-bonus-monitoring-list" var="uploadBonusListURL"/>
<portlet:resourceURL id="upload-bonus-monitoring-list-card" var="uploadBonusListCardURL"/>
<portlet:resourceURL id="upload-bonus-monitoring-single" var="uploadBonusSingleURL"/>
<portlet:resourceURL id="upload-bonus-monitoring-action" var="uploadBonusActionURL"/>
<portlet:resourceURL id="upload-bonus-monitoring-update-status-action" var="uploadBonusUpdateStatusActionURL"/>
<portlet:renderURL var="uploadBonusEditURL">
   <portlet:param name="mvcRenderCommandName" value="upload-bonus-monitoring-edit"/>
</portlet:renderURL>

<%--<portlet:resourceURL id="upload-bonus-monitoring-dealer" var="dealerUploadBonusURL"/>--%>
<portlet:resourceURL id="/non-cms/dealers-with-details" var="dealerUploadBonusURL"/>
<portlet:resourceURL id="upload-bonus-monitoring-application-category" var="requestUploadBonusURL"/>
<portlet:resourceURL id="upload-bonus-monitoring-application-assign-status" var="statusUploadBonusURL"/>
<portlet:resourceURL id="upload-bonus-monitoring-tahun" var="tahunUploadBonusURL"/>

<portlet:resourceURL id="/non-cms/download-upload-menu-file" var="downloadFileURL"/>

<link href="<%=request.getContextPath()%>/upload/bonus/monitoring/css/monitoring.css" rel="stylesheet" type="text/css">
<style>
    /*CUSTOM TUI STYLE*/
    .tui-pagination .tui-page-btn {
        border: none !important;
        margin: 0 3px;
        border-radius: 5px;
    }

    .tui-pagination .tui-is-selected, .tui-pagination .tui-is-selected:hover {
        background-color: #014689;
    }

    .tui-last, .tui-first {
        display: none !important;
    }

    .tui-pagination .tui-next, .tui-pagination .tui-prev {
        border: none;
        border-radius: 5px;
    }

    .tui-is-disabled.tui-prev .tui-ico-prev {
        width: 9px;
        height: 16px;
        background: url('<%=request.getContextPath()%>/assets/img/chevron-left-disabled.svg') no-repeat 0 0;
    }

    .tui-prev .tui-ico-prev {
        width: 9px;
        height: 16px;
        background: url('<%=request.getContextPath()%>/assets/img/chevron-left.svg') no-repeat 0 0;
    }

    .tui-is-disabled.tui-next .tui-ico-next {
        width: 9px;
        height: 16px;
        background: url('<%=request.getContextPath()%>/assets/img/chevron-right-disabled.svg') no-repeat 0 0;
    }

    .tui-next .tui-ico-next {
        width: 9px;
        height: 16px;
        background: url('<%=request.getContextPath()%>/assets/img/chevron-right.svg') no-repeat 0 0;
    }
    /*END OF TUI STYLE*/
</style>

<div class="row">
   <h5 class="title-breadcrumb"></h5>
</div>

<div class="row">
   <h5 class="title-format"></h5>
</div>

<div class="row" style="margin-bottom: 15px;">
   <div class="col-xs-12 col-sm-12 col-md-2" style="margin-bottom: 5px;">
      <select class="form-control" name="columnId" id="columnId" style="width: 100%;"></select>
   </div>
   <div class="col-xs-12 col-sm-12 col-md-2 requestId" style="margin-bottom: 5px;">
      <select class="form-control" name="requestId" id="requestId" style="width: 100%;">
         <option value="ALL">Select All</option>
      </select>
   </div>
   <div class="col-xs-12 col-sm-12 col-md-3 dealerList" style="margin-bottom: 5px;">
      <select class="form-control" name="dealerList" id="dealerList" style="width: 100%;">
         <option value="ALL">Select All</option>
      </select>
   </div>
   <div class="col-xs-12 col-sm-12 col-md-4 ticketList" style="margin-bottom: 5px;">
      <input type="text" class="form-control" id="ticketList" placeholder="">
   </div>
   <div class="col-xs-12 col-sm-12 col-md-2 ticketDateList" style="margin-bottom: 5px;">
      <select class="form-control" name="ticketDateList" id="ticketDateList" style="width: 100%;">
         <option value="ALL">Select All</option>
      </select>
   </div>
   <div class="col-xs-12 col-sm-12 col-md-2 statusId" style="margin-bottom: 5px;">
      <select class="form-control" name="statusId" id="statusId" style="width: 100%;">
         <option value="ALL">Select All</option>
      </select>
   </div>
   <div class="col-xs-12 col-sm-12 col-md-2 tahunId" style="margin-bottom: 5px;">
      <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
         <option value="ALL">Select All</option>
      </select>
   </div>
</div>

<div id="upload_bonus_content">
   <table id="upload_bonus_table" class="table table-hover nowrap" style="width:100%;margin: 0;">
      <thead>
      <tr>
         <th>Action</th>
         <th>Request Category</th>
         <th>Requester</th>
         <th>Dealer</th>
         <th>Ticket Number</th>
         <th>Ticket Date</th>
<%--         <th>Nominal</th>--%>
<%--         <th>Tahun</th>--%>
         <th>Status</th>
      </tr>
      </thead>
      <tbody></tbody>
   </table>
</div>

<div id="upload_bonus_cards"></div>
<div id="pagination" class="tui-pagination"></div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
   <div class="modal-dialog modal-lg" role="document" style="left: 0;right: 0;">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="hideForm();"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel"></h4>
         </div>
         <div class="modal-body">
            <form data-toggle="validator" role="form">

               <div class="row">
                  <h5 class="title-format">General Information</h5>
               </div>

               <div class="row">

                  <input type="hidden" class="form-control" id="entryId" name="entryId" />

                  <div class="col-xs-6 col-md-3">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="ticketDate" class="title-form">Ticket Date</label>
                        <input type="text" class="form-control" name="ticketDate" id="ticketDate" readonly>
                     </div>
                  </div>
                  <div class="col-xs-6 col-md-3">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <input type="text" class="form-control" style="margin-top: 23px;" id="ticketHour" name="ticketHour" placeholder="Auto" readonly />
                     </div>
                  </div>
                  <div class="col-xs-12 col-md-6">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="userName" class="title-form">Requester Name</label>
                        <input type="text" class="form-control" id="userName" name="userName" placeholder="Auto" readonly>
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="ticketNumber" class="title-form">Ticket Number</label>
                        <input type="text" class="form-control" id="ticketNumber" name="ticketNumber" placeholder="Auto Generate" readonly>
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="dealerName" class="title-form">Cabang Dealer</label>
                        <input type="text" class="form-control" id="dealerName" name="dealerName" placeholder="Auto" readonly>
                        <input type="hidden" id="dealerId" name="dealerId" readonly>
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="userId" class="title-form">User ID</label>
                        <input type="text" class="form-control" id="userId" name="userId" placeholder="Auto" readonly>
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="approverName" class="title-form">Approver</label>
                        <input type="text" class="form-control" id="approverName" name="approverName" placeholder="Auto" readonly>
                        <input type="hidden" id="approverId" name="approverId" readonly>
                     </div>
                  </div>
               </div>

               <div class="row">
                  <div class="col-md-12 line-bonus-popup"></div>
               </div>

               <div class="row">
                  <h5 class="title-format">Contact Information</h5>
               </div>

               <div class="row">
                  <div class="col-md-6">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="email" class="control-label title-form">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" readonly />
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="emailCC" class="control-label title-form">Email CC</label>
                        <input type="email" maxlength="35" class="form-control" id="emailCC" name="emailCC" placeholder="" />
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="phone" class="control-label title-form">Phone <span style="color: red;">*</span></label>
                        <input type="tel" class="form-control numberOnly" id="phone" name="phone"/>
                     </div>
                  </div>
               </div>

               <div class="row">
                  <div class="col-md-12 line-bonus-popup"></div>
               </div>

               <div class="row">
                  <h5 class="title-format">Request Information</h5>
               </div>

               <div class="row">
                  <div class="col-md-6">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="requestBonusName" class="control-label title-form">Request Category <span style="color: red;">*</span></label>
                        <input type="text" class="form-control" id="requestBonusName" name="requestBonusName" />
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group nominalPengajuan" style="margin-left: 1px;margin-right: 1px;">
                        <label for="nominalPengajuan" class="control-label title-form">Nominal Pengajuan <span style="color: red;">*</span></label>
                        <input type="tel" class="form-control numberOnly" id="nominalPengajuan" name="nominalPengajuan"/>
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="reqYearId" class="control-label title-form">Tahun <span style="color: red;">*</span></label>
                        <input type="tel" class="form-control numberOnly" id="reqYearId" name="reqYearId"/>
                     </div>
                  </div>
                  <div class="col-md-6"></div>
                  <div class="col-md-12">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="requestDescription" class="control-label title-form">Request Description <span style="color: red;">*</span></label>
                        <textarea class="form-control" rows="3" id="requestDescription" name="requestDescription"></textarea>
                     </div>
                  </div>
                  <div class="col-md-12">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="businessBenefit" class="control-label title-form">Business Benefit <span style="color: red;">*</span></label>
                        <textarea class="form-control" rows="3" id="businessBenefit" name="businessBenefit"></textarea>
                     </div>
                  </div>
                  <div class="col-md-12">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label class="control-label">Attachment <span style="color: red;">*</span></label>
                        <div class="input-group">
                           <a href="bonusFile" id="bonusFile" class="btn btn-ipr btn-info btn_table" target="_blank">
                              <span>Download File</span>
                           </a>
                           <span id="bonusFileName" style="margin-left: 15px;font-weight: bold;"></span>
                        </div>
                     </div>
                  </div>
               </div>

               <div class="row">
                  <div class="col-md-12 line-bonus-popup"></div>
               </div>

               <div class="row">
                  <h5 class="title-format">Notes</h5>
               </div>

               <div class="row">
                  <div class="col-md-12">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="requestDescription" class="control-label title-form">Notes <span style="color: red;">*</span></label>
                        <textarea class="form-control" rows="3" id="notes" name="notes"></textarea>
                     </div>
                  </div>
                  <div class="col-md-12">
                     <div class="form-group" style="margin-left: 1px;margin-right: 1px;">
                        <label for="businessBenefit" class="control-label title-form">Notes History</label>
                        <textarea class="form-control" rows="12" id="notesHistory" name="notesHistory" placeholder="" readonly></textarea>
                     </div>
                  </div>
               </div>

            </form>
         </div>
         <%--<div class="modal-footer"></div>--%>
      </div>
   </div>
</div>

<script>
   var UPLOAD_BONUS_LIST_URL = '${uploadBonusListURL}';
   var UPLOAD_BONUS_LIST_CARD_URL = '${uploadBonusListCardURL}';
   var UPLOAD_BONUS_EDIT_URL = '${uploadBonusEditURL}';
   var UPLOAD_BONUS_SINGLE_URL = '${uploadBonusSingleURL}';
   var UPLOAD_BONUS_UPDATE_STATUS_ACTION_URL = '${uploadBonusUpdateStatusActionURL}';
   var DEALER_UPLOAD_BONUS_URL = '${dealerUploadBonusURL}';
   var REQUEST_UPLOAD_BONUS_URL = '${requestUploadBonusURL}';
   var STATUS_UPLOAD_BONUS_URL = '${statusUploadBonusURL}';
   var TAHUN_UPLOAD_BONUS_URL = '${tahunUploadBonusURL}';
   var ENTRY_ID = "<portlet:namespace/>entryId";
   var DOWNLOAD_URL = '${downloadFileURL}';

   $(document).ready(function () {
      updateBreadcrumb();
   });

   function updateBreadcrumb() {
      var currentUrl = window.location.href;
      var breadcrumb = $(".title-breadcrumb");
      var titleMenu = $(".title-format")

      if (currentUrl.includes("inbox-request")) {
         titleMenu.text("Check Inbox List of Ticket")
         breadcrumb.text("Home > Claim Dealer > Kuitansi Bonus > Inbox Request");
      } else if (currentUrl.includes("monitoring-request")) {
         titleMenu.text("Display Tikets")
         breadcrumb.text("Home > Claim Dealer > Kuitansi Bonus > Display Request");
      }
   }
</script>

<%@ include file="/META-INF/resources/upload/bonus/monitoring/js/monitoring-script.jsp" %>
<%--<script src="<%=request.getContextPath()%>/upload/bonus/monitoring/js/monitoring.js"></script>--%>
