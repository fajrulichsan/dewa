package com.astra.dewa.web.command.render.diskon.testcar;

import com.astra.dewa.model.DiskonTestCar;
import com.astra.dewa.service.DiskonTestCarLocalServiceUtil;
import com.astra.dewa.service.TipeKendaraanLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.astra.dewa.utils.KuartalUtils;
import com.astra.dewa.web.constants.DewaWebKeys;
import com.astra.dewa.web.constants.DewaWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
   immediate = true,
   property = {
      "javax.portlet.name=" + DewaWebPortletKeys.DISKON_TEST_CAR,
      "mvc.command.name=/diskon-test-car-list"
   },
   service = MVCResourceCommand.class
)
public class DiskonTestCarListRenderCommand extends BaseMVCResourceCommand {
   private final Log _log = LogFactoryUtil.getLog(DiskonTestCarListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

      String dealerId = httpReq.getParameter("dealer");
      String tahun = httpReq.getParameter("tahun");
      String kuartal = httpReq.getParameter("kuartal");
      String tipeKendaraan = httpReq.getParameter("tipeKendaraan");

      int acknowledge = 0;
      int count = 0;

      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();

      try {
         AuthTokenUtil.checkCSRFToken(httpReq, this.getClass().getName());

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

         List<DiskonTestCar> diskonTestCars = new ArrayList<>();

         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));

         diskonTestCars = DiskonTestCarLocalServiceUtil.dynamicQuery(query);

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
               dto.put("kuartal", diskonTestCar.getKuartalId());
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
         if (e instanceof PrincipalException) {
            _log.error("You are not authorized to access resource. Possible CSRF attack. " + "UserId: " + PortalUtil.getUserId(httpReq));
            _log.error("Invalid CSRF token!  Token: " + ParamUtil.get(httpReq, "p_auth", "none"), e);
            jsonMessage = ERROR(401, "Unauthorized request!");
         } else {
            _log.error(e.getMessage(), e);
            jsonMessage = ERROR(500, e.getMessage());
         }
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}