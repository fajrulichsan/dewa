<%@ include file="/META-INF/resources/init.jsp" %>

<style>
    #breadcrumb-dewa{
        color: #262626;
        padding-top: 10px;
    }

    .list-menu{
        text-transform: none;
        font-weight: 400;
    }

</style>

<div id="breadcrumb-dewa">
    <c:choose>
        <c:when test="${not empty listBreadCrumb}">
            <h6 class="list-menu">
                <c:forEach var="menu" items="${listBreadCrumb}" varStatus="loop">
                    <c:out value="${menu}"/>
                    <c:if test="${!loop.last}">
                        <c:out value=" > "/>
                    </c:if>
                </c:forEach>
            </h6>
        </c:when>
    </c:choose>
</div>