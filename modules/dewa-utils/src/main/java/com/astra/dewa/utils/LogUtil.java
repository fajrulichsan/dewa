package com.astra.dewa.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class LogUtil {
   private static final Log LOG = LogFactoryUtil.getLog(LogUtil.class);

   public static void logStatus(int acknowledge, int entryId, String crudType, String menuName, int dealerId, String fileName, String message) {
      int length = getOpeningBar(menuName);
      getAckStatus(acknowledge, message);
      getCrudStatus(entryId, crudType, dealerId, fileName);
      getClosingBar(length);
   }

   public static void logStatus(int acknowledge, int entryId, String crudType, String menuName, int dealerId, String fileName, String invoiceDate, String uploadDate, String message) {
      int length = getOpeningBar(menuName);
      getAckStatus(acknowledge, message);
      getCrudStatus(entryId, crudType, dealerId, fileName);

      if (null != invoiceDate) {
         LOG.info("Tanggal faktur: " + invoiceDate);
      }

      if (null != uploadDate) {
         LOG.info("Tanggal upload: " + uploadDate);
      }

      getClosingBar(length);
   }

   private static int getOpeningBar(String menuName) {
      String openingBar = "==== " + menuName + " Menu ====";
      LOG.info(openingBar);
      return openingBar.length();
   }

   private static void getAckStatus(int acknowledge, String message) {
      if (acknowledge == 0) {
         LOG.error("Status: failed!");
         LOG.error("Message: " + message);
      } else if (acknowledge == 1) {
         LOG.info("Status: success!");
      }
   }

   private static void getCrudStatus(int entryId, String action, int dealerId, String fileName) {
      LOG.info("Entry ID: " + entryId);
      LOG.info("Aksi: " + action + " data");
      LOG.info("Nama file: " + fileName);

      if (!action.equalsIgnoreCase("delete")) {
         String dealerCode = DealerUtils.getDealerCode(dealerId);
         LOG.info("Kode dealer: " + dealerCode);
      }
   }

   private static void getClosingBar(int length) {
      String closingBar = "";

      for (int i = 0; i < length; i++) {
         closingBar = closingBar.concat("=");
      }

      LOG.info(closingBar);
   }
}
