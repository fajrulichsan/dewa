package com.astra.dewa.utils;

import com.astra.dewa.service.ApplicationHeaderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;

import java.util.List;

public class UploadUtils {
   public static String autoCode() {
      DynamicQuery query = ApplicationHeaderLocalServiceUtil.dynamicQuery();
      query.setProjection(ProjectionFactoryUtil.max("TicketCode"));
      List<String> autoCodes = ApplicationHeaderLocalServiceUtil.dynamicQuery(query);

      String auto = autoCodes.get(0);
      if (auto == null) {
         auto = "00001";
      } else {
         int angka = Integer.parseInt(auto) + 1;
         if (angka <= 9) {
            auto = "0000" + angka;
         } else if (angka <= 99) {
            auto = "000" + angka;
         } else if (angka <= 999) {
            auto = "00" + angka;
         } else if (angka <= 9999) {
            auto = "0" + angka;
         } else if (angka <= 99999) {
            auto = "" + angka;
         }
      }
      return auto;
   }

}
