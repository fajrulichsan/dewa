package com.astra.dewa.login.portlet;

import com.astra.dewa.login.constants.DewaLoginPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Dewa UI",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=DewaRegisterPublic",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/register/view.jsp",
                "javax.portlet.name=" + DewaLoginPortletKeys.REGISTER,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class RegisterPortlet extends MVCPortlet {
    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        super.doView(request, response);
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
        super.serveResource(request, response);
    }
}