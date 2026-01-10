package com.astra.dewa.utils.api;

import com.astra.dewa.model.Setting;
import com.astra.dewa.service.SettingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author psmahmad1402
 */
public class DealinkApiUtil {
    private static final Log _log = LogFactoryUtil.getLog(DealinkApiUtil.class);

    /**
     * Retrieves Setting
     * @param keySetting
     * @param code
     * @return
     */
    public static String getCredential(String keySetting, String code) {
        try {
            Setting setting = SettingLocalServiceUtil.findCredential(keySetting, code);
            return setting.getValue();
        } catch (PortalException e) {
            _log.warn("Failed to get Credential: " + e.getMessage(), e);
        }
        return "";
    }

    public static JSONObject getResponseValue(HttpResponse jsonResponse) {
        if (Validator.isNotNull(jsonResponse))
            try {
                InputStream in = jsonResponse.getEntity().getContent();
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                StringBuilder jsonData = new StringBuilder();
                String line;
                while ((line = streamReader.readLine()) != null)
                    jsonData.append(line).append("\n");
                return JSONFactoryUtil.createJSONObject(jsonData.toString());
            } catch (Exception e) {
                _log.error("Failed to get response value: " + e.getMessage(), e);
                return null;
            }
        return null;
    }
}
