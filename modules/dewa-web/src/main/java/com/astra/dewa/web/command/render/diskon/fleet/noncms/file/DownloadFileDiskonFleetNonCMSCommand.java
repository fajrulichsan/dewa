package com.astra.dewa.web.command.render.diskon.fleet.noncms.file;

import com.astra.dewa.model.DiskonFleet;
import com.astra.dewa.service.DiskonFleetLocalServiceUtil;
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
            "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FLEET_NONCMS,
            "mvc.command.name=/download-diskon-fleet-noncms"
      },
      service = MVCResourceCommand.class
)
public class DownloadFileDiskonFleetNonCMSCommand extends BaseMVCResourceCommand {

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      int id = ParamUtil.getInteger(resourceRequest, "entryId");

      DiskonFleet diskonFleet = DiskonFleetLocalServiceUtil.getDiskonFleet(id);
      String fileName = diskonFleet.getFileName();
      Long fileId = diskonFleet.getFileId();

      FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
      PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");
   }

}
