package com.astra.dewa.utils;

import com.astra.dewa.model.TopikMateri;
import com.astra.dewa.service.TopikMateriLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

public class TopikMateriUtils {

   public static JSONArray selects() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = TopikMateriLocalServiceUtil.dynamicQuery();
      List<TopikMateri> jenisMateris = TopikMateriLocalServiceUtil.dynamicQuery(query);
      jenisMateris.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getId());
         dto.put("text", data.getName());
         jsonData.put(dto);
      });
      return jsonData;
   }

}
