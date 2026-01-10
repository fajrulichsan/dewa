package com.astra.dewa.web.command.render.training.materi.file;

import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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
            "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
            "mvc.command.name=/upload-file-training-materi"
      },
      service = MVCResourceCommand.class
)

public class UploadFileTrainingMateriCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadFileTrainingMateriCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

      int acknowledge = 1;
      String status = "success";
      String message = "";

      HashMap<String, String> payload = new HashMap<>();

      List<String> allowedExtensions = Arrays.asList("docx", "xlsx", "pdf");
      List<String> allowedTypes = Arrays.asList(
            "application/pdf",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
      );

      try {
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         // File Attributes
         File file = uploadPortletRequest.getFile("file-upload");
         String mimeType = uploadPortletRequest.getContentType("file-upload");
         String fileName = ParamUtil.getString(uploadPortletRequest, "file-name");
         String fileExtension = FileUtil.getExtensionByStringHandling(fileName).get();
         long fileId = ParamUtil.getLong(uploadPortletRequest, "file-id");

         // File Validation
         if (!allowedExtensions.contains(fileExtension)) {
            throw new SystemException("Invalid file extension. Only .docx, .xlsx, .pdf files are allowed.");
         }

         if (!allowedTypes.contains(mimeType)) {
            throw new SystemException("Invalid mime type. Only .docx, .xlsx, .pdf files are allowed.");
         }

         // Check file extension
         if (fileName != null) {
            String lowerCaseFileName = fileName.toLowerCase();
            if (!(lowerCaseFileName.endsWith(".docx") || lowerCaseFileName.endsWith(".xlsx") || lowerCaseFileName.endsWith(".pdf"))) {
               acknowledge = 0;
               status = "error";
               message = "Upload File Gagal. Hanya file dengan format docx, xlsx, dan pdf yang diperbolehkan.";

               JSONObject response = JSONFactoryUtil.createJSONObject();
               response.put("acknowledge", acknowledge);
               response.put("status", status);
               response.put("message", message);

               ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), response.toJSONString());
               return;
            }
         }

         if (fileId == 0) {
            payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, resourceRequest, DewaWebKeys.MATERI_PELATIHAN);
         } else {
            try {
               FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
               payload = FileUtil.updateFile(fileEntry.getFileEntryId(), file, fileName, themeDisplay, resourceRequest);
            } catch (PortalException pe) {
               payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, resourceRequest, DewaWebKeys.MATERI_PELATIHAN);
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(uploadPortletRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(uploadPortletRequest, "p_auth", "none"), e);
            message = "Unauthorized request!";
         } else if (e instanceof SystemException) {
            message = e.getMessage();
         } else {
            message = "Bad request";
         }

         LOG.error(e.getMessage(), e);
         LOG.error(e.getLocalizedMessage());

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