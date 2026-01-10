package com.astra.dealink.rest.api.util;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;

import javax.ws.rs.core.Response;

public class ResponseUtil {
   private static final Log _log = LogFactoryUtil.getLog(ResponseUtil.class);

   public static Response CREATED(int acknowledge, String dealerCode, String message) {
      String response = JSONUtil.getJsonResponse(acknowledge, dealerCode, message).toJSONString();
      _log.info(response);
      return Response
            .status(Response.Status.CREATED)
            .entity(response)
            .type(ContentTypes.APPLICATION_JSON)
            .build();
   }

   public static Response BAD_REQUEST(int acknowledge, String dealerCode, String message) {
      String response = JSONUtil.getJsonResponse(acknowledge, dealerCode, message).toJSONString();
      _log.error(response);
      return Response
            .status(Response.Status.BAD_REQUEST)
            .entity(response)
            .type(ContentTypes.APPLICATION_JSON)
            .build();
   }

   public static Response UNAUTHORIZED(int acknowledge, String message, String error) {
      String response = JSONUtil.createResponseJson(acknowledge, message, error).toJSONString();
      return Response
            .status(Response.Status.UNAUTHORIZED)
            .entity(response)
            .type(ContentTypes.APPLICATION_JSON)
            .build();
   }

   public static Response CONFLICT(int acknowledge, String dealerCode, String message) {
      String response = JSONUtil.getJsonResponse(acknowledge, dealerCode, message).toJSONString();
      _log.error(response);
      return Response
            .status(Response.Status.CONFLICT)
            .entity(JSONUtil.getJsonResponse(acknowledge, dealerCode, message).toJSONString())
            .type(ContentTypes.APPLICATION_JSON)
            .build();
   }

   public static Response INTERNAL_SERVER_ERROR(int acknowledge, String dealerCode, String message) {
      String response = JSONUtil.getJsonResponse(acknowledge, dealerCode, message).toJSONString();
      _log.error(response);
      return Response
            .status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(response)
            .type(ContentTypes.APPLICATION_JSON)
            .build();
   }

   public static Response REQUEST_TIMEOUT(int acknowledge, String dealerCode, String message) {
      String response = JSONUtil.getJsonResponse(acknowledge, dealerCode, message).toJSONString();
      _log.error(response);
      return Response
            .status(Response.Status.REQUEST_TIMEOUT)
            .entity(response)
            .type(ContentTypes.APPLICATION_JSON)
            .build();
   }

   public static Response getResponse(int acknowledge, String dealerCode, String message, Response.Status status) {
      String response = JSONUtil.getJsonResponse(acknowledge, dealerCode, message).toJSONString();
      _log.info(response);
      return Response
              .status(status)
              .entity(response)
              .type(ContentTypes.APPLICATION_JSON)
              .build();
   }


   public static Response getAPIResponse(int acknowledge, String dealerCode, String message, Response.Status responseStatus) {
      String status;
      if (acknowledge == 0) {
         status = "Failed!";
      } else {
         status = "Success!";
      }
      JSONObject response = JSONFactoryUtil.createJSONObject()
              .put("acknowledge", acknowledge)
              .put("message", message)
              .put("status", status)
              .put("dealer_code", dealerCode);

      _log.info("Dealink API response: " + response);

      return Response
              .status(responseStatus)
              .entity(response.toJSONString())
              .type(ContentTypes.APPLICATION_JSON)
              .build();
   }
}
