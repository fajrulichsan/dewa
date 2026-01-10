<%--
  Created by IntelliJ IDEA.
  User: psmahmad1402
  Date: 20/01/2025
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>

<div class="tab-pane fade" id="create_d_others_tab" role="tabpanel" aria-labelledby="profile-tab">
  <form id="formDiskonOthers" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
    <div class="container row form-field">
      <input type="hidden" name="entryId">
      <div class="form-group col-md-9">
        <label class="title-form pull-left" for="formDealerId">
          Kode Dealer
          <span class="text-danger">*</span>
        </label>
        <select class="form-control" name="dealerId" id="formDealerId" style="width: 100%;"></select>
      </div>
      <div class="form-group col-md-9">
        <label class="title-form pull-left" for="formTahun">
          Tahun
          <span class="text-danger">*</span>
        </label>
        <select class="form-control" name="tahun" id="formTahun" style="width: 100%;">
          <%-- <option value="NULL">List Dealer</option>--%>
        </select>
      </div>
      <div class="form-group col-md-9">
        <label class="title-form pull-left" for="formKuartalId">
          Kuartal
          <span class="text-danger">*</span>
        </label>
        <select class="form-control" name="kuartalId" id="formKuartalId" style="width: 100%;">
          <%-- <option value="NULL">List Dealer</option>--%>
        </select>
      </div>
      <div class="form-group col-sm-9">
        <label class="col-form-label ipr-color">
          File Report
          <span class="text-danger">*</span>
        </label>
        <div class="input-group upload-file-group">
          <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
            <span class="icon-browse">
              <img src="<%=request.getContextPath()%>/assets/img/file.svg">
            </span>
            <span class="text-browse">Pilih File</span>
            <input type="file" accept=".xlsx" placeholder="" name="diskonOthersFile"
                   id="diskonOthersFile" data-filename="" data-location="" style="display: none;"
                   aria-invalid="false" class="materail_input valid">
          </label>
          <input type="text" class="form-control required" data-type="file" data-name="" name="diskonOthersFileName" readonly style="border: 1px solid #DBEDFF; background-color: white;">
          <input type="text" class="dpn" name="diskonOthersFileId">
          <input type="text" class="dpn" name="diskonOthersFilePath"/>
        </div>
        <label class="col-form-label text-danger" id="fileRestrictionText">
          Format file .xlsx dengan maksimal ukuran file 1MB
        </label>
      </div>
      <div class="form-group col-md-9">
        <div class="action-line"></div>
      </div>
      <div class="form-group col-md-9 action-button">
        <button class="btn-ipr" type="submit">Simpan</button>
        <a class="btn btn-ipr-cancel" id="backButtonDocFlow" href="javascript:void(0)">Batal</a>
      </div>
    </div>
  </form>
</div>