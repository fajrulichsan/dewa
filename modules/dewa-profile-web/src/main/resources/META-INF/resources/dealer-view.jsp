<%@ page import="com.liferay.portal.kernel.model.User" %>

<%@ include file="/META-INF/resources/init.jsp" %>

<link href="<%= request.getContextPath() %>/non-cms/profile.css?t=<%= System.currentTimeMillis() %>" rel="stylesheet" type="text/css"/>

<div class="navbar_right flex-nowrap">
    <img class="img-daihatsu" src="<%=request.getContextPath()%>/assets/img/astra-daihatsu-dealink.png" style="">
    <div class="menu-user">
        <div class="user-data" style="margin: auto">
            <div class="">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="color:#354052!important;">
                    <div style="margin: auto">
                        <c:choose>
                            <c:when test="${photoUrl != ''}">
                                <div class="profile-img" style="background-image: url('${photoUrl}')"></div>
                            </c:when>
                            <c:otherwise>
                                <img class="profile-img" src="<%=request.getContextPath()%>/assets/img/Account_Icon.png">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </a>
            </div>
        </div>
        <div>
            <div class="menu-user-name">${fullName}</div>
        </div>
        <div class="dropdown-content">
            <a data-senna-off="true" href="${siteDealerURL}/group/dealink/profile">Profile</a>
            <c:choose>
                <c:when test="${(isDSO eq true) or (isAdmin eq true)}">
                    <a data-senna-off="true" href="${siteDealerURL}/group/dealink/cms-home">Go to CMS Page</a>
                </c:when>
            </c:choose>
            <a href="/c/portal/logout">Logout</a>
        </div>
    </div>
</div>

<%@ include file="non-cms/profile.jsp" %>