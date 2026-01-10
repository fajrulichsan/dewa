package com.astra.dewa.web.command.render.surat_penalty_stock_noncms.file;

import com.astra.dewa.model.SuratPenaltyStock;
import com.astra.dewa.service.SuratPenaltyStockLocalServiceUtil;
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
                "javax.portlet.name=" + DewaWebPortletKeys.SURAT_PENALTY_STOCK_NONCMS,
                "mvc.command.name=/download-surat-penalty-stock-noncms"
        },
        service = MVCResourceCommand.class
)

public class DownloadFileSuratPenaltyStockNonCMSCommand extends BaseMVCResourceCommand {
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        Long suratPenaltyStockId = ParamUtil.getLong(resourceRequest, "entryId");
        SuratPenaltyStock suratPenaltyStock = SuratPenaltyStockLocalServiceUtil.getSuratPenaltyStock(suratPenaltyStockId);
        String fileName = suratPenaltyStock.getFileName();
        FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(suratPenaltyStock.getFileId());
        PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");
    }
}