package com.astra.dewa.utils.api.service;

import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.GroupDealerEnum;
import com.astra.dewa.utils.RoleUtils;
import com.astra.dewa.utils.api.constants.CmsDsoApiConstants;
import com.astra.dewa.utils.exception.DealinkAPIException;
import com.astra.dewa.utils.api.DealinkApiUtil;
import com.liferay.portal.kernel.json.JSONArray;
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
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * @author psmahmad1402
 */
public class CmsDsoService {
    private static final Log _log = LogFactoryUtil.getLog(CmsDsoService.class);

    private static JSONObject sync(
            String email,
            int status,
            String firstName,
            String lastName,
            String mobilePhone,
            String roleCode,
            JSONArray userAreas,
            String createdBy,
            String createdTime,
            String lastModifiedBy,
            String lastModifiedTime
    ) throws Exception {
        _log.info("==== SYNC CMS DSO USER ====");

        // response
        JSONObject result = JSONFactoryUtil.createJSONObject();

        // request parameters
        JSONObject params = JSONFactoryUtil.createJSONObject()
                .put("email", email)
                .put("status", status)
                .put("firstName", firstName)
                .put("lastName", lastName)
                .put("mobilePhone", mobilePhone)
                .put("roleCode", roleCode)
                .put("userAreas", userAreas)
                .put("createdBy", createdBy)
                .put("createdTime", createdTime)
                .put("lastModifiedBy", lastModifiedBy)
                .put("lastModifiedTime", lastModifiedTime);

        _log.info("CMS DSO request params: " + params.toString());

        // http request
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            StringEntity stringEntity = new StringEntity(params.toString(), StandardCharsets.UTF_8);
            HttpPost httpPost = new HttpPost(CmsDsoApiConstants.SYNC_URL);
            httpPost.setEntity(stringEntity);

            // request headers
            httpPost.setHeader("Accept", "*/*");
            httpPost.setHeader("Content-Type", ContentTypes.APPLICATION_JSON);

            // authorization
            String auth = CmsDsoApiConstants.USERNAME + ":" + CmsDsoApiConstants.PASSWORD;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            httpPost.setHeader("Authorization", "Basic " + encodedAuth);

            // additional headers
            httpPost.setHeader("ClientTag", CmsDsoApiConstants.CLIENT_TAG);
            httpPost.setHeader("CompanyCode", CmsDsoApiConstants.COMPANY_CODE);

            // _log.info("Header: " + Arrays.toString(httpPost.getAllHeaders()));
            // _log.info("SYNC CMS DSO: " + SYNC_URL);
            // _log.info("String entity: " + stringEntity);
            // _log.info("Params: " + params);

            CloseableHttpResponse response = client.execute(httpPost);

            _log.info("Response: " + response);
            int statusCode = response.getStatusLine().getStatusCode();
            _log.info("Response status: " + statusCode);

            if (statusCode != HttpServletResponse.SC_OK
                    && statusCode != HttpServletResponse.SC_CREATED
                    && statusCode != HttpServletResponse.SC_ACCEPTED
            ) {
                throw new HttpResponseException(statusCode, "CMS DSO synchronize request failed with status: " + statusCode);
            }

            // response entity value
            if (response.getEntity() != null) {
                JSONObject responseCreateUser = DealinkApiUtil.getResponseValue(response);
                if (null == responseCreateUser) throw new NullPointerException();

                _log.info(responseCreateUser.toString());
                result = responseCreateUser;
            }

            response.close();
        } catch (Exception e) {
            _log.error("Failed to sync CMS DSO user: " + e.getMessage(), e);
            throw e;
        }

        _log.info("==== SYNC CMS DSO COMPLETED ====");
        return result;
    }

    public static void checkRole(
            String email,
            int status,
            String firstName,
            String lastName,
            String mobilePhone,
            int groupDealer,
            JSONArray userAreas,
            String createdBy,
            Date createdDate,
            String modifiedBy,
            Date modifiedDate,
            List<Integer> roles
    ) throws Exception {
        _log.info("==== CHECKING CMS DSO STATUS IN REQUESTED ROLE ====");

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

        try {
            // formatted created date
            String createdDateString = DateUtil.formatDate(createdDate, CmsDsoApiConstants.DATE_FORMAT, ZoneId.systemDefault());

            // formatted modified date
            String modifiedDateString = DateUtil.formatDate(modifiedDate, CmsDsoApiConstants.DATE_FORMAT, ZoneId.systemDefault());

            String roleCode = "";
            if (groupDealer == GroupDealerEnum.DEALER_CABANG.ordinal()) {
                roleCode = "Dealer-CMSDealer";
            } else if (groupDealer == GroupDealerEnum.HO_DEALER.ordinal()) {
                roleCode = "HODealer-CMSDealer";
            }

            boolean isContainCmsDsoRole = false;
            for (Integer roleId : roles) {
                if (RoleUtils.isCmsDsoRole(roleId)) {
                    isContainCmsDsoRole = true;
                    jsonObject = sync(
                            email,
                            status,
                            firstName,
                            lastName,
                            mobilePhone,
                            roleCode,
                            userAreas,
                            createdBy,
                            createdDateString,
                            modifiedBy,
                            modifiedDateString
                    );
                }
            }

            // success
            if (isContainCmsDsoRole) {
                int acknowledge = jsonObject.getInt("acknowledge", -1);
                if (acknowledge == 1) {
                    _log.info("==== CMS DSO USER SYNCHRONIZE SUCCESS ====");
                } else {
                    _log.info("Failed to sync CMS DSO role with acknowledge: " + acknowledge);
                }
            }

            _log.info("==== CMS DSO ROLE CHECKING COMPLETE ====");
        } catch (Exception e) {
            _log.error("Failed to sync CMS DSO role: " + e.getMessage(), e);
            throw new DealinkAPIException("Failed to sync CMS DSO role.");
        }
    }
}
