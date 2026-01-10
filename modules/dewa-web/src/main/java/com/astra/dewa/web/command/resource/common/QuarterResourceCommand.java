package com.astra.dewa.web.command.resource.common;

import com.astra.dewa.utils.JSONResponseFormatUtil;
import com.astra.dewa.utils.KuartalUtils;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
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
import java.util.Map;

/**
 * @author psmahmad1402
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.DISKON_OTHERS,
                "mvc.command.name=" + DewaWebKeys.COMMON_PATH + "/resource/quarter"
        },
        service = MVCResourceCommand.class
)
public class QuarterResourceCommand extends BaseMVCResourceCommand {
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
            // get quarters
            Map<Integer, String> quarters = KuartalUtils.getQuarters();
            for (Map.Entry<Integer, String> q : quarters.entrySet()) {
                JSONObject dto = JSONResponseFormatUtil.select2Format(q.getKey(), q.getValue());
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
