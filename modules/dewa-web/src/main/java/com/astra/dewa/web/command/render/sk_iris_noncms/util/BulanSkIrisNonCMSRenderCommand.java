package com.astra.dewa.web.command.render.sk_iris_noncms.util;

import com.astra.dewa.utils.BulanUtils;
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
                "javax.portlet.name=" + DewaWebPortletKeys.SK_IRIS_NONCMS,
                "mvc.command.name=/bulan-sk-iris-non-cms"
        },
        service = MVCResourceCommand.class
)
public class BulanSkIrisNonCMSRenderCommand extends BaseMVCResourceCommand {
    private final Log log = LogFactoryUtil.getLog(BulanSkIrisNonCMSRenderCommand.class);
    @Override
    protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
        int acknowledge = 0;
        int count = 0;
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
        try {
            jsonData = BulanUtils.getMonthList();
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
