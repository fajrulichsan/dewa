package com.astra.dewa.utils;

import com.astra.dewa.model.TipeKendaraan;
import com.astra.dewa.service.TipeKendaraanLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

public class TipeKendaraanUtils {

   public static String getTipeKendaraanName(int tipeKendaraanId) {
      DynamicQuery query = TipeKendaraanLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("Id", tipeKendaraanId));
      List<TipeKendaraan> tipeKendaraans = TipeKendaraanLocalServiceUtil.dynamicQuery(query);
      return tipeKendaraans.get(0).getName();
   }

   public static JSONArray selects() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = TipeKendaraanLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<TipeKendaraan> tipeKendaraans = TipeKendaraanLocalServiceUtil.dynamicQuery(query);
      tipeKendaraans.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getId());
         dto.put("text", data.getName());
         jsonData.put(dto);
      });
      return jsonData;
   }

}
