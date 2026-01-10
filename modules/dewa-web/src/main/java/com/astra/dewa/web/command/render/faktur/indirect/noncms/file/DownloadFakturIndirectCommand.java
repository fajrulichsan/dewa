package com.astra.dewa.web.command.render.faktur.indirect.noncms.file;

import com.astra.dewa.model.FakturIndirect;
import com.astra.dewa.service.FakturIndirectLocalServiceUtil;
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

            "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_INDIRECT_NONCMS,
            "mvc.command.name=/non-cms/faktur-indirect/download"
      },
      service = MVCResourceCommand.class
)
public class DownloadFakturIndirectCommand extends BaseMVCResourceCommand {

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      int id = ParamUtil.getInteger(resourceRequest, "entryId");
      FakturIndirect fakturIndirect = FakturIndirectLocalServiceUtil.getFakturIndirect(id);
      String fileName = fakturIndirect.getFileName();
      Long fileId = fakturIndirect.getFileId();
      FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
      PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");
   }
}
