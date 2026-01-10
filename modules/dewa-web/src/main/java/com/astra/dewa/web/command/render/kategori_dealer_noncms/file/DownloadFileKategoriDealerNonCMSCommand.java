package com.astra.dewa.web.command.render.kategori_dealer_noncms.file;

import com.astra.dewa.model.KategoriDealer;
import com.astra.dewa.service.KategoriDealerLocalServiceUtil;
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
                "javax.portlet.name=" + DewaWebPortletKeys.KATEGORI_DEALER_NONCMS,
                "mvc.command.name=/download-kategori-dealer-noncms"
        },
        service = MVCResourceCommand.class
)
public class DownloadFileKategoriDealerNonCMSCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        Long kategoriDealerId = ParamUtil.getLong(resourceRequest, "entryId");
        KategoriDealer kategoriDealer = KategoriDealerLocalServiceUtil.getKategoriDealer(kategoriDealerId);
        String fileName = kategoriDealer.getFileName();

        FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(kategoriDealer.getFileId());
        PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");

    }
}
