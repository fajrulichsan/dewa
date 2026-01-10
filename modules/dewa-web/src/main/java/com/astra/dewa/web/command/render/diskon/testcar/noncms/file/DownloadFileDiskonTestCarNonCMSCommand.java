package com.astra.dewa.web.command.render.diskon.testcar.noncms.file;

import com.astra.dewa.model.DiskonTestCar;
import com.astra.dewa.service.DiskonTestCarLocalServiceUtil;
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
            "javax.portlet.name=" + DewaWebPortletKeys.DISKON_TEST_CAR_NONCMS,
            "mvc.command.name=/download-diskon-test-car-noncms"
      },
      service = MVCResourceCommand.class
)
public class DownloadFileDiskonTestCarNonCMSCommand extends BaseMVCResourceCommand {

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      int id = ParamUtil.getInteger(resourceRequest, "entryId");
      DiskonTestCar diskonTestCar = DiskonTestCarLocalServiceUtil.getDiskonTestCar(id);
      String fileName = diskonTestCar.getFileName();
      Long fileId = diskonTestCar.getFileId();

      FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
      PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");
   }
}
