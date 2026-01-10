package com.astra.dewa.utils;

import com.astra.dewa.model.Wilayah;
import com.astra.dewa.service.WilayahLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WilayahUtils {

   public static JSONArray selects() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = WilayahLocalServiceUtil.dynamicQuery();
      //query.add(RestrictionsFactoryUtil.ne("Active", 1));
      List<Wilayah> wilayahs = WilayahLocalServiceUtil.dynamicQuery(query);
      wilayahs.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getId());
         dto.put("text", data.getName());
         jsonData.put(dto);
      });
      return jsonData;
   }
   public static JSONArray selectsSK() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = WilayahLocalServiceUtil.dynamicQuery();
      List<String> SKList = new ArrayList<>();
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<Wilayah> wilayahs = WilayahLocalServiceUtil.dynamicQuery(query);
      wilayahs.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         if (!SKList.contains(data.getSK())) {
            SKList.add(data.getSK());
            dto.put("id", data.getId());
            dto.put("text", data.getSK());
         }
         jsonData.put(dto);
      });
      return jsonData;
   }
}
