package com.astra.dewa.web.command.render.kategori_dealer.file;

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
      "javax.portlet.name=" + DewaWebPortletKeys.KATEGORI_DEALER,
      "mvc.command.name=/delete-file-kategori-dealer"
   },
   service = MVCResourceCommand.class
)
public class DeleteFileKategoriDealerCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(DeleteFileKategoriDealerCommand.class);

   private UploadPortletRequest uploadPortletRequest;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      this.uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);

      int fileId = ParamUtil.getInteger(uploadPortletRequest, "file-id");

      int acknowledge = 1;
      String status = "success";
      String message = "";

      try {
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         FileUtil.deleteFile(fileId);
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(resourceRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(resourceRequest, "p_auth", "none"), e);
            message = "Unauthorized request!";
         } else {
            message = e.getMessage();
            LOG.error(message);
            LOG.error(e.getLocalizedMessage());
         }
         acknowledge = 0;
         status = "error";
      }

      JSONObject response = JSONFactoryUtil.createJSONObject();
      response.put("acknowledge", acknowledge);
      response.put("status", status);
      response.put("message", message);

      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), response.toJSONString());
   }
}
