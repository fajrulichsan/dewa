package com.astra.dewa.web.portlet.sales.report;

import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

/**
 * @author psmafifd1401
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Dewa UI",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Sales Report Non CMS",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/sales/report/non-cms/view.jsp",
                "javax.portlet.name=" + DewaWebPortletKeys.SALES_REPORT_NONCMS,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class SalesReportNonCMSPortlet extends MVCPortlet {
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        super.doView(request, response);
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
        super.serveResource(request, response);
    }
}
