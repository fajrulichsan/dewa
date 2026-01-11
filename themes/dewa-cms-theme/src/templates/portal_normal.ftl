<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<link rel="stylesheet" href="https://fonts.astra.id/fonts/fonts.css">
	<link rel="preload" href="https://fonts.astra.id/fonts/poppins/Poppins-Regular.woff2" as="font" type="font/woff2" crossorigin>

    <title>${the_title} - ${company_name}</title>

    <meta content="initial-scale=1.0, width=device-width" name="viewport"/>

    <@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">


<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<#assign
   showControlMenu = false
   wrapper = ""
/>

<#if is_signed_in>
   <#assign
      roles = user.getRoles()
      showControlMenu = false
      wrapper = ""
   />

   <#list roles as role>
      <#if role.getName() == "Administrator">
         <#assign
            showControlMenu = true
            wrapper = "has-control-menu"
         />
         <#break>
      </#if>
   </#list>

   <#if showControlMenu>
      <@liferay.control_menu />
   </#if>
</#if>

<div class="" id="wrapper" style="padding-left: 0px!important; margin-top: 0px !important;">
    <div class="page-wrapper default-theme toggled">
        <nav id="sidebar" class="sidebar-wrapper">
            <div class="sidebar-content">
                <style type="text/css">
                     <#if showControlMenu == false>
                        .sidebar-wrapper {
                           margin-top: 0 !important;
                        }
                     </#if>

                    .sidebar-content {
                        max-height: calc(100% - 35px);
                        height: calc(100% - 35px);
                        position: relative;
                    }

                    .sidebar-content.desktop {
                        overflow-y: hidden;
                    }

                    .default-theme .sidebar-wrapper {
                        background-color: #F8FBFF;
                        <#if showControlMenu>
                           margin-top: 10px;
                        </#if>
                    }

                    .page-wrapper.default-theme.toggled {
                        <#if showControlMenu>
                           margin-top: 20px;
                        </#if>
                    }

                    .sidebar-wrapper .sidebar-menu ul li a i {
                        width: 10px;
                        height: 10px;
                        border-radius: 50%;
                    }

                    .sidebar-wrapper .sidebar-menu ul li a {
                        height: 52px;
                    }

                </style>
                <div class="sidebar-item sidebar-header d-flex flex-nowrap" style="border-top: none;">
                    <img class="img-responsive" src="${images_folder}/logo_dewa_kanan.png" alt="User picture">
                </div>
                <#if is_signed_in>
                    <#assign roles = user.getRoles() />
                    <div class="sidebar-item sidebar-menu" style="border-top: none;">
                        <div class="header-info" style="padding: 1em 1em 1.5em">
                            <div class="header-sub-info" style="font-size: 14px">Hello, <span
                                        style="font-weight: 600">${user_name}</span></div>
                            <div class="header-detail-info" style="font-size: 14px">Welcome to ${appSiteName}</div>
                        </div>

                        <ul>
                            <#list roles as role>
                                <#if role.getName() == "DewaOnline:AdminDSO" || role.getName() == "Administrator">
                                    <li class="sidebar-mainmenuitem">
                                        <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-home">
                                            <img alt="" src="${images_folder}/home.svg">
                                            <span class="menu-text">Home</span>
                                        </a>
                                    </li>
                                    <#break>
                                </#if>
                            </#list>
                            <li class="sidebar-mainmenuitem has-sub-menu">
                                <a href="javascript:void(0);">
                                    <img alt="" src="${images_folder}/request.svg">
                                    <span class="menu-text">Report</span>
                                </a>

                                <ul class="sidebar-sub-menu">
                                    <li class="sub-menu-item sidebar-sub-menuitem has-sub-menu">
                                        <a href="javascript:void(0);">
                                            <img alt="" src="${images_folder}/unit.svg">
                                            <span class="menu-text">Unit</span>
                                        </a>

                                        <ul class="sidebar-sub-menu">
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-faktur-indirect">
                                                    <i></i>
                                                    <span class="menu-text">Faktur Indirect</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-sales-report">
                                                    <i></i>
                                                    <span class="menu-text">Sales Report</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-report-plafond">
                                                    <i></i>
                                                    <span class="menu-text">Report Plafond</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-realisasi-diskon-fleet">
                                                    <i></i>
                                                    <span class="menu-text">Realisasi Diskon Fleet</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-realisasi-diskon-fakpol">
                                                    <i></i>
                                                    <span class="menu-text">Realisasi Diskon Fakpol</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-realisasi-diskon-test-car">
                                                    <i></i>
                                                    <span class="menu-text">Realisasi Diskon Test Car</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-realisasi-diskon-others">
                                                    <i></i>
                                                    <span class="menu-text">Realisasi Diskon Others</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li class="sidebar-mainmenuitem has-sub-menu">
                                <a href="javascript:void(0);">
                                    <img alt="" src="${images_folder}/upload.svg">
                                    <span class="menu-text">Upload</span>
                                </a>

                                <ul class="sidebar-sub-menu">
                                    <li class="sub-menu-item sidebar-sub-menuitem has-sub-menu">
                                        <a href="javascript:void(0);">
                                            <img alt="" src="${images_folder}/unit.svg">
                                            <span class="menu-text">Unit</span>
                                        </a>

                                        <ul class="sidebar-sub-menu">
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-kategori-dealer">
                                                    <i></i>
                                                    <span class="menu-text">Kategori Dealer</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-surat-penalty-stock">
                                                    <i></i>
                                                    <span class="menu-text">Surat Penalty Stock</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-faktur-pajak">
                                                    <i></i>
                                                    <span class="menu-text">Faktur Pajak</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-e-srut">
                                                    <i></i>
                                                    <span class="menu-text">E-SRUT</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-sales-program">
                                                    <i></i>
                                                    <span class="menu-text">Sales Program</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>

                                    <li class="sub-menu-item sidebar-sub-menuitem has-sub-menu">
                                        <a href="javascript:void(0);">
                                            <img alt="" src="${images_folder}/sk.svg"></i>
                                            <span class="menu-text">SK</span>
                                        </a>

                                        <ul class="sidebar-sub-menu">
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-sp3d">
                                                    <i></i>
                                                    <span class="menu-text">SP3D</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item last-menu-submenu">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-sk-iris">
                                                    <i></i>
                                                    <span class="menu-text">SK IRIS</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>

                                </ul>
                            </li>
                            <li class="sidebar-mainmenuitem has-sub-menu">
                                <a href="javascript:void(0);">
                                    <img alt="" src="${images_folder}/download.svg">
                                    <span class="menu-text">Claim Dealer</span>
                                </a>

                                <ul class="sidebar-sub-menu">
                                    <li class=" sub-menu-item sidebar-mainmenuitem">
                                        <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-upload-kuitansi-bonus">
                                            <i></i>
                                            <span class="menu-text">Kuitansi Bonus</span>
                                        </a>
                                    </li>
                                    <li class=" sub-menu-item sidebar-mainmenuitem">
                                        <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-upload-copy-stnk">
                                            <i></i>
                                            <span class="menu-text">Copy STNK</span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="sidebar-mainmenuitem">
                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-training">
                                    <img alt="" src="${images_folder}/news.svg">
                                    <span class="menu-text">Training</span>
                                </a>
                            </li>
                            <li class="sidebar-mainmenuitem">
                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-calendar-event">
                                    <img alt="" src="${images_folder}/event.svg">
                                    <span class="menu-text">Calendar Event</span>
                                </a>
                            </li>

                            <#list roles as role>
                                <#if role.getName() == "DewaOnline:AdminDSO" || role.getName() == "Administrator">
                                    <li class="sidebar-mainmenuitem has-sub-menu">
                                        <a href="javascript:void(0);">
                                            <img alt="" src="${images_folder}/master-data.svg">
                                            <span class="menu-text">Master Data</span>
                                        </a>

                                        <ul class="sidebar-sub-menu">
                                            <li class=" sub-menu-item sidebar-mainmenuitem">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-master-approval">
                                                    <i></i>
                                                    <span class="menu-text">Master Approval</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item sidebar-mainmenuitem">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-master-dealer">
                                                    <i></i>
                                                    <span class="menu-text">Master Dealer</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item sidebar-mainmenuitem">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-master-role">
                                                    <i></i>
                                                    <span class="menu-text">Master Role</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item sidebar-mainmenuitem">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-wilayah">
                                                    <i></i>
                                                    <span class="menu-text">Wilayah</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item sidebar-mainmenuitem">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-cabang">
                                                    <i></i>
                                                    <span class="menu-text">Cabang</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <#break>
                                </#if>
                            </#list>

                            <#list roles as role>
                                <#if role.getName() == "DewaOnline:AdminDSO" || role.getName() == "Administrator">
                                    <li class="sidebar-mainmenuitem has-sub-menu">
                                        <a href="javascript:void(0);">
                                            <img alt="" src="${images_folder}/management-user.svg">
                                            <span class="menu-text">User Management</span>
                                        </a>

                                        <ul class="sidebar-sub-menu">
                                            <li class=" sub-menu-item sidebar-mainmenuitem">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-user-management">
                                                    <i></i>
                                                    <span class="menu-text">User Management</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item sidebar-mainmenuitem">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-registrasi-account">
                                                    <i></i>
                                                    <span class="menu-text">Registration Account</span>
                                                </a>
                                            </li>
                                            <li class=" sub-menu-item sidebar-mainmenuitem">
                                                <a data-senna-off="true" href="/group${appSiteFriendlyURL}/cms-otorisasi-menu">
                                                    <i></i>
                                                    <span class="menu-text">Otorisasi Menu</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <#break>
                                </#if>
                            </#list>
                        </ul>
                    </div>
                </#if>
            </div>
        </nav>

        <main class="page-content">
            <div id="overlay" class="overlay"></div>
            <div class="container-fluid">
                <section id="content">
                    <div class="row" style="margin-left: 0px!important; margin-right: 0px!important;">
                        <div class="col-sm-12 col-xs-12 col-md-12" style="padding: 0; <#if showControlMenu>margin-top: -20px;</#if>">
                            <#if is_signed_in>
                                <div class="pull-left">
                                    <a id="toggle-sidebar" class="btn">
                                        <i class="fas fa-bars"></i>
                                    </a>
                                </div>
                                <div class="pull-right">
                                    <div class="new-search">
                                        <@liferay_portlet["runtime"]
                                            portletName="com_astra_dewa_profile_web_DewaProfileWebPortlet"
                                        />
                                    </div>
                                </div>
                            <#else>
                                <p>Hanya dapat diakses oleh Admin</p>
                            </#if>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12 col-xs-12 col-md-12" style="padding: 0;border-top: 1px solid #DBEDFF;">
                            <#if selectable>
                                <@liferay_util["include"] page=content_include />
                            <#else>
                                ${portletDisplay.recycle()}

                                ${portletDisplay.setTitle(the_title)}

                                <@liferay_theme["wrap-portlet"] page="portlet.ftl">
                                    <@liferay_util["include"] page=content_include />
                                </@>
                            </#if>
                        </div>
                    </div>
                </section>
            </div>
        </main>
    </div>
</div>

<script type="text/javascript" src="${javascript_folder}/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
    function destroyLoading() {
        $('.loading-layer').remove();
    }

    $(document).ready(function () {
        $(".sidebar-mainmenuitem.has-sub-menu > a, .sidebar-sub-menuitem.has-sub-menu > a").on("click", function () {
            if ($(this).siblings(".sidebar-sub-menu").hasClass("open")) {
                $(this).siblings(".sidebar-sub-menu").slideUp();
                $(this).siblings(".sidebar-sub-menu").removeClass("open");
                $(this).closest(".has-sub-menu").removeClass("open");
            } else {
                $(this).siblings(".sidebar-sub-menu").slideDown();
                $(this).siblings(".sidebar-sub-menu").addClass("open");
                $(this).closest(".has-sub-menu").addClass("open");
            }
        });

        var url = window.location.href;

        $(".sidebar-mainmenuitem > a, .sub-menu-item > a").each(function (index) {
            if (url.indexOf($(this).attr('href')) >= 0) {
                if ($(this).parents(".sidebar-sub-menu").length > 0) {
                    $(this).parents(".sidebar-sub-menu").slideDown();
                    $(this).parents(".sidebar-sub-menu").addClass("open");

                    $(this).parents(".has-sub-menu").addClass("active");
                    $(this).parents(".has-sub-menu").find("> a").addClass("active");
                    $(this).parents(".sidebar-mainmenuitem, .sidebar-sub-menuitem").addClass("open");
                }
                $(this).addClass("active");
                $(this).parent().addClass("active");
            } else if (index == 0 && url.split("/").length < 6) {
                if ($(this).parents(".sidebar-sub-menu").length > 0) {
                    $(this).parents(".sidebar-sub-menu").slideDown();
                    $(this).parents(".sidebar-sub-menu").addClass("open");

                    $(this).parents(".has-sub-menu").addClass("active");
                    $(this).parents(".has-sub-menu").find("> a").addClass("active");
                    $(this).parents(".sidebar-mainmenuitem, .sidebar-sub-menuitem").addClass("open");
                }

                $(this).addClass("active");
                $(this).parent().addClass("active");
            } else if (index == 0 && !url.split("/").at(-1)) {
                if ($(this).parents(".sidebar-sub-menu").length > 0) {
                    $(this).parents(".sidebar-sub-menu").slideDown();
                    $(this).parents(".sidebar-sub-menu").addClass("open");

                    $(this).parents(".has-sub-menu").addClass("active");
                    $(this).parents(".has-sub-menu").find("> a").addClass("active");
                    $(this).parents(".sidebar-mainmenuitem, .sidebar-sub-menuitem").addClass("open");
                }
                $(this).addClass("active");
                $(this).parent().addClass("active");
            }
        });

        $('#overlay').click(function (event) {
            $('.page-wrapper').toggleClass('toggled');
        });

        $("#toggle-sidebar").click(function (event) {
            $(".page-wrapper").toggleClass("toggled");
        });
    });
</script>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>