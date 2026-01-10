<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/approval-regist-list" var="approvalRegistListURL"/>
<portlet:resourceURL id="/approve-regist-action" var="approveRegistActionURL"/>
<portlet:resourceURL id="/register/roles" var="dealerRoleListURL"/>

<style>
    .regist_account {
        margin: 25px;
    }

    .regist_account > .tabcontent {
        box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
        border-radius: 10px;
        padding: 0.75em;
    }

    #regist_account_table > thead {
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

    table#regist_account_table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#regist_account_table {
        border-radius: 10px 10px 10px 10px;
    }

    #regist_account_table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #regist_account_table > thead > tr > th {
        font-weight: normal;
        text-align: center;
    }

    #approval_table_filter > label > input {
        background-image: url(<%=request.getContextPath()%>/assets/img/search.svg);
        background-position: 10px 8px;
        background-repeat: no-repeat;
        padding: 5px 20px 5px 40px;
    }

    .dataTables_length {
        width: 100%;
        display: flex;
    }

    .dataTables_length label {
        margin-right: 10px;
    }

    .form-control {
        height: 40px !important;
        border: 2px solid #DBEDFF;
    }

    .form-control:focus {
        border: 2px solid #DBEDFF;
    }

    .btn_table, .btn_table:hover {
        border: 0;
        background-color: #014689 !important;
        color: white;
        font-weight: 400;
        opacity: 0.8;
        padding: 8px 16px;
        border-radius: 5px;
        height: 40px;
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

    .action_button {
        width: 25px;
        cursor: pointer;
    }

    .role-column-data {
        width: 100%;
        text-align: center;
    }
</style>

<div class="regist_account">
    <h3 style="font-size: 24px; font-weight: 700; margin-bottom: 1em;">Registrasi Account</h3>
    <div class="tabcontent">
        <table id="regist_account_table" class="table table-hover nowrap" style="width:100%">
            <thead>
            <tr>
                <th>No</th>
                <th>Nama Lengkap</th>
                <th>Email</th>
                <th>Role</th>
                <th>Nama Dealer</th>
                <th>Cabang</th>
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
    var isDepartment = false;

    var languageRegistAccountTable = {
        "lengthMenu": "_MENU_",
        "paginate": {
            "first": "",
            "last": "",
            "next": '<span class="glyphicon glyphicon-menu-right"></span>',
            "previous": '<span class="glyphicon glyphicon-menu-left"></span>'
        },
        "search": "",
        searchPlaceholder: "Search..."
    }

    function getRoleDealers() {
        createLoading();
        roleDealerDataTable = $('#regist_account_table').DataTable({
            info: true,
            paging: true,
            responsive: true,
            ajax: {
                url: '${approvalRegistListURL}',
                type: 'POST',
                data: function (params) {
                    params.roleId = roleId;
                    params.p_auth = Liferay.authToken;
                },
                dataSrc: function (response) {
                    isDepartment = response.isDepartment;
                    return response.Data;
                }
            },
            language: languageRegistAccountTable,
            columns: [
                {
                    data: "no",
                    width: "5%",
                    className: "text-center"
                },
                {
                    data: "fullName",
                    width: "20%"
                },
                {
                    data: "userEmail",
                    width: "20%"
                },
                {
                    data: 'roleName',
                    width: '10%'
                },
                {
                    data: "dealerName",
                    width: "20%",
                    className: "text-center"
                },
                {
                    data: "cabangName",
                    width: "15%",
                    className: "text-center"
                },
                {
                    data: "id",
                    width: "10%",
                    className: "text-center",
                    render: function (data, type, row, meta) {
                        return renderActionButton(data, row.roleId);
                    },
                    orderable: false
                }
            ],
            order: [],
            initComplete: function () {
                destroyLoading();
                getRoles();
                var input = $('.dataTables_filter input').unbind(),
                    self = this.api(),
                    $searchButton = $('<button>')
                        .text('Search')
                        .addClass("btn")
                        .addClass("btn-info")
                        .addClass("btn_table")
                        .css("margin-left", "10px")
                        .click(function () {
                            self.search(input.val()).draw();
                        }),
                    $roleSelect = $('<select class="role-filter form-control" style="margin-left: 10px; width: 25%;"><option value="ALL">Pilih Role</option></select>');
                if (isDepartment == false) {
                    $('.dataTables_length').append($roleSelect);
                }
                $('.dataTables_filter').append($searchButton);
            }
        });
    }

    function renderActionButton(dataId, roleId) {
        var html =
            `
                    <span class="action-wrapper" data-id="` + dataId + `">
                        <a onclick="approveUser(this)" data-toggle="tooltip" title="Approve" data-id="` + dataId + `" data-role="` + roleId + `">
                            <span><img class="action_button" src="<%=request.getContextPath()%>/assets/img/approve.svg"></span>
                        </a>
                        <a onclick="rejectUser(this)" data-toggle="tooltip" title="Reject" data-id="` + dataId + `" data-role="` + roleId + `">
                            <span><img class="action_button" src="<%=request.getContextPath()%>/assets/img/reject.svg"></span>
                        </a>
                    </span>
                `;
        return html;
    }

    function rejectUser(element) {
        var dataId = $(element).data('id');
        var roleId = $(element).data('role');

        Swal.fire({
            title: 'Confirmation',
            input: 'textarea',
            html: 'Are you sure want to reject this request?',
            inputLabel: 'Reject reason: ',
            inputAttributes: {
                'aria-label': 'Type your reason here',
                maxlength: 250
            },
            showCancelButton: true,
            confirmButtonText: 'Send Reject',
            cancelButtonText: 'Cancel',
            confirmButtonColor: '#014689',
            cancelButtonColor: '#c5c6c7',
            inputValidator: (input) => {
                if (!input) {
                    return 'Alasan reject tidak boleh kosong';
                }
                if (input.length > 250) {
                    return 'Maximum 250 characters allowed';
                }
                if (!regexBasicCharacter.test(input)) {
                    return 'Invalid characters. Please use only letters, numbers, and basic symbols.';
                    // Swal.fire("Alasan reject hanya boleh diisi dengan karakter .,/()@&_-", "", "warning");
                }
            }
        }).then((result) => {
            if (result.isConfirmed) {
                const rejectReason = result.value;

                var formData = new FormData();
                formData.set('<portlet:namespace/>state', 'REJECT_USER');
                formData.set('<portlet:namespace/>dataId', dataId);
                formData.set('<portlet:namespace/>roleId', roleId);
                formData.set('<portlet:namespace/>rejectReason', rejectReason);
                formData.set('<portlet:namespace/>p_auth', Liferay.authToken);

                createLoading();
                $.ajax({
                    url: '${approveRegistActionURL}',
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        if (JSON.parse(response).status === 'success') {
                            Swal.fire('Rejected!', 'Request has been rejected. Reason: ' + rejectReason, 'success')
                                .then(function () {
                                    window.location.reload();
                                });
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
                        destroyLoading();
                    }
                });
            }
        });
    }

    function approveUser(element) {
        var dataId = $(element).data('id');
        var roleId = $(element).data('role');

        var formData = new FormData();
        formData.set('<portlet:namespace/>state', 'APPROVE_USER')
        formData.set('<portlet:namespace/>dataId', dataId);
        formData.set('<portlet:namespace/>roleId', roleId);
        formData.set('<portlet:namespace/>p_auth', Liferay.authToken);

        Swal.fire({
            title: 'Confirmation',
            text: "Are you sure want to approve this request?",
            showCancelButton: true,
            confirmButtonColor: '#014689',
            cancelButtonColor: '#c5c6c7',
            confirmButtonText: 'Approve'
        }).then((result) => {
            if (result.isConfirmed) {

                createLoading();
                $.ajax({
                    url: '${approveRegistActionURL}',
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        if (JSON.parse(response).status === 'success') {
                            Swal.fire('Approved!', 'This request has been approved.', 'success')
                                .then(function () {
                                    window.location.reload();
                                });
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
                        destroyLoading();
                    }
                });
            }
        });
    }

    function getRoles() {
        var roleConst;
        $.ajax({
            url: '${dealerRoleListURL}',
            type: 'GET',
            success: function (response) {
                var responseData = JSON.parse(response);
                roleConst = $('.role-filter').select2({
                    data: responseData.Data,
                    tags: false,
                    placeholder: 'Pilih Role',
                    allowClear: false,
                    minimumResultsForSearch: -1,
                    maximumInputLength: 15,
                    templateSelection: function (data) {
                        return data.text;
                    }
                });
            },
            error: function (error) {
                console.log(error);
            },
            complete: function () {
                roleConst.val(null).trigger('change');
                $('.role-filter').on('select2:select', function () {
                    roleId = $('.role-filter').val();
                    roleDealerDataTable.ajax.reload();
                });
                console.log("complete");
            }
        });
    }

    $(document).ready(function () {
        getRoleDealers();
        getRoles();

        $('.dataTables_length select').select2({
            minimumResultsForSearch: -1
        });
    });
</script>