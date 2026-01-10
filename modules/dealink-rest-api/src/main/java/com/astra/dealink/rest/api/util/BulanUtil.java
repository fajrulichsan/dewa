package com.astra.dealink.rest.api.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BulanUtil {

   public final static String[] MONTH_INDONESIA = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};

   public static String getMonth(int monthId) {
      for (int i = 0; i < MONTH_INDONESIA.length; i++) {
         if (monthId == i) {
            return  MONTH_INDONESIA[i];
         }
      }
      return null;
   }

   public static boolean isMonthExist(String month) {
      for (String s : MONTH_INDONESIA) {
         if (month.equalsIgnoreCase(s)) {
            return true;
         }
      }
      return false;
   }

   private static Map<String, String> kuartalList() {
      HashMap<String, String> kuartalMap = new HashMap<>();
      for (int i = 1; i <= 4; i++) {
         kuartalMap.put("kuartal" + i, "Kuartal " + i);
      }
      TreeMap<String, String> sortedKuartalMap = new TreeMap<>();
      sortedKuartalMap.putAll(kuartalMap);
      return sortedKuartalMap;
   }

   public static String getKuartalId(String kuartalName) {
      Map<String, String> kuartalMap = kuartalList();
      for (Map.Entry<String, String> kuartal: kuartalMap.entrySet()){
         if (kuartalName.equalsIgnoreCase(kuartal.getValue())) {
            return kuartal.getKey();
         }
      }
      return null;
   }

   public static String getKuartalName(int monthIndex) {
      int kuartal = monthIndex / 3 + 1;
      return "Kuartal " + kuartal;
   }

   public static boolean isKuartalExist(String kuartalName) {
      Map<String, String> kuartalMap = kuartalList();
      for (Map.Entry<String, String> kuartal: kuartalMap.entrySet()){
         if (kuartalName.equalsIgnoreCase(kuartal.getValue())) {
            return true;
         }
      }
      return false;
   }

}
