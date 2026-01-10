package com.astra.dewa.cms.command.render.upload.bonus.file;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.utils.FileUtil;
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
      "javax.portlet.name=" + CmsPortletKeys.UPLOAD_BONUS,
      "mvc.command.name=delete-file-upload-bonus"
   },
   service = MVCResourceCommand.class
)
public class DeleteFileUploadCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(DeleteFileUploadCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(request);

      int fileId = ParamUtil.getInteger(uploadPortletRequest, "file-id");

      int acknowledge = 1;
      String status = "success";
      String message = "";
      try {
         FileUtil.deleteFile(fileId);
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

      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), res.toJSONString());
   }
}
