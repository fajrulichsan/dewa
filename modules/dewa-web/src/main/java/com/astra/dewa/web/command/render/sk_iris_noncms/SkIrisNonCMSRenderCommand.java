package com.astra.dewa.web.command.render.sk_iris_noncms;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.SkIris;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.SkIrisLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
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
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;
import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;

/**
 * @author psmafifd1401
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.SK_IRIS_NONCMS,
            "mvc.command.name=/sk-iris-non-cms-list"
      },
      service = MVCResourceCommand.class
)
public class SkIrisNonCMSRenderCommand extends BaseMVCResourceCommand {
   private final Log log = LogFactoryUtil.getLog(SkIrisNonCMSRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String tahun = httpReq.getParameter("tahun");
      String periode = httpReq.getParameter("periode");

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;

      try {
         UsersDealers user = RoleDealerUtils.userId(themeDisplay.getUserId());
         assert user != null;
         int roleGroupId = RoleDealerUtils.getUserRoleGroup(user.getUserId());

         DynamicQuery query = SkIrisLocalServiceUtil.dynamicQuery();
         if (!tahun.equalsIgnoreCase("ALL") && !tahun.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("Tahun", Integer.parseInt(tahun)));
         }
         if (!periode.equalsIgnoreCase("ALL") && !periode.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("Periode", periode));
         }

         if (roleGroupId == RolesEnum.HO_DEALER.getId()) {
            Dealer dealer = DealerLocalServiceUtil.getDealer(user.getDealerId());
            DynamicQuery dealerQuery = DealerLocalServiceUtil.dynamicQuery()
                  .add(RestrictionsFactoryUtil.eq("KodeHo", dealer.getKodeHo()))
                  .add(RestrictionsFactoryUtil.eq("RowStatus", true))
                  .setProjection(PropertyFactoryUtil.forName("Id"));
            query.add(PropertyFactoryUtil.forName("DealerId").in(dealerQuery));
         }

         query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));

         List<SkIris> skIriss = SkIrisLocalServiceUtil.dynamicQuery(query);

         for (SkIris skIris : skIriss) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", skIris.getId());
            dto.put("periode", skIris.getPeriode());
            dto.put("periodeSort", DateUtil.getMonthNumberByName(skIris.getPeriode()));
            dto.put("tahun", skIris.getTahun());
            dto.put("wilayahId", skIris.getWilayahId());
            dto.put("fileId", skIris.getFileId());
            dto.put("fileName", skIris.getFileName());
            dto.put("filePath", skIris.getFilePath());
            dto.put("uploadDate", DateUtil.dateToString(skIris.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H));
            dto.put("uploadDateSort", skIris.getModifiedDate().getTime());
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = skIriss.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         log.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}
