package com.astra.dewa.web.command.render.sales.report.noncms.file;

import com.astra.dewa.model.SalesReport;
import com.astra.dewa.service.SalesReportLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author psmafifd1401
 */
@Component(
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.SALES_REPORT_NONCMS,
                "mvc.command.name=/non-cms/sales-report/download"
        },
        service = MVCResourceCommand.class
)
public class DownloadFileSalesReportNonCMSCommand extends BaseMVCResourceCommand {
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        int salesReportId = ParamUtil.getInteger(resourceRequest, "entryId");
        SalesReport salesReport = SalesReportLocalServiceUtil.getSalesReport(salesReportId);
        String fileName = salesReport.getFileName();

        FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(salesReport.getFileId());
        PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");
    }
}
