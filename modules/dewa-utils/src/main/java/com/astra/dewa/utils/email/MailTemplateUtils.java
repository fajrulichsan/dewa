package com.astra.dewa.utils.email;

import com.astra.dewa.model.Common;
import com.astra.dewa.service.CommonLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

public class MailTemplateUtils {

   private static final Log log = LogFactoryUtil.getLog(MailTemplateUtils.class);

   private MailTemplateUtils() {
   }

   public static Common getMailTemplate(String commonKey) {
      try {
         DynamicQuery dynamicQuery = CommonLocalServiceUtil.dynamicQuery();
         dynamicQuery.add(RestrictionsFactoryUtil.eq("CommonKey", commonKey));
         dynamicQuery.add(RestrictionsFactoryUtil.eq("IsActive", true));
         List<Common> commonList = CommonLocalServiceUtil.dynamicQuery(dynamicQuery);
         if (!commonList.isEmpty()) {
            return commonList.get(0);
         } else {
            return null;
         }
      } catch (Exception e) {
         log.error(e);
         return null;
      }
   }

}
