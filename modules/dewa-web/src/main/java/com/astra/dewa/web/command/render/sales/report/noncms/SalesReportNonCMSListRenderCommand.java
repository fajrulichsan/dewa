package com.astra.dewa.web.command.render.sales.report.noncms;

import com.astra.dewa.model.Dealer;
import com.astra.dewa.model.SalesReport;
import com.astra.dewa.model.UsersDealers;
import com.astra.dewa.service.DealerLocalServiceUtil;
import com.astra.dewa.service.SalesReportLocalServiceUtil;
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
import java.util.Date;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

/**
 * @author psmafifd1401
 */
@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.SALES_REPORT_NONCMS,
            "mvc.command.name=/non-cms/sales-report/list"
      },
      service = MVCResourceCommand.class
)
public class SalesReportNonCMSListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(this.getClass().getName());

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

      String dealerId = httpReq.getParameter("dealerId");
      String periode = httpReq.getParameter("periode");
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

         DynamicQuery query = SalesReportLocalServiceUtil.dynamicQuery();
         if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
         }
         if (!periode.equalsIgnoreCase("ALL") && !periode.isEmpty()) {
            Date periodDate = DateUtil.stringToDate(periode);
            query.add(RestrictionsFactoryUtil.eq("Periode", periodDate));
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

         List<SalesReport> salesReports = SalesReportLocalServiceUtil.dynamicQuery(query);

         for (SalesReport salesReport : salesReports) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("no", count);
            dto.put("id", salesReport.getId());
            dto.put("fileName", salesReport.getFileName());
            dto.put("periode", DateUtil.dateToString(salesReport.getPeriode()));
            dto.put("periodeSort", salesReport.getModifiedDate().getTime());
            dto.put("uploadDate", DateUtil.dateToString(salesReport.getModifiedDate(), DewaWebKeys.DATE_FORMAT_SLASH_24_H));
            dto.put("uploadDateSort", salesReport.getModifiedDate().getTime());
            jsonData.put(dto);
         }

         acknowledge = 1;
         count = salesReports.size();
         jsonMessage = SUCCESS(200, "OK");
      } catch (Exception e) {
         LOG.error(e);
         jsonMessage = ERROR(500, e.getMessage());
      }
      JSONObject jsonObject = FORMAT(acknowledge, count, 1, 1, 1, 1, jsonData, jsonMessage);
      jsonObject.put("role", role);
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}
