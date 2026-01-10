<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<%--
<%@ include file="/META-INF/resources/general/non-cms/general-css.jsp" %>
--%>

<portlet:resourceURL id="/diskon-fleet-non-cms-list" var="diskonFleetNonCMSListURL"/>
<portlet:resourceURL id="/download-diskon-fleet-noncms" var="downloadDiskonFleetNonCMSActionURL">
   <portlet:param name="entryId" value="0"/>
</portlet:resourceURL>

<%--
<portlet:resourceURL id="/dealer-diskon-fleet-non-cms" var="dealerDiskonFleetNonCMSURL"/>
--%>
<portlet:resourceURL id="/tahun-diskon-fleet-non-cms" var="tahunDiskonFleetNonCMSURL"/>
<portlet:resourceURL id="/kuartal-diskon-fleet-non-cms" var="kuartalDiskonFleetNonCMSURL"/>
<portlet:resourceURL id="/non-cms/dealers-with-details" var="dealerDiskonFleetNonCMSURL"/>

<%--
<link href="<%=request.getContextPath()%>/diskon/css/diskon-menu.css" rel="stylesheet" type="text/css">
--%>
<link href="<%=request.getContextPath()%>/diskon/diskon-fleet/non-cms/css/diskon-fleet.css" rel="stylesheet" type="text/css">

<div class="diskon_fleet non-cms-menu">
   <ol class="breadcrumb">
      <li>Home</li>
      <li>Report</li>
      <li>Unit</li>
      <li>Realisasi Diskon Fleet</li>
   </ol>

   <div class="main-title">
      <h3>Realisasi Diskon Fleet</h3>
   </div>

   <div class="row table-filters">
      <div class="col-xs-12 col-md-3 dealerId dealer-filter">
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
            <select class="form-control" name="kuartalId" id="kuartalId" style="width: 100%;">
               <option value="ALL">Select All</option>
            </select>
         </div>
      </div>
   </div>
   <div class="tabcontent">
      <table id="diskon_fleet_non_cms_table" class="table table-hover nowrap non-cms-table" style="width:100%">
         <thead>
            <tr>
               <th>No</th>
               <th>Realisasi Diskon Fleet</th>
               <th>Tahun</th>
               <th>Kuartal</th>
               <th>Tanggal Upload</th>
               <th>Keterangan</th>
            </tr>
         </thead>
         <tbody></tbody>
      </table>
   </div>
</div>

<script>
   var DISKON_FLEET_LIST_URL = '${diskonFleetNonCMSListURL}';
   var DOWNLOAD_DISKON_FLEET_URL = '${downloadDiskonFleetNonCMSActionURL}';
   var DEALER_DISKON_FLEET_URL = '${dealerDiskonFleetNonCMSURL}';
   var TAHUN_DISKON_FLEET_URL = '${tahunDiskonFleetNonCMSURL}';
   var KUARTAL_DISKON_FLEET_URL = '${kuartalDiskonFleetNonCMSURL}';
   var ENTRY_ID = "<portlet:namespace/>entryId";
</script>

<script src="<%=request.getContextPath()%>/diskon/js/diskon-menu.js"></script>
<script src="<%=request.getContextPath()%>/diskon/diskon-fleet/non-cms/js/diskon-fleet.js"></script>