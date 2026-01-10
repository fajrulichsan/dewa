package com.astra.dewa.web.command.render.diskon.fakpol;

import com.astra.dewa.model.DiskonFakpol;
import com.astra.dewa.service.DiskonFakpolLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FAKPOL,
                "mvc.command.name=/diskon-fakpol-list"
        },
        service = MVCResourceCommand.class)
public class DiskonFakpolListRenderCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(DiskonFakpolListRenderCommand.class);

    @Override
    protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
        HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

        String dealerId = httpServletRequest.getParameter("dealer");
        String tahun = httpServletRequest.getParameter("tahun");
        String bulan = httpServletRequest.getParameter("periode");

        int acknowledge = 0;
        int count = 0;

        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();

        try {
            AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

            List<DiskonFakpol> diskonFakpols = new ArrayList<>();

            DynamicQuery query = DiskonFakpolLocalServiceUtil.dynamicQuery();
            DynamicQuery countQuery = DiskonFakpolLocalServiceUtil.dynamicQuery();

            if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
                query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
                countQuery.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
            }
            if (!tahun.equalsIgnoreCase("ALL") && !tahun.isEmpty()) {
                query.add(RestrictionsFactoryUtil.eq("Tahun", Integer.parseInt(tahun)));
                countQuery.add(RestrictionsFactoryUtil.eq("Tahun", Integer.parseInt(tahun)));
            }
            if (!bulan.equalsIgnoreCase("ALL") && !bulan.isEmpty()) {
                query.add(RestrictionsFactoryUtil.eq("Periode", bulan));
                countQuery.add(RestrictionsFactoryUtil.eq("Periode", bulan));
            }

            query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            countQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));

            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));

            diskonFakpols = DiskonFakpolLocalServiceUtil.dynamicQuery(query);

            for (DiskonFakpol diskonFakpol : diskonFakpols) {
                try {
                    count++;
                    JSONObject dto = JSONFactoryUtil.createJSONObject();
                    String uploadDateAsString = "";
                    if (Validator.isNotNull(diskonFakpol.getModifiedDate())) {
                        uploadDateAsString = DateUtil.dateToString(diskonFakpol.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H);
                    }
                    dto.put("no", count);
                    dto.put("id", diskonFakpol.getId());
                    dto.put("dealerId", diskonFakpol.getDealerId());
                    dto.put("tahun", diskonFakpol.getTahun());
                    dto.put("periode", diskonFakpol.getPeriode());
                    dto.put("periodNumber", DateUtil.getMonthNumberByName(diskonFakpol.getPeriode()));
                    dto.put("uploadDate", uploadDateAsString);
                    dto.put("uploadDateSort", diskonFakpol.getModifiedDate().getTime());
                    dto.put("keterangan", diskonFakpol.getKeterangan());
                    dto.put("fileId", diskonFakpol.getFileId());
                    dto.put("fileName", diskonFakpol.getFileName());
                    dto.put("filePath", diskonFakpol.getFilePath());
                    jsonData.put(dto);
                } catch (Exception e) {
                    _log.error(e.getMessage(), e);
                }
            }

            acknowledge = 1;
            count = diskonFakpols.size();
            jsonMessage = SUCCESS(200, "OK");
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            jsonMessage = ERROR(500, e.getMessage());
        }
        JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
    }
}