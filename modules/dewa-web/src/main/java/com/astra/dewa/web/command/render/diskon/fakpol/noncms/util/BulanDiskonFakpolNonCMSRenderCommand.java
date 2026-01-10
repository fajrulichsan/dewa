package com.astra.dewa.web.command.render.diskon.fakpol.noncms.util;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.DiskonFakpol;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.DiskonFakpolLocalServiceUtil;
import com.astra.dewa.utils.BulanUtils;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RoleUtils;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.*;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.DISKON_FAKPOL_NONCMS,
            "mvc.command.name=/bulan-diskon-fakpol-non-cms"
      },
      service = MVCResourceCommand.class
)
public class BulanDiskonFakpolNonCMSRenderCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(BulanDiskonFakpolNonCMSRenderCommand.class);
   private String role;
   private UsersDealers usersDealers;

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      this.usersDealers = RoleDealerUtils.userId(themeDisplay.getUserId());
      this.role = RoleUtils.getRoleName(usersDealers.getRoleId());
      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
      try {
         jsonData = monthList();
         acknowledge = 1;
         count = jsonData.length();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }

   private JSONArray monthList() {
      List<Integer> entryMonthList = getEntryMonthList();
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      try {
         entryMonthList.forEach(month -> {
            String monthName = BulanUtils.getMonthById(month);
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
            jsonObject.put("id", monthName);
            jsonObject.put("text", monthName);
            jsonData.put(jsonObject);
         });
      } catch (Exception e) {
         log.error(e);
      }
      return jsonData;
   }

   private List<Integer> getEntryMonthList() {
      List<Integer> monthIndexList = new ArrayList<>();
      try {
         DynamicQuery query = DiskonFakpolLocalServiceUtil.dynamicQuery();
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         if (role.equalsIgnoreCase("HO Dealer")) {
            Dealer dealer = DealerLocalServiceUtil.getDealer(usersDealers.getDealerId());
            DynamicQuery dealersQuery = DealerLocalServiceUtil.dynamicQuery();
            dealersQuery.add(RestrictionsFactoryUtil.eq("KodeHo", dealer.getKodeHo()));
            dealersQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));
            dealersQuery.setProjection(PropertyFactoryUtil.forName("Id"));
            query.add(PropertyFactoryUtil.forName("DealerId").in(dealersQuery));
         } else if (role.equalsIgnoreCase("Dealer")) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", usersDealers.getDealerId()));
         }
         List<DiskonFakpol> diskonFakpols = DiskonFakpolLocalServiceUtil.dynamicQuery(query);
         diskonFakpols.forEach(data -> {
            int monthIndex = DateUtil.getMonthNumberByName(data.getPeriode()) - 1;
            if (!monthIndexList.contains(monthIndex)) {
               monthIndexList.add(monthIndex);
            }
         });
      } catch (Exception e) {
         log.error(e);
      }
      Collections.sort(monthIndexList);
      return monthIndexList;
   }

}
