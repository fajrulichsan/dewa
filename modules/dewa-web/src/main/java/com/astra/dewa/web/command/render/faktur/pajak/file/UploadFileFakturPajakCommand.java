package com.astra.dewa.web.command.render.faktur.pajak.file;

import com.astra.dewa.utils.DealerUtils;
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
import java.util.HashMap;

@Component(
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_PAJAK,
      "mvc.command.name=/upload-file-faktur-pajak"
   },
   service = MVCResourceCommand.class
)

public class UploadFileFakturPajakCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(UploadFileFakturPajakCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

      int acknowledge = 1;
      String status = "success";
      String message = "";

      HashMap<String, String> payload = new HashMap<>();

      try {
         // CSRF AUTHENTICATION
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());

         // File Attributes
         File file = uploadPortletRequest.getFile("file-upload");
         String mimeType = uploadPortletRequest.getContentType("file-upload");
         String fileName = ParamUtil.getString(uploadPortletRequest, "file-name");
         long fileId = ParamUtil.getLong(uploadPortletRequest, "file-id");

         // Other Attributes
         int dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealerId");
         String invoiceDate = ParamUtil.getString(uploadPortletRequest, "invoiceDate");
         String documentId = ParamUtil.getString(uploadPortletRequest, "document-id");
         if (documentId.equals("undefined")) {
            documentId = ParamUtil.getString(uploadPortletRequest, "document-id-new");
         }

         // File Validation
         if (!fileName.toLowerCase().endsWith(".pdf")) {
            throw new Exception("Invalid file extension. Only .pdf files are allowed.");
         }
         if (!mimeType.equals("application/pdf")) {
            throw new Exception("Invalid mime type. Only .pdf files are allowed.");
         }

         String dealerCode = DealerUtils.getDealerCode(dealerId);
         fileName = "Rekap Faktur Pajak-" + dealerCode + "-" + invoiceDate + "." + FileUtil.getExtensionByStringHandling(fileName).get();

         if (fileId == 0) {
            payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, resourceRequest, DewaWebKeys.FAKTUR_PAJAK_MENU);
         } else {
            try {
               FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
               payload = FileUtil.updateFile(fileEntry.getFileEntryId(), file, fileName, themeDisplay, resourceRequest);
            } catch (PortalException pe) {
               payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, resourceRequest, DewaWebKeys.FAKTUR_PAJAK_MENU);
            }
         }
      } catch (Exception e) {
         if (e instanceof PrincipalException) {
            LOG.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(resourceRequest));
            LOG.error("Invalid CSRF token!  Token: " + ParamUtil.get(resourceRequest, "p_auth", "none"), e);
            message = "Unauthorized request!";
         } else {
            message = e.getMessage();
            LOG.error(e);
         }
         LOG.error("Upload failed: " + message);
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
