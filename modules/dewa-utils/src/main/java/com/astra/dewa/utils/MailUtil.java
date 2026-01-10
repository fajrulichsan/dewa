package com.astra.dewa.utils;

import com.astra.dewa.model.Setting;
import com.astra.dewa.service.EmailDomainLocalServiceUtil;
import com.astra.dewa.service.SettingLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MailUtil {
    public static void sendEmail(String[] recipients, String[] CC, String subject, String content, File[] attachments) throws Exception {
        String accessToken = getAccessToken();
        Setting mailSend = SettingLocalServiceUtil.findCredential("mail", "MAIL_SEND_URL");
        Setting sender = SettingLocalServiceUtil.findCredential("smtp", "username");
        String mailSendUrl = String.format(mailSend.getValue() + sender.getValue() + "/sendMail");

        if (accessToken != null) {
            String bearerToken = String.format("Bearer %s", accessToken);

            JSONObject body = generateEmailBody(recipients, CC, subject, content, attachments);

            System.out.println("Send Email URL: " + mailSendUrl);
            System.out.println("API Body Request: " + body.toString());
            System.out.println("Access Token: " + bearerToken);

            HttpPost httpPost = new HttpPost(mailSendUrl);
            httpPost.setHeader("Authorization", bearerToken);
            StringEntity stringEntity = new StringEntity(body.toString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity((HttpEntity) stringEntity);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute((HttpUriRequest) httpPost);
            int statusCode = response.getStatusLine().getStatusCode();

            System.out.println("Response CODE: " + statusCode);

            if (statusCode == 202) {
                System.out.println("Send Email Successfully");
            } else {
                throw new Exception("Failed to send email");
            }

            client.close();
            response.close();
        }
    }

    public static JSONObject generateEmailBody(String[] recipients, String[] CC, String subject, String content, File[] attachments)
            throws IOException {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        JSONObject message = JSONFactoryUtil.createJSONObject();

        if (Validator.isNotNull(subject)) {
            message.put("subject", subject);
        } else {
            message.put("subject", "");
        }

        JSONArray recipientList = JSONFactoryUtil.createJSONArray();
        for (String recipient : recipients) {
            JSONObject email = JSONFactoryUtil.createJSONObject();
            JSONObject address = JSONFactoryUtil.createJSONObject();
            address.put("address", recipient);
            email.put("emailAddress", address);
            recipientList.put(email);
        }
        message.put("toRecipients", recipientList);

        // If CC Not NULL
        if (Validator.isNotNull(CC)) {
            JSONArray ccRecipientList = JSONFactoryUtil.createJSONArray();
            for (String recipient : CC) {
                JSONObject email = JSONFactoryUtil.createJSONObject();
                JSONObject address = JSONFactoryUtil.createJSONObject();
                address.put("address", recipient);
                email.put("emailAddress", address);
                ccRecipientList.put(email);
            }
            message.put("ccRecipients", ccRecipientList);
        }

        JSONObject emailBody = JSONFactoryUtil.createJSONObject();
        emailBody.put("contentType", "HTML");
        emailBody.put("content", content);
        message.put("body", emailBody);

        // If Attachment not null
        if (Validator.isNotNull(attachments)) {
            JSONArray attachmentList = JSONFactoryUtil.createJSONArray();
            for (File file : attachments) {
                MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
                String mimeType = mimeTypesMap.getContentType(file);
                String fileEncoded = encodeFileToBase64Binary(file);

                JSONObject attachment = JSONFactoryUtil.createJSONObject();
                attachment.put("@odata.type", "#microsoft.graph.fileAttachment");
                attachment.put("name", file.getName());
                attachment.put("contentType", mimeType);
                attachment.put("contentBytes", fileEncoded);
                attachmentList.put(attachment);
            }
            message.put("attachments", attachmentList);
        }

        JSONArray headers = JSONFactoryUtil.createJSONArray();
        JSONObject header2 = JSONFactoryUtil.createJSONObject();

        header2.put("name", "x-custom-group-name");
        header2.put("value", "DEWA");

        headers.put(header2);
        message.put("internetMessageHeaders", headers);

        result.put("message", message);
        result.put("saveToSentItems", true);

        return result;
    }

    private static String encodeFileToBase64Binary(File file) throws IOException {
        byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
        return new String(encoded, StandardCharsets.US_ASCII);
    }

    public static String getAccessToken() throws Exception {
        String token = null;

        Setting clientId = SettingLocalServiceUtil.findCredential("mail", "CLIENT_ID");
        Setting clientSecret = SettingLocalServiceUtil.findCredential("mail", "CLIENT_SECRET");
        Setting scope = SettingLocalServiceUtil.findCredential("mail", "SCOPE");
        Setting grantType = SettingLocalServiceUtil.findCredential("mail", "GRANT_TYPE");
        Setting tokenUrl = SettingLocalServiceUtil.findCredential("mail", "TOKEN_URL");

        List<NameValuePair> urlEncodedParam = new ArrayList<>();
        urlEncodedParam.add(new BasicNameValuePair("client_id", clientId.getValue()));
        urlEncodedParam.add(new BasicNameValuePair("client_secret", clientSecret.getValue()));
        urlEncodedParam.add(new BasicNameValuePair("scope", scope.getValue()));
        urlEncodedParam.add(new BasicNameValuePair("grant_type", grantType.getValue()));
        JSONObject httpResponse = doHttpPostEncodedRequest(tokenUrl.getValue(), urlEncodedParam, null);
        if (httpResponse != null) {
            token = httpResponse.getString("access_token");
        }

        return token;
    }

    public static JSONObject doHttpPostEncodedRequest(String url, List<NameValuePair> urlParameters, Map<String, String> headers) throws Exception {
        JSONObject result = null;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String response = client.execute(httpPost, responseHandler);
            result = JSONFactoryUtil.createJSONObject(response);
        } catch (Exception e) {
            if (e instanceof HttpResponseException) {
                throw new IOException(e);
            } else {
                throw new Exception(e);
            }
        }

        return result;
    }

    public static JSONObject doHttpGetEncodedRequest(String url, List<NameValuePair> urlParameters, Map<String, String> headers) {
        JSONObject result = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            if (Validator.isNotNull(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }

            if (Validator.isNotNull(urlParameters)) {
                URI uri = new URIBuilder(httpGet.getURI())
                        .addParameters(urlParameters)
                        .build();
                httpGet.setURI(uri);
            }
            CloseableHttpClient client = HttpClients.createDefault();
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String response = client.execute(httpGet, responseHandler);
            result = JSONFactoryUtil.createJSONObject(response);

            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String replace(String sourceText, String[] targetReplace, String[] replaceBy) {
        return StringUtil.replace(sourceText, targetReplace, replaceBy);
    }

    public static JSONObject bodyCalendarEmail(String[] participan, String subjects, String contents, String startDate, String endDate) {
        JSONObject message = JSONFactoryUtil.createJSONObject();
        String subject = subjects;
        String content = contents;


        message.put("subject", subject);

        JSONObject emailBody = JSONFactoryUtil.createJSONObject();
        emailBody.put("contentType", "HTML");
        emailBody.put("content", content);
        message.put("body", emailBody);

        JSONObject start = JSONFactoryUtil.createJSONObject();
        start.put("dateTime", startDate);
        start.put("timeZone", "Asia/Bangkok");
        message.put("start", start);

        JSONObject end = JSONFactoryUtil.createJSONObject();
        end.put("dateTime", endDate);
        end.put("timeZone", "Asia/Bangkok");
        message.put("end", end);

        JSONObject location = JSONFactoryUtil.createJSONObject();
        location.put("displayName", "AMDI");
        message.put("location", location);

        if (Validator.isNotNull(participan)) {
            JSONArray attendees = JSONFactoryUtil.createJSONArray();
            for (String recipient : participan) {
                JSONObject email = JSONFactoryUtil.createJSONObject();
                JSONObject address = JSONFactoryUtil.createJSONObject();
                address.put("address", recipient);
                email.put("emailAddress", address);
                email.put("type", "required");
                attendees.put(email);
            }
            message.put("attendees", attendees);
        }

        message.put("allowNewTimeProposals", true);

        return message;
    }

    public static void sendEmailAndCalendar(String[] recipients, String[] CC, String subject, String content, String startDate, String endDate) throws Exception {
        Setting urlSendEmailCalendar = SettingLocalServiceUtil.findCredential("mail", "URL_SEND_EMAIL_CALENDAR");
        String accessToken = getAccessToken();

        if (accessToken != null) {
            String bearerToken = String.format("Bearer %s", accessToken);

            JSONObject body = bodyCalendarEmail(recipients, subject, content, startDate, endDate);

            System.out.println("Send Calendar URL: " + urlSendEmailCalendar.getValue());
            System.out.println("API Body Request: " + body.toString());
            System.out.println("Access Token: " + bearerToken);

            HttpPost httpPost = new HttpPost(urlSendEmailCalendar.getValue());
            httpPost.setHeader("Authorization", bearerToken);
            StringEntity stringEntity = new StringEntity(body.toString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity((HttpEntity) stringEntity);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute((HttpUriRequest) httpPost);
            int statusCode = response.getStatusLine().getStatusCode();


            System.out.println("Response CODE: " + statusCode);

            if (statusCode == 201) {
                System.out.println("Send Calendar Successfully");
            } else {
                System.out.println("Failed to Send Calendar");
            }

            client.close();
            response.close();
        }
    }

    public static boolean isDomainValid(int domainId) {
        try {
            DynamicQuery query = EmailDomainLocalServiceUtil.dynamicQuery()
                    .add(RestrictionsFactoryUtil.eq("Id", domainId))
                    .add(RestrictionsFactoryUtil.eq("RowStatus", true));
            long result = EmailDomainLocalServiceUtil.dynamicQueryCount(query);
            return result != 0;
        } catch (SystemException se) {
            se.printStackTrace();
            return false;
        }
    }
}
