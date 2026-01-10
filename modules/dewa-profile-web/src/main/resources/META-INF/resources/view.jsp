<%@ page import="com.liferay.portal.kernel.model.User" %>

<%@ include file="/META-INF/resources/init.jsp" %>


<style type="text/css">
    .navbar_right{
        display: flex;
        /*width: 230px;*/
    }
    .navbar_right .icon_wrap{
        cursor: pointer;
    }
    .navbar_right .notifications{
        margin-right: 15px;
    }
    .navbar_right .notifications .icon_wrap{
        font-size: 28px;
    }
    .navbar_right .notifications{
        position: relative;
    }
    .notification_dd{
        position: absolute;
        top: 48px;
        right: -15px;
        user-select: none;
        background: #fff;
        border: 1px solid #c7d8e2;
        width: 350px;
        height: auto;
        display: none;
        overflow: hidden;
        /*  border-radius: 3px;
         box-shadow: 10px 10px 35px rgba(0,0,0,0.125),
                     -10px -10px 35px rgba(0,0,0,0.125); */
        border-radius: 10px;
    }
    .notification_dd:before{
        content: "";
        position: absolute;
        top: -20px;
        right: 15px;
        /* border: 10px solid;
        border-color: transparent transparent #fff transparent; */
    }
    .notification_dd li {
        border-bottom: 1px solid #f1f2f4;
        padding: 10px 20px;
        display: flex;
        align-items: center;
    }
    .notification_dd li .notify_data .title{
        color: #337ab7;
        font-weight: 600;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    .notification_dd li .notify_data .sub_title{
        font-size: 12px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        margin-top: 5px;
    }
    .notification_dd li .notify_status p{
        font-size: 12px;
    }
    .notification_dd li.success .notify_status p{
        color: #47da89;
    }
    .notification_dd li.failed .notify_status p{
        color: #fb0001;
    }
    .notification_dd li.show_all{
        display: flex;
        /* padding: 20px;
        justify-content: center; */
    }
    .notification_dd li.show_all a{
        /* font-weight: 700; */
        color: #3b80f9;
        cursor: pointer;
    }
    .notification_dd li.show_all a:hover{
        text-decoration: underline;
    }
    .navbar_right .icon_wrap:hover,
    .navbar_right .notifications.active .icon_wrap{
        color: #3b80f9;
    }

    .notifications.active .notification_dd{
        display: block;
        z-index: 1000;
    }
    .noti_Counter {
        display:none;
        position: absolute;
        margin:2px 0 0 13px;
        padding: .2em .6em .3em;
        background: #ED193F;
        color: #FFF;
        font-size: 10px;
        font-weight: 700;
        /* line-height: 1; */
        text-align: center;
        border-radius: 100px;
        white-space: nowrap;
        vertical-align: baseline;
        z-index:1;
    }

    .navbar_right.flex-nowrap{
        background-color: white;
        border: 0px;
    }

    .menu-user{
        display: flex;
        gap: 10px;
        margin-left: 10px;
        align-items: center;
    }

    .profile-img {
        height: 35px;
        width: 35px;
        border-radius: 50%;
        /*margin-top: auto;*/
        /*margin-bottom: auto;*/
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #F8FBFF;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
        border-radius: 5px;
        overflow: hidden;
        top: 90%;
    }

    .dropdown-content a {
        color: #8B8B8B;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    /* Change color of dropdown links on hover */
    .dropdown-content a:hover {
        color: #014689;
        background-color: #DEEFFF ;
    }



    /* Show the dropdown menu on hover */
    .menu-user:hover .dropdown-content {
        display: block;
    }

    .menu-user-name{
        min-width: 100px;
        max-width: 150px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        font-weight: bold;
        font-size: 14px;
    }
    .menu-company-type{
        color: #B4BEC9;
        min-width: 100px;
        font-size: 12px;
        max-width: 150px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    @media (max-width: 767px) {
        .menu-user .menu-user-name,
        .menu-user .menu-company-type{
            display: none;
        }

        .menu-user .dropdown-content {
            right: 0;
        }
    }

    .popup-notification{
        position: absolute;
        background-color: white;
        right: 0;
        box-shadow: 1px 3px 9px #888888;
        z-index: 1;
        width: 13em;
        padding: 20px;
        height: 11em;
        display: none;
        border-radius: 10px;
    }

    .dot-red{
        width: 10px;
        height: 10px;
        border-radius: 100%;
        background-color: red;
        position: absolute;
        top: 6px;
        left: 62%;
    }

    .dot-blue{
        width: 3px;
        height: 3px;
        border-radius: 100%;
        background-color: #014689;
    }

    .notif-header{
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .notif-header p {
        color: #014689;
    }

    .notif-content{
        /*padding: 8px 15px;*/
        padding: 8px 8px 2px 15px;
        border-radius: 5px;
        margin-bottom: 10px;
        margin-right: 8px;
    }

    .bg-blue{
        background-color: #DEEFFF;

    }

    .bg-gray{
        background-color: rgba(222, 222, 222, 0.55);
    }


    ul{
        padding-inline-start: 5px;
    }

    .notif-container{
        overflow: auto;
        gap: 10px;
        height: 8em ;
    }

    ::-webkit-scrollbar {
        width: 10px;
    }

    /* Track */
    ::-webkit-scrollbar-track {
        box-shadow: inset 0 0 5px grey;
        border-radius: 10px;
    }

    /* Handle */
    ::-webkit-scrollbar-thumb {
        background: #aaaaaaa3;
        border-radius: 10px;
    }

    /* Handle on hover */
    ::-webkit-scrollbar-thumb:hover {
        background: gray;
    }

    .icon_wrap:hover{
        cursor: default !important;
    }

    cursor-pointer:hover{
        cursor: pointer !important;
    }
</style>

<div class="navbar_right flex-nowrap">
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
                                <img class="profile-img" src="<%=user.getPortraitURL(themeDisplay)%>">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </a>
            </div>
        </div>
        <div>
            <div class="menu-user-name">${fullName}</div>
            <c:choose>
                <c:when test="${isCMS eq true}">
                    <div class="menu-company-type">${roleUser}</div>
                </c:when>
            </c:choose>
        </div>
        <div class="dropdown-content">
            <c:choose>
                <c:when test="${isCMS eq true}">
                    <a data-senna-off="true" href="${siteDealerURL}/group/dealink/">Go to User Page</a>
                </c:when>
            </c:choose>
            <a href="/c/portal/logout">Logout</a>
        </div>
    </div>
</div>

<script>
    var menus = "${menus}";
    var isDSO = "${isDSODepartment}";
    var menuRepotList = ["Faktur Indirect", "Sales Report", "Report Plafond", "Realisasi Diskon Fakpol", "Realisasi Diskon Fleet", "Realisasi Diskon Test Car", "Realisasi Diskon Others"];
    var menuDownloadUnitList = ["Faktur Pajak", "E-SRUT", "Sales Program", "Kategori Dealer", "Surat Penalty Stock"];
    var menuDownloadSKList = ["SP3D", "SK IRIS"];
    var menuUploadList = ["Kuitansi Bonus", "Copy STNK Test Car"];
    var urlGroup = "/group/dealink";
    var baseUrl = window.location.origin + "/group/dealink";
    var currentUrl = window.location.href;
    var menusLink = [
        {
            menu: "Faktur Indirect",
            link: "/cms-faktur-indirect"
        },
        {
            menu: "Sales Report",
            link: "/cms-sales-report"
        },
        {
            menu: "Report Plafond",
            link: "/cms-report-plafond"
        },
        {
            menu: "Realisasi Diskon Fakpol",
            link: "/cms-realisasi-diskon-fakpol"
        },
        {
            menu: "Realisasi Diskon Fleet",
            link: "/cms-realisasi-diskon-fleet"
        },
        {
            menu: "Realisasi Diskon Test Car",
            link: "/cms-realisasi-diskon-test-car"
        },
        {
            menu: "Realisasi Diskon Others",
            link: "/cms-realisasi-diskon-others"
        },
        {
            menu: "Faktur Pajak",
            link: "/cms-faktur-pajak"
        },
        {
            menu: "E-SRUT",
            link: "/cms-e-srut"
        },
        {
            menu: "Sales Program",
            link: "/cms-sales-program"
        },
        {
            menu: "Kategori Dealer",
            link: "/cms-kategori-dealer"
        },
        {
            menu: "Surat Penalty Stock",
            link: "/cms-surat-penalty-stock"
        },
        {
            menu: "SP3D",
            link: "/cms-sp3d"
        },
        {
            menu: "SK IRIS",
            link: "/cms-sk-iris"
        },
        {
            menu: "Kuitansi Bonus",
            link: "/cms-upload-kuitansi-bonus"
        },
        {
            menu: "Copy STNK Test Car",
            link: "/cms-upload-copy-stnk"
        },
        {
            menu: "Training",
            link: "/cms-training"
        },
        {
            menu: "Calender Event",
            link: "/cms-calendar-event"
        }
    ];

    var isAdminDivision = "${isAdminDivision}";

    if (isDSO === "true") {
        hideMenu();
        checkActiveMenu();
        procedureHide();
    }

    if (isAdminDivision == "true") {
        var mainMenu = ["Home", "Training", "Calendar Event", "Report", "Upload", "Claim Dealer", "Master Data"];
        for (let i = 0; i < mainMenu.length; i++) {
            hideMenuCMS(mainMenu[i]);
        }

        hideOtorisasiMenu();

        if (currentUrl == baseUrl + "/cms-home"){
            window.location = '/group/dealink/cms-user-management';
        } else if (currentUrl !== baseUrl + "/cms-page-not-found") {
            if (!(currentUrl.startsWith(baseUrl + "/cms-user-management") || currentUrl.startsWith(baseUrl + "/cms-registrasi-account"))) {
                window.location = '/group/dealink/cms-page-not-found';
            }
        }
    }

    function checkActiveMenu() {
        console.log(currentUrl, "current url")
        var currentMenu = menusLink.find(function(menu) {
            var linkMenu = baseUrl + menu.link;
            return linkMenu == currentUrl;
        });
        if (currentMenu == undefined){
            currentMenu = { menu : ""}
        }
        if (!menus.includes(currentMenu.menu)) {
            window.location = '/group/dealink/cms-page-not-found';
        }
    }

    function hideMenu() {
        menusLink.forEach(function(item) {
            if (!menus.includes(item.menu)) {
                var itemLink = urlGroup + item.link
                var aTag = $('a[href="'+ itemLink +'"]');
                var parentLi = aTag.closest('li');
                if (parentLi.length) {
                    parentLi.hide();
                }
            }
        });
    }

    function procedureHide() {
        if (noneItemsPresent(menus, menuRepotList)) {
            hideMenuCMS("Report")
        }

        if (noneItemsPresent(menus, menuUploadList)){
            hideMenuCMS("Claim Dealer")
        }

        if (noneItemsPresent(menus, menuDownloadUnitList) && noneItemsPresent(menus, menuDownloadSKList)){
            hideMenuCMS("Upload")
        }

        if (noneItemsPresent(menus, menuDownloadUnitList)){
            hideChildDownloadCMS("Unit")
        }

        if (noneItemsPresent(menus, menuDownloadSKList)){
            hideChildDownloadCMS("SK")
        }
    }

    function noneItemsPresent(allMenu, checkedMenu) {
        return !checkedMenu.some(function(item) {
            return allMenu.includes(item);
        });
    }

    function hideChildDownloadCMS(child){
        var downloadElement = $('span.menu-text').filter(function() {
            return $(this).text().trim() === 'Upload';
        });

        var parentElement = downloadElement.closest('.sidebar-mainmenuitem.has-sub-menu');
        var unit = parentElement.find('span.menu-text').filter(function() {
            return $(this).text().trim() === child;
        });
        if (unit.length > 0) {
            unit.closest("li").hide();
        }
    }

    function hideMenuCMS(menu) {
        var element = $('span.menu-text').filter(function() {
            return $(this).text().trim() === menu;
        });
        element.closest('li').hide();
    }

    function hideOtorisasiMenu() {
        var otorisasiElement = $('span.menu-text').filter(function() {
            return $(this).text().trim() === 'Otorisasi Menu';
        });
        otorisasiElement.closest('li').hide();
    }

    //commennt


</script>
