package com.astra.dewa.web.command.render.faktur.pajak.noncms.file;

import com.astra.dewa.model.FakturPajak;
import com.astra.dewa.service.FakturPajakLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
            "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_PAJAK_NONCMS,
            "mvc.command.name=/download-faktur-pajak"
      },
      service = MVCResourceCommand.class
)
public class DownloadFakturPajakCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(DownloadFakturPajakCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      try {
         int id = ParamUtil.getInteger(resourceRequest, "entryId");
         FakturPajak fakturPajak = FakturPajakLocalServiceUtil.getFakturPajak(id);
         String fileName = fakturPajak.getFileName();
         Long fileId = fakturPajak.getFileId();
         FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
         PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");
      } catch (Exception e) {
         LOG.error(e.getMessage(), e);
      }
   }
}