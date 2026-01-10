<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects/>
<portlet:defineObjects/>

<%-- Style Utama ada di /assets/css/bootstrap-wizard.css --%>
<link href="<%=request.getContextPath()%>/assets/css/bootstrap-wizard.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/js/moment-with-locales.min.js"></script>
<link href="<%=request.getContextPath()%>/assets/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap-datepicker.min.js"></script>

<%-- datatable --%>
<link href="<%=request.getContextPath()%>/assets/library/datatables/datatables.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/library/datatables/datatables.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/datatables/ellipsis.js"></script>
<link href="<%=request.getContextPath()%>/assets/library/datatables/Select-1.2.6/css/select.bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/assets/library/datatables/Select-1.2.6/js/dataTables.select.min.js"></script>
<link href="<%=request.getContextPath()%>/assets/library/datatables/Select-1.2.6/css/select.dataTables.min.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/assets/library/datatables/Select-1.2.6/js/select.dataTables.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/datatables/Buttons-1.5.2/js/dataTables.buttons.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/datatables/Buttons-1.5.2/js/buttons.html5.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/datatables/Buttons-1.5.2/js/buttons.print.min.js"></script>

<%-- pdfmake --%>
<script src="<%=request.getContextPath()%>/assets/library/datatables/JSZip-2.5.0/jszip.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/datatables/pdfmake-0.1.36/pdfmake.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/datatables/pdfmake-0.1.36/vfs_fonts.js"></script>

<link href="<%=request.getContextPath()%>/assets/css/main.css?<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/css/upload-bonus.css?<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css"/>

<%-- sweetalert2 --%>
<script src="<%=request.getContextPath()%>/assets/js/sweetalert2.all.js?<%=System.currentTimeMillis() %>"></script>
<script src="<%=request.getContextPath()%>/assets/js/general.js?<%=System.currentTimeMillis() %>"></script>

<link href="<%=request.getContextPath()%>/assets/library/bootstrap-selectpicker/css/bootstrap-select.min.css?<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/library/bootstrap-selectpicker/js/bootstrap-select.min.js?<%=System.currentTimeMillis() %>"></script>

<link href="<%=request.getContextPath()%>/assets/library/select2/select2.min.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/library/select2/select2.full.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/1000hz-bootstrap-validator/validator.min.js"></script>

<%--<link href="<%=request.getContextPath()%>/assets/library/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />--%>
<%--&lt;%&ndash;<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/library/bootstrap/bootstrap-theme.min.css" />&ndash;%&gt;--%>
<%--&lt;%&ndash;<script src="<%=request.getContextPath()%>/assets/library/bootstrap/jquery-1.12.4.min.js"></script>&ndash;%&gt;--%>
<%--<script src="<%=request.getContextPath()%>/assets/library/bootstrap/bootstrap.min.js"></script>--%>

<%-- datetimepicker --%>
<link href="<%=request.getContextPath()%>/assets/library/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/library/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>

<%-- datatable checkboxes --%>
<link href="<%=request.getContextPath()%>/assets/library/checkboxes/dataTables.checkboxes.min.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/library/checkboxes/dataTables.checkboxes.min.js"></script>

<%-- exceljs --%>
<script src="<%=request.getContextPath()%>/assets/library/exceljs/exceljs.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/exceljs/FileSaver.min.js"></script>

<%-- tui pagination --%>
<link href="<%=request.getContextPath()%>/assets/library/tui-pagination/tui-pagination.min.css?<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/library/tui-pagination/tui-pagination-custom.js?<%=System.currentTimeMillis() %>"></script>

<%-- swiper --%>
<link href="<%=request.getContextPath()%>/assets/library/swiperJS/swiper-bundle.min.css?<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/library/swiperJS/swiper-bundle.min.js?<%=System.currentTimeMillis() %>"></script>

<%-- splide --%>
<link href="<%=request.getContextPath()%>/assets/library/splide/css/splide.min.css?<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/library/splide/js/splide.min.js?<%=System.currentTimeMillis() %>"></script>

<%-- custom css --%>
<link href="<%= request.getContextPath() %>/assets/css/general.css?<%= System.currentTimeMillis() %>" rel="stylesheet" type="text/css"/>

<style>
  .swal2-modal {
    font-size: 14px !important;
  }

  .swal2-styled.swal2-confirm {
    background-color: #014689 !important;
  }

  .bootstrap-select .dropdown-toggle {
    border: 1px solid #DBEDFF;
  }

  .bootstrap-select .dropdown-toggle.bs-placeholder {
    color: #bbb;
    opacity: 1;
  }

  .bootstrap-select .dropdown-toggle {
    color: #333;
    opacity: 1;
  }

  .bootstrap-select span.caret {
    border: none;
    width: auto !important;
    height: auto !important;
    top: 50% !important;
    transform: translateY(calc(-50% + 3px));
  }

  .bootstrap-select span.caret:after {
    content: "\f078";
    font-family: "Font Awesome 5 Free";
    font-weight: 900;
    color: #10569C;
  }

  ::placeholder {
    color: #bbb !important;
    opacity: 1;
  }

  :-ms-input-placeholder {
    color: #bbb !important;
  }

  ::-ms-input-placeholder {
    color: #bbb !important;
  }

  span.char-counter-text {
    display: block;
    margin-top: 5px;
    text-align: right;
    color: #333;
  }

  .datepicker {
    overflow-y: hidden;
  }

  .datepicker table tr td.today,
  .datepicker table tr td.today.focused,
  .datepicker table tr td.today:hover,
  .datepicker table tr td.today:focus,
  .datepicker table tr td.today:active,
  .datepicker table tr td.today:active:focus,
  .datepicker table tr td.today:active:hover {
    background-color: #DBEDFF;
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

  .bootstrap-datetimepicker-widget.dropdown-menu {
    display: block;
  }
</style>

<script>
	var dataTableLanguage = {
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

  $.fn.datepicker.dates['ID'] = {
    days: ["Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu"],
    daysShort: ["Min", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab"],
    daysMin: ["Min", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab"],
    months: ["Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"],
    monthsShort: ["Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu", "Sep", "Okt", "Nov", "Des"],
    today: "Hari Ini",
    clear: "Hapus",
    format: "mm/dd/yyyy",
    titleFormat: "MM yyyy", /* Leverages same syntax as 'format' */
    weekStart: 0
  };

  function charCounter(inputElement) {
    var $input = $(inputElement);
    var text = $input.val();
    var lengthText = text.length;
    var maxLength = $input.attr("maxlength");
    var textCounter = lengthText + "/" + maxLength;

    var $parent = $input.parents(".char-counter-parent");
    $parent.find(".char-counter-text").text(textCounter);

    if (lengthText < maxLength) {
      $parent.find(".char-counter-text").css("color", "#333333");
    } else {
      $parent.find(".char-counter-text").css("color", "red");
    }
  }

  function populateSingleSelect2(param) {
    const $element = $(param.element);
    $element.empty();

    var data = param.data ?? { p_auth: Liferay.authToken };

    $.ajax({
      url: param.url ?? '',
      type: 'POST',
      data: data,
      success: function (response) {
        if (param.placeholderValue || param.placeholderText) {
          const placeholder =
            '<option value="' + (param.placeholderValue ?? '') + '"' + (param.disablePlaceholder ? 'disabled' : '') + '>' +
              (param.placeholderText ?? '') +
            '</option>';
          $element.append(placeholder);
        }
        if (response) {
          const json = JSON.parse(response);
          if (json.acknowledge === 1) {
            json.data.forEach(item => {
              $element.append('<option value="' + item.id + '">' + item.text + '</option>');
            });
            $element.select2({
              maximumInputLength: 100,
              minimumResultsForSearch: param.minimumResultsForSearch ?? -1
            });
          }
        }
      },
      complete: function () {
        $element.val(param.initialValue ?? '').change();
      }
    });
  }

  var regexBasicCharacter = new RegExp("^[A-Za-z0-9./,()@&\\-_ ]+$");
  var regexBasicCharacterWithEnter = new RegExp("^[A-Za-z0-9./,()@&\\-_ \n]+$");
  var regexAlphaNumeric = new RegExp(/^[a-zA-Z0-9- ]*$/);
  var regexLink = new RegExp(/^(https?:\/\/)?([\w-]+\.)+[\w-]+(\/[\w\-._~:/?#[\]@!$&'()*+,;=]*)?$/);
</script>