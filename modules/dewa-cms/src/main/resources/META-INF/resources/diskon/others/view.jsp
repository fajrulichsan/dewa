<%--
  Created by IntelliJ IDEA.
  User: psmahmad1402
  Date: 20/01/2025
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/cms/diskon/others/list" var="diskonOtherListURL" />
<portlet:resourceURL id="/cms/diskon/others/action" var="diskonOthersActionURL" />
<portlet:resourceURL id="/cms/diskon/others/fetch" var="fetchDiskonOthersURL" />
<portlet:resourceURL id="/cms/diskon/others/year" var="diskonOtherYearURL" />
<portlet:resourceURL id="/cms/diskon/others/upload_file" var="uploadDiskonOthersFileURL" />
<portlet:resourceURL id="/cms/resource/dealer" var="dealerURL" />
<portlet:resourceURL id="/common/resource/delete_file" var="deleteDiskonOthersFileURL" />
<portlet:resourceURL id="/common/resource/quarter" var="quarterURL" />
<portlet:resourceURL id="/common/resource/year" var="yearURL" />

<div class="cms-menu" id="diskonOthersCMSMenu">
  <div class="tabcontent">
    <ul class="nav nav-tabs" id="cmsTab" role="tablist">
      <li class="nav-item">
        <a class="nav-link" id="create_d_others_navtab" data-toggle="tab" href="#create_d_others_tab" role="tab" aria-controls="create_d_others_tab" aria-selected="false">
          <span id="actionStatus"></span>
          Realisasi Diskon Others
        </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" id="kelola_d_others_navtab" data-toggle="tab" href="#kelola_d_others_tab" role="tab" aria-controls="kelola_d_others_tab" aria-selected="false">
          Kelola Realisasi Diskon Others
        </a>
      </li>
    </ul>
    <div class="tab-content" id="myTabContent">
      <%@ include file="form.jsp" %>
      <div class="tab-pane fade in active" id="kelola_d_others_tab" role="tabpanel" aria-labelledby="profile-tab">
        <aui:form name="searchDiskonOthers">
          <aui:row cssClass="table-filters">
			      <aui:select wrapperCssClass="col-xs-12 col-md-3" name="dealerId" label="" />
			      <div class="col-md-3"></div>
			      <aui:select wrapperCssClass="col-xs-12 col-md-3" name="tahun" label="" />
			      <aui:select wrapperCssClass="col-xs-12 col-md-3" name="kuartalId" label="" />
          </aui:row>
        </aui:form>
        <table id="<portlet:namespace/>diskonOthersCMSTable" class="table table-hover nowrap cms-table" style="width:100%">
          <thead>
            <tr>
              <th>No</th>
              <th>Realisasi Diskon Others</th>
              <th>Tahun</th>
              <th>Kuartal</th>
              <th>Tanggal Upload</th>
              <th>Aksi</th>
            </tr>
          </thead>
          <tbody></tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<%@ include file="script.jsp" %>