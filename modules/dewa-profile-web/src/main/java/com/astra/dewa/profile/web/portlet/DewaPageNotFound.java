package com.astra.dewa.profile.web.portlet;

import com.astra.dewa.profile.web.constants.DewaProfileWebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

/**
 * @author psmmutia0113
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Dewa UI",
                "com.liferay.portlet.header-portlet-css=/assets/css/main.css",
                "com.liferay.portlet.instanceable=false",
                "javax.portlet.display-name=Dewa Page Not Found",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/page-not-found.jsp",
                "javax.portlet.name=" + DewaProfileWebPortletKeys.DEWAPAGENOTFOUND,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class DewaPageNotFound extends MVCPortlet {

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        super.render(renderRequest, renderResponse);
    }

}