package com.astra.dewa.web.command.resource.common;

import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.utils.CustomDealerUtil;
import com.astra.dewa.utils.user.DealinkUserUtil;
import com.astra.dewa.utils.JSONResponseFormatUtil;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RolesEnum;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
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
import java.util.List;

/**
 * @author psmahmad1402
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.DISKON_OTHERS,
                "mvc.command.name=" + DewaWebKeys.NON_CMS_PATH + "/resource/dealer"
        },
        service = MVCResourceCommand.class
)
public class DealerNonCMSResourceCommand extends BaseMVCResourceCommand {
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
            // get user information
            UsersDealers user = RoleDealerUtils.userId(themeDisplay.getUserId());
            if (null == user) {
                throw new PortalException("The selected user has not registered yet.");
            }
            // user group
            RolesEnum userRoleGroup = DealinkUserUtil.getUserRoleGroupEnum(user.getUserId());
            if (null == userRoleGroup) {
                throw new PortalException("The selected user has no active roles.");
            }
            List<Dealer> dealers = CustomDealerUtil.getDealersByUserGroup(user);
            if (null != dealers) {
                for (Dealer d : dealers) {
                    // get branch information
                    String cabangName = "";
                    try {
                        Cabang c = CabangLocalServiceUtil.getCabang(d.getCabangId());
                        cabangName = c.getName();
                    } catch (Exception e) {
                        _log.warn("Error fetching branch information for dealer: " + d.getCode());
                    }
                    // populate result
                    JSONObject dto = JSONResponseFormatUtil.select2Format(d.getId(), d.getCode() + " - " + d.getName() + " - " + cabangName);
                    jsonData.put(dto);
                }
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
