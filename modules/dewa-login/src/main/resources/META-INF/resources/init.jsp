<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<link href="<%= request.getContextPath() %>/assets/library/select2/select2.min.css?t=<%= System.currentTimeMillis() %>" rel="stylesheet" type="text/css" />
<script src="<%= request.getContextPath() %>/assets/library/select2/select2.full.min.js?t=<%= System.currentTimeMillis() %>"></script>

<link href="<%= request.getContextPath() %>/assets/css/main.css?t=<%= System.currentTimeMillis() %>" rel="stylesheet" type="text/css"/>
<script src="<%= request.getContextPath() %>/assets/js/general.js?t=<%=System.currentTimeMillis() %>"></script>

<link href="<%= request.getContextPath() %>/assets/library/datatables/datatables.min.css?t=<%= System.currentTimeMillis() %>" rel="stylesheet" type="text/css">
<script src="<%= request.getContextPath() %>/assets/library/datatables/datatables.min.js?t=<%= System.currentTimeMillis() %>"></script>
<script src="<%= request.getContextPath() %>/assets/library/datatables/ellipsis.js?t=<%= System.currentTimeMillis() %>"></script>

<script src="<%= request.getContextPath() %>/assets/js/sweetalert2.all.js?t=<%=System.currentTimeMillis() %>"></script>

<script>
    var regexBasicCharacter = new RegExp("^[A-Za-z0-9./,()@&\\-_ ]+$");
    var regexAlphabetOnly = new RegExp("^[A-Za-z ]+$");
    var regexEmailUserNameValid = new RegExp("^[a-zA-Z0-9._+-]+$");
    var regexNameCharacter = new RegExp("^[a-zA-Z\s'.,-]+$");
    var emailLocalPartRegex = new RegExp("^[a-zA-Z0-9._%+-]+$");

    var regexEmail = new RegExp(
        /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    );
</script>