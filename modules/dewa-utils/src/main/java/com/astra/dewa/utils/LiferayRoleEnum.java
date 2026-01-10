package com.astra.dewa.utils;

/**
 * @author psmahmad1402
 */
public enum LiferayRoleEnum {
    ROLE_DSO("DewaOnline:DSO"),
    ROLE_DEALER("DewaOnline:Dealer"),
    ROLE_HO_DEALER("DewaOnline:HODealer"),
    ROLE_ADMIN_DSO("DewaOnline:AdminDSO");

    private final String name;
    LiferayRoleEnum(String name) { this.name = name; }
    public String getName() { return name; }
}
