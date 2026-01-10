package com.astra.dewa.web.command.render.upload.bonus.request.file;

import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Component(
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_BONUS_REQUEST,
      "mvc.command.name=delete-file-upload-bonus-request"
   },
   service = MVCResourceCommand.class
)
public class DeleteFileUploadCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(DeleteFileUploadCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(request);

      int fileId = ParamUtil.getInteger(uploadPortletRequest, "file-id");

      int acknowledge = 1;
      String status = "success";
      String message = "";

      try {
         // CSRF AUTHENTICATION
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         FileUtil.deleteFile(fileId);
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(request));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(request, "p_auth", "none"), e);
            message = "Unauthorized request!";
         } else {
            message = e.getMessage();
            LOG.error(message);
            LOG.error(e.getLocalizedMessage());
         }
         acknowledge = 0;
         status = "error";
      }

      JSONObject res = JSONFactoryUtil.createJSONObject();
      res.put("acknowledge", acknowledge);
      res.put("status", status);
      res.put("message", message);

      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), res.toJSONString());
   }
}