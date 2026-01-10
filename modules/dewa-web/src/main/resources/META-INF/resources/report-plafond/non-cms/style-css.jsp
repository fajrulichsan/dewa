<%--
  Created by IntelliJ IDEA.
  User: psmahmad1402
  Date: 16/01/2025
  Time: 9:24
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
            content: "Periode Faktur";
        }

        .non-cms-table td:nth-child(4):before {
            content: "Tanggal Upload";
        }

        .non-cms-table td:nth-child(5) span {
            justify-content: end;
        }
    }
</style>