package com.astra.dewa.web.command.render.diskon.fakpol.noncms;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.DiskonFakpol;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.DiskonFakpolLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
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
                "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FAKPOL_NONCMS,
                "mvc.command.name=/diskon-fakpol-non-cms-list"
        },
        service = MVCResourceCommand.class
)
public class DiskonFakpolNonCMSListRenderCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(DiskonFakpolNonCMSListRenderCommand.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

        String dealerId = httpReq.getParameter("dealer");
        String tahun = httpReq.getParameter("tahun");
        String bulan = httpReq.getParameter("periode");
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

            DynamicQuery query = DiskonFakpolLocalServiceUtil.dynamicQuery();
            if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
                query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
            }
            if (!tahun.equalsIgnoreCase("ALL") && !tahun.isEmpty()) {
                query.add(RestrictionsFactoryUtil.eq("Tahun", Integer.parseInt(tahun)));
            }
            if (!bulan.equalsIgnoreCase("ALL") && !bulan.isEmpty()) {
                query.add(RestrictionsFactoryUtil.eq("Periode", bulan));
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

            List<DiskonFakpol> diskonFakpols = DiskonFakpolLocalServiceUtil.dynamicQuery(query);

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
                    dto.put("uploadDate",  uploadDateAsString);
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
        jsonObject.put("role", role);
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
    }
}