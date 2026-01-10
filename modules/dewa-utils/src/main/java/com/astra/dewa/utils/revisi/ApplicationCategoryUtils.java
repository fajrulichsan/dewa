package com.astra.dewa.utils.revisi;

import com.astra.dewa.model.ApplicationCategory;
import com.astra.dewa.service.ApplicationCategoryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

public class ApplicationCategoryUtils {

   public static JSONArray selects(int categoryBonus) {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = ApplicationCategoryLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("CategoryBonus", categoryBonus));
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      query.addOrder(OrderFactoryUtil.asc("Id"));
      List<ApplicationCategory> categories = ApplicationCategoryLocalServiceUtil.dynamicQuery(query);
      categories.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getId());
         dto.put("text", data.getName());
         jsonData.put(dto);
      });
      return jsonData;
   }

}
