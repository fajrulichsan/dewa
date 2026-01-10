package com.astra.dealink.rest.api.util;

import com.astra.dealink.rest.api.service.TokenServiceImpl;
import com.astra.dewa.model.Setting;
import com.astra.dewa.model.Token;
import com.astra.dewa.service.SettingLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.Random;


public class TokenUtil {
   private static final TokenServiceImpl tokenService = new TokenServiceImpl();
   private static final Log LOG = LogFactoryUtil.getLog(TokenUtil.class);

   public static String generateToken(int length, String characterToken) {
      String token = "";

      StringBuilder randomString = new StringBuilder(length);
      Random random = new Random();

      for (int i = 0; i < length; i++) {
         int index = random.nextInt(characterToken.length());
         char randomChar = characterToken.charAt(index);
         randomString.append(randomChar);
      }
      token = randomString.toString();

      return token;
   }

   public static Boolean validateToken(String tokenNumber) {
      Token token = tokenService.getToken(tokenNumber);
      if (Validator.isNull(token)) {
         return false;
      }
      return !new Date().after(token.getExpiredDate());
   }

   public static String getUsername() {
      try {
         Setting setting = SettingLocalServiceUtil.findCredential("api", "username");
         return setting.getValue();
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
      }
      return "";
   }

   public static String getPassword() {
      try {
         Setting setting = SettingLocalServiceUtil.findCredential("api", "password");
         return setting.getValue();
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
      }
      return "";
   }
}
