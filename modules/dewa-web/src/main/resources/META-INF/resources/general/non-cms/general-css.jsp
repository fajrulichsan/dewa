<%--
  Created by IntelliJ IDEA.
  User: psmahmad1402
  Date: 20/01/2025
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>

<style>
  .root-div:not(.has-control-menu) .non-cms-menu {
    margin: 25px 0;
  }

  .non-cms-menu > .tabcontent {
    box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
    border-radius: 10px;
    padding: 0.75em;
  }

  /* breadcrumb */
  .breadcrumb {
    background: none;
    color: #333;
  }

  .breadcrumb > li + li:before {
    content: ">";
    color: #333;
  }

  /* title */
  .main-title {
    display: flex;
    justify-content: center;
  }

  .main-title > h3 {
    font-size: 28px;
    font-weight: 600;
    margin-bottom: 16px;
  }

  /* table */
  .non-cms-table {
    border-radius: 10px;
    border: 1px solid white;
  }

  .display-filters .dataTables_length {
    display: block;
  }

  .non-cms-table > thead {
    background-color: white;
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

  table.non-cms-table {
    border: 1px solid #DCDFE3;
    border-radius: 10px;
  }

  .non-cms-table tbody tr {
    border: 1px solid #DCDFE3;
  }

  .non-cms-table > thead > tr > th {
    font-weight: 600;
    text-align: center;
    border: unset !important;
  }

  /* filter button */
  .btn_table, .btn_table:hover {
    border: 0;
    background-color: #014689 !important;
    color: white;
    font-weight: 400;
    opacity: 0.8;
    padding: 8px 16px;
    border-radius: 5px;
    height: 40px;
  }

  /* table filter */
  .table-filters {
    margin: 16px 0;
    display: flex;
    justify-content: flex-end;
  }

  .table-filters .row.sub-filters {
    margin: 0;
  }

  .table-filters > div[class^="col-"],
  .table-filters .sub-filters > div[class^="col-"] {
    padding: 0;
  }

  .table-filters > div[class^="col-"]:first-child,
  .table-filters .sub-filters > div[class^="col-"]:first-child {
    padding-right: 10px;
  }

  .table-filters > div[class^="col-"]:last-child,
  .table-filters .sub-filters > div[class^="col-"]:last-child {
    padding-left: 10px;
  }

  .table-filters > div[class^="col-"]:first-child:last-child {
    padding-right: 0;
  }

  .table-filters > div[class^="col-"]:not(:first-child, :last-child),
  .table-filters .sub-filters > div[class^="col-"]:not(:first-child, :last-child) {
    padding: 0 10px;
  }

  .table-filters input,
  .table-filters input::placeholder {
    font-size: 16.8px;
  }

  .table-filters input {
    height: 40px !important;
    background-color: white !important;
    color: #444 !important;
    cursor: pointer !important;
    border: 1px solid #c7c7c7;
  }

  .period-filter {
    z-index: 0;
  }

  /* Select2 */
  .select2-container--default {
    width: 100% !important;
  }

  .select2-container .select2-selection--single {
    color: #444444;
    background-color: #FFFFFF;
    border: 1px solid #E3E3E3;
    border-radius: 4px;
    box-shadow: none;
    height: 38px;
  }

  .select2-container--default .select2-selection--single .select2-selection__rendered {
    line-height: 38px;
  }

  .select2-container--default .select2-selection--single .select2-selection__arrow {
    height: 34px;
  }

  <%--
  .dataTables_length .select2-selection.select2-selection--single {
    border: 2px solid #DBEDFF;
  }

  .select2-container--default .select2-selection--single {
    padding: 6px;
    height: 40px !important;
    font-size: 1.2em;
    position: relative;
    border: 1px solid #c7c7c7;
  }

  .select2-container--default .select2-selection--single .select2-selection__placeholder {
    color: #bbb;
  }
  --%>

  .form-control:focus {
    border: 1px solid #DBEDFF;
  }

  <%-- responsive --%>
  @media only screen and (max-width: 767px) {
    .breadcrumb {
      margin-bottom: 0;
      padding: 0;
    }

    .main-title {
      justify-content: start;
    }

    .main-title > h3 {
      font-size: 22px;
      font-weight: 600;
      margin-bottom: 6px;
    }

    .display-filters .dataTables_length {
      display: none;
    }

    .table-filters {
      flex-direction: column;
      margin: 16px 0 0 0;
    }

    .table-filters > div[class^="col-"],
    .table-filters .sub-filters > div[class^="col-"] {
      margin-bottom: 10px;
    }

    .table-filters > div[class^="col-"] {
      padding: 0 !important;
    }

    .table-filters .sub-filters div[class^="col-"]:first-child {
      padding-right: 5px;
    }

    .table-filters .sub-filters div[class^="col-"]:last-child {
      padding-left: 5px;
    }

    .dataTables_wrapper .display-filters div.dataTables_length {
      text-align: left;
    }

    .non-cms-table thead {
      display: none;
    }

    table.non-cms-table,
    .non-cms-table tbody,
    .non-cms-table tr,
    .non-cms-table td {
      display: block;
    }

    table.non-cms-table {
      border: none !important;
    }

    .non-cms-table td {
      position: relative;
      border: none !important;
      padding: 3px !important;
      font-size: 16px;
    }

    .non-cms-table tr {
      padding: 12px;
      margin-bottom: 15px;
      border-radius: 6px;
      border: none !important;
      /*box-shadow: 0 8px 20px 0 rgba(13, 12, 34, 0.08);*/
      box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.2);
    }

    .non-cms-table td:not(:nth-child(2)):before {
      position: absolute;
      left: 3px;
      font-weight: 600;
    }

    .non-cms-table.dataTable td.dataTables_empty {
      display: block;
    }

    .non-cms-menu > .tabcontent {
      box-shadow: none;
      border-radius: 0;
      padding: 0;
    }

    li.paginate_button.active > a {
      z-index: 0;
    }
  }
</style>