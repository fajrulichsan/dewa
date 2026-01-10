package com.astra.dewa.web.command.render.sales.report.cms;

import com.astra.dewa.model.SalesReport;
import com.astra.dewa.service.SalesReportLocalServiceUtil;
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

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.SALES_REPORT,
            "mvc.command.name=/sales-report/edit"
      },
      service = MVCResourceCommand.class
)
public class SalesReportEditRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(SalesReportEditRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
      int id = ParamUtil.getInteger(uploadPortletRequest, "entryId");
      JSONObject dto = JSONFactoryUtil.createJSONObject();
      if (id >= 0) {
         try {
            SalesReport salesReport = SalesReportLocalServiceUtil.getSalesReport(id);
            dto.put("id", salesReport.getId());
            dto.put("dealerId", salesReport.getDealerId());
            dto.put("fileId", salesReport.getFileId());
            dto.put("fileName", salesReport.getFileName());
            dto.put("filePath", salesReport.getFilePath());
            dto.put("periode", salesReport.getPeriode());
            dto.put("keterangan", salesReport.getKeterangan());
         } catch (PortalException e) {
            LOG.debug(e.getMessage());
         }
      }
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), dto.toString());
   }
}