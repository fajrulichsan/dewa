package com.astra.dewa.utils.api.constants;

import com.astra.dewa.utils.api.DealinkApiUtil;

/**
 * @author psmahmad1402
 */
public class CmsDsoApiConstants {
    public static final String SYNC_URL = DealinkApiUtil.getCredential("cmsdso", "SYNC_USER_URL");
    public static final String USERNAME = DealinkApiUtil.getCredential("cmsdso", "USERNAME");
    public static final String PASSWORD = DealinkApiUtil.getCredential("cmsdso", "PASSWORD");
    public static final String CLIENT_TAG = DealinkApiUtil.getCredential("cmsdso", "CLIENT_TAG");
    public static final String COMPANY_CODE = DealinkApiUtil.getCredential("cmsdso", "COMPANY_CODE");
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
}
