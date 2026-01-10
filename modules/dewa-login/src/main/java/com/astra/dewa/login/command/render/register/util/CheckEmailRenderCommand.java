package com.astra.dewa.login.command.render.register.util;

import com.astra.dewa.login.constants.DewaLoginPortletKeys;
import com.astra.dewa.service.EmailDomainLocalServiceUtil;
import com.astra.dewa.utils.InputValidationUtils;
import com.astra.dewa.utils.MailUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.NOT_SUCCESS;

/**
 * @author psmmutia0113
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaLoginPortletKeys.REGISTER,
            "mvc.command.name=/register-check-email"
      },
      service = MVCResourceCommand.class
)
public class CheckEmailRenderCommand extends BaseMVCResourceCommand {
   private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
      String emailUserName = ParamUtil.getString(request, "email");
      String domainId = ParamUtil.getString(request, "domainId");

      _log.info("==== VERIFIKASI EMAIL ====");
      _log.info("Email username: " + emailUserName);
      _log.info("Domain Id: " + domainId);
      _log.info("Checking email ...");
      long startTime = System.currentTimeMillis();

      try {
         if (!InputValidationUtils.isEmailUserNameValid(emailUserName)) {
            jsonObject = NOT_SUCCESS("Format email tidak valid");
         } else if (!InputValidationUtils.isNumberOnly(domainId)) {
            jsonObject = NOT_SUCCESS("Domain tidak valid");
         } else if (!MailUtil.isDomainValid(Integer.parseInt(domainId))) {
            jsonObject = NOT_SUCCESS("Domain tidak terdaftar");
         } else {
            String domain = EmailDomainLocalServiceUtil.getEmailDomain(Integer.parseInt(domainId)).getDomainName();
            if (domain.toLowerCase().endsWith("astra.co.id") || domain.toLowerCase().contains("astra.co.id")) {
               throw new Exception("Mohon tidak menggunakan domain astra");
            }

            String email = emailUserName + "@" + domain;
            DynamicQuery query = UserLocalServiceUtil.dynamicQuery()
                  .add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.ilike("emailAddress", "astra.co.id")))
                  .add(RestrictionsFactoryUtil.eq("emailAddress", email));
            List<User> userList = UserLocalServiceUtil.dynamicQuery(query);
            if (!userList.isEmpty()) {
               jsonObject = NOT_SUCCESS("Email already registered");
            } else {
               jsonObject = SUCCESS("Email is valid", String.valueOf(0));
               jsonObject.put("email", email);
            }
         }
      } catch (Exception e) {
         jsonObject = NOT_SUCCESS(e.getMessage());
         _log.error(e.getMessage(), e);
      }
      long endTime = System.currentTimeMillis();
      _log.info("Email checking complete in " + (endTime - startTime) + "ms");
      _log.info("==========================");
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}
