package com.astra.dewa.utils.api.service;

import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.api.constants.RsspApiConstants;
import com.astra.dewa.utils.RoleUtils;
import com.astra.dewa.utils.exception.DealinkAPIException;
import com.astra.dewa.utils.api.DealinkApiUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * @author psmfajru1107
 */
public class RSSPService {
    private static final Log _log = LogFactoryUtil.getLog(RSSPService.class);

    public static JSONObject syncUserRSSP(
            String email,
            String dealerCode,
            String groupDealer,
            String roleId,
            String createdBy,
            String createdTime,
            String lastModifiedBy,
            String lastModifiedTime,
            int status
    ) throws Exception {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        _log.info("==== SYNC RSSP USER ====");

        StringBuilder formBody = new StringBuilder();

        formBody.append("{");
        formBody.append("\"syncUserRSSP\": {");
        formBody.append("\"email\": \"").append(email).append("\",");
        formBody.append("\"code\": \"").append(dealerCode).append("\",");
        formBody.append("\"groupdealer\": \"").append(groupDealer).append("\",");
        formBody.append("\"Id\": \"").append(roleId).append("\",");
        formBody.append("\"createdBy\": \"").append(createdBy).append("\",");
        formBody.append("\"createdTime\": \"").append(createdTime).append("\",");
        formBody.append("\"lastModifiedBy\": \"").append(lastModifiedBy).append("\",");
        formBody.append("\"lastModifiedTime\": \"").append(lastModifiedTime).append("\",");
        formBody.append("\"status\": ").append(status);
        formBody.append("}");
        formBody.append("}");

        _log.info("RSSP request params: " + formBody);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            StringEntity stringEntity = new StringEntity(formBody.toString());
            HttpPost httpPost = new HttpPost(RsspApiConstants.URL_RSSP);
            httpPost.setEntity(stringEntity);

            // request headers
            httpPost.setHeader("Accept", "*/*");
            httpPost.setHeader("Content-type", ContentTypes.APPLICATION_JSON);

            // authorization
            String username = RsspApiConstants.CLIENT_ID_RSSP;
            String password = RsspApiConstants.CLIENT_SECRET_RSSP;
            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            httpPost.setHeader("Authorization", "Basic " + encodedAuth);

            CloseableHttpResponse response = client.execute(httpPost);

            _log.info("Response: " + response);
            int statusCode = response.getStatusLine().getStatusCode();
            _log.info("Response status: " + statusCode);

            if (statusCode != HttpServletResponse.SC_OK
                    && statusCode != HttpServletResponse.SC_CREATED
                    && statusCode != HttpServletResponse.SC_ACCEPTED
            ) {
                throw new HttpResponseException(statusCode, "RSSP synchronize request failed with status: " + statusCode);
            }

            // response entity value
            if (response.getEntity() != null) {
                JSONObject responseCreateUser = DealinkApiUtil.getResponseValue(response);
                if (null == responseCreateUser) throw new NullPointerException();

                _log.info(responseCreateUser.toString());
                jsonObject = responseCreateUser;
            }

            response.close();
        } catch (Exception e) {
            _log.error("Failed to sync RSSP user: " + e.getMessage(), e);
            throw e;
        }

        _log.info("==== SYNC RSSP COMPLETED ====");
        return jsonObject;
    }

    /**
     * Determines whether the given roles is assigned as RSSP role. <br>
     * Synchronizes if role(s) are found.
     *
     * @param email            the email of the user.
     * @param dealerCode       the code of the dealer from the user.
     * @param groupDealer      the group id of the dealer code,
     *                         0 is HO Dealer,
     *                         1 is Dealer.
     * @param roles            the roles of the user.
     * @param createdBy        the active user's or following user's credential.
     * @param createdTime      the date when the user's data created.
     * @param lastModifiedBy   the active user's or following user's credential.
     * @param lastModifiedTime the date when the user's data created or modified.
     * @param status           the activation status of the rssp role. <br>
     *                         1 to CREATE. <br>
     *                         0 to DELETE.
     */
    public static void rsspRoleCheck(
            String email,
            String dealerCode,
            int groupDealer,
            List<Integer> roles,
            String createdBy,
            Date createdTime,
            String lastModifiedBy,
            Date lastModifiedTime,
            int status
    ) throws Exception {
        _log.info("==== CHECKING RSSP STATUS IN REQUESTED ROLE ====");

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

        try {
            /*
            _log.debug("Email: " + email);
            _log.debug("Dealer code: " + dealerCode);
            _log.debug("Group dealer: " + groupDealer);
            _log.debug("Requested roles: " + roles);
            _log.debug("Status: " + status);
            */

            boolean containsRsspRoles = false;
            for (Integer role : roles) {
                if (RoleUtils.isRsspRole(role)) {
                    containsRsspRoles = true;
                    jsonObject = syncUserRSSP(
                            email,
                            dealerCode,
                            String.valueOf(groupDealer),
                            String.valueOf(role),
                            createdBy,
                            DateUtil.dateToString(createdTime, "yyyy-MM-dd HH:mm:ss.SSS"),
                            lastModifiedBy,
                            DateUtil.dateToString(lastModifiedTime, "yyyy-MM-dd HH:mm:ss.SSS"),
                            status
                    );
                }
            }

            // success
            if (containsRsspRoles) {
                int acknowledge = jsonObject.getInt("acknowledge", -1);
                if (acknowledge == 1) {
                    _log.info("==== RSSP USER SYNCHRONIZE SUCCESS ====");
                } else {
                    _log.info("Failed to sync RSSP role with acknowledge: " + acknowledge);
                }
            }

            _log.info("==== RSSP ROLE CHECKING COMPLETE ====");
        } catch (Exception e) {
            _log.error("Failed to sync CMS DSO role: " + e.getMessage(), e);
            throw new DealinkAPIException("Failed to sync RSSP role.");
        }
    }
}
