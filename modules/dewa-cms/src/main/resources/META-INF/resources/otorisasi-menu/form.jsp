<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 01/11/2022
  Time: 7:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL id="/otorisasi-menu-action" var="otorisasiMenuActionURL"/>

<portlet:resourceURL id="/dealer-role-dealer" var="dealerRoleDealerURL"/>
<portlet:resourceURL id="/cabang-role-dealer" var="cabangRoleDealerURL"/>
<portlet:resourceURL id="/user-role-dealer" var="userRoleDealerURL"/>
<portlet:resourceURL id="/role-role-dealer" var="roleRoleDealerURL"/>
<portlet:resourceURL id="/user-single-role-dealer" var="userSingleRoleDealerEditURL"/>

<style>
    /* Select2 */
    .select2-container--default .select2-selection--single {
        padding:6px;
        height: 37px;
        font-size: 1.2em;
        position: relative;
    }

    .role-input {
        padding-right: 30px;
    }
</style>

<form id="formOtorisasiMenu" method="post" enctype="multipart/form-data" novalidate autocomplete="off">
    <div class="container" style="margin-left: 0;">
        <h3 id="form-title" style="margin-bottom: 50px;">Otorisasi Menu</h3>
        <div class="row">
            <div class="form-group col-md-9 role-input">
                <label class="title-form pull-left" for="roleId">Role <span
                        style="color: red;">*</span></label>
                <select class="form-control" name="roleId" id="roleId" style="width: 100%;">
                </select>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-9 role-input">
                <label class="title-form" for="roleId">Otorisasi Menu <span style="color: red;">*</span></label><br>
                <div class="col-md-5">
                    <input type="checkbox" id="fakturIndirect" name="menu" value="Faktur Indirect">
                    <label for="fakturIndirect">Faktur Indirect</label><br>
                    <input type="checkbox" id="salesReport" name="menu" value="Sales Report">
                    <label for="salesReport">Sales Report</label><br>
                    <input type="checkbox" id="reportPlafond" name="menu" value="Report Plafond">
                    <label for="reportPlafond">Report Plafond</label><br>
                    <input type="checkbox" id="realisasiDiskonFakpol" name="menu" value="Realisasi Diskon Fakpol">
                    <label for="realisasiDiskonFakpol">Realisasi Diskon Fakpol</label><br>
                    <input type="checkbox" id="realisasiDiskonFleet" name="menu" value="Realisasi Diskon Fleet">
                    <label for="realisasiDiskonFleet">Realisasi Diskon Fleet</label><br>
                    <input type="checkbox" id="realisasiDiskonTestCar" name="menu" value="Realisasi Diskon Test Car">
                    <label for="realisasiDiskonTestCar">Realisasi Diskon Test Car</label><br>
                    <input type="checkbox" id="realisasiDiskonOthers" name="menu" value="Realisasi Diskon Others">
                    <label for="realisasiDiskonOthers">Realisasi Diskon Others</label><br>
                    <input type="checkbox" id="fakturPajak" name="menu" value="Faktur Pajak">
                    <label for="fakturPajak">Faktur Pajak</label><br>
                    <input type="checkbox" id="eSRUT" name="menu" value="E-SRUT">
                    <label for="eSRUT">E-SRUT</label><br>
                    <input type="checkbox" id="salesProgram" name="menu" value="Sales Program">
                    <label for="salesProgram">Sales Program</label><br>
                </div>
                <div class="col-md-3">
                    <input type="checkbox" id="kategoriDealer" name="menu" value="Kategori Dealer">
                    <label for="kategoriDealer">Kategori Dealer</label><br>
                    <input type="checkbox" id="suratPenaltyStock" name="menu" value="Surat Penalty Stock">
                    <label for="suratPenaltyStock">Surat Penalty Stock</label><br>
                    <input type="checkbox" id="sp3d" name="menu" value="SP3D">
                    <label for="sp3d">SP3D</label><br>
                    <input type="checkbox" id="skIRIS" name="menu" value="SK IRIS">
                    <label for="skIRIS">SK IRIS</label><br>
                    <input type="checkbox" id="kuitansiBonus" name="menu" value="Kuitansi Bonus">
                    <label for="kuitansiBonus">Kuitansi Bonus</label><br>
                    <input type="checkbox" id="copySTNKTestCar" name="menu" value="Copy STNK Test Car">
                    <label for="copySTNKTestCar">Copy STNK Test Car</label><br>
                    <input type="checkbox" id="training" name="menu" value="Training">
                    <label for="training">Training</label><br>
                    <input type="checkbox" id="calenderEvent" name="menu" value="Calender Event">
                    <label for="calenderEvent">Calender Event</label><br>
                    <input type="checkbox" id="rssp" name="rssp" value="rssp">
                    <label for="rssp">RSSP</label><br>
                    <input type="checkbox" id="cmsDso" name="cmsDso" value="cmsDso">
                    <label for="cmsDso">CMS Service</label><br>
                </div>
            </div>
        </div>

        <div class="row ">
            <button id="submitOtorisasiMenu" class="btn-ipr" type="submit">Save</button>
            <a class="btn btn-ipr-cancel" id="backButtonDocFlow" onclick="backToUM()" >Cancel</a>
        </div>
    </div>
</form>


<script>
    var roles = ${roles};
    var editMenu;
    var action = "${action}";
    var homeUrl = "/group/dealink/cms-otorisasi-menu";
    var submitProcess = false;

    $(document).ready(function() {
        generateDropdown(roles);

        if (action == 'edit') {
            editMenu = '${editMenu}' === '' ? {} : JSON.parse('${editMenu}');

            $("#roleId").val(editMenu.roleId);
            $("#roleId").selectpicker('refresh');

            $("#roleId").prop("disabled", true);
            $("#roleId").prop("readonly", true);
            $("#roleId").selectpicker('refresh');

            var menuValues = editMenu.menus.split(",");
            menuValues.forEach(function(menuValue) {
                menuValue = menuValue.trim();
                $('input[type="checkbox"][value="' + menuValue + '"]').prop('checked', true);
            });

            var isRssp = editMenu.isRssp;
            if (isRssp) {
                $('input[type="checkbox"][value="rssp"]').prop('checked', true);
            }

            var isCmsDso = editMenu.isCmsDso;
            if (isCmsDso) {
                $('input[type="checkbox"][value="cmsDso"]').prop('checked', true);
            }
        }
    });

    function backToUM() {
        window.location.href = homeUrl;
    };

    function generateDropdown(roles) {
        var selectElement = $("#roleId");
        selectElement.empty();
        selectElement.append($("<option>", {
            value: "",
            text: "Pilih Role",
            disabled: true,
            selected: true
        }));

        $.each(roles, function(index, role) {
            var option = $("<option></option>");
            option.text(role.roleName);
            option.val(role.roleId)
            option.attr("data-id", role.roleId);
            selectElement.append(option);
        });

        $("#roleId").selectpicker('refresh');
    }

    $("#submitOtorisasiMenu").click(function(event) {
        event.preventDefault();
        var roleId = $("#roleId option:selected").data('id')
        var isRsspChecked = $("#rssp").is(':checked');
        var isCmsDsoChecked = $('#cmsDso').is(':checked');
        if (roleId == null) {
            Swal.fire("Role belum dipilih.", "", "warning");
            return false;
        }
        var checkedValues = [];
        $("input[name='menu']:checked").each(function() {
            checkedValues.push($(this).val());
        });

        if (checkedValues.length == 0 && !(isRsspChecked || isCmsDsoChecked)){
            Swal.fire("Minimal pilih satu menu.", "", "warning");
            return false;
        }

        var menus = checkedValues.join(', ');
        submitOtorisasiMenu({
            roleId: roleId,
            menus: menus,
            isRsspChecked: isRsspChecked,
            isCmsDsoChecked: isCmsDsoChecked
        });
    });

    function submitOtorisasiMenu(param) {
        var formData = new FormData();
        formData.append("crudType", action)
        formData.append("roleId", param.roleId);
        formData.append("menus", param.menus);
        formData.append("isRssp", param.isRsspChecked);
        formData.append("isCmsDso", param.isCmsDsoChecked);

        if (action == "edit"){
            formData.append("id", editMenu.id)
        }

        createLoading();
        $.ajax({
            url: "${otorisasiMenuActionURL}",
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                if (JSON.parse(response).status === 'success') {
                    swal.fire("Success", "Your request has been saved", "success")
                        .then(function () {
                                window.location = homeUrl;
                            }
                        );
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
</script>
