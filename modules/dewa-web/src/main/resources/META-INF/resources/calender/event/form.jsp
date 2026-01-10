<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<div class="cms-content">
   <div class="tabcontent" id="cms-tabcontent">
      <ul class="nav nav-tabs" id="cmsTab" role="tablist">
         <li class="nav-item">
            <a class="nav-link" id="edit_acara_navtab" data-toggle="tab" href="#edit_acara_tab" role="tab" aria-controls="edit-acara" aria-selected="true">Edit Acara</a>
         </li>
      </ul>
      <div class="tab-content" id="myTabContent">
         <div class="tab-pane fade" id="edit_acara_tab" role="tabpanel" aria-labelledby="profile-tab">
            <%@ include file = "edit.jsp" %>
         </div>
      </div>
   </div>
</div>

<script>
   $(document).ready(function () {
      $('#edit_acara_navtab')[0].click()
   });
</script>