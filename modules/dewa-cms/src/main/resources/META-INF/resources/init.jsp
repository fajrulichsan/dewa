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