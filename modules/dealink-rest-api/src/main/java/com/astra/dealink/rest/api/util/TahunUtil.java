package com.astra.dealink.rest.api.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TahunUtil {

   private static List<Integer> yearList() {
      Calendar calendar = Calendar.getInstance();
      int currentYear = calendar.get(Calendar.YEAR);
      List<Integer> yearMap = new ArrayList<>();
      for (int i = currentYear - 1; i <= currentYear + 3; i++) {
         yearMap.add(i);
      }
      return yearMap;
   }

   public static boolean isYearExist(int year) {
      List<Integer> yearMap = yearList();
      for (int i : yearMap) {
         if (i == year) {
            return true;
         }
      }
      return false;
   }

}
