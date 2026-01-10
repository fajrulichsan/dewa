package com.astra.dewa.web.command.render.surat_penalty_stock_noncms.util;

import com.astra.dewa.utils.PeriodeReviewUtils;
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

import static com.astra.dewa.utils.JSONResponseFormatUtil.*;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.SURAT_PENALTY_STOCK_NONCMS,
                "mvc.command.name=/periode-review-surat-penalty-stock-noncms"
        },
        service = MVCResourceCommand.class
)

public class PeriodeReviewSPSNonCMSRenderCommand extends BaseMVCResourceCommand {

    private final Log log = LogFactoryUtil.getLog(PeriodeReviewSPSNonCMSRenderCommand.class);

    @Override
    protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
        int acknowledge = 0;
        int count = 0;
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
        try {
            jsonData = PeriodeReviewUtils.selectPeriode();
            acknowledge = 1;
            count = jsonData.length();
            jsonMessage = SUCCESS(200, "OK");
        } catch (Exception e) {
            log.error(e);
            jsonMessage = ERROR(500, e.getMessage());
        }
        JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
    }

}
