package com.astra.dewa.web.command.resource.diskon.others;

import com.astra.dewa.model.DiskonOther;
import com.astra.dewa.service.DiskonOtherLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
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

/**
 * @author psmahmad1402
 */
@Component(
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.DISKON_OTHERS,
                "mvc.command.name=" + DewaWebKeys.NON_CMS_PATH + DewaWebKeys.REALISASI_DISKON_PATH + "/others/download_file"
        },
        service = MVCResourceCommand.class
)
public class DownloadDiskonOtherFileResourceCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        try {
            long id = ParamUtil.getInteger(resourceRequest, "entryId");
            DiskonOther r = DiskonOtherLocalServiceUtil.getDiskonOther(id);
            String fileName = r.getFileName();
            long fileId = r.getFileId();
            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
            PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName, fileEntry.getContentStream(), "application/octet-stream");
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
    }
}
