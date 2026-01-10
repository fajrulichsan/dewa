package com.astra.dewa.cms.command.resource.user.management.util;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.utils.DealerUtils;
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

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + CmsPortletKeys.ROLEDEALERWEB,
      "mvc.command.name=/dealer-role-dealer"
   },
   service = MVCResourceCommand.class
)
public class DealerUserManagementResourceCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(DealerUserManagementResourceCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

      String roleGroupId = httpReq.getParameter("roleGroupId");

      try {
//         if (!roleGroupId.isEmpty()) {
//            if (Integer.parseInt(roleGroupId) == RolesEnum.HO_DEALER.getId()) {
//               jsonData = DealerUtils.selectDealerByGroupId(GroupDealerEnum.HO_DEALER.ordinal());
//            } else if (Integer.parseInt(roleGroupId) == RolesEnum.DEALER.getId()) {
//               jsonData = DealerUtils.selectDealerByGroupId(GroupDealerEnum.DEALER_CABANG.ordinal());
//            }
//         }
         if (!roleGroupId.isEmpty()) {
            if (Integer.parseInt(roleGroupId) == RolesEnum.HO_DEALER.getId()) {
               jsonData = DealerUtils.selectDealerByGroupId(GroupDealerEnum.HO_DEALER.ordinal());
            } else if (Integer.parseInt(roleGroupId) == RolesEnum.DEALER.getId()) {
               jsonData = DealerUtils.selectDetailDealer();
            }
         }
         acknowledge = 1;
         count = jsonData.length();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         LOG.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

}
