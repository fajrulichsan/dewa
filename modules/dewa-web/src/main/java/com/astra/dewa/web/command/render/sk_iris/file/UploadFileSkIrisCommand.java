package com.astra.dewa.web.command.render.sk_iris.file;

import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
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
      "javax.portlet.name=" + DewaWebPortletKeys.SK_IRIS,
      "mvc.command.name=/upload-file-sk-iris"
   },
   service = MVCResourceCommand.class
)

public class UploadFileSkIrisCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(UploadFileSkIrisCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

      int acknowledge = 1;
      String status = "success";
      String message = "";

      String documentId = ParamUtil.getString(uploadPortletRequest, "document-id");
      if (documentId.equals("undefined")) {
         documentId = ParamUtil.getString(uploadPortletRequest, "document-id-new");
      }
      long fileId = ParamUtil.getLong(uploadPortletRequest, "file-id");
      String fileName = ParamUtil.getString(uploadPortletRequest, "file-name");
      String wilayahName = ParamUtil.getString(uploadPortletRequest, "wilayahName");
      String periode = ParamUtil.getString(uploadPortletRequest, "periode");
      String tahun = ParamUtil.getString(uploadPortletRequest, "tahun");
      String kategori = ParamUtil.getString(uploadPortletRequest, "kategori");

      File file = uploadPortletRequest.getFile("file-upload");
      HashMap<String, String> payload = new HashMap<>();

      fileName = "SK IRIS "+ kategori +"-" + wilayahName + "-" + periode + "-" + tahun + "." + FileUtil.getExtensionByStringHandling(fileName).get();
      String menuName = DewaWebKeys.SK_IRIS_MENU;

      try {
         payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, resourceRequest, menuName);
      } catch (Exception e) {
         acknowledge = 0;
         status = "error";
         message = e.getMessage();

         log.error(e.getMessage());
         log.error(e.getLocalizedMessage());
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
