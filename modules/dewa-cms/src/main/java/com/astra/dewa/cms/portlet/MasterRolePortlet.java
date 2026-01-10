package com.astra.dewa.cms.portlet;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

/**
 * @author cstagung0825
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Dewa UI",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Master Role",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/master-role/view.jsp",
                "javax.portlet.name=" + CmsPortletKeys.MASTER_ROLE,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class MasterRolePortlet extends MVCPortlet {
    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        super.render(renderRequest, renderResponse);
    }
}