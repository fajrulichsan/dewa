<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/non-cms/general-css.jsp" %>

<portlet:resourceURL id="/diskon-fakpol-non-cms-list" var="diskonFakpolListURL"/>
<portlet:resourceURL id="/download-diskon-fakpol-noncms" var="downloadDiskonFakpolNonCMSActionURL">
   <portlet:param name="entryId" value="0"/>
</portlet:resourceURL>
<%--
<portlet:resourceURL id="/dealer-diskon-fakpol-non-cms" var="dealerDiskonFakpolURL"/>
--%>
<portlet:resourceURL id="/tahun-diskon-fakpol-non-cms" var="tahunDiskonFakpolURL"/>
<portlet:resourceURL id="/bulan-diskon-fakpol-non-cms" var="bulanDiskonFakpolURL"/>
<portlet:resourceURL id="/non-cms/dealers-with-details" var="dealerDiskonFakpolURL"/>

<%--
<link href="<%=request.getContextPath()%>/diskon/css/diskon-menu.css" rel="stylesheet" type="text/css">
--%>
<link href="<%=request.getContextPath()%>/diskon/diskon-fakpol/non-cms/css/diskon-fakpol.css" rel="stylesheet" type="text/css">

<div class="diskon_fakpol non-cms-menu">
   <ol class="breadcrumb">
      <li>Home</li>
      <li>Report</li>
      <li>Unit</li>
      <li>Realisasi Diskon Fakpol</li>
   </ol>

   <div class="main-title">
      <h3>Realisasi Diskon Fakpol</h3>
   </div>

   <div class="row table-filters">
      <div class="col-xs-12 col-md-3 dealerId">
         <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;">
            <option value="ALL">Select All</option>
         </select>
      </div>
      <div class="col-xs-12 col-md-3 dealer-space"></div>
      <div class="col-xs-12 col-md-6 row sub-filters">
         <div class="col-xs-6">
            <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
               <option value="ALL">Select All</option>
            </select>
         </div>
         <div class="col-xs-6">
            <select class="form-control" name="bulanId" id="bulanId" style="width: 100%;">
               <option value="ALL">Select All</option>
            </select>
         </div>
      </div>
   </div>

   <div class="tabcontent">
      <table id="diskon_fakpol_non_cms_table" class="table table-hover nowrap non-cms-table" style="width:100%">
         <thead>
            <tr>
               <th>No</th>
               <th>Realisasi Diskon Fakpol</th>
               <th>Tahun</th>
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
   var DISKON_FAKPOL_LIST_URL = '${diskonFakpolListURL}';
   var DOWNLOAD_DISKON_FAKPOL_URL = '${downloadDiskonFakpolNonCMSActionURL}';
   var DEALER_DISKON_FAKPOL_URL = '${dealerDiskonFakpolURL}';
   var TAHUN_DISKON_FAKPOL_URL = '${tahunDiskonFakpolURL}';
   var BULAN_DISKON_FAKPOL_URL = '${bulanDiskonFakpolURL}';
   var ENTRY_ID = "<portlet:namespace/>entryId";
</script>

<script src="<%=request.getContextPath()%>/diskon/js/diskon-menu.js"></script>
<script src="<%=request.getContextPath()%>/diskon/diskon-fakpol/non-cms/js/diskon-fakpol.js"></script>