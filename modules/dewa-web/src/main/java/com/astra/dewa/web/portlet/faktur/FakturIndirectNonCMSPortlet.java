package com.astra.dewa.web.portlet.faktur;

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
 * @author psmahmad1402
 */
@Component(
      immediate = true,
      property = {
            "com.liferay.portlet.display-category=Dewa UI",
            "com.liferay.portlet.instanceable=true",
            "javax.portlet.display-name=Faktur Indirect Non CMS",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/faktur-indirect/non-cms/view.jsp",
            "javax.portlet.name=" + DewaWebPortletKeys.FAKTUR_INDIRECT_NONCMS,
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user"
      },
      service = Portlet.class
)
public class FakturIndirectNonCMSPortlet extends MVCPortlet {
   @Override
   public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
      super.doView(renderRequest, renderResponse);
   }

   @Override
   public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
      super.serveResource(resourceRequest, resourceResponse);
   }
}