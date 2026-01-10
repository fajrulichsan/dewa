package com.astra.dewa.cms.command.resource.user.management.util;

import com.astra.dewa.cms.constants.CmsPortletKeys;
import com.astra.dewa.model.Cabang;
import com.astra.dewa.model.Dealer;
import com.astra.dewa.service.CabangLocalServiceUtil;
import com.astra.dewa.service.DealerLocalServiceUtil;
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
      "mvc.command.name=/cabang-role-dealer"
   },
   service = MVCResourceCommand.class
)
public class CabangUserManagementResourceCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(CabangUserManagementResourceCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();

      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

      String dealerId = httpReq.getParameter("dealerId");

      try {
         if (!dealerId.isEmpty()) {
            Dealer dealer = DealerLocalServiceUtil.getDealer(Integer.parseInt(dealerId));
            Cabang cabang = CabangLocalServiceUtil.getCabang(dealer.getCabangId());

            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("id", cabang.getId());
            dto.put("text", cabang.getName());
            jsonData.put(dto);
         }
         acknowledge = 1;
         count = jsonData.length();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }

}
