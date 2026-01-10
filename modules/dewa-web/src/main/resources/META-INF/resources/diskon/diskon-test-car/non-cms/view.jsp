<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/non-cms/general-css.jsp" %>

<portlet:resourceURL id="/diskon-test-car-non-cms-list" var="diskonTestCarNonCMSListURL"/>
<portlet:resourceURL id="/download-diskon-test-car-noncms" var="downloadDiskonTestCarNonCMSActionURL">
   <portlet:param name="entryId" value="0"/>
</portlet:resourceURL>
<%--
<portlet:resourceURL id="/dealer-diskon-test-car-non-cms" var="dealerDiskonTestCarNonCMSURL"/>
--%>
<portlet:resourceURL id="/tahun-diskon-test-car-non-cms" var="tahunDiskonTestCarNonCMSURL"/>
<portlet:resourceURL id="/tipe-kendaraan-diskon-test-car-non-cms" var="tipeKendaraanDiskonTestCarNonCMSURL"/>
<portlet:resourceURL id="/kuartal-diskon-test-car-non-cms" var="kuartalDiskonTestCarNonCMSURL"/>
<portlet:resourceURL id="/non-cms/dealers-with-details" var="dealerDiskonTestCarNonCMSURL"/>

<%--
<link href="<%=request.getContextPath()%>/diskon/css/diskon-menu.css" rel="stylesheet" type="text/css">
--%>
<link href="<%=request.getContextPath()%>/diskon/diskon-test-car/non-cms/css/diskon-test-car.css" rel="stylesheet" type="text/css">

<div class="diskon_test_car non-cms-menu">
   <ol class="breadcrumb">
      <li>Home</li>
      <li>Report</li>
      <li>Unit</li>
      <li>Realisasi Diskon Test Car</li>
   </ol>

   <div class="main-title">
      <h3>Realisasi Diskon Test Car</h3>
   </div>

   <div class="row table-filters">
      <div class="col-xs-12 col-md-3 dealerId">
         <select class="form-control" name="dealerId" id="dealerId" style="width: 100%;">
            <option value="ALL">Select All</option>
         </select>
      </div>
      <div class="col-xs-12 col-md-6 row sub-filters">
         <div class="col-xs-6">
            <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
               <option value="ALL">Select All</option>
            </select>
         </div>
         <div class="col-xs-6 kuartal-dropdown">
            <select class="form-control" name="kuartalId" id="kuartalId" style="width: 100%;">
               <option value="ALL">Select All</option>
            </select>
         </div>
      </div>
      <div class="col-xs-12 col-md-3 dealer-space"></div>
      <div class="col-xs-12 col-md-3">
         <select class="form-control" name="tipeKendaraanId" id="tipeKendaraanId" style="width: 100%;">
            <option value="ALL">Select All</option>
         </select>
      </div>
   </div>

   <div class="tabcontent">
      <table id="diskon_test_car_non_cms_table" class="table table-hover nowrap non-cms-table" style="width:100%">
         <thead>
            <tr>
               <th>No</th>
               <th>Realisasi Diskon Test Car</th>
               <th>Tahun</th>
               <th>Kuartal</th>
               <th>Tipe Kendaraan</th>
               <th>Tanggal Upload</th>
               <th>Keterangan</th>
            </tr>
         </thead>
         <tbody></tbody>
      </table>
   </div>
</div>

<script>
   var DISKON_TEST_CAR_LIST_URL = '${diskonTestCarNonCMSListURL}';
   var DOWNLOAD_DISKON_TEST_CAR_URL = '${downloadDiskonTestCarNonCMSActionURL}';
   var DEALER_DISKON_TEST_CAR_URL = '${dealerDiskonTestCarNonCMSURL}';
   var TAHUN_DISKON_TEST_CAR_URL = '${tahunDiskonTestCarNonCMSURL}';
   var KUARTAL_DISKON_TEST_CAR_URL = '${kuartalDiskonTestCarNonCMSURL}';
   var TIPE_KENDARAAN_DISKON_TEST_CAR_URL = '${tipeKendaraanDiskonTestCarNonCMSURL}';
   var ENTRY_ID = "<portlet:namespace/>entryId";
</script>

<script src="<%=request.getContextPath()%>/diskon/js/diskon-menu.js"></script>
<script src="<%=request.getContextPath()%>/diskon/diskon-test-car/non-cms/js/diskon-test-car.js"></script>