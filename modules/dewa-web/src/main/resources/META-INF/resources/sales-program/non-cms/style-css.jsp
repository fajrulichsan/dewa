<%--
  Created by IntelliJ IDEA.
  User: psmahmad1402
  Date: 22/01/2025
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<style>
    @media only screen and (max-width: 767px) {
        .non-cms-table td:not(:nth-child(2)) {
            text-align: right;
        }

        .non-cms-table td:first-child {
            display: none;
        }

        .non-cms-table td:nth-child(2) {
            font-size: 16px;
            white-space: break-spaces !important;
            word-wrap: break-word !important;
        }

        .non-cms-table td:nth-child(3):before {
            content: "Periode";
        }

        .non-cms-table td:nth-child(4):before {
            content: "Tahun";
        }

        .non-cms-table td:nth-child(5):before {
            content: "Tanggal Upload";
        }
    }

    <%--
    .dpn{
        display: none;
    }

    .label-sp{
        margin-bottom: -10px;
    }

    #sales_program_nonCMS_table_length > label{
        visibility: hidden;
    }

    #sales_program_nonCMS_table_length > label > select{
        visibility: visible !important;
    }

    .sales_program_nonCMS {
        margin: 25px;
    }

    .sales_program > .tabcontent {
        box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
        border-radius: 10px;
        padding: 0.75em;
    }

    #sales_program_nonCMS_table > thead {
        border: none !important;
        color: black;
        border-radius: 10px 10px 0 0;
    }

    .table > thead:first-child > tr:first-child > th:first-child {
        border-radius: 10px 0 0 0;
    }

    .table > thead:first-child > tr:first-child > th:last-child {
        border-radius: 0 10px 0 0;
    }

    table#sales_program_nonCMS_table {
        border: 1px solid #DCDFE3;
        border-radius: 10px 10px 10px 10px;
    }

    table#sales_program_nonCMS_table {
        border-radius: 10px 10px 10px 10px;
    }

    #sales_program_nonCMS_table tbody tr {
        border: 1px solid #DCDFE3;
    }

    #sales_program_nonCMS_table > thead > tr > th {
        font-weight: bold;
        text-align: center;
        border: unset !important;
    }

    #approval_table_filter > label > input {
        background-image: url(/o/com.astra.dewa.web/assets/img/search.svg);
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

    /*Select2*/
    .select2-container--default .select2-selection--single {
        padding: 6px;
        margin-top: 16px;
        height: 37px;
        font-size: 1.2em;
        position: relative;
    }

    @media only screen and (max-width: 600px) {
        thead, .dataTables_length{
            display: none;
        }
        table, tbody, tr, td{
            display: block;
        }
        table{
            border: none !important;
        }
        td{
            position: relative;
            border: none !important;
            padding: 3px !important;
        }
        tr{
            padding: 12px;
            margin-bottom: 15px;
            border-radius: 6px;
            border: none !important;
            box-shadow: 0px 8px 20px 0px rgba(13, 12, 34, 0.08);
        }
        td:not(:nth-child(2)){
            text-align: right !important;
        }
        td:first-child{
            display: none;
        }
        td:nth-child(2){
            white-space: break-spaces !important;
            word-wrap: break-word !important;
        }
        td:nth-child(3):before{
            position: absolute;
            left: 8px;
            font-weight: bold;
            content: "Periode Review";
        }
        td:nth-child(4):before{
            position: absolute;
            left: 8px;
            font-weight: bold;
            content: "Tahun";
        }
        td:nth-child(5):before{
            position: absolute;
            left: 8px;
            font-weight: bold;
            content: "Tanggal Upload";
        }
    }
    --%>
</style>