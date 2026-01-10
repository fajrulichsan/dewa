package com.astra.dewa.web.command.render.diskon.fleet.noncms;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.DiskonFleet;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.DiskonFleetLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.KuartalUtils;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RolesEnum;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FLEET_NONCMS,
                "mvc.command.name=/diskon-fleet-non-cms-list"
        },
        service = MVCResourceCommand.class
)
public class DiskonFleetNonCMSListRenderCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(DiskonFleetNonCMSListRenderCommand.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

        String dealerId = httpServletRequest.getParameter("dealer");
        String tahun = httpServletRequest.getParameter("tahun");
        String kuartal = httpServletRequest.getParameter("kuartal");
        String role = "";

        int acknowledge = 0;
        int count = 0;

        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        JSONObject jsonMessage;

        try {
            UsersDealers user = RoleDealerUtils.userId(themeDisplay.getUserId());
            assert user != null;
            int roleGroupId = RoleDealerUtils.getUserRoleGroup(user.getUserId());
            role = RoleDealerUtils.getUserRoleGroupName(roleGroupId);

            if (roleGroupId == RolesEnum.DEALER.getId()) {
                dealerId = String.valueOf(user.getDealerId());
            }

            DynamicQuery query = DiskonFleetLocalServiceUtil.dynamicQuery();
            if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
                query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
            }
            if (!tahun.equalsIgnoreCase("ALL") && !tahun.isEmpty()) {
                query.add(RestrictionsFactoryUtil.eq("Tahun", Integer.parseInt(tahun)));
            }
            if (!kuartal.equalsIgnoreCase("ALL") && !kuartal.isEmpty()) {
                query.add(RestrictionsFactoryUtil.eq("Kuartal", kuartal));
            }
            if (dealerId.equalsIgnoreCase("ALL")) {
                if (roleGroupId == RolesEnum.HO_DEALER.getId()) {
                    Dealer dealer = DealerLocalServiceUtil.getDealer(user.getDealerId());
                    DynamicQuery dealersQuery = DealerLocalServiceUtil.dynamicQuery();
                    dealersQuery.add(RestrictionsFactoryUtil.eq("KodeHo", dealer.getKodeHo()));
                    dealersQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));
                    dealersQuery.setProjection(PropertyFactoryUtil.forName("Id"));
                    query.add(PropertyFactoryUtil.forName("DealerId").in(dealersQuery));
                }
            }
            query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));

            List<DiskonFleet> diskonFleets = DiskonFleetLocalServiceUtil.dynamicQuery(query);

            for (DiskonFleet diskonFleet : diskonFleets) {
                count++;
                JSONObject dto = JSONFactoryUtil.createJSONObject();
                try {
                    String kuartalName = KuartalUtils.getKuartalName(diskonFleet.getKuartal());
                    String uploadDateAsString = "";
                    if (Validator.isNotNull(diskonFleet.getModifiedDate())) {
                        uploadDateAsString = DateUtil.dateToString(diskonFleet.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H);
                    }
                    dto.put("no", count);
                    dto.put("id", diskonFleet.getId());
                    dto.put("dealerId", diskonFleet.getDealerId());
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

            acknowledge = 1;
            count = diskonFleets.size();
            jsonMessage = SUCCESS(200, "OK");
        } catch (Exception e) {
            jsonMessage = ERROR(500, e.getMessage());
            _log.error(e.getMessage(), e);
        }
        JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
        jsonObject.put("role", role);
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
    }
}