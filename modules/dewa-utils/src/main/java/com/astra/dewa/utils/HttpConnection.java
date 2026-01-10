package com.astra.dewa.utils;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.List;
import java.util.Map;

public class HttpConnection {
   public static JSONObject doHttpPostJsonRequest(String url, String requestBody, Map<String, String> headers) {
      JSONObject result = null;

      _log.debug("Make HTTP Request");
      _log.debug("URL: " + url);

      try {
         HttpPost httpPost = new HttpPost(url);

         if (Validator.isNotNull(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
               httpPost.setHeader(entry.getKey(), entry.getValue());
            }
         }

         StringEntity stringEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
         httpPost.setEntity((HttpEntity) stringEntity);

         CloseableHttpClient client = HttpClients.createDefault();
         ResponseHandler<String> responseHandler = new BasicResponseHandler();
         String response = client.execute(httpPost, responseHandler);
         result = JSONFactoryUtil.createJSONObject(response);

         client.close();
      } catch (Exception e) {
         _log.error(e.getMessage(), e);
      }

      return result;
   }

   public static JSONObject doHttpPostEncodedRequest(String url, List<NameValuePair> urlParameters, Map<String, String> headers) {
      JSONObject result = null;

      _log.debug("Make HTTP Request");
      _log.debug("URL: " + url);

      try {
         HttpPost httpPost = new HttpPost(url);
         httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));

         if (Validator.isNotNull(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
               httpPost.setHeader(entry.getKey(), entry.getValue());
            }
         }

         CloseableHttpClient client = HttpClients.createDefault();
         ResponseHandler<String> responseHandler = new BasicResponseHandler();
         String response = client.execute(httpPost, responseHandler);
         result = JSONFactoryUtil.createJSONObject(response);

         client.close();
      } catch (Exception e) {
         _log.error(e.getMessage(), e);
      }

      return result;
   }

   private final static Log _log = LogFactoryUtil.getLog(HttpConnection.class);
}
