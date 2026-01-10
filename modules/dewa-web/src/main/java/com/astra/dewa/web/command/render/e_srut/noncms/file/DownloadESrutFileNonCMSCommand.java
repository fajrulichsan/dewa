package com.astra.dewa.web.command.render.e_srut.noncms.file;

import com.astra.dewa.model.ESrut;
import com.astra.dewa.service.ESrutLocalServiceUtil;
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
            "javax.portlet.name=" + DewaWebPortletKeys.E_SRUT_NONCMS,
            "mvc.command.name=/download-e-srut-noncms"
      },
      service = MVCResourceCommand.class
)
public class DownloadESrutFileNonCMSCommand extends BaseMVCResourceCommand {

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      int id = ParamUtil.getInteger(resourceRequest, "entryId");
      ESrut eSrut = ESrutLocalServiceUtil.getESrut(id);
      String fileName = eSrut.getFileName();
      Long fileId = eSrut.getFileId();

      FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
      PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");
   }
}
