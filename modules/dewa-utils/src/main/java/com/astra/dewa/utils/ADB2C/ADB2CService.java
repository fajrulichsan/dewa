package com.astra.dewa.utils.ADB2C;

import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.UserRoleTypeLocalServiceUtil;
import com.astra.dewa.service.UsersDealersLocalServiceUtil;
import com.astra.dewa.utils.ADB2C.constants.APIConstants;
import com.astra.dewa.utils.JSONResponseFormatUtil;
import com.astra.dewa.utils.RoleUtils;
import com.astra.dewa.utils.RolesEnum;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ADB2CService {
    private static final Log _log = LogFactoryUtil.getLog(ADB2CService.class);

    /**
     * This method is used to create a user in User Management menu
     * by registering the user in ADB2C first
     * then create the user in Liferay
     *
     * @param dealerId       requested dealer id
     * @param requestRoleIds requested list of role
     * @param fullName       requester full name
     * @param email          requester email
     * @param userScreenName logged user screen name
     * @param serviceContext active service context
     * @return status of User Creation, TRUE or FALSE
     */
    public static UsersDealers createUserDEWA(
            Integer dealerId,
            List<Integer> requestRoleIds,
            String fullName,
            String email,
            String userScreenName,
            ServiceContext serviceContext
    ) {
        UsersDealers result = null;
        try {
            String token = getToken();

            // CREATE ADB2C ACCOUNT
            JSONObject newUser = createNewUserAPI(token, fullName, email);
            String ADB2CId = newUser.getString("id");
            String UPN = newUser.getString("UPN");

            JSONObject userLiferay = createUserLiferaySide(email, fullName, requestRoleIds, serviceContext);
            String messageValue = userLiferay.getString("message");
            if (messageValue.equalsIgnoreCase("success")) {
                _log.info("==== CREATE USER LIFERAY SIDE SUCCESS ====");
                String entryId = userLiferay.getString("entryId");
                _log.info(entryId);
                Integer userId = Integer.valueOf(entryId);
                _log.info(userId);

                // ADD NEW USER INTO USER MANAGEMENT FOR EACH ROLE
                result = createToUserManagement(userId, dealerId, requestRoleIds, fullName, email, userScreenName, ADB2CId, UPN);
            }
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * This method is used to get Token from ADB2C API
     *
     * @return the response Token from the API
     */
    public static String getToken() {
        String token = null;
        _log.info("==== GET TOKEN ====");
        List<NameValuePair> urlEncodedParam = new ArrayList<>();
        urlEncodedParam.add(new BasicNameValuePair("scope", APIConstants.getScope()));
        urlEncodedParam.add(new BasicNameValuePair("client_id", APIConstants.getCLientId()));
        urlEncodedParam.add(new BasicNameValuePair("client_secret", APIConstants.getClientSecret()));
        urlEncodedParam.add(new BasicNameValuePair("grant_type", APIConstants.getGrantType()));
        JSONObject httpResponse = doHttpPostEncodedRequest(APIConstants.getTokenURL(), urlEncodedParam, null);
        _log.info("response token : " + httpResponse.toString());
        if (httpResponse != null) {
            token = httpResponse.getString("access_token");
        }
        return token;
    }

    /**
     * This method is used to register a new user to the ADB2C database
     *
     * @param token    the token bearer generated from getToken() method
     * @param fullName the requester full name
     * @param email    the requester email
     * @return ADB2C user ID, Principal Name, and display name of the requester
     */
    public static JSONObject createNewUserAPI(String token, String fullName, String email) {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        _log.info("==== CREATE NEW USER ====");

        StringBuilder formBody = new StringBuilder();

        formBody.append("{");
        formBody.append("\"displayName\": \"" + fullName + "\",");

        formBody.append("\"identities\": [");
        formBody.append("{");
        formBody.append("\"signInType\": \"emailAddress\",");
        formBody.append("\"issuer\": \"" + APIConstants.getTenant() + "\",");
        formBody.append("\"issuerAssignedId\": \"" + email + "\"");
        formBody.append("}");
        formBody.append("],");

        formBody.append("\"passwordProfile\": {");
        formBody.append("\"password\": \"Jakarta@2023\",");
        formBody.append("\"forceChangePasswordNextSignIn\": true");
        formBody.append("}");

        formBody.append("}");

        try {
            StringEntity stringEntity = new StringEntity(formBody.toString());
            _log.debug("stringEntity : " + stringEntity);
            if (Validator.isNull(token)) {
                token = getToken();
            }
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(APIConstants.ADB2C_URL);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Accept", "*/*");
            httpPost.setHeader("Authorization", token);
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);

            _log.debug("httpEntity : " + response.getEntity());
            _log.info("status : " + response.getStatusLine().getStatusCode());

            JSONObject responseCreateUser;
            if (response.getStatusLine().getStatusCode() == 201) {
                if (response.getEntity() != null) {
                    responseCreateUser = getResponseValue(response);
                    _log.info(responseCreateUser.toString());
                    String id = responseCreateUser.getString("id");
                    String displayName = responseCreateUser.getString("displayName");
                    String UPN = responseCreateUser.getString("userPrincipalName");
                    jsonObject.put("id", id);
                    jsonObject.put("displayName", displayName);
                    jsonObject.put("UPN", UPN);
                    _log.info("json : " + jsonObject.toString());
                }
            }
            response.close();
            client.close();
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
        return jsonObject;
    }

    /**
     * This method is used to delete a user from ADB2C database
     *
     * @param token   the token bearer generated from getToken() method
     * @param idADB2C the ADB2C ID of the user
     * @return response status of the user deletion
     */
    public static JSONObject deleteUserADB2C(String token, String idADB2C) {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        _log.info("==== DELETE USER ADB2C SIDE ====");
        try {
            if (Validator.isNull(token)) {
                token = getToken();
            }
            CloseableHttpClient client = HttpClients.createDefault();
            HttpDelete httpDelete = new HttpDelete(APIConstants.ADB2C_URL + "/" + idADB2C);
            httpDelete.setHeader("Accept", "*/*");
            httpDelete.setHeader("Authorization", token);
            CloseableHttpResponse response = client.execute(httpDelete);

            _log.debug("httpEntity : " + response.getEntity());
            _log.info("status : " + response.getStatusLine().getStatusCode());

            if (response.getStatusLine().getStatusCode() == 204) {
                jsonObject = JSONResponseFormatUtil.SUCCESS("Success delete user", "");
            }
            response.close();
            client.close();
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
        return jsonObject;
    }

    /**
     * This method is used to convert value of the HTTP response into JSON Object
     *
     * @param jsonResponse the HTTP response value of the ADB2C API
     * @return response value in JSON Object format
     */
    public static JSONObject getResponseValue(HttpResponse jsonResponse) {
        if (Validator.isNotNull(jsonResponse))
            try {
                InputStream in = jsonResponse.getEntity().getContent();
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                StringBuilder jsonData = new StringBuilder();
                String line;
                while ((line = streamReader.readLine()) != null)
                    jsonData.append(line + "\n");
                return JSONFactoryUtil.createJSONObject(jsonData.toString());
            } catch (Exception e) {
                _log.error(e.getMessage(), e);
                return null;
            }
        return null;
    }

    public static JSONObject doHttpPostEncodedRequest(String url, List<NameValuePair> urlParameters, Map<String, String> headers) {
        JSONObject result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));

            if (headers != null) {
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

    private static UsersDealers createToUserManagement(
            Integer userId,
            Integer dealerId,
            List<Integer> requestRoleIds,
            String fullName,
            String email,
            String createBy,
            String ADB2CId,
            String UPN
    ) {
        UsersDealers result = null;
        try {
            UsersDealers usersDealers = UsersDealersLocalServiceUtil.createUsersDealers(0);
            usersDealers.setUserId(userId);
            usersDealers.setDealerId(dealerId);
            usersDealers.setFullName(fullName);
            usersDealers.setUserEmail(email);
            usersDealers.setADB2CId(ADB2CId);
            usersDealers.setUserPrincipalName(UPN);
            usersDealers.setApprovedDate(new Date());
            usersDealers.setRowStatus(true);
            usersDealers.setCreatedBy(createBy);
            usersDealers.setCreatedDate(new Date());
            usersDealers.setModifiedBy(createBy);
            usersDealers.setModifiedDate(new Date());
            result = UsersDealersLocalServiceUtil.createDealerUserWithRoles(usersDealers, requestRoleIds);
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
        return result;
    }

    public static JSONObject deleteUserDEWA(Long userId, String idADB2C) {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        try {
            String token = getToken();
            JSONObject deleteUser = deleteUserADB2C(token, idADB2C);
            String messageValue = deleteUser.getString("status");
            if (messageValue.equalsIgnoreCase("success")) {
                _log.info("==== DELETE USER ADB2C SIDE SUCCESS ====");
                result = deleteUserLiferaySide(userId);
            }
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
        return result;
    }

    private static JSONObject createUserLiferaySide(String email, String fullName, List<Integer> requestRoleIds, ServiceContext serviceContext) {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        try {
            String screenName = "";
            String firstName = "";
            String lastName = "";
            boolean male = true; // true untuk pria, false untuk wanita
            String password = "password";
            String confirmPassword = "password";
            boolean autoPassword = false;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String[] nameParts = fullName.split("\\s+", 2);
            String filteredName;
            if (nameParts.length == 2) {
                firstName = nameParts[0];
                lastName = nameParts[1];
                filteredName = nameParts[0].replaceAll("[^a-zA-Z0-9]", "");
                screenName = filteredName + "_" + sdf.format(new Date());
            } else {
                firstName = fullName;
                lastName = fullName;
                filteredName = fullName.replaceAll("[^a-zA-Z0-9]", "");
                screenName = filteredName + "_" + sdf.format(new Date());
            }

            _log.info("==== CREATE LIFERAY USER ====");
            User user = UserLocalServiceUtil.addUser(
                    0L, 
                    PortalUtil.getDefaultCompanyId(), 
                    autoPassword, 
                    password, 
                    confirmPassword, 
                    autoPassword,
                    screenName, 
                    email, 
                    java.util.Locale.getDefault(),
                    firstName, 
                    "", 
                    lastName, 
                    0L, 
                    0L, 
                    male, 
                    1, 
                    1,
                    1970, 
                    "", 
                    0,
                    null, 
                    null, 
                    null, 
                    null, 
                    false,
                    serviceContext);
            _log.info("user : " + user.toString());

            _log.info("==== GET LIFERAY DEALER ROLE ====");
            for (Integer roleId : requestRoleIds) {
                Role role = getRoleLiferay(roleId);
                if (role == null) {
                    return JSONResponseFormatUtil.NOT_SUCCESS("Gagal menambahkan role user di liferay");
                }
                _log.info("==== ADD DEWA ROLE TO USER ====");
                UserLocalServiceUtil.addRoleUser(role.getRoleId(), user.getUserId());
                _log.info("==== ADD ROLE SUCCESS ====");
            }

            // ADD USER TO ACCESS DEWA SITE
            _log.info("==== ADD NEW USER TO DEWA SITE ====");
            Group group = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), APIConstants.NAME_SITE);
            GroupLocalServiceUtil.addUserGroup(user.getUserId(), group);

            result = JSONResponseFormatUtil.SUCCESS("success", String.valueOf(user.getUserId()));
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            return JSONResponseFormatUtil.NOT_SUCCESS("Gagal menambahkan user di liferay");
        }
        return result;
    }

    private static JSONObject deleteUserLiferaySide(Long userId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        try {
            _log.info("==== DELETE USER FROM LIFERAY ====");
            User user = UserLocalServiceUtil.deleteUser(userId);
            _log.info("user : " + user.toString());
            result = JSONResponseFormatUtil.SUCCESS("Success delete user at liferay", String.valueOf(userId));
            _log.info("==== DELETE USER LIFERAY SIDE SUCCESS ====");
        } catch (Exception e) {
            _log.error(e.getMessage(), e);

        }
        return result;
    }

    public static Role getRoleLiferay(Integer roleId) {
        Role result = null;

        try {
            if (RoleUtils.isDealer(roleId)) {
                result = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), APIConstants.ROLE_DEALER);
            } else if (RoleUtils.isHODealer(roleId)) {
                result = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), APIConstants.ROLE_HO_DEALER);
            } else if (RoleUtils.isAdminDSO(roleId) || RoleUtils.isAdminDivision(roleId)) {
                result = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), APIConstants.ROLE_ADMIN_DSO);
            } else if (RoleUtils.isDSO(roleId)) {
                result = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), APIConstants.ROLE_DSO);
            }
        } catch (Exception e) {
            _log.error(e.getMessage(), e);

        }
        return result;
    }

    /**
     * This method is used to get Liferay roles of selected user
     *
     * @param userId is the ID of the selected user
     * @return the list of active Liferay roles
     */
    public static List<Integer> getLiferayRoles(long userId) {
        DynamicQuery query = UserRoleTypeLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq("UserId", userId))
                .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                .setProjection(ProjectionFactoryUtil.property("RoleId"));
        List<Integer> roleIds = UserRoleTypeLocalServiceUtil.dynamicQuery(query);
        List<Integer> liferayRoles = new ArrayList<>();
        roleIds.forEach(role -> {
            if ((RoleUtils.isAdminDSO(role) || RoleUtils.isAdminDivision(role)) && !liferayRoles.contains(RolesEnum.ADMIN_DSO.getId()))
                liferayRoles.add(RolesEnum.ADMIN_DSO.getId());
            else if (RoleUtils.isDSO(role) && !liferayRoles.contains(RolesEnum.DSO.getId()))
                liferayRoles.add(RolesEnum.DSO.getId());
            else if (RoleUtils.isHODealer(role) && !liferayRoles.contains(RolesEnum.HO_DEALER.getId()))
                liferayRoles.add(RolesEnum.HO_DEALER.getId());
            else if (RoleUtils.isDealer(role) && !liferayRoles.contains(RolesEnum.DEALER.getId()))
                liferayRoles.add(RolesEnum.DEALER.getId());
        });
        return liferayRoles;
    }

    public static List<String> getUserLiferayRoles(long userId) {
        try {
            User user = UserLocalServiceUtil.getUser(userId);
            List<Role> userLiferayRoles = user.getRoles();
            List<String> userRoles = new ArrayList<>();
            if (!userLiferayRoles.isEmpty()) {
                for (Role role : userLiferayRoles) {
                    userRoles.add(role.getName());
                }
            }
            return userRoles;
        } catch (PortalException e) {
            _log.error(e.getMessage(), e);

            return null;
        }
    }
}
