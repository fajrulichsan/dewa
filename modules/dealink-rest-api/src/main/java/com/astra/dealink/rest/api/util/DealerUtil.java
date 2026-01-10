package com.astra.dealink.rest.api.util;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

import java.util.List;

public class DealerUtil {

    public static Boolean isDealerExist (String dealerCode){

        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        query.add(RestrictionsFactoryUtil.eq("Code", dealerCode));
        List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);

        if(dealers.isEmpty()){
            return false;
        }

        return true;
    }
    public static boolean isHODealerExist (String dealerCode){
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        query.add(RestrictionsFactoryUtil.eq("Code", dealerCode));
        query.add(RestrictionsFactoryUtil.eq("GroupDealer", 0));
        List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);

        if(dealers.isEmpty()){
            return false;
        }

        return true;
    }

    public static int getDealerId(String dealerCode) {
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        query.add(RestrictionsFactoryUtil.eq("Code", dealerCode));
        List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);
        return dealers.get(0).getId();
    }

    public  static  String getDealerName(String dealerCode){
        DynamicQuery query = DealerLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
        query.add(RestrictionsFactoryUtil.eq("Code", dealerCode));
        List<Dealer> dealers = DealerLocalServiceUtil.dynamicQuery(query);
        return dealers.get(0).getName();
    }

}
