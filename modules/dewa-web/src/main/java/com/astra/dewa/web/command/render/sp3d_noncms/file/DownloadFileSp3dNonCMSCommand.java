package com.astra.dewa.web.command.render.sp3d_noncms.file;

import com.astra.dewa.model.SP3D;
import com.astra.dewa.service.SP3DLocalServiceUtil;
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
                "javax.portlet.name=" + DewaWebPortletKeys.SP3D_PORTLET_NONCMS,
                "mvc.command.name=/download-sp3d-non-cms"
        },
        service = MVCResourceCommand.class
)
public class DownloadFileSp3dNonCMSCommand extends BaseMVCResourceCommand {
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        int sp3dId = ParamUtil.getInteger(resourceRequest, "entryId");
        SP3D sp3d = SP3DLocalServiceUtil.getSP3D(sp3dId);
        String fileName = sp3d.getFileName();

        FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(sp3d.getFileId());
        PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");
    }
}
