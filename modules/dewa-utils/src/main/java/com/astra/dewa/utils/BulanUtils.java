package com.astra.dewa.utils;

import com.astra.dewa.model.Bulan;
import com.astra.dewa.service.BulanLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

public class BulanUtils {

   public final static String[] MONTH_INDONESIA = {
         "Januari",
         "Februari",
         "Maret",
         "April",
         "Mei",
         "Juni",
         "Juli",
         "Agustus",
         "September",
         "Oktober",
         "November",
         "Desember"
   };

   public static JSONArray getMonthList() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      for (String month : MONTH_INDONESIA) {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", month);
         dto.put("text", month);
         jsonData.put(dto);
      }
      return jsonData;
   }

   public static String getMonthById(int index) {
      return MONTH_INDONESIA[index];
   }

   public static JSONArray selects() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = BulanLocalServiceUtil.dynamicQuery();
      // query.add(RestrictionsFactoryUtil.ne("Active", 1));
      query.addOrder(OrderFactoryUtil.asc("SortIndex"));
      List<Bulan> bulans = BulanLocalServiceUtil.dynamicQuery(query);
      bulans.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getId());
         dto.put("text", data.getName());
         jsonData.put(dto);
      });
      return jsonData;
   }

   public static boolean isMonthExist(String month) {
      for (String string : MONTH_INDONESIA) {
         if (string.equalsIgnoreCase(month)) {
            return true;
         }
      }
      return false;
   }
}
