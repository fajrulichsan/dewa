package com.astra.dewa.web.command.resource.diskon.others;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.DiskonOther;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.DiskonOtherLocalServiceUtil;
import com.astra.dewa.utils.user.DealinkUserUtil;
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
import com.liferay.portal.kernel.exception.PortalException;
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
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

/**
 * @author psmahmad1402
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.DISKON_OTHERS,
                "mvc.command.name=" + DewaWebKeys.NON_CMS_PATH + DewaWebKeys.REALISASI_DISKON_PATH + "/others/list"
        },
        service = MVCResourceCommand.class
)
public class DiskonOthersNonCMSListResourceCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        // data table params
        int start = ParamUtil.getInteger(httpServletRequest, "start");
        int length = ParamUtil.getInteger(httpServletRequest, "length");
        int end = start + length;
        int draw = ParamUtil.getInteger(httpServletRequest, "draw");
        int orderColumn = ParamUtil.getInteger(httpServletRequest, "order[0][column]");
        String orderDir = ParamUtil.getString(httpServletRequest, "order[0][dir]");
        // filter params
        int dealerId = ParamUtil.getInteger(httpServletRequest, "dealerId", -1);
        int tahun = ParamUtil.getInteger(httpServletRequest, "tahun", -1);
        int kuartalId = ParamUtil.getInteger(httpServletRequest, "kuartalId", -1);
        // helpers
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        int count = start;
        long totalRecords = 0;

        try {
            // csrf validation
            AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());
            // active records
            DynamicQuery q = DiskonOtherLocalServiceUtil
                    .dynamicQuery()
                    .add(RestrictionsFactoryUtil.eq("rowStatus", true));
            // count query
            DynamicQuery countQuery = DiskonOtherLocalServiceUtil
                    .dynamicQuery()
                    .add(RestrictionsFactoryUtil.eq("rowStatus", true));
            // get user from user management
            UsersDealers user = RoleDealerUtils.userId(themeDisplay.getUserId());
            if (null == user) {
                throw new PortalException("The selected user has not registered yet.");
            }
            // user group
            RolesEnum userRoleGroup = DealinkUserUtil.getUserRoleGroupEnum(user.getUserId());
            if (null == userRoleGroup) {
                throw new PortalException("The selected user has no active roles.");
            }
            // logic for non super user
            switch (userRoleGroup) {
                case HO_DEALER:
                    Dealer dealer = DealerLocalServiceUtil.getDealer(user.getDealerId());
                    DynamicQuery dealersQuery = DealerLocalServiceUtil
                            .dynamicQuery()
                            .add(RestrictionsFactoryUtil.eq("KodeHo", dealer.getKodeHo()))
                            .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                            .setProjection(PropertyFactoryUtil.forName("Id"));
                    q.add(PropertyFactoryUtil.forName("dealerId").in(dealersQuery));
                    countQuery.add(PropertyFactoryUtil.forName("dealerId").in(dealersQuery));
                    break;
                case DEALER:
                    dealerId = user.getDealerId();
                    break;
            }
            // filter query
            if (dealerId > 0) {
                q.add(RestrictionsFactoryUtil.eq("dealerId", dealerId));
                countQuery.add(RestrictionsFactoryUtil.eq("dealerId", dealerId));
            }
            if (tahun > 0) {
                q.add(RestrictionsFactoryUtil.eq("tahun", tahun));
                countQuery.add(RestrictionsFactoryUtil.eq("tahun", tahun));
            }
            if (kuartalId > 0) {
                q.add(RestrictionsFactoryUtil.eq("kuartalId", kuartalId));
                countQuery.add(RestrictionsFactoryUtil.eq("kuartalId", kuartalId));
            }
            // order column
            switch (orderColumn) {
                case 1:
                    if (orderDir.equals("asc")) {
                        q.addOrder(OrderFactoryUtil.asc("fileName"));
                    } else {
                        q.addOrder(OrderFactoryUtil.desc("fileName"));
                    }
                    break;
                case 2:
                    if (orderDir.equals("asc")) {
                        q.addOrder(OrderFactoryUtil.asc("tahun"));
                    } else {
                        q.addOrder(OrderFactoryUtil.desc("tahun"));
                    }
                    break;
                case 3:
                    if (orderDir.equals("asc")) {
                        q.addOrder(OrderFactoryUtil.asc("kuartalId"));
                    } else {
                        q.addOrder(OrderFactoryUtil.desc("kuartalId"));
                    }
                    break;
                case 4:
                    if (orderDir.equals("asc")) {
                        q.addOrder(OrderFactoryUtil.asc("modifiedDate"));
                    } else {
                        q.addOrder(OrderFactoryUtil.desc("modifiedDate"));
                    }
                    break;
                default:
                    q.addOrder(OrderFactoryUtil.desc("modifiedDate"));
            }

            List<DiskonOther> r = DiskonOtherLocalServiceUtil.dynamicQuery(q, start, end);
            // populate json response
            for (DiskonOther diskonOther : r) {
                try {
                    JSONObject dto = JSONFactoryUtil.createJSONObject();
                    count++;
                    dto.put("no", count);
                    dto.put("id", diskonOther.getDiskonOtherId());
                    dto.put("tahun", diskonOther.getTahun());
                    dto.put("fileName", diskonOther.getFileName());
                    // quarter name
                    String quarter = KuartalUtils.getQuarterName(diskonOther.getKuartalId());
                    if (null == quarter) {
                        _log.warn("No quarter found for diskon other: " + diskonOther.getFileName());
                        quarter = "";
                    }
                    dto.put("kuartal", quarter);
                    // modified date
                    String uploadDateAsString = "";
                    if (Validator.isNotNull(diskonOther.getModifiedDate())) {
                        uploadDateAsString = DateUtil.dateToString(diskonOther.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H);
                    }
                    dto.put("uploadDate", diskonOther.getModifiedDate().getTime());
                    dto.put("uploadDateString", uploadDateAsString);
                    jsonData.put(dto);
                } catch (Exception e) {
                    _log.error("Error fetching diskon other: " + diskonOther.getFileName() + e.getMessage());
                }
            }
            // get total record
            totalRecords = DiskonOtherLocalServiceUtil.dynamicQueryCount(countQuery);
        } catch (Exception e) {
            if (e instanceof PrincipalException) {
                _log.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpServletRequest));
                _log.error("Invalid CSRF token! Token: " + ParamUtil.get(httpServletRequest, "p_auth", "none"), e);
            } else {
                _log.error(e.getMessage(), e);
            }
        }
        JSONObject jsonObject = FORMAT(totalRecords, totalRecords, draw, jsonData);
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
    }
}
