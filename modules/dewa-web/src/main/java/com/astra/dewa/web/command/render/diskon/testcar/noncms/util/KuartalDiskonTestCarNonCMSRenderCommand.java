package com.astra.dewa.web.command.render.diskon.testcar.noncms.util;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.DiskonTestCar;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.DiskonTestCarLocalServiceUtil;
import com.astra.dewa.utils.KuartalUtils;
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
            "javax.portlet.name=" + DewaWebPortletKeys.DISKON_TEST_CAR_NONCMS,
            "mvc.command.name=/kuartal-diskon-test-car-non-cms"
      },
      service = MVCResourceCommand.class
)
public class KuartalDiskonTestCarNonCMSRenderCommand extends BaseMVCResourceCommand {

   private final Log log = LogFactoryUtil.getLog(KuartalDiskonTestCarNonCMSRenderCommand.class);
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
         jsonData = kuartalList();
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

   private JSONArray kuartalList() {
      List<String> entryKuartalList = getEntryKuartalList();
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      try {
         entryKuartalList.forEach(kuartal -> {
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
            jsonObject.put("id", kuartal);
            jsonObject.put("text", KuartalUtils.getKuartalName(kuartal));
            jsonData.put(jsonObject);
         });
      } catch (Exception e) {
         log.error(e);
      }
      return jsonData;
   }

   private List<String> getEntryKuartalList() {
      List<String> kuartalList = new ArrayList<>();
      try {
         DynamicQuery query = DiskonTestCarLocalServiceUtil.dynamicQuery();
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
         List<DiskonTestCar> diskonTestCars = DiskonTestCarLocalServiceUtil.dynamicQuery(query);
         diskonTestCars.forEach(data -> {
            if (!kuartalList.contains(data.getKuartalId())) {
               kuartalList.add(data.getKuartalId());
            }
         });
      } catch (Exception e) {
         log.error(e);
      }
      Collections.sort(kuartalList);
      return kuartalList;
   }

}
