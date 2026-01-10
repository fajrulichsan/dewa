package com.astra.dewa.web.command.render.sk_iris_noncms.util;

import com.astra.dewa.model.SkIris;
import com.astra.dewa.service.SkIrisLocalServiceUtil;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.*;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.SK_IRIS_NONCMS,
                "mvc.command.name=/tahun-sk-iris-non-cms"
        },
        service = MVCResourceCommand.class
)
public class TahunSkIrisRenderNonCMSCommand extends BaseMVCResourceCommand {
    private final Log log = LogFactoryUtil.getLog(TahunSkIrisRenderNonCMSCommand.class);
    @Override
    protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
        int acknowledge = 0;
        int count = 0;
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
        try {
            jsonData = yearList();
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
    private JSONArray yearList() {
        List<Integer> entryYearsList = getEntryYearsList();
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        try {
            entryYearsList.forEach(year ->{
                JSONObject dto = JSONFactoryUtil.createJSONObject();
                dto.put("id", year);
                dto.put("text", year);
                jsonData.put(dto);
            });
        } catch (Exception e) {
            log.error(e);
        }
        return jsonData;
    }
    private List<Integer> getEntryYearsList() {
        List<Integer> yearList = new ArrayList<>();
        try {
            DynamicQuery query = SkIrisLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            List<SkIris> skIris = SkIrisLocalServiceUtil.dynamicQuery(query);
            skIris.forEach(data -> {
                if (!yearList.contains(data.getTahun())) {
                    yearList.add(data.getTahun());
                }
            });
        } catch (Exception e) {
            log.error(e);
        }
        Collections.sort(yearList);
        return yearList;
    }
}
