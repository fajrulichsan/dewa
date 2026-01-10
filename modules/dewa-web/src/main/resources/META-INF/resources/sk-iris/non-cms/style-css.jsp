<%--
  Created by IntelliJ IDEA.
  User: psmahmad1402
  Date: 22/01/2025
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<style>
    @media only screen and (max-width: 767px) {
        .non-cms-table td:not(:nth-child(2)) {
            text-align: right;
        }

        .non-cms-table td:first-child,
        .non-cms-table td:nth-child(4) {
            display: none;
        }

        .non-cms-table td:nth-child(2) {
            white-space: break-spaces !important;
            word-wrap: break-word !important;
        }

        .non-cms-table td:nth-child(3):before {
            content: "Periode";
        }

        .non-cms-table td:nth-child(5):before {
            content: "Tanggal Upload";
        }

        .non-cms-table td:nth-child(6) span {
            justify-content: end;
        }
    }

    <%--
    .sk_iris {
      margin: 25px;
    }

    #sk_iris_table_length > label{
      visibility: hidden;
    }

    #sk_iris_table_length > label > select{
      visibility: visible !important;
    }

    .sk_iris > .tabcontent {
      border-radius: 10px;
      padding: 0.75em;
    }

    #sk_iris_table{
      border-radius: 10px;
      border: 1px white;
    }

    #sk_iris_table > thead {
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

    table#sk_iris_table {
      border: 1px solid #DCDFE3;
      border-radius: 10px 10px 10px 10px;
    }

    table#sk_iris_table {
      border-radius: 10px 10px 10px 10px;
    }

    #sk_iris_table tbody tr {
      border: 1px solid #DCDFE3;
    }

    #sk_iris_table > thead > tr > th {
      font-weight: bold;
      text-align: center;
      border : unset !important;
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
      /*margin-bottom: 16px;*/
      height: 37px;
      /*width: 148px;*/
      font-size: 1.2em;
      position: relative;
    }
    .dataTables_length{
      padding: 16px;
    }

    @media only screen and (max-width: 780px) {
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
        left: 3px;
        font-weight: bold;
        content: "Periode";
      }
      td:nth-child(4){
        display: none;
      }
      td:nth-child(5) span{
        justify-content: end;
      }
    }
    --%>
</style>