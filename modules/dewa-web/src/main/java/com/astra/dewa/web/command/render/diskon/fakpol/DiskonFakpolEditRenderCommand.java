package com.astra.dewa.web.command.render.diskon.fakpol;

import com.astra.dewa.model.DiskonFakpol;
import com.astra.dewa.service.DiskonFakpolLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FAKPOL,
                "mvc.command.name=/diskon-fakpol-edit"
        },
        service = MVCResourceCommand.class
)
public class DiskonFakpolEditRenderCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(DiskonFakpolEditRenderCommand.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
        int id = ParamUtil.getInteger(uploadPortletRequest, "entryId");
        JSONObject dto = JSONFactoryUtil.createJSONObject();

        if (id >= 0){
            try {
                DiskonFakpol diskonFakpol = DiskonFakpolLocalServiceUtil.getDiskonFakpol(id);
                dto.put("id", diskonFakpol.getId());
                dto.put("dealerId", diskonFakpol.getDealerId());
                dto.put("fileId", diskonFakpol.getFileId());
                dto.put("fileName", diskonFakpol.getFileName());
                dto.put("filePath", diskonFakpol.getFilePath());
                dto.put("tahun", diskonFakpol.getTahun());
                dto.put("periode", diskonFakpol.getPeriode());
                dto.put("keterangan", diskonFakpol.getKeterangan());
            } catch (PortalException e) {
                _log.error("Error fetching data: " + e.getMessage());
            }
        }
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), dto.toString());
    }
}