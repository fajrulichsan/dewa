package com.astra.dewa.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TahunUtils {

   public static List<Integer> yearList() {
      Calendar calendar = Calendar.getInstance();
      int currentYear = calendar.get(Calendar.YEAR);
      List<Integer> yearMap = new ArrayList<>();
      for (int i = currentYear - 1; i <= currentYear + 3; i++) {
         yearMap.add(i);
      }
      return yearMap;
   }

   public static JSONArray getYearList() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      List<Integer> yearList = yearList();
      yearList.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data);
         dto.put("text", data);
         jsonData.put(dto);
      });
      return jsonData;
   }

   public static JSONArray listTahun (List<Integer> years) {
      List<Integer> listYear = years;
      JSONArray tahunArray = JSONFactoryUtil.createJSONArray();
      try {
         for(int i=0; i< listYear.size(); i++){
            if(!listYear.contains(i)){
               JSONObject tahunObject = JSONFactoryUtil.createJSONObject();
               tahunObject.put("id", listYear.get(i));
               tahunObject.put("text", listYear.get(i));
               tahunArray.put(tahunObject);
            }
         }
      }catch (Exception e) {
         e.printStackTrace();
      }
      return tahunArray;
   }

}
