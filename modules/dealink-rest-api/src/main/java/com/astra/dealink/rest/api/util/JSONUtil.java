package com.astra.dealink.rest.api.util;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class JSONUtil {

    public static JSONObject createResponseJson(int acknowledge, String message, String error) {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        result.put("Acknowledge", acknowledge);
        result.put("Message", message);
        result.put("Error", error);
        return result;
    }

    public static JSONObject getJsonResponse(int ack, String dealerCode, String message) {
        JSONObject response = JSONFactoryUtil.createJSONObject();
        String status = "";
        if (ack == 0) {
            response.put("Message", message);
            status = "Failed!";
        } else {
            status = "Success!";
        }
        response.put("Status", status);
        response.put("Kode Dealer Cabang", dealerCode);
        return response;
    }
}
