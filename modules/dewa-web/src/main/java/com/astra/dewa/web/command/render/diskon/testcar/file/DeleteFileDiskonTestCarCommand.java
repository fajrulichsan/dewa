package com.astra.dewa.web.command.render.diskon.testcar.file;

import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Component(
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.DISKON_TEST_CAR,
      "mvc.command.name=/delete-file-diskon-test-car"
   },
   service = MVCResourceCommand.class
)
public class DeleteFileDiskonTestCarCommand extends BaseMVCResourceCommand {
   private final Log LOGGER = LogFactoryUtil.getLog(DeleteFileDiskonTestCarCommand.class);

   private UploadPortletRequest uploadPortletRequest;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      this.uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);

      int fileId = ParamUtil.getInteger(uploadPortletRequest, "file-id");
      String authToken = ParamUtil.getString(uploadPortletRequest, "authToken");

      int acknowledge = 1;
      String status = "success";
      String message = "";

      LOGGER.info("Auth Token: " + authToken);
      LOGGER.info("File Id: " + fileId);

      try {
         FileUtil.deleteFile(fileId);
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

      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), response.toJSONString());
   }
}
