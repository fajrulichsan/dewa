package com.astra.dewa.web.command.action;

import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaWebPortletKeys.KATEGORI_DEALER_NONCMS,
                "mvc.command.name=/kategori-dealer-noncms-action"
        },
        service = MVCResourceCommand.class
)
public class KategoriDealerActionNonCMSCommand extends BaseMVCResourceCommand {
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

    }
}
