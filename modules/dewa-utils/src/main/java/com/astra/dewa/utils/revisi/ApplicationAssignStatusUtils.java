package com.astra.dewa.utils.revisi;

import com.astra.dewa.model.ApplicationAssignStatus;
import com.astra.dewa.service.ApplicationAssignStatusLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

public class ApplicationAssignStatusUtils {

   public static JSONArray selects() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = ApplicationAssignStatusLocalServiceUtil.dynamicQuery();
      query.addOrder(OrderFactoryUtil.asc("Id"));
      List<ApplicationAssignStatus> statuses = ApplicationAssignStatusLocalServiceUtil.dynamicQuery(query);
      statuses.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getId());
         dto.put("text", data.getName());
         jsonData.put(dto);
      });
      return jsonData;
   }

}
