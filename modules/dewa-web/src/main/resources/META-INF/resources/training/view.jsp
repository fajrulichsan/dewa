<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<%--View Materi--%>
<portlet:resourceURL id="/training-materi-list" var="trainingMateriListURL"/>
<portlet:resourceURL id="/training-materi-action" var="trainingMateriActionURL"/>
<portlet:renderURL var="trainingMateriEditURL">
   <portlet:param name="mvcRenderCommandName" value="/training-materi-edit"/>
</portlet:renderURL>
<%--end - View Materi--%>

<%--Form Materi--%>
<portlet:resourceURL id="/upload-file-training-materi" var="uploadFileTrainingMateriCommandURL"/>
<portlet:resourceURL id="/delete-file-training-materi" var="deleteFileTrainingMateriCommandURL"/>

<portlet:resourceURL id="/jenis-materi-list-training" var="jenisMateriListTrainingURL"/>
<portlet:resourceURL id="/jenis-materi-single-training" var="jenisMateriSingleTrainingURL"/>
<portlet:resourceURL id="/jenis-materi-training" var="jenisMateriTrainingURL"/>
<portlet:resourceURL id="/jenis-materi-action" var="jenisMateriActionURL"/>

<portlet:resourceURL id="/topik-materi-list-training" var="topikMateriListTrainingURL"/>
<portlet:resourceURL id="/topik-materi-single-training" var="topikMateriSingleTrainingURL"/>
<portlet:resourceURL id="/topik-materi-training" var="topikMateriTrainingURL"/>
<portlet:resourceURL id="/topik-materi-action" var="topikMateriActionURL"/>
<%--end - Form Materi--%>

<%--View Agenda--%>
<portlet:resourceURL id="/training-agenda-list" var="trainingAgendaListURL"/>
<portlet:resourceURL id="/training-agenda-action" var="trainingAgendaActionURL"/>
<portlet:renderURL var="trainingAgendaEditURL">
   <portlet:param name="mvcRenderCommandName" value="/training-agenda-edit"/>
</portlet:renderURL>
<portlet:renderURL var="trainingAgendaParticipantManualViewURL">
   <portlet:param name="mvcRenderCommandName" value="/training-agenda-participant-manual-view"/>
</portlet:renderURL>

<portlet:resourceURL id="/jenis-agenda-training-agenda" var="jenisAgendaCalenderEventURL"/>
<portlet:resourceURL id="/status-agenda-training-agenda" var="statusAgendaCalenderEventURL"/>
<%--end - View Agenda--%>
<%--Form Agenda--%>
<portlet:resourceURL id="/upload-file-training-agenda" var="uploadFileTrainingAgendaCommandURL"/>
<portlet:resourceURL id="/delete-file-training-agenda" var="deleteFileTrainingAgendaCommandURL"/>

<portlet:resourceURL id="/jenis-agenda-training-agenda" var="jenisAgendaTrainingAgendaURL"/>
<portlet:resourceURL id="/status-agenda-training-agenda" var="statusAgendaTrainingAgendaURL"/>
<%--end - Form Agenda--%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/library/quill/katex.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/library/quill/monokai-sublime.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/library/quill/quill.snow.css"/>

<style>
    .training_materi > .tabcontent {
        box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
        border-radius: 10px;
        padding: 0.75em;
    }

    #jenis_materi_table > thead {
        background-color: #014689;
        border: none !important;
        color: white;
        border-radius: 10px 10px 0 0;
    }

    #topik_materi_table > thead {
        background-color: #014689;
        border: none !important;
        color: white;
        border-radius: 10px 10px 0 0;
    }

    #training_materi_table > thead {
        background-color: #014689;
        border: none !important;
        color: white;
        border-radius: 10px 10px 0 0;
    }

    #training_agenda_table > thead {
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

    /*Jenis Materi*/
    table#jenis_materi_table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#jenis_materi_table {
        border-radius: 10px 10px 10px 10px;
    }

    #jenis_materi_table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #jenis_materi_table > thead > tr > th {
        font-weight: normal;
        text-align: center;
    }

    /*Topik Materi*/
    table#topik_materi_table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#topik_materi_table {
        border-radius: 10px 10px 10px 10px;
    }

    #topik_materi_table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #topik_materi_table > thead > tr > th {
        font-weight: normal;
        text-align: center;
    }

    /*Materi*/
    table#training_materi_table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#training_materi_table {
        border-radius: 10px 10px 10px 10px;
    }

    #training_materi_table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #training_materi_table > thead > tr > th {
        font-weight: normal;
        text-align: center;
    }

    /*Agenda*/
    table#training_agenda_table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#training_agenda_table {
        border-radius: 10px 10px 10px 10px;
    }

    #training_agenda_table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #training_agenda_table > thead > tr > th {
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
</style>
<style>
    .datepicker table tr td.today,
    .datepicker table tr td.today.focused,
    .datepicker table tr td.today:hover,
    .datepicker table tr td.today:focus,
    .datepicker table tr td.today:active,
    .datepicker table tr td.today:active:focus,
    .datepicker table tr td.today:active:hover {
        background-color: #DBEDFF;
    }

    .bootstrap-select .dropdown-menu.inner {
        max-height: 100%;
    }

    .form-control {
        border: 1px solid #DBEDFF;
        transition: none;
    }

    input.form-control, select.form-control {
        height: 40px !important;
    }

    .form-control:focus {
        border: 1px solid #8ec6ff;
        box-shadow: 0 0 5px rgba(142, 198, 255, 0.2);
    }

    .form-control.date {
        background-image: url(<%=request.getContextPath()%>/assets/img/calendar.svg);
        background-repeat: no-repeat;
        background-size: 20px;
        background-position: calc(100% - 10px) center;
    }

    .file-container-button {
        display: flex;
        flex-wrap: wrap;
        gap: 8px;
    }

    .file-container-button > div {
        flex: 1;
    }

    .btn-add-file-container {
        background-color: #014689;
        color: #FFFFFF;
        height: 40px;
        width: 40px;
        line-height: 40px;
        border: none;
        outline: none;
        border-radius: 5px;
        text-align: center;
    }

    .file-input-container {
        position: relative;
        height: 40px;
        display: flex;
    }

    .file-input-label {
        background-image: url(<%=request.getContextPath()%>/assets/img/upload.svg);
        background-color: #014689;
        background-repeat: no-repeat;
        background-position: 12px 8px;
        background-size: 20px;
        color: #FFFFFF;
        display: inline-block;
        height: 40px;
        line-height: 40px;
        padding: 0 20px 0 40px;
        border-radius: 5px 0 0 5px;
        white-space: nowrap;
        cursor: pointer;
    }

    .file-input-container input[type=file] {
        position: absolute;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
        opacity: 0;
        cursor: pointer;
        z-index: 1;
    }

    .file-input-container .file-text {
        display: inline-block;
        width: 100%;
        height: 40px;
        outline: none;
        border: 1px solid #DBEDFF;
        border-radius: 0 5px 5px 0;
        padding-left: 10px;
        padding-right: 10px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }

    button.action-button {
        padding: 8px 16px;
        border: none;
        outline: none;
        border-radius: 5px;
        background-color: #014689;
        color: #fff;
    }

    button.action-button:hover,
    .btn-add-file-container:hover {
        background-color: #092f52;
    }

    span.action-title {
        color: #014689;
    }

    button.action-button.outline {
        background-color: transparent;
        color: #014689;
        border: 1px solid #014689;
    }

    button.action-button.outline:hover {
        background-color: #014689;
        color: #FFFFFF;
    }

    .myBreadcrumb > div > p {
        margin-left: 8rem;
        line-height: 42px;
    }

    .datepicker-days thead th {
        font-weight: 400;
    }

    .datepicker-days thead tr:last-child {
        font-size: 11px;
        font-weight: 300;
    }

    .datepicker table tr .day.highlighted,
    .datepicker table tr .day.highlighted:focus,
    .datepicker table tr .day.highlighted:active,
    .datepicker table tr .day.highlighted:active:focus {
        background-color: transparent;
        color: #EE1C25;
    }

    .datepicker table tr .day.highlighted.disabled {
        background-color: transparent !important;
    }

    .datepicker table tr .day.highlighted {
        color: #EE1C25;
        border-radius: 4px;
    }

    .datepicker table tr td.new,
    .datepicker table tr td.old {
        color: #dbdbdb;
    }

    .datepicker table tr td.disabled {
        color: #dbdbdb !important;
    }

    .datepicker table tr .day.highlighted.disabled,
    .datepicker table tr .day.old.highlighted,
    .datepicker table tr .day.new.highlighted {
        color: #ffd4d6 !important;
    }

    .datepicker table tr .day.highlighted:hover,
    .datepicker table tr .day.highlighted.focused,
    .datepicker table tr .day.highlighted:active:hover {
        background-color: #eee;
        color: #EE1C25;
    }

    @media only screen and (max-width: 1199px) {
        .myBreadcrumb {
            margin-right: -25px;
        }
    }

    @media only screen and (max-width: 991px) {
        .col-md-3 input {
            margin-top: 10px;
        }
    }

    @media only screen and (max-width: 767px) {
        .myBreadcrumb {
            margin-right: -15px;
        }
    }

    /*Select2*/
    .select2-container--default .select2-selection--single {
        padding: 6px;
        /*margin-top: 16px;*/
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

    #editor-container {
        height: 375px;
    }
</style>

<div class="cms-content">
   <%--<h3 style="font-size: 24px; font-weight: 700; margin-bottom: 1em;">Buat Acara</h3>--%>
   <div class="tabcontent" id="cms-tabcontent" style="padding-bottom: 15px;">
      <ul class="nav nav-tabs" id="cmsTab" role="tablist">
         <li class="nav-item" style="width: 230px;">
            <a class="nav-link" id="jenis_materi_navtab" data-toggle="tab" href="#jenis_materi_tab" role="tab"
               aria-controls="jenis-materi" aria-selected="false">Jenis Materi Pelatihan</a>
         </li>
         <li class="nav-item" style="width: 230px;">
            <a class="nav-link" id="topik_materi_navtab" data-toggle="tab" href="#topik_materi_tab" role="tab"
               aria-controls="topik-materi" aria-selected="false">Topik Materi Pelatihan</a>
         </li>
         <li class="nav-item" style="width: 230px;">
            <a class="nav-link" id="buat_materi_navtab" data-toggle="tab" href="#buat_materi_tab" role="tab"
               aria-controls="buat-materi" aria-selected="false">Buat Materi Pelatihan</a>
         </li>
         <li class="nav-item" style="width: 230px;">
            <a class="nav-link" id="buat_agenda_navtab" data-toggle="tab" href="#buat_agenda_tab" role="tab"
               aria-controls="buat-agenda" aria-selected="false">Buat Agenda Pelatihan</a>
         </li>
         <li class="nav-item" style="width: 230px;">
            <a class="nav-link" id="kelola_materi_navtab" data-toggle="tab" href="#kelola_materi_tab" role="tab"
               aria-controls="kelola-materi" aria-selected="true">Kelola Materi Pelatihan</a>
         </li>
         <li class="nav-item" style="width: 230px;">
            <a class="nav-link" id="kelola_agenda_navtab" data-toggle="tab" href="#kelola_agenda_tab" role="tab"
               aria-controls="kelola-agenda" aria-selected="true">Kelola Agenda Pelatihan</a>
         </li>
      </ul>
      <div class="tab-content" id="myTabContent">
         <div class="tab-pane fade" id="jenis_materi_tab" role="tabpanel" aria-labelledby="profile-tab">
            <div class="form-group col-md-12">
               <button type="button" class="btn btn_table" title="Add File" id="openJenisMateri">
                  Buat Jenis Materi Pelatihan
               </button>
            </div>
            <table id="jenis_materi_table" class="table table-hover nowrap" style="width:100%">
               <thead>
               <tr>
                  <th>Image</th>
                  <th>Jenis Materi Pelatihan</th>
                  <th>Aksi</th>
               </tr>
               </thead>
               <tbody></tbody>
            </table>
         </div>
         <div class="tab-pane fade" id="topik_materi_tab" role="tabpanel" aria-labelledby="profile-tab">
            <div class="form-group col-md-12">
               <button type="button" class="btn btn_table" title="Add File" id="openTopikMateri">
                  Buat Topik Materi Pelatihan
               </button>
            </div>
            <table id="topik_materi_table" class="table table-hover nowrap" style="width:100%">
               <thead>
               <tr>
                  <%--<th>No</th>--%>
                  <th>Topik Materi Pelatihan</th>
                  <%--<th>Description</th>--%>
                  <th>Aksi</th>
               </tr>
               </thead>
               <tbody></tbody>
            </table>
         </div>
         <div class="tab-pane fade" id="buat_materi_tab" role="tabpanel" aria-labelledby="profile-tab">
            <%--<%@ include file = "materi-new.jsp" %>--%>
            <form data-toggle="validator" role="form" id="formTrainingMateri" method="post"
                  enctype="multipart/form-data" novalidate autocomplete="off">
               <div class="container" style="margin-left: 0;">
                  <%--<h3 id="form-title" style="margin-bottom: 50px;">Buat Materi Pelatihan</h3>--%>
                  <input type="hidden" name="entryId">
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="jenisMateriId">
                        Jenis Materi Pelatihan
                        <span style="color: red;">*</span>
                     </label>
                     <select class="form-control" name="jenisMateriId" id="jenisMateriId" style="width: 100%;">
                        <%-- <option value="NULL">List Dealer</option>--%>
                     </select>
                  </div>
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="topikMateriId">
                        Topik Materi Pelatihan
                        <span style="color: red;">*</span>
                     </label>
                     <select class="form-control" name="topikMateriId" id="topikMateriId" style="width: 100%;">
                        <%-- <option value="NULL">List Dealer</option>--%>
                     </select>
                  </div>
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="judulMateri">
                        Judul Materi
                        <span style="color: red;">*</span>
                     </label>
                     <input type="text" class="form-control" name="judulMateri" id="judulMateri"
                            pattern="[A-Za-z0-9. ]+" data-minlength="5" maxlength="100"
                            data-error="Text dengan 5-100 karakter & tidak boleh kosong." required/>
                     <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                     <div class="col-md-9">
                        <label class="col-form-label ipr-color">Lampiran <span style="color: red;">*</span></label>
                        <div id="other-file-container">
                           <div class="parent-file-container">
                              <div class="file-container-button" style="margin-bottom: 15px;">
                                 <div>
                                    <div class="file-input-container">
                                       <label class="file-input-label" for="trainingMateriOne">Pilih File</label>
                                       <input onchange="fileChangeOne(this)" type="file" name="trainingMateriOne"
                                              id="trainingMateriOne" accept=".docx, .xlsx, .pdf">
                                       <input type="hidden" name="trainingMateriOneFileId" id="trainingMateriOneFileId">
                                       <input class="file-text" name="trainingMateriOneFileName"
                                              id="trainingMateriOneFileName">
                                       <input type="hidden" name="trainingMateriOneFilePath"
                                              id="trainingMateriOneFilePath">
                                    </div>
                                    <label class="col-form-label ipr-gray">
                                       File dengan format docx, xlsx, dan pdf *maks. 5MB
                                    </label>
                                 </div>
                              </div>
                           </div>
                        </div>
                        <button type="button" class="btn btn-sm btn-info" title="Add File" onclick="addOtherFile();">
                           <%--<i class="fas fa-plus"></i> --%>
                           <span>Tambahkan Lampiran</span>
                        </button>
                     </div>
                  </div>
                  <div class="form-group">
                     <div class="col-sm-9" style="margin-top: 15px;">
                        <label class="col-form-label ipr-color">
                           Images
                           <span style="color: red;">*</span>
                        </label>
                        <div class="input-group">
                           <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                              <span class="icon-browse">
                                 <img src="<%=request.getContextPath()%>/assets/img/file.svg">
                              </span>
                              <span class="text-browse">Pilih File</span>
                              <input type="file" accept="image/gif, image/jpeg, image/png" placeholder=""
                                     name="trainingMateriImage" id="trainingMateriImage" data-filename=""
                                     data-location="" style="display: none;" aria-invalid="false"
                                     class="materail_input valid">
                           </label>
                           <input type="hidden" name="trainingMateriImageFileId">
                           <input type="text" class="form-control required" data-type="file" data-name=""
                                  name="trainingMateriImageFileName" readonly
                                  style="border: 1px solid #DBEDFF; background-color: white">
                           <input type="hidden" name="trainingMateriImageFilePath">
                        </div>
                        <label class="col-form-label ipr-gray">
                           File dengan format jpg dan jpeg *maks. 5MB
                        </label>
                     </div>
                  </div>
                  <div class="col-md-9" style="border-top: 1px solid #D9D9D9 ; margin: 10px 15px"></div>
                  <div class="col-md-9">
                     <button type="button" class="btn btn-ipr-cancel" id="resetButton">Reset</button>
                     <button class="btn-ipr" type="submit">Simpan</button>
                  </div>
               </div>
            </form>
         </div>
         <div class="tab-pane fade" id="buat_agenda_tab" role="tabpanel" aria-labelledby="profile-tab">
            <%--<%@ include file = "agenda-new.jsp" %>--%>
            <form data-toggle="validator" role="form" id="formTrainingAgenda" method="post"
                  enctype="multipart/form-data" novalidate autocomplete="off">
               <div class="container" style="margin-left: 0;">
                  <%--<h3 id="form-title" style="margin-bottom: 50px;">New Agenda</h3>--%>
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="judulAgenda">
                        Judul Agenda Pelatihan
                        <span style="color: red;">*</span>
                     </label>
                     <input type="text" class="form-control" name="judulAgenda" id="judulAgenda"
                            pattern="[A-Za-z0-9. ]+" data-minlength="5" maxlength="255"
                            data-error="Text dengan 5-255 karakter & tidak boleh kosong." required/>
                     <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group col-md-9">
                     <label class="title-form pull-left" for="jenisAgendaId">
                        Jenis Agenda Pelatihan
                        <span style="color: red;">*</span>
                     </label>
                     <select class="form-control" name="jenisAgendaId" id="jenisAgendaId" style="width: 100%;">
                        <%-- <option value="NULL">List Dealer</option>--%>
                     </select>
                  </div>
                  <div class="form-group">
                     <div class="col-sm-9" style="margin-bottom: 10px;">
                        <label class="col-form-label ipr-color">
                           Image (760x450)
                           <span style="color: red;">*</span>
                        </label>
                        <div class="input-group">
                           <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                              <span class="icon-browse">
                                 <img src="<%=request.getContextPath()%>/assets/img/file.svg" alt="">
                              </span>
                              <span class="text-browse">Pilih File</span>
                              <input type="file" accept="image/gif, image/jpeg, image/png" placeholder=""
                                     name="trainingAgendaImage" id="trainingAgendaImage" data-filename=""
                                     data-location="" style="display: none;" aria-invalid="false"
                                     class="materail_input valid">
                           </label>
                           <input type="text" class="form-control required" data-type="file" data-name=""
                                  name="trainingAgendaImageFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
                           <input type="hidden" class="dpn" name="trainingAgendaImageFileId"/>
                           <input type="hidden" class="dpn" name="trainingAgendaImageFilePath"/>
                        </div>
                        <label class="col-form-label ipr-gray">*maks. 5MB</label>
                     </div>
                  </div>
                  <div class="form-group col-md-9 lokasiAgenda">
                     <label class="title-form pull-left" for="lokasiAgenda">
                        Lokasi
                        <span style="color: red;">*</span>
                     </label>
                     <input type="text" class="form-control" name="lokasiAgenda" id="lokasiAgenda"
                            pattern="[A-Za-z0-9. ]+" data-minlength="0" maxlength="255"
                            data-error="Text maksimal 255 karakter."/>
                     <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group col-md-9 linkAgenda">
                     <label class="title-form pull-left" for="linkAgenda">
                        Link Meeting
                        <span style="color: red;">*</span>
                     </label>
                     <input type="text" class="form-control" name="linkAgenda" id="linkAgenda"
                            pattern="[A-Za-z0-9. -:/]+" data-minlength="0" maxlength="255"
                            data-error="Text maksimal 255 karakter."/>
                     <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group col-md-9">
                     <div class="row">
                        <div class="col-lg-4">
                           <div class="form-group">
                              <label class="title-form pull-left" for="startDate">
                                 Tanggal Mulai
                                 <span style="color: red;">*</span>
                              </label>
                              <input type='text' class="form-control dateIcon required" name='startDate' id='startDate' style="margin-top: 15px;font-size: 15px;border: 1px solid gray;"/>
                           </div>
                        </div>
                        <div class="col-lg-4">
                           <div class="form-group">
                              <label class="title-form pull-left" for="endDate">
                                 Tanggal Berakhir
                                 <span style="color: red;">*</span>
                              </label>
                              <input type='text' class="form-control dateIcon required" name='endDate' id='endDate' style="margin-top: 15px;font-size: 15px;border: 1px solid gray;"/>
                           </div>
                        </div>
                        <div class="col-lg-4">
                           <div class="form-group">
                              <label class="title-form pull-left" for="registrationDate">
                                 Batas Pendaftaran
                                 <span style="color: red;">*</span>
                              </label>
                              <input type='text' class="form-control dateIcon required" name='registrationDate'
                                     id='registrationDate' style="margin-top: 15px;font-size: 15px;border: 1px solid gray;"/>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="col-md-9">
                     <p>Deskripsi <span style="color: red;">*</span></p>
                     <div id="standalone-container">
                        <div id="toolbar-container">
                           <span class="ql-formats">
                              <%--<select class="ql-font"></select>--%>
                              <select class="ql-size"></select>
                           </span>
                           <span class="ql-formats">
                              <button class="ql-bold"></button>
                              <button class="ql-italic"></button>
                              <button class="ql-underline"></button>
                              <button class="ql-strike"></button>
                           </span>
                           <span class="ql-formats">
                              <select class="ql-color"></select>
                              <select class="ql-background"></select>
                           </span>
                           <span class="ql-formats">
                              <button class="ql-script" value="sub"></button>
                              <button class="ql-script" value="super"></button>
                           </span>
                           <span class="ql-formats">
                              <button class="ql-header" value="1"></button>
                              <button class="ql-header" value="2"></button>
                              <button class="ql-blockquote"></button>
                              <button class="ql-code-block"></button>
                           </span>
                           <span class="ql-formats">
                              <button class="ql-list" value="ordered"></button>
                              <button class="ql-list" value="bullet"></button>
                              <button class="ql-indent" value="-1"></button>
                              <button class="ql-indent" value="+1"></button>
                           </span>
                           <span class="ql-formats">
                              <button class="ql-direction" value="rtl"></button>
                              <select class="ql-align"></select>
                           </span>
                           <span class="ql-formats">
                              <button class="ql-link"></button>
                              <button class="ql-image"></button>
                              <button class="ql-video"></button>
                              <button class="ql-formula"></button>
                           </span>
                           <span class="ql-formats">
                              <button class="ql-clean"></button>
                           </span>
                        </div>
                        <div id="editor-container"></div>
                     </div>
                  </div>
                  <div class="col-md-9" style="border-top: 1px solid #D9D9D9; margin: 10px 15px"></div>
                  <div class="col-md-9">
                     <button type="button" class="btn btn-ipr-cancel" id="resetBtnAgenda">Reset</button>
                     <button type="submit" class="btn-ipr">Simpan</button>
                  </div>
               </div>
            </form>
         </div>
         <div class="tab-pane fade" id="kelola_materi_tab" role="tabpanel" aria-labelledby="profile-tab">
            <%--<%@ include file = "detail-jenis.jsp" %>--%>
            <div class="row" style="margin-bottom: 16px;">
               <div class="col-xs-12 col-sm-12 col-md-3"></div>
               <div class="col-xs-12 col-sm-12 col-md-3"></div>
               <div class="col-xs-12 col-sm-12 col-md-3">
                  <select class="form-control" name="jenisMateriIdSearch" id="jenisMateriIdSearch" style="width: 100%;">
                     <option value="ALL">Select All</option>
                     <%-- <option value="NULL">List Dealer</option>--%>
                  </select>
               </div>
               <div class="col-xs-12 col-sm-12 col-md-3">
                  <select class="form-control" name="topikMateriIdSearch" id="topikMateriIdSearch" style="width: 100%;">
                     <option value="ALL">Select All</option>
                     <%-- <option value="NULL">List Dealer</option>--%>
                  </select>
               </div>
            </div>
            <table id="training_materi_table" class="table table-hover nowrap" style="width:100%">
               <thead>
                  <tr>
                     <th>No</th>
                     <th>Jenis Materi Pelatihan</th>
                     <th>Topik Materi</th>
                     <th>Judul Materi</th>
                     <th>Aksi</th>
                  </tr>
               </thead>
               <tbody></tbody>
            </table>
         </div>
         <div class="tab-pane fade" id="kelola_agenda_tab" role="tabpanel" aria-labelledby="profile-tab">
            <%--<%@ include file = "agenda-view.jsp" %>--%>
            <div class="row" style="margin-bottom: 16px;">
               <div class="col-xs-12 col-sm-12 col-md-3"></div>
               <div class="col-xs-12 col-sm-12 col-md-3"></div>
               <div class="col-xs-12 col-sm-12 col-md-3"></div>
               <div class="col-xs-12 col-sm-12 col-md-3">
                  <select class="form-control" name="statusAgendaId" id="statusAgendaId" style="width: 100%;">
                     <option value="ALL">Select All</option>
                  </select>
               </div>
            </div>
            <table id="training_agenda_table" class="table table-hover nowrap" style="width:100%">
               <thead>
                  <tr>
                     <th>No</th>
                     <th>Agenda Pelatihan</th>
                     <th>Tanggal Acara</th>
                     <th>Jenis Agenda Pelatihan</th>
                     <th>Status</th>
                     <th>Aksi</th>
                  </tr>
               </thead>
               <tbody></tbody>
            </table>
         </div>
      </div>
   </div>
</div>

<!--Modal Jenis Materi Pelatihan-->
<div class="modal fade" id="materiModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
   <div class="modal-dialog modal-md" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title" id="myModalLabel">Jenis Materi Pelatihan</h4>
         </div>
         <div class="modal-body">
            <form data-toggle="validator" role="form" id="formJenisMateri" method="post" enctype="multipart/form-data"
                  novalidate autocomplete="off">
               <div class="form-group">
                  <label class="title-form pull-left" for="jmName">
                     Jenis Materi
                     <span style="color: red;">*</span>
                  </label>
                  <input type="text" class="form-control" name="jmName" id="jmName" style="border: 1px solid black;"
                         pattern="[A-Za-z0-9. ]+" data-minlength="3" maxlength="100"
                         data-error="Text dengan 3-155 karakter & tidak boleh kosong." required/>
               </div>
               <div class="form-group">
                  <label class="col-form-label ipr-color">
                     Images
                     <span style="color: red;">*</span>
                  </label>
                  <div class="input-group">
                     <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                        <span class="icon-browse">
                           <img src="<%=request.getContextPath()%>/assets/img/file.svg">
                        </span>
                        <span class="text-browse">Pilih File</span>
                        <input type="file" accept="image/gif, image/jpeg, image/png" placeholder=""
                               name="jenisMateriImage" id="jenisMateriImage" data-filename="" data-location=""
                               style="display: none;" aria-invalid="false" class="materail_input valid">
                     </label>
                     <input type="text" class="form-control required" data-type="file" data-name=""
                            name="jenisMateriImageFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
                     <input type="hidden" class="dpn" name="jenisMateriImageFileId">
                     <input type="hidden" class="dpn" name="jenisMateriImageFilePath">
                  </div>
                  <label class="col-form-label ipr-gray">File dengan format jpg dan jpeg *maks. 5MB</label>
               </div>
               <button type="button" class="btn btn-default btn-block" onclick="createJenisMateri();">Simpan</button>
            </form>
         </div>
      </div>
   </div>
</div>

<!--Modal Topik Materi Pelatihan-->
<div class="modal fade" id="topikMateriModal" tabindex="-1" role="dialog" aria-labelledby="topikMateriModalLabel"
     style="display: none;">
   <div class="modal-dialog modal-md" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title" id="topikMateriModalLabel">Topik Materi Pelatihan</h4>
         </div>
         <div class="modal-body">
            <form data-toggle="validator" role="form" id="formTopikMateri" method="post" enctype="multipart/form-data"
                  novalidate autocomplete="off">
               <div class="form-group">
                  <label class="title-form pull-left" for="tmName">
                     Topik Materi
                     <span style="color: red;">*</span>
                  </label>
                  <input type="text" class="form-control" name="tmName" id="tmName" style="border: 1px solid black;"
                         pattern="[A-Za-z0-9. ]+" data-minlength="3" maxlength="100"
                         data-error="Text dengan 3-155 karakter & tidak boleh kosong." required/>
               </div>
               <button type="button" class="btn btn-default btn-block" onclick="createTopikMateri();">Simpan</button>
            </form>
         </div>
      </div>
   </div>
</div>

<script>
   var topikMateriId = "0";
   var jenisMateriIdSearch = 'ALL'
   var topikMateriIdSearch = "ALL";
   var jenisMateries;
   var topikMateries;
   var jenisAgendaData = [{id: 0, text: 'Acara Offline'}, {id: 1, text: 'Acara Online'}];
   var statusAgendaData = [{id: 0, text: 'Belum Terlaksana'}, {id: 1, text: 'Sudah Terlaksana'}];

   function setCurrentPage(valuePage) {
      localStorage.setItem('currentPageTraining', valuePage);
      getCurrentPage();
   }

   function getCurrentPage() {
      // kelolaMateri, kelolaAgenda
      var currentPage = localStorage.getItem('currentPageTraining')
      if (currentPage === null) {
         localStorage.setItem('currentPageTraining', 'kelolaAgenda')
      }
      if (currentPage === 'kelolaMateri') {
         $('#kelola_materi_navtab')[0].click();
      } else {
         $('#kelola_agenda_navtab')[0].click();
      }
   }

   $('#kelola_materi_navtab').click(function () {
      setCurrentPage('kelolaMateri')
   })

   $('#kelola_agenda_navtab').click(function () {
      setCurrentPage('kelolaAgenda')
   })

   var languageOptions = {
      // info: "",
      "lengthMenu": "_MENU_",
      "paginate": {
         "next": '<span class="glyphicon glyphicon-menu-right"></span>',
         "previous": '<span class="glyphicon glyphicon-menu-left"></span>'
      },
      "search": "",
      searchPlaceholder: "Cari Dokumen"
   }

   $(document).ready(function () {
      getCurrentPage();
      call_jenis_materi_table();
      call_topik_materi_table();
      call_training_materi_table();
      getStatusAgendas();
      call_training_agenda_table();
   });

   // Jenis Materi
   function call_jenis_materi_table() {
      $('#jenis_materi_table').DataTable({
         destroy: true,
         processing: true,
         serverSide: true,
         paging: true,
         info: true,
         searching: false,
         searchDelay: 1000,
         autoWidth: true,
         responsive: true,
         order: [],
         language: languageOptions,
         ajax: {
            url: '${jenisMateriListTrainingURL}',
            type: 'GET',
            data: function (d) {
               d.p_auth = Liferay.authToken;
               // d.topikMateriId = topikMateriId;
            },
            dataSrc: function (json) {
               // return json.Data;
               return json.data;
            }
         },
         columns: [
            {
               data: "imagePath",
               width: "7%",
               className: "text-center",
               render: function (data, type, row, meta) {
                  return imageJenisTraining(data);
               },
               orderable: false
            },
            {
               data: "jenisMateriName",
               width: "25%",
               render: $.fn.dataTable.render.ellipsis(25)
            },
            {
               data: "id",
               width: "7%",
               className: "text-center",
               render: function (data, type, row, meta) {
                  return renderActionButtonJenisTraining(data);
               },
               orderable: false
            }
         ]
      });
   }

   function renderActionButtonJenisTraining(dataId) {
      var response = "";
      response = '<span class="action-wrapper" data-id="' + dataId + '">' +
         '<a href="javascript:void(0)" onclick="editJenisMateri(this)" data-id="' + dataId + '" >' +
         '<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg" /></span>' +
         '</a>' +
         '</span>';
      return response;
   }

   function imageJenisTraining(dataId) {
      return '<img src="' + dataId + '" class="img-rounded" style="height:50px;">';
   }

   // Topik Materi
   function call_topik_materi_table() {
      $('#topik_materi_table').DataTable({
         destroy: true,
         processing: true,
         serverSide: true,
         paging: true,
         info: true,
         searching: false,
         searchDelay: 1000,
         autoWidth: true,
         responsive: true,
         order: [],
         language: languageOptions,
         ajax: {
            url: '${topikMateriListTrainingURL}',
            type: 'GET',
            data: function (d) {
               d.p_auth = Liferay.authToken;
               // d.topikMateriId = topikMateriId;
            },
            dataSrc: function (json) {
               // return json.Data;
               return json.data;
            }
         },
         columns: [
            {
               data: "topikMateriName",
               width: "25%",
               render: $.fn.dataTable.render.ellipsis(25)
            },
            {
               data: "id",
               width: "7%",
               className: "text-center",
               render: function (data, type, row, meta) {
                  return renderActionButtonTopikTraining(data);
               },
               orderable: false
            }
         ]
      });
   }

   function renderActionButtonTopikTraining(dataId) {
      var response = "";
      response = '<span class="action-wrapper" data-id="' + dataId + '">' +
         '<a href="javascript:void(0)" onclick="editTopikMateri(this)" data-id="' + dataId + '" >' +
         '<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg" /></span>' +
         '</a>' +
         '</span>';
      return response;
   }

   function call_training_materi_table() {
      var trainingTable = $('#training_materi_table').DataTable({
         destroy: true,
         processing: true,
         serverSide: true,
         paging: true,
         info: true,
         // searching: true,
         searchDelay: 1000,
         // ordering: true,
         // select: true,
         autoWidth: true,
         responsive: true,
         order: [],
         columnDefs: [
            {
               targets: [2],
               orderData: [2, 3, 4]
            }
         ],

         // scrollY: '65vh',
         // fixedHeader: true,
         language: languageOptions,
         ajax: {
            url: '${trainingMateriListURL}',
            type: 'GET',
            data: function (d) {
               d.jenisMateriId = jenisMateriIdSearch;
               d.topikMateriId = topikMateriIdSearch;
               d.p_auth = Liferay.authToken;
            },
         },
         columns: [
            {
               data: "no",
               width: "5%",
               className: "text-center",
               orderable: false
            },
            {
               data: "jenisMateriName",
               width: "25%",
               render: $.fn.dataTable.render.ellipsis(25)
            },
            {
               data: "topikMateriName",
               width: "20",
               render: $.fn.dataTable.render.ellipsis(25)
            },
            {
               data: "judulMateri",
               width: "30%",
               render: $.fn.dataTable.render.ellipsis(25)
            },
            {
               data: "id",
               width: "7%",
               className: "text-center",
               render: function (data, type, row, meta) {
                  return renderActionButton(data);
               },
               orderable: false
            }
         ],
         initComplete: function () {
            $(".dataTables_filter input", $('#training_materi_table_wrapper'))
               .unbind()
               .bind("input", function (e) {
                  if (this.value.length >= 3 || e.keyCode === 13) {
                     trainingTable.search(this.value).draw();
                  }
                  if (this.value === "") {
                     trainingTable.search("").draw();
                  }
               });
         }
      });
   }

   function renderActionButton(dataId) {
      var response = "";
      response = '<span class="action-wrapper" data-id="' + dataId + '">' +
         '<a href="${trainingMateriEditURL}&id=' + dataId + '" >' +
         '<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg"></img></span>' +
         '</a> ' +
         '<a href="javascript:void(0)" onclick="deleteMateri(this)" data-id="' + dataId + '" >' +
         '<span><img src="<%=request.getContextPath()%>/assets/img/trash.svg"></img></span>' +
         '</a>' +
         '</span>';
      return response;
   }

   function deleteMateri(element) {
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
            formData.set("<portlet:namespace/>crudType", "delete");
            formData.set("<portlet:namespace/>entryId", entryId);
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            $.ajax({
               url: "${trainingMateriActionURL}",
               type: "POST",
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var data = JSON.parse(response);
                  var acknowledge = data.acknowledge;
                  var status = data.status;
                  var message = data.message;
                  if (status === 'warning') {
                     Swal.fire("Failed to Delete", message, "warning");
                  } else {
                     Swal.fire("Successfully delete data", "", "success")
                        .then((res) => {
                           // trainingMateriDataTable.ajax.reload();
                           call_training_materi_table();
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
<script>
   var action = "create";
   var entryId = "0";
   var maxFile = 4;
   var submitProcess = false;
   var listDeleted = [];

   $(document).ready(function () {
      resetForm()
   });

   $("#resetButton").click(function () {
      resetForm()
   });

   function resetForm() {
      formMateri();
      getJenisMateris();
      getJenisMateriSearch();
      getTopikMateris();
      getTopikMateriSearch();
   }

   function formMateri() {
      $('[name="jenisMateriId"]')[0].value = "";
      $('[name="judulMateri"]')[0].value = "";
      $('[name="topikMateriId"]')[0].value = "";
      $('[name="trainingMateriOneFileId"]')[0].value = "";
      $('[name="trainingMateriOneFileName"]')[0].value = "";
      $('[name="trainingMateriOneFilePath"]')[0].value = "";
      $('[name="trainingMateriImageFileId"]')[0].value = "";
      $('[name="trainingMateriImageFileName"]')[0].value = "";
      $('[name="trainingMateriImageFilePath"]')[0].value = "";
      $(".upload-content").remove();
   }

   $("#formTrainingMateri").submit(function (e) {
      e.preventDefault();
      const jenisMateriId = $('[name="jenisMateriId"]').val();
      const topikMateriId = $('[name="topikMateriId"]').val();
      const judulMateri = $('[name="judulMateri"]').val();
      const trainingMateriOneFileId = $('[name="trainingMateriOneFileId"]').val();
      const trainingMateriImageFileId = $('[name="trainingMateriImageFileId"]').val();
      if (submitProcess === false) {
         if (jenisMateriId === null || jenisMateriId === undefined || jenisMateriId.length < 1) {
            Swal.fire("Jenis Materi belum diisi", "", "warning");
            return false;
         } else if (topikMateriId === null || topikMateriId === undefined || topikMateriId.length < 1) {
            Swal.fire("Topik Materi belum diisi", "", "warning");
            return false;
         } else if (judulMateri === null || judulMateri === undefined || judulMateri.length < 5) {
            Swal.fire("Judul materi belum diisi", "", "warning");
            return false;
         } else if (!regexBasicCharacter.test(judulMateri)) {
            Swal.fire("Mohon tidak menggunakan karakter selain .,/()@&_- pada judul", "", "warning");
            return false;
         } else if (trainingMateriOneFileId === null || trainingMateriOneFileId === undefined || trainingMateriOneFileId.length < 1) {
            Swal.fire("Lampiran belum diisi", "", "warning");
            return false;
         } else if (trainingMateriImageFileId === null || trainingMateriImageFileId === undefined || trainingMateriImageFileId.length < 1) {
               Swal.fire("Images belum diisi.", "", "warning");
               return false;
         } else {
            submitProcess = true
            var formData = new FormData(this);
            formData.set("<portlet:namespace/>crudType", action);
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
            listDeleted.forEach(item => {
               formData.append("<portlet:namespace/>listDeleted", item);
            });

            createLoading();
            $.ajax({
               url: '${trainingMateriActionURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  if (JSON.parse(response).status === 'success') {
                     swal.fire("Success", "Your request has been saved", "success")
                        .then(function () {
                           // window.location = homeUrl;
                           resetForm();
                           call_training_materi_table();
                           $('#kelola_materi_navtab')[0].click();
                        });
                     for (var i = 0; i < 5; i++) {
                        $('#fileOther').remove()
                     }
                  } else if (JSON.parse(response).status === 'warning') {
                     swal.fire('Sorry', JSON.parse(response).message, 'warning');
                  } else {
                     swal.fire('Sorry', 'There is an internal error', 'error');
                  }
               },
               error: function (data, textStatus, XMLHttpRequest) {
                  if (data.status === 500) {
                     var msg = data.responseJSON.Message
                     swal('Sorry', msg, 'error');
                  } else if (data.status === 408) {
                     swal('Sorry', 'Request Time Out, Please Try Again', 'error');
                  } else {
                     swal('Sorry', textStatus + 'error submit', 'error');
                  }
               },
               complete: function (jqXHR, textStatus) {
                  submitProcess = false;
                  destroyLoading();
               }
            })
         }
      }
   })

   function getJenisMateris() {
      $.ajax({
         url: "${jenisMateriTrainingURL}",
         type: "POST",
         data: {
            '<portlet:namespace/>p_auth': Liferay.authToken
         },
         delay: 250,
         success: function (response) {
            var jenisMateriData = JSON.parse(response);
            jenisMateries = jenisMateriData.Data
            const jenisMateriConst = $("#jenisMateriId").select2({
               data: jenisMateriData.Data,
               tags: false,
               placeholder: '',
               allowClear: false,
               multiple: false,
               // minimumInputLength: 1,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="jenisMateriId"]')[0].value = data.id;
                  if (data.id === '') {
                     return '';
                  }
                  getJenisMateriSearch()
                  return data.text;
               }
            });
            jenisMateriConst.val(null);
            jenisMateriConst.trigger('change');
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            console.log("complete");
         }
      });
   }

   function getJenisMateriSearch() {
      const jenisMateriSearchConst = $("#jenisMateriIdSearch").select2({
         data: jenisMateries,
         tags: false,
         placeholder: 'Pilih Jenis Materi',
         allowClear: false,
         multiple: false,
         // minimumInputLength: 1,
         maximumInputLength: 100,
         templateSelection: function (data) {
            $('[name="jenisMateriIdSearch"]')[0].value = data.id;
            if (data.id === '' || data.id === 'Pilih Jenis Materi') {
               jenisMateriIdSearch = 'ALL';
               return 'Pilih Jenis Materi';
            } else {
               jenisMateriIdSearch = data.id;
            }
            call_training_materi_table()
            return data.text;
         }
      });
      jenisMateriSearchConst.val(null);
      jenisMateriSearchConst.trigger('change');
   }

   function getTopikMateris() {
      $.ajax({
         url: "${topikMateriTrainingURL}",
         type: "POST",
         data: {"windowsId": "test"},
         delay: 250,
         success: function (response) {
            var topikMateriData = JSON.parse(response);
            topikMateries = topikMateriData.Data
            const topikMateriConst = $("#topikMateriId").select2({
               data: topikMateriData.Data,
               tags: false,
               placeholder: '',
               allowClear: false,
               multiple: false,
               // minimumInputLength: 1,
               maximumInputLength: 100,
               templateSelection: function (data) {
                  $('[name="topikMateriId"]')[0].value = data.id;
                  if (data.id === '') {
                     return '';
                  }
                  getTopikMateriSearch()
                  return data.text;
               }
            });
            topikMateriConst.val(null);
            topikMateriConst.trigger('change');
         },
         error: function (error) {
            console.log(error);
         },
         complete: function () {
            console.log("complete");
         }
      });
   }

   function getTopikMateriSearch() {
      const topikMateriSearchConst = $("#topikMateriIdSearch").select2({
         data: topikMateries,
         tags: false,
         placeholder: 'Pilih Topik Materi',
         allowClear: false,
         multiple: false,
         // minimumInputLength: 1,
         maximumInputLength: 100,
         templateSelection: function (data) {
            $('[name="topikMateriIdSearch"]')[0].value = data.id;
            if (data.id === '' || data.id === 'Pilih Topik Materi') {
               topikMateriIdSearch = 'ALL';
               return 'Pilih Topik Materi';
            } else {
               topikMateriIdSearch = data.id;
            }
            call_training_materi_table()
            return data.text;
         }
      });
      topikMateriSearchConst.val(null);
      topikMateriSearchConst.trigger('change');
   }
</script>
<script>
   var jenisMateriId = '0'
   var jenisMateriOperation = 'create'

   $("#openJenisMateri").click(function () {
      jenisMateriId = '0'
      jenisMateriOperation = 'create'
      $('[name="jmName"]')[0].value = "";
      $('[name="jenisMateriImageFileId"]')[0].value = "";
      $('[name="jenisMateriImageFileName"]')[0].value = "";
      $('[name="jenisMateriImageFilePath"]')[0].value = "";
      $(".upload-content").remove();
      $('#materiModal').modal({
         backdrop: 'static',
         keyboard: false,
         isBodyOverflowing: true
      }).show()
      // getJenisMateris();
   });

   function editJenisMateri(element) {
      jenisMateriId = $(element).data("id");
      jenisMateriOperation = 'update';
      $.ajax({
         url: "${jenisMateriSingleTrainingURL}",
         type: "POST",
         data: {
            "<portlet:namespace/>entryId": jenisMateriId,
            "<portlet:namespace/>p_auth": Liferay.authToken
         },
         success: function (response) {
            var data = JSON.parse(response);
            var jm = data["data"]
            $('[name="jmName"]')[0].value = jm.jenisMateriName;
            $('[name="jenisMateriImageFileId"]')[0].value = jm.imageId;
            $('[name="jenisMateriImageFileName"]')[0].value = jm.imageName;
            $('[name="jenisMateriImageFilePath"]')[0].value = jm.imagePath;
            $('#materiModal').modal({
               backdrop: 'static',
               keyboard: false,
               isBodyOverflowing: true
            }).show()
         },
         error: function (err) {
            console.log(err);
         }
      });
   }

   function hideJenisMateri() {
      $("#materiModal").modal('hide')
   }

   function createJenisMateri() {
      const jenisMateriName = $('[name="jmName"]').val();
      const jenisMateriImageFileId = $('[name="jenisMateriImageFileId"]').val();

      if (jenisMateriName === null || jenisMateriName === undefined || jenisMateriName.length < 3 || jenisMateriName.length > 100) {
         Swal.fire("Jenis Materi minimal 3-100 karakter.", "", "warning");
         return false;
      } else if (!regexBasicCharacter.test(jenisMateriName)) {
         Swal.fire('Mohon tidak menggunakan karakter selain selain .,/()@&_-', '', 'warning');
         return false;
      } else if (jenisMateriImageFileId === null || jenisMateriImageFileId === undefined || jenisMateriImageFileId.length < 1) {
         Swal.fire("Image belum diisi", "", "warning");
         return false;
      } else {
         var formData = new FormData();
         formData.set("<portlet:namespace/>crudType", jenisMateriOperation);
         formData.set("<portlet:namespace/>entryId", jenisMateriId);
         formData.set("<portlet:namespace/>jenisMateriName", jenisMateriName);
         formData.set("<portlet:namespace/>jenisMateriImageFileId", $('[name="jenisMateriImageFileId"]').val());
         formData.set("<portlet:namespace/>jenisMateriImageFileName", $('[name="jenisMateriImageFileName"]').val());
         formData.set("<portlet:namespace/>jenisMateriImageFilePath", $('[name="jenisMateriImageFilePath"]').val());
         formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
         $.ajax({
            url: '${jenisMateriActionURL}',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
               if (JSON.parse(response).status === 'success') {
                  hideJenisMateri();
                  call_jenis_materi_table();
                  getJenisMateris();
               } else if (JSON.parse(response).status === 'warning') {
                  swal.fire('Sorry', JSON.parse(response).message, 'warning');
               } else {
                  swal.fire('Sorry', 'There is an internal error', 'error');
               }
            },
            error: function (data, textStatus, XMLHttpRequest) {
               if (data.status === 500) {
                  var msg = data.responseJSON.Message
                  swal('Sorry', msg, 'error');
               } else if (data.status === 408) {
                  swal('Sorry', 'Request Time Out, Please Try Again', 'error');
               } else {
                  swal('Sorry', textStatus + 'error submit', 'error');
               }
            },
            complete: function (jqXHR, textStatus) {
               submitProcess = false;
               destroyLoading();
            }
         });
      }
   }
</script>
<script>
   var topikMateriId = '0';
   var topikMateriOperation = 'create';

   $("#openTopikMateri").click(function () {
      topikMateriId = '0';
      topikMateriOperation = 'create';
      $('[name="tmName"]')[0].value = "";
      $('#topikMateriModal').modal({
         backdrop: 'static',
         keyboard: false,
         isBodyOverflowing: true
      }).show()
      getTopikMateris();
   });

   function editTopikMateri(element) {
      topikMateriId = $(element).data("id");
      topikMateriOperation = 'update';
      $.ajax({
         url: "${topikMateriSingleTrainingURL}",
         type: "POST",
         data: {
            "<portlet:namespace/>entryId": topikMateriId,
            "<portlet:namespace/>p_auth": Liferay.authToken
         },
         success: function (response) {
            var data = JSON.parse(response);
            var tm = data["data"]
            $('[name="tmName"]')[0].value = tm.topikMateriName;
            $('#topikMateriModal').modal({
               backdrop: 'static',
               keyboard: false,
               isBodyOverflowing: true
            }).show()
         },
         error: function (err) {
            console.log(err);
         }
      });
   }

   function hideTopikMateri() {
      $("#topikMateriModal").modal('hide')
   }

   function createTopikMateri() {
      const topikMateriName = $('[name="tmName"]').val();
      if (topikMateriName === null || topikMateriName === undefined || topikMateriName.length < 3 || topikMateriName.length > 100) {
         Swal.fire("Mohon input topik materi dengan 3 - 100 karakter", "", "warning");
         return false;
      } else if (!regexBasicCharacter.test(topikMateriName)) {
         Swal.fire("Mohon tidak menggunakan karakter selain selain .,/()@&_-", "", "warning");
         return false;
      } else {
         var formData = new FormData();
         formData.set("<portlet:namespace/>crudType", topikMateriOperation)
         formData.set("<portlet:namespace/>topikMateriId", topikMateriId);
         formData.set("<portlet:namespace/>topikMateriName", topikMateriName);
         formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
         $.ajax({
            url: '${topikMateriActionURL}',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
               if (JSON.parse(response).status === 'success') {
                  hideTopikMateri();
                  call_topik_materi_table();
                  getTopikMateris();
               } else if (JSON.parse(response).status === 'warning') {
                  swal.fire('Sorry', JSON.parse(response).message, 'warning');
               } else {
                  swal.fire('Sorry', 'There is an internal error', 'error');
               }
            },
            error: function (data, textStatus, XMLHttpRequest) {
               if (data.status === 500) {
                  var msg = data.responseJSON.Message
                  swal('Sorry', msg, 'error');
               } else if (data.status === 408) {
                  swal('Sorry', 'Request Time Out, Please Try Again', 'error');
               } else {
                  swal('Sorry', textStatus + 'error submit', 'error');
               }
            },
            complete: function (jqXHR, textStatus) {
               submitProcess = false;
               destroyLoading();
            }
         })
      }
   }
</script>
<script>
   var statusAgendaId = "ALL";

   var languageOptionsAgenda = {
      // info: "",
      "lengthMenu": "_MENU_",
      "paginate": {
         "next": '<span class="glyphicon glyphicon-menu-right"></span>',
         "previous": '<span class="glyphicon glyphicon-menu-left"></span>'
      },
      "search": "",
      searchPlaceholder: "Cari Dokumen"
   }

   function call_training_agenda_table() {
      var agendaTable = $('#training_agenda_table').DataTable({
         destroy: true,
         processing: true,
         serverSide: true,
         paging: true,
         info: true,
         searchDelay: 1000,
         autoWidth: true,
         responsive: true,
         order: [2, 3, 4, 5],
         language: languageOptionsAgenda,
         ajax: {
            url: '${trainingAgendaListURL}',
            type: 'GET',
            data: function (d) {
               d.statusAgendaId = statusAgendaId;
               d.p_auth = Liferay.authToken;
            }
         },
         columns: [
            {
               data: "no",
               width: "5%",
               className: "text-center",
               orderable: false
            },
            {
               data: "judul",
               width: "25%",
               render: $.fn.dataTable.render.ellipsis(25)
            },
            {
               data: "startDate",
               width: "20"
            },
            {
               data: "jenisAgenda",
               render: function (data, type, row, meta) {
                  var html = '';
                  if (data === 0) {
                     html = 'Acara Offline';
                  } else if (data === 1) {
                     html = 'Acara Online';
                  }
                  return html;
               },
               width: "10%",
               className: "text-center"
            },
            {
               data: "statusAgenda",
               render: function (data, type, row, meta) {
                  var html = '';
                  if (data === 0) {
                     html = '<a class="btn-status btn-on-danger" href="javascript:void(0)">Belum Terlaksana</a>';
                  } else if (data === 1) {
                     html = '<a class="btn-status btn-on-warning" href="javascript:void(0)">Sudah Terlaksana</a>';
                  }
                  return html;
               },
               width: "10%",
               className: "text-center"
            },
            {
               data: "id",
               render: function (data, type, row, meta) {
                  return renderActionButtonAgenda(data, row);
               },
               width: "7%",
               className: "text-center",
               orderable: false
            }
         ],
         // "sDom": '<"row view-filter"<"col-sm-12"<"pull-left"l><"pull-right"f><"clearfix">>>t<"row view-pager"<"col-sm-12"<"text-center"ip>>>',
         initComplete: function () {
            $(".dataTables_filter input", $('#training_agenda_table_wrapper'))
               .unbind()
               .bind("input", function (e) {
                  if (this.value.length >= 3 || e.keyCode === 13) {
                     agendaTable.search(this.value).draw();
                  }
                  if (this.value === "") {
                     agendaTable.search("").draw();
                  }
               });
         }
      });
   }

   function renderActionButtonAgenda(dataId, row) {
      console.log('row.statusAgenda : ' + row.statusAgenda)
      var response = "";
      var editMenu = '<a href="${trainingAgendaEditURL}&id=' + dataId + '" >' +
         '<span><img src="<%=request.getContextPath()%>/assets/img/edit.svg" /></span>' +
         '</a> &nbsp;';
      var deleteMenu = '<a href="javascript:void(0)" onclick="deleteAgenda(this)" data-id="' + dataId + '" >' +
         '<span><img src="<%=request.getContextPath()%>/assets/img/trash.svg" /></span>' +
         '</a> &nbsp;';
      var participantMenu = '<a href="${trainingAgendaParticipantManualViewURL}&trainingAgendaId=' + dataId + '" class="btn btn-sm" style="border: 1px solid #D9D9D9;color: black;">' +
         '<i class="fa fa-users"></i>' +
         '</a>';
      if (row.statusAgenda === 1) {
         response = '<span class="action-wrapper" data-id="' + dataId + '">' +
            deleteMenu +
            participantMenu +
            '</span>';
      } else {
         response = '<span class="action-wrapper" data-id="' + dataId + '">' +
            editMenu +
            deleteMenu +
            participantMenu +
            '</span>';
      }
      return response;
   }

   function deleteAgenda(element) {
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
            formData.set("<portlet:namespace/>crudType", "delete");
            formData.set("<portlet:namespace/>entryId", entryId);
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            $.ajax({
               url: "${trainingAgendaActionURL}",
               type: "POST",
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var data = JSON.parse(response);
                  if (data["acknowledge"] === 1) {
                     Swal.fire("Failed to Delete", data["message"], "");
                  } else {
                     Swal.fire("Successfully delete data", "", "success")
                        .then((res) => {
                           // trainingAgendaDataTable.ajax.reload();
                           call_training_agenda_table()
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

   function getStatusAgendas() {
      const statusAgendaConst = $('#statusAgendaId').select2({
         data: statusAgendaData,
         tags: "true",
         placeholder: 'Pilih Status',
         allowClear: false,
         maximumInputLength: 100,
         templateSelection: function (data) {
            if (data.id === '' || data.id === 'Pilih Status') {
               statusAgendaId = 'ALL';
               return 'Pilih Status';
            } else {
               statusAgendaId = data.id;
            }
            call_training_agenda_table()
            return data.text;
         }
      });
      statusAgendaConst.val(null);
      statusAgendaConst.trigger('change');
   }
</script>
<script src="<%=request.getContextPath()%>/assets/library/quill/katex.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/quill/highlight.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/quill/quill.min.js"></script>
<script>
   var action = "create";
   var entryId = "0";
   var quill;
   var submitProcess = false;

   $(document).ready(function () {
      const options = {
         debug: 'info',
         modules: {
            syntax: true,
            toolbar: '#toolbar-container'
            // toolbar: [
            //    [{ header: [1, 2, 3, false] }],
            //    ['background', 'bold', 'color', 'font', 'code',  'italic', 'link', 'size', 'script', 'underline'],
            //    ['blockquote', 'header', 'indent', 'list', 'align', 'direction', 'code-block'],
            //    ['formula', 'image', 'video']
            // ]
         },
         // placeholder: 'Compose an epic...',
         // readOnly: true,
         theme: 'snow'  // 'snow' or 'bubble'
      };
      quill = new Quill('#editor-container', options);
      resetFormAgenda()
   });

   $("#resetBtnAgenda").click(function () {
      resetFormAgenda()
   });

   function resetFormAgenda() {
      formAgenda();
      createJenisAgendas();
      // getStatusAgendas();
      // $('#form-title').text("New Agenda")
      formAgenda();

      setTanggalMulai();
      setTanggalBerakhir();
      setRegisterEnd();
   }

   function formAgenda() {
      $('[name="entryId"]')[0].value = "0";
      $('[name="judulAgenda"]')[0].value = "";
      $('[name="jenisAgendaId"]')[0].value = "";
      $('[name="lokasiAgenda"]')[0].value = "";
      $('[name="linkAgenda"]')[0].value = "";
      $('[name="startDate"]')[0].value = "";
      $('[name="endDate"]')[0].value = "";
      $('[name="registrationDate"]')[0].value = "";
      $('[name="trainingAgendaImageFileId"]')[0].value = "";
      $('[name="trainingAgendaImageFileName"]')[0].value = "";
      $('[name="trainingAgendaImageFilePath"]')[0].value = "";

      $(".lokasiAgenda").hide();
      $(".linkAgenda").hide();
      $(".upload-content").remove();
      // Clear Quill
      var element = document.getElementsByClassName("ql-editor");
      element[0].innerHTML = "";
   }

   $("#formTrainingAgenda").submit(function (e) {
      e.preventDefault();
      const judul = $('[name="judulAgenda"]').val();
      const jenisAgendaId = $('[name="jenisAgendaId"]').val();
      const lokasi = $('[name="lokasiAgenda"]').val();
      const linkMeeting = $('[name="linkAgenda"]').val();
      const trainingAgendaImageFileId = $('[name="trainingAgendaImageFileId"]').val();
      const startDate = $('[name="startDate"]').val();
      const endDate = $('[name="endDate"]').val();
      const registrationDate = $('[name="registrationDate"]').val();
      const deskripsiHtml = quill.container.innerHTML;
      if (submitProcess === false) {
         if (judul === null || judul === undefined || judul.length < 5) {
            Swal.fire("Mohon isi judul minimal 5 karakter", "", "warning");
            return false;
         } else if (!regexBasicCharacter.test(judul)) {
            Swal.fire("Mohon tidak menggunakan karakter selain selain .,/()@&_-", "", "warning");
            return false;
         }
         // else if (/^[a-zA-Z0-9- ]*$/.test(judul) === false) {
         //    Swal.fire("Judul tidak boleh memakai karakter khusus.", "", "warning");
         //    return false;
         // }
         else if (jenisAgendaId === null || jenisAgendaId === undefined) {
            Swal.fire("Jenis Agenda belum dipilih", "", "warning");
            return false;
         } else if (trainingAgendaImageFileId === null || trainingAgendaImageFileId === undefined || trainingAgendaImageFileId.length < 1) {
            Swal.fire("Image belum diunggah", "", "warning");
            return false;
         } else if (jenisAgendaId === '0' && (lokasi === null || lokasi === undefined || lokasi.length < 5)) {
            Swal.fire("Lokasi belum sesuai.", "", "warning");
            return false;
         } else if (jenisAgendaId === '1' && (linkMeeting === null || linkMeeting === undefined || linkMeeting.length < 5)) {
            Swal.fire("Link Meeting belum sesuai.", "", "warning");
            return false;
         } else if (startDate === null || startDate === undefined || startDate.length < 3) {
            Swal.fire("Tanggal Mulai belum sesuai", "", "warning");
            return false;
         } else if (endDate === null || endDate === undefined || endDate.length < 3) {
            Swal.fire("Tanggal Berakhir belum sesuai", "", "warning");
            return false;
         } else if (registrationDate === null || registrationDate === undefined || registrationDate.length < 3) {
            Swal.fire("Batas Pendaftaran belum sesuai", "", "warning");
            return false;
         } else if (quill.getText().trim().length < 5) {
            Swal.fire("Deskripsi belum sesuai.", "", "warning");
            return false;
         } else {
            submitProcess = true;
            var formData = new FormData(this);
            formData.set("<portlet:namespace/>crudType", action);
            formData.set("<portlet:namespace/>entryId", "0");
            formData.set("<portlet:namespace/>deskripsi", deskripsiHtml);
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

            createLoading();
            $.ajax({
               url: '${trainingAgendaActionURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               success: function (response) {
                  var status = JSON.parse(response).status;
                  var message = JSON.parse(response).message;

                  if (status === 'success') {
                     swal.fire("Success", "Your request has been saved", "success")
                        .then(function () {
                           // window.location = homeUrl;
                           call_training_agenda_table();
                           formAgenda();
                           $('#kelola_agenda_navtab')[0].click();
                        });
                  } else if (status === 'warning') {
                     swal.fire('Sorry', message, 'warning');
                  } else {
                     swal.fire('Sorry', 'There is an internal error', 'error');
                  }
               },
               error: function (data, textStatus, XMLHttpRequest) {
                  if (data.status === 500) {
                     var msg = data.responseJSON.Message
                     swal('Sorry', msg, 'error');
                  } else if (data.status === 408) {
                     swal('Sorry', 'Request Time Out, Please Try Again', 'error');
                  } else {
                     swal('Sorry', textStatus + 'error submit', 'error');
                  }
               },
               complete: function (jqXHR, textStatus) {
                  submitProcess = false;
                  destroyLoading();
               }
            })
         }
      }
   })

   function createJenisAgendas() {
      const jenisAgendaConst = $('#jenisAgendaId').select2({
         data: jenisAgendaData,
         tags: false,
         placeholder: 'Pilih Jenis Agenda',
         allowClear: false,
         minimumInputLength: -1,
         maximumInputLength: 100,
         templateSelection: function (data) {
            if (data.id === '0') {
               $(".linkAgenda").hide();
               $(".lokasiAgenda").show();
            }
            if (data.id === '1') {
               $(".linkAgenda").show();
               $(".lokasiAgenda").hide();
            }
            if (data.id === 'Pilih Jenis Agenda') {
               return 'Pilih Jenis Agenda';
            } else {
               $('[name="jenisAgendaId"]')[0].value = data.id;
            }
            return data.text;
         }
      });
      jenisAgendaConst.val(null);
      jenisAgendaConst.trigger('change');
   }

   function setTanggalMulai() {
      $('#startDate').datetimepicker({
         // maxDate: moment(new Date()).add(30, 'days').format('YYYY/MM/DD'),
         minDate: moment(new Date()).format('YYYY/MM/DD'),
         format: 'DD/MM/YYYY HH:mm',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: true
      }).on("dp.change", function (e) {
         $('[name="endDate"]')[0].value = "";
         $('[name="registrationDate"]')[0].value = "";
         const maxEnd = moment(e.date).add(30, 'days');
         $('#endDate').data("DateTimePicker").maxDate(maxEnd);
         const minEnd = moment(e.date).add(0, 'days');
         $('#endDate').data("DateTimePicker").minDate(minEnd);
         try {
            // issue : maxReg harus lebih besar dari minReg, kalau sama tanggalnya tidak bisa
            const maxReg = moment(e.date).subtract(1, 'days');
            $('#registrationDate').data("DateTimePicker").maxDate(maxReg);
            const minReg = moment(e.date).subtract(1, 'days');
            $('#registrationDate').data("DateTimePicker").minDate(minReg);
         } catch (e) {
            const minReg = moment(e.date).subtract(1, 'days');
            $('#registrationDate').data("DateTimePicker").minDate(minReg);
            const maxReg = moment(e.date).subtract(1, 'days');
            $('#registrationDate').data("DateTimePicker").maxDate(maxReg);
         }
      });
   }

   function setTanggalBerakhir() {
      $('#endDate').datetimepicker({
         // minDate: moment(new Date()).format('YYYY/MM/DD'),
         format: 'DD/MM/YYYY HH:mm',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: false
      }).on("dp.change", function (e) {
         console.log('dateEnd : ' + e.date)
      });
   }

   function setRegisterEnd() {
      $('#registrationDate').datetimepicker({
         // minDate: moment(new Date()).format('YYYY/MM/DD'),
         format: 'DD/MM/YYYY HH:mm',
         locale: 'id',
         showClose: true,
         ignoreReadonly: true,
         useCurrent: false
      }).on("dp.change", function (e) {
         console.log('dateReg : ' + e.date)
      });
   }

   // Upload File
   $("#jenisMateriImage").change(function (e) {
      // processUpload(e, "jenisMateriImage", $(this));
      const allowedTypes = ['image/gif', 'image/jpeg', 'image/png'];
      var name = "jenisMateriImage";
      var element = $(this);

      if (e.target.files[0].size > 5242880) {
         swal.fire("", "File Size cannot more than 5 MB", "warning");
      } else {
         // uploadDocumentFile(e.target.files[0], fileName, $('[name="'+name+'FileId"]')[0].value, element, name);
         // uploadDocumentFile(file, fileName, fileID, element, name)
         var file = e.target.files[0];
         var fileName = e.target.files[0].name;
         var fileID = $('[name="' + name + 'FileId"]')[0].value;

         let formData = new FormData();
         let format = /[:"\\|,<>\/?^*]/;
         if (format.test(fileName)) {
            swal.fire('', 'Mohon tidak menggunakan karakter ,^*:"|<>\\ /?', 'warning');
         } else if (!allowedTypes.includes(file.type)) {
            Swal.fire('Upload File Gagal', 'Hanya file dengan format jpg, jpeg, png, dan gif yang diperbolehkan', 'warning');
            element.val(null);
         } else {
            fileName = fileName.replaceAll(",", "");
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
            formData.set("<portlet:namespace/>file-upload", file);
            formData.set("<portlet:namespace/>file-name", fileName);
            formData.set("<portlet:namespace/>file-id", fileID);

            element.parents(".input-group").siblings(".upload-content").remove();
            element.val("");

            let htmlLoading = $(`
                <div class="upload-content">
                    <div class="progress" style="margin-top: 10px; margin-bottom: 0px; transform: scaleY(0.7);">
                        <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" style="width: 0%;"></div>
                    </div>
                    <div id="progress-percentage">0% uploaded</div>
                </div>
            `);

            element.parents(".input-group").after(htmlLoading);

            let elementEdit = element;
            let nameEdit = name;

            $.ajax({
               url: '${uploadFileTrainingAgendaCommandURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               xhr: function () {
                  var xhr = new XMLHttpRequest();

                  xhr.upload.addEventListener('progress', function (e) {
                     if (e.lengthComputable) {
                        var uploadPercent = e.loaded / e.total;
                        uploadPercent = (uploadPercent * 100);

                        if (parseInt(uploadPercent) === 100) {
                           htmlLoading.find("#progress-percentage").text('Silahkan tunggu...');
                        } else {
                           htmlLoading.find("#progress-percentage").text(parseInt(uploadPercent) + '% uploaded');
                        }
                        htmlLoading.find(".progress-bar").width(uploadPercent + '%');
                     }
                  }, false);

                  return xhr;
               },
               success: function (response) {
                  let data = JSON.parse(response);
                  let acknowledge = data["acknowledge"];
                  let message = data["message"]
                  let fileID = data["fileID"];
                  fileName = data["fileName"];
                  let fileURL = data["fileURL"];

                  if (acknowledge === 1) {
                     $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileId"]'))[0].value = fileID;
                     $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileName"]'))[0].value = fileName;
                     $(elementEdit.parent().siblings('[name="' + nameEdit + 'FilePath"]'))[0].value = fileURL;

                     let successHTML = `
                     <div style="margin-top: -8px;margin-bottom: 15px;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
                        <div>
                           <p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px">Upload file berhasil </p>
                        </div>
                        <div>
                           <div id="delete-file-button">
                              <a href="javascript:void(0);" onclick="deleteJenisMateriImage(this, ` + fileID + `)" title="Delete File"><i class="fas fa-trash"></i></a>
                           </div>
                           <div id="delete-loader" style="display: none;">
                              <i class="fas fa-spinner anim-rotate"></i>
                           </div>
                        </div>
                     </div>
                  `;
                     setTimeout(function () {
                        htmlLoading.empty();
                        htmlLoading.append(successHTML);
                     }, 2000);
                  } else {
                     htmlLoading.find("#progress-percentage").hide();
                     htmlLoading.find(".progress-bar").hide();
                     htmlLoading.hide();
                     element.val(null);
                     Swal.fire({
                        icon: "error",
                        title: "Gagal Mengunggah Image",
                        text: message
                     });
                  }
               },
               error: function (e) {
                  console.log(e);
                  htmlLoading.find("#progress-percentage").hide();
                  htmlLoading.find(".progress-bar").hide();
                  htmlLoading.hide();
                  swal.fire({icon: "error", title: "Failed to upload file"});
               },
               complete: function () {
                  console.log("DONE");
               }
            });
         }
      }
   });

   function deleteJenisMateriImage(element, fileID) {
      var parentElement1 = $(element).parents(".upload-content");
      parentElement1.find("#delete-file-button").css("display", "none");
      parentElement1.find("#delete-loader").css("display", "inherit");

      var formData = new FormData();
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      formData.set("<portlet:namespace/>file-id", fileID);
      var parentElement = element.parentElement.parentElement.parentElement;
      // let inputElement = $(parentElement1.siblings().find('input.form-control.required.switch-readonly'))[0];
      var inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url: '${deleteFileTrainingAgendaCommandURL}',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            var data = JSON.parse(response);
            if (data["acknowledge"] === 1) {
               parentElement.remove();
               parentElement1.remove();
               // inputElement.value = '';
               $('[name="jenisMateriImageFileId"]')[0].value = "";
               $('[name="jenisMateriImageFileName"]')[0].value = "";
               $('[name="jenisMateriImageFilePath"]')[0].value = "";
            } else {
               parentElement1.find("#delete-file-button").css("display", "inherit");
               parentElement1.find("#delete-loader").css("display", "none");
               parentElement.find("[name=file-status]").text("delete failed");
            }
         },
         error: function (e) {
            console.log(e);
            swal({icon: "error", title: "Failed to delete file"});
         },
         complete: function () {
            console.log("DONE");
         }
      });
   }

   $("#trainingMateriImage").change(function (e) {
      // processUpload(e, "trainingMateriImage", $(this));
      var name = "trainingMateriImage";
      var element = $(this);
      if (e.target.files[0].size > 5242880) {
         swal.fire("", "File Size cannot more than 5 MB", "warning");
      } else {
         // uploadDocumentFile(e.target.files[0], fileName, $('[name="'+name+'FileId"]')[0].value, element, name);
         // uploadDocumentFile(file, fileName, fileID, element, name)
         const allowedTypes = ['image/gif', 'image/jpeg', 'image/png'];

         var file = e.target.files[0];
         var fileName = e.target.files[0].name;
         var fileID = $('[name="' + name + 'FileId"]')[0].value

         let formData = new FormData();
         let format = /[:"\\|,<>\/?^*]/;
         if (format.test(fileName)) {
            swal.fire('', 'File name cannot contains special character ,^*:"|<>\\ /?', 'warning')
         } else if (!allowedTypes.includes(file.type)) {
            Swal.fire('Upload File Gagal', 'Hanya file dengan format jpg, jpeg, png, dan gif yang diperbolehkan', 'warning');
            element.val(null);
         } else {
            fileName = fileName.replaceAll(",", "")
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
            formData.set("<portlet:namespace/>file-upload", file);
            formData.set("<portlet:namespace/>file-name", fileName);
            formData.set("<portlet:namespace/>file-id", fileID);
            formData.set("<portlet:namespace/>document-id", $('[name="document-id"]').val());
            formData.set("<portlet:namespace/>document-id-new", $('[name="document-id-new"]').val());

            element.parents(".input-group").siblings(".upload-content").remove();
            element.val("");

            let htmlLoading = $(`
                <div class="upload-content">
                    <div class="progress" style="margin-top: 10px; margin-bottom: 0px; transform: scaleY(0.7);">
                        <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" style="width: 0%;"></div>
                    </div>
                    <div id="progress-percentage">0% uploaded</div>
                </div>
            `);

            element.parents(".input-group").after(htmlLoading);

            let elementEdit = element;
            let nameEdit = name;

            $.ajax({
               url: '${uploadFileTrainingAgendaCommandURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               xhr: function () {
                  var xhr = new XMLHttpRequest();

                  xhr.upload.addEventListener('progress', function (e) {
                     if (e.lengthComputable) {
                        var uploadPercent = e.loaded / e.total;
                        uploadPercent = (uploadPercent * 100);

                        if (parseInt(uploadPercent) === 100) {
                           htmlLoading.find("#progress-percentage").text('Silahkan tunggu...');
                        } else {
                           htmlLoading.find("#progress-percentage").text(parseInt(uploadPercent) + '% uploaded');
                        }
                        htmlLoading.find(".progress-bar").width(uploadPercent + '%');
                     }
                  }, false);

                  return xhr;
               },
               success: function (response) {
                  let data = JSON.parse(response);
                  let acknowledge = data["acknowledge"];
                  let message = data["message"]
                  let fileID = data["fileID"];
                  fileName = data["fileName"];
                  let fileURL = data["fileURL"];

                  if (acknowledge === 1) {
                     $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileId"]'))[0].value = fileID;
                     $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileName"]'))[0].value = fileName;
                     $(elementEdit.parent().siblings('[name="' + nameEdit + 'FilePath"]'))[0].value = fileURL;

                     let successHTML = `
                     <div style="margin-top: -8px;margin-bottom: 15px;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
                        <div>
                           <p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px">Upload file berhasil </p>
                        </div>
                        <div>
                           <div id="delete-file-button">
                              <a href="javascript:void(0);" onclick="deleteTrainingMateriImage(this, ` + fileID + `)" title="Delete File"><i class="fas fa-trash"></i></a>
                           </div>
                           <div id="delete-loader" style="display: none;">
                              <i class="fas fa-spinner anim-rotate"></i>
                           </div>
                        </div>
                     </div>
                  `;

                     setTimeout(function () {
                        htmlLoading.empty();
                        htmlLoading.append(successHTML);
                     }, 2000);
                  } else {
                     htmlLoading.find("#progress-percentage").hide();
                     htmlLoading.find(".progress-bar").hide();
                     htmlLoading.hide();
                     element.val(null);
                     Swal.fire({
                        icon: "error",
                        title: "Gagal Mengupload File",
                        text: message
                     });
                  }
               },
               error: function (e) {
                  console.log(e);
                  htmlLoading.find("#progress-percentage").hide();
                  htmlLoading.find(".progress-bar").hide();
                  htmlLoading.hide();
                  swal.fire({icon: "error", title: "Failed to upload file"});
               },
               complete: function () {
                  console.log("DONE");
               }
            });
         }
      }
   });

   function deleteTrainingMateriImage(element, fileID) {
      var parentElement1 = $(element).parents(".upload-content");
      parentElement1.find("#delete-file-button").css("display", "none");
      parentElement1.find("#delete-loader").css("display", "inherit");

      var formData = new FormData();
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      formData.set("<portlet:namespace/>file-id", fileID);
      var parentElement = element.parentElement.parentElement.parentElement
      // let inputElement = $(parentElement1.siblings().find('input.form-control.required.switch-readonly'))[0];
      var inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url: '${deleteFileTrainingAgendaCommandURL}',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            var data = JSON.parse(response);
            if (data["acknowledge"] === 1) {
               parentElement.remove()
               parentElement1.remove();
               // inputElement.value = '';
               $('[name="trainingMateriImageFileId"]')[0].value = "";
               $('[name="trainingMateriImageFileName"]')[0].value = "";
               $('[name="trainingMateriImageFilePath"]')[0].value = "";
            } else {
               parentElement1.find("#delete-file-button").css("display", "inherit");
               parentElement1.find("#delete-loader").css("display", "none");
               parentElement.find("[name=file-status]").text("delete failed");
            }
         },
         error: function (e) {
            console.log(e);
            swal({icon: "error", title: "Failed to delete file"});
         },
         complete: function () {
            console.log("DONE");
         }
      });
   }

   $("#trainingAgendaImage").change(function (e) {
      // processUpload(e, "trainingAgendaImage", $(this));
      var name = "trainingAgendaImage";
      var element = $(this);
      var fileSize = e.target.files[0].size;
      if (fileSize > 5242880) {
         swal.fire("", "File Size cannot more than 5 MB", "warning");
      } else {
         // uploadDocumentFile(e.target.files[0], fileName, $('[name="'+name+'FileId"]')[0].value, element, name);
         // uploadDocumentFile(file, fileName, fileID, element, name)
         const allowedTypes = ['image/gif', 'image/jpeg', 'image/png'];
         var file = e.target.files[0];
         var fileName = e.target.files[0].name;
         var fileID = $('[name="' + name + 'FileId"]')[0].value;

         let formData = new FormData();
         let format = /[:"\\|,<>\/?^*]/;
         if (format.test(fileName)) {
            swal.fire('', 'File name cannot contains special character ,^*:"|<>\\ /?', 'warning');
            element.val(null);
         } else if (!allowedTypes.includes(file.type)) {
            Swal.fire('Upload File Gagal', 'Hanya file dengan format jpg, jpeg, png, dan gif yang diperbolehkan', 'warning');
            element.val(null);
         } else {
            fileName = fileName.replaceAll(",", "")
            formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
            formData.set("<portlet:namespace/>file-upload", file);
            formData.set("<portlet:namespace/>file-name", fileName);
            formData.set("<portlet:namespace/>file-id", fileID);
            formData.set("<portlet:namespace/>document-id", $('[name="document-id"]').val());
            formData.set("<portlet:namespace/>document-id-new", $('[name="document-id-new"]').val());

            element.parents(".input-group").siblings(".upload-content").remove();
            element.val("");

            let htmlLoading = $(`
                <div class="upload-content">
                    <div class="progress" style="margin-top: 10px; margin-bottom: 0px; transform: scaleY(0.7);">
                        <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" style="width: 0%;"></div>
                    </div>
                    <div id="progress-percentage">0% uploaded</div>
                </div>
            `);

            element.parents(".input-group").after(htmlLoading);

            let elementEdit = element;
            let nameEdit = name;

            $.ajax({
               url: '${uploadFileTrainingAgendaCommandURL}',
               type: 'POST',
               data: formData,
               processData: false,
               contentType: false,
               xhr: function () {
                  var xhr = new XMLHttpRequest();

                  xhr.upload.addEventListener('progress', function (e) {
                     if (e.lengthComputable) {
                        var uploadPercent = e.loaded / e.total;
                        uploadPercent = (uploadPercent * 100);

                        if (parseInt(uploadPercent) === 100) {
                           htmlLoading.find("#progress-percentage").text('Silahkan tunggu...');
                        } else {
                           htmlLoading.find("#progress-percentage").text(parseInt(uploadPercent) + '% uploaded');
                        }
                        htmlLoading.find(".progress-bar").width(uploadPercent + '%');
                     }
                  }, false);

                  return xhr;
               },
               success: function (response) {
                  let data = JSON.parse(response);
                  let acknowledge = data["acknowledge"];
                  let fileID = data["fileID"];
                  fileName = data["fileName"];
                  let fileURL = data["fileURL"];

                  if (acknowledge === 1) {
                     $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileId"]'))[0].value = fileID
                     $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileName"]'))[0].value = fileName
                     $(elementEdit.parent().siblings('[name="' + nameEdit + 'FilePath"]'))[0].value = fileURL

                     let successHTML = `
                     <div style="margin-top: -8px;margin-bottom: 15px;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
                        <div>
                           <p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px">Upload file berhasil </p>
                        </div>
                        <div>
                           <div id="delete-file-button">
                              <a href="javascript:void(0);" onclick="deleteTrainingAgendaImage(this, ` + fileID + `)" title="Delete File"><i class="fas fa-trash"></i></a>
                           </div>
                           <div id="delete-loader" style="display: none;">
                              <i class="fas fa-spinner anim-rotate"></i>
                           </div>
                        </div>
                     </div>
                  `;
                     setTimeout(function () {
                        htmlLoading.empty();
                        htmlLoading.append(successHTML);
                     }, 2000);
                  } else {
                     swal.fire({icon: "warning", title: "Gagal mengunggah file"});
                  }
               },
               error: function (e) {
                  console.log(e);
                  swal.fire({icon: "error", title: "Failed to upload file"});
               },
               complete: function () {
                  console.log("DONE");
               }
            });
         }
      }
   });

   function deleteTrainingAgendaImage(element, fileID) {
      var parentElement1 = $(element).parents(".upload-content");
      parentElement1.find("#delete-file-button").css("display", "none");
      parentElement1.find("#delete-loader").css("display", "inherit");

      var formData = new FormData();
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      formData.set("<portlet:namespace/>file-id", fileID);
      var parentElement = element.parentElement.parentElement.parentElement
      // let inputElement = $(parentElement1.siblings().find('input.form-control.required.switch-readonly'))[0];
      var inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url: '${deleteFileTrainingAgendaCommandURL}',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            var data = JSON.parse(response);
            if (data["acknowledge"] === 1) {
               parentElement.remove()
               parentElement1.remove();
               // inputElement.value = '';
               $('[name="trainingAgendaImageFileId"]')[0].value = "";
               $('[name="trainingAgendaImageFileName"]')[0].value = "";
               $('[name="trainingAgendaImageFilePath"]')[0].value = "";
            } else {
               parentElement1.find("#delete-file-button").css("display", "inherit");
               parentElement1.find("#delete-loader").css("display", "none");
               parentElement.find("[name=file-status]").text("delete failed");
            }
         },
         error: function (e) {
            console.log(e);
            swal({icon: "error", title: "Failed to delete file"});
         },
         complete: function () {
            console.log("DONE");
         }
      });
   }

   // Multi Upload File
   function addOtherFile() {
      var html = $(`<div class="parent-file-container" id="fileOther">
                        <div class="file-container-button" style="margin-bottom: 15px;">
                            <div>
                                <div class="file-input-container">
                                    <label class="file-input-label" for="trainingMateri">Pilih File</label>
                                    <input onchange="fileChange(this)" type="file" name="trainingMateri" id="trainingMateri" accept=".docx, .xlsx, .pdf">
                                    <input class="file-text" name="trainingMateriFileName" id="trainingMateriFileName">
                                    <input type="hidden" class="file-text form-required" name="trainingMateriFileId" id="trainingMateriFileId">
                                    <input type="hidden" class="file-text form-required" name="trainingMateriFilePath" id="trainingMateriFilePath">
                                </div>
                            </div>
                            <button onclick="removeFile(this);" type="button" class="btn-add-file-container" title="Remove File"><i class="fas fa-minus"></i></button>
                        </div>
                    </div>`);
      if ($("#other-file-container").find(".parent-file-container").length < maxFile) {
         $("#other-file-container").append(html);
      }
      return html;
   }

   function fileChange(element) {
      var format = /[:"\\|,<>\/?^*#%]/;
      var $element = $(element);
      var $parent = $element.parents(".file-input-container");
      var elementId = $element.attr("id");
      $parent.find("#" + elementId + "FileName").val('');
      $parent.find("#" + elementId + "FileId").val('');
      $parent.find("#" + elementId + "FilePath").val('');

      var file = element.files[0];
      if (file === undefined) {
         return false;
      }

      var fileId = $parent.find("#" + elementId + "FileId").val();
      var fileType = file["type"];
      var fileName = file["name"];
      var fileSize = file["size"];
      var fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

      if (fileSize > 5 * 1024 * 1024) {
         $element.val("");
         Swal.fire("Upload File Gagal", "Ukuran file terlalu besar", "warning");
         return false;
      }
      if (format.test(fileName)) {
         $element.val("");
         Swal.fire("Upload File Gagal", 'Nama file tidak boleh mengandung karakter spesial ,^*:"|<>\\ /?#%', "warning");
         return false;
      }
      if (fileExtension !== "pdf" && fileExtension !== "docx" && fileExtension !== "xlsx") {
         $element.val("");
         Swal.fire("Upload File Gagal", "Hanya file File dengan format docx, xlsx, dan pdf yang diperbolehkan", "warning");
         return false;
      }

      var htmlLoading = $(`
                <div class="upload-content">
                    <div class="progress" style="margin-top: 10px; margin-bottom: 0; transform: scaleY(0.7);">
                        <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" style="width: 0;"></div>
                    </div>
                    <div id="progress-percentage">0% uploaded</div>
                </div>
            `);

      $element.parent().siblings(".upload-content").remove();
      $element.parent().after(htmlLoading);

      let formData = new FormData();
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      formData.set("<portlet:namespace/>file-upload", file);
      formData.set("<portlet:namespace/>file-name", fileName);
      formData.set("<portlet:namespace/>file-id", fileId);
      formData.set("<portlet:namespace/>document-id", $("#<portlet:namespace/>documentId").val() == "0" ? "undefined" : $("#<portlet:namespace/>documentId").val());
      formData.set("<portlet:namespace/>document-id-new", $("#<portlet:namespace/>newDocumentId").val());
      $.ajax({
         url: '${uploadFileTrainingMateriCommandURL}',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         xhr: function () {
            var xhr = new XMLHttpRequest();
            xhr.upload.addEventListener('progress', function (e) {
               if (e.lengthComputable) {
                  var uploadPercent = e.loaded / e.total;
                  uploadPercent = (uploadPercent * 100);
                  if (parseInt(uploadPercent) === 100) {
                     htmlLoading.find("#progress-percentage").text('Silahkan tunggu...');
                  } else {
                     htmlLoading.find("#progress-percentage").text(parseInt(uploadPercent) + '%');
                  }
                  htmlLoading.find(".progress-bar").width(uploadPercent + '%');
               }
            }, false);
            return xhr;
         },
         success: function (response) {
            let data = JSON.parse(response);
            let acknowledge = data["acknowledge"];
            let fileID = data["fileID"];
            fileName = data["fileName"];
            let fileURL = data["fileURL"];
            if (acknowledge === 1) {
               $parent.find("#" + elementId + "").val('');
               $parent.find("#" + elementId + "FileId").val(fileID);
               $parent.find("#" + elementId + "FileName").val(fileName);
               $parent.find("#" + elementId + "FilePath").val(fileURL);
               let successHTML = `
                        <div style="margin-top: -5px;margin-bottom: 0;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
                            <div>
                                <p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px">Upload file berhasil</p>
                            </div>
                        </div>
                        `;
               htmlLoading.empty();
               htmlLoading.append(successHTML);
            } else {
               Swal.fire({icon: "error", title: "Gagal Mengupload File"});
            }
         },
         error: function (e) {
            console.log(e);
            Swal.fire({icon: "error", title: "Gagal Mengupload File"});
         },
         complete: function () {
            console.log("DONE");
         }
      });
   }

   function removeFile(element) {
      if (action === 'update') {
         var parent = $(element).parents(".parent-file-container");
         var id = parent.find("#trainingMateriFileId").val();
         var name = parent.find("#trainingMateriFileName").val();
         listDeleted.push(id);
      }
      $(element).parents(".parent-file-container").remove();
   }

   function deleteTrainingMateriFileId(element, fileID) {
      var parentElement1 = $(element).parents(".upload-content");
      parentElement1.find("#delete-file-button").css("display", "none");
      parentElement1.find("#delete-loader").css("display", "inherit");

      var formData = new FormData();
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      formData.set("<portlet:namespace/>file-id", fileID);
      var parentElement = element.parentElement.parentElement.parentElement;
      var inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url: '${deleteFileTrainingAgendaCommandURL}',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            var data = JSON.parse(response);
            if (data["acknowledge"] === 1) {
               parentElement.remove()
               parentElement1.remove();
            } else {
               parentElement1.find("#delete-file-button").css("display", "inherit");
               parentElement1.find("#delete-loader").css("display", "none");
               parentElement.find("[name=file-status]").text("delete failed");
            }
         },
         error: function (e) {
            console.log(e);
            swal({icon: "error", title: "Failed to delete file"});
         },
         complete: function () {
            console.log("DONE");
         }
      });
   }

   function fileChangeOne(element) {
      var format = /[:"\\|,<>\/?^*#%]/;
      var $element = $(element);
      var $parent = $element.parents(".file-input-container");
      var elementId = $element.attr("id");
      $parent.find("#trainingMateriOneFileId").val('');
      $parent.find("#trainingMateriOneFileName").val('');
      $parent.find("#trainingMateriOneFilePath").val('');

      var file = element.files[0];
      if (file === undefined) {
         return false;
      }

      var fileId = $parent.find("#trainingMateriOneFileId").val();
      var fileType = file["type"];
      var fileName = file["name"];
      var fileSize = file["size"];
      var fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

      if (fileSize > 5 * 1024 * 1024) {
         $element.val("");
         Swal.fire("Upload File Gagal", "Ukuran file terlalu besar", "warning");
         return false;
      }
      if (format.test(fileName)) {
         $element.val("");
         Swal.fire("Upload File Gagal", 'Nama file tidak boleh mengandung karakter spesial ,^*:"|<>\\ /?#%', "warning");
         return false;
      }
      if (fileExtension !== "pdf" && fileExtension !== "docx" && fileExtension !== "xlsx") {
         $element.val("");
         Swal.fire("Upload File Gagal", "Hanya file File dengan format docx, xlsx, dan pdf yang diperbolehkan", "warning");
         return false;
      }

      var htmlLoading = $(`
                <div class="upload-content">
                    <div class="progress" style="margin-top: 10px; margin-bottom: 0px; transform: scaleY(0.7);">
                        <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" style="width: 0%;"></div>
                    </div>
                    <div id="progress-percentage">0% uploaded</div>
                </div>
            `);

      $element.parent().siblings(".upload-content").remove();
      $element.parent().after(htmlLoading);

      let formData = new FormData();
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      formData.set("<portlet:namespace/>file-upload", file);
      formData.set("<portlet:namespace/>file-name", fileName);
      formData.set("<portlet:namespace/>file-id", fileId);
      formData.set("<portlet:namespace/>document-id", $("#<portlet:namespace/>documentId").val() == "0" ? "undefined" : $("#<portlet:namespace/>documentId").val());
      formData.set("<portlet:namespace/>document-id-new", $("#<portlet:namespace/>newDocumentId").val());
      $.ajax({
         url: '${uploadFileTrainingMateriCommandURL}',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         xhr: function () {
            var xhr = new XMLHttpRequest();
            xhr.upload.addEventListener('progress', function (e) {
               if (e.lengthComputable) {
                  var uploadPercent = e.loaded / e.total;
                  uploadPercent = (uploadPercent * 100);
                  if (parseInt(uploadPercent) === 100) {
                     htmlLoading.find("#progress-percentage").text('Silahkan tunggu...');
                  } else {
                     htmlLoading.find("#progress-percentage").text(parseInt(uploadPercent) + '%');
                  }
                  htmlLoading.find(".progress-bar").width(uploadPercent + '%');
               }
            }, false);
            return xhr;
         },
         success: function (response) {
            let data = JSON.parse(response);
            let acknowledge = data["acknowledge"];
            let message = data["message"]
            let fileID = data["fileID"];
            fileName = data["fileName"];
            let fileURL = data["fileURL"];
            if (acknowledge === 1) {
               $parent.find("#trainingMateriOne").val('');
               $parent.find("#trainingMateriOneFileId").val(fileID);
               $parent.find("#trainingMateriOneFileName").val(fileName);
               $parent.find("#trainingMateriOneFilePath").val(fileURL);
               let successHTML = `
                        <div style="margin-top: -5px;margin-bottom: 0;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
                            <div>
                                <p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px">Upload file berhasil</p>
                            </div>
                            <div>
                                <div id="delete-file-button">
                                    <a href="javascript:void(0);" onclick="deleteTrainingMateriOne(this, ` + fileID + `)" title="Delete File"><i class="fas fa-trash"></i></a>
                                </div>
                                <div id="delete-loader" style="display: none;">
                                    <i class="fas fa-spinner anim-rotate"></i>
                                </div>
                            </div>
                        </div>
                        `;
               htmlLoading.empty();
               htmlLoading.append(successHTML);
            } else {
               htmlLoading.find("#progress-percentage").hide();
               htmlLoading.find(".progress-bar").hide();
               htmlLoading.hide();
               $element.val(null);
               Swal.fire({
                  icon: "error",
                  title: "Gagal Mengunggah File",
                  text: message
               });
            }
         },
         error: function (e) {
            console.log(e);
            htmlLoading.find("#progress-percentage").hide();
            htmlLoading.find(".progress-bar").hide();
            htmlLoading.hide();
            Swal.fire({icon: "error", title: "Gagal Mengupload File"});
         },
         complete: function () {
            console.log("DONE");
         }
      });
   }

   function deleteTrainingMateriOne(element, fileID) {
      var parentElement1 = $(element).parents(".upload-content");
      parentElement1.find("#delete-file-button").css("display", "none");
      parentElement1.find("#delete-loader").css("display", "inherit");

      var formData = new FormData();
      formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
      formData.set("<portlet:namespace/>file-id", fileID);
      var parentElement = element.parentElement.parentElement.parentElement;
      var inputElement = $(parentElement1.siblings().find('input.form-control'))[0];

      $.ajax({
         url: '${deleteFileTrainingAgendaCommandURL}',
         type: 'POST',
         data: formData,
         processData: false,
         contentType: false,
         success: function (response) {
            var data = JSON.parse(response);
            if (data["acknowledge"] === 1) {
               parentElement.remove()
               parentElement1.remove();
               // inputElement.value = '';

               $('[name="trainingMateriOneFileId"]')[0].value = "";
               $('[name="trainingMateriOneFileName"]')[0].value = "";
               $('[name="trainingMateriOneFilePath"]')[0].value = "";
            } else {
               parentElement1.find("#delete-file-button").css("display", "inherit");
               parentElement1.find("#delete-loader").css("display", "none");
               parentElement.find("[name=file-status]").text("delete failed");
            }
         },
         error: function (e) {
            console.log(e);
            swal({icon: "error", title: "Failed to delete file"});
         },
         complete: function () {
            console.log("DONE");
         }
      });
   }
</script>