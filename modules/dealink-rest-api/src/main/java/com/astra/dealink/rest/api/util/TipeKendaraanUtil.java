package com.astra.dealink.rest.api.util;

import com.astra.dewa.model.TipeKendaraan;
import com.astra.dewa.service.TipeKendaraanLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

import java.util.List;

public class TipeKendaraanUtil {

   public static boolean isTipeKendaraanExist(String tipeKendaraanName) {
      DynamicQuery query = TipeKendaraanLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      query.add(RestrictionsFactoryUtil.eq("Name", tipeKendaraanName));
      List<TipeKendaraan> tipeKendaraans = TipeKendaraanLocalServiceUtil.dynamicQuery(query);
      return !tipeKendaraans.isEmpty();
   }

   public static int getTipeKendaraanId(String tipeKendaraanName) {
      DynamicQuery query = TipeKendaraanLocalServiceUtil.dynamicQuery();
      query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
      query.add(RestrictionsFactoryUtil.eq("Name", tipeKendaraanName));
      List<TipeKendaraan> tipeKendaraans = TipeKendaraanLocalServiceUtil.dynamicQuery(query);
      return tipeKendaraans.get(0).getId();
   }

}
