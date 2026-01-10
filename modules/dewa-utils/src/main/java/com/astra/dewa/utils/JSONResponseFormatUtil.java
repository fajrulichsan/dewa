package com.astra.dewa.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class JSONResponseFormatUtil {

   public static JSONObject FORMAT(int acknowledge, long totalData, int limit, int page, int start, int end, JSONArray data, JSONObject error) {
      return JSONFactoryUtil.createJSONObject()
         .put("Acknowledge", acknowledge)
         .put("TotalData", totalData)
         .put("Limit", limit)
         .put("Page", page)
         .put("Start", start)
         .put("End", end)
         .put("Data", data)
         .put("Error", error);
   }

   public static JSONObject FORMAT(long recordsTotal, long recordsFiltered, int draw, JSONArray data) {
      return JSONFactoryUtil.createJSONObject()
         .put("draw", draw)
         .put("recordsTotal", recordsTotal)
         .put("recordsFiltered", recordsFiltered)
         .put("data", data);
   }

   public static JSONObject SUCCESS(String message, String entryId) {
      return JSONFactoryUtil.createJSONObject()
         .put("acknowledge", 0)
         .put("status", "success")
         .put("message", message)
         .put("entryId", entryId);
   }

   public static JSONObject SUCCESS(String message, String entryId, JSONObject data) {
      return JSONFactoryUtil.createJSONObject()
         .put("acknowledge", 0)
         .put("status", "success")
         .put("message", message)
         .put("entryId", entryId)
         .put("data", data);
   }

   public static JSONObject SUCCESS(int code, String message) {
      return JSONFactoryUtil.createJSONObject()
         .put("code", code)
         .put("message", message);
   }

   public static JSONObject FORMAT(JSONObject data, JSONObject error) {
      return JSONFactoryUtil.createJSONObject()
         .put("Data", data)
         .put("Error", error);
   }

   public static JSONObject NOT_SUCCESS(String message) {
      return JSONFactoryUtil.createJSONObject()
         .put("acknowledge", 1)
         .put("status", "error")
         .put("message", message);
   }

   public static JSONObject WARNING(String message) {
      return JSONFactoryUtil.createJSONObject()
         .put("acknowledge", 1)
         .put("status", "warning")
         .put("message", message);
   }

   public static JSONObject format(int acknowledge, JSONObject data, JSONObject error) {
      return JSONFactoryUtil.createJSONObject()
         .put("Acknowledge", acknowledge)
         .put("Data", data)
         .put("Error", error);
   }

   public static JSONObject ERROR(String message) {
      return JSONFactoryUtil.createJSONObject()
         .put("acknowledge", 0)
         .put("status", "warning")
         .put("message", message);
   }

   public static JSONObject ERROR(int code, String message) {
      return JSONFactoryUtil.createJSONObject()
         .put("code", code)
         .put("message", message);
   }

   public static JSONObject DELETED_SUCCESS(String message, String entryId) {
      return JSONFactoryUtil.createJSONObject()
         .put("acknowledge", 1)
         .put("status", "success")
         .put("message", message)
         .put("entryId", entryId);
   }

   public static JSONObject DELETED_NOT_SUCCESS(String message, String entryId) {
      return JSONFactoryUtil.createJSONObject()
         .put("acknowledge", 0)
         .put("status", "warning")
         .put("message", message)
         .put("entryId", entryId);
   }

   public static JSONObject getCommonFormat(int acknowledge, String status, String message) {
      return JSONFactoryUtil
              .createJSONObject()
              .put("acknowledge", acknowledge)
              .put("status", status)
              .put("message", message);
   }

   public static JSONObject select2Format(int id, String text) {
       return JSONFactoryUtil
              .createJSONObject()
              .put("id", id)
              .put("text", text);
   }

   public static JSONObject select2Format(String id, String text) {
      return JSONFactoryUtil
              .createJSONObject()
              .put("id", id)
              .put("text", text);
   }

   public static JSONObject select2ResponseFormat(int acknowledge, JSONArray data, String status, String message) {
      return JSONFactoryUtil
              .createJSONObject()
              .put("acknowledge", acknowledge)
              .put("data", data)
              .put("status", status)
              .put("message", message);
   }

   public static JSONObject fileUploadResponseFormat(int acknowledge, String status, String message, long fileId, String fileURL, String fileName) {
      return JSONFactoryUtil
              .createJSONObject()
              .put("acknowledge", acknowledge)
              .put("status", status)
              .put("message", message)
              .put("fileURL", fileURL)
              .put("fileID", fileId)
              .put("fileName", fileName);
   };
}
