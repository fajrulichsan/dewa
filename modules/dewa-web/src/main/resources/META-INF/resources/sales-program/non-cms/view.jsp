<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/non-cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/sales-program/non-cms/style-css.jsp" %>

<portlet:resourceURL id="/sales-program-noncms-list" var="salesProgramNonCMSListURL"/>
<portlet:resourceURL id="/sales-program-noncms-action" var="salesProgramNonCMSActionURL"/>
<portlet:resourceURL id="/download-sales-program-noncms" var="downloadSalesProgramNonCMSActionURL">
    <portlet:param name="entryId" value="0"/>
</portlet:resourceURL>
<portlet:resourceURL id="/tahun-sales-program-noncms" var="tahunSalesProgramNonCMSURL"/>
<portlet:resourceURL id="/bulan-sales-program-noncms" var="bulanSalesProgramNonCMSURL"/>

<div class="sales_program_nonCMS non-cms-menu">
    <ol class="breadcrumb">
        <li>Home</li>
        <li>Download</li>
        <li>Unit</li>
        <li>Sales Program</li>
    </ol>

    <div class="main-title">
        <h3>Sales Program</h3>
    </div>

    <div class="row table-filters">
        <div class="col-xs-12 col-md-6 row sub-filters">
            <div class="col-xs-6">
                <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
                    <option value="ALL">Select All</option>
                </select>
            </div>
            <div class="col-xs-6">
                <select class="form-control bulanId" name="bulanId" id="bulanId" style="width: 100%;">
                    <option value="ALL">Select All</option>
                    <option value="Januari">Januari</option>
                    <option value="Februari">Februari</option>
                    <option value="Maret">Maret</option>
                    <option value="April">April</option>
                    <option value="Mei">Mei</option>
                    <option value="Juni">Juni</option>
                    <option value="Juli">Juli</option>
                    <option value="Agustus">Agustus</option>
                    <option value="September">September</option>
                    <option value="Oktober">Oktober</option>
                    <option value="November">November</option>
                    <option value="Desember">Desember</option>
                </select>
            </div>
        </div>
    </div>
    <div class="tabcontent">
        <table id="sales_program_nonCMS_table" class="table table-hover nowrap row-border non-cms-table" style="width:100%">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Sales Program</th>
                    <th>Periode</th>
                    <th>Tahun</th>
                    <th>Tanggal Upload</th>
                    <th>Aksi</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
</div>

<script>
    var tahun = "ALL";
    var bulan = "ALL";

    $(document).ready(function () {
        getTahuns();
        getBulans();
        $('.dataTables_length select').select2({
            minimumResultsForSearch: -1
        });
    });

    var tableLanguage = {
        "lengthMenu": "_MENU_",
        "paginate": {
            "first": "",
            "last": "",
            "next": '<span class="glyphicon glyphicon-menu-right"></span>',
            "previous": '<span class="glyphicon glyphicon-menu-left"></span>'
        },
        "search": "",
        "searchPlaceholder": "Search..."
    }

    var salesProgramDataTable = $('#sales_program_nonCMS_table').DataTable({
        searching: false,
        paging: true,
        responsive: true,
        info: false,
        language: tableLanguage,
        dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',
        ajax: {
            url: '${salesProgramNonCMSListURL}',
            type: 'GET',
            data: function(d){
                d.tahun =  tahun;
                d.periode =  bulan;
            },
            dataSrc: function (json) {
                return json.Data;
            }
        },
        columns: [
            {data: "no", "width": "5%", className: "text-center"},
            {data: "fileName", "width": "25%"},
            {
                data: "periodeNumber",
                "width": "15%",
                className: "text-center",
                "render": function (data, type, row) {
                    if(type === "display"){
                        return row.periode;
                    }
                    return data;
                },
            },
            {data: "yearFile", "width": "15%", className: "text-center"},
            {
                data: "dateFile",
                "width": "15%",
                className: "text-center",
                "render": function (data, type, row, meta) {
                    if(type === "sort"){
                        return row.dateSorting;
                    }
                    return data;
                },
            },
            {
                data: "id",
                "width": "7%",
                className: "text-center",
                "render": function (data, type, row, meta) {
                    return renderActionButton(data);
                },
                orderable: false
            }
        ],
        order: [],
        columnDefs : [
            { targets : [2],
                orderData : [2,3]
            }
        ]
    });

    function renderActionButton(dataId) {
        var downloadURL = new URL("${downloadSalesProgramNonCMSActionURL}");
        downloadURL.searchParams.set("<portlet:namespace/>entryId", dataId);
        var response = "";
        response =
            '<span class="action-wrapper" data-id="' + dataId + '">' +
            '<a target="_blank" href="'+downloadURL.toString()+'" style="margin-right: 5px;" class="text-center">' +
            'Unduh Data' +
            '</a>' +
            '</span>';
        return response;
    }


    function getTahuns() {
        $.ajax({
            url: "${tahunSalesProgramNonCMSURL}",
            type: "POST",
            data: {"windowsId": "test"},
            success: function (response) {
                var tahunData = JSON.parse(response);
                const tahunConst = $('#tahunId').select2({
                    data: tahunData.Data,
                    placeholder: 'Pilih Tahun',
                    allowClear: false,
                    maximumInputLength: 100,
                    templateSelection: function (data) {
                        if(data.id !== '') {
                            tahun = data.id;
                            salesProgramDataTable.ajax.reload();
                        }
                        return data.text;
                    }
                });
                tahunConst.val(null);
                tahunConst.trigger('change');
            },
            error: function (error) {
                console.log(error);
            },
            complete: function (){
                console.log("complete");
            }
        });
    }

    function getBulans() {
        $.ajax({
            url: "${bulanSalesProgramNonCMSURL}",
            type: "POST",
            data: {"windowsId": "test"},
            success: function (response) {
                var bulanData = JSON.parse(response);
                var bulanConst = $('.bulanId').select2({
                    data: bulanData.Data,
                    placeholder: 'Pilih Periode',
                    allowClear: false,
                    maximumInputLength: 100,
                    templateSelection: function (data) {
                        if(data.id !== '') {
                            bulan = data.id;
                            salesProgramDataTable.ajax.reload();
                        }
                        return data.text;
                    }
                });
                console.log(bulanConst)
                bulanConst.val(null);
                bulanConst.trigger('change');
            },
            error: function (error) {
                console.log(error);
            },
            complete: function () {
                console.log("complete");
            }
        });
    }

    //comment

</script>