<%--
  Created by IntelliJ IDEA.
  User: psmahmad1402
  Date: 14/01/2025
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<style>
    .nav-item {
        width: 33%;
    }

    .width-form {
        width: 100%;
    }

    @media only screen and (min-width: 0) {
        .nav-item {
            width: 100%;
        }

        .date-group {
            flex-direction: column;
            gap: 0;
        }

        .invoice-date-field {
            width: 100%;
        }

        .action-line {
            margin: 0;
        }
    }

    @media only screen and (min-width: 577px) {
        .nav-item {
            width: 50%;
        }
    }

    @media only screen and (min-width: 769px) {
        .nav-item {
            width: 200px;
        }

        .action-line {
            margin: 7pc 15px 15px 0;
        }
    }

    @media only screen and (min-width: 993px) {
        .width-form {
            width: 90%;
        }

        .action-line {
            margin: 0;
        }
    }
</style>