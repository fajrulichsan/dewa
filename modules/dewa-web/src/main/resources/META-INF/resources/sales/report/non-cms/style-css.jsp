<%--
  Created by IntelliJ IDEA.
  User: psmahmad1402
  Date: 15/01/2025
  Time: 11:10
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
            white-space: break-spaces !important;
            word-wrap: break-word !important;
        }

        .non-cms-table td:nth-child(3):before {
            content: "Periode Faktur";
        }

        .non-cms-table td:nth-child(4):before {
            content: "Tanggal Upload";
        }

        .non-cms-table td:nth-child(4) {
            justify-content: end;
        }
    }
</style>