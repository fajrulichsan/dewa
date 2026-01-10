package com.astra.dewa.web.command.resource.diskon.others;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.DiskonOtherLocalServiceUtil;
import com.astra.dewa.utils.user.DealinkUserUtil;
import com.astra.dewa.utils.JSONResponseFormatUtil;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RolesEnum;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
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
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author psmahmad1402
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.DISKON_OTHERS,
                "mvc.command.name=" + DewaWebKeys.NON_CMS_PATH + DewaWebKeys.REALISASI_DISKON_PATH + "/others/year"
        },
        service = MVCResourceCommand.class
)
public class DiskonOthersYearNonCMSResourceCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        int acknowledge = 0;
        String status = "";
        String message = "";
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();

        try {
            // csrf validation
            AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());
            // active records
            DynamicQuery q = DiskonOtherLocalServiceUtil
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
                    break;
                case DEALER:
                    q.add(RestrictionsFactoryUtil.eq("dealerId", user.getDealerId()));
                    break;
            }
            // set year column only as result
            q.setProjection(PropertyFactoryUtil.forName("tahun"));

            List<Integer> yearResult = new ArrayList<>();
            List<Integer> years = DiskonOtherLocalServiceUtil.dynamicQuery(q);

            for (Integer year : years) {
                if (!yearResult.contains(year)) {
                    yearResult.add(year);
                }
            }

            Collections.sort(yearResult);

            for (Integer year : yearResult) {
                JSONObject dto = JSONResponseFormatUtil.select2Format(year, String.valueOf(year));
                jsonData.put(dto);
            }

            acknowledge = 1;
        } catch (Exception e) {
            status = "error";
            if (e instanceof PrincipalException) {
                message = "You are not authorized to access resource.";
                _log.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpServletRequest));
                _log.error("Invalid CSRF token! Token: " + ParamUtil.get(httpServletRequest, "p_auth", "none"), e);
            } else {
                message = "Oops! Something went wrong.";
                _log.error(e.getMessage(), e);
            }
        }
        JSONObject jsonObject = JSONResponseFormatUtil.select2ResponseFormat(acknowledge, jsonData, status, message);
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
    }
}
