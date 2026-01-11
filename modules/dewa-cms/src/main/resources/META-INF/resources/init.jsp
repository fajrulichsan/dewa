<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ taglib prefix="theme" uri="http://java.sun.com/portlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<liferay-theme:defineObjects/>
<portlet:defineObjects/>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />

<link href="<%=request.getContextPath()%>/assets/css/bootstrap-wizard.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/js/moment-with-locales.min.js"></script>

<link href="<%=request.getContextPath()%>/assets/library/datatables/datatables.min.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/assets/library/datatables/datatables.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/datatables/ellipsis.js"></script>

<script src="<%=request.getContextPath()%>/assets/js/general.js"></script>
<link href="<%=request.getContextPath()%>/assets/css/main.css?<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/js/sweetalert2.all.js?<%=System.currentTimeMillis() %>"></script>

<link href="<%=request.getContextPath()%>/assets/library/bootstrap-selectpicker/css/bootstrap-select.min.css?<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/library/bootstrap-selectpicker/js/bootstrap-select.min.js?<%=System.currentTimeMillis() %>"></script>

<link href="<%=request.getContextPath()%>/assets/library/tui-pagination/tui-pagination.min.css?<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/library/tui-pagination/tui-pagination-custom.js?<%=System.currentTimeMillis() %>"></script>

<link href="<%=request.getContextPath()%>/assets/library/select2/select2.min.css" rel="stylesheet"/>
<script src="<%=request.getContextPath()%>/assets/library/select2/select2.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/1000hz-bootstrap-validator/validator.min.js"></script>

<link href="<%=request.getContextPath()%>/assets/library/checkboxes/dataTables.checkboxes.min.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/library/checkboxes/dataTables.checkboxes.min.js"></script>

<link href="<%=request.getContextPath()%>/assets/library/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/assets/library/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>

<script src="<%=request.getContextPath()%>/assets/library/exceljs/exceljs.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/library/exceljs/FileSaver.min.js"></script>

<link href="<%= request.getContextPath() %>/assets/css/general.css?<%= System.currentTimeMillis() %>" rel="stylesheet" type="text/css" />

<style>
  .swal2-modal {
    font-size: 14px !important;
  }

  .swal2-styled.swal2-confirm {
    background-color: #014689 !important;
  }

  .bootstrap-select .dropdown-toggle {
    border: 2px solid #DBEDFF;
  }

  /* DataTables Sorting Icons with FontAwesome */
  table.dataTable thead .sorting:before,
  table.dataTable thead .sorting_asc:before,
  table.dataTable thead .sorting_desc:before {
    font-family: "Font Awesome 6 Free";
    font-weight: 900;
    position: absolute;
    right: 8px;
    top: 50%;
    transform: translateY(-50%);
  }

  table.dataTable thead .sorting:before {
    content: "\f0dc";
    opacity: 0.3;
  }

  table.dataTable thead .sorting_asc:before {
    content: "\f0de";
    opacity: 1;
  }

  table.dataTable thead .sorting_desc:before {
    content: "\f0dd";
    opacity: 1;
  }

  table.dataTable thead th {
    position: relative;
    padding-right: 30px;
  }

  /* DataTables Blue Header */
  table.dataTable thead th {
    background-color: #014689 !important;
    color: white !important;
  }

  /* DataTables Pagination Style */
  .dataTables_wrapper .dataTables_paginate {
    padding-top: 15px;
    text-align: center !important;
  }

  .dataTables_wrapper .dataTables_paginate .paginate_button {
    border: none !important;
    background: transparent !important;
    margin: 0 3px !important;
    border-radius: 5px !important;
    transition: background-color 0.3s ease;
    padding: 5px !important;
    color: #333 !important;
    box-shadow: none !important;
  }

  ul.pagination > li > a {
    color: #014689;
  }

  li.paginate_button.active > a {
    border-radius: 10px;
    background-color: #10569C;
    padding: 6px 12px;
    color: white;
  }

  .dataTables_wrapper .dataTables_paginate .paginate_button.current,
  .dataTables_wrapper .dataTables_paginate .paginate_button.current:hover,
  .dataTables_wrapper .dataTables_paginate .paginate_button.current:focus {
    background-color: #014689 !important;
    background: #014689 !important;
    color: #ffffff !important;
    border: none !important;
  }

  .dataTables_wrapper .dataTables_paginate .paginate_button:hover {
    background: #e9ecef !important;
    color: #333 !important;
    border: none !important;
  }

  .dataTables_wrapper .dataTables_paginate .paginate_button.disabled,
  .dataTables_wrapper .dataTables_paginate .paginate_button.disabled:hover,
  .dataTables_wrapper .dataTables_paginate .paginate_button.disabled:focus {
    color: #ccc !important;
    background: transparent !important;
    cursor: default !important;
  }

  /* Panah next/previous - aktif warna theme, disabled warna abu */
  .dataTables_wrapper .dataTables_paginate .paginate_button.previous:not(.disabled) i,
  .dataTables_wrapper .dataTables_paginate .paginate_button.next:not(.disabled) i {
    color: #014689 !important;
  }

  .dataTables_wrapper .dataTables_paginate .paginate_button.previous.disabled i,
  .dataTables_wrapper .dataTables_paginate .paginate_button.next.disabled i {
    color: #ccc !important;
  }

  .dataTables_wrapper .dataTables_paginate .paginate_button.previous,
  .dataTables_wrapper .dataTables_paginate .paginate_button.next {
    border: none !important;
    border-radius: 5px !important;
  }

  .dataTables_wrapper .dataTables_paginate .paginate_button.first,
  .dataTables_wrapper .dataTables_paginate .paginate_button.last {
    display: none !important;
  }

  /* Matikan icon sorting default DataTables */
  table.dataTable thead .sorting:after,
  table.dataTable thead .sorting_asc:after,
  table.dataTable thead .sorting_desc:after,
  table.dataTable thead .sorting_asc_disabled:after,
  table.dataTable thead .sorting_desc_disabled:after {
    display: none !important;
  }

  /* Select2 - matikan overflow hidden */
  .select2-container .select2-selection--single .select2-selection__rendered {
    overflow: visible !important;
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

  .loading-layer {
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 9999;
  }

  .spinner {
    width: 20vw;
    text-align: center;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  .spinner > div {
    width: 18px;
    height: 18px;
    background-color: #2ca8ff;
    border-radius: 100%;
    display: inline-block;
    -webkit-animation: sk-bouncedelay 1.4s infinite ease-in-out both;
    animation: sk-bouncedelay 1.4s infinite ease-in-out both;
  }

  .spinner .bounce1 {
    -webkit-animation-delay: -0.32s;
    animation-delay: -0.32s;
  }

  .spinner .bounce2 {
    -webkit-animation-delay: -0.16s;
    animation-delay: -0.16s;
  }

  @-webkit-keyframes sk-bouncedelay {
    0%, 80%, 100% {
      -webkit-transform: scale(0);
    }
    40% {
      -webkit-transform: scale(1);
    }
  }

  @keyframes sk-bouncedelay {
    0%, 80%, 100% {
      -webkit-transform: scale(0);
      transform: scale(0);
    }
    40% {
      -webkit-transform: scale(1);
      transform: scale(1);
    }
  }
</style>

<script>
  var regexBasicCharacter = new RegExp("^[A-Za-z0-9./,()@&\\-_ ]+$");

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
</script>