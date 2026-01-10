package com.astra.dewa.web.command.render.diskon.fleet.file;

import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.utils.KuartalUtils;
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
      "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FLEET,
      "mvc.command.name=/upload-file-diskon-fleet"
   },
   service = MVCResourceCommand.class
)

public class UploadFileDiskonFleetCommand extends BaseMVCResourceCommand {
   private final Log LOGGER = LogFactoryUtil.getLog(UploadFileDiskonFleetCommand.class);

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

      String fileName = ParamUtil.getString(uploadPortletRequest, "file-name");
      int dealerId = ParamUtil.getInteger(uploadPortletRequest, "dealer");
      String dealerCode = DealerUtils.getDealerCode(dealerId);
      String tahun = ParamUtil.getString(uploadPortletRequest, "tahun");
      String kuartal = ParamUtil.getString(uploadPortletRequest, "kuartal");
      String kuartalName = KuartalUtils.getKuartalName(kuartal);

      File file = uploadPortletRequest.getFile("file-upload");
      HashMap<String, String> payload = new HashMap<>();

      fileName = DewaWebKeys.DISKON_FLEET_MENU + "-" + kuartalName + "-" + tahun + "-" + dealerCode + "." + FileUtil.getExtensionByStringHandling(fileName).get();

      try {
//         payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, resourceRequest, DewaWebKeys.TEMP_FOLDER);
         payload = FileUtil.uploadFileIntoMenuFolder(file, fileName, themeDisplay, resourceRequest, DewaWebKeys.DISKON_FLEET_MENU);
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
