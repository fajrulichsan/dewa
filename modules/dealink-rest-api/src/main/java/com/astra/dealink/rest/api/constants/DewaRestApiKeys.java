package com.astra.dealink.rest.api.constants;

import com.astra.dealink.rest.api.util.TokenUtil;

public class DewaRestApiKeys {
    public static final String USER_NAME = TokenUtil.getUsername();
    public static final String PASSWORD = TokenUtil.getPassword();

//    public static final String USER_NAME = "ZLKF1694FW7J7Y4XK0MZSDUZ7N986EO2";
//    public static final String PASSWORD = "9NJQyUvby9zR4T3AkL2XAyVKm4k17gdEMnFTA8cAawkjTuhEdc";

    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final String NAME_SITE = "dealink";
    public static final String DEWA_SITE_FOLDER = "dewa";
    public static final String MENU_DISKON_FAKPOL = "Diskon Fakpol";
    public static final String MENU_DISKON_FLEET = "Diskon Fleet";
    public static final String MENU_DISKON_TEST_CAR = "Diskon Test Car";
    public static final String MENU_DISKON_OTHERS = "Diskon Others";
    public static final String MENU_SP3D = "SP3D";
    public static final String MENU_SKIRIS = "SK-Iris";
    public static final String MENU_E_SRUT = "E-SRUT";
    public static final String SRUT = "SRUT";
    public static final String MENU_FAKTUR_PAJAK = "Faktur Pajak";
    public static final String REKAP_FAKTUR_PAJAK = "Rekap Faktur Pajak";
    public static final String REKAP_FAKTUR_KENDARAAN = "Rekap Faktur Kendaraan";
    public static final String MENU_FAKTUR_INDIRECT = "Faktur Indirect";
    public static final String TEMP_FOLDER = "temp";
    public static final String MENU_SALES_REPORT = "Sales Report";
    public static final String REPORT_PLAFOND = "Report Plafond";

    public static final String DATE_FORMAT_DOT = "dd.MM.yyyy";

    // Extensions
    public static final String EXT_XLS = "xls";
    public static final String EXT_XLSX = "xlsx";
    public static final String EXT_PDF = "pdf";

    public static final String PSP = "PSP";
    public static final String NON_PSP = "Non PSP";
    public static final String API = "API";
    public static final String DISKON_FAKPOL_API_PATH = "/realisasi-diskon-fakpol";
    public static final String DISKON_FLEET_API_PATH = "/realisasi-diskon-fleet";
    public static final String DISKON_TEST_CAR_API_PATH = "/realisasi-diskon-test-car";
    public static final String DISKON_OTHERS_API_PATH = "/realisasi-diskon-others";
}
