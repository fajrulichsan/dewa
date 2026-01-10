package com.astra.dewa.utils.ADB2C.constants;

import com.astra.dewa.model.Setting;
import com.astra.dewa.service.SettingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringUtil;

public class APIConstants {

   public static String replace(String sourceText, String[] targetReplace, String[] replaceBy) {
      return StringUtil.replace(sourceText, targetReplace, replaceBy);
   }

   public static String getTenant() {
      try {
         Setting setting = SettingLocalServiceUtil.findCredential("adb2c", "TENANT_ADB2C");
         return setting.getValue();
      } catch (PortalException e) {
         // PASS
      }
      return "";
   }

   public static String getCLientId() {
      try {
         Setting setting = SettingLocalServiceUtil.findCredential("adb2c", "CLIENT_ID_ADB2C");
         return setting.getValue();
      } catch (PortalException e) {
         // PASS
      }
      return "";
   }

   public static String getClientSecret() {
      try {
         Setting setting = SettingLocalServiceUtil.findCredential("adb2c", "CLIENT_SECRET_ADB2C");
         return setting.getValue();
      } catch (PortalException e) {
         // PASS
      }
      return "";
   }

   public static String getURLADB2C() {
      try {
         Setting setting = SettingLocalServiceUtil.findCredential("adb2c", "URL_ADB2C");
         return setting.getValue();
      } catch (PortalException e) {
         // PASS
      }
      return "";
   }

   public static String getScope() {
      try {
         Setting setting = SettingLocalServiceUtil.findCredential("adb2c", "SCOPE_ADB2C");
         return setting.getValue();
      } catch (PortalException e) {
         // PASS
      }
      return "";
   }

   public static String getGrantType() {
      try {
         Setting setting = SettingLocalServiceUtil.findCredential("adb2c", "GRANT_TYPE_ADB2C");
         return setting.getValue();
      } catch (PortalException e) {
         // PASS
      }
      return "";
   }

   public static String getTokenURL() {
      try {
         Setting setting = SettingLocalServiceUtil.findCredential("adb2c", "URL_GET_TOKEN");
         String url = setting.getValue();
         url = replace(
               url,
               new String[]{"[tenant]"},
               new String[]{getTenant()
               });
         return url;
      } catch (PortalException e) {
         // PASS
      }
      return "";
   }

   public static final String TOKEN_URL = getTokenURL();
   public static final String ADB2C_URL = getURLADB2C();
   public static final String NAME_SITE = "dealink";
   public static final String ROLE_DSO = "DewaOnline:DSO";
   public static final String ROLE_DEALER = "DewaOnline:Dealer";
   public static final String ROLE_HO_DEALER = "DewaOnline:HODealer";
   public static final String ROLE_ADMIN_DSO = "DewaOnline:AdminDSO";
}
