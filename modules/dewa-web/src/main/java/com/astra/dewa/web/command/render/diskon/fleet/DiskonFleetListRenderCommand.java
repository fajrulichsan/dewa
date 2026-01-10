package com.astra.dewa.web.command.render.diskon.fleet;

import com.astra.dewa.model.DiskonFleet;
import com.astra.dewa.service.DiskonFleetLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.KuartalUtils;
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
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FLEET,
                "mvc.command.name=/diskon-fleet-list"
        },
        service = MVCResourceCommand.class
)
public class DiskonFleetListRenderCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(DiskonFleetListRenderCommand.class);

    @Override
    protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
        HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

        int start = ParamUtil.getInteger(httpServletRequest, "start");
        int length = ParamUtil.getInteger(httpServletRequest, "length");
        int end = start + length;
        int draw = ParamUtil.getInteger(httpServletRequest, "draw");
        int orderColumn = ParamUtil.getInteger(httpServletRequest, "order[0][column]");
        String orderDir = ParamUtil.getString(httpServletRequest, "order[0][dir]");

        String dealerId = httpServletRequest.getParameter("dealer");
        String tahun = httpServletRequest.getParameter("tahun");
        String kuartal = httpServletRequest.getParameter("kuartal");

        int count = start;
        long totalRecords = 0;

        JSONArray jsonData = JSONFactoryUtil.createJSONArray();

        try {
            AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());

            List<DiskonFleet> diskonFleets = new ArrayList<>();

            DynamicQuery query = DiskonFleetLocalServiceUtil.dynamicQuery();
            DynamicQuery countQuery = DiskonFleetLocalServiceUtil.dynamicQuery();

            if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
                query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
                countQuery.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
            }
            if (!tahun.equalsIgnoreCase("ALL") && !tahun.isEmpty()) {
                query.add(RestrictionsFactoryUtil.eq("Tahun", Integer.parseInt(tahun)));
                countQuery.add(RestrictionsFactoryUtil.eq("Tahun", Integer.parseInt(tahun)));
            }
            if (!kuartal.equalsIgnoreCase("ALL") && !kuartal.isEmpty()) {
                query.add(RestrictionsFactoryUtil.eq("Kuartal", kuartal));
                countQuery.add(RestrictionsFactoryUtil.eq("Kuartal", kuartal));
            }

            query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            countQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));

            switch (orderColumn) {
                case 1:
                    if (orderDir.equals("asc")) {
                        query.addOrder(OrderFactoryUtil.asc("FileName"));
                    } else {
                        query.addOrder(OrderFactoryUtil.desc("FileName"));
                    }
                    break;
                case 2:
                    if (orderDir.equals("asc")) {
                        query.addOrder(OrderFactoryUtil.asc("Tahun"));
                    } else {
                        query.addOrder(OrderFactoryUtil.desc("Tahun"));
                    }
                    break;
                case 3:
                    if (orderDir.equals("asc")) {
                        query.addOrder(OrderFactoryUtil.asc("Kuartal"));
                    } else {
                        query.addOrder(OrderFactoryUtil.desc("Kuartal"));
                    }
                    break;
                case 4:
                    if (orderDir.equals("asc")) {
                        query.addOrder(OrderFactoryUtil.asc("ModifiedDate"));
                    } else {
                        query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
                    }
                    break;
                default:
                    query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
            }

            diskonFleets = DiskonFleetLocalServiceUtil.dynamicQuery(query, start, end);

            for (DiskonFleet diskonFleet : diskonFleets) {
                try {
                    count++;
                    JSONObject dto = JSONFactoryUtil.createJSONObject();
                    String dealerCode = DealerUtils.getDealerCode(diskonFleet.getDealerId());
                    String kuartalName = KuartalUtils.getKuartalName(diskonFleet.getKuartal());
                    String uploadDateAsString = "";
                    if (Validator.isNotNull(diskonFleet.getModifiedDate())) {
                        uploadDateAsString = DateUtil.dateToString(diskonFleet.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H);
                    }
                    dto.put("no", count);
                    dto.put("id", diskonFleet.getId());
                    dto.put("dealerId", diskonFleet.getDealerId());
                    dto.put("dealerCode", dealerCode);
                    dto.put("tahun", diskonFleet.getTahun());
                    dto.put("kuartal", diskonFleet.getKuartal());
                    dto.put("kuartalName", kuartalName);
                    dto.put("uploadDate", uploadDateAsString);
                    dto.put("uploadDateSort", diskonFleet.getModifiedDate().getTime());
                    dto.put("keterangan", diskonFleet.getKeterangan());
                    dto.put("fileId", diskonFleet.getFileId());
                    dto.put("fileName", diskonFleet.getFileName());
                    dto.put("filePath", diskonFleet.getFilePath());
                    jsonData.put(dto);
                } catch (Exception e) {
                    _log.error(e.getMessage(), e);
                }
            }

            totalRecords = DiskonFleetLocalServiceUtil.dynamicQueryCount(countQuery);
        } catch (Exception e) {
            if (e instanceof PrincipalException) {
                _log.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpServletRequest));
                _log.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpServletRequest, "p_auth", "none"), e);
            } else {
                _log.error(e.getMessage(), e);
            }
        }
        JSONObject jsonObject = FORMAT(totalRecords, totalRecords, draw, jsonData);
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
    }
}