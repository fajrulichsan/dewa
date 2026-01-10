package com.astra.dewa.cms.command.resource.common;

import com.astra.dewa.cms.constants.CmsKeys;
import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.utils.JSONResponseFormatUtil;
import com.astra.dewa.utils.TahunUtils;
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
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author psmahmad1402
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + CmsPortletKeys.DISKON_OTHERS,
                "mvc.command.name=" + CmsKeys.COMMON_PATH + "/resource/year"
        },
        service = MVCResourceCommand.class
)
public class YearResourceCommand extends BaseMVCResourceCommand {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        int acknowledge = 0;
        String status = "";
        String message = "";
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();

        try {
            // csrf validation
            AuthTokenUtil.checkCSRFToken(httpServletRequest, this.getClass().getName());
            // get years
            jsonData = TahunUtils.getYearList();
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
