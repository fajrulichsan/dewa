package com.astra.dewa.web.command.render.training.file;

import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
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
      "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
      "mvc.command.name=/upload-file-training"
   },
   service = MVCResourceCommand.class
)

public class UploadFileTrainingCommand extends BaseMVCResourceCommand {
   private final Log LOGGER = LogFactoryUtil.getLog(UploadFileTrainingCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      // ServiceContext serviceContext = ServiceContextFactory.getInstance(resourceRequest);
      // User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

      String authToken = ParamUtil.getString(uploadPortletRequest, "authToken");

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
//            DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(fileId);
//            if(fileEntry == null) {
         payload = FileUtil.uploadFileIntoCompanyFolder(file, fileName, themeDisplay, resourceRequest, documentId);
         LOGGER.info("payload : " + payload.toString());
//            }else{
//                payload =  FileUtil.updateFile(fileId, file, fileName, themeDisplay, resourceRequest);
//            }
      } catch (Exception e) {
         acknowledge = 0;
         status = "error";
         message = e.getMessage();

         LOGGER.error(e.getMessage());
         LOGGER.error(e.getLocalizedMessage());
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
