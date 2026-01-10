<%@ page import="com.liferay.portal.kernel.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: cstagung0825
  Date: 12/10/2022
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="../init.jsp" %>

<style type="text/css">
    .navbar_right {
        display: flex;
        /*width: 230px;*/
    }

    .navbar_right .icon_wrap {
        cursor: pointer;
    }

    .navbar_right .notifications {
        margin-right: 15px;
    }

    .navbar_right .notifications .icon_wrap {
        font-size: 28px;
    }

    .navbar_right .notifications {
        position: relative;
    }

    .notification_dd {
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

    .notification_dd:before {
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

    .notification_dd li .notify_data .title {
        color: #337ab7;
        font-weight: 600;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .notification_dd li .notify_data .sub_title {
        font-size: 12px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        margin-top: 5px;
    }

    .notification_dd li .notify_status p {
        font-size: 12px;
    }

    .notification_dd li.success .notify_status p {
        color: #47da89;
    }

    .notification_dd li.failed .notify_status p {
        color: #fb0001;
    }

    .notification_dd li.show_all {
        display: flex;
        /* padding: 20px;
        justify-content: center; */
    }

    .notification_dd li.show_all a {
        /* font-weight: 700; */
        color: #3b80f9;
        cursor: pointer;
    }

    .notification_dd li.show_all a:hover {
        text-decoration: underline;
    }

    .navbar_right .icon_wrap:hover,
    .navbar_right .notifications.active .icon_wrap {
        color: #3b80f9;
    }

    .notifications.active .notification_dd {
        display: block;
        z-index: 1000;
    }

    .noti_Counter {
        display: none;
        position: absolute;
        margin: 2px 0 0 13px;
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
        z-index: 1;
    }

    .navbar_right.flex-nowrap {
        background-color: white;
        border: 0px;
    }

    .menu-user {
        display: flex;
        gap: 10px;
        margin-left: 10px;
    }

    .profile-img {
        height: 35px;
        width: 35px;
        border-radius: 50%;
        /*border: 2px solid #084996;*/
        /*margin-right: 20px;*/
        /*margin-left: 20px;*/
        margin-top: auto;
        margin-bottom: auto;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #F8FBFF;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
        z-index: 1;
        margin-top: 40px;
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
        background-color: #DEEFFF;
    }


    /* Show the dropdown menu on hover */
    .menu-user:hover .dropdown-content {
        display: block;
    }

    .menu-user-name {
        min-width: 100px;
        max-width: 200px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        font-weight: bold;
        font-size: 14px;
    }

    .menu-company-type {
        color: #B4BEC9;
        min-width: 100px;
        font-size: 12px;
        max-width: 200px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
</style>

<div class="navbar_right flex-nowrap">
   <div class="notifications">
      <div class="noti_Counter" id="countNotif"></div>
      <div class="icon_wrap"><img src="/o/com.astra.dewa.cms/assets/img/bell.svg" style="vertical-align: middle;">
      </div>
      <div class="notification_dd">
         <ul style="padding-inline-start: 0px;">
            <li style="font-size: 16px; color: #041736;weight: 550;margin-top: 5px;">
               Notification
            </li>
         </ul>
         <ul id="listNotif" style="padding-inline-start: 0px;">
         </ul>
         <ul style="padding-inline-start: 0px;">
            <li class="show_all">
               <a href="/group/dewa/notifications-all" style="width:100%; color: #8A93AA; font-size: 12px; text-decoration: underline; ">All Notification
                  <%-- <a href="/group/eportfolio/notifications-all" style="width:100%; color: #8A93AA; font-size: 12px; text-decoration: underline; ">All Notification--%>
                  <img src="/o/com.astra.dewa.cms/assets/img/bell.svg" style="right: 0;position: absolute;margin-right: 20px;margin-top: 4px;">
               </a>
            </li>
         </ul>
      </div>
   </div>
   <div class="d-none d-sm-block topbar-divider" style="border: 1px solid #ECF0F4"></div>
   <div class="menu-user">
      <div class="user-data" style="margin: auto">
         <div class="">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="color:#354052!important;">
               <div style="margin: auto"><img class="profile-img" src="<%=user.getPortraitURL(themeDisplay)%>"></div>
            </a>
         </div>
      </div>
      <div>
         <div class="menu-user-name"><%=user.getFullName()%>
         </div>
         <c:choose>
            <c:when test="${userType eq 0}">
               <div class="menu-company-type">AI HO</div>
            </c:when>
            <c:otherwise>
               <div title="<c:out value="${companyName}" /> - <c:out value="${typeName}" />" class="menu-company-type">
                  <c:out value="${companyName}"/> - <c:out value="${typeName}"/>
               </div>
            </c:otherwise>
         </c:choose>
      </div>
      <div class="dropdown-content">
         <a href="/c/portal/logout">Logout</a>
      </div>
   </div>
</div>