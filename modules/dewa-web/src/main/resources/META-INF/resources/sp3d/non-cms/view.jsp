<%@ include file="/META-INF/resources/init.jsp" %>
<%@ include file="/META-INF/resources/general/non-cms/general-css.jsp" %>
<%@ include file="/META-INF/resources/sp3d/non-cms/style-css.jsp" %>

<portlet:resourceURL id="/sp3d-non-cms-list" var="sp3dNonCMSListURL"/>
<portlet:resourceURL id="/sp3d-non-cms-action" var="sp3dNonCMSActionURL"/>
<portlet:resourceURL id="/tahun-sp3d-non-cms" var="tahunSp3dNonCMSURL"/>
<portlet:resourceURL id="/download-sp3d-non-cms" var="downloadSp3dNonCMSActionURL">
    <portlet:param name="entryId" value="0"/>
</portlet:resourceURL>
<portlet:resourceURL id="/upload-file-sp3d-non-cms" var="uploadSp3dURL" />

<div class="sp3d non-cms-menu">
    <ol class="breadcrumb">
        <li>Home</li>
        <li>Download</li>
        <li>SK</li>
        <li>SP3D</li>
    </ol>

    <div class="main-title">
        <h3>SP3D</h3>
    </div>

    <div class="row table-filters">
        <div class="col-xs-12 col-md-3">
            <select class="form-control" name="tahunId" id="tahunId" style="width: 100%;">
                <option value="ALL">Select All</option>
            </select>
        </div>
    </div>

    <div class="tabcontent">
        <table id="sp3d_table" class="table table-hover nowrap row-border non-cms-table" style="width:100%">
            <thead>
                <tr>
                    <th>No</th>
                    <th>SP3D</th>
                    <th>Periode</th>
                    <th>Nama Dealer</th>
                    <th>Tanggal Upload</th>
                    <th>Keterangan</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
</div>

<script>
    var tahun = "ALL";
    var role = "ALL";

    $(document).ready(function () {
        getTahuns();
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

    var sp3dDataTable = $('#sp3d_table').DataTable({
        searching: false,
        paging: true,
        info: false,
        language: tableLanguage,
        dom: '<"row"<"col-sm-12 display-filters"l><"col-sm-12"t>r<"col-sm-12 text-center"p>>',

        // serverSide: true,
        ajax: {
            url: '${sp3dNonCMSListURL}',
            type: 'GET',
            data: function(d){
                d.tahun =  tahun;
            },
            // dataSrc: 'Data'
            dataSrc: function (json) {
                role = json.role;
                if(role === 'Dealer') {
                    // $( ".dealerId" ).hide();
                    dealerCode = json.codeDealer;
                    dealerName = json.nameDealer;
                    // $('[name="dealerName"]')[0].value = dealerCode + ' - ' + dealerName;
                } else {
                    // $( ".dealerName" ).hide();
                }
                if(role !== 'DSO') {
                    // $( ".roleDso" ).hide();
                }
                // getCard(data);
                return json.Data;
            }
        },
        columns: [
            {data: "no", "width": "5%", className: "text-center"},
            {data: "fileName", "width": "15%"},
            {data: "tahun", "width": "15%",className: "text-center"},
            {data: "dealerName","width": "30%",className: "text-center"},
            {
                data: "uploadDate", "width":"25%",
                className: "text-center",
                "render": function (data, type, row, meta) {
                    if(type === "sort"){
                        return row.uploadDateSort;
                    }
                    return data;
                },
            },
            {
                data: "id",
                "width": "65%",
                className: "text-center",
                "render": function (data, type, row, meta) {
                    return renderActionButton(data, row);
                },
                orderable: false
            }
        ],
        responsive: true,
        order: []
    });

    function renderActionButton(dataId) {
        var downloadURL = new URL("${downloadSp3dNonCMSActionURL}");
        downloadURL.searchParams.set("<portlet:namespace/>entryId", dataId);
        var response = "";
        response =
            '<span class="action-wrapper" data-id="' + dataId + '">' +
                '<a target="_blank" href="'+downloadURL.toString()+'" class="text-center">' +
                    'Unduh Data' +
                '</a>' +
            '</span>';
        return response;
    }

    function getTahuns() {
        $.ajax({
            url: "${tahunSp3dNonCMSURL}",
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
                            sp3dDataTable.ajax.reload();
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

</script>