package com.astra.dewa.web.command.render.report_plafond.cms.file;

import com.astra.dewa.utils.FileUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author psmafifd1401
 */
@Component(
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.REPORT_PLAFOND,
      "mvc.command.name=/report-plafond/delete-file"
   },
   service = MVCResourceCommand.class
)
public class DeleteFileReportPlafondCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(DeleteFileReportPlafondCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      int fileId = ParamUtil.getInteger(uploadPortletRequest, "file-id");
      int acknowledge = 1;
      String status = "success";
      String message = "";
      try {
         AuthTokenUtil.checkCSRFToken(uploadPortletRequest, this.getClass().getName());
         FileUtil.deleteFile(fileId);
      } catch (Exception e) {
         acknowledge = 0;
         status = "error";
         message = e.getMessage();
         LOG.error(e.getMessage());
         LOG.error(e.getLocalizedMessage());
      }
      JSONObject response = JSONFactoryUtil.createJSONObject();
      response.put("acknowledge", acknowledge);
      response.put("status", status);
      response.put("message", message);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), response.toJSONString());
   }
}
