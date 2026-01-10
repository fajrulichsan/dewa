package com.astra.dewa.cms.command.render.upload.bonus.file;

import com.astra.dewa.cms.constants.CmsKeys;
import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.utils.FileUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
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
      "javax.portlet.name=" + CmsPortletKeys.UPLOAD_BONUS,
      "mvc.command.name=upload-file-upload-bonus"
   },
   service = MVCResourceCommand.class
)

public class UploadFileUploadCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(UploadFileUploadCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(request);
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

      int acknowledge = 1;
      String status = "success";
      String message = "";

      String documentId = ParamUtil.getString(uploadPortletRequest, "document-id");
      if (documentId.equals("undefined")) {
         documentId = ParamUtil.getString(uploadPortletRequest, "document-id-new");
      }
      long fileId = ParamUtil.getLong(uploadPortletRequest, "file-id");
      String fileName = ParamUtil.getString(uploadPortletRequest, "file-name");

      File file = uploadPortletRequest.getFile("file-upload");
      HashMap<String, String> payload = new HashMap<>();

      try {
         if (fileId == 0) {
//            payload = FileUtil.uploadFileIntoCompanyFolder(file, fileName, themeDisplay, request, documentId);
            payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, request, CmsKeys.UPLOAD_KWITANSI_BONUS);
         } else {
            payload = FileUtil.updateFile(fileId, file, fileName, themeDisplay, request);
         }
      } catch (Exception e) {
         acknowledge = 0;
         status = "error";
         message = e.getMessage();

         log.error(e.getMessage());
         log.error(e.getLocalizedMessage());
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
