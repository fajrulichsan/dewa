package com.astra.dewa.web.portlet;

import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;

@Component(
      immediate = true,
      property = {
            "com.liferay.portlet.display-category=Dewa UI",
            "com.liferay.portlet.instanceable=true",
            "javax.portlet.display-name=Realisasi Diskon Test Car Non CMS",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/diskon/diskon-test-car/non-cms/view.jsp",
            "javax.portlet.name=" + DewaWebPortletKeys.DISKON_TEST_CAR_NONCMS,
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user"
      },
      service = Portlet.class
)
public class DiskonTestCarNonCMSPortlet extends MVCPortlet {

   @Override
   public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
      super.doView(request, response);
   }

   @Override
   public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
      super.serveResource(request, response);
   }

}
