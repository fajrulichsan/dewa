<%@ include file="/META-INF/resources/init.jsp" %>
<style>
    .page-not-found{
        background-image: url('<%=request.getContextPath()%>/assets/img/page-not-found.svg');;
        width: 100%;
        height: 66vh;
        background-size: contain;
        background-repeat: no-repeat;
        background-position: center;
        position: relative;
    }

    .message{
        position: absolute;
        text-align: center;
        bottom: 50px;
        color: #5e5e5e;
        font-size: 36px;
    }
</style>
<div class="col-md-12">
    <div class="page-not-found">
        <h1 class="message col-md-12">Maaf, anda tidak punya akses untuk halaman ini</h1>
    </div>
</div>