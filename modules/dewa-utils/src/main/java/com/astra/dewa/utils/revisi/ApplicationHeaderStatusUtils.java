package com.astra.dewa.utils.revisi;

import com.astra.dewa.model.ApplicationHeaderStatus;
import com.astra.dewa.service.ApplicationHeaderStatusLocalServiceUtil;
import com.astra.dewa.utils.ApplicationHeaderStatusEnum;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

public class ApplicationHeaderStatusUtils {
   public static JSONArray selects() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = ApplicationHeaderStatusLocalServiceUtil.dynamicQuery()
            .add(RestrictionsFactoryUtil.eq("RowStatus", true))
            .addOrder(OrderFactoryUtil.asc("Id"));
      List<ApplicationHeaderStatus> statuses = ApplicationHeaderStatusLocalServiceUtil.dynamicQuery(query);
      statuses.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         if (data.getId() != 6){
            dto.put("id", data.getId());
            dto.put("text", data.getName());
            jsonData.put(dto);
         }
      });
      return jsonData;
   }

   public static JSONArray selectNoDraft() {
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = ApplicationHeaderStatusLocalServiceUtil.dynamicQuery()
            .add(RestrictionsFactoryUtil.ne("Id", ApplicationHeaderStatusEnum.DRAFT.getId()))
            .add(RestrictionsFactoryUtil.ne("Id", ApplicationHeaderStatusEnum.SUBMIT.getId()))
            .add(RestrictionsFactoryUtil.eq("RowStatus", true))
            .addOrder(OrderFactoryUtil.asc("Id"));
      List<ApplicationHeaderStatus> result = ApplicationHeaderStatusLocalServiceUtil.dynamicQuery(query);
      result.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getId());
         dto.put("text", data.getName());
         jsonData.put(dto);
      });
      return jsonData;
   }

   public static JSONArray selectWaitingStatuses() {
      JSONArray result = JSONFactoryUtil.createJSONArray();
      DynamicQuery query = ApplicationHeaderStatusLocalServiceUtil.dynamicQuery()
            .add(RestrictionsFactoryUtil.in("Id", ApplicationHeaderStatusEnum.waitingStatusIdList()))
            .add(RestrictionsFactoryUtil.eq("RowStatus", true));
      List<ApplicationHeaderStatus> statuses = ApplicationHeaderStatusLocalServiceUtil.dynamicQuery(query);
      statuses.forEach(data -> {
         JSONObject dto = JSONFactoryUtil.createJSONObject();
         dto.put("id", data.getId());
         dto.put("text", data.getName());
         result.put(dto);
      });
      return result;
   }
}
