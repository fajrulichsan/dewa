package com.astra.dewa.web.command.render.diskon.fakpol.noncms.file;

import com.astra.dewa.model.DiskonFakpol;
import com.astra.dewa.service.DiskonFakpolLocalServiceUtil;
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
            "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FAKPOL_NONCMS,
            "mvc.command.name=/download-diskon-fakpol-noncms"
      },
      service = MVCResourceCommand.class
)
public class DownloadFileDiskonFakpolNonCMSCommand extends BaseMVCResourceCommand {

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      int id = ParamUtil.getInteger(resourceRequest, "entryId");

      DiskonFakpol diskonFakpol = DiskonFakpolLocalServiceUtil.getDiskonFakpol(id);
      String fileName = diskonFakpol.getFileName();
      Long fileId = diskonFakpol.getFileId();
      FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
      PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");
   }

}
