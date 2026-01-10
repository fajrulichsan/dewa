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
            "javax.portlet.display-name=Faktur Pajak Non CMS",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/faktur-pajak/non-cms/view.jsp",
            "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_PAJAK_NONCMS,
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user"
      },
      service = Portlet.class
)
public class FakturPajakNonCMSPortlet extends MVCPortlet {
   @Override
   public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
      super.doView(renderRequest, renderResponse);
   }
   @Override
   public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
      super.serveResource(resourceRequest, resourceResponse);
   }
}
