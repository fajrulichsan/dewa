package com.astra.dewa.cms.portlet.diskon;

import com.astra.dewa.cms.constants.CmsKeys;
import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
                "javax.portlet.display-name=" + CmsKeys.REALISASI_DISKON_OTHERS,
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=" + CmsKeys.REALISASI_DISKON_PATH + "/others/view.jsp",
                "javax.portlet.name=" + CmsPortletKeys.DISKON_OTHERS,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class DiskonOthersPortlet extends MVCPortlet {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        super.doView(renderRequest, renderResponse);
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
        super.serveResource(resourceRequest, resourceResponse);
    }
}
