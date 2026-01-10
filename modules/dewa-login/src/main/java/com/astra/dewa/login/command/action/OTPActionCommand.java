package com.astra.dewa.login.command.action;

import com.astra.dewa.exception.NoSuchEmailDomainException;
import com.astra.dewa.login.command.util.RegistrationUtil;
import com.astra.dewa.login.constants.DewaLoginKeys;
import com.astra.dewa.login.constants.DewaLoginPortletKeys;
import com.astra.dewa.model.OTP;
import com.astra.dewa.model.Setting;
import com.astra.dewa.service.EmailDomainLocalServiceUtil;
import com.astra.dewa.service.SettingLocalServiceUtil;
import com.astra.dewa.utils.InputValidationUtils;
import com.astra.dewa.utils.MailUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.Date;

import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.WARNING;

/**
 * @author psmmutia0113
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaLoginPortletKeys.REGISTER,
                "mvc.command.name=/otp-action"
        },
        service = MVCResourceCommand.class
)
public class OTPActionCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        String state = ParamUtil.getString(resourceRequest, "state");
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

        _log.info("======== Dealink OTP ========");

        try {
            String emailUserName = ParamUtil.getString(resourceRequest, "email");
            int domainId = ParamUtil.getInteger(resourceRequest, "domainId");

            String domain = EmailDomainLocalServiceUtil.getEmailDomain(domainId).getDomainName();
            String email = emailUserName + "@" + domain;

            if (!InputValidationUtils.isEmailUserNameValid(emailUserName)) {
                jsonObject = NOT_SUCCESS("Format email tidak valid");
            } else if (domain.equalsIgnoreCase("ai.astra.co.id")) {
                jsonObject = NOT_SUCCESS("Mohon tidak menggunakan domain astra.co.id");
            } else {
                switch (state) {
                    case "SEND":
                        OTP otp = RegistrationUtil.createOTP(new Date(), email);
                        String[] reciepients = new String[]{email};
                        Setting bodyMail = SettingLocalServiceUtil.findCredential("mail", DewaLoginKeys.BODY_EMAIL_OTP);
                        String body = bodyMail.getValue();
                        body = MailUtil.replace(
                                body,
                                new String[]{"[otp]"},
                                new String[]{String.valueOf(otp.getOTPNumber())}
                        );

                        MailUtil.sendEmail(
                                reciepients,
                                null,
                                "Dealer Information Link - Kode OTP",
                                body,
                                null
                        );

                        jsonObject = SUCCESS("Data tersimpan", String.valueOf(otp.getId()));
                        break;
                    case "VALIDATE":
                        Integer otpNumber = ParamUtil.getInteger(resourceRequest, "otp");
                        jsonObject = RegistrationUtil.isOTPValid(otpNumber, email) ?
                              SUCCESS("OTP is valid", String.valueOf(0)) :
                              WARNING("OTP is not valid");
                        break;
                    default:
                        jsonObject = NOT_SUCCESS("Invalid request");
                        break;
                }
            }
        } catch (Exception e) {
            if (e instanceof NoSuchEmailDomainException) {
                _log.error("Invalid domain!");
            } else if (e instanceof IOException){
                _log.error("Send email notification failed.");
            } else {
                _log.error(e.getMessage(), e);
            }
        }

        _log.info("=============================");

        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
    }
}
