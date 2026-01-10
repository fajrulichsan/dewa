package com.astra.dealink.rest.api.util;

import com.astra.dewa.model.Wilayah;
import com.astra.dewa.service.WilayahLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

import java.util.List;

public class WilayahUtil {

    public static boolean isWilayahExist(String wilayahId){
        DynamicQuery query = WilayahLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("SK",wilayahId));
        List<Wilayah> wilayah = WilayahLocalServiceUtil.dynamicQuery(query);
        if(wilayah.isEmpty()){
            return false;
        }
        return true;
    }
    public static int getWilayahId(String wilayahName){
        DynamicQuery query = WilayahLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("SK",wilayahName));
        List<Wilayah> wilayah = WilayahLocalServiceUtil.dynamicQuery(query);
        return wilayah.get(0).getId();
    }
}
