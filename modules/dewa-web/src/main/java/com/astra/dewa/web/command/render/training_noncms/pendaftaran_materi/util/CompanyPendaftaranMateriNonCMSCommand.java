package com.astra.dewa.web.command.render.training_noncms.pendaftaran_materi.util;

import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.web.command.render.training_noncms.pendaftaran_materi.file.UploadFilePendaftaranMateriNonCMSCommand;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Component(
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.PENDAFTARAN_PELATIHAN_NONCMS,
                "mvc.command.name=/company-pendaftaran-materi-noncms"
        },
        service = MVCResourceCommand.class
)

public class CompanyPendaftaranMateriNonCMSCommand extends BaseMVCResourceCommand {

    private final Log log = LogFactoryUtil.getLog(UploadFilePendaftaranMateriNonCMSCommand.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        try {
            jsonArray = DealerUtils.selectDealerAndCabang();
        } catch (Exception e) {
            log.error(e);
        }
        JSONObject tableData = JSONFactoryUtil.createJSONObject();
        tableData.put("data", jsonArray);
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), tableData.toJSONString());

    }
}
