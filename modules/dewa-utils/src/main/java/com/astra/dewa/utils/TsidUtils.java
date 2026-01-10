package com.astra.dewa.utils;

import com.github.f4b6a3.tsid.TsidCreator;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author jepesoft
 */
public class TsidUtils {

   public static String stringTsid() {
      return TsidCreator.getTsid4096().toLowerCase();
   }

   public static Long longTsid() {
      return TsidCreator.getTsid4096().getUnixMilliseconds();
   }

   public static String randomString(int length) {
      return RandomStringUtils.randomAlphanumeric(length);
   }

}
