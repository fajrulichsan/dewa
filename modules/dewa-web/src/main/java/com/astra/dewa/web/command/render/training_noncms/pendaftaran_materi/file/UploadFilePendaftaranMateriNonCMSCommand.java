package com.astra.dewa.web.command.render.training_noncms.pendaftaran_materi.file;

import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
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
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
import java.util.HashMap;

@Component(
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.PENDAFTARAN_PELATIHAN_NONCMS,
            "mvc.command.name=/upload-file-pendaftaran-materi-noncms"
      },
      service = MVCResourceCommand.class
)

public class UploadFilePendaftaranMateriNonCMSCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadFilePendaftaranMateriNonCMSCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);

      int acknowledge = 1;
      String status = "success";
      String message = "";

      HashMap<String, String> payload = new HashMap<>();

      String allowedExtensions = "xlsx";
      String allowedTypes = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

      try {
         // CSRF Authentication
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         // File Attributes
         File file = uploadPortletRequest.getFile("file-upload");
         String mimeType = uploadPortletRequest.getContentType("file-upload");
         String fileName = ParamUtil.getString(uploadPortletRequest, "file-name");
         String fileExtension = FileUtil.getExtensionByStringHandling(fileName).get();

         // File Validation
         if (!allowedExtensions.contains(fileExtension)) {
            throw new Exception("Invalid file extension. Only .xlsx files are allowed.");
         }
         if (!allowedTypes.contains(mimeType)) {
            throw new Exception("Invalid mime type. Only .xlsx files are allowed.");
         }

         fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "." + FileUtil.getExtensionByStringHandling(fileName).get();

         payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, resourceRequest, DewaWebKeys.PENDAFTARAN_PESERTA_PELATIHAN_MENU);
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
      response.put("fileURL", payload.get("URL"));
      response.put("fileID", payload.get("ID"));
      response.put("fileName", payload.get("fileName"));

      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), response.toJSONString());
   }
}