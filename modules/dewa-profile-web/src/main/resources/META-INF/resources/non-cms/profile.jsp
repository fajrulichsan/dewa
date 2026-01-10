<script>
		(function () {
        var menus = "${menus}";
        var isAdmin = ${isAdmin};
        var isDso = ${isDSO};
        var isAdminDivision = ${isAdminDivision};
        var isHoDealer = ${isHoDealer};
        var baseUrl = window.location.origin + "/group/dealink";

        var menuRepotList = [
            "Faktur Indirect",
            "Sales Report",
            "Report Plafond",
            "Realisasi Diskon Fakpol",
            "Realisasi Diskon Fleet",
            "Realisasi Diskon Test Car",
            "Realisasi Diskon Others"
        ];

        var menuDownloadUnitList = [
            "Faktur Pajak",
            "E-SRUT",
            "Sales Program",
            "Kategori Dealer",
            "Surat Penalty Stock"
        ];

        var menuDownloadSKList = [
            "SP3D",
            "SK IRIS"
        ];

        var isRssp = ${isRssp};
        var isCmsDso = ${isCmsDso};
        var menu;
        var menusLink = [
            {
                menu: "Faktur Indirect",
                link: baseUrl + "/faktur-indirect"
            },
            {
                menu: "Sales Report",
                link: baseUrl + "/sales-report"
            },
            {
                menu: "Report Plafond",
                link: baseUrl + "/report-plafond"
            },
            {
                menu: "Realisasi Diskon Fakpol",
                link: baseUrl + "/realisasi-diskon-fakpol"
            },
            {
                menu: "Realisasi Diskon Fleet",
                link: baseUrl + "/realisasi-diskon-fleet"
            },
            {
                menu: "Realisasi Diskon Test Car",
                link: baseUrl + "/realisasi-diskon-test-car"
            },
            {
                menu: "Realisasi Diskon Others",
                link: baseUrl + "/realisasi-diskon-others"
            },
            {
                menu: "Faktur Pajak",
                link: baseUrl + "/faktur-pajak"
            },
            {
                menu: "E-SRUT",
                link: baseUrl + "/e-srut"
            },
            {
                menu: "Sales Program",
                link: baseUrl + "/sales-program"
            },
            {
                menu: "Kategori Dealer",
                link: baseUrl + "/kategori-dealer"
            },
            {
                menu: "Surat Penalty Stock",
                link: baseUrl + "/surat-penalty-stock"
            },
            {
                menu: "SP3D",
                link: baseUrl + "/sp3d"
            },
            {
                menu: "SK IRIS",
                link: baseUrl + "/sk-iris"
            },
            {
                menu: "Kuitansi Bonus",
                link: baseUrl + "/kuitansi-bonus"
            },
            {
                menu: "Kuitansi Bonus",
                link: baseUrl + "/kuitansi-bonus/buat-request"
            },
            {
                menu: "Kuitansi Bonus",
                link: baseUrl + "/kuitansi-bonus/monitoring-request"
            },
            {
                menu: "Kuitansi Bonus",
                link: baseUrl + "/kuitansi-bonus/hasil-upload-document"
            },
            {
                menu: "Copy STNK Test Car",
                link: baseUrl + "/copy-stnk-test-car"
            },
            {
                menu: "Copy STNK Test Car",
                link: baseUrl + "/copy-stnk-test-car/buat-request"
            },
            {
                menu: "Copy STNK Test Car",
                link: baseUrl + "/copy-stnk-test-car/monitoring-request"
            },
            {
                menu: "Copy STNK Test Car",
                link: baseUrl + "/copy-stnk-test-car/hasil-upload-document"
            },
            {
                menu: "Training",
                link: baseUrl + "/training"
            },
            {
                menu: "Training",
                link: baseUrl + "/materi-pelatihan"
            },
            {
                menu: "Training",
                link: baseUrl + "/pendaftaran-pelatihan"
            },
            {
                menu: "Calender Event",
                link: baseUrl + "/calendar-event"
            }
        ];

        var $download = 'Download';
        var $upload = 'Claim Dealer';
        if (isAdmin || isAdminDivision || isDso) {
            $('.custom-nav-item > li > a:contains("Download")').first().text('Swapping');
            $('.custom-nav-item > li > a:contains("Upload")').first().text('Download').append('&nbsp;<span><b class="caret"></b></span>');
            $('.custom-nav-item > li > a:contains("Swapping")').first().text('Upload').append('&nbsp;<span><b class="caret"></b></span>');
            $download = 'Claim Dealer';
            $upload = 'Download';
        }

        $(document).ready(function () {
            $('.custom-nav-item a:contains("RSSP")').attr('target', '_blank');
            $('.custom-nav-item a:contains("CMS Service")').attr('target', '_blank');
        });

        if (!isAdmin) {
            hideMenu();
            checkActiveMenu();
            procedureHide();
        }

        if (!isRssp) {
            hideMenuRSSP();
        }

        if (!isCmsDso) {
            hideCmsDso();
        }

        function checkActiveMenu() {
            var currentUrl = window.location.href;
            var currentMenu = menusLink.find(function (menu) {
                return menu.link === currentUrl;
            });

            if (currentMenu == undefined) {
                currentMenu = {menu: ""};
            }

            if (!menus.includes(currentMenu.menu)) {
                window.location = '/group/dealink/page-not-found';
            }
        }

        function hideMenu() {
            menusLink.forEach(function (item) {
                if (!menus.includes(item.menu)) {
                    let link = $('.custom-nav-item a[href="' + item.link + '"]');
                    if (link.length) {
                        link.hide();
                    }
                }
            });

            if (!menus.includes("Training")) {
                hideMenuNonCMS("mega-menu-1", "Training");
            }

            if (!menus.includes("Kuitansi Bonus")) {
                hideMenuNonCMS("mega-menu-2", "Kuitansi Bonus");
            }

            if (!menus.includes("Copy STNK Test Car")) {
                hideMenuNonCMS("mega-menu-2", "Copy STNK Test Car");
            }
        }

        function procedureHide() {
            if (noneItemsPresent(menus, menuRepotList)) {
                hideMenuNonCMS("mega-menu-1", "Report");
            }

            if (noneItemsPresent(menus, menuDownloadUnitList) && noneItemsPresent(menus, menuDownloadSKList)) {
                hideMenuNonCMS("mega-menu-1", $download);
            }

            if (noneItemsPresent(menus, menuDownloadUnitList)) {
                var unit = $('.custom-nav-item-mega-2 a[href="' + baseUrl + "/faktur-pajak" + '"]');
                unit.closest("ul").closest("li").hide();
            }

            if (noneItemsPresent(menus, menuDownloadSKList)) {
                hideMenuNonCMS("mega-menu-2", "SK");
            }

            if (!menus.includes("Kuitansi Bonus") && !menus.includes("Copy STNK Test Car")) {
                hideMenuNonCMS("mega-menu-1", $upload);
            }
        }

        function noneItemsPresent(allMenu, checkedMenu) {
            return !checkedMenu.some(function (item) {
                return allMenu.includes(item);
            });
        }

        function hideMenuNonCMS(dataToggle, menu) {
            var element = $('a[data-toggle="' + dataToggle + '"]').filter(function () {
                return $(this).text().trim() === menu;
            })
            element.closest('li').hide()
        }

        function hideAllMenusExceptHome() {
            $('a[data-toggle]').filter(function () {
                return $(this).text().trim() !== 'Home';
            }).closest('li').hide();
        }

        function hideMenuRSSP() {
            $('.custom-nav-item a:contains("RSSP")').closest('li').hide();
            $('.custom-nav-item-mobile a:contains("RSSP")').closest('li').hide();
        }

        function hideCmsDso() {
            $('.custom-nav-item a:contains("CMS Service")').closest('li').hide();
            $('.custom-nav-item-mobile a:contains("CMS Service")').closest('li').hide();
        }
		})();
</script>