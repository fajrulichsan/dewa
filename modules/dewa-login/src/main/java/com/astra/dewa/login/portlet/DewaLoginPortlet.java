package com.astra.dewa.login.portlet;

import com.astra.dewa.login.constants.DewaLoginPortletKeys;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.utils.RoleDealerUtils;
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
 * @author psmmutia0113
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Dewa UI",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=DewaLoginPublic",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/login/view.jsp",
                "javax.portlet.name=" + DewaLoginPortletKeys.DEWALOGIN,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class DewaLoginPortlet extends MVCPortlet {
    @Override
    public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        UsersDealers usersDealers = RoleDealerUtils.userId(themeDisplay.getUserId());
        request.setAttribute("isSignedIn", themeDisplay.isSignedIn());
        request.setAttribute("isRegistered", usersDealers != null);
        super.render(request, response);
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
        super.serveResource(request, response);
    }
}