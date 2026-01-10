package com.astra.dewa.utils;

import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

public class CabangUtils {
    public static JSONArray selects() throws SystemException {
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        DynamicQuery query = CabangLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("RowStatus", true));
        List<Cabang> result = CabangLocalServiceUtil.dynamicQuery(query);
        result.forEach(data -> {
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("id", data.getId());
            dto.put("text", data.getName());
            jsonData.put(dto);
        });
        return jsonData;
    }

    public static JSONArray selectCabang(int dealerId, int groupDealerId) {
        try {
            JSONArray jsonData = JSONFactoryUtil.createJSONArray();
            Dealer dealer = DealerLocalServiceUtil.getDealer(dealerId);
            DynamicQuery dealerQuery = DealerLocalServiceUtil.dynamicQuery()
                    .add(RestrictionsFactoryUtil.eq("KodeHo", dealer.getKodeHo()))
                    .add(RestrictionsFactoryUtil.eq("GroupDealer", groupDealerId))
                    .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                    .setProjection(ProjectionFactoryUtil.property("CabangId"));
            DynamicQuery cabangQuery = CabangLocalServiceUtil.dynamicQuery()
                    .add(PropertyFactoryUtil.forName("Id").in(dealerQuery))
                    .add(RestrictionsFactoryUtil.eq("RowStatus", true));
            List<Cabang> result = CabangLocalServiceUtil.dynamicQuery(cabangQuery);
            result.forEach(data -> {
                JSONObject dto = JSONFactoryUtil.createJSONObject()
                        .put("id", data.getId())
                        .put("text", data.getName());
                jsonData.put(dto);
            });
            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray selectCabang(int dealerId) {
        try {
            JSONArray jsonData = JSONFactoryUtil.createJSONArray();
            Dealer dealer = DealerLocalServiceUtil.getDealer(dealerId);
            DynamicQuery dealerQuery = DealerLocalServiceUtil.dynamicQuery()
                    .add(RestrictionsFactoryUtil.eq("KodeHo", dealer.getKodeHo()))
                    .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                    .setProjection(ProjectionFactoryUtil.property("CabangId"));
            DynamicQuery cabangQuery = CabangLocalServiceUtil.dynamicQuery()
                    .add(PropertyFactoryUtil.forName("Id").in(dealerQuery))
                    .add(RestrictionsFactoryUtil.eq("RowStatus", true));
            List<Cabang> result = CabangLocalServiceUtil.dynamicQuery(cabangQuery);
            result.forEach(data -> {
                JSONObject dto = JSONFactoryUtil.createJSONObject()
                        .put("id", data.getId())
                        .put("text", data.getName());
                jsonData.put(dto);
            });
            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Cabang getCabang(int cabangId, Boolean rowStatus) {
       DynamicQuery q = CabangLocalServiceUtil
               .dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("Id", cabangId));
       if (null != rowStatus) {
          q.add(RestrictionsFactoryUtil.eq("RowStatus", rowStatus));
       }
       List<Cabang> r = CabangLocalServiceUtil.dynamicQuery(q);
       return r.isEmpty() ? null : r.get(0);
    }
}
