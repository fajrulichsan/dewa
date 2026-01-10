package com.astra.dewa.utils;

public enum RolesEnum {
   ADMIN_DSO(1, "Administrator"),
   HO_DEALER(2, "HO Dealer"),
   DEALER(3, "Dealer"),
   DSO(4, "DSO"),
   ADMIN_DIVISION(5, "Admin");
   private final int id;
   private final String name;
   RolesEnum(int id, String name) { this.id = id; this.name = name; }
   public int getId() { return id; }
   public String getName() { return name; }
}
