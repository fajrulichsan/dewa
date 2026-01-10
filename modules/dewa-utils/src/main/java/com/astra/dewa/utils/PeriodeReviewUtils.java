package com.astra.dewa.utils;

import com.astra.dewa.model.PeriodeReview;
import com.astra.dewa.service.PeriodeReviewLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

public class PeriodeReviewUtils {

   public static JSONArray selects() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = PeriodeReviewLocalServiceUtil.dynamicQuery();
      //query.add(RestrictionsFactoryUtil.ne("Active", 1));
      List<PeriodeReview> bulans = PeriodeReviewLocalServiceUtil.dynamicQuery(query);
      bulans.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getId());
         dto.put("text", data.getName());
         jsonData.put(dto);
      });
      return jsonData;
   }

   public static JSONArray selectPeriode() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();

      for (int i = 1; i <= 12; i++) {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         String period = "Periode " + i;
         dto.put("id", period);
         dto.put("text", period);
         jsonData.put(dto);
      }

      return jsonData;
   }

}
