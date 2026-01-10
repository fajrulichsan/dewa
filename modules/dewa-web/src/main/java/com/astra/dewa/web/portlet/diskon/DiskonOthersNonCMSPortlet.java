package com.astra.dewa.web.portlet.diskon;

import com.astra.dewa.utils.user.DealinkUserUtil;
import com.astra.dewa.utils.RolesEnum;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

/**
 * @author psmahmad1402
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Dewa UI",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=" + DewaWebKeys.REALISASI_DISKON_OTHERS + DewaWebKeys.NON_CMS,
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=" + DewaWebKeys.REALISASI_DISKON_PATH + "/others/view.jsp",
                "javax.portlet.name=" + DewaWebPortletKeys.DISKON_OTHERS,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class DiskonOthersNonCMSPortlet extends MVCPortlet {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        boolean hasDealerAccess = false;
        try {
            RolesEnum userRoleGroup = DealinkUserUtil.getUserRoleGroupEnum(themeDisplay.getUserId());
            if (null == userRoleGroup) {
                throw new PortalException("The selected user has no active roles.");
            }
            switch (userRoleGroup) {
                case ADMIN_DSO:
                case ADMIN_DIVISION:
                case DSO:
                case HO_DEALER:
                    hasDealerAccess = true;
                    break;
            }
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
        request.setAttribute("hasDealerAccess", hasDealerAccess);
        request.setAttribute("currentMenu", DewaWebKeys.REALISASI_DISKON_OTHERS);
        super.doView(request, response);
    }
    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
        super.serveResource(request, response);
    }
}
