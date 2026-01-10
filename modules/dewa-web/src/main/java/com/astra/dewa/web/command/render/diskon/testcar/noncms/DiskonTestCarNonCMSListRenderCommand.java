package com.astra.dewa.web.command.render.diskon.testcar.noncms;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.DiskonTestCar;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.DiskonTestCarLocalServiceUtil;
import com.astra.dewa.service.TipeKendaraanLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.KuartalUtils;
import com.astra.dewa.utils.RoleDealerUtils;
import com.astra.dewa.utils.RolesEnum;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.DISKON_TEST_CAR_NONCMS,
            "mvc.command.name=/diskon-test-car-non-cms-list"
      },
      service = MVCResourceCommand.class
)
public class DiskonTestCarNonCMSListRenderCommand extends BaseMVCResourceCommand {
   private final Log _log = LogFactoryUtil.getLog(DiskonTestCarNonCMSListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

      String dealerId = httpReq.getParameter("dealer");
      String tahun = httpReq.getParameter("tahun");
      String kuartal = httpReq.getParameter("kuartal");
      String tipeKendaraan = httpReq.getParameter("tipeKendaraan");
      String role = "";

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;

      try {
         UsersDealers user = RoleDealerUtils.userId(themeDisplay.getUserId());
         assert user != null;
         int roleGroupId = RoleDealerUtils.getUserRoleGroup(user.getUserId());
         role = RoleDealerUtils.getUserRoleGroupName(roleGroupId);

         if (roleGroupId == RolesEnum.DEALER.getId()) {
            dealerId = String.valueOf(user.getDealerId());
         }

         DynamicQuery query = DiskonTestCarLocalServiceUtil.dynamicQuery();
         if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
         }
         if (!tahun.equalsIgnoreCase("ALL") && !tahun.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("Tahun", Integer.parseInt(tahun)));
         }
         if (!kuartal.equalsIgnoreCase("ALL") && !kuartal.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("KuartalId", kuartal));
         }
         if (!tipeKendaraan.equalsIgnoreCase("ALL") && !tipeKendaraan.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("TipeKendaraanId", Integer.parseInt(tipeKendaraan)));
         }
         if (dealerId.equalsIgnoreCase("ALL")) {
            if (roleGroupId == RolesEnum.HO_DEALER.getId()) {
               Dealer dealer = DealerLocalServiceUtil.getDealer(user.getDealerId());
               DynamicQuery dealersQuery = DealerLocalServiceUtil.dynamicQuery();
               dealersQuery.add(RestrictionsFactoryUtil.eq("KodeHo", dealer.getKodeHo()));
               dealersQuery.add(RestrictionsFactoryUtil.eq("RowStatus", true));
               dealersQuery.setProjection(PropertyFactoryUtil.forName("Id"));
               query.add(PropertyFactoryUtil.forName("DealerId").in(dealersQuery));
            }
         }
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));

         List<DiskonTestCar> diskonTestCars = DiskonTestCarLocalServiceUtil.dynamicQuery(query);

         for (DiskonTestCar diskonTestCar : diskonTestCars) {
            try {
               count++;
               JSONObject dto = JSONFactoryUtil.createJSONObject();
               String kuartalName = KuartalUtils.getKuartalName(diskonTestCar.getKuartalId());
               String tipeKendaraanName = TipeKendaraanLocalServiceUtil.getTipeKendaraan(diskonTestCar.getTipeKendaraanId()).getName();
               String uploadDateAsString = "";
               if (Validator.isNotNull(diskonTestCar.getModifiedDate())) {
                  uploadDateAsString = DateUtil.dateToString(diskonTestCar.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H);
               }
               dto.put("no", count);
               dto.put("id", diskonTestCar.getId());
               dto.put("dealerId", diskonTestCar.getDealerId());
               dto.put("tahun", diskonTestCar.getTahun());
               dto.put("kuartalName", kuartalName);
               dto.put("tipeKendaraanName", tipeKendaraanName);
               dto.put("uploadDate", uploadDateAsString);
               dto.put("uploadDateSort", diskonTestCar.getModifiedDate().getTime());
               dto.put("keterangan", diskonTestCar.getKeterangan());
               dto.put("fileId", diskonTestCar.getFileId());
               dto.put("fileName", diskonTestCar.getFileName());
               dto.put("filePath", diskonTestCar.getFilePath());
               jsonData.put(dto);
            } catch (Exception e) {
               _log.error(e.getMessage(), e);
            }
         }

         acknowledge = 1;
         count = diskonTestCars.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         _log.error(e.getMessage(), e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      jsonObject.put("role", role);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), jsonObject.toJSONString());
   }
}