<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 31/10/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/diskon/diskon-test-car/style-css.jsp" %>

<portlet:resourceURL id="/diskon-test-car-list" var="diskonTestCarListURL" />
<portlet:resourceURL id="/diskon-test-car-action" var="diskonTestCarActionURL" />
<portlet:resourceURL id="/diskon-test-car-edit" var="diskonTestCarEditURL" />
<portlet:resourceURL id="/upload-file-diskon-test-car" var="uploadFileDiskonTestCarCommandURL" />
<portlet:resourceURL id="/delete-file-diskon-test-car" var="deleteFileDiskonTestCarCommandURL" />
<portlet:resourceURL id="/dealer-diskon-test-car" var="dealerDiskonTestCarURL" />
<portlet:resourceURL id="/tahun-diskon-test-car" var="tahunDiskonTestCarURL" />
<portlet:resourceURL id="/tahun-diskon-test-car-file" var="tahunDiskonTestCarFileURL" />
<portlet:resourceURL id="/tipe-kendaraan-diskon-test-car" var="tipeKendaraanDiskonTestCarURL" />
<portlet:resourceURL id="/kuartal-diskon-test-car" var="kuartalDiskonTestCarURL" />

<div class="diskon_test_car cms-menu">
    <div class="tabcontent">
        <ul class="nav nav-tabs" id="cmsTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link" id="create_d_test_car_navtab" data-toggle="tab" href="#create_d_test_car_tab" role="tab" aria-controls="create_d_test_car_tab" aria-selected="false">
                    <span id="action-status"></span>
                    &nbsp;Realisasi Diskon Test Car
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="kelola_d_test_car_navtab" data-toggle="tab" href="#kelola_d_test_car_tab" role="tab" aria-controls="kelola_d_test_car_tab" aria-selected="false">
                    Kelola Realisasi Diskon Test Car
                </a>
            </li>
        </ul>

        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade" id="kelola_d_test_car_tab" role="tabpanel" aria-labelledby="profile-tab">
                <div class="row table-filters">
                    <div class="col-xs-12 col-sm-12 col-md-3 dealerId">
                        <select class="form-control" name="dealerId" id="viewDealerId" style="width: 100%;">
                            <option value="ALL">Select All</option>
                        </select>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-3">
                        <select class="form-control" name="tahunId" id="viewTahunId" style="width: 100%;">
                            <option value="ALL">Select All</option>
                        </select>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-3">
                        <select class="form-control" name="kuartalId" id="viewKuartalId" style="width: 100%;">
                            <option value="ALL">Select All</option>
                        </select>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-3">
                        <select class="form-control" name="tipeKendaraanId" id="viewTipeKendaraanId" style="width: 100%;">
                            <option value="ALL">Select All</option>
                        </select>
                    </div>
                </div>
                <table id="diskon_test_car_table" class="table table-hover nowrap cms-table" style="width:100%">
                    <thead>
                    <tr>
                        <th>No</th>
                        <th>Realisasi Diskon Test Car</th>
                        <th>Tahun</th>
                        <th>Kuartal</th>
                        <th>Tipe Kendaraan</th>
                        <th>Tanggal Upload</th>
                        <th>Aksi</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>

            <div class="tab-pane fade" id="create_d_test_car_tab" role="tabpanel" aria-labelledby="profile-tab">
                <form id="formDiskonTestCar" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
                    <div class="container row form-field">
                        <input type="hidden" name="entryId">
                        <div class="form-group col-md-9">
                            <label class="title-form pull-left" for="dealerId">
                                Kode Dealer
                                <span class="text-danger">*</span>
                            </label>
                            <input type="hidden" class="form-control" name="dealerId">
                            <select class="form-control" id="dealerId" style="width: 100%;"></select>
                        </div>
                        <div class="form-group col-md-9">
                            <label class="title-form pull-left" for="tahunId">
                                Tahun
                                <span class="text-danger">*</span>
                            </label>
                            <input type="hidden" class="form-control" name="tahun"/>
                            <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;"></select>
                        </div>
                        <div class="form-group col-md-9">
                            <label class="title-form pull-left" for="kuartalId">
                                Kuartal
                                <span class="text-danger">*</span>
                            </label>
                            <input type="hidden" class="form-control" name="kuartalName"/>
                            <select class="form-control" name="kuartalId" id="kuartalId" style="width: 100%;"></select>
                        </div>
                        <div class="form-group col-md-9">
                            <label class="title-form pull-left" for="tipeKendaraanId">
                                Tipe Kendaraan
                                <span class="text-danger">*</span>
                            </label>
                            <input type="hidden" class="form-control" name="tipeKendaraanName"/>
                            <select class="form-control" name="tipeKendaraanId" id="tipeKendaraanId" style="width: 100%;"></select>
                        </div>
                        <div class="form-group col-sm-9">
                            <label class="col-form-label ipr-color">
                                File Report
                                <span class="text-danger">*</span>
                            </label>
                            <div class="input-group upload-file-group">
                                <label class="btn btn-browse btn-fill input-group-addon lbl-atc inv" style="border-width: 0px;background-color:  #014689;">
                                    <span class="icon-browse">
                                        <img src="/o/com.astra.dewa.cms/assets/img/file.svg">
                                    </span>
                                    <span class="text-browse">Pilih File</span>
                                    <input type="file" accept=".xlsx" placeholder="" name="diskonTestCarFile"
                                           id="diskonTestCarFile" data-filename="" data-location="" style="display: none;"
                                           aria-invalid="false" class="materail_input valid">
                                </label>
                                <input type="text" class="form-control required" data-type="file" data-name="" name="diskonTestCarFileName" readonly style="border: 1px solid #DBEDFF; background-color: white">
                                <input type="text" class="dpn" name="diskonTestCarFileId">
                                <input type="hidden" class="dpn" name="diskonTestCarFilePath">
                            </div>
                            <label class="col-form-label text-danger" id="file-restriction-text">
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
        </div>
    </div>
</div>

<script>
    var dealer = "ALL";
    var tahun = "ALL";
    var kuartal = "ALL";
    var tipeKendaraan = "ALL";
    var role = "ALL";
    var data;
    var action = "";
    var homeUrl = "/group/dealink/cms-realisasi-diskon-test-car";

    $(document).ready(function () {
        formNew();
        reloadForm();
        action = "create";
        $("#action-status").html("Buat");
        $("#kelola_d_test_car_navtab")[0].click();
        $('[data-toggle="tooltip"]').tooltip();

        $(".dataTables_length select").select2({
            minimumResultsForSearch: -1,
        });
    });

    function formNew() {
        $('[name="entryId"]')[0].value = "0";
        $('[name="dealerId"]')[0].value = "";
        $('[name="tahun"]')[0].value = "";
        $('[name="kuartalId"]')[0].value = "";
        $('[name="tipeKendaraanId"]')[0].value = "";
        $('[name="diskonTestCarFileName"]')[0].value = "";
        $('[name="diskonTestCarFileId"]')[0].value = "";
        $('[name="diskonTestCarFilePath"]')[0].value = "";
    }

    function formEdit() {
        $('[name="entryId"]')[0].value = data.id;
        $('[name="dealerId"]')[0].value = data.dealerId;
        $('[name="tahun"]')[0].value = data.tahun;
        $('[name="kuartalId"]')[0].value = data.kuartalId;
        $('[name="tipeKendaraanId"]')[0].value = data.tipeKendaraanId;
        $('[name="diskonTestCarFileId"]')[0].value = data.fileId;
        $('[name="diskonTestCarFileName"]')[0].value = data.fileName;
        $('[name="diskonTestCarFilePath"]')[0].value = data.filePath;
    }

    function refresh() {
        dealer = "ALL";
        tahun = "ALL";
        kuartal = "ALL";
        tipeKendaraan = "ALL";
        diskonTestCarDataTable.ajax.reload();
        $("[name=dealerId]")[0].value = "";
        $("[name=tahunId]")[0].value = "";
        $("[name=kuartalId]")[0].value = "";
        $("[name=tipeKendaraanId]")[0].value = "";
    }

    $("#backButtonDocFlow").click(function (e) {
        refreshForm();
    });

    function refreshForm() {
        action = "create";
        $("#kelola_d_test_car_navtab")[0].click();
        $("#delete-file-button").find("a").click();
        $("#action-status").html("Buat");
        $("#upload-status").hide();
        $("#file-restriction-text").show();
        formNew();
        reloadForm();
    }

    function reloadForm() {
        getDealers();
        getTahuns();
        getFileYears();
        getTipeKendaraans();
        getKuartals();
    }

    var languageDiskonFakpolTable = {
        lengthMenu: "_MENU_",
        paginate: {
            first: "",
            last: "",
            next: '<span class="glyphicon glyphicon-menu-right"></span>',
            previous: '<span class="glyphicon glyphicon-menu-left"></span>',
        },
        search: "",
        searchPlaceholder: "Search...",
    };

    var diskonTestCarDataTable = $('#diskon_test_car_table').DataTable({
        searching: false,
        paging: true,
        info: false,
        dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
        ajax: {
            url: '${diskonTestCarListURL}',
            type: 'GET',
            data: function (d) {
                d.dealer = dealer;
                d.tahun = tahun;
                d.kuartal = kuartal
                d.tipeKendaraan = tipeKendaraan;
                d.p_auth = Liferay.authToken;
            },
            // dataSrc: 'Data'
            dataSrc: function (json) {
                return json.Data;
            }
        },
        language: languageDiskonFakpolTable,
        columns: [
            {
                data: "no",
                width: "5%",
                className: "text-center",
                orderable: false
            },
            {data: "fileName", "width": "30%"},
            {data: "tahun", "width": "10%", className: "text-center"},
            {data: "kuartalName", "width": "10%", className: "text-center"},
            {data: "tipeKendaraanName", "width": "10%", className: "text-center"},
            {
                data: "uploadDate",
                width: "30%",
                className: "text-center",
                render: function (data, type, row, meta) {
                    if (type === "sort") {
                        return row.uploadDateSort;
                    }
                    return data;
                }
            },
            {
                data: "id",
                "width": "5%",
                className: "text-center",
                "render": function (data, type, row, meta) {
                    return renderActionButton(data, row);
                },
                orderable: false
            }
        ],
        responsive: true,
        order: [],
        columnDefs: [
            {
                targets: [2],
                orderData: [2, 1]
            },
            {
                targets: [3],
                orderData: [3, 4]
            }
        ]
    });

    function renderActionButton(dataId, row) {
        var response = "";
        response =
            '<span class="action-wrapper" data-id="' + dataId + '">' +
                '<a href="javascript:void(0)" onclick="updateDiskonTestCar(this)" data-id="' + dataId + '">' +
                    '<span><img data-toggle="tooltip" title="Edit" src="/o/com.astra.dewa.cms/assets/img/edit.svg"></img></span>' +
                '</a>' +
                '<a href="javascript:void(0)" onclick="deleteDiskonTestCar(this)" data-id="' + dataId + '">' +
                    '<span><img data-toggle="tooltip" title="Delete" src="/o/com.astra.dewa.cms/assets/img/trash.svg"></img></span>' +
                '</a>' +
            '</span>';
        return response;
    }

    function updateDiskonTestCar(element) {
        action = "update";
        $("#action-status").html("Edit");
        var entryId = $(element).data("id");
        var formData = new FormData();
        formData.append("entryId", entryId);
        createLoading();
        $.ajax({
            url: "${diskonTestCarEditURL}",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                data = JSON.parse(response);
                const oneYearAgo = new Date().getFullYear() - 1;
                if (data.tahun < oneYearAgo) {
                    Swal.fire({
                        icon: "error",
                        title: "Dokumen tidak bisa diedit!",
                        confirmButtonText: "Back",
                        text: "Tidak bisa edit dokumen satu tahun ke belakang.",
                        allowOutsideClick: false,
                    });
                } else {
                    formEdit();
                    reloadForm();
                    $("#create_d_test_car_navtab")[0].click();
                }
            },
            error: function (error) {
                Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: "Something went wrong!",
                });
            },
            complete: function () {
                destroyLoading();
            },
        });
    }

    function deleteDiskonTestCar(element) {
        var entryId = $(element).data("id");
        Swal.fire({
            title: "Apakah anda yakin ingin menghapus data ini?",
            icon: "question",
            showCancelButton: true,
            confirmButtonText: "Yes",
            confirmButtonColor: "#EE1C25",
        }).then((result) => {
            if (result.isConfirmed) {
                var formData = new FormData();
                formData.set("<portlet:namespace/>crudType", "delete");
                formData.set("<portlet:namespace/>entryId", entryId);
                formData.set("<portlet:namespace/>p_auth", Liferay.authToken);

                $.ajax({
                    url: "${diskonTestCarActionURL}",
                    type: "POST",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        var data = JSON.parse(response);
                        if (data["acknowledge"] === 0) {
                            Swal.fire("Successfully delete data", "", "success")
                                .then((res) => {
                                    diskonTestCarDataTable.ajax.reload();
                                });
                        } else {
                            Swal.fire("Failed to Delete", data["message"], "");
                        }
                    },
                    error: function (err) {}
                });
            }
        });
    }

    $("#diskonTestCarFile").change(function (e) {
        const dealer = $('[name="dealerId"]').val();
        const tahun = $('[name="tahun"]').val();
        const kuartalId = $('[name="kuartalId"]').val();
        const tipeKendaraanId = $('[name="tipeKendaraanId"]').val();

        if (dealer === null || dealer === undefined || dealer.length < 1 || dealer === "Pilih Dealer") {
            Swal.fire("Kode Dealer belum terisi.", "", "warning");
        } else if (tahun === null || tahun === undefined || tahun.length < 1 || tahun === "Pilih Tahun") {
            Swal.fire("Tahun Report belum terisi.", "", "warning");
        } else if (kuartalId === null || kuartalId === undefined || kuartalId.length < 1 || kuartalId === "Pilih Kuartal") {
            Swal.fire("Kuartal belum terisi.", "", "warning");
        } else if (tipeKendaraanId === null || tipeKendaraanId === undefined || tipeKendaraanId.length < 1 || tipeKendaraanId === "Pilih Tipe Kendaraan") {
            Swal.fire("Tipe Kendaraan belum terisi.", "", "warning");
        } else {
            processUpload(e, "diskonTestCar", $(this));
            return true;
        }

        $(this).val(null);
    });

    function processUpload(e, name, element) {
        let fileName = e.target.files[0].name;
        let fileSize = e.target.files[0].size;
        if (!(fileName && (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")))) {
            Swal.fire("File harus .xls atau .xlsx.", "", "warning");
        } else if (fileSize > 1048576) {
            Swal.fire("Ukuran file maksimal 1 MB!", "", "warning");
        } else {
            uploadDocumentFile(e.target.files[0], fileName, $('[name="' + name + 'FileId"]')[0].value, element, name);
            return true;
        }
        $("#diskonTestCarFile").val(null);
    }

    function uploadDocumentFile(file, fileName, fileID, element, name) {
        let formData = new FormData();
        let format = /[:"\\|,<>\/?^*]/;
        if (format.test(fileName)) {
            swal.fire("", 'File name cannot contains special character ,^*:"|<>\\ /?', "warning");
        } else {
            fileName = fileName.replaceAll(",", "");
            formData.append("authToken", Liferay.authToken);
            formData.append("file-upload", file);
            formData.append("file-name", fileName);
            formData.append("file-id", fileID);
            formData.append("document-id", $('[name="document-id"]').val());
            formData.append("document-id-new", $('[name="document-id-new"]').val());
            formData.append("dealer", $('[name="dealerId"]').val());
            formData.append("tahun", $('[name="tahun"]').val());
            formData.append("kuartalId", $('[name="kuartalId"]').val());
            formData.append("tipeKendaraanName", $('[name="tipeKendaraanName"]').val());

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
                url: "${uploadFileDiskonTestCarCommandURL}",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                xhr: function () {
                    var xhr = new XMLHttpRequest();

                    xhr.upload.addEventListener("progress", function (e) {
                        if (e.lengthComputable) {
                            var uploadPercent = e.loaded / e.total;
                            uploadPercent = uploadPercent * 100;

                            if (parseInt(uploadPercent) === 100) {
                                htmlLoading.find("#progress-percentage").text("Silahkan tunggu...");
                            } else {
                                htmlLoading.find("#progress-percentage").text(parseInt(uploadPercent) + "% uploaded");
                            }
                            htmlLoading.find(".progress-bar").width(uploadPercent + "%");
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
                        $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileId"]'))[0].value = fileID;
                        $(elementEdit.parent().siblings('[name="' + nameEdit + 'FileName"]'))[0].value = fileName;
                        $(elementEdit.parent().siblings('[name="' + nameEdit + 'FilePath"]'))[0].value = fileURL;

                        let successHTML = `
                            <div id="upload-status" style="margin-top: -8px;margin-bottom: 15px;padding: 0 15px;border: 1px solid #ddd;border-radius: 5px; display: flex; align-items: center; justify-content: space-between;">
                                <div>
                                    <p style="font-size: 14px;color: green;font-weight: 600; margin-top: 15px">Upload file berhasil </p>
                                </div>
                                <div>
                                    <div id="delete-file-button">
                                        <a href="javascript:void(0);" onclick="deleteUploadedFile(this, ` + fileID + `)" title="Delete File"><i class="fas fa-trash"></i></a>
                                    </div>
                                    <div id="delete-loader" style="display: none;">
                                        <i class="fas fa-spinner anim-rotate"></i>
                                    </div>
                                </div>
                            </div>`;

                        setTimeout(function () {
                            htmlLoading.empty();
                            htmlLoading.append(successHTML);
                        }, 2000);
                    } else {
                        swal.fire({
                            icon: "error",
                            title: "Failed to upload file"
                        });
                    }
                },
                error: function (e) {
                    swal.fire({
                        icon: "error",
                        title: "Failed to upload file"
                    });
                },
                complete: function () {
                    $('#file-restriction-text').hide();
                }
            });
        }
    }

    function deleteUploadedFile(element, fileID) {
        let parentElement1 = $(element).parents(".upload-content");
        parentElement1.find("#delete-file-button").css("display", "none");
        parentElement1.find("#delete-loader").css("display", "inherit");

        let formData = new FormData();
        formData.append("authToken", Liferay.authToken);
        formData.append("file-id", fileID);
        let parentElement = element.parentElement.parentElement.parentElement;
        let inputElement = $(parentElement1.siblings().find("input.form-control"))[0];

        $.ajax({
            url: "${deleteFileDiskonTestCarCommandURL}",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                let data = JSON.parse(response);
                if (data["acknowledge"] === 1) {
                    parentElement.remove();

                    parentElement1.remove();
                    inputElement.value = "";
                } else {
                    parentElement1.find("#delete-file-button").css("display", "inherit");
                    parentElement1.find("#delete-loader").css("display", "none");

                    parentElement.find("[name=file-status]").text("delete failed");
                }
            },
            error: function (e) {
                swal({
                    icon: "error",
                    title: "Failed to delete file"
                });
            },
            complete: function () {
                $('[name="diskonTestCarFileId"]')[0].value = null;
                $('#file-restriction-text').show();
            }
        });
    }

    var submitProcess = false;
    $("#formDiskonTestCar").submit(function (e) {
        e.preventDefault();
        const dealer = $('[name="dealerId"]').val();
        const tahun = $('[name="tahun"]').val();
        const kuartalId = $('[name="kuartalId"]').val();
        const tipeKendaraanId = $('[name="tipeKendaraanId"]').val();
        const diskonTestCarFileId = $('[name="diskonTestCarFileId"]').val();

        if (submitProcess === false) {
            if (dealer === null || dealer === undefined || dealer.length < 1 || dealer === "Pilih Dealer") {
                Swal.fire("Kode Dealer belum terisi.", "", "warning");
            } else if (tahun === null || tahun === undefined || tahun.length < 1 || tahun === "Pilih Tahun") {
                Swal.fire("Tahun belum terisi.", "", "warning");
            } else if (kuartalId === null || kuartalId === undefined || kuartalId.length < 1 || kuartalId === "Pilih Kuartal") {
                Swal.fire("Kuartal belum terisi.", "", "warning");
            } else if (tipeKendaraanId === null || tipeKendaraanId === undefined || tipeKendaraanId.length < 1 || tipeKendaraanId === "Pilih Tipe Kendaraan") {
                Swal.fire("Tipe Kendaraan belum terisi.", "", "warning");
            } else if (diskonTestCarFileId === null || diskonTestCarFileId === undefined || diskonTestCarFileId.length < 1) {
                Swal.fire("File Report belum terisi.", "", "warning");
            } else {
                var formData = new FormData();
                formData.set("<portlet:namespace/>crudType", action);
                formData.set("<portlet:namespace/>p_auth", Liferay.authToken);
                formData.set("<portlet:namespace/>entryId", $('[name="entryId"]').val());
                formData.set("<portlet:namespace/>dealer", dealer);
                formData.set("<portlet:namespace/>tahun", tahun);
                formData.set("<portlet:namespace/>kuartalId", kuartalId);
                formData.set("<portlet:namespace/>tipeKendaraanId", tipeKendaraanId);
                formData.set("<portlet:namespace/>diskonTestCarFileId", diskonTestCarFileId);
                formData.set("<portlet:namespace/>diskonTestCarFileName", $('[name="diskonTestCarFileName"]').val());
                formData.set("<portlet:namespace/>diskonTestCarFilePath", $('[name="diskonTestCarFilePath"]').val());

                Swal.fire({
                    title: "Apakah data ini sudah benar?",
                    icon: "question",
                    showCancelButton: true,
                    confirmButtonText: "Yes",
                }).then((result) => {
                    if (result.isConfirmed) {
                        createLoading();
                        $.ajax({
                            url: "${diskonTestCarActionURL}",
                            type: "POST",
                            data: formData,
                            processData: false,
                            contentType: false,
                            success: function (response) {
                                submitProcess = true;
                                var status = JSON.parse(response).status;
                                var code = JSON.parse(response).code;
                                var message = JSON.parse(response).message;
                                if (status === "success") {
                                    swal.fire("Success!", message, "success")
                                        .then(function () {
                                                window.location = homeUrl;

                                        });
                                } else if (status === "warning") {
                                    swal.fire("Sorry", message, "warning");
                                } else {
                                    if (code === 409) {
                                        swal.fire("Sorry", message, "error");
                                    } else {
                                        swal.fire("Sorry", "There is an internal error", "error");
                                    }
                                }
                            },
                            error: function (data, textStatus, XMLHttpRequest) {
                                if (data.status === 500) {
                                    var msg = data.responseJSON.Message;
                                    swal("Sorry", msg, "error");
                                } else if (data.status === 408) {
                                    swal("Sorry", "Request Time Out, Please Try Again", "error");
                                } else {
                                    swal("Sorry", textStatus + "error submit", "error");
                                }
                            },
                            complete: function (jqXHR, textStatus) {
                                submitProcess = false;
                                destroyLoading();
                            },
                        });
                    }
                });
            }
        }
    });

    function getDealers() {
        $.ajax({
            url: "${dealerDiskonTestCarURL}",
            type: "POST",
            data: {},
            success: function (response) {
                var responseData = JSON.parse(response);

                var viewDealerConst = $("#viewDealerId").select2({
                    data: responseData.Data,
                    tags: "true",
                    placeholder: "Pilih Dealer",
                    allowClear: false,
                    maximumInputLength: 100,
                    templateSelection: function (data) {
                        if (data.id !== "") {
                            dealer = data.id;
                            diskonTestCarDataTable.ajax.reload();
                        }
                        return data.text;},
                    })
                .tooltip("disable");
                viewDealerConst.val(null);
                viewDealerConst.trigger("change");

                var dealerConst = $('#dealerId').select2({
                    data: responseData.Data,
                    tags: "true",
                    placeholder: 'Pilih Dealer',
                    allowClear: false,
                    maximumInputLength: 100,
                    templateSelection: function (data) {
                        $('[name="dealerId"]')[0].value = data.id;
                        if (data.id === 'Pilih Dealer') {
                            return 'Pilih Dealer';
                        }
                        return data.text;
                    }
                }).tooltip('disable');
                if (action === "update") {
                    dealerConst.val(data.dealerId);
                } else {
                    dealerConst.val(null);
                }
                dealerConst.trigger('change');
            },
            error: function (error) {},
            complete: function () {}
        });
    }

    function getTahuns() {
        $.ajax({
            url: "${tahunDiskonTestCarURL}",
            type: "POST",
            data: {},
            success: function (response) {
                var tahunData = JSON.parse(response);

                var tahunConst = $('#tahunId').select2({
                    data: tahunData.Data,
                    tags: "true",
                    placeholder: 'Pilih Tahun',
                    allowClear: false,
                    maximumInputLength: 100,
                    templateSelection: function (data) {
                        $('[name="tahun"]')[0].value = data.text;
                        if (data.id === 'Pilih Tahun') {
                            return 'Pilih Tahun';
                        }
                        return data.text;
                    }
                }).tooltip('disable');
                if (action === "update") {
                    tahunConst.val(data.tahun);
                } else {
                    tahunConst.val(null);
                }
                tahunConst.trigger('change');
            },
            error: function (error) {},
            complete: function () {}
        });
    }

    function getFileYears() {
        $.ajax({
            url: "${tahunDiskonTestCarFileURL}",
            type: "POST",
            data: {},
            success: function (response) {
                var tahunData = JSON.parse(response);

                var viewTahunConst = $('#viewTahunId').select2({
                    data: tahunData.Data,
                    tags: "true",
                    placeholder: 'Pilih Tahun',
                    allowClear: false,
                    maximumInputLength: 100,
                    templateSelection: function (data) {
                        if (data.id !== '') {
                            tahun = data.id;
                            diskonTestCarDataTable.ajax.reload();
                        }
                        return data.text;
                    }
                }).tooltip('disable');
                viewTahunConst.val(null);
                viewTahunConst.trigger('change');
            },
            error: function (error) {},
            complete: function () {}
        });
    }

    function getTipeKendaraans() {
        $.ajax({
            url: "${tipeKendaraanDiskonTestCarURL}",
            type: "POST",
            data: {},
            success: function (response) {
                var tipeKendaraanData = JSON.parse(response);

                var viewTipeKendaraanConst = $("#viewTipeKendaraanId").select2({
                    data: tipeKendaraanData.Data,
                    tags: "true",
                    placeholder: "Pilih Tipe Kendaraan",
                    allowClear: false,
                    maximumInputLength: 100,
                    templateSelection: function (data) {
                        if (data.id !== "") {
                            tipeKendaraan = data.id;
                            diskonTestCarDataTable.ajax.reload();
                        }
                        return data.text;},
                    })
                .tooltip("disable");
                viewTipeKendaraanConst.val(null);
                viewTipeKendaraanConst.trigger("change");

                var tipeKendaraanConst = $('#tipeKendaraanId').select2({
                    data: tipeKendaraanData.Data,
                    tags: "true",
                    placeholder: 'Pilih Tipe Kendaraan',
                    allowClear: false,
                    maximumInputLength: 100,
                    templateSelection: function (data) {
                        $('[name="tipeKendaraanId"]')[0].value = data.id;
                        $('[name="tipeKendaraanName"]')[0].value = data.text;
                        if (data.id === 'Pilih Tipe Kendaraan') {
                            return 'Pilih Tipe Kendaraan';
                        }
                        return data.text;
                    }
                }).tooltip('disable');
                if (action === "update") {
                    tipeKendaraanConst.val(data.tipeKendaraanId);
                } else {
                    tipeKendaraanConst.val(null);
                }
                tipeKendaraanConst.trigger('change');
            },
            error: function (error) {},
            complete: function () {}
        });
    }

    function getKuartals() {
        $.ajax({
            url: "${kuartalDiskonTestCarURL}",
            type: "POST",
            data: {},
            success: function (response) {
                var kuartalData = JSON.parse(response);

                var viewKuartalConst = $("#viewKuartalId").select2({
                    data: kuartalData.Data,
                    tags: "true",
                    placeholder: "Pilih Kuartal",
                    allowClear: false,
                    maximumInputLength: 100,
                    templateSelection: function (data) {
                        if (data.id !== "") {
                            kuartal = data.id;
                            diskonTestCarDataTable.ajax.reload();
                        }
                        return data.text;},
                    })
                .tooltip("disable");
                viewKuartalConst.val(null);
                viewKuartalConst.trigger("change");

                var kuartalConst = $('#kuartalId').select2({
                    data: kuartalData.Data,
                    tags: "true",
                    placeholder: 'Pilih Kuartal',
                    allowClear: false,
                    maximumInputLength: 100,
                    templateSelection: function (data) {
                        $('[name="kuartalId"]')[0].value = data.id;
                        $('[name="kuartalName"]')[0].value = data.text;
                        if (data.id === 'Pilih Kuartal') {
                            return 'Pilih Kuartal';
                        }
                        return data.text;
                    }
                }).tooltip('disable');
                if (action === "update") {
                    kuartalConst.val(data.kuartalId);
                } else {
                    kuartalConst.val(null);
                }
                kuartalConst.trigger('change');
            },
            error: function (error) {},
            complete: function () {}
        });
    }
</script>