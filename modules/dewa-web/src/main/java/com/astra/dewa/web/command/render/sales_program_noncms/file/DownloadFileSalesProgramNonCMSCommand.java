package com.astra.dewa.web.command.render.sales_program_noncms.file;

import com.astra.dewa.model.SalesProgram;
import com.astra.dewa.service.SalesProgramLocalServiceUtil;
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
                "javax.portlet.name=" + DewaWebPortletKeys.SALES_PROGRAM_NONCMS,
                "mvc.command.name=/download-sales-program-noncms"
        },
        service = MVCResourceCommand.class
)

public class DownloadFileSalesProgramNonCMSCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        Long salesProgramId = ParamUtil.getLong(resourceRequest, "entryId");
        SalesProgram salesProgram = SalesProgramLocalServiceUtil.getSalesProgram(salesProgramId);
        String fileName = salesProgram.getFileName();

        FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(salesProgram.getFileId());
        PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");

    }
}
