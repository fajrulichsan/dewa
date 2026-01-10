package com.astra.dewa.login.command.resource;

import com.astra.dewa.login.constants.DewaLoginPortletKeys;
import com.astra.dewa.utils.DealerUtils;
import com.astra.dewa.utils.GroupDealerEnum;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;

/**
 * @author psmmutia0113
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaLoginPortletKeys.REGISTER,
                "mvc.command.name=/register/dealers"
        },
        service = MVCResourceCommand.class
)
public class DealerResourceCommand extends BaseMVCResourceCommand {
    private final Log LOG = LogFactoryUtil.getLog(DealerResourceCommand.class);

    @Override
    protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
        int acknowledge = 0;
        int count = 0;
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        JSONObject jsonMessage;
        try {
            jsonData = DealerUtils.selectGroupDealer(GroupDealerEnum.HO_DEALER.ordinal());
            acknowledge = 1;
            count = jsonData.length();
            jsonMessage = SUCCESS(200, "OK");
        } catch (SystemException e) {
            LOG.error(e);
            jsonMessage = ERROR(500, e.getMessage());
        }
        JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
    }
}
