<%--
  Created by IntelliJ IDEA.
  User: psmahmad1402
  Date: 17/01/2025
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<style>
   <%-- content --%>
   .cms-menu {
       margin: 25px;
   }

   .cms-menu > .tabcontent {
       box-shadow: 0 6px 10px rgba(0, 0, 0, 0.42);
       border-radius: 10px;
       padding: 0.75em;
   }

   .menu-title {
       font-size: 24px;
       font-weight: 700;
       margin-bottom: 1em;
   }

   .nav-item {
       min-width: 230px;
       width: auto;
   }

   <%-- table --%>
   .cms-table > thead {
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

   table.cms-table {
       border: 1px solid #DCDFE3;
       border-radius: 10px;
   }

   .cms-table tbody tr {
       border: 1px solid #DCDFE3;
   }

   .cms-table > thead > tr > th {
       font-weight: normal;
       text-align: center;
   }

   .action-wrapper {
       width: inherit;
       display: flex;
       justify-content: center;
       gap: 5px;
   }

   <%-- refresh button --%>
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

   <%-- form fields --%>
   .form-field {
       padding: 25px 20px;
   }

   .form-field input,
   .form-field input::placeholder,
   .table-filters input,
   .table-filters input::placeholder {
       font-size: 16.8px;
   }

   .form-field input,
   .table-filters input {
       cursor: pointer !important;
       height: 40px !important;
       background-color: white !important;
       color: #444 !important;
       border: 1px solid #c7c7c7;
   }

   .upload-file-group {
       margin-bottom: 5px;
   }

   .action-line {
       border-top: 1px solid #D9D9D9;
   <%--
   margin: 10px 15px;
   --%>
   }

   .action-button {
       display: flex;
       gap: 0 10px;
   }

   <%-- filter field --%>
   .table-filters {
       margin: 16px 0 11px 0;
       display: flex;
       justify-content: flex-end;
   }

   .table-filters div[class^="col-"] {
       padding: 0;
   }

   .table-filters div[class^="col"]:not(:first-child) {
       padding-left: 20px;
   }

   <%-- date field --%>
   <%--
   .date-input {
       height: 40px !important;
       background-color: white !important;
       color: #444 !important;
       border: 1px solid gray;
   }
   --%>

   /* Select2 */
   .select2-container--default .select2-selection--single {
       padding: 6px;
       font-size: 1.2em;
       position: relative;
       height: 40px !important;
       border: 1px solid #c7c7c7;
   }

   .select2-container--default .select2-selection--single .select2-selection__placeholder {
       color: #bbb;
   }

   .select2-container--default.select2-container--open.select2-container--below .select2-selection--single,
   .select2-container--default.select2-container--open.select2-container--below .select2-selection--multiple {
       border: 1px solid #DBEDFF;
   }

   <%-- table length --%>
   .dataTables_length .select2-selection.select2-selection--single {
       border: 2px solid #DBEDFF;
   }

   .form-control:focus {
       border: 1px solid #DBEDFF;
   }
</style>
