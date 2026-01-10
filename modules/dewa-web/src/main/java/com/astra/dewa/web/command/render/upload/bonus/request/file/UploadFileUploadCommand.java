package com.astra.dewa.web.command.render.upload.bonus.request.file;

import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component(
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.UPLOAD_BONUS_REQUEST,
      "mvc.command.name=upload-file-upload-bonus-request"
   },
   service = MVCResourceCommand.class
)

public class UploadFileUploadCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadFileUploadCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(request);
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      int acknowledge = 1;
      String status = "success";
      String message = "";

      HashMap<String, String> payload = new HashMap<>();

      List<String> allowedExtensions = Arrays.asList("pdf", "jpg", "jpeg", "png");
      List<String> allowedTypes = Arrays.asList("application/pdf", "image/jpg", "image/jpeg", "image/png");

      try {
         // CSRF AUTHENTICATION
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         // File Attributes
         File file = uploadPortletRequest.getFile("file-upload");
         String mimeType = uploadPortletRequest.getContentType("file-upload");
         String fileName = ParamUtil.getString(uploadPortletRequest, "file-name");
         String fileExtension = FileUtil.getExtensionByStringHandling(fileName).get();
         long fileId = ParamUtil.getLong(uploadPortletRequest, "file-id");

         // File Validation
         if (!allowedExtensions.contains(fileExtension)) {
            throw new Exception("Invalid file extension. Only .pdf, .jpg, .jpeg, .png files are allowed.");
         }
         if (!allowedTypes.contains(mimeType)) {
            throw new Exception("Invalid mime type. Only .pdf, .jpg, .jpeg, .png files are allowed.");
         }

         if (fileId == 0) {
            payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, request, DewaWebKeys.UPLOAD_KWITANSI_BONUS);
         } else {
            try {
               FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
               payload = FileUtil.updateFile(fileEntry.getFileEntryId(), file, fileName, themeDisplay, request);
            } catch (PortalException pe) {
               payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, request, DewaWebKeys.UPLOAD_KWITANSI_BONUS);
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(request));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(request, "p_auth", "none"), e);
            message = "Unauthorized request!";
         } else {
            message = "Bad request!";
            LOG.error(e.getMessage(), e);
            LOG.error(e.getLocalizedMessage());
         }
         acknowledge = 0;
         status = "error";
      }

      JSONObject res = JSONFactoryUtil.createJSONObject();
      res.put("acknowledge", acknowledge);
      res.put("status", status);
      res.put("message", message);
      res.put("fileURL", payload.get("URL"));
      res.put("fileID", payload.get("ID"));
      res.put("fileName", payload.get("fileName"));

      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), res.toJSONString());
   }
}