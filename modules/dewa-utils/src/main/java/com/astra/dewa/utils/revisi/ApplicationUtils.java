package com.astra.dewa.utils.revisi;

import com.astra.dewa.model.Application;
import com.astra.dewa.service.ApplicationLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

public class ApplicationUtils {

   public static JSONArray selects() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = ApplicationLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      query.addOrder(OrderFactoryUtil.asc("Id"));
      List<Application> applications = ApplicationLocalServiceUtil.dynamicQuery(query);
      applications.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getId());
         dto.put("text", data.getName());
         jsonData.put(dto);
      });
      return jsonData;
   }

   public static String getApplicationName(int applicationId) {
      DynamicQuery query = ApplicationLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      query.add(RestrictionsFactoryUtil.eq("Id", applicationId));
      List<Application> applications = ApplicationLocalServiceUtil.dynamicQuery(query);
      return (applications.isEmpty()) ? null : applications.get(0).getName();
   }
}
