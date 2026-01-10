package com.astra.dewa.web.command.render.calender.event.file;

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
      "javax.portlet.name=" + DewaWebPortletKeys.CALENDER_EVENT,
      "mvc.command.name=upload-file-calender-event"
   },
   service = MVCResourceCommand.class
)

public class UploadFileCalenderEventCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadFileCalenderEventCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

      int acknowledge = 1;
      String status = "success";
      String message = "";

      HashMap<String, String> payload = new HashMap<>();

      List<String> allowedExtensions = Arrays.asList("gif", "jpg", "jpeg", "png");
      List<String> allowedTypes = Arrays.asList("image/gif", "image/jpg", "image/jpeg", "image/png");

      try {
         // CSRF AUTHENTICATION
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         // File Attributes
         File file = uploadPortletRequest.getFile("file-upload");
         String mimeType = uploadPortletRequest.getContentType("file-upload");
         String fileName = ParamUtil.getString(uploadPortletRequest, "file-name");
         String fileExtension = FileUtil.getExtensionByStringHandling(fileName).get();
         long fileId = ParamUtil.getLong(uploadPortletRequest, "file-id");

         // Other Attributes
         String documentId = ParamUtil.getString(uploadPortletRequest, "document-id");
         if (documentId.equals("undefined")) {
            documentId = ParamUtil.getString(uploadPortletRequest, "document-id-new");
         }

         // File Validation
         if (!allowedExtensions.contains(fileExtension)) {
            throw new Exception("Invalid file extension. Only .gif, .jpg, .jpeg, .png files are allowed.");
         }
         if (!allowedTypes.contains(mimeType)) {
            throw new Exception("Invalid mime type. Only .gif, .jpg, .jpeg, .png files are allowed.");
         }

         if (fileId == 0) {
            payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, resourceRequest, DewaWebKeys.CALENDAR_EVENT);
         } else {
            try {
               FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
               payload = FileUtil.updateFile(fileEntry.getFileEntryId(), file, fileName, themeDisplay, resourceRequest);
            } catch (PortalException pe) {
               payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, resourceRequest, DewaWebKeys.CALENDAR_EVENT);
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
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