<%--
  Created by IntelliJ IDEA.
  User: psmahmad1402
  Date: 20/01/2025
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/META-INF/resources/init.jsp" %>

<div class="non-cms-menu" id="diskonOthersNonCMSMenu">
  <ol class="breadcrumb">
    <li>Home</li>
    <li>Report</li>
    <li>Unit</li>
    <li>${currentMenu}</li>
  </ol>

  <div class="main-title">
    <h3>${currentMenu}</h3>
  </div>

	<aui:form name="searchDiskonOthers">
		<aui:row>
			<c:choose>
				<c:when test="${hasDealerAccess}">
					<aui:select wrapperCssClass="col-xs-12 col-md-3" name="dealerId" label="" />
					<div class="col-md-3"></div>
				</c:when>
				<c:otherwise>
					<div class="col-md-6"></div>
				</c:otherwise>
			</c:choose>
      <aui:select wrapperCssClass="col-xs-12 col-md-3" name="tahun" label="" />
      <aui:select wrapperCssClass="col-xs-12 col-md-3" name="kuartalId" label="" />
		</aui:row>
	</aui:form>

  <div class="tabcontent">
    <table id="<portlet:namespace/>diskonOthersNonCMSTable" class="table table-hover nowrap non-cms-table" style="width:100%">
      <thead>
        <tr>
          <th>No</th>
          <th>${currentMenu}</th>
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

<%@ include file="view_script.jsp" %>