package com.astra.dewa.web.command.render.diskon.fleet;

import com.astra.dewa.model.DiskonFleet;
import com.astra.dewa.service.DiskonFleetLocalServiceUtil;
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
                "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FLEET,
                "mvc.command.name=/diskon-fleet-edit"
        },
        service = MVCResourceCommand.class
)
public class DiskonFleetEditRenderCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(DiskonFleetEditRenderCommand.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
        int id = ParamUtil.getInteger(uploadPortletRequest, "entryId");
        JSONObject dto = JSONFactoryUtil.createJSONObject();

        if (id >= 0) {
            try {
                DiskonFleet diskonFleet = DiskonFleetLocalServiceUtil.getDiskonFleet(id);
                dto.put("id", diskonFleet.getId());
                dto.put("dealerId", diskonFleet.getDealerId());
                dto.put("tahun", diskonFleet.getTahun());
                dto.put("kuartal", diskonFleet.getKuartal());
                dto.put("fileId", diskonFleet.getFileId());
                dto.put("fileName", diskonFleet.getFileName());
                dto.put("filePath", diskonFleet.getFilePath());
                dto.put("keterangan", diskonFleet.getKeterangan());
            } catch (PortalException e) {
                _log.error("Error fetching data: " + e.getMessage());
            }
        }
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), dto.toString());
    }
}