<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/non-cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/faktur-indirect/non-cms/style-non-cms.jsp" %>

<portlet:resourceURL id="/non-cms/faktur-indirect/list" var="fakturIndirectListURL"/>
<portlet:resourceURL id="/non-cms/faktur-indirect/download" var="downloadFakturIndirectURL"/>
<portlet:resourceURL id="/non-cms/dealers-with-details" var="dealerFakturIndirectURL"/>

<%--<link href="<%=request.getContextPath()%>/faktur-indirect/non-cms/css/faktur-indirect.css" rel="stylesheet" type="text/css">--%>

<div class="faktur_indirect non-cms-menu">
   <ol class="breadcrumb">
      <li>Home</li>
      <li>Report</li>
      <li>Unit</li>
      <li>Faktur Indirect</li>
   </ol>

   <div class="main-title">
      <h3>Faktur Indirect</h3>
   </div>

   <div class="row table-filters">
      <%--
      <div class="col-xs-12 col-md-6 white-space"></div>
      --%>
      <div class="col-xs-12 col-md-3 dealerId dealer-filter">
         <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;">
            <option value="ALL">Select All</option>
         </select>
      </div>
      <div class="col-xs-12 col-md-3 period-filter">
         <div class="input-group">
            <input type="text" onkeydown="return false" class="form-control dateIcon required" name="periode-date" placeholder="Pilih Tanggal Faktur" readonly>
            <span class="input-group-btn">
               <button class="btn btn-info btn_table" onclick="refresh()">
                  <span><i class="icon-refresh"></i></span>
               </button>
            </span>
         </div>
      </div>
   </div>
   <div class="tabcontent">
      <table id="faktur_indirect_non_cms_table" class="table table-hover nowrap non-cms-table" style="width:100%">
         <thead>
            <tr>
               <th>No</th>
               <th>Faktur Indirect</th>
               <th>Periode Faktur</th>
               <th>Tanggal Upload</th>
               <th>Keterangan</th>
            </tr>
         </thead>
         <tbody></tbody>
      </table>
   </div>
</div>

<script>
   var FAKTUR_INDIRECT_LIST_URL = '${fakturIndirectListURL}';
   var DOWNLOAD_FAKTUR_INDIRECT_URL = '${downloadFakturIndirectURL}';
   var DEALER_FAKTUR_INDIRECT_URL = '${dealerFakturIndirectURL}';
   var FAKTUR_INDIRECT_ENTRY_ID = "<portlet:namespace/>entryId";
</script>

<script src="<%=request.getContextPath()%>/diskon/js/diskon-menu.js"></script>
<script src="<%=request.getContextPath()%>/faktur-indirect/non-cms/js/faktur-indirect.js"></script>