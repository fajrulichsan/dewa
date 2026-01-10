package com.astra.dewa.utils;

import com.astra.dewa.model.JenisMateri;
import com.astra.dewa.service.JenisMateriLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

public class JenisMateriUtils {

   public static JSONArray selects() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = JenisMateriLocalServiceUtil.dynamicQuery();
      List<JenisMateri> jenisMateris = JenisMateriLocalServiceUtil.dynamicQuery(query);
      jenisMateris.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getId());
         dto.put("text", data.getName());
         jsonData.put(dto);
      });
      return jsonData;
   }

}
