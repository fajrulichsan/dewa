<%--
  Created by IntelliJ IDEA.
  User: psmahmad1402
  Date: 17/01/2025
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<style>
    <%--
    .e_srut {
        margin: 25px;
    }

    .e_srut > .tabcontent {
        box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
        border-radius: 10px;
        padding: 0.75em;
    }

    .e-srut-filter {
        display: flex;
        flex-direction: row;
        gap: 0 5px;
    }

    .sub-filter {
        padding-left: 0;
        padding-right: 0;
    }

    .period-field {
        margin-top: 15px;
    }

    .period-trigger, .refresh-trigger {
        border: 1px solid #c7c7c7;
        border-radius: 6px;
        margin-top: 15px;
    }

    #e_srut_table_length > label {
        visibility: hidden;
    }

    #e_srut_table_length > label > select {
        visibility: visible !important;
    }

    .nav-item {
        width: 100%;
    }

    #e_srut_table > thead {
        background-color: #014689;
        border: none !important;
        color: white;
        border-radius: 10px 10px 0 0;
    }

    .table > thead:first-child > tr:first-child > th:first-child {
        border-radius: 10px 0 0 0;
    }

    .table > thead:first-child > tr:first-child > th:last-child {
        border-radius: 0 10px 0 0;
    }

    table#e_srut_table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#e_srut_table {
        border-radius: 10px 10px 10px 10px;
    }

    #e_srut_table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #e_srut_table > thead > tr > th {
        font-weight: normal;
        text-align: center;
    }

    #approval_table_filter > label > input {
        background-image: url(<%=request.getContextPath()%>/assets/img/search.svg);
        background-position: 10px 8px;
        background-repeat: no-repeat;
        padding: 5px 20px 5px 40px;
    }

    .btn_table, .btn_table:hover {
        border: 0px;
        background-color: #014689 !important;
        color: white;
        font-weight: 400;
        opacity: 0.8;
        padding: 8px 16px;
        border-radius: 5px;
    }

    #periode, #periodDate {
        border: 1px solid #aaa;
        background-color: white;
        cursor: pointer;
        color: black;
    }

    #periode {
        border-right: none;
        border-radius: 4px 0 0 4px;
    }

    #periodDate {
        border-radius: 4px;
    }

    .width-form {
        width: 100%;
    }

    .action-line {
        border-top: 1px solid #D9D9D9;
        margin: 10px 15px;
    }

    .action-button {
        display: flex;
        gap: 0 10px;
        margin: 1pc 0;
    }

    /*Select2*/
    .select2-container--default .select2-selection--single {
        padding: 6px;
        margin-top: 16px;
        /*margin-bottom: 16px;*/
        height: 37px;
        /*width: 148px;*/
        font-size: 1.2em;
        position: relative;
    }

    @media only screen and (min-width: 0) {
        .action-line {
            margin: 0;
        }
    }

    @media only screen and (min-width: 768px) {
        .nav-item {
            width: 50%;
        }

        .action-line {
            margin: 7pc 15px 15px 0;
        }
    }

    @media only screen and (min-width: 992px) {
        .nav-item {
            width: 25%;
        }

        .width-form {
            width: 90%;
        }

        .action-line {
            margin: 0;
        }
    }
    --%>
</style>