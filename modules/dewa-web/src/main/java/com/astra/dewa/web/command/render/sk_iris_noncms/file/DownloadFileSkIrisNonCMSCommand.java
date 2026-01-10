package com.astra.dewa.web.command.render.sk_iris_noncms.file;

import com.astra.dewa.model.SkIris;
import com.astra.dewa.service.SkIrisLocalServiceUtil;
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
                "javax.portlet.name=" + DewaWebPortletKeys.SK_IRIS_NONCMS,
                "mvc.command.name=/download-sk-iris-non-cms"
        },
        service = MVCResourceCommand.class
)
public class DownloadFileSkIrisNonCMSCommand extends BaseMVCResourceCommand {
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        int skIrisId = ParamUtil.getInteger(resourceRequest, "entryId");
        SkIris skIris = SkIrisLocalServiceUtil.getSkIris(skIrisId);
        String fileName = skIris.getFileName();

        FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(skIris.getFileId());
        PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");
    }
}
