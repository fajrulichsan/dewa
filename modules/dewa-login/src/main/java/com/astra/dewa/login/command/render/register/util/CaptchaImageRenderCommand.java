package com.astra.dewa.login.command.render.register.util;

import com.astra.dewa.login.constants.DewaLoginPortletKeys;
import com.liferay.portal.kernel.captcha.CaptchaUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author psmmutia0113
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaLoginPortletKeys.REGISTER,
            "mvc.command.name=/register-captchaImage"
      },
      service = MVCResourceCommand.class
)
public class CaptchaImageRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(CaptchaImageRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      try {
         CaptchaUtil.serveImage(request, response);
      } catch (Exception exception) {
         LOG.error(exception.getMessage(), exception);
      }
   }
}
