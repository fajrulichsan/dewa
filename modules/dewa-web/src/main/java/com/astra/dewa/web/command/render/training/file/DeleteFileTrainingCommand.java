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
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Component(
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.TRAINING_MATERI,
      "mvc.command.name=/delete-file-training"
   },
   service = MVCResourceCommand.class
)
public class DeleteFileTrainingCommand extends BaseMVCResourceCommand {
   private final Log LOGGER = LogFactoryUtil.getLog(DeleteFileTrainingCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      // ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      // ServiceContext serviceContext = ServiceContextFactory.getInstance(resourceRequest);
      // User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

      int fileId = ParamUtil.getInteger(uploadPortletRequest, "file-id");
      String authToken = ParamUtil.getString(uploadPortletRequest, "authToken");

      int acknowledge = 1;
      String status = "success";
      String message = "";

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
