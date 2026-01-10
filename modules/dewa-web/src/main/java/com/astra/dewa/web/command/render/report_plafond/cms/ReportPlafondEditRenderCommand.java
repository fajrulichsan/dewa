package com.astra.dewa.web.command.render.report_plafond.cms;

import com.astra.dewa.model.ReportPlafond;
import com.astra.dewa.service.ReportPlafondLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
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

/**
 * @author psmafifd1401
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.REPORT_PLAFOND,
            "mvc.command.name=/report-plafond/edit"
      },
      service = MVCResourceCommand.class
)
public class ReportPlafondEditRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(ReportPlafondEditRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      int id = ParamUtil.getInteger(uploadPortletRequest, "entryId");
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (id > 0) {
         try {
            ReportPlafond reportPlafond = ReportPlafondLocalServiceUtil.getReportPlafond(id);
            dto.put("id", reportPlafond.getId());
            dto.put("dealerId", reportPlafond.getDealerId());
            dto.put("fileId", reportPlafond.getFileId());
            dto.put("fileName", reportPlafond.getFileName());
            dto.put("filePath", reportPlafond.getFilePath());
            dto.put("periode", reportPlafond.getPeriode());
         } catch (PortalException e) {
            LOG.error("Error fetching data: " + e.getMessage());
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), dto.toString());
   }
}