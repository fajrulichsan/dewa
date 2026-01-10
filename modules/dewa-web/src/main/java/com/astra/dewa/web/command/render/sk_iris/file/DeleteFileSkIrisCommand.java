package com.astra.dewa.web.command.render.sk_iris.file;

import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Component(
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.SK_IRIS,
      "mvc.command.name=/delete-file-sk-iris"
   },
   service = MVCResourceCommand.class
)
public class DeleteFileSkIrisCommand extends BaseMVCResourceCommand {
   private final Log LOGGER = LogFactoryUtil.getLog(DeleteFileSkIrisCommand.class);

   private ResourceRequest resourceRequest;
   private UploadPortletRequest uploadPortletRequest;
   private ThemeDisplay themeDisplay;
   private ServiceContext serviceContext;
   private User user;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      this.resourceRequest = resourceRequest;
      this.uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      this.themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      this.serviceContext = ServiceContextFactory.getInstance(resourceRequest);
      this.user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

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
