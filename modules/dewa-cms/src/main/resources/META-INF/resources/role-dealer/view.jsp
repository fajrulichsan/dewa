<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/user-dealer-list" var="userDealerListURL"/>
<portlet:resourceURL id="/role-dealer-action" var="roleDealerActionURL"/>
<portlet:resourceURL id="/cms/dealer-roles" var="dealerRoleListURL"/>
<portlet:renderURL var="roleDealerEditURL">
    <portlet:param name="mvcRenderCommandName" value="/role-dealer-edit"/>
</portlet:renderURL>

<style>
    .role_dealer {
        margin: 25px;
    }

    .role_dealer > .tabcontent {
        box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
        border-radius: 10px;
        padding: 0.75em;
    }

    #role_dealer_table > thead {
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

    table#role_dealer_table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#role_dealer_table {
        border-radius: 10px 10px 10px 10px;
    }

    #role_dealer_table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #role_dealer_table > thead > tr > th {
        font-weight: normal;
        text-align: center;
    }

    #approval_table_filter > label > input {
        background-image: url(<%=request.getContextPath()%>/assets/img/search.svg);
        background-position: 10px 8px;
        background-repeat: no-repeat;
        padding: 5px 20px 5px 40px;
    }

    .btn {
        height: 40px;
    }

    .dataTables_length {
        width: 100%;
        display: flex;
    }

    .dataTables_length label {
        margin-right: 10px;
    }

    .input-filter {
        margin-left: 0 !important;
    }

    .btn_table, .btn_table:hover {
        max-height: 40px;
        margin-right: 10px;
        background-color: #014689 !important;
        color: white;
        font-weight: 400;
        border: 0px;
        border-radius: 5px;
        padding: 0 16px;
        opacity: 0.8;
        display: inline-flex;
        align-items: center;
    }

    .form-control {
        height: 40px !important;
        border: 2px solid #DBEDFF;
    }

    /* Select2 Begin */
    .dataTables_length + .select2-container--default .select2-selection--single {
        color: #999 !important;
    }

    .select2-container--default .select2-selection--single {
        border: 2px solid #dbedff;
        margin-top: 0;
        height: 40px;
        padding: 6px;
        font-size: 1.2em;
        position: relative;
    }
    /* Select2 End */
</style>

<div class="role_dealer">
    <h3 style="font-size: 24px; font-weight: 700; margin-bottom: 1em;">User Management</h3>
    <div class="tabcontent">
        <table id="role_dealer_table" class="table table-hover nowrap" style="width:100%">
            <thead>
            <tr>
                <th style="text-align: center;"></th>
                <th>No</th>
                <th>Kode HO</th>
                <th>Nama Lengkap</th>
                <th>Email</th>
                <th>Nama Dealer</th>
                <th>Cabang</th>
                <th>Tgl. Registrasi</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
</div>

<script>
    var roleDealerDataTable;
    var roleId = "ALL";
    var column = "ALL";
    var keyword = "ALL";
    var loginUrl = "<%=themeDisplay.getPortalURL()%>/group/dealink/home";
    var isAdminDivision = false;
    var dataRoleDealers = [];

    function refresh() {
        column = 'ALL';
        keyword = 'ALL';
        getRoleDealers();
    }

    var languageRoleDealerTable = {
        "lengthMenu": "_MENU_",
        "paginate": {
            "first": "",
            "last": "",
            "next": '<span class="glyphicon glyphicon-menu-right"></span>',
            "previous": '<span class="glyphicon glyphicon-menu-left"></span>'
        },
        "search": "",
        searchPlaceholder: "Search...",
        infoFiltered: ""
    }

    function getRoleDealers() {
        roleDealerDataTable = $('#role_dealer_table').DataTable({
            destroy: true,
            processing: true,
            serverSide: true,
            paging: true,
            info: true,
            searchDelay: 1000,
            searching: true,
            ordering: true,
            autoWidth: true,
            responsive: true,
            order: [],
            fixedHeader: true,
            language: languageRoleDealerTable,
            columnDefs: [
                {
                    targets: 0,
                    checkboxes: { selectRow: false }
                }
            ],
            ajax: {
                url: '${userDealerListURL}',
                type: 'GET',
                data: function (d) {
                    d.roleId = roleId;
                    d.column = column;
                    d.keyword = keyword;
                    d.p_auth = Liferay.authToken;
                },
                dataSrc: function (json) {
                    destroyLoading();
                    isAdminDivision = json.isAdminDivision;
                    dataRoleDealers = json.data;
                    return json.data;
                }
            },
            columns: [
                {
                    data: "id",
                    width: "9",
                    targets: 0,
                    searchable: false,
                    orderable: false,
                    className: 'text-center',
                },
                {
                    data: "no", "width": "5",
                    className: 'text-center',
                    orderable: false
                },
                {
                    data: "kodeHo",
                    width: "10",
                    className: 'text-center'
                },
                {
                    data: "fullName",
                    width: "20",
                    render: $.fn.dataTable.render.ellipsis(15)
                },
                {
                    data: "userEmail",
                    width: "25",
                    render: $.fn.dataTable.render.ellipsis(15)
                },
                {
                    data: "dealerName",
                    width: "30",
                    className: 'text-center',
                    render: $.fn.dataTable.render.ellipsis(15)
                },
                {
                    data: "cabangName",
                    width: "30",
                    className: 'text-center',
                    render: $.fn.dataTable.render.ellipsis(15)
                },
                {
                    data: "approvedDate",
                    width: "30",
                    className: "text-center",
                    render: function (data, type, row, meta) {
                        if (type === "sort") {
                            return row.dateApprovSorting;
                        }
                        return data;
                    },
                },
                {
                    data: "id",
                    width: "7",
                    className: "text-center",
                    render: function (data, type, row, meta) {
                        return renderActionButton(data);
                    },
                    orderable: false
                }
            ],
            initComplete: function () {
                getRoles();
                var searchTimeout = null;
                $(".dataTables_filter input", $('#role_dealer_table_wrapper'))
                    .unbind()
                    .bind("input", function (e) {
                        clearTimeout(searchTimeout);
                        var param = this.value;
                        var keyCode = e.keyCode;
                        searchTimeout = setTimeout(function () {
                            if (param.length >= 3 || keyCode === 13) {
                                roleDealerDataTable.search(param).draw();
                            }
                            if (param.length === 0) {
                                roleDealerDataTable.search("").draw();
                            }
                        }, 1000);
                    });
                $(".dataTables_filter input").addClass('input-filter');
                var $roleSelect = $('<div class="role-filter-container"><select class="role-filter"><option value="ALL">Pilih Role</option></select></div>');

                $('.dataTables_length').append('<a href="javascript:void(0)" class="btn btn-info" style="margin-right: 10px;" onclick="getChecks()">Download</a>');

                if (isAdminDivision === false) {
                    $('.dataTables_length').append('<a href="javascript:void(0)" class="btn btn-danger" style="margin-right: 10px;" onclick="deleteList()">Delete</a>');
                    $('.dataTables_length').append($roleSelect);
                    $('.dataTables_filter').prepend('<a href="${roleDealerEditURL}&id=0" class="btn btn-info btn_table"><span><i class="fas fa-plus" style="margin-right: 10px"></i> Tambah </span></a>');
                }
            }
        });
    }

    $('#role_dealer_table').on('error.dt', function (e, settings, techNote, message) {
        Swal.fire('Oops! Something went wrong', '', 'warning');
    }).DataTable();

    function renderActionButton(dataId) {
        var response = "";
        if (isAdminDivision) {
            response =
                '<a href="${roleDealerEditURL}&id=' + dataId + '" style="margin-right: 5px;">' +
                    '<span><img data-toggle="tooltip" title="View" src="<%=request.getContextPath()%>/assets/img/eye.svg"></img></span>' +
                '</a>';
        } else {
            response =
                '<span class="action-wrapper" data-id="' + dataId + '" style="text-decoration: none;">' +
                    '<a href="${roleDealerEditURL}&id=' + dataId + '" style="margin-right: 5px;">' +
                        '<span><img data-toggle="tooltip" title="Edit" src="<%=request.getContextPath()%>/assets/img/edit.svg"></img></span>' +
                    '</a>' +
                    '<a href="javascript:void(0)" onclick="deleteData(this)" data-id="' + dataId + '">' +
                        '<span><img data-toggle="tooltip" title="Hapus" src="<%=request.getContextPath()%>/assets/img/trash.svg"></img></span>' +
                    '</a>' +
                '</span>';
        }
        return response;
    }

    function deleteData(element) {
        var entryId = $(element).data("id");
        Swal.fire({
            title: 'Do you want to delete the data?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Yes',
            confirmButtonColor: '#EE1C25',
        }).then((result) => {
            if (result.isConfirmed) {
                createLoading();
                var formData = new FormData();
                formData.set('<portlet:namespace/>p_auth', Liferay.authToken);
                formData.set('<portlet:namespace/>crudType', 'delete');
                formData.set('<portlet:namespace/>entryId', entryId);

                $.ajax({
                    url: "${roleDealerActionURL}",
                    type: "POST",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        destroyLoading();
                        var data = JSON.parse(response);
                        if (data.code === 302) {
                            Swal.fire('Success', data.message, 'success')
                                .then(function () {
                                    $.ajax({
                                        url: '/c/portal/logout',
                                        method: 'POST',
                                        success: function () {
                                            window.location.href = loginUrl;
                                        },
                                        error: function (xhr, status, error) {}
                                    });
                                });
                        } else if (data["acknowledge"] === 0) {
                            Swal.fire("Failed", data["message"], "warning");
                        } else {
                            Swal.fire("Success", data["message"], "success")
                                .then((res) => {
                                    getRoleDealers();
                                });
                        }
                    },
                    error: function (err) {}
                });
            }
        });
    }

    function getRoleDealer(array, id) {
        var obj = array.filter(function (val) { return val.id === id; });
        return obj[0];
    }

    function getChecks() {
        var roleDealers = [];
        var rows_selected = roleDealerDataTable.column(0).checkboxes.selected();
        $.each(rows_selected, function (index, rowValue) {
            var roleDealer = getRoleDealer(dataRoleDealers, Number(rowValue));
            var roles = roleDealer.roles.map(item => item.name).join(", ");
            roleDealers.push(
                {
                    kodeHo: roleDealer.kodeHo,
                    fullName: roleDealer.fullName,
                    userEmail: roleDealer.userEmail,
                    roles: roles,
                    dealerName: roleDealer.dealerName,
                    cabangName: roleDealer.cabangName
                }
            )
        });
        createExels(roleDealers);
    }

    function createExels(roleDealers) {
        if (roleDealers.length < 1) {
            Swal.fire("Info", "Data belum terpilih.", "");
            return;
        }
        const workbook = new ExcelJS.Workbook();
        workbook.creator = 'DEALINK';
        workbook.lastModifiedBy = 'DEALINK';
        workbook.created = new Date();
        workbook.modified = new Date();
        workbook.lastPrinted = new Date();

        const worksheet = workbook.addWorksheet('New Sheet');
        worksheet.columns = [
            {header: 'Kode HO', key: 'kodeHo'},
            {header: 'Nama Lengkap', key: 'fullName'},
            {header: 'Email', key: 'userEmail'},
            {header: 'Roles', key: 'roles'},
            {header: 'Nama Dealer', key: 'dealerName'},
            {header: 'Cabang', key: 'cabangName'},
        ];
        // worksheet.addRow({id: 1, name: 'John Doe', age: 35});
        worksheet.addRows(roleDealers);

        // Set footer (default centered), result: "Page 2 of 16"
        worksheet.headerFooter.oddFooter = "Page &P of &N";

        workbook.xlsx.writeBuffer().then((xData) => {
            const blob = new Blob([xData], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
            saveAs(blob, "User Management" + ".xlsx");
        });
    }

    function deleteList() {
        var rows_selected = roleDealerDataTable.column(0).checkboxes.selected();
        if (rows_selected.count() === 0) {
            Swal.fire('Tidak ada data yang dipilih', '', 'warning');
        } else {
            Swal.fire({
                title: 'Do you want to delete the data?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Yes',
                confirmButtonColor: '#EE1C25',
            }).then((result) => {
                if (result.isConfirmed) {
                    createLoading();
                    var roleDealers = [];

                    $.each(rows_selected, function (index, rowValue) {
                        var roleDealer = getRoleDealer(dataRoleDealers, Number(rowValue));
                        roleDealers[index] = roleDealer.id;
                    });

                    var formData = new FormData();
                    formData.set('<portlet:namespace/>p_auth', Liferay.authToken);
                    formData.set('<portlet:namespace/>crudType', 'DELETE_LIST');
                    formData.set('<portlet:namespace/>entryList', roleDealers);

                    $.ajax({
                        url: "${roleDealerActionURL}",
                        type: "POST",
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function (response) {
                            destroyLoading();
                            var data = JSON.parse(response);
                            if (data.code === 302) {
                                Swal.fire('Success', data.message, data.status)
                                    .then(function () {
                                        $.ajax({
                                            url: "/c/portal/logout",
                                            method: 'POST',
                                            success: function () {
                                                window.location.href = loginUrl;
                                            },
                                            error: function (xhr, status, error) {}
                                        });
                                    });
                            } else if (data.code === 401) {
                                Swal.fire('Unauthorized request', '', 'error')
                            } else if (data["acknowledge"] === 0) {
                                Swal.fire("Failed", data["message"], "warning");
                            } else {
                                Swal.fire("Success", data["message"], "success")
                                    .then((res) => { getRoleDealers(); });
                            }
                        },
                        error: function (err) {}
                    });
                }
            });
        }
    }

    function getRoles() {
        var roleConst;
        $.ajax({
            url: "${dealerRoleListURL}",
            type: "GET",
            success: function (response) {
                var responseData = JSON.parse(response);
                roleConst = $('.role-filter').select2({
                    data: responseData.Data,
                    tags: false,
                    placeholder: 'Pilih Role',
                    allowClear: false,
                    minimumResultsForSearch: -1,
                    maximumInputLength: 15,
                    width: '200px',
                    templateSelection: function (data) {
                        return data.text;
                    }
                });
            },
            error: function (error) {},
            complete: function () {
                roleConst.val(null).trigger('change');
                $('.role-filter').on('select2:select', function() {
                    roleId = $('.role-filter').val();
                    roleDealerDataTable.ajax.reload();
                });
            }
        });
    }

    $(document).ready(function () {
        refresh();
        $('.dataTables_length select').select2({
            minimumResultsForSearch: -1
        });
    });
</script>