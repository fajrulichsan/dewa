package com.astra.dewa.web.command.render.report_plafond.noncms.file;

import com.astra.dewa.model.ReportPlafond;
import com.astra.dewa.service.ReportPlafondLocalServiceUtil;
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

@Component(
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.REPORT_PLAFOND_NONCMS,
                "mvc.command.name=/non-cms/report-plafond/download"
        },
        service = MVCResourceCommand.class
)
public class DownloadFileReportPlafondNonCMSCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        int reportPlafondId = ParamUtil.getInteger(resourceRequest, "entryId");
        ReportPlafond reportPlafond = ReportPlafondLocalServiceUtil.getReportPlafond(reportPlafondId);
        String fileName = reportPlafond.getFileName();

        FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(reportPlafond.getFileId());
        PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");
    }
}
