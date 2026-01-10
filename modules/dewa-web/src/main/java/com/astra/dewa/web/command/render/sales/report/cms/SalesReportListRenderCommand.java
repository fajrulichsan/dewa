package com.astra.dewa.web.command.render.sales.report.cms;

import com.astra.dewa.model.SalesReport;
import com.astra.dewa.service.SalesReportLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
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
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.astra.dewa.utils.JSONResponseFormatUtil.ERROR;
import static com.astra.dewa.utils.JSONResponseFormatUtil.FORMAT;
import static com.astra.dewa.utils.JSONResponseFormatUtil.SUCCESS;

@Component(
      immediate = true,
      property = {
            "javax.portlet.name=" + DewaWebPortletKeys.SALES_REPORT,
            "mvc.command.name=/sales-report-list"
      },
      service = MVCResourceCommand.class
)
public class SalesReportListRenderCommand extends BaseMVCResourceCommand {
   private final Log LOG = LogFactoryUtil.getLog(SalesReportListRenderCommand.class);

   @Override
   protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
      HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
      String dealerId = httpReq.getParameter("dealerId");
      String periode = httpReq.getParameter("periode");

      int acknowledge = 0;
      int count = 0;
      JSONArray jsonData = JSONFactoryUtil.createJSONArray();
      JSONObject jsonMessage;
      try {
         List<SalesReport> salesReports = new ArrayList<>();

         DynamicQuery query = SalesReportLocalServiceUtil.dynamicQuery();
         if (!dealerId.equalsIgnoreCase("ALL") && !dealerId.isEmpty()) {
            query.add(RestrictionsFactoryUtil.eq("DealerId", Integer.parseInt(dealerId)));
         }
         if (!periode.equalsIgnoreCase("ALL") && !periode.isEmpty()) {
            Date periodDate = DateUtil.stringToDate(periode, DewaWebKeys.DATE_FORMAT_DOT);
            query.add(RestrictionsFactoryUtil.eq("Periode", periodDate));
         }
         query.add(RestrictionsFactoryUtil.eq("RowStatus", true));
         query.addOrder(OrderFactoryUtil.desc("ModifiedDate"));

         salesReports = SalesReportLocalServiceUtil.dynamicQuery(query);

         for (SalesReport salesReport : salesReports) {
            count++;
            JSONObject dto = JSONFactoryUtil.createJSONObject();
            dto.put("id", salesReport.getId());
            dto.put("no", count);
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
      ServletResponseUtil.write(PortalUtil.getHttpServletResponse(response), jsonObject.toJSONString());
   }
}
