package com.astra.dewa.web.portlet;

import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;

/**
 * @author HP
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Dewa UI",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Pendaftaran Pelatihan NonCMS",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/training-noncms/pendaftaran/view.jsp",
                "javax.portlet.name=" + DewaWebPortletKeys.PENDAFTARAN_PELATIHAN_NONCMS,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class TrainingDealerAgendaPortlet extends MVCPortlet {

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        super.doView(request, response);
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
        super.serveResource(request, response);
    }

}