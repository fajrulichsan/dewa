<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/non-cms/general-css.jsp" %>

<portlet:resourceURL id="/e-srut-non-cms-list" var="eSrutListURL"/>
<portlet:resourceURL id="/download-e-srut-noncms" var="downloadESrutActionURL">
   <portlet:param name="entryId" value="0"/>
</portlet:resourceURL>
<portlet:resourceURL id="/non-cms/dealers-with-details" var="dealerESrutURL"/>

<link href="<%=request.getContextPath()%>/e-srut/non-cms/css/e-srut.css" rel="stylesheet" type="text/css">

<div class="e_srut non-cms-menu">
   <ol class="breadcrumb">
      <li>Home</li>
      <li>Download</li>
      <li>Unit</li>
      <li>E-SRUT</li>
   </ol>

   <div class="main-title">
      <h3>E-SRUT</h3>
   </div>

   <div class="row table-filters">
      <div class="col-xs-12 col-md-3 dealerId">
         <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;">
            <option value="ALL">Select All</option>
         </select>
      </div>
      <div class="col-xs-12 col-md-3 period-filter">
         <div class="input-group">
            <input type="text" onkeydown="return false" class="form-control dateIcon required" name="periodDate" id="periodDate" placeholder="Pilih Tanggal Faktur" readonly>
            <span class="input-group-btn">
               <button class="btn btn-info btn_table" onclick="refresh()">
                  <span><i class="icon-refresh"></i></span>
               </button>
            </span>
         </div>
      </div>

      <%--
      <div class="col-xs-12 col-md-8"></div>
      <div class="col-xs-12 col-md-4 e-srut-filter">
         <div class="col-xs-8 sub-filter dealer-space"></div>
         <div class="col-xs-8 sub-filter dealerId">
            <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;">
               <option value="ALL">Select All</option>
            </select>
         </div>
         <div class="col-xs-8 sub-filter period-field">
            <input type="text" class="form-control" name="periodDate" id="periodDate" placeholder="Pilih Tanggal Faktur" readonly/>
         </div>
         <div class="col-xs-2 sub-filter period-filter">
            <button class="form-control glyphicon glyphicon-calendar period-trigger"></button>
         </div>
         <div class="col-xs-2 sub-filter refresh-filter">
            <button class="form-control glyphicon glyphicon-refresh refresh-trigger"></button>
         </div>
      </div>
      --%>
   </div>
   <div class="tabcontent">
      <table id="e_srut_non_cms_table" class="table table-hover nowrap non-cms-table" style="width:100%">
         <thead>
            <tr>
               <th>No</th>
               <th>E-SRUT</th>
               <th>Periode</th>
               <th>Tanggal Upload</th>
               <th>Keterangan</th>
            </tr>
         </thead>
         <tbody></tbody>
      </table>

   </div>
</div>

<script>
   var E_SRUT_LIST_URL = '${eSrutListURL}';
   var DOWNLOAD_E_SRUT_URL = '${downloadESrutActionURL}';
   var DEALER_E_SRUT_URL = '${dealerESrutURL}';
   var ENTRY_ID = "<portlet:namespace/>entryId";
</script>

<script src="<%=request.getContextPath()%>/diskon/js/diskon-menu.js"></script>
<script src="<%=request.getContextPath()%>/e-srut/non-cms/js/e-srut.js"></script>