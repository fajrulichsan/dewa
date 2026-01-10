package com.astra.dewa.login.command.resource;

import com.astra.dewa.login.constants.DewaLoginPortletKeys;
import com.astra.dewa.utils.CabangUtils;
import com.astra.dewa.utils.GroupDealerEnum;
import com.astra.dewa.utils.RolesEnum;
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
import javax.servlet.http.HttpServletRequest;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

/**
 * @author psmmutia0113
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DewaLoginPortletKeys.REGISTER,
                "mvc.command.name=/register/cabangs"
        },
        service = MVCResourceCommand.class
)
public class CabangResourceCommand extends BaseMVCResourceCommand {
    private final Log LOG = LogFactoryUtil.getLog(CabangResourceCommand.class);

    @Override
    protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
        int acknowledge = 0;
        int count = 0;
        int dealerId = -1;
        int groupId = -1;
        JSONArray jsonData = JSONFactoryUtil.createJSONArray();
        JSONObject jsonMessage;
        try {
            if (!httpReq.getParameter("dealerId").isEmpty()) {
                dealerId = Integer.parseInt(httpReq.getParameter("dealerId"));
            }
            if (!httpReq.getParameter("groupId").isEmpty()) {
                groupId = Integer.parseInt(httpReq.getParameter("groupId"));
            }
            int groupDealer = -1;
//            if (groupId == RolesEnum.HO_DEALER.getId()) groupDealer = GroupDealerEnum.HO_DEALER.ordinal();
//            else if (groupId == RolesEnum.DEALER.getId()) groupDealer = GroupDealerEnum.DEALER_CABANG.ordinal();
//            jsonData = CabangUtils.selectCabang(dealerId, groupDealer);
            if (groupId == RolesEnum.HO_DEALER.getId())  {
                groupDealer = GroupDealerEnum.HO_DEALER.ordinal();
                jsonData = CabangUtils.selectCabang(dealerId, groupDealer);
            } else if (groupId == RolesEnum.DEALER.getId()) {
                jsonData = CabangUtils.selectCabang(dealerId);
            }
            acknowledge = 1;
            count = jsonData.length();
            jsonMessage = SUCCESS(200, "OK");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            jsonMessage = ERROR(500, e.getMessage());
        }
        JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
    }
}
