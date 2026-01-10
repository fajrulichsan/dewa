package com.astra.dewa.login.command.action;

import com.astra.dewa.login.command.util.RegistrationUtil;
import com.astra.dewa.login.constants.DewaLoginKeys;
import com.astra.dewa.login.constants.DewaLoginPortletKeys;
import com.astra.dewa.model.ApprovalHeaderUser;
import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.Roles;
import com.astra.dewa.model.Setting;
import com.astra.dewa.service.ApprovalHeaderUserLocalServiceUtil;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.RolesLocalServiceUtil;
import com.astra.dewa.service.SettingLocalServiceUtil;
import com.astra.dewa.utils.ApplicationAssignStatusEnum;
import com.astra.dewa.utils.FilterXSS;
import com.astra.dewa.utils.InputValidationUtils;
import com.astra.dewa.utils.MailUtil;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RoleUtils;
import com.liferay.portal.kernel.captcha.CaptchaTextException;
import com.liferay.portal.kernel.captcha.CaptchaUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.StringJoiner;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

/**
 * @author psmmutia0113
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaLoginPortletKeys.REGISTER,
                "mvc.command.name=/register/action"
        },
        service = MVCResourceCommand.class
)
public class RegisterActionCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(RegisterActionCommand.class);

    @SuppressWarnings("deprecation")
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);

        boolean isRequestContainsXSS = false;
        Enumeration<String> attributes = resourceRequest.getParameterNames();
        while (attributes.hasMoreElements()) {
            String param = attributes.nextElement();
            String value = resourceRequest.getParameter(param);
            if (FilterXSS.checkXSS(value)) {
                _log.warn(value + " contains XSS payload");
                isRequestContainsXSS = true;
                break;
            }
        }

        attributes = uploadPortletRequest.getParameterNames();
        while (attributes.hasMoreElements()) {
            String param = attributes.nextElement();
            String value = uploadPortletRequest.getParameter(param);
            if (FilterXSS.checkXSS(value)) {
                _log.warn(value + " contains XSS payload");
                isRequestContainsXSS = true;
                break;
            }
        }

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        if (isRequestContainsXSS) {
            jsonObject = ERROR("Oops! Something went wrong.");
        } else {
            String fullName = ParamUtil.getString(resourceRequest, "fullName");
            String email = ParamUtil.getString(resourceRequest, "email");
            String roles = ParamUtil.getString(resourceRequest, "roles");
            String action = ParamUtil.getString(resourceRequest, "action");
            int dealerId = ParamUtil.getInteger(resourceRequest, "dealerId");
            int cabangId = ParamUtil.getInteger(resourceRequest, "cabangDealerId");
            int captchaText = ParamUtil.getInteger(resourceRequest, "captchaText");
            int otpNumber = ParamUtil.getInteger(resourceRequest, "otpNumber");

            try {
                _log.info("==== DEWA Registration ====");
                _log.info("Full name: " + fullName);
                _log.info("Email: " + email);
                _log.info("Dealer: " + dealerId);
                _log.info("Cabang: " + cabangId);
                _log.info("Roles: " + roles);
                _log.info("CAPTCHA text: " + captchaText);
                _log.info("===========================");

                JSONArray jsonRoles = JSONFactoryUtil.createJSONArray(roles);
                if (action.equalsIgnoreCase("VALIDATE")) {
                    if (!RoleUtils.isRoleGroupValid(jsonRoles)) {
                        jsonObject = ERROR("Role yang dipilih harus dalam satu group (ex: HO Dealer)");
                    } else {
                        if (!roles.isEmpty()) {
                            int roleGroupId = RoleUtils.getRoleGroupId(jsonRoles.getJSONObject(0).getInt("id"));
                            jsonObject = SUCCESS("", String.valueOf(roleGroupId));
                        }
                    }
                } else if (action.equalsIgnoreCase("REGISTER")) {
                    // Input Validation
                    if (!InputValidationUtils.isBasicCharacter(fullName)) {
                        jsonObject = ERROR("Nama lengkap hanya boleh diisi dengan karakter .,/()@&_-");
                    } else if (!RoleUtils.isRoleGroupValid(jsonRoles)) {
                        jsonObject = ERROR("Role yang dipilih harus dalam satu group (ex: HO Dealer)");
                    } else if (!InputValidationUtils.isEmailValid(email)) {
                        jsonObject = ERROR("Format email tidak valid");
                    } else if (!RegistrationUtil.isOTPVerified(otpNumber, email)) {
                        jsonObject = ERROR("OTP belum terverifikasi");
                    } else {
                        CaptchaUtil.check(resourceRequest);
                        _log.info("CAPTCHA verification successful.");

                        ApprovalHeaderUser approvalHeaderUser = createApprovalHeaderUser(dealerId, cabangId, fullName, email, roles);
                        _log.info(approvalHeaderUser.toString());

                        Dealer dealer = DealerLocalServiceUtil.getDealer(dealerId);
                        Cabang cabang = CabangLocalServiceUtil.getCabang(cabangId);
                        String urlRegistrationAccount = themeDisplay.getURLPortal() + "/web/dealink/";

                        List<Integer> requestedRoles = RoleUtils.getRolesAsList(jsonRoles);

                        // SEND EMAIL TO ADMINISTRATOR
                        sendEmailToAdmin(email, fullName, dealer.getName(), cabang.getName(), urlRegistrationAccount, "Dealer Information Link", requestedRoles);

                        jsonObject = SUCCESS("Data tersimpan", String.valueOf(approvalHeaderUser.getId()));
                    }
                }
            } catch (Exception e) {
                if (e instanceof CaptchaTextException) {
                    SessionErrors.add(resourceRequest, e.getClass(), e);
                    _log.warn("CAPTCHA verification failed.");
                    jsonObject = NOT_SUCCESS("CAPTCHA verification failed");
                } else if (e instanceof IOException) {
                    jsonObject = NOT_SUCCESS("Send email notification failed.");
                } else {
                    jsonObject = NOT_SUCCESS(e.getMessage());
                }
                _log.error(e.getMessage(), e);
            }
        }
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
    }

    private ApprovalHeaderUser createApprovalHeaderUser(int dealerId, int cabangId, String requesterName, String requesterEmail, String roles) throws SystemException {
        ApprovalHeaderUser result;
        Date now = new Date();
        ApprovalHeaderUser approvalHeaderUser = ApprovalHeaderUserLocalServiceUtil.createApprovalHeaderUser(0);
        approvalHeaderUser.setApplicationAssignStatusId(ApplicationAssignStatusEnum.WAITING.getId());
        approvalHeaderUser.setDealerId(dealerId);
        approvalHeaderUser.setCabangId(cabangId);
        approvalHeaderUser.setRequesterName(requesterName);
        approvalHeaderUser.setRequesterEmail(requesterEmail);
        approvalHeaderUser.setRowStatus(true);
        approvalHeaderUser.setCreatedBy(requesterEmail);
        approvalHeaderUser.setCreatedDate(now);
        approvalHeaderUser.setModifiedBy(requesterEmail);
        approvalHeaderUser.setModifiedDate(now);
        result = ApprovalHeaderUserLocalServiceUtil.createApprovalHeaderUserWithHistoryAndDetails(approvalHeaderUser, roles);
        return result;
    }

    private void sendEmailToAdmin(String email, String fullname, String dealerName, String cabangDealer, String linkURL, String nameLinkURL, List<Integer> roles) throws Exception {
        try {
            StringJoiner requestedRoles = new StringJoiner(", ");
            for (Integer role : roles) {
                Roles requestedRole = RolesLocalServiceUtil.getRoles(role);
                requestedRoles.add(requestedRole.getName());
            }

            Setting bodyMail = SettingLocalServiceUtil.findCredential("mail", DewaLoginKeys.BODY_EMAIL_NEW_USER);
            String body = bodyMail.getValue();
            body = MailUtil.replace(
                    body,
                    new String[]{
                            "[Nama User DSO]",
                            "[Nama Lengkap User]",
                            "[Alamat Email User Baru]",
                            "[Nama Dealer]",
                            "[Cabang Dealer]",
                            "[Link Registrasi Account]",
                            "[Registrasi Account]",
                            "[Role User]"
                    },
                    new String[]{
                            "Head Office Daihatsu Sales Operation",
                            fullname,
                            email,
                            dealerName,
                            cabangDealer,
                            linkURL,
                            nameLinkURL,
                            requestedRoles.toString()
                    }
            );
            String[] recipients = RoleDealerUtils.listEmailAdminDSO(roles);
            String[] cc = new String[]{email};
            MailUtil.sendEmail(
                    recipients,
                    cc,
                    "Dealer Information Link - Pengajuan User " + dealerName,
                    body,
                    null
            );
        } catch (Exception e) {
            throw new Exception("Send email failed");
        }
    }
}
